package com.vietqr.org.repository;

import com.vietqr.org.entity.AccountBankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountBankRepository extends JpaRepository<AccountBankEntity, String> {
    Optional<AccountBankEntity> findByBankShortName(String bankShortName);
}