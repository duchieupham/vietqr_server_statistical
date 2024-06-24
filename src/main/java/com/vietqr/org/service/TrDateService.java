package com.vietqr.org.service;

import com.vietqr.org.dto.IMidStatisticDTO;
import com.vietqr.org.dto.ITransAdminSumDTO;
import com.vietqr.org.dto.ITransSumDTO;
import com.vietqr.org.entity.TrDateEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrDateService {
    List<IMidStatisticDTO> getTransMidStatistic(String time);

    void insertAll(List<TrDateEntity> entities);

    List<ITransSumDTO> getTranSumStatistic(String time, int offset, int size);

    int countTranSumStatistic(String time);

    ITransAdminSumDTO sumTranSumStatistic(String time);

//    List<ITransSumDTO> getTranSumStatistic(String time, int offset, int size);

    void saveUserStatistics(long count, String date);
}
