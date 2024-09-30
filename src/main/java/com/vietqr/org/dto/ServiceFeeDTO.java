package com.vietqr.org.dto;

import java.util.List;

public class ServiceFeeDTO {
    private String vso;
    private long timePaid;
    private String mid;
    private String name;
    private String nationalId;
    private long fee;
    private List<String> packageFeeCode;
    private List<String> packageFeeName;
    private List<String> platformPackage;
    private int status;

    public ServiceFeeDTO() {
    }

    public String getVso() {
        return vso;
    }

    public void setVso(String vso) {
        this.vso = vso;
    }

    public long getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(long timePaid) {
        this.timePaid = timePaid;
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

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public List<String> getPackageFeeCode() {
        return packageFeeCode;
    }

    public void setPackageFeeCode(List<String> packageFeeCode) {
        this.packageFeeCode = packageFeeCode;
    }

    public List<String> getPackageFeeName() {
        return packageFeeName;
    }

    public void setPackageFeeName(List<String> packageFeeName) {
        this.packageFeeName = packageFeeName;
    }

    public List<String> getPlatformPackage() {
        return platformPackage;
    }

    public void setPlatformPackage(List<String> platformPackage) {
        this.platformPackage = platformPackage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
