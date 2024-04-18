package com.vietqr.org.dto;

public class TransAdminStatisticDTO {
    private String time;
    private long credit;
    private int creCount;
    private int deCount;
    private int reCount;
    private int toCount;
    private long debit;
    private long recon;
    private long total;

    public TransAdminStatisticDTO() {
    }

    public TransAdminStatisticDTO(String time, long credit, int creCount, int deCount,
                                  int toCount, long debit, long recon, long total) {
        this.time = time;
        this.credit = credit;
        this.creCount = creCount;
        this.deCount = deCount;
        this.toCount = toCount;
        this.debit = debit;
        this.recon = recon;
        this.total = total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public int getCreCount() {
        return creCount;
    }

    public void setCreCount(int creCount) {
        this.creCount = creCount;
    }

    public int getDeCount() {
        return deCount;
    }

    public void setDeCount(int deCount) {
        this.deCount = deCount;
    }

    public int getToCount() {
        return toCount;
    }

    public void setToCount(int toCount) {
        this.toCount = toCount;
    }

    public long getDebit() {
        return debit;
    }

    public void setDebit(long debit) {
        this.debit = debit;
    }

    public long getRecon() {
        return recon;
    }

    public void setRecon(long recon) {
        this.recon = recon;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getReCount() {
        return reCount;
    }

    public void setReCount(int reCount) {
        this.reCount = reCount;
    }
}
