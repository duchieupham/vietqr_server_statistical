package com.vietqr.org.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietqr.org.dto.*;
import com.vietqr.org.mapper.MidInfoMapper;
import com.vietqr.org.mapper.MidMapper;
import com.vietqr.org.mapper.SumMapper;
import com.vietqr.org.mapper.SumMidMapper;
import com.vietqr.org.scheduled.IAutomationService;
import com.vietqr.org.service.MidInfoService;
import com.vietqr.org.service.TrDateService;
import com.vietqr.org.service.TrMonthService;
import com.vietqr.org.util.DateTimeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TransactionStatisticController {
    private static final Logger logger = Logger.getLogger(TransactionStatisticController.class);
    @Autowired
    private TrMonthService trMonthService;

    @Autowired
    private TrDateService trDateService;

    @Autowired
    private IAutomationService automationService;

    @Autowired
    private MidInfoService midInfoService;

    @GetMapping("/mid/statistic")
    public ResponseEntity<PageResultDTO> getTransMidStatistic(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value ="filterBy") int filterBy,
            @RequestParam(value = "time") String time,
            @RequestParam(value = "type") int type,
            @RequestParam(value = "value") String value) {
        PageResultDTO result = new PageResultDTO();
        HttpStatus httpStatus = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String[] parts = time.split("-");
            time = String.join("", parts);
            List<MidStatisticDTO> midStatisticDTOS = new ArrayList<>();
            List<IMidStatisticDTO> dtos = new ArrayList<>();
            List<IMidInfoDTO> infos = new ArrayList<>();
            List<MidMapper> midMappers = new ArrayList<>();
            DataDTO dataDTO = new DataDTO(new SumMidMapper(0,0,0,0,0,0,0,0));
            int offset = (page - 1) * size;
            int totalElement = 0;
            // 0 = daily, 1 = monthly
            switch (filterBy) {
                case 0:
                    dtos = trDateService.getTransMidStatistic(time);
                    SumMidMapper e = new SumMidMapper();
                    String midMappersString2 = "";
                    if (dtos != null && !dtos.isEmpty()) {
                        String json2 = dtos.get(0).getSumMid();
                        e = mapper.readValue(json2, SumMidMapper.class);
                        midMappersString2 = dtos.get(0).getMids();
                        try {
                            midMappers = mapper.readValue(midMappersString2, new TypeReference<List<MidMapper>>() {});
                        } catch (IOException ignored) {}
                    }
                    switch (type) {
                        // 0 = name, 1 = vso, 2 = nationalId, 9 = all
                        case 0:
                            infos = midInfoService.getInfoByName(time, value, offset, size);
                            totalElement = midInfoService.countInfoByName(time, value);
                            break;
                        case 1:
                            infos = midInfoService.getInfoByVso(time, value, offset, size);
                            totalElement = midInfoService.countInfoByVso(time, value);
                            break;
                        case 2:
                            infos = midInfoService.getInfoByNationalId(time, value, offset, size);
                            totalElement = midInfoService.countInfoByNationalId(time, value);
                            break;
                        case 9:
                            infos = midInfoService.getInfo(time, offset, size);
                            totalElement = midInfoService.countInfo(time);
                            break;
                        default:
                            break;
                    }
                    List<MidMapper> finalMidMappers = midMappers;
                    midStatisticDTOS = infos.stream().map(info -> {
                        MidStatisticDTO dto = new MidStatisticDTO();
                        MidInfoMapper midInfo = null;
                        try {
                            midInfo = mapper.readValue(info.getInfo(), MidInfoMapper.class);
                        } catch (JsonProcessingException ex) {
                            throw new RuntimeException(ex);
                        }
                        dto.setMid(info.getId());
                        dto.setName(midInfo.getName());
                        dto.setVso(midInfo.getVso());
                        dto.setNationalId(midInfo.getNationalId());
                        dto.setCredit(finalMidMappers.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getCredit).sum());
                        dto.setCreCount((int) finalMidMappers.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getCreCount).sum());
                        dto.setDeCount((int) finalMidMappers.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getDeCount).sum());
                        dto.setDebit((int) finalMidMappers.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getDebit).sum());
                        dto.setRecon((long) finalMidMappers.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getRecon).sum());
                        dto.setReCount((int) finalMidMappers.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getRecCount).sum());
                        dto.setToCount((int) finalMidMappers.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getToCount).sum());
                        dto.setTotal((long) finalMidMappers.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getTotal).sum());
                        return dto;
                    }).collect(Collectors.toList());
                    dataDTO.setExtraData(e);
                    break;
                case 1:
                    dtos = trMonthService.getTransMidStatistic(time);
                    SumMidMapper ex = new SumMidMapper();
                    if (dtos != null && !dtos.isEmpty()) {
                        String json = dtos.get(0).getSumMid();
                        ex = mapper.readValue(json, SumMidMapper.class);
                        String midMappersString = dtos.get(0).getMids();
                        try {
                            midMappers = mapper.readValue(midMappersString, new TypeReference<List<MidMapper>>() {});
                        } catch (IOException ignored) {}
                    }
                    switch (type) {
                        // 0 = name, 1 = vso, 2 = nationalId, 9 = all
                        case 0:
                            infos = midInfoService.getInfoByNameByMonth(time, value, offset, size);
                            totalElement = midInfoService.countInfoByNameByMonth(time, value);
                            break;
                        case 1:
                            infos = midInfoService.getInfoByVsoByMonth(time, value, offset, size);
                            totalElement = midInfoService.countInfoByVsoByMonth(time, value);
                            break;
                        case 2:
                            infos = midInfoService.getInfoByNationalIdByMonth(time, value, offset, size);
                            totalElement = midInfoService.countInfoByNationalIdByMonth(time, value);
                            break;
                        case 9:
                            infos = midInfoService.getInfoByMonth(time, offset, size);
                            totalElement = midInfoService.countInfoByMonth(time);
                            break;
                        default:
                            break;
                    }
                    List<MidMapper> midMapperList = midMappers;
                    midStatisticDTOS = infos.stream().map(info -> {
                        MidStatisticDTO dto = new MidStatisticDTO();
                        MidInfoMapper midInfo = null;
                        try {
                            midInfo = mapper.readValue(info.getInfo(), MidInfoMapper.class);
                        } catch (JsonProcessingException exc) {
                            throw new RuntimeException(exc);
                        }
                        dto.setMid(info.getId());
                        dto.setName(midInfo.getName());
                        dto.setVso(midInfo.getVso());
                        dto.setNationalId(midInfo.getNationalId());
                        dto.setCredit(midMapperList.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getCredit).sum());
                        dto.setCreCount((int) midMapperList.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getCreCount).sum());
                        dto.setDeCount((int) midMapperList.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getDeCount).sum());
                        dto.setDebit((int) midMapperList.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getDebit).sum());
                        dto.setRecon((long) midMapperList.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getRecon).sum());
                        dto.setReCount((int) midMapperList.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getRecCount).sum());
                        dto.setToCount((int) midMapperList.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getToCount).sum());
                        dto.setTotal((long) midMapperList.stream().filter(m -> m.getMid().equals(info.getId()))
                                .mapToLong(MidMapper::getTotal).sum());
                        return dto;
                    }).collect(Collectors.toList());
                    dataDTO.setExtraData(ex);
                    break;
                default:
                    break;
            }
            PageDTO pageDTO = new PageDTO();
            pageDTO.setPage(page);
            pageDTO.setSize(size);
            pageDTO.setTotalElement(totalElement);
            pageDTO.setTotalPage(totalElement % size == 0 ?
                    totalElement / size : totalElement / size + 1);
            dataDTO.setItems(midStatisticDTOS);
            result.setMetadata(pageDTO);
            result.setData(dataDTO);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            logger.error("TransactionStatisticController.getTransMidStatistic: ERROR: " + e.getMessage()
             + " at: " + System.currentTimeMillis());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result, httpStatus);
    }

    @GetMapping("/trans-admin/statistic")
    public ResponseEntity<PageResultDTO> getTransAdminStatistic(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value ="filterBy") int filterBy,
            @RequestParam(value = "time") String time) {
        PageResultDTO result = new PageResultDTO();
        HttpStatus httpStatus = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String[] parts = time.split("-");
            time = String.join("", parts);
            TransAdminSumDTO extraData = new TransAdminSumDTO();
            List<ITransSumDTO> transSumDTOS = new ArrayList<>();
            int offset = (page - 1) * size;
            int totalElement = 0;
            switch (filterBy) {
                case 1:
                    transSumDTOS = trDateService.getTranSumStatistic(time, offset, size);
                    totalElement = trDateService.countTranSumStatistic(time);
                    ITransAdminSumDTO sumDTO = trDateService.sumTranSumStatistic(time);
                    if (sumDTO != null) {
                        extraData.setCreCountTotal(sumDTO.getCreCountTotal());
                        extraData.setDeCountTotal(sumDTO.getDeCountTotal());
                        extraData.setTotalCount(sumDTO.getTotalCount());
                        extraData.setRecCountTotal(sumDTO.getRecCountTotal());
                        extraData.setCreditTotal(sumDTO.getCreditTotal());
                        extraData.setDebitTotal(sumDTO.getDebitTotal());
                        extraData.setTotalTrans(sumDTO.getTotalTrans());
                        extraData.setRecTotal(sumDTO.getRecTotal());
                    }
                    break;
                case 2:
                    transSumDTOS = trMonthService.getTransSumStatistic(time, offset, size);
                    totalElement = trMonthService.countTranSumStatistic(time);
                    ITransAdminSumDTO sumDTOByMonth = trMonthService.sumTranSumStatistic(time);
                    if (sumDTOByMonth != null) {
                        extraData.setCreCountTotal(sumDTOByMonth.getCreCountTotal());
                        extraData.setDeCountTotal(sumDTOByMonth.getDeCountTotal());
                        extraData.setTotalCount(sumDTOByMonth.getTotalCount());
                        extraData.setRecCountTotal(sumDTOByMonth.getRecCountTotal());
                        extraData.setCreditTotal(sumDTOByMonth.getCreditTotal());
                        extraData.setDebitTotal(sumDTOByMonth.getDebitTotal());
                        extraData.setTotalTrans(sumDTOByMonth.getTotalTrans());
                        extraData.setRecTotal(sumDTOByMonth.getRecTotal());
                    }
                    break;
                default:
                    break;
            }
            List<TransAdminStatisticDTO> responses = new ArrayList<>();
            responses = transSumDTOS.stream().map(item -> {
                TransAdminStatisticDTO dto = new TransAdminStatisticDTO();
                String timeStr = DateTimeUtils.parseToStringFormat(item.getTime());
                dto.setTime(timeStr);
                SumMapper mapper1 = new SumMapper();
                try {
                    mapper1 = mapper.readValue(item.getTransSum(), SumMapper.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                dto.setCredit(mapper1.getCredit());
                dto.setDebit(mapper1.getDebit());
                dto.setRecon(mapper1.getRecon());
                dto.setTotal(mapper1.getTotal());
                dto.setCreCount(mapper1.getCreCount());
                dto.setDeCount(mapper1.getDeCount());
                dto.setToCount(mapper1.getToCount());
                dto.setReCount(mapper1.getRecCount());
                return dto;
            }).collect(Collectors.toList());
            PageDTO pageDTO = new PageDTO();
            pageDTO.setPage(page);
            pageDTO.setSize(size);
            pageDTO.setTotalElement(totalElement);
            pageDTO.setTotalPage(totalElement % size == 0 ?
                    totalElement / size : totalElement / size + 1);
            DataDTO data = new DataDTO(new TransAdminSumDTO(0,0,0,0,0,0,0,0));
            data.setItems(responses);
            data.setExtraData(extraData);

            result.setMetadata(pageDTO);
            result.setData(data);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            logger.error("TransactionStatisticController.getTransAdminStatistic: ERROR: " + e.getMessage()
                    + " at: " + System.currentTimeMillis());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result, httpStatus);
    }

//    @PostMapping("/heheheh")
//    public ResponseEntity<String> getTransAdminStatistic(
//            @RequestParam long from,
//            @RequestParam long to,
//            @RequestBody List<String> bankIds
//    ) {
//        HttpStatus httpStatus = null;
//        try {
//            automationService.syncInformationCustomer();
////            automationService.syncTrDate(from, to);
////            automationService.syncTrMonth(from, to, bankIds);
//            httpStatus = HttpStatus.OK;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            logger.error("TransactionStatisticController.getTransAdminStatistic: ERROR: " + e.getMessage()
//                    + " at: " + System.currentTimeMillis());
//            httpStatus = HttpStatus.BAD_REQUEST;
//        }
//        return new ResponseEntity<>("", httpStatus);
//    }
}
