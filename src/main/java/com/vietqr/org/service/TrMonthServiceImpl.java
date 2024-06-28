package com.vietqr.org.service;

import com.vietqr.org.dto.IMidStatisticDTO;
import com.vietqr.org.dto.ITransAdminSumDTO;
import com.vietqr.org.dto.ITransMonthDTO;
import com.vietqr.org.dto.ITransSumDTO;
import com.vietqr.org.entity.TrMonthEntity;
import com.vietqr.org.repository.TrMonthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class TrMonthServiceImpl implements TrMonthService {
    private static final Logger logger = LoggerFactory.getLogger(TrMonthServiceImpl.class);
    @Autowired
    private TrMonthRepository repo;
    @Override
    public List<IMidStatisticDTO> getTransMidStatistic(String time) {
        return repo.getTransMidStatistic(time);
    }

    @Override
    public void insertAll(List<TrMonthEntity> entities) {
        repo.saveAll(entities);
    }

    @Override
    public List<ITransSumDTO> getTransSumStatistic(String time, int offset, int size) {
        return repo.getTransSumStatistic(time, offset, size);
    }

    @Override
    public int countTranSumStatistic(String time) {
        return repo.countTranSumStatistic(time);
    }

    @Override
    public ITransAdminSumDTO sumTranSumStatistic(String time) {
        return repo.sumTranSumStatistic(time);
    }

    @Override
    public ITransMonthDTO getTranMidStatistic(String month) {
        return repo.getTranMidStatistic(month);
    }

    @Override
    public String getTrMonthBr(String month) {
        return repo.getTrMonthBr(month);
    }

//    @Override
//    public void saveUserStatistics(long count, String sumUserJson, String month) {
//        logger.info("Saving user statistics: count={}, month={}, sumUserJson={}", count, month, sumUserJson);
//
//        // Chuyển đổi định dạng tháng
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMM");
//        YearMonth yearMonth = YearMonth.parse(month, inputFormatter);
//        String formattedMonth = yearMonth.format(outputFormatter);
//
//        TrMonthEntity trMonthEntity = new TrMonthEntity();
//        trMonthEntity.setId(UUID.randomUUID().toString());
//        trMonthEntity.setMids("{}");
//        trMonthEntity.setTids("{}");
//        trMonthEntity.setSum("{}");
//        trMonthEntity.setSumMid("{}");
//        trMonthEntity.setSumUser(sumUserJson);
//        trMonthEntity.setMonth(formattedMonth); // Lưu tháng định dạng yyyyMM
//
//        try {
//            repo.save(trMonthEntity);  // Đảm bảo dòng này được thực thi mà không có lỗi
//            logger.info("Successfully saved user statistics");
//        } catch (Exception e) {
//            logger.error("Failed to save user statistics", e);
//        }
//    }
    @Override
    public void saveUserStatistics(long count, String sumUserJson, String month) {
        logger.info("Saving user statistics: count={}, month={}, sumUserJson={}", count, month, sumUserJson);

        // Chuyển đổi định dạng tháng
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMM");
        String formattedMonth;
        if (month.contains("-")) {
            // Định dạng tháng hiện tại đến ngày hôm trước (yyyy-MM-dd)
            DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(month, fullFormatter);
            formattedMonth = date.format(outputFormatter) + String.format("%02d", date.getDayOfMonth());
        } else {
            // Định dạng tháng đầy đủ (yyyy-MM)
            YearMonth yearMonth = YearMonth.parse(month, inputFormatter);
            formattedMonth = yearMonth.format(outputFormatter);
        }

        TrMonthEntity trMonthEntity = new TrMonthEntity();
        trMonthEntity.setId(UUID.randomUUID().toString());
        trMonthEntity.setMids("{}");
        trMonthEntity.setTids("{}");
        trMonthEntity.setSum("{}");
        trMonthEntity.setSumMid("{}");
        trMonthEntity.setSumUser(sumUserJson);
        trMonthEntity.setMonth(formattedMonth); // Lưu tháng định dạng yyyyMM hoặc yyyyMMdd

        try {
            repo.save(trMonthEntity);  // Đảm bảo dòng này được thực thi mà không có lỗi
            logger.info("Successfully saved user statistics");
        } catch (Exception e) {
            logger.error("Failed to save user statistics", e);
        }
    }
}
