package com.vietqr.org.repository;

import com.vietqr.org.dto.ICustomerBankFeeDTO;
import com.vietqr.org.entity.AccountCustomerBankEntity;
import com.vietqr.org.dto.BrInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountCustomerBankRepository extends JpaRepository<AccountCustomerBankEntity, String> {
    @Query(value = "SELECT a.bank_id AS bankId, b.customer_sync_id AS customerSyncId, "
            + "a.short_name AS shortName, a.active_fee AS activeFee, a.annual_fee AS annualFee, "
            + " a.trans_fee AS transFee, a.percent_fee AS percentFee, a.vat as vat, "
            + "a.counting_trans_type AS countingTransType "
            + "FROM account_bank_fee a "
            + "INNER JOIN account_customer_bank b ON a.bank_id = b.bank_id "
            + "WHERE b.customer_sync_id IN (:mids)", nativeQuery = true)
    List<ICustomerBankFeeDTO> getCustomerBankFeeByMids(List<String> mids);

    @Query(value = "SELECT a.id AS id, a.bank_account AS bankAccount, "
            + "b.bank_code, a.bank_account_name AS userBankName, "
            + "CASE WHEN mms_active = TRUE THEN 2 ELSE 1 END AS flow, "
            + "b.bank_short_name, c.customer_sync_id AS customerSyncId "
            + "FROM account_bank_receive a "
            + "INNER JOIN bank_type b ON b.id = a.bank_type_id "
            + "INNER JOIN account_customer_bank c ON c.bank_id = a.id "
            + "WHERE c.customer_sync_id IN (:cusId) AND is_authenticated = TRUE "
            , nativeQuery = true)
    List<BrInfoDTO> getBanks(List<String> cusId);
}
