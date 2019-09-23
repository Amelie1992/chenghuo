
package com.wzlue.web.controller.contact;

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

import com.wzlue.contact.entity.RefundLogisticsEntity;
import com.wzlue.contact.service.RefundLogisticsService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-08-15 18:30:15
 */
@RestController
@RequestMapping("/contact/refundlogistics")
public class RefundLogisticsController extends AbstractController{
	@Autowired
	private RefundLogisticsService refundLogisticsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RefundLogisticsEntity> refundLogisticsList = refundLogisticsService.queryList(query);
		int total = refundLogisticsService.queryTotal(query);
		
		return R.page(refundLogisticsList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("contact:refundlogistics:info")
	public R info(@PathVariable("id") Long id){
		RefundLogisticsEntity refundLogistics = refundLogisticsService.queryObject(id);
		
		return R.ok().put("refundLogistics", refundLogistics);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("contact:refundlogistics:save")
	public R save(@RequestBody RefundLogisticsEntity refundLogistics){
		refundLogisticsService.save(refundLogistics);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("contact:refundlogistics:update")
	public R update(@RequestBody RefundLogisticsEntity refundLogistics){
		refundLogisticsService.update(refundLogistics);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("contact:refundlogistics:delete")
	public R delete(@RequestBody Long[] ids){
		refundLogisticsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
