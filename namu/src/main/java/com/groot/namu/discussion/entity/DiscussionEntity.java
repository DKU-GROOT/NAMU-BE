package com.groot.namu.discussion.entity;

import jakarta.persistence.Entity;
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
    private Long discussionId;
    private String email;
    private String title;
    private String text;
    private String subjectName;
    private String postDate;
    private String modifiedDate;
    private boolean anonymous;
}
