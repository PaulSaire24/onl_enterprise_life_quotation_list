package com.bbva.rbvd.dto.insuranceenterprise.commons.rimac;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanBO {

    private Long plan;
    private String descripcionPlan;
    private BigDecimal primaNeta;
    private BigDecimal primaBruta;
    private List<FinancingBO> financiamientos;
    private List<CoverageBO> coberturas;
    private List<ParticularDataBO> datosParticulares;
    private List<AssistanceBO> asistencias;


    public Long getPlan() {
        return plan;
    }

    public void setPlan(Long plan) {
        this.plan = plan;
    }

    public String getDescripcionPlan() {
        return descripcionPlan;
    }

    public void setDescripcionPlan(String descripcionPlan) {
        this.descripcionPlan = descripcionPlan;
    }

    public BigDecimal getPrimaNeta() {
        return primaNeta;
    }

    public void setPrimaNeta(BigDecimal primaNeta) {
        this.primaNeta = primaNeta;
    }

    public BigDecimal getPrimaBruta() {
        return primaBruta;
    }

    public void setPrimaBruta(BigDecimal primaBruta) {
        this.primaBruta = primaBruta;
    }

    public List<FinancingBO> getFinanciamientos() {
        return financiamientos;
    }

    public void setFinanciamientos(List<FinancingBO> financiamientos) {
        this.financiamientos = financiamientos;
    }

    public List<CoverageBO> getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(List<CoverageBO> coberturas) {
        this.coberturas = coberturas;
    }

    public List<ParticularDataBO> getDatosParticulares() {
        return datosParticulares;
    }

    public void setDatosParticulares(List<ParticularDataBO> datosParticulares) {
        this.datosParticulares = datosParticulares;
    }

    public List<AssistanceBO> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<AssistanceBO> asistencias) {
        this.asistencias = asistencias;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlanBO{");
        sb.append("plan=").append(plan);
        sb.append(", descripcionPlan='").append(descripcionPlan).append('\'');
        sb.append(", primaNeta=").append(primaNeta);
        sb.append(", primaBruta=").append(primaBruta);
        sb.append(", financiamientos=").append(financiamientos);
        sb.append(", coberturas=").append(coberturas);
        sb.append(", datosParticulares=").append(datosParticulares);
        sb.append(", asistencias=").append(asistencias);
        sb.append('}');
        return sb.toString();
    }
}