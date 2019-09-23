
package com.wzlue.app.controller.shop;

import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;
import com.wzlue.shop.entity.ShopEntity;
import com.wzlue.shop.service.ShopService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.List;
import java.util.Map;


/**
 * 商家表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-03-26 15:44:44
 */
@RestController
@RequestMapping("/app/sysshop")
public class ShopController {
	@Autowired
	private ShopService sysShopService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ShopEntity> sysShopList = sysShopService.queryList(query);
		int total = sysShopService.queryTotal(query);
		
		return R.page(sysShopList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
//	@RequiresPermissions("shop:sysshop:info")
	public R info(@PathVariable("id") Long id){
		ShopEntity sysShop = sysShopService.queryObject(id);
		
		return R.ok().put("sysShop", sysShop);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("shop:sysshop:save")
	public R save(@RequestBody ShopEntity sysShop){
		sysShopService.save(sysShop);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("shop:sysshop:update")
	public R update(@RequestBody ShopEntity sysShop){
		sysShopService.update(sysShop);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("shop:sysshop:delete")
	public R delete(@RequestBody Long[] ids){
		sysShopService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
