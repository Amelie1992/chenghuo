package com.wzlue.order.dao;

import com.wzlue.order.entity.OrderGoodsEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 订单商品
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:16
 */
@Mapper
public interface OrderGoodsDao extends BaseDao<OrderGoodsEntity> {
	int buyNumOrder(String orderNumber);
	OrderGoodsEntity queryListOrderGood(Map<String, Object> map);
	OrderGoodsEntity queryByOrderNumber(String orderNumber);

}
