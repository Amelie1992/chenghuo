// pages/jifen/index.js
const app = getApp()
Page({
  data: {
    hideShopPopup: true,
    rule:{},
    integralRecordList: [],
    signIn:false,
    page:1,
    noData: false
  },
  // 下拉刷新
  onPullDownRefresh: function () {
    // 显示加载图标
    wx.showLoading({
      title: '正在加载中',
      duration:500
    })
    // 显示顶部刷新图标
    wx.showNavigationBarLoading();
    this.data.page = 1;
    this.data.integralRecordList = [];
    this.getIntegralRecord();
  },

  // 加载更多
  onReachBottom: function () {
    var that = this;
    // 显示加载图标
    wx.showLoading({
      title: '正在加载中',
      duration: 500
    })
    this.getIntegralRecord();
  },
  //显示选择弹出框

  bindGuiGeTap: function () {
    this.setData({
      hideShopPopup: false
    })
  },
  //隐藏选择弹出框

  closePopupTap: function () {
    this.setData({
      hideShopPopup: true
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getRule();
    this.getIntegral();
    this.getIntegralRecord();
    this.isSignIn();
    wx.getSystemInfo({
      success:  (res)=> {
        this.setData({
          scrollHeight: res.windowHeight
        });
      }
    });
  },

  isSignIn: function(){
    wx.request({
      url: `${app.globalData.domain}/app/member/isSignIn`,
      data: {
        token: app.globalData.token
      },
      success: res => {
        if(res.data.code == 0){
          this.setData({
            signInBtn:res.data.isSignIn
          })
        }
      }
    })
  },

  getRule: function(){
    wx.request({
      url: `${app.globalData.domain}/app/sys/config/getValByKey`,
      data: {
        key: "INTEGRAL_SETTING"
      },
      success: res => {
        var json = JSON.parse(res.data.value);
        this.setData({
          rule: json
        })
        // console.log(res)
      }
    })
  },

  getIntegral: function(){
    wx.request({
      url: `${app.globalData.domain}/app/member/getIntegral`,
      data: {
        token: app.globalData.token
      },
      success: res => {
        this.setData({
          integral: res.data.integral
        })
      }
    })
  },

  signIn: function(){
    wx.showLoading();
    if (!this.data.signInBtn){
      wx.request({
        url: `${app.globalData.domain}/app/member/signIn`,
        data: {
          token: app.globalData.token
        },
        success: res => {
          this.setData({
            signInBtn: true
          })
          wx.showToast({
            title: '签到成功',
            icon: 'success',
            duration: 500
          })
          this.getIntegral();
          this.data.page = 1;
          this.data.integralRecordList = [];
          this.getIntegralRecord();
        }
      })
    }else{
      wx.showToast({
        title: '不能重复签到',
        icon: 'none',
        duration: 1500
      })
    }
  },

  getIntegralRecord: function(){
    wx.request({
      url: `${app.globalData.domain}/app/member/integralRecord/list`,
      data: {
        token: app.globalData.token,
        page: this.data.page++,
        limit: 10
      },
      success: res => {
        wx.hideNavigationBarLoading();
        wx.stopPullDownRefresh();
        let newArray = res.data.integralRecordList;
        if(newArray.length<10){
          this.setData({
            noData: true
          })
        }
        this.setData({
          integralRecordList: this.data.integralRecordList.concat(newArray)
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
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})