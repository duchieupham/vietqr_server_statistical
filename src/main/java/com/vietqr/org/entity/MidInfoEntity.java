package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mid_info")
public class MidInfoEntity {
    // mid
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "tids", columnDefinition = "JSON")
    private String tids;
    @Column(name = "service_type", columnDefinition = "JSON")
    private String serviceType;
    @Column(name = "info", columnDefinition = "JSON")
    private String info;

    @Column(name = "banks", columnDefinition = "JSON")
    private String banks;

    public MidInfoEntity() {
    }

    public MidInfoEntity(String id, String tids, String serviceType, String info, String banks) {
        this.id = id;
        this.tids = tids;
        this.serviceType = serviceType;
        this.info = info;
        this.banks = banks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTids() {
        return tids;
    }

    public void setTids(String tids) {
        this.tids = tids;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getBanks() {
        return banks;
    }

    public void setBanks(String banks) {
        this.banks = banks;
    }
}
