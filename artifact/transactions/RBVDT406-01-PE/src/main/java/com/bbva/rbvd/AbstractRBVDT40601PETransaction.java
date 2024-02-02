package com.bbva.rbvd;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.rbvd.dto.insuranceenterprise.listquotation.ListQuotationDTO;
import java.util.List;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractRBVDT40601PETransaction extends AbstractTransaction {

	public AbstractRBVDT40601PETransaction(){
	}


	/**
	 * Set value for List<ListQuotationDTO> output parameter data
	 */
	protected void setData(final List<ListQuotationDTO> field){
		this.addParameter("data", field);
	}
}
