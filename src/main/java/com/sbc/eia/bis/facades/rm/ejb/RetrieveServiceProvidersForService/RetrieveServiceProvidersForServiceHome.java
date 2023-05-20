/*
 * Created on Feb 24, 2005
 *
 */
package com.sbc.eia.bis.facades.rm.ejb.RetrieveServiceProvidersForService;

/**
 * @author va6483
 * @creation date:02/24/05
 * Local Home Interface for RetrieveServiceProvidersForService Enterprise Bean
 * RetrieveServiceProvidersForServiceHome
 */

public interface RetrieveServiceProvidersForServiceHome
	extends javax.ejb.EJBLocalHome {

	/**
	 * Creates a default instance of Session Bean
	 */

	public com
		.sbc
		.eia
		.bis
		.facades
		.rm
		.ejb
		.RetrieveServiceProvidersForService
		.RetrieveServiceProvidersForService create()
		throws javax.ejb.CreateException;
}
