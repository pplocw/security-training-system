const app = getApp()
Page({
  data: { userInfo: null },
  onShow() {
    const userInfo = wx.getStorageSync('userInfo')
    this.setData({ userInfo: userInfo || { nickname: '未登录', username: '' } })
  },
  onLogin() {
    if (!this.data.userInfo || !this.data.userInfo.username) {
      wx.showModal({
        title: '登录',
        content: '请选择登录账号：\n\n· admin（密码：admin123）管理员\n· user01（密码：pass123）普通员工',
        confirmText: 'admin',
        cancelText: 'user01',
        success: res => {
          if (res.confirm) {
            this.doLogin('admin', '系统管理员', 'admin')
          } else if (res.cancel) {
            this.doLogin('user01', '普通员工', 'user')
          }
        }
      })
    }
  },
  doLogin(username, nickname, role) {
    wx.setStorageSync('userInfo', { username, nickname, role })
    wx.setStorageSync('token', 'demo_token_' + username)
    this.setData({ userInfo: { username, nickname, role } })
    wx.showToast({ title: '登录成功', icon: 'success' })
  },
  onMenuItemTap(e) {
    const idx = parseInt(e.currentTarget.dataset.idx)
    const userInfo = wx.getStorageSync('userInfo')
    if (!userInfo || !userInfo.username) {
      wx.showToast({ title: '请先登录', icon: 'none' })
      return
    }
    if (idx === 0) wx.navigateTo({ url: '/pages/study-data/study-data' })
    else if (idx === 3) wx.navigateTo({ url: '/pages/settings/settings' })
    else {
      const menus = ['学习数据', '证书中心', '我的收藏', '设置']
      wx.showToast({ title: menus[idx] + '（演示）', icon: 'none' })
    }
  }
})