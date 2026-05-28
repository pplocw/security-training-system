package com.qclaw.securitytraining.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Entity
public class ExamPaper {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String title;
    private String description;
    private Integer totalScore;
    private Integer passScore;
    private Integer duration;
    private Integer questionCount;
    private Integer status;
    private String creator;
    @Column(updatable = false) private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @PrePersist protected void onCreate() { createTime = LocalDateTime.now(); updateTime = LocalDateTime.now(); }
    @PreUpdate protected void onUpdate() { updateTime = LocalDateTime.now(); }
}