
package com.wzlue.web.controller.goods;

import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;
import com.wzlue.goods.dao.GoodsSpecDao;
import com.wzlue.goods.dao.SpecDao;
import com.wzlue.goods.entity.GoodsSpecEntity;
import com.wzlue.goods.entity.SpecEntity;
import com.wzlue.goods.service.GoodsSpecService;
import com.wzlue.web.controller.sys.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




/**
 * 商品规格
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
@RestController
@RequestMapping("/goods/goodsSpec")
public class GoodsSpecController extends AbstractController{
	@Autowired
	private GoodsSpecService goodsSpecService;
	@Autowired
	private GoodsSpecDao goodsSpecDao;
	@Autowired
	private SpecDao specDao;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GoodsSpecEntity> goodsSpecList = goodsSpecService.queryList(query);
		int total = goodsSpecService.queryTotal(query);
		
		return R.page(goodsSpecList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:goodsSpec:info")
	public R info(@PathVariable("id") Long id){
		GoodsSpecEntity goodsSpec = goodsSpecService.queryObject(id);
		
		return R.ok().put("goodsSpec", goodsSpec);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:goodsSpec:save")
	public R save(@RequestBody GoodsSpecEntity goodsSpec){
		goodsSpecService.save(goodsSpec);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:goodsSpec:update")
	public R update(@RequestBody GoodsSpecEntity goodsSpec){
		goodsSpecService.update(goodsSpec);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:goodsSpec:delete")
	public R delete(@RequestBody Long[] ids){
		goodsSpecService.deleteBatch(ids);
		
		return R.ok();
	}
    /*获取规格详情*/
	@RequestMapping("/getEntity")
	public R getSpec(@RequestBody Long[] ids){
		List<SpecEntity> list=new ArrayList<SpecEntity>();
		for (int i = 0;i<ids.length;i++){
			Object id = ids[i];
			SpecEntity getSpecs=specDao.GoodsSpecEntity(id);
			list.add(getSpecs);
		}

		return R.ok().put("specList",list);
	}
	
}
