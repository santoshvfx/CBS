package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.utilities;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.ListerRequestTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpRequestListImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpRequestTypeImpl;
	

/**
 * @author ML2917
 *
 * Request and Response impl for theNpConnector adapter.
 * 
 */

public class ListerRequestHelper extends ListerHelper {

	private String theClassName;

	/**
	 * @see java.lang.Object#Object()
	 */

	public ListerRequestHelper() {
		super();

		theClassName = "";

	}

	/**
	 * Method createRequest.
	 * @return NpRequestListImpl
	 */

	public NpRequestListImpl getRequest() {

		return addRequest(getListerRequest());

	}

	/**************************************************************************
	 * Method getListerRequest.
	 * @param requestType
	 * @return NpRequestTypeImpl
	 *
	 * Create a ListerRequest.
	 * 
	 */

	private NpRequestTypeImpl getListerRequest() {

		ListerRequestTypeImpl requestType = getObject();

		requestType.setClassName(theClassName);

		NpRequestTypeImpl request = new NpRequestTypeImpl();

		request.setListerRequest(requestType);

		return request;

	}

	/**************************************************************************
	 * Method setClassName.
	 * @param className
	 * 
	 * Set the className.
	 * 
	 */

	public void setClassName(String className) {
		theClassName = className;
	}

	/**************************************************************************
	 * Method addRequest.
	 * @param request
	 * 
	 *  Add a request type to the top level request.
	 * 
	 */

	private NpRequestListImpl addRequest(NpRequestTypeImpl request) {

		NpRequestListImpl NpRequest = new NpRequestListImpl();

		NpRequest.getNpRequest().add(request);

		return NpRequest;

	}

}
