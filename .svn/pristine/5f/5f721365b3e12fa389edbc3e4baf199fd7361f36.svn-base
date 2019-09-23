package com.wzlue.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.member.dao.InvoiceDao;
import com.wzlue.member.entity.InvoiceEntity;
import com.wzlue.member.service.InvoiceService;



@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Override
	public InvoiceEntity queryObject(Long id){
		return invoiceDao.queryObject(id);
	}
	
	@Override
	public List<InvoiceEntity> queryList(Map<String, Object> map){
		return invoiceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return invoiceDao.queryTotal(map);
	}
	
	@Override
	public void save(InvoiceEntity invoice){
		invoiceDao.save(invoice);
	}
	
	@Override
	public void update(InvoiceEntity invoice){
		invoiceDao.update(invoice);
	}
	
	@Override
	public void delete(Long id){
		invoiceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		invoiceDao.deleteBatch(ids);
	}
	
}
