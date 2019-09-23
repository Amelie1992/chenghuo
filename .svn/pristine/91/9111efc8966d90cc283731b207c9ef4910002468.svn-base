var app = getApp();

Page({
  data: {
    orderId: 0,
    goodsList: [],
    durationTime: "00小时00分钟00秒"
  },
  onLoad: function (e) {
    // console.log(e)
    var orderId = e.id;
    this.data.orderId = orderId;
    this.setData({
      orderId: orderId
    });
    this.getOrder(orderId);
   
  },
 

  //订单详情
  getOrder: function (id) {
    wx.showLoading()
    wx.request({
      url: `${app.globalData.domain}/app/order/detail`,
      data: {
        token: app.globalData.token,
        id: id
      },
      success: res => {
        if (res.data.code == 0) {
          this.setData({
            orderInfo: res.data.order
          })
          var orderTime = res.data.order.createTime;
          this.countDown(orderTime);
        }
      },
      complete: res => {
        wx.hideLoading();
      }
    })
  },

  onShow: function () {
    var that = this;
    let order_info = that.data.orderInfo;
    that.setData({
      // orderDetail: order_info.data
    });
    var yunPrice = parseFloat(this.data.yunPrice);
    var allprice = 0;
    var goodsList = this.data.goodsList;
    for (var i = 0; i < goodsList.length; i++) {
      allprice += parseFloat(goodsList[0].price) * goodsList[0].number;
    }
    this.setData({
      allGoodsPrice: allprice,
      yunPrice: 0
    });
  },
  wuliuDetailsTap: function (e) {

    var orderId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: "/pages/order/wuliu/index?id=" + orderId
    })
  },
  afterSale: function (e) {
    var orderId = e.currentTarget.dataset.id;
    var orderNumber = e.currentTarget.dataset.ordernumber;
    wx.navigateTo({
      url: "/pages/order/apply-afterSale/index?id=" + orderId + "&orderNumber=" + orderNumber
    })
  },
  after_record: function (e) {
    var orderId = e.currentTarget.dataset.id;
    var orderNumber = e.currentTarget.dataset.ordernumber;
    wx.navigateTo({
      url: "/pages/order/after-record/index?id=" + orderId + "&orderNumber=" + orderNumber
    })
  },
  confirmBtnTap: function (e) {
    let that = this;
    let orderId = this.data.orderId;
    let formId = e.detail.formId;
    wx.showModal({
      title: '确认您已收到商品？',
      content: '',
      success: function (res) {
        if (res.confirm == true) {
          // console.log(res)
          wx.request({
            url: `${app.globalData.domain}/app/order/complete`,
            data: {
              id: orderId,
              token: app.globalData.token
            },
            success: res => {
              if (res.data.code == 0) {
                wx.navigateBack();
              }
            }
          })
        }
      }
    })
  },
  //待付款倒计时
  countDown: function (orderTime) {
    var that = this;
    var now = new Date();
    var orderDate = new Date(orderTime).getTime();
    var maxTime = 1 * 3600 * 1000;
    var rTime = orderDate + maxTime - now;
    var times = rTime / 1000;
    var timer = setInterval(function () {
      var day = 0,
        hour = 0,
        minute = 0,
        second = 0; //时间默认值
      if (times > 0) {
        hour = Math.floor(times / (60 * 60)) - (day * 24);
        minute = Math.floor(times / 60) - (day * 24 * 60) - (hour * 60);
        second = Math.floor(times) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
      } else {
        clearInterval(timer);
      }
      if (day <= 9) day = '0' + day;
      if (hour <= 9) hour = '0' + hour;
      if (minute <= 9) minute = '0' + minute;
      if (second <= 9) second = '0' + second;
      var durationTime = hour + "小时" + minute + "分钟" + second + "秒";
      that.setData({
        durationTime: durationTime
      })
      times--;
    }, 1000);
    if (times <= 0) {
      clearInterval(timer);
    }
  },
  submitReputation: function (e) { }

})