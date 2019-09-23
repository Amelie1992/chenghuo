// var wxpay = require('../../utils/pay.js')
var app = getApp()
var WxPay = require('../../../utils/pay.js')
var util = require('../../../utils/util.js');
Page({
  data: {
    statusType: ["全部","待付款", "待发货", "待收货", "退款"],
    currentType: 0,
    date:''//当前时间
  },
  statusTap: function(e) {
    var curType = e.currentTarget.dataset.index;
    this.data.currentType = curType;
    this.setData({
      currentType: curType
    });
    this.onShow();
  },
  orderDetail: function(e) {
    var orderId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: "/pages/order/order-detail/index?id=" + orderId
    })
  },
  cancelOrderTap: function(e) {
    var that = this;
    var orderId = e.currentTarget.dataset.id;
    wx.showModal({
      title: '确定要取消该订单吗？',
      content: '',
      success: (res) => {
        if (res.confirm) {
          //取消订单
          this.cancelOrder(orderId);
        }
      }
    })
  },

  //查询订单 status 1:待付款，2:待发货，3:待收货，4:退款， """:全部
  getOrderList: function(status) {
    // console.log(status)
    let data = {};
    data.token = app.globalData.token
    data.status = status;
    if(status == 0){
      delete data.status;
      delete data.statuses;
    }
    // if (status == 4) {
    //   delete data.status;
    //   data.statuses = "4,5,6"
    // }
    wx.showLoading({
      title: "请稍候",
      duration: 500
    })
    wx.request({
      url: `${app.globalData.domain}/app/order/list`,
      data: data,
      success: res => {
        if (res.data.code == 0) {
          this.setData({
            orderList: res.data.orderList
          })
        }
      },
    })
  },

  //取消订单
  cancelOrder: function(id) {
    wx.showLoading()
    wx.request({
      url: `${app.globalData.domain}/app/order/cancel`,
      data: {
        token: app.globalData.token,
        id: id
      },
      success: res => {
        // console.log(res);
        if (res.data.code == 0) {
          this.onShow();
        }
      },
      complete: res => {
        wx.hideLoading();
      }
    })
  },
  getTime:function(){
    var timeOne = util.formatTime(new Date());
    this.setData({
      date: timeOne
    });
  },
  //支付
  toPayTap: function(e) {
    this.getTime()
    console.log(this.data.date)
    let that = this;
    var paymentAmount = e.currentTarget.dataset.paymentamount;
    var orderNumber = e.currentTarget.dataset.ordernumber;
    var curType = this.data.currentType;
    WxPay.wxpay(app, paymentAmount, orderNumber, function () {
      that.getOrderList(curType);
    });
  },
  onLoad: function(options) {
    // 生命周期函数--监听页面加载
    var arr = Object.keys(options);
    if (arr.length > 0) {
      let id = options.id - 1;
      this.setData({
        currentType: id
      })
    }
    
  },
  onReady: function() {
    // 生命周期函数--监听页面初次渲染完成

  },

  onShow: function(e) {
    // 获取订单列表
    var curType = this.data.currentType;
    this.getOrderList(curType);

  },
  onHide: function() {
    // 生命周期函数--监听页面隐藏

  },
  onUnload: function() {
    // 生命周期函数--监听页面卸载

  },
  onPullDownRefresh: function() {
    // 页面相关事件处理函数--监听用户下拉动作

  },
  onReachBottom: function() {
    // 页面上拉触底事件的处理函数

  }
})