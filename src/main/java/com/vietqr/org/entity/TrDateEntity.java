package com.vietqr.org.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trDate")
public class TrDateEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "mids", columnDefinition = "JSON")
    private String mids;
    @Column(name = "tids", columnDefinition = "JSON")
    private String tids;
    private String date;
    @Column(name = "sum", columnDefinition = "JSON")
    private String sum;
    @Column(name = "sumMid", columnDefinition = "JSON")
    private String sumMid;
    @Column(name = "trs", columnDefinition = "JSON")
    private String trs;

    @Column(name = "brCount", columnDefinition = "JSON")
    private String brCount;

    public TrDateEntity() {
    }

    public TrDateEntity(String id, String mids, String tids,
                        String date, String sum, String sumMid, String trs) {
        this.id = id;
        this.mids = mids;
        this.tids = tids;
        this.date = date;
        this.sum = sum;
        this.sumMid = sumMid;
        this.trs = trs;
    }

    public String getBrCount() {
        return brCount;
    }

    public void setBrCount(String brCount) {
        this.brCount = brCount;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
