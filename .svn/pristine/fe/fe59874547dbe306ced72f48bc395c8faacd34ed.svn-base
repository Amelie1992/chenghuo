package com.wzlue.app.controller.advert;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wzlue.advert.entity.AdvertEntity;
import com.wzlue.advert.service.AdvertService;
import com.wzlue.common.base.R;

/**
 * 广告
 * @author wzlue
 *
 */
@RestController
@RequestMapping("/app/advert")
public class ApiAdvertController {

	@Autowired
	private AdvertService advertService;
	
	/**
	 * 分类列表
	 * @return
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//状态，0：禁用，1：启用
		params.put("status", 1);
		List<AdvertEntity> adertList = advertService.queryList(params);
		return R.ok().put("adertList", adertList);
	}
	
}
