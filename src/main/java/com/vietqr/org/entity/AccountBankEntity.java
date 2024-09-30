package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AccountBank")
public class AccountBankEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "bankShortName")
    private String bankShortName;
    @Column(name = "totalAccounts")
    private Long totalAccounts;
    @Column(name = "linkedAccounts")
    private Long linkedAccounts;
    @Column(name = "unlinkedAccounts")
    private Long unlinkedAccounts;

    public AccountBankEntity() {

    }

    public AccountBankEntity(String id, String bankShortName, Long totalAccounts, Long linkedAccounts, Long unlinkedAccounts) {
        this.id = id;
        this.bankShortName = bankShortName;
        this.totalAccounts = totalAccounts;
        this.linkedAccounts = linkedAccounts;
        this.unlinkedAccounts = unlinkedAccounts;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public Long getTotalAccounts() {
        return totalAccounts;
    }

    public void setTotalAccounts(Long totalAccounts) {
        this.totalAccounts = totalAccounts;
    }

    public Long getLinkedAccounts() {
        return linkedAccounts;
    }

    public void setLinkedAccounts(Long linkedAccounts) {
        this.linkedAccounts = linkedAccounts;
    }

    public Long getUnlinkedAccounts() {
        return unlinkedAccounts;
    }

    public void setUnlinkedAccounts(Long unlinkedAccounts) {
        this.unlinkedAccounts = unlinkedAccounts;
    }


}
