package com.qclaw.securitytraining.mapper;
import com.qclaw.securitytraining.entity.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface StudyRecordRepository extends JpaRepository<StudyRecord, Long> {
    Optional<StudyRecord> findByUserIdAndCourseId(Long userId, Long courseId);
}