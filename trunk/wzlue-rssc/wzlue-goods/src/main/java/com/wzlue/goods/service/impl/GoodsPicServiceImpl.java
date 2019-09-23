package com.wzlue.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.goods.dao.GoodsPicDao;
import com.wzlue.goods.entity.GoodsPicEntity;
import com.wzlue.goods.service.GoodsPicService;



@Service("goodsPicService")
public class GoodsPicServiceImpl implements GoodsPicService {
	@Autowired
	private GoodsPicDao goodsPicDao;
	
	@Override
	public GoodsPicEntity queryObject(Long id){
		return goodsPicDao.queryObject(id);
	}
	
	@Override
	public List<GoodsPicEntity> queryList(Map<String, Object> map){
		return goodsPicDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return goodsPicDao.queryTotal(map);
	}
	
	@Override
	public void save(GoodsPicEntity goodsPic){
		goodsPicDao.save(goodsPic);
	}
	
	@Override
	public void update(GoodsPicEntity goodsPic){
		goodsPicDao.update(goodsPic);
	}
	
	@Override
	public void delete(Long id){
		goodsPicDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		goodsPicDao.deleteBatch(ids);
	}
	
}
