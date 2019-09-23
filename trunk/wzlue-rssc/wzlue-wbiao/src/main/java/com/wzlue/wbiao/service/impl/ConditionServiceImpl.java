package com.wzlue.wbiao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.wbiao.dao.ConditionDao;
import com.wzlue.wbiao.entity.ConditionEntity;
import com.wzlue.wbiao.service.ConditionService;



@Service("conditionService")
public class ConditionServiceImpl implements ConditionService {
	@Autowired
	private ConditionDao conditionDao;
	
	@Override
	public ConditionEntity queryObject(Long id){
		return conditionDao.queryObject(id);
	}
	
	@Override
	public List<ConditionEntity> queryList(Map<String, Object> map){
		return conditionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return conditionDao.queryTotal(map);
	}
	
	@Override
	public void save(ConditionEntity condition){
		conditionDao.save(condition);
	}
	
	@Override
	public void update(ConditionEntity condition){
		conditionDao.update(condition);
	}
	
	@Override
	public void delete(Long id){
		conditionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		conditionDao.deleteBatch(ids);
	}
	
}
