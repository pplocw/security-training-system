package com.qclaw.securitytraining.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Entity
public class StudyRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Long userId;
    private Long courseId;
    private Integer progress;
    private Integer duration;
    @Column(updatable = false) private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @PrePersist protected void onCreate() { createTime = LocalDateTime.now(); updateTime = LocalDateTime.now(); }
    @PreUpdate protected void onUpdate() { updateTime = LocalDateTime.now(); }
}