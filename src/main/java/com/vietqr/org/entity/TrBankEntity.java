package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tr_bank")
public class TrBankEntity implements Serializable {
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

    @Column(name = "bank_short_name")
    private String bankShortName;

    @Column(name = "total_number_credits")
    private int totalNumberCredits;

    @Column(name = "total_amount_credits")
    private Long totalAmountCredits;

    @Column(name = "total_amount_recon")
    private Long totalAmountRecon;

    @Column(name = "total_number_recon")
    private int totalNumberRecon;

    public TrBankEntity() {}

    public TrBankEntity(String id, int year, String yMonth, String yMonthDay, String bankShortName, int totalNumberCredits
            , Long totalAmountCredits, Long totalAmountRecon, int totalNumberRecon) {
        this.id = id;
        this.year = year;
        this.yMonth = yMonth;
        this.yMonthDay = yMonthDay;
        this.bankShortName = bankShortName;
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

    public String getyMonth() {
        return yMonth;
    }

    public String getyMonthDay() {
        return yMonthDay;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }


    public int getTotalNumberCredits() {
        return totalNumberCredits;
    }

    public void setTotalNumberCredits(int totalNumberCredits) {
        this.totalNumberCredits = totalNumberCredits;
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

    public void setyMonth(String yMonth) {
        this.yMonth = yMonth;
    }

    public void setyMonthDay(String yMonthDay) {
        this.yMonthDay = yMonthDay;
    }

    public int getTotalNumberRecon() {
        return totalNumberRecon;
    }

    public void setTotalNumberRecon(int totalNumberRecon) {
        this.totalNumberRecon = totalNumberRecon;
    }
}
