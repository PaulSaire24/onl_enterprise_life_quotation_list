package com.bbva.rbvd.dto.insuranceenterprise.commons.rimac;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoverageBO {

    private Long cobertura;
    private String descripcionCobertura;
    private String tipoCobertura;
    private BigDecimal primaBruta;
    private BigDecimal primaNeta;
    private String moneda;
    private BigDecimal sumaAsegurada;
    private String porcentajeSeleccionado;
    private String observacionCobertura;
    private String condicion;


    public Long getCobertura() {
        return cobertura;
    }

    public void setCobertura(Long cobertura) {
        this.cobertura = cobertura;
    }

    public String getDescripcionCobertura() {
        return descripcionCobertura;
    }

    public void setDescripcionCobertura(String descripcionCobertura) {
        this.descripcionCobertura = descripcionCobertura;
    }

    public String getTipoCobertura() {
        return tipoCobertura;
    }

    public void setTipoCobertura(String tipoCobertura) {
        this.tipoCobertura = tipoCobertura;
    }

    public BigDecimal getPrimaBruta() {
        return primaBruta;
    }

    public void setPrimaBruta(BigDecimal primaBruta) {
        this.primaBruta = primaBruta;
    }

    public BigDecimal getPrimaNeta() {
        return primaNeta;
    }

    public void setPrimaNeta(BigDecimal primaNeta) {
        this.primaNeta = primaNeta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getSumaAsegurada() {
        return sumaAsegurada;
    }

    public void setSumaAsegurada(BigDecimal sumaAsegurada) {
        this.sumaAsegurada = sumaAsegurada;
    }

    public String getPorcentajeSeleccionado() {
        return porcentajeSeleccionado;
    }

    public void setPorcentajeSeleccionado(String porcentajeSeleccionado) {
        this.porcentajeSeleccionado = porcentajeSeleccionado;
    }

    public String getObservacionCobertura() {
        return observacionCobertura;
    }

    public void setObservacionCobertura(String observacionCobertura) {
        this.observacionCobertura = observacionCobertura;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CoverageBO{");
        sb.append("cobertura=").append(cobertura);
        sb.append(", descripcionCobertura='").append(descripcionCobertura).append('\'');
        sb.append(", tipoCobertura='").append(tipoCobertura).append('\'');
        sb.append(", primaBruta=").append(primaBruta);
        sb.append(", primaNeta=").append(primaNeta);
        sb.append(", moneda='").append(moneda).append('\'');
        sb.append(", sumaAsegurada=").append(sumaAsegurada);
        sb.append(", porcentajeSeleccionado='").append(porcentajeSeleccionado).append('\'');
        sb.append(", observacionCobertura='").append(observacionCobertura).append('\'');
        sb.append(", condicion='").append(condicion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}