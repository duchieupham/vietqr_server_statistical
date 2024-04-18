package com.vietqr.org.repository;

import com.vietqr.org.entity.FeeOfAnnualEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeOfAnnualRepository extends JpaRepository<FeeOfAnnualEntity, String> {
}
