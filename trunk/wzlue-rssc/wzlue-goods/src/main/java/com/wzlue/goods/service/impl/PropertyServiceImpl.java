package com.wzlue.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.goods.dao.PropertyDao;
import com.wzlue.goods.entity.PropertyEntity;
import com.wzlue.goods.service.PropertyService;



@Service("propertyService")
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	private PropertyDao propertyDao;
	
	@Override
	public PropertyEntity queryObject(Long id){
		return propertyDao.queryObject(id);
	}
	
	@Override
	public List<PropertyEntity> queryList(Map<String, Object> map){
		return propertyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return propertyDao.queryTotal(map);
	}
	
	@Override
	public void save(PropertyEntity property){
		propertyDao.save(property);
	}
	
	@Override
	public void update(PropertyEntity property){
		propertyDao.update(property);
	}
	
	@Override
	public void delete(Long id){
		propertyDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		propertyDao.deleteBatch(ids);
	}
	
}
