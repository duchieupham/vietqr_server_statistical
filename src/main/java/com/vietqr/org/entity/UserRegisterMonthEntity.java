package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "UserRegisterMonth")
public class UserRegisterMonthEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "month")
    private String month;
    @Column(name = "userCount")
    private String userCount;

    public UserRegisterMonthEntity() {
    }

    public UserRegisterMonthEntity(String id, String month, String userCount) {
        this.id = id;
        this.month = month;
        this.userCount = userCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }
}
