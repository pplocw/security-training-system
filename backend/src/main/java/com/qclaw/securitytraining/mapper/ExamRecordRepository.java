package com.qclaw.securitytraining.mapper;
import com.qclaw.securitytraining.entity.ExamRecord;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ExamRecordRepository extends JpaRepository<ExamRecord, Long> {}