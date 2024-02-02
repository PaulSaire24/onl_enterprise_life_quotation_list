package com.bbva.rbvd.dto.insuranceenterprise.commons.dto;

import java.io.Serializable;

public class EmployeesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean areMajorityAge;

    private Long employeesNumber;

    private AmountDTO monthlyPayrollAmount;


    public Boolean getAreMajorityAge() {
        return areMajorityAge;
    }

    public void setAreMajorityAge(Boolean areMajorityAge) {
        this.areMajorityAge = areMajorityAge;
    }

    public Long getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(Long employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public AmountDTO getMonthlyPayrollAmount() {
        return monthlyPayrollAmount;
    }

    public void setMonthlyPayrollAmount(AmountDTO monthlyPayrollAmount) {
        this.monthlyPayrollAmount = monthlyPayrollAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmployeesDTO{");
        sb.append("areMajorityAge=").append(areMajorityAge);
        sb.append(", employeesNumber=").append(employeesNumber);
        sb.append(", monthlyPayrollAmount=").append(monthlyPayrollAmount);
        sb.append('}');
        return sb.toString();
    }
}
