// var wxpay = require('../../utils/pay.js')
var app = getApp()
Page({
  data: {
    orderInfo: {}
  },
  orderDetail: function(e) {
    var orderId = this.data.orderId;
    // console.log(orderId)
    wx.navigateTo({
      url: "/pages/order/order-detail/index?id=" + orderId
    })
  },
  toPayTap: function(e) {

  },
  onLoad: function(options) {
    // 生命周期函数--监听页面加载

    // console.log(options);
    this.setData({
      orderId: options.id,
      orderNumber:options.orderNumber
    })
    this.getOrder(options.orderNumber)
  },

  //订单商品
  getOrder: function(orderNumber) {
    wx.showLoading()
    wx.request({
      url: `${app.globalData.domain}/app/order/orderGoods`,
      data: {
        token: app.globalData.token,
        orderNumber: orderNumber
      },
      success: res => {
        if (res.data.code == 0) {
          this.setData({
            orderInfo: res.data.orderGoodsList
          })
        }
      },
      complete: res => {
        wx.hideLoading();
      }
    })
  },

  onReady: function() {
    // 生命周期函数--监听页面初次渲染完成

  },

  onShow: function(e) {


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