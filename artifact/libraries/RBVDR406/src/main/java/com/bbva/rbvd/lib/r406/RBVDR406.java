package com.bbva.rbvd.lib.r406;

import com.bbva.rbvd.dto.insuranceenterprise.listquotation.ListQuotationDTO;

import java.util.List;

public interface RBVDR406 {

	List<ListQuotationDTO> executeListQuotationByClient(String customerId);

}
