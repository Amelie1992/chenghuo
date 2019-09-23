package com.wzlue.goods.service;

import com.wzlue.goods.entity.RecommendGoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 推荐商品表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 10:02:57
 */
public interface RecommendGoodsService {
	
	RecommendGoodsEntity queryObject(Long id);
	
	List<RecommendGoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RecommendGoodsEntity gRecommendGoods);
	
	void update(RecommendGoodsEntity gRecommendGoods);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
