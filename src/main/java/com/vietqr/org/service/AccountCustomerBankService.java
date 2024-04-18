package com.vietqr.org.service;

import com.vietqr.org.dto.ICustomerBankFeeDTO;
import com.vietqr.org.dto.BrInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountCustomerBankService {
    List<ICustomerBankFeeDTO> getCustomerBankFeeByMids(List<String> mids);

    List<BrInfoDTO> getBanks(List<String> cusId);
}
