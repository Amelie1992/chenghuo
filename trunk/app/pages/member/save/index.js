const app = getApp();
Page({
  data: {
    items: [],
    startX: 0, //开始坐标
    startY: 0
  },
  onLoad: function(e) {
    this.getCollection();
  },

  getCollection: function() {
    wx.request({
      url: app.globalData.domain + '/app/goodscollection/list',
      data: {
        token: app.globalData.token
      },
      success: (res) => {
        if (res.data.code == 0) {
          let data = res.data.goodsList;
          this.setData({
            items: data
          });
        }
      }
    })
  },

  //删除收藏
  delCollection: function(goodsId){
    wx.request({
      url: app.globalData.domain + '/app/goodscollection/cancel',
      data: {
        token: app.globalData.token,
        goodsId: goodsId
      },
      success: (res) => {
        if (res.data.code == 0) {
          wx.showToast({
            title: '删除成功',
            icon: 'success',
            duration: 2000
          })
        } else {
          wx.showToast({
            title: '删除失败',
            icon: 'waiting',
            duration: 2000
          })
        }
      }
    })
  },

  toIndex: function() {
    wx.switchTab({
      url: "/pages/index/index",
    })
  },
  toDetail: function (e) {
    wx.navigateTo({
      url: "/pages/goods/goods-detail/index?id=" + e.currentTarget.dataset.id
    })
  },
  //手指触摸动作开始 记录起点X坐标
  touchstart: function(e) { //开始触摸时 重置所有删除
    this.data.items.forEach(function(v, i) {
      if (v.isTouchMove) //只操作为true的
        v.isTouchMove = false;
    })
    this.setData({
      startX: e.changedTouches[0].clientX,
      startY: e.changedTouches[0].clientY,
      items: this.data.items

    })
  }, //滑动事件处理
  touchmove: function(e) {
    var that = this,
      index = e.currentTarget.dataset.index, //当前索引
      startX = that.data.startX, //开始X坐标
      startY = that.data.startY, //开始Y坐标
      touchMoveX = e.changedTouches[0].clientX, //滑动变化坐标
      touchMoveY = e.changedTouches[0].clientY, //滑动变化坐标
      //获取滑动角度
      angle = that.angle({
        X: startX,
        Y: startY
      }, {
        X: touchMoveX,
        Y: touchMoveY
      });
    that.data.items.forEach(function(v, i) {
      v.isTouchMove = false
      //滑动超过30度角 return
      if (Math.abs(angle) > 30) return;
      if (i == index) {
        if (touchMoveX > startX) //右滑
          v.isTouchMove = false
        else //左滑
          v.isTouchMove = true
      }
    }) //更新数据
    that.setData({
      items: that.data.items
    })
  },
  /**
   * 计算滑动角度
   */
  angle: function(start, end) {
    var _X = end.X - start.X,
      _Y = end.Y - start.Y
    //返回角度 /Math.atan()返回数字的反正切值
    return 360 * Math.atan(_Y / _X) / (2 * Math.PI);
  }, //删除事件
  del: function(e) {
    let id = e.currentTarget.dataset.id;
    // console.log(id);

    this.delCollection(id);
    this.getCollection();
  }
})