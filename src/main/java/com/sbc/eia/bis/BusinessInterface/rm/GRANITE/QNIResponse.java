//$Id: QNIResponse.java,v 1.42 2009/07/25 02:23:35 js7440 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date         | Author              | Notes
//# ------------------------------------------------------------
//# 01/2009       Jon Costa              Creation.
//# 02/25/2009    Lira Galsim            DR122545: Initialized PendingServiceOrders and Services in getFacilityLoops() method.
//# 03/31/2009    Julius Sembrano        DR125448: CR22919 AMSS to GRANITE Pending SO with due date of 2010 is being returned on ADSL
//# 04/22/2009    Lira Galsim			 DR127798: Fixed the NullPointerException encountered on scenario where path info is not returned by Granite.
//# 05/9/2009     Julius Sembrano        PR24700828/DR128786: Added condition to set PATH NOT FOUND and SITE NOT FOUND in the response helper
//# 05/20/2009    Lira Galsim            DR131633/PR24823669: If RM BIS cannot decide service type from Granite, Billing account structure will not be sent.
//# 07/22/2009    Julius Sembrano        PR25176019: Granite flow, there is no loop info return when WTN is returned from Granite. 
//#									     Modified logic for setting GraniteUverseNotFound and SiteNotFound

package com.sbc.eia.bis.BusinessInterface.rm.GRANITE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationRequestHelper;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationResponseHelper;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationResponseNtis;
import com.sbc.eia.bis.common.utilities.BisDateUtil;
import com.sbc.eia.bis.embus.service.xng.helpers.SendRequestToXNG;
import com.sbc.eia.bis.facades.rm.transactions.PublishValidateFacilityNotification.PublishValidateFacilityNotificationConstants;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.database.GraniteFiberServicesRow;
import com.sbc.eia.bis.rm.database.GraniteFiberServicesTable;
import com.sbc.eia.bis.rm.utilities.BuildEmptyIDL;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2;
import com.sbc.eia.idl.rm_ls_types.PendingServiceOrder;
import com.sbc.eia.idl.rm_ls_types.PendingServiceOrderSeqOpt;
import com.sbc.eia.idl.rm_ls_types.ServiceItem;
import com.sbc.eia.idl.rm_ls_types.ServiceItemSeqOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
 * Response class specific to QNI transaction.
 * 
 * @author jc1421
 */
public class QNIResponse
{   
    private QNISite aSite = null;    
    private QNIPath aPath[] = null;
    private ArrayList aWTNList = null;
    private boolean aDSLFound = false;
    private boolean aUVerseFound = false;
    private boolean isPathFinalState = true;
    private BisContext aContext = null;
    private PublishValidateFacilityNotificationRequestHelper aRequestHelper = null;
    private PublishValidateFacilityNotificationResponseHelper aResponseHelper = null;
    
    // Unused parameters of GRANITE response:
    // private QNIEquipment aEquipment;
    // private QNICable aCable;
    // private QNIGroupGlobal aGrpGlobal;
    // private QNIGroupRec aGrpRec;
    // private String transactionType;
   
    public PublishValidateFacilityNotificationRequestHelper getARequestHelper()
    {
        return aRequestHelper;
    }

    public void setARequestHelper(PublishValidateFacilityNotificationRequestHelper requestHelper)
    {
        aRequestHelper = requestHelper;
    }

    public PublishValidateFacilityNotificationResponseHelper getAResponseHelper()
    {
        return aResponseHelper;
    }

    public void setAResponseHelper(PublishValidateFacilityNotificationResponseHelper responseHelper)
    {
        aResponseHelper = responseHelper;
    }

    public QNIResponse()
    {
        super();
    }
    
