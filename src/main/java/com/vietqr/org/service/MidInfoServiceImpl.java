package com.vietqr.org.service;

import com.vietqr.org.dto.IMidInfoDTO;
import com.vietqr.org.entity.MidInfoEntity;
import com.vietqr.org.repository.MidInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MidInfoServiceImpl implements MidInfoService {

    @Autowired
    private MidInfoRepository repo;
    @Override
    public List<IMidInfoDTO> getInfoByName(String time, String value, int offset, int size) {
        return repo.getInfoByName(time, value, offset, size);
    }

    @Override
    public List<IMidInfoDTO> getInfoByVso(String time, String value, int offset, int size) {
        return repo.getInfoByVso(time, value, offset, size);
    }

    @Override
    public List<IMidInfoDTO> getInfoByNationalId(String time, String value, int offset, int size) {
        return repo.getInfoByNationalId(time, value, offset, size);
    }

    @Override
    public List<IMidInfoDTO> getInfo(String time, int offset, int size) {
        return repo.getInfo(time, offset, size);
    }

    @Override
    public List<IMidInfoDTO> getInfoByNameByMonth(String time, String value, int offset, int size) {
        return repo.getInfoByNameByMonth(time, value, offset, size);
    }

    @Override
    public List<IMidInfoDTO> getInfoByVsoByMonth(String time, String value, int offset, int size) {
        return repo.getInfoByVsoByMonth(time, value, offset, size);
    }

    @Override
    public List<IMidInfoDTO> getInfoByNationalIdByMonth(String time, String value, int offset, int size) {
        return repo.getInfoByNationalIdByMonth(time, value, offset, size);
    }

    @Override
    public List<IMidInfoDTO> getInfoByMonth(String time, int offset, int size) {
        return repo.getInfoByMonth(time, offset, size);
    }

    @Override
    public int countInfoByName(String time, String value) {
        return repo.countInfoByName(time, value);
    }

    @Override
    public int countInfoByVso(String time, String value) {
        return repo.countInfoByVso(time, value);
    }

    @Override
    public int countInfoByNationalId(String time, String value) {
        return repo.countInfoByNationalId(time, value);
    }

    @Override
    public int countInfo(String time) {
        return repo.countInfo(time);
    }

    @Override
    public int countInfoByNameByMonth(String time, String value) {
        return repo.countInfoByNameByMonth(time, value);
    }

    @Override
    public int countInfoByVsoByMonth(String time, String value) {
        return repo.countInfoByVsoByMonth(time, value);
    }

    @Override
    public int countInfoByNationalIdByMonth(String time, String value) {
        return repo.countInfoByNationalIdByMonth(time, value);
    }

    @Override
    public int countInfoByMonth(String time) {
        return repo.countInfoByMonth(time);
    }

    @Override
    public void insertAll(List<MidInfoEntity> entities) {
        repo.saveAll(entities);
    }

    @Override
    public IMidInfoDTO getInfoById(String mid) {
        return repo.getInfoById(mid);
    }
}
