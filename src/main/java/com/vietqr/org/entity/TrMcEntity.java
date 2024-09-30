package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tr_mc")
public class TrMcEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "year")
    private int year;

    @Column(name = "y_month")
    private String yMonth;

    @Column(name = "y_month_day")
    private String yMonthDay;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "total_number_credits")
    private int totalNumberCredits;

    @Column(name = "total_amount_credits")
    private Long totalAmountCredits;

    @Column(name = "total_amount_recon")
    private Long totalAmountRecon;

    @Column(name = "total_number_recon")
    private int totalNumberRecon;

    public TrMcEntity() {}

    public TrMcEntity(String id, int year, String yMonth, String yMonthDay, String merchantName,
                      int totalNumberCredits, Long totalAmountCredits, Long totalAmountRecon, int totalNumberRecon) {
        this.id = id;
        this.year = year;
        this.yMonth = yMonth;
        this.yMonthDay = yMonthDay;
        this.merchantName = merchantName;
        this.totalNumberCredits = totalNumberCredits;
        this.totalAmountCredits = totalAmountCredits;
        this.totalAmountRecon = totalAmountRecon;
        this.totalNumberRecon = totalNumberRecon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getTotalAmountCredits() {
        return totalAmountCredits;
    }

    public void setTotalAmountCredits(Long totalAmountCredits) {
        this.totalAmountCredits = totalAmountCredits;
    }

    public Long getTotalAmountRecon() {
        return totalAmountRecon;
    }

    public void setTotalAmountRecon(Long totalAmountRecon) {
        this.totalAmountRecon = totalAmountRecon;
    }

    public String getyMonth() {
        return yMonth;
    }

    public void setyMonth(String yMonth) {
        this.yMonth = yMonth;
    }

    public String getyMonthDay() {
        return yMonthDay;
    }

    public void setyMonthDay(String yMonthDay) {
        this.yMonthDay = yMonthDay;
    }

    public int getTotalNumberCredits() {
        return totalNumberCredits;
    }

    public void setTotalNumberCredits(int totalNumberCredits) {
        this.totalNumberCredits = totalNumberCredits;
    }

    public int getTotalNumberRecon() {
        return totalNumberRecon;
    }

    public void setTotalNumberRecon(int totalNumberRecon) {
        this.totalNumberRecon = totalNumberRecon;
    }
}
