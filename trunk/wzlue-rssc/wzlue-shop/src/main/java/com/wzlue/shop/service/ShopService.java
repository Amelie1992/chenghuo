package com.wzlue.shop.service;

import com.wzlue.shop.entity.ShopEntity;

import java.util.List;
import java.util.Map;

/**
 * 商家表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-03-26 15:44:44
 */
public interface ShopService {
	
	ShopEntity queryObject(Long id);
	
	List<ShopEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ShopEntity sysShop);
	
	void update(ShopEntity sysShop);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
