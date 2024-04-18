package com.vietqr.org.dto;

public class TrDateMidsDTO {
    private String mid;
    private long debit;
    private long recon;
    private long total;
    private long credit;
    private int deCount;
    private int toCount;
    private int creCount;
    private int recCount;

    public TrDateMidsDTO() {
    }

    public TrDateMidsDTO(String mid, long debit, long recon, long total,
                         long credit, int deCount, int toCount, int creCount, int recCount) {
        this.mid = mid;
        this.debit = debit;
        this.recon = recon;
        this.total = total;
        this.credit = credit;
        this.deCount = deCount;
        this.toCount = toCount;
        this.creCount = creCount;
        this.recCount = recCount;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
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

    public int getCreCount() {
        return creCount;
    }

    public void setCreCount(int creCount) {
        this.creCount = creCount;
    }

    public int getRecCount() {
        return recCount;
    }

    public void setRecCount(int recCount) {
        this.recCount = recCount;
    }
}
