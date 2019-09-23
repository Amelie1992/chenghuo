
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

import com.wzlue.order.entity.OrderAddressEntity;
import com.wzlue.order.service.OrderAddressService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 订单收货地址
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:17
 */
@RestController
@RequestMapping("/order/orderaddress")
public class OrderAddressController extends AbstractController{
	@Autowired
	private OrderAddressService orderAddressService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderAddressEntity> orderAddressList = orderAddressService.queryList(query);
		int total = orderAddressService.queryTotal(query);
		
		return R.page(orderAddressList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("order:orderaddress:info")
	public R info(@PathVariable("id") Long id){
		OrderAddressEntity orderAddress = orderAddressService.queryObject(id);
		
		return R.ok().put("orderAddress", orderAddress);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("order:orderaddress:save")
	public R save(@RequestBody OrderAddressEntity orderAddress){
		orderAddressService.save(orderAddress);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("order:orderaddress:update")
	public R update(@RequestBody OrderAddressEntity orderAddress){
		orderAddressService.update(orderAddress);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("order:orderaddress:delete")
	public R delete(@RequestBody Long[] ids){
		orderAddressService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
