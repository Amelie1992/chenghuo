package com.wzlue.goods.service.impl;

import com.wzlue.goods.dao.MerchantAddressDao;
import com.wzlue.goods.entity.MerchantAddressEntity;
import com.wzlue.goods.service.MerchantAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("merchantAddressService")
public class MerchantAddressServiceImpl implements MerchantAddressService {
	@Autowired
	private MerchantAddressDao merchantAddressDao;
	
	@Override
	public MerchantAddressEntity queryObject(Long id){
		return merchantAddressDao.queryObject(id);
	}
	
	@Override
	public List<MerchantAddressEntity> queryList(Map<String, Object> map){
		return merchantAddressDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return merchantAddressDao.queryTotal(map);
	}
	
	@Override
	public void save(MerchantAddressEntity merchantAddress){
		merchantAddressDao.save(merchantAddress);
	}
	
	@Override
	public void update(MerchantAddressEntity merchantAddress){
		merchantAddressDao.update(merchantAddress);
	}
	
	@Override
	public void delete(Long id){
		merchantAddressDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		merchantAddressDao.deleteBatch(ids);
	}
	
}
