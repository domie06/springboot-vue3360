
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
 * 竞赛获奖
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jingsai")
public class JingsaiController {
    private static final Logger logger = LoggerFactory.getLogger(JingsaiController.class);

    @Autowired
    private JingsaiService jingsaiService;


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
        PageUtils page = jingsaiService.queryPage(params);

        //字典表数据转换
        List<JingsaiView> list =(List<JingsaiView>)page.getList();
        for(JingsaiView c:list){
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
        JingsaiEntity jingsai = jingsaiService.selectById(id);
        if(jingsai !=null){
            //entity转view
            JingsaiView view = new JingsaiView();
            BeanUtils.copyProperties( jingsai , view );//把实体数据重构到view中

                //级联表
                XueshengEntity xuesheng = xueshengService.selectById(jingsai.getXueshengId());
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
    public R save(@RequestBody JingsaiEntity jingsai, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jingsai:{}",this.getClass().getName(),jingsai.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            jingsai.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JingsaiEntity> queryWrapper = new EntityWrapper<JingsaiEntity>()
            .eq("xuesheng_id", jingsai.getXueshengId())
            .eq("jingsai_name", jingsai.getJingsaiName())
            .eq("jingsai_types", jingsai.getJingsaiTypes())
            .eq("jingsai_address", jingsai.getJingsaiAddress())
            .eq("jingsai_mingci", jingsai.getJingsaiMingci())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JingsaiEntity jingsaiEntity = jingsaiService.selectOne(queryWrapper);
        if(jingsaiEntity==null){
            jingsai.setInsertTime(new Date());
            jingsai.setCreateTime(new Date());
            jingsaiService.insert(jingsai);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JingsaiEntity jingsai, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jingsai:{}",this.getClass().getName(),jingsai.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            jingsai.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<JingsaiEntity> queryWrapper = new EntityWrapper<JingsaiEntity>()
            .notIn("id",jingsai.getId())
            .andNew()
            .eq("xuesheng_id", jingsai.getXueshengId())
            .eq("jingsai_name", jingsai.getJingsaiName())
            .eq("jingsai_types", jingsai.getJingsaiTypes())
            .eq("jingsai_address", jingsai.getJingsaiAddress())
            .eq("jingsai_mingci", jingsai.getJingsaiMingci())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JingsaiEntity jingsaiEntity = jingsaiService.selectOne(queryWrapper);
        if("".equals(jingsai.getJingsaiPhoto()) || "null".equals(jingsai.getJingsaiPhoto())){
                jingsai.setJingsaiPhoto(null);
        }
        if("".equals(jingsai.getJingsaiFile()) || "null".equals(jingsai.getJingsaiFile())){
                jingsai.setJingsaiFile(null);
        }
        if(jingsaiEntity==null){
            jingsaiService.updateById(jingsai);//根据id更新
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
        jingsaiService.deleteBatchIds(Arrays.asList(ids));
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
            List<JingsaiEntity> jingsaiList = new ArrayList<>();//上传的东西
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
                            JingsaiEntity jingsaiEntity = new JingsaiEntity();
//                            jingsaiEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            jingsaiEntity.setJingsaiName(data.get(0));                    //竞赛名称 要改的
//                            jingsaiEntity.setJingsaiTypes(Integer.valueOf(data.get(0)));   //竞赛类型 要改的
//                            jingsaiEntity.setJingsaiAddress(data.get(0));                    //竞赛地址 要改的
//                            jingsaiEntity.setJingsaiMingci(data.get(0));                    //名次 要改的
//                            jingsaiEntity.setJingsaiPhoto("");//详情和图片
//                            jingsaiEntity.setJingsaiFile(data.get(0));                    //相关文件 要改的
//                            jingsaiEntity.setHuojiangTime(sdf.parse(data.get(0)));          //获奖时间 要改的
//                            jingsaiEntity.setJingsaiContent("");//详情和图片
//                            jingsaiEntity.setInsertTime(date);//时间
//                            jingsaiEntity.setCreateTime(date);//时间
                            jingsaiList.add(jingsaiEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jingsaiService.insertBatch(jingsaiList);
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
