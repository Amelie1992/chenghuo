
package com.wzlue.web.controller.goods;

import com.wzlue.common.base.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wzlue.goods.entity.RecommendGoodsEntity;
import com.wzlue.goods.entity.RecommendModuleEntity;
import com.wzlue.goods.service.RecommendGoodsService;
import com.wzlue.goods.service.RecommendModuleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wzlue.web.controller.sys.AbstractController;

import com.wzlue.common.base.Query;




/**
 * 推荐商品模块表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 10:06:35
 */
@RestController
@RequestMapping("/goods/recommendModule")
public class RecommendModuleController extends AbstractController{
	@Autowired
	private RecommendModuleService recommendModuleService;
	@Autowired
	private RecommendGoodsService recommendGoodsService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RecommendModuleEntity> gRecommendModuleList = recommendModuleService.queryList(query);
		int total = recommendModuleService.queryTotal(query);
		
		return R.page(gRecommendModuleList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:recommendModule:info")
	public R info(@PathVariable("id") Long id){
		RecommendModuleEntity recommendModule = recommendModuleService.queryObject(id);

		return R.ok().put("recommendModule", recommendModule);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:recommendModule:save")
	public R save(@RequestBody RecommendModuleEntity recommendModule){
		recommendModuleService.save(recommendModule);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:recommendModule:update")
	public R update(@RequestBody RecommendModuleEntity recommendModule){
		recommendModuleService.update(recommendModule);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:recommendModule:delete")
	public R delete(@RequestBody Long[] ids){
		recommendModuleService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
