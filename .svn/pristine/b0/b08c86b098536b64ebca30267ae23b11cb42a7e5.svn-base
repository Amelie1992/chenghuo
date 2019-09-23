package com.wzlue.wbiao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.wzlue.wbiao.dao.RecoveryDao;
import com.wzlue.wbiao.dao.RecoveryPicDao;
import com.wzlue.wbiao.entity.RecoveryEntity;
import com.wzlue.wbiao.entity.RecoveryPicEntity;
import com.wzlue.wbiao.service.RecoveryService;



@Service("recoveryService")
public class RecoveryServiceImpl implements RecoveryService {
	@Autowired
	private RecoveryDao recoveryDao;
	@Autowired
	private RecoveryPicDao recoveryPicDao;
	
	@Override
	public RecoveryEntity queryObject(Long id){
		return recoveryDao.queryObject(id);
	}
	
	@Override
	public List<RecoveryEntity> queryList(Map<String, Object> map){
		return recoveryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return recoveryDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(RecoveryEntity recovery){
		recoveryDao.save(recovery);
		String[] picUrls = recovery.getPicUrls();
		RecoveryPicEntity recoveryPic = new RecoveryPicEntity();
		for (int i = 0; i < picUrls.length; i++) {
			recoveryPic.setRecoveryId(recovery.getId());
			recoveryPic.setPicUrl(picUrls[i]);
			recoveryPicDao.save(recoveryPic);
		}
	}
	
	@Override
	public void update(RecoveryEntity recovery){
		recoveryDao.update(recovery);
	}
	
	@Override
	public void delete(Long id){
		recoveryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		recoveryDao.deleteBatch(ids);
	}
	
}
