const app = getApp()
Page({
  data: {
    courses: [],
    categories: [
      { key: '', name: '全部' },
      { key: 'mandatory', name: '必修课程' },
      { key: 'level3', name: '等保课程' },
      { key: 'attack', name: '攻防课程' },
      { key: 'awareness', name: '安全意识' }
    ],
    activeCategory: ''
  },
  onLoad() { this.loadCourses() },
  loadCourses() {
    const active = this.data.activeCategory
    const url = active ? '/api/course/list?category=' + active : '/api/course/list'
    wx.request({
      url: app.globalData.baseUrl + url,
      success: res => {
        const courses = res.data.data || []
        this.setData({ courses: courses })
        wx.setStorageSync('courseList', courses)
      }
    })
  },
  onCategoryTap(e) {
    const key = e.currentTarget.dataset.key
    this.setData({ activeCategory: key }, () => this.loadCourses())
  },
  goStudy(e) {
    const id = e.currentTarget.dataset.id
    wx.navigateTo({ url: '/pages/course-detail/course-detail?id=' + id })
  }
})