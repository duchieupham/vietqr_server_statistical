package com.vietqr.org.repository;

import com.vietqr.org.entity.UserRegisterDayEntity;
import com.vietqr.org.entity.UserRegisterMonthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegisterDayRepository extends JpaRepository<UserRegisterDayEntity, String> {
}
