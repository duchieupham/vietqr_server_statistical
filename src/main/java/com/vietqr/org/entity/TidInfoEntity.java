package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TidInfo")
public class TidInfoEntity {
    // tid
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "mid", columnDefinition = "JSON")
    private String mid;
    @Column(name = "count", columnDefinition = "JSON")
    private String count;
    @Column(name = "info", columnDefinition = "JSON")
    private String info;

    public TidInfoEntity() {
    }

    public TidInfoEntity(String id, String mid, String count, String info) {
        this.id = id;
        this.mid = mid;
        this.count = count;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
