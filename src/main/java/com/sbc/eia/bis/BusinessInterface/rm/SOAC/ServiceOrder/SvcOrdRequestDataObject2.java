// $Id: SvcOrdRequestDataObject2.java,v 1.2 2008/02/25 07:34:12 op1664 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//# RESTRICTED - PROPRIETARY INFORMATION
//# The information herein is for use only by authorized employees
//# of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//# Inc., and is not for general distribution within or outside the
//# respective companies.
//# Copying or reproduction without prior written approval is prohibited.
//#
//# © 2008-2010 AT&T Intellectual Property All rights reserved.
//#
//# History :
//# Date | Author | Notes
//# --------------------------------------------------------------------------
//# 01/29/2008 Ott Phannavong Creation
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrdRequestDataObject;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.VoipReqSnEDataObject;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

public class SvcOrdRequestDataObject2 extends SvcOrdRequestDataObject
{
    protected StringOpt oldNetworkType = null;

    protected StringOpt secondaryCircuitID = null;

    protected StringOpt secondaryRelatedCircuitID = null;

    protected CompositeObjectKey billingAccountNumber = null;

    /**
     * @param aSOACServiceOrderNumber
     * @param aSOACServiceOrderCorrectionSuffix
     * @param aNetworkType
     * @param aOrderActionId
     * @param aOrderNumber
     * @param aOrderActionType
     * @param aCompletionIndicator
     * @param aSubActionType
     * @param aCircuitId
     * @param aServiceLocation
     * @param aOriginalDueDate
     * @param aSubsequentDueDate
     * @param aApplicationDate
     * @param aTDMTelphoneNumber
     * @param aRelatedServiceOrderNumber
     * @param aLineShareDisconnectFlag
     * @param aClassOfService
     * @param aResendFlag
     * @param aEntity
     * @param aEntityPlatform
     * @param aProperties
     * @param aOriginatingHost
     * @param aRegion
     * @param voipSnEData
     * @param NpaNxx
     * @param SpecialCondData
     */
    public SvcOrdRequestDataObject2(String aSOACServiceOrderNumber,
            String aSOACServiceOrderCorrectionSuffix, String aNetworkType,
            String aOrderActionId, String aOrderNumber,
            String aOrderActionType, BooleanOpt aCompletionIndicator,
            StringOpt aSubActionType, String aCircuitId,
            Location aServiceLocation, EiaDate aOriginalDueDate,
            EiaDateOpt aSubsequentDueDate, EiaDate aApplicationDate,
            StringOpt aTDMTelphoneNumber, StringOpt aRelatedServiceOrderNumber,
            BooleanOpt aLineShareDisconnectFlag, String aClassOfService,
            BooleanOpt aResendFlag, String aEntity, String aEntityPlatform,
            ObjectPropertySeqOpt aProperties, String aOriginatingHost,
            String aRegion, VoipReqSnEDataObject[] aVoipSnEData,
            String aNpaNxx, String aSpecialCondData,
            String aApplicationIndicator)
    {
        super(aSOACServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
                aNetworkType, aOrderActionId, aOrderNumber, aOrderActionType,
                aCompletionIndicator, aSubActionType, aCircuitId,
                aServiceLocation, aOriginalDueDate, aSubsequentDueDate,
                aApplicationDate, aTDMTelphoneNumber,
                aRelatedServiceOrderNumber, aLineShareDisconnectFlag,
                aClassOfService, aResendFlag, aEntity, aEntityPlatform,
                aProperties, aOriginatingHost, aRegion, aVoipSnEData, aNpaNxx,
                aSpecialCondData, aApplicationIndicator);

    }

    /**
     * @param billingAccountNumber
     *            The billingAccountNumber to set.
     */
    public void setBillingAccountNumber(CompositeObjectKey billingAccountNumber)
    {
        this.billingAccountNumber = billingAccountNumber;
    }

    /**
     * @param oldNetworkType
     *            The oldNetworkType to set.
     */
    public void setOldNetworkType(StringOpt oldNetworkType)
    {
        this.oldNetworkType = oldNetworkType;
    }

    /**
     * @param secondaryRelatedCircuitID
     *            The secondaryRelatedCircuitID to set.
     */
    public void setSecondaryRelatedCircuitID(StringOpt secondaryRelatedCircuitID)
    {
        this.secondaryRelatedCircuitID = secondaryRelatedCircuitID;
    }

    /**
     * @param secondaryCircuitID
     *            The secondaryCircuitID to set.
     */
    public void setSecondaryCircuitID(StringOpt secondaryCircuitID)
    {
        this.secondaryCircuitID = secondaryCircuitID;
    }

    /**
     * @return Returns the secondaryCircuitID.
     */
    public StringOpt getSecondaryCircuitID()
    {
        return secondaryCircuitID;
    }

    /**
     * @return Returns the billingAccountNumber.
     */
    public CompositeObjectKey getBillingAccountNumber()
    {
        return billingAccountNumber;
    }
    public String getBillingAccountNumberString()
    {
        String ban = null;
        
        for(int i = 0; i < billingAccountNumber.aKeys.length; i++)
        {
            if(ban==null)
            {
              ban = billingAccountNumber.aKeys[i].aValue;
            }
            else
            {
                ban += billingAccountNumber.aKeys[i].aValue;
            }
        }
        return ban;
    }
    /**
     * @return Returns the secondaryRelatedCircuitID.
     */
    public StringOpt getSecondaryRelatedCircuitID()
    {
        return secondaryRelatedCircuitID;
    }
    /**
     * @return Returns the oldNetworkType.
     */
    public StringOpt getOldNetworkType()
    {
        return oldNetworkType;
    }
}