
package com.wzlue.web.controller.wbiao;

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

import com.wzlue.wbiao.entity.RecoveryEntity;
import com.wzlue.wbiao.service.RecoveryService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 回收置换
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 20:30:44
 */
@RestController
@RequestMapping("/wbiao/recovery")
public class RecoveryController extends AbstractController{
	@Autowired
	private RecoveryService recoveryService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RecoveryEntity> recoveryList = recoveryService.queryList(query);
		int total = recoveryService.queryTotal(query);
		
		return R.page(recoveryList,total);
	}
	
	/*
	 * 
	 */
	@RequestMapping("/handle")
	@RequiresPermissions("wbiao:recovery:update")
	public R handle(@RequestBody RecoveryEntity recovery){
		recovery.setStatus(2);
		recoveryService.update(recovery);
		return R.ok();
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("wbiao:recovery:info")
	public R info(@PathVariable("id") Long id){
		RecoveryEntity recovery = recoveryService.queryObject(id);
		
		return R.ok().put("recovery", recovery);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("wbiao:recovery:save")
	public R save(RecoveryEntity recovery){
		recoveryService.save(recovery);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("wbiao:recovery:update")
	public R update(@RequestBody RecoveryEntity recovery){
		recoveryService.update(recovery);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("wbiao:recovery:delete")
	public R delete(@RequestBody Long[] ids){
		recoveryService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
