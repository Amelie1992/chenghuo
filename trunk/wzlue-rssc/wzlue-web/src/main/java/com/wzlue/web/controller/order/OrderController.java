
package com.wzlue.web.controller.order;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.wzlue.common.base.R;

import java.util.*;

import com.wzlue.common.utils.DateUtils;
import com.wzlue.common.utils.EasyPoiUtil;
import com.wzlue.contact.dao.MMemberLogDao;
import com.wzlue.order.dao.OrderDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wzlue.web.controller.sys.AbstractController;

import com.wzlue.order.entity.OrderEntity;
import com.wzlue.order.service.OrderService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 订单
 *
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:17
 */
@RestController
@RequestMapping("/order/order")
public class OrderController extends AbstractController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MMemberLogDao mMemberLogDao;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<OrderEntity> orderList = orderService.queryList(query);
        int total = orderService.queryTotal(query);

        return R.page(orderList, total);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:info")
    public R info(@PathVariable("id") Long id) {
        OrderEntity order = orderService.queryObject(id);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:save")
    public R save(@RequestBody OrderEntity order) {
        orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:update")
    public R update(@RequestBody OrderEntity order) {
        orderService.update(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:delete")
    public R delete(@RequestBody Long[] ids) {
        orderService.deleteBatch(ids);

        return R.ok();
    }

    @RequestMapping("/sendGoods")
    @RequiresPermissions("order:update")
    public R sendGoods(String orderNumber, Long companyId, String companyName, String logisticsNumber) {
        orderService.sendGoods(orderNumber, companyId, companyName, logisticsNumber);
        return R.ok();
    }

    @RequestMapping("/statistics")
    public R statistics() {
        Map<String, Object> statistics = orderService.statistics();
        List<Map<String, Object>> chart = orderService.queryOrderChart();
        Map<String, Object> mapList=new HashMap<>();
        double todayPrice=orderDao.todayPrice();//今日成交额
        double yesterDayPrice=orderDao.yesterdayPrice();//昨日成交额
        int visitToday=mMemberLogDao.visitToday();//今日访问数
        int visitYeasterDay=mMemberLogDao.visitYeasterDay();//昨日访问数
        int okOrder=orderDao.okOrder();//成功的订单
        double keDanPrice=orderDao.keDanPrice();
        mapList.put("todayPrice",todayPrice);
        mapList.put("yesterDayPrice",yesterDayPrice);
        mapList.put("visitToday",visitToday);
        mapList.put("visitYeasterDay",visitYeasterDay);
        mapList.put("okOrder",okOrder);
        mapList.put("keDanPrice",keDanPrice);


        return R.ok().put("statistics", statistics).put("chart", chart).put("mapList",mapList);
    }

    @RequestMapping("/queryOrderChart")
    public R queryOrderChart() {
        List<Map<String, Object>> result = orderService.queryOrderChart();
        return R.ok().put("result", result);
    }
/*导出待发货的订单*/
    @RequestMapping("outPut")
    public void exportOrder(HttpServletResponse response){
        List<Map<String,Object>> list=orderDao.exportOrder();
        List<ExcelExportEntity> exeList=addList();
        EasyPoiUtil.exportExcelByExcelExportEntity(list, null, "订单", exeList, "订单Excel" + DateUtils.format(new Date(), DateUtils.DATE_PATTERN) + ".xls", response);


    }

    private List<ExcelExportEntity> addList(){
        List<ExcelExportEntity> exeList=new ArrayList<>();
        exeList.add(new ExcelExportEntity("订单号","oNumber"));
        exeList.add(new ExcelExportEntity("供货商名称","supplierName"));
        exeList.add(new ExcelExportEntity("供货商联系方式","supplierContact"));
        exeList.add(new ExcelExportEntity("成本单价","unitCost"));
        exeList.add(new ExcelExportEntity("成交数量","buyNum"));
        exeList.add(new ExcelExportEntity("成本总价","sum"));
        exeList.add(new ExcelExportEntity("货号","itemNo"));
        exeList.add(new ExcelExportEntity("收货信息","detail"));
        exeList.add(new ExcelExportEntity("收货人名称","customerName"));
        exeList.add(new ExcelExportEntity("联系人电话","telephone"));
        exeList.add(new ExcelExportEntity("下单时间","payTime"));
        exeList.add(new ExcelExportEntity("运费",""));
        exeList.add(new ExcelExportEntity("快递单号",""));
        return exeList;
    }


}
