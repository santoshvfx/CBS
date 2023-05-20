//$Id: LFACS.java,v 1.121 2009/07/31 18:33:33 lg4542 Exp $
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
//# 07/16/2007   Rene Duka             Creation for LS6.
//# 11/13/2007   Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 11/28/2007   Rene Duka             Defect 79174: DryLoop not identified in Publish as conflicting service. 
//#                                                  Publish is incorreclty recommending ok to proceed.
//# 12/03/2007   Rene Duka             Defect 79379: pVFN not concatenating NPA correctly when formatting WTN. 
//#                                    Defect 79198: pVFN returns incorrect log message for maxPairsToAnalyze equal to 0.
//# 12/04/2007   Rene Duka             Defect 79385: pVFN throwing an error if CLLI8 is empty. 
//# 12/11/2007   Rene Duka             Defect 79919: When RM BIS is converting the Order number into OrderType and OrderNumber, 
//#                                                  RM BIS is missing the first digit from the OrderNumber
//# 12/11/2007   Rene Duka             CR 16563: Project Lightspeed - Release 6.0.
//# 12/17/2007   Rene Duka             Defect 80248: Null pointer exception if segments are null. 
//#                                    Defect 80317: Address with Fiber returns null pointer exception.
//# 12/17/2007   Rene Duka             Defect 80022: SOACServiceOrderNumber issue.
//# 12/19/2007   Rene Duka             Defect 79679: Changes in the CLS-formatted circuit ID rules.
//# 12/20/2007   Rene Duka             Defect 80252: Recommended Due Date issue.
//# 12/27/2007   Shyamali Banerjee     Defect 80925: While testing PVFN, RMBIS is returning a serviceType of PON instead of GPON/BPON.
//# 12/27/2007   Shyamali Banerjee     Defect 80908: While testing pVFN, when LFACS sends a Model = M-300-A-1 or M-300-A-2 or M-300-A-3 Terminal Type = ONT and  LTS is blank, 
//                                                   it is being considered as PON service type.
//# 12/27/2007   Rene Duka             Defect 80628: Address with Fiber and Segment info results in Null Pointer.
//# 12/31/2007   Rene Duka             Defect 80925: While testing PVFN, RMBIS is returning a serviceType of PON instead of GPON/BPON.
//# 01/02/2008   Rene Duka             Defect 81327: Address without loop information results in Null Pointer.
//# 01/15/2008   Rene Duka             Defect 82602: Determine the service items from pending service order information.
//# 02/12/2008   Rene Duka             LS 7.
//# 02/14/2008   Shyamali Banerjee     LS 7.
//# 02/21/2008   Shyamali Banerjee     LS7: Changed INQOSP methods to public.
//# 02/26/2008   Jon Costa			   LS7: INQOSP updated.
//# 03/04/2008   Rene Duka             Defect 87321: When NTI FTTN-BP sent from any client no loop analysis is returned to client.
//# 03/04/2008   Rene Duka             Defect 87345: When NTI FTTPIP sent from any client no loop analysis is returned to client.
//# 03/07/2008   Rene Duka             Defect 87142: System Failure when testing GPON.
//# 03/07/2008   Rene Duka             Defect 87589: CKID concatenad for multiple pending service orders.
//# 03/09/2008   Rene Duka             Defect 87644: Recommended RTID not returned for GPON.
//# 03/10/2008   Rene Duka             Defect 87592: U-Verse pending SO due date not returned as recommended due date.
//# 03/13/2008   Rene Duka             LS 7: Log the response from LFACS.
//# 03/27/2008   Rene Duka             Defect 88721: Handle Dry Loop in the East region.
//# 03/27/2008   Rene Duka             Defect 88209: CKID in TN format followed by TID not to be used as TN/RTID.
//# 04/11/2008   Rene Duka             Defect 90831: Exception in analyzeLoop->checkCKIDs.
//# 05/14/2008   Rene Duka             PR 22077157: Resolve null pointer exception in printLoopsFromLFACS().
//# 05/28/2008   Jon Costa             DR96018: Corrected Null Pointer exception when aLoopSegments is null.
//# 06/13/2008   Lira Galsim           LS 8.
//# 06/17/2008   Jon Costa             CR 20871: Fix the Richardson conversion GPON RTID issue.
//# 06/19/2008   Jon Costa             DR 98799: Added check for subsequent FACSRC calls for CKID, TID, CAPR NOT null.
//# 07/02/2008   Jon Costa             DR99749: Time out exception(SystemFailure) from LFACS host not populated on response.
//# 08/08/2008   Shyamali Banerjee     PR 22487342: If fiber is not found, assign the loop to copper (FTTN).
//# 08/08/2008   Shyamali Banerjee     Defect 102194: When LS-conditioned loop is found, SC10-0000 should not be returned.
//# 08/14/2008   Shyamali Banerjee     Defect 102930: Call to INQOSP is not done due to the wrong increment in BBPLoops counter.
//# 08/29/2008	 Souvik Paul		   CR 20389: Check exception conflict & proceed indicator while building service items.
//# 09/02/2008   Vickie Ng			   LS 9.
//# 09/04/2008	 Lira Galsim		   Defect 104863: If RGPON is not in the NTI_TO_EXCLUDE_LIST, Recommended RTID should be returned.
//# 09/16/2008   Shyamali Banerjee     PR 22920967: Remove "null" from <commitStatus>null</commitStatus> and <broadbandPair>null</broadbandPair>.
//# 09/29/2008   Vickie Ng    		   LS 9: Utilized LS_COMMITTED_VALUES, LS_CONDITIONED_VALUES and LS_NON_COMMITTED_VAULES
//# 12/15/2008   Lira Galsim		   Defect 112756: For East region, for Service Codes - LX, LY, and UA 
//#									    - if service type is ADSL1, DSL Service is found. 
//#										- If service type is ADSL2, Dry Loop Service is found. 
//# 01/05/2009   Julius Sembrano       LS 10.
//# 02/10/2009	 Lira Galsim		   LS10: 
//#										- Determine the loop's RTID for Provisioning
//#										- Determine Services for Copper/Fiber/IPDSLAM
//# 02/24/2009   Lira Galsim           DR122497: When LFACS response has no COMM value, RM BIS should return a blank commitStatus (<commitStatus></commitStatus>), insteaad of "N" (<commitStatus>N</commitStatus>).
//# 03/02/2009   Lira Galsim           DR122907: proceedIndicator>false</proceedIndicator> should be returning True for HY-IP-DRYL service.
//# 03/04/2009   Lira Galsim           DR123137: Conflicts are being returned incorrectly.
//# 03/04/2009   Lira Galsim           DR123291: RM BIS is returning a service type of LS-IP-DSL and DSL when a SRVTYP of ADSL1 is returned from LFACS.
//# 03/05/2009   Julius Sembrano       DR123407: IPDSLAM NTI's making INQTERM call per BPR only FTTN should call INQTERM
//# 03/06/2009   Julius Sembrano       DR123137: Conflicts are being returned incorrectly.
//# 03/10/2009   Julius Sembrano       DR123647: RM BIS is returning incorrect PVFN3 response for FTTN-BP network type
//# 03/11/2009   Julius Sembrano       Modified logic for determining exhaust condition for IPDSLAM
//# 03/11/2009   Lira Galsim		   DR123877: Error accessing LFACS: Exception in analyzeLoop() Exception in buildServiceItems() null.
//# 03/12/2009   Julius Sembrano       DR123847: INQTERM call for the new VRAD functionality seems to not be working correctly.
//# 03/14/2009   Lira Galsim		   LS10: Modified buildServiceItems() for COPPER and IPDSLAM (as per the updated requirements - table update).
//# 03/18/2009   Julius Sembrano       DR124447: recommended due date is not being set to when there is an outward pending service order
//# 03/24/2009   Julius Sembrano       DR124695: serviceType is not being returned for DSL, multiple tags are missing.
//# 03/26/2009   Lira Galsim		   CR22919: If the pending service order has a due date of greater than 120 days it will be excluded from evaluation.
//# 03/26/2009   Lira Galsim		   DR125446: If the client doesn't send an Order Due Date, pending service orders with due date greater than 120 days are evaluated.
//# 04/02/2009   Lira Galsim		   DR125695: Error assigning a non-conflicting type of service of BPON to the loop when Network Type is FTTPIP.
//# 04/14/2009   Lira Galsim           PR24577856: WTN appended incorrectly 
//# 04/20/2009   Julius Sembrano       CR24678: RM BIS not to treat UNE-P/LWC as conflict
//# 04/30/2009   Lira Galsim           DR128327: Updated requirement in determining the loop's RTID for Provisioning
//# 05/29/2009   Lira Galsim           PR24867364: For scenario where client sends FTTN and IP-RT network types, the loop info is returned only under IP-RT, and missing under FTTN.
//# 06/13/2009   Lira Galsim           DR134059: Duplicate processing of WTN resulting to multiple DSL Info returned.
//# 06/15/2009 	 Sheilla Lirio		   Pending PR in lsprod: UNE-P should be returned instead of POTS.
//# 06/18/2009   Lira Galsim           PR24988507, PR25018934: Fixed the Null Pointer Exception in analyzeLoop().
//# 06/21/2009   Lira Galsim		   PR25016056: When client sends a combination of copper and ipdslam network types, the service items returned for both are whatever were set/retrieved last.
//# 06/23/2009   Lira Galsim           PR24988507: Additional fix to address the NULL Circuit ID issue in buildPendingServiceOrders().
//# 07/22/2009   Julius Sembrano       CR26940: ValidateFacilities clean up - RM will use the TEA from the Loop Level if the TEA from the Segment Level is not present
//# 07/30/2009   Lira Galsim           DR136681: Partial Fix: If TEA is found at the Loop Address Types level, RM BIS will call INQTERM and will identify Premise based VRAD.
//# 07/31/2009   Julius Sembrano       DR136681: Missed requirements in setting TEA from calling INQTERM.

package com.sbc.eia.bis.BusinessInterface.rm.LFACS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.StringTokenizer;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationRequestHelper;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationResponseDriver;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationResponseHelper;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.facsrc.FACSRCService;
import com.sbc.eia.bis.embus.service.facsrc.SendRequestToFACSRC;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.NINQImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqTermRequest.impl.INQTERMImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgRequest.impl.INQFASGImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgRequest.impl.INQFASGTypeImpl.REQTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ResponseMessageImpl;
import com.sbc.eia.bis.facades.rm.transactions.PublishValidateFacilityNotification.PublishValidateFacilityNotificationConstants;
import com.sbc.eia.bis.facades.rm.transactions.ValidateFacility.ValidateFacilityConstants;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.database.FiberFacilitiesRow;
import com.sbc.eia.bis.rm.database.FiberFacilitiesTable;
import com.sbc.eia.bis.rm.database.LfacsCopperServicesRow;
import com.sbc.eia.bis.rm.database.LfacsCopperServicesTable;
import com.sbc.eia.bis.rm.database.LfacsFiberServicesRow;
import com.sbc.eia.bis.rm.database.LfacsFiberServicesTable;
import com.sbc.eia.bis.rm.database.LfacsIpdslamServicesRow;
import com.sbc.eia.bis.rm.database.LfacsIpdslamServicesTable;
import com.sbc.eia.bis.rm.database.LfacsServicesRow;
import com.sbc.eia.bis.rm.database.LfacsServicesTable;
import com.sbc.eia.bis.rm.database.ServiceCategoryRow;
import com.sbc.eia.bis.rm.database.ServiceCategoryTable;
import com.sbc.eia.bis.rm.database.SoacWireCenterRow;
import com.sbc.eia.bis.rm.database.SoacWireCenterTable;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2;
import com.sbc.eia.idl.rm_ls_types.NetworkType2Values;
import com.sbc.eia.idl.rm_ls_types.NetworkType3Values;
import com.sbc.eia.idl.rm_ls_types.PendingServiceOrder;
import com.sbc.eia.idl.rm_ls_types.PendingServiceOrderSeqOpt;
import com.sbc.eia.idl.rm_ls_types.ServiceItem;
import com.sbc.eia.idl.rm_ls_types.ServiceItemSeqOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.EiaDateBisHelper;

