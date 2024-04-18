package com.vietqr.org.service;

import com.vietqr.org.repository.AccountCustomerBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBankFeeServiceImpl implements AccountBankFeeService {
    @Autowired
    private AccountCustomerBankRepository repo;
}
