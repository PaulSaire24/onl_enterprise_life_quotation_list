package com.bbva.rbvd.lib.r406;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;

import com.bbva.pisd.lib.r402.PISDR402;
import com.bbva.rbvd.dto.insuranceenterprise.listquotation.ListQuotationDTO;
import com.bbva.rbvd.dto.insuranceenterprise.utils.ConstantsUtil;
import com.bbva.rbvd.lib.r406.impl.RBVDR406Impl;
import com.bbva.rbvd.lib.r406.impl.utils.ConvertUtils;
import com.bbva.rbvd.lib.r406.impl.utils.ValidateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/RBVDR406-app.xml",
		"classpath:/META-INF/spring/RBVDR406-app-test.xml",
		"classpath:/META-INF/spring/RBVDR406-arc.xml",
		"classpath:/META-INF/spring/RBVDR406-arc-test.xml" })
public class RBVDR406Test {

	@Spy
	private Context context;

	private final RBVDR406Impl rbvdR406 = new RBVDR406Impl();

	private PISDR402 pisdr402;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();

		pisdr402 = Mockito.mock(PISDR402.class);
		ApplicationConfigurationService applicationConfigurationService = Mockito.mock(ApplicationConfigurationService.class);

		rbvdR406.setPisdR402(pisdr402);
		rbvdR406.setApplicationConfigurationService(applicationConfigurationService);

