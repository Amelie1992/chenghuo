//index.js
var app = getApp()
Page({
  data: {
    goodsList: {
      saveHidden: true,
      totalPrice: 0,
      totalScoreToPay: 0,
      allSelect: true,
      noSelect: false,
      list: [],
      shopNum: 0,
    },
    goodsListTwo: [],
    delBtnWidth: 120,
    buyNum: ''//商品数量
  },
  getEleWidth: function (w) {
    var real = 0;
    try {
      var res = wx.getSystemInfoSync().windowWidth;
      var scale = (750 / 2) / (w / 2);
      real = Math.floor(res / scale);
      return real;
    } catch (e) {
      return false;
      // Do something when catch error
    }
  },
  initEleWidth: function () {
    var delBtnWidth = this.getEleWidth(this.data.delBtnWidth);
    this.setData({
      delBtnWidth: delBtnWidth
    });
  },
  onLoad: function () {
    this.initEleWidth();
    this.getGoodsList()

  },
  //获取存在的商品数据
  getGoodsList: function () {
    var that = this;
    return new Promise(function (resolve, reject) {
      wx.request({
        url: `${app.globalData.domain}/app/goods/list`,
        data: that.data.params,
        success: res => {
          that.data.goodsListTwo = res.data.goodsList
          //存在的商品
          wx.setStorageSync("aa", res.data.goodsList)
          console.log(1)
          that.onShow();
        }
      })
    });

  },

  onShow: function () {
    var goodListTwo = wx.getStorageSync('aa')
    console.log(goodListTwo)
    var shopList = [];
    // 获取购物车数据
    var shopCarInfoMem = wx.getStorageSync('shopCarInfo');
    if (shopCarInfoMem && shopCarInfoMem.shopList) {
      shopList = shopCarInfoMem.shopList;
      console.log(shopCarInfoMem)
      for (var j = 0; j < shopList.length; j++) {
        var count = 0;
        for (var i = 0; i < goodListTwo.length; i++) {
          if (goodListTwo[i].id != shopList[j].goodsId) {
            count++;
          }
          if (count == goodListTwo.length) {
            shopList.splice(j, 1)
          }
        }
        this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), shopList, this.shopNum());
      }




    }



  },
  //返回首页
  toIndexPage: function () {
    wx.switchTab({
      url: "/pages/index/index"
    });
  },
  toGoodsDetail: function (e, item) {
    var index = e.currentTarget.dataset.index;
    wx.navigateTo({
      url: "/pages/goods/goods-detail/index?id=" + index.goodsId
    });
  },
  touchS: function (e) {
    if (e.touches.length == 1) {
      this.setData({
        startX: e.touches[0].clientX
      });
    }
  },
  touchM: function (e) {
    var index = e.currentTarget.dataset.index;

    if (e.touches.length == 1) {
      var moveX = e.touches[0].clientX;
      var disX = this.data.startX - moveX;
      var delBtnWidth = this.data.delBtnWidth;
      var left = "";
      if (disX == 0 || disX < 0) {
        left = "margin-left:0px";
      } else if (disX > 0) {
        left = "margin-left:-" + disX + "px";
        if (disX >= delBtnWidth) {
          left = "left:-" + delBtnWidth + "px";
        }
      }
      var list = this.data.goodsList.list;
      if (index != "" && index != null) {
        list[parseInt(index)].left = left;
        this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list, this.shopNum());
      }
    }
  },

  touchE: function (e) {
    var index = e.currentTarget.dataset.index;
    if (e.changedTouches.length == 1) {
      var endX = e.changedTouches[0].clientX;
      var disX = this.data.startX - endX;
      var delBtnWidth = this.data.delBtnWidth;
      var left = disX > delBtnWidth / 2 ? "margin-left:-" + delBtnWidth + "px" : "margin-left:0px";
      var list = this.data.goodsList.list;
      if (index !== "" && index != null) {
        list[parseInt(index)].left = left;
        this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list, this.shopNum());
      }
    }
  },
  delItem: function (e) {
    var index = e.currentTarget.dataset.index;
    var list = this.data.goodsList.list;
    list.splice(index, 1);
    this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list, this.shopNum());
  },
  selectTap: function (e) {
    var index = e.currentTarget.dataset.index;
    var list = this.data.goodsList.list;
    if (index !== "" && index != null) {
      list[parseInt(index)].active = !list[parseInt(index)].active;
      this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list, this.shopNum());
    }
  },
  shopNum: function () {
    var list = this.data.goodsList.list;
    // console.log(list)
    var num = 0;
    for (var i = 0; i < list.length; i++) {
      var curItem = list[i];
      if (curItem.active) {
        num += curItem.number;
      }
    }
    this.data.goodsList.shopNum = num;
    return num;
  },
  totalPrice: function () {
    var list = this.data.goodsList.list;
    var total = 0;
    let totalScoreToPay = 0;
    for (var i = 0; i < list.length; i++) {
      var curItem = list[i];
      if (curItem.active) {
        if (curItem.specsprice==undefined){
          total += parseFloat(curItem.payPrice) * curItem.number;
        }else{
          total += parseFloat(curItem.specsprice) * curItem.number;
        } 
       
        totalScoreToPay += curItem.score * curItem.number;
      }
    }
    this.data.goodsList.totalScoreToPay = totalScoreToPay;
    total = parseFloat(total.toFixed(2));
    return total;
  },
  allSelect: function () {
    var list = this.data.goodsList.list;
    var allSelect = false;
    for (var i = 0; i < list.length; i++) {
      var curItem = list[i];
      if (curItem.active) {
        allSelect = true;
      } else {
        allSelect = false;
        break;
      }
    }
    return allSelect;
  },
  noSelect: function () {
    var list = this.data.goodsList.list;
    var noSelect = 0;
    for (var i = 0; i < list.length; i++) {
      var curItem = list[i];
      if (!curItem.active) {
        noSelect++;
      }
    }
    if (noSelect == list.length) {
      return true;
    } else {
      return false;
    }
  },
  setGoodsList: function (saveHidden, total, allSelect, noSelect, list, num) {
    this.setData({
      goodsList: {
        saveHidden: saveHidden,
        totalPrice: total,
        allSelect: allSelect,
        noSelect: noSelect,
        list: list,
        totalScoreToPay: this.data.goodsList.totalScoreToPay,
        shopNum: num
      }
    });
    var shopCarInfo = {};
    var tempNumber = 0;
    shopCarInfo.shopList = list;
    for (var i = 0; i < list.length; i++) {
      tempNumber = tempNumber + list[i].number
    }
    shopCarInfo.shopNum = tempNumber;
    wx.setStorage({
      key: "shopCarInfo",
      data: shopCarInfo
    })
  },
  bindAllSelect: function () {
    var currentAllSelect = this.data.goodsList.allSelect;
    var list = this.data.goodsList.list;
    if (currentAllSelect) {
      for (var i = 0; i < list.length; i++) {
        var curItem = list[i];
        curItem.active = false;
      }
    } else {
      for (var i = 0; i < list.length; i++) {
        var curItem = list[i];
        curItem.active = true;
      }
    }

    this.setGoodsList(this.getSaveHide(), this.totalPrice(), !currentAllSelect, this.noSelect(), list, this.shopNum());
  },
  jiaBtnTap: function (e) {
    var that = this
    var index = e.currentTarget.dataset.index;
    var list = that.data.goodsList.list;
    if (index !== "" && index != null) {
      var carShopBean = list[parseInt(index)];
      list[parseInt(index)].number++;
      that.setGoodsList(that.getSaveHide(), that.totalPrice(), that.allSelect(), that.noSelect(), list, this.shopNum());
    }
  },
  jianBtnTap: function (e) {
    var index = e.currentTarget.dataset.index;
    var list = this.data.goodsList.list;
    if (index !== "" && index != null) {
      if (list[parseInt(index)].number > 1) {
        list[parseInt(index)].number--;
        this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list, this.shopNum());
      }
    }
  },
  editTap: function () {
    var list = this.data.goodsList.list;
    for (var i = 0; i < list.length; i++) {
      var curItem = list[i];
      curItem.active = false;
    }
    this.setGoodsList(!this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list, this.shopNum());
  },
  saveTap: function () {
    var list = this.data.goodsList.list;
    for (var i = 0; i < list.length; i++) {
      var curItem = list[i];
      curItem.active = true;
    }
    this.setGoodsList(!this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list, this.shopNum());
  },
  getSaveHide: function () {
    var saveHidden = this.data.goodsList.saveHidden;
    return saveHidden;
  },
  deleteSelected: function () {
    var list = this.data.goodsList.list;

    list = list.filter(function (curGoods) {
      return !curGoods.active;
    });
    this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list, this.shopNum());
  },

  toPayOrder: function () {
    if (!app.globalData.token) {
      wx.navigateTo({
        url: "/pages/auth/index"
      })
      return;
    }
    wx.showLoading();

    // this.shopNum()
    var that = this;
    if (this.data.goodsList.noSelect) {
      wx.hideLoading();
      return;
    }
    // 重新计算价格，判断库存
    var shopList = [];
    var shopCarInfoMem = wx.getStorageSync('shopCarInfo');
    if (shopCarInfoMem && shopCarInfoMem.shopList) {
      // shopList = shopCarInfoMem.shopList
      shopList = shopCarInfoMem.shopList.filter(entity => {
        return entity.active;
      });
    }
    if (shopList.length == 0) {
      wx.hideLoading();
      return;
    }
    var isFail = false;
    var doneNumber = 0;
    var needDoneNUmber = shopList.length;
    for (let i = 0; i < shopList.length; i++) {
      if (isFail) {
        wx.hideLoading();
        return;
      }
      let carShopBean = shopList[i];
      // 获取价格和库存

    }
    this.navigateToPayOrder();
  },
  navigateToPayOrder: function () {
    wx.hideLoading();
    wx.navigateTo({
      url: "/pages/pay/index?orderType=shopCart"
    })
  }
})
