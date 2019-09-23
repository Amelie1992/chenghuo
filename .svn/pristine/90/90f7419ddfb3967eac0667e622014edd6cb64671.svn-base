package com.wzlue.member.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzlue.common.exception.RRException;
import com.wzlue.common.utils.DateUtils;
import com.wzlue.member.dao.MemberInfoDao;
import com.wzlue.member.dao.SignInRecordDao;
import com.wzlue.member.entity.IntegralRecordEntity;
import com.wzlue.member.entity.MemberInfoEntity;
import com.wzlue.member.entity.SignInRecordEntity;
import com.wzlue.member.service.IntegralRecordService;
import com.wzlue.member.service.MemberInfoService;
import com.wzlue.sys.common.config.IntegralConfig;



@Service("memberInfoService")
public class MemberInfoServiceImpl implements MemberInfoService {
	@Autowired
	private MemberInfoDao memberInfoDao;
	@Autowired
	private SignInRecordDao signInRecordDao;
	@Autowired
	private IntegralConfig integralConfig;
	@Autowired
	private IntegralRecordService integralRecordService;
	
	@Override
	public MemberInfoEntity queryObject(Long id){
		return memberInfoDao.queryObject(id);
	}
	
	@Override
	public List<MemberInfoEntity> queryList(Map<String, Object> map){
		return memberInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return memberInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(MemberInfoEntity memberInfo){
		memberInfo.setIsVip(2);
		memberInfoDao.save(memberInfo);
	}
	
	@Override
	public void update(MemberInfoEntity memberInfo){
		memberInfoDao.update(memberInfo);
	}
	
	@Override
	public void delete(Long id){
		memberInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		memberInfoDao.deleteBatch(ids);
	}

	@Override
	public MemberInfoEntity queryByOpenid(String openid) {
		return memberInfoDao.queryByOpenid(openid);
	}

	@Override
	public void signIn(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", userId);
		map.put("date", DateUtils.format(new Date()));
		int total = signInRecordDao.queryTotal(map);
		if(total > 0){
			throw new RRException("请勿重复签到");
		}
		
		SignInRecordEntity signInRecord = new SignInRecordEntity();
		signInRecord.setMemberId(userId);
		signInRecord.setCreateTime(new Date());
		signInRecordDao.save(signInRecord);
		Integer integral = integralConfig.config3().getSignIn().getGet();
		memberInfoDao.addIntegral(userId, integral);
		IntegralRecordEntity integralRecord = new IntegralRecordEntity();
		integralRecord.setIntegral(integral);
		integralRecord.setMemberId(userId);
		integralRecord.setRemarks("签到");
		integralRecord.setCreateTime(new Date());
		integralRecordService.save(integralRecord);
	}

	@Override
	public boolean isSignIn(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", userId);
		map.put("date", DateUtils.format(new Date()));
		int total = signInRecordDao.queryTotal(map);
		return (total > 0);
	}

	@Override
	public Integer queryIntegral(Long userId) {
		return memberInfoDao.queryIntegral(userId);
	}

	@Override
	public void activateBatch(Long[] ids) {
		memberInfoDao.activateBatch(ids);
	}

}