/**
* Class      : LFACS
* Description: Business interface for LFACS interface.
*              - used by pVFN
*/
public class LFACS 
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;
    
    private String aRuleFile = null;    
    private String aOriginator = null;
    private String aApplication = null;
    
    private FACSRCService aFACSRCService = null;
    private PublishValidateFacilityNotificationRequestHelper aRequestHelper = null;
    
    /**
     * Constructor: LFACS
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Rene Duka
     */
    public LFACS(Utility utility, Hashtable properties) 
    {
        aProperties = properties;
        aUtility = utility;
        aRuleFile = (String) aProperties.get(SendRequestToFACSRC.FACSRC_EXCEPTION_RULE_FILE_TAG);
        aOriginator = (String) aProperties.get("BIS_NAME");
    }
    
    /**
     * Retrieves the loops from LFACS using the INQFASG contract.
     *
     * @param BisContext aContext
     * @param PublishValidateFacilityNotificationRequestHelper  aRequestHelper
     * @param PublishValidateFacilityNotificationResponseHelper aResponseHelper
     * @return FacilityLoop2[]
     * @exception Exception generic exception
     * 
     * @author Rene Duka
     */
    public FacilityLoop2[] publishValidateFacilityNotification(
        BisContext aContext,
        PublishValidateFacilityNotificationRequestHelper aRequestHelper,
        PublishValidateFacilityNotificationResponseHelper aResponseHelper)
        throws
            Exception
    {
        String myMethodName = "LFACS: publishValidateFacilityNotification()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
      
        this.aRequestHelper = aRequestHelper;
        FacilityLoop2[] aLoops = null;
        PublishValidateFacilityNotificationResponseDriver[] aResponseDrivers = null;
        aApplication = aRequestHelper.getClient();
        
        // build INITIAL request to LFACS
        // initial indicator set to 'true' means to call INQFASG by service address
        INQFASGImpl aINQFASGRequest = buildINQFASGRequest(aContext,
                                                          true,
                                                          aRequestHelper.getFacilityAddress(),
                                                          null);
    
        // process request
        if (aFACSRCService == null) 
        {
            try 
            {
                aFACSRCService = new FACSRCService(aProperties, aUtility);
            }
            catch (ServiceException e)  
            {
                ExceptionBuilder.parseException(aContext,
                                                aRuleFile,
                                                null,
                                                null,
                                                e.getMessage(),
                                                true,
                                                1,
                                                null,
                                                e,
                                                aUtility,            // utility
                                                null,               // origin use file
                                                null,               // severity use file
                                                null);
    
                // set Partial LFACS Facilities Indicator to true
                aResponseHelper.setPartialAnalysisIndicator(true);
            }
        }
    
        // retrieve loops from LFACS via INQFASG
        try 
        {
            // send request to FACSRCAccess
            ResponseMessageImpl aFACSRCResponse = sendINQFASGRequest(aContext,
                                                                     aRequestHelper.getNpaNxx(),
                                                                     aINQFASGRequest);
    
            // process response from FACSRCAccess
            if (aFACSRCResponse != null) 
            {
                // parse the response
                INQFASGResponseHelper aINQFASGResponseHelper = parseINQFASGResponse(aFACSRCResponse,
                                                                                    aResponseHelper);
                
                // print loops from LFACS 
                printLoopsFromLFACS(aINQFASGResponseHelper);
                   
                // determine number of loops to analyze
                int aLoopsToAnalyze = 0;
                if (aRequestHelper.getMaxPairsToAnalyze() > 0) 
                {
                    aLoopsToAnalyze = determineLoopsToAnalyze(aRequestHelper.getMaxPairsToAnalyze(),
                                                              aINQFASGResponseHelper.getNumberOfLoops(),
                                                              aResponseHelper);
                }
                else 
                {
                    aLoopsToAnalyze = aINQFASGResponseHelper.getNumberOfLoops();                
                }
    
                // initialize array list
                ArrayList aFacilityLoops = new ArrayList();
                ArrayList aResponseDriversList = new ArrayList();
                ArrayList aFinalResponseDriversList = new ArrayList();
                PublishValidateFacilityNotificationResponseDriver[] aResponseDriverArray = null;
                // analyze the loops
                int aLoopsBPP = 0;
                // R9
                int aLoopsLoadCoil = 0;
                
                for (int i=0; i < aLoopsToAnalyze; i++) 
                {
                    aUtility.log(LogEventId.INFO_LEVEL_1, "> Analyzing loop " + (i + 1) + " of " + aLoopsToAnalyze);

                    // initialize response driver array
                    aResponseDriversList = new ArrayList();
                    aResponseDriverArray = null;
                    
                    if (i <= 1) 
                    {                        
                        FacilityLoop2 aFacilityLoop = analyzeLoop(aRequestHelper.getTransactionType(),
                        										  aContext,
                        										  aINQFASGResponseHelper.aLoops[i],
                                                                  aRequestHelper.getRegion(),
                                                                  aRequestHelper.getNpaNxx(),
                                                                  aRequestHelper.getSOACServiceOrderNumber(),
                                                                  aRequestHelper.getUverseOrderDueDate(),
                                                                  aRequestHelper.getLfacsNtiList(),
                                                                  aResponseHelper,
                                                                  aResponseDriversList);
                        
                        if (aResponseDriversList.size() > 0)
                        {
                        	aResponseDriverArray = (PublishValidateFacilityNotificationResponseDriver[])aResponseDriversList.toArray(new PublishValidateFacilityNotificationResponseDriver[aResponseDriversList.size()]);
                            aFinalResponseDriversList.addAll(aResponseDriversList);
                        }
                        
                        try
                        {
                        	for (int ii=0; aResponseDriverArray != null && ii < aResponseDriverArray.length; ii++)
                            {
                        		if (aResponseDriverArray[ii].getFacilityLoop() != null)
                                {
                                    aFacilityLoops.add(aResponseDriverArray[ii].getFacilityLoop());
                                    // if network type is FTTN, 
                                    //     - loop is also an FTTN-BP loop
                                    //     - increment counter for number of BPP loop
                                    if (aResponseDriverArray[ii].getNT().equalsIgnoreCase(NetworkType2Values.FTTN)
                                    		|| aResponseDriverArray[ii].getNT().equalsIgnoreCase(NetworkType2Values.FTTNBP))
                                    {                                
                                        // R9 Code
                                        if (aINQFASGResponseHelper.aLoops[i].getIsLoadCoilExhausted())
                                        {
                                        	aLoopsLoadCoil++;
                                        	if ( ! aResponseHelper.getLoadCoilExhaustedIndicator() ) 
                                				aResponseHelper.setLoadCoilExhaustedIndicator(aINQFASGResponseHelper.aLoops[i].getIsLoadCoilExhausted());	
                                        }
                                		// set BBPMissing indicator
                                		if ( ! aResponseHelper.getBBPMissingIndicator() &&
                                    			aINQFASGResponseHelper.aLoops[i].getIsBBPMissing())
                                    			aResponseHelper.setBBPMissingIndicator(aINQFASGResponseHelper.aLoops[i].getIsBBPMissing());
                                    }
                                    // if network type is GPON, 
                                    //     - loop is also an RGPON loop
                                    if (aResponseDriverArray[ii].getNT().equalsIgnoreCase(NetworkType2Values.GPON))
                                    {
                                        // initialize response driver object for RGPON
                                        PublishValidateFacilityNotificationResponseDriver aResponseDriver_RGPON = new PublishValidateFacilityNotificationResponseDriver(); 
                                        aResponseDriver_RGPON.setNT(NetworkType2Values.RGPON);
                                        aResponseDriver_RGPON.setWTN(aResponseDriverArray[ii].getWTN());
                                        aResponseDriversList.add(aResponseDriver_RGPON);
                                    }
                                    // handle loop with no BTN/WTN
                                    if (OptHelper.isStringOptEmpty(aFacilityLoop.aWorkingTelephoneNumber))
                                    {
                                        aResponseHelper.handleLoopWithNoBTN(aResponseDriverArray[ii].getNT(),
                                        									aResponseDriverArray[ii].getFacilityLoop());
                                    }
                                    // set the LS conditioned loop indicator in the response helper
                                	if (aINQFASGResponseHelper.aLoops[i].getIsLoopConditionedForLS())
                                    {
                                    	aLoopsBPP++;
                                    	if (!aResponseHelper.getLSConditionedLoopFound())
                                    	{
                                    		aResponseHelper.setLSConditionedLoopFound(aINQFASGResponseHelper.aLoops[i].getIsLoopConditionedForLS());
                                    	}
                                    }
                                }
                            }
                        }
                        catch(Exception e) 
                        { 
                        	aUtility.log(LogEventId.INFO_LEVEL_1, "Response Driver is null"); 
                        } 
                    }
                    else 
                    {
                        // make subsequent calls to LFACS for loops 3 to aLoopsToAnalyze
                        // initial indicator set to 'false' means to call INQFASG by either circuit ID, terminal ID or cable pair
                        INQFASGImpl aINQFASGRequest_1 = buildINQFASGRequest(aContext,
                                                                            false,
                                                                            null,
                                                                            aINQFASGResponseHelper.aLoops[i]);
                        ResponseMessageImpl aFACSRCResponse_1 = null;
                        
                        if (aINQFASGRequest_1 != null)
                        {
                            aFACSRCResponse_1 = sendINQFASGRequest(aContext,
                                                                   aRequestHelper.getNpaNxx(),
                                                                   aINQFASGRequest_1);
                        }
                        if (aFACSRCResponse_1 != null) 
                        {
                            // parse the response
                            INQFASGResponseHelper aINQFASGResponseHelper_1 = parseINQFASGResponse(aFACSRCResponse_1,
                                                                                                  aResponseHelper);
                            printLoopsFromLFACS(aINQFASGResponseHelper_1);
                            int aSubLoopsToAnalyze = aINQFASGResponseHelper_1.getNumberOfLoops();
                            
                            // analyze the response
                            for (int j=0; j < aSubLoopsToAnalyze; j++) 
                            {
                            	aResponseDriversList = new ArrayList();
                            	aResponseDriverArray = null;
                            	
                                aUtility.log(LogEventId.INFO_LEVEL_1, "> Analyzing SubLoop " + (j + 1) + " of " + aSubLoopsToAnalyze);                                
                                FacilityLoop2 aFacilityLoop = analyzeLoop(aRequestHelper.getTransactionType(),
                                										  aContext,
                                										  aINQFASGResponseHelper_1.aLoops[j],
                                                                          aRequestHelper.getRegion(),
                                                                          aRequestHelper.getNpaNxx(),
                                                                          aRequestHelper.getSOACServiceOrderNumber(),
                                                                          aRequestHelper.getUverseOrderDueDate(),
                                                                          aRequestHelper.getLfacsNtiList(),
                                                                          aResponseHelper,
                                                                          aResponseDriversList);
                                
                                if (aResponseDriversList.size() > 0)
                                {
                                	aResponseDriverArray = (PublishValidateFacilityNotificationResponseDriver[])aResponseDriversList.toArray(new PublishValidateFacilityNotificationResponseDriver[aResponseDriversList.size()]);
                                	aFinalResponseDriversList.addAll(aResponseDriversList);
                                }
                                
                                try
                                {
                                	for (int jj=0; aResponseDriverArray != null && jj < aResponseDriverArray.length; jj++)
                                    {
                                    	if (aFacilityLoop != null)
                                        {
                                            aFacilityLoops.add(aFacilityLoop);
                                            // if network type is FTTN, 
                                            //     - loop is also an FTTN-BP loop
                                            //     - increment counter for number of BPP loop
                                            if (aResponseDriverArray[jj].getNT().equalsIgnoreCase(NetworkType2Values.FTTN)
                                            	|| aResponseDriverArray[jj].getNT().equalsIgnoreCase(NetworkType2Values.FTTNBP))
                                            {                                        
                                                // R9 Code
                                                // set Load Coil exhausted indicator	
                                        		if ( aINQFASGResponseHelper_1.aLoops[j].getIsLoadCoilExhausted())
                                        		{
                                        			aLoopsLoadCoil++;
                                        			if (! aResponseHelper.getLoadCoilExhaustedIndicator())
                                        					aResponseHelper.setLoadCoilExhaustedIndicator(aINQFASGResponseHelper_1.aLoops[j].getIsLoadCoilExhausted());	
                                        		}
                                        		// set BBPMissing indicator
                                        		if ( ! aResponseHelper.getBBPMissingIndicator() &&
                                            			aINQFASGResponseHelper_1.aLoops[j].getIsBBPMissing())
                                            			aResponseHelper.setBBPMissingIndicator(aINQFASGResponseHelper_1.aLoops[j].getIsBBPMissing());
                                            }
                                            // if network type is GPON, 
                                            //     - loop is also an RGPON loop
                                            if (aResponseDriverArray[jj].getNT().equalsIgnoreCase(NetworkType2Values.GPON))
                                            {
                                                // initialize response driver object for RGPON
                                                PublishValidateFacilityNotificationResponseDriver aResponseDriver_RGPON = new PublishValidateFacilityNotificationResponseDriver(); 
                                                aResponseDriver_RGPON.setNT(NetworkType2Values.RGPON);
                                                aResponseDriver_RGPON.setWTN(aResponseDriverArray[jj].getWTN());
                                                aResponseDriversList.add(aResponseDriver_RGPON);
                                            }
                                            // handle loop with no BTN/WTN
                                            if (OptHelper.isStringOptEmpty(aFacilityLoop.aWorkingTelephoneNumber))
                                            {
                                                aResponseHelper.handleLoopWithNoBTN(aResponseDriverArray[jj].getNT(),
                                                                                    aFacilityLoop);
                                            }
                                            // set the LS conditioned loop indicator in the response helper
                                            if (aINQFASGResponseHelper_1.aLoops[j].getIsLoopConditionedForLS())
                                            {
                                            	if (!aResponseHelper.getLSConditionedLoopFound())
                                            	{
                                            		aResponseHelper.setLSConditionedLoopFound(aINQFASGResponseHelper_1.aLoops[j].getIsLoopConditionedForLS());
                                            	}
                                            	
                                            	// R9 Code
                                            	if ( aINQFASGResponseHelper_1.aLoops[j].getIsLoopConditionedForLS())
                                            	{  
                                                    aLoopsBPP++;
                                                    if (!aResponseHelper.getLSConditionedLoopFound())
                                                    {
                                                    	aResponseHelper.setLSConditionedLoopFound(aINQFASGResponseHelper.aLoops[i].getIsLoopConditionedForLS());
                                                    }
                                            	}
                                            }
                                        }
                                    }
                                }
                                catch(Exception e) 
                                { 
                                	aUtility.log(LogEventId.INFO_LEVEL_1, "Response Driver is null"); 
                                } 
                            }
                        }
                    }
                }

                // format facility loops
                if (aFacilityLoops.size() > 0) 
                {
                    aLoops = (FacilityLoop2[]) aFacilityLoops.toArray(new FacilityLoop2[aFacilityLoops.size()]);                
                }
                
                // format response drivers
                if (aFinalResponseDriversList.size() > 0) 
                {
                    aResponseDrivers = (PublishValidateFacilityNotificationResponseDriver[]) aFinalResponseDriversList.toArray(new PublishValidateFacilityNotificationResponseDriver[aFinalResponseDriversList.size()]);
                    aResponseHelper.setResponseDrivers(aResponseDrivers);
                }
                
                // format number of BBP loops
                aResponseHelper.setNumberOfBBPLoops(aLoopsBPP);
                aUtility.log(LogEventId.INFO_LEVEL_1, "NumberOfBBPLoops <" + aResponseHelper.getNumberOfBBPLoops() + ">" );
                
                // format number of Load Coil loops
                aResponseHelper.setNumberOfLoadCoilLoops(aLoopsLoadCoil);
                aUtility.log(LogEventId.INFO_LEVEL_1, "NumberOfLoadCoilLoops <" + aResponseHelper.getNumberOfLoadCoilLoops() + ">" );                

            }
            else {
                // set Partial LFACS Facilities Indicator to true
                aResponseHelper.setPartialAnalysisIndicator(true);
            }
        }
        catch (SystemFailure sf)
        {
            // log: exception message
            String errText = "Error accessing LFACS";
            String ErrCode = ExceptionCode.ERR_RM_SYSTEM_FAILURE;
            try
            {
                errText = errText
                          + (sf.aExceptionData.aDescription != null ? (": " + sf.aExceptionData.aDescription) : ".");
            }
            catch (Exception ee)
            { /*Ignore, the default value will be used*/ }
            try
            {
                ErrCode = sf.aExceptionData.aCode;
            }
            catch (Exception ee)
            { /*Ignore, the default value will be used*/ }

            aUtility.log(LogEventId.INFO_LEVEL_1, "SystemFailure in LFACS: publishValidateFacilityNotification()");
            
            // set: exception code / message
            
            aResponseHelper.handleException(ErrCode,
            		errText,
                   (String) aProperties.get("BIS_NAME").toString(),
                   aRequestHelper);
            // throw: System Failure Exception
            aUtility.throwException(ErrCode,
                                    errText,
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        catch (Exception e) 
        {
        	// log: message            
            aUtility.log(LogEventId.INFO_LEVEL_1, "Exception in LFACS: publishValidateFacilityNotification()");
            
            String errText = "Error accessing LFACS" + (e.getMessage() != null ? (": " + e.getMessage()) : ".");
           
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		errText,
                   (String) aProperties.get("BIS_NAME").toString(),
                   aRequestHelper);
            
            // throw: System Failure Exception
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                    errText,
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);           
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);            
        }
        return aLoops; 
    }   
    
    /**
     * Builds the INQOSP request.
     * 
     * @param BisContext aContext
     * @param boolean    aInitialCallIndicator
     * @param Address    aFacilityAddress
     * @return NINQImpl
     * 
     * @author Shyamali Banerjee
     */
    public NINQImpl buildINQOSPRequest(
            BisContext aContext,
            Address aFacilityAddress)
        {
    	String aMethodName = "LFACS: buildINQOSPRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        NINQImpl aNINQRequest = null;
        
    	try 
        {
            
            // build request using Facility Address
    	    aNINQRequest = new INQOSPRequestHelper().INQOSPRequestBuilder(aUtility, 
                                                     aProperties,
                                                     aFacilityAddress);
        }
        catch (Exception e) 
        {
            // log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
        }
        finally {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
    	return aNINQRequest;
        }
    /**
     * Sends the INQOSP request to LFACS via FACSRCAccess.
     * 
     * @param BisContext  aContext
     * @param String      aPrimaryNpaNxx
     * @param NINQImpl aNINQRequest
     * @return ResponseMessageImpl
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     * 
     * @author Shyamali Banerjee
     */
    public com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.ResponseMessageImpl sendINQOSPRequest(
        BisContext aContext,
        String aPrimaryNpaNxx,
        NINQImpl aNINQRequest)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound 
    {
        String aMethodName = "LFACS: sendINQOSPRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    
        String aDestination = "";
        
        // process request
        if (aFACSRCService == null) 
        {
            try 
            {
                aFACSRCService = new FACSRCService(aProperties, aUtility);
            }
            catch (ServiceException e)  
            {
                ExceptionBuilder.parseException(aContext,
                                                aRuleFile,
                                                null,
                                                null,
                                                e.getMessage(),
                                                true,
                                                1,
                                                null,
                                                e,
                                                aUtility,            // utility
                                                null,               // origin use file
                                                null,               // severity use file
                                                null);
            }
        }   
        com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.ResponseMessageImpl aFACSRCResponse =         
            			  SendRequestToFACSRC.sendOSPRequest(aNINQRequest, 
                                                             aUtility, 
                                                             aProperties, 
                                                             aContext, 
                                                             aFACSRCService, 
                                                             aDestination, 
                                                             aPrimaryNpaNxx);            
    
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        return aFACSRCResponse;
    }
    
    /**
     * Parses the INQOSP response.
     * 
     * @param ResponseMessageImpl aFACSRCResponse
     * @return INQOSPResponseHelper
     * 
     * @author Shyamali Banerjee
     */
    public INQOSPResponseHelper parseINQOSPGResponse(
    	com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.ResponseMessageImpl aFACSRCResponse,
        PublishValidateFacilityNotificationResponseHelper aResponseHelper)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound  
    {
        String aMethodName = "LFACS: parseINQOSPResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        INQOSPResponseHelper aINQOSPResponseHelper = null;
        try
        {
        	com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.NINQTypeImpl aNINQResponse = new com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.NINQTypeImpl();            
            aNINQResponse = (com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.NINQTypeImpl) aFACSRCResponse.getResults().getNINQ();
            com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.RECTypeImpl aRECResponse = new com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.RECTypeImpl();
            aRECResponse = (com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.RECTypeImpl) aFACSRCResponse.getResults().getFACSADAPTERERROR();
            
            aINQOSPResponseHelper = new INQOSPResponseHelper(aUtility, aProperties);
            aINQOSPResponseHelper.parseResponse(aNINQResponse,aRECResponse);
        }
        catch (Exception e) 
        {
        	// set: exception code / message
            
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		e.getMessage(),
                  (String) aProperties.get("BIS_NAME").toString(),
                  aRequestHelper);
            // throw: System Failure Exception
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		                e.getMessage(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
        return aINQOSPResponseHelper;
    }
    
    /**
     * Builds the INQFASG request.
     * 
     * @param BisContext aContext
     * @param boolean    aInitialCallIndicator
     * @param Address    aFacilityAddress
     * @param StringOpt  aCircuitID
     * @param StringOpt  aTerminalID
     * @param StringOpt  aCablePair
     * @return INQFASGImpl
     * 
     * @author Rene Duka
     */
    private INQFASGImpl buildINQFASGRequest(
        BisContext aContext,
        boolean aInitialCallIndicator,
        Address aFacilityAddress,
        Loop aLoop) 
    {
        String aMethodName = "LFACS: buildINQFASGRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    
        INQFASGImpl aINQFASGRequest = new INQFASGImpl();
        String aCircuitID   = aLoop != null ? aLoop.getCKID()   : null;
        String aTerminalID  = aLoop != null ? aLoop.getTID()    : null;
        String aCablePair   = aLoop != null ? aLoop.getDCAPR()  : null;
        
        try 
        {
            INQFASGRequestHelper aRequestHelper = null;
            if (aInitialCallIndicator) 
            {
                // build request using Facility Address
                aRequestHelper = new INQFASGRequestHelper(aUtility, 
                                                          aProperties,
                                                          aFacilityAddress);
    
            }
            else 
            {        
                // build request using Circuit ID in the pending SO if
                //     - Terminal ID, Cable and Pair in the loop are null
                //     - Circuit ID in the pending SO is in TN format
                boolean performINQFASG = false;
                
                if (aCircuitID == null && aTerminalID == null && aCablePair == null)
                {
                    // Check for service order values
                    ServiceOrder[] aServiceOrders = aLoop != null ? aLoop.getServiceOrders() : null;
                    if (aServiceOrders != null)
                    {
                        for (int i=0; i < aServiceOrders.length; i++) 
                        {
                            if (aServiceOrders[i].getCKID() != null
                                && isNumeric(new StringBuffer(aServiceOrders[i].getCKID())))
                            {
                                aCircuitID = aServiceOrders[i].getCKID();
                                performINQFASG = true;
                                break;
                            }
                        }
                    }
                }
                else
                {
                    // Check to see if any one has a value we can use.
                    if (aCircuitID != null || aTerminalID != null || aCablePair != null)
                        performINQFASG = true; 
                }
                
                if (!performINQFASG)
                {
                    aUtility.log(LogEventId.DEBUG_LEVEL_1, "No valid values for subsequent INQFASG call, skipping");
                    return (INQFASGImpl)null;
                }
                    
                // build request using Circuit ID, Terminal ID, Cable and Pair
                aRequestHelper = new INQFASGRequestHelper(aUtility, 
                                                          aProperties,
                                                          aCircuitID,
                                                          aTerminalID,
                                                          aCablePair);

                
            }
            REQTypeImpl aRequest = aRequestHelper.getRequest();                                                                     
            aINQFASGRequest.setREQ(aRequest);
        }
        catch (Exception e) 
        {
            // log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
        }
        finally {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
        return aINQFASGRequest;
    }
    
    /**
     * Sends the INQFASG request to LFACS via FACSRCAccess.
     * 
     * @param BisContext  aContext
     * @param String      aPrimaryNpaNxx
     * @param INQFASGImpl aINQFASGRequest
     * @return ResponseMessageImpl
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     * 
     * @author Rene Duka
     */
    private ResponseMessageImpl sendINQFASGRequest(
        BisContext aContext,
        String aPrimaryNpaNxx,
        INQFASGImpl aINQFASGRequest)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound 
    {
        String aMethodName = "LFACS: sendINQFASGRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    
        String aDestination = "";                
    
        ResponseMessageImpl aFACSRCResponse = null;        
        aFACSRCResponse = SendRequestToFACSRC.sendRequest(aINQFASGRequest, 
                                                          aUtility, 
                                                          aProperties, 
                                                          aContext, 
                                                          aFACSRCService, 
                                                          aDestination, 
                                                          aPrimaryNpaNxx);            
    
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        return aFACSRCResponse;
    }
    
    /**
     * Parses the INQFASG response.
     * 
     * @param ResponseMessageImpl aFACSRCResponse
     * @return INQFASGResponseHelper
     * 
     * @author Rene Duka
     */
    private INQFASGResponseHelper parseINQFASGResponse(
        ResponseMessageImpl aFACSRCResponse,
        PublishValidateFacilityNotificationResponseHelper aResponseHelper)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound  
    {
        String aMethodName = "LFACS: parseINQFASGResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        INQFASGResponseHelper aINQFASGResponseHelper = null;
        try
        {
            INQFASGTypeImpl aINQFASGResponse = new INQFASGTypeImpl();            
            aINQFASGResponse = (INQFASGTypeImpl) aFACSRCResponse.getResults().getINQFASG();
            
            aINQFASGResponseHelper = new INQFASGResponseHelper(aUtility, aProperties);
            aINQFASGResponseHelper.parseResponse(aINQFASGResponse);
        }
        catch (Exception e) 
        {

        	// set: exception code / message
            
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		e.getMessage(),
                   (String) aProperties.get("BIS_NAME").toString(),
                   aRequestHelper);
            // throw: System Failure Exception
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		                e.getMessage(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
        return aINQFASGResponseHelper;
    }
    
    /**
     * Analyzes the loop.
     * 
     * @param Loop   aLoop
     * @param String aRegion
     * @param String aPrimaryNpaNxx
     * @param String aServiceOrderNumber
     * @param EiaDate aUverseOrderDueDate
     * @param String[] aNetworkTypeList
     * @param PublishValidateFacilityNotificationResponseHelper aResponseHelper
     * @param PublishValidateFacilityNotificationResponseDriver aResponseDriver
     * @return FacilityLoop2
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     * 
     * @author Rene Duka
     */
    private FacilityLoop2 analyzeLoop(
    	String aTransactionType,
    	BisContext aContext,
        Loop aLoop,
        String aRegion,
        String aPrimaryNpaNxx,
        String aServiceOrderNumber,
        EiaDate aUverseOrderDueDate,
        String[] aNetworkTypeList,
        PublishValidateFacilityNotificationResponseHelper aResponseHelper,
        ArrayList aResponseDriversList)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound  
    {
        String aMethodName = "LFACS: analyzeLoop()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    
        // initialize loop to be returned
        FacilityLoop2 aLoopAnalyzed = null;

        // initilizes loop information to be returned
        ArrayList aLoopNetworkTypeList = new ArrayList();
        String[] aLoopNetworkTypes = null;
        StringBuffer aCommitStatus = new StringBuffer();
        StringBuffer aBroadbandPair = new StringBuffer();
        StringBuffer aWorkingTelephoneNumber = new StringBuffer();
        StringBuffer aCircuitID = new StringBuffer();
        StringBuffer aRelatedCircuitID = new StringBuffer();
        ServiceItem[] aServiceItems = null;
        PendingServiceOrder[] aPendingServiceOrders = null;
        
        StringBuffer aServiceCode = new StringBuffer();
        StringBuffer aModifier = new StringBuffer();
        boolean aPremiseBasedVRADFound = false;

        // retrieve loop information from LFACS
        String aLoopCKID = aLoop.getCKID();
        String aLoopCKID2 = aLoop.getCKID2();
        String aLoopCKID3 = aLoop.getCKID3();
        String aLoopTID = aLoop.getTID();
        String aLoopServiceType = aLoop.getSRVTYP();
        String aLoopUSOC = aLoop.getUSOC();
        String aLoopNIF = aLoop.getNIF();
        Segment[] aLoopSegments = aLoop.getSegments();
        ServiceOrder[] aLoopServiceOrders = aLoop.getServiceOrders();
        AddressType[] aLoopAddressTypes = aLoop.getAddressTypes();
        
        PublishValidateFacilityNotificationResponseDriver aResponseDriver = new PublishValidateFacilityNotificationResponseDriver();
        
        try 
        {
            // Locate the LOOP.ADDR.BSTE (Broadband Serving Terminal) field.
            //     - The presence of this field will determine if there is fiber in 
            //       the premise or not.
            boolean bIsFiberInPremise = checkFiber(aLoopAddressTypes, 
                                                   aLoopServiceOrders);

            // Format the following:
            //      - network type
            //      - commit status
            //      - broadband pair
            // If fiber is present, loop could be one of the following:
            //      - FTTP-IP
            //      - GPON / RGPON
            //      - commit status is set to null
            //      - broadband pair is set to null
            // If fiber is not present, loop could be one of the following:
            //       - FTTN
            //       - FTTN-BP
            //       - commit status is set to the value of LOOP.SEG[last].COMM
            //       - broadband pair is set to the value of LOOP.SEG[last].COND 
            //
            // If the value of LOOP.SEG[last].COND is equal to “BPP” and the
            //     value of LOOP.SEG[last].COMM is equal to “P”, the loop is Lightspeed 
            //     conditioned. 
            // If the value of LOOP.SEG[last].COND is equal to “BPP” and the
            //     value of LOOP.SEG[last].COMM is equal to “S”, the loop is Lightspeed 
            //     conditioned for bonded pair. 
            // Otherwise, the loop is not conditioned for LightSpeed service 
            boolean bIsLoopConditionedForLS = false;
            if (bIsFiberInPremise) 
            {
            	aUtility.log(LogEventId.INFO_LEVEL_1, "Processing loop : Fiber - BSTE Found.");
                bIsLoopConditionedForLS = true;
                aLoopNetworkTypeList.add(buildNetworkTypeFromFiber(aLoopSegments, 
                												   aLoopServiceOrders)); 
            }
            else
            {
            	aUtility.log(LogEventId.INFO_LEVEL_1, "Processing loop : Copper - BSTE Not Found.");
            	/* R10 Code */
            	aLoopNetworkTypeList.add(NetworkType2Values.FTTN);
            	aLoopNetworkTypeList.add(NetworkType2Values.FTTNBP);
            	aLoopNetworkTypeList.add(NetworkType3Values.IPCO);
            	aLoopNetworkTypeList.add(NetworkType3Values.IPRT);
            }
            
            // Determine if network type was sent by the client
            // If loop belongs to one the network types sent by the client:
            //     - format the Facility Loop
            // If loop does not belong to any of the network types sent by the client:
            //     - skip
            if (aLoopNetworkTypeList.size() > 0)
            {
            	aLoopNetworkTypes = (String[])aLoopNetworkTypeList.toArray(new String[aLoopNetworkTypeList.size()]);
            }
            
            for (int i = 0; i < aLoopNetworkTypes.length; i++)
            {
            	if(!checkNetworkType(aNetworkTypeList, aLoopNetworkTypes[i]))
            	{
            		continue;
            	}
            	
            	aCommitStatus = new StringBuffer();
            	aBroadbandPair = new StringBuffer();
		        if (!bIsFiberInPremise)
		        {
	                if (aLoopSegments != null)
	                {
	                	aCommitStatus.append(aLoopSegments[aLoopSegments.length -1].getCOMM() == null ? 
	                			"" 	: aLoopSegments[aLoopSegments.length -1].getCOMM());
	                	aBroadbandPair.append(aLoopSegments[aLoopSegments.length -1].getCOND() == null ? 
	                			"" : aLoopSegments[aLoopSegments.length -1].getCOND());
	                	
	                    if ( aCommitStatus.length() > 0 && (!aLoopNetworkTypes[i].equalsIgnoreCase(NetworkType3Values.IPCO) || !aLoopNetworkTypes[i].equalsIgnoreCase(NetworkType3Values.IPRT))) 
	                    {
	                        // check if the loop is an LS conditioned loop
	                    	if ( aProperties.get("LS_COMMITTED_VALUES") != null &&
	                    		((String) aProperties.get("LS_COMMITTED_VALUES")).indexOf(":" + aCommitStatus.toString() + ":") >= 0)
	                    	{
	                    		if (( aProperties.get("LS_CONDITIONED_VALUES") != null &&
	                    			((String) aProperties.get("LS_CONDITIONED_VALUES")).indexOf(":" + aBroadbandPair.toString() + ":") >= 0)&& 
	                    			(aLoopNetworkTypes[i] != null ))
	                    		{
	                    			aUtility.log(LogEventId.INFO_LEVEL_1, "Processing loop : Copper - LS Conditioned.");
	                    			bIsLoopConditionedForLS = true;
	                    		}
	                    		else
	                    		{  /* R9 CODE */
	                    			aUtility.log(LogEventId.INFO_LEVEL_1, "Processing loop : Copper - LS Condition Missing");
	                    			aLoop.setIsBBPMissing(true);
	                    	    } 	
	                    	}
	                    }                	
	                	
	                    /* R9 Code - search for Load Coil in an LS conditioned loop segment, break out of loop when one is found. */
	                    if ( bIsLoopConditionedForLS && 
	                    		(!aLoopNetworkTypes[i].equalsIgnoreCase(NetworkType3Values.IPCO) || !aLoopNetworkTypes[i].equalsIgnoreCase(NetworkType3Values.IPRT)))
	                    {
	                    	for ( int j = 0 ; j < aLoopSegments.length; j ++ )
	                    	{
	                    		if ( aLoopSegments[j].getCOND() != null &&
	                    				( aProperties.get("LS_CONDITIONED_VALUES") != null &&
	                    				((String) aProperties.get("LS_CONDITIONED_VALUES")).indexOf(":" + aLoopSegments[j].getCOND() + ":") >= 0 ) &&
	                    				aLoopSegments[j].getLT() != null &&
	                    				aLoopSegments[j].getLT().length() > 0 )
	                    		{
	                    			aUtility.log(LogEventId.INFO_LEVEL_1, "Processing loop : Copper - Load Coil found");
	                    			aLoop.setIsLoadCoilExhausted(true);
	                    			break;
	                    		}
	                    	}
	    	            }
	                }
	                
	                if(aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING) && 
                			aLoopNetworkTypes[i].equalsIgnoreCase(NetworkType2Values.FTTN) &&
                			(!bIsLoopConditionedForLS || aLoop.getIsBBPMissing()))
                	{
                		aPremiseBasedVRADFound = checkVRAD(aContext, aLoop, aPrimaryNpaNxx, aResponseHelper);
                		
                		if(aPremiseBasedVRADFound)
                		{
                			aUtility.log(LogEventId.INFO_LEVEL_1, "VRAD found in premise");
                			aCommitStatus = new StringBuffer("P");
                			bIsLoopConditionedForLS = true;
                		}
                		else
                		{
                			aUtility.log(LogEventId.INFO_LEVEL_1, "VRAD not found in premise");
                		}
                	}
		        }
		        
	            // Set the LS conditioned loop indicator
	            aLoop.setIsLoopConditionedForLS(bIsLoopConditionedForLS);
            	
            	boolean bIsNetworkTypeValid = true;
		        bIsNetworkTypeValid = checkNetworkType(aNetworkTypeList,
		        										aLoopNetworkTypes[i]);
		        
		        // If network type is not valid using FTTN, check using FTTN-BP
		        if (aLoopNetworkTypes[i] != null
		        	&& aLoopNetworkTypes[i].equalsIgnoreCase(NetworkType2Values.FTTN)
		            && !bIsNetworkTypeValid)
		        {
		            bIsNetworkTypeValid = checkNetworkType(aNetworkTypeList,
		                                                   NetworkType2Values.FTTNBP);
		        }
		
		        // If network type is not valid using GPON, check using RGPON
		        if (aLoopNetworkTypes[i] != null
			        && aLoopNetworkTypes[i].equalsIgnoreCase(NetworkType2Values.GPON)
		            && !bIsNetworkTypeValid)
		        {
		            bIsNetworkTypeValid = checkNetworkType(aNetworkTypeList,
		                                                   NetworkType2Values.RGPON);
		        }
		        
		        // If network type is not valid using IP-CO, check using IP-RT
		        if (aLoopNetworkTypes[i] != null
			        && aLoopNetworkTypes[i].equalsIgnoreCase(NetworkType3Values.IPCO)
		            && !bIsNetworkTypeValid)
		        {
		            bIsNetworkTypeValid = checkNetworkType(aNetworkTypeList,
		                                                   NetworkType3Values.IPRT);
		        }
		        
		        if (bIsNetworkTypeValid)
		        {
		            // log network type
		            aUtility.log(LogEventId.INFO_LEVEL_1, "Processing loop : Network Type is " + aLoopNetworkTypes[i]);
		            // Determine the final state of the loop by examining the pending service orders
		            if (aLoopServiceOrders != null) 
		            {
		                ServiceOrder aServiceOrder = determineLatestPendingServiceOrder(aLoopServiceOrders,
		                                                                                aUverseOrderDueDate);
		
		                // Replace with loop information from pending service order
		                if (aServiceOrder != null)
		                {
		                    aLoopCKID = aServiceOrder.getCKID();
		                    aLoopCKID2 = aServiceOrder.getCKID2();
		                    aLoopCKID3 = aServiceOrder.getCKID3();
		                    aLoopTID = aServiceOrder.getTID();
		                    aLoopServiceType = aServiceOrder.getSRVTYP();
		                    aLoopNIF = aServiceOrder.getNIF();
		                    aLoopUSOC = aServiceOrder.getUSOC();
		                    aLoopSegments = aServiceOrder.getSegments();
		                }
		            }
		            
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            // format working telephone number and circuit ID
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            //     - The value of the CKID field in the TN format (npanxxlinenumber) will be the 
		            //       working telephone number
		            //     - The value of the CKID field not in the TN format (npanxxlinenumber) will be the 
		            //       circuit ID
		            //
		            // derive service code and modifier from circuit ID
		            //     - service code and modifier can only be extracted if one of the Circuit ID fields 
		            //       is a CLS-formatted Circuit ID, otherwise set the value of these fields to Null. 
		            //     - a CLS-formatted Circuit ID is xx.XXXX.xxxxxx.xxx.xxx where:
		            //       For example: A1.MCXX.000001..SW
		            //           A1=1st Field(2AN) 
		            //           MCXX=2nd Field(4AN) 
		            //           000001=3rd Field(6AN) 
		            //           blank=4th Field(0-3AN)
		            //           SW=5th Field(1-3AN)
		            //     - value of service code is the first 2 characters of the 2nd field
		            //     - value of modifier is the last 2 characters of the 2nd field
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            aWorkingTelephoneNumber = new StringBuffer();
		            aCircuitID = new StringBuffer();
		            aServiceCode = new StringBuffer();
		            aModifier = new StringBuffer();
		            
		            checkCKIDs(aLoopCKID,
		                       aLoopCKID2,
		                       aLoopCKID3,
		                       aPrimaryNpaNxx,
		                       aWorkingTelephoneNumber,     // return value
		                       aCircuitID,                  // return value
		                       aServiceCode,                // return value
		                       aModifier);                  // return value
		    
		            aUtility.log(LogEventId.INFO_LEVEL_2, "WTN          : " + aWorkingTelephoneNumber.toString());
		            aUtility.log(LogEventId.INFO_LEVEL_2, "TID          : " + aLoopTID);
		            aUtility.log(LogEventId.INFO_LEVEL_2, "Circuit ID   : " + aCircuitID.toString());
		            aUtility.log(LogEventId.INFO_LEVEL_2, "Service Code : " + aServiceCode.toString());
		            aUtility.log(LogEventId.INFO_LEVEL_2, "Modifier     : " + aModifier.toString());
		
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            // if CKID in TN format followed by TID, TN will not be used as TN and RTID 
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            if (aWorkingTelephoneNumber.length() > 0
		                && aLoopTID != null)
		            {
		                aWorkingTelephoneNumber = new StringBuffer();
		            }
		            
	                ///////////////////////////////////////////////////////////////////////////////////////////////////
	                // format RTID
	                ///////////////////////////////////////////////////////////////////////////////////////////////////
	                // If fiber is present, only RGPON and GPON will have a RTID 
	                //     - The indication on whether the fiber facilities in the premise is either RGPON or GPON 
	                //       will be defined in the FIBER_FACILITY table. 
	                // If fiber is not present, RTID will only be extracted if the loop is conditioned for LS service
		            // OR network type is IP-CO or IP-RT
	                //     - The value of the Circuit ID field in the TN format (npanxxlinenumber) will be the 
	                //       RTID
	                ///////////////////////////////////////////////////////////////////////////////////////////////////
		            if (bIsFiberInPremise)
		            {
		                if (aLoopNetworkTypes[i] != null)
		                {
		                    // Per CR 16563: set the value of recommended RTID to the WTN found on the first GPON/RGPON loop
		                    // where INQFASG.RSP.LOOP.ADL is not equal to Y and INQFASG.RSP.LOOP.SEG[last].LTS is equal to “CCV”
		                    if (aLoopNetworkTypes[i].equalsIgnoreCase("GPON") || aLoopNetworkTypes[i].equalsIgnoreCase("RGPON"))
		                    {
		                        boolean bADLOk = false;
		                        if (aLoop.getADL() == null)
		                        {
		                            bADLOk = true;
		                        }
		                        else
		                        {
		                            if (!aLoop.getADL().equalsIgnoreCase("Y"))
		                            {
		                                bADLOk = true;
		                            }
		                        }
		                        // check ADL and LTS values
		                        if (bADLOk && aLoopSegments != null
		                            && aLoopSegments[aLoopSegments.length-1].getLTS() != null
		                            && aLoopSegments[aLoopSegments.length-1].getLTS().equalsIgnoreCase("CCV"))
		                        {
		                            aRelatedCircuitID = aWorkingTelephoneNumber;
		                            aResponseHelper.setRecommendedRTID_GPON(aWorkingTelephoneNumber.toString());
		                        }
		                    }
		                    else if (aLoopServiceOrders != null)
		                    {
		                        boolean aSOSegmentFound = false;
		                        for (int k=0; k < aLoopServiceOrders.length; k++)
		                        {
		                            Segment[] aSOSegments = aLoopServiceOrders[k].getSegments();
		                            if (aSOSegments != null)
		                            {
		                                for (int j=0; j < aSOSegments.length; j++)
		                                {
		                                    if (aLoopNetworkTypes[i].equalsIgnoreCase("GPON") || aLoopNetworkTypes[i].equalsIgnoreCase("RGPON"))
		                                    {
		                                        boolean bADLOk = false;
		                                        if (aLoop.getADL() == null)
		                                        {
		                                            bADLOk = true;
		                                        }
		                                        else
		                                        {
		                                            if (!aLoop.getADL().equalsIgnoreCase("Y"))
		                                            {
		                                                bADLOk = true;
		                                            }
		                                        }
		                                        // check ADL and LTS values
		                                        if (bADLOk
		                                            && aSOSegments[aSOSegments.length-1].getLTS() != null
		                                            && aSOSegments[aSOSegments.length-1].getLTS().equalsIgnoreCase("CCV"))
		                                        {
		                                            aRelatedCircuitID = aWorkingTelephoneNumber;
		                                            aResponseHelper.setRecommendedRTID_GPON(aWorkingTelephoneNumber.toString());
		                                            aSOSegmentFound = true;
		                                            break;
		                                        }
		                                    }
		                                }
		                            }
		                            if (aSOSegmentFound)
		                            {
		                                break;
		                            }
		                        }
		                    }
		                }
		                aResponseHelper.setFiberInPremise(true);
		            }
		            else
		            {
		                if (bIsLoopConditionedForLS ||
		                		aLoopNetworkTypes[i].equalsIgnoreCase(NetworkType3Values.IPCO) ||
		                		aLoopNetworkTypes[i].equalsIgnoreCase(NetworkType3Values.IPRT))
		                {
		                    aRelatedCircuitID = aWorkingTelephoneNumber;
		                }
		            }

	                ///////////////////////////////////////////////////////////////////////////////////////////////////
	                // Determine the loop's RTID for Provisioning
	                ///////////////////////////////////////////////////////////////////////////////////////////////////
		            // If the U-Verse Order Due Date is sent by the client and the loop is Lightspeed capable
		            // with pending service orders having Order Type = “N” or “T” 
		            //     - If a WTN-formatted circuit ID is found on the CKID, CKID2, or CKID3 fields,
		            //       the WTN will be the loop’s RTID. 
	                ///////////////////////////////////////////////////////////////////////////////////////////////////
		            if (aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING)
		                && aUverseOrderDueDate != null
		                && bIsLoopConditionedForLS
		                && aLoopServiceOrders != null)
		            {
		            	for (int j=0; j < aLoopServiceOrders.length; j++)
		                {
		                    if (aLoopServiceOrders[j].getORD() != null
		                    	&& (aLoopServiceOrders[j].getORD().charAt(0) == 'N'
		                    	 	|| aLoopServiceOrders[j].getORD().charAt(0) == 'T'))
		                    {
		                    	if (aWorkingTelephoneNumber.length() > 0)
		                    	{
		                    		aRelatedCircuitID = aWorkingTelephoneNumber;
		                    	}
		                    }
		                }
		            }
		            
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            // format service items (conflicting services)
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            aServiceItems = buildServiceItems(aTransactionType,
		            								  aRegion,
		                                              aServiceOrderNumber,
		                                              bIsFiberInPremise,
		                                              aLoopNetworkTypes[i],
		                                              bIsLoopConditionedForLS,
		                                              aCommitStatus,
		                                              aLoopCKID,
		                                              aLoopCKID2,
		                                              aLoopCKID3,
		                                              aServiceCode.toString(),
		                                              aModifier.toString(),
		                                              aLoopUSOC,
		                                              aLoopServiceType,
		                                              aLoopServiceOrders,
		                                              aLoopNIF,
		                                              aResponseHelper);	
		            
		            // CR24678: If loop is LS-Conditioned and NO services exist, we set the flag
		            if( bIsLoopConditionedForLS 
                    	&& aServiceItems == null )
                    {
                    	aResponseHelper.setOverrideUNEPLWCIndicator(true);
                    }
                    // CR24678: If loop is LS-Conditioned and a NON-UVERSE NON-CONFLICTING service exists, we set the flag 
		            else if ( bIsLoopConditionedForLS 
                    	      && aServiceItems != null )
                    	
                    {
                    	for (int j = 0; j < aServiceItems.length; j ++)
                    	{
                    		if ( !OptHelper.isStringOptEmpty(aServiceItems[j].aServiceType)
                    			 && !aServiceItems[j].aServiceType.toString().equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_UVERSE)
                    			 && aServiceItems[j].aConflictingServiceIndicator == false)
                    		{
                    			aResponseHelper.setOverrideUNEPLWCIndicator(true);
                    			break;
                    		}
                    	}
                    }
                    
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            // format pending service orders
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            aPendingServiceOrders = buildPendingServiceOrders(aLoopServiceOrders,
		                                                              aPrimaryNpaNxx,
		                                                              aUverseOrderDueDate);
		            
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            // format response driver
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            
		            aResponseDriver = new PublishValidateFacilityNotificationResponseDriver();
		            aResponseDriver.setNT(aLoopNetworkTypes[i]);
		            aResponseDriver.setWTN(aWorkingTelephoneNumber.toString());
		            
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            // format facility loop
		            ///////////////////////////////////////////////////////////////////////////////////////////////////
		            aLoopAnalyzed = new FacilityLoop2((StringOpt) IDLUtil.toOpt(StringOpt.class, aWorkingTelephoneNumber.toString()),
		                                              (StringOpt) IDLUtil.toOpt(StringOpt.class, aCircuitID.toString()),
		                                              (StringOpt) IDLUtil.toOpt(StringOpt.class, aRelatedCircuitID.toString()),
		                                              (StringOpt) IDLUtil.toOpt(StringOpt.class, aCommitStatus.toString()),
		                                              (StringOpt) IDLUtil.toOpt(StringOpt.class, aBroadbandPair.toString()),
		                                              (ServiceItemSeqOpt) IDLUtil.toOpt(ServiceItemSeqOpt.class, aServiceItems),
		                                              (PendingServiceOrderSeqOpt) IDLUtil.toOpt(PendingServiceOrderSeqOpt.class, aPendingServiceOrders));

		            aResponseDriver.setFacilityLoop(aLoopAnalyzed);
		            aResponseDriversList.add(aResponseDriver);
		        }
		        else
		        {
		            aUtility.log(LogEventId.INFO_LEVEL_1, "Skipping loop : Network Type is " + aLoopNetworkTypes[i]);
		        }
            }
        }
        catch (SQLException sqle) 
        {
        	// log: exception message            
            StringBuffer sqleLogMessage = new StringBuffer();
            sqleLogMessage.append("SQL Exception in analyzeLoop() ");
            aUtility.log(LogEventId.ERROR, sqleLogMessage.toString());
            // set: exception code / message
           
            aResponseHelper.handleException(ExceptionCode.ERR_RM_INVALID_DATA,
            		sqle.getMessage(),
                   (String) aProperties.get("BIS_NAME").toString(),
                   aRequestHelper);
           
            // throw: System Failure Exception
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                    sqleLogMessage.toString(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        catch (NullPointerException npe) 
        {
        	// log: exception message     
            StringBuffer npeLogMessage = new StringBuffer();
            npeLogMessage.append("Null Pointer Exception in analyzeLoop() ");
            
            // set: exception code / message
           
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		npe.getMessage(),
                   (String) aProperties.get("BIS_NAME").toString(),
                   aRequestHelper);
            // throw: System Failure Exception
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                    npeLogMessage.toString(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        catch (Exception e) 
        {
        	// log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception in analyzeLoop() ");
            eLogMessage.append(e.getMessage());
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            // set: exception code / message
           
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		e.getMessage(),
                   (String) aProperties.get("BIS_NAME").toString(),
                   aRequestHelper);
            // throw: System Failure Exception
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                    eLogMessage.toString(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
       }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
        return aLoopAnalyzed; 
    }
 
	/**
     * identifies premise-based VRAD indicator by calling INQTERM.
     * 
     * @param BisContext aContext
     * @param Loop aLoop
     * @param String aPrimaryNpaNxx
     * @param PublishValidateFacilityNotificationResponseHelper aResponseHelper
     * @return boolean
     * 
     * @author Julius Sembrano
     */ 
	private boolean checkVRAD(BisContext aContext, 
							  Loop aLoop, 
							  String aPrimaryNpaNxx, 
							  PublishValidateFacilityNotificationResponseHelper aResponseHelper
							  ) 
	    throws
	    InvalidData,
	    AccessDenied,
	    BusinessViolation,
	    SystemFailure,
	    NotImplemented,
	    ObjectNotFound,
	    DataNotFound
	{
		boolean bVRADFound = false;
		
		INQTERMImpl aINQTERMRequest = buildINQTERMRequest(aContext, aLoop, aResponseHelper);
		
		// process request
		if (aFACSRCService == null) 
        {
            try 
            {
                aFACSRCService = new FACSRCService(aProperties, aUtility);
            }
            catch (ServiceException e)  
            {
                ExceptionBuilder.parseException(aContext,
                                                aRuleFile,
                                                null,
                                                null,
                                                e.getMessage(),
                                                true,
                                                1,
                                                null,
                                                e,
                                                aUtility,            // utility
                                                null,               // origin use file
                                                null,               // severity use file
                                                null);
            }
        }
		try
        {
			ArrayList aSYSTPValues = null;
			String aINQTERMResponse = sendINQTERMRequest(aContext, aPrimaryNpaNxx, aINQTERMRequest);
        	
        	if(aINQTERMResponse != null)
        	{
        		INQTERMResponseHelper aINQTERMResponseHelper = parseINQTERMResponse(aINQTERMResponse, aResponseHelper);
        		aSYSTPValues = aINQTERMResponseHelper.getSYSTPValues();
        		
        		
        		for(int i = 0; i < aSYSTPValues.size(); i++)
        		{
        			String aSYSTP = (String)aSYSTPValues.get(i);
	        		if(aSYSTP != null)
	        		{
	        			aUtility.log(LogEventId.INFO_LEVEL_1, "SYSTP " + i + "  ==> " + aSYSTP);
	        			// compare the value of the SYSTP received from INQTERM
		        		if(aSYSTP.equalsIgnoreCase(INQTERMConstants.ALLOWED_SYSTP1)
		        				|| aSYSTP.equalsIgnoreCase(INQTERMConstants.ALLOWED_SYSTP2))
		        		{
		        			bVRADFound = true;
		        		}
	        		}
        		}
        	}
        }
        catch (SystemFailure sf)
        {
            // log: exception message
            String errText = "Error accessing LFACS";
            String ErrCode = ExceptionCode.ERR_RM_SYSTEM_FAILURE;
            try
            {
                errText = errText
                          + (sf.aExceptionData.aDescription != null ? (": " + sf.aExceptionData.aDescription) : ".");
            }
            catch (Exception ee)
            { /*Ignore, the default value will be used*/ }
            try
            {
                ErrCode = sf.aExceptionData.aCode;
            }
            catch (Exception ee)
            { /*Ignore, the default value will be used*/ }

            aUtility.log(LogEventId.INFO_LEVEL_1, "SystemFailure in LFACS: analyzeLoop()");
            
            // set: exception code / message
           
            aResponseHelper.handleException(ErrCode,
            		errText,
                   (String) aProperties.get("BIS_NAME").toString(),
                   aRequestHelper);
            // throw: System Failure Exception
            aUtility.throwException(ErrCode,
                                    errText,
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        catch (Exception e) 
        {
            // log: message            
        	aUtility.log(LogEventId.INFO_LEVEL_1, "Exception in LFACS: publishValidateFacilityNotification()");
            
            String errText = "Error accessing LFACS" + (e.getMessage() != null ? (": " + e.getMessage()) : ".");
           
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		errText,
                   (String) aProperties.get("BIS_NAME").toString(),
                   aRequestHelper);
            // throw: System Failure Exception
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                    errText,
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);           
        }
		
		return bVRADFound;
		
	}

	/**
     * Builds network type from Copper.
     * 
     * @param String aCommitStatus
     * @param String aBroadbandPair
     * @param String[] aNetworkTypeList
     * @return String
     * 
     * @author Julius Sembrano
     */   
    private String buildNetworkTypeFromCopper(String[] aNetworkTypeList) 
    {
        String aMethodName = "LFACS: buildNetworkTypeFromCopper()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    	
		String aNetworkType = null;
		
		// check the NTIs sent by the client
		for(int i = 0; i < aNetworkTypeList.length; i++)
		{
			// if client requests FTTN, assign the loop to FTTN
			if(aNetworkTypeList[i].equalsIgnoreCase(NetworkType2Values.FTTN))
			{
				aNetworkType = NetworkType2Values.FTTN;
			}
			// if client requests IP_CO, assign the loop to IP_CO
			else if(aNetworkTypeList[i].equalsIgnoreCase(NetworkType3Values.IPCO))
			{
				aNetworkType = NetworkType3Values.IPCO;
			}
			// if client request IP_RT, assign the loop to IP_RT
			else if(aNetworkTypeList[i].equalsIgnoreCase(NetworkType3Values.IPRT))
			{
				aNetworkType = NetworkType3Values.IPRT;
			}
		}
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
		return aNetworkType;
	}

	/**
     * Determines number of loops to be analyzed.
     * 
     * @param int aMaxPairsToAnalyze
     * @param int aNumberOfLoopsFromLFACS
     * @param INQFASGResponseHelper aResponseHelper
     * @return int
     * 
     * @author Rene Duka
     */
    private int determineLoopsToAnalyze(
        int aMaxPairsToAnalyze,
        int aNumberOfLoopsFromLFACS,
        PublishValidateFacilityNotificationResponseHelper aResponseHelper) 
    {
        String aMethodName = "LFACS: determineLoopsToAnalyze()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    
        // initialize
        int aLoopsToAnalyze = aMaxPairsToAnalyze;
        // if count from client is less than count from LFACS, 
        //     - set to count from client
        //     - set Partial LFACS Facilities Indicator to true 
        // if count from client is greater than count from LFACS, 
        //     - set to count from LFACS
        // if count from client is eqaul to count from LFACS, 
        //      - set to count from LFACS
        if (aMaxPairsToAnalyze < aNumberOfLoopsFromLFACS) 
        {
            aLoopsToAnalyze = aMaxPairsToAnalyze;
            aResponseHelper.setPartialAnalysisIndicator(true);
        }
        else if (aMaxPairsToAnalyze > aNumberOfLoopsFromLFACS) 
        {
            aLoopsToAnalyze = aNumberOfLoopsFromLFACS;
        } 
        else 
        {
            aLoopsToAnalyze = aNumberOfLoopsFromLFACS;
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);                
        return aLoopsToAnalyze;
    }
    
    /**
     * Check if there is fiber on the premise.
     * 
     * @param AddressType[] aAddressTypes
     * @exception Exception      : Exception.
     * 
     * @author Rene Duka
     */
    private boolean checkFiber(
        AddressType[] aAddressTypes,
        ServiceOrder[] aServiceOrders)
        throws 
            Exception 
    {
        String aMethodName = "LFACS: checkFiber()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
           
        boolean bCheckFiber = false;
        try 
        {
            if (aAddressTypes != null)
            {
                for (int i=0; i < aAddressTypes.length; i++) 
                {
                    if (aAddressTypes[i].getBSTE() != null) 
                    {
                        bCheckFiber = true;
                        break;
                    }
                }
            }
            
            // if fiber not found in the loop, check the service orders
            if (!bCheckFiber)
            {
                if (aServiceOrders != null)
                {
                    for (int i=0; i < aServiceOrders.length; i++) 
                    {
                        AddressType[] aSOAddressTypes = aServiceOrders[i].getAddressTypes();
                        if (aSOAddressTypes != null)
                        {
                            for (int j=0; j < aSOAddressTypes.length; j++) 
                            {
                                if (aSOAddressTypes[j].getBSTE() != null) 
                                {
                                    bCheckFiber = true;
                                    break;
                                }
                            }
                        }
                        if (bCheckFiber)
                        {
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception e) 
        {
            // log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception in checkFiber() ");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            // throw: Exception
            throw new Exception(eLogMessage.toString());
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " Fiber Found: " + bCheckFiber);
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
        return bCheckFiber;
    }

    /**
     * Check if network type was in the list of network types sent by the client.
     * 
     * @param String[] aNetworkTypeList
     * @param String aNetworkType
     * @exception Exception      : Exception.
     * 
     * @author Rene Duka
     */
    private boolean checkNetworkType(
        String[] aNetworkTypeList,
        String aNetworkType)
        throws 
            Exception 
    {
        String aMethodName = "LFACS: checkNetworkType()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
           
        boolean bCheckNetworkType = false;
        try 
        {
            for (int i=0; i < aNetworkTypeList.length; i++) 
            {
                if (aNetworkTypeList[i].equalsIgnoreCase(aNetworkType))
                {
                    bCheckNetworkType = true;
                    break;
                }
            }
        }
        catch (Exception e) 
        {
            // log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception in checkNetworkType() ");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            // throw: Exception
            throw new Exception(eLogMessage.toString());
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " Network Type Found: " + bCheckNetworkType);
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
        return bCheckNetworkType;
    }
    
    /**
     * Checks CKIDs from LFACS.
     *     - Derive WTN
     *     - Derive Circuit ID
     *     - Derive Service Code and Modifier
     * 
     * @param String aLoopCKID
     * @param String aLoopCKID2
     * @param String aLoopCKID3
     * @param String aPrimaryNPANXX
     * @param StringBuffer aWorkingTelephoneNumber
     * @param StringBuffer aCircuitID
     * @param StringBuffer aServiceCode
     * @param StringBuffer aModifier
     * @exception Exception      : Exception.
     * 
     * @author Deepti Nayar
     */
    private void checkCKIDs(
        String aLoopCKID,
        String aLoopCKID2,
        String aLoopCKID3,
        String aPrimaryNPANXX, 
        StringBuffer aWorkingTelephoneNumber,
        StringBuffer aCircuitID,
        StringBuffer aServiceCode,
        StringBuffer aModifier)
        throws
            Exception 
    {
        String aMethodName = "LFACS: checkCKIDs()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    
        try 
        {
            StringBuffer aNPA = null;
            if (aPrimaryNPANXX != null) 
            {
                aNPA = new StringBuffer(aPrimaryNPANXX.substring(0, 3));
            }
            
            // examine CKID
            StringBuffer aCKID = null;
            
            try
            {
            	if (aLoopCKID != null) 
                {
                    aCKID = new StringBuffer(aLoopCKID);
                    if (isNumeric(aCKID))
                    {
                        // check if aCKID is in WTN format
                        if (aCKID.toString().length() == PublishValidateFacilityNotificationConstants.WTN_Format1_LENGTH)
                        {
                            if (aCKID.charAt(3) == '-' || Character.isWhitespace(aCKID.charAt(3)))
                            {
                                aCKID.deleteCharAt(3);
                                aWorkingTelephoneNumber.append(aNPA.toString().concat(aCKID.toString()));
                            }
                        }
                        else if (aCKID.toString().length() == PublishValidateFacilityNotificationConstants.WTN_Format2_LENGTH)
                        {
                            if ((aCKID.charAt(3) == '-' || Character.isWhitespace(aCKID.charAt(3)))
                                && (aCKID.charAt(7) == '-' || Character.isWhitespace(aCKID.charAt(7))))
                            {
                                aCKID.deleteCharAt(3);
                                aCKID.deleteCharAt(6);
                                aWorkingTelephoneNumber.append(aCKID);
                            }
                        }
                        else if (aCKID.toString().length() == PublishValidateFacilityNotificationConstants.WTN_Format3_LENGTH)
                        {
                            aWorkingTelephoneNumber.append(aCKID);
                        }
                    }
                    // check if aCKID is a CLS-formatted Circuit ID
                    if (aCKID.indexOf(".") >= 0) 
                    {
                        aCircuitID.append(formatCircuitID(aCKID,
                                                          aServiceCode,
                                                          aModifier));
                    }
                }
            }
            catch (Exception e)
            {
            	aUtility.log(LogEventId.INFO_LEVEL_1, "CKID is null");
            }
            
            
            // examine CKID2
            StringBuffer aCKID2 = null;
            try 
            {
            	if (aLoopCKID2 != null ) 
                {
                    aCKID2 = new StringBuffer(aLoopCKID2);
                    if (isNumeric(aCKID2))
                    {
                        // check if aCKID2 is in WTN format
                        if (aCKID2.toString().length() == PublishValidateFacilityNotificationConstants.WTN_Format1_LENGTH)
                        {
                            if (aCKID2.charAt(3) == '-' || Character.isWhitespace(aCKID2.charAt(3)))
                            {
                                aCKID2.deleteCharAt(3);
                                aWorkingTelephoneNumber.append(aNPA.toString().concat(aCKID2.toString()));
                            }
                        }
                        else if (aCKID2.toString().length() == PublishValidateFacilityNotificationConstants.WTN_Format2_LENGTH)
                        {
                            if ((aCKID2.charAt(3) == '-' || Character.isWhitespace(aCKID2.charAt(3)))
                                && (aCKID2.charAt(7) == '-' || Character.isWhitespace(aCKID2.charAt(7))))
                            {
                                aCKID2.deleteCharAt(3);
                                aCKID2.deleteCharAt(6);
                                aWorkingTelephoneNumber.append(aCKID2);
                            }
                        }
                        else if (aCKID2.toString().length() == PublishValidateFacilityNotificationConstants.WTN_Format3_LENGTH)
                        {
                            aWorkingTelephoneNumber.append(aCKID2);
                        }
                    }
                    // check if aCKID2 is a CLS-formatted Circuit ID
                    if (aCKID2.indexOf(".") >= 0) 
                    {
                        aCircuitID.append(formatCircuitID(aCKID2,
                                                          aServiceCode,
                                                          aModifier));
                    }
                }
            }
            catch (Exception e)
            {
            	aUtility.log(LogEventId.INFO_LEVEL_1, "CKID2 is null");
            }
            
    
            // examine CKID3
            StringBuffer aCKID3 = null;
            try
            {
            	if (aLoopCKID3 != null) 
                { 
                    aCKID3 = new StringBuffer(aLoopCKID3);
                    if (isNumeric(aCKID3))
                    {
                        // check if aCKID3 is in WTN format
                        if (aCKID3.toString().length() == PublishValidateFacilityNotificationConstants.WTN_Format1_LENGTH)
                        {
                            if (aCKID3.charAt(3) == '-' || Character.isWhitespace(aCKID3.charAt(3)))
                            {
                                aCKID3.deleteCharAt(3);
                                aWorkingTelephoneNumber.append(aNPA.toString().concat(aCKID3.toString()));
                            }
                        }
                        else if (aCKID3.toString().length() == PublishValidateFacilityNotificationConstants.WTN_Format2_LENGTH)
                        {
                            if ((aCKID3.charAt(3) == '-' || Character.isWhitespace(aCKID3.charAt(3)))
                                && (aCKID3.charAt(7) == '-' || Character.isWhitespace(aCKID3.charAt(7))))
                            {
                                aCKID3.deleteCharAt(3);
                                aCKID3.deleteCharAt(6);
                                aWorkingTelephoneNumber.append(aCKID3);
                            }
                        }
                        else if (aCKID3.toString().length() == PublishValidateFacilityNotificationConstants.WTN_Format3_LENGTH)
                        {
                            aWorkingTelephoneNumber.append(aCKID3);
                        }
                    }
                    // check if aCKID3 is a CLS-formatted Circuit ID
                    if (aCKID3.indexOf(".") >= 0) 
                    {
                        aCircuitID.append(formatCircuitID(aCKID3,
                                                          aServiceCode,
                                                          aModifier));
                    }
                }
            }
            catch (Exception e)
            {
            	aUtility.log(LogEventId.INFO_LEVEL_1, "CKID3 is null");
            }
            
        }
        catch (Exception e) 
        {
            // log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception in checkCKIDs() ");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            // throw: Exception
            throw new Exception(eLogMessage.toString());
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
    }
    
    /**
     * Checks if the CKID is numeric.
     * @param StringBuffer aInput
     * @return boolean
     * @author Rene Duka
     */
    private boolean isNumeric(StringBuffer aInput)
    {
    	// Updated code for the Pending PR in lsprod
    	// Issue: UNE-P should be returned instead of POTS
    	if (aInput == null)
    		return false;
    		
    	
    	for (int i=0; aInput != null && i < aInput.length(); i++)
        {
            if (aInput.charAt(i) == '-' || aInput.charAt(i) == ' ')
            {
                continue;
            }
            if (!Character.isDigit(aInput.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Formats the CircuitID.
     * 
     * @param StringBuffer aCKID
     * @param StringBuffer aServiceCode
     * @param StringBuffer aModifier
     * @return String
     * @exception Exception      : Exception.
     * 
     * @author Rene Duka
     */
    private String formatCircuitID(
    		StringBuffer aCKID,
            StringBuffer aServiceCode,
            StringBuffer aModifier) 
        {
            String aMethodName = "LFACS: formatCircuitID()";
            aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
               
            int delimiter_count = 0;
 
            // look for valid ckid with 4 dots 
            for (int i = 0 ; i < aCKID.length(); i++)
            {
            	if (aCKID.charAt(i) == '.')
            		delimiter_count++;
            }
                
            // look for service code and modifier if it has valid ckid format
            if (delimiter_count == 4) 
            {
                String aField = "";
          
            	StringTokenizer aTokenizer = new StringTokenizer(aCKID.toString(), ".");
            	
            	if (!aCKID.toString().startsWith(".")) // skip the 1st field
    	        {
            		if (aTokenizer.hasMoreElements()) 
    	            {
    	                aTokenizer.nextToken().trim();
    	            }
    	        }
    	            
    	        // look for service code and modifier in 2nd field
    	        if (aTokenizer.hasMoreElements()) 
    	        {
    	        	aField = aTokenizer.nextToken().trim();
    	        }
    	             
    	        // check 2nd field length
    	        if (aField.length() == 4 )
    	        {
    	        	// format service code and modifier if Circuit ID in CLS format is found
    	            aServiceCode.append(aField.substring(0, 2));
    	            aModifier.append(aField.substring(2, 4));
    	        }
            }
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
            
            return aCKID.toString();
        }



    /**
     * Builds network type from fiber.
     * 
     * @param Segment[]      aSegments
     * @param ServiceOrder[] aServiceOrders
     * @return String
     * @exception Exception      : Exception.
     * 
     * @author Rene Duka
     */
    private String buildNetworkTypeFromFiber(
        Segment[] aSegments,
        ServiceOrder[] aServiceOrders)
        throws 
            Exception 
    {
        String aMethodName = "LFACS: buildNetworkTypeFromFiber()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    
        // If fiber found in the loop, determine what type of fiber it is by looking at
        //     the FIBER_FACILITY table.
        FiberFacilitiesTable aFiberFacilitiesTable = new FiberFacilitiesTable();
        FiberFacilitiesRow aRow = null;
        String aNetworkType = null;
        // check if segments are found in the loop
        try
        {
            if (aSegments != null)
            {
                aRow = aFiberFacilitiesTable.retrieveRow(aSegments[aSegments.length -1].getTP(),
                                                         aSegments[aSegments.length -1].getMODEL(),
                                                         aSegments[aSegments.length -1].getLTS(),
                                                         aProperties,
                                                         aUtility);
                // Set the value of network type
                if (aRow.getFIBER_FACILITY().length() > 0)
                {
                    aNetworkType = aRow.getFIBER_FACILITY();
                }
            }
            // check if segments are found in the pending SO
            else if (aServiceOrders != null)
            {
                boolean aSOSegmentFound = false;
                for (int i=0; i < aServiceOrders.length; i++) 
                {
                    Segment[] aSOSegments = aServiceOrders[i].getSegments();
                    if (aSOSegments != null)
                    {
                        for (int j=0; j < aSOSegments.length; j++) 
                        {
                            aRow = aFiberFacilitiesTable.retrieveRow(aSOSegments[aSOSegments.length -1].getTP(),
                                                                     aSOSegments[aSOSegments.length -1].getMODEL(),
                                                                     aSOSegments[aSOSegments.length -1].getLTS(),
                                                                     aProperties,
                                                                     aUtility);
                            // change for CR 16563
                            // if no row is found, set Fiber Facility to Null
                            if (aRow.getFIBER_FACILITY().length() > 0)
                            {
                                aNetworkType = aRow.getFIBER_FACILITY();
                                aSOSegmentFound = true;
                                break;
                            }
                        }
                    }
                    if (aSOSegmentFound)
                    {
                        break;
                    }
                }
            }
            // if network type found in the FIBER_FACILITIES table is BPON, 
            //     - set network type to FTTPIP
            if (aNetworkType != null
                && aNetworkType.equalsIgnoreCase("BPON"))
            {
                aNetworkType = NetworkType2Values.FTTP;
            }
        }
        catch (Exception e) 
        {
            // log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception in buildNetworkTypeFromFiber() ");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            // throw: Exception
            throw new Exception(eLogMessage.toString());
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
        return aNetworkType;
    }

    
    /**
     * Builds service items.
     * 
     * @param String  aTransactionType
     * @param String  aRegion
     * @param String  aServiceOrderNumber
     * @param boolean aIsFiberInPremise
     * @param String  aFiberFacility
     * @param boolean aIsLoopConditionedForLS
     * @param String  aCKID
     * @param String  aCKID2
     * @param String  aCKID3
     * @param String  aCLS_SERVICE_CODE
     * @param String  aCLS_MODIFIER
     * @param String  aUSOC
     * @param String  aSERVICETYPE
     * @param ServiceOrder[] aServiceOrders
     * @param String aLoopNIF
     * @param PublishValidateFacilityNotificationResponseHelper aResponseHelper
     * @return ServiceItem[]
     * @exception SQLException   : SQL Exception.
     * @exception Exception      : Exception.
     * @author Rene Duka
     */
    private ServiceItem[] buildServiceItems(
        String aTransactionType,
    	String aRegion,
        String aServiceOrderNumber,
        boolean bIsFiberInPremise,
    	String aNetworkType,
        boolean aIsLoopConditionedForLS,
        StringBuffer aCommitSatus,
        String aCKID,
        String aCKID2,
        String aCKID3,
        String aCLS_SERVICE_CODE,
        String aCLS_MODIFIER,
        String aUSOC,
        String aSERVICETYPE,
        ServiceOrder[] aServiceOrders,
        String aLoopNIF,
        PublishValidateFacilityNotificationResponseHelper aResponseHelper)
        throws 
            SQLException,
            Exception 
    {
	    String aMethodName = "LFACS: buildServiceItems()";
	    aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

	    ServiceItem[] aServiceItems = null;
	    ArrayList aServiceItemsArrayTemp = new ArrayList();
	    ArrayList aServiceItemsArray = new ArrayList();
	    boolean aConflictingServiceIndicator = false;

	    try
	    {
	        LfacsFiberServicesRow aLfacsFiberServicesRow = null;
	        LfacsCopperServicesRow aLfacsCopperServicesRow = null;
	        LfacsIpdslamServicesRow aLfacsIpdslamServicesRow = null;
	        ServiceCategoryRow aServiceCategoryRow = null;
	        String aSERVICE_CATEGORY = null;

	        // initialize the flags
	        boolean bFoundRowWithUsocAndServiceCategory = false;
            boolean bFoundRowWithServiceCodeAndModifierAndServiceCategory = false;
            boolean bFoundRowWithServiceCodeAndServiceCategory = false;
     
            //Copper table flag
            boolean oNotFoundRowWithUsocAndServiceCategory = true;
            boolean oNotFoundRowWithServiceCodeAndModifierAndServiceCategory = true;
            boolean oNotFoundRowWithServiceCodeAndServiceCategory = true;
            boolean oNotFoundRowWithServiceCategory = true;

            //ipdslam flag
            boolean mNotFoundRowWithUsocAndServiceCategory = true;
            boolean mNotFoundRowWithServiceCodeAndModifierAndServiceCategory = true;
            boolean mNotFoundRowWithServiceCodeAndServiceCategory = true;
	        boolean mNotFoundRowWithNifAndServiceCategory = true;
            boolean mNotFoundRowWithServiceCodeAndModifierAndNifAndServiceCategory = true;
            
            if (aCLS_SERVICE_CODE.length() <= 0)
            {
            	aCLS_SERVICE_CODE = null;
            }
            
            if (aCLS_MODIFIER.length() <=0 )
            {
            	aCLS_MODIFIER = null;
            }
            
            StringBuffer aCircuitID = ((aCKID != null) && !(aCKID.equals(""))) ? new StringBuffer(aCKID) : null;
            StringBuffer aCircuitID2 = ((aCKID2 != null) && !(aCKID2.equals(""))) ? new StringBuffer(aCKID2) : null;
            StringBuffer aCircuitID3 = ((aCKID3 != null) && !(aCKID3.equals(""))) ? new StringBuffer(aCKID3) : null;
            
            // Query SERVICE_CATEGORY table using the loop service_type to get the service_category
	        if (aSERVICETYPE != null && !aSERVICETYPE.equals(""))
	        {
	        	ServiceCategoryTable aServiceCategoryTable = new ServiceCategoryTable();
	            aServiceCategoryRow = null;

	            aServiceCategoryRow = aServiceCategoryTable.retrieveRow(aSERVICETYPE,
																		aProperties,
																		aUtility);
	            if (aServiceCategoryRow != null)
	            {
	            	aSERVICE_CATEGORY = aServiceCategoryRow.getSERVICE_CATEGORY();
	            }
	        }
	        
	        // Determine services for FIBER
	        if (bIsFiberInPremise)
	        {
	        	if (aNetworkType != null)
                {
                    if (aNetworkType.equalsIgnoreCase(NetworkType2Values.FTTP))
                    {
                    	aNetworkType = "BPON";
                    }
                    aLfacsFiberServicesRow = new LfacsFiberServicesRow();
                    aLfacsFiberServicesRow.setSERVICE_NAME(aNetworkType);
                	aLfacsFiberServicesRow.setNEGOTIATION_CONFLICT_IND("N");
                	aLfacsFiberServicesRow.setPROVISIONING_CONFLICT_IND("N");
                    aServiceItemsArrayTemp.add(aLfacsFiberServicesRow);
                }
	        	
	        	LfacsFiberServicesTable aLfacsFiberServicesTable = new LfacsFiberServicesTable();

	            // retrieve the row from LFACS_FIBER_SERVICES table using the following information:
	            //     - Region
	            //     - USOC
	            //     - Service Category
	            if (aUSOC != null && aSERVICE_CATEGORY != null)
	            {
	                aLfacsFiberServicesRow = null;
	                aLfacsFiberServicesRow = aLfacsFiberServicesTable.retrieveRowByUsocAndServiceCategory(aRegion,
	                                                                                    				  aUSOC,
	                                                                                    				  aSERVICE_CATEGORY,
	                                                                                    				  aProperties,
	                                                                                    				  aUtility);
	                if (aLfacsFiberServicesRow != null)
	                {
	                    aServiceItemsArrayTemp.add(aLfacsFiberServicesRow);
	                    bFoundRowWithUsocAndServiceCategory = true;
	                }
	            }

	            // retrieve the row from LFACS_FIBER_SERVICES table using the following information:
	            //     - Region
	            //     - USOC set to NULL
	            //     - Service Category
	            if (aSERVICE_CATEGORY != null
	            	&& !bFoundRowWithUsocAndServiceCategory)
	            {
	            	aLfacsFiberServicesRow = null;
	            	aLfacsFiberServicesRow = aLfacsFiberServicesTable.retrieveRowByUsocAndServiceCategory(aRegion,
	                                                                                        			  null,
	                                                                                        			  aSERVICE_CATEGORY,
	                                                                                        			  aProperties,
	                                                                                        			  aUtility);
	                if (aLfacsFiberServicesRow != null)
	                {
	                    aServiceItemsArrayTemp.add(aLfacsFiberServicesRow);
	                    bFoundRowWithUsocAndServiceCategory = true;
	                }
	            }

	            // retrieve the row from LFACS_FIBER_SERVICES table using the following information:
	            //     - Region
	            //     - Service Code from CLS-formatted Circuit ID
	            //     - Modifier from CLS-formatted Circuit ID
	            //	   - Service Category
	            if (aSERVICE_CATEGORY != null)
	            {
	            	aLfacsFiberServicesRow = null;
	            	aLfacsFiberServicesRow = aLfacsFiberServicesTable.retrieveRowByServiceCodeAndModifierAndServiceCategory(aRegion,
	            																											aCLS_SERVICE_CODE,
	            																											aCLS_MODIFIER,
	            																											aSERVICE_CATEGORY,
	            																											aProperties,
	            																											aUtility);
	                if (aLfacsFiberServicesRow != null)
	                {
	                    aServiceItemsArrayTemp.add(aLfacsFiberServicesRow);
	                    bFoundRowWithServiceCodeAndModifierAndServiceCategory = true;

	                }
	            }

	            // retrieve the row from LFACS_FIBER_SERVICES table using the following information:
	            //     - Region
	            //     - Service Code from CLS-formatted Circuit ID
	            //     - Service Category
	            if (aSERVICE_CATEGORY != null)
	            {
	            	aLfacsFiberServicesRow = aLfacsFiberServicesTable.retrieveRowByServiceCodeAndServiceCategory(aRegion,
	                																							 aCLS_SERVICE_CODE,
	                																							 aSERVICE_CATEGORY,
	                																							 aProperties,
	                																							 aUtility);
	                if (aLfacsFiberServicesRow != null)
	                {
	                    aServiceItemsArrayTemp.add(aLfacsFiberServicesRow);
	                    bFoundRowWithServiceCodeAndServiceCategory = true;
	                }
	            }

	            // retrieve the row from LFACS_FIBER_SERVICES table using the following information:
	            //     - Region
	            //     - Service Category
	            if (aSERVICE_CATEGORY != null
					&& !bFoundRowWithUsocAndServiceCategory
					&& !bFoundRowWithServiceCodeAndModifierAndServiceCategory
	            	&& !bFoundRowWithServiceCodeAndServiceCategory)
	            {
	            	aLfacsFiberServicesRow = null;
	            	aLfacsFiberServicesRow = aLfacsFiberServicesTable.retrieveRowByServiceCategory(aRegion,
	    																						   aSERVICE_CATEGORY,
	    																						   aProperties,
	    																						   aUtility);

	                if (aLfacsFiberServicesRow != null)
	                {
	                    aServiceItemsArrayTemp.add(aLfacsFiberServicesRow);
	                }
	            }

	            if (!(aServiceItemsArrayTemp.size() > 0))
	            {
	            	aLfacsFiberServicesRow = new LfacsFiberServicesRow();
	            	aLfacsFiberServicesRow.setSERVICE_NAME(PublishValidateFacilityNotificationConstants.SERVICE_NAME_OTHER);
	            	aLfacsFiberServicesRow.setPROVISIONING_CONFLICT_IND("Y");
	            	aLfacsFiberServicesRow.setNEGOTIATION_CONFLICT_IND("Y");
	                aServiceItemsArrayTemp.add(aLfacsFiberServicesRow);
	            }

	            // build the service items
	            if (aServiceItemsArrayTemp.size() > 0)
	            {
	                LfacsFiberServicesRow[] LfacsFiberServicesRow = (LfacsFiberServicesRow[])aServiceItemsArrayTemp.toArray(new LfacsFiberServicesRow[aServiceItemsArrayTemp.size()]);
	                ArrayList aServicesList = new ArrayList();
	                String aConflictingServiceInd = null;
	                for (int i=0; i < LfacsFiberServicesRow.length; i++)
	                {
	                    String aServiceName = LfacsFiberServicesRow[i].getSERVICE_NAME();

	                    if (aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)
		                    || aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3))
	                    {
	                    	aConflictingServiceInd = LfacsFiberServicesRow[i].getNEGOTIATION_CONFLICT_IND();
	                    }
	                    else if (aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING))
	                    {
	                    	aConflictingServiceInd = LfacsFiberServicesRow[i].getPROVISIONING_CONFLICT_IND();
	                    }

                    	if (aConflictingServiceInd.equalsIgnoreCase("Y"))
                    	{
                    		aConflictingServiceIndicator = true;
                    	}

                    	// initialize service item properties / object property manager
	                    ObjectProperty[] aServiceItemProperties = null;
	                    // log
	                    aUtility.log(LogEventId.DEBUG_LEVEL_2, "Service Name : " + aServiceName);
	                    aUtility.log(LogEventId.DEBUG_LEVEL_2, "Conflict?    : " + aConflictingServiceIndicator);
	                    // If one of the services found is U-verse,
	                    //     - set aConflictingServiceIndicator to FALSE if the Service Order Number is equal to the value of aSOACServiceOrderNumber in the input
	                    //     - otherwise set to TRUE
	                    //     - save the pending service order due date in the response helper
	                    if (aConflictingServiceIndicator)
	                    {
	                        // if U-Verse, set conflicting service indicator to false if ServiceOrder Number provided by client is one
	                        //             of the service orders in the loop
	                        if (aServiceName.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_UVERSE)
	                            && aServiceOrderNumber != null
	                            && aServiceOrders != null)
	                        {
	                            for (int j=0; j < aServiceOrders.length; j++)
	                            {
	                                if (aServiceOrders[j].getORD() != null)
	                                {
	                                    String aSOOrderNumber = aServiceOrders[j].getORD().substring(0, aServiceOrders[j].getORD().length() - 1);
	                                    if (aSOOrderNumber.equalsIgnoreCase(aServiceOrderNumber))
	                                    {
	                                        aConflictingServiceIndicator = false;
	                                        aResponseHelper.setRecommendedDueDate_UVerseSO(aServiceOrders[j].getDD());
	                                        break;
	                                    }
	                                }
	                            }
	                        }
	                    }

	                    ServiceItem aServiceItem
	                        = new ServiceItem((StringOpt) IDLUtil.toOpt(StringOpt.class, aServiceName),
	                                           aConflictingServiceIndicator,
	                                           (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, aServiceItemProperties));

	                    if (!aServicesList.contains(aServiceItem.aServiceType.theValue().toUpperCase()))
	                    {
	                    	aServiceItemsArray.add(aServiceItem);
	                    	aServicesList.add(aServiceItem.aServiceType.theValue().toUpperCase());
	                    }
	                }
	            }

	            if (aServiceItemsArray.size() > 0)
	            {
	            	aServiceItems = (ServiceItem[]) aServiceItemsArray.toArray(new ServiceItem[aServiceItemsArray.size()]);
	            }
	        }
	        
	        // Determine services for COPPER
	        else if (!bIsFiberInPremise
	        	&& aNetworkType != null
	        	&& (((String) aProperties.get("LFACS_COPPER_NTI_LIST")).indexOf(":" + aNetworkType.toUpperCase() + ":") >= 0))
	        {
	        	LfacsCopperServicesTable aLfacsCopperServicesTable = new LfacsCopperServicesTable();

	            // format LS_CONDITIONED
	            String aLS_CONDITIONED = null;
	            if ( aIsLoopConditionedForLS )
	            {
	            	if ( aCommitSatus != null && aCommitSatus.toString().trim().length() > 0 )
	            		aLS_CONDITIONED =  aCommitSatus.toString(); // copper
	            	else
	            		aLS_CONDITIONED =  aProperties.get("LS_COMMITTED_FIBER_VALUE") == null ? null : 
	            			(String) aProperties.get("LS_COMMITTED_FIBER_VALUE").toString() ;
	            }
	            else
	            {
	            	aLS_CONDITIONED = aProperties.get("LS_NON_COMMITTED_VALUE") == null ? null : 
	            		(String) aProperties.get("LS_NON_COMMITTED_VALUE").toString() ;
	            }

	            // retrieve the row from LFACS_COPPER_SERVICES table using the following information:
	            //     - Region
	            //     - Loop is conditioned for LS service or not
	            //     - USOC
	            //     - Service Category
	            if (aUSOC != null && aSERVICE_CATEGORY != null)
	            {
	                aLfacsCopperServicesRow = aLfacsCopperServicesTable.retrieveRowByUsocAndServiceCategory(aRegion,
	                                                                                        				aLS_CONDITIONED,
	                                                                                        				aUSOC,
	                                                                                        				aSERVICE_CATEGORY,
	                                                                                        				aProperties,
	                                                                                        				aUtility);
	                if (aLfacsCopperServicesRow != null)
	                {
                    	aServiceItemsArrayTemp.add(aLfacsCopperServicesRow);
                    	oNotFoundRowWithUsocAndServiceCategory = false;
	                } 
	                else 
	                {
	                	oNotFoundRowWithUsocAndServiceCategory = true;
	                }
	            }

	            // retrieve the row from LFACS_COPPER_SERVICES table using the following information:
	            //     - Region
	            //     - Loop is conditioned for LS service or not
	            //     - Service Code from CLS-formatted Circuit ID
	            //     - Modifier from CLS-formatted Circuit ID
	            //	   - Service Category
	            if (aSERVICE_CATEGORY != null
	            		&& aCLS_MODIFIER != null	
		            	&& aCLS_SERVICE_CODE != null
		            	&& oNotFoundRowWithUsocAndServiceCategory == true)
	            {
	            	aLfacsCopperServicesRow = aLfacsCopperServicesTable.retrieveRowByServiceCodeAndModifierAndServiceCategory(aRegion,
	            																											  aLS_CONDITIONED,
	            																											  aCLS_SERVICE_CODE,
	            																											  aCLS_MODIFIER,
	            																											  aSERVICE_CATEGORY,
	            																											  aProperties,
	            																											  aUtility);

	                if (aLfacsCopperServicesRow != null)
	                {
	                    aServiceItemsArrayTemp.add(aLfacsCopperServicesRow);
	                    oNotFoundRowWithServiceCodeAndModifierAndServiceCategory = false;
	                } 
	                else 
	                {
	                	oNotFoundRowWithServiceCodeAndModifierAndServiceCategory = true;
	                }
	            }

	            // retrieve the row from LFACS_COPPER_SERVICES table using the following information:
	            //     - Region
	            //     - Loop is conditioned for LS service or not
	            //     - Service Code from CLS-formatted Circuit ID
	            //     - Service Category
	            if (aSERVICE_CATEGORY != null
	            		&& aCLS_SERVICE_CODE != null
	            		&& oNotFoundRowWithServiceCodeAndModifierAndServiceCategory == true
	            		&& oNotFoundRowWithUsocAndServiceCategory == true)
	            {
	                aLfacsCopperServicesRow = aLfacsCopperServicesTable.retrieveRowByServiceCodeAndServiceCategory(aRegion,
	                																							   aLS_CONDITIONED,
	                																							   aCLS_SERVICE_CODE,
	                																							   aSERVICE_CATEGORY,
	                																							   aProperties,
	                																							   aUtility);

	                if (aLfacsCopperServicesRow != null)
	                {
	                    aServiceItemsArrayTemp.add(aLfacsCopperServicesRow);
	                    oNotFoundRowWithServiceCodeAndServiceCategory = false;
	                } 
	                else 
	                {
	                	oNotFoundRowWithServiceCodeAndServiceCategory = true;
	                }
	            }

	            if ((aCircuitID != null && aCircuitID.length() > 0 && !aCircuitID.toString().equalsIgnoreCase("NONE"))
		            	|| (aCircuitID2 != null && aCircuitID2.length() > 0 && !aCircuitID2.toString().equalsIgnoreCase("NONE"))
			            || (aCircuitID3 != null && aCircuitID3.length() > 0 && !aCircuitID3.toString().equalsIgnoreCase("NONE")))
            	{
            		// if CKID/CKID2/CKID3 is in WTN format
            		// - retrieve service using region, service code (null), modifier (null), USOC(*), and service type category
	            	// - retrieve service using region, service code (*), modifier (*), USOC (*), and service type category.
	            	// - retrieve service using region, service code (*), modifier (*), USOC (*), and service type category.
            		if (isNumeric(aCircuitID)
                		|| isNumeric(aCircuitID2)
                		|| isNumeric(aCircuitID3))
            		{
            			// retrieve the row from LFACS_COPPER_SERVICES table using the following information:
        	            //     - Region
            			//     - Loop is conditioned for LS service or not
        	            //     - Service Code from CLS-formatted Circuit ID
        	            //     - Modifier from CLS-formatted Circuit ID
        	            //	   - Service Category
        	            if (aSERVICE_CATEGORY != null
        		            && oNotFoundRowWithUsocAndServiceCategory == true
        	            	&& oNotFoundRowWithServiceCodeAndModifierAndServiceCategory == true
        	        		&& oNotFoundRowWithServiceCodeAndServiceCategory == true)
        	            {
        	            	aLfacsCopperServicesRow = aLfacsCopperServicesTable.retrieveRowByServiceCodeAndModifierAndServiceCategory(aRegion,
																																	  aLS_CONDITIONED,
																																	  "00",
																																	  "00",
																																	  aSERVICE_CATEGORY,
																																	  aProperties,
																																	  aUtility);

        	            	if (aLfacsCopperServicesRow != null)
        	            	{
        	            		aServiceItemsArrayTemp.add(aLfacsCopperServicesRow);
        	            		oNotFoundRowWithServiceCodeAndModifierAndServiceCategory = false;
        	            	} 
        	            	else 
        	            	{
        	            		oNotFoundRowWithServiceCodeAndModifierAndServiceCategory = true;
        	            	}
        	            }
        	            
        	            // retrieve the row from LFACS_COPPER_SERVICES table using the following information:
        	            //     - Region
        	            //     - Loop is conditioned for LS service or not
        	            //	   - Service Category
        	            if (aSERVICE_CATEGORY != null
        	            	&& oNotFoundRowWithUsocAndServiceCategory == true
        	            	&& oNotFoundRowWithServiceCodeAndModifierAndServiceCategory == true
        	        		&& oNotFoundRowWithServiceCodeAndServiceCategory == true)
        	            {
        	            	aLfacsCopperServicesRow = aLfacsCopperServicesTable.retrieveRowByServiceCategory(aRegion,
        	            																					 aLS_CONDITIONED,
        		            																				 aSERVICE_CATEGORY,
        		            																				 aProperties,
        		            																				 aUtility);
        	                if (aLfacsCopperServicesRow != null)
        	                {
        	                    aServiceItemsArrayTemp.add(aLfacsCopperServicesRow);
        	                    oNotFoundRowWithServiceCategory = false;
        	                    
        	                } 
        	                else 
        	                {
        	                	oNotFoundRowWithServiceCategory = true;
        	                }
        	            }        	            
            		}
            		// if CKID/CKID2/CKID3 is not in WTN format 
            		// - retrieve service using region, service code (*), modifier (*), USOC (*), and service type category.
            		// - set the Service Name to "Other" and the conflicting service indicator to "Y"
	            	else
	            	{
        	            // retrieve the row from LFACS_COPPER_SERVICES table using the following information:
        	            //     - Region
	            		//     - Loop is conditioned for LS service or not
        	            //	   - Service Category
        	            if (aSERVICE_CATEGORY != null
        	            	&& oNotFoundRowWithUsocAndServiceCategory == true
        	            	&& oNotFoundRowWithServiceCodeAndModifierAndServiceCategory == true
        	        		&& oNotFoundRowWithServiceCodeAndServiceCategory == true
        	        		&& oNotFoundRowWithServiceCategory == true)
        	            {
        	            	aLfacsCopperServicesRow = aLfacsCopperServicesTable.retrieveRowByServiceCategory(aRegion,
        	            																					 aLS_CONDITIONED,
        		            																				 aSERVICE_CATEGORY,
        		            																				 aProperties,
        		            																				 aUtility);
        	                if (aLfacsCopperServicesRow != null)
        	                {
        	                    aServiceItemsArrayTemp.add(aLfacsCopperServicesRow);
        	                    
        	                } 
        	            }        	            
        	            
        	            // set the Service Name to "Other" and the conflicting service indicator to "Y"
        	            if (!(aServiceItemsArrayTemp.size() > 0))
        	            {
        	            	aLfacsCopperServicesRow = new LfacsCopperServicesRow();
        	            	aLfacsCopperServicesRow.setSERVICE_NAME(PublishValidateFacilityNotificationConstants.SERVICE_NAME_OTHER);
        	            	aLfacsCopperServicesRow.setPROVISIONING_CONFLICT_IND("Y");
        	            	aLfacsCopperServicesRow.setNEGOTIATION_CONFLICT_IND("Y");
    		                aServiceItemsArrayTemp.add(aLfacsCopperServicesRow);
        	            }
	            	}
            	}

	            // build the service items
	            if (aServiceItemsArrayTemp.size() > 0)
	            {
	                LfacsCopperServicesRow[] LfacsCopperServicesRow = (LfacsCopperServicesRow[])aServiceItemsArrayTemp.toArray(new LfacsCopperServicesRow[aServiceItemsArrayTemp.size()]);
	                ArrayList aServicesList = new ArrayList();
	                ServiceItem aPriorityServiceItem = null;
	                String aConflictingServiceInd = null;
	                int priority_number = 0;
	                for (int i=0; i < LfacsCopperServicesRow.length; i++)
	                {
	                    String aServiceName = LfacsCopperServicesRow[i].getSERVICE_NAME();

	                    if (aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)
		                    || aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3))
	                    {
	                    	aConflictingServiceInd = LfacsCopperServicesRow[i].getNEGOTIATION_CONFLICT_IND();
	                    }
	                    else if (aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING))
	                    {
	                    	aConflictingServiceInd = LfacsCopperServicesRow[i].getPROVISIONING_CONFLICT_IND();
	                    }

                    	if (aConflictingServiceInd.equalsIgnoreCase("Y"))
                    	{
                    		aConflictingServiceIndicator = true;
                    	}

                    	// initialize service item properties / object property manager
	                    ObjectProperty[] aServiceItemProperties = null;
	                    // log
	                    aUtility.log(LogEventId.DEBUG_LEVEL_2, "Service Name : " + aServiceName);
	                    aUtility.log(LogEventId.DEBUG_LEVEL_2, "Conflict?    : " + aConflictingServiceIndicator);
	                    // If one of the services found is U-verse,
	                    //     - set aConflictingServiceIndicator to FALSE if the Service Order Number is equal to the value of aSOACServiceOrderNumber in the input
	                    //     - otherwise set to TRUE
	                    //     - save the pending service order due date in the response helper
	                    if (aConflictingServiceIndicator)
	                    {
	                        // if U-Verse, set conflicting service indicator to false if ServiceOrder Number provided by client is one
	                        //             of the service orders in the loop
	                        if (aServiceName.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_UVERSE)
	                            && aServiceOrderNumber != null
	                            && aServiceOrders != null)
	                        {
	                            for (int j=0; j < aServiceOrders.length; j++)
	                            {
	                                if (aServiceOrders[j].getORD() != null)
	                                {
	                                    String aSOOrderNumber = aServiceOrders[j].getORD().substring(0, aServiceOrders[j].getORD().length() - 1);
	                                    if (aSOOrderNumber.equalsIgnoreCase(aServiceOrderNumber))
	                                    {
	                                        aConflictingServiceIndicator = false;
	                                        aResponseHelper.setRecommendedDueDate_UVerseSO(aServiceOrders[j].getDD());
	                                        break;
	                                    }
	                                }
	                            }
	                        }
	                    }
	                    
	                    ServiceItem aServiceItem
	                        = new ServiceItem((StringOpt) IDLUtil.toOpt(StringOpt.class, aServiceName),
	                                           aConflictingServiceIndicator,
	                                           (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, aServiceItemProperties));

	                    if (((String) aProperties.get("PRIORITY_SERVICES_LIST")).indexOf(":" + aServiceName.toUpperCase() + ":") >= 0)
	                    {
	                    	if (aPriorityServiceItem == null && priority_number == 0)
		                    {
		                    	aPriorityServiceItem = aServiceItem;
		                    	priority_number = LfacsCopperServicesRow[i].getPRIORITY_NUMBER();
		                    }
	                    	else
	                    	{
	                    		if (priority_number > LfacsCopperServicesRow[i].getPRIORITY_NUMBER())
	                    		{
	                    			aPriorityServiceItem = aServiceItem;
			                    	priority_number = LfacsCopperServicesRow[i].getPRIORITY_NUMBER();
	                    		}
	                    	}
	                    }
	                    else
	                    {
	                    	if (!aServicesList.contains(aServiceItem.aServiceType.theValue().toUpperCase()))
	                    	{
	                    		aServiceItemsArray.add(aServiceItem);
		                    	aServicesList.add(aServiceItem.aServiceType.theValue().toUpperCase());
	                    	}
	                    }
	                }

	                if (aPriorityServiceItem != null)
            	    {
            	    	aServiceItemsArray.add(aPriorityServiceItem);
            	    }
	            }

	            if (aServiceItemsArray.size() > 0)
	            {
	            	aServiceItems = (ServiceItem[]) aServiceItemsArray.toArray(new ServiceItem[aServiceItemsArray.size()]);
	            }
	        }
	        
	        // Determine services for IPDSLAM
	        else if (!bIsFiberInPremise
	        		 && aNetworkType != null
	        		 && (((String) aProperties.get("LFACS_IPDSLAM_NTI_LIST")).indexOf(":" + aNetworkType.toUpperCase() + ":") >= 0))
	        {
	        	LfacsIpdslamServicesTable aLfacsIpdslamServicesTable = new LfacsIpdslamServicesTable();

	            // retrieve the row from LFACS_IPDSLAM_SERVICES table using the following information:
	            //     - Region
	            //     - USOC
	            //     - Service Category
	            if (aUSOC != null 	            	
	            	&& aSERVICE_CATEGORY != null)
	            {
	                aLfacsIpdslamServicesRow = aLfacsIpdslamServicesTable.retrieveRowByUsocAndServiceCategory(aRegion,
		                                                                                    				  aUSOC,		                                                                                    				  		
		                                                                                    				  aSERVICE_CATEGORY,
		                                                                                    				  aProperties,
		                                                                                    				  aUtility);
	                if (aLfacsIpdslamServicesRow != null)
	                {
	                    aServiceItemsArrayTemp.add(aLfacsIpdslamServicesRow);
	                    mNotFoundRowWithUsocAndServiceCategory = false;
	                    
	                } 
	                else 
	                {
	                	mNotFoundRowWithUsocAndServiceCategory = true;
	                }
	            }       
	            
	            // retrieve the row from LFACS_IPDSLAM_SERVICES table using the following information:
	            //     - Region
	            //     - Service Code from CLS-formatted Circuit ID
	            //     - Modifier from CLS-formatted Circuit ID
	     	    //     - NIF (Network Interface)
	            //	   - Service Category
	            if (aSERVICE_CATEGORY != null
	            	&& aCLS_MODIFIER != null	
	            	&& aCLS_SERVICE_CODE != null
	            	&& aLoopNIF != null
	            	&& mNotFoundRowWithUsocAndServiceCategory == true)
	            {
	            	aLfacsIpdslamServicesRow = aLfacsIpdslamServicesTable.retrieveRowByServiceCodeAndModifierAndNifAndServiceCategory(aRegion,
		            																												  aCLS_SERVICE_CODE,
		            																												  aCLS_MODIFIER,
		            																												  aLoopNIF,
		            																												  aSERVICE_CATEGORY,
		            																												  aProperties,
		            																												  aUtility);
	                if (aLfacsIpdslamServicesRow != null)
	                {
	                    aServiceItemsArrayTemp.add(aLfacsIpdslamServicesRow);
	                    mNotFoundRowWithServiceCodeAndModifierAndNifAndServiceCategory = false;
	                    
	                } 
	                else 
	                {
	                	mNotFoundRowWithServiceCodeAndModifierAndNifAndServiceCategory = true;
	                }
	            }
	            
	            // retrieve the row from LFACS_IPDSLAM_SERVICES table using the following information:
	            //     - Region
	            //     - Service Code from CLS-formatted Circuit ID
	            //     - Modifier from CLS-formatted Circuit ID
	            //	   - Service Category
	            if (aSERVICE_CATEGORY != null
	            	&& aCLS_MODIFIER != null	
		            && aCLS_SERVICE_CODE != null	
	        		&& mNotFoundRowWithServiceCodeAndModifierAndNifAndServiceCategory == true
	        		&& mNotFoundRowWithUsocAndServiceCategory == true)
	            {
	            	aLfacsIpdslamServicesRow = aLfacsIpdslamServicesTable.retrieveRowByServiceCodeAndModifierAndServiceCategory(aRegion,
		            																											aCLS_SERVICE_CODE,
		            																											aCLS_MODIFIER,		            																												  
		            																											aSERVICE_CATEGORY,
		            																											aProperties,
		            																											aUtility);
	                if (aLfacsIpdslamServicesRow != null)
	                {
	                    aServiceItemsArrayTemp.add(aLfacsIpdslamServicesRow);
	                    mNotFoundRowWithServiceCodeAndModifierAndServiceCategory = false;
	                } 
	                else 
	                {
	                	mNotFoundRowWithServiceCodeAndModifierAndServiceCategory = true;
	                }
	            }
	            
	            // retrieve the row from LFACS_IPDSLAM_SERVICES table using the following information:
	            //     - Region
	            //     - Service Code from CLS-formatted Circuit ID set to NULL
	            //     - Service Category
	            if (aSERVICE_CATEGORY != null
	            	&& aCLS_SERVICE_CODE != null
	            	&& mNotFoundRowWithServiceCodeAndModifierAndServiceCategory == true
	            	&& mNotFoundRowWithServiceCodeAndModifierAndNifAndServiceCategory == true
	        		&& mNotFoundRowWithUsocAndServiceCategory == true)
	            {
	            	aLfacsIpdslamServicesRow = aLfacsIpdslamServicesTable.retrieveRowByServiceCodeAndServiceCategory(aRegion,
	            																									 aCLS_SERVICE_CODE,
		                                                                                                        	 aSERVICE_CATEGORY,
		                                                                                                        	 aProperties,
		                                                                                                        	 aUtility);
	                if (aLfacsIpdslamServicesRow != null)
	                {
	                    aServiceItemsArrayTemp.add(aLfacsIpdslamServicesRow);
	                    mNotFoundRowWithServiceCodeAndServiceCategory = false;
	                } 
	                else 
	                {
	                	mNotFoundRowWithServiceCodeAndServiceCategory = true;
	                }
	            }
	            
	            if ((aCircuitID != null && aCircuitID.length() > 0 && !aCircuitID.toString().equalsIgnoreCase("NONE"))
	            	|| (aCircuitID2 != null && aCircuitID2.length() > 0 && !aCircuitID2.toString().equalsIgnoreCase("NONE"))
		            || (aCircuitID3 != null && aCircuitID3.length() > 0 && !aCircuitID3.toString().equalsIgnoreCase("NONE")))
            	{
            		// if CKID/CKID2/CKID3 is in WTN format
            		// - retrieve service using region, service code (null), modifier (null), USOC(*), NIF (*)  and service type category
	            	// - retrieve service using region, service code (*), modifier (*), USOC (*), NIF and service type category.
	            	// - retrieve service using region, service code (*), modifier (*), USOC (*), NIF (*) and service type category.
            		if (isNumeric(aCircuitID)
                    	|| isNumeric(aCircuitID2)
                    	|| isNumeric(aCircuitID3))
            		{
            			// retrieve the row from LFACS_IPDSLAM_SERVICES table using the following information:
        	            //     - Region
        	            //     - Service Code from CLS-formatted Circuit ID
        	            //     - Modifier from CLS-formatted Circuit ID
        	            //	   - Service Category
        	            if (aSERVICE_CATEGORY != null
        	            	&& mNotFoundRowWithServiceCodeAndModifierAndServiceCategory == true
        	            	&& mNotFoundRowWithServiceCodeAndModifierAndNifAndServiceCategory == true
        	        		&& mNotFoundRowWithUsocAndServiceCategory == true
        	        		&& mNotFoundRowWithServiceCodeAndServiceCategory == true)
        	            {
        	            	aLfacsIpdslamServicesRow = aLfacsIpdslamServicesTable.retrieveRowByServiceCodeAndModifierAndServiceCategory(aRegion,
        		            																											"00",
        		            																											"00",		            																												  
        		            																											aSERVICE_CATEGORY,
        		            																											aProperties,
        		            																											aUtility);
        	                if (aLfacsIpdslamServicesRow != null)
        	                {
        	                    aServiceItemsArrayTemp.add(aLfacsIpdslamServicesRow);
        	                    mNotFoundRowWithServiceCodeAndModifierAndServiceCategory = false;
        	                } 
        	                else 
        	                {
        	                	mNotFoundRowWithServiceCodeAndModifierAndServiceCategory = true;
        	                }
        	            }
        	            
        	            // retrieve the row from LFACS_IPDSLAM_SERVICES table using the following information:
        	            //     - Region
        	     	    //     - NIF (Network Interface)
        	            //	   - Service Category
        	            if (aLoopNIF != null
        	            	&& aSERVICE_CATEGORY != null
        	            	&& mNotFoundRowWithServiceCodeAndModifierAndServiceCategory == true
        	            	&& mNotFoundRowWithServiceCodeAndModifierAndNifAndServiceCategory == true
        	        		&& mNotFoundRowWithUsocAndServiceCategory == true
        	        		&& mNotFoundRowWithServiceCodeAndServiceCategory == true)
        	            {
        	            	aLfacsIpdslamServicesRow = aLfacsIpdslamServicesTable.retrieveRowByNifAndServiceCategory(aRegion,
        		            																						 aLoopNIF,
        		            																						 aSERVICE_CATEGORY,
        		            																						 aProperties,
        		            																						 aUtility);
        	                if (aLfacsIpdslamServicesRow != null)
        	                {
        	                    aServiceItemsArrayTemp.add(aLfacsIpdslamServicesRow);
        	                    mNotFoundRowWithNifAndServiceCategory = false;
        	                    
        	                } 
        	                else 
        	                {
        	                	mNotFoundRowWithNifAndServiceCategory = true;
        	                }
        	            }
        	            
        	            // retrieve the row from LFACS_IPDSLAM_SERVICES table using the following information:
        	            //     - Region
        	            //	   - Service Category
        	            if (aSERVICE_CATEGORY != null
        	            	&& mNotFoundRowWithServiceCodeAndModifierAndServiceCategory == true
        	            	&& mNotFoundRowWithServiceCodeAndModifierAndNifAndServiceCategory == true
        	        		&& mNotFoundRowWithUsocAndServiceCategory == true
        	        		&& mNotFoundRowWithServiceCodeAndServiceCategory == true
        	        		&& mNotFoundRowWithNifAndServiceCategory == true)
        	            {
        	            	aLfacsIpdslamServicesRow = aLfacsIpdslamServicesTable.retrieveRowByServiceCategory(aRegion,
        		            																				   aSERVICE_CATEGORY,
        		            																				   aProperties,
        		            																				   aUtility);
        	                if (aLfacsIpdslamServicesRow != null)
        	                {
        	                    aServiceItemsArrayTemp.add(aLfacsIpdslamServicesRow);
        	                } 
        	            }        	            
            		}
            		// if CKID/CKID2/CKID3 is not in WTN format 
            		// - retrieve service using region, service code (*), modifier (*), USOC (*), NIF, and service type category.
            		// - retrieve service using region, service code (*), modifier (*), USOC (*), NIF (*) and service type category.
            		// - set the Service Name to "Other" and the conflicting service indicator to "Y"
	            	else
	            	{
        	            // retrieve the row from LFACS_IPDSLAM_SERVICES table using the following information:
        	            //     - Region
        	     	    //     - NIF (Network Interface)
        	            //	   - Service Category
        	            if (aLoopNIF != null
        	            	&& aSERVICE_CATEGORY != null
        	            	&& mNotFoundRowWithServiceCodeAndModifierAndServiceCategory == true
        	            	&& mNotFoundRowWithServiceCodeAndModifierAndNifAndServiceCategory == true
        	        		&& mNotFoundRowWithUsocAndServiceCategory == true
        	        		&& mNotFoundRowWithServiceCodeAndServiceCategory == true)
        	            {
        	            	aLfacsIpdslamServicesRow = aLfacsIpdslamServicesTable.retrieveRowByNifAndServiceCategory(aRegion,
        		            																						 aLoopNIF,
        		            																						 aSERVICE_CATEGORY,
        		            																						 aProperties,
        		            																						 aUtility);
        	                if (aLfacsIpdslamServicesRow != null)
        	                {
        	                    aServiceItemsArrayTemp.add(aLfacsIpdslamServicesRow);
        	                    mNotFoundRowWithNifAndServiceCategory = false;
        	                    
        	                } 
        	                else 
        	                {
        	                	mNotFoundRowWithNifAndServiceCategory = true;
        	                }
        	            }
        	            
        	            // retrieve the row from LFACS_IPDSLAM_SERVICES table using the following information:
        	            //     - Region
        	            //	   - Service Category
        	            if (aSERVICE_CATEGORY != null
        	            	&& mNotFoundRowWithServiceCodeAndModifierAndServiceCategory == true
        	            	&& mNotFoundRowWithServiceCodeAndModifierAndNifAndServiceCategory == true
        	        		&& mNotFoundRowWithUsocAndServiceCategory == true
        	        		&& mNotFoundRowWithServiceCodeAndServiceCategory == true
        	        		&& mNotFoundRowWithNifAndServiceCategory == true)
        	            {
        	            	aLfacsIpdslamServicesRow = aLfacsIpdslamServicesTable.retrieveRowByServiceCategory(aRegion,
        		            																				   aSERVICE_CATEGORY,
        		            																				   aProperties,
        		            																				   aUtility);
        	                if (aLfacsIpdslamServicesRow != null)
        	                {
        	                    aServiceItemsArrayTemp.add(aLfacsIpdslamServicesRow);
        	                } 
        	            }
        	            
        	            // set the Service Name to "Other" and the conflicting service indicator to "Y"
        	            if (!(aServiceItemsArrayTemp.size() > 0))
        	            {
        	            	aLfacsIpdslamServicesRow = new LfacsIpdslamServicesRow();
                			aLfacsIpdslamServicesRow.setSERVICE_NAME(PublishValidateFacilityNotificationConstants.SERVICE_NAME_OTHER);
    	            		aLfacsIpdslamServicesRow.setPROVISIONING_CONFLICT_IND("Y");
    	            		aLfacsIpdslamServicesRow.setNEGOTIATION_CONFLICT_IND("Y");
    		                aServiceItemsArrayTemp.add(aLfacsIpdslamServicesRow);
        	            }
	            	}
            	}
	            
	            // build the service items
	            if (aServiceItemsArrayTemp.size() > 0)
	            {
	            	LfacsIpdslamServicesRow[] LfacsIpdslamServicesRow = (LfacsIpdslamServicesRow[])aServiceItemsArrayTemp.toArray(new LfacsIpdslamServicesRow[aServiceItemsArrayTemp.size()]);
	                ArrayList aServicesList = new ArrayList();
	                ServiceItem aPriorityServiceItem = null;
	                String aConflictingServiceInd = null;
	                int priority_number = 0;
	                for (int i=0; i < LfacsIpdslamServicesRow.length; i++)
	                {
	                    String aServiceName = LfacsIpdslamServicesRow[i].getSERVICE_NAME();

	                    if (aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)
		                    || aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3))
	                    {
	                    	aConflictingServiceInd = LfacsIpdslamServicesRow[i].getNEGOTIATION_CONFLICT_IND();
	                    }
	                    else if (aTransactionType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING))
	                    {
	                    	aConflictingServiceInd = LfacsIpdslamServicesRow[i].getPROVISIONING_CONFLICT_IND();
	                    }

                    	if (aConflictingServiceInd.equalsIgnoreCase("Y"))
                    	{
                    		aConflictingServiceIndicator = true;
                    	}

                    	// Set IPDSLAM proceed indicator
                    	if (LfacsIpdslamServicesRow[i].getOK_TO_PROCEED_IND().equalsIgnoreCase("N"))
                    	{
                    		aResponseHelper.setIPDSLAMProceedIndicator(false);
                    	}

                    	// initialize service item properties / object property manager
	                    ObjectProperty[] aServiceItemProperties = null;
	                    // log
	                    aUtility.log(LogEventId.DEBUG_LEVEL_2, "Service Name : " + aServiceName);
	                    aUtility.log(LogEventId.DEBUG_LEVEL_2, "Conflict?    : " + aConflictingServiceIndicator);
	                    // If one of the services found is U-verse,
	                    //     - set aConflictingServiceIndicator to FALSE if the Service Order Number is equal to the value of aSOACServiceOrderNumber in the input
	                    //     - otherwise set to TRUE
	                    //     - save the pending service order due date in the response helper
	                    if (aConflictingServiceIndicator)
	                    {
	                        // if U-Verse, set conflicting service indicator to false if ServiceOrder Number provided by client is one
	                        //             of the service orders in the loop
	                        if (aServiceName.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_UVERSE)
	                            && aServiceOrderNumber != null
	                            && aServiceOrders != null)
	                        {
	                            for (int j=0; j < aServiceOrders.length; j++)
	                            {
	                                if (aServiceOrders[j].getORD() != null)
	                                {
	                                    String aSOOrderNumber = aServiceOrders[j].getORD().substring(0, aServiceOrders[j].getORD().length() - 1);
	                                    if (aSOOrderNumber.equalsIgnoreCase(aServiceOrderNumber))
	                                    {
	                                        aConflictingServiceIndicator = false;
	                                        aResponseHelper.setRecommendedDueDate_UVerseSO(aServiceOrders[j].getDD());
	                                        break;
	                                    }
	                                }
	                            }
	                        }
	                    }
	                    
	                    ServiceItem aServiceItem
	                        = new ServiceItem((StringOpt) IDLUtil.toOpt(StringOpt.class, aServiceName),
	                                           aConflictingServiceIndicator,
	                                           (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, aServiceItemProperties));

	                    if (((String) aProperties.get("PRIORITY_SERVICES_LIST")).indexOf(":" + aServiceName.toUpperCase() + ":") >= 0)
	                    {
	                    	if (aPriorityServiceItem == null && priority_number == 0)
		                    {
		                    	aPriorityServiceItem = aServiceItem;
		                    	priority_number = LfacsIpdslamServicesRow[i].getPRIORITY_NUMBER();
		                    }
	                    	else
	                    	{
	                    		if (priority_number > LfacsIpdslamServicesRow[i].getPRIORITY_NUMBER())
	                    		{
	                    			aPriorityServiceItem = aServiceItem;
			                    	priority_number = LfacsIpdslamServicesRow[i].getPRIORITY_NUMBER();
	                    		}
	                    	}
	                    }
	                    else
	                    {
	                    	if (!aServicesList.contains(aServiceItem.aServiceType.theValue().toUpperCase()))
	                    	{
	                    		aServiceItemsArray.add(aServiceItem);
		                    	aServicesList.add(aServiceItem.aServiceType.theValue().toUpperCase());
	                    	}
	                    }
	                }

	                if (aPriorityServiceItem != null)
            	    {
            	    	aServiceItemsArray.add(aPriorityServiceItem);
            	    }
	            }

	            if (aServiceItemsArray.size() > 0)
	            {
	            	aServiceItems = (ServiceItem[]) aServiceItemsArray.toArray(new ServiceItem[aServiceItemsArray.size()]);
	            }
	        }
	    }
	    catch(SQLException sqle)
	    {
	        // log: exception message
	        StringBuffer sqleLogMessage = new StringBuffer();
	        sqleLogMessage.append("> SQL Exception: [ ");
	        sqleLogMessage.append(sqle.getMessage());
	        sqleLogMessage.append(" ]");
	        aUtility.log(LogEventId.ERROR, sqleLogMessage.toString());
	        throw new SQLException(sqleLogMessage.toString());
	    }
	    catch (Exception e)
	    {
	        // log: exception message
	        StringBuffer eLogMessage = new StringBuffer();
	        eLogMessage.append("Exception in buildServiceItems() " + e.getMessage());
	        aUtility.log(LogEventId.ERROR, eLogMessage.toString());
	        // throw: Exception
	        throw new Exception(eLogMessage.toString());
	    }
	    finally {
	        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
	    }
	    return aServiceItems;
    }    
    
    /**
     * Builds pending service orders.
     * 
     * @param ServiceOrder[] aServiceOrders
     * @param String         aPrimaryNpaNxx
     * @param EiaDate        aRecommendedDueDate
     * @return PendingServiceOrder[]
     * @exception Exception      : Exception.
     * 
     * @author Rene Duka
     */
    private PendingServiceOrder[] buildPendingServiceOrders(
        ServiceOrder[] aServiceOrders,
        String aPrimaryNpaNxx,
        EiaDate aRecommendedDueDate) 
        throws 
            Exception 
    {
        String aMethodName = "LFACS: buildPendingServiceOrders()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    
        PendingServiceOrder[] aPendingServiceOrders = null;
        ArrayList aPendingServiceOrdersArray = new ArrayList();
        Date aSODueDate = null;
        
        try 
        {
        	Calendar calN = Calendar.getInstance();
        	calN.add(Calendar.DATE, Integer.parseInt((String) aProperties.get("N_DAYS")));
        	Date dateN = new Date (calN.get(Calendar.YEAR),
		   			   			   calN.get(Calendar.MONTH) + 1,
		   			   			   calN.get(Calendar.DATE));
    		
            if (aServiceOrders != null)
            {
                for (int i=0; i < aServiceOrders.length; i++) 
                {
                	// convert SO due date into Date format
                    aSODueDate = new Date((int) aServiceOrders[i].getDD().aYear, 
                                               (int) aServiceOrders[i].getDD().aMonth, 
                                               (int) aServiceOrders[i].getDD().aDay);
                    
                    boolean bSkipPendingSO = false;
                    // ignore if SO Date is after the U-verse due date sent by the client
                    if (aRecommendedDueDate != null)
                    {
                        // covert U-Verse due date into Date format 
                        Date aUverseDueDate = new Date((int) aRecommendedDueDate.aYear, 
                                                       (int) aRecommendedDueDate.aMonth, 
                                                       (int) aRecommendedDueDate.aDay);
        
                        // compare dates
                        if (aSODueDate.after(aUverseDueDate))
                        {
                    		bSkipPendingSO = true;
                        }
                    }
                    else
                    {
                    	if (aSODueDate.after(dateN))
                    	{
                    		bSkipPendingSO = true;
                    	}
                    }
                    
                    if (!bSkipPendingSO)
                    {
                        // initialize
                        StringBuffer aWorkingTelephoneNumber = new StringBuffer();
                        StringBuffer aCircuitID = new StringBuffer();
                        StringBuffer aServiceCode = new StringBuffer();
                        StringBuffer aModifier = new StringBuffer();

                        // format WTN : CKID
                        checkCKIDs(aServiceOrders[i].getCKID(),
                                   aServiceOrders[i].getCKID2(),
                                   aServiceOrders[i].getCKID3(),
                                   aPrimaryNpaNxx,
                                   aWorkingTelephoneNumber,
                                   aCircuitID,
                                   aServiceCode,
                                   aModifier);
                        
                        String aCKID = null;
                        if (aCircuitID.length() > 0) 
                        {
                            aCKID = aCircuitID.toString();
                        }
                        
                    	if (aServiceOrders[i].getCKID() != null
                    		&& aServiceOrders[i].getCKID().equalsIgnoreCase("NONE")) 
                        {
                            aCKID = aServiceOrders[i].getCKID();
                        }

                        PendingServiceOrder aPendingServiceOrder 
                            = new PendingServiceOrder((StringOpt) IDLUtil.toOpt(StringOpt.class, new Character(aServiceOrders[i].getORD().charAt(0)).toString()),
                                                      (StringOpt) IDLUtil.toOpt(StringOpt.class, aServiceOrders[i].getORD().substring(1)),
                                                      (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, aServiceOrders[i].getDD()),
                                                      (StringOpt) IDLUtil.toOpt(StringOpt.class, aCKID));
                        
                        aPendingServiceOrdersArray.add(aPendingServiceOrder);
                    }
                }
        
                if (aPendingServiceOrdersArray.size() > 0) 
                {
                    aPendingServiceOrders = (PendingServiceOrder[]) aPendingServiceOrdersArray.toArray(new PendingServiceOrder[aPendingServiceOrdersArray.size()]);
                }
            }
        }
        catch (Exception e) 
        {
            // log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception in buildPendingServiceOrders() ");
            eLogMessage.append(e.getMessage());
            eLogMessage.append(" ]");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            // throw: Exception
            throw new Exception(eLogMessage.toString());
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
        return aPendingServiceOrders;
    }

    /**
     * Determines the pending service order with the latest due date.
     * 
     * @param ServiceOrder[] aServiceOrders
     * @param EiaDate        aRecommendedDueDate
     * @return ServiceOrder
     * @exception Exception      : Exception.
     * 
     * @author Rene Duka
     */
    private ServiceOrder determineLatestPendingServiceOrder(
        ServiceOrder[] aServiceOrders,
        EiaDate aRecommendedDueDate) 
        throws 
            Exception 
    {
        String aMethodName = "LFACS: determineLatestPendingServiceOrder()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // initialize
        ServiceOrder aServiceOrder = null;
        Date aServiceOrderDate = null;

        ServiceOrder aFinalServiceOrder = null;
        Date aFinalServiceOrderDate = null;
        try 
        {
        	Calendar calN = Calendar.getInstance();
    		calN.add(Calendar.DATE, Integer.parseInt((String) aProperties.get("N_DAYS")));
    		
    		Date dateN = new Date (calN.get(Calendar.YEAR),
					   			   calN.get(Calendar.MONTH) + 1,
					   			   calN.get(Calendar.DATE));
    		
            for (int i=0; i < aServiceOrders.length; i++) 
            {
                // retrieve the values of service order information from the loop
                aServiceOrder = aServiceOrders[i];
                aServiceOrderDate = new Date((int) aServiceOrders[i].getDD().aYear, 
                                             (int) aServiceOrders[i].getDD().aMonth, 
                                             (int) aServiceOrders[i].getDD().aDay);
                
                // initialize the flag
                boolean bSkipPendingSO = false;
                
                // ignore if SO Date is after the U-verse due date sent by the client
                if (aRecommendedDueDate != null)
                {
                    // covert U-Verse due date into Date format 
                    Date aUverseDueDate = new Date((int) aRecommendedDueDate.aYear, 
                                                   (int) aRecommendedDueDate.aMonth, 
                                                   (int) aRecommendedDueDate.aDay);
    
                    // compare dates
                    if (aServiceOrderDate.after(aUverseDueDate))
                    {
                		bSkipPendingSO = true;
                    }
                }
                else
                {
                	if (aServiceOrderDate.after(dateN))
                	{
                		bSkipPendingSO = true;
                	}
                }
                
                if (!bSkipPendingSO)
                {
                    // set to the value of the first service order
                    if (i==0) 
                    {
                        aFinalServiceOrder = aServiceOrder;
                        aFinalServiceOrderDate = aServiceOrderDate; 
                    }

                    // compare dates
                    if (aServiceOrderDate.after(aFinalServiceOrderDate) || aFinalServiceOrderDate == null) 
                    {
                        aFinalServiceOrder = aServiceOrder;
                        aFinalServiceOrderDate = aServiceOrderDate; 
                    }
                    else
                    {
                        if (!aServiceOrderDate.after(aFinalServiceOrderDate) 
                            && !aServiceOrderDate.before(aFinalServiceOrderDate)) 
                        {
                            aFinalServiceOrder = aServiceOrder;
                            aFinalServiceOrderDate = aServiceOrderDate; 
                        }
                    }
                }
            }
        }
        catch (Exception e) 
        {
            // log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception in determineLatestPendingServiceOrder() ");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            // throw: Exception
            throw new Exception(eLogMessage.toString());
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
        return aFinalServiceOrder;
    }
    
    /**
     * Prints loop information from LFACS.
     * @param INQFASGResponseHelper aResponseHelper
     * @author Rene Duka
     */
    private void printLoopsFromLFACS(INQFASGResponseHelper aResponseHelper) 
    {
        Loop[] aLoops = aResponseHelper.getLoops();
        for (int i=0; i < aLoops.length; i++) 
        {
            int aLoopNumber = i+1;
            aUtility.log(LogEventId.INFO_LEVEL_1, "Loop # " + aLoopNumber);
            aUtility.log(LogEventId.INFO_LEVEL_1, "CKID   ==> " + aLoops[i].getCKID());
            aUtility.log(LogEventId.INFO_LEVEL_1, "CKID2  ==> " + aLoops[i].getCKID2());            
            aUtility.log(LogEventId.INFO_LEVEL_1, "CKID3  ==> " + aLoops[i].getCKID3());
            aUtility.log(LogEventId.INFO_LEVEL_1, "DCAPR  ==> " + aLoops[i].getDCAPR());
            aUtility.log(LogEventId.INFO_LEVEL_1, "SRVTYP ==> " + aLoops[i].getSRVTYP());
            aUtility.log(LogEventId.INFO_LEVEL_1, "NIF    ==> " + aLoops[i].getNIF());
            aUtility.log(LogEventId.INFO_LEVEL_1, "TEA    ==> " + aLoops[i].getTEA());
            aUtility.log(LogEventId.INFO_LEVEL_1, "USOC   ==> " + aLoops[i].getUSOC());
            aUtility.log(LogEventId.INFO_LEVEL_1, "TID    ==> " + aLoops[i].getTID());            
            // log address type info
            if (aLoops[i].getAddressTypes() != null)
            {
                AddressType[] aAddressTypes = aLoops[i].getAddressTypes();
                for (int j=0; j < aAddressTypes.length; j++) 
                {
                    int aAddressTypeNumber = j+1;
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Loop # " + aLoopNumber + " : Address Type # " + aAddressTypeNumber);
                    aUtility.log(LogEventId.INFO_LEVEL_1, "ADDRNO ==> " + aAddressTypes[j].getADDRNO());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "BSTE   ==> " + aAddressTypes[j].getBSTE());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "TEA    ==> " + aAddressTypes[j].getTEA());
                }
            }
            // log segment info
            if (aLoops[i].getSegments() != null) 
            {
                Segment[] aSegments = aLoops[i].getSegments();
                for (int j=0; j < aSegments.length; j++) 
                {
                    int aSegmentNumber = j+1;
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Loop # " + aLoopNumber + " : Segment # " + aSegmentNumber);
                    aUtility.log(LogEventId.INFO_LEVEL_1, "COMM  ==> " + aSegments[j].getCOMM());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "COND  ==> " + aSegments[j].getCOND());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "MODEL ==> " + aSegments[j].getMODEL());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "LTS   ==> " + aSegments[j].getLTS());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "TEA   ==> " + aSegments[j].getTEA());                
                    aUtility.log(LogEventId.INFO_LEVEL_1, "TP    ==> " + aSegments[j].getTP());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "LT    ==> " + aSegments[j].getLT());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "SYSTP ==> " + aSegments[j].getSYSTP());
                }
            }
            // log pending service order info
            if (aLoops[i].getServiceOrders() != null) 
            {
                ServiceOrder[] aServiceOrders = aLoops[i].getServiceOrders();
                for (int k=0; k < aServiceOrders.length; k++) 
                {
                    int aServiceOrderNumber = k+1;
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Loop # " + aLoopNumber + " : Pending Service Order # " + aServiceOrderNumber);                
                    aUtility.log(LogEventId.INFO_LEVEL_1, "BSTE   ==> " + aServiceOrders[k].getADDR_BSTE());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "TEA    ==> " + aServiceOrders[k].getADDR_TEA());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "ADL    ==> " + aServiceOrders[k].getADL());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "CKID   ==> " + aServiceOrders[k].getCKID());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "CKID2  ==> " + aServiceOrders[k].getCKID2());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "CKID3  ==> " + aServiceOrders[k].getCKID3());                
                    aUtility.log(LogEventId.INFO_LEVEL_1, "DD     ==> " + new EiaDateBisHelper(aServiceOrders[k].getDD()).toString());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "ORD    ==> " + aServiceOrders[k].getORD());                
                    aUtility.log(LogEventId.INFO_LEVEL_1, "SRVTYP ==> " + aServiceOrders[k].getSRVTYP());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "NIF    ==> " + aServiceOrders[k].getNIF());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "TEA    ==> " + aServiceOrders[k].getTEA());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "STAT   ==> " + aServiceOrders[k].getSTAT());
                    aUtility.log(LogEventId.INFO_LEVEL_1, "USOC   ==> " + aServiceOrders[k].getUSOC());
                    // log segments in pending service order info
                    if (aServiceOrders[k].getSegments() != null)
                    {
                        Segment[] aSOSegments = aServiceOrders[k].getSegments();
                        for (int l=0; l < aSOSegments.length; l++) 
                        {
                            int aSOSegmentNumber = l+1;
                            aUtility.log(LogEventId.INFO_LEVEL_1, "Loop # " + aLoopNumber + " : Pending Service Order # " + aServiceOrderNumber + " : Segment # " + aSOSegmentNumber);                
                            aUtility.log(LogEventId.INFO_LEVEL_1, "COMM  ==> " + aSOSegments[l].getCOMM());
                            aUtility.log(LogEventId.INFO_LEVEL_1, "COND  ==> " + aSOSegments[l].getCOND());
                            aUtility.log(LogEventId.INFO_LEVEL_1, "MODEL ==> " + aSOSegments[l].getMODEL());
                            aUtility.log(LogEventId.INFO_LEVEL_1, "LTS   ==> " + aSOSegments[l].getLTS());
                            aUtility.log(LogEventId.INFO_LEVEL_1, "TEA   ==> " + aSOSegments[l].getTEA());                
                            aUtility.log(LogEventId.INFO_LEVEL_1, "TP    ==> " + aSOSegments[l].getTP());
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Builds the INQTERM request.
     * 
     * @param BisContext aContext
     * @param Loop aLoop  
     * @param PublishValidateFacilityNotificationResponseHelper aResponseHelper  
     * @return INQTERMImpl
     * 
     * @author Julius Sembrano
     */
    public INQTERMImpl buildINQTERMRequest(
    		BisContext aContext,
    		Loop aLoop,
    		PublishValidateFacilityNotificationResponseHelper aResponseHelper)
    {    	
        String aMethodName = "LFACS: buildINQTERMRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
    	INQTERMImpl aINQTERMRequest = new INQTERMImpl();
    	Segment[] aLoopSegments = null;
    	AddressType[] aAddressTypes = null;
    	ServiceOrder[] aServiceOrders = null;
    	Segment[] aServiceOrdersSegments = null;
    	AddressType[] aServiceOrdersAddressTypes = null;
    	String aTEA = null;
    	
    	if(aLoop != null)
    	{
    		aLoopSegments = aLoop.getSegments();
    		aAddressTypes = aLoop.getAddressTypes();
    		aServiceOrders = aLoop.getServiceOrders();
    		if(aServiceOrders != null)
    		{
    			aServiceOrdersSegments = aServiceOrders[0].getSegments();
    			aServiceOrdersAddressTypes = aServiceOrders[0].getAddressTypes();
    		}
    		
    		if(aLoopSegments != null)
    		{
    			aTEA = aLoopSegments[aLoopSegments.length - 1].getTEA();
    		}
    		if(aTEA == null && aAddressTypes != null)
    		{
    			aTEA = aAddressTypes[0].getTEA();
    		}
    		if(aTEA == null && aServiceOrdersSegments != null)
    		{
    			aTEA = aServiceOrdersSegments[aServiceOrdersSegments.length - 1].getTEA();
    		}
    		if(aTEA == null && aServiceOrdersAddressTypes != null)
    		{
    			aTEA = aServiceOrdersAddressTypes[0].getTEA();
    		}
    	}
        try
        {
        	INQTERMRequestHelper aRequestHelper = new INQTERMRequestHelper(aUtility,
        			                                                       aProperties,
        			                                                       aTEA);
        	
            com.sbc.eia.bis.embus.service.facsrc.InqTermRequest.impl.INQTERMTypeImpl.REQTypeImpl aRequest = aRequestHelper.getRequest();                                                                     
            aINQTERMRequest.setREQ(aRequest);

        }
        catch (Exception e) 
        {
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		e.getMessage(),
                    (String) aProperties.get("BIS_NAME").toString(),
                    aRequestHelper);

        }
        finally
        {
        	aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
    	return aINQTERMRequest;
    }

    /**
     * Sends the INQTERM request.
     * 
     * @param BisContext aContext
     * @param Loop aLoop    
     * @return INQTERMImpl
     * 
     * @author Julius Sembrano
     */
    public String sendINQTERMRequest(
    		BisContext aContext,
    		String aPrimaryNpaNxx,
    		INQTERMImpl aINQTERMRequest)
		    throws
		    InvalidData,
		    AccessDenied,
		    BusinessViolation,
		    SystemFailure,
		    NotImplemented,
		    ObjectNotFound,
		    DataNotFound
    {
        String aMethodName = "LFACS: sendINQTERMRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
 
        String aDestination = "";
        
    	String aINQTERMResponse = null;
    	aINQTERMResponse = SendRequestToFACSRC.sendINQTERMRequest(aINQTERMRequest, 
    			                                                 aUtility, 
    			                                                 aProperties, 
    			                                                 aContext, 
    			                                                 aFACSRCService, 
    			                                                 aDestination, 
    			                                                 aPrimaryNpaNxx);
    	
    	aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
    	return aINQTERMResponse;
    }
 
    /**
     * Parses the INQTERM response.
     * 
     * @param BisContext aContext
     * @param Loop aLoop    
     * @return INQTERMImpl
     * 
     * @author Julius Sembrano
     */
    public INQTERMResponseHelper parseINQTERMResponse(
    		String aFACSRCResponse,
    		PublishValidateFacilityNotificationResponseHelper aResponseHelper)
		    throws
		    InvalidData,
		    AccessDenied,
		    BusinessViolation,
		    SystemFailure,
		    NotImplemented,
		    ObjectNotFound,
		    DataNotFound 
    {
        String aMethodName = "LFACS: parseINQTERMResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
    	INQTERMResponseHelper aINQTERMResponseHelper = null;
    	
    	try
    	{
    		String aINQTERMResponse = aFACSRCResponse;
    		
    		aINQTERMResponseHelper = new INQTERMResponseHelper(aUtility, aProperties);
    		aINQTERMResponseHelper.parseResponse(aINQTERMResponse);
    	}
        catch (Exception e) 
        {
        	// set: exception code / message
            
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		e.getMessage(),
                   (String) aProperties.get("BIS_NAME").toString(),
                   aRequestHelper);
            // throw: System Failure Exception
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		                e.getMessage(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
    	finally
    	{
    	
    		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    	}
    	return aINQTERMResponseHelper;
    }  
}
