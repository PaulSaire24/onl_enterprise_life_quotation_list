package com.bbva.rbvd.lib.r406.impl;

import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.PlanDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.ProductDTO;
import com.bbva.rbvd.dto.insuranceenterprise.listquotation.ListQuotationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bbva.rbvd.lib.r406.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RBVDR406Impl extends RBVDR406Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR406Impl.class);


	@Override
	public List<ListQuotationDTO> executeListQuotationByClient() {
		boolean flaRimac = Boolean.parseBoolean(this.applicationConfigurationService.getProperty(Constants.FLAG_RIMAC_LIST_QUOTATION));
		LOGGER.info("flag rimac list quotation {}",flaRimac);
		if (flaRimac){
		List<ListQuotationDTO> listQuotation = new ArrayList<>();
			ListQuotationDTO quotationDTO =  new ListQuotationDTO();
			quotationDTO.setId("12457690");
			ProductDTO product = new ProductDTO();
			product.setId("product_id");
			PlanDTO plan = new PlanDTO();
			plan.setId("id_plan");
			product.setPlans(Collections.singletonList(plan));
			quotationDTO.setProduct(product);
			listQuotation.add(quotationDTO);
			return listQuotation;

		}else{
			return null;
		}

	}
}
