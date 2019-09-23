package com.wzlue.goods.service;

import com.wzlue.goods.entity.GoodsPropertyEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
public interface GoodsPropertyService {
	
	GoodsPropertyEntity queryObject(Long id);
	
	List<GoodsPropertyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodsPropertyEntity goodsProperty);
	
	void update(GoodsPropertyEntity goodsProperty);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
