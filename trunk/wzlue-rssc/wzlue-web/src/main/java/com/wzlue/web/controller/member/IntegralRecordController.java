
package com.wzlue.web.controller.member;

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

import com.wzlue.member.entity.IntegralRecordEntity;
import com.wzlue.member.service.IntegralRecordService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 积分记录
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 15:34:01
 */
@RestController
@RequestMapping("/member/integralrecord")
public class IntegralRecordController extends AbstractController{
	@Autowired
	private IntegralRecordService integralRecordService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<IntegralRecordEntity> integralRecordList = integralRecordService.queryList(query);
		int total = integralRecordService.queryTotal(query);
		
		return R.page(integralRecordList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("member:integralrecord:info")
	public R info(@PathVariable("id") Long id){
		IntegralRecordEntity integralRecord = integralRecordService.queryObject(id);
		
		return R.ok().put("integralRecord", integralRecord);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("member:integralrecord:save")
	public R save(@RequestBody IntegralRecordEntity integralRecord){
		integralRecordService.save(integralRecord);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("member:integralrecord:update")
	public R update(@RequestBody IntegralRecordEntity integralRecord){
		integralRecordService.update(integralRecord);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("member:integralrecord:delete")
	public R delete(@RequestBody Long[] ids){
		integralRecordService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
