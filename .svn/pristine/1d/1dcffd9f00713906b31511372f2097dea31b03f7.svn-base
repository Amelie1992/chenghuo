// pages/makeapp/index.js
//引入验证
import WxValidate from '../../utils/WxValidate.js'
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    form: {
      name: '',
      mobile: '',
      remark: ''
    }
  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      name: {
        required: true,
      },
      mobile: {
        required: true,
        tel: true,
      },
      remark: {
        required: true,
      },
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      name: {
        required: '请输入姓名',
      },
      mobile: {
        required: '请输入手机号',
        tel: '请输入正确的手机号',
      },
      remark: {
        required: '请输入你所从事的行业',
      },
    }

    // 创建实例对象
    this.WxValidate = new WxValidate(rules, messages)
  },

  formSubmit: function (e) {
    console.log(params)
    const params = e.detail.value
    // 传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      this.showModal(error)
      return false
    }else {
      wx.request({
        url: `${app.globalData.domain}/app/wcontact/save`,
        method: "POST",
        data: { name: e.detail.value.name, tel: e.detail.value.mobile, remark: e.detail.value.remark },
        success: function (res) {
          if (res.code == 0) {
            wx.showToast({
              title: "加载中",
              icon: 'loading',
              duration: 1500
            })
          } else {
            wx.showToast({
              title: "提交成功！",//这里打印出登录成功
              icon: 'success',
              duration: 1000
            })
          }
        }
      })
    }
  },  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    this.initValidate()
    console.log(this.WxValidate)
  },
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
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