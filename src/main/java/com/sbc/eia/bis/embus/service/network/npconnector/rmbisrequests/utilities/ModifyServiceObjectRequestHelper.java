package com
	.sbc
	.eia
	.bis
	.embus
	.service
	.network
	.npconnector
	.rmbisrequests
	.utilities;

import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.ModifyServiceObjectRequestTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpRequestListImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpRequestTypeImpl;

/**
 * @author ML2917
 *
 *
 */
public class ModifyServiceObjectRequestHelper implements RequestHelperI
{

	private ModifyServiceObjectRequestTypeImpl theRequest;

	private AttributeValueItemSeqHelper theAttributeValueHelper;

	/**************************************************************************
	 * @see java.lang.Object#Object()
	 */

	public ModifyServiceObjectRequestHelper() {
		super();

		theRequest = new ModifyServiceObjectRequestTypeImpl();

		theAttributeValueHelper = new AttributeValueItemSeqHelper();

	}

	/**************************************************************************
	 * Method getRequest.
	 * 
	 */

	public NpRequestListImpl getRequest() {

		return addRequest(setRequest(theRequest));

	}

	/**************************************************************************
	 * Method getRequest.
	 * @param requestType
	 * @return NpRequestTypeImpl
	 */

	private NpRequestTypeImpl setRequest(ModifyServiceObjectRequestTypeImpl requestType) {

		requestType.setAttributeList(theAttributeValueHelper.getObject());

		NpRequestTypeImpl request = new NpRequestTypeImpl();

		request.setModifyServiceObjectRequest(requestType);

		return request;

	}

	/**************************************************************************
	 * Method addRequest.
	 * @param request
	 * 
	 *  Add a request type to the top level request.
	 * 
	 */

	private static NpRequestListImpl addRequest(NpRequestTypeImpl request) {

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

	public void setClassName(String className) {
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

	public void addValue(String name, String value) {
		theAttributeValueHelper.add(name, value);
	}

	/* temporary fix put in by LF Team */
	public void setId(String id) {
		theRequest.setId(id);
	}

}
