package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "UserRegisterDay")
public class UsDateEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "day")
    private String day;
    @Column(name = "userCount")
    private String userCount;

    @Column(name = "iosPlatform")
    private Long iosPlatform;
    @Column(name = "androidPlatform")
    private Long androidPlatform;
    @Column(name = "webPlatform")
    private Long webPlatform;

    public UsDateEntity(String id) {
        this.id = id;
    }

    public UsDateEntity(String id, String day, String userCount) {
        this.id = id;
        this.day = day;
        this.userCount = userCount;
    }

    public UsDateEntity(String id, String day, String userCount, Long iosPlatform, Long androidPlatform, Long webPlatform) {
        this.id = id;
        this.day = day;
        this.userCount = userCount;
        this.iosPlatform = iosPlatform;
        this.androidPlatform = androidPlatform;
        this.webPlatform = webPlatform;
    }

    public UsDateEntity() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }
    public Long getIosPlatform() {
        return iosPlatform;
    }

    public void setIosPlatform(Long iosPlatform) {
        this.iosPlatform = iosPlatform;
    }

    public Long getAndroidPlatform() {
        return androidPlatform;
    }

    public void setAndroidPlatform(Long androidPlatform) {
        this.androidPlatform = androidPlatform;
    }

    public Long getWebPlatform() {
        return webPlatform;
    }

    public void setWebPlatform(Long webPlatform) {
        this.webPlatform = webPlatform;
    }
}
