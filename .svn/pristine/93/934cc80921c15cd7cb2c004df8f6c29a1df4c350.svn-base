const app = getApp()


Page({
  data: {
    fun_id: 2,
    disabled: false,
    timer: "",
    hiddenmodalput: true,
    realName: ""
  },
  onLoad() {

  },
  onShow() {
    let that = this;
    let userInfo = wx.getStorageSync('userInfo')
    if (!userInfo) {
      // wx.navigateTo({
      //   url: "/pages/auth/index"
      // })
    } else {
      that.setData({
        // userInfo: userInfo,
        version: app.globalData.version
      })
    }
    wx.request({
      url: `${app.globalData.domain}/app/member/info`,
      data: {
        token: app.globalData.token
      },
      success: res => {
        if (res.data.code == 0) {
          this.setData({
            userInfo: res.data.memberInfo,
            realName: res.data.memberInfo.realName,
            phoneNumber: res.data.memberInfo.mobile
          })
        }
      }
    })
  },
  //获取手机号
  getPhoneNumber: function (e) {
    if (e.detail.encryptedData != undefined) {
      //解析返回手机号码
      wx.request({
        url: `${app.globalData.domain}/app/wechat/phone`,
        data: {
          token: app.globalData.token,
          iv: e.detail.iv,
          encryptedData: e.detail.encryptedData,
          sessionKey: app.globalData.sessionKey
        },
        success: res => {
          if (res.data.code == 0) {
            wx.request({
              url: `${app.globalData.domain}/app/member/updateMobile`,
              data: {
                mobile: res.data.wechatPhone.phoneNumber,
                token: app.globalData.token,
              },
              success: res => {
                // console.log(res)
              }
            })
            this.setData({
              phoneNumber: res.data.wechatPhone.phoneNumber
            })
          }
        }
      })
    }
  },
  userName: function () {
      this.setData({
        hiddenmodalput: false
      })
  },
  inputName: function (e) {
    this.data.realName = e.detail.value;
  },
  confirm: function (e) {
    wx.request({
      url: `${app.globalData.domain}/app/member/updateRealName`,
      data: {
        realName: this.data.realName,
        token: app.globalData.token,
      },
      success: res => {
        if (res.data.code == 0) {
          this.setData({
            hiddenmodalput: true,
            realName: this.data.realName
          })
        }
      }
    })
    // console.log(this.data.realName)
  },
  cancel: function () {
    this.setData({
      hiddenmodalput: true
    })
  },
  toDaifu: function () {
    wx.navigateTo({
      url: "/pages/order/order-list/index"
    })
  },
  toDaifa: function () {
    wx.navigateTo({
      url: "/pages/order/order-list/index"
    })
  },
  toDaishou: function () {
    wx.navigateTo({
      url: "/pages/order/order-list/index"
    })
  },
  toAll: function () {
    wx.navigateTo({
      url: "/pages/order/order-list/index"
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