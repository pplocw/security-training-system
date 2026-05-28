package com.qclaw.securitytraining.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Entity
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Long examPaperId;
    private String content;
    private String type;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private Integer score;
}