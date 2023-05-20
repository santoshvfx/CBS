// $Id: RmBisRequestsEncoderDecoder.java,v 1.2 2004/07/26 23:26:36 ml2917 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.
 */

package com.sbc.eia.bis.embus.service.netprovision.access;

import java.util.Properties;

import com.sbc.eia.bis.embus.service.access.DecoderException;
import com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ServiceException;

/** 
 *  Encoding and decoding XML messages for Net Provision.
 *  
 */

public class RmBisRequestsEncoderDecoder extends DefaultJAXBEncoderDecoder
{

	/**
	 * Constructor for cmRequestEncoderDecoder.
	 * Builds the encoder decoder obect passed to ServiceAccess to marshal and unmarshal xml.
	 * @param Sring
	 * @param Properties
	 */

	public RmBisRequestsEncoderDecoder(String packageName, Properties marshalUnmarshalOptions)
	{
		super(packageName, marshalUnmarshalOptions);
	}

	/**
	 * @see decode(String)
	 * Used to decode an xml message into objects.
	 */

	public Object[] decode(String message)
		throws DecoderException, ServiceException
	{

		return new Object[] { super.decode(message)[0] };

	}

	/**
	 * @see encode(Object[])
	 * Used to encode a number of objects into xml.
	 */

	public String encode(Object[] request)
		throws ServiceException, EncoderException
	{

		return (String) super.encode(request);

	}

}
