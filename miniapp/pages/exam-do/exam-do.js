const app = getApp()
Page({
  data: {
    paperId: null,
    paper: { title: "信息安全知识测试", totalScore: 100, passScore: 60 },
    questions: [
      {
        id: 1,
        type: "single",
        title: "以下哪种行为最可能导致钓鱼攻击？",
        options: [
          { key: "A", text: "打开陌生邮件中的附件" },
          { key: "B", text: "使用复杂密码" },
          { key: "C", text: "定期更新系统" },
          { key: "D", text: "使用HTTPS网站" }
        ],
        answer: "A"
      },
      {
        id: 2,
        type: "single",
        title: "强密码应该包含以下哪些元素？",
        options: [
          { key: "A", text: "仅包含字母" },
          { key: "B", text: "大小写字母+数字+特殊符号" },
          { key: "C", text: "仅包含生日日期" },
          { key: "D", text: "仅包含数字" }
        ],
        answer: "B"
      },
      {
        id: 3,
        type: "single",
        title: "使用公共WiFi时，以下哪种做法是正确的？",
        options: [
          { key: "A", text: "进行网上银行操作" },
          { key: "B", text: "登录公司VPN后再访问内部系统" },
          { key: "C", text: "随意下载安装软件" },
          { key: "D", text: "关闭防火墙以提高速度" }
        ],
        answer: "B"
      },
      {
        id: 4,
        type: "judge",
        title: "收到陌生邮件附件，应该直接打开。",
        options: [
          { key: "A", text: "正确" },
          { key: "B", text: "错误" }
        ],
        answer: "B"
      },
      {
        id: 5,
        type: "judge",
        title: "企业内部资料可以通过微信随意发送给外部人员。",
        options: [
          { key: "A", text: "正确" },
          { key: "B", text: "错误" }
        ],
        answer: "B"
      }
    ],
    answers: {},
    remaining: 1800,  // 30分钟
    timer: null,
    score: 0
  },

  onLoad(options) {
    this.setData({ paperId: options.paperId })
    this.startTimer()
  },

  startTimer() {
    const t = setInterval(() => {
      const r = this.data.remaining - 1
      if (r <= 0) {
        clearInterval(t)
        this.submitExam()
      } else {
        this.setData({ remaining: r })
      }
    }, 1000)
    this.setData({ timer: t })
  },

  selectOption(e) {
    const qindex = e.currentTarget.dataset.qindex
    const key = e.currentTarget.dataset.key
    const answers = this.data.answers
    answers[qindex] = key
    this.setData({ answers })
  },

  submitExam() {
    const { questions, answers } = this.data
    let score = 0
    questions.forEach((q, i) => {
      if (answers[i] === q.answer) score += 20
    })
    clearInterval(this.data.timer)
    wx.showModal({
      title: "考试结束",
      content: "你的得分：" + score + " / 100\n" + (score >= 60 ? "✅ 及格" : "❌ 不及格"),
      showCancel: false
    })
  },

  onUnload() {
    clearInterval(this.data.timer)
  }
})