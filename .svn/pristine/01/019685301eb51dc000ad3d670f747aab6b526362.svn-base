package com.wzlue.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.order.dao.OrderGoodsDao;
import com.wzlue.order.entity.OrderGoodsEntity;
import com.wzlue.order.service.OrderGoodsService;



@Service("orderGoodsService")
public class OrderGoodsServiceImpl implements OrderGoodsService {
	@Autowired
	private OrderGoodsDao orderGoodsDao;
	
	@Override
	public OrderGoodsEntity queryObject(Long id){
		return orderGoodsDao.queryObject(id);
	}
	
	@Override
	public List<OrderGoodsEntity> queryList(Map<String, Object> map){
		return orderGoodsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderGoodsDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderGoodsEntity orderGoods){
		orderGoodsDao.save(orderGoods);
	}
	
	@Override
	public void update(OrderGoodsEntity orderGoods){
		orderGoodsDao.update(orderGoods);
	}
	
	@Override
	public void delete(Long id){
		orderGoodsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		orderGoodsDao.deleteBatch(ids);
	}
	
}
