//app.js
App({
  onLaunch: function() {
    var that = this;
    this.login(function(data) {});
  },

  //登录
  login: function(callback) {
    var that = this;
    wx.login({
      success: res => {
        wx.request({
          url: `${this.globalData.domain}/app/wechat/login`,
          data: {
            code: res.code
          },
          success: res => {
            if (res.data.code == 1) { //未注册
              this.globalData.sessionKey = res.data.sessionKey;
              this.register(callback);
              return;
            }
            if (res.data.code != 0) {
              wx.hideLoading();
              wx.showModal({
                title: '提示',
                content: '无法登录，请重试',
                showCancel: false
              })
              return;
            }
            this.globalData.token = res.data.token;
            this.globalData.sessionKey = res.data.sessionKey;
            callback();
          }
        })
      },
      fail: function(res) {
        // console.log(res)
      }
    })
  },

  //注册
  register: function(callback) {
    var that = this;
    wx.login({
      success: res => {
        var code = res.code; // 微信登录接口返回的 code 参数，下面注册接口需要用到
        wx.getUserInfo({
          success: userRes => {
            var iv = userRes.iv;
            var encryptedData = userRes.encryptedData;
            var rawData = userRes.rawData;
            var signature = userRes.signature;
            // 下面开始调用注册接口
            wx.request({
              url: `${this.globalData.domain}/app/wechat/register`,
              data: {
                code: code,
                encryptedData: encryptedData,
                iv: iv,
                rawData: rawData,
                signature: signature,
                sessionKey: this.globalData.sessionKey
              },
              success: res => {
                if (res.data.code == 0) {
                  wx.hideLoading();
                  this.login(callback);
                } else {
                  // 登录错误 
                  wx.hideLoading();
                  wx.showModal({
                    title: '提示',
                    content: '无法登录，请重试',
                    showCancel: false
                  })
                }
              }
            })
          }
        })
      }
    })
  },

  globalData: {
    userInfo: null,
    domain: "http://127.0.0.1:8088",
    //domain: "http://10.0.32.194:8088",
    //domain: "http://10.0.32.221:8088",
    //domain: "https://swy.sun7y.com",
    token: ""
  }
})