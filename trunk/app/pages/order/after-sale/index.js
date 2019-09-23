// var wxpay = require('../../utils/pay.js')
var app = getApp()
Page({
  data: {
    status: ["已收到货", "未收到货"],
    reason: ["拍错/不喜欢/效果不好", "材质与商品描叙不符", "大小尺寸与商品描叙不符", "卖家发错货", "假冒品牌", "收到商品少件/破损或污渍", "颜色/款式/描叙不符"],
    statusIndex: 0,
    reasonIndex: 0,
    files: [],
    orderInfo: {},
    remakes: "",
    upLoadImg: []
  },
  orderDetail: function(e) {
    var orderId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: "/pages/order/order-detail/index?id=" + orderId
    })
  },

  //货物状态
  bindStatusChange: function(e) {
    this.setData({
      statusIndex: e.detail.value
    })
  },
  //退货原因
  bindReasonChange: function(e) {
    this.setData({
      reasonIndex: e.detail.value
    })
  },
  //选择图片
  chooseImage: function(e) {
    var that = this;
    wx.chooseImage({
      count: 3,
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function(res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        if (that.data.files.length + res.tempFilePaths.length <= 3) {
          that.setData({
            files: that.data.files.concat(res.tempFilePaths)
          });
          that.data.upLoadImg = [];
          that.uploadimg({
            url: app.globalData.domain + '/app/fileupload/upload',
            path: that.data.files
          })
        } else {
          wx.showModal({
            content: '最多只能选择三张图片',
            showCancel: false,
            success: function(res) {
              if (res.confirm) {
                // console.log('用户点击确定')
              }
            }
          });
        }

      }
    })
  },
  //上传图片
  uploadimg: function(data) {
    var that = this,
      i = data.i ? data.i : 0,
      success = data.success ? data.success : 0,
      fail = data.fail ? data.fail : 0;
    wx.uploadFile({
      url: data.url,
      filePath: data.path[i],
      name: 'file',
      success: function(res) {
        success++;
        let data = JSON.parse(res.data);
        that.data.upLoadImg.push(data.url)
      },
      fail: function(res) {
        fail++;
      },
      complete: function(res) {
        i++;
        if (i == data.path.length) { //当图片传完时，停止调用     
        } else {
          data.i = i;
          data.success = success;
          data.fail = fail;
          that.uploadimg(data);
        }

      }
    })
  },

  // 删除图片
  deleteImg: function(e) {
    var imgs = this.data.files;
    var upLoadImg = this.data.upLoadImg;
    var index = e.currentTarget.dataset.index;
    imgs.splice(index, 1);
    upLoadImg.splice(index, 1);

    this.setData({
      files: imgs
    });
  },
  //预览图片
  previewImage: function(e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.files // 需要预览的图片http链接列表
    })
  },
  onLoad: function(options) {
    // 生命周期函数--监听页面加载
    this.setData({
      orderId: options.orderId,
      orderNumber: options.orderNumber,
      type: options.type
    })
    this.getOrder(options.orderId)
  },
  onReady: function() {
    // 生命周期函数--监听页面初次渲染完成

  },

  onShow: function() {

  },

  //订单详情
  getOrder: function(id) {
    wx.showLoading()
    wx.request({
      url: `${app.globalData.domain}/app/order/detail`,
      data: {
        token: app.globalData.token,
        id: id
      },
      success: res => {
        if (res.data.code == 0) {
          this.setData({
            order:res.data.order,
            orderInfo: res.data.order.orderGoodsList,
            paymentAmount: res.data.order.paymentAmount
          })
        }
      },
      complete: res => {
        wx.hideLoading();
      }
    })
  },
  inputPayment: function(e) {
    this.setData({
      paymentAmount: e.detail.value
    })
  },
  inputgetRemakes: function(e) {
    this.setData({
      remakes: e.detail.value
    })
  },
  //提交申请
  submit: function() {
    if (this.data.upLoadImg.length == 0) {
      wx.showModal({
        title: '错误',
        content: '请上传图片',
        showCancel: false
      })
      return;
    }
    if (this.data.paymentAmount > this.data.order.productAmount){
      wx.showToast({
        icon:"none",
        title: '退款金额不能大于订单金额',
      })
      return;
    }
    wx.showLoading({
      title: '请稍候',
    })
    var orderRefund = {
      orderNumber: this.data.orderNumber,
      refundAmount: this.data.paymentAmount, //退款金额
      picUrls: this.data.upLoadImg,
      explain: this.data.remakes,
      goodsStatus: this.data.statusIndex, //货物状态 默认已收到货
      reason: this.data.reasonIndex, //退货原因
      type: this.data.type
    }
    // console.log(orderRefund)
    wx.request({
      url: `${app.globalData.domain}/app/orderrefund/apply`,
      header: {
        token: app.globalData.token
      },
      method: "POST",
      data: orderRefund,
      success: res => {
        wx.hideLoading();
        if (res.data.code == 0) {
          wx.showModal({
            title: "成功",
            content: "申请成功，返回订单列表",
            showCancel: false,
            success: res => {
              wx.navigateTo({
                url: '/pages/order/order-list/index',
              })
            }
          })
        }
      }
    })
  },

  onHide: function() {
    // 生命周期函数--监听页面隐藏

  },
  onUnload: function() {
    // 生命周期函数--监听页面卸载

  },
  onPullDownRefresh: function() {
    // 页面相关事件处理函数--监听用户下拉动作

  },
  onReachBottom: function() {
    // 页面上拉触底事件的处理函数

  }
})