package com.wzlue.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.order.dao.OrderLogisticsDao;
import com.wzlue.order.entity.OrderLogisticsEntity;
import com.wzlue.order.service.OrderLogisticsService;



@Service("orderLogisticsService")
public class OrderLogisticsServiceImpl implements OrderLogisticsService {
	@Autowired
	private OrderLogisticsDao orderLogisticsDao;
	
	@Override
	public OrderLogisticsEntity queryObject(Long id){
		return orderLogisticsDao.queryObject(id);
	}
	
	@Override
	public List<OrderLogisticsEntity> queryList(Map<String, Object> map){
		return orderLogisticsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderLogisticsDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderLogisticsEntity orderLogistics){
		orderLogisticsDao.save(orderLogistics);
	}
	
	@Override
	public void update(OrderLogisticsEntity orderLogistics){
		orderLogisticsDao.update(orderLogistics);
	}
	
	@Override
	public void delete(Long id){
		orderLogisticsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		orderLogisticsDao.deleteBatch(ids);
	}
	
}
