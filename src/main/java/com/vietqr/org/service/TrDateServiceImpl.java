package com.vietqr.org.service;

import com.vietqr.org.dto.IMidStatisticDTO;
import com.vietqr.org.dto.ITransAdminSumDTO;
import com.vietqr.org.dto.ITransSumDTO;
import com.vietqr.org.entity.TrDateEntity;
import com.vietqr.org.repository.TrDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrDateServiceImpl implements TrDateService {
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
}
