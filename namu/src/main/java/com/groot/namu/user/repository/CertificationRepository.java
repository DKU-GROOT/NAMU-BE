package com.groot.namu.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groot.namu.user.entity.CertificationEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, String>{
    CertificationEntity findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
