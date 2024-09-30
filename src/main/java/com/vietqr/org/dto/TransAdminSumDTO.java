package com.vietqr.org.dto;

public class TransAdminSumDTO {
    private int creCountTotal;
    private long creditTotal;
    private int deCountTotal;
    private long debitTotal;
    private int recCountTotal;
    private long recTotal;
    private int totalCount;
    private long totalTrans;

    public TransAdminSumDTO() {
    }

    public TransAdminSumDTO(int creCountTotal, long creditTotal, int deCountTotal,
                            long debitTotal, int recCountTotal, long recTotal, int totalCount, long totalTrans) {
        this.creCountTotal = creCountTotal;
        this.creditTotal = creditTotal;
        this.deCountTotal = deCountTotal;
        this.debitTotal = debitTotal;
        this.recCountTotal = recCountTotal;
        this.recTotal = recTotal;
        this.totalCount = totalCount;
        this.totalTrans = totalTrans;
    }

    public int getCreCountTotal() {
        return creCountTotal;
    }

    public void setCreCountTotal(int creCountTotal) {
        this.creCountTotal = creCountTotal;
    }

    public long getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(long creditTotal) {
        this.creditTotal = creditTotal;
    }

    public int getDeCountTotal() {
        return deCountTotal;
    }

    public void setDeCountTotal(int deCountTotal) {
        this.deCountTotal = deCountTotal;
    }

    public long getDebitTotal() {
        return debitTotal;
    }

    public void setDebitTotal(long debitTotal) {
        this.debitTotal = debitTotal;
    }

    public int getRecCountTotal() {
        return recCountTotal;
    }

    public void setRecCountTotal(int recCountTotal) {
        this.recCountTotal = recCountTotal;
    }

    public long getRecTotal() {
        return recTotal;
    }

    public void setRecTotal(long recTotal) {
        this.recTotal = recTotal;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalTrans() {
        return totalTrans;
    }

    public void setTotalTrans(long totalTrans) {
        this.totalTrans = totalTrans;
    }
}
