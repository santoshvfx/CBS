//$Id: RmBisXngEncoderDecoder.java,v 1.3 2005/06/01 23:04:27 jn1936 Exp $

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
//# 4/14/2005 | Jinmin Ni	        | create
//# 6/01/2005 | Jinmin Ni           | Changed to use new copyright notice
 
package com.sbc.eia.bis.embus.service.xng.access;

import java.util.Properties;

import com.sbc.eia.bis.embus.service.access.DecoderException;
import com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ServiceException;

/**
 * @author jn1936
 *
 */
public class RmBisXngEncoderDecoder extends DefaultJAXBEncoderDecoder
{


	public RmBisXngEncoderDecoder(String packageName, Properties marshalUnmarshalOptions)
	{
		super(packageName, marshalUnmarshalOptions);
	}



	public Object[] decode(String message)
		throws DecoderException, ServiceException
	{

		return new Object[] { super.decode(message)[0] };

	}



	public String encode(Object[] request)
		throws ServiceException, EncoderException
	{

		return (String) super.encode(request);

	}
}
