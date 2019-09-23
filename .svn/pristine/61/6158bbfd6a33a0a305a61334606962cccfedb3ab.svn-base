package com.wzlue.order.service.impl;

import com.wzlue.order.dao.ServiceEvaluateDao;
import com.wzlue.order.entity.ServiceEvaluateEntity;
import com.wzlue.order.service.ServiceEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("serviceEvaluateService")
public class ServiceEvaluateServiceImpl implements ServiceEvaluateService {
	@Autowired
	private ServiceEvaluateDao serviceEvaluateDao;
	
	@Override
	public ServiceEvaluateEntity queryObject(Long id){
		return serviceEvaluateDao.queryObject(id);
	}
	
	@Override
	public List<ServiceEvaluateEntity> queryList(Map<String, Object> map){
		return serviceEvaluateDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return serviceEvaluateDao.queryTotal(map);
	}
	
	@Override
	public void save(ServiceEvaluateEntity serviceEvaluate){
		serviceEvaluateDao.save(serviceEvaluate);
	}
	
	@Override
	public void update(ServiceEvaluateEntity serviceEvaluate){
		serviceEvaluateDao.update(serviceEvaluate);
	}
	
	@Override
	public void delete(Long id){
		serviceEvaluateDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		serviceEvaluateDao.deleteBatch(ids);
	}
	
}
