
package com.wzlue.web.controller.integral;

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

import com.wzlue.integral.entity.IntegralCardBatchEntity;
import com.wzlue.integral.service.IntegralCardBatchService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 积分充值卡批次
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 14:48:04
 */
@RestController
@RequestMapping("/integral/integralcardbatch")
public class IntegralCardBatchController extends AbstractController{
	@Autowired
	private IntegralCardBatchService integralCardBatchService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<IntegralCardBatchEntity> integralCardBatchList = integralCardBatchService.queryList(query);
		int total = integralCardBatchService.queryTotal(query);
		
		return R.page(integralCardBatchList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("integral:integralcardbatch:info")
	public R info(@PathVariable("id") Long id){
		IntegralCardBatchEntity integralCardBatch = integralCardBatchService.queryObject(id);
		
		return R.ok().put("integralCardBatch", integralCardBatch);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
//	@RequiresPermissions("integral:integralcardbatch:save")
	public R save(@RequestBody IntegralCardBatchEntity integralCardBatch){
		integralCardBatchService.save(integralCardBatch);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("integral:integralcardbatch:update")
	public R update(@RequestBody IntegralCardBatchEntity integralCardBatch){
		integralCardBatchService.update(integralCardBatch);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("integral:integralcardbatch:delete")
	public R delete(@RequestBody Long[] ids){
		integralCardBatchService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
