
package com.wzlue.web.controller.goods;

import com.wzlue.common.base.R;
import java.util.List;
import java.util.Map;

import com.wzlue.goods.dao.SpecDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wzlue.web.controller.sys.AbstractController;

import com.wzlue.goods.entity.SpecEntity;
import com.wzlue.goods.service.SpecService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 商品规格
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 20:42:51
 */
@RestController
@RequestMapping("/goods/spec")
public class SpecController extends AbstractController{
	@Autowired
	private SpecService specService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("sidx", "sort");
		params.put("order", "asc");
        Query query = new Query(params);

		List<SpecEntity> specList = specService.queryList(query);
		int total = specService.queryTotal(query);
		
		return R.page(specList,total);
	}

	/**
	 * 列表2级
	 */
	@RequestMapping("/attributeList")
	public R attributeList(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("sidx", "sort");
		params.put("order", "asc");
		Query query = new Query(params);

		List<SpecEntity> specList = specService.queryList2(query);
		int total = specService.queryTotal2(query);

		return R.page(specList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:spec:info")
	public R info(@PathVariable("id") Long id){
		SpecEntity spec = specService.queryObject(id);
		
		return R.ok().put("spec", spec);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:spec:save")
	public R save(@RequestBody SpecEntity spec){
		spec.setParentId((long) 0);
		specService.save(spec);
		
		return R.ok();
	}

	/**
	 * 保存二级
	 */
	@RequestMapping("/attributeSave")
	@RequiresPermissions("goods:spec:save")
	public R attributeSave(@RequestBody SpecEntity spec,String parentId){
		spec.setParentId(Long.valueOf(parentId));
		specService.save(spec);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:spec:update")
	public R update(@RequestBody SpecEntity spec){
		specService.update(spec);
		
		return R.ok();
	}

	/**
	 * 修改二级
	 */
	@RequestMapping("/attributeUpdate")
	@RequiresPermissions("goods:spec:update")
	public R attributeUpdate(@RequestBody SpecEntity spec){
		specService.update(spec);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:spec:delete")
	public R delete(@RequestBody Long[] ids){
		for (int i = 0; i < ids.length; i++){
			long id = ids[i];//要删除的规格id
			specService.del(id);
		}
		specService.deleteBatch(ids);
		return R.ok();
	}
   //	查询父级规格

	@RequestMapping("/getPid")
	public R getPid(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		List<SpecEntity> getPList = specService.selectPid(query);
		int totalTwo = specService.queryTotalTwo(query);

		return R.page(getPList,totalTwo);
	}
	@RequestMapping("/getSon/{id}")
	public R getSon(@PathVariable("id") Integer id){
		List<SpecEntity> specList = specService.selectIdSon(id);
		return R.ok().put("specList", specList);
	}

}
