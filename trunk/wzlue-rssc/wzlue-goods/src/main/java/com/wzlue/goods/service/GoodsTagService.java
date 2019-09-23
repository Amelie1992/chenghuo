package com.wzlue.goods.service;

import com.wzlue.goods.entity.GoodsTagEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品标签
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 15:03:21
 */
public interface GoodsTagService {
	
	GoodsTagEntity queryObject(Long id);
	
	List<GoodsTagEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodsTagEntity goodsTag);
	
	void update(GoodsTagEntity goodsTag);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
