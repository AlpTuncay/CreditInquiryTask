package com.kocfinans.creditinquiry.entity;

import javax.persistence.*;

@Entity
@Table(name="inquiry_results")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="inquirer_name")
    private String inquirerName;

    @Column(name="inquirer_last_name")
    private String inquirerLastName;

    @Column(name="inquirer_id")
    private Long inquirerId;

    @Column(name="inquirer_phone_number")
    private String inquirerPhoneNumber;

    @Column(name="inquirer_income")
    private Long inquirerIncome;

    @Column(name="inquiry_result")
    private String inquiryResult;

    @Column(name="credit_limit")
    private Long creditLimit;

    public Long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Long getInquiryId() {
        return id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInquirerPhoneNumber() {
        return inquirerPhoneNumber;
    }

    public void setInquirerPhoneNumber(String inquirerPhoneNumber) {
        this.inquirerPhoneNumber = inquirerPhoneNumber;
    }

    public String getInquirerName() {
        return inquirerName;
    }

    public void setInquirerName(String inquirerName) {
        this.inquirerName = inquirerName;
    }

    public String getInquirerLastName() {
        return inquirerLastName;
    }

    public void setInquirerLastName(String inquirerLastName) {
        this.inquirerLastName = inquirerLastName;
    }

    public Long getInquirerIncome() {
        return inquirerIncome;
    }

    public void setInquirerIncome(Long inquirerIncome) {
        this.inquirerIncome = inquirerIncome;
    }

    public void setInquiryId(Long id) {
        this.id = id;
    }

    public String getInquiryResult() {
        return inquiryResult;
    }

    public void setInquiryResult(String inquiryResult) {
        this.inquiryResult = inquiryResult;
    }

    public Long getInquirerId() {
        return inquirerId;
    }

    public void setInquirerId(Long inquirerId) {
        this.inquirerId = inquirerId;
    }

    @Override
    public String toString(){
        return String.format("Inquiry id: %d, Inquirer ID: %d, Inquirer Name: %s, " +
                        "Inquirer Surname: %s, Phone: %s, Income: %d, Inquiry result: %s, Credit Limit: %d",
                this.id, this.inquirerId, this.inquirerName, this.inquirerLastName, this.inquirerPhoneNumber,
                this.inquirerIncome, this.inquiryResult, this.creditLimit);
    }
}
