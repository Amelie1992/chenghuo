
package com.wzlue.web.controller.member;

import com.wzlue.common.base.R;
import java.util.List;
import java.util.Map;

import com.wzlue.member.entity.MemberAgreementEntity;
import com.wzlue.member.service.MemberAgreementService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wzlue.web.controller.sys.AbstractController;

import com.wzlue.common.base.Query;

/**
 * 会员协议
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-04 14:08:30
 */
@RestController
@RequestMapping("/member/agreement")
public class MemberAgreementController extends AbstractController{
	@Autowired
	private MemberAgreementService memberAgreementService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<MemberAgreementEntity> memberAgreementList = memberAgreementService.queryList(query);
		int total = memberAgreementService.queryTotal(query);
		
		return R.page(memberAgreementList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("member:agreement:info")
	public R info(@PathVariable("id") Long id){
		MemberAgreementEntity memberAgreement = memberAgreementService.queryObject(id);
		
		return R.ok().put("memberAgreement", memberAgreement);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("member:agreement:save")
	public R save(@RequestBody MemberAgreementEntity memberAgreement){
		memberAgreementService.save(memberAgreement);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("member:agreement:update")
	public R update(@RequestBody MemberAgreementEntity memberAgreement){
		memberAgreementService.update(memberAgreement);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("member:agreement:delete")
	public R delete(@RequestBody Long[] ids){
		memberAgreementService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
