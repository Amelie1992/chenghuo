
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

import com.wzlue.goods.entity.TagEntity;
import com.wzlue.goods.service.TagService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 标签
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 15:03:21
 */
@RestController
@RequestMapping("/goods/tag")
public class TagController extends AbstractController{
	@Autowired
	private TagService tagService;
	
	@RequestMapping("/getAll")
	public R getAll(@RequestParam Map<String, Object> params){
		params.put("sidx", "sort");
		params.put("order", "asc");
		List<TagEntity> tagList = tagService.queryList(params);
		
		return R.ok().put("tagList", tagList);
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

		List<TagEntity> tagList = tagService.queryList(query);
		int total = tagService.queryTotal(query);
		
		return R.page(tagList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:tag:info")
	public R info(@PathVariable("id") Long id){
		TagEntity tag = tagService.queryObject(id);
		
		return R.ok().put("tag", tag);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:tag:save")
	public R save(@RequestBody TagEntity tag){
		tagService.save(tag);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:tag:update")
	public R update(@RequestBody TagEntity tag){
		tagService.update(tag);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:tag:delete")
	public R delete(@RequestBody Long[] ids){
		tagService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
