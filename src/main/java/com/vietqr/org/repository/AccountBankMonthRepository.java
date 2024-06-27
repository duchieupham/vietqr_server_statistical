package com.vietqr.org.repository;

import com.vietqr.org.entity.AccountBankMonthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  AccountBankMonthRepository extends JpaRepository<AccountBankMonthEntity, String> {
}
