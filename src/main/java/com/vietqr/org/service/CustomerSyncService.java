package com.vietqr.org.service;

import com.vietqr.org.dto.CustomerInformationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerSyncService {
    List<CustomerInformationDTO> findAllCustomerSync();
}
