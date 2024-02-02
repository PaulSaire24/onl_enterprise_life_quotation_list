package com.bbva.rbvd;

import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.TransactionParameter;
import com.bbva.elara.domain.transaction.request.TransactionRequest;
import com.bbva.elara.domain.transaction.request.body.CommonRequestBody;
import com.bbva.elara.domain.transaction.request.header.CommonRequestHeader;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.PlanDTO;
import com.bbva.rbvd.dto.insuranceenterprise.commons.dto.ProductDTO;
import com.bbva.rbvd.dto.insuranceenterprise.listquotation.ListQuotationDTO;
import com.bbva.rbvd.lib.r406.RBVDR406;
import com.bbva.elara.test.osgi.DummyBundleContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/elara-test.xml",
		"classpath:/META-INF/spring/RBVDT40601PETest.xml" })
public class RBVDT40601PETransactionTest {

	@Autowired
	private RBVDT40601PETransaction transaction;

	@Resource(name = "dummyBundleContext")
	private DummyBundleContext bundleContext;

	@Mock
	private CommonRequestHeader header;
	@Resource(name = "rbvdR406")
	private RBVDR406 rbvdr406;

	@Mock
	private TransactionRequest transactionRequest;

	@Before
	public void initializeClass() throws Exception {
		// Initializing mocks
		MockitoAnnotations.initMocks(this);
		// Start BundleContext
		this.transaction.start(bundleContext);
		// Setting Context
		this.transaction.setContext(new Context());
		// Set Body
		CommonRequestBody commonRequestBody = new CommonRequestBody();
		commonRequestBody.setTransactionParameters(new ArrayList<>());
		this.transactionRequest.setBody(commonRequestBody);
		// Set Header Mock
		this.transactionRequest.setHeader(header);
		// Set TransactionRequest
		this.transaction.getContext().setTransactionRequest(transactionRequest);
	}

	@Test
	public void testNotNull(){
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
	    when(this.rbvdr406.executeListQuotationByClient()).thenReturn(listQuotation);
		Assert.assertNotNull(this.transaction);
		this.transaction.execute();
	}

	// Add Parameter to Transaction
	private void addParameter(final String parameter, final Object value) {
		final TransactionParameter tParameter = new TransactionParameter(parameter, value);
		transaction.getContext().getParameterList().put(parameter, tParameter);
	}

	// Get Parameter from Transaction
	private Object getParameter(final String parameter) {
		final TransactionParameter param = transaction.getContext().getParameterList().get(parameter);
		return param != null ? param.getValue() : null;
	}
}
