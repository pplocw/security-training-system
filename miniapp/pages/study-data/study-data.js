const app = getApp()
Page({
  data: {
    userInfo: null,
    stats: { totalHours: 0, courseCount: 0, examCount: 0, level: 'Lv1 入门' },
    courses: [],
    examRecords: [],
    badges: []
  },

  onLoad() {
    this.setData({ userInfo: wx.getStorageSync('userInfo') || {} })
    this.loadStudyData()
  },

  onShow() {
    this.setData({ userInfo: wx.getStorageSync('userInfo') || {} })
  },

  loadStudyData() {
    // 模拟数据（真实场景从 /api/study/progress 获取）
    this.setData({
      stats: {
        totalHours: 12,
        courseCount: 4,
        examCount: 2,
        level: 'Lv2 进阶'
      },
      courses: [
        { id: 1, title: '钓鱼邮件识别', duration: 45, category: '安全意识', progress: 100 },
        { id: 2, title: '密码安全管理', duration: 30, category: '安全意识', progress: 75 },
        { id: 3, title: '数据保护规范', duration: 60, category: '合规培训', progress: 50 },
        { id: 4, title: '社会工程学防范', duration: 40, category: '安全意识', progress: 25 }
      ],
      examRecords: [
        { id: 1, paperTitle: '信息安全知识测试', examTime: '2024-12-10 14:30', score: 85 },
        { id: 2, paperTitle: '钓鱼邮件识别专项考核', examTime: '2024-12-15 10:00', score: 72 },
        { id: 3, paperTitle: '密码安全阶段性测试', examTime: '2024-12-18 16:00', score: 58 }
      ],
      badges: [
        { id: 1, icon: '🏆', name: '初学者' },
        { id: 2, icon: '📚', name: '好学者' },
        { id: 3, icon: '⭐', name: '优秀学员' }
      ]
    })
  }
})