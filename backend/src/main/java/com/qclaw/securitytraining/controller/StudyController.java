package com.qclaw.securitytraining.controller;
import com.qclaw.securitytraining.entity.StudyRecord;
import com.qclaw.securitytraining.mapper.StudyRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudyController {
    private final StudyRecordRepository repo;
    
    @PostMapping("/progress")
    public Map<String, Object> saveProgress(@RequestBody Map<String, Object> data) {
        Long userId = ((Number)data.getOrDefault("userId", 1)).longValue();
        Long courseId = ((Number)data.get("courseId")).longValue();
        Integer duration = ((Number)data.getOrDefault("duration", 0)).intValue();
        Integer progress = ((Number)data.getOrDefault("progress", 0)).intValue();
        
        StudyRecord record = repo.findByUserIdAndCourseId(userId, courseId).orElse(null);
        if (record == null) {
            record = new StudyRecord();
            record.setUserId(userId);
            record.setCourseId(courseId);
        }
        record.setDuration(duration);
        record.setProgress(progress);
        repo.save(record);
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "保存成功");
        return result;
    }
    
    @GetMapping("/progress")
    public Map<String, Object> getProgress(@RequestParam Long userId, @RequestParam Long courseId) {
        StudyRecord record = repo.findByUserIdAndCourseId(userId, courseId).orElse(null);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        if (record != null) {
            result.put("progress", record.getProgress());
            result.put("duration", record.getDuration());
        } else {
            result.put("progress", 0);
            result.put("duration", 0);
        }
        return result;
    }
}