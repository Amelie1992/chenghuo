package com.wzlue.integral.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wzlue.integral.dao.IntegralCardDao;
import com.wzlue.integral.entity.IntegralCardEntity;
import com.wzlue.integral.service.IntegralCardService;
import com.wzlue.member.dao.IntegralRecordDao;
import com.wzlue.member.dao.MemberInfoDao;
import com.wzlue.member.entity.IntegralRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("integralCardService")
public class IntegralCardServiceImpl implements IntegralCardService {
	@Autowired
	private IntegralCardDao integralCardDao;
	@Autowired
	private MemberInfoDao memberInfoDao;
	@Autowired
	private IntegralRecordDao integralRecordDao;
	
	@Override
	public IntegralCardEntity queryObject(Long id){
		return integralCardDao.queryObject(id);
	}
	
	@Override
	public List<IntegralCardEntity> queryList(Map<String, Object> map){
		return integralCardDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return integralCardDao.queryTotal(map);
	}
	
	@Override
	public void save(IntegralCardEntity integralCard){
		integralCardDao.save(integralCard);
	}
	
	@Override
	public void update(IntegralCardEntity integralCard){
		integralCardDao.update(integralCard);
	}
	
	@Override
	public void delete(Long id){
		integralCardDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		integralCardDao.deleteBatch(ids);
	}

	@Override
    public void activateBatch(Long[] ids){
        integralCardDao.activateBatch(ids);
	}

    @Override
    public IntegralCardEntity queryByCardNumber(String cardNumber){
	    return integralCardDao.queryByCardNumber(cardNumber);
    }

    //积分充值
    @Override
    public void recharge(Long memberId, IntegralCardEntity integralCard){
		//核销
		integralCard.setWriteOffStatus(1);
		integralCard.setRechargeBy(memberId);
		integralCard.setRechargeTime(new Date());
		integralCardDao.update(integralCard);
	    //积分
        memberInfoDao.addIntegral(memberId,integralCard.getIntegral());
        //积分记录
		IntegralRecordEntity integralRecord = new IntegralRecordEntity();
		integralRecord.setMemberId(memberId);
		String remarks="积分充值卡";
		integralRecord.setRemarks(remarks);
		integralRecord.setIntegral(integralCard.getIntegral());
		integralRecord.setCreateTime(new Date());
		integralRecord.setType(2);
		integralRecord.setTraceId(integralCard.getId());
		integralRecord.setTrace(integralCard.getCardNumber());
		integralRecordDao.save(integralRecord);

	}

}
