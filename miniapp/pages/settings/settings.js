const app = getApp()
Page({
  data: {
    userInfo: null,
    notifyEnabled: true,
    darkMode: false
  },

  onLoad() {
    const userInfo = wx.getStorageSync('userInfo')
    this.setData({
      userInfo: userInfo || { nickname: '未登录', username: '' },
      notifyEnabled: wx.getStorageSync('notifyEnabled') !== false,
      darkMode: wx.getStorageSync('darkMode') || false
    })
  },

  onShow() {
    this.setData({ userInfo: wx.getStorageSync('userInfo') || { nickname: '未登录', username: '' } })
  },

  // 切换账号
  switchAccount() {
    wx.showModal({
      title: '切换账号',
      content: '确定要切换账号吗？当前账号将退出登录。',
      confirmColor: '#1890ff',
      success: res => {
        if (res.confirm) {
          this.doLogout(false)
          wx.showModal({
            title: '登录',
            content: '请选择登录账号：\n\n测试账号：\n· admin（密码：admin123）\n· user01（密码：pass123）',
            confirmText: 'admin',
            cancelText: 'user01',
            success: r => {
              if (r.confirm) {
                this.loginAs('admin', '系统管理员', 'admin')
              } else if (r.cancel) {
                this.loginAs('user01', '普通员工', 'user')
              }
            }
          })
        }
      }
    })
  },

  // 退出登录
  logout() {
    wx.showModal({
      title: '退出登录',
      content: '确定退出当前账号？',
      confirmColor: '#ff4d4f',
      success: res => {
        if (res.confirm) this.doLogout(true)
      }
    })
  },

  doLogout(showMsg) {
    wx.clearStorageSync()
    wx.removeStorageSync('userInfo')
    wx.removeStorageSync('token')
    this.setData({ userInfo: { nickname: '未登录', username: '' } })
    if (showMsg !== false) wx.showToast({ title: '已退出登录', icon: 'success' })
    else this.setData({ userInfo: { nickname: '未登录', username: '' } })
  },

  loginAs(username, nickname, role) {
    wx.setStorageSync('userInfo', { username, nickname, role })
    wx.setStorageSync('token', 'demo_token_' + username)
    this.setData({ userInfo: { username, nickname, role } })
    wx.showToast({ title: '登录成功', icon: 'success' })
  },

  // 学习数据
  goStudyData() {
    const userInfo = wx.getStorageSync('userInfo')
    if (!userInfo || !userInfo.username) {
      wx.showToast({ title: '请先登录', icon: 'none' })
      return
    }
    wx.navigateTo({ url: '/pages/study-data/study-data' })
  },

  // 推送通知
  onNotifyChange(e) {
    const val = e.detail.value
    wx.setStorageSync('notifyEnabled', val)
    this.setData({ notifyEnabled: val })
    wx.showToast({ title: val ? '已开启' : '已关闭', icon: 'none' })
  },

  // 深色模式
  onDarkModeChange(e) {
    const val = e.detail.value
    wx.setStorageSync('darkMode', val)
    this.setData({ darkMode: val })
    wx.showToast({ title: val ? '已开启' : '已关闭', icon: 'none' })
  },

  // 检查更新
  checkUpdate() {
    wx.showModal({
      title: '检查更新',
      content: '当前已是最新版本 v1.0.0',
      showCancel: false
    })
  }
})