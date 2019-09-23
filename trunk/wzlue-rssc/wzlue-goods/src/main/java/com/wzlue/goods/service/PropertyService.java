package com.wzlue.goods.service;

import com.wzlue.goods.entity.PropertyEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
public interface PropertyService {
	
	PropertyEntity queryObject(Long id);
	
	List<PropertyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PropertyEntity property);
	
	void update(PropertyEntity property);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
