package com.wzlue.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.goods.dao.GoodsTagDao;
import com.wzlue.goods.entity.GoodsTagEntity;
import com.wzlue.goods.service.GoodsTagService;



@Service("goodsTagService")
public class GoodsTagServiceImpl implements GoodsTagService {
	@Autowired
	private GoodsTagDao goodsTagDao;
	
	@Override
	public GoodsTagEntity queryObject(Long id){
		return goodsTagDao.queryObject(id);
	}
	
	@Override
	public List<GoodsTagEntity> queryList(Map<String, Object> map){
		return goodsTagDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return goodsTagDao.queryTotal(map);
	}
	
	@Override
	public void save(GoodsTagEntity goodsTag){
		goodsTagDao.save(goodsTag);
	}
	
	@Override
	public void update(GoodsTagEntity goodsTag){
		goodsTagDao.update(goodsTag);
	}
	
	@Override
	public void delete(Long id){
		goodsTagDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		goodsTagDao.deleteBatch(ids);
	}
	
}
