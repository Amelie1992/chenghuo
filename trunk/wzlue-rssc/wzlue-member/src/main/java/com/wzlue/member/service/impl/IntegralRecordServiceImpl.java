package com.wzlue.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.member.dao.IntegralRecordDao;
import com.wzlue.member.entity.IntegralRecordEntity;
import com.wzlue.member.service.IntegralRecordService;



@Service("integralRecordService")
public class IntegralRecordServiceImpl implements IntegralRecordService {
	@Autowired
	private IntegralRecordDao integralRecordDao;
	
	@Override
	public IntegralRecordEntity queryObject(Long id){
		return integralRecordDao.queryObject(id);
	}
	
	@Override
	public List<IntegralRecordEntity> queryList(Map<String, Object> map){
		return integralRecordDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return integralRecordDao.queryTotal(map);
	}
	
	@Override
	public void save(IntegralRecordEntity integralRecord){
		integralRecordDao.save(integralRecord);
	}
	
	@Override
	public void update(IntegralRecordEntity integralRecord){
		integralRecordDao.update(integralRecord);
	}
	
	@Override
	public void delete(Long id){
		integralRecordDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		integralRecordDao.deleteBatch(ids);
	}
	
}
