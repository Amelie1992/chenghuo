package com.wzlue.app.controller.order;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wzlue.AppApplication;
import com.wzlue.app.controller.member.ApiIntegralScordSave;
import com.wzlue.common.base.Query;
import com.wzlue.common.utils.DateUtils;
import com.wzlue.goods.dao.GoodsDao;
import com.wzlue.goods.dao.GoodsSpecDao;
import com.wzlue.goods.entity.GoodsEntity;
import com.wzlue.goods.entity.GoodsSpecEntity;
import com.wzlue.integral.dao.IntegralCardDao;
import com.wzlue.integral.entity.IntegralCardEntity;
import com.wzlue.member.entity.IntegralRecordEntity;
import com.wzlue.member.entity.MemberInfoEntity;
import com.wzlue.member.service.MemberInfoService;
import com.wzlue.order.dao.OrderDao;
import com.wzlue.order.dao.OrderGoodsDao;
import com.wzlue.order.dao.OrderRefundDao;
import com.wzlue.order.entity.LogisticsEntity;
import com.wzlue.order.service.LogisticsService;
import com.wzlue.sys.MSdx.ApiDemo4Java;
import com.wzlue.sys.dao.SysConfigDao;
import com.wzlue.sys.entity.SysConfigEntity;
import com.wzlue.sys.util.ExpressHundred;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wzlue.app.common.annotation.Login;
import com.wzlue.common.base.R;
import com.wzlue.order.entity.OrderEntity;
import com.wzlue.order.entity.OrderGoodsEntity;
import com.wzlue.order.service.OrderGoodsService;
import com.wzlue.order.service.OrderService;

