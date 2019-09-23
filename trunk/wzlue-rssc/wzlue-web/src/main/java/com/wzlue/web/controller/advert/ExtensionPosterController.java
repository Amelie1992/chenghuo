
package com.wzlue.web.controller.advert;

import com.wzlue.advert.entity.ExtensionPosterEntity;
import com.wzlue.advert.service.ExtensionPosterService;
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

import com.wzlue.common.base.Query;




/**
 * 商户推广海报表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 14:18:34
 */
@RestController
@RequestMapping("/poster/extensionPoster")
public class ExtensionPosterController extends AbstractController{
	@Autowired
	private ExtensionPosterService extensionPosterService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ExtensionPosterEntity> extensionPosterList = extensionPosterService.queryList(query);
		int total = extensionPosterService.queryTotal(query);
		
		return R.page(extensionPosterList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("poster:extensionPoster:info")
	public R info(@PathVariable("id") Long id){
		ExtensionPosterEntity extensionPoster = extensionPosterService.queryObject(id);
		
		return R.ok().put("extensionPoster", extensionPoster);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("poster:extensionPoster:save")
	public R save(@RequestBody ExtensionPosterEntity extensionPoster){
		extensionPosterService.save(extensionPoster);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("poster:extensionPoster:update")
	public R update(@RequestBody ExtensionPosterEntity extensionPoster){
		extensionPosterService.update(extensionPoster);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("poster:extensionPoster:delete")
	public R delete(@RequestBody Long[] ids){
		extensionPosterService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
