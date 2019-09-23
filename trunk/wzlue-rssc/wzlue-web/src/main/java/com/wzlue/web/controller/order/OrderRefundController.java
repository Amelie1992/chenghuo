
package com.wzlue.web.controller.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wzlue.member.dao.MemberInfoDao;
import com.wzlue.member.entity.MemberInfoEntity;
import com.wzlue.order.entity.LogisticsEntity;
import com.wzlue.order.service.LogisticsService;
import com.wzlue.order.service.impl.ApiIntegralScordSaveTwo;
import com.wzlue.sys.util.ExpressHundred;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;
import com.wzlue.order.entity.OrderRefundEntity;
import com.wzlue.order.service.OrderRefundService;
import com.wzlue.web.controller.sys.AbstractController;


/**
 * 退款
 *
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-08 21:03:13
 */
@RestController
@RequestMapping("/order/orderrefund")
public class OrderRefundController extends AbstractController {
	@Autowired
	private OrderRefundService orderRefundService;
	@Autowired
	private LogisticsService logisticsService;
	@Autowired
	private MemberInfoDao memberDao;
	@Autowired
	public ApiIntegralScordSaveTwo apiIntegralScordSave;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);

		List<OrderRefundEntity> orderRefundList = orderRefundService.queryList(query);
		int total = orderRefundService.queryTotal(query);

		return R.page(orderRefundList, total);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("order:orderrefund:info")
	public R info(@PathVariable("id") Long id) {
		OrderRefundEntity orderRefund = orderRefundService.queryObject(id);

		return R.ok().put("orderRefund", orderRefund);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("order:orderrefund:save")
	public R save(@RequestBody OrderRefundEntity orderRefund) {
		orderRefundService.save(orderRefund);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("order:orderrefund:update")
	public R update(OrderRefundEntity orderRefund) {
		try {
			orderRefundService.update(orderRefund);
		} catch (WxPayException e) {
			e.printStackTrace();
		}

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("order:orderrefund:delete")
	public R delete(@RequestBody Long[] ids) {
		orderRefundService.deleteBatch(ids);

		return R.ok();
	}

	/**
	 * 处理退货退款
	 * @param id
	 * @param remarks 备注
	 * @param status（5同意6拒绝）
	 * @return
	 * @throws WxPayException
	 */
	@RequestMapping("/handle")
	@RequiresPermissions("order:orderrefund:update")
	public R handle(Long id, String remarks, Integer status,String refundNumber,String orderNumber ) throws WxPayException {
		OrderRefundEntity orderRefund = new OrderRefundEntity();
		orderRefund.setId(id);
		orderRefund.setRemarks(remarks);
		orderRefund.setStatus(status);
		orderRefund.setRefundNumber(refundNumber);//退款编码
		orderRefund.setOrderNumber(orderNumber);//订单编号
		orderRefundService.update(orderRefund);
		return R.ok();
	}

	/**
	 * 确认收件退款
	 * 主键+状态+快递费
	 * @param id
	 * @param status
	 * @return
	 * @throws WxPayException
	 */
	@RequestMapping("/refundReceipt")
	@RequiresPermissions("order:orderrefund:update")
	public R refundReceipt(Long id, Integer status, BigDecimal expressFee) throws WxPayException {
		OrderRefundEntity orderRefund = new OrderRefundEntity();
		orderRefund.setId(id);
		orderRefund.setStatus(status);
		orderRefund.setExpressFee(expressFee);
		orderRefundService.refundReceipt(orderRefund);
		return R.ok();
	}

	/**
	 * 退货发货
	 *
	 * @param refundNumber
	 * @param companyName
	 * @param logisticsNumber
	 * @return
	 */
	@RequestMapping("/sendGoods")
	@RequiresPermissions("order:update")
	public R sendGoods(String refundNumber, Long companyId, String companyName, String logisticsNumber,Long logisticsId,String logisticsPrice) {
		orderRefundService.sendGoods(refundNumber, companyId, companyName, logisticsNumber,logisticsId,logisticsPrice);
		return R.ok();
	}

	/**
	 * 查询实时物流
	 *
	 * @param companyId 快递公司Id
	 * @param nu        物流单号
	 * @return
	 */
	@RequestMapping("/queryLogistics")
	public R queryLogistics(Long companyId, String nu) {
		LogisticsEntity logistics = logisticsService.queryObject(companyId);
		//查询列表数据
		/**
		 * com 快递公司代码
		 */
		String content = ExpressHundred.getSynquery(logistics.getCompanyCode(), nu);
		return R.ok().put("content", content);
	}

	//退运费
	@RequestMapping("/tuiK")
	public R tuiK(String id,String money){
		Integer a= Integer.valueOf(money);
		MemberInfoEntity member=memberDao.queryObject(id);
		member.setIntegral(member.getIntegral()+a);
		memberDao.update(member);//保存积分
		Long idTwo= Long.valueOf(id);
		apiIntegralScordSave.insertIntegralRecord(idTwo,"退运费返积分",a,8,member.getId(),member.getNickName(),0);

		return R.ok();

	}
}
