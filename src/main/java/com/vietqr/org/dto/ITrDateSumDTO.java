package com.vietqr.org.dto;

public interface ITrDateSumDTO {
    String getMid();
    String getDatetime();
    long getDebit();
    long getRecon();
    long getTotal();
    long getCredit();
    int getDeCount();
    int getToCount();
    int getCreCount();
    int getRecCount();
}
