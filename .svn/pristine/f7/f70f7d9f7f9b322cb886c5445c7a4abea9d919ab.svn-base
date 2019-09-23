
package com.wzlue.web.controller.goods;

import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;
import com.wzlue.goods.entity.BrandEntity;
import com.wzlue.goods.service.BrandService;
import com.wzlue.web.controller.sys.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * 品牌
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-03 13:10:59
 */
@RestController
@RequestMapping("/goods/brand")
public class BrandController extends AbstractController{
	@Autowired
	private BrandService gbrandService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("sidx", "sort");
		params.put("order", "asc");
        Query query = new Query(params);

		List<BrandEntity> brandList = gbrandService.queryList(query);
		int total = gbrandService.queryTotal(query);
		
		return R.page(brandList,total);
	}

	/**
	 * all
	 */
	@RequestMapping("/all")
	public R all(@RequestParam Map<String, Object> params){

		List<BrandEntity> brandList = gbrandService.queryList(new HashMap<>());

		return R.ok().put("list",brandList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:brand:info")
	public R info(@PathVariable("id") Long id){
		BrandEntity brand = gbrandService.queryObject(id);
		
		return R.ok().put("brand", brand);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:brand:save")
	public R save(@RequestBody BrandEntity brand){
		gbrandService.save(brand);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:brand:update")
	public R update(@RequestBody BrandEntity brand){
		gbrandService.update(brand);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:brand:delete")
	public R delete(@RequestBody Long[] ids){
		gbrandService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
