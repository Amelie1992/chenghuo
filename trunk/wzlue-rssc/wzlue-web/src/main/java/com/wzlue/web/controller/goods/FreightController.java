
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
import com.wzlue.goods.entity.FreightEntity;
import com.wzlue.goods.service.FreightService;
import com.wzlue.web.controller.sys.AbstractController;




/**
 * 运费价格
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-09-17 15:22:31
 */
@RestController
@RequestMapping("/order/freight")
public class FreightController extends AbstractController{
	@Autowired
	private FreightService freightService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<FreightEntity> freightList = freightService.queryList(query);
		int total = freightService.queryTotal(query);
		
		return R.page(freightList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("order:freight:info")
	public R info(@PathVariable("id") Long id){
		FreightEntity freight = freightService.queryObject(id);
		
		return R.ok().put("freight", freight);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("order:freight:save")
	public R save(@RequestBody FreightEntity freight){
		freightService.save(freight);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("order:freight:update")
	public R update(@RequestBody FreightEntity freight){
		freightService.update(freight);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("order:freight:delete")
	public R delete(@RequestBody Long[] ids){
		freightService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
