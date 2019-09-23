// pages/goods/goods-detail/index.js
const app = getApp();
var WxParse = require('../../../wxParse/wxParse.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 规格集合
    specList:{},
    isBanck:0,
    pageBackgroundColor:'#21c5b4',
    isRound:0,
    goodsInfo: {
      salesNum: 2323,
      serviceLabel: ["免运费"],
      serviceCon: "16:45前支付，预计1_3个工作日内发货，支持货到付款",
      servicePromise: ["正规发票", "假一赔三", "七天包退", "终生售后"]
    },
    toggle: true,
    shopType: "addShopCar", //购物类型，加入购物车或立即购买，默认为加入购物车
    hideShopPopup: true,
    buyNumber: 1, //购买数量
    buyNumMin: 1, //最小购买数量
    buyNumMax: 0, //最大购买数量
    shopCarInfo: {},//购物车?
    shopNum: "",//实际购买量？
    countPrice:1,//输入的数量
    panCount:""//判断输入还是加减数量
  },

  // panel 展开
  panelToggle() {
    this.setData({
      toggle: !this.data.toggle
    })
  },
  //点击添加购物车按钮
  toAddShopCar: function() {
    this.setData({
      shopType: "addShopCar"
    })
    this.bindGuiGeTap();
  },
  //点击立即购买按钮
  tobuy: function() {
    this.setData({
      shopType: "tobuy"
    });
    this.bindGuiGeTap();
  },
  // 加入购物车

  addShopCar: function() {
    wx.showLoading({
      mask: true
    })
    wx.hideLoading();
    //没有明确的价格
    if (this.data.specList.length > 0) {
      if (this.data.isBanck == 0) {
        wx.showModal({
          title: '友情提醒',
          content: '请选择规格',
          showCancel: false
        })
        return;
        
      }
    }
    //数量如果小于1
    if (this.data.buyNumber < 1) {
      wx.hideLoading();
      wx.showModal({
        title: '提示',
        content: '购买数量不能为0！',
        showCancel: false
      })
      return;
    }
    //数量如果大于库存
    let goodsInfo = this.data.goods;
    let shopList = this.data.shopCarInfo.shopList;
    let flag = true;
   

    if (shopList && shopList.length > 0) {
      shopList.forEach(ele => {
        if (ele.goodsId == goodsInfo.id) {
          if (Number(ele.number) + Number(this.data.buyNumber) > Number(goodsInfo.stock)) {
            wx.hideLoading();
            wx.showModal({
              content: '库存不足',
              showCancel: false,
              success: function(res) {
                if (res.confirm) {
                  // console.log('用户点击确定')
                }
              }
            });
            flag = false;
          }
        }
      })
    }
    if (!flag) return;

    
    if (this.data.countPrice > this.data.goods.stock) {
      wx.showModal({
        title: '提示',
        content: '超出库存数量了',
        showCancel: false
      })
      wx.hideLoading();
      return;
    }else{
     

      //组建购物车
      var shopCarInfo = this.bulidShopCarInfo();
      this.setData({
        shopCarInfo: shopCarInfo,
        shopNum: shopCarInfo.shopNum
      });


      // 写入本地存储
      wx.setStorage({
        key: 'shopCarInfo',
        data: shopCarInfo
      })
      this.closePopupTap();
      let a = (this.data.goods.stock) - (this.data.shopCarInfo.shopNum);
      wx.showModal({
        title: '提示',
        content: '库存剩余' + a,
        showCancel: false
      })
      wx.hideLoading();
      wx.showToast({
        title: '加入购物车成功',
        icon: 'success',
        duration: 2000
      })
    }
    
   
  },
  bulidShopCarInfo: function() {
    var shopCarMap = {};
    shopCarMap.price = this.data.goods.price;
    shopCarMap.payPrice = this.data.goods.payPrice;

   shopCarMap.number = this.data.countPrice;
    
    shopCarMap.goodsId = this.data.goods.id;
    shopCarMap.name = this.data.goods.goodsName;
    shopCarMap.pic = this.data.goods.picUrl;
    shopCarMap.buyNumMax = this.data.goods.stock;
    if (this.data.goodsSpecEntity != undefined) {
      // 规格价格
      shopCarMap.specsprice = this.data.goodsSpecEntity.specPrice;
      //规格名称
      shopCarMap.specName = this.data.goodsSpecEntity.specName;
    }
 
    shopCarMap.active = true;
  //  shopCarMap.yunDetail = this.data.goods.freightTemplate.freightList;
    var shopCarInfo = this.data.shopCarInfo;
    if (!shopCarInfo.shopNum) {
      shopCarInfo.shopNum = 0;
    }
    if (!shopCarInfo.shopList) {
      shopCarInfo.shopList = [];
    }
    var hasSameGoodsIndex = -1;
    for (var i = 0; i < shopCarInfo.shopList.length; i++) {
      var tmpShopCarMap = shopCarInfo.shopList[i];
      //不同规格价格不重叠在一起
      if (tmpShopCarMap.goodsId == shopCarMap.goodsId && tmpShopCarMap.specsprice==shopCarMap.specsprice) {
        hasSameGoodsIndex = i;
        shopCarMap.number = shopCarMap.number + tmpShopCarMap.number;
        break;
      }
    }
    shopCarInfo.shopNum = shopCarInfo.shopNum + this.data.buyNumber;
    if (hasSameGoodsIndex > -1) {
      shopCarInfo.shopList.splice(hasSameGoodsIndex, 1, shopCarMap);
    } else {
      shopCarInfo.shopList.push(shopCarMap);
    }
    shopCarInfo.kjId = this.data.kjId;
    return shopCarInfo;
  },
  //显示选择弹出框

  bindGuiGeTap: function() {
    this.setData({
      hideShopPopup: false
    })
  },
  //隐藏选择弹出框

  closePopupTap: function() {
    this.setData({
      hideShopPopup: true
    })
  },
  //数量加减
  numJianTap: function() {
    this.data.panCount=1;
    if (this.data.countPrice > this.data.buyNumMin) {
      var currentNum = this.data.countPrice;
      currentNum--;
      this.setData({
        countPrice: currentNum
      })
    }
  },
  numJiaTap: function() {
    this.data.panCount = 1;
    if (this.data.countPrice < this.data.buyNumMax) {
      var currentNum = this.data.countPrice;
      currentNum++;
      this.setData({
        countPrice: currentNum
      })
    }
  },
  //购买数量
  formName: function (e) {
    this.setData({
      countPrice: e.detail.value
    })
  },
  //立即购买
  buyNow: function() {
   
    if (!app.globalData.token) {
      wx.navigateTo({
        url: "/pages/auth/index"
      })
      return;
    }
    if (this.data.buyNumber < 1) {
      wx.showModal({
        title: '提示',
        content: '购买数量不能为0！',
        showCancel: false
      })
      return;
    }

    
    // 数量库存
    if (this.data.countPrice > this.data.goods.stock) {
      wx.showModal({
        title: '友情提醒',
        content: '库存不够了',
        showCancel: false
      })
      return;
    }
    //没有明确的价格
    if (this.data.specList.length>0){
      if (this.data.isBanck == 0) {
        wx.showModal({
          title: '友情提醒',
          content: '请选择规格',
          showCancel: false
        })
        return;

      }
    }
     
 

    //组建立即购买信息
    var buyNowInfo = this.buliduBuyNowInfo();
    // 写入本地存储
    wx.setStorage({
      key: "buyNowInfo",
      data: buyNowInfo
    })
  
    this.closePopupTap();

    wx.navigateTo({
      url: "/pages/pay/index?orderType=buyNow"
    })
  },
  buliduBuyNowInfo: function() {
    var shopCarMap = {};

    shopCarMap.price = this.data.goods.price;
    shopCarMap.payPrice = this.data.goods.payPrice;
   
    shopCarMap.number = this.data.countPrice;
    
    shopCarMap.goodsId = this.data.goods.id;
    shopCarMap.name = this.data.goods.goodsName;
    shopCarMap.pic = this.data.goods.picUrl;
    shopCarMap.buyNumMax = this.data.goods.stock;
    // 规格价格
    if (this.data.goodsSpecEntity!=undefined){
      shopCarMap.specsprice = this.data.goodsSpecEntity.specPrice;
      //规格名称
      shopCarMap.specName = this.data.goodsSpecEntity.specName;
    }
    shopCarMap.active = true;
  //  shopCarMap.yunDetail = this.data.goods.freightTemplate.freightList;
    var buyNowInfo = {};
    if (!buyNowInfo.shopNum) {
      buyNowInfo.shopNum = 0;
    }
    if (!buyNowInfo.shopList) {
      buyNowInfo.shopList = [];
    }
    buyNowInfo.shopList.push(shopCarMap);
    return buyNowInfo;
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(e) {
    // 获取购物车数据
    wx.getStorage({
      key: 'shopCarInfo',
      success: (res) => {
        console.log(res)
        if (res.data.shopList && res.data.shopList.length > 0) {
          this.setData({
            shopCarInfo: res.data,
            shopNum: res.data.shopNum
          });
        }
      }
    })
    this.getGoodsDetail(e.id);
    if (app.globalData.token) {
      this.saveFootPrint(e.id);
    }


  },

  saveFootPrint: function(goodsId) {
    wx.request({
      url: `${app.globalData.domain}/app/goods/footprint`,
      data: {
        token: app.globalData.token,
        id: goodsId
      },
      success: res => {

      }
    })
  },
  // 点击规格
  showCollection: function (e) {
      // 设置边框颜色
      this.setData({
        isRound: e.target.dataset.id,
        isBanck:1 //选中的规格
      });

      // 获取规格中的价格
      wx.request({
        url: `${app.globalData.domain}/app/goods/getSpecPrice`,
      data: {
        id: e.target.dataset.id
      },
      success: res => {
        this.setData({
          goodsSpecEntity: res.data.goodsSpecEntity,

        })

      }
    })

    
   
  },
  //收藏
  saveCollection: function(e) {
    let goodsId = e.target.dataset.id;
    if (!this.data.goods.collection) {
      wx.request({
        url: `${app.globalData.domain}/app/goods/collection`,
        data: {
          token: app.globalData.token,
          id: goodsId
        },
        success: res => {
          // console.log(res)
          if (res.data.code == 0) {
            wx.showToast({
              title: '收藏成功',
              icon: 'success',
              duration: 2000
            })
            this.getGoodsDetail(goodsId);
          }
        }
      })
    } else {
      this.delCollection(goodsId);
    }
  },
  //取消收藏
  delCollection: function(goodsId) {
    wx.request({
      url: app.globalData.domain + '/app/goodscollection/cancel',
      data: {
        token: app.globalData.token,
        goodsId: goodsId
      },
      success: (res) => {
        if (res.data.code == 0) {
          wx.showToast({
            title: '取消收藏成功',
            icon: 'success',
            duration: 2000
          })
          this.getGoodsDetail(goodsId);
        }
      }
    })
  },
  
  getGoodsDetail: function(id) {
    var that = this;
    wx.request({
      url: `${app.globalData.domain}/app/goods/detail`,
      data: {
        id: id,
        token: app.globalData.token
      },
      success: res => {
        this.setData({
          goods: res.data.goods,
          buyNumMax: res.data.goods.stock
        })
        WxParse.wxParse('article', 'html', res.data.goods.description, that, 5);
      }
    })
// 规格获取
    wx.request({
      url: `${app.globalData.domain}/app/goods/getSpecs`,
      data: {
        id: id
      },
      success: res => {
        this.setData({
          specList: res.data.goodsSpecEntities,
    
        })
      
      }
    })

  },
  // 生成海报
  bingLongTap:function(e){
    let that = this
    let goodsId = JSON.stringify(that.data.goods.id)
    wx.showActionSheet({
      itemList: ['海报分享'],
      success: function (res) {
        wx.navigateTo({
          url: "/pages/goods/goods-poster/index?id=" + goodsId
        })
      },
      fail: function (res) {
        console.log(res.errMsg)
      }
    })
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