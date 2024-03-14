package com.bbva.rbvd.dto.insuranceenterprise.commons.dto;

import java.util.List;

public class PlanDTO extends  CommonFieldsDTO{

    private AmountDTO totalInstallment;
    private List<InstallmentPlansDTO> installmentPlans;
    private List<CoverageDTO> coverages;
    private List<DescriptionDTO> exclusions;
    private List<DescriptionDTO> benefits;


    public AmountDTO getTotalInstallment() {
        return totalInstallment;
    }

    public void setTotalInstallment(AmountDTO totalInstallment) {
        this.totalInstallment = totalInstallment;
    }

    public List<InstallmentPlansDTO> getInstallmentPlans() {
        return installmentPlans;
    }

    public void setInstallmentPlans(List<InstallmentPlansDTO> installmentPlans) {
        this.installmentPlans = installmentPlans;
    }

    public List<CoverageDTO> getCoverages() {
        return coverages;
    }

    public void setCoverages(List<CoverageDTO> coverages) {
        this.coverages = coverages;
    }

    public List<DescriptionDTO> getExclusions() {
        return exclusions;
    }

    public void setExclusions(List<DescriptionDTO> exclusions) {
        this.exclusions = exclusions;
    }

    public List<DescriptionDTO> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<DescriptionDTO> benefits) {
        this.benefits = benefits;
    }

    @Override
    public String toString() {
        return "PlanDTO{" +
                "id='" + this.getId()+ + '\''+
                ", name='" + this.getName()+ '\''+
                ", isSelected=" + getIsSelected() +
                ", isRecommended=" + getIsRecommended() +
                ", totalInstallment=" + totalInstallment +
                ", installmentPlans=" + installmentPlans +
                ", coverages=" + coverages +
                ", exclusions=" + exclusions +
                ", benefits=" + benefits +
                '}';
    }
}