const app = getApp()
Page({
  data: { 
    course: null, 
    currentProgress: 0,
    watchDuration: 0
  },
  onLoad(options) {
    const courseList = wx.getStorageSync('courseList') || []
    const course = courseList.find(c => c.id == options.id) || {
      id: options.id,
      title: '课程' + options.id,
      description: '课程内容详情',
      videoUrl: 'https://www.w3schools.com/html/mov_bbb.mp4'
    }
    this.setData({ course: course })
  },
  onVideoTimeUpdate(e) {
    const ct = e.detail.currentTime
    const du = e.detail.duration
    const p = du > 0 ? Math.round((ct / du) * 100) : 0
    this.setData({ watchDuration: Math.floor(ct), currentProgress: p })
  },
  onVideoEnded() {
    wx.showToast({ title: '学习完成！', icon: 'success' })
  }
})