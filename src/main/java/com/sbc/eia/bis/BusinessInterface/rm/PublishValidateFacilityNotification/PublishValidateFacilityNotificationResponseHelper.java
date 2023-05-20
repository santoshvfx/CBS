//$Id: PublishValidateFacilityNotificationResponseHelper.java,v 1.75 2009/08/26 03:54:35 js7440 Exp $
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
//# 10/02/2007   Rene Duka             Creation.
//# 11/13/2007   Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 01/03/2008   Rene Duka             Defect 81266: retrieveAccount() exception issue.
//# 02/17/2008   Rene Duka             LS 7.
//# 02/29/2008   Rene Duka             PR 21563615 (LS 6): Return loop information when SM is not available.
//# 03/09/2008   Rene Duka             Defect 87644: Recommended RTID not returned for GPON.
//# 03/10/2008   Rene Duka             Defect 87592: U-Verse pending SO due date not returned as recommended due date.
//# 03/10/2008   Rene Duka             Defect 87641: Set BTN not found indicator to false if at least 1 SM account is found.
//# 08/08/2008   Shyamali Banerjee     Modified for PR 22487342
//# 08/29/2008	 Souvik Paul		   CR 20389: Added getter and setter for Exception Proceed Indicator.
//# 09/02/2008	 Vickie Ng			   LS 9.
//# 11/05/2008   Lira Galsim           DR111479: BillingTelephoneNumber in process is returned twice.
//# 11/20/2008   Lira Galsim           DR112021: WTNs from LFACS that matches with the WTNs from rSAFABAN should not appear on the loops with no BTN List.
//# 01/08/2009   Julius Sembrano       LS 10
//# 02/10/2009   Lira Galsim           LS10: Added getter and setter for IPDSLAM Proceed Indicator.
//# 03/02/20090  Julius Sembrano       DR122537: RM BIS returning RM-SystemFailure-00521 error multiple times for one transaction with different descriptions
//# 03/06/2009	 Sheilla Lirio		   DR123358: RM BIS is calling CPSOS when there is no DSL on the Living Unit contrary to what is stated in the BPR.
//# 03/09/2009   Julius Sembrano       DR123550: Invalid error description is being returned when sending invalid service address.
//# 03/11/2009   Lira Galsim		   DR123769: Granite testing to SM BIS returns RM-SystemFailure-00521 - Exception: [ Unexpected error encountered in retrieving accounts from SM.null ].
//# 03/12/2009   Lira Galsim		   DR123650: Fixed the null pointer exception that caused the failed processing of WTN.
//# 03/14/2009   Lira Galsim		   DR123984: Fixed the return of loops.
//# 03/19/2009   Lira Galsim		   DR124539: Fixed the null pointer exception in setUniversalBillingAccountNumber().
//# 03/24/2009   Lira Galsim           DR124770: Added setter and getter for WTNsALreadyProcessed to fix the procesing of WTNs in CPSOS.
//# 04/10/2009   Julius Sembrano       Fixed the duplicate exception returned by the handleException() method
//# 04/20/2009   Julius Sembrano       CR24678: RM BIS not to treat UNE-P/LWC as conflict
//# 05/09/2009   Julius Sembrano       PR24700828/DR128786: Added boolean flags bSiteNotFoundInGranite and bPathNotFoundInGranite
//# 06/21/2009   Lira Galsim		   PR25016056: Removed helper for FacilityLoops3 and BillingAccounts3.
//# 07/14/2009   Lira Galsim		   PR25176019: Fixed potential null pointer exceptions.
//# 07/22/2009 	 Julius Sembrano       PR25176019: Granite flow, there is no loop info return when WTN is returned from Granite. 
//#									   Modified logic for setting GraniteUverseNotFound and SiteNotFound
//# 08/14/2009   Julius Sembrano	   DR137399: DLS disconnect is not getting triggered for self installation on HSIA at the back end --WUP00229080
//# 08/18/2009   Julius Sembrano       DR137957: WTN's being returned multiple times
//# 08/25/2009   Julius Sembrano       DR138130: Conflict should be true for ISDN based on the IPDSLAM table

package com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.facades.rm.transactions.ValidateFacility.ValidateFacilityConstants;
import com.sbc.eia.bis.facades.testing.objHelpers;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.BuildEmptyIDL;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.rm_ls_types.BillingAccount2;
import com.sbc.eia.idl.rm_ls_types.BillingAccount2SeqOpt;
import com.sbc.eia.idl.rm_ls_types.BillingAccount3;
import com.sbc.eia.idl.rm_ls_types.BillingAccount3SeqOpt;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2SeqOpt;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop3;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop3SeqOpt;
import com.sbc.eia.idl.rm_ls_types.NetworkType2Values;
import com.sbc.eia.idl.rm_ls_types.NetworkType3Values;
import com.sbc.eia.idl.rm_ls_types.ResponseDetail3;
import com.sbc.eia.idl.rm_ls_types.ResponseDetail3SeqOpt;
import com.sbc.eia.idl.rm_ls_types.ServiceItem;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.CompositeObjectKeyOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ExceptionDataSeqOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
 * Class      : PublishValidateFacilityNotificationResponseHelper
 * Description: Helper used for formatting the pVFN response message.  
 */
