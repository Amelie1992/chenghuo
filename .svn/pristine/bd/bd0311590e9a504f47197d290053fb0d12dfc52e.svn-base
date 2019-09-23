package com.wzlue.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.order.dao.OrderInvoiceDao;
import com.wzlue.order.entity.OrderInvoiceEntity;
import com.wzlue.order.service.OrderInvoiceService;



@Service("orderInvoiceService")
public class OrderInvoiceServiceImpl implements OrderInvoiceService {
	@Autowired
	private OrderInvoiceDao orderInvoiceDao;
	
	@Override
	public OrderInvoiceEntity queryObject(Integer id){
		return orderInvoiceDao.queryObject(id);
	}
	
	@Override
	public List<OrderInvoiceEntity> queryList(Map<String, Object> map){
		return orderInvoiceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderInvoiceDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderInvoiceEntity orderInvoice){
		orderInvoiceDao.save(orderInvoice);
	}
	
	@Override
	public void update(OrderInvoiceEntity orderInvoice){
		orderInvoiceDao.update(orderInvoice);
	}
	
	@Override
	public void delete(Integer id){
		orderInvoiceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		orderInvoiceDao.deleteBatch(ids);
	}
	
}
