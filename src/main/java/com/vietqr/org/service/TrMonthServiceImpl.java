package com.vietqr.org.service;

import com.vietqr.org.dto.IMidStatisticDTO;
import com.vietqr.org.dto.ITransAdminSumDTO;
import com.vietqr.org.dto.ITransMonthDTO;
import com.vietqr.org.dto.ITransSumDTO;
import com.vietqr.org.entity.TrMonthEntity;
import com.vietqr.org.repository.TrMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrMonthServiceImpl implements TrMonthService {

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
}
