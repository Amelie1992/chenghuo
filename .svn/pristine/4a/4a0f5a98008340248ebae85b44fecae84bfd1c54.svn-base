
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

import com.wzlue.order.entity.OrderInvoiceEntity;
import com.wzlue.order.service.OrderInvoiceService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 订单发票
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-15 10:36:04
 */
@RestController
@RequestMapping("/order/orderinvoice")
public class OrderInvoiceController extends AbstractController{
	@Autowired
	private OrderInvoiceService orderInvoiceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderInvoiceEntity> orderInvoiceList = orderInvoiceService.queryList(query);
		int total = orderInvoiceService.queryTotal(query);
		
		return R.page(orderInvoiceList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("order:orderinvoice:info")
	public R info(@PathVariable("id") Integer id){
		OrderInvoiceEntity orderInvoice = orderInvoiceService.queryObject(id);
		
		return R.ok().put("orderInvoice", orderInvoice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("order:orderinvoice:save")
	public R save(@RequestBody OrderInvoiceEntity orderInvoice){
		orderInvoiceService.save(orderInvoice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("order:orderinvoice:update")
	public R update(@RequestBody OrderInvoiceEntity orderInvoice){
		orderInvoiceService.update(orderInvoice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("order:orderinvoice:delete")
	public R delete(@RequestBody Integer[] ids){
		orderInvoiceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
