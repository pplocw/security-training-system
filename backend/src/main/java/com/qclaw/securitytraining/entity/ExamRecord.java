package com.qclaw.securitytraining.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Entity
public class ExamRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Long userId;
    private Long examPaperId;
    private Integer score;
    private Integer status;
    private String answers;
    @Column(updatable = false) private LocalDateTime createTime;
    private LocalDateTime submitTime;
}