package com.wzlue.app.controller.goods;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wzlue.common.base.R;
import com.wzlue.goods.entity.CategoryEntity;
import com.wzlue.goods.service.CategoryService;

/**
 * 分类
 * @author wzlue
 *
 */
@RestController
@RequestMapping("/app/category")
public class ApiCategoryController {
	
	@Autowired
	private CategoryService categoryService;

	
	/**
	 * 分类列表
	 * @return
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		params.put("sidx", "sort");
		params.put("order", "asc");
		List<CategoryEntity> categoryList = categoryService.queryList(params);
		return R.ok().put("categoryList", categoryList);
	}
	
}
