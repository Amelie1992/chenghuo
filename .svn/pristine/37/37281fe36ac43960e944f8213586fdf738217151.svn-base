package com.wzlue.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wzlue.order.dao.LogisticsDao;
import com.wzlue.order.entity.LogisticsEntity;
import com.wzlue.order.service.LogisticsService;



@Service("logisticsService")
public class LogisticsServiceImpl implements LogisticsService {
	@Autowired
	private LogisticsDao logisticsDao;
	
	@Override
	public LogisticsEntity queryObject(Long id){
		return logisticsDao.queryObject(id);
	}
	
	@Override
	public List<LogisticsEntity> queryList(Map<String, Object> map){
		return logisticsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return logisticsDao.queryTotal(map);
	}
	
	@Override
	public void save(LogisticsEntity logistics){
		logisticsDao.save(logistics);
	}
	
	@Override
	public void update(LogisticsEntity logistics){
		logistics.setUpdateTime(new Date());
		logisticsDao.update(logistics);
	}
	
	@Override
	public void delete(Long id){
		logisticsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		logisticsDao.deleteBatch(ids);
	}
	
}
