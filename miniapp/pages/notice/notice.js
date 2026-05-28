const app = getApp()
Page({
  data: { notices: [] },
  onLoad() {
    wx.request({
      url: app.globalData.baseUrl + '/api/notice/list',
      success: res => this.setData({ notices: res.data.data || [] })
    })
  }
})