		Mockito.when(pisdr402.executeGetListASingleRow(Mockito.anyString(),Mockito.anyMap())).thenReturn(Collections.emptyList());
		Mockito.when(applicationConfigurationService.getProperty("Mensual")).thenReturn("MONTHLY");
		Mockito.when(applicationConfigurationService.getProperty("ENTERPRISE_LIFE_STATUS_QUOTED_ID")).thenReturn("QUOTED");
		Mockito.when(applicationConfigurationService.getProperty("ENTERPRISE_LIFE_STATUS_QUOTED_NAME")).thenReturn("COTIZADA");
		Mockito.when(applicationConfigurationService.getProperty("ENTERPRISE_LIFE_STATUS_CONTRACTED_ID")).thenReturn("CONTRACTED");
		Mockito.when(applicationConfigurationService.getProperty("ENTERPRISE_LIFE_STATUS_CONTRACTED_NAME")).thenReturn("CONTRATADA");
	}

	private Object getObjectIntrospection() throws Exception{
		Object result = this.rbvdR406;
		if(this.rbvdR406 instanceof Advised){
			Advised advised = (Advised) this.rbvdR406;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}

	private List<Map<String,Object>> getListQuotationsMap(){
		List<Map<String,Object>> mapList = new ArrayList<>();

		Map<String,Object> map1 = new HashMap<>();
		map1.put("POLICY_QUOTA_INTERNAL_ID","0814000038346");
		map1.put("QUOTE_DATE","2024-01-26");
		map1.put("QUOTE_STATUS","COT");
		map1.put("FINANCING_START_DATE","2024-01-26");
		map1.put("FINANCING_END_DATE","2025-01-26");
		map1.put("PREMIUM_AMOUNT",874.29);
		map1.put("PREMIUM_CURRENCY_ID","PEN");
		map1.put("NUMBER_PAYMENTS",null);
		map1.put("CO_STATUS",null);
		map1.put("INSURANCE_PRODUCT_TYPE","842");
		map1.put("INSURANCE_PRODUCT_DESC","SEGURO VIDA LEY");
		map1.put("INSURANCE_MODALITY_TYPE","01");
		map1.put("INSURANCE_MODALITY_NAME","PLAN PLATA");
		map1.put("PAYMENT_FREQUENCY_NAME",null);
		map1.put("PAYMENT_FREQUENCY_ID",null);
		map1.put("RFQ_INTERNAL_ID","0814000038434");
		map1.put("YEARS_OLD_18_65_EMPLOYEES_IND_TYPE","1");
		map1.put("PAYROLL_EMPLOYEE_NUMBER",3);
		map1.put("INCOMES_PAYROLL_AMOUNT",567.91);
		map1.put("CURRENCY_ID","PEN");

		mapList.add(map1);

		Map<String,Object> map2 = new HashMap<>();
		map2.put("POLICY_QUOTA_INTERNAL_ID","0814000038434");
		map2.put("QUOTE_DATE","2024-01-26");
		map2.put("QUOTE_STATUS","COT");
		map2.put("FINANCING_START_DATE","2024-01-26");
		map2.put("FINANCING_END_DATE","2025-01-26");
		map2.put("PREMIUM_AMOUNT",874.29);
		map2.put("PREMIUM_CURRENCY_ID","PEN");
		map2.put("NUMBER_PAYMENTS",12);
		map2.put("CO_STATUS","PEN");
		map2.put("INSURANCE_PRODUCT_TYPE","842");
		map2.put("INSURANCE_PRODUCT_DESC","SEGURO VIDA LEY");
		map2.put("INSURANCE_MODALITY_TYPE","02");
		map2.put("INSURANCE_MODALITY_NAME","PLAN ORO");
		map2.put("PAYMENT_FREQUENCY_NAME","MENSUAL");
		map2.put("PAYMENT_FREQUENCY_ID",1);
		map2.put("RFQ_INTERNAL_ID",null);
		map2.put("YEARS_OLD_18_65_EMPLOYEES_IND_TYPE","1");
		map2.put("PAYROLL_EMPLOYEE_NUMBER",2);
		map2.put("INCOMES_PAYROLL_AMOUNT",8322.74);
		map2.put("CURRENCY_ID","PEN");

		mapList.add(map2);

		return mapList;
	}

	@Test
	public void executeTestWithListQuotationNotEmpty(){
		List<Map<String,Object>> listQuotationsMap = getListQuotationsMap();

		Mockito.when(this.pisdr402.executeGetListASingleRow(Mockito.anyString(),Mockito.anyMap())).thenReturn(listQuotationsMap);

		List<ListQuotationDTO> listQuotation = rbvdR406.executeListQuotationByClient("97790084","PC");

		Assert.assertNotNull(listQuotation);
		Assert.assertEquals(2,listQuotation.size());

		Assert.assertEquals(listQuotationsMap.get(0).get("POLICY_QUOTA_INTERNAL_ID"),listQuotation.get(0).getId());
		Assert.assertEquals(listQuotationsMap.get(1).get("POLICY_QUOTA_INTERNAL_ID"),listQuotation.get(1).getId());
		Assert.assertEquals("QUOTED",listQuotation.get(0).getStatus().getId());
		Assert.assertEquals("CONTRACTED",listQuotation.get(1).getStatus().getId());
		Assert.assertEquals("COTIZADA",listQuotation.get(0).getStatus().getName());
		Assert.assertEquals("CONTRATADA",listQuotation.get(1).getStatus().getName());
		Assert.assertEquals(1,listQuotation.get(0).getProduct().getPlans().size());
		Assert.assertEquals(1,listQuotation.get(1).getProduct().getPlans().size());
		Assert.assertNotNull(listQuotation.get(0).getQuotationDate());
		Assert.assertNotNull(listQuotation.get(1).getQuotationDate());
		Assert.assertNotNull(listQuotation.get(1).getProduct().getPlans().get(0).getInstallmentPlans().get(0).getPaymentsTotalNumber());
		Assert.assertNotNull(listQuotation.get(1).getProduct().getPlans().get(0).getInstallmentPlans().get(0).getPaymentAmount());
		Assert.assertNotNull(listQuotation.get(1).getProduct().getPlans().get(0).getInstallmentPlans().get(0).getPaymentAmount().getAmount());
		Assert.assertNotNull(listQuotation.get(1).getProduct().getPlans().get(0).getInstallmentPlans().get(0).getPaymentAmount().getCurrency());
		Assert.assertNotNull(listQuotation.get(1).getProduct().getPlans().get(0).getInstallmentPlans().get(0).getPeriod());
		Assert.assertNotNull(listQuotation.get(1).getProduct().getPlans().get(0).getInstallmentPlans().get(0).getPeriod().getId());
		Assert.assertNotNull(listQuotation.get(1).getProduct().getPlans().get(0).getInstallmentPlans().get(0).getPeriod().getName());
		Assert.assertNotNull(listQuotation.get(0).getQuotationReference());
		Assert.assertNull(listQuotation.get(1).getQuotationReference());

		Assert.assertNotNull(listQuotation.get(0).getEmployees());
		Assert.assertNotNull(listQuotation.get(1).getEmployees());
		Assert.assertTrue(listQuotation.get(0).getEmployees().getAreMajorityAge());
		Assert.assertTrue(listQuotation.get(1).getEmployees().getAreMajorityAge());
		Assert.assertEquals(new Long(3),listQuotation.get(0).getEmployees().getEmployeesNumber());
		Assert.assertEquals(new Long(2),listQuotation.get(1).getEmployees().getEmployeesNumber());

	}

	@Test
	public void executeTestWithListQuotationEmpty(){
		List<ListQuotationDTO> listQuotation = rbvdR406.executeListQuotationByClient("97790084","PC");

		Assert.assertEquals(1, context.getAdviceList().size());
		Assert.assertEquals(Collections.emptyList(),listQuotation);
		Assert.assertEquals(0,listQuotation.size());
	}

	@Test
	public void executeListQuotationWithStatusNull(){
		List<Map<String,Object>> listQuotationsMap = getListQuotationsMap();
		listQuotationsMap.get(1).put("QUOTE_STATUS",null);
		listQuotationsMap.get(0).put("QUOTE_STATUS",null);

		Mockito.when(this.pisdr402.executeGetListASingleRow(Mockito.anyString(),Mockito.anyMap())).thenReturn(listQuotationsMap);

		List<ListQuotationDTO> listQuotation = rbvdR406.executeListQuotationByClient("97790084","PC");

		Assert.assertNotNull(listQuotation);
		Assert.assertEquals(2,listQuotation.size());
		Assert.assertNull(listQuotation.get(0).getStatus());
		Assert.assertNull(listQuotation.get(1).getStatus());
		Assert.assertNotNull(listQuotation.get(0).getProduct());
	}

	@Test
	public void executeListQuotationWithFinancingDateIsNull(){
		List<Map<String,Object>> listQuotationsMap = getListQuotationsMap();
		listQuotationsMap.get(1).put("FINANCING_START_DATE",null);
		listQuotationsMap.get(0).put("FINANCING_END_DATE",null);

		Mockito.when(this.pisdr402.executeGetListASingleRow(Mockito.anyString(),Mockito.anyMap())).thenReturn(listQuotationsMap);

		List<ListQuotationDTO> listQuotation = rbvdR406.executeListQuotationByClient("97790084","");

		Assert.assertNotNull(listQuotation);
		Assert.assertEquals(2,listQuotation.size());
		Assert.assertNull(listQuotation.get(0).getValidityPeriod());
		Assert.assertNull(listQuotation.get(1).getValidityPeriod());
	}

	@Test
	public void executeListQuotationWithModalityNull() {
		List<Map<String, Object>> listQuotationsMap = getListQuotationsMap();
		listQuotationsMap.get(0).put("INSURANCE_MODALITY_TYPE", null);
		listQuotationsMap.get(0).put("INSURANCE_MODALITY_NAME", null);

		Mockito.when(this.pisdr402.executeGetListASingleRow(Mockito.anyString(), Mockito.anyMap())).thenReturn(listQuotationsMap);

		List<ListQuotationDTO> listQuotation = rbvdR406.executeListQuotationByClient("97790084","PC");

		Assert.assertNotNull(listQuotation);
		Assert.assertEquals(2,listQuotation.size());
		Assert.assertNotNull(listQuotation.get(0).getProduct());
		Assert.assertNotNull(listQuotation.get(1).getProduct());
		Assert.assertEquals(0,listQuotation.get(0).getProduct().getPlans().size());
		Assert.assertEquals(1,listQuotation.get(1).getProduct().getPlans().size());
	}

	@Test
	public void executeListQuotationWithPeriodNull() {
		List<Map<String, Object>> listQuotationsMap = getListQuotationsMap();
		listQuotationsMap.get(1).put("PAYMENT_FREQUENCY_NAME", null);
		listQuotationsMap.get(1).put("PAYMENT_FREQUENCY_ID", null);

		Mockito.when(this.pisdr402.executeGetListASingleRow(Mockito.anyString(), Mockito.anyMap())).thenReturn(listQuotationsMap);

		List<ListQuotationDTO> listQuotation = rbvdR406.executeListQuotationByClient("97790084","PC");

		Assert.assertNotNull(listQuotation);
		Assert.assertEquals(2,listQuotation.size());
		Assert.assertNotNull(listQuotation.get(1).getId());
		Assert.assertNotNull(listQuotation.get(1).getProduct());
		Assert.assertNotNull(listQuotation.get(1).getProduct().getId());
		Assert.assertEquals(1,listQuotation.get(1).getProduct().getPlans().size());
		Assert.assertEquals(1,listQuotation.get(1).getProduct().getPlans().get(0).getInstallmentPlans().size());
		Assert.assertNull(listQuotation.get(1).getProduct().getPlans().get(0).getInstallmentPlans().get(0).getPeriod());
	}

	@Test
	public void executeListQuotationWithEmployeesDataContainsNull(){
		List<Map<String,Object>> listQuotationsMap = getListQuotationsMap();
		listQuotationsMap.get(0).put("YEARS_OLD_18_65_EMPLOYEES_IND_TYPE",null);
		listQuotationsMap.get(0).put("PAYROLL_EMPLOYEE_NUMBER",null);
		listQuotationsMap.get(0).put("INCOMES_PAYROLL_AMOUNT",null);
		listQuotationsMap.get(0).put("CURRENCY_ID",null);

		Mockito.when(this.pisdr402.executeGetListASingleRow(Mockito.anyString(),Mockito.anyMap())).thenReturn(listQuotationsMap);

		List<ListQuotationDTO> listQuotation = rbvdR406.executeListQuotationByClient("97790084","PC");

		Assert.assertNotNull(listQuotation);
		Assert.assertEquals(2,listQuotation.size());

		Assert.assertNull(listQuotation.get(0).getEmployees());

		Assert.assertNotNull(listQuotation.get(1).getEmployees());
		Assert.assertNotNull(listQuotation.get(1).getEmployees().getAreMajorityAge());
		Assert.assertNotNull(listQuotation.get(1).getEmployees().getEmployeesNumber());
		Assert.assertNotNull(listQuotation.get(1).getEmployees().getMonthlyPayrollAmount());
		Assert.assertNotNull(listQuotation.get(1).getEmployees().getMonthlyPayrollAmount().getAmount());
		Assert.assertNotNull(listQuotation.get(1).getEmployees().getMonthlyPayrollAmount().getCurrency());
	}

}
