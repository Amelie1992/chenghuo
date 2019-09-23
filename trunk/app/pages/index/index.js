// pages/home/index.js
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    categoryList: [],
    sysShop:[]
  },
  toSearch: function () {
    wx.navigateTo({
      url:"/pages/index/search/index"
    })
  },
  hideInput: function () {
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  clearInput: function () {
    this.setData({
      inputVal: ""
    });
  },
  inputTyping: function (e) {
    this.setData({
      inputVal: e.detail.value
    });
  },
  //跳转关于我们的界面
  goShop: function()
  {
      wx.navigateTo({
        url: `../../pages/company/about/index`,
      })
  },
  //跳转合作伙伴的界面
  goCooperation:function(){
      wx.navigateTo({
        url: `../../pages/company/cooperation/index`,
      })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getBanner();
    this.getCategory();
    this.getGoodsJx();
    this.getStore();
  },

  getCategory: function () {
    wx.request({
      url: `${app.globalData.domain}/app/category/list`,
      data: {

      },
      success: res => {
        this.setData({
          categoryList: res.data.categoryList
        })
      }
    })
  },

  getBanner: function () {
    wx.request({
      url: `${app.globalData.domain}/app/advert/list`,
      data: {
        positionId: 1
      },
      success: res => {
        this.setData({
          bannerList: res.data.adertList
        })
      }
    })
  },

  getGoodsJx: function(){
    //精选
    wx.request({
      url: `${app.globalData.domain}/app/goods/list`,
      data: {
        sign: 1
      },
      success: res => {
        this.setData({
          goodsListJx: res.data.goodsList
        })
      }
    })
    
  },
  

  getGoodsTj: function(){
    //推荐
    wx.request({
      url: `${app.globalData.domain}/app/goods/list`,
      data: {
        sign: 2
      },
      success: res => {
        this.setData({
          goodsListTj: res.data.goodsList
        })
      }
    })
  },
  // 呼叫号码
  getPhone:function(){
    // 企业号码
    var text = this.data.sysShop.tel
    wx.showActionSheet({
      itemList: [text, '呼叫'],
      success: function (res) {
        
        if (res.tapIndex == 1) {
          wx.makePhoneCall({
            phoneNumber: text,
          })
        }
      }
    })
  },
  //地址的跳转
  getAddress:function(){
    wx.getLocation({
      type: 'gcj02', //返回可以用于wx.openLocation的经纬度
      success: function (res) {
        var latitude = res.latitude
        var longitude = res.longitude
        wx.openLocation({
          latitude: 32.04053,
          longitude: 118.79888,
          name: "南京市秦淮区中山东路402号"
        })
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
  onShow: function () {
  
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
  
  },
  
  makeApp: function() {
    wx.navigateTo({
      url: '/pages/makeapp/index',
    })
  },

  getStore: function(){
    wx.request({
      url: `${app.globalData.domain}/app/sysshop/info/1`,
      data: {
      },
      success: res => {
        var sysShop = res.data.sysShop;
        this.setData({
          sysShop: res.data.sysShop,
        })
    
      }
    })
  }
 
})