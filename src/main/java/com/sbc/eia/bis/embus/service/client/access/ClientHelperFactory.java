//$Id: ClientHelperFactory.java,v 1.4 2007/11/14 15:27:04 rd2842 Exp $
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
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 09/07/2007  Rene Duka             Creation.
//# 09/12/2007  Rene Duka             Modified for LS6.
//# 11/13/2007  Rene Duka             CR 15842: Added mobilityCSI as a client.

package com.sbc.eia.bis.embus.service.client.access;

import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.amss.AMSSHelper;
import com.sbc.eia.bis.embus.service.bbnms.BBNMSHelper;
import com.sbc.eia.bis.embus.service.first.FIRSTHelper;
import com.sbc.eia.bis.embus.service.mobilityCSI.MobilityCSIHelper;
import com.sbc.eia.bis.embus.service.oms.OMSHelper;
import com.sbc.eia.bis.embus.service.rm.RMHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;

/**
* @author rd2842
*
*/
public class ClientHelperFactory 
{
    private OMSHelper aOMSHelper;
    private AMSSHelper aAMSSHelper;
    private FIRSTHelper aFIRSTHelper;
    private BBNMSHelper aBBNMSHelper;
    private MobilityCSIHelper aMobilityCSIHelper;
    private RMHelper aRMHelper;

    /**
     * Constructor for ClientHelperFactory.
     */
    public ClientHelperFactory() 
    {
        aOMSHelper = null;
        aAMSSHelper = null;
        aFIRSTHelper = null;
        aBBNMSHelper = null;
        aMobilityCSIHelper = null;
        aRMHelper = null;
    }

    /**
     * Method: getInstance
     *
     * Create an object using the BisContext.Application as the key.
     * If the obejct exists in the cache return it otherwise create a new object.
     * Throw an exception if none of the keys match.
     * 
     * @return ClientHelperIF
     * @param BisContext    aContext
     * @param Logger        aLogger
     * @param Hashtable     aProperties
     * @throws Exception
     */
    public ClientHelperIF getInstance(
        BisContext aContext,
        Logger aLogger,
        Hashtable aProperties)
        throws 
            Exception 
    {
        String aMethodName = "ClientHelperFactory: getInstance()";
        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        ObjectPropertyManager aContextOPM = new ObjectPropertyManager(aContext.aProperties);
        String aPropertyValue = aContextOPM.getValue(BisContextProperty.APPLICATION); 
        String aHelperMessage = "Using " + aPropertyValue + " client helper";

        // OMS
        if (aPropertyValue.equalsIgnoreCase(ClientHelperConstants.OMS_SERVICE_NAME)) 
        {
            if (aOMSHelper == null) 
            {
                aOMSHelper = new OMSHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aOMSHelper;
        } 

        // AMSS
        if (aPropertyValue.equalsIgnoreCase(ClientHelperConstants.AMSS_SERVICE_NAME)) 
        {
            if (aAMSSHelper == null) 
            {
                aAMSSHelper = new AMSSHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aAMSSHelper;
        } 

        // FIRST
        if (aPropertyValue.equalsIgnoreCase(ClientHelperConstants.FIRST_SERVICE_NAME)) 
        {
            if (aFIRSTHelper == null) 
            {
                aFIRSTHelper = new FIRSTHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aFIRSTHelper;
        } 

        // BBNMS
        if (aPropertyValue.equalsIgnoreCase(ClientHelperConstants.BBNMS_SERVICE_NAME)) 
        {
            if (aBBNMSHelper == null) 
            {
                aBBNMSHelper = new BBNMSHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aBBNMSHelper;
        } 

        // mobilityCSI
        if (aPropertyValue.equalsIgnoreCase(ClientHelperConstants.MOBILITY_CSI_SERVICE_NAME)) 
        {
            if (aMobilityCSIHelper == null) 
            {
                aMobilityCSIHelper = new MobilityCSIHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aMobilityCSIHelper;
        } 

        // RM
        if (aPropertyValue.equalsIgnoreCase(ClientHelperConstants.RM_SERVICE_NAME)) 
        {
            if (aRMHelper == null) 
            {
                aRMHelper = new RMHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aRMHelper;
        } 

        aLogger.log(LogEventId.DEBUG_LEVEL_1, "Invalid Client: No object instantiated for ClientHelper");
        aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
      
        throw new Exception("No object instantiated for ClientHelper");
    }

    /**
     * Method: getInstance
     *
     * Create an object using the aClientName as the key.
     * If the obejct exists in the cache return it otherwise create a new object.
     * Throw an exception if none of the keys match.
     * 
     * @return ClientHelperIF
     * @param String        aClientName
     * @param Logger        aLogger
     * @param Hashtable     aProperties
     * @throws Exception
     */
    public ClientHelperIF getInstance(
        String aClientName,
        Logger aLogger,
        Hashtable aProperties)
        throws 
            Exception 
    {
        String aMethodName = "ClientHelperFactory: getInstance()";
        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        String aHelperMessage = "Using " + aClientName + " client helper";

        // OMS
        if (aClientName.equalsIgnoreCase(ClientHelperConstants.OMS_SERVICE_NAME)) 
        {
            if (aOMSHelper == null) 
            {
                aOMSHelper = new OMSHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aOMSHelper;
        } 

        // AMSS
        if (aClientName.equalsIgnoreCase(ClientHelperConstants.AMSS_SERVICE_NAME)) 
        {
            if (aAMSSHelper == null) 
            {
                aAMSSHelper = new AMSSHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aAMSSHelper;
        } 

        // FIRST
        if (aClientName.equalsIgnoreCase(ClientHelperConstants.FIRST_SERVICE_NAME)) 
        {
            if (aFIRSTHelper == null) 
            {
                aFIRSTHelper = new FIRSTHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aFIRSTHelper;
        } 

        // BBNMS
        if (aClientName.equalsIgnoreCase(ClientHelperConstants.BBNMS_SERVICE_NAME)) 
        {
            if (aBBNMSHelper == null) 
            {
                aBBNMSHelper = new BBNMSHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aBBNMSHelper;
        } 

        // mobilityCSI
        if (aClientName.equalsIgnoreCase(ClientHelperConstants.MOBILITY_CSI_SERVICE_NAME)) 
        {
            if (aMobilityCSIHelper == null) 
            {
                aMobilityCSIHelper = new MobilityCSIHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aMobilityCSIHelper;
        } 

        // RM
        if (aClientName.equalsIgnoreCase(ClientHelperConstants.RM_SERVICE_NAME)) 
        {
            if (aRMHelper == null) 
            {
                aRMHelper = new RMHelper(aLogger, aProperties);
            }
            aLogger.log(LogEventId.DEBUG_LEVEL_1, aHelperMessage);
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
            return aRMHelper;
        } 

        aLogger.log(LogEventId.DEBUG_LEVEL_1, "Invalid Client: No object instantiated for ClientHelper");
        aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
      
        throw new Exception("No object instantiated for ClientHelper");
    }
}
