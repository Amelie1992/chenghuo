
package com.wzlue.web.controller.goods;

import com.wzlue.common.base.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wzlue.goods.dao.CategoryDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wzlue.web.controller.sys.AbstractController;

import com.wzlue.goods.entity.CategoryEntity;
import com.wzlue.goods.service.CategoryService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 分类
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
@RestController
@RequestMapping("/goods/category")
public class CategoryController extends AbstractController{
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryDao categoryDao;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		params.put("sidx", "sort");
		params.put("order", "asc");
		//查询列表数据
        Query query = new Query(params);

		List<CategoryEntity> categoryList = categoryService.queryList(query);
		int total = categoryService.queryTotal(query);
		
		return R.page(categoryList,total);
	}


	/**
	 * 列表
	 */
	@RequestMapping("/all")
	public R tree(){
		List<CategoryEntity> categoryList = categoryService.queryList(new HashMap<>());
		return R.ok().put("list",categoryList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:category:info")
	public R info(@PathVariable("id") Long id){
		CategoryEntity category = categoryService.queryObject(id);
		
		return R.ok().put("category", category);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:category:save")
	public R save(@RequestBody CategoryEntity category){

		String categoryName=category.getCategoryName();
		int count=categoryDao.queryTotalName(categoryName);
		if(count>0){
			R r=new R();
			r.put("code",1);
			return r;
		}else{
			categoryService.save(category);
		}

		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:category:update")
	public R update(@RequestBody CategoryEntity category){
		categoryService.update(category);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:category:delete")
	public R delete(@RequestBody Long[] ids){
		categoryService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
