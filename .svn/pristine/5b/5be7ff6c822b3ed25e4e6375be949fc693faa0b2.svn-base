// pages/goods/goods-poster/index.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods:{},
    cardInfo: {
      avater: "", //需要https图片路径
      qrCode: "http://i4.hexun.com/2018-07-05/193365388.jpg", //需要二维码图片路径
    
    }
  },


  /**
   * 先下载头像图片
   */
  getAvaterInfo: function () {
    wx.showLoading({
      title: '生成中...',
      mask: true,
    });
    var that = this;
    wx.downloadFile({
      url: that.data.goods.picUrl, //头像图片路径
      success: function (res) {
        wx.hideLoading();
        if (res.statusCode === 200) {
          var avaterSrc = res.tempFilePath; //下载成功返回结果
          that.getQrCode(avaterSrc); //继续下载二维码图片
        } else {
          wx.showToast({
            title: '头像下载失败！',
            icon: 'none',
            duration: 2000,
            success: function () {
              var avaterSrc = "";
              that.getQrCode(avaterSrc);
            }
          })
        }
      }
    })
  },

  /**
   * 下载二维码图片
   */
  getQrCode: function (avaterSrc) {
    wx.showLoading({
      title: '生成中...',
      mask: true,
    });
    var that = this;
    wx.downloadFile({
      url: that.data.cardInfo.qrCode, //二维码路径
      success: function (res) {
        wx.hideLoading();
        if (res.statusCode === 200) {
          var codeSrc = res.tempFilePath;
          that.sharePosteCanvas(avaterSrc, codeSrc);
        } else {
          wx.showToast({
            title: '二维码下载失败！',
            icon: 'none',
            duration: 2000,
            success: function () {
              var codeSrc = "";
              that.sharePosteCanvas(avaterSrc, codeSrc);
            }
          })
        }
      }
    })
  },
 


  /**
   * 开始用canvas绘制分享海报
   * @param avaterSrc 下载的头像图片路径
   * @param codeSrc   下载的二维码图片路径
   */
  sharePosteCanvas: function (avaterSrc, codeSrc) {
    wx.showLoading({
      title: '生成中...',
      mask: true,
    })
    var that = this;
    var cardInfo = that.data.goods; //需要绘制的数据集合
    const ctx = wx.createCanvasContext('myCanvas'); //创建画布
    var width = "";
    wx.createSelectorQuery().select('#canvas-container').boundingClientRect(function (rect) {
      var height = rect.height;
      var right = rect.right;
      width = rect.width * 0.8;
      var left = rect.left + 5;
      ctx.setFillStyle('#fff');
      ctx.fillRect(0, 0, rect.width, height);

      //头像为正方形
      if (avaterSrc) {
        ctx.drawImage(avaterSrc, left, 20, width, width);
        ctx.setFontSize(14);
        ctx.setFillStyle('#fff');
        ctx.setTextAlign('left');
      }

      //标签
      if (cardInfo.TagText) {
        ctx.fillText(cardInfo.TagText, left + 20, width - 4);
        const metrics = ctx.measureText(cardInfo.TagText); //测量文本信息
        ctx.stroke();
        ctx.rect(left + 10, width - 20, metrics.width + 20, 25);
        ctx.setFillStyle('rgba(255,255,255,0.4)');
        ctx.fill();
      }

      //商品名
      if (cardInfo.goodsName) {
        ctx.setFontSize(14);
        ctx.setFillStyle('#000');
        ctx.setTextAlign('left');
        ctx.fillText(cardInfo.goodsName, 40, width + 60);
      }

      //活动价
      if (cardInfo.payPrice) {
        ctx.setFontSize(20);
        ctx.setFillStyle('red');
        ctx.setTextAlign('left');
        let payPrice="￥"+cardInfo.payPrice;
        ctx.fillText(payPrice, left, width + 85);
      }

      //原价
      if (cardInfo.price) {
        ctx.setFontSize(12);
        ctx.setFillStyle('#666');
        ctx.setTextAlign('left');
        let cardInfoPrice = "原价:￥" + cardInfo.price
        ctx.fillText(cardInfoPrice, left, width + 105);
        let a=width+101
        ctx.moveTo(30, a)
        ctx.lineTo(100, a)
        ctx.setLineWidth(0.5)
        ctx.stroke()
        
      }

      // 公司名称
      if (cardInfo.price) {
        let company="三味鱼文化传媒"
        const CONTENT_ROW_LENGTH = 24; // 正文 单行显示字符长度
        let [contentLeng, contentArray, contentRows] = that.textByteLength(company, CONTENT_ROW_LENGTH);
        ctx.setTextAlign('left');
        ctx.setFillStyle('#000');
        ctx.setFontSize(10);
        let contentHh = 22 * 1;
        for (let m = 0; m < contentArray.length; m++) {
          ctx.fillText(contentArray[m], left, width + 150 + contentHh * m);
        }
      }

      //  绘制二维码
      if (codeSrc) {
        ctx.drawImage(codeSrc, left + 160, width + 40, width / 3, width / 3)
        ctx.setFontSize(10);
        ctx.setFillStyle('#000');
        ctx.fillText("微信扫码或长按识别", left + 160, width + 150);
      }

    }).exec()

    setTimeout(function () {
      ctx.draw();
      wx.hideLoading();
    }, 1000)

  },

  /**
   * 多行文字处理，每行显示数量
   * @param text 为传入的文本
   * @param num  为单行显示的字节长度
   */
  textByteLength(text, num) {
    let strLength = 0; // text byte length
    let rows = 1;
    let str = 0;
    let arr = [];
    for (let j = 0; j < text.length; j++) {
      if (text.charCodeAt(j) > 255) {
        strLength += 2;
        if (strLength > rows * num) {
          strLength++;
          arr.push(text.slice(str, j));
          str = j;
          rows++;
        }
      } else {
        strLength++;
        if (strLength > rows * num) {
          arr.push(text.slice(str, j));
          str = j;
          rows++;
        }
      }
    }
    arr.push(text.slice(str, text.length));
    return [strLength, arr, rows] //  [处理文字的总字节长度，每行显示内容的数组，行数]
  },

  //点击保存到相册
  saveShareImg: function (e) { 
    wx.showLoading({
      title: '正在保存',
      mask: true,
    })
    setTimeout(function () {
      
      wx.canvasToTempFilePath({
        canvasId: 'myCanvas',
        success: function (res) {
          wx.hideLoading();
          var tempFilePath = res.tempFilePath;
          wx.saveImageToPhotosAlbum({
            filePath: tempFilePath,
            success(res) {
              // utils.aiCardActionRecord(19);
              wx.showModal({
                content: '图片已保存到相册，赶紧晒一下吧~',
                showCancel: false,
                confirmText: '好的',
                confirmColor: '#333',
                success: function (res) {
                  if (res.confirm) { }
                },
                fail: function (res) { }
              })
            },
            fail: function (res) {
              wx.showToast({
                title: res.errMsg,
                icon: 'none',
                duration: 2000
              })
            }
          })
        }
      });
    }, 1000);
  },
  //长按保存
  bingLongTap: function (e) {
    let that=this
    wx.showActionSheet({
      itemList: ['保存到相册'],
      success: function (res) {
        that.saveShareImg()
      },
      fail: function (res) {
        console.log(res.errMsg)
      }
    })
   
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //获取相册授权
    wx.getSetting({
      success(res) {
        if (!res.authSetting['scope.writePhotosAlbum']) {
          wx.authorize({
            scope: 'scope.writePhotosAlbum',
            success() {
              console.log('授权成功')
            }
          })
        }
      }
    })
    wx.request({
      url: `${app.globalData.domain}/app/goods/detail`,
      data: {
        id: options.id,
        token: app.globalData.token
      },
      success: res => {
        this.setData({
          goods: res.data.goods,
        })
        console.log(this.data.goods)
        this.getAvaterInfo();
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

  }
})