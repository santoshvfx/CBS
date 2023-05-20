//$Id: NtiHelper.java,v 1.9 2009/02/18 23:29:47 hw7243 Exp $
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
//#
//# History :
//# Date       | Author              | Notes
//# --------------------------------------------------------------------
//# 02/08/2008   Deepti Nayar            LS 7. Creation.
//# 02/21/2008	 Deepti Nayar			LS7 : Added two valid NetworkTypeValues FTTC_EGPON,FTTP_EGPON.
//#	02/27/2008	 Deepti Nayar			DR 86750,DR 86766:Throwing Invalid Network Type Exception for invalid NTI.
//# 02/28/2008	 Deepti Nayar 			DR 86953,DR86750,DR86676:Throwing Invalid Network Type Exception and changed the Exception Code description.
//# 03/06/2008	 Jon Costa				DR 87328 : Throwing Missing Network Type EXception when nti value is missing.

package com.sbc.eia.bis.rm.utilities;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm_ls_types.NetworkType2Values;
import com.sbc.eia.idl.rm_ls_types.NetworkType3Values;
import com.sbc.eia.idl.types.AttributeType;
import com.sbc.eia.idl.types.ObjectType;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.bis.facades.rm.transactions.ValidateFacility.ValidateFacilityConstants;
import com.sbc.eia.bis.facades.rm.transactions.ValidateFacility2.ValidateFacilty2NetworkTypes;

/**
* Class : NtiHelper Description: Helper used for validating NetworkType.
*  
*/
public class NtiHelper
{

/**
 * Constructor: NtiHelper
 * 
 * @author Deepti Nayar
 */

public NtiHelper()
{
    super();
}

/**
 * Determines the network type(s) sent by the client.
 * 
 * @param ObjectType[]
 *            aNtis
 * @return String[]
 * 
 * @author Rene Duka
 * @author Deepti Nayar
 */
public void determineNetworkTypes(ObjectType[] aNtis, 
                                  Utility aUtility, 
                                  Hashtable aProperties,
                                  String aTransactionType) 
	throws InvalidData,
           AccessDenied,
           BusinessViolation,
           SystemFailure,
           NotImplemented,
           ObjectNotFound,
           DataNotFound
{

    String aMethodName = "NtiHelper: determineNetworkTypes()";
    aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

    String aExceptionCode = ExceptionCode.ERR_RM_MISSING_NETWORK_TYPE;
    String aExceptionDescription = "Missing the required Network Type.";
    
    if (aNtis.length > 0)
    {
        for (int i = 0; i < aNtis.length; i++)
        {
            if (aNtis[i].aName.equalsIgnoreCase("networkTransport"))
            {
                if (!OptHelper.isAttributeTypeSeqOptEmpty(aNtis[i].aAttribute))
                {
                    AttributeType[] aAttributeTypes = aNtis[i].aAttribute.theValue();
                    for (int j = 0; j < aAttributeTypes.length; j++)
                    {
                        if (aAttributeTypes[j].aName.equalsIgnoreCase("ntiNegotiationValue"))
                        {
                            // retrieve network type
                            String aNetworkType = aAttributeTypes[j].aValue != null ? aAttributeTypes[j].aValue : "";
                           
                            // check for valid NetworkType
                            if ((aNetworkType.equalsIgnoreCase(NetworkType2Values.FTTN)
                                || aNetworkType.equalsIgnoreCase(NetworkType2Values.FTTNBP)
                                || aNetworkType.equalsIgnoreCase(NetworkType2Values.FTTP)
                                || aNetworkType.equalsIgnoreCase(NetworkType2Values.GPON)
                                || aNetworkType.equalsIgnoreCase(NetworkType2Values.RGPON)
                                || aNetworkType.equalsIgnoreCase("FTTC-EGPON")
                                || aNetworkType.equalsIgnoreCase("FTTP-EGPON")
                                || aNetworkType.equalsIgnoreCase("FTTP-RGPON")
                                || aNetworkType.equalsIgnoreCase("FTTP-GPON")
                                || aNetworkType.equalsIgnoreCase("FTTC-GPON"))
                                || (!aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)
                                	&& (aNetworkType.equalsIgnoreCase(NetworkType3Values.IPCO)
                                		|| aNetworkType.equalsIgnoreCase(NetworkType3Values.IPRT))))
                            {
                                aUtility.log(LogEventId.DEBUG_LEVEL_2, "Network Type ==> " + aNetworkType);
                                return;
                            }                                                      
                            else if (!aNetworkType.equals(""))
                            {
                                    aExceptionCode = ExceptionCode.ERR_RM_INVALID_NETWORK_TYPE;
                                	aExceptionDescription = "Invalid Network Type entered.";
                            }
                        }
                    }
                }
            }
        }
    }
    aUtility.throwException(aExceptionCode,
                            aExceptionDescription,
                            aProperties.get("BIS_NAME").toString(),
                            Severity.UnRecoverable);        
}
}