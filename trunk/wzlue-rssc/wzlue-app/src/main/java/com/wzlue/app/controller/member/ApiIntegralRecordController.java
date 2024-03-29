package com.wzlue.app.controller.member;

import java.util.List;
import java.util.Map;

import com.wzlue.member.dao.IntegralRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wzlue.app.common.annotation.Login;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;
import com.wzlue.member.entity.IntegralRecordEntity;
import com.wzlue.member.service.IntegralRecordService;

@RestController
@RequestMapping("/app/member/integralRecord")
public class ApiIntegralRecordController {
	
	@Autowired
	private IntegralRecordService integralRecordService;
	@Autowired
	private IntegralRecordDao integralRecordDao;

	@Login
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params, @RequestAttribute("userId") Long userId){
		params.put("memberId", userId);
		Query query = new Query(params);
		List<IntegralRecordEntity> integralRecordList = integralRecordDao.queryListTwo(query);
		return R.ok().put("integralRecordList", integralRecordList);
	}
}
