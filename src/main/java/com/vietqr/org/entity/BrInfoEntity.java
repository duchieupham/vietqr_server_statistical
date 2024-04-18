package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "br_info")
public class BrInfoEntity {
    // br
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "bank_account")
    private String bankAccount;
    @Column(name = "bank_code")
    private String bankCode;
    @Column(name = "user_bank_name")
    private String userBankName;
    @Column(name = "bank_short_name")
    private String bankShortName;
    @Column(name = "flow")
    private int flow;

    public BrInfoEntity() {
    }

    public BrInfoEntity(String id, String bankAccount, String bankCode, String userBankName, String bankShortName, int flow) {
        this.id = id;
        this.bankAccount = bankAccount;
        this.bankCode = bankCode;
        this.userBankName = userBankName;
        this.bankShortName = bankShortName;
        this.flow = flow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getUserBankName() {
        return userBankName;
    }

    public void setUserBankName(String userBankName) {
        this.userBankName = userBankName;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
}
