package com.bbva.rbvd;

import com.bbva.elara.domain.transaction.Severity;
import com.bbva.elara.domain.transaction.response.HttpResponseCode;
import com.bbva.rbvd.dto.insuranceenterprise.listquotation.ListQuotationDTO;
import com.bbva.rbvd.lib.r406.RBVDR406;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class RBVDT40601PETransaction extends AbstractRBVDT40601PETransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDT40601PETransaction.class);

	@Override
	public void execute() {

		RBVDR406 rbvdR406 = this.getServiceLibrary(RBVDR406.class);

		List<ListQuotationDTO> listQuotation = rbvdR406.listQuotationByClient();

		if (!CollectionUtils.isEmpty(listQuotation) && !this.getAdviceList().isEmpty()){
			this.setData(listQuotation);
			this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_200, Severity.OK);
		}else{
			this.setSeverity(Severity.EWR);
		}
	}
}
