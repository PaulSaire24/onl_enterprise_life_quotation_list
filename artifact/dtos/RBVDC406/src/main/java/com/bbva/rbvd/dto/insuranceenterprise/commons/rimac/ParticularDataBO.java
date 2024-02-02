package com.bbva.rbvd.dto.insuranceenterprise.commons.rimac;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticularDataBO {

    private String etiqueta;
    private String codigo;
    private String valor;


    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ParticularDataBO{");
        sb.append("etiqueta='").append(etiqueta).append('\'');
        sb.append(", codigo='").append(codigo).append('\'');
        sb.append(", valor='").append(valor).append('\'');
        sb.append('}');
        return sb.toString();
    }
}