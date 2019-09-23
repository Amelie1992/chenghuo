package com.wzlue.goods.service;

import com.wzlue.goods.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
public interface GoodsService {
	
	GoodsEntity queryObject(Long id);
	
	List<GoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodsEntity goods);
	
	void update(GoodsEntity goods);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	void updateStatus(Long[] ids, Integer status);
}