    /**
     * @param aNetworkTypes
     * @param aOrdNbr
     * @param aOrdActionType
     * @param aDueDateFromClient
     * @param aUtility
     * @param aProperties
     * @return PublishValidateFacilityNotificationResponseNtis[]
     */
    public PublishValidateFacilityNotificationResponseNtis[] getGraniteResponse(String[] aNetworkTypes,
                                                                                String aOrdNbr,
                                                                                String aOrdActionType,
                                                                                EiaDate aDueDateFromClient,
                                                                                Utility aUtility,
                                                                                Hashtable aProperties) throws InvalidData,
                                                                                                      AccessDenied,
                                                                                                      BusinessViolation,
                                                                                                      SystemFailure,
                                                                                                      NotImplemented,
                                                                                                      ObjectNotFound,
                                                                                                      DataNotFound
    {
        String myMethodName = "QNIResponse: getGraniteResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        PublishValidateFacilityNotificationResponseNtis[] aPVFNRNArr = null;
        // Must have response values populated

        try
        {
            if (aNetworkTypes != null && aNetworkTypes.length > 0)
            {
                ArrayList aPVFNRNList = new ArrayList();
                aWTNList = new ArrayList();
                
                for (int i = 0; i < aNetworkTypes.length; i++)
                {
                    PublishValidateFacilityNotificationResponseNtis aPVFNRN = 
                        new PublishValidateFacilityNotificationResponseNtis(aUtility, aProperties);
                    // One object per networkType
                    aPVFNRN.setANetworkType(aNetworkTypes[i]);

                    // Do we have a valid GRANITE response, we must have a Site, we may not have Path(s)
                    if (this.aSite != null)
                    {
                        aUtility.log(LogEventId.INFO_LEVEL_1, "Process GRANITE data for: [" + aNetworkTypes[i] + "]");
                        // preset to false,  getFacilityLoops() method will reset as necessary.   
                        aUVerseFound = false;
                        aDSLFound = false; 
                        FacilityLoop2[] aFacilityLoop = getFacilityLoops(aNetworkTypes[i],
                                                                         aOrdNbr,
                                                                         aOrdActionType,
                                                                         aDueDateFromClient,
                                                                         aUtility,
                                                                         aProperties);
                        // ---- okToProceedIndicator rules ----
                        // RM BIS shall set this indicator to true if:
                        // - If the call to Granite is not successful
                        // - If the NTI in the client request is not in the NTI list from Granite’s response
                        // - If the WTN Not Found Indicator is true
                        // - If there are no conflicting services
                        if (aFacilityLoop != null)
                        {
                            aPVFNRN.setFacilityLoops(aFacilityLoop);
                            aPVFNRN.setAGraniteUVerseFound(aNetworkTypes[i], this.aUVerseFound);
                            
                            if (!OptHelper.isStringOptEmpty(aFacilityLoop[0].aWorkingTelephoneNumber))
                            {
                                aPVFNRN.setAWTN(aFacilityLoop[0].aWorkingTelephoneNumber.theValue());
                                
                                // If the client sent a WTN and the WTN does NOT match
                                if (aRequestHelper.getWorkingTelephoneNumber() != null &&
                                    !aRequestHelper.getWorkingTelephoneNumber().equalsIgnoreCase(aPVFNRN.getAWTN()))
                                {
                                    aPVFNRN.setWTNnotInPremiseIndicator(true);
                                    aPVFNRN.setOkToProceedIndicator(true);
                                }
                            }
                            if (aFacilityLoop[0] != null
                                && !OptHelper.isServiceItemSeqOptEmpty(aFacilityLoop[0].aServices)
                                && aFacilityLoop[0].aServices.theValue()[0] != null
                                && !aFacilityLoop[0].aServices.theValue()[0].aConflictingServiceIndicator)
                            {
                                // aConflictingServiceIndicator is false,
                                // okToProceed is true
                                aPVFNRN.setOkToProceedIndicator(true);
                            }
                            if (this.aSite.getANTIConversionDate() != null)
                            {
                                aPVFNRN.setDueDate(BisDateUtil.toEiaDate(this.aSite.getANTIConversionDate(),
                                                                         GRANITE.DATE_FORMAT));
                            }
                            else
                            {
                                // If no NTI conversion date, get pending order due date
                                aPVFNRN.setDueDate(getDueDate(aDueDateFromClient, aProperties));
                            }

                            if (aDSLFound)
                            {
                                aPVFNRN.setDSLFound(true);
                                aResponseHelper.setDSLFound(true);
                                aPVFNRN.setFacilityLoopsWithDSL(aFacilityLoop);
                                aResponseHelper.setFacilityLoopsWithDSL(aFacilityLoop);
                            }
                        }
                        else
                        {
                            // If no facilityLoop found
                            aPVFNRN.setOkToProceedIndicator(true);
                        }
                    }
                    else
                    {
                        // No response data, GRANITE failure is assumed, default values are used.
                    	aResponseHelper.setSiteNotFoundInGranite(aNetworkTypes[i], true);
                        aResponseHelper.setErrorInLFACSIndicator(true); // Granite uses same SCx msg based on this indicator.
                        aPVFNRN.setOkToProceedIndicator(true);                        
                    }
                    aPVFNRNList.add(aPVFNRN);
                }// end for

                if (aPVFNRNList.size() > 0)
                {
                    aPVFNRNArr = (PublishValidateFacilityNotificationResponseNtis[]) 
                    aPVFNRNList.toArray(new PublishValidateFacilityNotificationResponseNtis[aPVFNRNList.size()]);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ExceptionBuilderResult aExceptionBuilderResult = 
                ExceptionBuilder.parseException(aContext,
                                                (String) aProperties.get(SendRequestToXNG.XNG_EXCEPTION_RULE_FILE_TAG),
                                                null,
                                                e.getMessage(),
                                                e.getMessage(),
                                                false,
                                                ExceptionBuilderRule.NO_DEFAULT,
                                                null,
                                                e,
                                                aUtility,
                                                GRANITE.HostName,
                                                null,
                                                null);
            aResponseHelper.setErrorInLFACSIndicator(true); // Granite uses same SCx msg based on this indicator.
            aResponseHelper.handleException(aExceptionBuilderResult.getException(), aRequestHelper);          
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aPVFNRNArr;
    }
    
    /**
     * @param aNetworkType
     * @param aOrdNbr
     * @param aOrdActionType
     * @param aDueDateFromClient
     * @param aUtility
     * @param aProperties
     * @return FacilityLoop2[]
     */
    private FacilityLoop2[] getFacilityLoops(String aNetworkType,
                                             String aOrdNbr,
                                             String aOrdActionType,
                                             EiaDate aDueDateFromClient,
                                             Utility aUtility,
                                             Hashtable aProperties)
    {
        String myMethodName = "QNIResponse: getFacilityLoop()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        
        FacilityLoop2[] aLoop = null; // we return an array, but for GRANITE, there is one loop per NetworkType
        // Per requirements, we use Path structure response data but require a
        // matching Site NTI. This verifies that the Site is capable for this networkType
        if (foundSiteNTI(aNetworkType, aSite.getANtiGrp(), aUtility))
        {
            // We have a match in Site structure, create a single element for this aNetworkType.
            aLoop = new FacilityLoop2[] { new FacilityLoop2() };
            aLoop[0].aWorkingTelephoneNumber = BuildEmptyIDL.buildEmptyStringOpt();
            aLoop[0].aCircuitId = BuildEmptyIDL.buildEmptyStringOpt();
            aLoop[0].aRelatedCircuitID = BuildEmptyIDL.buildEmptyStringOpt();
            aLoop[0].aCommitStatus = BuildEmptyIDL.buildEmptyStringOpt();
            aLoop[0].aBroadbandPair = BuildEmptyIDL.buildEmptyStringOpt();
            aLoop[0].aServices = (ServiceItemSeqOpt) IDLUtil.toOpt(ServiceItemSeqOpt.class, null);
            aLoop[0].aPendingServiceOrders = (PendingServiceOrderSeqOpt) IDLUtil.toOpt(PendingServiceOrderSeqOpt.class, null);
           
            String aNTI = null;
            String aCircuitId = null;
            String aWTN = null;
            String aVOICE = GRANITE.NO_VAL;
            String aVLAN = GRANITE.NO_VAL;
            String aADSL = GRANITE.NO_VAL;
            String aVDSL = GRANITE.NO_VAL;
            String aGPON = GRANITE.NO_VAL;
            String aORDER_MATCH = GRANITE.NO_VAL;
            ArrayList aPendingServiceOrders = new ArrayList();

            if (aPath != null)
            {
            	for (int i = 0; i < aPath.length; i++)
                {
                    if (aPath[i].getAAttrGrp() != null
                        && compareNTI(aNetworkType,aPath[i].getAAttrGrp().getAPathNTI(),aPath[i].getAAttrGrp().getAPathNTIModifier(),aUtility)
                        && (aPath[i].getAStatus().trim().toUpperCase().startsWith(GRANITE.PENDING_STATUS)
                        		|| aPath[i].getAStatus().trim().toUpperCase().startsWith(GRANITE.IN_EFFECT_STATUS))                        )
                    {
                        this.isPathFinalState = true;
                        PendingServiceOrder aPendingSO = getPendingServiceOrder(aDueDateFromClient, 
                                                                                aPath[i], 
                                                                                aUtility,
                                                                                aProperties);

                        if (!isPathFinalState) continue; // Path has Pending order beyond client due date
                        
                        if (aPendingSO != null)
                        {
                            aPendingServiceOrders.add(aPendingSO);
                            if (compareOrderNumbers(aOrdNbr, aPendingSO.aOrderNumber))
                            {
                                aUtility.log(LogEventId.INFO_LEVEL_1, "Granite order number [" + 
                                             aPendingSO.aOrderNumber.theValue() + "] matches order number provided [" +
                                             aOrdNbr + "]");
                                aORDER_MATCH = GRANITE.YES_VAL;
                            }
                        }
                        aNTI = aPath[i].getAAttrGrp().getAPathNTI();
                        if (aPath[i].getACategory() != null)
                        {
                            if (aPath[i].getAID() != null && aPath[i].getAID().trim().length() > 0)
                            {
                                // The aID has TN(voice) or circuit(vlan), category tells us which one.
                                if (aPath[i].getACategory().equalsIgnoreCase(GRANITE.VOICE_VAL))
                                {
                                    aVOICE = GRANITE.YES_VAL;
                                    aWTN = aPath[i].getAID().replaceAll("-", "").trim();
                                    aLoop[0].aWorkingTelephoneNumber.theValue(aWTN);
                                    aLoop[0].aCircuitId.theValue(aWTN);
                                    aWTNList.add(aWTN);
                                }
                                else if (aPath[i].getACategory().equalsIgnoreCase(GRANITE.VLAN_VAL))
                                {
                                    aVLAN = GRANITE.YES_VAL;
                                    // Note: This could be either a TN or a circuit, replace for either value.
                                    aCircuitId = aPath[i].getAID().replaceAll("-", "").replace('/', '.').trim();
                                    aLoop[0].aCircuitId.theValue(aCircuitId);
                                }
                            }
                            if (aPath[i].getACategory().equalsIgnoreCase(GRANITE.ADSL_VAL))
                            {
                                aUtility.log(LogEventId.INFO_LEVEL_1, "DSL Found on Path");
                                this.aDSLFound = true;   // Set DSL Found indicator for use in building response.
                                aADSL = GRANITE.YES_VAL; // set data base lookup value.
                            }
                            if (aPath[i].getACategory().equalsIgnoreCase(GRANITE.VDSL_VAL)) aVDSL = GRANITE.YES_VAL;
                            if (aPath[i].getACategory().equalsIgnoreCase(GRANITE.GPON_VAL)) aGPON = GRANITE.YES_VAL;
                        }
                    }
                }
            }
            
            if (aPendingServiceOrders.size() > 0)
            {
                aLoop[0].aPendingServiceOrders = 
                    (PendingServiceOrderSeqOpt) IDLUtil.toOpt(PendingServiceOrderSeqOpt.class,
                        (PendingServiceOrder[]) aPendingServiceOrders.toArray(
                            new PendingServiceOrder[aPendingServiceOrders.size()]));
            }
            aLoop[0].aServices = getServices(aNTI,
                                             aVOICE,
                                             aVLAN,
                                             aADSL,
                                             aVDSL,
                                             aGPON,
                                             aORDER_MATCH,
                                             aWTN,
                                             aCircuitId,
                                             aUtility,
                                             aProperties);

            if (OptHelper.isServiceItemSeqOptEmpty(aLoop[0].aServices))
            {
            	aLoop = null;
            }
        }
        else
        {
        	aResponseHelper.setSiteNotFoundInGranite(aNetworkType, true);
        }
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aLoop;
    }
    
    /**
     * @param clientProvidedNTI
     * @param aNtiGrp
     * @param aUtility
     * @return boolean
     */
    private boolean foundSiteNTI(String clientProvidedNTI, NtiGrp SiteNTI[], Utility aUtility)
    {
        if (SiteNTI != null)
        {        
            for (int i = 0; i < SiteNTI.length; i++)
            {
                if(compareNTI(clientProvidedNTI, SiteNTI[i].getASiteNTI(), SiteNTI[i].getASiteNTIModifier(), aUtility))
                {
                    return true;
                }
            }
        }     
        return false;
    }
    
    /**
     * @param aNetworkType
     * @param aNTI
     * @param aNTIModifier
     * @param aUtility
     * @return boolean
     */
    private boolean compareNTI(String aNetworkType, String aNTI, String aNTIModifier, Utility aUtility)
    {
        String myMethodName = "QNIResponse: compareNTI()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        
        // GRANITE NTI response comparison
        // Valid NetworkTypes: RGPON, FTTP-EGPON, FTTC-EGPON, FTTP-RGPON, FTTP-GPON, FTTC-GPON
        // Set graniteNetworkType to the combination of NTI(FTTP) + NTIModifer(EGPON)
        String graniteNetworkType = "";
        String graniteModifier = "";
        
        if (aNTI != null && aNTI.trim().length() > 0)
        {
            graniteNetworkType = aNTI.trim();
        }
        if(aNTIModifier != null && aNTIModifier.trim().length() > 0)
        {
            graniteModifier = aNTIModifier.trim();
            
            if(graniteNetworkType.length() > 0) 
            { 
                graniteNetworkType = graniteNetworkType + "-"; 
            }
            graniteNetworkType = graniteNetworkType + graniteModifier;
        }
        
        // If we have valid values to compare...
        if(aNetworkType != null && aNetworkType.trim().length() > 0 && graniteNetworkType.length() > 0)
        {
            // Check both, the aNetworkType maybe "RGPON" but Granite returns NTI("FTTC"), NTIModifier("RGPON") 
            if (aNetworkType.trim().equalsIgnoreCase(graniteNetworkType) ||
                aNetworkType.trim().equalsIgnoreCase(graniteModifier))
            {
                aUtility.log(LogEventId.INFO_LEVEL_1, "Found NTI:[" + aNetworkType + "] in GRANITE response.");
                return true;
            }
        }
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return false;
    }
    
    /**
     * @param clientOrderNbr
     * @param graniteOrderNbr
     * @return boolean
     */
    private boolean compareOrderNumbers(String clientOrderNbr, StringOpt graniteOrderNbr)
    {
        if (clientOrderNbr != null && graniteOrderNbr != null)
        {
            try
            {
                // Compare order numbers, ignore case on the chance there may be embedded alpha character(s).
                if(clientOrderNbr.trim().equalsIgnoreCase(graniteOrderNbr.theValue().trim()))
                {   
                    return true; // Order numbers match
                }
            }
            catch (Exception any) // ignore exceptions, default to false return.
            {}
        }
        return false;
    }
    
    /**
     * @param aNTI
     * @param aVOICE
     * @param aVLAN
     * @param aADSL
     * @param aVDSL
     * @param aGPON
     * @param aORDER_MATCH
     * @param aWTN
     * @param aCircuitId
     * @param aUtility
     * @param aProperties
     * @return ServiceItemSeqOpt
     */
    private ServiceItemSeqOpt getServices(String aNTI,
                                          String aVOICE,
                                          String aVLAN,
                                          String aADSL,
                                          String aVDSL,
                                          String aGPON,
                                          String aORDER_MATCH,
                                          String aWTN,
                                          String aCircuitId,
                                          Utility aUtility,
                                          Hashtable aProperties)
    {
        String myMethodName = "QNIResponse: getServices()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        
        ServiceItemSeqOpt aServices = new ServiceItemSeqOpt(); 
        aServices.__default();
        GraniteFiberServicesTable aGraniteFiberServicesTable = new GraniteFiberServicesTable();
        GraniteFiberServicesRow aGFSRow = null;
        
        if (aNTI != null)
        {
            try
            {
            	aGFSRow = aGraniteFiberServicesTable.retrieveRow(aNTI,
                                                                 aORDER_MATCH,
                                                                 aADSL,
                                                                 aVDSL,
                                                                 aGPON,
                                                                 aVOICE,
                                                                 aVLAN,
                                                                 aProperties,
                                                                 aUtility);
            }
            catch (SQLException sqle)
            {
                aUtility.log(LogEventId.ERROR, "GraniteFiberServicesTable access failed with: " + sqle.getMessage());
                aUtility.log(LogEventId.INFO_LEVEL_1, "Continuing with default values.");
            }
        }
        // Create Services return object array
        ServiceItem[] aServiceItem = null;
        if (aGFSRow != null)
        {
        	aServiceItem = new ServiceItem[] { new ServiceItem() };
        	
        	// Populate values based on retrieved data base row.
            aServiceItem[0].aServiceType = IDLUtil.toOpt(aGFSRow.getSERVICE_NAME());
            aServiceItem[0].aConflictingServiceIndicator = 
                aGFSRow.getCONFLICT_IND().equalsIgnoreCase(GRANITE.YES_VAL) ? true : false;
            
            if (aGFSRow.getSERVICE_NAME().equalsIgnoreCase(
                    PublishValidateFacilityNotificationConstants.SERVICE_NAME_UVERSE))
            {
                if(aResponseHelper != null) aResponseHelper.setUverseFound(true);
                this.aUVerseFound = true;
            }
        }
        
        if (aServiceItem != null)
        {
        	aServiceItem[0].aServiceItemProperties = new ObjectPropertySeqOpt();
            aServiceItem[0].aServiceItemProperties.__default();
            
            if(aWTN != null && aWTN.trim().length() > 0 && aCircuitId != null && aCircuitId.trim().length() > 0)
            {
                // a Voice(CKID) and VLAN(CKID1) value exist
                ObjectPropertyManager objPropertyMgr = new ObjectPropertyManager(aServiceItem[0].aServiceItemProperties);
                objPropertyMgr.add(new ObjectProperty(GRANITE.VOICE_CKID_TAG, aWTN));
                objPropertyMgr.add(new ObjectProperty(GRANITE.VLAN_CKID_TAG, aCircuitId));
                aServiceItem[0].aServiceItemProperties = 
                    (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, objPropertyMgr.toArray());  
            }
            
            aServices.theValue(aServiceItem);
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aServices;
    }
    
    /**
     * @param aDueDateFromClient
     * @param aPathObj
     * @param aUtility
     * @return PendingServiceOrder
     */
    private PendingServiceOrder getPendingServiceOrder(EiaDate aDueDateFromClient, 
                                                       QNIPath aPathObj,
                                                       Utility aUtility,
                                                       Hashtable aProperties)
    {
        String myMethodName = "QNIResponse: getPendingServiceOrder()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        
        PendingServiceOrder aPendingServiceOrder = null;
        
        // Select only "Pending" service orders as determined by the status.
        // Criteria:  1) order value exists and has non white space characters
        //            2) status value exists and has non white space characters
        //            3) the status begins with the pending key word (pending or pending disconnect)
        if(aPathObj.getAOrderNumber() != null && aPathObj.getAOrderNumber().trim().length() > 0 &&
                aPathObj.getAStatus() != null && aPathObj.getAStatus().trim().length() > 0 &&
                aPathObj.getAStatus().trim().toUpperCase().startsWith(GRANITE.PENDING_STATUS))
        {
            // Capture orders where: dueDate <= client due date OR if no client due date value in request.
            if (compareDueDates(aDueDateFromClient, aPathObj.getAAttrGrp().getADueDate(), aProperties))
            {
                aPendingServiceOrder = new PendingServiceOrder();
                aPendingServiceOrder.aOrderType   = IDLUtil.toOpt(aPathObj.getAOrderType());
                aPendingServiceOrder.aOrderNumber = IDLUtil.toOpt(aPathObj.getAOrderNumber());
                aPendingServiceOrder.aDueDate     = BisDateUtil.toEiaDateOpt(aPathObj.getAAttrGrp().getADueDate(), 
                                                                                GRANITE.DATE_FORMAT);
                if (aPathObj.getAID() != null && aPathObj.getAID().trim().length() > 0)
                {
                    //  Note: This could be either a TN or a circuit, replace for either value.
                    aPendingServiceOrder.aCircuitId   
                                = IDLUtil.toOpt(aPathObj.getAID().replaceAll("-", "").replace('/', '.').trim());
                }
                aUtility.log(LogEventId.INFO_LEVEL_1, "Found Pending order: [" + aPathObj.getAOrderNumber() + "]");
            }
            // determine if the Path should be ignored
            if (aDueDateFromClient != null)
            {
                if (aPathObj.getAAttrGrp().getADueDate() != null)
                {
                    Calendar aClientDD = Calendar.getInstance();
                    Calendar aGraniteDD = Calendar.getInstance();
                    EiaDate aGDD = BisDateUtil.toEiaDate(aPathObj.getAAttrGrp().getADueDate(), GRANITE.DATE_FORMAT);

                    aClientDD.set((int) aDueDateFromClient.aYear,
                                  (int) aDueDateFromClient.aMonth,
                                  (int) aDueDateFromClient.aDay);
                    aGraniteDD.set((int) aGDD.aYear, (int) aGDD.aMonth, (int) aGDD.aDay);

                    if (aGraniteDD.after(aClientDD)) this.isPathFinalState = false;
                }
            }
        }    
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aPendingServiceOrder;
    }
    
    /**
     * @param aDueDateFromClient
     * @param GraniteDueDate
     * @return boolean
     */
    private boolean compareDueDates(EiaDate aDueDateFromClient, 
    								String GraniteDueDate,
    								Hashtable aProperties)
    {
        // The point of this comparison is to determine if the due date will keep
        // us from considering the pending order. Pending orders where the due date
        // is less than or equal to the due date sent by the client are returned.
        // If there is no due date from the client, we always return the order.
        if (aDueDateFromClient != null)
        {
            if (GraniteDueDate != null)
            {
                try
                {
                    Calendar aClientDD = Calendar.getInstance();
                    Calendar aGraniteDD = Calendar.getInstance();
                    EiaDate aGDD = BisDateUtil.toEiaDate(GraniteDueDate, GRANITE.DATE_FORMAT);

                    aClientDD.set((int) aDueDateFromClient.aYear,
                                  (int) aDueDateFromClient.aMonth,
                                  (int) aDueDateFromClient.aDay);
                    aGraniteDD.set((int) aGDD.aYear, (int) aGDD.aMonth, (int) aGDD.aDay);

                    if (aGraniteDD.after(aClientDD)) 
                    {
                    	return false;
                    }
                }
                catch (Exception any)
                {}
            }
        }
        //CR22919: If the pending service order has a due date of greater than 120 days it will be excluded from evaluation.
        else
        {
        	if (GraniteDueDate != null)
        	{
        		try
        		{
                	Calendar calN = Calendar.getInstance();
                	calN.add(Calendar.DATE, Integer.parseInt((String) aProperties.get("N_DAYS")));
                	Date dateN = new Date (calN.get(Calendar.YEAR),
        		   			   			   calN.get(Calendar.MONTH) + 1,
        		   			   			   calN.get(Calendar.DATE));
                	
                	EiaDate aGDD = BisDateUtil.toEiaDate(GraniteDueDate, GRANITE.DATE_FORMAT);
                	Date aGraniteDD = new Date((int)aGDD.aYear,
                								(int)aGDD.aMonth,
                								(int)aGDD.aDay);
                	
                	if(aGraniteDD.after(dateN))
                	{
                		return false;
                	}
        		}
        		catch(Exception any)
        		{
        			// nothing to do
        		}
        	}
        }
        return true;
    }
    
    /**
     * @return EiaDate
     */
    private EiaDate getDueDate(EiaDate aDueDateFromClient, Hashtable aProperties)
    {
        EiaDate aDueDate = null;
        Calendar aClientDD = null;
        boolean clientDueDateExists = false;
        
        if (aDueDateFromClient != null)
        {
            aDueDate = aDueDateFromClient; // if no pending order due dates exist/apply, use client's date
            aClientDD = Calendar.getInstance();
            aClientDD.set((int) aDueDateFromClient.aYear,
                          (int) aDueDateFromClient.aMonth,
                          (int) aDueDateFromClient.aDay);
            clientDueDateExists = true;
        }
        
        if (this.aPath != null)
        {
            for (int i = 0; i < this.aPath.length; i++)
            {   
                // Long if statement, we need:
                // 1) a service order
                // 2) pending status)
                // 3) with a valid due date
                if (this.aPath[i].getAOrderNumber() != null && 
                    this.aPath[i].getAOrderNumber().length() > 0 &&
                    this.aPath[i].getAStatus() != null && 
                    this.aPath[i].getAStatus().trim().toUpperCase().startsWith(GRANITE.PENDING_STATUS) &&
                    this.aPath[i].getAAttrGrp() != null &&
                    this.aPath[i].getAAttrGrp().getADueDate() != null)
                {
                    EiaDate aGDD = BisDateUtil.toEiaDate(this.aPath[i].getAAttrGrp().getADueDate(), 
                                                         GRANITE.DATE_FORMAT);
                    Calendar aThisDD = Calendar.getInstance();
                    aThisDD.set((int) aGDD.aYear, (int) aGDD.aMonth, (int) aGDD.aDay);
                    
                    if (clientDueDateExists && aThisDD.after(aClientDD)) continue; // Ignore orders later than client's
                    
                    if (this.aPath[i].getACategory() != null &&
                        this.aPath[i].getACategory().equalsIgnoreCase(GRANITE.VLAN_VAL))
                    {
                        // Set due date to pending UVerse order and check no further
                        aDueDate = aGDD;
                        break;
                    }
                    
                    if(aDueDate != null)
                    {
                        // Set due date to the latest pending order
                        Calendar aPreviousDD = Calendar.getInstance();
                        aPreviousDD.set((int) aDueDate.aYear, (int) aDueDate.aMonth, (int) aDueDate.aDay);
                        if(aThisDD.after(aPreviousDD)) aDueDate = aGDD;   
                    }
                    // CR22919: If the pending service order has a due date of greater than 120 days it will be excluded from evaluation.
                    else if(aDueDate == null)
                    {
                    	Calendar calN = Calendar.getInstance();
                    	calN.add(Calendar.DATE, Integer.parseInt((String) aProperties.get("N_DAYS")));
                    	
                    	if(calN.after(aThisDD))
                    	{
                    		aDueDate = aGDD;
                    	}
                    }
                }
            }
        }
        
        return aDueDate;
    }

    public QNIPath[] getAPath()
    {
        return aPath;
    }

    public void setAPath(QNIPath[] path)
    {
        aPath = path;
    }

    public QNISite getASite()
    {
        return aSite;
    }

    public void setASite(QNISite site)
    {
        aSite = site;
    }

    public ArrayList getAWTNList()
    {
        return aWTNList;
    }

    public void setAWTNList(ArrayList list)
    {
        aWTNList = list;
    }

    public BisContext getAContext()
    {
        return aContext;
    }

    public void setAContext(BisContext context)
    {
        aContext = context;
    }
}