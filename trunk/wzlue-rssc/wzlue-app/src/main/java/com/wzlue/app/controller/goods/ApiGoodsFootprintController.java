package com.wzlue.app.controller.goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wzlue.common.base.Query;
import com.wzlue.goods.entity.GoodsFootprintEntity;
import com.wzlue.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wzlue.app.common.annotation.Login;
import com.wzlue.common.base.R;
import com.wzlue.goods.entity.GoodsEntity;
import com.wzlue.goods.service.GoodsFootprintService;

@RestController
@RequestMapping("/app/goodsfootprint")
public class ApiGoodsFootprintController {

	@Autowired
	private GoodsFootprintService goodsFootprintService;
	@Autowired
	private GoodsService goodsService;

	/**
	 * 足迹加分页
	 * limit
	 * page
	 * offset
	 * @param userId
	 * @param params
	 * @return
	 */
	@Login
	@RequestMapping("/list")
	public R list(@RequestAttribute("userId") Long userId,@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("memberId",userId);
		Query query = new Query(params);
		List<GoodsFootprintEntity> goodsFootprintList = goodsFootprintService.queryList(query);
//		List<GoodsEntity> goodsList = goodsFootprintService.getFootprintGoods(query);
		return R.ok().put("goodsList", goodsFootprintList);
	}
	
	@Login
	@RequestMapping("cancel")
	public R cancel(@RequestAttribute("userId") Long userId, Long goodsId){
		goodsFootprintService.deleteByGoodsAndMember(goodsId, userId);
		return R.ok();
	}


	/**
	 * 您可能喜欢的8个商品
	 * @return
	 */
	@Login
	@RequestMapping("likeGoods")
	public R likeList(@RequestAttribute("userId") Long userId,@RequestParam Map<String, Object> params){
		params.put("memberId",userId);
		List<GoodsFootprintEntity> goodsFootprintEntityList=goodsFootprintService.queryList(params);//获取足迹里的商品
		params.clear();
		List<GoodsEntity> goodsEntityList=goodsService.queryList(params);//商品数据
		int randomNumber=(int)(1+Math.random()*(goodsEntityList.size()-1+1));//随机数
		List<Object> likeList=new ArrayList<>();
		if(goodsFootprintEntityList.size()>0){//有足迹记录的话
			if(goodsFootprintEntityList.size()>=8){//足迹里的数据有8个的话
				for (int i=0;i<8;i++){
					GoodsEntity goodsEntity=goodsService.queryObject(goodsFootprintEntityList.get(i).getGoodsId());//商品
					if(goodsEntity!=null){
						likeList.add(goodsEntity);//塞入足迹数据8次
					}else{
						likeList.add(goodsEntityList.get(i));
					}

				}
			}else{//小于8条数据的话
				for (int j=0;j<goodsFootprintEntityList.size();j++){
					GoodsEntity goodsEntity=goodsService.queryObject(goodsFootprintEntityList.get(j).getGoodsId());//商品
					if(goodsEntity!=null){
						likeList.add(goodsEntity);//把足迹里的数据都塞入你可能喜欢的数据
					}else{
						likeList.add(goodsEntityList.get(randomNumber));
					}

				}
				int surPlus=8-goodsFootprintEntityList.size();//您喜欢的数据剩余条数
				for (int k=0;k<surPlus;k++){//把商品数据塞入剩余的条数
					likeList.add(goodsEntityList.get(randomNumber));
				}
			}
		}else{
			for (int i=0;i<8;i++){
				likeList.add(goodsEntityList.get(randomNumber));//随机抽取8条商品的数据
			}
		}
		return R.ok().put("likeList",likeList);
	}
}
