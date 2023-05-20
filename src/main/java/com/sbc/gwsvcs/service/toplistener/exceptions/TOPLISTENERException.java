// $Id: TOPLISTENERException.java,v 1.1 2005/07/22 21:42:51 jn1936 Exp $

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
//# 6/15/2005 | Jinmin Ni	        | create

package com.sbc.gwsvcs.service.toplistener.exceptions;

import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;

public class TOPLISTENERException extends ServiceException
{
	/**
	 * Constructor accepting code and text.
	 * @param arg1 java.lang.String
	 * @param arg2 java.lang.String
	 */
	public TOPLISTENERException(String arg1, String arg2)
	{
		super(arg1, arg2);
	}
	/**
	 * Constructor accepting code, text and original code.
	 * @param arg1 java.lang.String
	 * @param arg2 java.lang.String
	 * @param arg3 java.lang.String
	 */
	public TOPLISTENERException(String arg1, String arg2, String arg3)
	{
		super(arg1, arg2, arg3);
	}
}
