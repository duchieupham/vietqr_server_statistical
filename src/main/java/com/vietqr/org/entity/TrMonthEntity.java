package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tr_month")
public class TrMonthEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "mids", columnDefinition = "JSON")
    private String mids;
    @Column(name = "tids", columnDefinition = "JSON")
    private String tids;
    @Column(name = "trs", columnDefinition = "JSON")
    private String trs;
    @Column(name = "month")
    private String month;
    @Column(name = "sum", columnDefinition = "JSON")
    private String sum;
    @Column(name = "sum_mid", columnDefinition = "JSON")
    private String sumMid;

    public TrMonthEntity() {
    }

    public TrMonthEntity(String id, String mids, String tids, String trs, String month, String sum, String sumMid) {
        this.id = id;
        this.mids = mids;
        this.tids = tids;
        this.trs = trs;
        this.month = month;
        this.sum = sum;
        this.sumMid = sumMid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMids() {
        return mids;
    }

    public void setMids(String mids) {
        this.mids = mids;
    }

    public String getTids() {
        return tids;
    }

    public void setTids(String tids) {
        this.tids = tids;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getSumMid() {
        return sumMid;
    }

    public void setSumMid(String sumMid) {
        this.sumMid = sumMid;
    }

    public String getTrs() {
        return trs;
    }

    public void setTrs(String trs) {
        this.trs = trs;
    }
}
