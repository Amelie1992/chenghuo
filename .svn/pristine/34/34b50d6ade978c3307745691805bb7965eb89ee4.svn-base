
package com.wzlue.web.controller.order;

import com.wzlue.common.base.R;
import java.util.List;
import java.util.Map;

import com.wzlue.order.entity.ServiceEvaluateEntity;
import com.wzlue.order.service.ServiceEvaluateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wzlue.web.controller.sys.AbstractController;

import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 售后服务评价
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-09 10:28:07
 */
@RestController
@RequestMapping("/order/serviceEvaluate")
public class ServiceEvaluateController extends AbstractController{
	@Autowired
	private ServiceEvaluateService serviceEvaluateService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ServiceEvaluateEntity> serviceEvaluateList = serviceEvaluateService.queryList(query);
		int total = serviceEvaluateService.queryTotal(query);
		
		return R.page(serviceEvaluateList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("order:serviceEvaluate:info")
	public R info(@PathVariable("id") Long id){
		ServiceEvaluateEntity serviceEvaluate = serviceEvaluateService.queryObject(id);
		
		return R.ok().put("serviceEvaluate", serviceEvaluate);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("order:serviceEvaluate:save")
	public R save(@RequestBody ServiceEvaluateEntity serviceEvaluate){
		serviceEvaluateService.save(serviceEvaluate);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("order:serviceEvaluate:update")
	public R update(@RequestBody ServiceEvaluateEntity serviceEvaluate){
		serviceEvaluateService.update(serviceEvaluate);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	/*@RequiresPermissions("order:serviceEvaluate:delete")*/
	public R delete(@RequestBody Long[] ids){
		serviceEvaluateService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
