package com.vietqr.org.dto;

import java.util.List;

public class TrSumMidDTO {
    private String dateTime;
    private List<TrDateMidsDTO> transDate;

    public TrSumMidDTO() {
    }

    public TrSumMidDTO(String dateTime, List<TrDateMidsDTO> transDate) {
        this.dateTime = dateTime;
        this.transDate = transDate;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<TrDateMidsDTO> getTransDate() {
        return transDate;
    }

    public void setTransDate(List<TrDateMidsDTO> transDate) {
        this.transDate = transDate;
    }
}
