package com.bbva.rbvd.lib.r406.impl.transform.bean;

import com.bbva.rbvd.dto.insuranceenterprise.dao.QuotationsDAO;
import com.bbva.rbvd.dto.insuranceenterprise.utils.ConstantsUtil;

import java.util.Map;

import static com.bbva.rbvd.lib.r406.impl.utils.ConvertUtils.getBigDecimalValue;

public class InsuranceQuotationBean {

    private InsuranceQuotationBean(){}

    public static QuotationsDAO convertMapToBeanQuotation(Map<String,Object> map){
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
        quotationsDAO.setPremiumAmount(getBigDecimalValue(map.get(
                ConstantsUtil.InsuranceQuotationMod.FIELD_PREMIUN_AMOUNT)));
        quotationsDAO.setPremiumCurrencyId((String) map.get(ConstantsUtil.InsuranceQuotationMod.FIELD_PREMIUM_CURRENCY_ID));
        quotationsDAO.setNumberPayments(getBigDecimalValue(map.get(
                ConstantsUtil.InsuranceQuotationMod.FIELD_PAYMENT_TERM_NUMBER)));
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
        quotationsDAO.setPaymentFrequencyId(getBigDecimalValue(map.get(
                ConstantsUtil.InsrncPaymentPeriod.FIELD_PAYMENT_FREQUENCY_ID)));
        quotationsDAO.setRfqInternalId((String) map.get(ConstantsUtil.InsuranceQuotation.FIELD_RFQ_INTERNAL_ID));
        quotationsDAO.setEmployeesIndType((String) map.get(
                ConstantsUtil.InsuranceQuoteCompanyLife.FIELD_YEARS_OLD_18_65_EMPLOYEES_IND_TYPE));
        quotationsDAO.setEmployeeNumber(getBigDecimalValue(map.get(
                ConstantsUtil.InsuranceQuoteCompanyLife.FIELD_PAYROLL_EMPLOYEE_NUMBER)));
        quotationsDAO.setPayrollAmount(getBigDecimalValue(map.get(
                ConstantsUtil.InsuranceQuoteCompanyLife.FIELD_INCOMES_PAYROLL_AMOUNT)));
        quotationsDAO.setPayrollCurrencyId((String) map.get(
                ConstantsUtil.InsuranceQuoteCompanyLife.FIELD_CURRENCY_ID));

        return quotationsDAO;
    }

}
