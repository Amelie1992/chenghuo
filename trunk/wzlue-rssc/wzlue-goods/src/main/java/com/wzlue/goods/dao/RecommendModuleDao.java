package com.wzlue.goods.dao;

import com.wzlue.common.base.BaseDao;
import com.wzlue.goods.entity.RecommendModuleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 推荐商品模块表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 10:06:35
 */
@Mapper
public interface RecommendModuleDao extends BaseDao<RecommendModuleEntity> {

	//接口一对多模块+商品
	List<RecommendModuleEntity> queryListApi(Map<String, Object> map);
}
