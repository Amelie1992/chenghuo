package com.wzlue.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.order.dao.OrderRefundPicDao;
import com.wzlue.order.entity.OrderRefundPicEntity;
import com.wzlue.order.service.OrderRefundPicService;



@Service("orderRefundPicService")
public class OrderRefundPicServiceImpl implements OrderRefundPicService {
	@Autowired
	private OrderRefundPicDao orderRefundPicDao;
	
	@Override
	public OrderRefundPicEntity queryObject(Integer id){
		return orderRefundPicDao.queryObject(id);
	}
	
	@Override
	public List<OrderRefundPicEntity> queryList(Map<String, Object> map){
		return orderRefundPicDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderRefundPicDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderRefundPicEntity orderRefundPic){
		orderRefundPicDao.save(orderRefundPic);
	}
	
	@Override
	public void update(OrderRefundPicEntity orderRefundPic){
		orderRefundPicDao.update(orderRefundPic);
	}
	
	@Override
	public void delete(Integer id){
		orderRefundPicDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		orderRefundPicDao.deleteBatch(ids);
	}
	
}
