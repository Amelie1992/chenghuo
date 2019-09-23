package com.wzlue.member.service.impl;

import com.wzlue.member.dao.MemberAgreementDao;
import com.wzlue.member.entity.MemberAgreementEntity;
import com.wzlue.member.service.MemberAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("memberAgreementService")
public class MemberAgreementServiceImpl implements MemberAgreementService {
	@Autowired
	private MemberAgreementDao memberAgreementDao;
	
	@Override
	public MemberAgreementEntity queryObject(Long id){
		return memberAgreementDao.queryObject(id);
	}
	
	@Override
	public List<MemberAgreementEntity> queryList(Map<String, Object> map){
		return memberAgreementDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return memberAgreementDao.queryTotal(map);
	}
	
	@Override
	public void save(MemberAgreementEntity memberAgreement){
		memberAgreementDao.save(memberAgreement);
	}
	
	@Override
	public void update(MemberAgreementEntity memberAgreement){
		memberAgreement.setUpdateTime(new Date());
		memberAgreementDao.update(memberAgreement);
	}
	
	@Override
	public void delete(Long id){
		memberAgreementDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		memberAgreementDao.deleteBatch(ids);
	}
	
}
