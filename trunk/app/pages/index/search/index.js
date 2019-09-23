// pages/index/search/index.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    wxSearchData: {},
    hotKeys: [],
    tipKeys: [],
    inputVal:[],
    showNoGoods: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    // console.log(wx.getStorageSync("history"))
    if (wx.getStorageSync("history") != undefined) {
      this.setData({
        "history": wx.getStorageSync("history")
      })
    } else {
      this.setData({
        "history": []
      })
    }
  },
  inputTyping: function(e) {
    this.setData({
      inputVal: e.detail.value, 
      goodsList:[],
    });
  },
  searchClear: function() {
    this.setData({
      inputVal: "",
      showNoGoods:false
    });
  },
  wxSearchConfirm: function(e) {
    let key = e.target.dataset.key;
    if (key == "back") {
      wx.navigateBack({})
    } else {
      this.search(this.data.inputVal)
    }
  },
  search: function(val) {
    if (val.trim() == "") return;
    if (!this.data.history) {
      this.data.history = [];
    }
    this.data.history.unshift(val);
    let history_sto = [...new Set(this.data.history)]
    wx.setStorageSync("history", history_sto);
    wx.request({
      url: `${app.globalData.domain}/app/goods/list`,
      data: {
        keywords: val
      },
      success: res => {
        this.setData({
          goodsList: res.data.goodsList,
          showNoGoods:true
        })
      }
    })

    this.setData({
      "history": wx.getStorageSync("history")
    })
  },
  wxSearchDeleteAll: function() {
    wx.removeStorageSync('history');
    this.setData({
      "history": wx.getStorageSync("history")
    })
  },
  wxSearchKeyTap: function(e) {
    let searchVal = e.target.dataset.key;
    this.setData({
      inputVal: searchVal
    })
    this.search(searchVal);
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