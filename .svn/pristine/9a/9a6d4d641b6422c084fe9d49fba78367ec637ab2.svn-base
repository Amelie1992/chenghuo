package com.wzlue.integral.dao;

import com.wzlue.integral.entity.IntegralCardEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 积分充值卡
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 17:43:00
 */
@Mapper
public interface IntegralCardDao extends BaseDao<IntegralCardEntity> {

    void activateBatch(Long[] ids);

    IntegralCardEntity queryByCardNumber(String cardNumber);
}
