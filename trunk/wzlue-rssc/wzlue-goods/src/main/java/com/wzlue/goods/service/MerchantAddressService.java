package com.wzlue.goods.service;


import com.wzlue.goods.entity.MerchantAddressEntity;

import java.util.List;
import java.util.Map;

/**
 * 商户发货地址
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-09 15:47:43
 */
public interface MerchantAddressService {
	
	MerchantAddressEntity queryObject(Long id);
	
	List<MerchantAddressEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MerchantAddressEntity merchantAddress);
	
	void update(MerchantAddressEntity merchantAddress);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
