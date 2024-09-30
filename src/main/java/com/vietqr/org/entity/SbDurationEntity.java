package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sb_duration")
public class SbDurationEntity implements Serializable {
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

    @Column(name = "nearing_expiration_count")
    private int nearingExpirationCount;

    @Column(name = "expired_count")
    private int expiredCount;

    public SbDurationEntity() {}


    public SbDurationEntity(String id, int year, String yMonth, String yMonthDay, int nearingExpirationCount, int expiredCount) {
        this.id = id;
        this.year = year;
        this.yMonth = yMonth;
        this.yMonthDay = yMonthDay;
        this.nearingExpirationCount = nearingExpirationCount;
        this.expiredCount = expiredCount;
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

    public int getNearingExpirationCount() {
        return nearingExpirationCount;
    }

    public void setNearingExpirationCount(int nearingExpirationCount) {
        this.nearingExpirationCount = nearingExpirationCount;
    }

    public int getExpiredCount() {
        return expiredCount;
    }

    public void setExpiredCount(int expiredCount) {
        this.expiredCount = expiredCount;
    }


}
