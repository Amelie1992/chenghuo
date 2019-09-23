package com.wzlue.contact.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.contact.dao.RefundLogisticsDao;
import com.wzlue.contact.entity.RefundLogisticsEntity;
import com.wzlue.contact.service.RefundLogisticsService;



@Service("refundLogisticsService")
public class RefundLogisticsServiceImpl implements RefundLogisticsService {
	@Autowired
	private RefundLogisticsDao refundLogisticsDao;
	
	@Override
	public RefundLogisticsEntity queryObject(Long id){
		return refundLogisticsDao.queryObject(id);
	}
	
	@Override
	public List<RefundLogisticsEntity> queryList(Map<String, Object> map){
		return refundLogisticsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return refundLogisticsDao.queryTotal(map);
	}
	
	@Override
	public void save(RefundLogisticsEntity refundLogistics){
		refundLogisticsDao.save(refundLogistics);
	}
	
	@Override
	public void update(RefundLogisticsEntity refundLogistics){
		refundLogisticsDao.update(refundLogistics);
	}
	
	@Override
	public void delete(Long id){
		refundLogisticsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		refundLogisticsDao.deleteBatch(ids);
	}
	
}
