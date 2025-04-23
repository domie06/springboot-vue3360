
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 荣誉
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/rongyu")
public class RongyuController {
    private static final Logger logger = LoggerFactory.getLogger(RongyuController.class);

    @Autowired
    private RongyuService rongyuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private XueshengService xueshengService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("xueshengId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = rongyuService.queryPage(params);

        //字典表数据转换
        List<RongyuView> list =(List<RongyuView>)page.getList();
        for(RongyuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        RongyuEntity rongyu = rongyuService.selectById(id);
        if(rongyu !=null){
            //entity转view
            RongyuView view = new RongyuView();
            BeanUtils.copyProperties( rongyu , view );//把实体数据重构到view中

                //级联表
                XueshengEntity xuesheng = xueshengService.selectById(rongyu.getXueshengId());
                if(xuesheng != null){
                    BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueshengId(xuesheng.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody RongyuEntity rongyu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,rongyu:{}",this.getClass().getName(),rongyu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            rongyu.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<RongyuEntity> queryWrapper = new EntityWrapper<RongyuEntity>()
            .eq("xuesheng_id", rongyu.getXueshengId())
            .eq("rongyu_name", rongyu.getRongyuName())
            .eq("rongyu_types", rongyu.getRongyuTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        RongyuEntity rongyuEntity = rongyuService.selectOne(queryWrapper);
        if(rongyuEntity==null){
            rongyu.setInsertTime(new Date());
            rongyu.setCreateTime(new Date());
            rongyuService.insert(rongyu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody RongyuEntity rongyu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,rongyu:{}",this.getClass().getName(),rongyu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            rongyu.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<RongyuEntity> queryWrapper = new EntityWrapper<RongyuEntity>()
            .notIn("id",rongyu.getId())
            .andNew()
            .eq("xuesheng_id", rongyu.getXueshengId())
            .eq("rongyu_name", rongyu.getRongyuName())
            .eq("rongyu_types", rongyu.getRongyuTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        RongyuEntity rongyuEntity = rongyuService.selectOne(queryWrapper);
        if("".equals(rongyu.getRongyuPhoto()) || "null".equals(rongyu.getRongyuPhoto())){
                rongyu.setRongyuPhoto(null);
        }
        if("".equals(rongyu.getRongyuFile()) || "null".equals(rongyu.getRongyuFile())){
                rongyu.setRongyuFile(null);
        }
        if(rongyuEntity==null){
            rongyuService.updateById(rongyu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        rongyuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<RongyuEntity> rongyuList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            RongyuEntity rongyuEntity = new RongyuEntity();
//                            rongyuEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            rongyuEntity.setRongyuName(data.get(0));                    //荣誉名称 要改的
//                            rongyuEntity.setRongyuTypes(Integer.valueOf(data.get(0)));   //荣誉类型 要改的
//                            rongyuEntity.setRongyuPhoto("");//详情和图片
//                            rongyuEntity.setRongyuFile(data.get(0));                    //相关文件 要改的
//                            rongyuEntity.setHuojiangTime(sdf.parse(data.get(0)));          //获取时间 要改的
//                            rongyuEntity.setRongyuContent("");//详情和图片
//                            rongyuEntity.setInsertTime(date);//时间
//                            rongyuEntity.setCreateTime(date);//时间
                            rongyuList.add(rongyuEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        rongyuService.insertBatch(rongyuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
