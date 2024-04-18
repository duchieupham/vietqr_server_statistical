package com.vietqr.org.mapper;

public class SumMidMapper {
    private int creCount;
    private int deCount;
    private int recCount;
    private int toCount;
    private long credit;
    private long debit;
    private long recon;
    private long total;

    public SumMidMapper() {
    }

    public SumMidMapper(int creCount, int deCount, int recCount, int toCount,
                     long credit, long debit, long recon, long total) {
        this.creCount = creCount;
        this.deCount = deCount;
        this.recCount = recCount;
        this.toCount = toCount;
        this.credit = credit;
        this.debit = debit;
        this.recon = recon;
        this.total = total;
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

    public int getRecCount() {
        return recCount;
    }

    public void setRecCount(int recCount) {
        this.recCount = recCount;
    }

    public int getToCount() {
        return toCount;
    }

    public void setToCount(int toCount) {
        this.toCount = toCount;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
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
}
