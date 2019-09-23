package com.wzlue.app.controller.member;

import java.math.BigDecimal;
import java.util.*;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wzlue.advert.entity.ExtensionPosterEntity;
import com.wzlue.advert.service.ExtensionPosterService;
import com.wzlue.app.controller.wechat.ApiPayController;
import com.wzlue.common.base.Query;
import com.wzlue.common.utils.NumberUtils;
import com.wzlue.contact.dao.MMemberLogDao;
import com.wzlue.contact.entity.MMemberLogEntity;
import com.wzlue.goods.service.GoodsCollectionService;
import com.wzlue.goods.service.GoodsFootprintService;
import com.wzlue.member.entity.IntegralRecordEntity;
import com.wzlue.member.entity.MemberAgreementEntity;
import com.wzlue.member.entity.MemberRecommendEntity;
import com.wzlue.member.service.IntegralRecordService;
import com.wzlue.member.service.MemberAgreementService;
import com.wzlue.member.service.MemberRecommendService;
import com.wzlue.order.entity.OrderEntity;
import com.wzlue.order.service.OrderService;
import com.wzlue.sys.MSdx.ApiDemo4Java;
import com.wzlue.sys.dao.SysConfigDao;
import com.wzlue.sys.entity.SysConfigEntity;
import com.wzlue.sys.service.SysConfigService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.wzlue.app.common.annotation.Login;
import com.wzlue.common.base.R;
import com.wzlue.member.entity.MemberInfoEntity;
import com.wzlue.member.service.MemberInfoService;
import com.wzlue.sys.common.config.IntegralConfig;

@RestController

@RequestMapping("/app/member")
public class ApiMemberInfoController {

	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private IntegralConfig integralConfig;
	@Autowired
	private GoodsCollectionService goodsCollectionService;
	@Autowired
	private GoodsFootprintService goodsFootprintService;
	@Autowired
	private MemberAgreementService memberAgreementService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private IntegralRecordService integralRecordService;
	@Autowired
	private MemberRecommendService memberRecommendService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ApiIntegralScordSave apiIntegralScordSave;
	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private ExtensionPosterService extensionPosterService;
	@Autowired
	private MMemberLogDao mMemberLogDao;


	@Login
	@RequestMapping("/info")
	public R info(@RequestAttribute("userId") Long userId) {
		MemberInfoEntity memberInfo = memberInfoService.queryObject(userId);
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", userId);
		int saveNum = goodsCollectionService.queryTotal(map);
		int footNum = goodsFootprintService.queryTotal(map);
		Long memberInfoId=memberInfo.getId();

		int queryTotalToday=mMemberLogDao.queryTotalToday(memberInfoId);//今天是否访问过
		if(queryTotalToday<1){
			//保存访客记录
			MMemberLogEntity mMemberLogEntity=new MMemberLogEntity();
			mMemberLogEntity.setMemberId(memberInfo.getId().intValue());
			mMemberLogEntity.setOpenId(memberInfo.getOpenid());
			mMemberLogEntity.setTime(new Date());
			mMemberLogDao.save(mMemberLogEntity);
		}

		R r = new R();
		r.put("saveNum", saveNum);
		r.put("footNum", footNum);
		r.put("memberInfo", memberInfo);
		return r;
	}

	@Login
	@RequestMapping("/updateMobile")
	public R updateMobile(String mobile, @RequestAttribute("userId") Long userId) {
		MemberInfoEntity memberInfo = new MemberInfoEntity();
		memberInfo.setId(userId);
		memberInfo.setMobile(mobile);
		memberInfoService.update(memberInfo);
		return R.ok();
	}


	@Login
	@RequestMapping("/updateRealName")
	public R updateRealName(String realName, @RequestAttribute("userId") Long userId) {
		MemberInfoEntity memberInfo = new MemberInfoEntity();
		memberInfo.setId(userId);
		memberInfo.setRealName(realName);
		memberInfoService.update(memberInfo);
		return R.ok();
	}

