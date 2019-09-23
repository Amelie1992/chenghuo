
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

import com.wzlue.goods.entity.GoodsCollectionEntity;
import com.wzlue.goods.service.GoodsCollectionService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 商品收藏
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 15:03:21
 */
@RestController
@RequestMapping("/goods/goodscollection")
public class GoodsCollectionController extends AbstractController{
	@Autowired
	private GoodsCollectionService goodsCollectionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GoodsCollectionEntity> goodsCollectionList = goodsCollectionService.queryList(query);
		int total = goodsCollectionService.queryTotal(query);
		
		return R.page(goodsCollectionList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:goodscollection:info")
	public R info(@PathVariable("id") Long id){
		GoodsCollectionEntity goodsCollection = goodsCollectionService.queryObject(id);
		
		return R.ok().put("goodsCollection", goodsCollection);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:goodscollection:save")
	public R save(@RequestBody GoodsCollectionEntity goodsCollection){
		goodsCollectionService.save(goodsCollection);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:goodscollection:update")
	public R update(@RequestBody GoodsCollectionEntity goodsCollection){
		goodsCollectionService.update(goodsCollection);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:goodscollection:delete")
	public R delete(@RequestBody Long[] ids){
		goodsCollectionService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
