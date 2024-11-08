package com.groot.namu.discussion.entity;

import java.time.LocalDate;

import com.groot.namu.discussion.dto.request.PostDiscussionRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "discussion")
@Table(name = "discussion")
public class DiscussionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discussionId;
    private String email;
    private String title;
    private String text;
    private String subjectName;
    private String postDate;
    private String modifiedDate;
    private boolean anonymous;

    public DiscussionEntity (PostDiscussionRequestDto dto) {
        this.email = dto.getEmail();
        this.title = dto.getTitle();
        this.text = dto.getText();
        this.subjectName = dto.getSubjectName();
        this.anonymous = dto.isAnonymous();
        this.postDate = LocalDate.now().toString();
        this.modifiedDate = LocalDate.now().toString();
    }
}
