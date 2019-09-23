package com.wzlue.advert.service.impl;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.advert.dao.AdvertDao;
import com.wzlue.advert.entity.AdvertEntity;
import com.wzlue.advert.service.AdvertService;



@Service("advertService")
public class AdvertServiceImpl implements AdvertService {
	@Autowired
	private AdvertDao advertDao;
	
	@Override
	public AdvertEntity queryObject(Long id){
		return advertDao.queryObject(id);
	}
	
	@Override
	public List<AdvertEntity> queryList(Map<String, Object> map){
		return advertDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return advertDao.queryTotal(map);
	}
	
	@Override
	public void save(AdvertEntity advert){
		advertDao.save(advert);
	}
	
	@Override
	public void update(AdvertEntity advert){
		advertDao.update(advert);
	}
	
	@Override
	public void delete(Long id){
		advertDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		advertDao.deleteBatch(ids);
	}

	@Override
	public List<AdvertEntity> mobileAll(Integer positionId) {
		return advertDao.queryList(ImmutableMap.of("positionId",positionId));
	}

}
