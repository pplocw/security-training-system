package com.qclaw.securitytraining.controller;
import com.qclaw.securitytraining.dto.ApiResponse;
import com.qclaw.securitytraining.entity.Notice;
import com.qclaw.securitytraining.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;
    @GetMapping("/list")
    public ApiResponse<List<Notice>> list() { return ApiResponse.success(noticeService.getAllNotices()); }
    @GetMapping("/{id}")
    public ApiResponse<Notice> detail(@PathVariable Long id) { return ApiResponse.success(noticeService.getNoticeById(id)); }
    @PostMapping("/save")
    public ApiResponse<Notice> save(@RequestBody Notice notice) { return ApiResponse.success(noticeService.saveNotice(notice)); }
}