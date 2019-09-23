//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    orderInfo: { "code": 0, "data": { "logisticsTraces": [{ "AcceptStation": "【芜湖市】  【芜湖】（0553-7553079、0553-7556060） 的 芜湖镜湖区十一部 （18949431019） 已揽收", "AcceptTime": "2018-07-31 15:18:33" }, { "AcceptStation": "[芜湖市]快件离开芜湖已发往芜湖中转部", "AcceptTime": "2018-07-31 16:28:14" }, { "AcceptStation": "[芜湖市]快件离开芜湖中转部已发往南京中转部", "AcceptTime": "2018-07-31 20:07:24" }, { "AcceptStation": "[南京市]快件离开南京中转部已发往南京雨花区", "AcceptTime": "2018-07-31 22:45:23" }, { "AcceptStation": "[南京市]南京雨花区的银杏山庄[15556663546]正在派件", "AcceptTime": "2018-08-01 08:42:05" }, { "AcceptStation": "[南京市]快件已投递【丰巢的银杏山庄(丰巢智能快递柜)】如有问题请电联【】，感谢您使用中通快递，期待再次为您服务！", "AcceptTime": "2018-08-01 11:02:13" }, { "AcceptStation": "[南京市]快件已在南京雨花区签收 签收人：快递柜,感谢您使用中通快递，期待再次为您服务!", "AcceptTime": "2018-08-01 11:31:24" }], "orderInfo": { "amount": 20.00, "amountLogistics": 0.00, "amountReal": 20.00, "dateAdd": "2018-08-01 14:31:33", "dateClose": "2018-08-01 15:01:33", "dateUpdate": "2018-08-04 15:26:00", "goodsNumber": 2, "hasRefund": false, "id": 130928, "isCanHx": false, "isNeedLogistics": true, "isPay": true, "isSuccessPingtuan": false, "orderNumber": "OD1808010555128559", "remark": "", "score": 20, "status": 4, "statusStr": "交易成功", "type": 0, "uid": 493101, "userId": 8629 }, "goods": [{ "amount": 20.00, "dateReputation": "2018-08-04 15:26:00", "goodReputation": 0, "goodReputationRemark": "qwerrewqrwerqewrqwerqew", "goodReputationStr": "差评", "goodsId": 74844, "goodsName": "12321", "id": 145377, "number": 2, "orderId": 130928, "pic": "https://cdn.it120.cc/apifactory/2018/07/28/ee088bb76329879c0b317c1740208326.png", "property": "21321:2132,shangcheng:123,23:shangcheng", "score": 20, "uid": 493101, "userId": 8629 }], "logistics": { "address": "213213", "areaStr": "-", "cityId": 110101, "cityStr": "东城区", "code": "213", "dateUpdate": "2018-08-01 14:50:38", "districtId": 0, "id": 130928, "linkMan": "123", "mobile": "13512123213", "provinceId": 110000, "provinceStr": "北京市", "shipperCode": "ZTO", "shipperName": "中通速递", "status": 3, "trackingNumber": "540738747554", "type": 0 }, "logs": [{ "dateAdd": "2018-08-01 14:31:33", "id": 463288, "orderId": 130928, "type": 0, "typeStr": "下单" }, { "dateAdd": "2018-08-01 14:31:42", "id": 463291, "orderId": 130928, "type": 1, "typeStr": "支付" }, { "dateAdd": "2018-08-01 14:32:38", "id": 463292, "orderId": 130928, "type": 2, "typeStr": "发货" }, { "dateAdd": "2018-08-03 15:56:21", "id": 464196, "orderId": 130928, "type": 3, "typeStr": "确认收货" }, { "dateAdd": "2018-08-04 15:26:00", "id": 464547, "orderId": 130928, "type": 4, "typeStr": "评价" }] }, "msg": "success" }
  },
  onLoad: function (e) {
    var orderId = e.id;
    this.data.orderId = orderId;
  },
  onShow: function () {
    var that = this;
    let res = that.data.orderInfo;
    that.setData({
      orderDetail: res.data,
      logisticsTraces: res.data.logisticsTraces.reverse()
    });
  }
})
