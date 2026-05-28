package com.qclaw.securitytraining.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Entity
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String title;
    private String description;
    private String category;
    private Integer duration;
    private Integer status;
    private Integer sort;
    private String videoUrl;  // 视频URL
    @Column(updatable = false) private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @PrePersist protected void onCreate() { createTime = LocalDateTime.now(); updateTime = LocalDateTime.now(); }
    @PreUpdate protected void onUpdate() { updateTime = LocalDateTime.now(); }
}