package com.wzlue.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.member.dao.SignInRecordDao;
import com.wzlue.member.entity.SignInRecordEntity;
import com.wzlue.member.service.SignInRecordService;



@Service("signInRecordService")
public class SignInRecordServiceImpl implements SignInRecordService {
	@Autowired
	private SignInRecordDao signInRecordDao;
	
	@Override
	public SignInRecordEntity queryObject(Long id){
		return signInRecordDao.queryObject(id);
	}
	
	@Override
	public List<SignInRecordEntity> queryList(Map<String, Object> map){
		return signInRecordDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return signInRecordDao.queryTotal(map);
	}
	
	@Override
	public void save(SignInRecordEntity signInRecord){
		signInRecordDao.save(signInRecord);
	}
	
	@Override
	public void update(SignInRecordEntity signInRecord){
		signInRecordDao.update(signInRecord);
	}
	
	@Override
	public void delete(Long id){
		signInRecordDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		signInRecordDao.deleteBatch(ids);
	}
	
}
