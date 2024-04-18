package com.vietqr.org.repotrans;

import com.vietqr.org.dto.CustomerInformationDTO;
import com.vietqr.org.entitytrans.CustomerSyncEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerSyncRepository extends JpaRepository<CustomerSyncEntity, String> {
    @Query(value = "SELECT id AS id, information AS information, "
            + "merchant AS name, "
            + "CASE "
            + "WHEN user_id IS NOT NULL AND user_id <> '' THEN 'Ecommerce' "
            + "ELSE 'API service' "
            + "END AS platform "
            + "FROM customer_sync ", nativeQuery = true)
    List<CustomerInformationDTO> findAllCustomerSync();
}
