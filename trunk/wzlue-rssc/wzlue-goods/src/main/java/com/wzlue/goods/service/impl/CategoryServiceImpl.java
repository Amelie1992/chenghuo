package com.wzlue.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.goods.dao.CategoryDao;
import com.wzlue.goods.entity.CategoryEntity;
import com.wzlue.goods.service.CategoryService;



@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public CategoryEntity queryObject(Long id){
		return categoryDao.queryObject(id);
	}
	
	@Override
	public List<CategoryEntity> queryList(Map<String, Object> map){
		return categoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return categoryDao.queryTotal(map);
	}
	
	@Override
	public void save(CategoryEntity category){
		categoryDao.save(category);
	}
	
	@Override
	public void update(CategoryEntity category){
		categoryDao.update(category);
	}
	
	@Override
	public void delete(Long id){
		categoryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		categoryDao.deleteBatch(ids);
	}



}
