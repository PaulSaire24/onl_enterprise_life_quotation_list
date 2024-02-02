package com.bbva.rbvd.dto.insuranceenterprise.commons.rimac;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class FinancingBO {

    private String periodicidad;
    private Long financiamiento;
    private Long numeroCuotas;
    private String fechaInicio;
    private String fechaFin;
    private List<InstallmentFinancingBO> cuotasFinanciamiento;

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Long getFinanciamiento() {
        return financiamiento;
    }

    public void setFinanciamiento(Long financiamiento) {
        this.financiamiento = financiamiento;
    }

    public Long getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(Long numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<InstallmentFinancingBO> getCuotasFinanciamiento() {
        return cuotasFinanciamiento;
    }

    public void setCuotasFinanciamiento(List<InstallmentFinancingBO> cuotasFinanciamiento) {
        this.cuotasFinanciamiento = cuotasFinanciamiento;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FinancingBO{");
        sb.append("periodicidad='").append(periodicidad).append('\'');
        sb.append(", financiamiento=").append(financiamiento);
        sb.append(", numeroCuotas=").append(numeroCuotas);
        sb.append(", fechaInicio='").append(fechaInicio).append('\'');
        sb.append(", fechaFin='").append(fechaFin).append('\'');
        sb.append(", cuotasFinanciamiento=").append(cuotasFinanciamiento);
        sb.append('}');
        return sb.toString();
    }
}