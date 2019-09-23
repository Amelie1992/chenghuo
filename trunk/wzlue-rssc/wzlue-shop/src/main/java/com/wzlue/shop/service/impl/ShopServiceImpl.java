package com.wzlue.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.shop.dao.ShopDao;
import com.wzlue.shop.entity.ShopEntity;
import com.wzlue.shop.service.ShopService;



@Service("sysShopService")
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao sysShopDao;
	
	@Override
	public ShopEntity queryObject(Long id){
		return sysShopDao.queryObject(id);
	}
	
	@Override
	public List<ShopEntity> queryList(Map<String, Object> map){
		return sysShopDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysShopDao.queryTotal(map);
	}
	
	@Override
	public void save(ShopEntity sysShop){
		sysShopDao.save(sysShop);
	}
	
	@Override
	public void update(ShopEntity sysShop){
		sysShopDao.update(sysShop);
	}
	
	@Override
	public void delete(Long id){
		sysShopDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sysShopDao.deleteBatch(ids);
	}
	
}
