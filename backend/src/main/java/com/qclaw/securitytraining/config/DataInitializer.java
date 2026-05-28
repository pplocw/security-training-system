package com.qclaw.securitytraining.config;
import com.qclaw.securitytraining.entity.*;
import com.qclaw.securitytraining.mapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepo;
    private final CourseRepository courseRepo;
    private final ExamPaperRepository paperRepo;
    private final QuestionRepository questionRepo;
    private final NoticeRepository noticeRepo;
    
    // 示例视频URL（可以使用实际的视频地址）
    private static final String[] VIDEO_URLS = {
        "https://www.w3schools.com/html/mov_bbb.mp4",
        "https://www.w3schools.com/html/movie.mp4",
        "https://www.w3schools.com/html/mov_bbb.mp4"
    };
    
    @Override
    public void run(String... args) {
        if (userRepo.count() == 0) initUsers();
        if (courseRepo.count() == 0) initCourses();
        if (paperRepo.count() == 0) initExams();
        if (noticeRepo.count() == 0) initNotices();
        log.info("测试数据初始化完成");
    }
    private void initUsers() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOJ7grF2C.Vyy");
        admin.setNickname("系统管理员");
        admin.setRole("admin");
        admin.setStatus(1);
        admin.setDepartment("信息安全部");
        admin.setCreateTime(LocalDateTime.now());
        userRepo.save(admin);
        
        User emp = new User();
        emp.setUsername("zhangsan");
        emp.setPassword("$2a$10$X5wGutsRl3gMGF0QvK8aK.3jhdKfJpX7kQYLrNKJ8qN4qKfU0PzJ7");
        emp.setNickname("张三");
        emp.setRole("employee");
        emp.setStatus(1);
        emp.setDepartment("研发部");
        emp.setCreateTime(LocalDateTime.now());
        userRepo.save(emp);
        log.info("初始化用户");
    }
    private void initCourses() {
        String[] titles = {"信息安全意识培训","钓鱼邮件识别与防范","等保2.0合规培训",
            "SQL注入漏洞原理与防御","密码安全与多因素认证","XSS跨站脚本攻击"};
        String[] descs = {"提高员工信息安全意识，了解常见网络攻击手段",
            "学习识别钓鱼邮件的特征",
            "网络安全等级保护2.0制度详解",
            "SQL注入攻击及防护方法",
            "创建强密码，理解多因素认证",
            "XSS攻击类型及防护"};
        String[] cats = {"mandatory","awareness","level3","attack","awareness","attack"};
        int[] durations = {60,45,90,60,30,45};
        
        for (int i = 0; i < titles.length; i++) {
            Course c = new Course();
            c.setTitle(titles[i]);
            c.setDescription(descs[i]);
            c.setCategory(cats[i]);
            c.setDuration(durations[i]);
            c.setStatus(1);
            c.setSort(i + 1);
            c.setVideoUrl(VIDEO_URLS[i % VIDEO_URLS.length]);
            c.setCreateTime(LocalDateTime.now());
            c.setUpdateTime(LocalDateTime.now());
            courseRepo.save(c);
        }
        log.info("初始化6门课程（带视频URL）");
    }
    private void initExams() {
        ExamPaper p1 = new ExamPaper();
        p1.setTitle("信息安全基础知识测试");
        p1.setDescription("测试员工信息安全基础知识");
        p1.setCreator("admin");
        p1.setTotalScore(100);
        p1.setPassScore(60);
        p1.setQuestionCount(5);
        p1.setDuration(30);
        p1.setStatus(1);
        p1.setCreateTime(LocalDateTime.now());
        p1.setUpdateTime(LocalDateTime.now());
        p1 = paperRepo.save(p1);
        
        questionRepo.save(createQuestion(p1.getId(), "single", "以下哪种密码最安全？", "123456", "password", "Qwerty@2024!", "用户名+生日", "C", 20));
        questionRepo.save(createQuestion(p1.getId(), "single", "收到疑似钓鱼邮件首先应该？", "点击链接查看", "回复邮件询问", "不轻信核实发件人", "转发给同事", "C", 20));
        questionRepo.save(createQuestion(p1.getId(), "multi", "钓鱼邮件常见特征？", "发件人地址可疑", "紧迫性语言", "链接指向陌生网站", "以上都是", "D", 20));
        questionRepo.save(createQuestion(p1.getId(), "single", "等保2.0简称？", "等保1.0", "等保2.0", "等保3.0", "等保Plus", "B", 20));
        questionRepo.save(createQuestion(p1.getId(), "judge", "定期更换密码可提高安全性", "正确", "错误", "", "", "A", 20));
        
        ExamPaper p2 = new ExamPaper();
        p2.setTitle("钓鱼邮件识别专项测试");
        p2.setDescription("测试钓鱼邮件识别能力");
        p2.setCreator("admin");
        p2.setTotalScore(100);
        p2.setPassScore(80);
        p2.setQuestionCount(4);
        p2.setDuration(20);
        p2.setStatus(1);
        p2.setCreateTime(LocalDateTime.now());
        p2.setUpdateTime(LocalDateTime.now());
        p2 = paperRepo.save(p2);
        
        questionRepo.save(createQuestion(p2.getId(), "single", "发件人显示客服但邮箱可疑是？", "官方邮件", "钓鱼邮件", "广告邮件", "正常邮件", "B", 25));
        questionRepo.save(createQuestion(p2.getId(), "single", "邮件要求立即更新密码正确做法？", "立即点击", "通过官网独立访问", "回复确认", "转发给他人", "B", 25));
        questionRepo.save(createQuestion(p2.getId(), "multi", "钓鱼邮件特征包括？", "伪造发件人", "紧急威胁语气", "可疑链接", "以上都是", "D", 25));
        questionRepo.save(createQuestion(p2.getId(), "single", "发现疑似钓鱼邮件正确处理？", "忽略", "报告安全部门", "保存邮件", "删除即可", "B", 25));
        log.info("初始化2套试卷9道题");
    }
    private Question createQuestion(Long paperId, String type, String content, String a, String b, String c, String d, String ans, int score) {
        Question q = new Question();
        q.setExamPaperId(paperId);
        q.setType(type);
        q.setContent(content);
        q.setOptionA(a);
        q.setOptionB(b);
        q.setOptionC(c);
        q.setOptionD(d);
        q.setAnswer(ans);
        q.setScore(score);
        return q;
    }
    private void initNotices() {
        Notice n1 = new Notice();
        n1.setTitle("2024年度信息安全培训通知");
        n1.setContent("根据公司信息安全管理制度要求，全体员工需在2024年12月31日前完成年度信息安全意识培训课程。本次培训涵盖钓鱼邮件识别、密码安全管理、数据保护规范等核心内容。请各部门负责人督促本部门员工按时完成培训并通过考核。未按时完成者将纳入部门年度安全考核扣分项。");
        n1.setType("training");
        n1.setStatus(1);
        n1.setCreateTime(LocalDateTime.now());
        n1.setUpdateTime(LocalDateTime.now());
        noticeRepo.save(n1);
        
        Notice n2 = new Notice();
        n2.setTitle("关于加强密码安全的通知");
        n2.setContent("近期安全审计发现部分员工使用弱密码（如123456、出生日期等），存在严重安全隐患。即日起，请全体员工按照以下要求更新密码：1.密码长度不少于12位；2.必须包含大小写字母、数字和特殊符号；3.不得使用个人信息（姓名、生日、手机号）作为密码；4.建议启用多因素认证（MFA）。请在两周内完成密码更新，过期账户将被暂时锁定。");
        n2.setType("security");
        n2.setStatus(1);
        n2.setCreateTime(LocalDateTime.now());
        n2.setUpdateTime(LocalDateTime.now());
        noticeRepo.save(n2);
        
        Notice n3 = new Notice();
        n3.setTitle("钓鱼邮件攻击预警");
        n3.setContent("近期信息安全部监测到大量针对公司员工的钓鱼邮件攻击，攻击者冒充IT部门发送虚假密码重置邮件，诱导员工点击恶意链接。请各位员工提高警惕：1.切勿点击来路不明的链接；2.收到密码重置等敏感邮件时，请通过官方网站或联系IT部门核实；3.如已点击可疑链接，请立即修改密码并报告信息安全部。发现可疑邮件请截图转发至 security@company.com。");
        n3.setType("warning");
        n3.setStatus(1);
        n3.setCreateTime(LocalDateTime.now());
        n3.setUpdateTime(LocalDateTime.now());
        noticeRepo.save(n3);
        log.info("初始化3条通知");
    }
}