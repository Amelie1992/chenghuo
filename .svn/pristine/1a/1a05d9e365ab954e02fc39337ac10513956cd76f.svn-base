
package com.wzlue.web.controller.menu;

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

import com.wzlue.menu.entity.CustomMenuEntity;
import com.wzlue.menu.service.CustomMenuService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 自定义菜单
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 14:17:34
 */
@RestController
@RequestMapping("/menu/custommenu")
public class CustomMenuController extends AbstractController{
	@Autowired
	private CustomMenuService customMenuService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CustomMenuEntity> customMenuList = customMenuService.queryList(query);
		int total = customMenuService.queryTotal(query);
		
		return R.page(customMenuList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("menu:custommenu:info")
	public R info(@PathVariable("id") Long id){
		CustomMenuEntity customMenu = customMenuService.queryObject(id);
		
		return R.ok().put("customMenu", customMenu);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("menu:custommenu:save")
	public R save(@RequestBody CustomMenuEntity customMenu){
		customMenuService.save(customMenu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("menu:custommenu:update")
	public R update(@RequestBody CustomMenuEntity customMenu){
		customMenuService.update(customMenu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("menu:custommenu:delete")
	public R delete(@RequestBody Long[] ids){
		customMenuService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 显示
	 *
	 */
	@RequestMapping("/show/{id}")
	@RequiresPermissions("menu:custommenu:update")
	public R show(@PathVariable("id") Long id){
		customMenuService.show(id);

		return R.ok();
	}

}
