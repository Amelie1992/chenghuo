
package com.wzlue.web.controller.goods;

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
import com.wzlue.goods.entity.FreightTemplateEntity;
import com.wzlue.goods.service.FreightTemplateService;
import com.wzlue.web.controller.sys.AbstractController;




/**
 * 运费模板
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-09-17 15:22:30
 */
@RestController
@RequestMapping("/order/freighttemplate")
public class FreightTemplateController extends AbstractController{
	@Autowired
	private FreightTemplateService freightTemplateService;
	
	
	@RequestMapping("/all")
	public R all(){
		List<FreightTemplateEntity> freightTemplateList = freightTemplateService.queryList(null);
		return R.ok().put("list", freightTemplateList);
	}
	
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<FreightTemplateEntity> freightTemplateList = freightTemplateService.queryList(query);
		int total = freightTemplateService.queryTotal(query);
		
		return R.page(freightTemplateList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("freighttemplate:info")
	public R info(@PathVariable("id") Integer id){
		FreightTemplateEntity freightTemplate = freightTemplateService.queryObject(id);
		
		return R.ok().put("freightTemplate", freightTemplate);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("freighttemplate:save")
	public R save(@RequestBody FreightTemplateEntity freightTemplate){
		freightTemplateService.save(freightTemplate);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("freighttemplate:update")
	public R update(@RequestBody FreightTemplateEntity freightTemplate){
		freightTemplateService.update(freightTemplate);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("freighttemplate:delete")
	public R delete(@RequestBody Integer[] ids){
		freightTemplateService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
