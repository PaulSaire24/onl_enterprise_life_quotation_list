package com.bbva.rbvd.dto.insuranceenterprise.listquotation;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.DescriptionDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.EmployeesDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.ProductDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.ValidityPeriodDTO;

public class ListQuotationDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private LocalDateTime quotationDate;
	private EmployeesDTO employees;
	private ProductDTO product;
	private ValidityPeriodDTO validityPeriod;
	private DescriptionDTO status;
	private String quotationReference;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(LocalDateTime quotationDate) {
		this.quotationDate = quotationDate;
	}

	public EmployeesDTO getEmployees() {
		return employees;
	}

	public void setEmployees(EmployeesDTO employees) {
		this.employees = employees;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public ValidityPeriodDTO getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(ValidityPeriodDTO validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public DescriptionDTO getStatus() {
		return status;
	}

	public void setStatus(DescriptionDTO status) {
		this.status = status;
	}

	public String getQuotationReference() {
		return quotationReference;
	}

	public void setQuotationReference(String quotationReference) {
		this.quotationReference = quotationReference;
	}

	@Override
	public String toString() {
		return "ListQuotationDTO{" +
				"id='" + id + '\'' +
				", quotationDate=" + quotationDate +
				", employees=" + employees +
				", product=" + product +
				", validityPeriod=" + validityPeriod +
				", status=" + status +
				", quotationReference='" + quotationReference + '\'' +
				'}';
	}
}