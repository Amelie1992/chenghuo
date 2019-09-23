package com.wzlue.wbiao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.wbiao.dao.RecoveryPicDao;
import com.wzlue.wbiao.entity.RecoveryPicEntity;
import com.wzlue.wbiao.service.RecoveryPicService;



@Service("recoveryPicService")
public class RecoveryPicServiceImpl implements RecoveryPicService {
	@Autowired
	private RecoveryPicDao recoveryPicDao;
	
	@Override
	public RecoveryPicEntity queryObject(Long id){
		return recoveryPicDao.queryObject(id);
	}
	
	@Override
	public List<RecoveryPicEntity> queryList(Map<String, Object> map){
		return recoveryPicDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return recoveryPicDao.queryTotal(map);
	}
	
	@Override
	public void save(RecoveryPicEntity recoveryPic){
		recoveryPicDao.save(recoveryPic);
	}
	
	@Override
	public void update(RecoveryPicEntity recoveryPic){
		recoveryPicDao.update(recoveryPic);
	}
	
	@Override
	public void delete(Long id){
		recoveryPicDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		recoveryPicDao.deleteBatch(ids);
	}
	
}
