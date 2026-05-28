package com.qclaw.securitytraining.mapper;
import com.qclaw.securitytraining.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
public interface QuestionRepository extends JpaRepository<Question, Long> {}