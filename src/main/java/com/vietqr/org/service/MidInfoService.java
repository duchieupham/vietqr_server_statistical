package com.vietqr.org.service;

import com.vietqr.org.dto.IMidInfoDTO;
import com.vietqr.org.entity.MidInfoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MidInfoService {
    List<IMidInfoDTO> getInfoByName(String time, String value, int offset, int size);

    List<IMidInfoDTO> getInfoByVso(String time, String value, int offset, int size);

    List<IMidInfoDTO> getInfoByNationalId(String time, String value, int offset, int size);

    List<IMidInfoDTO> getInfo(String time, int offset, int size);

    List<IMidInfoDTO>  getInfoByNameByMonth(String time, String value, int offset, int size);

    List<IMidInfoDTO> getInfoByVsoByMonth(String time, String value, int offset, int size);

    List<IMidInfoDTO> getInfoByNationalIdByMonth(String time, String value, int offset, int size);

    List<IMidInfoDTO> getInfoByMonth(String time, int offset, int size);

    int countInfoByName(String time, String value);

    int countInfoByVso(String time, String value);

    int countInfoByNationalId(String time, String value);

    int countInfo(String time);

    int countInfoByNameByMonth(String time, String value);

    int countInfoByVsoByMonth(String time, String value);

    int countInfoByNationalIdByMonth(String time, String value);

    int countInfoByMonth(String time);

    void insertAll(List<MidInfoEntity> entities);

    IMidInfoDTO getInfoById(String mid);
}
