package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.utilities;

import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.DeleteServiceObjectRequestTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpRequestListImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpRequestTypeImpl;

/**
 * @author ML2917
 *
 *
 */

public class DeleteServiceObjectRequestHelper implements RequestHelperI
{

	private DeleteServiceObjectRequestTypeImpl theRequest;

	/**************************************************************************
	 * Constructor for DeleteServiceObjectRequest.
	 *
	 * 
	 * 
	 * @see java.lang.Object#Object()
	 */

	public DeleteServiceObjectRequestHelper()
	{
		super();

		theRequest = new DeleteServiceObjectRequestTypeImpl();

	}

	/**************************************************************************
	 * Method getObejct.
	 * 
	 */

	public NpRequestListImpl getRequest()
	{

		return addRequest(setRequest(theRequest));

	}

	/**************************************************************************
	 * Method getRequest.
	 * @param requestType
	 * @return NpRequestTypeImpl
	 */

	private NpRequestTypeImpl setRequest(DeleteServiceObjectRequestTypeImpl requestType)
	{

		NpRequestTypeImpl request = new NpRequestTypeImpl();

		request.setDeleteServiceObjectRequest(requestType);

		return request;

	}

	/**************************************************************************
	 * Method addRequest.
	 * @param request
	 * 
	 *  Add a request type to the top level request.
	 * 
	 */

	private static NpRequestListImpl addRequest(NpRequestTypeImpl request)
	{

		NpRequestListImpl NpRequest = new NpRequestListImpl();

		NpRequest.getNpRequest().add(request);

		return NpRequest;

	}

	/**************************************************************************
	 * Method setClassName.
	 * @param className
	 * 
	 * Set the className.
	 * 
	 */

	public void setClassName(String className)
	{
		theRequest.setClassName(className);
	}

	/**************************************************************************
	 * Method setId.
	 * @param id
	 * 
	 *  Set the id.
	 */

	public void setId(String id)
	{
		theRequest.setId(id);
	}

}
