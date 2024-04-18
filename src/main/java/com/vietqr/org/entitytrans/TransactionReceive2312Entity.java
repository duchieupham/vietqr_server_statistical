package com.vietqr.org.entitytrans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TransactionReceive2312")
public class TransactionReceive2312Entity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "bankAccount")
    private String bankAccount;

    @Column(name = "bankId")
    private String bankId;

    @Column(name = "content")
    private String content;

    @Column(name = "amount")
    private long amount;

    @Column(name = "time")
    private long time;

    @Column(name = "timePaid")
    private long timePaid;

    // ref id from transaction_bank/(transaction_sms:removed)
    @Column(name = "refId")
    private String refId;

    // transaction type: From Bank/(SMS:removed)
    @Column(name = "type")
    private int type;

    // transaction status: PAID/UNPAID
    @Column(name = "status")
    private int status;

    @Column(name = "traceId")
    private String traceId;

    @Column(name = "transType")
    private String transType;

    @Column(name = "referenceNumber")
    private String referenceNumber;

    // for customers
    @Column(name = "orderId")
    private String orderId;

    @Column(name = "sign")
    private String sign;

    @Column(name = "customerBankAccount")
    private String customerBankAccount;

    @Column(name = "customerBankCode")
    private String customerBankCode;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "terminalCode")
    private String terminalCode;

    @Column(name = "qrCode")
    private String qrCode;

    @Column(name = "userId")
    private String userId;

    @Column(name = "note")
    private String note;

    @Column(name = "transStatus")
    private Integer transStatus;

    @Column(name = "urlLink")
    private String urlLink;

    public TransactionReceive2312Entity() {
        super();
    }

    public TransactionReceive2312Entity(String id, String bankAccount, String bankId, String content, long amount,
                                    long time, String refId, int type, int status, String traceId, String transType, String referenceNumber,
                                    String sign, String orderId, String terminalCode, String qrCode, String userId, String note) {
        this.id = id;
        this.bankAccount = bankAccount;
        this.bankId = bankId;
        this.content = content;
        this.amount = amount;
        this.time = time;
        this.refId = refId;
        this.type = type;
        this.status = status;
        this.traceId = traceId;
        this.transType = transType;
        this.referenceNumber = referenceNumber;
        this.sign = sign;
        this.orderId = orderId;
        this.terminalCode = terminalCode;
        this.qrCode = qrCode;
        this.userId = userId;
        this.note = note;
    }

    public TransactionReceive2312Entity(String id, String bankAccount, String bankId, String content, long amount,
                                    long time, String refId, int type, int status, String traceId, String transType, String referenceNumber,
                                    String orderId, String sign, String customerBankAccount, String customerBankCode, String customerName,
                                    String terminalCode, String qrCode, String userId, String note) {
        this.id = id;
        this.bankAccount = bankAccount;
        this.bankId = bankId;
        this.content = content;
        this.amount = amount;
        this.time = time;
        this.refId = refId;
        this.type = type;
        this.status = status;
        this.traceId = traceId;
        this.transType = transType;
        this.referenceNumber = referenceNumber;
        this.orderId = orderId;
        this.sign = sign;
        this.customerBankAccount = customerBankAccount;
        this.customerBankCode = customerBankCode;
        this.customerName = customerName;
        this.terminalCode = terminalCode;
        this.qrCode = qrCode;
        this.userId = userId;
        this.note = note;
    }

    public TransactionReceive2312Entity(String id, String bankAccount, String bankId, String content, long amount,
                                    long time, long timePaid, String refId, int type, int status, String traceId, String transType,
                                    String referenceNumber, String orderId, String sign, String customerBankAccount, String customerBankCode,
                                    String customerName, String terminalCode, String qrCode, String userId, String note) {
        this.id = id;
        this.bankAccount = bankAccount;
        this.bankId = bankId;
        this.content = content;
        this.amount = amount;
        this.time = time;
        this.timePaid = timePaid;
        this.refId = refId;
        this.type = type;
        this.status = status;
        this.traceId = traceId;
        this.transType = transType;
        this.referenceNumber = referenceNumber;
        this.orderId = orderId;
        this.sign = sign;
        this.customerBankAccount = customerBankAccount;
        this.customerBankCode = customerBankCode;
        this.customerName = customerName;
        this.terminalCode = terminalCode;
        this.qrCode = qrCode;
        this.userId = userId;
        this.note = note;
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

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCustomerBankAccount() {
        return customerBankAccount;
    }

    public void setCustomerBankAccount(String customerBankAccount) {
        this.customerBankAccount = customerBankAccount;
    }

    public String getCustomerBankCode() {
        return customerBankCode;
    }

    public void setCustomerBankCode(String customerBankCode) {
        this.customerBankCode = customerBankCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(long timePaid) {
        this.timePaid = timePaid;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(Integer transStatus) {
        this.transStatus = transStatus;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }
}
