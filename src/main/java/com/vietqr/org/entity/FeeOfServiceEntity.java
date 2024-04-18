package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "fee_of_service")
public class FeeOfServiceEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "month")
    private String month;

    @Column(name = "fees", columnDefinition = "JSON")
    private String fees;

    @Column(name = "sum", columnDefinition = "JSON")
    private String sum;

    public FeeOfServiceEntity() {
    }

    public FeeOfServiceEntity(String id, String month, String fees, String sum) {
        this.id = id;
        this.month = month;
        this.fees = fees;
        this.sum = sum;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
