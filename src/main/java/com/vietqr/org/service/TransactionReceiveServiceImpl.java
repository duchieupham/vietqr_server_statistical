package com.vietqr.org.service;

import com.vietqr.org.dto.ITrDateSumDTO;
import com.vietqr.org.dto.SumTrDateDTO;
import com.vietqr.org.repotrans.TransactionReceiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionReceiveServiceImpl implements TransactionReceiveService {

    @Autowired
    private TransactionReceiveRepository repo;
    @Override
    public List<ITrDateSumDTO> getTrDateSync(long from, long to) {
        return repo.getTrDateSync(from, to);
    }

    @Override
    public List<SumTrDateDTO> getSumTrDate(List<String> dateTime) {
        return repo.getSumTrDate(dateTime);
    }

    @Override
    public List<ITrDateSumDTO> getTrMonthSync(long from, long to) {
        return repo.getTrMonthSync(from, to);
    }

    @Override
    public List<ITrDateSumDTO> getTrMonthSyncBank(long from, long to, List<String> bankIds) {
        return repo.getTrMonthSyncBank(from, to, bankIds);
    }


    @Override
    public List<SumTrDateDTO> getSumTrMonth(List<String> dateTime) {
        return repo.getSumTrMonth(dateTime);
    }
}
