package com.wzlue.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.goods.dao.GoodsPropertyDao;
import com.wzlue.goods.entity.GoodsPropertyEntity;
import com.wzlue.goods.service.GoodsPropertyService;



@Service("goodsPropertyService")
public class GoodsPropertyServiceImpl implements GoodsPropertyService {
	@Autowired
	private GoodsPropertyDao goodsPropertyDao;
	
	@Override
	public GoodsPropertyEntity queryObject(Long id){
		return goodsPropertyDao.queryObject(id);
	}
	
	@Override
	public List<GoodsPropertyEntity> queryList(Map<String, Object> map){
		return goodsPropertyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return goodsPropertyDao.queryTotal(map);
	}
	
	@Override
	public void save(GoodsPropertyEntity goodsProperty){
		goodsPropertyDao.save(goodsProperty);
	}
	
	@Override
	public void update(GoodsPropertyEntity goodsProperty){
		goodsPropertyDao.update(goodsProperty);
	}
	
	@Override
	public void delete(Long id){
		goodsPropertyDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		goodsPropertyDao.deleteBatch(ids);
	}
	
}
