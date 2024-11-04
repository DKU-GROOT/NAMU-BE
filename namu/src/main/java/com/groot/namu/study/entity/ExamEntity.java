package com.groot.namu.study.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="exam")
public class ExamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exam_id;

    private String email;

    private String subjectName;

    @Column(columnDefinition = "LONGTEXT")
    private String quiz1;
    @Column(columnDefinition = "LONGTEXT")
    private String solution1;
    @Column(columnDefinition = "LONGTEXT")
    private String quiz2;
    @Column(columnDefinition = "LONGTEXT")
    private String solution2;
    @Column(columnDefinition = "LONGTEXT")
    private String quiz3;
    @Column(columnDefinition = "LONGTEXT")
    private String solution3;
    @Column(columnDefinition = "LONGTEXT")
    private String quiz4;
    @Column(columnDefinition = "LONGTEXT")
    private String solution4;
    @Column(columnDefinition = "LONGTEXT")
    private String quiz5;
    @Column(columnDefinition = "LONGTEXT")
    private String solution5;
}
