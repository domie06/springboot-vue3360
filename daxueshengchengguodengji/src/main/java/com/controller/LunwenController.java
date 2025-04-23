
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
 * 论文
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/lunwen")
public class LunwenController {
    private static final Logger logger = LoggerFactory.getLogger(LunwenController.class);

    @Autowired
    private LunwenService lunwenService;


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
        PageUtils page = lunwenService.queryPage(params);

        //字典表数据转换
        List<LunwenView> list =(List<LunwenView>)page.getList();
        for(LunwenView c:list){
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
        LunwenEntity lunwen = lunwenService.selectById(id);
        if(lunwen !=null){
            //entity转view
            LunwenView view = new LunwenView();
            BeanUtils.copyProperties( lunwen , view );//把实体数据重构到view中

                //级联表
                XueshengEntity xuesheng = xueshengService.selectById(lunwen.getXueshengId());
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
    public R save(@RequestBody LunwenEntity lunwen, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,lunwen:{}",this.getClass().getName(),lunwen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            lunwen.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<LunwenEntity> queryWrapper = new EntityWrapper<LunwenEntity>()
            .eq("xuesheng_id", lunwen.getXueshengId())
            .eq("lunwen_uuid_number", lunwen.getLunwenUuidNumber())
            .eq("lunwen_name", lunwen.getLunwenName())
            .eq("lunwen_types", lunwen.getLunwenTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LunwenEntity lunwenEntity = lunwenService.selectOne(queryWrapper);
        if(lunwenEntity==null){
            lunwen.setInsertTime(new Date());
            lunwen.setCreateTime(new Date());
            lunwenService.insert(lunwen);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LunwenEntity lunwen, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,lunwen:{}",this.getClass().getName(),lunwen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            lunwen.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<LunwenEntity> queryWrapper = new EntityWrapper<LunwenEntity>()
            .notIn("id",lunwen.getId())
            .andNew()
            .eq("xuesheng_id", lunwen.getXueshengId())
            .eq("lunwen_uuid_number", lunwen.getLunwenUuidNumber())
            .eq("lunwen_name", lunwen.getLunwenName())
            .eq("lunwen_types", lunwen.getLunwenTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LunwenEntity lunwenEntity = lunwenService.selectOne(queryWrapper);
        if("".equals(lunwen.getLunwenFile()) || "null".equals(lunwen.getLunwenFile())){
                lunwen.setLunwenFile(null);
        }
        if(lunwenEntity==null){
            lunwenService.updateById(lunwen);//根据id更新
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
        lunwenService.deleteBatchIds(Arrays.asList(ids));
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
            List<LunwenEntity> lunwenList = new ArrayList<>();//上传的东西
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
                            LunwenEntity lunwenEntity = new LunwenEntity();
//                            lunwenEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            lunwenEntity.setLunwenUuidNumber(data.get(0));                    //论文编号 要改的
//                            lunwenEntity.setLunwenName(data.get(0));                    //论文标题 要改的
//                            lunwenEntity.setLunwenTypes(Integer.valueOf(data.get(0)));   //论文类型 要改的
//                            lunwenEntity.setLunwenFile(data.get(0));                    //论文文件 要改的
//                            lunwenEntity.setLunwenContent("");//详情和图片
//                            lunwenEntity.setInsertTime(date);//时间
//                            lunwenEntity.setCreateTime(date);//时间
                            lunwenList.add(lunwenEntity);


                            //把要查询是否重复的字段放入map中
                                //论文编号
                                if(seachFields.containsKey("lunwenUuidNumber")){
                                    List<String> lunwenUuidNumber = seachFields.get("lunwenUuidNumber");
                                    lunwenUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> lunwenUuidNumber = new ArrayList<>();
                                    lunwenUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("lunwenUuidNumber",lunwenUuidNumber);
                                }
                        }

                        //查询是否重复
                         //论文编号
                        List<LunwenEntity> lunwenEntities_lunwenUuidNumber = lunwenService.selectList(new EntityWrapper<LunwenEntity>().in("lunwen_uuid_number", seachFields.get("lunwenUuidNumber")));
                        if(lunwenEntities_lunwenUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LunwenEntity s:lunwenEntities_lunwenUuidNumber){
                                repeatFields.add(s.getLunwenUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [论文编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        lunwenService.insertBatch(lunwenList);
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
