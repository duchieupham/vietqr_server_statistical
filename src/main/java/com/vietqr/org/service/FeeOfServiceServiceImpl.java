package com.vietqr.org.service;

import com.vietqr.org.dto.IFeeOfServiceDTO;
import com.vietqr.org.entity.FeeOfServiceEntity;
import com.vietqr.org.repository.FeeOfServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeOfServiceServiceImpl implements FeeOfServiceService {
    @Autowired
    private FeeOfServiceRepository repo;
    @Override
    public IFeeOfServiceDTO getFeeOfServiceByMonth(String time) {
        return repo.getFeeOfServiceByMonth(time);
    }

    @Override
    public void insert(FeeOfServiceEntity entity) {
        repo.save(entity);
    }
}
