package com.bbva.rbvd.dto.insuranceenterprise.commons.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CommonFieldsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String description;
    private BigDecimal amount;
    private String currency;

    private String id;
    private String name;

    private String quotationId;

    private DescriptionDTO period;
    private Boolean isSelected;
    private Boolean isRecommended;
    private String traceId;
    private String saleChannelId;
    private String creationUser;
    private String userAudit;
    private String aap;
    private String externalQuotationId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(String quotationId) {
        this.quotationId = quotationId;
    }

    public DescriptionDTO getPeriod() {
        return period;
    }

    public void setPeriod(DescriptionDTO period) {
        this.period = period;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean selected) {
        isSelected = selected;
    }

    public Boolean getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(Boolean recommended) {
        isRecommended = recommended;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSaleChannelId() {
        return saleChannelId;
    }

    public void setSaleChannelId(String saleChannelId) {
        this.saleChannelId = saleChannelId;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public String getUserAudit() {
        return userAudit;
    }

    public void setUserAudit(String userAudit) {
        this.userAudit = userAudit;
    }

    public String getAap() {
        return aap;
    }

    public void setAap(String aap) {
        this.aap = aap;
    }

    public String getExternalQuotationId() {
        return externalQuotationId;
    }

    public void setExternalQuotationId(String externalQuotationId) {
        this.externalQuotationId = externalQuotationId;
    }
}