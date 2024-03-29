
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

import com.wzlue.order.entity.LogisticsEntity;
import com.wzlue.order.service.LogisticsService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 物流公司
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:16
 */
@RestController
@RequestMapping("/order/logistics")
public class LogisticsController extends AbstractController{
	@Autowired
	private LogisticsService logisticsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LogisticsEntity> logisticsList = logisticsService.queryList(query);
		int total = logisticsService.queryTotal(query);
		
		return R.page(logisticsList,total);
	}

	/**
	 * 列表(订单发货用)
	 */
	@RequestMapping("/list2")
	public R list2(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<LogisticsEntity> logisticsList = logisticsService.queryList(params);

		return R.ok().put("logisticsList",logisticsList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("order:logistics:info")
	public R info(@PathVariable("id") Long id){
		LogisticsEntity logistics = logisticsService.queryObject(id);
		
		return R.ok().put("logistics", logistics);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("order:logistics:save")
	public R save(@RequestBody LogisticsEntity logistics){
		logisticsService.save(logistics);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("order:logistics:update")
	public R update(@RequestBody LogisticsEntity logistics){
		logisticsService.update(logistics);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("order:logistics:delete")
	public R delete(@RequestBody Long[] ids){
		logisticsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
