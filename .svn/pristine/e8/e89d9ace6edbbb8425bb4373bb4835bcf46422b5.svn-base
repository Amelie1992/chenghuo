// pages/apply/index.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    info: "",
    noteNowLen: 0,
    date: "请选择日期",
    countries: ["无", "有"],
    countryIndex: 0,
    qualitys: [],
    qualityIndex: 0,
    brands: [],
    brandIndex: 0,
    allNumber: 0,
    images: {
      files: "", //正面图
      side: "", //侧面图
      right: "", //右侧图
      clasp: "", //表扣
      back: "", //背面
      parts: "", //配件
    },
    files: [],
    paddingBottom: false,
    spaceNum: 350,
    confirmBar: false,
  },

  chooseImage: function(e, index) {
    var that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function(res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        let index = Number(e.currentTarget.dataset.index);
        switch (index) {
          case 1:
            that.setData({
              files: that.data.images.files = res.tempFilePaths
            });
            that.uploadFile(that.data.images.files[0], index);
            break;
          case 2:
            that.setData({
              side: that.data.images.side = res.tempFilePaths
            })
            that.uploadFile(that.data.images.side[0], index);
            break;
          case 3:
            that.setData({
              right: that.data.images.right = res.tempFilePaths
            })
            that.uploadFile(that.data.images.right[0], index);
            break;
          case 4:
            that.setData({
              clasp: that.data.images.clasp = res.tempFilePaths
            })
            that.uploadFile(that.data.images.clasp[0], index);
            break;
          case 5:
            that.setData({
              back: that.data.images.back = res.tempFilePaths
            })
            that.uploadFile(that.data.images.back[0], index);
            break;
          case 6:
            that.setData({
              parts: that.data.images.parts = res.tempFilePaths
            })
            that.uploadFile(that.data.images.parts[0], index);
            break;
        }
        let image_list = that.data.images;
        let allNumber = 0;
        for (let key in image_list) {
          if (image_list[key].length != "") {
            allNumber++
          }
        }
        that.setData({
          allNumber: allNumber
        })
      }
    })
  },

  //参数：filePath:res.tempFilePaths[0] ,num:图片序号
  uploadFile: function(filePath, num) {
    let that = this;
    wx.uploadFile({
      url: app.globalData.domain + '/app/fileupload/upload',
      filePath: filePath,
      name: 'file',
      success: function(res) {
        let data = JSON.parse(res.data);
        if (data.code == 0) {
          wx.showToast({
            title: '上传成功',
            icon: 'success',
            duration: 2000
          })
          let index = num - 1;
          that.data.files.length = 6;
          that.data.files[index] = data.url;
        }
      },
      fail: function(res) {}
    })
  },
  previewImage: function(e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.files // 需要预览的图片http链接列表
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getBrand();
    this.getCondition();
  },
  //选择日期
  bindDateChange: function(e) {
    this.setData({
      date: e.detail.value
    })
  },
  //选择是否维修保养
  bindCountryChange: function(e) {
    // console.log('picker country 发生选择改变，携带值为', e.detail.value);

    this.setData({
      countryIndex: e.detail.value,
    })


  },
  //选择品牌
  bindbrandChange: function(e) {
    this.setData({
      brandIndex: e.detail.value
    })
    this.data.brandList.forEach((ele, index) => {
      if (index == e.detail.value) {
        this.setData({
          selectBrandId: ele.id
        })
      }
    })
  },
  //选择手表成色
  bindqualityChange: function(e) {
    // console.log('picker quality 发生选择改变，携带值为', e.detail.value);

    this.setData({
      qualityIndex: e.detail.value,
    })
    this.data.conditionList.forEach((ele, index) => {
      if (index == e.detail.value) {
        this.setData({
          selectCondition: ele.id
        })
      }
    })
  },
  //底部输入框
  bindTextAreaChange: function(e) {
    var that = this;
    var value = e.detail.value,
      len = parseInt(value.length);
    if (len > that.data.noteMaxLen) return;
    that.setData({
      info: value,
      noteNowLen: len
    })
  },
  //获取品牌列表
  getBrand: function() {
    wx.request({
      url: `${app.globalData.domain}/app/brand/list`,
      data: {
        token: app.globalData.token
      },
      success: res => {
        var brands = [];
        var brandList = res.data.brandList;
        for (var i = 0; i < brandList.length; i++) {
          brands.push(brandList[i].brandName)
        }
        this.setData({
          brandList: res.data.brandList,
          brands: brands,
          selectBrandId: res.data.brandList[0].id
        })
      }
    })
  },

  getCondition: function() {
    wx.request({
      url: `${app.globalData.domain}/app/condition/list`,
      data: {
        token: app.globalData.token
      },
      success: res => {
        var qualitys = [];
        var conditionList = res.data.conditionList;
        for (var i = 0; i < conditionList.length; i++) {
          qualitys.push(conditionList[i].conditionName)
        }
        this.setData({
          conditionList: res.data.conditionList,
          qualitys: qualitys,
          selectCondition: res.data.conditionList[0].id
        })
      }
    })
  },

  //提交评估
  apply: function() {
    var that = this;
    var recovery = {
      brandId: this.data.selectBrandId,
      conditionId: this.data.selectCondition,
      buyTime: this.data.date,
      maintain: this.data.countryIndex,
      picUrls: this.data.files,
      functionStatus: this.data.info
    };
    if (this.data.date == "请选择日期") {
      wx.showModal({
        content: '请选择日期',
        showCancel: false,
        success: function(res) {
          if (res.confirm) {
            // console.log('用户点击确定')
          }
        }
      });
      return;
    }
    if (this.data.allNumber == 0) {
      wx.showModal({
        content: '请上传图片详情',
        showCancel: false,
        success: function(res) {
          if (res.confirm) {
            // console.log('用户点击确定')
          }
        }
      });
      return;
    }
    if (that.data.images.files == "") {
      wx.showModal({
        content: '请上传正面图',
        showCancel: false,
      });
      return;
    }
    if (that.data.images.side == "") {
      wx.showModal({
        content: '请上传侧面图',
        showCancel: false,
      });
      return;
    }
    if (that.data.images.right == "") {
      wx.showModal({
        content: '请上传右侧图',
        showCancel: false,
      });
      return;
    }
    if (that.data.images.clasp == "") {
      wx.showModal({
        content: '请上传表扣图',
        showCancel: false,
      });
      return;
    }
    if (that.data.images.back == "") {
      wx.showModal({
        content: '请上传背面图',
        showCancel: false,
      });
      return;
    }

    wx.showLoading({
      title: '请稍候',
      mask: true,
    })
    wx.request({
      url: `${app.globalData.domain}/app/recovery/add`,
      method: "POST",
      header: {
        token: app.globalData.token
      },
      data: recovery,
      success: res => {
        if (res.data.code == 0) {
          wx.hideLoading();
          wx.showModal({
            content: '申请成功，返回上一页',
            showCancel: false,
            success: function(res) {
              if (res.confirm) {
                wx.navigateBack()
              }
            }
          });
        }
      },
      fail: err => {

      }
    })

  },
  //文本框获取焦点
  textFocus: function(e) {
    this.setData({
      paddingBottom: true,
      space: 200
    })
  },
  textBlur: function(e) {
    this.setData({
      paddingBottom: false
    })
  },
  textLine: function(e) {
    this.setData({
      space: 200
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