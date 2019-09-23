//index.js
//获取应用实例
const app = getApp()
Page({
  data: {
    addressList: []
  },

  selectTap: function (e) {
    var id = e.currentTarget.dataset.id;
    // console.log(id)
    this.updateDefault(id);
  },

  //设置成为默认地址
  
  updateDefault: function(addressId){
    var that = this;
    wx.request({
      url: app.globalData.domain + '/app/member/address/updateDefault',
      data: {
        token: app.globalData.token,
        id: addressId
      },
      success: (res) => {
        if (res.data.code == 0) {
          this.initShippingAddress();
        }
      }
    })
  },

  addAddess: function () {
    wx.navigateTo({
      url: "/pages/member/address/address-add/index"
    })
  },

  editAddess: function (e) {
    wx.navigateTo({
      url: "/pages/member/address/address-add/index?id=" + e.currentTarget.dataset.id
    })
  },

  onLoad: function () {
    // console.log('onLoad')


  },
  onShow: function () {
    this.initShippingAddress();
  },
  initShippingAddress: function () {
    var that = this;
    wx.request({
      url: app.globalData.domain + '/app/member/address/list',
      data: {
        token: app.globalData.token
      },
      success: (res) => {
        if (res.data.code == 0) {
          that.setData({
            addressList: res.data.memberAddressList
          });
        }
      }
    })
  }

})
