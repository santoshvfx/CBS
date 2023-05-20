// $Id: CodesRCAccessHelper.java,v 1.6 2007/02/15 21:30:24 cy4727 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of AT&T Services Inc. and authorized Affiliates of AT&T Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies. Copying or reproduction without prior 
 * written approval is prohibited.
 * Copyright (c) 2007 AT&T Services, Inc.
 * All rights reserved.
 */
 
//# History :
//# Date      | Author        | Notes
//# 12/15/2006  Changchuan Yin  Created.
//# 02/05/2007  Changchuan Yin  Updated the function key


package com.sbc.eia.bis.embus.service.codes.access;

import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Properties;

import javax.xml.bind.Marshaller;

import com.att.lms.rc.enumeration.RcTypesEnum;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceHelper;
import com.sbc.eia.bis.embus.service.access.ServiceHelperSelfTestException;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceRequest.GetClliCICAvailRequest;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceRequest.impl.GetClliCICAvailRequestImpl;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceRequest.impl.GetClliCICAvailRequestTypeImpl;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.ResponseMessage;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.impl.ResponseMessageTypeImpl;
import com.sbc.eia.bis.embus.service.decryption.DecryptionServiceHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;

//public class CodesRCAccessHelper extends ServiceHelper
public class CodesRCAccessHelper extends DecryptionServiceHelper
{

    public final static String CODES_SERVICE_NAME = "CODESRC";
	public final static String CODES_REQUEST = "CODESRCRetrieveServiceProvidersForResourceRequest";
				
	private Properties marshalUnmarshalOptions;
	private Properties jmsPropertyTagAndValuesToSet;

	public CodesRCAccessHelper(Hashtable properties, Logger logger)
		throws ServiceException
	{
	    /* 02/14/2007
		super(properties, logger, CODES_SERVICE_NAME);

		setServiceAccess(
			new CodesRCAccess(
				getEnvironmentName(),
				getClientConfigFileName(),
				logger));
				
		*/	

		super(properties, logger, CODES_SERVICE_NAME);
		//m_selfTestFunctionKey = "CODESRCRetrieveServiceProvidersForResourceRequest";
		
		// Set marshalUnmarshalOptions properties.
		marshalUnmarshalOptions = new Properties();
		marshalUnmarshalOptions.setProperty(
			Marshaller.JAXB_FORMATTED_OUTPUT,
			"true");

		setPropertyTagAndValues(properties);
	}

	private void setPropertyTagAndValues(Hashtable props) {
		jmsPropertyTagAndValuesToSet = new Properties();
		jmsPropertyTagAndValuesToSet.setProperty("USERID", (String) props.get("CODESRC_USERID"));
		jmsPropertyTagAndValuesToSet.setProperty("PASSWORD", (String) props.get("CODESRC_PASSWORD"));
		jmsPropertyTagAndValuesToSet.setProperty("CLIENTID", (String) props.get("CODESRC_CLIENTID"));
		jmsPropertyTagAndValuesToSet.setProperty("embusMessageTag", (String) props.get("CODESRC_MESSAGETAG"));
		jmsPropertyTagAndValuesToSet.setProperty("DESTINATION", (String) props.get("CODESRC_DESTINATION"));
		jmsPropertyTagAndValuesToSet.setProperty("GROUPID", (String) props.get("CODESRC_GROUPID"));
		jmsPropertyTagAndValuesToSet.setProperty("embusMessagingMode", (String) props.get("CODESRC_MESSAGINGMODE"));
		jmsPropertyTagAndValuesToSet.setProperty("embusApplicationID", (String) props.get("CODESRC_APPLICATIONID"));
	}

	

	public ResponseMessage rmBisRequests(GetClliCICAvailRequest request)
		throws ServiceException
	{
       // Set the encoder for the request class.

		getServiceAccess().setEncoder(
			new RmBisRequestsEncoderDecoder(
		       GetClliCICAvailRequest.class.getPackage().getName(),
		marshalUnmarshalOptions));

		// Set the decoder for the response class.

		getServiceAccess().setDecoder(
			new RmBisRequestsEncoderDecoder(
		       ResponseMessage.class.getPackage().getName(),
		      marshalUnmarshalOptions));
		
		return (ResponseMessage) getServiceAccess().sendAndReceive(CODES_REQUEST,
			new Object[] { request }, jmsPropertyTagAndValuesToSet)[0];
	}
	
    public ResponseMessage rmBisRequests
	(GetClliCICAvailRequest request,
				Properties jmsResponseProperties)
			throws ServiceException
		{

			// Set the encoder for the request class.
			getServiceAccess().setDecoder(
			new RmBisRequestsEncoderDecoder(
			   ResponseMessage.class.getPackage().getName(),
			  marshalUnmarshalOptions));
		
		
			return (ResponseMessage) getServiceAccess().sendAndReceive(CODES_REQUEST,
			new Object[] { request },jmsResponseProperties)[0];
		}

	/**
	 * Method selfTest.
	 */
	public BisContext selfTest(BisContext aBisContext) throws ServiceException
	{

		log(LogEventId.DEBUG_LEVEL_2, "ServiceHelper::selfTest started.");

		try
		{
			GetClliCICAvailRequest request = new GetClliCICAvailRequestImpl();
			request.setCLLI("SHOKCA01DS1");
			request.setCICFLAG(new BigInteger("1"));
			ResponseMessage response = this.rmBisRequests(request);
		}
		catch(Exception e)
		{
			log(LogEventId.DEBUG_LEVEL_2, "ServiceHelper::selfTest failed. Exception message: " + e.getMessage());
			throw new ServiceHelperSelfTestException("ServiceHelper::selfTest failed! " + e.getMessage(),	e);
		}

		log(LogEventId.DEBUG_LEVEL_2, "ServiceHelper::selfTest completed.");

		return aBisContext;
	}


	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.embus.service.access.ServiceHelper#sendAndReceiveSelfTestMessage()
	 */
	protected void sendAndReceiveSelfTestMessage() throws ServiceHelperSelfTestException {
		// TODO Auto-generated method stub
		
	}

}
