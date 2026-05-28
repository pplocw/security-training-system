const app = getApp()
Page({
  data: { course: {}, progress: 0 },
  onLoad(options) {
    const id = options.id || 1
    wx.request({
      url: app.globalData.baseUrl + '/api/course/' + id,
      success: res => this.setData({ course: res.data.data || {} })
    })
  },
  complete() {
    wx.showToast({ title: '学习完成！', icon: 'success' })
    setTimeout(() => wx.navigateBack(), 1500)
  }
})