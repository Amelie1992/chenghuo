
package com.wzlue.app.controller.advert;

import com.wzlue.advert.entity.ExtensionPosterEntity;
import com.wzlue.advert.service.ExtensionPosterService;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 商户推广海报表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 14:18:34
 */
@RestController
@RequestMapping("/app/extensionPoster")
public class ApiExtensionPosterController {
	@Autowired
	private ExtensionPosterService extensionPosterService;
	
	/**
	 * 信息
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ExtensionPosterEntity> extensionPosterList = extensionPosterService.queryListApi(query);
		return R.ok().put("extensionPosterList",extensionPosterList);
	}
	
}
