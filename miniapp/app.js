App({
  onLaunch() {
    const token = wx.getStorageSync('token')
    if (!token) {
      console.log('用户未登录')
    }
    const systemInfo = wx.getSystemInfoSync()
    this.globalData.systemInfo = systemInfo
  },

  globalData: {
    userInfo: null,
    token: null,
    systemInfo: null,
    // 后端API地址（本地开发用，答辩演示时用本机IP）
    baseUrl: 'http://localhost:8080'
  }
})
