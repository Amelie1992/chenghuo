package com.wzlue.order.dao;

import com.wzlue.order.entity.OrderRefundEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 退款
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-08 21:03:13
 */
@Mapper
public interface OrderRefundDao extends BaseDao<OrderRefundEntity> {

	OrderRefundEntity queryByOrderNumber(String orderNumber);

	OrderRefundEntity queryByRefundNumber(String refundNumber);

	/**
	 * 退款数量
	 * @param params
	 * @return
	 */
	int reNumOrder(Map<String, Object> params);

	/**
	 * 退款积分
	 * @param params
	 * @return
	 */
	int reIntegral(Map<String, Object> params);

	List<OrderRefundEntity> queryOrderGoodList(Map<String,Object>params);
     /*售后商品剩余数量*/
	int goodNum(Map<String, Object> params);

}
