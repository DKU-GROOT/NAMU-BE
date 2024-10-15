package com.groot.namu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ExamEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    private String email;

    private String subjectName;
    
    @Column(columnDefinition = "TEXT")
    private String question1;

    @Column(columnDefinition = "TEXT")
    private String question2;

    @Column(columnDefinition = "TEXT")
    private String question3;
}
