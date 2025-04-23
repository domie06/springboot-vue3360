package com.dao;

import com.entity.JingsaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JingsaiView;

/**
 * 竞赛获奖 Dao 接口
 *
 * @author 
 */
public interface JingsaiDao extends BaseMapper<JingsaiEntity> {

   List<JingsaiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
