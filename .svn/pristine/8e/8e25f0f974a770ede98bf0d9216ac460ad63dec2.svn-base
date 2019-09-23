// pages/category/index.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    filterNav: [{
      name: "全部分类",
      hasIcon: true,
      iconName: "icon-arrowdown",
      bindEvent: "filterList",
      filterRuleArrow: true, //当前筛选箭头 默认降序
      bindItemEvent: "checkFilterItem"
    }, {
      name: "默认排序",
      hasIcon: true,
      iconName: "icon-arrowdown",
      bindEvent: "filterNew",
      filterRuleArrow: true, //当前筛选箭头 默认降序
      filterItem: ["默认排序", "价格升序", "价格降序"],
      bindItemEvent: "checkFilterItem"
    }, {
      name: "筛选",
      hasIcon: true,
      iconName: "icon-guolv",
      bindEvent: "filterShop",
      filterRuleArrow: true, //当前筛选箭头 默认降序
    }],
    params: {},
    categoryList: [],
    isBrand: 0
  },
  filterList: function (e) {
    if (this.data.isActive) {
      this.setData({
        isActive: 0
      })

    } else {
      this.setData({
        isActive: 1
      })
    }

  },
  checkFilterItem: function (e) {
    var data = e.target.dataset;
    var arr = Object.keys(data);
    if (arr.length == 0) {
      this.setData({
        "filterNav[0].name": "全部分类"
      })
      delete this.data.params.categoryId;
      this.getGoodsList();
    } else {
      if (this.data.isActive == 1) {
        this.setData({
          "filterNav[0].name": e.target.dataset.item,
          "filterNav[0].id": e.target.dataset.id,
        })
      }
      if (this.data.isActive == 2) {
        this.setData({
          "filterNav[1].name": e.target.dataset.item,
          "filterNav[1].id": e.target.dataset.id,
        })
      }
      this.data.params.categoryId = e.target.dataset.id;
      this.getGoodsList();
    }

  },
  checkFilternew: function (e) {
    let index = e.target.dataset.index;
    if (index == 1) {
      this.data.params.sidx = "price";
      this.data.params.order = "asc";
      this.getGoodsList();
    } else if (index == 2) {
      this.data.params.sidx = "price";
      this.data.params.order = "desc";
      this.getGoodsList();
    }else{
      delete this.data.params.sidx;
      delete this.data.params.order;
      this.getGoodsList();
    }
    this.setData({
      "filterNav[1].name": e.target.dataset.item,
    })
  },

  filterShop: function () {
    if (this.data.isActive) {
      this.setData({
        isActive: 0
      })
    } else {
      this.setData({
        isActive: 3
      })
    }
  },
  filterNew: function () {
    if (this.data.isActive) {
      this.setData({
        isActive: 0
      })

    } else {
      this.setData({
        isActive: 2
      })

    }
  },
  checkFilter: function () {
    this.setData({
      isActive: 3
    })
  },
  //分类单选
  typeChange: function (e) {
    // console.log(e.detail.value)
    var items = this.data.categoryList;
    var checkArr = e.detail.value;
    for (var i = 0; i < items.length; i++) {
      if (checkArr.indexOf(items[i].id + "") != -1) {
        items[i].checked = true;
      } else {
        items[i].checked = false;
      }
    }
    this.setData({
      categoryList: items,
      categoryCheckArr: checkArr
    })
    console.log(this.data.categoryCheckArr)
  },
  resetRadio1: function (e) {
    // console.log(e)
    let isCheck = e.target.dataset.checked;
    let index = e.target.dataset.index;
    if (isCheck) {
      this.data.categoryList[index].checked = false;
      this.setData({
        categoryList: this.data.categoryList,
        categoryCheckArr: ""
      })
    }
  },
  //标签筛选
  checkChange: function (e) {
    var that = this
    that.setData({
      value: e.detail.value
    })
    var items = this.data.tagList;
    var checkArr = e.detail.value;
    for (var i = 0; i < items.length; i++) {
      if (checkArr.indexOf(items[i].id + "") != -1) {
        items[i].checked = true;
      } else {
        items[i].checked = false;
      }
    }
    this.setData({
      tagList: items,
      checkArr: checkArr
    })
  },
  //重置筛选
  resetForm: function (e) {
    let newTagArr = [];
    let newCategoryArr = [];
    this.data.tagList.forEach(ele => {
      ele.checked = false;
      newTagArr.push(ele);
    })
    this.data.categoryList.forEach(ele => {
      ele.checked = false;
      newCategoryArr.push(ele);
    })

    this.setData({
      tagList: newTagArr,
      categoryList: newCategoryArr,
      checkArr: [],
      categoryCheckArr: [],
    })
  },
  //确认筛选
  screen: function () {
    // console.log(this.data.brandCheckArr)

    if (this.data.categoryCheckArr && this.data.categoryCheckArr.length > 0) {
      this.data.params.categoryId = this.data.categoryCheckArr;
    } else {
      delete this.data.params.categoryId;
    }
    if (this.data.checkArr && this.data.checkArr.length > 0) {
      this.data.params.tags = this.data.checkArr.join(",");
    } else {
      delete this.data.params.tags;
    }
    this.getGoodsList();
    this.setData({
      isActive: 0
    })
  },
  closeFilter: function () {
    this.setData({
      isActive: 0
    })
  },

  getCategory: function () {
    wx.request({
      url: `${app.globalData.domain}/app/category/list`,
      data: {
      },
      success: res => {
        var categoryList = res.data.categoryList;
        categoryList.unshift({ id: "0", categoryName: "全部", checked: true })
        this.setData({
          categoryList: res.data.categoryList
        })
       
       
      }
      
    })
  },

  getBrand: function () {
    wx.request({
      url: `${app.globalData.domain}/app/brand/list`,
      data: {

      },
      success: res => {
        this.setData({
          brandList: res.data.brandList
        })
      }
    })
  },
  checkBrand: function (e) {
    this.setData({
      isBrand: e.target.dataset.id
    })
    this.data.params.categoryId = e.target.dataset.id;
    this.getGoodsList();
    
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getBanner();
    this.getBrand();
    this.getCategory();
    this.getTag();
    this.getGoodsList();
  },
  getBanner: function () {
    wx.request({
      url: `${app.globalData.domain}/app/advert/list`,
      data: {
        positionId: 2
      },
      success: res => {
        this.setData({
          bannerList: res.data.adertList
        })
      }
    })
  },
  getBrand: function () {
    wx.request({
      url: `${app.globalData.domain}/app/brand/list`,
      data: {

      },
      success: res => {
        this.setData({
          brandList: res.data.brandList
        })
      }
    })
  },

  getTag: function () {
    wx.request({
      url: `${app.globalData.domain}/app/tag/list`,
      data: {

      },
      success: res => {
        this.setData({
          tagList: res.data.tagList
        })
      }
    })
  },
  getGoodsList: function () {
    var that = this;
    wx.request({
      url: `${app.globalData.domain}/app/goods/list`,
      data: that.data.params,
      success: res => {
        this.setData({
          goodsList: res.data.goodsList
        })
      }
    })
  },
  //最新
  getNewGoods: function () {
    if (this.data.isNew) {
      this.setData({
        isNew: false
      })
    } else {
      this.setData({
        isNew: true,
        isHot: false
      })
      this.data.params.sidx = "create_time";
      this.data.params.order = "desc";
      this.getGoodsList();
    }

  },
  //热销
  getHotGoods: function () {
    if (this.data.isHot) {
      this.setData({
        isHot: false
      })
    } else {
      this.setData({
        isHot: true,
        isNew: false
      })
      this.data.params.sidx = "sales_num";
      this.data.params.order = "desc";
      this.getGoodsList();
    }
  },
  toSearch: function () {
    wx.navigateTo({
      url: "/pages/index/search/index"
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