package com.bbva.rbvd.dto.insuranceenterprise.commons.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AmountDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double amount;
    private String currency;


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AmountDTO{");
        sb.append("amount=").append(amount);
        sb.append(", currency='").append(currency).append('\'');
        sb.append('}');
        return sb.toString();
    }
}