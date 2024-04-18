package com.vietqr.org.service;

import com.vietqr.org.dto.CustomerInformationDTO;
import com.vietqr.org.repotrans.CustomerSyncRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerSyncServiceImpl implements CustomerSyncService {
    @Autowired
    private CustomerSyncRepository repo;

    @Override
    public List<CustomerInformationDTO> findAllCustomerSync() {
        return repo.findAllCustomerSync();
    }
}
