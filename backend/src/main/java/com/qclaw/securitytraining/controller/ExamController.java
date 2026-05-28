package com.qclaw.securitytraining.controller;
import com.qclaw.securitytraining.dto.ApiResponse;
import com.qclaw.securitytraining.entity.*;
import com.qclaw.securitytraining.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;
    @GetMapping("/paper/list")
    public ApiResponse<List<ExamPaper>> paperList() { return ApiResponse.success(examService.getAllExamPapers()); }
    @GetMapping("/paper/{id}")
    public ApiResponse<ExamPaper> paperDetail(@PathVariable Long id) { return ApiResponse.success(examService.getExamPaperById(id)); }
    @GetMapping("/question/{paperId}")
    public ApiResponse<List<Question>> questions(@PathVariable Long paperId) { return ApiResponse.success(examService.getQuestionsByExamPaperId(paperId)); }
    @PostMapping("/submit")
    public ApiResponse<ExamRecord> submit(@RequestParam Long userId, @RequestParam Long paperId, @RequestBody Map<Long, String> answers) {
        return ApiResponse.success(examService.submitExam(userId, paperId, answers));
    }
    @GetMapping("/record/{userId}")
    public ApiResponse<List<ExamRecord>> records(@PathVariable Long userId) { return ApiResponse.success(examService.getExamRecordsByUserId(userId)); }
}