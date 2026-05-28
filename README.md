# 基于微信小程序的企业信息安全培训系统

English version below ↓

## 项目简介

这是一个基于微信小程序的企业信息安全培训系统，用于企业员工的信息安全培训、课程学习、在线考试和通知公告。

## 技术栈

### 后端
- **框架**: Spring Boot 3.5.0
- **数据库**: H2 (内存数据库)
- **认证**: JWT (JSON Web Token)
- **权限**: RBAC (基于角色的访问控制)

### 前端
- **平台**: 微信小程序
- **开发工具**: 微信开发者工具

## 项目结构

```
security-training-system/
├── backend/          # Spring Boot 后端
│   ├── src/
│   ├── pom.xml
│   └── .gitignore
├── miniapp/         # 微信小程序前端
│   ├── pages/
│   ├── components/
│   ├── app.js
│   └── app.json
├── 论文.docx         # 毕业论文
└── README.md
```

## 功能模块

- **课程管理**: 课程列表、课程详情、视频学习
- **考试管理**: 在线考试、自动评分
- **通知公告**: 通知列表、通知详情
- **个人中心**: 学习记录、学习统计

## 如何运行

### 后端

```bash
cd backend
./mvnw spring-boot:run
```

后端运行在 `http://localhost:8080`

### 小程序

1. 打开微信开发者工具
2. 导入 `miniapp/` 目录
3. 修改 `miniapp/utils/config.js` 中的 API 地址
4. 编译运行

## 作者

pplocw

---

# Enterprise Information Security Training System Based on WeChat Mini Program

## Introduction

This is an enterprise information security training system based on WeChat Mini Program, used for employee information security training, course learning, online exams, and notifications.

## Tech Stack

### Backend
- **Framework**: Spring Boot 3.5.0
- **Database**: H2 (in-memory database)
- **Authentication**: JWT (JSON Web Token)
- **Authorization**: RBAC (Role-Based Access Control)

### Frontend
- **Platform**: WeChat Mini Program
- **Development Tool**: WeChat DevTools

## Project Structure

```
security-training-system/
├── backend/          # Spring Boot backend
│   ├── src/
│   ├── pom.xml
│   └── .gitignore
├── miniapp/         # WeChat Mini Program frontend
│   ├── pages/
│   ├── components/
│   ├── app.js
│   └── app.json
├── 论文.docx         # Graduation thesis
└── README.md
```

## Features

- **Course Management**: Course list, course detail, video learning
- **Exam Management**: Online exam, auto-grading
- **Notifications**: Notification list, notification detail
- **Profile**: Learning records, learning statistics

## How to Run

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

Backend runs at `http://localhost:8080`

### Mini Program

1. Open WeChat DevTools
2. Import `miniapp/` directory
3. Modify API URL in `miniapp/utils/config.js`
4. Compile and run

## Author

pplocw
