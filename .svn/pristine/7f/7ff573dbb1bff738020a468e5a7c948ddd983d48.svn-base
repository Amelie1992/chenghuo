var app = getApp();
// pages/order/after-record/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  },

  /**
   * 生命周期函数--监听页面加载
   * 1待处理，2退款成功，3退款失败
   */
  onLoad: function (options) {
    // console.log(options)
    wx.request({
      url: `${app.globalData.domain}/app/orderrefund/getByOrderNumber`,
      data: {
        id: options.id,
        orderNumber: options.orderNumber,
        token: app.globalData.token,
      },
      success:res=>{
        if(res.data.code ==0){
          // console.log(res)
          this.setData({
            orderRefund: res.data.orderRefund,
            order:res.data.order,
            orderInfo: res.data.order
          })
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function (e) {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})