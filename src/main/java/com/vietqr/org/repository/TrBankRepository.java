package com.vietqr.org.repository;

import com.vietqr.org.entity.TrBankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrBankRepository extends JpaRepository<TrBankEntity, String> {
}
