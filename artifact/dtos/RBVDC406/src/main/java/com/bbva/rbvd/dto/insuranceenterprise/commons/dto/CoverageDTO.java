package com.bbva.rbvd.dto.insuranceenterprise.commons.dto;

public class CoverageDTO extends DescriptionDTO {
    private DescriptionDTO coverageType;

    public DescriptionDTO getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(DescriptionDTO coverageType) {
        this.coverageType = coverageType;
    }

    @Override
    public String toString() {
        return "CoverageDTO{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\''+
                ", description='"+this.getDescription()+'\''+
                ", coverageType=" + coverageType +
                '}';
    }
}