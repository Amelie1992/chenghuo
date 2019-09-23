
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

import com.wzlue.member.entity.SignInRecordEntity;
import com.wzlue.member.service.SignInRecordService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 签到记录
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-02 16:15:55
 */
@RestController
@RequestMapping("/member/signinrecord")
public class SignInRecordController extends AbstractController{
	@Autowired
	private SignInRecordService signInRecordService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SignInRecordEntity> signInRecordList = signInRecordService.queryList(query);
		int total = signInRecordService.queryTotal(query);
		
		return R.page(signInRecordList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("member:signinrecord:info")
	public R info(@PathVariable("id") Long id){
		SignInRecordEntity signInRecord = signInRecordService.queryObject(id);
		
		return R.ok().put("signInRecord", signInRecord);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("member:signinrecord:save")
	public R save(@RequestBody SignInRecordEntity signInRecord){
		signInRecordService.save(signInRecord);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("member:signinrecord:update")
	public R update(@RequestBody SignInRecordEntity signInRecord){
		signInRecordService.update(signInRecord);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("member:signinrecord:delete")
	public R delete(@RequestBody Long[] ids){
		signInRecordService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
