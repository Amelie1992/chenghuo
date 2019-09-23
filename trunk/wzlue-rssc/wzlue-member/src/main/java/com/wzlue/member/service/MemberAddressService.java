package com.wzlue.member.service;

import com.wzlue.member.entity.MemberAddressEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户收货表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 20:58:06
 */
public interface MemberAddressService {
	
	MemberAddressEntity queryObject(Long id);
	
	List<MemberAddressEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MemberAddressEntity memberAddress);
	
	void update(MemberAddressEntity memberAddress);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	MemberAddressEntity queryDefault(Long userId);

	void updateDefault(Long userId, Long id);
}
