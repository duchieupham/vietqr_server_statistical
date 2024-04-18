package com.vietqr.org.dto;

public class MidStatisticDTO {
    private String mid;
    private String name;
    private String vso;
    private String nationalId;
    private long credit;
    private int creCount;
    private int deCount;
    private int reCount;
    private int toCount;
    private long debit;
    private long recon;
    private long total;

    public MidStatisticDTO() {
    }

    public MidStatisticDTO(String mid, String name, String vso, String nationalId,
                           long credit, int creCount, int deCount, int reCount,
                           int toCount, long debit, long recon, long total) {
        this.mid = mid;
        this.name = name;
        this.vso = vso;
        this.nationalId = nationalId;
        this.credit = credit;
        this.creCount = creCount;
        this.deCount = deCount;
        this.reCount = reCount;
        this.toCount = toCount;
        this.debit = debit;
        this.recon = recon;
        this.total = total;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVso() {
        return vso;
    }

    public void setVso(String vso) {
        this.vso = vso;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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

    public int getReCount() {
        return reCount;
    }

    public void setReCount(int reCount) {
        this.reCount = reCount;
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
}
