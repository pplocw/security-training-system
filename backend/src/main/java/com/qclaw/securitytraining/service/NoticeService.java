package com.qclaw.securitytraining.service;
import com.qclaw.securitytraining.entity.Notice;
import com.qclaw.securitytraining.mapper.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepo;
    public List<Notice> getAllNotices() { return noticeRepo.findAll(); }
    public Notice getNoticeById(Long id) { return noticeRepo.findById(id).orElse(null); }
    public Notice saveNotice(Notice n) { return noticeRepo.save(n); }
}