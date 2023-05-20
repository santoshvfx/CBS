//$Id: INQTERMRequestHelper.java,v 1.2 2009/03/12 18:26:20 js7440 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date      |  Author              | Notes
//# --------------------------------------------------------------------
//# 01/14/2009   Julius Sembrano       Creation.
//# 03/12/2009   Julius Sembrano       DR123847: INQTERM call for the new VRAD functionality seems to not be working correctly.

package com.sbc.eia.bis.BusinessInterface.rm.LFACS;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.facsrc.InqTermRequest.impl.INQTERMTypeImpl.REQTypeImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.lim_types.Address;

/**
 * Class      : INQTERMRequestHelper
 * Description: Helper used for handling the request of the INQTERM contract of FACSRCAccess.  
 */
public class INQTERMRequestHelper 
{

	protected REQTypeImpl aRequest;
	
    /**
     * Constructor: INQTERMRequestHelper
     * 
     * @author Julius Sembrano
     */
	public INQTERMRequestHelper()
	{
		// initialize request
		aRequest = new REQTypeImpl();
		
		aRequest.setEMP("");
		aRequest.setTEA("");
	}

	/**
     * Constructor: INQTERMRequestHelper
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Julius Sembrano
     */	
	public INQTERMRequestHelper(
			Utility aUtility,
			Hashtable aProperties,
			String aTEA)
	{
		this();
		try
		{
			// set EMP
			aRequest.setEMP((String) aProperties.get("BIS_NAME"));
			// set TEA
			aRequest.setTEA(aTEA);
		}
        catch (Exception e) 
        {
            String aExceptionMessage = "Error in building REQTypeImpl" + " - "
                                            + e.getMessage() + " - "
                                            + "IGNORED: Ok to continue.";

            aUtility.log(LogEventId.DEBUG_LEVEL_2, aExceptionMessage);
        }
	}

    /**
     * Get the request.
     * 
     * @return REQTypeImpl
     * 
     * @author Julius Sembrano
     */
	public REQTypeImpl getRequest() 
	{
		return aRequest;
	}
}
