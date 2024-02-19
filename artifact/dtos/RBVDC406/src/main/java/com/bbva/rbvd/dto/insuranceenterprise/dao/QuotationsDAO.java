package com.bbva.rbvd.dto.insuranceenterprise.dao;


import java.math.BigDecimal;

public class QuotationsDAO {

    private String policyQuotaInternalId;
    private String quoteDate;
    private String quoteStatus;
    private String financingStartDate;
    private String financingEndDate;
    private BigDecimal totalAmount;
    private String currencyId;
    private Integer numberPayments;
    private BigDecimal premiumAmount;
    private String coStatus;
    private String productType;
    private String productDesc;
    private String modalityType;
    private String modalityName;
    private String paymentFrequencyName;
    private Integer paymentFrequencyId;


    public String getPolicyQuotaInternalId() {
        return policyQuotaInternalId;
    }

    public void setPolicyQuotaInternalId(String policyQuotaInternalId) {
        this.policyQuotaInternalId = policyQuotaInternalId;
    }

    public String getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(String quoteDate) {
        this.quoteDate = quoteDate;
    }

    public String getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(String quoteStatus) {
        this.quoteStatus = quoteStatus;
    }


    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(BigDecimal premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getNumberPayments() {
        return numberPayments;
    }

    public void setNumberPayments(Integer numberPayments) {
        this.numberPayments = numberPayments;
    }



    public String getCoStatus() {
        return coStatus;
    }

    public void setCoStatus(String coStatus) {
        this.coStatus = coStatus;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getModalityType() {
        return modalityType;
    }

    public void setModalityType(String modalityType) {
        this.modalityType = modalityType;
    }

    public String getModalityName() {
        return modalityName;
    }

    public void setModalityName(String modalityName) {
        this.modalityName = modalityName;
    }

    public String getPaymentFrequencyName() {
        return paymentFrequencyName;
    }

    public void setPaymentFrequencyName(String paymentFrequencyName) {
        this.paymentFrequencyName = paymentFrequencyName;
    }

    public Integer getPaymentFrequencyId() {
        return paymentFrequencyId;
    }

    public void setPaymentFrequencyId(Integer paymentFrequencyId) {
        this.paymentFrequencyId = paymentFrequencyId;
    }

    public String getFinancingStartDate() {
        return financingStartDate;
    }

    public void setFinancingStartDate(String financingStartDate) {
        this.financingStartDate = financingStartDate;
    }

    public String getFinancingEndDate() {
        return financingEndDate;
    }

    public void setFinancingEndDate(String financingEndDate) {
        this.financingEndDate = financingEndDate;
    }

    @Override
    public String toString() {
        return "QuotationsDAO{" +
                "policyQuotaInternalId='" + policyQuotaInternalId + '\'' +
                ", quoteDate=" + quoteDate +
                ", quoteStatus='" + quoteStatus + '\'' +
                ", financingStartDate=" + financingStartDate +
                ", financingEndDate=" + financingEndDate +
                ", totalAmount=" + totalAmount +
                ", currencyId='" + currencyId + '\'' +
                ", numberPayments=" + numberPayments +
                ", premiumAmount=" + premiumAmount +
                ", coStatus='" + coStatus + '\'' +
                ", productType='" + productType + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", modalityType='" + modalityType + '\'' +
                ", modalityName='" + modalityName + '\'' +
                ", paymentFrequencyName='" + paymentFrequencyName + '\'' +
                ", paymentFrequencyId=" + paymentFrequencyId +
                '}';
    }
}
