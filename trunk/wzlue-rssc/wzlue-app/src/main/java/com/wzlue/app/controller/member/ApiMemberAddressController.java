package com.wzlue.app.controller.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wzlue.app.common.annotation.Login;
import com.wzlue.common.base.R;
import com.wzlue.member.entity.MemberAddressEntity;
import com.wzlue.member.service.MemberAddressService;

/**
 * 收货地址
 * @author wzlue
 *
 */
@RestController
@RequestMapping("/app/member/address")
public class ApiMemberAddressController {
	
	@Autowired
	private MemberAddressService memberAddressService;

	
	/**
	 * 收货地址列表
	 * @return
	 */
	@Login
	@RequestMapping("/list")
	public R list(@RequestAttribute("userId") Long userId, @RequestParam Map<String, Object> params){
		params.put("memberId", userId);
		List<MemberAddressEntity> memberAddressList = memberAddressService.queryList(params);
		return R.ok().put("memberAddressList", memberAddressList);
	}
	
	
	/**
	 * 新增收货地址
	 * @return
	 */
	@Login
	@RequestMapping("/add")
	public R add(@RequestAttribute("userId") Long userId, @RequestBody MemberAddressEntity memberAddress){
		memberAddress.setMemberId(userId);
		memberAddressService.save(memberAddress);
		return R.ok();
	}
	
	@Login
	@RequestMapping("/detail")
	public R detail(@RequestAttribute("userId") Long userId, Long id){
		MemberAddressEntity memberAddress = memberAddressService.queryObject(id);
		return R.ok().put("memberAddress", memberAddress);
	}
	
	@Login
	@RequestMapping("/getDefault")
	public R getDefault(@RequestAttribute("userId") Long userId){
		
		MemberAddressEntity memberAddress = memberAddressService.queryDefault(userId);
		return R.ok().put("memberAddress", memberAddress);
	}
	
	@Login
	@RequestMapping("/updateDefault")
	public R updateDefault(@RequestAttribute("userId") Long userId, Long id){
		memberAddressService.updateDefault(userId, id);
		return R.ok();
	}
	
	/**
	 * 修改收货地址
	 * @return
	 */
	@Login
	@RequestMapping("/update")
	public R update(@RequestAttribute("userId") Long userId, @RequestBody MemberAddressEntity memberAddress){
		memberAddressService.update(memberAddress);
		return R.ok();
	}
	
	/**
	 * 修改收货地址
	 * @return
	 */
	@Login
	@RequestMapping("/delete")
	public R delete(Long id){
		memberAddressService.delete(id);
		return R.ok();
	}
}
