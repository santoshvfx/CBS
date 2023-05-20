//$Id: TIRKSJXError.java,v 1.2 2011/04/07 03:10:48 rs278j Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;

/**
 * Class for handling the TIRKSJX errors
 * 
 * @author js7440
 */
public class TIRKSJXError 
{
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	private String errorType = null;
	private String errorMessage = null;
	
	/**
	 * Constructor for class TIRKSJXError
	 * 
	 * @param Utility aUtility
	 * @param Hashtable aProperties
	 */
	public TIRKSJXError(Utility aUtility, Hashtable aProperties) 
	{
		utility = aUtility;
		properties = aProperties;
	}

	/**
	 * get the Error Message
	 * 
	 * @return String
	 */
	public String getErrorMessage() 
	{
		return errorMessage;
	}

	/**
	 * set the Error Message
	 * 
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) 
	{
		this.errorMessage = errorMessage;
	}

	/**
	 * get the Error Type
	 * 
	 * @return STring
	 */
	public String getErrorType() 
	{
		return errorType;
	}

	/**
	 * set the Error Type
	 * 
	 * @param String errorType
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
}
