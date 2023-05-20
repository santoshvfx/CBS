//$Id: REC.java,v 1.1 2008/02/14 23:15:39 sb7398 Exp $
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
//# 01/29/2008   Shyamali Banerjee    Creation.

package com.sbc.eia.bis.BusinessInterface.rm.LFACS;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.RECTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ADDRTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.SEGTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl.RSPTypeImpl.LOOPTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl.RSPTypeImpl.LOOPTypeImpl.SOTypeImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;


public class REC {
	
	private Utility aUtility = null;
	private Hashtable aProperties = null;
	private String aTCOMBBPCNT = null;
	
    /**
     * Constructor: REC
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Shyamali Banerjee
     */
	public REC(Utility utility, Hashtable properties) {
		aProperties = properties;
		aUtility = utility;
	}
	
	/**
     * Parses the loop information from LFACS response.
     * 
     * @param RECTypeImpl aRECTypeImpl
     * 
     * @author Shyamali Banerjee
     */
	public void parseRec(RECTypeImpl aRECTypeImpl) 
    {
		try 
        {
			 // set the TCOMBBPCNT 
            if (aRECTypeImpl.getCTL().getTCOMBBPCNT() != null)            
                aTCOMBBPCNT = aRECTypeImpl.getCTL().getTCOMBBPCNT();
            
		}
        catch (NullPointerException npe) 
        {
            aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in Rec: parseRec() " + npe.getMessage());
		}
        catch (Exception e) 
        {
            aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in Rec: parseRec() " + e.getMessage());
		} 
	}
	/**
	 * Get TCOMBBPCNT.
     * 
	 * @return String
     * 
     * @author Shyamali Banerjee
     */
	public String getTCOMBBPCNT() 
    {
		return aTCOMBBPCNT;
	}
	

}