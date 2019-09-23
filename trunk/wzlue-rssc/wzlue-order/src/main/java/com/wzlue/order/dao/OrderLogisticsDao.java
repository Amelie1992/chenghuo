package com.wzlue.order.dao;

import com.wzlue.order.entity.OrderLogisticsEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单物流
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:16
 */
@Mapper
public interface OrderLogisticsDao extends BaseDao<OrderLogisticsEntity> {

	OrderLogisticsEntity queryByOrderNumber(String orderNumber);
	
}
