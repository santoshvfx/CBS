//$Id: PublishValidateFacilityNotificationResponseIPCO.java,v 1.3 2009/07/25 02:23:34 js7440 Exp $
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
//# 01/13/2009   Julius Sembrano       LS 10. Creation.
//# 07/22/2009 	 Julius Sembrano       PR25176019: Granite flow, there is no loop info return when WTN is returned from Granite. 
//#									   Modified logic for setting GraniteUverseNotFound and SiteNotFound


package com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.rm.utilities.BuildEmptyIDL;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.rm_ls_types.BillingAccount2;
import com.sbc.eia.idl.rm_ls_types.BillingAccount2SeqOpt;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2SeqOpt;
import com.sbc.eia.idl.rm_ls_types.Message;
import com.sbc.eia.idl.rm_ls_types.RelatedCircuitIDSourceValues;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;

/**
 * Class      : PublishValidateFacilityNotificationResponseIPCO
 * Description: Helper used for formatting the pVFN response message for IPCO network type.  
 */
public class PublishValidateFacilityNotificationResponseIPCO
    extends PublishValidateFacilityNotificationResponseHelper
    implements PublishValidateFacilityNotificationResponseIF
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;

    private FacilityLoop2SeqOpt aLoops;
    private BillingAccount2SeqOpt aAccounts;
    private boolean bOkToProceedIndicator;
    private boolean bBTNnotFoundIndicator;
    private boolean bWTNnotInPremiseIndicator;
    private StringOpt aRecommendedRTID;
    private StringSeqOpt aRecommendedSecondaryRTIDs;
    private BooleanOpt aTerminalExhaustIndicator;
    private StringSeqOpt aAutoDSLDisconnectTelephoneNumbers;
    private String aRTIDSource;
    private EiaDateOpt aDueDate;
    private Message[] aMessages;
    private ObjectPropertySeqOpt aObjectProperties;
    
    /**
     * Constructor: PublishValidateFacilityNotificationResponseIPCO
     * @author Julius Sembrano
     */
    public PublishValidateFacilityNotificationResponseIPCO() 
    {
        // set default values
        aLoops = BuildEmptyIDL.buildEmptyFacilityLoop2SeqOpt();
        aAccounts = BuildEmptyIDL.buildEmptyBillingAccount2SeqOpt();
        bOkToProceedIndicator = false;
        bBTNnotFoundIndicator = false;
        bWTNnotInPremiseIndicator = false;
        aRecommendedRTID = BuildEmptyIDL.buildEmptyStringOpt();
        aRecommendedSecondaryRTIDs = BuildEmptyIDL.buildEmptyStringSeqOpt();
        aRTIDSource = RelatedCircuitIDSourceValues.RTID_OVALS;
        aDueDate = BuildEmptyIDL.buildEmptyEiaDateOpt();
        aMessages = new Message[] {new Message("", "", "")};
        aTerminalExhaustIndicator = BuildEmptyIDL.buildEmptyBooleanOpt();
        aAutoDSLDisconnectTelephoneNumbers = BuildEmptyIDL.buildEmptyStringSeqOpt();
        aObjectProperties = BuildEmptyIDL.buildEmptyObjectPropertySeqOpt();
    }

    /**
     * Constructor: PublishValidateFacilityNotificationResponseIPCO
     * @param Utility   utility
     * @param Hashtable properties
     * @author Julius Sembrano
     */
    public PublishValidateFacilityNotificationResponseIPCO (
        Utility utility, 
        Hashtable properties) 
    {
        this();
        aProperties = properties;
        aUtility = utility;
    }

    // ***************************************************************************************    

    /**
     * Retrieve all loops.
     * @return FacilityLoop2[]
     * @author Julius Sembrano  
     */
    public FacilityLoop2[] getLoops() 
    {
        FacilityLoop2[] aFacilityLoops = null;
        if (!OptHelper.isFacilityLoop2SeqOptEmpty(this.aLoops)) 
        {
            aFacilityLoops = this.aLoops.theValue();
        }
        return aFacilityLoops;
    }
    
    /**
     * Get billing accounts.
     * @return BillingAccount2[]  
     * @author Julius Sembrano  
     */
    public BillingAccount2[] getAccounts() 
    {
        BillingAccount2[] aBillingAccounts = null;
        if (!OptHelper.isBillingAccount2SeqOptEmpty(this.aAccounts)) 
        {
            aBillingAccounts = this.aAccounts.theValue();
        }
        return aBillingAccounts;
    }

    /**
     * Get recommended related circuit ID (RTID).
     * @return String[]
     * @author Julius Sembrano  
     */
    public String getRecommendedRTID() 
    {
        String aRTID = null;
        if (!OptHelper.isStringOptEmpty(this.aRecommendedRTID)) 
        {
            aRTID = this.aRecommendedRTID.theValue();
        }
        return aRTID;
    }

    /**
     * Get secondary recommended related circuit ID (RTID).
     * @return String  
     * @author Julius Sembrano  
     */
    public String[] getRecommendedSecondaryRTID()
    {
        String[] aSecondaryRTIDs = null;
        if (!OptHelper.isStringSeqOptEmpty(this.aRecommendedSecondaryRTIDs)) 
        {
            aSecondaryRTIDs = this.aRecommendedSecondaryRTIDs.theValue();
        }
        return aSecondaryRTIDs;
    }

    /**
     * Get terminal exhaust indicator.
     * @return boolean
     * @author Julius Sembrano  
     */
    public boolean getTerminalExhaustIndicator()
    {
        boolean aExhaustIndicator = false;
        if (!OptHelper.isBooleanOptEmpty(this.aTerminalExhaustIndicator)) 
        {
            aExhaustIndicator = this.aTerminalExhaustIndicator.theValue();
        }
        return aExhaustIndicator;
    }

    /**
     * Get auto DSL disconnect telephone numbers.
     * @return String[]
     * @author Julius Sembrano  
     */
    public String[] getAutoDSLDisconnectTelephoneNumbers()
    {
        String[] aTNs = null;
        if (!OptHelper.isStringSeqOptEmpty(this.aAutoDSLDisconnectTelephoneNumbers)) 
        {
            aTNs = this.aAutoDSLDisconnectTelephoneNumbers.theValue();
        }
        return aTNs;
    }

    /**
     * Get related circuit ID (RTID) source.
     * @return String  
     * @author Julius Sembrano  
     */
    public String getRTIDSource() 
    {
        return aRTIDSource;
    }

    /**
     * Get recommended due date.
     * @return EiaDate  
     * @author Julius Sembrano  
     */
    public EiaDate getDueDate() 
    {
        EiaDate aDueDate = null;
        if (!OptHelper.isEiaDateOptNull(this.aDueDate)) 
        {
            aDueDate = this.aDueDate.theValue();
        }
        return aDueDate;
    }

    /**
     * Get proceed indicator.
     * @return boolean (true or false)  
     * @author Julius Sembrano  
     */
    public boolean getOkToProceedIndicator() 
    {
        return bOkToProceedIndicator;
    }

    /**
     * Get BTN not found indicator.
     * @return boolean (true or false)  
     * @author Julius Sembrano  
     */
    public boolean getBTNnotFoundIndicator()
    {
        return bBTNnotFoundIndicator;
    }

    /**
     * Get WTN not in premise indicator.
     * @return boolean (true or false)  
     * @author Julius Sembrano  
     */
    public boolean getWTNnotInPremiseIndicator()
    {
        return bWTNnotInPremiseIndicator;
    }

    /**
     * Get messages.
     * @return Message[]  
     * @author Julius Sembrano  
     */
    public Message[] getMessages() 
    {
        return aMessages;
    }

    // ***************************************************************************************    

    /**
     * Set facility loops.
     * @param aInput  
     * @author Julius Sembrano  
     */
    public void setLoops(FacilityLoop2[] aInput) 
    {
        aLoops.theValue(aInput);
    }
    
    /**
     * Set billing accounts.
     * @param aInput  
     * @author Julius Sembrano  
     */
    
    public void setAccounts(BillingAccount2[] aInput) 
    {
        aAccounts.theValue(aInput);
    }

    /**
     * Set related circuit ID (RTID).
     * @param String aInput  
     * @author Julius Sembrano  
     */
    public void setRecommendedRTID(String aInput) 
    {
        if (aInput != null && aInput.length() > 0)
        {
            aRecommendedRTID.theValue(aInput);
        }
        else
        {
            aRecommendedRTID.theValue(new StringBuffer("blank").toString());
        }
    }

    /**
     * Set secondary recommended related circuit IDs (RTID).
     * @param String[] aInput  
     * @author Julius Sembrano  
     */
    public void setRecommendedSecondaryRTID(String[] aInput)
    {
        aRecommendedSecondaryRTIDs.theValue(aInput);
    }

    /**
     * Set terminal exhaust indicator.
     * @param boolean aInput
     * @author Julius Sembrano  
     */
    public void setTerminalExhaustIndicator(boolean aInput)
    {
        aTerminalExhaustIndicator.theValue(aInput);
    }

    /**
     * Set auto DSL disconnect telephone numbers.
     * @return String[] aInput
     * @author Julius Sembrano  
     */
    public void setAutoDSLDisconnectTelephoneNumbers(String[] aInput)
    {
        aAutoDSLDisconnectTelephoneNumbers.theValue(aInput);
    }
    
    /**
     * Set related circuit ID (RTID) source.
     * @param String aInput  
     * @author Julius Sembrano  
     */
    public void setRTIDSource(String aInput) 
    {
        aRTIDSource = aInput;
    }

    /**
     * Set recommended due date.
     * @param EiaDate aInput  
     * @author Julius Sembrano  
     */
    public void setDueDate(EiaDate aInput) 
    {
        aDueDate.theValue(aInput);
    }

    /**
     * Set proceed indicator.
     * @param boolean aInput  
     * @author Julius Sembrano  
     */
    public void setOkToProceedIndicator(boolean aInput) 
    {
        bOkToProceedIndicator = aInput;
    }

    /**
     * Set BTN not found indicator.
     * @param boolean aInput  
     * @author Julius Sembrano  
     */
    public void setBTNnotFoundIndicator(boolean aInput)
    {
        bBTNnotFoundIndicator = aInput;
    }

    /**
     * Set WTN not in premise indicator.
     * @param boolean aInput  
     * @author Julius Sembrano  
     */
    public void setWTNnotInPremiseIndicator(boolean aInput)
    {
        bWTNnotInPremiseIndicator = aInput;
    }
    
    /**
     * Set messages.
     * @param Message[] aInput  
     * @author Julius Sembrano  
     */
    public void setMessages(Message[] aInput) 
    {
        aMessages = aInput;
    }
    public boolean isGraniteUVerseFound(String aNTI)
    {
    	return checkUVerseFoundInGranite(aNTI);
    }
}
