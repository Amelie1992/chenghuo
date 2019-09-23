
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

import com.wzlue.order.entity.OrderRefundPicEntity;
import com.wzlue.order.service.OrderRefundPicService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 退款图片
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-08 21:23:38
 */
@RestController
@RequestMapping("/order/orderrefundpic")
public class OrderRefundPicController extends AbstractController{
	@Autowired
	private OrderRefundPicService orderRefundPicService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderRefundPicEntity> orderRefundPicList = orderRefundPicService.queryList(query);
		int total = orderRefundPicService.queryTotal(query);
		
		return R.page(orderRefundPicList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("order:orderrefundpic:info")
	public R info(@PathVariable("id") Integer id){
		OrderRefundPicEntity orderRefundPic = orderRefundPicService.queryObject(id);
		
		return R.ok().put("orderRefundPic", orderRefundPic);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("order:orderrefundpic:save")
	public R save(@RequestBody OrderRefundPicEntity orderRefundPic){
		orderRefundPicService.save(orderRefundPic);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("order:orderrefundpic:update")
	public R update(@RequestBody OrderRefundPicEntity orderRefundPic){
		orderRefundPicService.update(orderRefundPic);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("order:orderrefundpic:delete")
	public R delete(@RequestBody Integer[] ids){
		orderRefundPicService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
