package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "UserRegisterDay")
public class UserRegisterDayEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "day")
    private String day;
    @Column(name = "userCount")
    private String userCount;

    public UserRegisterDayEntity(String id) {
        this.id = id;
    }

    public UserRegisterDayEntity(String id, String day, String userCount) {
        this.id = id;
        this.day = day;
        this.userCount = userCount;
    }

    public UserRegisterDayEntity() {

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
}
