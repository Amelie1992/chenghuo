package com.wzlue.goods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzlue.goods.dao.FreightDao;
import com.wzlue.goods.entity.FreightEntity;
import com.wzlue.goods.service.FreightService;



@Service("freightService")
public class FreightServiceImpl implements FreightService {
	@Autowired
	private FreightDao freightDao;
	
	@Override
	public FreightEntity queryObject(Long id){
		return freightDao.queryObject(id);
	}
	
	@Override
	public List<FreightEntity> queryList(Map<String, Object> map){
		return freightDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return freightDao.queryTotal(map);
	}
	
	@Override
	public void save(FreightEntity freight){
		freightDao.save(freight);
	}
	
	@Override
	public void update(FreightEntity freight){
		freightDao.update(freight);
	}
	
	@Override
	public void delete(Long id){
		freightDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		freightDao.deleteBatch(ids);
	}
	
}
