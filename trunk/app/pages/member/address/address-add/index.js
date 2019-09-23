//获取应用实例
var app = getApp()
Page({
  data: {
    region: ["请选择", "请选择", "请选择"],
    customItem: "请选择",
  },
  bindCancel: function() {
    wx.navigateBack({})
  },

  //update
  getAddress: function(id) {
    if (id) {
      wx.request({
        url: `${app.globalData.domain}/app/member/address/detail`,
        header: {
          token: app.globalData.token
        },
        data: {
          id: id
        },
        success: res => {
          // console.log(res.data.memberAddress)
          let data = res.data.memberAddress;
          this.setData({
            region: [data.province, data.city, data.county],
            wxaddress: data
          })

        }
      })
    }
  },

  bindSave: function(e) {
    // console.log(e)
    var that = this;
    var userName = e.detail.value.linkMan;
    var address = e.detail.value.address;
    var mobile = e.detail.value.mobile;
    var code = e.detail.value.code;
    var id = e.detail.target.dataset.id;

    if (userName == "") {
      wx.showModal({
        title: '提示',
        content: '请填写联系人姓名',
        showCancel: false
      })
      return
    }
    var myUserName = /^[\u4e00-\u9fa5]{2,20}$/;
    if (!myUserName.test(userName)){
      wx.showModal({
        title: '提示',
        content: '联系人请填写2-20位的长度',
        showCancel: false
      })
      return 
    }
    if (mobile == "") {
      wx.showModal({
        title: '提示',
        content: '请填写手机号码',
        showCancel: false
      })
      return
    }

    var myreg = /^1\d{10}$/;
    if (!myreg.test(mobile)) {
      wx.showModal({
        title: '提示',
        content: '请填写正确的手机号码',
        showCancel: false
      })
      return
    }

    if (this.data.region[0] == "请选择") {
      wx.showModal({
        title: '提示',
        content: '请选择地区',
        showCancel: false
      })
      return
    }
    if (this.data.region[1] == "请选择") {
      wx.showModal({
        title: '提示',
        content: '请选择地区',
        showCancel: false
      })
      return
    }

    if (this.data.region[2] == "请选择") {
      wx.showModal({
        title: '提示',
        content: '请选择地区',
        showCancel: false
      })
      return
    }


    if (address == "") {
      wx.showModal({
        title: '提示',
        content: '请填写详细地址',
        showCancel: false
      })
      return
    }

    var myAddress = /^.{1,100}$/;
    if (!myAddress.test(address)){
        wx.showModal({
        title: '提示',
        content: '小于100长度',
        showCancel: false
      })
      return
    }
    // if (code == "") {
    //   wx.showModal({
    //     title: '提示',
    //     content: '请填写邮编',
    //     showCancel: false
    //   })
    //   return
    // }
    var myCode = /^\d{6}$/;
    if (!myCode.test(code)) {
      wx.showModal({
        title: '提示',
        content: '邮编输入6位长度的数字',
        showCancel: false
      })
      return
    }
    wx.showModal({
      title: '提示',
      content: '确定要保存该收货地址吗？',

      success: function(res) {
        if (res.confirm) {
          var address_info = {
            userName: userName,
            telNumber: mobile,
            street: address,
            province: that.data.region[0],
            city: that.data.region[1],
            county: that.data.region[2],
            defaultAddress: 1,
            zipCode: code
          }
          let url;
          if (id) {
            delete address_info.defaultAddress;
            address_info.id = id;
            url = `${app.globalData.domain}/app/member/address/update` //更新
          } else {
            url = `${app.globalData.domain}/app/member/address/add` //新增
          }
          wx.request({
            url: url,
            header: {
              token: app.globalData.token
            },
            method: "POST",
            data: address_info,
            success: res => {
              // 跳转到结算页面
              wx.navigateBack({})
            }
          })
        } else if (res.cancel) {
          // console.log('用户点击取消')
        }
      }
    })

  },

  onLoad: function(e) {
    // console.log(e)
    var that = this;
    var id = e.id;
    that.getAddress(id)
  },
  bindRegionChange: function(e) {
    // console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      region: e.detail.value
    })
  },
  deleteAddress: function(e) {
    var that = this;
    var id = e.currentTarget.dataset.id; 
    wx.showModal({
      title: '提示',
      content: '确定要删除该收货地址吗？',
      success: function(res) {
        if (res.confirm) {
          wx.request({
            url: `${app.globalData.domain}/app/member/address/delete`,
            header: {
              token: app.globalData.token
            },
            data: {
              id: id
            },
            success: res => {
              if (res.data.code == 0) {
                wx.navigateBack({})
                // console.log(res)
              }
            }
          })
        } else if (res.cancel) {
          // console.log('用户点击取消')
        }
      }
    })
  },
})