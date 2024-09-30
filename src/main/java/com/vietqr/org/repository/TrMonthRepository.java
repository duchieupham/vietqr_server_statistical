package com.vietqr.org.repository;

import com.vietqr.org.dto.IMidStatisticDTO;
import com.vietqr.org.dto.ITransAdminSumDTO;
import com.vietqr.org.dto.ITransMonthDTO;
import com.vietqr.org.dto.ITransSumDTO;
import com.vietqr.org.entity.TrMonthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TrMonthRepository extends JpaRepository<TrMonthEntity, String> {

    @Query(value = "SELECT a.mids AS mids, a.sum_mid AS sumMid, "
            + "a.month AS time, a.sum AS sum "
            + "FROM tr_month a "
            + "WHERE a.month = :time", nativeQuery = true)
    List<IMidStatisticDTO> getTransMidStatistic(String time);

    @Query(value = "SELECT month AS time, sum AS transSum "
            + "FROM tr_month "
            + "WHERE month LIKE :time% "
            + "ORDER BY month DESC "
            + "LIMIT :offset, :size ", nativeQuery = true)
    List<ITransSumDTO> getTransSumStatistic(String time, int offset, int size);

    @Query(value = "SELECT COUNT(id) "
            + "FROM tr_month "
            + "WHERE month LIKE :time% ", nativeQuery = true)
    int countTranSumStatistic(String time);

    @Query(value = "SELECT COALESCE(SUM(JSON_EXTRACT(sum, '$.debit')), 0) AS debitTotal, "
            + "COALESCE(SUM(JSON_EXTRACT(sum, '$.recon')), 0) AS recTotal, "
            + "COALESCE(SUM(JSON_EXTRACT(sum, '$.total')), 0) AS totalTrans, "
            + "COALESCE(SUM(JSON_EXTRACT(sum, '$.credit')), 0) AS creditTotal, "
            + "COALESCE(SUM(JSON_EXTRACT(sum, '$.deCount')), 0) AS deCountTotal, "
            + "COALESCE(SUM(JSON_EXTRACT(sum, '$.toCount')), 0) AS totalCount, "
            + "COALESCE(SUM(JSON_EXTRACT(sum, '$.creCount')), 0) AS creCountTotal, "
            + "COALESCE(SUM(JSON_EXTRACT(sum, '$.recCount')), 0) AS recCountTotal "
            + "FROM tr_month "
            + "WHERE month LIKE :time% ", nativeQuery = true)
    ITransAdminSumDTO sumTranSumStatistic(String time);

    @Query(value = "SELECT month AS datetime, mids AS mids "
            + "FROM tr_month "
            + "WHERE month = :month ", nativeQuery = true)
    ITransMonthDTO getTranMidStatistic(String month);

    @Query(value = "SELECT trs "
            + "FROM tr_month "
            + "WHERE month = :month LIMIT 1", nativeQuery = true)
    String getTrMonthBr(String month);
}
