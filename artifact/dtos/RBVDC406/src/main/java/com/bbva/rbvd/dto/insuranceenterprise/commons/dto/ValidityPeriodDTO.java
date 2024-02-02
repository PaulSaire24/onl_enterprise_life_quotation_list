package com.bbva.rbvd.dto.insuranceenterprise.commons.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ValidityPeriodDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private LocalDate startDate;
    private LocalDate endDate;


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ValidityPeriodDTO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}