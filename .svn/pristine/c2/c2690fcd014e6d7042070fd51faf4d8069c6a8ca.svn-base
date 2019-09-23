
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

import com.wzlue.contact.entity.MMemberLogEntity;
import com.wzlue.contact.service.MMemberLogService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 每日访问表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-08-13 17:39:33
 */
@RestController
@RequestMapping("/contact/mmemberlog")
public class MMemberLogController extends AbstractController{
	@Autowired
	private MMemberLogService mMemberLogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<MMemberLogEntity> mMemberLogList = mMemberLogService.queryList(query);
		int total = mMemberLogService.queryTotal(query);
		
		return R.page(mMemberLogList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("contact:mmemberlog:info")
	public R info(@PathVariable("id") Integer id){
		MMemberLogEntity mMemberLog = mMemberLogService.queryObject(id);
		
		return R.ok().put("mMemberLog", mMemberLog);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("contact:mmemberlog:save")
	public R save(@RequestBody MMemberLogEntity mMemberLog){
		mMemberLogService.save(mMemberLog);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("contact:mmemberlog:update")
	public R update(@RequestBody MMemberLogEntity mMemberLog){
		mMemberLogService.update(mMemberLog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("contact:mmemberlog:delete")
	public R delete(@RequestBody Integer[] ids){
		mMemberLogService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
