package com.wzlue.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.goods.dao.BrandDao;
import com.wzlue.goods.entity.BrandEntity;
import com.wzlue.goods.service.BrandService;



@Service("brandService")
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandDao brandDao;
	
	@Override
	public BrandEntity queryObject(Long id){
		return brandDao.queryObject(id);
	}
	
	@Override
	public List<BrandEntity> queryList(Map<String, Object> map){
		return brandDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return brandDao.queryTotal(map);
	}
	
	@Override
	public void save(BrandEntity brand){
		brandDao.save(brand);
	}
	
	@Override
	public void update(BrandEntity brand){
		brandDao.update(brand);
	}
	
	@Override
	public void delete(Long id){
		brandDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		brandDao.deleteBatch(ids);
	}
	
}
