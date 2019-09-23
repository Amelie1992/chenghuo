package com.wzlue.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.order.dao.OrderAddressDao;
import com.wzlue.order.entity.OrderAddressEntity;
import com.wzlue.order.service.OrderAddressService;



@Service("orderAddressService")
public class OrderAddressServiceImpl implements OrderAddressService {
	@Autowired
	private OrderAddressDao orderAddressDao;
	
	@Override
	public OrderAddressEntity queryObject(Long id){
		return orderAddressDao.queryObject(id);
	}
	
	@Override
	public List<OrderAddressEntity> queryList(Map<String, Object> map){
		return orderAddressDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderAddressDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderAddressEntity orderAddress){
		orderAddressDao.save(orderAddress);
	}
	
	@Override
	public void update(OrderAddressEntity orderAddress){
		orderAddressDao.update(orderAddress);
	}
	
	@Override
	public void delete(Long id){
		orderAddressDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		orderAddressDao.deleteBatch(ids);
	}
	
}
