package com.wzlue.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.order.dao.EvaluateDao;
import com.wzlue.order.entity.EvaluateEntity;
import com.wzlue.order.service.EvaluateService;



@Service("evaluateService")
public class EvaluateServiceImpl implements EvaluateService {
	@Autowired
	private EvaluateDao evaluateDao;
	
	@Override
	public EvaluateEntity queryObject(Long id){
		return evaluateDao.queryObject(id);
	}
	
	@Override
	public List<EvaluateEntity> queryList(Map<String, Object> map){
		return evaluateDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return evaluateDao.queryTotal(map);
	}
	
	@Override
	public void save(EvaluateEntity evaluate){
		evaluateDao.save(evaluate);
	}
	
	@Override
	public void update(EvaluateEntity evaluate){
		evaluateDao.update(evaluate);
	}
	
	@Override
	public void delete(Long id){
		evaluateDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		evaluateDao.deleteBatch(ids);
	}
	
}
