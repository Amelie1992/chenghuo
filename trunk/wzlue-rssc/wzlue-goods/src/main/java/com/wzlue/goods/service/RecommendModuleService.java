package com.wzlue.goods.service;

import com.wzlue.goods.entity.RecommendModuleEntity;

import java.util.List;
import java.util.Map;

/**
 * 推荐商品模块表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 10:06:35
 */
public interface RecommendModuleService {
	
	RecommendModuleEntity queryObject(Long id);
	
	List<RecommendModuleEntity> queryList(Map<String, Object> map);

	List<RecommendModuleEntity> queryListApi(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RecommendModuleEntity gRecommendModule);
	
	void update(RecommendModuleEntity gRecommendModule);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
