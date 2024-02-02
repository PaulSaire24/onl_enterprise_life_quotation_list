package com.bbva.rbvd.lib.r406;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.rbvd.dto.insuranceenterprise.listquotation.ListQuotationDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bbva.rbvd.lib.r406.util.Constants;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/RBVDR406-app.xml",
		"classpath:/META-INF/spring/RBVDR406-app-test.xml",
		"classpath:/META-INF/spring/RBVDR406-arc.xml",
		"classpath:/META-INF/spring/RBVDR406-arc-test.xml" })
public class RBVDR406Test {

	@Spy
	private Context context;

	@Resource(name = "rbvdR406")
	private RBVDR406 rbvdR406;

	@Resource(name = "applicationConfigurationService")
	private ApplicationConfigurationService applicationConfigurationService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.rbvdR406;
		if(this.rbvdR406 instanceof Advised){
			Advised advised = (Advised) this.rbvdR406;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
	@Test
	public void executeTest(){
		when(this.applicationConfigurationService.getProperty(Constants.FLAG_RIMAC_LIST_QUOTATION)).thenReturn("true");
		List<ListQuotationDTO> listQuotation = rbvdR406.listQuotationByClient();
		Assert.assertEquals(0, context.getAdviceList().size());
		Assert.assertNotNull(listQuotation);
		Assert.assertEquals(1,listQuotation.size());
	}

	@Test
	public void executeTestNUll(){
		when(this.applicationConfigurationService.getProperty(Constants.FLAG_RIMAC_LIST_QUOTATION)).thenReturn("false");
		List<ListQuotationDTO> listQuotation = rbvdR406.listQuotationByClient();
		Assert.assertEquals(0, context.getAdviceList().size());
		Assert.assertNull(listQuotation);
		Assert.assertEquals(null,listQuotation);
	}
	
}
