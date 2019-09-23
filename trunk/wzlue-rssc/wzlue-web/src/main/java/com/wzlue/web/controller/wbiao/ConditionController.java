
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

import com.wzlue.wbiao.entity.ConditionEntity;
import com.wzlue.wbiao.service.ConditionService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 成色
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 20:30:44
 */
@RestController
@RequestMapping("/wbiao/condition")
public class ConditionController extends AbstractController{
	@Autowired
	private ConditionService conditionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		params.put("sidx", "sort");
		params.put("order", "asc");
		//查询列表数据
        Query query = new Query(params);

		List<ConditionEntity> conditionList = conditionService.queryList(query);
		int total = conditionService.queryTotal(query);
		
		return R.page(conditionList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("wbiao:condition:info")
	public R info(@PathVariable("id") Long id){
		ConditionEntity condition = conditionService.queryObject(id);
		
		return R.ok().put("condition", condition);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("wbiao:condition:save")
	public R save(@RequestBody ConditionEntity condition){
		conditionService.save(condition);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("wbiao:condition:update")
	public R update(@RequestBody ConditionEntity condition){
		conditionService.update(condition);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("wbiao:condition:delete")
	public R delete(@RequestBody Long[] ids){
		conditionService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
