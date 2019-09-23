
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

import com.wzlue.wbiao.entity.RecoveryPicEntity;
import com.wzlue.wbiao.service.RecoveryPicService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 回收置换图片
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 20:30:44
 */
@RestController
@RequestMapping("/wbiao/recoverypic")
public class RecoveryPicController extends AbstractController{
	@Autowired
	private RecoveryPicService recoveryPicService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RecoveryPicEntity> recoveryPicList = recoveryPicService.queryList(query);
		int total = recoveryPicService.queryTotal(query);
		
		return R.page(recoveryPicList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("wbiao:recoverypic:info")
	public R info(@PathVariable("id") Long id){
		RecoveryPicEntity recoveryPic = recoveryPicService.queryObject(id);
		
		return R.ok().put("recoveryPic", recoveryPic);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("wbiao:recoverypic:save")
	public R save(@RequestBody RecoveryPicEntity recoveryPic){
		recoveryPicService.save(recoveryPic);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("wbiao:recoverypic:update")
	public R update(@RequestBody RecoveryPicEntity recoveryPic){
		recoveryPicService.update(recoveryPic);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("wbiao:recoverypic:delete")
	public R delete(@RequestBody Long[] ids){
		recoveryPicService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
