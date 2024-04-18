package com.vietqr.org.dto;

public interface ICustomerBankFeeDTO {
    String getBankId();

    String getCustomerSyncId();
    
    String getShortName();

    Long getActiveFee();

    Long getAnnualFee();

    Long getTransFee();

    Double getPercentFee();

    Integer getCountingTransType();

    Double getVat();

}
