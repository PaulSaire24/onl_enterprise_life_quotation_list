package com.bbva.rbvd.lib.r406.impl;

import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.ProductDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.DescriptionDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.ValidityPeriodDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.PlanDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.AmountDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.InstallmentPlansDTO;
import com.bbva.rbvd.dto.insuranceenterprise.dao.QuotationsDAO;
import com.bbva.rbvd.dto.insuranceenterprise.listquotation.ListQuotationDTO;
import com.bbva.rbvd.dto.insuranceenterprise.utils.ConstantsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.bbva.rbvd.lib.r406.impl.utils.ConvertUtils.convertStringDateToLocalDate;
import static com.bbva.rbvd.lib.r406.impl.utils.ConvertUtils.stringIsNullOrEmpty;
import static com.bbva.rbvd.lib.r406.impl.utils.ConvertUtils.convertStringToUpperAndLowerCase;
import static com.bbva.rbvd.lib.r406.impl.utils.ConvertUtils.convertLocalDateToDate;


public class RBVDR406Impl extends RBVDR406Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR406Impl.class);

	private static final String PRODUCT_NAME = "VIDADINAMICO";


	@Override
	public List<ListQuotationDTO> executeListQuotationByClient(String customerId) {

		LOGGER.info("RBVDR406Impl - executeListQuotationByClient() - START");

		Map<String,Object> arguments = new HashMap<>();
		arguments.put(ConstantsUtil.InsuranceQuotation.FIELD_CUSTOMER_ID,customerId);
		arguments.put(ConstantsUtil.InsuranceProduct.FIELD_PRODUCT_SHORT_DESC,PRODUCT_NAME);

		List<Map<String,Object>> lisQuotationsMap = this.pisdR402.executeGetListASingleRow(
				"PISD.GET_LIST_QUOTATIONS_BY_CUSTOMERID",arguments);

		if(!CollectionUtils.isEmpty(lisQuotationsMap)){
			List<ListQuotationDTO> quotations;

			List<QuotationsDAO> quotationsDAOList = lisQuotationsMap.stream().map(map -> {
				map.forEach((key,value) -> LOGGER.info("RBVDR406Impl - executeListQuotationByClient() - " +
						"lisQuotationsMap - key {} -> value {}",key,value));

				QuotationsDAO quotationsDAO = new QuotationsDAO();
				quotationsDAO.setPolicyQuotaInternalId((String) map.get(
						ConstantsUtil.InsuranceQuotation.FIELD_POLICY_QUOTA_INTERNAL_ID));
				quotationsDAO.setQuoteDate((String) map.get(
						ConstantsUtil.InsuranceQuotation.FIELD_QUOTE_DATE));
				quotationsDAO.setQuoteStatus((String) map.get(
						ConstantsUtil.InsuranceQuotation.FIELD_POLICY_QUOTA_STATUS_TYPE));
				quotationsDAO.setFinancingStartDate((String) map.get(
						ConstantsUtil.InsuranceQuotationMod.FIELD_FINANCING_START_DATE));
				quotationsDAO.setFinancingEndDate((String) map.get(
						ConstantsUtil.InsuranceQuotationMod.FIELD_FINANCING_END_DATE));
				quotationsDAO.setTotalAmount((Double) map.get(
						ConstantsUtil.InsuranceContract.FIELD_SETTLEMENT_FIX_PREMIUM_AMOUNT));
				quotationsDAO.setCurrencyId((String) map.get(ConstantsUtil.InsuranceContract.FIELD_CURRENCY_ID));
				quotationsDAO.setNumberPayments((Integer) map.get(
						ConstantsUtil.InsuranceContract.FIELD_ISSUED_RECEIPT_NUMBER));
				quotationsDAO.setPremiumAmount((Double) map.get(
						ConstantsUtil.InsuranceContract.FIELD_PREMIUM_AMOUNT));
				quotationsDAO.setCoStatus((String) map.get(
						ConstantsUtil.InsuranceContract.FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE));
				quotationsDAO.setProductType((String) map.get(
						ConstantsUtil.InsuranceProduct.FIELD_INSURANCE_PRODUCT_TYPE));
				quotationsDAO.setProductDesc((String) map.get(
						ConstantsUtil.InsuranceProduct.FIELD_INSURANCE_PRODUCT_DESC));
				quotationsDAO.setModalityType((String) map.get(
						ConstantsUtil.InsurancePrdModality.FIELD_INSURANCE_MODALITY_TYPE));
				quotationsDAO.setModalityName((String) map.get(
						ConstantsUtil.InsurancePrdModality.FIELD_INSURANCE_MODALITY_NAME));
				quotationsDAO.setPaymentFrequencyName((String) map.get(
						ConstantsUtil.InsrncPaymentPeriod.FIELD_PAYMENT_FREQUENCY_NAME));
				quotationsDAO.setPaymentFrequencyId((Integer) map.get(
						ConstantsUtil.InsrncPaymentPeriod.FIELD_PAYMENT_FREQUENCY_ID));

				return quotationsDAO;
			}).collect(Collectors.toList());

			quotations = quotationsDAOList.stream().map(quotationsDAO -> {
				LOGGER.info("RBVDR406Impl - executeListQuotationByClient() - quotationsDAO {}",quotationsDAO.toString());
				ListQuotationDTO quotationDTO = new ListQuotationDTO();

				quotationDTO.setId(quotationsDAO.getPolicyQuotaInternalId());
				quotationDTO.setQuotationDate(convertLocalDateToDate(
						convertStringDateToLocalDate(quotationsDAO.getQuoteDate())));
				quotationDTO.setProduct(createProduct(quotationsDAO));
				quotationDTO.setValidityPeriod(createValidityPeriod(
						quotationsDAO.getFinancingStartDate(),quotationsDAO.getFinancingEndDate()));
				quotationDTO.setStatus(createStatus(quotationsDAO.getCoStatus(),quotationsDAO.getQuoteStatus()));

				return quotationDTO;
			}).collect(Collectors.toList());

			return quotations;

		}else{
			this.addAdvice("RBVD01020091","No se encontró ninguna cotización del cliente para el producto Vida Ley");
			return Collections.emptyList();
		}

	}

	private ProductDTO createProduct(QuotationsDAO quotationsDAO){
		ProductDTO productDTO = new ProductDTO();

		productDTO.setId(quotationsDAO.getProductType());

		if(!stringIsNullOrEmpty(quotationsDAO.getProductDesc())){
			productDTO.setName(quotationsDAO.getProductDesc());
		}

		productDTO.setPlans(createPlans(quotationsDAO));

		return productDTO;
	}

	private DescriptionDTO createStatus(String coStatus,String quoteStatus){

		if(!stringIsNullOrEmpty(quoteStatus)){
			DescriptionDTO statusDTO = new DescriptionDTO();

			if(!stringIsNullOrEmpty(coStatus) &&
					ConstantsUtil.InsuranceContract.INSRNC_CO_CONTRACT_STATUS_PEN.equalsIgnoreCase(coStatus)){
				statusDTO.setId(ConstantsUtil.StatusEnum.STATUS_CONTRACTED.getStatusId());
				statusDTO.setName(ConstantsUtil.StatusEnum.STATUS_CONTRACTED.getStatusName());
			}else if(ConstantsUtil.InsuranceQuotation.QUOTE_STATUS_COT.equalsIgnoreCase(quoteStatus)){
				statusDTO.setId(ConstantsUtil.StatusEnum.STATUS_QUOTED.getStatusId());
				statusDTO.setName(ConstantsUtil.StatusEnum.STATUS_QUOTED.getStatusName());
			}

			return statusDTO;
		}else{
			return null;
		}

	}

	private ValidityPeriodDTO createValidityPeriod(String startDate,String endDate){

		if(!stringIsNullOrEmpty(startDate) && !stringIsNullOrEmpty(endDate)){
			ValidityPeriodDTO validityPeriod = new ValidityPeriodDTO();
			validityPeriod.setStartDate(convertLocalDateToDate(convertStringDateToLocalDate(startDate)));
			validityPeriod.setEndDate(convertLocalDateToDate(convertStringDateToLocalDate(endDate)));

			return validityPeriod;
		}
		return null;
	}

	private List<PlanDTO> createPlans(QuotationsDAO quotationsDAO){
		List<PlanDTO> plans = new ArrayList<>();

		if(stringIsNullOrEmpty(quotationsDAO.getModalityType()) &&
				stringIsNullOrEmpty(quotationsDAO.getModalityName())){
			return Collections.emptyList();
		}else{
			PlanDTO planDTO = new PlanDTO();

			planDTO.setId(quotationsDAO.getModalityType());
			planDTO.setName(quotationsDAO.getModalityName());
			planDTO.setIsSelected(Boolean.TRUE);
			planDTO.setTotalInstallment(createAmountDTO(quotationsDAO.getTotalAmount(),
					quotationsDAO.getCurrencyId()));
			planDTO.setInstallmentPlans(createInstallmentPlans(quotationsDAO));

			plans.add(planDTO);

			return plans;
		}
	}

	private List<InstallmentPlansDTO> createInstallmentPlans(QuotationsDAO quotationsDAO){
		if(quotationsDAO.getNumberPayments() == null){
			return Collections.emptyList();
		}else{
			List<InstallmentPlansDTO> installmentPlans = new ArrayList<>();
			InstallmentPlansDTO installmentPlansDTO = new InstallmentPlansDTO();

			installmentPlansDTO.setPaymentsTotalNumber(Long.valueOf(quotationsDAO.getNumberPayments()));
			installmentPlansDTO.setPaymentAmount(createAmountDTO(quotationsDAO.getPremiumAmount(),
					quotationsDAO.getCurrencyId()));
			installmentPlansDTO.setPeriod(createPeriod(quotationsDAO.getPaymentFrequencyName()));

			installmentPlans.add(installmentPlansDTO);

			return installmentPlans;
		}
	}

	private DescriptionDTO createPeriod(String frequencyName){

		if(stringIsNullOrEmpty(frequencyName)){
			return null;
		}else{
			DescriptionDTO periodDTO = new DescriptionDTO();

			periodDTO.setId(this.applicationConfigurationService.getProperty(
					convertStringToUpperAndLowerCase(frequencyName)));
			periodDTO.setName(frequencyName);

			return periodDTO;
		}
	}

	private AmountDTO createAmountDTO(Double amount,String currency){
		if(amount != null && !stringIsNullOrEmpty(currency)){
			AmountDTO amountDTO = new AmountDTO();
			amountDTO.setAmount(BigDecimal.valueOf(amount));
			amountDTO.setCurrency(currency);

			return amountDTO;
		}
		return null;
	}

}
