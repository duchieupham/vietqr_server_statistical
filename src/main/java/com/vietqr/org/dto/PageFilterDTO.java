package com.vietqr.org.dto;

public class PageFilterDTO {
    private int startIdx;
    private int endIdx;

    public PageFilterDTO() {
    }

    public PageFilterDTO(int startIdx, int endIdx) {
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    public int getStartIdx() {
        return startIdx;
    }

    public void setStartIdx(int startIdx) {
        this.startIdx = startIdx;
    }

    public int getEndIdx() {
        return endIdx;
    }

    public void setEndIdx(int endIdx) {
        this.endIdx = endIdx;
    }
}
