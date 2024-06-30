package com.vietqr.org.repository;

import com.vietqr.org.entity.AccountBankMonthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  AccountBankMonthRepository extends JpaRepository<AccountBankMonthEntity, String> {
    Optional<AccountBankMonthEntity> findByBankShortNameAndTime(String bankShortName, String time);
}
