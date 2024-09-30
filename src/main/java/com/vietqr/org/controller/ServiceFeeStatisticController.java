package com.vietqr.org.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietqr.org.dto.*;
import com.vietqr.org.entity.FeeOfServiceEntity;
import com.vietqr.org.mapper.*;
import com.vietqr.org.service.*;
import com.vietqr.org.util.EnvironmentUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ServiceFeeStatisticController {
    private static final Logger logger = Logger.getLogger(ServiceFeeStatisticController.class);

    @Autowired
    private FeeOfAnnualService feeOfAnnualService;

    @Autowired
    private TrMonthService trMonthService;

    @Autowired
    private MidInfoService midInfoService;

    @Autowired
    private AccountCustomerBankService accountCustomerBankService;

    @Autowired
    private FeeOfServiceService feeOfServiceService;


    @GetMapping("service-fee/statistic")
    public ResponseEntity<PageResultDTO> getServiceFeeStatistic(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value ="filterBy") int filterBy,
            @RequestParam(value = "time") String time,
            @RequestParam(value = "value") String value) {
        PageResultDTO result = new PageResultDTO();
        HttpStatus httpStatus = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            PageDTO pageDTO = new PageDTO();
            String[] parts = time.split("-");
            time = String.join("", parts);
            pageDTO.setSize(size);
            pageDTO.setPage(page);
            IFeeOfServiceDTO dto = feeOfServiceService.getFeeOfServiceByMonth(time);
            DataDTO data = new DataDTO(new SumFeeOfServiceMapper(0, 0, 0));
            result.setData(data);
            int offset = (page - 1) * size;
            int totalElement = 0;
            if (dto != null) {
                SumFeeOfServiceMapper sumFeeOfServiceMapper = new SumFeeOfServiceMapper();
                List<ServiceFeeDTO> items = new ArrayList<>();
                List<ServiceFeeDTO> itemNoFilters = new ArrayList<>();
                List<ServiceFeeDTO> itemWithPaging = new ArrayList<>();
                try {
                    sumFeeOfServiceMapper = mapper.readValue(dto.getSum(), SumFeeOfServiceMapper.class);
                    itemNoFilters = mapper.readValue(dto.getFees(), new TypeReference<List<ServiceFeeDTO>>() {});
                    int platform = Integer.parseInt(value);
                    switch (platform) {
                        // Phần mềm ViệtQR
                        case 0:
                            items = itemNoFilters.stream().filter(item -> item.getPackageFeeName()
                                            .contains(EnvironmentUtil.getDefaultServiceFee()))
                                    .collect(Collectors.toList());
                            break;
                        case 9:
                        default:
                            items = itemNoFilters;
                            break;
                    }
                    totalElement = items.size();
                    PageFilterDTO pageFilterDTO = getPageFIlterDTO(totalElement, size, page);
                    if (pageFilterDTO.getStartIdx() == -1) {
                        itemWithPaging = new ArrayList<>();
                    } else {
                        itemWithPaging = items.subList(pageFilterDTO.getStartIdx(),
                                pageFilterDTO.getEndIdx());
                    }
                    totalElement = items.size();
                    data.setItems(itemWithPaging);
                    if (sumFeeOfServiceMapper != null) {
                        data.setExtraData(sumFeeOfServiceMapper);
                    }
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                pageDTO.setTotalElement(totalElement);
                pageDTO.setTotalPage(totalElement % size == 0 ?
                        totalElement / size : totalElement / size + 1);
                result.setData(data);
                result.setMetadata(pageDTO);
            }
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            logger.error("ServiceFeeStatisticController: ERROR: getServiceFeeStatistic: "
            + e.getMessage() + " at: " + System.currentTimeMillis());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result, httpStatus);
    }

    @PostMapping("service-fee/statistic")
    public ResponseEntity<ResponseMessageDTO> postServiceFeeStatistic(
            @RequestParam(value = "month") String month) {
        ResponseMessageDTO result = null;
        HttpStatus httpStatus = null;
        try {
            String[] parts = month.split("-");
            month = String.join("", parts);
            ObjectMapper mapper = new ObjectMapper();
            ITransMonthDTO dto = trMonthService.getTranMidStatistic(month);
            if (dto != null) {
                SumFeeOfServiceMapper sumFeeOfServiceMapper = new SumFeeOfServiceMapper();
                List<FeesServiceMapper> feesServiceMappers = new ArrayList<>();
                List<MidMapper> midMappers = new ArrayList<>();
                try {
                    midMappers = mapper.readValue(dto.getMids(), new TypeReference<List<MidMapper>>() {});
                } catch (IOException ignored) {}
                List<String> mids = midMappers.stream()
                        .map(MidMapper::getMid)
                        .collect(Collectors.toList());
                List<ICustomerBankFeeDTO> customerBankFeeDTOS = accountCustomerBankService
                        .getCustomerBankFeeByMids(mids);
                Map<String, List<ICustomerBankFeeDTO>> cusMaps = customerBankFeeDTOS.stream()
                        .collect(Collectors.groupingBy(ICustomerBankFeeDTO::getCustomerSyncId));
                String brBanks = trMonthService.getTrMonthBr(month);
                List<TrMonthBrMapper> banks = mapper.readValue(brBanks, new TypeReference<List<TrMonthBrMapper>>() {});
                Map<String, TrMonthBrMapper> trMonthMaps = banks.stream()
                        .collect(Collectors.toMap(TrMonthBrMapper::getBrId,
                                Function.identity(),
                                (existing, replacement) -> existing));
                long feeTotal = 0L;
                for (String mid: mids) {
                    List<String> packageFeeCode = new ArrayList<>();
                    List<String> packageFeeName = new ArrayList<>();
                    List<String> platformFee = new ArrayList<>();
                    long totalAmount = 0L;
                    packageFeeName.add(EnvironmentUtil.getDefaultServiceFee());
                    List<ICustomerBankFeeDTO> cusDto = cusMaps.getOrDefault(mid, new ArrayList<>());
                    for (ICustomerBankFeeDTO feeDTO: cusDto) {
                        int isTotal = feeDTO.getCountingTransType();
                        long feeCredit = 0;
                        long feeCreCount = 0;
                        long totalBeforeTax = 0;
                        TrMonthBrMapper dtoBank = trMonthMaps.get(feeDTO.getBankId());
                        if (dtoBank != null) {
                            switch (isTotal) {
                                // tất cả giao dịch đến
                                case 0:
                                    long credit = dtoBank.getCredit();
                                    int creCount = dtoBank.getCreCount();
                                    feeCredit = Math
                                            .round(feeDTO.getPercentFee() * credit / 100);
                                    feeCreCount = creCount * feeDTO.getTransFee();
                                    totalBeforeTax = Math
                                            .round(feeCredit + feeCreCount -
                                                    feeDTO.getAnnualFee());
                                    if (totalBeforeTax < 0) {
                                        totalBeforeTax = 0;
                                    }
                                    totalAmount += Math
                                            .round(totalBeforeTax *  (1 + feeDTO.getVat() / 100));
                                    break;
                                case 1:
                                    long recon = dtoBank.getRecon();
                                    long reCount = dtoBank.getRecCount();
                                    feeCredit = Math
                                            .round(feeDTO.getPercentFee() * recon / 100);
                                    feeCreCount = reCount * feeDTO.getTransFee();
                                    totalBeforeTax = Math
                                            .round(feeCredit + feeCreCount -
                                                    feeDTO.getAnnualFee());
                                    if (totalBeforeTax < 0) {
                                        totalBeforeTax = 0;
                                    }
                                    totalAmount += Math
                                            .round(totalBeforeTax *  (1 + feeDTO.getVat() / 100));
                                    break;
                                default:
                                    break;
                            }
                        }
                        packageFeeCode.add(feeDTO.getShortName());
                    }
                    // get midInfo:
                    IMidInfoDTO info = midInfoService.getInfoById(mid);
                    FeesServiceMapper fee = new FeesServiceMapper();
                    fee.setMid(info.getId());
                    MidInfoMapper midInfoMapper = mapper.readValue(info.getInfo(), MidInfoMapper.class);
                    fee.setName(midInfoMapper.getName());
                    fee.setNationalId(midInfoMapper.getNationalId());
                    fee.setVso(midInfoMapper.getVso());
                    fee.setTimePaid(0);
                    fee.setStatus(0);
                    fee.setFee(totalAmount);
                    feeTotal += totalAmount;
                    platformFee = mapper.readValue(info.getServiceType(), new TypeReference<List<String>>() {});
                    fee.setPackageFeeName(packageFeeName);
                    fee.setPackageFeeCode(packageFeeCode);
                    fee.setPlatformPackage(platformFee);
                    feesServiceMappers.add(fee);
                }
                sumFeeOfServiceMapper.setCompleteFee(0L);
                sumFeeOfServiceMapper.setPendingFee(feeTotal);
                sumFeeOfServiceMapper.setTransFee(feeTotal);
                FeeOfServiceEntity entity = new FeeOfServiceEntity();
                entity.setId(UUID.randomUUID().toString());
                entity.setMonth(month);
                entity.setSum(mapper.writeValueAsString(sumFeeOfServiceMapper));
                entity.setFees(mapper.writeValueAsString(feesServiceMappers));
                feeOfServiceService.insert(entity);
            }
            result = new ResponseMessageDTO("SUCCESS", "");
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            logger.error("ServiceFeeStatisticController: ERROR: postServiceFeeStatistic: "
                    + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO("FAILED", "E05");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result, httpStatus);
    }

    private PageFilterDTO getPageFIlterDTO(int totalElements, int size, int page) {
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, totalElements);
        if (startIndex >= totalElements) {
            startIndex = -1;
            endIndex = -1;
        }
        return new PageFilterDTO(startIndex, endIndex);
    }
}
