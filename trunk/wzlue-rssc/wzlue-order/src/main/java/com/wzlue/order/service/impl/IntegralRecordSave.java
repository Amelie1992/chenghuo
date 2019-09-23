package com.wzlue.order.service.impl;

import com.wzlue.member.dao.IntegralRecordDao;
import com.wzlue.member.entity.IntegralRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2019/7/31.
 */
@Component
public class IntegralRecordSave {
    @Autowired
    private IntegralRecordDao integralRecordDao;

    /**
     * 添加积分记录
     * @param memberId 用户id
     * @param remarks 备注
     * @param integral 积分
     * @param type 分类：1推广；2充值；3消费；4购买会员；5解冻；6退单
     * @param traceId 踪迹id：推广记录id/积分充值卡id/订单id/会员id,
     * @param trace 踪迹：被推广人/积分充值码/订单编号/会员名
     * @param freezingIntegral 冻结积分标识：1
     */
    public void insertIntegralRecord(Long memberId, String remarks, Integer integral,Integer type,Long traceId,String trace,Integer freezingIntegral){
        IntegralRecordEntity integralRecordEntity=new IntegralRecordEntity();
        integralRecordEntity.setMemberId(memberId);
        integralRecordEntity.setRemarks(remarks);
        integralRecordEntity.setIntegral(integral);
        integralRecordEntity.setCreateTime(new Date());//创建时间
        integralRecordEntity.setType(type);
        integralRecordEntity.setTraceId(traceId);
        integralRecordEntity.setTrace(trace);
        integralRecordEntity.setFreezingIntegral(freezingIntegral);
        integralRecordDao.save(integralRecordEntity);
    }
}
