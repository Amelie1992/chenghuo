//index.js
//获取应用实例
var app = getApp()
var WxPay = require('../../utils/pay.js')
var util = require('../../utils/util.js');
Page({
  data: {
    totalScoreToPay: 0,
    goodsList: [],
    isNeedLogistics: 0, // 是否需要物流信息
    allGoodsPrice: 0,
    goodsPrice: 0,
    allGoodsNumber: 0,
    yunPrice: 0, //运费
    allGoodsAndYunPrice: 0, //总价格包含运费
    goodsJsonStr: "",
    orderType: "", //订单类型，购物车下单或立即支付下单，默认是购物车，
    changeSwitch: false, //是否使用积分
    scoreToPay: 0, //使用积分数量
    purposeMoney: 0 ,//抵用金额
    date:''
  },
  onShow: function() {
    //获取默认地址
    this.getDefaultAddress();
    //发票
    this.getInvoiceType();
    //计算价格
    this.calculatePrice();

    this.getAvailableIntegral();
  },

  onLoad: function(e) {
    // console.log(e);
    var that = this;
    that.setData({
      isNeedLogistics: 1,
      orderType: e.orderType
    });
  },
  getTime: function () {
    var timeOne = util.formatTime(new Date());
    this.setData({
      date: timeOne
    });
  },
  //获取默认地址
  getDefaultAddress: function() {
    var that = this;
    wx.request({
      url: `${app.globalData.domain}/app/member/address/getDefault`,
      data: {
        token: app.globalData.token
      },
      success: res => {
        this.setData({
          memberAddress: res.data.memberAddress,
          city: res.data.memberAddress.city
        })
        // this.data.goodsList[0].yunDetail.forEach(ele=>{
        //   if (ele.address.indexOf(res.data.memberAddress.city) != -1){
        //       this.setData({
        //         yunPrice:ele.price
        //       })
        //   }
        // })
      }
    })
  },

  //获取使用积分
  getAvailableIntegral: function () {
    var that = this;
    wx.request({
      url: `${app.globalData.domain}/app/member/getAvailableIntegral`,
      data: {
        token: app.globalData.token,
        paymentAmount: that.data.allGoodsPrice
      },
      success: res => {
        if(res.data.code == 0){
          // console.log(res.data)
          that.setData({
            scoreToPay: res.data.integral,
            purposeMoney: res.data.purposeMoney
          })
        }
      }
    })
  },

  //创建订单
  createOrder: function(e) {
    // wx.showLoading();
    var that = this;
    var remark = ""; // 备注信息
    if (e) {
      remark = e.detail.value.remark; // 备注信息
    }

    if (that.data.isNeedLogistics > 0) {
      if (!that.data.memberAddress) {
        wx.hideLoading();
        wx.showModal({
          title: '错误',
          content: '请先设置您的收货地址！',
          showCancel: false
        })
        return;
      }
       if (!that.data.invoiceInfo) {
         wx.hideLoading();
         wx.showModal({
           title: '错误',
           content: '请先设置您的发票信息！',
           showCancel: false
         })
         return;
       }
    }

    //订单地址信息;
    var addressInfo = {};
    addressInfo.customerName = this.data.memberAddress.userName;
    addressInfo.telephone = this.data.memberAddress.telNumber;
    addressInfo.province = this.data.memberAddress.province;
    addressInfo.city = this.data.memberAddress.city;
    addressInfo.county = this.data.memberAddress.county;
    addressInfo.street = this.data.memberAddress.street;

    //订单商品信息
    var orderGoodsList = [];
    this.data.goodsList.forEach(ele => {
      var item = {};
      item.goodsId = ele.goodsId;
      item.goodsName = ele.name;
      item.picUrl = ele.pic;
      item.buyNum = ele.number;
      // item.price = ele.payPrice; 
      item.price = ele.specsprice;
      orderGoodsList.push(item)
    })
    //提交订单 去除购物车数据
    wx.getStorage({
      key: 'shopCarInfo',
      success: function(res) {
        let list = res.data.shopList;
        let shopCarInfo = {};
        shopCarInfo.shopList = arr(list, orderGoodsList);
        wx.setStorageSync("shopCarInfo", shopCarInfo);
      }
    })
    //去除购物车重复数据
    function arr(array, array2) {
      var arr3 = [];
      for (let key in array) {
        var stra = array[key].goodsId; 
        var count = 0;
        for (var j = 0; j < array2.length; j++) {
          var strb = array2[j].goodsId;
          if (stra == strb) {
            count++;
          }
        }
        if (count === 0) { //表示数组1的这个值没有重复的，放到arr3列表中  
          arr3.push(array[key]);
        }
      }
      return arr3;
    }
    //订单发票信息
    var orderInvoice = {};
    if (this.data.invoiceInfo){
      if (this.data.invoiceInfo.type == 0) { //企业
        orderInvoice.invoiceType = 0;
        orderInvoice.invoiceTitle = this.data.invoiceInfo.title;
        orderInvoice.taxNumber = this.data.invoiceInfo.taxNumber;
      } else { //个人
        orderInvoice.invoiceType = 1;
        orderInvoice.invoiceTitle = this.data.invoiceInfo.title;
      }
    }else{
      orderInvoice.invoiceType = "";
      orderInvoice.invoiceTitle = "";
    }
 
    //订单数据
    var order = {
      orderAddress: addressInfo,
      payType: "wechat",
      paymentAmount: this.data.allGoodsPrice + this.data.yunPrice, //支付金额
      ramarks: remark,
      useIntegral: this.data.scoreToPay,
      orderGoodsList: orderGoodsList,
      logisticsAmount: this.data.yunPrice,//运费
      productAmount: this.data.goodsPrice, //商品价格
      orderInvoice: orderInvoice //发票类型:1个人，0公司
    }
    
    wx.request({
      header: {
        "token": app.globalData.token
      },
      url: app.globalData.domain + '/app/order/create',
      data: order,
      method: 'POST',
      success: function(res) {
        // console.log(res);

        WxPay.wxpay(app, res.data.paymentAmount, res.data.orderNumber, function() {

        });
      }
    })
    this.getTime()
    console.log(this.data.date)
    
  },
  //如果没有默认地址，跳转到添加地址或者选择地址
  addAddress: function() {
    if (this.data.memberAddress) {
      wx.navigateTo({
        url: "/pages/member/address/address-list/index"
      })
    } else {
      wx.navigateTo({
        url: "/pages/member/address/address-add/index"
      })
    }
  },
  selectAddress: function() {
    wx.navigateTo({
      url: "/pages/member/address/address-list/index"
    })
  },
  //是否使用积分
  changeSwitch: function() {
    this.data.changeSwitch = !this.data.changeSwitch;
    this.calculatePrice();
  },
  //计算价格
  calculatePrice: function() {
    var that = this;
    var shopList = [];
    var purposeMoney = that.data.purposeMoney;
    var changeSwitch = that.data.changeSwitch;
    //立即购买下单
    if ("buyNow" == that.data.orderType) {
      var buyNowInfoMem = wx.getStorageSync('buyNowInfo');
      if (buyNowInfoMem && buyNowInfoMem.shopList) {
        shopList = buyNowInfoMem.shopList
      }
      var allpri = 0;
      var allNum = 0;
      for (var i = 0; i < shopList.length; i++) {
        if (shopList[i].specsprice==undefined){
          allpri += shopList[i].payPrice * shopList[i].number;
        }else{
          allpri += shopList[i].specsprice * shopList[i].number;
        }
      
        allNum += shopList[i].number;
      }
    } else {
      //购物车下单
      var shopCarInfoMem = wx.getStorageSync('shopCarInfo');
      if (shopCarInfoMem && shopCarInfoMem.shopList) {
        shopList = shopCarInfoMem.shopList.filter(entity => {
          return entity.active;
        });
      }
      var allpri = 0;
      var allNum = 0;
      for (var i = 0; i < shopList.length; i++) {
        if (shopList[i].specsprice == undefined) {
           allpri += shopList[i].payPrice * shopList[i].number;
        }else{
          allpri += shopList[i].specsprice * shopList[i].number;
        }
        allNum += shopList[i].number;
      }
    }

    if (changeSwitch) {
      that.setData({
        goodsList: shopList,
        allGoodsPrice: (allpri - purposeMoney),
        goodsPrice: allpri,
        allGoodsNumber: allNum
      });
    } else {
      that.setData({
        goodsList: shopList,
        allGoodsPrice: allpri,
        goodsPrice: allpri,
        allGoodsNumber: allNum
      });
    }
  },
  //发票类型信息
  getInvoiceType: function() {
    wx.getStorage({
      key: 'invoiceInfo',
      success: (res) => {
        this.setData({
          invoiceInfo: res.data
        })
      },
    })
    wx.getStorage({
      key: 'invoiceType',
      complete: (res) => {
        if (res.data && res.data == 1) {
          this.setData({
            invoiceType: 0
          })
        } else {
          this.setData({
            invoiceType: 1
          })
        }
      },
    })
  }
})