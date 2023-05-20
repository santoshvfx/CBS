package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.utilities;

import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.CreateServiceObjectRequestTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpRequestListImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpRequestTypeImpl;

/**
 * @author ML2917
 *
 * 
 * 
 */

public class CreateServiceObjectRequestHelper implements RequestHelperI
{

	private CreateServiceObjectRequestTypeImpl theRequest;

	private AttributeValueItemSeqHelper theAttributeValueHelper;


	/**************************************************************************
	 * @see java.lang.Object#Object()
	 */

	public CreateServiceObjectRequestHelper()
	{
		super();

		theRequest = new CreateServiceObjectRequestTypeImpl();

		theAttributeValueHelper = new AttributeValueItemSeqHelper();

	}


	/**************************************************************************
	 * Method getRequest.
	 * 
	 */

	public NpRequestListImpl getRequest()
	{

		return addRequest(setRequest(theRequest));

	}

	/**************************************************************************
	 * Method setRequest.
	 * @param requestType
	 * @return NpRequestTypeImpl
	 */

	private NpRequestTypeImpl setRequest(CreateServiceObjectRequestTypeImpl requestType)
	{

		requestType.setAttributeList(theAttributeValueHelper.getObject());

		NpRequestTypeImpl request = new NpRequestTypeImpl();

		request.setCreateServiceObjectRequest(requestType);

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
	 * Method addValue.
	 * 
	 * Add a name and value  attribute to the list.
	 * 
	 * @param name
	 * @param value
	 */

	public void addValue(String name, String value)
	{
		theAttributeValueHelper.add(name, value);
	}

}
