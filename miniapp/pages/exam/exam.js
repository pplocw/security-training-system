const app = getApp()
Page({
  data: {
    papers: [],
    examStats: { total: 0, completed: 0, avgScore: 0 }
  },
  onLoad() { this.loadData() },
  loadData() {
    wx.request({
      url: app.globalData.baseUrl + '/api/exam/paper/list',
      success: res => this.setData({ papers: res.data.data || [], 'examStats.total': (res.data.data || []).length })
    })
  },
  startExam(e) {
    const id = e.currentTarget.dataset.id
    wx.navigateTo({ url: '/pages/exam-do/exam-do?paperId=' + id })
  }
})