package com.vietqr.org.repository;

import com.vietqr.org.entity.UsMonthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsMonthRepository extends JpaRepository<UsMonthEntity, String> {
    UsMonthEntity findByMonth(String month);

}
