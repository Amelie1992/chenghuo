package com.wzlue.app.controller.goods;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wzlue.common.base.R;
import com.wzlue.goods.entity.TagEntity;
import com.wzlue.goods.service.TagService;

/**
 * 标签
 * 
 * @author wzlue
 *
 */
@RestController
@RequestMapping("/app/tag")
public class ApiTagController {
	
	@Autowired
	private TagService tagService;

	
	/**
	 * 分类列表
	 * @return
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		params.put("sidx", "sort");
		params.put("order", "asc");
		List<TagEntity> tagList = tagService.queryList(params);
		return R.ok().put("tagList", tagList);
	}
	
}
