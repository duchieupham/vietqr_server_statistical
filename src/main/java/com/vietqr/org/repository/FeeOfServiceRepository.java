package com.vietqr.org.repository;

import com.vietqr.org.dto.IFeeOfServiceDTO;
import com.vietqr.org.entity.FeeOfServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeOfServiceRepository extends JpaRepository<FeeOfServiceEntity, String> {
    @Query(value = "SELECT month AS month, fees AS fees, "
            + "sum AS sum "
            + "FROM fee_of_service "
            + "WHERE month = :time "
            + "LIMIT 1 ", nativeQuery = true)
    IFeeOfServiceDTO getFeeOfServiceByMonth(String time);
}
