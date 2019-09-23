// pages/invoice/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    countries: ["单位", "个人"],
    countryIndex: 0
  },
  bindCountryChange: function(e) {
    var that = this;
    wx.getSetting({
      success(res) {
        if (!res.authSetting['scope.invoiceTitle']) {
          wx.authorize({
            scope: 'scope.invoiceTitle',
            success() {
              that.toInvoiceTitle();
            },
            fail(){
              //用户拒绝授权
              wx.showModal({
                title: '提示',
                content: '发票需要您的微信授权才能使用哦~ 错过授权页面的处理方法：删除小程序->重新搜索进入->点击授权按钮',
                cancelText: "不授权",
                confirmText: "授权",
                confirmColor: "#a08250",
                success: function (res) {
                  if (res.confirm) {
                    // 这个 API 是基础库 1.1.0 才有的，所以需要做兼容处理：
                    if (wx.openSetting) {
                      wx.openSetting({
                        success: function (res) {//第三种情况：用户拒绝授权，进入引导弹窗，用户点击授权，进入授权设置页，用户点击授权。 
                          that.toInvoiceTitle();
                        }
                      })
                    } else {
                      wx.showModal({
                        title: '授权提示',
                        content: '发票需要您的微信授权才能使用哦~ 错过授权页面的处理方法：删除小程序->重新搜索进入->点击授权按钮'
                      })
                    }
                  } else if (res.cancel) {//第二种情况：用户拒绝授权，进入引导弹窗，用户继续拒绝授权。
                    wx.showModal({
                      title: '提示',
                      content: '发票获取失败，错过授权页面的处理方法：删除小程序->重新搜索进入->点击授权按钮',
                      showCancel: false,
                      confirmColor: "#c00",
                      success: function (res) {
                        if (res.confirm) {
                          console.log('用户点击确定')
                        }
                      }
                    })
                  }
                }
              })
              console.log(res);
            }
          })
        } else {
          that.toInvoiceTitle();
        }
      },
      fail: function(res) {
        console.log(res)
      }
    })
  },
  toInvoiceTitle: function(){
    wx.chooseInvoiceTitle({
      success: res => {
        this.setData({
          "invoiceInfo": res,
          "countryIndex": res.type
        })
        wx.setStorageSync("invoiceType", this.data.countryIndex);
        wx.setStorageSync("invoiceInfo", res);
      },
      fail: function (res) {
        console.log(res)
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    wx.getStorage({
      key: 'invoiceInfo',
      success: (res) => {
        this.setData({
          "invoiceInfo": res.data,
          "countryIndex": res.data.type
        })
      },
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})