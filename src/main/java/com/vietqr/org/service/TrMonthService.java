package com.vietqr.org.service;

import com.vietqr.org.dto.*;
import com.vietqr.org.entity.TrMonthEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrMonthService {
    List<IMidStatisticDTO> getTransMidStatistic(String time);

    void insertAll(List<TrMonthEntity> entities);

    List<ITransSumDTO> getTransSumStatistic(String time, int offset, int size);

    int countTranSumStatistic(String time);

    ITransAdminSumDTO sumTranSumStatistic(String time);

    ITransMonthDTO getTranMidStatistic(String month);

    String getTrMonthBr(String month);

    void saveUserStatistics(long count, String sumUserJson, String month);
}
