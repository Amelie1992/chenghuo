
package com.wzlue.web.controller.order;

import com.wzlue.common.base.R;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wzlue.web.controller.sys.AbstractController;

import com.wzlue.order.entity.OrderLogisticsEntity;
import com.wzlue.order.service.OrderLogisticsService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 订单物流
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:16
 */
@RestController
@RequestMapping("/order/orderlogistics")
public class OrderLogisticsController extends AbstractController{
	@Autowired
	private OrderLogisticsService orderLogisticsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderLogisticsEntity> orderLogisticsList = orderLogisticsService.queryList(query);
		int total = orderLogisticsService.queryTotal(query);
		
		return R.page(orderLogisticsList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("order:orderlogistics:info")
	public R info(@PathVariable("id") Long id){
		OrderLogisticsEntity orderLogistics = orderLogisticsService.queryObject(id);
		
		return R.ok().put("orderLogistics", orderLogistics);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("order:orderlogistics:save")
	public R save(@RequestBody OrderLogisticsEntity orderLogistics){
		orderLogisticsService.save(orderLogistics);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("order:orderlogistics:update")
	public R update(@RequestBody OrderLogisticsEntity orderLogistics){
		orderLogisticsService.update(orderLogistics);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("order:orderlogistics:delete")
	public R delete(@RequestBody Long[] ids){
		orderLogisticsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