	/**
	 * 发送手机验证码
	 *
	 * @param mobile
	 * @return
	 */
	@Login
	@RequestMapping("/sendSMS")
	public R sendSMS(String mobile) {
		String smsCode = null;
		String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
		if (!"".equals(mobile) && ReUtil.contains(regex, mobile)) {
			Map<String, Object> map = new HashMap<>();
			map.put("mobile", mobile);
			int total = memberInfoService.queryTotal(map);
			if (total > 0)
				return R.error("手机号码已被绑定！");
			ApiDemo4Java.send(mobile);
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 验证短信码
	 *
	 * @param mobile
	 * @param smsCode
	 * @return
	 */
	@Login
	@RequestMapping("/testSmsCode")
	public R register(String mobile, String smsCode) {
		// 验证短信码
		if (!ApiDemo4Java.yz(mobile, smsCode)) {
			return R.error("短信码错误!");
		}
		//绑定用户
//		MemberInfoEntity memberInfo = memberInfoService.queryObject(userId);
//		memberInfo.setMobile(mobile);
//		memberInfoService.update(memberInfo);
		return R.ok();
	}

	/**
	 * 创建虚拟订单
	 * @param userId
	 * @return
	 */
	@Login
	@Transactional
	@RequestMapping("/createOrder")
	public R createOrder(@RequestBody OrderEntity order, @RequestAttribute("userId") Long userId) {
		MemberInfoEntity memberInfo = memberInfoService.queryObject(userId);
		if (memberInfo.getIsVip()==1){
			return R.error("已是会员，请勿重复购买");
		} else {
			order.setMemberId(userId);
			order.setCreateTime(new Date());
			order.setStatus(1);
			orderService.create(order);//生成虚拟订单

			Integer orderUse = order.getUseIntegral();
			Integer yuan=orderUse;//订单所用积分
			Integer integral = memberInfo.getIntegral();//正常积分
			Integer freezingIntegral = memberInfo.getFreezingIntegral();//冻结积分
			Integer all = integral+freezingIntegral;//所拥有的所有积分
			Integer leftIntegral = 0;
			//判断支付类型
			if("integral".equals(order.getPayType())) {//积分支付
				orderUse=orderUse-freezingIntegral;//先扣冻结积分所剩积分
				if (all>=yuan){//所拥有的所有积分比订单所用积分要多
					if(orderUse==0){
						//积分记录保存
						apiIntegralScordSave.insertIntegralRecord(userId,"耗费冻结积分",memberInfo.getFreezingIntegral(),4,order.getId(),order.getOrderNumber(),1);
						memberInfo.setFreezingIntegral(0);

					} else if(orderUse>0){//冻结积分大于订单所要积分
						//积分记录保存
						apiIntegralScordSave.insertIntegralRecord(userId,"耗费冻结积分",memberInfo.getFreezingIntegral(),4,order.getId(),order.getOrderNumber(),1);
						memberInfo.setFreezingIntegral(0);
                        leftIntegral=orderUse;//还需支付的积分
						orderUse = integral-orderUse;//减去正常积分
							if(orderUse > 0){
								memberInfo.setIntegral(orderUse);
							}else{
								memberInfo.setIntegral(0);
							}
						apiIntegralScordSave.insertIntegralRecord(userId,"耗费正常积分",leftIntegral,4,order.getId(),order.getOrderNumber(),0);


					}else{
						leftIntegral = freezingIntegral-yuan;
						memberInfo.setFreezingIntegral(0);
						memberInfo.setIntegral(leftIntegral+memberInfo.getIntegral());
						apiIntegralScordSave.insertIntegralRecord(userId,"耗费冻结积分",yuan,4,order.getId(),order.getOrderNumber(),1);
						//解冻剩余积分
						apiIntegralScordSave.insertIntegralRecord(userId,"解冻积分",leftIntegral,5,order.getId(),order.getOrderNumber(),0);
					}


				} else {
					return R.error("积分不够");
				}

				memberInfo.setIsVip(1);
				memberInfo.setMobile(order.getRamarks());//手机号绑定
				memberInfo.setVipAddTime(new Date());
				Calendar curr = Calendar.getInstance();
				curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 1);//会员有效期一年
				memberInfo.setVipEndTime(curr.getTime());
				memberInfoService.update(memberInfo);
				//订单支付成功--完成里面有返积分
				orderService.payNotifyMember(order);
				return R.ok().put("orderNumber", order.getOrderNumber()).put("paymentAmount", order.getWechatAmount());
			}else if ("mix".equals(order.getPayType())){//混合
				if(all>=orderUse){//积分够的情况下
					int a=orderUse-freezingIntegral;//消耗的正常积分
					if (freezingIntegral>=orderUse) {//冻结积分够
						memberInfo.setFreezingIntegral(orderUse-freezingIntegral);
						if(a<0){
							//冻结积分购买会员记录
							apiIntegralScordSave.insertIntegralRecord(userId,"耗费冻结积分",memberInfo.getFreezingIntegral(),4,order.getId(),order.getOrderNumber(),1);
						}else{
							int b=freezingIntegral-orderUse;
							memberInfo.setFreezingIntegral(0);
							memberInfo.setIntegral(b+memberInfo.getIntegral());//解冻到正常积分
							//解冻剩余积分
							apiIntegralScordSave.insertIntegralRecord(userId,"解冻积分",b,5,order.getId(),order.getOrderNumber(),0);
						}


					} else {
						memberInfo.setFreezingIntegral(0);

						//冻结积分购买会员记录
						apiIntegralScordSave.insertIntegralRecord(userId,"耗费冻结积分",memberInfo.getFreezingIntegral(),4,order.getId(),order.getOrderNumber(),1);


						//正常积分购买会员记录
						apiIntegralScordSave.insertIntegralRecord(userId,"耗费正常积分",a,4,order.getId(),order.getOrderNumber(),0);
						memberInfo.setIntegral(orderUse-freezingIntegral);
						BigDecimal number = new BigDecimal(0);
						number=BigDecimal.valueOf((int)0);
//						//应付-积分
//						BigDecimal wechatPay = order.getPaymentAmount().subtract(number);
						order.setWechatAmount(number);
						memberInfo.setIsVip(1);
						memberInfo.setMobile(order.getRamarks());//手机号绑定
						memberInfo.setVipAddTime(new Date());
						Calendar curr = Calendar.getInstance();
						curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 1);//会员有效期一年
						memberInfo.setVipEndTime(curr.getTime());
						memberInfoService.update(memberInfo);
						//订单支付成功--完成
						orderService.payNotifyMember(order);
						return R.ok().put("orderNumber", order.getOrderNumber()).put("paymentAmount", order.getWechatAmount());

				}

					memberInfo.setIsVip(1);
					memberInfo.setMobile(order.getRamarks());//手机号绑定
					memberInfo.setVipAddTime(new Date());
					Calendar curr = Calendar.getInstance();
					curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 1);//会员有效期一年
					memberInfo.setVipEndTime(curr.getTime());
					memberInfoService.update(memberInfo);
					//订单支付成功--完成
					orderService.payNotifyMember(order);
					return R.ok().put("orderNumber", order.getOrderNumber()).put("paymentAmount", order.getWechatAmount());
				}
				double amountMoney=(integral+freezingIntegral);//需支付金额
				BigDecimal number = new BigDecimal(0);
				number=BigDecimal.valueOf((double)amountMoney);
				//应付-积分
				BigDecimal wechatPay = order.getPaymentAmount().subtract(number);
				order.setWechatAmount(wechatPay);
				return R.ok().put("orderNumber", order.getOrderNumber()).put("paymentAmount", order.getWechatAmount());

			}
			//微信支付
			BigDecimal wechatPay = order.getPaymentAmount();
			order.setWechatAmount(wechatPay);
			return R.ok().put("orderNumber", order.getOrderNumber()).put("paymentAmount", order.getWechatAmount());
		}
	}

	@Login
	@RequestMapping("/getIntegral")
	public R getIntegral(@RequestAttribute("userId") Long userId) {
		MemberInfoEntity memberInfo = memberInfoService.queryObject(userId);
		return R.ok().put("integral", memberInfo.getIntegral());
	}

	@Login
	@RequestMapping("/signIn")
	public R signIn(@RequestAttribute("userId") Long userId) {
		memberInfoService.signIn(userId);
		return R.ok();
	}

	@Login
	@RequestMapping("/isSignIn")
	public R isSignIn(@RequestAttribute("userId") Long userId) {
		boolean isSignIn = memberInfoService.isSignIn(userId);
		return R.ok().put("isSignIn", isSignIn);
	}

	@Login
	@RequestMapping("/getAvailableIntegral")
	public R getAvailableIntegral(@RequestAttribute("userId") Long userId, BigDecimal paymentAmount) {
		Integer integral = memberInfoService.queryIntegral(userId);
		if (integral > 0) {

			int lose = integralConfig.config3().getDeduction().getLose(); //失去
			int get = integralConfig.config3().getDeduction().getGet(); //获得
			int upLimit = integralConfig.config3().getDeduction().getUpperLimit(); //上限

			int purposeMoney = integral / lose * get;

			if (purposeMoney > paymentAmount.intValue() * upLimit / 100) {
				purposeMoney = paymentAmount.intValue() * upLimit / 100;
				integral = purposeMoney / lose * get;
			}

			return R.ok().put("purposeMoney", purposeMoney).put("integral", integral);

		}
		return R.ok().put("purposeMoney", 0).put("integral", 0);
	}

	//会员下级列表（会员推荐列表）
	@Login
	@RequestMapping("/getRecommender")
	public R getRecommender(@RequestAttribute("userId") Long userId,@RequestParam Map<String, Object> params) {

		params.put("userId", userId);
		Query query=new Query(params);
		List<MemberInfoEntity> memberInfoList = memberInfoService.queryList(query);
		return R.ok().put("memberInfoList", memberInfoList);
	}

	//会员协议接口
	@Login
	@RequestMapping("/getAgreement")
	public R getAgreement(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		params.put("status", 1);
		List<MemberAgreementEntity> memberAgreementList = memberAgreementService.queryList(query);
		return R.ok().put("memberAgreement", memberAgreementList);
	}

	/*推荐人的设置到用户中(锁粉)
	* userId 新用户
	* bigId 是推荐人的
	*/
	@Login
	@RequestMapping("/insertRecommenderId")
	public R insertRecommenderId(@RequestAttribute("userId")Long userId,@RequestParam int bigId){
		int a = bigId;
		long b = (int)a;
		MemberInfoEntity memberInfoEntitySmall=memberInfoService.queryObject(userId);//扫码用户
		MemberInfoEntity memberInfoEntityBig=memberInfoService.queryObject(b);//推荐人用户
		//扫码给的积分
		JsonParser jp = new JsonParser();
		SysConfigEntity sysConfigEntity = sysConfigDao.queryByKey("INTEGRAL_SETTING");
		String value2 = sysConfigEntity.getValue();
		JsonObject jo2 = jp.parse(value2).getAsJsonObject();
		int extendedIntegral = Integer.valueOf(jo2.get("sweepCode").getAsJsonObject().get("get").getAsString());
		if(userId==bigId){//自己推荐不算
			return R.ok().put("code",1);
		}
		if(memberInfoEntitySmall.getRecommenderId()==0){//不是是新粉的话
			memberInfoEntitySmall.setRecommenderId(b);
			memberInfoEntitySmall.setIntegral(memberInfoEntitySmall.getIntegral()+10);//扫码人加10积分
			memberInfoService.update(memberInfoEntitySmall);
			//推荐人和扫码人都获得10积分
			apiIntegralScordSave.insertIntegralRecord(userId,"扫码注册",extendedIntegral,9,userId,memberInfoEntitySmall.getNickName(),0);
			memberInfoEntityBig.setIntegral(memberInfoEntityBig.getIntegral()+10);//推广人加10积分
			memberInfoService.update(memberInfoEntityBig);
			apiIntegralScordSave.insertIntegralRecord(b,"推广注册",extendedIntegral,9,b,memberInfoEntityBig.getNickName(),0);
			return R.ok().put("code",0);//成功(添加成功推荐人)
		}
			return R.ok().put("code",1);//已存在推荐人(不做改变)


	}

	/*最新推广海报*/
	@RequestMapping("/getPoster")
	public R getPoster(Map<String, Object> params){
		params.put("status",1);
		List<ExtensionPosterEntity> extensionPosterList = extensionPosterService.queryListApi(params);
		String posterUrl=extensionPosterList.get(0).getPosterUrl();
		return R.ok().put("posterUrl",posterUrl);
	}




}
