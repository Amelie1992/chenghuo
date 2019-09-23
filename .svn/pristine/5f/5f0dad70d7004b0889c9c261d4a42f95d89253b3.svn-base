
package com.wzlue.web.controller.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;
import com.wzlue.goods.entity.GoodsPropertyEntity;
import com.wzlue.goods.service.GoodsPropertyService;
import com.wzlue.web.controller.sys.AbstractController;




/**
 * 商品属性
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
@RestController
@RequestMapping("/goods/goodsproperty")
public class GoodsPropertyController extends AbstractController{
	@Autowired
	private GoodsPropertyService goodsPropertyService;
	
	@RequestMapping("/getByCategoryId")
	public R getByCategoryId(Long categoryId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("categoryId", categoryId);
		List<GoodsPropertyEntity> goodsPropertyList = goodsPropertyService.queryList(params);
		
		return R.ok().put("goodsPropertyList", goodsPropertyList);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GoodsPropertyEntity> goodsPropertyList = goodsPropertyService.queryList(query);
		int total = goodsPropertyService.queryTotal(query);
		
		return R.page(goodsPropertyList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:goodsproperty:info")
	public R info(@PathVariable("id") Long id){
		GoodsPropertyEntity goodsProperty = goodsPropertyService.queryObject(id);
		
		return R.ok().put("goodsProperty", goodsProperty);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:goodsproperty:save")
	public R save(@RequestBody GoodsPropertyEntity goodsProperty){
		goodsPropertyService.save(goodsProperty);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:goodsproperty:update")
	public R update(@RequestBody GoodsPropertyEntity goodsProperty){
		goodsPropertyService.update(goodsProperty);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:goodsproperty:delete")
	public R delete(@RequestBody Long[] ids){
		goodsPropertyService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
