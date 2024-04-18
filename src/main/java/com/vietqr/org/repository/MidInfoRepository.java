package com.vietqr.org.repository;

import com.vietqr.org.dto.IMidInfoDTO;
import com.vietqr.org.entity.MidInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MidInfoRepository extends JpaRepository<MidInfoEntity, String> {
    @Query(value = "SELECT a.info AS info, a.service_type AS serviceType, "
            + "a.id AS id "
            + "FROM mid_info a INNER JOIN tr_date b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE JSON_EXTRACT(a.info, '$.name') LIKE %:value% AND b.date = :time "
            + "LIMIT :offset, :size ", nativeQuery = true)
    List<IMidInfoDTO> getInfoByName(String time, String value, int offset, int size);

    @Query(value = "SELECT a.info AS info, a.service_type AS serviceType, "
            + "a.id AS id "
            + "FROM mid_info a INNER JOIN tr_date b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE  JSON_EXTRACT(a.info, '$.vso') = :value AND b.date = :time "
            + "LIMIT :offset, :size ", nativeQuery = true)
    List<IMidInfoDTO> getInfoByVso(String time, String value, int offset, int size);

    @Query(value = "SELECT a.info AS info, a.service_type AS serviceType, "
            + "a.id AS id "
            + "FROM mid_info a INNER JOIN tr_date b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE JSON_EXTRACT(a.info, '$.nationalId') = :value AND b.date = :time "
            + "LIMIT :offset, :size ", nativeQuery = true)
    List<IMidInfoDTO> getInfoByNationalId(String time, String value, int offset, int size);

    @Query(value = "SELECT a.info AS info, a.service_type AS serviceType, "
            + "a.id AS id "
            + "FROM mid_info a INNER JOIN tr_date b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE b.date = :time "
            + "LIMIT :offset, :size ", nativeQuery = true)
    List<IMidInfoDTO> getInfo(String time, int offset, int size);

    @Query(value = "SELECT a.info AS info, a.service_type AS sumMid, "
            + "a.id AS id "
            + "FROM mid_info a INNER JOIN tr_month b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE JSON_EXTRACT(a.info, '$.name') LIKE %:value% AND b.month = :time "
            + "LIMIT :offset, :size ", nativeQuery = true)
    List<IMidInfoDTO> getInfoByNameByMonth(String time, String value, int offset, int size);

    @Query(value = "SELECT a.info AS info, a.service_type AS sumMid, "
            + "a.id AS id "
            + "FROM mid_info a INNER JOIN tr_month b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE JSON_EXTRACT(a.info, '$.vso') = :value AND b.month = :time "
            + "LIMIT :offset, :size ", nativeQuery = true)
    List<IMidInfoDTO> getInfoByVsoByMonth(String time, String value, int offset, int size);

    @Query(value = "SELECT a.info AS info, a.service_type AS sumMid, "
            + "a.id AS id "
            + "FROM mid_info a INNER JOIN tr_month b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE JSON_EXTRACT(a.info, '$.nationalId') = :value AND b.month = :time "
            + "LIMIT :offset, :size ", nativeQuery = true)
    List<IMidInfoDTO> getInfoByNationalIdByMonth(String time, String value, int offset, int size);

    @Query(value = "SELECT a.info AS info, a.service_type AS sumMid, "
            + "a.id AS id "
            + "FROM mid_info a INNER JOIN tr_month b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE b.month = :time "
            + "LIMIT :offset, :size ", nativeQuery = true)
    List<IMidInfoDTO> getInfoByMonth(String time, int offset, int size);

    @Query(value = "SELECT COUNT(a.id) "
            + "FROM mid_info a INNER JOIN tr_date b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE JSON_EXTRACT(a.info, '$.name') LIKE %:value% "
            + "AND b.date = :time ", nativeQuery = true)
    int countInfoByName(String time, String value);

    @Query(value = "SELECT COUNT(a.id) "
            + "FROM mid_info a INNER JOIN tr_date b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE JSON_EXTRACT(a.info, '$.vso') = :value "
            + "AND b.date = :time ", nativeQuery = true)
    int countInfoByVso(String time, String value);

    @Query(value = "SELECT COUNT(a.id) "
            + "FROM mid_info a INNER JOIN tr_date b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE JSON_EXTRACT(a.info, '$.nationalId') = :value "
            + "AND b.date = :time ", nativeQuery = true)
    int countInfoByNationalId(String time, String value);

    @Query(value = "SELECT COUNT(a.id) "
            + "FROM mid_info a INNER JOIN tr_date b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE b.date = :time ", nativeQuery = true)
    int countInfo(String time);

    @Query(value = "SELECT COUNT(a.id) "
            + "FROM mid_info a INNER JOIN tr_month b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE JSON_EXTRACT(a.info, '$.name') LIKE %:value% "
            + "AND b.month = :time ", nativeQuery = true)
    int countInfoByNameByMonth(String time, String value);

    @Query(value = "SELECT COUNT(a.id) "
            + "FROM mid_info a INNER JOIN tr_month b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE JSON_EXTRACT(a.info, '$.vso') = :value "
            + "AND b.month = :time ", nativeQuery = true)
    int countInfoByVsoByMonth(String time, String value);

    @Query(value = "SELECT COUNT(a.id) "
            + "FROM mid_info a INNER JOIN tr_month b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE JSON_EXTRACT(a.info, '$.nationalId') = :value "
            + "AND b.month = :time ", nativeQuery = true)
    int countInfoByNationalIdByMonth(String time, String value);

    @Query(value = "SELECT COUNT(a.id) "
            + "FROM mid_info a INNER JOIN tr_month b "
            + "ON JSON_CONTAINS(JSON_EXTRACT(b.mids, '$[*].mid'), JSON_QUOTE(a.id)) "
            + "WHERE b.month = :time ", nativeQuery = true)
    int countInfoByMonth(String time);

    @Query(value = "SELECT a.info AS info , a.service_type AS serviceType, "
            + "a.id AS id "
            + "FROM mid_info a "
            + "WHERE a.id = :mid LIMIT 1 ", nativeQuery = true)
    IMidInfoDTO getInfoById(String mid);
}
