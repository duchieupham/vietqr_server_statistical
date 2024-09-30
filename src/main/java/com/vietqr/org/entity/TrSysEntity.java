package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tr_sys")
public class TrSysEntity implements Serializable {
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


    @Column(name = "total_number_credits")
    private int totalNumberCredits;

    @Column(name = "total_amount_credits")
    private Long totalAmountCredits;

    @Column(name = "total_amount_recon")
    private Long totalAmountRecon;

    @Column(name = "total_number_recon")
    private int totalNumberRecon;

    @Column(name = "total_amount_without_recon")
    private Long totalAmountWithoutRecon;

    @Column(name = "total_number_without_recon")
    private int totalNumberWithoutRecon;

    @Column(name = "total_number_push_error")
    private int totalNumberPushError;

    @Column(name = "total_amount_push_error_sum")
    private Long totalAmountPushError;


    public TrSysEntity() {}

    public TrSysEntity(String id, int year, String yMonth, String yMonthDay, int totalNumberCredits, Long totalAmountCredits, Long totalAmountRecon, int totalNumberRecon,
                       Long totalAmountWithoutRecon, int totalNumberWithoutRecon, int totalNumberPushError, Long totalAmountPushError) {
        this.id = id;
        this.year = year;
        this.yMonth = yMonth;
        this.yMonthDay = yMonthDay;
        this.totalNumberCredits = totalNumberCredits;
        this.totalAmountCredits = totalAmountCredits;
        this.totalAmountRecon = totalAmountRecon;
        this.totalNumberRecon = totalNumberRecon;
        this.totalAmountWithoutRecon = totalAmountWithoutRecon;
        this.totalNumberWithoutRecon = totalNumberWithoutRecon;
        this.totalNumberPushError = totalNumberPushError;
        this.totalAmountPushError = totalAmountPushError;
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

    public int getTotalNumberRecon() {
        return totalNumberRecon;
    }

    public void setTotalNumberRecon(int totalNumberRecon) {
        this.totalNumberRecon = totalNumberRecon;
    }

    public Long getTotalAmountWithoutRecon() {
        return totalAmountWithoutRecon;
    }

    public void setTotalAmountWithoutRecon(Long totalAmountWithoutRecon) {
        this.totalAmountWithoutRecon = totalAmountWithoutRecon;
    }

    public int getTotalNumberWithoutRecon() {
        return totalNumberWithoutRecon;
    }

    public void setTotalNumberWithoutRecon(int totalNumberWithoutRecon) {
        this.totalNumberWithoutRecon = totalNumberWithoutRecon;
    }

    public int getTotalNumberPushError() {
        return totalNumberPushError;
    }

    public void setTotalNumberPushError(int totalNumberPushError) {
        this.totalNumberPushError = totalNumberPushError;
    }

    public Long getTotalAmountPushError() {
        return totalAmountPushError;
    }

    public void setTotalAmountPushError(Long totalAmountPushError) {
        this.totalAmountPushError = totalAmountPushError;
    }
}