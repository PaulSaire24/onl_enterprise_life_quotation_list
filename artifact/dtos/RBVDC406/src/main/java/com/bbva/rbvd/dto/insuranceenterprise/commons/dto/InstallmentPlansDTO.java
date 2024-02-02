package com.bbva.rbvd.dto.insuranceenterprise.commons.dto;

public class InstallmentPlansDTO extends CommonFieldsDTO {

    private Long paymentsTotalNumber;
    private AmountDTO paymentAmount;

    public Long getPaymentsTotalNumber() {
        return paymentsTotalNumber;
    }

    public void setPaymentsTotalNumber(Long paymentsTotalNumber) {
        this.paymentsTotalNumber = paymentsTotalNumber;
    }

    public AmountDTO getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(AmountDTO paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return "InstallmentPlansDTO{" +
                "paymentsTotalNumber=" + paymentsTotalNumber +
                ", paymentAmount=" + paymentAmount +
                ", period=" + this.getPeriod() +
                '}';
    }
}