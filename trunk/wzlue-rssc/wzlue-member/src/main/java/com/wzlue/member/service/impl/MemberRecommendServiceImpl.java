package com.wzlue.member.service.impl;

import com.wzlue.member.dao.MemberInfoDao;
import com.wzlue.member.dao.MemberRecommendDao;
import com.wzlue.member.entity.MemberInfoEntity;
import com.wzlue.member.entity.MemberRecommendEntity;
import com.wzlue.member.service.MemberInfoService;
import com.wzlue.member.service.MemberRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("mMemberRecommendService")
public class MemberRecommendServiceImpl implements MemberRecommendService {
	@Autowired
	private MemberRecommendDao mMemberRecommendDao;
	@Autowired
	private MemberInfoDao memberInfoDao;
	
	@Override
	public MemberRecommendEntity queryObject(Long id){
		return mMemberRecommendDao.queryObject(id);
	}
	
	@Override
	public List<MemberRecommendEntity> queryList(Map<String, Object> map){
		return mMemberRecommendDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mMemberRecommendDao.queryTotal(map);
	}
	
	@Override
	public void save(MemberRecommendEntity mMemberRecommend){
		mMemberRecommendDao.save(mMemberRecommend);
	}

	@Override
	@Transactional
	public void add(Long memberId, Long recommenderId) {
		MemberInfoEntity member = memberInfoDao.queryObject(recommenderId);
		MemberInfoEntity recommender = memberInfoDao.queryObject(memberId);
		MemberRecommendEntity memberRecommend = new MemberRecommendEntity();
		memberRecommend.setMemberId(memberId);
		memberRecommend.setMemberMobile(member.getMobile());
		memberRecommend.setMemberNickName(member.getNickName());
		memberRecommend.setRecommenderId(recommenderId);
		memberRecommend.setRecommenderMobile(recommender.getMobile());
		memberRecommend.setRecommenderNickName(recommender.getNickName());
		mMemberRecommendDao.save(memberRecommend);
	}

	@Override
	public void update(MemberRecommendEntity mMemberRecommend){
		mMemberRecommendDao.update(mMemberRecommend);
	}
	
	@Override
	public void delete(Long id){
		mMemberRecommendDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		mMemberRecommendDao.deleteBatch(ids);
	}
	
}
