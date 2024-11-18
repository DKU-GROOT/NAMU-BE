package com.groot.namu.discussion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groot.namu.discussion.entity.DiscussionEntity;

public interface DiscussionRepository extends JpaRepository<DiscussionEntity, Long>{
    DiscussionEntity findByDiscussionId(Long discussionId);
    DiscussionEntity findByEmail(String email);
}
