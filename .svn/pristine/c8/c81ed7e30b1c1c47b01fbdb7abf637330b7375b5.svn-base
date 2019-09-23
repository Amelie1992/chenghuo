package com.wzlue.app.controller.sys;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wzlue.common.base.R;
import com.wzlue.sys.service.SysConfigService;

@RestController
@RequestMapping("/app/sys/config")
public class ApiConfigController {

	@Autowired
	private SysConfigService configService;

	@RequestMapping("/getValByKey")
	public R getValByKey(String key){
		String value = configService.queryByKey(key);
		JSONObject json_test = JSON.parseObject(value);
		return R.ok().put("value", json_test);
	}
}
