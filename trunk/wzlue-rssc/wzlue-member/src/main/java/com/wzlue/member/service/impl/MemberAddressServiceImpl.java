package com.wzlue.member.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzlue.member.dao.MemberAddressDao;
import com.wzlue.member.entity.MemberAddressEntity;
import com.wzlue.member.service.MemberAddressService;



@Service("memberAddressService")
public class MemberAddressServiceImpl implements MemberAddressService {
	@Autowired
	private MemberAddressDao memberAddressDao;
	
	@Override
	public MemberAddressEntity queryObject(Long id){
		return memberAddressDao.queryObject(id);
	}
	
	@Override
	public List<MemberAddressEntity> queryList(Map<String, Object> map){
		return memberAddressDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return memberAddressDao.queryTotal(map);
	}
	
	@Override
	public void save(MemberAddressEntity memberAddress){
		memberAddressDao.updateDefault(memberAddress);
		memberAddressDao.save(memberAddress);
	}
	
	@Override
	public void update(MemberAddressEntity memberAddress){
		memberAddressDao.update(memberAddress);
	}
	
	@Override
	public void delete(Long id){
		memberAddressDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		memberAddressDao.deleteBatch(ids);
	}

	@Override
	public MemberAddressEntity queryDefault(Long userId) {
		return memberAddressDao.queryDefault(userId);
	}

	@Override
	public void updateDefault(Long userId, Long id) {
		// TODO Auto-generated method stub
		MemberAddressEntity memberAddress = new MemberAddressEntity();
		memberAddress.setMemberId(userId);
		memberAddressDao.updateDefault(memberAddress);
		memberAddress.setId(id);
		memberAddress.setDefaultAddress(1);
		memberAddressDao.update(memberAddress);
	}
	
}
