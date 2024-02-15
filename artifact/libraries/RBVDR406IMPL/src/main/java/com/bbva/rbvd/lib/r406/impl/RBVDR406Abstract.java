package com.bbva.rbvd.lib.r406.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.pisd.lib.r402.PISDR402;
import com.bbva.rbvd.lib.r406.RBVDR406;

/**
 * This class automatically defines the libraries and utilities that it will use.
 */
public abstract class RBVDR406Abstract extends AbstractLibrary implements RBVDR406 {

	protected ApplicationConfigurationService applicationConfigurationService;

	protected PISDR402 pisdR402;


	/**
	* @param applicationConfigurationService the this.applicationConfigurationService to set
	*/
	public void setApplicationConfigurationService(ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

	/**
	* @param pisdR402 the this.pisdR402 to set
	*/
	public void setPisdR402(PISDR402 pisdR402) {
		this.pisdR402 = pisdR402;
	}

}