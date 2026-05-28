package com.qclaw.securitytraining.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Entity
public class Notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String title;
    private String content;
    private String type;
    private Integer status;
    @Column(updatable = false) private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @PrePersist protected void onCreate() { createTime = LocalDateTime.now(); updateTime = LocalDateTime.now(); }
    @PreUpdate protected void onUpdate() { updateTime = LocalDateTime.now(); }
}