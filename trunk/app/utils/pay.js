function wxpay(app, money, orderNum, callback) {
  wx.request({
    header: {
      "token": app.globalData.token
    },
    url: app.globalData.domain + '/app/wechat/pay/unifiedOrder',
    data: {
      orderNumber: orderNum,
      paymentAmount: money,
      remark: "支付订单：" + orderNum
    },
    method: 'POST',
    success: function(res) {
      if (res.data.code == 0) {
        // 发起支付
        wx.requestPayment({
          timeStamp: res.data.data.timeStamp,
          nonceStr: res.data.data.nonceStr,
          package: res.data.data.package,
          signType: 'MD5',
          paySign: res.data.data.paySign,
          fail: function(resp) {
            wx.showToast({
              title: '支付失败'
            })
          },
          success: function() {
            wx.showToast({
              title: '支付成功'
            })
            wx.navigateTo({
              url: '/pages/index/index'    
            })
            callback();
          }
        })
      } else {
        wx.showToast({
          title: '服务器忙' + res.data.code
        })
      }
    }
  })
}

module.exports = {
  wxpay: wxpay
}