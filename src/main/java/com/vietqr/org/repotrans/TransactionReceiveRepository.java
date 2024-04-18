package com.vietqr.org.repotrans;

import com.vietqr.org.dto.ITrDateSumDTO;
import com.vietqr.org.dto.SumTrDateDTO;
import com.vietqr.org.entitytrans.TransactionReceive2312Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionReceiveRepository extends JpaRepository<TransactionReceive2312Entity, String> {
    @Query(value = "SELECT COUNT(a.id) AS toCount, b.customer_sync_id AS mid, "
            + "DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m%d') AS datetime, "
            + "SUM(CASE WHEN (trans_type = 'C' AND (type = 0 OR type = 1)) THEN 1 ELSE 0 END) AS recCount, "
            + "SUM(CASE WHEN (trans_type = 'C') THEN 1 ELSE 0 END) AS creCount, "
            + "SUM(CASE WHEN (trans_type = 'D') THEN 1 ELSE 0 END) AS deCount, "
            + "SUM(a.amount) AS total, "
            + "SUM(CASE WHEN trans_type = 'C' THEN amount ELSE 0 END) AS credit, "
            + "SUM(CASE WHEN trans_type = 'D' THEN amount ELSE 0 END) AS debit, "
            + "SUM(CASE WHEN (type = 0 OR type = 1) THEN amount ELSE 0 END) AS recon "
            + "FROM transaction_receive_2312 a "
            + "INNER JOIN account_customer_bank b "
            + "ON a.bank_id = b.bank_id "
            + "WHERE a.time >= :from AND a.time <= :to AND a.status = 1 "
            + "GROUP BY DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m%d'), "
            + "b.customer_sync_id ", nativeQuery = true)
    List<ITrDateSumDTO> getTrDateSync(long from, long to);

    @Query(value = "SELECT COUNT(a.id) AS toCount, "
            + "DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m%d') AS datetime, "
            + "SUM(CASE WHEN (trans_type = 'C' AND (type = 0 OR type = 1)) THEN 1 ELSE 0 END) AS recCount, "
            + "SUM(CASE WHEN (trans_type = 'C') THEN 1 ELSE 0 END) AS creCount, "
            + "SUM(CASE WHEN (trans_type = 'D') THEN 1 ELSE 0 END) AS deCount, "
            + "SUM(a.amount) AS total, "
            + "SUM(CASE WHEN trans_type = 'C' THEN amount ELSE 0 END) AS credit, "
            + "SUM(CASE WHEN trans_type = 'D' THEN amount ELSE 0 END) AS debit, "
            + "SUM(CASE WHEN (type = 0 OR type = 1) THEN amount ELSE 0 END) AS recon "
            + "FROM transaction_receive_2312 a "
            + "WHERE DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m%d') IN (:dateTime) "
            + "AND a.status = 1 "
            + "GROUP BY DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m%d') "
            , nativeQuery = true)
    List<SumTrDateDTO> getSumTrDate(List<String> dateTime);

    @Query(value = "SELECT COUNT(a.id) AS toCount, b.customer_sync_id AS mid, "
            + "DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m') AS datetime, "
            + "SUM(CASE WHEN (trans_type = 'C' AND (type = 0 OR type = 1)) THEN 1 ELSE 0 END) AS recCount, "
            + "SUM(CASE WHEN (trans_type = 'C') THEN 1 ELSE 0 END) AS creCount, "
            + "SUM(CASE WHEN (trans_type = 'D') THEN 1 ELSE 0 END) AS deCount, "
            + "SUM(a.amount) AS total, "
            + "SUM(CASE WHEN trans_type = 'C' THEN amount ELSE 0 END) AS credit, "
            + "SUM(CASE WHEN trans_type = 'D' THEN amount ELSE 0 END) AS debit, "
            + "SUM(CASE WHEN (type = 0 OR type = 1) THEN amount ELSE 0 END) AS recon "
            + "FROM transaction_receive_2312 a "
            + "INNER JOIN account_customer_bank b "
            + "ON a.bank_id = b.bank_id "
            + "WHERE a.time >= :from AND a.time <= :to AND a.status = 1 "
            + "GROUP BY DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m'), "
            + "b.customer_sync_id ", nativeQuery = true)
    List<ITrDateSumDTO> getTrMonthSync(long from, long to);

    @Query(value = "SELECT COUNT(a.id) AS toCount, "
            + "DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m') AS datetime, "
            + "SUM(CASE WHEN (trans_type = 'C' AND (type = 0 OR type = 1)) THEN 1 ELSE 0 END) AS recCount, "
            + "SUM(CASE WHEN (trans_type = 'C') THEN 1 ELSE 0 END) AS creCount, "
            + "SUM(CASE WHEN (trans_type = 'D') THEN 1 ELSE 0 END) AS deCount, "
            + "SUM(a.amount) AS total, "
            + "SUM(CASE WHEN trans_type = 'C' THEN amount ELSE 0 END) AS credit, "
            + "SUM(CASE WHEN trans_type = 'D' THEN amount ELSE 0 END) AS debit, "
            + "SUM(CASE WHEN (type = 0 OR type = 1) THEN amount ELSE 0 END) AS recon "
            + "FROM transaction_receive_2312 a "
            + "WHERE DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m') IN (:dateTime) AND a.status = 1 "
            + "GROUP BY DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m')"
            , nativeQuery = true)
    List<SumTrDateDTO> getSumTrMonth(List<String> dateTime);

    @Query(value = "SELECT COUNT(a.id) AS toCount, b.bank_id AS mid, "
            + "DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m') AS datetime, "
            + "SUM(CASE WHEN (trans_type = 'C' AND (type = 0 OR type = 1)) THEN 1 ELSE 0 END) AS recCount, "
            + "SUM(CASE WHEN (trans_type = 'C') THEN 1 ELSE 0 END) AS creCount, "
            + "SUM(CASE WHEN (trans_type = 'D') THEN 1 ELSE 0 END) AS deCount, "
            + "SUM(a.amount) AS total, "
            + "SUM(CASE WHEN trans_type = 'C' THEN amount ELSE 0 END) AS credit, "
            + "SUM(CASE WHEN trans_type = 'D' THEN amount ELSE 0 END) AS debit, "
            + "SUM(CASE WHEN (type = 0 OR type = 1) THEN amount ELSE 0 END) AS recon "
            + "FROM transaction_receive_2312 a "
            + "INNER JOIN account_customer_bank b "
            + "ON a.bank_id = b.bank_id "
            + "WHERE a.time >= :from AND a.time <= :to AND a.status = 1 AND b.bank_id IN (:bankIds) "
            + "GROUP BY DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(a.time), '+00:00', '+07:00'), '%Y%m'), b.bank_id ", nativeQuery = true)
    List<ITrDateSumDTO> getTrMonthSyncBank(long from, long to, List<String> bankIds);
}
