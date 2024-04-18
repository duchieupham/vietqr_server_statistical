package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "AccountBankFee")
public class AccountBankFeeEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "bankId")
    private String bankId;

    @Column(name = "shortName")
    private String shortName;

    // fee for active service
    @Column(name = "activeFee")
    private Long activeFee;

    // (monthly fee, but collect money by 3/6/9/12 months)
    @Column(name = "annualFee")
    private Long annualFee;

    // (fee per transaction)
    @Column(name = "transFee")
    private Long transFee;

    // (fee based on total successful amount)
    @Column(name = "percentFee")
    private Double percentFee;

    @Column(name = "countingTransType")
    private Integer countingTransType;

    @Column(name = "vat")
    private Double vat;

    public AccountBankFeeEntity() {
        super();
    }

    public AccountBankFeeEntity(String id, String bankId, String shortName, Long activeFee,
                                Long annualFee, Long transFee, Double percentFee, Integer countingTransType, Double vat) {
        this.id = id;
        this.bankId = bankId;
        this.shortName = shortName;
        this.activeFee = activeFee;
        this.annualFee = annualFee;
        this.transFee = transFee;
        this.percentFee = percentFee;
        this.countingTransType = countingTransType;
        this.vat = vat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getActiveFee() {
        return activeFee;
    }

    public void setActiveFee(Long activeFee) {
        this.activeFee = activeFee;
    }

    public Long getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(Long annualFee) {
        this.annualFee = annualFee;
    }

    public Long getTransFee() {
        return transFee;
    }

    public void setTransFee(Long transFee) {
        this.transFee = transFee;
    }

    public Double getPercentFee() {
        return percentFee;
    }

    public void setPercentFee(Double percentFee) {
        this.percentFee = percentFee;
    }

    public Integer getCountingTransType() {
        return countingTransType;
    }

    public void setCountingTransType(Integer countingTransType) {
        this.countingTransType = countingTransType;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

}
