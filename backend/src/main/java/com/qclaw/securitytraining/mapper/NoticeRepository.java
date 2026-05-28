package com.qclaw.securitytraining.mapper;
import com.qclaw.securitytraining.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NoticeRepository extends JpaRepository<Notice, Long> {}