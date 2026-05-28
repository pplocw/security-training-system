package com.qclaw.securitytraining.service;
import com.qclaw.securitytraining.entity.StudyRecord;
import com.qclaw.securitytraining.mapper.StudyRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StudyService {
    private final StudyRecordRepository studyRecordRepo;
    public StudyRecord updateProgress(Long userId, Long courseId, Integer progress, Integer duration) {
        StudyRecord r = studyRecordRepo.findAll().stream()
            .filter(x -> userId.equals(x.getUserId()) && courseId.equals(x.getCourseId()))
            .findFirst().orElseGet(() -> { StudyRecord x = new StudyRecord(); x.setUserId(userId); x.setCourseId(courseId); return x; });
        r.setProgress(progress); r.setDuration(duration);
        return studyRecordRepo.save(r);
    }
    public List<StudyRecord> getStudyRecordsByUserId(Long userId) {
        return studyRecordRepo.findAll().stream().filter(r -> userId.equals(r.getUserId())).toList();
    }
}