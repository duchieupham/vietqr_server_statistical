package com.vietqr.org.service;

import com.vietqr.org.dto.ITrDateSumDTO;
import com.vietqr.org.dto.SumTrDateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionReceiveService {
    List<ITrDateSumDTO> getTrDateSync(long from, long to);

    List<SumTrDateDTO> getSumTrDate(List<String> dateTime);

    List<ITrDateSumDTO> getTrMonthSync(long from, long to);

    List<ITrDateSumDTO> getTrMonthSyncBank(long from, long to, List<String> bankIds);

    List<SumTrDateDTO> getSumTrMonth(List<String> dateTime);
}
