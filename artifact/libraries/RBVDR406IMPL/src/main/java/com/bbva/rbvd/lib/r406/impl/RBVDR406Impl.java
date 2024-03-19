package com.bbva.rbvd.lib.r406.impl;

import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.EmployeesDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.ProductDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.DescriptionDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.PlanDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.InstallmentPlansDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.AmountDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.ValidityPeriodDTO;

import com.bbva.rbvd.dto.insuranceenterprise.dao.QuotationsDAO;
import com.bbva.rbvd.dto.insuranceenterprise.listquotation.ListQuotationDTO;
import com.bbva.rbvd.dto.insuranceenterprise.utils.ConstantsUtil;
import com.bbva.rbvd.dto.insuranceenterprise.utils.ErrorsUtil;
import com.bbva.rbvd.lib.r406.impl.transform.bean.InsuranceQuotationBean;
import com.bbva.rbvd.lib.r406.impl.utils.ValidateUtils;
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
import static com.bbva.rbvd.lib.r406.impl.utils.ConvertUtils.convertStringToUpperAndLowerCase;
import static com.bbva.rbvd.lib.r406.impl.utils.ConvertUtils.convertLocalDateToDate;
import static com.bbva.rbvd.lib.r406.impl.utils.ConvertUtils.convertObjectToString;
import static com.bbva.rbvd.lib.r406.impl.utils.ValidateUtils.stringIsNullOrEmpty;


