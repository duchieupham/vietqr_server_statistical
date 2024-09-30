package com.vietqr.org.mapper;

public class SumFeeOfServiceMapper {
    private long transFee;
    private long pendingFee;
    private long completeFee;

    public SumFeeOfServiceMapper() {
    }

    public SumFeeOfServiceMapper(long transFee, long pendingFee, long completeFee) {
        this.transFee = transFee;
        this.pendingFee = pendingFee;
        this.completeFee = completeFee;
    }

    public long getTransFee() {
        return transFee;
    }

    public void setTransFee(long transFee) {
        this.transFee = transFee;
    }

    public long getPendingFee() {
        return pendingFee;
    }

    public void setPendingFee(long pendingFee) {
        this.pendingFee = pendingFee;
    }

    public long getCompleteFee() {
        return completeFee;
    }

    public void setCompleteFee(long completeFee) {
        this.completeFee = completeFee;
    }
}
