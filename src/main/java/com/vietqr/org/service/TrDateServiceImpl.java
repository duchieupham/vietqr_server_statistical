package com.vietqr.org.service;

import com.vietqr.org.dto.IMidStatisticDTO;
import com.vietqr.org.dto.ITransAdminSumDTO;
import com.vietqr.org.dto.ITransSumDTO;
import com.vietqr.org.entity.TrDateEntity;
import com.vietqr.org.repository.TrDateRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;


@Service
public class TrDateServiceImpl implements TrDateService {
    private static final Logger logger = LoggerFactory.getLogger(TrDateService.class);

    @Autowired
    private TrDateRepository repo;
    @Override
    public List<IMidStatisticDTO> getTransMidStatistic(String time) {
        return repo.getTransMidStatistic(time);
    }

    @Override
    public void insertAll(List<TrDateEntity> entities) {
        repo.saveAll(entities);
    }

    @Override
    public List<ITransSumDTO> getTranSumStatistic(String time, int offset, int size) {
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
    public void saveUserStatistics(long count, String date) {
        System.out.println("Saving user statistics: count={}, date={}");

        // Chuyển đổi định dạng ngày
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, inputFormatter);
        String formattedDate = localDate.format(outputFormatter);
        TrDateEntity trDateEntity = new TrDateEntity();
        trDateEntity.setId(UUID.randomUUID().toString());
        trDateEntity.setMids("{}");
        trDateEntity.setTids("{}");
        trDateEntity.setSum("{}");
        trDateEntity.setBrCount("{}");
        trDateEntity.setSumMid("{}");
        trDateEntity.setSumUser(String.valueOf(count));

        trDateEntity.setDate(formattedDate); // Lưu ngày định dạng yyyyMMdd

        try {
            repo.save(trDateEntity);  // Đảm bảo dòng này được thực thi mà không có lỗi
            System.out.println("Successfully saved user statistics");
        } catch (Exception e) {
            System.out.println("Failed to save user statistics");
        }
    }
    @Override
    public void saveUserStatistics(long count, String sumUserJson, String date) {
        logger.info("Saving user statistics: count={}, date={}", count, date);

        // Chuyển đổi định dạng ngày
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, inputFormatter);
        String formattedDate = localDate.format(outputFormatter);

        TrDateEntity trDateEntity = new TrDateEntity();
        trDateEntity.setId(UUID.randomUUID().toString());
        trDateEntity.setMids("{}");
        trDateEntity.setTids("{}");
        trDateEntity.setSum("{}");
        trDateEntity.setBrCount("{}");
        trDateEntity.setSumMid("{}");
        trDateEntity.setSumUser(sumUserJson);  // Lưu sumUserJson
        trDateEntity.setDate(formattedDate); // Lưu ngày định dạng yyyyMMdd

        try {
            repo.save(trDateEntity);  // Đảm bảo dòng này được thực thi mà không có lỗi
            logger.info("Successfully saved user statistics");
        } catch (Exception e) {
            logger.error("Failed to save user statistics", e);
        }
    }
}
