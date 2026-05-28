const app = getApp()
Page({
  data: {
    userInfo: null,
    stats: { courseCount: 0, completeCount: 0, examCount: 0 },
    notices: [],
    recommendedCourses: []
  },
  onLoad() {
    this.loadData()
  },
  onShow() {
    const userInfo = wx.getStorageSync('userInfo')
    if (userInfo) this.setData({ userInfo })
  },
  loadData() {
    const req = (url, cb) => wx.request({ url: app.globalData.baseUrl + url, success: r => cb(r.data.data) })
    req('/api/course/list', courses => {
      this.setData({ recommendedCourses: courses.slice(0, 3), 'stats.courseCount': courses.length })
    })
    req('/api/notice/list', notices => {
      notices.forEach(n => {
        n.brief = n.content.length > 30 ? n.content.substring(0, 30) + '...' : n.content
        n.expanded = false
      })
      this.setData({ notices: notices.slice(0, 3) })
    })
    req('/api/exam/paper/list', papers => this.setData({ 'stats.examCount': papers.length }))
  },
  goCourse() { wx.switchTab({ url: '/pages/course/course' }) },
  goExam() { wx.switchTab({ url: '/pages/exam/exam' }) },
  goStudy(e) {
    const id = e.currentTarget.dataset.id
    wx.navigateTo({ url: '/pages/course-detail/course-detail?id=' + id })
  },
  toggleNotice(e) {
    const nIdx = e.currentTarget.dataset.nidx
    const key = 'notices[' + nIdx + '].expanded'
    this.setData({ [key]: !this.data.notices[nIdx].expanded })
  }
})