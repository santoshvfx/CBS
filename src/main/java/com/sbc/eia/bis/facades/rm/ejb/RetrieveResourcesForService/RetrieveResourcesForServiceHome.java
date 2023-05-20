/*
 * Created on Feb 24, 2005
 *
 */
package com.sbc.eia.bis.facades.rm.ejb.RetrieveResourcesForService;

/**
 * @author va6483
 * @creation date:02/24/05
 * Local Home Interface for RetrieveResourcesForService Enterprise Bean
 * RetrieveResourcesForServiceHome
 */

public interface RetrieveResourcesForServiceHome
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
		.RetrieveResourcesForService
		.RetrieveResourcesForService create()
		throws javax.ejb.CreateException;
}
