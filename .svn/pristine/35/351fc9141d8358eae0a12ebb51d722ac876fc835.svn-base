
package com.wzlue.web.controller.goods;

import com.wzlue.common.base.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wzlue.goods.entity.RecommendGoodsEntity;
import com.wzlue.goods.service.RecommendGoodsService;
import io.swagger.models.auth.In;
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
 * 推荐商品表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 10:02:57
 */
@RestController
@RequestMapping("/goods/recommendGoods")
public class RecommendGoodsController extends AbstractController{
	@Autowired
	private RecommendGoodsService recommendGoodsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RecommendGoodsEntity> recommendGoodsList = recommendGoodsService.queryList(query);
		int total = recommendGoodsService.queryTotal(query);
		
		return R.page(recommendGoodsList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:recommendGoods:info")
	public R info(@PathVariable("id") Long id){
		RecommendGoodsEntity recommendGoods = recommendGoodsService.queryObject(id);
		return R.ok().put("recommendGoods", recommendGoods);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:recommendGoods:save")
	public R save(@RequestBody RecommendGoodsEntity recommendGoods){
		List<RecommendGoodsEntity> recommendGoodsList = recommendGoods.getRecommendGoodsList();
		for (RecommendGoodsEntity recommendGoodsEntity:recommendGoodsList) {
			Map<String, Object> params = new HashMap<>();
			params.put("moduleId",recommendGoodsEntity.getModuleId());
			params.put("goodsId",recommendGoodsEntity.getGoodsId());
			//查询商品是否已经存在此模块下
			Integer num = recommendGoodsService.queryTotal(params);
			if (num>=1){
				return R.error("此模块下已经存在该商品");
			} else {
				recommendGoodsService.save(recommendGoodsEntity);
			}
		}
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:recommendGoods:update")
	public R update(@RequestBody RecommendGoodsEntity recommendGoods){
		recommendGoodsService.update(recommendGoods);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:recommendGoods:delete")
	public R delete(@RequestBody Long[] ids){
		recommendGoodsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
