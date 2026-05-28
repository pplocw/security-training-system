/**
 * 工具函数库
 */

// 格式化时间
const formatTime = (date, format = 'YYYY-MM-DD HH:mm:ss') => {
  const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, '0')
  const day = String(date.getDate()).padStart(2, '0')
  const hour = String(date.getHours()).padStart(2, '0')
  const minute = String(date.getMinutes()).padStart(2, '0')
  const second = String(date.getSeconds()).padStart(2, '0')

  return format
    .replace('YYYY', year)
    .replace('MM', month)
    .replace('DD', day)
    .replace('HH', hour)
    .replace('mm', minute)
    .replace('ss', second)
}

// 防抖函数
const debounce = (fn, delay = 300) => {
  let timer = null
  return function(...args) {
    if (timer) clearTimeout(timer)
    timer = setTimeout(() => {
      fn.apply(this, args)
    }, delay)
  }
}

// 节流函数
const throttle = (fn, delay = 300) => {
  let lastTime = 0
  return function(...args) {
    const now = Date.now()
    if (now - lastTime >= delay) {
      lastTime = now
      fn.apply(this, args)
    }
  }
}

// 请求封装
const request = (options) => {
  const app = getApp()
  const token = wx.getStorageSync('token')
  
  return new Promise((resolve, reject) => {
    wx.request({
      url: app.globalData.baseUrl + options.url,
      method: options.method || 'GET',
      data: options.data || {},
      header: {
        'Content-Type': 'application/json',
        'Authorization': token ? `Bearer ${token}` : '',
        ...options.header
      },
      success: (res) => {
        if (res.statusCode === 200) {
          resolve(res.data)
        } else if (res.statusCode === 401) {
          // token过期，跳转登录
          wx.removeStorageSync('token')
          wx.reLaunch({ url: '/pages/index/index' })
          reject(new Error('未授权'))
        } else {
          reject(new Error(res.data.message || '请求失败'))
        }
      },
      fail: (err) => {
        reject(err)
      }
    })
  })
}

// xAPI 学习行为记录
const recordLearningActivity = (verb, object, result = {}) => {
  const statement = {
    verb: verb, // 'completed', 'progressed', 'answered'
    object: object, // 课程ID、题目ID等
    result: result, // 分数、进度等
    timestamp: new Date().toISOString(),
    actor: wx.getStorageSync('userId') || 'unknown'
  }
  
  // 发送到LRS (Learning Record Store)
  return request({
    url: '/api/xapi/statements',
    method: 'POST',
    data: statement
  })
}

// 检查登录状态
const checkLogin = () => {
  const token = wx.getStorageSync('token')
  return !!token
}

// 获取用户信息
const getUserInfo = () => {
  return wx.getStorageSync('userInfo') || null
}

module.exports = {
  formatTime,
  debounce,
  throttle,
  request,
  recordLearningActivity,
  checkLogin,
  getUserInfo
}
