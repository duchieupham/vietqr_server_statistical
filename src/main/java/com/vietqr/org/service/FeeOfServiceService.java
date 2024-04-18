package com.vietqr.org.service;

import com.vietqr.org.dto.IFeeOfServiceDTO;
import com.vietqr.org.entity.FeeOfServiceEntity;
import org.springframework.stereotype.Service;

@Service
public interface FeeOfServiceService {
    IFeeOfServiceDTO getFeeOfServiceByMonth(String time);

    void insert(FeeOfServiceEntity entity);
}
