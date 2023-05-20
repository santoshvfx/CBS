//$Id: INQOSPResponseHelper.java,v 1.1 2008/02/14 23:15:16 sb7398 Exp $
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
import com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.NINQTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.RECTypeImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author sb7398
 *
 */
 
public class INQOSPResponseHelper {
	
	private Utility aUtility = null;
    private Hashtable aProperties = null;
    private int aNumberOfRecList = 0;
    private int aTCOMBBPCNT = 0;

    /**
     * Constructor: INQOSPResponseHelper
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Shyamali Banerjee
     */
    public INQOSPResponseHelper(Utility utility, Hashtable properties) 
    {
        aProperties = properties;
        aUtility = utility;
    }
    /**
     * Parses the response information.
     * 
     * @param INQOSPTypeImpl aResponse
     * 
     * @author Shyamali Banerjee
     */
    public void parseResponse(NINQTypeImpl aResponse,RECTypeImpl aRECResponse)
        throws 
            Exception
    {
        String aMethodName = "INQOSPResponseHelper: parseResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        try {
            // set aNumberOfRecLists
            aNumberOfRecList = aResponse.getREC().size();
            // set aRec
            ArrayList aRecArray = new ArrayList();
            for (int i=0; i < aNumberOfRecList; i++) 
            {   
                RECTypeImpl aRECTypeImpl= (RECTypeImpl) aResponse.getREC().get(i);
                REC aRec = new REC(aUtility, aProperties);
                aRec.parseRec(aRECTypeImpl);
                setATCOMBBPCNT(aRec.getTCOMBBPCNT());
            }
            
        }
        catch (Exception e) 
        {
        	aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in INQOSPResponseHelper: parseResponse() " + e.getMessage());

            // log: exception message
            StringBuffer eLogMessage = new StringBuffer();
            if (aRECResponse.getERRDSP().getERRMSG() != null)
            {
                eLogMessage.append(aRECResponse.getERRDSP().getERRMSG());
            }
            else
            {
                eLogMessage.append(e.getMessage());
            }
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            
            if (aRECResponse.getERRDSP().getERRNUM() != null)
            {
                eLogMessage.append(aRECResponse.getERRDSP().getERRNUM());
            }
            else
            {
                eLogMessage.append(e.getMessage());
            }
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());

            // throw: Exception
            throw new Exception(eLogMessage.toString());
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
    }
    /**
     * @return Returns the aTCOMBBPCNT.
     *  @author Jon Costa
     */
    public int getATCOMBBPCNT()
    {
        return this.aTCOMBBPCNT;
    }
    /**
     * @param atcombbpcnt The aTCOMBBPCNT to set.
     *  @author Jon Costa
     */
    public void setATCOMBBPCNT(String atcombbpcnt)
    {
        if(atcombbpcnt != null)
        {
            this.aTCOMBBPCNT = Integer.valueOf(atcombbpcnt).intValue();
        }
    }
}