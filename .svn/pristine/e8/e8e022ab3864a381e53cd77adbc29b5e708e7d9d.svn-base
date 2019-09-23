
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

import com.wzlue.member.entity.MemberAddressEntity;
import com.wzlue.member.service.MemberAddressService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 用户收货表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 20:58:06
 */
@RestController
@RequestMapping("/member/memberaddress")
public class MemberAddressController extends AbstractController{
	@Autowired
	private MemberAddressService memberAddressService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<MemberAddressEntity> memberAddressList = memberAddressService.queryList(query);
		int total = memberAddressService.queryTotal(query);
		
		return R.page(memberAddressList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("member:memberaddress:info")
	public R info(@PathVariable("id") Long id){
		MemberAddressEntity memberAddress = memberAddressService.queryObject(id);
		
		return R.ok().put("memberAddress", memberAddress);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("member:memberaddress:save")
	public R save(@RequestBody MemberAddressEntity memberAddress){
		memberAddressService.save(memberAddress);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("member:memberaddress:update")
	public R update(@RequestBody MemberAddressEntity memberAddress){
		memberAddressService.update(memberAddress);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("member:memberaddress:delete")
	public R delete(@RequestBody Long[] ids){
		memberAddressService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
