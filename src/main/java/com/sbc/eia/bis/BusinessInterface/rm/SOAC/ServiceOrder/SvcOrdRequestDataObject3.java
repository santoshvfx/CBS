// $Id: SvcOrdRequestDataObject3.java,v 1.3 2009/02/27 07:36:10 ra0331 Exp $
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

import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

public class SvcOrdRequestDataObject3 extends SvcOrdRequestDataObject
{
    protected StringOpt oldNetworkType = null;
    protected StringOpt secondaryCircuitID = null;
    protected StringOpt secondaryRelatedCircuitID = null;
    protected CompositeObjectKey billingAccountNumber = null;

    //ra0331: added changes to LS10
    protected BooleanOpt interceptRedirectIndicator = null;
    protected StringOpt dryloopRelatedCircuitId = null;
    protected StringOpt dSLDisconnectTelephoneNumber = null;
    protected StringOpt exceptionRoutingIndicator = null;
    private StringOpt relatedCircuitId = null;
    
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
    public SvcOrdRequestDataObject3(String aSOACServiceOrderNumber,
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

	/**
	 * @return the dryloopRelatedCircuitId
	 */
	public StringOpt getDryloopRelatedCircuitId() {
		return dryloopRelatedCircuitId;
	}

	/**
	 * @param dryloopRelatedCircuitId the dryloopRelatedCircuitId to set
	 */
	public void setDryloopRelatedCircuitId(StringOpt dryloopRelatedCircuitId) {
		this.dryloopRelatedCircuitId = dryloopRelatedCircuitId;
	}

	/**
	 * @return the dSLDisconnectTelephoneNumber
	 */
	public StringOpt getDSLDisconnectTelephoneNumber() {
		return dSLDisconnectTelephoneNumber;
	}

	/**
	 * @param disconnectTelephoneNumber the dSLDisconnectTelephoneNumber to set
	 */
	public void setDSLDisconnectTelephoneNumber(StringOpt disconnectTelephoneNumber) {
		dSLDisconnectTelephoneNumber = disconnectTelephoneNumber;
	}

	/**
	 * @return the interceptRedirectIndicator
	 */
	public BooleanOpt getInterceptRedirectIndicator() {
		return interceptRedirectIndicator;
	}

	/**
	 * @param interceptRedirectIndicator the interceptRedirectIndicator to set
	 */
	public void setInterceptRedirectIndicator(BooleanOpt interceptRedirectIndicator) {
		this.interceptRedirectIndicator = interceptRedirectIndicator;
	}

	/**
	 * @return the exceptionRoutingIndicator
	 */
	public StringOpt getExceptionRoutingIndicator() {
		return exceptionRoutingIndicator;
	}

	/**
	 * @param exceptionRoutingIndicator the exceptionRoutingIndicator to set
	 */
	public void setExceptionRoutingIndicator(StringOpt exceptionRoutingIndicator) {
		this.exceptionRoutingIndicator = exceptionRoutingIndicator;
	}

	/**
	 * @return the relatedCircuitId
	 */
	public StringOpt getRelatedCircuitId() {
		return relatedCircuitId;
	}

	/**
	 * @param relatedCircuitId the relatedCircuitId to set
	 */
	public void setRelatedCircuitId(StringOpt relatedCircuitId) {
		this.relatedCircuitId = relatedCircuitId;
	}
}