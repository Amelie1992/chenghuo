
package com.wzlue.web.controller.goods;

import com.wzlue.common.base.R;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wzlue.web.controller.sys.AbstractController;

import com.wzlue.goods.entity.GoodsFootprintEntity;
import com.wzlue.goods.service.GoodsFootprintService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 商品足迹
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 15:03:21
 */
@RestController
@RequestMapping("/goods/goodsfootprint")
public class GoodsFootprintController extends AbstractController{
	@Autowired
	private GoodsFootprintService goodsFootprintService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GoodsFootprintEntity> goodsFootprintList = goodsFootprintService.queryList(query);
		int total = goodsFootprintService.queryTotal(query);
		
		return R.page(goodsFootprintList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:goodsfootprint:info")
	public R info(@PathVariable("id") Long id){
		GoodsFootprintEntity goodsFootprint = goodsFootprintService.queryObject(id);
		
		return R.ok().put("goodsFootprint", goodsFootprint);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:goodsfootprint:save")
	public R save(@RequestBody GoodsFootprintEntity goodsFootprint){
		goodsFootprintService.save(goodsFootprint);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:goodsfootprint:update")
	public R update(@RequestBody GoodsFootprintEntity goodsFootprint){
		goodsFootprintService.update(goodsFootprint);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:goodsfootprint:delete")
	public R delete(@RequestBody Long[] ids){
		goodsFootprintService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
