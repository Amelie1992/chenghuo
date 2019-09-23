package com.wzlue.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.goods.dao.GoodsSpecDao;
import com.wzlue.goods.entity.GoodsSpecEntity;
import com.wzlue.goods.service.GoodsSpecService;



@Service("goodsSpecService")
public class GoodsSpecServiceImpl implements GoodsSpecService {
	@Autowired
	private GoodsSpecDao goodsSpecDao;
	
	@Override
	public GoodsSpecEntity queryObject(Long id){
		return goodsSpecDao.queryObject(id);
	}
	
	@Override
	public List<GoodsSpecEntity> queryList(Map<String, Object> map){
		return goodsSpecDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return goodsSpecDao.queryTotal(map);
	}
	
	@Override
	public void save(GoodsSpecEntity goodsSpec){
		goodsSpecDao.save(goodsSpec);
	}
	
	@Override
	public void update(GoodsSpecEntity goodsSpec){
		goodsSpecDao.update(goodsSpec);
	}
	
	@Override
	public void delete(Long id){
		goodsSpecDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		goodsSpecDao.deleteBatch(ids);
	}
	
}
