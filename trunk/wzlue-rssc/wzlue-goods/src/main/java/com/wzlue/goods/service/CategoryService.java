package com.wzlue.goods.service;

import com.wzlue.goods.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 分类
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
public interface CategoryService {
	
	CategoryEntity queryObject(Long id);
	
	List<CategoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CategoryEntity category);
	
	void update(CategoryEntity category);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);


}
