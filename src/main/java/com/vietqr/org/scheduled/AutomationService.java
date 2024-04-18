package com.vietqr.org.scheduled;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietqr.org.dto.*;
import com.vietqr.org.entity.MidInfoEntity;
import com.vietqr.org.dto.BrInfoDTO;
import com.vietqr.org.mapper.MidInfoMapper;
import com.vietqr.org.mapper.TrMonthBrMapper;
import com.vietqr.org.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AutomationService implements IAutomationService {
    private static final Logger logger = Logger.getLogger(AutomationService.class);

//    @Autowired
//    private CustomerSyncService customerSyncService;

    @Autowired
    private AccountCustomerBankService accountCustomerBankService;

    @Autowired
    private TrDateService trDateService;

    @Autowired
    private TrMonthService trMonthService;

    @Autowired
    private MidInfoService midInfoService;

//    @Scheduled(zone = "Asia/Ho_Chi_Minh", cron = "0 0 0 * * *")
//    public void syncInformationCustomer() {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            List<CustomerInformationDTO> cus = new ArrayList<>();
//            List<String> empty = new ArrayList<>();
//            List<MidInfoEntity> entities = new ArrayList<>();
//            List<BrInfoDTO> brInfoDTOS = new ArrayList<>();
////            cus = customerSyncService.findAllCustomerSync();
//            List<String> cusId = cus.stream()
//                    .map(CustomerInformationDTO::getId)
//                    .collect(Collectors.toList());
//            brInfoDTOS = accountCustomerBankService.getBanks(cusId);
//            Map<String, List<String>> brInfosMap = brInfoDTOS.stream()
//                    .collect(Collectors.groupingBy(BrInfoDTO::getCustomerSyncId,
//                            Collectors.mapping(BrInfoDTO::getId, Collectors.toList())));
//            entities = cus.stream().map(element -> {
//                List<String> serviceType = new ArrayList<>();
//                MidInfoEntity entity = new MidInfoEntity();
//                MidInfoMapper infoMapper = new MidInfoMapper();
//                infoMapper.setName(element.getName() != null ? element.getName() : element.getInformation());
//                infoMapper.setVso("");
//                infoMapper.setNationalId("");
//                serviceType.add(element.getPlatform());
//                entity.setId(element.getId());
//                try {
//                    List<String> banksIds = brInfosMap.getOrDefault(element.getId(), new ArrayList<>());
//                    entity.setBanks(mapper.writeValueAsString(banksIds));
//                    entity.setServiceType(mapper.writeValueAsString(serviceType));
//                    entity.setInfo(mapper.writeValueAsString(infoMapper));
//                    entity.setTids(mapper.writeValueAsString(empty));
//                } catch (JsonProcessingException e) {
//                    throw new RuntimeException(e);
//                }
//                return entity;
//            }).collect(Collectors.toList());
//            midInfoService.insertAll(entities);
//        } catch (Exception e) {
//            logger.error("Error at scheduleExecuteTask: " + e.toString());
//        }
//    }

    private TrMonthBrMapper mapToTrBrsDTO(ITrDateSumDTO dto) {
        TrMonthBrMapper trDateMidsDTO = new TrMonthBrMapper();
        trDateMidsDTO.setBrId(dto.getMid());
        trDateMidsDTO.setCredit(dto.getCredit());
        trDateMidsDTO.setDebit(dto.getDebit());
        trDateMidsDTO.setRecon(dto.getRecon());
        trDateMidsDTO.setTotal(dto.getTotal());
        trDateMidsDTO.setToCount(dto.getToCount());
        trDateMidsDTO.setCreCount(dto.getCreCount());
        trDateMidsDTO.setDeCount(dto.getDeCount());
        trDateMidsDTO.setRecCount(dto.getRecCount());
        return trDateMidsDTO;
    }

    private TrDateMidsDTO mapToTrDateMidsDTO(ITrDateSumDTO dto) {
        TrDateMidsDTO trDateMidsDTO = new TrDateMidsDTO();
        trDateMidsDTO.setMid(dto.getMid());
        trDateMidsDTO.setCredit(dto.getCredit());
        trDateMidsDTO.setDebit(dto.getDebit());
        trDateMidsDTO.setRecon(dto.getRecon());
        trDateMidsDTO.setTotal(dto.getTotal());
        trDateMidsDTO.setToCount(dto.getToCount());
        trDateMidsDTO.setCreCount(dto.getCreCount());
        trDateMidsDTO.setDeCount(dto.getDeCount());
        trDateMidsDTO.setRecCount(dto.getRecCount());
        return trDateMidsDTO;
    }

//    @Scheduled(cron = "0 0 0 * * *")
//    public void syncTrDate(long from, long to) {
//        try {
//
//            ObjectMapper mapper = new ObjectMapper();
//            List<CustomerInformationDTO> cus = new ArrayList<>();
//            List<String> empty = new ArrayList<>();
//            List<TrDateEntity> entities = new ArrayList<>();
//            List<ITrDateSumDTO> trDateSumDTOS  = new ArrayList<>();
////            trDateSumDTOS = transactionReceiveService.getTrDateSync(from, to);
//            List<TrSumMidDTO> dtos = new ArrayList<>();
//            Map<String, List<ITrDateSumDTO>> roleMaps = trDateSumDTOS.stream()
//                    .collect(Collectors.groupingBy(ITrDateSumDTO::getDatetime));
//            dtos = roleMaps.entrySet().stream()
//                    .map(entry -> {
//                        TrSumMidDTO trSumMidDTO = new TrSumMidDTO();
//                        trSumMidDTO.setDateTime(entry.getKey());
//                        List<TrDateMidsDTO> transDateList = entry.getValue().stream()
//                                .map(this::mapToTrDateMidsDTO)
//                                .collect(Collectors.toList());
//                        trSumMidDTO.setTransDate(transDateList);
//                        return trSumMidDTO;
//                    })
//                    .collect(Collectors.toList());
//            List<String> dateTime = new ArrayList<>();
//            dateTime = dtos.stream().map(TrSumMidDTO::getDateTime).collect(Collectors.toList());
////            List<SumTrDateDTO> sumTr = transactionReceiveService.getSumTrDate(dateTime);
//            Map<String, SumTrDateDTO> maps = sumTr.stream()
//                    .collect(Collectors.toMap(SumTrDateDTO::getDatetime, dto -> dto));
//            entities = dtos.stream().map(item -> {
//                TrDateEntity entity = new TrDateEntity();
//                entity.setId(UUID.randomUUID().toString());
//                entity.setDate(item.getDateTime());
//                SumMapper sumMapper = new SumMapper();
//                sumMapper.setTotal(item.getTransDate().stream()
//                        .mapToLong(TrDateMidsDTO::getTotal)
//                        .sum());
//                sumMapper.setCredit(item.getTransDate().stream()
//                        .mapToLong(TrDateMidsDTO::getCredit)
//                        .sum());
//                sumMapper.setDebit(item.getTransDate().stream()
//                        .mapToLong(TrDateMidsDTO::getDebit)
//                        .sum());
//                sumMapper.setRecon(item.getTransDate().stream()
//                        .mapToLong(TrDateMidsDTO::getRecon)
//                        .sum());
//                sumMapper.setRecCount(item.getTransDate().stream()
//                        .mapToInt(TrDateMidsDTO::getRecCount)
//                        .sum());
//                sumMapper.setDeCount(item.getTransDate().stream()
//                        .mapToInt(TrDateMidsDTO::getDeCount)
//                        .sum());
//                sumMapper.setToCount(item.getTransDate().stream()
//                        .mapToInt(TrDateMidsDTO::getToCount)
//                        .sum());
//                sumMapper.setCreCount(item.getTransDate().stream()
//                        .mapToInt(TrDateMidsDTO::getCreCount)
//                        .sum());
//                SumMapper sumMapper1 = new SumMapper();
//                SumTrDateDTO dto = maps.get(item.getDateTime());
//                sumMapper1.setCreCount(dto.getCreCount());
//                sumMapper1.setRecCount(dto.getRecCount());
//                sumMapper1.setToCount(dto.getToCount());
//                sumMapper1.setDeCount(dto.getDeCount());
//                sumMapper1.setDebit(dto.getDebit());
//                sumMapper1.setCredit(dto.getCredit());
//                sumMapper1.setRecon(dto.getRecon());
//                sumMapper1.setTotal(dto.getTotal());
//                try {
//                    entity.setSum(mapper.writeValueAsString(sumMapper1));
//                    entity.setSumMid(mapper.writeValueAsString(sumMapper));
//                    entity.setMids(mapper.writeValueAsString(item.getTransDate()));
//                    entity.setTids(mapper.writeValueAsString(empty));
//                } catch (JsonProcessingException e) {
//                    throw new RuntimeException(e);
//                }
//                return entity;
//            }).collect(Collectors.toList());
//            trDateService.insertAll(entities);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            logger.error("Error at scheduleExecuteTask: " + e.toString());
//        }
//    }

//    public void syncTrMonth(long from, long to, List<String> bankIds) {
//        try {
//
//            ObjectMapper mapper = new ObjectMapper();
//            List<CustomerInformationDTO> cus = new ArrayList<>();
//            List<String> empty = new ArrayList<>();
//            List<TrMonthEntity> entities = new ArrayList<>();
//            List<ITrDateSumDTO> trDateSumDTOS  = new ArrayList<>();
//            trDateSumDTOS = transactionReceiveService.getTrMonthSync(from, to);
//            List<ITrDateSumDTO> sumTrsBanks = transactionReceiveService.getTrMonthSyncBank(from, to, bankIds);
//            List<TrSumMidDTO> dtos = new ArrayList<>();
//            Map<String, List<ITrDateSumDTO>> roleMaps = trDateSumDTOS.stream()
//                    .collect(Collectors.groupingBy(ITrDateSumDTO::getDatetime));
//            dtos = roleMaps.entrySet().stream()
//                    .map(entry -> {
//                        TrSumMidDTO trSumMidDTO = new TrSumMidDTO();
//                        trSumMidDTO.setDateTime(entry.getKey());
//                        List<TrDateMidsDTO> transDateList = entry.getValue().stream()
//                                .map(this::mapToTrDateMidsDTO)
//                                .collect(Collectors.toList());
//                        trSumMidDTO.setTransDate(transDateList);
//                        return trSumMidDTO;
//                    })
//                    .collect(Collectors.toList());
//            Map<String, List<ITrDateSumDTO>> brMaps = sumTrsBanks.stream()
//                    .collect(Collectors.groupingBy(ITrDateSumDTO::getDatetime));
//            Map<String, List<TrMonthBrDTO>> trSumBr = new HashMap<>();
//            brMaps.forEach((key, value) -> {
//                List<TrMonthBrDTO> trSumBrList = value.stream()
//                        .map(this::mapToTrBrsDTO)
//                        .collect(Collectors.toList());
//                trSumBr.put(key, trSumBrList);
//            });
//            List<String> dateTime = new ArrayList<>();
//            dateTime = dtos.stream().map(TrSumMidDTO::getDateTime).collect(Collectors.toList());
//            List<SumTrDateDTO> sumTr = transactionReceiveService.getSumTrMonth(dateTime);
//            Map<String, SumTrDateDTO> maps = sumTr.stream()
//                    .collect(Collectors.toMap(SumTrDateDTO::getDatetime, dto -> dto));
//            entities = dtos.stream().map(item -> {
//                TrMonthEntity entity = new TrMonthEntity();
//                entity.setId(UUID.randomUUID().toString());
//                entity.setMonth(item.getDateTime());
//                SumMapper sumMapper = new SumMapper();
//                sumMapper.setTotal(item.getTransDate().stream()
//                        .mapToLong(TrDateMidsDTO::getTotal)
//                        .sum());
//                sumMapper.setCredit(item.getTransDate().stream()
//                        .mapToLong(TrDateMidsDTO::getCredit)
//                        .sum());
//                sumMapper.setDebit(item.getTransDate().stream()
//                        .mapToLong(TrDateMidsDTO::getDebit)
//                        .sum());
//                sumMapper.setRecon(item.getTransDate().stream()
//                        .mapToLong(TrDateMidsDTO::getRecon)
//                        .sum());
//                sumMapper.setRecCount(item.getTransDate().stream()
//                        .mapToInt(TrDateMidsDTO::getRecCount)
//                        .sum());
//                sumMapper.setDeCount(item.getTransDate().stream()
//                        .mapToInt(TrDateMidsDTO::getDeCount)
//                        .sum());
//                sumMapper.setToCount(item.getTransDate().stream()
//                        .mapToInt(TrDateMidsDTO::getToCount)
//                        .sum());
//                sumMapper.setCreCount(item.getTransDate().stream()
//                        .mapToInt(TrDateMidsDTO::getCreCount)
//                        .sum());
//                SumMapper sumMapper1 = new SumMapper();
//                SumTrDateDTO dto = maps.get(item.getDateTime());
//                sumMapper1.setCreCount(dto.getCreCount());
//                sumMapper1.setRecCount(dto.getRecCount());
//                sumMapper1.setToCount(dto.getToCount());
//                sumMapper1.setDeCount(dto.getDeCount());
//                sumMapper1.setDebit(dto.getDebit());
//                sumMapper1.setCredit(dto.getCredit());
//                sumMapper1.setRecon(dto.getRecon());
//                sumMapper1.setTotal(dto.getTotal());
//                try {
//                    List<TrMonthBrDTO> dtos1 = trSumBr.get(item.getDateTime());
//                    if (dtos1 != null) {
//                        entity.setTrs(mapper.writeValueAsString(dtos1));
//                    } else {
//                        entity.setTrs(mapper.writeValueAsString(empty));
//                    }
//                    entity.setSum(mapper.writeValueAsString(sumMapper1));
//                    entity.setSumMid(mapper.writeValueAsString(sumMapper));
//                    entity.setMids(mapper.writeValueAsString(item.getTransDate()));
//                    entity.setTids(mapper.writeValueAsString(empty));
//                } catch (JsonProcessingException e) {
//                    throw new RuntimeException(e);
//                }
//                return entity;
//            }).collect(Collectors.toList());
//            trMonthService.insertAll(entities);
//
//        } catch (Exception e) {
//            logger.error("Error at scheduleExecuteTask: " + e.toString());
//        }
//    }
}
