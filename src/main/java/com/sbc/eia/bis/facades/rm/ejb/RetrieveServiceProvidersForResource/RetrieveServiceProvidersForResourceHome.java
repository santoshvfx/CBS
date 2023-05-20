/*
 * Created on Feb 24, 2005
 *
 */
package com.sbc.eia.bis.facades.rm.ejb.RetrieveServiceProvidersForResource;

/**
 * @author va6483
 * @creation date:02/24/05
 * Local Home Interface for RetrieveServiceProvidersForResource Enterprise Bean
 * RetrieveServiceProvidersForResourceHome
 */

public interface RetrieveServiceProvidersForResourceHome
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
		.RetrieveServiceProvidersForResource
		.RetrieveServiceProvidersForResource create()
		throws javax.ejb.CreateException;
}