@RestController
@RequestMapping("/app/order")
public class ApiOrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderGoodsService orderGoodsService;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private MemberInfoService memberInfoService;
    @Autowired
	private ApiIntegralScordSave apiIntegralScordSave;
    @Autowired
	private OrderGoodsDao orderGoodsDao;
    @Autowired
	private OrderRefundDao orderRefundDao;
    @Autowired
	private GoodsDao goodsDao;
    @Autowired
	private IntegralCardDao integralCardDao;
	@Autowired
	private LogisticsService logisticsService;
	@Autowired
	private GoodsSpecDao goodsSpecDao;



	@Login
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params, @RequestAttribute("userId") Long userId){
		params.put("memberId", userId);
		params.put("orderType",1);
		params.put("statusTwo",2);
     	if(params.get("status") != null){
			//params.put("status", params.get("status").toString().split(","));
			params.put("status", params.get("status"));
		}
		Query query = new Query(params);
		List<OrderEntity> orderList = orderService.queryList(query);
		return R.ok().put("orderList", orderList);
	}

	/**
	 * 商品为充值卡支付
	 * @param userId      用户id
	 * @param  order      订单详情
	 */
	public void createCard(OrderEntity order,Long userId){
      if(order.getOrderGoodsList().size()==1){//订单商品数量为1的时候
		  GoodsEntity goodsEntity=goodsDao.queryObject(order.getOrderGoodsList().get(0).getGoodsId());//获取商品详情
		  if(goodsEntity.getIsCard()==1){//当商品为充值卡的时候
			  IntegralCardEntity cardEntity=new IntegralCardEntity();//积分充值卡
			  String cardRom="";//卡号
			  for (int j=0;j<3 ;j++){//3位随机大写字母
				  char a=(char)(int)(Math.random()*26+65);
				  cardRom=cardRom+a;
			  }
			  for (int k=0;k<6;k++){//6位随机数
				  int random=(int)(Math.random()*10);
				  cardRom=cardRom+random;
			  }
			  Map<String, Object> map= new HashMap<>();
			  map.put("cardNumber",cardRom);
			  int count=integralCardDao.queryTotal(map);//是否有此卡号
			  while (count>0){
				  cardRom="";//卡号
				  for (int j=0;j<3 ;j++){//3位随机大写字母
					  char a=(char)(int)(Math.random()*26+65);
					  cardRom=cardRom+a;
				  }
				  for (int k=0;k<6;k++){//6位随机数
					  int random=(int)(Math.random()*10);
					  cardRom=cardRom+random;
				  }
			  }

			  cardEntity.setCardNumber(cardRom);
			  cardEntity.setType(2);//分类2为虚拟
			  cardEntity.setIntegral(order.getOrderGoodsList().get(0).getPrice().intValue());//积分面值
			  cardEntity.setActivationState(1);//激活状态1为激活
			  cardEntity.setCreateTime(new Date());//创建时间
			  integralCardDao.save(cardEntity);
			  MemberInfoEntity user=memberInfoService.queryObject(userId);
			  ApiDemo4Java.sendCard(user.getMobile(),cardRom);//发送兑换码卡号
			  order.setStatus(7);//充值卡已完成
			  orderService.update(order);

		  }
	  }
	  return;

	}
  public static void main(String[] args) {
		String a="1.\n" +
				"俊俊超市(马驹桥中学路店)\n" +
				"北京市通州区马团路西段";
	  String[] ss=a.split("\n|\r");
	  for (int i = 0; i < ss.length; i++){
		  System.out.println(ss[i]);
  }


  }

	/**
	 * 创建订单（积分支付，混合支付，微信支付）
	 * @param order
	 * @param userId
	 * @return
	 */
	@Login
	@RequestMapping("/create")
	@Transactional
	public R create(@RequestBody OrderEntity order, @RequestAttribute("userId") Long userId){
		order.setMemberId(userId);
		order.setCreateTime(new Date());
		order.setStatus(1);
		orderService.create(order);//创建订单

		Map<String,Object>map=new HashMap<String,Object>();
		if(order.getOrderGoodsList().size()>0){
			map.put("goodsId",order.getOrderGoodsList().get(0).getGoodsId());
			map.put("specId",order.getOrderGoodsList().get(0).getSpecId());
			map.put("specIdTwo",order.getOrderGoodsList().get(0).getSpecIdTwo());
			GoodsSpecEntity good =goodsSpecDao.querySpecPrice(map);//规格详情
			if(order.getOrderGoodsList().get(0).getSpecId()==0 && order.getOrderGoodsList().get(0).getSpecIdTwo()==0){
				GoodsEntity goods=goodsDao.queryObject(order.getOrderGoodsList().get(0).getGoodsId());
				goods.setStock(goods.getStock()-order.getOrderGoodsList().get(0).getBuyNum());
				goodsDao.update(goods);
			}else{
				good.setStock(good.getStock()-order.getOrderGoodsList().get(0).getBuyNum());
				goodsSpecDao.update(good);
			}
		}



		MemberInfoEntity memberInfo = memberInfoService.queryObject(userId);
		Integer integral = memberInfo.getIntegral();
		Integer orderUse = order.getUseIntegral();

		//判断支付类型
		if("integral".equals(order.getPayType())){//积分
			if (integral>=orderUse) {//积分够
				memberInfo.setIntegral(integral-orderUse);
				apiIntegralScordSave.insertIntegralRecord(userId,"消费使用积分",orderUse,3,order.getId(),order.getOrderNumber(),0);
			} else {
				return R.error("积分不够");
			}
			memberInfoService.update(memberInfo);

			//订单支付成功--待发货
			order.setStatus(2);
			order.setPayTime(new Date());
			orderService.payNotify(order);//积分扣除记录(缺接口)
			createCard(order,userId);//创建积分卡
		}else if ("mix".equals(order.getPayType())){//混合
			if (integral>orderUse) {//积分够
				memberInfo.setIntegral(integral-orderUse);
				apiIntegralScordSave.insertIntegralRecord(userId,"消费使用积分",orderUse,3,order.getId(),order.getOrderNumber(),0);
				memberInfoService.update(memberInfo);
				//订单支付成功--待发货
				order.setStatus(2);
				order.setPayTime(new Date());
				orderService.payNotify(order);//积分扣除记录(缺接口)
				createCard(order,userId);//创建积分卡

			}
		}
		return R.ok().put("orderNumber", order.getOrderNumber()).put("paymentAmount", order.getWechatAmount());
	}

	/*混合支付的接口修改*/
	@Login
	@RequestMapping("/mix")
	@Transactional
	public R mix(@RequestBody String orderNum, @RequestAttribute("userId") Long userId){
      OrderEntity order=orderDao.queryByOrderNumber(orderNum);//订单详情
		MemberInfoEntity memberInfo = memberInfoService.queryObject(userId);
		Integer integral = memberInfo.getIntegral();
		Integer orderUse = order.getUseIntegral();
		if ("mix".equals(order.getPayType())){//混合
			if (integral>=orderUse) {//积分够
				memberInfo.setIntegral(integral-orderUse);
				apiIntegralScordSave.insertIntegralRecord(userId,"消费使用积分",orderUse,3,order.getId(),order.getOrderNumber(),0);

			} else {
				apiIntegralScordSave.insertIntegralRecord(userId,"消费使用积分",orderUse,3,order.getId(),order.getOrderNumber(),0);
				memberInfo.setIntegral(0);
				BigDecimal number = new BigDecimal(0);
				number=BigDecimal.valueOf((int)integral);
				//应付-积分
				BigDecimal wechatPay = order.getPaymentAmount().subtract(number);
				order.setWechatAmount(wechatPay);
				createCard(order,userId);//创建积分卡
				return R.ok();
			}
			memberInfoService.update(memberInfo);
			//订单支付成功--待发货
			order.setStatus(2);
			order.setPayTime(new Date());
			orderService.payNotify(order);//积分扣除记录(缺接口)
			createCard(order,userId);//创建积分卡
		}
		return R.ok();
	}


	/**
	 * 测试用接口，接收支付状态
	 * @param order
	 * @return
	 */
	@Login
	@RequestMapping("/testModify")
	public R complete(@RequestBody OrderEntity order){
		order.setStatus(2);
		orderService.payNotify(order);
		return R.ok();
	}

	@Login
	@RequestMapping("/statistics")
	public R statistics(@RequestAttribute("userId") Long userId){
		Map<String, Object> statistics = orderService.statistics(userId);
		return R.ok(statistics);
	}

	@Login
	@RequestMapping("/detail")
	public R detail(Long id, @RequestAttribute("userId") Long userId){
		OrderEntity order = orderService.queryObject(id);
		return R.ok().put("order", order);
	}
	/*物流的信息*/
	@RequestMapping("/logistics")
	public R logistics(Long companyId, String number){
		LogisticsEntity logistics = logisticsService.queryObject(companyId);
		String content = ExpressHundred.getSynquery(logistics.getCompanyCode(), number);//物流详情
		return R.ok().put("content", content);
	}

	
	@Login
	@RequestMapping("/orderGoods")
	public R orderGoods(String orderNumber,int goodsId,@RequestAttribute("userId") Long userId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNumber", orderNumber);
		map.put("memberId", userId);
		map.put("goodsId",goodsId);
		OrderGoodsEntity orderGoods=orderGoodsDao.queryListOrderGood(map);//商品订单详情
		map.put("orderGoodId",orderGoods.getId());
		int goodNum=orderRefundDao.goodNum(map);//剩余商品退款数量

//		List<OrderGoodsEntity> orderGoodsList = orderGoodsService.queryList(map);
		return R.ok().put("orderGoods", orderGoods).put("goodNum",goodNum);
	}
	//取消订单
	@Login
	@RequestMapping("/cancel")
	public R cancel(Long id, @RequestAttribute("userId") Long userId){
		OrderEntity order = orderService.queryObject(id);
		order.setId(id);
		order.setStatus(0);
		orderService.update(order);
	/*	MemberInfoEntity memberInfoEntity=memberInfoService.queryObject(userId);//用戶信息
		memberInfoEntity.setIntegral(memberInfoEntity.getIntegral()+order.getUseIntegral());//订单取消后返回积分
		memberInfoService.update(memberInfoEntity);
		if(order.getUseIntegral()>0){//积分大于0
			apiIntegralScordSave.insertIntegralRecord(userId,"取消订单返还积分",order.getUseIntegral(),6,order.getId(),order.getOrderNumber(),0);//取消订单退回积分
		}*/

		return R.ok();
	}
	
	@Login
	@RequestMapping("/complete")
	public R complete(Long id, @RequestAttribute("userId") Long userId){
		OrderEntity order = new OrderEntity();
		order.setId(id);
		order.setStatus(7);
		orderService.update(order);
		return R.ok();
	}

	/**
	 * 延长收货
	 * @param id
	 * @param userId
	 * @return
	 */
	@Login
	@RequestMapping("/lengthenReceive")
	public R lengthenReceive(String orderNumber){
		OrderEntity order = orderService.queryByOrderNumber(orderNumber);
		Date date = order.getConfirmTime();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE,day+3);
		order.setConfirmTime(c.getTime());
		orderService.update(order);
		return R.ok();
	}



}
