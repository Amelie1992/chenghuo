const app = getApp()

Page({
  data: {
    balance: 0,
    freeze: 0,
    score: 0,
    score_sign_continuous: 0
  },
  onLoad() {

  },
  onShow() {
    let that = this;
    if (!app.globalData.token) {
      wx.navigateTo({
        url: "/pages/auth/index"
      })
    } else {
      wx.request({
        url: `${app.globalData.domain}/app/member/info`,
        data: {
          token: app.globalData.token
        },
        success: res => {
          if (res.data.code == 0) {
            this.setData({
              userInfo: res.data.memberInfo,
              saveNum: res.data.saveNum,
              footNum: res.data.footNum,
            })
          }
        }
      })
    }
    this.getOrderStatistics();
    this.getGoodsTj();
  },

  getOrderStatistics: function () {
    wx.request({
      url: `${app.globalData.domain}/app/order/statistics`,
      header: {
        token: app.globalData.token
      },
      success: res => {
        if (res.data.code == 0) {
          // console.log(res)
          this.setData({
            orderNumber: res.data
          })
        }
      }
    })
  },
  getGoodsTj: function () {
    //推荐
    wx.request({
      url: `${app.globalData.domain}/app/goods/list`,
      data: {
        sign: 1
      },
      success: res => {
        this.setData({
          goodsListTj: res.data.goodsList
        })
      }
    })
  },
  toUser: function () {
    wx.navigateTo({
      url: "/pages/member/user/index"
    })
  },
  toDaifu: function (e) {
    var id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: "/pages/order/order-list/index?id="+id
    })
  },

  relogin: function () {
    wx.navigateTo({
      url: "/pages/auth/index"
    })
  },
  recharge: function () {
    wx.navigateTo({
      url: "/pages/recharge/index"
    })
  },
  withdraw: function () {
    wx.navigateTo({
      url: "/pages/withdraw/index"
    })
  }
})