package com.wzlue.contact.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.contact.dao.MMemberLogDao;
import com.wzlue.contact.entity.MMemberLogEntity;
import com.wzlue.contact.service.MMemberLogService;



@Service("mMemberLogService")
public class MMemberLogServiceImpl implements MMemberLogService {
	@Autowired
	private MMemberLogDao mMemberLogDao;
	
	@Override
	public MMemberLogEntity queryObject(Integer id){
		return mMemberLogDao.queryObject(id);
	}
	
	@Override
	public List<MMemberLogEntity> queryList(Map<String, Object> map){
		return mMemberLogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mMemberLogDao.queryTotal(map);
	}
	
	@Override
	public void save(MMemberLogEntity mMemberLog){
		mMemberLogDao.save(mMemberLog);
	}
	
	@Override
	public void update(MMemberLogEntity mMemberLog){
		mMemberLogDao.update(mMemberLog);
	}
	
	@Override
	public void delete(Integer id){
		mMemberLogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		mMemberLogDao.deleteBatch(ids);
	}
	
}
