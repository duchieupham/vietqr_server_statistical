package com.vietqr.org.service;

import com.vietqr.org.dto.ICustomerBankFeeDTO;
import com.vietqr.org.dto.BrInfoDTO;
import com.vietqr.org.repository.AccountCustomerBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountCustomerBankServiceImpl implements AccountCustomerBankService {
    @Autowired
    private AccountCustomerBankRepository repo;

    @Override
    public List<ICustomerBankFeeDTO> getCustomerBankFeeByMids(List<String> mids) {
        return repo.getCustomerBankFeeByMids(mids);
    }

    @Override
    public List<BrInfoDTO> getBanks(List<String> cusId) {
        return repo.getBanks(cusId);
    }
}
