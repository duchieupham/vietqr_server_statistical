package com.vietqr.org.repository;

import com.vietqr.org.entity.UserRegisterMonthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterMonthRepository extends JpaRepository<UserRegisterMonthEntity, String> {
}
