package com.groot.namu.study.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="subject")
public class SubjectEntity {
    @Id
    private String subjectName;
    private int subjectLike;
}
