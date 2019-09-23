
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

import com.wzlue.goods.entity.GoodsTagEntity;
import com.wzlue.goods.service.GoodsTagService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 商品标签
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 15:03:21
 */
@RestController
@RequestMapping("/goods/goodstag")
public class GoodsTagController extends AbstractController{
	@Autowired
	private GoodsTagService goodsTagService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GoodsTagEntity> goodsTagList = goodsTagService.queryList(query);
		int total = goodsTagService.queryTotal(query);
		
		return R.page(goodsTagList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:goodstag:info")
	public R info(@PathVariable("id") Long id){
		GoodsTagEntity goodsTag = goodsTagService.queryObject(id);
		
		return R.ok().put("goodsTag", goodsTag);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:goodstag:save")
	public R save(@RequestBody GoodsTagEntity goodsTag){
		goodsTagService.save(goodsTag);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:goodstag:update")
	public R update(@RequestBody GoodsTagEntity goodsTag){
		goodsTagService.update(goodsTag);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:goodstag:delete")
	public R delete(@RequestBody Long[] ids){
		goodsTagService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
