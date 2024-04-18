package com.vietqr.org.repository;

import com.vietqr.org.entity.AccountBankFeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBankFeeRepository extends JpaRepository<AccountBankFeeEntity, String> {
}
