
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

import com.wzlue.goods.entity.PropertyEntity;
import com.wzlue.goods.service.PropertyService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 商品属性
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
@RestController
@RequestMapping("/goods/property")
public class PropertyController extends AbstractController{
	@Autowired
	private PropertyService propertyService;
	
	@RequestMapping("/getByCategoryId")
	public R getByCategoryId(@RequestParam Map<String, Object> params){
		params.put("sidx", "sort");
		params.put("order", "asc");
		List<PropertyEntity> propertyList = propertyService.queryList(params);
		return R.ok().put("propertyList", propertyList);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("sidx", "sort");
		params.put("order", "asc");
        Query query = new Query(params);

		List<PropertyEntity> propertyList = propertyService.queryList(query);
		int total = propertyService.queryTotal(query);
		
		return R.page(propertyList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:property:info")
	public R info(@PathVariable("id") Long id){
		PropertyEntity property = propertyService.queryObject(id);
		
		return R.ok().put("property", property);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:property:save")
	public R save(@RequestBody PropertyEntity property){
		propertyService.save(property);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:property:update")
	public R update(@RequestBody PropertyEntity property){
		propertyService.update(property);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:property:delete")
	public R delete(@RequestBody Long[] ids){
		propertyService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
