package com.wzlue.app.controller.wbiao;

import java.util.Date;
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
import com.wzlue.common.utils.NumberUtils;
import com.wzlue.wbiao.entity.RecoveryEntity;
import com.wzlue.wbiao.service.RecoveryService;

@RestController
@RequestMapping("/app/recovery")
public class ApiRecoveryController {
	
	@Autowired
	private RecoveryService recoveryService;

	@Login
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params, @RequestAttribute("userId") Long userId){
		params.put("memberId", userId);
		List<RecoveryEntity> recoveryList = recoveryService.queryList(params);
		return R.ok().put("recoveryList", recoveryList);
	}
	
	@Login
	@RequestMapping("/add")
	public R add(@RequestBody RecoveryEntity recovery, @RequestAttribute("userId") Long userId){
		recovery.setMemberId(userId);
		recovery.setRecoveryNumber(NumberUtils.getOrderNumder());
		recovery.setCreateTime(new Date());
		recovery.setStatus(1);
		recoveryService.save(recovery);
		return R.ok();
	}
	
	@Login
	@RequestMapping("/detail")
	public R detail(Long id){
		RecoveryEntity recovery = recoveryService.queryObject(id);
		return R.ok().put("recovery", recovery);
	}
}