public class PublishValidateFacilityNotificationResponseHelper 
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;

    private boolean aWorkingTelephoneNumberNotInPremiseIndicator;
    private boolean aErrorInLFACSIndicator;
    private boolean aErrorInSMIndicator;
    protected Properties aGraniteUVerseProperties;
    protected Properties aSiteNotFoundInGraniteProperties;
    
    // common to all responses
    private ObjectPropertySeqOpt aObjectProperties;
    private ExceptionDataOpt aExceptionData;
    private boolean aPartialAnalysisIndicator;
    
    // helpers
    private PublishValidateFacilityNotificationResponseDriver[] aResponseDrivers;
    private BillingAccount2SeqOpt aBillingAccounts;
    private FacilityLoop2SeqOpt aFacilityLoops;
    private ResponseDetail3SeqOpt aResponseDetail3;
    private String[] aNetworkTypeList;
    private int aNumberOfBBPLoops;
    private int aNumberOfATTDSL;
    private int aNumberOfNonATTDSL;
    private FacilityLoop2 aLoopWithRTIDandDSL;
    private ArrayList aLoopsWithNoBTN_FTTN;
    private ArrayList aLoopsWithNoBTN_FTTP;
    private ArrayList aLoopsWithNoBTN_GPON;
    private ArrayList aLoopsWithNoBTN_IPDSLAM;
    private ArrayList aLoopsWithNoBTN_Ntis;
    private FacilityLoop2[] aLoopsWithPCommit;
    private FacilityLoop2[] aLoopsWithSCommit;
    private FacilityLoop2[] aLoopsWithDSL;
    private Properties aNTIProcessed;
    private ArrayList aLoopProcessed;
    private boolean bRecommendedRTIDFound;
    private boolean bRecommendedSecondaryRTIDFound;
    private boolean bConflictFound;
    private boolean bUverseFound;
    private boolean bUverseSOFound;    
    private boolean bDSLFound;
    private boolean bDSLMisuse;
    private boolean bDISHFound;
    private boolean bDialUpFound;
    private boolean bLSConditionedLoopFound;
    private boolean bFiberInPremise;
    private boolean bAccountFound;
    private String aRecommendedRTID_GPON;
    private EiaDate aRecommendedDueDate_UVerseSO;
    private boolean bBBPMissingIndicator;
    private boolean bLoadCoilExhaustedIndicator;
    private boolean bTCommitIndicator;
    private int aNumberOfLoadCoilLoops;
    private boolean bBBPMissingMsgIndicator;
    private boolean bLoadCoilMsgIndicator;
    private ExceptionDataSeqOpt aExceptionDataList;
    private boolean bIPDSLAMProceedIndicator;
    private ArrayList aWTNListAlreadyProcessed;
    private boolean bDISHServiceIndicator;
    private boolean bNonPublishedIndicator;
    private boolean bOverrideUNEPLWCIndicator;
    
    /**
     * Constructor: PublishValidateFacilityNotificationResponseHelper
     * 
     * @author Rene Duka
     */
    public PublishValidateFacilityNotificationResponseHelper() 
    {
        // initialize
        aPartialAnalysisIndicator = false;
        aWorkingTelephoneNumberNotInPremiseIndicator = false;
        aErrorInLFACSIndicator = false;
        aErrorInSMIndicator = false;
        aGraniteUVerseProperties = new Properties();
        aSiteNotFoundInGraniteProperties = new Properties();
        
        // common to all responses
        aObjectProperties = BuildEmptyIDL.buildEmptyObjectPropertySeqOpt();
       
        aExceptionData = (ExceptionDataOpt) IDLUtil.toOpt(ExceptionDataOpt.class, null);
        aExceptionDataList=(ExceptionDataSeqOpt) IDLUtil.toOpt(ExceptionDataSeqOpt.class, null);
        aResponseDetail3 = (ResponseDetail3SeqOpt) IDLUtil.toOpt(ResponseDetail3SeqOpt.class, null);
        // helpers
        aResponseDrivers = null;
        aBillingAccounts = (BillingAccount2SeqOpt) IDLUtil.toOpt(BillingAccount2SeqOpt.class, null);
        aFacilityLoops = (FacilityLoop2SeqOpt) IDLUtil.toOpt(FacilityLoop2SeqOpt.class, null);
        aNetworkTypeList = null;
        aNumberOfBBPLoops = 0;
        aNumberOfATTDSL = 0;
        aNumberOfNonATTDSL = 0;
        aLoopWithRTIDandDSL = null;
        aLoopsWithNoBTN_FTTN = new ArrayList();
        aLoopsWithNoBTN_FTTP = new ArrayList();
        aLoopsWithNoBTN_GPON = new ArrayList();
        aLoopsWithNoBTN_IPDSLAM = new ArrayList();
        aLoopsWithNoBTN_Ntis = new ArrayList();
        aNTIProcessed = new Properties();
        aLoopProcessed = new ArrayList();
        aLoopsWithPCommit = null;
        aLoopsWithSCommit = null;
        aLoopsWithDSL = null;
        bRecommendedRTIDFound = false;
        bRecommendedSecondaryRTIDFound = false;
        bConflictFound = false;
        bUverseFound = false;
        bUverseSOFound = false;
        bDSLFound = false;
        bDSLMisuse = false;
        bDISHFound = false;
        bDialUpFound = false;
        bLSConditionedLoopFound = false;
        bFiberInPremise = false;
        bAccountFound = false;
        aRecommendedRTID_GPON = null;
        aRecommendedDueDate_UVerseSO = null;
        bBBPMissingIndicator = false;
        bLoadCoilExhaustedIndicator = false;
        bTCommitIndicator = false;
        aNumberOfLoadCoilLoops = 0;
        bBBPMissingMsgIndicator = false;
        bLoadCoilMsgIndicator = false;  
        
        bIPDSLAMProceedIndicator = true;
        
        aWTNListAlreadyProcessed = new ArrayList();
        
        bDISHServiceIndicator = false;
        bNonPublishedIndicator = false;
        bOverrideUNEPLWCIndicator = false;
    }
  
    /**
     * Constructor: PublishValidateFacilityNotificationResponseHelper
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Rene Duka
     */
    public PublishValidateFacilityNotificationResponseHelper (
        Utility utility, 
        Hashtable properties) 
    {
        this();
        aProperties = properties;
        aUtility = utility;
    }

    // ***************************************************************************************    

    /**
     * Get object properties.
     * @return ObjectProperty[]
     * @author Rene Duka  
     */
    public ObjectProperty[] getObjectProperties() 
    {
        ObjectProperty[] aProps = null;
        if (!OptHelper.isObjectPropertySeqOptEmpty(this.aObjectProperties)) 
        {
            aProps = this.aObjectProperties.theValue();
        }
        return aProps;
    }

    /**
     * Get exception data.
     * @return ExceptionData  
     * @author Rene Duka  
     */
    public ExceptionData getExceptionData() 
    {
        ExceptionData aException = null;
        if (!OptHelper.isExceptionDataOptEmpty(this.aExceptionData)) 
        {
            aException = this.aExceptionData.theValue();
        }
        return aException;
    }
    
    /**
     * Get response drivers.
     * @return PublishValidateFacilityNotificationResponseController[]  
     * @author Rene Duka  
     */
    public PublishValidateFacilityNotificationResponseDriver[] getResponseDrivers() 
    {
        return aResponseDrivers;
    }

    /**
     * Get billing accounts.
     * @return BillingAccount2[]  
     * @author Rene Duka  
     */
    public BillingAccount2[] getBillingAccounts() 
    {
        BillingAccount2[] aAccounts = null;
        if (!OptHelper.isBillingAccount2SeqOptEmpty(this.aBillingAccounts)) 
        {
            aAccounts = this.aBillingAccounts.theValue();
        }
        return aAccounts;
    }
    
    
    /**
     * Get loops.
     * @return FacilityLoop2[]
     * @author Rene Duka  
     */
    public FacilityLoop2[] getFacilityLoops() 
    {
        FacilityLoop2[] aLoops = null;
        if (!OptHelper.isFacilityLoop2SeqOptEmpty(this.aFacilityLoops)) 
        {
            aLoops = this.aFacilityLoops.theValue();
        }
        return aLoops;
    }

    /**
     * Get network type list.
     * @return String[]  
     * @author Rene Duka  
     */
    public String[] getNetworkTypeList() 
    {
        return aNetworkTypeList;
    }

    /**
     * Get number of BBP loops.
     * @return int  
     * @author Rene Duka  
     */
    public int getNumberOfBBPLoops() 
    {
        return aNumberOfBBPLoops;
    }

    /**
     * Get the number of AT&T DSL.
     * @return int  
     * @author Rene Duka  
     */
    public int getNumberOfATTDSL() 
    {
        return aNumberOfATTDSL;
    }

    /**
     * Get the number of non-AT&T DSL.
     * @return int  
     * @author Rene Duka  
     */
    public int getNumberOfNonATTDSL() 
    {
        return aNumberOfNonATTDSL;
    }
    
    /**
     * Get loop with recommended RTID and DSL.
     * @return FacilityLoop2 
     * @author Rene Duka  
     */
    public FacilityLoop2 getFacilityLoopWithRTIDandDSL() 
    {
        return aLoopWithRTIDandDSL;
    }

    /**
     * Get loops with no BTN for FTTN and FTTN-BP network type.
     * @return ArrayList  
     * @author Rene Duka  
     */
    public ArrayList getFacilityLoopsNoBTN_FTTN() 
    {
        return aLoopsWithNoBTN_FTTN;
    }

    /**
     * Get loops with no WTN for FTTPIP network type.
     * @return ArrayList  
     * @author Rene Duka  
     */
    public ArrayList getFacilityLoopsNoBTN_FTTP() 
    {
        return aLoopsWithNoBTN_FTTP;
    }

    /**
     * Get loops with no WTN for GPON and RGPON network type.
     * @return ArrayList  
     * @author Rene Duka  
     */
    public ArrayList getFacilityLoopsNoBTN_GPON() 
    {
        return aLoopsWithNoBTN_GPON;
    }

    /**
     * Get loops with no WTN for IPCO and IPRT network type.
     * @return ArrayList  
     * @author Julius Sembrano  
     */
	public ArrayList getFacilityLoopsNoBTN_IPDSLAM() 
	{
		return aLoopsWithNoBTN_IPDSLAM;
	}

    /**
     * Get loops with P commit.
     * @return FacilityLoop2[]  
     * @author Rene Duka  
     */
    public FacilityLoop2[] getFacilityLoopsWithPCommit() 
    {
        return aLoopsWithPCommit;
    }

    /**
     * Get loops with S commit.
     * @return FacilityLoop2[]  
     * @author Rene Duka  
     */
    public FacilityLoop2[] getFacilityLoopsWithSCommit() 
    {
        return aLoopsWithSCommit;
    }

    /**
     * Get loops with DSL.
     * @return FacilityLoop2[]  
     * @author Rene Duka  
     */
    public FacilityLoop2[] getFacilityLoopsWithDSL() 
    {
        return aLoopsWithDSL;
    }

    /**
     * Get recommended RTID found indicator.
     * @return boolean  
     * @author Rene Duka  
     */
    public boolean getRecommendedRTIDFound() 
    {
        return bRecommendedRTIDFound;
    }

    /**
     * Get recommended secondary RTID found indicator.
     * @return boolean  
     * @author Rene Duka  
     */
    public boolean getRecommendedSecondaryRTIDFound() 
    {
        return bRecommendedSecondaryRTIDFound;
    }

    /**
     * Get conflict found indicator.
     * @return boolean (true or false)
     * @author Rene Duka  
     */
    public boolean getConflictFound() 
    {
        return bConflictFound;
    }

    /**
     * Get U-Verse found indicator.
     * @return boolean (true or false)
     * @author Rene Duka  
     */
    public boolean getUverseFound() 
    {
        return bUverseFound;
    }

    /**
     * Get U-Verse service order found indicator.
     * @return boolean (true or false)
     * @author Rene Duka  
     */
    public boolean getUverseSOFound() 
    {
        return bUverseSOFound;
    }

    /**
     * Get DSL found indicator.
     * @return boolean (true or false)
     * @author Rene Duka  
     */
    public boolean getDSLFound() 
    {
        return bDSLFound;
    }
    
    /**
     * Get DSL misuse indicator.
     * @return boolean (true or false)
     * @author Rene Duka  
     */
    public boolean getDSLMisuse() 
    {
        return bDSLMisuse;
    }

    /**
     * Get DISH service indicator.
     * @return boolean (true or false)
     * @author Rene Duka  
     */
    public boolean getDISHFound() 
    {
        return bDISHFound;
    }

    /**
     * Get Dial-up service indicator.
     * @return boolean (true or false)
     * @author Rene Duka  
     */
    public boolean getDialUpFound() 
    {
        return bDialUpFound;
    }

    /**
     * Get LS-conditioned loop found indicator.
     * @return boolean (true or false)
     * @author Rene Duka  
     */
    public boolean getLSConditionedLoopFound() 
    {
        return bLSConditionedLoopFound;
    }

    /**
     * Method: Get fiber in premise indicator.
     * @return boolean (true or false)
     * @author Rene Duka  
     */
    public boolean getFiberInPremise() 
    {
        return bFiberInPremise;
    }

    /**
     * Method: Get account found indicator.
     * @return boolean (true or false)
     * @author Rene Duka  
     */
    public boolean getAccountFound() 
    {
        return bAccountFound;
    }

    /**
     * Method: Get recommended RTID for GPON.
     * @return String
     * @author Rene Duka  
     */
    public String getRecommendedRTID_GPON() 
    {
        return aRecommendedRTID_GPON;
    }

    /**
     * Method: Get recommended due date for u-verse amend service order.
     * @return EaiDate
     * @author Rene Duka  
     */
    public EiaDate getRecommendedDueDate_UVerseSO() 
    {
        return aRecommendedDueDate_UVerseSO;
    }

    /**
     * Get IPDSLAM Proceed indicator.
     * @return boolean  
     * @author Lira Galsim 
     */
    public boolean getIPDSLAMProceedIndicator() 
    {
        return bIPDSLAMProceedIndicator;
    }
    
    /**
     * Get DISH Service Indicator.
     * @return boolean
     * @author Lira Galsim  
     */
	public boolean getDISHServiceIndicator() 
	{
		return bDISHServiceIndicator;
	}
	
	/**
     * Get Non-Published Indicator.
     * @return boolean
     * @author Lira Galsim  
     */
	public boolean getNonPublishedIndicator() 
	{
		return bNonPublishedIndicator;
	}
    
    // ***************************************************************************************    
   
    /**
     * Set object properties.
     * @param ObjectPropertySeqOpt aInput  
     * @author Rene Duka  
     */
    public void setObjectProperties(ObjectProperty[] aInput) 
    {
        aObjectProperties.theValue(aInput);
    }

    /**
     * Set exception data.
     * @param ExceptionData aInput  
     * @author Rene Duka  
     */
    public void setExceptionData(ExceptionData aInput) 
    {
    	if(OptHelper.isExceptionDataOptEmpty(aExceptionData))
		{
    		 aExceptionData.theValue(aInput);
		}
    	else if(aExceptionData.theValue().aDescription.length() < aInput.aDescription.length())
    	{
    		aExceptionData.theValue(aInput);
    	}
    }

    /**
     * Set response driver.
     * @param PublishValidateFacilityNotificationResponseController[] aInput
     * @author Rene Duka  
     */
    public void setResponseDrivers(PublishValidateFacilityNotificationResponseDriver[] aInput) 
    {
    	ArrayList aFinalResponseDrivers = null;
    	if(aResponseDrivers == null)
    	{
    		aResponseDrivers = aInput;
    	}
    	else
    	{
    		aFinalResponseDrivers = new ArrayList();
    		for(int i = 0; i < aResponseDrivers.length; i ++)
    		{
    			aFinalResponseDrivers.add(aResponseDrivers[i]);
    		}
    		if(aFinalResponseDrivers.size() > 0)
    		{	
    			aResponseDrivers = (PublishValidateFacilityNotificationResponseDriver[]) aFinalResponseDrivers.toArray(new PublishValidateFacilityNotificationResponseDriver[aFinalResponseDrivers.size()]);
    		}
    	}
    }
    
    /**
     * Set billing accounts.
     * @param BillingAccont2[] aInput  
     * @author Rene Duka  
     */
    public void setBillingAccounts(BillingAccount2[] aInput) 
    {
        aBillingAccounts.theValue(aInput);
    }

    /**
     * Set facility loops.
     * @param FacilityLoop2[] aInput
     * @author Rene Duka  
     */
    public void setFacilityLoops(FacilityLoop2[] aInput) 
    {
    	if (aInput != null) 
    	{
    		if (OptHelper.isFacilityLoop2SeqOptEmpty(this.aFacilityLoops) == true)  
    		{
    			this.aFacilityLoops.theValue(aInput);
    		 
    		} 
    		else  if (OptHelper.isFacilityLoop2SeqOptEmpty(this.aFacilityLoops) == false
        		&& aInput != null)
    		{
        	
        		ArrayList aTemp = new ArrayList();	
                
        		FacilityLoop2[] aLoops = this.aFacilityLoops.theValue();
                for (int j = 0; j< aLoops.length; j++)
            	{
                	aTemp.add(aLoops[j]);
            	}
                for (int i = 0; i< aInput.length; i++)
            	{
                	aTemp.add(aInput[i]);
            	}
                aLoops = (FacilityLoop2[])aTemp.toArray(new FacilityLoop2[aTemp.size()]);
        		aFacilityLoops.theValue(aLoops);
        	
    		}
    	}
    } 
  
    /**
     * Set network type list.
     * @param String[] aInput  
     * @author Rene Duka  
     */
    public void setNetworkTypeList(String[] aInput) 
    {
        aNetworkTypeList = aInput;
    }

    /**
     * Set number of BBP loops.
     * @param int aInput
     * @author Rene Duka  
     */
    public void setNumberOfBBPLoops(int aInput) 
    {
        aNumberOfBBPLoops = aInput;
    }

    /**
     * Set the number of AT&T DSL.
     * @param int  
     * @author Rene Duka  
     */
    public void setNumberOfATTDSL(int aInput) 
    {
        aNumberOfATTDSL = aInput;
    }

    /**
     * Set the number of non-AT&T DSL.
     * @param int  
     * @author Rene Duka  
     */
    public void setNumberOfNonATTDSL(int aInput) 
    {
        aNumberOfNonATTDSL = aInput;
    }

    /**
     * Set loop with recommended RTID and DSL.
     * @param FacilityLoop2[] aInput  
     * @author Rene Duka  
     */
    public void setFacilityLoopWithRTIDandDSL(FacilityLoop2 aInput) 
    {
        aLoopWithRTIDandDSL = aInput;
    }

    /**
     * Set loops with no BTN for FTTN and FTTN-BP network type.
     * @param ArrayList aInput  
     * @author Rene Duka  
     */
    public void setFacilityLoopsNoBTN_FTTN(ArrayList aInput) 
    {
        aLoopsWithNoBTN_FTTN = aInput;
    }

    /**
     * Set loops with no WTN for FTTPIP network type.
     * @param ArrayList aInput  
     * @author Rene Duka  
     */
    public void setFacilityLoopsNoBTN_FTTP(ArrayList aInput) 
    {
        aLoopsWithNoBTN_FTTP = aInput;
    }

    /**
     * Set loops with no WTN for GPON and RGPON network type.
     * @param ArrayList aInput  
     * @author Rene Duka  
     */
    public void setFacilityLoopsNoBTN_GPON(ArrayList aInput) 
    {
        aLoopsWithNoBTN_GPON = aInput;
    }
    
    /**
     * Set loops with no WTN for IPCO and IPRT network type.
     * @param ArrayList aInput  
     * @author Julius Sembrano  
     */
	public void setALoopsWithNoBTN_IPDSLAM(ArrayList aInput) 
	{
		aLoopsWithNoBTN_IPDSLAM = aInput;
	}

    /**
     * Set loops with P commit.
     * @param FacilityLoop2[] aInput  
     * @author Rene Duka  
     */
    public void setFacilityLoopsWithPCommit(FacilityLoop2[] aInput) 
    {
        aLoopsWithPCommit = aInput;
    }

    /**
     * Set loops with S commit.
     * @param FacilityLoop2[] aInput  
     * @author Rene Duka  
     */
    public void setFacilityLoopsWithSCommit(FacilityLoop2[] aInput) 
    {
        aLoopsWithSCommit = aInput;
    }

    /**
     * Set loops with DSL.
     * @param FacilityLoop2[] aInput  
     * @author Rene Duka  
     */
    public void setFacilityLoopsWithDSL(FacilityLoop2[] aInput) 
    {
        aLoopsWithDSL = aInput;
    }

    /**
     * Set recommended RTID found indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setRecommendedRTIDFound(boolean aInput) 
    {
        bRecommendedRTIDFound = aInput;
    }

    /**
     * Set recommended secondary RTID found indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setRecommendedSecondaryRTIDFound(boolean aInput) 
    {
        bRecommendedSecondaryRTIDFound = aInput;
    }

    /**
     * Set conflict indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setConflictFound(boolean aInput) 
    {
        bConflictFound = aInput;
    }
   
    /**
     * Set U-Verse found indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setUverseFound(boolean aInput) 
    {
        bUverseFound = aInput;
    }

    /**
     * Set U-Verse service order found indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setUverseSOFound(boolean aInput) 
    {
        bUverseSOFound = aInput;
    }

    /**
     * Set DSL found indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setDSLFound(boolean aInput) 
    {
        bDSLFound = aInput;
    }
    
    /**
     * Set DSL misuse indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setDSLMisuse(boolean aInput) 
    {
        bDSLMisuse = aInput;
    }

    /**
     * Set DISH service indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setDISHFound(boolean aInput) 
    {
        bDISHFound = aInput;
    }

    /**
     * Set Dial-up service indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setDialUpFound(boolean aInput) 
    {
        bDialUpFound = aInput;
    }

    /**
     * Set LS-conditioned loop found indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setLSConditionedLoopFound(boolean aInput) 
    {
        bLSConditionedLoopFound = aInput;
    }
    
    /**
     * Set fiber in premise indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setFiberInPremise(boolean aInput) 
    {
        bFiberInPremise = aInput;
    }

    /**
     * Method: Set account found indicator.
     * @param boolean aInput  
     * @author Rene Duka  
     */
    public void setAccountFound(boolean aInput) 
    {
        bAccountFound = aInput;
    }

    /**
     * Method: Set recommended RTID for GPON.
     * @param String aInput
     * @author Rene Duka  
     */
    public void setRecommendedRTID_GPON(String aInput) 
    {
        aRecommendedRTID_GPON = aInput;
    }

    /**
     * Method: Set recommended due date for u-verse amend service order.
     * @param String aInput
     * @author Rene Duka  
     */
    public void setRecommendedDueDate_UVerseSO(EiaDate aInput) 
    {
        aRecommendedDueDate_UVerseSO = aInput;
    }
    
    /**
     * Set DISH Service indicator.
     * @param boolean aInput  
     * @author Lira Galsim  
     */
	public void setDISHServiceIndicator(boolean aInput) 
	{
		bDISHServiceIndicator = aInput;
	}
	
	/**
     * Set Non-Published indicator.
     * @param boolean aInput  
     * @author Lira Galsim  
     */
	public void setNonPublishedIndicator(boolean aInput) 
	{
		bNonPublishedIndicator = aInput;
	}    
    
    // ***************************************************************************************    

    /**
     * Reset indicators in the helper.
     * @author Rene Duka
     */
    public void resetIndicators()
    {
        this.aNumberOfATTDSL = 0;
        this.aNumberOfNonATTDSL = 0;
        this.aLoopWithRTIDandDSL = null;
        this.aLoopsWithPCommit = null;
        this.aLoopsWithSCommit = null;
        this.aLoopsWithDSL = null;
        this.bRecommendedRTIDFound = false;
        this.bRecommendedSecondaryRTIDFound = false;
        this.bConflictFound = false;
        this.bUverseFound = false;
        this.bUverseSOFound = false;
        this.bDSLFound = false;
        this.bDSLMisuse = false;
        this.bDISHFound = false;
        this.bDialUpFound = false;
        this.bFiberInPremise = false;
        this.bAccountFound = false;
        this.bBBPMissingMsgIndicator = false;
        this.bLoadCoilMsgIndicator = false;
    }

 
    
    /**
     * Add the loop with no BTN to the list.
     * @param String        aNetworkType
     * @param FacilityLoop2 aFacilityLoop
     * @author Rene Duka
     */
    public void handleLoopWithNoBTN(
        String aNetworkType,
        FacilityLoop2 aFacilityLoop)
    {
    	PublishValidateFacilityNotificationRequestHelper aRequestHelper = new PublishValidateFacilityNotificationRequestHelper(aUtility,
                aProperties);
        
    	if (aNetworkType != null && aFacilityLoop != null)
        {
    		///////////////////////////////////////////////////////////////////////////////////////////////////                
            // Network Type : FTTN
            // Network Type : FTTNBP
            ///////////////////////////////////////////////////////////////////////////////////////////////////                
            if (aNetworkType.equals(NetworkType2Values.FTTN)
                || aNetworkType.endsWith(NetworkType2Values.FTTNBP))
            {
                this.aLoopsWithNoBTN_FTTN.add(aFacilityLoop);
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////                
            // Network Type : FTTP
            ///////////////////////////////////////////////////////////////////////////////////////////////////                
            else if (aNetworkType.equals(NetworkType2Values.FTTP))
            {
                this.aLoopsWithNoBTN_FTTP.add(aFacilityLoop);
            }
           
            ///////////////////////////////////////////////////////////////////////////////////////////////////                
            // Network Type : IPCO
            // Network Type : IPRT
            ///////////////////////////////////////////////////////////////////////////////////////////////////                
            else if (aNetworkType.equals(NetworkType3Values.IPCO)
                     || aNetworkType.equals(NetworkType3Values.IPRT))
            {
                this.aLoopsWithNoBTN_IPDSLAM.add(aFacilityLoop);
            }
           
            else if (aRequestHelper.isGraniteNtis(aNetworkType)) 
            {
            	this.aLoopsWithNoBTN_Ntis.add(aFacilityLoop);
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////                
            // Network Type : Invalid
            ///////////////////////////////////////////////////////////////////////////////////////////////////                
            else
            {
                // skip            
            }
        }
    }
    
    /**
     * Remove the loop from the no BTN list.
     * @param String        aNetworkType
     * @param FacilityLoop2 aFacilityLoop
     * @author Lira Galsim
     */
    public void removeLoopWithNoBTN(
        String aNetworkType,
        FacilityLoop2 aFacilityLoop)
    {
    	PublishValidateFacilityNotificationRequestHelper aRequestHelper = new PublishValidateFacilityNotificationRequestHelper(aUtility,
                aProperties);
        
    	if (aNetworkType != null && aFacilityLoop != null)
        {
        	///////////////////////////////////////////////////////////////////////////////////////////////////                
            // Network Type : FTTN
            // Network Type : FTTNBP
            ///////////////////////////////////////////////////////////////////////////////////////////////////
            if (aNetworkType.equals(NetworkType2Values.FTTN)
                || aNetworkType.equals(NetworkType2Values.FTTNBP))
            {
                if (this.aLoopsWithNoBTN_FTTN.contains(aFacilityLoop))
                {
                	this.aLoopsWithNoBTN_FTTN.remove(aFacilityLoop);
                }
            }
        	///////////////////////////////////////////////////////////////////////////////////////////////////                
            // Network Type : IP-CO
            // Network Type : FIP-RT
            ///////////////////////////////////////////////////////////////////////////////////////////////////
            if (aNetworkType.equals(NetworkType3Values.IPCO)
                || aNetworkType.equals(NetworkType3Values.IPRT))
            {
                if (this.aLoopsWithNoBTN_IPDSLAM.contains(aFacilityLoop))
                {
                	this.aLoopsWithNoBTN_IPDSLAM.remove(aFacilityLoop);
                }
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////                
            // Network Type : FTTP
            ///////////////////////////////////////////////////////////////////////////////////////////////////                
            else if (aNetworkType.equals(NetworkType2Values.FTTP))
            {
            	if (this.aLoopsWithNoBTN_FTTP.contains(aFacilityLoop))
                {
                	this.aLoopsWithNoBTN_FTTP.remove(aFacilityLoop);
                }
            }
           
            else if (aRequestHelper.isGraniteNtis(aNetworkType)) 
            {
            	if (this.aLoopsWithNoBTN_Ntis.contains(aFacilityLoop))
                {
                	this.aLoopsWithNoBTN_Ntis.remove(aFacilityLoop);
                }
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////                
            // Network Type : Invalid
            ///////////////////////////////////////////////////////////////////////////////////////////////////                
            else
            {
                // skip            
            }
        }
    }    

    /**
     * Handle all loops when SM error encountered in SM.
     * @author Rene Duka
     */
    public void handleLoopsWhenErrorInSM()
    {
        if (this.aResponseDrivers != null) 
        {
        	this.resetLoops();
            for (int i=0; i < this.aResponseDrivers.length; i++) 
            {
                String aNetworkType = this.aResponseDrivers[i].getNT();
                if (!aNetworkType.equals(NetworkType2Values.FTTNBP)
                    && !aNetworkType.equals(NetworkType2Values.RGPON))
                {
                    FacilityLoop2 aLoop = null;
                    String aWTN = this.aResponseDrivers[i].getWTN();
                    if (aWTN.length() > 0)
                    {
                        aLoop = getFacilityLoop(this.aResponseDrivers[i].getWTN());
                        handleLoopWithNoBTN(aNetworkType,
                                            aLoop);

                    }
                }
            }
        }
    }

    /**
     * Update the BTN in the Response Driver using WTN.
     * @param String aBTN
     * @param String aWTN
     * @author Rene Duka  
     */
    public void updateResponseDriver(
        String aBTN,
        String aWTN) 
    {
        if (this.aResponseDrivers != null) 
        {
            for (int i = 0 ; i < this.aResponseDrivers.length ; i++) 
            {
                if (this.aResponseDrivers[i].getWTN() != null
                	&& this.aResponseDrivers[i].getWTN().equalsIgnoreCase(aWTN)) 
                	
                {
                    this.aResponseDrivers[i].setBTN(aBTN);
                }
            }
        }
    }
    
    public void updateUBANResponseDriver(
            String aUban,
            String aWTN) 
        {
            if (this.aResponseDrivers != null) 
            {
                for (int i = 0 ; i < this.aResponseDrivers.length ; i++) 
                {
                    if (this.aResponseDrivers[i].getWTN() != null
                    	&& this.aResponseDrivers[i].getWTN().equalsIgnoreCase(aWTN)) 
                    {
                        this.aResponseDrivers[i].setUban(aUban);
                    }
                }
            }
        }

    public String retrieveUban(String aWTN) 
    {
        String aUban = "";
        if (this.aResponseDrivers != null) 
        {
            for (int i = 0 ; i < this.aResponseDrivers.length ; i++) 
            {
                if (this.aResponseDrivers[i].getWTN().equalsIgnoreCase(aWTN)) 
                {
                	aUban = this.aResponseDrivers[i].getUban();
                    break;
                }
            }
        }
        return aUban;
    }
    /**
     * Retrieve the network type from the Response Drivers using WTN.
     * @param String aWTN
     * @author Rene Duka  
     */
    public String retrieveNT(String aWTN) 
    {
        String aNT = null;
        String aTEMPNT = null;
        if (this.aResponseDrivers != null) 
        {
            for (int i = 0 ; i < this.aResponseDrivers.length ; i++) 
            {
                if (this.aResponseDrivers[i].getWTN() != null
                	&& this.aResponseDrivers[i].getWTN().equalsIgnoreCase(aWTN)) 
                {
                    aTEMPNT = this.aResponseDrivers[i].getNT();
                    if(this.aNTIProcessed.get(aWTN) == null)
                    {
                    	aNT = aTEMPNT;
                    	this.aNTIProcessed.put(aWTN, aNT);

                        break;
                    }
                    else if((this.aNTIProcessed.get(aWTN) != null)&&
                    		(!((String)this.aNTIProcessed.get(aWTN)).equalsIgnoreCase(aTEMPNT)))
                    {
                    	aNT = aTEMPNT;
                    	this.aNTIProcessed.put(aWTN, aNT);

                        break;
                    }
                }
            }
        }
        return aNT;
    }

    /**
     * Retrieve account information using network type.
     * @param String    aNetworkType
     * @param ArrayList aLoopsNoBTNList
     * @return BillingAccount2[]
     * @author Rene Duka  
     */
    public BillingAccount2[] getBillingAccountsByNetworkType(
        String aNetworkType,
        ArrayList aLoopsNoBTNList)
    {
        ArrayList aLoopsList = new ArrayList();
        ArrayList aAccountsList = new ArrayList();

        // get the number of instances of response drivers
        int aNumberOfResponseDrivers = this.aResponseDrivers.length;

        // get the an account list associated with the network type       
        ArrayList aBillingAccountsList = getBillingAccountsList(aNetworkType);
        Iterator aIterator = aBillingAccountsList.iterator();
        while (aIterator.hasNext()) 
        {
            String aBTN = (String) aIterator.next();
            for (int i=0; i < aNumberOfResponseDrivers; i++)
            {
                PublishValidateFacilityNotificationResponseDriver aResponseDriver = this.aResponseDrivers[i];
                if (aResponseDriver.getNT().equalsIgnoreCase(aNetworkType)) 
                {
                    // check if BTN is in process
                    if (aResponseDriver.getBTN() != "")
                    {
                        if (aResponseDriver.getBTN().equalsIgnoreCase(aBTN))
                        {
                            // retrieve loop information
                            FacilityLoop2 aLoop = this.aResponseDrivers[i].getFacilityLoop();
                            aLoopsList.add(aLoop);
                        }
                    }
                }
            }
            
            // format facility loops for BTN
            FacilityLoop2[] aFacilityLoops = null;        
            if (aLoopsList.size() > 0) 
            {
                aFacilityLoops = (FacilityLoop2[]) aLoopsList.toArray(new FacilityLoop2[aLoopsList.size()]);
            }

            // format account
            // add facility loops into account
            // add account into array list
            BillingAccount2 aAccount = getBillingAccount(aBTN);
            if (aAccount != null)
            {
                aAccount.aFacilityLoops.theValue(aFacilityLoops);
                aAccountsList.add(aAccount);
            }
            // initialize loops list
            aLoopsList.clear();
        }

        // check loops with no BTN
        if (aLoopsNoBTNList.size() > 0) {
            FacilityLoop2[] aLoopsNoBTN = (FacilityLoop2[]) aLoopsNoBTNList.toArray(new FacilityLoop2[aLoopsNoBTNList.size()]);
            
            BillingAccount2 aAccount = new BillingAccount2((StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                                                           (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                                                           (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                                                           (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                                                           (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (FacilityLoop2SeqOpt) IDLUtil.toOpt(FacilityLoop2SeqOpt.class, aLoopsNoBTN));
            aAccountsList.add(aAccount);
        }

        // format accounts
        BillingAccount2[] aAccounts = null;        
        if (aAccountsList.size() > 0) 
        {
            aAccounts = (BillingAccount2[]) aAccountsList.toArray(new BillingAccount2[aAccountsList.size()]);
        }
        return aAccounts;
    }

    /**
     * Get the list of Billing Accounts for Granite NTIs
     * @param PublishValidateFacilityNotificationResponseNtis aResponseNtis
     * @param ArrayList aLoopsNoBTNList
     * @return BillingAccount2[]  
     * @author Hongmei Parkin  
     */
    public BillingAccount2[] getBillingAccountsByGraniteNetworkType(
    		PublishValidateFacilityNotificationResponseNtis aResponseNtis,
            ArrayList aLoopsNoBTNList)
    {
        ArrayList aLoopsList = new ArrayList();
        ArrayList aAccountsList = new ArrayList();

        // get the an account list associated with the network type       
        ArrayList aBillingAccountsList = getBillingAccountsList(aResponseNtis.getANetworkType());
        Iterator aIterator = aBillingAccountsList.iterator();
        this.resetLoops();
        
        while (aIterator.hasNext()) 
        {
            String aBTN = (String) aIterator.next();
            FacilityLoop2 aLoop = this.getFacilityLoop(aResponseNtis.getAWTN());
            aLoopsList.add(aLoop);
            
            // format facility loops for BTN
            FacilityLoop2[] aFacilityLoops = null;        
            if (aLoopsList.size() > 0) 
            {
                aFacilityLoops = (FacilityLoop2[]) aLoopsList.toArray(new FacilityLoop2[aLoopsList.size()]);
            }

            // format account
            // add facility loops into account
            // add account into array list
            BillingAccount2 aAccount = getBillingAccount(aBTN);
            if (aAccount != null)
            {
                aAccount.aFacilityLoops.theValue(aFacilityLoops);
                aAccountsList.add(aAccount);
            }
            // initialize loops list
            aLoopsList.clear();
        }

        // check loops with no BTN
        if (aLoopsNoBTNList.size() > 0) 
        {
            FacilityLoop2[] aLoopsNoBTN = (FacilityLoop2[]) aLoopsNoBTNList.toArray(new FacilityLoop2[aLoopsNoBTNList.size()]);
            
            BillingAccount2 aAccount = new BillingAccount2((StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                                                           (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                                                           (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                                                           (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                                                           (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                           (FacilityLoop2SeqOpt) IDLUtil.toOpt(FacilityLoop2SeqOpt.class, aLoopsNoBTN));
            aAccountsList.add(aAccount);
        }

        // format accounts
        BillingAccount2[] aAccounts = null;        
        if (aAccountsList.size() > 0) 
        {
            aAccounts = (BillingAccount2[]) aAccountsList.toArray(new BillingAccount2[aAccountsList.size()]);
        }
        return aAccounts;
    }
    
    /**
     * Get the list of BTNs per network type.
     * @param String aNetworkType
     * @return ArrayList  
     * @author Rene Duka  
     */
    private ArrayList getBillingAccountsList(String aNetworkType) 
    {
    	ArrayList aBTNInProcessList = new ArrayList();
        
        if (this.aResponseDrivers != null) 
        {
	        for (int i = 0 ; i < this.aResponseDrivers.length ; i++) 
	        {
		        // check if NT is in process
		        if (this.aResponseDrivers[i].getNT().equalsIgnoreCase(aNetworkType)) 
		        {
	                // retrieve BTN
	                String aBTN = this.aResponseDrivers[i].getBTN();
	                
	                // check if BTN is in process
	                if (aBTN != "" && !aBTNInProcessList.contains(aBTN))
	                {
	                	// add to the list
	                	aBTNInProcessList.add(aBTN);
                    }
                }
            }
        }
        return aBTNInProcessList;
    }
    
    /**
     * Retrieve account account using billing telephone number.
     * @param String aBTN
     * @return BillingAccount2
     * @author Rene Duka  
     */
    public BillingAccount2 getBillingAccount(String aBTN) 
    {
        BillingAccount2 aAccount = null;
        if (!OptHelper.isBillingAccount2SeqOptEmpty(this.aBillingAccounts)) 
        {
            BillingAccount2[] aAccounts = this.aBillingAccounts.theValue();
            for (int i=0 ; i < aAccounts.length ; i++) 
            {
                if (!OptHelper.isStringOptEmpty(aAccounts[i].aBillingTelephoneNumber)) 
                {
                    if (aBTN.equalsIgnoreCase(aAccounts[i].aBillingTelephoneNumber.theValue())) 
                    {
                        aAccount = aAccounts[i];
                        break;
                    }
                }
            }
        }
        return aAccount;
    }

    /**
     * Retrieve loop information using network type.
     * @param String    aNetworkType
     * @param ArrayList aLoopsNoBTNList
     * @return FacilityLoop2[]
     * @author Rene Duka  
     */
    public FacilityLoop2[] getFacilityLoopsByNetworkType(
        String aNetworkType,
        ArrayList aLoopsNoBTNList) 
    {
        FacilityLoop2[] aLoops = null;        
        ArrayList aLoopsList = new ArrayList();
        if (this.aResponseDrivers != null) 
        {
            for (int i=0; i < this.aResponseDrivers.length; i++) 
            {
                // check if NT is in process
                if (this.aResponseDrivers[i].getNT().equalsIgnoreCase(aNetworkType)) 
                {
                	FacilityLoop2 aLoop = this.aResponseDrivers[i].getFacilityLoop();
                	if (aLoop != null)
                    {
                		aLoopsList.add(aLoop);
                    }
                }
            }
            
            // format accounts
            if (aLoopsList.size() > 0) 
            {
                aLoops = (FacilityLoop2[]) aLoopsList.toArray(new FacilityLoop2[aLoopsList.size()]);
            }
        }
        return aLoops;
    }

    /**
     * Retrieve all loops without working telephone number (WTN).        
     * @return FacilityLoop2[]
     * @author Rene Duka  
     */
    public FacilityLoop2[] getFacilityLoopsNoWTN() 
    {
        FacilityLoop2[] aFacilityLoopsNoWTN = null;
        ArrayList aFacilityLoopsNoWTNList = new ArrayList(); 
        if (!OptHelper.isFacilityLoop2SeqOptEmpty(this.aFacilityLoops))
        {
            FacilityLoop2[] aLoops = this.aFacilityLoops.theValue();
            for (int i=0 ; i < aLoops.length ; i++)
            {
                if (OptHelper.isStringOptEmpty(aLoops[i].aWorkingTelephoneNumber))
                {
                    aFacilityLoopsNoWTNList.add(aLoops[i]);
                }
            }
        }

        // if Loop with no WTN is found
        if (aFacilityLoopsNoWTNList.size() > 0) {
            aFacilityLoopsNoWTN = (FacilityLoop2[]) aFacilityLoopsNoWTNList.toArray(new FacilityLoop2[aFacilityLoopsNoWTNList.size()]);
        }
        return aFacilityLoopsNoWTN;
    }
    
    /**
     * Retrieve all loops with related circuit ID (RTID).
     * @return FacilityLoop2[]
     * @author Rene Duka  
     */
    public FacilityLoop2[] getFacilityLoopsWithRTID() 
    {
        FacilityLoop2[] aFacilityLoopsWithRTID = null;
        ArrayList aFacilityLoopsWithRTIDList = new ArrayList(); 
        if (!OptHelper.isFacilityLoop2SeqOptEmpty(this.aFacilityLoops)) 
        {
            FacilityLoop2[] aLoops = this.aFacilityLoops.theValue();
            for (int i=0 ; i < aLoops.length ; i++) 
            {
                if (!OptHelper.isStringOptEmpty(aLoops[i].aRelatedCircuitID)) 
                {
                    aFacilityLoopsWithRTIDList.add(aLoops[i]);
                }
            }
        }

        // if Loop with RITD is found
        if (aFacilityLoopsWithRTIDList.size() > 0) 
        {
            aFacilityLoopsWithRTID = (FacilityLoop2[]) aFacilityLoopsWithRTIDList.toArray(new FacilityLoop2[aFacilityLoopsWithRTIDList.size()]);
        }
        return aFacilityLoopsWithRTID;
    }

    /**
     * Retrieve all loops with pending service orders (SO).
     * @return FacilityLoop2[]
     * @author Rene Duka  
     */
    public FacilityLoop2[] getFacilityLoopsWithPendingSO() 
    {
        FacilityLoop2[] aFacilityLoopsWithPendingSO = null;
        ArrayList aFacilityLoopsWithPendingSOList = new ArrayList(); 
        if (!OptHelper.isFacilityLoop2SeqOptEmpty(this.aFacilityLoops)) 
        {
            FacilityLoop2[] aLoops = this.aFacilityLoops.theValue();
            for (int i=0 ; i < aLoops.length ; i++) 
            {
                if (!OptHelper.isPendingServiceOrderSeqOptEmpty(aLoops[i].aPendingServiceOrders)) 
                {
                    aFacilityLoopsWithPendingSOList.add(aLoops[i]);
                }
            }
        }

        // if Loop with Pending SO is found
        if (aFacilityLoopsWithPendingSOList.size() > 0) 
        {
            aFacilityLoopsWithPendingSO = (FacilityLoop2[]) aFacilityLoopsWithPendingSOList.toArray(new FacilityLoop2[aFacilityLoopsWithPendingSOList.size()]);
        }
        return aFacilityLoopsWithPendingSO;
    }

    /**
     * Retrieve loop information using working telephone number.
     * @param String aWTN
     * @return FacilityLoop2
     * @author Rene Duka  
     */
    public FacilityLoop2 getFacilityLoop(String aWTN) 
    {
        FacilityLoop2 aFacilityLoop = null;
        if (!OptHelper.isFacilityLoop2SeqOptEmpty(this.aFacilityLoops)) 
        {
            FacilityLoop2[] aLoops = this.aFacilityLoops.theValue();
            for (int i=0 ; i < aLoops.length ; i++) 
            {
                if (!OptHelper.isStringOptEmpty(aLoops[i].aWorkingTelephoneNumber)) 
                {
                    if (aWTN.equalsIgnoreCase(aLoops[i].aWorkingTelephoneNumber.theValue())) 
                    {
                    	if(!this.aLoopProcessed.contains(aLoops[i]))
                    	{
                            aFacilityLoop = aLoops[i];
                            this.aLoopProcessed.add(aFacilityLoop);
                            break;
                    	}
                    	continue;
                    }
                }
            }
        }
        return aFacilityLoop;
    }
    

    // ***************************************************************************************    
    
    
    /**
     * Get partial analysis indicator.
     * 
     * @return boolean (true or false)  
     *   
     * @author Rene Duka  
     */
    public boolean getPartialAnalysisIndicator() 
    {
        return aPartialAnalysisIndicator;
    }

    /**
     * Get working telephone number not in premise indicator.
     * 
     * @return boolean (true or false)
     *   
     * @author Rene Duka  
     */
    public boolean getWorkingTelephoneNumberNotInPremiseIndicator() 
    {
        return aWorkingTelephoneNumberNotInPremiseIndicator;
    }

  
    /**
     * Get error in LFACS indicator.
     * 
     * @return boolean (true or false)
     *   
     * @author Rene Duka  
     */
    public boolean getErrorInLFACSIndicator() 
    {
        return aErrorInLFACSIndicator;
    }

    /**
     * Get error in SM indicator.
     * 
     * @return boolean (true or false)
     *   
     * @author Rene Duka  
     */
    public boolean getErrorInSMIndicator() 
    {
        return aErrorInSMIndicator;
    }

    

    // ***************************************************************************************
    
    /**
     * Set partial analysis indicator.
     * 
     * @param boolean aInput  
     *   
     * @author Rene Duka  
     */
    public void setPartialAnalysisIndicator(boolean aInput) 
    {
        aPartialAnalysisIndicator = aInput;
    }
  
    /**
     * Set working telephone number not in premise indicator.
     * 
     * @param boolean aInput  
     *   
     * @author Rene Duka  
     */
    public void setWorkingTelephoneNumberNotInPremiseIndicator(boolean aInput) 
    {
        aWorkingTelephoneNumberNotInPremiseIndicator = aInput;
    }


    /**
     * Set error in LFACS indicator.
     * 
     * @param boolean aInput  
     *   
     * @author Rene Duka  
     */
    public void setErrorInLFACSIndicator(boolean aInput) 
    {
        aErrorInLFACSIndicator = aInput;
    }

    /**
     * Set error in SM indicator.
     * 
     * @param boolean aInput  
     *   
     * @author Rene Duka  
     */
    public void setErrorInSMIndicator(boolean aInput) 
    {
        aErrorInSMIndicator = aInput;
    }



    

    // ***************************************************************************************    
    
    /**
     * Update the service item properties of a loop using working telephone number.
     *
     * @param String        aWTN
     * @param ServiceItem[] aServiceItems
     *   
     * @author Rene Duka  
     */
    public void updateFacilityLoop(
        String aWTN,
        ServiceItem[] aServiceItems) 
    {
        if (!OptHelper.isFacilityLoop2SeqOptEmpty(this.aFacilityLoops)) 
        {
            for (int i = 0 ; i < this.aFacilityLoops.theValue().length ; i++) 
            {
                if (!OptHelper.isStringOptEmpty(this.aFacilityLoops.theValue()[i].aWorkingTelephoneNumber)) 
                {
                    if (aWTN.equalsIgnoreCase(this.aFacilityLoops.theValue()[i].aWorkingTelephoneNumber.theValue())) 
                    {
                        this.aFacilityLoops.theValue()[i].aServices.theValue(aServiceItems);
//                        break;
                    }
                }
            }
        }
    }
    
    /**   
     * Get BBPMissing flag .
     * @return boolean  
     * @author Vickie Ng  
     */
    public boolean getBBPMissingIndicator() 
    {
        return bBBPMissingIndicator;
    }
    
    /**
     * Get LoadCoilExhausted flag .
     * @return boolean  
     * @author Vickie Ng  
     */
   public boolean getLoadCoilExhaustedIndicator() 
    {
        return bLoadCoilExhaustedIndicator;
    }

    /**
     * Get T Commit flag .
     * @return boolean  
     * @author Vickie Ng  
     */
  public boolean getTCommitIndicator() 
    {
        return bTCommitIndicator;
    }
    
    /**
     * Set BBPMissing flag .
     * @return void  
     * @author Vickie Ng  
     */
 public void setBBPMissingIndicator(boolean aInput)
    {
    	bBBPMissingIndicator = aInput;
    }
    
    /**
     * Set LoadCoilExhausted flag .
     * @return void  
     * @author Vickie Ng  
     */
   public void setLoadCoilExhaustedIndicator(boolean aInput)
    {
    	bLoadCoilExhaustedIndicator = aInput;
    }
    
    /**
     * Set TCommit flag .
     * @return void  
     * @author Vickie Ng  
     */
   public void setTCommitIndicator(boolean aInput)
    {
    	bTCommitIndicator = aInput;
    } 
   
   /**
    * Set number of Load Coil loops.
    * @return int  
    * @author Vickie Ng  
    */
   public void setNumberOfLoadCoilLoops(int aInput) 
   {
       aNumberOfLoadCoilLoops = aInput;
   }
   /**
    * Get number of Load Coil loops.
    * @return int  
    * @author Vickie Ng  
    */
   public int getNumberOfLoadCoilLoops() 
   {
       return aNumberOfLoadCoilLoops;
   }

   /**
    * Gets the BBPMissingMsgIndicator.
    *
    * @return boolean
    * @author Hongmei Parkin
    */
	public boolean getBBPMissingMsgIndicator() 
	{
		return bBBPMissingMsgIndicator;
	}

    /**
     * Sets the BBPMissingMsgIndicator.
     *
     * @return void
     * @author Hongmei Parkin
     */
	public void setBBPMissingMsgIndicator(boolean aInput) 
	{
		bBBPMissingMsgIndicator = aInput;
	}

    /**
     * Gets the LoadCoilMsgIndicator.
     *
     * @return boolean
     * @author Hongmei Parkin
     */
	public boolean getLoadCoilMsgIndicator() 
	{
		return bLoadCoilMsgIndicator;
	}

    /**
     * Sets the LoadCoilMsgIndicator.
     *
     * @return void
     * @author Hongmei Parkin
     */
	public void setLoadCoilMsgIndicator(boolean aInput) 
	{
		bLoadCoilMsgIndicator = aInput;
	}

    /**
     * Gets the ResponseDetail3.
     *
     * @return ResponseDetail3SeqOpt
     * @author Hongmei Parkin
     */
	public ResponseDetail3SeqOpt getResponseDetail3() 
	{
		return aResponseDetail3;
	}

    /**
     * Sets the ResponseDetail3.
     *
     * @return void
     * @author Hongmei Parkin
     */
	public void setResponseDetail3(ResponseDetail3[] responseDetail3) 
	{
		
		aResponseDetail3.theValue(responseDetail3);
	}

    /**
     * Gets the ExceptionDalaList.
     *
     * @return ExceptionDataSeqOp
     * @author Hongmei Parkin
     */	
	public ExceptionDataSeqOpt getExceptionDataList() 
	{
		return aExceptionDataList;
	}

    /**
     * Sets the exception data in the response.
     *
     * @param ExceptionData aInput
     * @return void
     * @author Hongmei Parkin
     */
	public void setExceptionDataList(ExceptionData aInput) 
	{
		
		String aMethodName = "PublishValidateFacilityNotificationResponseHelper: setExceptionDataList()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        try 
        {
			// if the list is empty. set the first element
			if (OptHelper.isExceptionDataSeqOptEmpty(this.aExceptionDataList) == true)  
			{
	   		 
				ExceptionData[] aExceptionDataArray = new ExceptionData[1];
				aExceptionDataArray[0] = aInput;                                                        
				aExceptionDataList.theValue(aExceptionDataArray);
	   		 
	        } 
			// the list is not empty, we add to the list
			else  if (OptHelper.isExceptionDataSeqOptEmpty(this.aExceptionDataList) == false
	       		&& aInput != null)
			{
	       	
	       		ArrayList aTemp = new ArrayList();	
	       		
	       		ArrayList aFinalList = new ArrayList();
	               
	       		ExceptionData[] aList = this.aExceptionDataList.theValue();
	               for (int j = 0; j< aList.length; j++)
	           	{
	               	aTemp.add(aList[j]);               
	           	}
	               
	            aFinalList.addAll(aTemp);
	            
	            for(int k = 0; k < aTemp.size(); k ++)
	            {
	            	ExceptionData tempExceptionData = (ExceptionData)aTemp.get(k);
	            	
       				if(tempExceptionData.aCode.trim().equalsIgnoreCase(aInput.aCode)
       						&& tempExceptionData.aDescription.trim().length() < aInput.aDescription.trim().length())
	       			{	       				
       					aFinalList.remove(k);
       					aFinalList.add(aInput);
	       			}
       				else
       				{
       					//we can't use ArrayList.contais() method because ExceptionData doesn't override .equals() method
       					//this else block acts like .contains() and .equals() at the same time
       					boolean isPresent = false;
       					
       					for(int l = 0; l < aFinalList.size(); l ++)
       					{
       						ExceptionData tempException = (ExceptionData)aFinalList.get(l);
       						if (tempException.aCode.trim().equalsIgnoreCase(aInput.aCode))
       						{
       							isPresent = true;
       							break;
       						}
       					}
       					if(!isPresent)
       					{
       						aFinalList.add(aInput);
       					}
       				}
	            }
	          
	            aList = (ExceptionData[])aFinalList.toArray(new ExceptionData[aFinalList.size()]);
	            
	            aExceptionDataList.theValue(aList);
	       	
	        }
        }
        catch (Exception e) 
        {
        	aUtility.log(LogEventId.DEBUG_LEVEL_1, "Exception in setExceptionDataList" + e.getMessage());   		
    	}
        finally 
        {
        	aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
	}
	
    /**
     * Gets Granite loops with no BTN.
     *
     * @param ArrayList loopsWithNoBTN_Ntis
     * @return void
     * @author Hongmei Parkin
     */
	public ArrayList getLoopsWithNoBTN_Ntis() 
	{
		return aLoopsWithNoBTN_Ntis;
	}

    /**
     * Sets Granite loops with no BTN.
     *
     * @param ArrayList loopsWithNoBTN_Ntis
     * @return void
     * @author Hongmei Parkin
     */
	public void setLoopsWithNoBTN_Ntis(
			ArrayList loopsWithNoBTN_Ntis) 
	{
		aLoopsWithNoBTN_Ntis = loopsWithNoBTN_Ntis;
	}
	
    /**
     * Handles exception.
     *
     * @param Exception x
     * @param PublishValidateFacilityNotificationRequestHelper aRequestHelperc
     * @return void
     * @author Hongmei Parkin
     */	
	public void handleException(
			Exception x, 
			PublishValidateFacilityNotificationRequestHelper aRequestHelper)
    {
		String aMethodName = "PublishValidateFacilityNotificationResponseHelper: handleException()";
	    aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    	ExceptionData aExceptionData = null;
    	
    	try 
    	{
		    if (x instanceof ObjectNotFound) 
		    {
				ObjectNotFound aNewx = (ObjectNotFound) x;	
				aExceptionData = aNewx.aExceptionData;
			}
			else if (x instanceof SystemFailure) 
			{
				SystemFailure aNewx = (SystemFailure) x;
				aExceptionData = aNewx.aExceptionData;
			}
			else if (x instanceof BusinessViolation) 
			{
				BusinessViolation aNewx = (BusinessViolation) x;
				aExceptionData = aNewx.aExceptionData;
			}
			else if (x instanceof DataNotFound) 
			{
				DataNotFound aNewx = (DataNotFound) x;	
				aExceptionData = aNewx.aExceptionData;
			}
			else if (x instanceof AccessDenied) 
			{
				AccessDenied aNewx = (AccessDenied) x;	
				aExceptionData = aNewx.aExceptionData;
			}
			else if (x instanceof InvalidData) 
			{
				InvalidData aNewx = (InvalidData) x;
				aExceptionData = aNewx.aExceptionData;
			}
		    aUtility.log(LogEventId.ERROR, aExceptionData.aCode + aExceptionData.aDescription);
		    // application null for now
		    setResponseExceptionData(aExceptionData,aRequestHelper);
    	}
    	catch (Exception e)
    	{
    		aUtility.log(LogEventId.DEBUG_LEVEL_1, "Exception in handleException(x)" + e.getMessage());   		
    	}
	    finally 
	    {
	    	aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
	    }
    }
	
    /**
     * Sets the exception data in the response.
     *
     * @param ExceptionData aExceptionData
     * @param PublishValidateFacilityNotificationRequestHelper aRequestHelper
     * @return void
     * @author Hongmei Parkin
     */
    private void setResponseExceptionData (
    		ExceptionData aExceptionData, 
    		PublishValidateFacilityNotificationRequestHelper aRequestHelper )
    {
    	String aMethodName = "PublishValidateFacilityNotificationResponseHelper: setResponseExceptionData()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
    	try 
    	{
	    	if (aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)) 
	    	{
	    		this.setExceptionData(aExceptionData);
	    	} 
	    	else if (aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3)
	    			|| aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING)) 
	    	{
	    		this.setExceptionDataList(aExceptionData);  
	    	}
    	}
    	catch (Exception e)
    	{
    		aUtility.log(LogEventId.DEBUG_LEVEL_1, "Exception in setResponseExceptionData" + e.getMessage());   		
    	}
	    finally 
	    {
	    	aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
	    }
    }

    /**
     * Handles exception.
     *
     * @param String aCode
     * @param String aDescripton
     * @param String aOrigination
     * @return void
     * @author Hongmei Parkin
     */
    public void handleException(
        String aCode,
        String aDescription,
        String aOrigination,
        PublishValidateFacilityNotificationRequestHelper aRequestHelper)
    {
    	String aMethodName = "PublishValidateFacilityNotificationResponseHelper: handleException()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        try 
        {
        	if (aCode != null && aDescription != null)
        	{
        		// log: exception message
	            StringBuffer eLogMessage = new StringBuffer();
	            eLogMessage.append("Exception: [ ");
	           
	            eLogMessage.append(aCode);
	            eLogMessage.append(" - ");
	            eLogMessage.append(aDescription);	          
	            eLogMessage.append(" ]");
	            
	            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
	                 
	        	ExceptionData aExceptionData = new ExceptionData(aCode,
	                    aDescription,
	                    (StringOpt) IDLUtil.toOpt((String) aOrigination),
	                    (SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable));
	        	
	        	setResponseExceptionData(aExceptionData,aRequestHelper);
        	 }
        } 
        catch (Exception e)
    	{
    		aUtility.log(LogEventId.DEBUG_LEVEL_1, "Exception in handleException(code)" + e.getMessage());   		
    	}
        finally 
        {
        	aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
	    }
    }

	/**
     * Set IPDSLAM Proceed indicator.
     * @param boolean aInput 
     * @return void 
     * @author Lira Galsim  
     */
    public void setIPDSLAMProceedIndicator(boolean aInput) 
    {
    	bIPDSLAMProceedIndicator = aInput;
    }
    
    public void setAGraniteUVerseFound(String aNTI, boolean newValue)
    {
    	aGraniteUVerseProperties.put(aNTI, String.valueOf(newValue));
    }
    
    /** 
     * Gets the WTNs Already Processed. 
     * @return ArrayList 
     * @author Lira Galsim 
     */ 
    public ArrayList getWTNListAlreadyProcessed () 
    { 
        return aWTNListAlreadyProcessed; 
    } 
 
    /** 
     * Set WTNs Already Processed. 
     * @param ArrayList[] aInput 
     * @return void 
     * @author Lira Galsim 
     */ 
    public void setWTNListAlreadyProcessed (ArrayList aInput) 
    { 
        aWTNListAlreadyProcessed = aInput; 
    }

	/**
     * Get OverrideUNEPLWCIndicator.
     * @param boolean aInput 
     * @return boolean 
     * @author Julius Sembrano 
     */
	public boolean getOverrideUNEPLWCIndicator() 
	{
		return bOverrideUNEPLWCIndicator;
	}

	/**
     * Set OverrideUNEPLWCIndicator.
     * @param boolean aInput 
     * @return void 
     * @author Julius Sembrano 
     */
	public void setOverrideUNEPLWCIndicator(boolean aInput) 
	{
		bOverrideUNEPLWCIndicator = aInput;
	}

	/**
     * Get SiteNotFoundInGraniteIndicator.
     * @param  
     * @return boolean 
     * @author Julius Sembrano 
     */
	public boolean getSiteNotFoundInGranite(String aNTI) 
	{
		boolean bSiteNotFoundInGranite = false;
		
		try
		{
			bSiteNotFoundInGranite = Boolean.valueOf((String)aSiteNotFoundInGraniteProperties.get(aNTI)).booleanValue();
		}
		catch(Exception e)
		{
			
		}
		return bSiteNotFoundInGranite;
	}

	/**
     * Set SiteNotFoundInGraniteIndicator.
     * @param boolean aInput 
     * @return void 
     * @author Julius Sembrano 
     */
	public void setSiteNotFoundInGranite(String aNTI, boolean aInput) 
	{
		aSiteNotFoundInGraniteProperties.put(aNTI, String.valueOf(aInput));
	}	
	
	public boolean checkUVerseFoundInGranite(String aNTI)
	{
    	boolean isGraniteUVerseFound = false;
    	try
    	{
    		isGraniteUVerseFound = Boolean.valueOf((String)aGraniteUVerseProperties.get(aNTI)).booleanValue();
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return isGraniteUVerseFound;
	}
	
	public void resetLoops()
	{
		aLoopProcessed = new ArrayList();
	}
}
