package com.bbva.rbvd.dto.insuranceenterprise.utils;

public class ConstantsUtil {

    private ConstantsUtil(){}

    public static final class InsuranceQuotation{
        private InsuranceQuotation(){}

        public static final String FIELD_POLICY_QUOTA_INTERNAL_ID = "POLICY_QUOTA_INTERNAL_ID";
        public static final String FIELD_QUOTE_DATE = "QUOTE_DATE";
        public static final String FIELD_POLICY_QUOTA_STATUS_TYPE = "QUOTE_STATUS";
        public static final String FIELD_CUSTOMER_ID = "CUSTOMER_ID";
        public static final String FIELD_RFQ_INTERNAL_ID = "RFQ_INTERNAL_ID";

        public static final String QUOTE_STATUS_COT = "COT";

    }

    public static final class InsuranceQuotationMod{
        private InsuranceQuotationMod(){}

        public static final String FIELD_FINANCING_START_DATE = "FINANCING_START_DATE";
        public static final String FIELD_FINANCING_END_DATE = "FINANCING_END_DATE";
        public static final String FIELD_PREMIUN_AMOUNT = "PREMIUM_AMOUNT";
        public static final String FIELD_PREMIUM_CURRENCY_ID = "PREMIUM_CURRENCY_ID";
        public static final String FIELD_PAYMENT_TERM_NUMBER = "NUMBER_PAYMENTS";
        public static final String FIELD_SALE_CHANNEL_ID = "CHANNEL_ID";
    }

    public static final class InsuranceContract{
        private InsuranceContract(){}

        public static final String FIELD_CURRENCY_ID = "CURRENCY_ID";
        public static final String FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE = "CO_STATUS";

        public static final String INSRNC_CO_CONTRACT_STATUS_PEN = "PEN";

    }

    public static final class InsuranceProduct{
        private InsuranceProduct(){}

        public static final String FIELD_INSURANCE_PRODUCT_TYPE = "INSURANCE_PRODUCT_TYPE";
        public static final String FIELD_INSURANCE_PRODUCT_DESC = "INSURANCE_PRODUCT_DESC";
        public static final String FIELD_PRODUCT_SHORT_DESC = "PRODUCT_NAME";
    }

    public static final class InsurancePrdModality{
        private InsurancePrdModality(){}

        public static final String FIELD_INSURANCE_MODALITY_TYPE = "INSURANCE_MODALITY_TYPE";
        public static final String FIELD_INSURANCE_MODALITY_NAME = "INSURANCE_MODALITY_NAME";
    }

    public static final class InsrncPaymentPeriod{
        private InsrncPaymentPeriod(){}

        public static final String FIELD_PAYMENT_FREQUENCY_NAME = "PAYMENT_FREQUENCY_NAME";
        public static final String FIELD_PAYMENT_FREQUENCY_ID = "PAYMENT_FREQUENCY_ID";
    }

    public static final class InsuranceQuoteCompanyLife{
        private InsuranceQuoteCompanyLife(){}

        public static final String FIELD_YEARS_OLD_18_65_EMPLOYEES_IND_TYPE = "YEARS_OLD_18_65_EMPLOYEES_IND_TYPE";
        public static final String FIELD_PAYROLL_EMPLOYEE_NUMBER = "PAYROLL_EMPLOYEE_NUMBER";
        public static final String FIELD_INCOMES_PAYROLL_AMOUNT = "INCOMES_PAYROLL_AMOUNT";
        public static final String FIELD_CURRENCY_ID = "CURRENCY_ID";

    }

    public static final class QueriesName{
        private QueriesName(){}


        public static final String QUERY_LIST_QUOTATIONS_BY_CUSTOMER = "PISD.GET_LIST_QUOTATIONS_BY_CUSTOMERID";
    }




    public enum StatusEnum{

        STATUS_QUOTED("QUOTED_ID","QUOTED_NAME"),
        STATUS_CONTRACTED("CONTRACTED_ID","CONTRACTED_NAME");


        private final String keyId;
        private final String keyName;


        public String getKeyId() {
            return keyId;
        }

        public String getKeyName() {
            return keyName;
        }

        StatusEnum(String keyId, String keyName) {
            this.keyId = keyId;
            this.keyName = keyName;
        }
    }

}
