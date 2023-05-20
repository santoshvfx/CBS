//$Id: PublishValidateFacilityNotificationRequestHelper.java,v 1.11 2009/08/15 05:05:21 js7440 Exp $
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
//# Date       | Author              | Notes
//# --------------------------------------------------------------------
//# 01/15/2008   Rene Duka             LS 7. Creation.
//# 02/07/2008   Rene Duka             LS 7.
//# 02/07/2008   Shyamali Banerjee     Modified for LS7
//# 05/22/2008   Sriram Chevuturu	   LS 7:PR 22162695: JMSSelector for MobilityCSI
//# 06/13/2008	 Lira Galsim           LS 8.
//# 03/03/2009   Lira Galsim		   DR123173: Added getter and setter for invalid NTI List
//# 08/14/2009   Julius Sembrano	   DR137399: DLS disconnect is not getting triggered for self installation on HSIA at the back end --WUP00229080

package com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.BuildEmptyIDL;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.rm_ls_types.NetworkType2Values;
import com.sbc.eia.idl.rm_ls_types.ServiceItem;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectType;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;

import com.sbc.eia.bis.facades.rm.transactions.PublishValidateFacilityNotification.PublishValidateFacilityNotificationConstants;

/**
 * Class      : PublishValidateFacilityNotificationRequestHelper
 * Description: Helper for the request.  
 */
