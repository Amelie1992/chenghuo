
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

import com.wzlue.goods.entity.GoodsPicEntity;
import com.wzlue.goods.service.GoodsPicService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 商品图片
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
@RestController
@RequestMapping("/goods/goodspic")
public class GoodsPicController extends AbstractController{
	@Autowired
	private GoodsPicService goodsPicService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GoodsPicEntity> goodsPicList = goodsPicService.queryList(query);
		int total = goodsPicService.queryTotal(query);
		
		return R.page(goodsPicList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:goodspic:info")
	public R info(@PathVariable("id") Long id){
		GoodsPicEntity goodsPic = goodsPicService.queryObject(id);
		
		return R.ok().put("goodsPic", goodsPic);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:goodspic:save")
	public R save(@RequestBody GoodsPicEntity goodsPic){
		goodsPicService.save(goodsPic);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:goodspic:update")
	public R update(@RequestBody GoodsPicEntity goodsPic){
		goodsPicService.update(goodsPic);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:goodspic:delete")
	public R delete(@RequestBody Long[] ids){
		goodsPicService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/deleteOne")
	public R deleteOne(Long id){
		Long[] ids = new Long[]{id};
		goodsPicService.deleteBatch(ids);

		return R.ok();
	}
	
}
