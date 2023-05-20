//$Id: RmBisSoaEncoderDecoder.java,v 1.1 2006/06/02 20:48:56 jp2854 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//#06/02/2006   Jyothi Jasti         Creation for SOA EMBus service wrapper.

package com.sbc.eia.bis.embus.service.soa;

import java.util.Properties;

import com.sbc.eia.bis.embus.service.access.DecoderException;
import com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ServiceException;

public class RmBisSoaEncoderDecoder extends DefaultJAXBEncoderDecoder {

	/**
	 * Constructor
	 * @param packageName 
	 * @param marshalUnmarshalOptions
	 */
	public RmBisSoaEncoderDecoder(
		String packageName,
		Properties marshalUnmarshalOptions) {
		super(packageName, marshalUnmarshalOptions);
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.embus.service.access.IDecoder#decode(java.lang.String)
	 */
	public Object[] decode(String message)
		throws DecoderException, ServiceException {

		return new Object[] { super.decode(message)[0] };

	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.embus.service.access.IEncoder#encode(java.lang.Object[])
	 */
	public String encode(Object[] request)
		throws ServiceException, EncoderException {

		return (String) super.encode(request);

	}

}