public class PublishValidateFacilityNotificationRequestHelper 
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;

    private ObjectType[] aNtis;
    private String aClient;
    private String aRegion;
    private Address aFacilityAddress;
    private String aNpaNxx;
    private String aCLLI8;
    private String aRTID;
    private String aWorkingTelephoneNumber;
    private int aMaxPairsToAnalyze;
    private String aSOACServiceOrderNumber;
    private String aSOACServiceOrderNumberSuffix;
    private EiaDate aUverseOrderDueDate;
    private String aOrderActionType;
    private String aSubActionType;
    private String aOmsOrderId;
    private BisContext aContext;     
    private String aJMSSelectorValue;
    private boolean bJMSSelectorTagPresent;
    private String[] aLfacsNtiList;
    private String[] aGraniteNtiList;
    private String[] aInvalidNtiList;
    private String[] aValidNtiList;
    private String aTransactionType;
    /**
     * Constructor: PublishValidateFacilityNotificationRequestHelper
     * @author Rene Duka
     */
    public PublishValidateFacilityNotificationRequestHelper() 
    {
        // initialize
        aClient = null;
        aRegion = null;
        aFacilityAddress = null;
        aNpaNxx = null;
        aCLLI8 = null;
        aRTID = null;
        aWorkingTelephoneNumber = null;
        aMaxPairsToAnalyze = 0;
        aSOACServiceOrderNumber = null;
        aSOACServiceOrderNumberSuffix = null;
        aUverseOrderDueDate = null;
        aOrderActionType = null;
        aSubActionType = null;
        aJMSSelectorValue = null;
        bJMSSelectorTagPresent = false;
        aOmsOrderId = null;
        aLfacsNtiList = null;
        aGraniteNtiList = null;
        aInvalidNtiList = null;
        aValidNtiList = null;
    }

    /**
     * Constructor: PublishValidateFacilityNotificationRequestHelper
     * @param Utility   utility
     * @param Hashtable properties
     * @author Rene Duka
     */
    public PublishValidateFacilityNotificationRequestHelper (
        Utility utility, 
        Hashtable properties) 
    {
        this();
        aProperties = properties;
        aUtility = utility;
    }

    /**
     * Get aContext.
     * @return BisContext  
     * @author Shyamali Banerjee  
     */
    public BisContext getContext() 
    {
        return aContext;
    }
    /**
     * Get ntis.
     * @return ObjectType[]  
     * @author Rene Duka  
     */
    public ObjectType[] getNtis() 
    {
        return aNtis;
    }

    /**
     * Get client.
     * @return String  
     * @author Rene Duka  
     */
    public String getClient() 
    {
        return aClient;
    }

    /**
     * Get region.
     * @return String  
     * @author Rene Duka  
     */
    public String getRegion() 
    {
        return aRegion;
    }

    /**
     * Get U-Verse order due date.
     * @return EiaDate  
     * @author Rene Duka  
     */
    public EiaDate getUverseOrderDueDate() 
    {
        return aUverseOrderDueDate;
    }

    /**
     * Get maximum pairs to analyze.
     * @return int  
     * @author Rene Duka  
     */
    public int getMaxPairsToAnalyze() 
    {
        return aMaxPairsToAnalyze;
    }

    /**
     * Get SOAC service order number.
     * @return String  
     * @author Rene Duka  
     */
    public String getSOACServiceOrderNumber() 
    {
        return aSOACServiceOrderNumber;
    }

    /**
     * Get SOAC service order number suffix.
     * @return String  
     * @author Rene Duka  
     */
    public String getSOACServiceOrderNumberSuffix() 
    {
        return aSOACServiceOrderNumberSuffix;
    }

    /**
     * Get order action type.
     * @return String  
     * @author Rene Duka  
     */
    public String getOrderActionType() 
    {
        return aOrderActionType;
    }

    /**
     * Get sub action type.
     * @return String  
     * @author Rene Duka  
     */
    public String getSubActionType() 
    {
        return aSubActionType;
    }

    /**
     * Get NpaNxx.
     * @return String  
     * @author Rene Duka  
     */
    public String getNpaNxx() 
    {
        return aNpaNxx;
    }

    /**
     * Get CLLI8.
     * @return String  
     * @author Rene Duka  
     */
    public String getCLLI8() 
    {
        return aCLLI8;
    }

    /**
     * Get RTID.
     * @return String  
     * @author Rene Duka  
     */
    public String getRTID() 
    {
        return aRTID;
    }
    
    /**
     * Get facility address.
     * @return Address  
     * @author Rene Duka  
     */
    public Address getFacilityAddress() 
    {
        return aFacilityAddress;
    }

    /**
     * Get working telephone number.
     * @return String  
     * @author Rene Duka  
     */
    public String getWorkingTelephoneNumber() 
    {
        return aWorkingTelephoneNumber;
    }
    
	/**
	 * @return Returns the aJMSSelectorValue.
	 */
	public String getAJMSSelectorValue() 
	{
		return aJMSSelectorValue;
	}    

	/**
	 * @return Returns the bJMSSelectorPresent.
	 */
	public boolean isJMSSelectorTagPresent() 
	{
		return bJMSSelectorTagPresent;
	}

	
    // ***************************************************************************************
    /**
     * Set Context.
     * @param BisContext aInput  
     * @author Shyamali Banerjee 
     */
    public void setContext(BisContext aInput) 
    {
        aContext = aInput;
    }
    /**
     * Set ntis.
     * @param ObjectType[] aInput  
     * @author Rene Duka  
     */
    public void setNtis(ObjectType[] aInput) 
    {
        aNtis = aInput;
    }

    /**
     * Set client.
     * @param String aInput  
     * @author Rene Duka  
     */
    public void setClient(String aInput) 
    {
        aClient = aInput;
    }
    
    /**
     * Set region.
     * @param String aInput  
     * @author Rene Duka  
     */
    public void setRegion(String aInput) 
    {
        aRegion = aInput;
    }

    /**
     * Set U-Verese order due date.
     * @param EiaDate aInput  
     * @author Rene Duka  
     */
    public void setUverseOrderDueDate(EiaDate aInput) 
    {
        aUverseOrderDueDate = aInput;
    }

    /**
     * Set maximum pairs to analyze.
     * @param aInput  
     * @author Rene Duka  
     */
    public void setMaxPairsToAnalyze(short aInput) 
    {
        aMaxPairsToAnalyze = aInput;
    }

    /**
     * Set SOAC service order number.
     * @param aInput  
     * @author Rene Duka  
     */
    public void setSOACServiceOrderNumber(String aInput) 
    {
        aSOACServiceOrderNumber = aInput;
    }
    
    /**
     * Set SOAC service order number suffix.
     * @param aInput  
     * @author Rene Duka  
     */
    public void setSOACServiceOrderNumberSuffix(String aInput) 
    {
        aSOACServiceOrderNumberSuffix = aInput;
    }

    /**
     * Set order action type.
     * @param aInput  
     * @author Rene Duka  
     */
    public void setOrderActionType(String aInput) 
    {
        aOrderActionType = aInput;
    }

    /**
     * Set sub action type.
     * @param aInput  
     * @author Rene Duka  
     */
    public void setSubActionType(String aInput) 
    {
        aSubActionType = aInput;
    }

    /**
     * Set NpaNxx.
     * @param aInput 
     * @author Rene Duka  
     */
    public void setNpaNxx(String aInput) 
    {
        aNpaNxx = aInput;
    }

    /**
     * Set CLLI8.
     * @param aInput  
     * @author Rene Duka  
     */
    public void setCLLI8(String aInput) 
    {
        aCLLI8 = aInput;
    }

    /**
     * Set RTID.
     * @param aInput  
     * @author Rene Duka  
     */
    public void setRTID(String aInput) 
    {
        aRTID = aInput;
    }

    /**
     * Set facility address.
     * @param aInput  
     * @author Rene Duka  
     */
    public void setFacilityAddress(Address aInput) 
    {
        aFacilityAddress = aInput;
    }

    /**
     * Set working telephone number.
     * @param aInput  
     * @author Rene Duka  
     */
    public void setWorkingTelephoneNumber(String aInput) 
    {
        aWorkingTelephoneNumber = aInput;
    }
    
	/**
	 * @param aClientBisContext  to extract aJMSSelectorValue.
	 */
	public void setAJMSSelectorValue(BisContext aClientBisContext) 
	{

        ObjectPropertyManager aOPM = new ObjectPropertyManager(aClientBisContext.aProperties);  
        aJMSSelectorValue = aOPM.getValue(PublishValidateFacilityNotificationConstants.JMS_SELECTOR_TAG);
        
        if(aJMSSelectorValue != null )
        	bJMSSelectorTagPresent = true;		
	}

    
    // ***************************************************************************************

    /**
     * Validate the network type.
     * @param String aNetworkType
     * @return boolean
     * @author Rene Duka  
     */
    public boolean validateNetworkType(String aNetworkType)
    {
        boolean bIsNetworkTypeValid = false;
        if (aNetworkType.equalsIgnoreCase(NetworkType2Values.FTTN)
            || aNetworkType.equalsIgnoreCase(NetworkType2Values.FTTNBP)
            || aNetworkType.equalsIgnoreCase(NetworkType2Values.FTTP)
            || aNetworkType.equalsIgnoreCase(NetworkType2Values.GPON)
            || aNetworkType.equalsIgnoreCase(NetworkType2Values.RGPON))
        {
            bIsNetworkTypeValid = true;
        }
        return bIsNetworkTypeValid;
    }
    
    /**
     * Get the network type from the list.
     * @param String aNetworkType
     * @return boolean
     * @author Rene Duka  
     */
    public ObjectType getNetworkType(String aNetworkType)
    {
        ObjectType aNti = null;
        boolean bNtiFound = false;
        for (int i = 0; i < this.aNtis.length; i++)
        {
            if (aNtis[i].aName.equalsIgnoreCase("networkTransport"))
            {
                if (!OptHelper.isAttributeTypeSeqOptEmpty(aNtis[i].aAttribute))
                {
                    for (int j = 0; j < aNtis[i].aAttribute.theValue().length; j++)
                    {
                        if (aNtis[i].aAttribute.theValue()[j].aName.equalsIgnoreCase("ntiNegotiationValue")
                            && aNtis[i].aAttribute.theValue()[j].aValue.equalsIgnoreCase(aNetworkType.trim()))
                        {
                            aNti = aNtis[i];
                            bNtiFound = true;
                            break;
                        }
                    }
                }
                if (bNtiFound)
                {
                    break;
                }
            }
        }
        return aNti;    
    }

    /**
     * Get OMS order id.
     * @return String 
     * @author Hongmei Parkin  
     */
	public String getOmsOrderId() 
	{
		return aOmsOrderId;
	}

    /**
     * Set OMS order id.
     * @param String 
     * @author Hongmei Parkin  
     */
	public void setOmsOrderId(String omsOrderId) 
	{
		aOmsOrderId = omsOrderId;
	}

    /**
     * Get Granite NTI List.
     * @return String[] 
     * @author Hongmei Parkin  
     */
	public String[] getGraniteNtiList() 
	{
		return aGraniteNtiList;
	}

    /**
     * Set Granite NTI List.
     * @param String[] 
     * @author Hongmei Parkin  
     */
	public void setGraniteNtiList(String[] graniteNtiList) 
	{
		aGraniteNtiList = graniteNtiList;
	}
	
    /**
     * Get Granite NTI List.
     * @return String[] 
     * @author Hongmei Parkin  
     */
	public String[] getValidNtiList() 
	{
		return aValidNtiList;
	}

    /**
     * Set Granite NTI List.
     * @param String[] 
     * @author Hongmei Parkin  
     */
	public void setValidNtiList(String[] validNtiList) 
	{
		aValidNtiList = validNtiList;
	}

    /**
     * Get LFACS NTI List.
     * @return String[] 
     * @author Hongmei Parkin  
     */
	public String[] getLfacsNtiList() 
	{
		return aLfacsNtiList;
	}

	/**
     * Set LFACS NTI List.
     * @param String[] 
     * @author Hongmei Parkin  
     */
	public void setLfacsNtiList(String[] invalidNtiList) 
	{
		aLfacsNtiList = invalidNtiList;
	}

    /**
     * Get Invalid NTI List.
     * @return String[] 
     * @author Hongmei Parkin  
     */
	public String[] getInvalidNtiList() 
	{
		return aInvalidNtiList;
	}

    /**
     * Set Invalid NTI List.
     * @param String[] 
     * @author Hongmei Parkin  
     */
	public void setInvalidNtiList(String[] invalidNtiList) 
	{
		aInvalidNtiList = invalidNtiList;
	}
	
	 /**
     * Check if the network type(s) received from the client is LFACS Assigned NTIs.
     *
     * @param String aNetworkType
     * @return boolean
     *
     */
    public boolean isLfacsNtis (String aNetworkType)
    {
    	if (((String) aProperties.get("LFACS_NTIS")).indexOf(":" + aNetworkType + ":") >= 0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /**
     * Check if the network type(s) received from the client is Granite Assigned NTIs.
     *
     * @param String aNetworkType
     * @return boolean
     *
     */
    public boolean isGraniteNtis (String aNetworkType)
    {
    	if (((String) aProperties.get("GRANITE_NTIS")).indexOf(":" + aNetworkType + ":") >= 0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    /**
     * Get the Transaction type.
     * @param 
     * @return String
     * @author Julius Sembrano  
     */
	public String getTransactionType() 
	{
		return aTransactionType;
	}

    /**
     * Set the Transaction type.
     * @param String transactionType
     * @return String
     * @author Julius Sembrano  
     */
	public void setTransactionType(String transactionType) 
	{
		aTransactionType = transactionType;
	}
}
