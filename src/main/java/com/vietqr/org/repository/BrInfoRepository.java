package com.vietqr.org.repository;

import com.vietqr.org.entity.BrInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrInfoRepository extends JpaRepository<BrInfoEntity, String> {
}
