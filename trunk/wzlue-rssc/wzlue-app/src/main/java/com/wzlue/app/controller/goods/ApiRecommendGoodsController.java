
package com.wzlue.app.controller.goods;

import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;
import com.wzlue.goods.entity.GoodsEntity;
import com.wzlue.goods.entity.RecommendGoodsEntity;
import com.wzlue.goods.entity.RecommendModuleEntity;
import com.wzlue.goods.service.GoodsService;
import com.wzlue.goods.service.RecommendGoodsService;
import com.wzlue.goods.service.RecommendModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 推荐商品模块Api
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 10:02:57
 */
@RestController
@RequestMapping("/app/recommendGoods")
public class ApiRecommendGoodsController{
	@Autowired
	private RecommendGoodsService recommendGoodsService;

	@Autowired
	private RecommendModuleService recommendModuleService;

	@Autowired
	private GoodsService goodsService;

	/**
	 * 列表(首页展示模块和商品)
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        //模块列表
		List<RecommendModuleEntity> recommendModuleEntities = recommendModuleService.queryList(query);
		//推荐商品列表
		List<RecommendGoodsEntity> recommendGoodsList = recommendGoodsService.queryList(query);
		//模块加商品列表(多对多)
		List<RecommendModuleEntity> recommendModuleEntityList = recommendModuleService.queryListApi(query);
		//查询商品详情
		for (RecommendModuleEntity recommendModule:recommendModuleEntityList) {
			List<RecommendGoodsEntity> recommendGoods = recommendModule.getRecommendGoodsEntities();
			for (RecommendGoodsEntity recommendGood:recommendGoods) {
				GoodsEntity goodsEntity = goodsService.queryObject(recommendGood.getGoodsId());
				recommendGood.setGoodsEntity(goodsEntity);
			}
		}
		return R.ok().put("recommendModuleEntityList",recommendModuleEntityList);
	}

}
