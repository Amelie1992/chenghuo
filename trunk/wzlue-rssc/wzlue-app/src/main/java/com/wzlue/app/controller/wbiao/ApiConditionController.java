package com.wzlue.app.controller.wbiao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wzlue.app.common.annotation.Login;
import com.wzlue.common.base.R;
import com.wzlue.wbiao.entity.ConditionEntity;
import com.wzlue.wbiao.service.ConditionService;

@RestController
@RequestMapping("/app/condition")
public class ApiConditionController {
	
	@Autowired
	private ConditionService conditionService;

	@Login
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		params.put("sidx", "sort");
		params.put("order", "asc");
		List<ConditionEntity> conditionList = conditionService.queryList(params);
		return R.ok().put("conditionList", conditionList);
	}
}