public class RBVDR406Impl extends RBVDR406Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR406Impl.class);

	private static final String PRODUCT_NAME = "VIDALEY";
	private static final String ENTERPRISE_LIFE_STATUS = "ENTERPRISE_LIFE_STATUS_";



	@Override
	public List<ListQuotationDTO> executeListQuotationByClient(String customerId,String channelCode) {

		LOGGER.info("RBVDR406Impl - executeListQuotationByClient() - START");

		List<Map<String, Object>> lisQuotationsMap = getListQuotationsFromDB(customerId,channelCode);

		if(!CollectionUtils.isEmpty(lisQuotationsMap)){
			List<ListQuotationDTO> quotations;

			//Conversion a listado de objetos
			List<QuotationsDAO> quotationsDAOList = lisQuotationsMap.stream().map(map -> {
				map.forEach((key,value) -> LOGGER.info("RBVDR406Impl - executeListQuotationByClient() - " +
						"lisQuotationsMap - key {} -> value {}",key,value));

				return InsuranceQuotationBean.convertMapToBeanQuotation(map);
			}).collect(Collectors.toList());

			//Construcción de trx

			quotations = quotationsDAOList.stream().map(quotationsDAO -> {
				LOGGER.info("RBVDR406Impl - executeListQuotationByClient() - quotationsDAO {}",convertObjectToString(quotationsDAO));
				ListQuotationDTO quotationDTO = new ListQuotationDTO();

				quotationDTO.setId(quotationsDAO.getPolicyQuotaInternalId());
				quotationDTO.setQuotationDate(convertLocalDateToDate(
						convertStringDateToLocalDate(quotationsDAO.getQuoteDate())));
				quotationDTO.setEmployees(createEmployees(quotationsDAO));
				quotationDTO.setProduct(createProduct(quotationsDAO));
				quotationDTO.setValidityPeriod(createValidityPeriod(
						quotationsDAO.getFinancingStartDate(),quotationsDAO.getFinancingEndDate()));
				quotationDTO.setStatus(createStatus(quotationsDAO.getCoStatus(),quotationsDAO.getQuoteStatus()));
				quotationDTO.setQuotationReference(quotationsDAO.getRfqInternalId());

				return quotationDTO;
			}).collect(Collectors.toList());

			return quotations;

		}else{
			this.addAdvice(ErrorsUtil.QUOTATIONS_EMPTY_BY_CUSTOMER.getErrorCode(),
					ErrorsUtil.QUOTATIONS_EMPTY_BY_CUSTOMER.getErrorMessage());
			return Collections.emptyList();
		}
	}

	private EmployeesDTO createEmployees(QuotationsDAO quotationsDAO){
		if(ValidateUtils.allValuesStringNotNullOrEmpty(quotationsDAO.getEmployeesIndType(),
				quotationsDAO.getPayrollCurrencyId()) && quotationsDAO.getEmployeeNumber() != null &&
				quotationsDAO.getPayrollAmount() != null){
			EmployeesDTO employeesDTO = new EmployeesDTO();

			employeesDTO.setAreMajorityAge(quotationsDAO.getEmployeesIndType().equals("1"));
			employeesDTO.setEmployeesNumber(quotationsDAO.getEmployeeNumber().longValue());

			AmountDTO payrollAmount = new AmountDTO();
			payrollAmount.setAmount(quotationsDAO.getPayrollAmount().doubleValue());
			payrollAmount.setCurrency(quotationsDAO.getPayrollCurrencyId());
			employeesDTO.setMonthlyPayrollAmount(payrollAmount);

			return employeesDTO;
		}
		return null;
	}

	private List<Map<String, Object>> getListQuotationsFromDB(String customerId,String channelCode) {
		Map<String,Object> arguments = new HashMap<>();
		arguments.put(ConstantsUtil.InsuranceQuotation.FIELD_CUSTOMER_ID, customerId);
		arguments.put(ConstantsUtil.InsuranceProduct.FIELD_PRODUCT_SHORT_DESC,PRODUCT_NAME);
		arguments.put(ConstantsUtil.InsuranceQuotationMod.FIELD_SALE_CHANNEL_ID,channelCode);

		return this.pisdR402.executeGetListASingleRow(
				ConstantsUtil.QueriesName.QUERY_LIST_QUOTATIONS_BY_CUSTOMER,arguments);
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
				statusDTO.setId(getStatusFromConsole(ConstantsUtil.StatusEnum.STATUS_CONTRACTED.getKeyId()));
				statusDTO.setName(getStatusFromConsole(ConstantsUtil.StatusEnum.STATUS_CONTRACTED.getKeyName()));
			}else if(ConstantsUtil.InsuranceQuotation.QUOTE_STATUS_COT.equalsIgnoreCase(quoteStatus)){
				statusDTO.setId(getStatusFromConsole(ConstantsUtil.StatusEnum.STATUS_QUOTED.getKeyId()));
				statusDTO.setName(getStatusFromConsole(ConstantsUtil.StatusEnum.STATUS_QUOTED.getKeyName()));
			}

			return statusDTO;
		}else{
			return null;
		}
	}

	private String getStatusFromConsole(String key){
		return this.applicationConfigurationService.getProperty(ENTERPRISE_LIFE_STATUS + key);
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
			planDTO.setTotalInstallment(createAmountDTO(
					calculateTotalAmountFromDB(quotationsDAO.getPremiumAmount(),quotationsDAO.getNumberPayments()),
					quotationsDAO.getPremiumCurrencyId()));
			planDTO.setInstallmentPlans(createInstallmentPlans(quotationsDAO));

			plans.add(planDTO);

			return plans;
		}
	}

	private static BigDecimal calculateTotalAmountFromDB(BigDecimal paymentAmount,BigDecimal numberOfPayments){
		if(numberOfPayments == null){
			numberOfPayments = new BigDecimal("1");
		}

		if(paymentAmount == null){
			return null;
		}

		return paymentAmount.multiply(numberOfPayments);
	}

	private List<InstallmentPlansDTO> createInstallmentPlans(QuotationsDAO quotationsDAO){
		if(quotationsDAO.getNumberPayments() == null){
			return Collections.emptyList();
		}else{
			List<InstallmentPlansDTO> installmentPlans = new ArrayList<>();
			InstallmentPlansDTO installmentPlansDTO = new InstallmentPlansDTO();

			installmentPlansDTO.setPaymentsTotalNumber(quotationsDAO.getNumberPayments().longValue());
			installmentPlansDTO.setPaymentAmount(createAmountDTO(quotationsDAO.getPremiumAmount(),
					quotationsDAO.getPremiumCurrencyId()));
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

			String frequencyId = convertStringToUpperAndLowerCase(frequencyName);
			periodDTO.setId(this.applicationConfigurationService.getProperty(frequencyId));
			periodDTO.setName(frequencyName);

			return periodDTO;
		}
	}

	private AmountDTO createAmountDTO(BigDecimal amount,String currency){
		if(amount != null && !stringIsNullOrEmpty(currency)){
			AmountDTO amountDTO = new AmountDTO();
			amountDTO.setAmount(amount.doubleValue());
			amountDTO.setCurrency(currency);

			return amountDTO;
		}
		return null;
	}

}
