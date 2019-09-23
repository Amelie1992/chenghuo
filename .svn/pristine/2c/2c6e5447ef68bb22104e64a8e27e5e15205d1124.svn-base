
package com.wzlue.web.controller.advert;

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

import com.wzlue.advert.entity.AdvertEntity;
import com.wzlue.advert.service.AdvertService;
import com.wzlue.common.base.Query;


/**
 * 广告
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 15:18:40
 */
@RestController
@RequestMapping("/advert/advert")
public class AdvertController extends AbstractController{
	@Autowired
	private AdvertService advertService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<AdvertEntity> advertList = advertService.queryList(query);
		int total = advertService.queryTotal(query);
		
		return R.page(advertList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("advert:info")
	public R info(@PathVariable("id") Long id){
		AdvertEntity advert = advertService.queryObject(id);
		
		return R.ok().put("advert", advert);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("advert:save")
	public R save(@RequestBody AdvertEntity advert){
		advertService.save(advert);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("advert:update")
	public R update(@RequestBody AdvertEntity advert){
		advertService.update(advert);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("advert:delete")
	public R delete(@RequestBody Long[] ids){
		advertService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
