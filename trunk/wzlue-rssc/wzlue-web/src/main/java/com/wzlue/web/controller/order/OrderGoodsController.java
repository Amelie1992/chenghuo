
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

import com.wzlue.order.entity.OrderGoodsEntity;
import com.wzlue.order.service.OrderGoodsService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 订单商品
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:16
 */
@RestController
@RequestMapping("/order/ordergoods")
public class OrderGoodsController extends AbstractController{
	@Autowired
	private OrderGoodsService orderGoodsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderGoodsEntity> orderGoodsList = orderGoodsService.queryList(query);
		int total = orderGoodsService.queryTotal(query);
		
		return R.page(orderGoodsList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("order:ordergoods:info")
	public R info(@PathVariable("id") Long id){
		OrderGoodsEntity orderGoods = orderGoodsService.queryObject(id);
		
		return R.ok().put("orderGoods", orderGoods);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("order:ordergoods:save")
	public R save(@RequestBody OrderGoodsEntity orderGoods){
		orderGoodsService.save(orderGoods);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("order:ordergoods:update")
	public R update(@RequestBody OrderGoodsEntity orderGoods){
		orderGoodsService.update(orderGoods);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("order:ordergoods:delete")
	public R delete(@RequestBody Long[] ids){
		orderGoodsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
