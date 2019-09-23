package com.wzlue.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.sys.dao.SysAreaDao;
import com.wzlue.sys.entity.SysAreaEntity;
import com.wzlue.sys.service.SysAreaService;



@Service("sysAreaService")
public class SysAreaServiceImpl implements SysAreaService {
	@Autowired
	private SysAreaDao sysAreaDao;
	
	@Override
	public SysAreaEntity queryObject(Integer id){
		return sysAreaDao.queryObject(id);
	}
	
	@Override
	public List<SysAreaEntity> queryList(Map<String, Object> map){
		return sysAreaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysAreaDao.queryTotal(map);
	}
	
	@Override
	public void save(SysAreaEntity sysArea){
		sysAreaDao.save(sysArea);
	}
	
	@Override
	public void update(SysAreaEntity sysArea){
		sysAreaDao.update(sysArea);
	}
	
	@Override
	public void delete(Integer id){
		sysAreaDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sysAreaDao.deleteBatch(ids);
	}

	@Override
	public List<SysAreaEntity> areaList(Map<String,Object> params) {
		return sysAreaDao.areaList(params);
	}
}
