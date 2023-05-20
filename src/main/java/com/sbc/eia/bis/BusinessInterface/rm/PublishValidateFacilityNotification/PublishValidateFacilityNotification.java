//$Id: PublishValidateFacilityNotification.java,v 1.207 2009/09/10 17:47:33 lg4542 Exp $
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
//# 07/16/2007   Rene Duka             Creation.
//# 11/15/2007   Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 11/28/2007   Rene Duka             Defect 79174: DryLoop not identified in Publish as conflicting service.
//#                                                  Publish is incorreclty recommending ok to proceed.
//# 12/10/2007   Rene Duka             CR 16976: Handle SE region for identifying AT&T DSL.
//# 12/11/2007   Rene Duka             Defect 79970: Extra error message returned that conflicts with initial message.
//# 12/11/2007   Rene Duka             CR 16563: Additional requirements for Project Lightspeed - Release 6.0.
//# 12/12/2007   Rene Duka             Defect 79678: Invalid WTN sent, no response in sonic for lfac wtn not found.
//# 12/18/2007   Rene Duka             Defect 80252: Recommended Due Date issue.
//# 12/18/2007   Rene Duka             Defect 80485: Handle empty Identifer in the PropertyValues.
//# 12/27/2007   Rene Duka             Defect 80318: Gift Billing indicator not being picked up.
//# 12/31/2007   Rene Duka             Defect 80011: Dish USOC is not being picked up.
//# 12/31/2007   Rene Duka             Defect 80848: Not returning Internet Service Provider tag for USOC equal to BISX6.
//# 12/31/2007   Rene Duka             Defect 80850: Not returning Speed tag for USOC equal to BISX6 for SE region.
//# 12/31/2007   Rene Duka             Defect 80855: Not returning internet Service Provider tag when SMBIS returns a Product Name of SBC_DSL_BASIC.
//# 01/02/2008   Rene Duka             Defect 81020: DSL Auto Disconnect Indicator not set properly.
//# 01/03/2008   Rene Duka             Defect 81388: Recommended Due Date issue.
//# 01/03/2008   Rene Duka             Defect 81266: retrieveAccount() exception issue.
//# 01/07/2008   Rene Duka             Defect 81654: DSL Auto Disconnect Indicator not set to FALSE.
//# 01/08/2008   Rene Duka             Defect 82016: Multiple calls to SM BIS returns exception.
//# 01/08/2008   Rene Duka             Defect 82016: Multiple calls to SM BIS returns exception.
//# 01/08/2008   Rene Duka             Defect 81996: DSL Auto Disconnect Indicator returned twice.
//# 01/11/2008   Rene Duka             Defect 82282: DSL Auto Disconnect Indicator issue.
//# 01/17/2008   Rene Duka             Defect 83040: RMBIS is returning extra message to the client about Conflicting services exist.
//# 01/17/2008   Rene Duka             Defect 82903: RMBIS is not returning the correct message to the client.
//# 01/18/2008   Rene Duka             Defect 82903: Incorrect conflicting service exists messages sent back to client incorrectly.
//# 01/18/2008   Rene Duka             Defect 82956: Proceed Indicator not being set correctly.
//# 01/21/2008   Rene Duka             Defect 83191: Incorrect message sent back to client .
//# 01/24/2008   Rene Duka             Defect 83475: For an Address in LFACS that has 3 loops, one with nothing on it,
//#                                                  one with a DSL service and the other with a disconnect order,
//#                                                  Expecting a conflict on the second loop that DSL exists,
//#                                                  Instead RM BIS returns a No Conflict Ok to proceed response.
//# 01/25/2008   Shyamali Banerjee     RollBack for Defect 83475.
//# 01/27/2008   Rene Duka             Defect 83931: DSL Misuse Indicator not set to true.
//# 01/28/2008   Rene Duka             Defect 82780: NonPublished indicator missing or not set to true.
//# 01/31/2008   Rene Duka             Defect 84468: Exclude speed property in determining ATT-owned DSL.
//# 02/17/2008   Rene Duka             LS 7.
//# 02/21/2008   Jon Costa             LS7: updated call to RTID MESSAGES retrieveRow().
//# 02/21/2008   Shyamali Banerjee     LS7: Inserted call to INQOSP.
//# 02/26/2008   Jon Costa             DR: 86748, Corrected pVFN to pVFN2 MessageTag, fixed INQOSP.
//# 02/29/2008   Rene Duka             PR 21563615 (LS 6): Return loop information when SM is not available.
//# 02/29/2008   Rene Duka             PR 21510204 (LS 6): Look at both services and product subscription in determining the the value
//                                                         DSL Service Provider (ASI vs Non-ASI).
//# 03/04/2008   Rene Duka             PR 21512146 (LS 6): Set ProceedIndicator to true if fiber is found.
//# 03/06/2008   Jon Costa             DR87450: Primary RTID and Secondary RTID are the same in the response, added check of
//                                              primary RTID when populating the secondary RTID.
//# 03/07/2008   Rene Duka             Defect 87481: No messages returned for certain clients.
//# 03/07/2008   Rene Duka             Defect 87523: Set recommended due date to the u-verse due date sent by the client.
//# 03/09/2008   Rene Duka             Defect 87643: SC9 message not returned if U-verse found for FIRST/BBNMS.
//# 03/09/2008   Rene Duka             Defect 87646: Unexpected error in formatting recommended due date.
//# 03/10/2008   Jon Costa			   Defect 87582: SC1-0000 for FTTx-EGPON networkTypes.
//# 03/10/2008   Rene Duka             Defect 87592: U-Verse pending SO due date not returned as recommended due date.
//# 03/10/2008   Rene Duka             Defect 87641: Set BTN not found indicator to false if at least 1 SM account is found.
//# 03/11/2008   Jon Costa			   Defect 87792: Invalid address is not returning Sc5-0000 or sc1-0000.
//# 03/11/2008   Rene Duka             Defect 87841: Non-ATT owned DSL should be a conflict.
//# 03/13/2008   Rene Duka             LS 7: Log the subscription account return structure from SM.
//# 03/18/2008   Rene Duka             Defect 87857 and 87111: WTN not in prem indicator is set incorrectly to FALSE if found in LFACS.
//# 03/21/2008   Jon Costa			   Defect 88817: For ERRRSP from LFACS, return OVALS as the RTID source.
//# 03/25/2008   Rene Duka             Defect 88954: SC3A-0000 message not being returned properly.
//# 03/25/2008   Rene Duka             Defect 88905: SC3B-0000 message not being returned properly.
//# 03/25/2008   Rene Duka             Defect 88899: System-Failure in building responses for FTTN-BP.
//# 03/26/2008   Jon Costa			   Defect 88760: Per BPR: RecommendedDueDate = "The Latest Due Date of any Loop."
//# 03/27/2008   Jon Costa			   Defect 89061: When there are 2 loops,  RM BIS is returning 3 loops, reset falg to supress 3rd loop.
//# 03/31/2008   Jon Costa			   DR89457: For a network Type for FTTPIP, there should be not any recommended RTID.
//# 04/01/2008   Jon Costa			   DR89795: Added criteria for setting the autoDSLDisconnect TN.
//# 04/07/2008   Jon Costa			   CR18873: Set internet svc provider to ATTIS and DSL Svc Provider to ASI if there is no UNE1 & UNN1.
//# 04/07/2008   Rene Duka             Defect 90292: NonPublished indicator missing or not set to true.
//# 04/10/2008   Rene Duka             Defect 90790: CR 18873-UNN1 with incorrect OCN not handled properly.
//# 04/10/2008   Rene Duka             Defect 90802: CR 18873-SE not handled properly.
//# 04/28/2008   Jon Costa			   PR21969394: If FTTP networkType, over ride OVALS RTID source default to LFACS when LFACS successful. 
//# 05/14/2008   Jon Costa			   PR 22094289: SM BIS response too large to output to log and will bring down the logAgent.
//# 05/22/2008   Sriram Chevuturu	   LS 7:PR 22162695: JMSSelector for MobilityCSI
//# 05/30/2008   Jon Costa             DR96621: Corrected (R)GPON NTI types to set RTIDSource to LFACS when LFACS is successful and no RTID exists.
//# 06/13/2008   Lira Galsim           LS 8.
//# 07/10/2008   Jon Costa             PR22487342: Correct RTIDSource to be LFACS when we get loop info from LFACS for NTI(s) requested.
//# 08/08/2008   Lira Galsim           PR22487342: If client NTI is a fiber (FTTPIP/GPON/RGPON), RM BIS will not return a SC10-0000 message.
//# 08/08/2008   Lira Galsim           DR102300: RTID Source is incorrectly returned as OVALS. Source should be LFACS, when we get loop info from LFACS.
//# 08/08/2008   Lira Galsim           DR102313: If client NTI is FTTC-EGPON/FTTP-EGPON, RM BIS will not return a SC10-0000 message.
//# 08/14/2008   Lira Galsim           PR22573198: Add mapping of SM partial failure exception
//# 08/15/2008   Lira Galsim           PR22573198: Fixed mapping of SM partial failure exception.
//# 08/26/2008   Lira Galsim           DR103697: Fixed the duplicate return of AutoDSLDisconnectTelephoneNumbers.
//# 08/29/2008	 Souvik Paul		   CR 20389: Set OkToProceedIndicator to the value of Exception Proceed Indicator 
//# 09/04/2008   Vickie Ng		       LS 9.
//# 09/10/2008   Lira Galsim		   DR105817: Removed the attachment of SM partial exception in the response structure.
//# 09/16/2008   Shyamali Banerjee     CR 22040: Changed the way to identify SE ATTIS.
//# 09/19/2008   Shyamali Banerjee     Defect 106979: For SE region if InternetServiceProvider is Non-ATTIS then DSLServiceProvider is set to Non-ASI.
//# 09/22/2008   Shyamali Banerjee     Defect 106790: SC10 message returned to client when only one good LS capable loop is found for FTTN-BP.
//# 10/02/2008   Lira Galsim           PR23090698: Set DISH Service Indicator to TRUE for SE when USOC KST is found.
//# 10/22/2008   Lira Galsim           DR108918: SE DISH is not being recognized when USOC=KST** is found in inner ProductSubscription (services.ProductSubscriptions.ProductSubscriptions).
//# 10/22/2008   Lira Galsim           DR108934: SC6-0000 message is not being returned when DISH Service is found on the 1st loop.
//# 10/22/2008   Lira Galsim           DR110313: For a Service Address having 2 ATT DSL loops - 1st loop having DISH & 2nd loop without DISH, DISH Service indicator should be set to TRUE only for the 1st loop.
//# 10/28/2008   Rene Duka             PR23279095: Only 2 loops were returned when OMS asked to analyze 5 loops.
//# 11/20/2008   Lira Galsim           DR112021: WTNs from LFACS that matches with the WTNs from rSAFABAN should not appear on the loops with no BTN List.
//# 12/19/2008   Lira Galsim           Pending PR: Fixed the NullPointerException that triggered the SM BIS Failure Exception in SE region
//# 01/19/2009   Lira Galsim           PR23840402: When Left-Handed NPU is found in SW region, non-published indicator should be set to TRUE.
//# 02/02/2009	 Sheilla Lirio		   LS 10: Added CPSOS call
//# 02/03/2009   Julius Sembrano       LS 10
//# 02/10/2009   Lira Galsim           LS10: Modified formatProceedIndicator().
//# 02/25/2009   Lira Galsim		   DR122545: No response detail is being returned for Granite transactions.
//# 02/26/2009   Lira Galsim		   DR122765: RMBIS is failing and not responding when NPANXX is not found in SOAC_WIRE_CENTER table.
//# 03/02/2009   Julius Sembrano       DR122910: Response details are not being returned correctly
//# 03/03/2009   Lira Galsim		   DR123173: RM BIS is not returning "SC11-0000 : Network Transport Type is Invalid" for a network type of ABCD along with a valid network type in the request.
//# 03/06/2009	 Sheilla Lirio		   DR123358: RM BIS is calling CPSOS when there is no DSL on the Living Unit contrary to what is stated in the BPR.
//# 03/09/2009	 Sheilla Lirio		   DR123545: CPSOS is not being called when UNN1 FID is not present from SM BIS.
//# 03/11/2009   Lira Galsim		   DR123769: Granite testing to SM BIS returns RM-SystemFailure-00521 - Exception: [ Unexpected error encountered in retrieving accounts from SM.null ].
//# 03/13/2009   Lira Galsim		   DR124090: DSL Service Provider is not being set by RM BIS when UNN1 fid is found from SM BIS.
//# 03/14/2009   Julius Sembrano       DR123976: conflictingServiceIndicator is being set to true when  DSL is owned by AT&T and there is no UBAN, for ipdslam.
//# 03/16/2009	 Sheilla Lirio		   DR124291: Not returning DSL autoDisconnect indicator for Non-ATT DSL.
//# 03/18/2009   Lira Galsim		   DR124483: proceedIndicator not being returned correctly when CPSOS is called for a conflicting service DSL.
//# 03/18/2009   Julius Sembrano       DR124447: recommended due date is not being set to when there is an outward pending service order
//# 03/19/2009   Lira Galsim		   DR124539: RM BIS is Calling SM BIS looking for UniversalBillingAccountNumber, SM BIS returns UniveralBillingAccountNumber.
//# 03/21/2009   Lira Galsim           DR124662: RMBIS is not calling CPSOS for DSL service type, when Granite is the assignment control.
//# 03/24/2009   Lira Galsim           DR124770: For scenario with 2 WTNs from LFACS - the 1st WTN that was already processed in SM was again processed in CPSOS, resulting to duplicate return of DSL Service Provider, Internet Service Provider, and DSL Auto Disconnect Indicator on the 1st loop.
//# 03/24/2009   Julius Sembrano       DR124695: serviceType is not being returned for DSL, multiple tags are missing.
//# 03/25/2009   Lira Galsim		   DR122586: Set handicap indicator for SE region.
//# 03/25/2009   Sheilla Lirio         DR124940: when running address with no circut id the following is being returned for pVFPN.    <circuitId>NONE</circuitId>
//# 03/26/2009   Julius Sembrano       CR24671: If OrderActionType sent by the client is CHANGE and NTI is FTTNBP and UVERSE is found,
//#												the conflicting services indicator will be set to FALSE for this conflicting service
//# 03/26/2009   Lira Galsim		   CR24530: DR110624: KST DishIndicator parsing refinement. Only check the list of USOC not all KST* USOC. 	
//# 04/16/2009	 Sheilla Lirio		   CR 25554: RM to update call to CPSOS
//# 04/17/2009   Lira Galsim		   DR122586: [New requirement] For SE region, Handicap Indicator will be set to TRUE if the FLOATED_FID with a value of DPD is found under PropertyValueHandle.
//# 04/20/2009   Julius Sembrano       CR24678: RM BIS not to treat UNE-P/LWC as conflict
//# 04/27/2009	 Sheilla Lirio		   DR 128284: RM is returning a system failure 00521 when a WTN with a null value existing on a ADSL loop with cls fomat only.
//# 04/30/2009   Lira Galsim           DR129055: When UNN1 FID is found, DSL Service Provider and Internet Service Provider are set to null instead of ASI and ATTIS respectively.
//# 05/06/2009   Julius Sembrano       PR24729777: RMBIS did not pupulate DSLAutoDisconnectIndicator tag when DSL was identified in PVFN3. SC2 should not be sent.
//#                                                Renamed callCPSOS() method to checkDSLServiceProvider() to avoid confusion
//# 05/07/2009   Julius Sembrano       PR24740072: RMBIS returning SC9 and SC10 messages when INQOSP is showing 4 BBP Loops
//# 05/08/2009   Julius Sembrano       DR130064: RM is returning RM-SystemFailure-00521 with a "NULL" description
//# 05/09/2009   Julius Sembrano       PR24700828/DR128786: RM BIS returning empty loop w/ Conflicting Services Indicator set to FALSE when no PATH is found and not returning SC10 message when no SITE is found.
//# 05/14/2009   Julius Sembrano       CR 26281: RM BIS will not send exception messages to OMS
//# 05/20/2009   Lira Galsim           DR131633/PR24823669: If RM BIS cannot decide service type from Granite, Billing account structure will not be sent.
//                                     PR24829771: Recommended RTID should not be sent when there is a conflict on the premese.
//# 05/27/2009   Lira Galsim           DR132566: SC4A-0000 message should be returned for LFACS Fiber (BPON) when U-Verse or DryLoop is found.
//# 06/13/2009   Lira Galsim           DR134059: Duplicate processing of WTN resulting to multiple DSL Info returned.
//# 06/21/2009   Lira Galsim		   PR25016056: When client sends a combination of copper and ipdslam network types, the service items returned for both are whatever were set/retrieved last.
//# 07/14/2009   Lira Galsim		   PR25176019: For Granite scenarios that returns a facility loop, null pointer exceptions are being encountered.
//# 07/22/2009 	 Julius Sembrano       PR25176019: Granite flow, there is no loop info return when WTN is returned from Granite. 
//#									   Modified logic for setting GraniteUverseNotFound and SiteNotFound
//# 07/30/2009   Lira Galsim		   DR136676: SC4 is not being returned correctly if fiber is found in premise.
//# 08/07/2009   Julius Sembrano	   DR137179: For IPDSLAM NTIs, when DSL is ATT-owned and UBAN is not found, the Conflicting Service Indicator is being set to TRUE (should be FALSE).
//# 08/14/2009   Julius Sembrano	   DR137399: DLS disconnect is not getting triggered for self installation on HSIA at the back end --WUP00229080
//# 08/18/2009   Julius Sembrano	   DR137840: SC2 is not being returned w/ ATTDSL is present on IPDSLAM NTIs.
//# 08/25/2009   Julius Sembrano       DR138130: Conflict should be true for ISDN based on the IPDSLAM table
//# 09/10/2009   Lira Galsim           DR140584: If UNN1/UNE1 FID is not returned by SM, no default values will be set for DSL and Internet Service Provider. CPSOS will determine the DSL ownership. 

package com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.bccs.utility.soaphelpers.SoapParserHelper;
import com.sbc.eia.bis.BusinessInterface.rm.CPSOS.CPSOS;
import com.sbc.eia.bis.BusinessInterface.rm.CPSOS.CPSOSConstants;
import com.sbc.eia.bis.BusinessInterface.rm.CPSOS.DslAccountLookupResponse;
import com.sbc.eia.bis.BusinessInterface.rm.GRANITE.QNIRequestHelper;
import com.sbc.eia.bis.BusinessInterface.rm.GRANITE.QNIResponse;
import com.sbc.eia.bis.BusinessInterface.rm.LFACS.INQOSPResponseHelper;
import com.sbc.eia.bis.BusinessInterface.rm.LFACS.LFACS;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.TopListenerSoacLinkRow;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.TopListenerSoacLinkTable;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.RmNam.utilities.SmClient.SmClient;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.client.ClientService;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.NINQImpl;
import com.sbc.eia.bis.facades.rm.transactions.PublishValidateFacilityNotification.PublishValidateFacilityNotificationConstants;
import com.sbc.eia.bis.facades.rm.transactions.ValidateFacility.ValidateFacilityConstants;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.database.RtidMessagesRow;
import com.sbc.eia.bis.rm.database.RtidMessagesTable;
import com.sbc.eia.bis.rm.database.SoacWireCenterRow;
import com.sbc.eia.bis.rm.database.SoacWireCenterTable;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BisTypesObjectKeyKind;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.Identifier;
import com.sbc.eia.idl.bis_types.IdentifierType;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.PropertyValue;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.rm.PublishValidateFacilityNotification2Return;
import com.sbc.eia.idl.rm.PublishValidateFacilityNotification3Return;
import com.sbc.eia.idl.rm.publishValidateFacilityForProvisioningNotificationReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._publishValidateFacilityForProvisioningNotificationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishValidateFacilityForProvisioningNotificationResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishValidateFacilityNotification2Response;
import com.sbc.eia.idl.rm.RmFacadePackage._publishValidateFacilityNotification2ResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishValidateFacilityNotification3Response;
import com.sbc.eia.idl.rm.RmFacadePackage._publishValidateFacilityNotification3ResponseBISMsg;
import com.sbc.eia.idl.rm_ls_types.BillingAccount2;
import com.sbc.eia.idl.rm_ls_types.BillingAccount2SeqOpt;
import com.sbc.eia.idl.rm_ls_types.BillingAccount3;
import com.sbc.eia.idl.rm_ls_types.BillingAccount3SeqOpt;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2SeqOpt;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop3;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop3SeqOpt;
import com.sbc.eia.idl.rm_ls_types.Message;
import com.sbc.eia.idl.rm_ls_types.NetworkType2Values;
import com.sbc.eia.idl.rm_ls_types.NetworkType3Values;
import com.sbc.eia.idl.rm_ls_types.OrderAction2Opt;
import com.sbc.eia.idl.rm_ls_types.PendingServiceOrder;
import com.sbc.eia.idl.rm_ls_types.PendingServiceOrderSeqOpt;
import com.sbc.eia.idl.rm_ls_types.RelatedCircuitIDSourceValues;
import com.sbc.eia.idl.rm_ls_types.ResponseDetail2;
import com.sbc.eia.idl.rm_ls_types.ResponseDetail2SeqOpt;
import com.sbc.eia.idl.rm_ls_types.ResponseDetail3;
import com.sbc.eia.idl.rm_ls_types.ServiceItem;
import com.sbc.eia.idl.rm_ls_types.ServiceItemPropertyValues;
import com.sbc.eia.idl.rm_ls_types.ServiceItemSeqOpt;
import com.sbc.eia.idl.sm.SubscriptionAccountReturn;
import com.sbc.eia.idl.sm_types.Affiliate;
import com.sbc.eia.idl.sm_types.ProductSubscription;
import com.sbc.eia.idl.sm_types.Service;
import com.sbc.eia.idl.sm_types.SubscriptionAccount;
import com.sbc.eia.idl.sm_types.SubscriptionAccountInformationType;
import com.sbc.eia.idl.sm_types.SubscriptionAccountInformationTypeOpt;
import com.sbc.eia.idl.types.AttributeType;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.CompositeObjectKeyOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ExceptionDataSeqOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ObjectType;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.eia.idl.types.bishelpers.SeverityOptBisHelper;
import com.sbc.vicunalite.vaxb.VAXBDocumentWriter;

/**
 * Class      : PublishValidateFacilityNotification
 * Description: Business interface for pVFN transaction.
 *              - perform analysis by calling LFACS and SM BIS
 *              - log the XML input message
 *              - convert XML input message to java objects
 *              - log input
 *              - analyzes the message
 */
public class PublishValidateFacilityNotification
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;
    private LFACS aLFACSService = null;
    private SmClient aSMClient = null;
    private com.sbc.eia.idl.sm.SmFacade aSMBean = null;

    private PublishValidateFacilityNotificationRequestHelper aRequestHelper = null;
    private PublishValidateFacilityNotificationResponseHelper aResponseHelper = null;

    private PublishValidateFacilityNotificationResponseFTTN aResponseFTTN = null;
    private PublishValidateFacilityNotificationResponseFTTNBP aResponseFTTNBP = null;
    private PublishValidateFacilityNotificationResponseFTTP aResponseFTTP = null;
    private PublishValidateFacilityNotificationResponseIPCO aResponseIPCO = null;
    private PublishValidateFacilityNotificationResponseIPRT aResponseIPRT = null;
    private PublishValidateFacilityNotificationResponseNtis[] aResponseNtis = null;
    private PublishValidateFacilityNotificationResponseINVALIDNTI aResponseINVALIDNTI = null;

    private String aClient = null;
   
    /**
     * Constructor: PublishValidateFacilityNotification
     *
     * @param Utility   utility
     * @param Hashtable properties
     *
     * @author Rene Duka
     */
    public PublishValidateFacilityNotification (Utility utility, Hashtable properties) 
    {
        aProperties = properties;
        aUtility = utility;
    }

    /**
     * Analyzes the messages by calling LFACS and SM BIS.
     *
     * @param BisContext           aContext
     * @param BisContext           aClientBisContext
     * @param Location             aServiceLocation
     * @param StringOpt            aRelatedCircuitID
     * @param StringOpt            aWorkingTelephoneNumber
     * @param ShortOpt             aMaxPairsToAnalyze
     * @param StringOpt            aSOACServiceOrderNumber
     * @param StringOpt            aSOACServiceOrderNumberSuffix
     * @param EiaDateOpt           aUverseOrderDueDate,
     * @param ObjectType[]         aNtis
     * @param StringOpt            aOrderActionType
     * @param StringOpt            aSubActionType
     * @param ObjectPropertySeqOpt aObjectProperties
     * @exception Exception generic exception
     *
     * @author Rene Duka
     */
    public void analyzeMessage(
        BisContext aContext,
        BisContext aClientBisContext,
        Location aServiceLocation,
        StringOpt aRelatedCircuitID,
        StringOpt aWorkingTelephoneNumber,
        ShortOpt aMaxPairsToAnalyze,
        StringOpt aSOACServiceOrderNumber,
        StringOpt aSOACServiceOrderNumberSuffix,
        EiaDateOpt aUverseOrderDueDate,
        ObjectType[] aNtis,
        StringOpt aOrderActionType,
        StringOpt aSubActionType,
        OrderAction2Opt aOrderAction2,
        String aTransactionType,
        ObjectPropertySeqOpt aObjectProperties)
        throws
            Exception
    {
    	String aMethodName = "PublishValidateFacilityNotification: analyzeMessage()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // initialize response helpers
        aResponseFTTN = null;
        aResponseFTTNBP = null;
        aResponseFTTP = null; 
        aResponseIPCO = null;
        aResponseIPRT = null;
        aResponseNtis = null;
        
        // ************************************************************************************
        // instantiate request helper
        // ************************************************************************************
        aRequestHelper = new PublishValidateFacilityNotificationRequestHelper(aUtility,
                                                                              aProperties);
        // set aContext
        if(aContext != null)
        {
            aRequestHelper.setContext(aContext);
        }
        
        // set transaction type
        if(aTransactionType != null)
        {
        	aRequestHelper.setTransactionType(aTransactionType);
        }

        // set client
        aClient = determineClient(aClientBisContext);
        if (aClient != null)
        {
            aRequestHelper.setClient(aClient);
        }
        
        // Extract the JMSSelector Porperty Value if it is set
        aRequestHelper.setAJMSSelectorValue(aClientBisContext);
        
        // set list of network types from the client
        determineNetworkTypes(aNtis);
        
        // set NpaNxx and CLLI8 from the client
        StringBuffer aNpaNxx = new StringBuffer();
        StringBuffer aCLLI8 = new StringBuffer();
        Address aFacilityAddress = determineAddressInfo(aServiceLocation,
                                                        aNpaNxx,
                                                        aCLLI8);

        if (aFacilityAddress != null)
        {
            aRequestHelper.setFacilityAddress(aFacilityAddress);
        }

        if (aNpaNxx.length() > 0)
        {
            aRequestHelper.setNpaNxx(aNpaNxx.toString());
        }

        if (aCLLI8.length() > 0)
        {
            aRequestHelper.setCLLI8(aCLLI8.toString());
        }

        // set RTID from the client
        if (!OptHelper.isStringOptEmpty(aRelatedCircuitID))
        {
            aRequestHelper.setRTID(aRelatedCircuitID.theValue());
        }

        // set U-Verse order due date from the client
        if (!OptHelper.isEiaDateOptNull(aUverseOrderDueDate))
        {
            aRequestHelper.setUverseOrderDueDate(aUverseOrderDueDate.theValue());
        }

        // set working telephone number from the client
        if (!OptHelper.isStringOptEmpty(aWorkingTelephoneNumber))
        {
            aRequestHelper.setWorkingTelephoneNumber(aWorkingTelephoneNumber.theValue());
        }

        // set maximum pairs to analyze from the client
        if (!OptHelper.isShortOptEmpty(aMaxPairsToAnalyze))
        {
            aRequestHelper.setMaxPairsToAnalyze(aMaxPairsToAnalyze.theValue());
        }

        // set SOAC service order number from the client
        if (!OptHelper.isStringOptEmpty(aSOACServiceOrderNumber))
        {
            aRequestHelper.setSOACServiceOrderNumber(aSOACServiceOrderNumber.theValue());
        }

        // set SOAC service order number suffix from the client
        if (!OptHelper.isStringOptEmpty(aSOACServiceOrderNumberSuffix))
        {
            aRequestHelper.setSOACServiceOrderNumberSuffix(aSOACServiceOrderNumberSuffix.theValue());
        }
        
        if (!OptHelper.isOrderAction2OptNull(aOrderAction2)) 
        {
        	try {
        	aRequestHelper.setOrderActionType(aOrderAction2.theValue().aOrderActionType.theValue());
        	} catch (org.omg.CORBA.BAD_OPERATION e)  
            {
            	//don't need to do anything
            } 
            catch (NullPointerException e) 
            {
            	// don't need to do anything
            }
            try {
        	aRequestHelper.setSubActionType(aOrderAction2.theValue().aSubType.theValue());
            } catch (org.omg.CORBA.BAD_OPERATION e)  
            {
            	//don't need to do anything
            } 
            catch (NullPointerException e) 
            {
            	// don't need to do anything
            }
            try {
        	aRequestHelper.setOmsOrderId(aOrderAction2.theValue().aOrderId.theValue());
            } catch (org.omg.CORBA.BAD_OPERATION e)  
            {
            	//don't need to do anything
            } 
            catch (NullPointerException e) 
            {
            	// don't need to do anything
            }
        }

        // set order action type from the client
        else if (!OptHelper.isStringOptEmpty(aOrderActionType))
        {
            aRequestHelper.setOrderActionType(aOrderActionType.theValue());
        }
	
        // set sub action type from the client
        else if (!OptHelper.isStringOptEmpty(aSubActionType))
        {
            aRequestHelper.setSubActionType(aSubActionType.theValue());
        }
    
        // ************************************************************************************
        // instantiate response helper
        // ************************************************************************************
        aResponseHelper = new PublishValidateFacilityNotificationResponseHelper(aUtility,
                                                                                aProperties);
        // format billing accounts
        try
        {
            // detemine region using primary NPA NXX) and CLLI8 (if applicable)
            //     - if Region is not found, send error/exception that region is not found, unable to identify the wire center.
            String aRegion = determineRegion(aRequestHelper.getNpaNxx(), 
                                             aRequestHelper.getCLLI8());
            
            if (aRegion == null) 
            {
            	// set Partial LFACS Facilities Indicator to true
                aResponseHelper.setPartialAnalysisIndicator(true);
                // set Error in LFACS Indicator to true
                aResponseHelper.setErrorInLFACSIndicator(true);
            	
                // log: exception message            
            	String aMessage = "Data Error: [Region not found. Unable to identify wire center "
    	              + "aPrimaryNpaNxx: " + aRequestHelper.getNpaNxx()+ ", "
    	              + "aSbcServingOfficeWirecenter(CLLI8): " 
    	              + aRequestHelper.getCLLI8() + "].";
                aUtility.log(LogEventId.ERROR, aMessage);
                // set: exception code / message
                aResponseHelper.handleException(ExceptionCode.ERR_RM_INVALID_DATA,
                								aMessage,
                								(String) aProperties.get("BIS_NAME").toString(),
                								aRequestHelper);
                throw new Exception(aMessage);
            }
            else 
            {
                aUtility.log(LogEventId.INFO_LEVEL_1, "Region: [" + aRegion + "]");
                aRequestHelper.setRegion(aRegion);
            }  
       	    // initialize WTN list from LFACS and array list
        	ArrayList aWTNListFromLFACS = new ArrayList();
        	//Put Lfacs assigned NTI handle here
        	if (aRequestHelper.getLfacsNtiList()!= null
        		&& aRequestHelper.getLfacsNtiList().length > 0)
        	{          		
	        	// retrieve facility assignment information
	        	FacilityLoop2[] aFacilityLoops = null;
                
                try
                {
                    aFacilityLoops = retrieveFacilities(aContext, aRequestHelper, aResponseHelper);
                    aResponseHelper.setFacilityLoops(aFacilityLoops);
                }
                catch (Exception e)
                {
                    aUtility.log(LogEventId.FAILURE, "Error in retrieving LFACS data, continue if Granite data.");
                    if (aRequestHelper.getGraniteNtiList()== null)
                        throw e;
                }
	        	
    			// build the array lists by examining the loops from LFACS
	        	if (aResponseHelper.getFacilityLoops() != null)
	        	{
	        		examineLoopsFromLFACS(aWTNListFromLFACS);
	        	}
        	}
        	if (aRequestHelper.getGraniteNtiList()!= null
        			&& aRequestHelper.getGraniteNtiList().length > 0)
        	{	  
        		String aNpanxx = null;
        		try {
        		aNpanxx=aServiceLocation.aProviderLocationProperties[0].aPrimaryNpaNxx.theValue();
        		} catch (org.omg.CORBA.BAD_OPERATION e)  
                {
                	//don't need to do anything
                } 
                catch (NullPointerException e) 
                {
                	// don't need to do anything
                }
        		String aWireCenterClli = null;
        		try {
        		   aWireCenterClli=aServiceLocation.aProviderLocationProperties[0].aSbcServingOfficeWirecenter.theValue();
	        	} catch (org.omg.CORBA.BAD_OPERATION e)  
	            {
	            	//don't need to do anything
	            } 
	            catch (NullPointerException e) 
	            {
	            	// don't need to do anything
	            }
        		
        		try 
	      	    {       		
        			QNIResponse aQniResponse = new QNIRequestHelper(aUtility, aProperties).callQueryNetworkInventory(
	      	                    aContext, 
	      	                    aServiceLocation.aProviderLocationProperties[0].aServiceAddress.theValue(), 
	      	                    aNpanxx, 
	      	                    aWireCenterClli, 
	      	                    aRequestHelper,
	      	                    aResponseHelper); 
	      	              			
	      	        if (aQniResponse != null) 
	      	        {
	      	        	aResponseNtis = aQniResponse.getGraniteResponse(aRequestHelper.getGraniteNtiList(), 
												      	        		aRequestHelper.getOmsOrderId(), 
												      	        		aRequestHelper.getOrderActionType(), 
												      	        		aRequestHelper.getUverseOrderDueDate(), aUtility, aProperties);
	      	       
	      	        	//SetResponseDriver
	      	        	if (aResponseNtis != null)
	      	        	{
	      	        		ArrayList aResponseDriversList = new ArrayList();
	      	        		PublishValidateFacilityNotificationResponseDriver[] aResponseDrivers = null;
	      	        		FacilityLoop2 aFacilityLoop2 = new FacilityLoop2();
	      	        		
	      	        		for (int i = 0; i < aResponseNtis.length; i++)
	      	        		{
	      	        			aFacilityLoop2 = null;
	      	        			PublishValidateFacilityNotificationResponseDriver aResponseDriver = new PublishValidateFacilityNotificationResponseDriver(); 
	      	        			aResponseDriver.setNT(aResponseNtis[i].getANetworkType());
	      	        			aResponseDriver.setWTN(aResponseNtis[i].getAWTN());
	      	        			
                                if (aResponseNtis[i].getFacilityLoops() != null && aResponseNtis[i].getFacilityLoops().length >0)
                                {
                                	aFacilityLoop2 = aResponseNtis[i].getFacilityLoops()[0];
                                }
                                aResponseDriver.setFacilityLoop(aFacilityLoop2);
                                
                                aResponseDriversList.add(aResponseDriver);
                                
                                if (aFacilityLoop2 != null 
                                	&& OptHelper.isStringOptEmpty(aFacilityLoop2.aWorkingTelephoneNumber)) 
	      	        			{
	      	        				aResponseHelper.handleLoopWithNoBTN(aResponseNtis[i].getANetworkType(),
	      	        													aResponseNtis[i].getFacilityLoops()[0]);
	      	        			}
	      	        		}
	 	            
	      	        		// format response drivers
	      	        		if (aResponseDriversList.size() > 0) 
	      	        		{
	      	        			aResponseDrivers = (PublishValidateFacilityNotificationResponseDriver[]) aResponseDriversList.toArray(new PublishValidateFacilityNotificationResponseDriver[aResponseDriversList.size()]);
	      	        			aResponseHelper.setResponseDrivers(aResponseDrivers);
	      	        		}
	      	        	}
	      	        
	      	        	ArrayList aWTNListFromGranite = aQniResponse.getAWTNList();
	    				// Append two list and make sure there is no dupliate WTN   			
	      	        	if (aWTNListFromGranite != null && aWTNListFromGranite.size() > 0)
	      	        	{
	      	        		Iterator aIterator = aWTNListFromGranite.iterator();
	      	        		while(aIterator.hasNext()) 
		        			{
		        				String aTemp = (String)aIterator.next();
		        				if (!aWTNListFromLFACS.contains(aTemp)) 
		        				{
		        					aWTNListFromLFACS.add(aTemp);
		        					aRequestHelper.setWorkingTelephoneNumber(aTemp);
		        				} 
		        			}     			
	      	        	}    	    
	      	        }
	      	        if (aResponseNtis != null )
	      	        {
		      	        for (int i=0; i<aResponseNtis.length; i++ ) 
		      	        {
		      	        	aResponseHelper.setFacilityLoops(aResponseNtis[i].getFacilityLoops());
		      	        }
	      	        }//if there is ntis
	      	    }
        		catch(Throwable e)
        		{
        			aUtility.log(LogEventId.INFO_LEVEL_1, "Unexpected ERROR in analyzeMessage: [" + e.getMessage() + "]");
        			aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
					         e.getMessage(),
                           (String) aProperties.get("BIS_NAME").toString(),
                           aRequestHelper);
        		}
        	}
        	// check facility loops from LFACS and Granite
        	boolean aWTNfromClientFoundInLFACS = true;
        	if (aWTNListFromLFACS.size() > 0 )
	        {    			   			
        		// check WTNs from LFACS
    			//      - if no WTN is found in LFACS, SM BIS will not be called
    			//      - if WTN from client is not found in LFACS, SM BIS will not be called
				if (aRequestHelper.getWorkingTelephoneNumber() != null)
				{
					aWTNfromClientFoundInLFACS = checkWTN(aRequestHelper.getWorkingTelephoneNumber(),
                                                      	  aWTNListFromLFACS);
				}
				if (aWTNfromClientFoundInLFACS)
				{
					// retrieve account information
					BillingAccount2[] aAccounts = retrieveAccounts(aContext,
                                                               	   aWTNListFromLFACS);
					if (aAccounts != null)
					{
						aResponseHelper.setBillingAccounts(aAccounts);
					}
					// build response for each network type
					buildResponseByNetworkType();        					
				}
	        }
			else
			{
        	    if (aRequestHelper.getWorkingTelephoneNumber() != null)
        	    {
        		    // set the to WTN from client not found in LFACS indicator to false
        		    aWTNfromClientFoundInLFACS = false;
        	    }
        	    else
        	    {
        	    	// build response for each network type               	    	     		        	
					buildResponseByNetworkType();      		        
        	    }
			}
        
			// check if WTN found in LFACS
			if (!aWTNfromClientFoundInLFACS)
			{
				// WTN from client Not in Premise
				// set WTN from client Not in Premise indicator to true
				aResponseHelper.setWorkingTelephoneNumberNotInPremiseIndicator(true);
				// log: exception message
				String aMessage = "> Data Error: [WTN from client not found in LFACS.]";
				aUtility.log(LogEventId.ERROR, aMessage);
				// set: exception code / message
				
				aResponseHelper.handleException(ExceptionCode.ERR_RM_INVALID_DATA,
						         aMessage,
	                            (String) aProperties.get("BIS_NAME").toString(),
	                            aRequestHelper);
				
				// throw: exception
				throw new Exception(aMessage.toString());
			}

            // build response message
            String aXMLMessage = buildMessagesByNetworkType(aClientBisContext,
                                                            aRequestHelper,
                                                            aResponseHelper,
                                                            false);
            // publish message
            publishResponseMessage(aClient,
                                   aXMLMessage);
        }
        catch (BusinessViolation bve)
        {
            // log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "Business Violation Error in PublishValidateFacilityNotification: analyzeMessage()");
        }
        catch (ObjectNotFound onfe)
        {
            // log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "Object Not Found Error in PublishValidateFacilityNotification: analyzeMessage()");
        }
        catch (InvalidData ide)
        {
            // log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "Invalid Data Error in PublishValidateFacilityNotification: analyzeMessage()");
        }
        catch (AccessDenied ade)
        {
            // log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "Access Denied Error in PublishValidateFacilityNotification: analyzeMessage()");
        }
        catch (SystemFailure sfe)
        {
            // log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "System Failure Error in PublishValidateFacilityNotification: analyzeMessage()");
        }
        catch (DataNotFound dne)
        {
            // log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "Data Not Found Error in PublishValidateFacilityNotification: analyzeMessage()");
        }
        catch (NotImplemented nie)
        {
            // log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "Not Implemented Error in PublishValidateFacilityNotification: analyzeMessage()");
        }
        catch (Exception e)
        {
            // log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "Exception in PublishValidateFacilityNotification: analyzeMessage()");

            // check if error in LFACS and SM
            boolean bErrorIndicator = true;
            if (aResponseHelper.getErrorInSMIndicator())
            {
                bErrorIndicator = false;
            }
            
            // build response messages
            buildResponseByNetworkType();            
            String aXMLMessage = buildMessagesByNetworkType(aClientBisContext,
                                                            aRequestHelper,
                                                            aResponseHelper,
                                                            bErrorIndicator);

            // publish message with the exception
            publishResponseMessage(aClient,
                                   aXMLMessage);
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Retrieve and analyze loops from LFACS.
     * @param BisContext aContext
     * @param PublishValidateFacilityNotificationRequestHelper  aRequestHelper
     * @param PublishValidateFacilityNotificationResponseHelper aResponseHelper
     * @exception Exception generic exception
     * @return FacilityLoop2[]
     * @exception Exception generic exception
     * @author Rene Duka
     */
    private FacilityLoop2[] retrieveFacilities(
        BisContext aContext,
        PublishValidateFacilityNotificationRequestHelper aRequestHelper,
        PublishValidateFacilityNotificationResponseHelper aResponseHelper)
        throws
            Exception
    {
        String aMethodName = "PublishValidateFacilityNotification: retrieveFacilities()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        FacilityLoop2[] aLoops = null;
        try
        {
            if (aLFACSService == null)
            {
                aLFACSService = new LFACS(aUtility, aProperties);
            }
            aLoops = aLFACSService.publishValidateFacilityNotification(aContext,
                                                                       aRequestHelper,
                                                                       aResponseHelper);
        }
        catch (Exception e)
        {
            // log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "Exception in PublishValidateFacilityNotification: retrieveFacilities()");

            // set Partial LFACS Facilities Indicator to true
            aResponseHelper.setPartialAnalysisIndicator(true);
            // set Error in LFACS Indicator to true
            aResponseHelper.setErrorInLFACSIndicator(true);

            // log: exception message
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception: [ ");
            
            eLogMessage.append(e.getMessage());
            
            eLogMessage.append(" ]");
            aResponseHelper.handleException(ExceptionCode.ERR_RM_INVALID_DATA,
            		e.getMessage(),
                   (String) aProperties.get("BIS_NAME").toString(),
            aRequestHelper);
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());

            // throw: exception
           	throw new Exception(eLogMessage.toString());
        }
        finally
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
        return aLoops;
    }

    /**
     * Retrieves account information from SM BIS.
     * @param BisContext aContext
     * @param ArrayList  aWTNListFromLFACS
     * @return BillingAccount2[]
     * @exception Exception generic exception
     * @author Rene Duka
     */
    private BillingAccount2[] retrieveAccounts(
        BisContext aContext,
        ArrayList aWTNListFromLFACS)
        throws
            Exception
    {
        String aMethodName = "PublishValidateFacilityNotification: retrieveAccounts()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        boolean bBTNFound = true;
        BillingAccount2[] aAccounts = null;
        try
        {
            // access SM BIS
            accessSMClient(aContext);
            // initialize array lists
            ArrayList aBillingAccounts = new ArrayList();
            
            // process all WTNs from LFACS until all loops are accounted for
            Iterator aIterator = aWTNListFromLFACS.iterator();
            
            while (aIterator.hasNext())
            {
                String aWorkingTelephoneNumber = (String) aIterator.next();
                aUtility.log(LogEventId.INFO_LEVEL_1, "> Processing WTN: " + aWorkingTelephoneNumber);
                
                // skip if WTN is already processed
                if (aResponseHelper.getWTNListAlreadyProcessed().indexOf(aWorkingTelephoneNumber) == -1)
                {
                    // call SM:retrieveSubscriptionAccountsForService()
                    SubscriptionAccountReturn aRSAFSreturn =  null;
                    aRSAFSreturn = callRSAFS(aContext,
                                             aWorkingTelephoneNumber);
                    // extract BTN
                    if (aRSAFSreturn != null)
                    {
                        String aBillingTelephoneNumber = null;
                        aBillingTelephoneNumber = extractBTN(aContext,
                                                             aRSAFSreturn);
                        // check if BTN is found
                        if (aBillingTelephoneNumber != null)
                        {
                            // call SM:retrieveSubscriptionAccountsForAffiliatesByAccountNumber()
                            SubscriptionAccountReturn aRSAFABANreturn =  null;
                            aRSAFABANreturn = callRSAFABAN(aContext,
                                                           aBillingTelephoneNumber);
                            // extract subscription account
                            if (aRSAFABANreturn != null)
                            {
                                // extract account
                                BillingAccount2 aAccount = extractAccount(aContext,
                                                                          aRSAFABANreturn,
                                                                          aWTNListFromLFACS,
                                                                          aResponseHelper.getWTNListAlreadyProcessed());

                                // add the account to the Billing Account List if at least one of the loops with WTN from LFACS 
                                // is found in the rSAFABAN response 
                                if (!OptHelper.isFacilityLoop2SeqOptEmpty(aAccount.aFacilityLoops))
                                { 
                                	// add to aBillingAccounts array list 
                                    aBillingAccounts.add(aAccount); 
                                } 
                                
                                // add the loop to the No Billing Account List if the WTN from LFACS is not found in the rSAFABAN response
                                if (!aResponseHelper.getWTNListAlreadyProcessed().contains(aWorkingTelephoneNumber))
                                { 
                                	aResponseHelper.resetLoops();
                                	for(int y = 0; y < aRequestHelper.getValidNtiList().length; y++)
                                	{
		                                // retrieve network type from response drivers 
		                                String aNetworkType = aResponseHelper.retrieveNT(aWorkingTelephoneNumber); 
		
		                                // retrieve loop 
		                                FacilityLoop2 aFacilityLoop = aResponseHelper.getFacilityLoop(aWorkingTelephoneNumber);
		
		                                // handle loop with no BTN/WTN
		                                aResponseHelper.handleLoopWithNoBTN(aNetworkType, 
		                                                                    aFacilityLoop); 
                                	}
	                                
                                }
                            }
                            else
                            {
                                // SubscriptionAccount from SM:retrieveSubscriptionAccountsForAffiliatesByAccountNumber() is null
                                aUtility.log(LogEventId.INFO_LEVEL_2, "> The Subscription Account from SM:retrieveSubscriptionAccountsForAffiliatesByAccountNumber() is null.");
                                // set flag to false
                                bBTNFound = false;
                            }
                        }
                        else
                        {
                            // BTN from SM:retrieveSubscriptionAccountsForService() is null
                            aUtility.log(LogEventId.INFO_LEVEL_2, "> The BTN from SM:retrieveSubscriptionAccountsForService() is null.");
                            // set flag to false
                            bBTNFound = false;
                        }

                    }
                    else
                    {
                        // SubscriptionAccount from SM:retrieveSubscriptionAccountsForService() is null
                        aUtility.log(LogEventId.INFO_LEVEL_2, "> The Subscription Account from SM:retrieveSubscriptionAccountsForService() is null.");
                        // set flag to false
                        bBTNFound = false;
                    }                                     
                }
                else
                {
                    aUtility.log(LogEventId.DEBUG_LEVEL_1, "> WTN already processed.");
                }

                // check if BTN found for WTN
                if (!bBTNFound)
                {
                	aResponseHelper.resetLoops();
                	for(int x = 0; x < aRequestHelper.getValidNtiList().length; x++)
                	{
	                    // retrieve network type from response drivers
	                    String aNetworkType = aResponseHelper.retrieveNT(aWorkingTelephoneNumber);
	
	                    // retrieve loop
	                    FacilityLoop2 aFacilityLoop = aResponseHelper.getFacilityLoop(aWorkingTelephoneNumber);
	
	                    // handle loop with no BTN/WTN
	                    aResponseHelper.handleLoopWithNoBTN(aNetworkType,
	                                                        aFacilityLoop);
	                    bBTNFound = true; 
                	}
                }
            }           
            
            // format billing accounts
            if (aBillingAccounts.size() > 0)
            {
                aAccounts = (BillingAccount2[]) aBillingAccounts.toArray(new BillingAccount2[aBillingAccounts.size()]);
            }
        }
        catch (Exception e)
        {
        	// log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "Exception in PublishValidateFacilityNotification: retrieveAccounts()");

            // log: exception message
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception: [ Unexpected error encountered in retrieving accounts from SM."); 
            eLogMessage.append(e.getMessage());
            eLogMessage.append(" ]");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());

            // set: exception code / message
            // set: SM error indicator to true
            
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		eLogMessage.toString(),
                   (String) aProperties.get("BIS_NAME").toString(),
                   aRequestHelper);
            
            aResponseHelper.setErrorInSMIndicator(true);

            // handle loops
            aResponseHelper.handleLoopsWhenErrorInSM();

            // R9 Code: dont need this
            // build response for each network type
            // buildResponseByNetworkType();

            // throw: exception
            throw new Exception(eLogMessage.toString());
        }
        finally
        {
            aSMBean = null;
            aSMClient = null;
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }        
        return aAccounts;
    }

    /**
     * Publishes response message.
     *
     * @param String aClient
     * @param String aXMLMessage
     * @exception Exception generic exception
     *
     * @author Rene Duka
     */
    private void publishResponseMessage(
        String aClient,
        String aXMLMessage)
        throws
            Exception
    {
        String aMethodName = "PublishValidateFacilityNotification: publishResponseMessage()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        ClientService aService = null;
        try
        {
            if (aService == null)
            {
                aService = new ClientService(aProperties, aUtility);
            }
            // aService.setClient(String);
            aService.setClient(aClient);
            // log remote call
            aService.logREMOTE_CALL();
            // add the JMS property idetifier for vF
            Properties aMessageTags = new Properties();
            aMessageTags.put(ValidateFacilityConstants.JMS_propertyName,
                             ValidateFacilityConstants.JMS_propertyValue);
            // Add JMSSelector BisContext Property Key / Value as JMS Properties, If previously extracted. 
            if(aRequestHelper.isJMSSelectorTagPresent())
                aMessageTags.put(PublishValidateFacilityNotificationConstants.JMS_SELECTOR_TAG, aRequestHelper.getAJMSSelectorValue());

            // send message
            aService.publishMessage(aXMLMessage, aMessageTags);            
            // log remote return
            aService.logREMOTE_RETURN();
        }
        catch (ServiceException se)
        {
            // log: XML message not sent successfully
            logMessageNotSent(aXMLMessage);
            // log: service exception message
            StringBuffer aSELogMessage = new StringBuffer();
            aSELogMessage.append("Service Exception: [ ").append(se.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, aSELogMessage.toString());
            // throw: System Failure Exception
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                    "Exception message: " + se.getMessage(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        catch (Exception e)
        {
            // log: XML message not sent successfully
            logMessageNotSent(aXMLMessage);
            // log: exception message
            StringBuffer aELogMessage = new StringBuffer();
            aELogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, aELogMessage.toString());
            // throw: System Failure Exception
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                    "Exception message: " + e.getMessage(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        finally 
        {            
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
    }

    /**
     * Check working telephone number.
     *
     * @param String aWTNFromClient
     * @param ArrayList aWTNListFromLFACS
     * @return boolean
     *
     * @author Rene Duka
     */
    private boolean checkWTN(
        String aWTNFromClient,
        ArrayList aWTNListFromLFACS)
    {
        String aMethodName = "PublishValidateFacilityNotification: checkWTN()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // initialize return value to false
        boolean aCheckWTN = false;

        // check if WTN provided by client is one of WTNs returned from LFACS
        String[] aWTNListFromLFACSArray = (String[]) aWTNListFromLFACS.toArray(new String[aWTNListFromLFACS.size()]);
        for (int i=0 ; i < aWTNListFromLFACSArray.length ; i++)
        {
            if (aWTNFromClient.equalsIgnoreCase(aWTNListFromLFACSArray[i]))
            {
                aCheckWTN = true;
                break;
            }
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return aCheckWTN;
    }

    /**
     * Check if DSL is owned by AT&T.
     *
     * @param ObjectProperty[] aServiceItemProperties
     * @return boolean
     *
     * @author Rene Duka
     */
    private boolean checkDSLOwnedByATT(ObjectProperty[] aServiceItemProperties)
    {
        String aMethodName = "PublishValidateFacilityNotification: checkDSLOwnedByATT()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // initialize return value to false
        boolean aDSLOwnedByATT = false;

        // check if DSL is owned by AT&T
        ObjectPropertyManager aServiceItemOPM = new ObjectPropertyManager(aServiceItemProperties);
        
        String aDSLServiceProvider = aServiceItemOPM.getValue(ServiceItemPropertyValues.DSL_SERVICE_PROVIDER);
    	String aInternetServiceProvider = aServiceItemOPM.getValue(ServiceItemPropertyValues.INTERNET_SERVICE_PROVIDER);
        
    	if ((aServiceItemOPM.getValue(ServiceItemPropertyValues.DSL_SERVICE_PROVIDER) != null) 
    		 && (aServiceItemOPM.getValue(ServiceItemPropertyValues.INTERNET_SERVICE_PROVIDER) != null)
        	 && (aDSLServiceProvider.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_ASI))
        	 && (aInternetServiceProvider.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.INTERNET_SERVICE_PROVIDER_ATTIS)))
        {        	
    		aDSLOwnedByATT = true;                
        }
                
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return aDSLOwnedByATT;
    }

    /**
     * Build Granite NTI response.
     * @return void
     * @throws Exception
     * @author Hongmei Parkin
     */
    private void buildGraniteNtiResponse() 
    throws Exception
    {
    	String aMethodName = "PublishValidateFacilityNotification: buildGraniteNtiResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    	
    	try 
    	{  	
    		if (aResponseNtis != null)
    		{
    			for (int i = 0; i < aResponseNtis.length; i++)
	            {
					// retrieve the billing accounts for this network type
                    BillingAccount2[] aAccounts = null;
                    if (aResponseNtis[i].getFacilityLoops() != null)
                    {
                    	aResponseNtis[i].setLoops(aResponseNtis[i].getFacilityLoops());
                    	examineLoops(aResponseNtis[i].getANetworkType(), aResponseNtis[i]);
                        aAccounts = aResponseHelper.getBillingAccountsByNetworkType(aResponseNtis[i].getANetworkType(),
                                                                                    aResponseHelper.getLoopsWithNoBTN_Ntis());

                        // set the value of the account information
                        if (aAccounts != null)
                        {
                            aResponseNtis[i].setAccounts(aAccounts);
                            examineAccounts(aResponseNtis[i].getANetworkType(), aResponseNtis[i]);

                        }
                    }
	
					// format BTN not found indicator
					formatBTNnotFoundIndicator(aResponseNtis[i].getANetworkType(),
											   aResponseNtis[i],
											   aResponseHelper.getLoopsWithNoBTN_Ntis());				
					
					// examine properties if DSL is found
					// format auto DSL disconnect TNs
					if (aResponseHelper.getDSLFound())
					{
						examineLoopsWithDSL(aResponseNtis[i].getANetworkType(),
											aResponseNtis[i]);
					}
	
					// format messages
					formatMessages(aResponseNtis[i].getANetworkType(),
								   aResponseNtis[i],
								   aRequestHelper.getClient());
					
					if (aResponseNtis[i].getFacilityLoops() != null)
					{
						aResponseHelper.setLSConditionedLoopFound(true);
					}
					else
					{
						aResponseHelper.setLSConditionedLoopFound(false);
						aResponseNtis[i].setOkToProceedIndicator(true);
					}
	            }  
    		}
    	} 
    	catch (Exception e) 
    	{
    		//caller will handle the exception
    		throw e;
        }
	    finally
	    {
	        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
	    }
     }

    /**
     * Check if all DSL are on the same account.
     * @param String        aWTN
     * @param ServiceItem[] aServiceItems
     * @author Rene Duka
     */
    public boolean checkDSLOnSameAccount(
        BillingAccount2[] aAccounts)
    {
        String aMethodName = "PublishValidateFacilityNotification: checkDSLOnSameAccount()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        boolean aDSLOnSameAccount = true;
        int aAccountCounter = 0;
        if (aAccounts != null)
        {
            String aSavedBillingTelephoneNumber = "";
            for (int i=0 ; i < aAccounts.length ; i++)
            {
                if (!OptHelper.isFacilityLoop2SeqOptEmpty(aAccounts[i].aFacilityLoops))
                {
                    FacilityLoop2[] aLoops = aAccounts[i].aFacilityLoops.theValue();
                    for (int j=0 ; j < aLoops.length ; j++)
                    {
                        if (!OptHelper.isServiceItemSeqOptEmpty(aLoops[j].aServices))
                        {
                            ServiceItem[] aServiceItems = aLoops[j].aServices.theValue();
                            for (int k=0; k < aServiceItems.length; k++)
                            {
                                if (!OptHelper.isStringOptEmpty(aServiceItems[k].aServiceType))
                                {
                                    String aServiceType = aServiceItems[k].aServiceType.theValue();
                                    if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
                                    	|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
                                    	|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
                                    {
                                        if (!OptHelper.isObjectPropertySeqOptEmpty(aServiceItems[k].aServiceItemProperties))
                                        {
                                            ObjectProperty[] aServiceItemProperties = aServiceItems[k].aServiceItemProperties.theValue();
                                            boolean bATTOwnedByATT  = checkDSLOwnedByATT(aServiceItemProperties);
                                            if (!OptHelper.isStringOptEmpty(aAccounts[i].aBillingTelephoneNumber))
                                            {
                                                String aBTN = aAccounts[i].aBillingTelephoneNumber.theValue();
                                                if (bATTOwnedByATT
                                                    && (!aBTN.equals(aSavedBillingTelephoneNumber)))
                                                {
                                                    aAccountCounter++;
                                                    aSavedBillingTelephoneNumber = aAccounts[i].aBillingTelephoneNumber.theValue();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // return
        if (aAccountCounter > 1)
        {
            aDSLOnSameAccount = false;
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return aDSLOnSameAccount;
    }

    /**
     * Check if loop is conditioned for LS or not.
     * @param FacilityLoop2 aLoop
     * @return boolean
     * @author Rene Duka
     */
    private boolean checkLoopLSConditioned(FacilityLoop2 aLoop)
    {
        String aMethodName = "PublishValidateFacilityNotification: checkLoopLSConditioned()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // initialize return value to false
        boolean bLoopLSConditioned = false;

        // check if loop is conditioned for LS or not
        if (!OptHelper.isStringOptEmpty(aLoop.aCommitStatus)
             && !OptHelper.isStringOptEmpty(aLoop.aBroadbandPair))
        {
            if ((aLoop.aCommitStatus.theValue().equalsIgnoreCase("P") || aLoop.aCommitStatus.theValue().equalsIgnoreCase("S"))
                 && aLoop.aBroadbandPair.theValue().equalsIgnoreCase("BBP"))
            {
                bLoopLSConditioned = true;
            }
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return bLoopLSConditioned;
    }

    /**
     * Check if loop has a conflict.
     * @param FacilityLoop2 aLoop
     * @return boolean
     * @author Rene Duka
     */
    private boolean checkLoopHasConflict(FacilityLoop2 aLoop)
    {
        String aMethodName = "PublishValidateFacilityNotification: checkLoopHasConflict()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // initialize return value to false
        boolean bLoopHasConflict = false;

        // check if has a conflict or not
        if (!OptHelper.isServiceItemSeqOptEmpty(aLoop.aServices))
        {
            ServiceItem[] aServiceItems = aLoop.aServices.theValue();
            for (int i=0; i < aServiceItems.length; i++)
            {
                if (!OptHelper.isStringOptEmpty(aServiceItems[i].aServiceType))
                {
                    // retrieve the service type
                    String aServiceType = aServiceItems[i].aServiceType.theValue();
                    boolean bServiceConflictIndicator = aServiceItems[i].aConflictingServiceIndicator;
                    // set the value of conflicting service found indicator to TRUE if a conflicting service other than DSL is found
                    if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
                    	|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
                        || aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
                    {
                        if (!OptHelper.isObjectPropertySeqOptEmpty(aServiceItems[i].aServiceItemProperties))
                        {
                            ObjectProperty[] aServiceItemProperties = null;
                            ObjectPropertyManager aServiceItemOPM = new ObjectPropertyManager();
                            aServiceItemProperties = aServiceItems[i].aServiceItemProperties.theValue();
                            aServiceItemOPM = new ObjectPropertyManager(aServiceItemProperties);
                            if (!checkDSLOwnedByATT(aServiceItemProperties))
                            {
                                bLoopHasConflict = true;
                            }
                        }
                    }
                    else
                    {
                        if (bServiceConflictIndicator)
                        {
                            bLoopHasConflict = true;
                        }
                    }
                }
            }
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return bLoopHasConflict;
    }

    /**
     * Build response by network type.
     * @author Rene Duka
     */
    private void buildResponseByNetworkType()
        throws Exception
    {
        String aMethodName = "PublishValidateFacilityNotification: buildResponseByNetworkType()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        try
        {
            // If no facilities are found for any transport type, send the following:
            //    - Ok to Proceed  Indicator "true"
            //    - OVALS RTID as Recommended RTID
            //    - OVALS as RTID Source
            //    - Message:  “No Facilities Found for this Network Type”
        	if (aRequestHelper.getGraniteNtiList() != null &&
        		aRequestHelper.getGraniteNtiList().length > 0)
            {
        		buildGraniteNtiResponse();
            }
          
        	if (aRequestHelper.getLfacsNtiList() != null &&
        		aRequestHelper.getLfacsNtiList().length > 0)
            {
                String[] aNetworkTypes = aRequestHelper.getLfacsNtiList();
                for (int i = 0; i < aNetworkTypes.length; i++)
                {
                    String aNetworkType = aNetworkTypes[i];
                    // log: message
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Building response for Network Type " + aNetworkType);

                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    // Network Type : FTTN
                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    if (aNetworkType.equals(NetworkType2Values.FTTN))
                    {
                        // initialize response helper for FTTN network type
                        aResponseFTTN = new PublishValidateFacilityNotificationResponseFTTN(aUtility,
                                                                                            aProperties);

                        // reset the indicators
                        aResponseHelper.resetIndicators();

                        // retrieve the facility loops for this network type
                        FacilityLoop2[] aLoops = aResponseHelper.getFacilityLoopsByNetworkType(aNetworkType,
                                                                                               aResponseHelper.getFacilityLoopsNoBTN_FTTN());
                        
                        // set the value of the loop information and examine them
                        if (aLoops != null)
                        {
                            aResponseFTTN.setLoops(aLoops);
                            examineLoops(aNetworkType,
                                         aResponseFTTN);

                            // retrieve the billing accounts for this network type
                            BillingAccount2[] aAccounts = aResponseHelper.getBillingAccountsByNetworkType(aNetworkType,
                                                                                                          aResponseHelper.getFacilityLoopsNoBTN_FTTN());

                            // set the value of the account information
                            if (aAccounts != null)
                            {
                                aResponseFTTN.setAccounts(aAccounts);
                                examineAccounts(aNetworkType,
                                                aResponseFTTN);

                            }

                            // format BTN not found indicator
                            formatBTNnotFoundIndicator(aNetworkType,
                                                       aResponseFTTN,
                                                       aResponseHelper.getFacilityLoopsNoBTN_FTTN());

                            // examine properties if DSL is found
                            // format auto DSL disconnect TNs
                            if (aResponseHelper.getDSLFound())
                            {
                                examineLoopsWithDSL(aNetworkType,
                                                    aResponseFTTN);
                            }
                            
                            // format recommended RTID
                            formatRecommendedRTID(aNetworkType,
                                                  aResponseFTTN,
                                                  aRequestHelper.getWorkingTelephoneNumber());

                            // format secondary recommended RTIDs < Not Applicable >

                            // format terminal exhaust indicator < Not Applicalble >

                            // R9 Code: format Terminal Exhausted Indicator 
                            formatTerminalExhaustIndicator(aRequestHelper.getContext(), 
                                                           aNetworkType, 
                                                           aResponseFTTN); 

                            // format recommended due date
                            formatRecommendedDueDate(aNetworkType,
                                                     aResponseFTTN,
                                                     aResponseHelper.getFacilityLoopsWithRTID(),
                                                     aResponseHelper.getFacilityLoopsWithPendingSO(),
                                                     aRequestHelper.getUverseOrderDueDate());

                            // format proceed indicator
                            formatProceedIndicator(aNetworkType,
                                                   aResponseFTTN);
                        }
                        else
                        {
                        	aResponseHelper.setLSConditionedLoopFound(false);

                            aResponseFTTN.setOkToProceedIndicator(true);
                            aResponseFTTN.setRecommendedRTID(aRequestHelper.getRTID());
                        }
                        
                        // format RTID Source
                        formatRTIDSource(aNetworkType,
                        				 aResponseFTTN,
										 aResponseHelper.getErrorInLFACSIndicator());

                        // format messages
                        formatMessages(aNetworkType,
                                       aResponseFTTN,
                                       aRequestHelper.getClient());
                    }
                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    // Network Type : FTTNBP
                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    else if (aNetworkType.equals(NetworkType2Values.FTTNBP))
                    {
                        // initialize response helper for FTTN network type
                        aResponseFTTNBP = new PublishValidateFacilityNotificationResponseFTTNBP(aUtility,
                                                                                                aProperties);

                        // reset the indicators
                        aResponseHelper.resetIndicators();

                        // retrieve the facility loops using FTTNBP as a network type
                        FacilityLoop2[] aLoops = aResponseHelper.getFacilityLoopsByNetworkType(NetworkType2Values.FTTNBP,
                                                                                               aResponseHelper.getFacilityLoopsNoBTN_FTTN());

                        // set the value of the loop information
                        if (aLoops != null)
                        {
                            aResponseFTTNBP.setLoops(aLoops);
                            examineLoops(aNetworkType,
                                         aResponseFTTNBP);

                            // retrieve the billing accounts for this network type
                            BillingAccount2[] aAccounts = aResponseHelper.getBillingAccountsByNetworkType(NetworkType2Values.FTTNBP,
                                                                                                          aResponseHelper.getFacilityLoopsNoBTN_FTTN());
                            // set the value of the account information
                            if (aAccounts != null)
                            {
                                aResponseFTTNBP.setAccounts(aAccounts);
                                examineAccounts(aNetworkType,
                                                aResponseFTTNBP);
                            }

                            // format BTN not found indicator
                            formatBTNnotFoundIndicator(aNetworkType,
                                                       aResponseFTTNBP,
                                                       aResponseHelper.getFacilityLoopsNoBTN_FTTN());

                            // examine properties if DSL is found
                            // format auto DSL disconnect TNs
                            if (aResponseHelper.getDSLFound())
                            {
                                examineLoopsWithDSL(aNetworkType,
                                                    aResponseFTTNBP);
                            }
                            
                            // format recommended RTID
                            formatRecommendedRTID(aNetworkType,
                                                  aResponseFTTNBP,
                                                  aRequestHelper.getWorkingTelephoneNumber());

                            // format secondary recommended RTIDs
                            formatRecommendedSecondaryRTID(aNetworkType,
                                                           aResponseFTTNBP,
                                                           aRequestHelper.getWorkingTelephoneNumber(),
                                                           aRequestHelper.getRTID());
                            
                            // R9 determine terminal Commit 
                            determineTCommit(aRequestHelper.getContext(), 
                                                           aNetworkType); 


                            // format terminal exhaust indicator
                            formatTerminalExhaustIndicator(aRequestHelper.getContext(),
                                                           aNetworkType,
                                                           aResponseFTTNBP);

                            // format recommended due date
                            formatRecommendedDueDate(aNetworkType,
                                                     aResponseFTTNBP,
                                                     aResponseHelper.getFacilityLoopsWithRTID(),
                                                     aResponseHelper.getFacilityLoopsWithPendingSO(),
                                                     aRequestHelper.getUverseOrderDueDate());

                            // format proceed indicator
                            formatProceedIndicator(aNetworkType,
                                                   aResponseFTTNBP);
                        }
                        else
                        {
                        	aResponseHelper.setLSConditionedLoopFound(false);

                            aResponseFTTNBP.setOkToProceedIndicator(true);
                            aResponseFTTNBP.setRecommendedRTID(aRequestHelper.getRTID());
                        }
                        
                        // format RTID Source
                        formatRTIDSource(aNetworkType,
                        				 aResponseFTTNBP,
										 aResponseHelper.getErrorInLFACSIndicator());

                        // format messages
                        formatMessages(aNetworkType,
                                       aResponseFTTNBP,
                                       aRequestHelper.getClient());

                    }
                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    // Network Type : FTTP
                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    else if (aNetworkType.equals(NetworkType2Values.FTTP))
                    {
                        // initialize response helper for FTTP network type
                        aResponseFTTP = new PublishValidateFacilityNotificationResponseFTTP(aUtility,
                                                                                            aProperties);

                        // reset the indicators
                        aResponseHelper.resetIndicators();

                        // retrieve the facility loops for this network type
                        FacilityLoop2[] aLoops = aResponseHelper.getFacilityLoopsByNetworkType(aNetworkType,
                                                                                               aResponseHelper.getFacilityLoopsNoBTN_FTTP());
                        // set the value of the loop information
                        if (aLoops != null)
                        {
                            aResponseFTTP.setLoops(aLoops);
                            examineLoops(aNetworkType,
                                         aResponseFTTP);

                            // retrieve the billing accounts for this network type
                            BillingAccount2[] aAccounts = aResponseHelper.getBillingAccountsByNetworkType(aNetworkType,
                                                                                                          aResponseHelper.getFacilityLoopsNoBTN_FTTP());
                            // set the value of the account information
                            if (aAccounts != null)
                            {
                                aResponseFTTP.setAccounts(aAccounts);
                                examineAccounts(aNetworkType,
                                                aResponseFTTP);
                            }

                            // format BTN not found indicator
                            formatBTNnotFoundIndicator(aNetworkType,
                                                       aResponseFTTP,
                                                       aResponseHelper.getFacilityLoopsNoBTN_FTTP());

                            // examine properties if DSL is found
                            // format auto DSL disconnect TNs
                            if (aResponseHelper.getDSLFound())
                            {
                                examineLoopsWithDSL(aNetworkType,
                                                    aResponseFTTP);
                            }
                            
                            // format recommended RTID
                            formatRecommendedRTID(aNetworkType,
                                                  aResponseFTTP,
                                                  aRequestHelper.getWorkingTelephoneNumber());

                            // format secondary recommended RTIDs < Not Applicable >

                            // format terminal exhaust indicator < Not Applicalble >

                            // format recommended due date
                            formatRecommendedDueDate(aNetworkType,
                                                     aResponseFTTP,
                                                     aResponseHelper.getFacilityLoopsWithRTID(),
                                                     aResponseHelper.getFacilityLoopsWithPendingSO(),
                                                     aRequestHelper.getUverseOrderDueDate());

                            // format proceed indicator
                            formatProceedIndicator(aNetworkType,
                                                   aResponseFTTP);
                        }
                        else
                        {
                        	aResponseHelper.setLSConditionedLoopFound(false);

                            aResponseFTTP.setOkToProceedIndicator(true);
                            aResponseFTTP.setRecommendedRTID(aRequestHelper.getRTID());
                        }
                        
                        // format RTID Source
                        formatRTIDSource(aNetworkType,
                        				 aResponseFTTP,
										 aResponseHelper.getErrorInLFACSIndicator());

                        // format messages
                        formatMessages(aNetworkType,
                                       aResponseFTTP,
                                       aRequestHelper.getClient());
                    }
                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    // Network Type : IP-CO
                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    else if (aNetworkType.equalsIgnoreCase(NetworkType3Values.IPCO))
                    {
                        // initialize response helper for FTTC-EGPON networkType
                        aResponseIPCO = new PublishValidateFacilityNotificationResponseIPCO(aUtility, aProperties);
                        
                        // reset the indicators                        
                        aResponseHelper.resetIndicators();
                        
                        // retrieve the facility loops using FTTN as a network type
                        FacilityLoop2[] aLoops = aResponseHelper.getFacilityLoopsByNetworkType(NetworkType3Values.IPCO,
                        		                                                               aResponseHelper.getFacilityLoopsNoBTN_IPDSLAM());

                        // set the value of the loop information
                        if (aLoops != null)
                        {
                            aResponseIPCO.setLoops(aLoops);
                            examineLoops(aNetworkType,
                                         aResponseIPCO);

                            // retrieve the billing accounts for this network type
                            BillingAccount2[] aAccounts = aResponseHelper.getBillingAccountsByNetworkType(NetworkType3Values.IPCO,
                            		                                                                      aResponseHelper.getFacilityLoopsNoBTN_IPDSLAM());
                            // set the value of the account information
                            if (aAccounts != null)
                            {
                                aResponseIPCO.setAccounts(aAccounts);
                                examineAccounts(aNetworkType,
                                                aResponseIPCO);
                            }

                            // format BTN not found indicator
                            formatBTNnotFoundIndicator(aNetworkType,
                                                       aResponseIPCO,
                                                       aResponseHelper.getFacilityLoopsNoBTN_IPDSLAM());

                            // examine properties if DSL is found
                            // format auto DSL disconnect TNs
                            if (aResponseHelper.getDSLFound())
                            {
                                examineLoopsWithDSL(aNetworkType,
                                                    aResponseIPCO);
                            }
                            
                            // format recommended RTID
                            formatRecommendedRTIDIPDSLAM(NetworkType3Values.IPCO, aResponseIPCO);
                          
                            // format secondary recommended RTIDs < Not Applicable >

                            // format terminal exhaust indicator < Not Applicalble >

                            // format recommended due date
                            formatRecommendedDueDate(aNetworkType,
                            		                 aResponseIPCO,
                                                     aResponseHelper.getFacilityLoopsWithRTID(),
                                                     aResponseHelper.getFacilityLoopsWithPendingSO(),
                                                     aRequestHelper.getUverseOrderDueDate());

                            // format proceed indicator
                            formatProceedIndicator(aNetworkType,
                                                   aResponseIPCO);
                        }
                        else
                        {
                        	aResponseHelper.setLSConditionedLoopFound(false);

                            aResponseIPCO.setOkToProceedIndicator(true);
                            aResponseIPCO.setRecommendedRTID(aRequestHelper.getRTID());
                        }
                        
                        // format RTID Source
                        formatRTIDSource(aNetworkType,
                        				 aResponseIPCO,
										 aResponseHelper.getErrorInLFACSIndicator());

                        // format messages
                        formatMessages(aNetworkType,
                                       aResponseIPCO,
                                       aRequestHelper.getClient());
                    }
                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    // Network Type : IP-RT
                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    else if (aNetworkType.equalsIgnoreCase(NetworkType3Values.IPRT))
                    {   
                    	// initialize response helper for FTTC-EGPON networkType
                        aResponseIPRT = new PublishValidateFacilityNotificationResponseIPRT(aUtility, aProperties);
                        
                        // reset the indicators                        
                        aResponseHelper.resetIndicators();
                                                
                        // retrieve the facility loops using IPRT as a network type
                        FacilityLoop2[] aLoops = aResponseHelper.getFacilityLoopsByNetworkType(NetworkType3Values.IPRT,
                        		                                                               aResponseHelper.getFacilityLoopsNoBTN_IPDSLAM());

                        // set the value of the loop information
                        if (aLoops != null)
                        {
                            aResponseIPRT.setLoops(aLoops);
                            examineLoops(aNetworkType,
                                         aResponseIPRT);

                            // retrieve the billing accounts for this network type
                            BillingAccount2[] aAccounts = aResponseHelper.getBillingAccountsByNetworkType(NetworkType3Values.IPRT,
                            		                                                                      aResponseHelper.getFacilityLoopsNoBTN_IPDSLAM());
                            // set the value of the account information
                            if (aAccounts != null)
                            {
                                aResponseIPRT.setAccounts(aAccounts);
                                examineAccounts(aNetworkType,
                                                aResponseIPRT);
                            }

                            // format BTN not found indicator
                            formatBTNnotFoundIndicator(aNetworkType,
                                                       aResponseIPRT,
                                                       aResponseHelper.getFacilityLoopsNoBTN_IPDSLAM());

                            // examine properties if DSL is found
                            // format auto DSL disconnect TNs
                            if (aResponseHelper.getDSLFound())
                            {
                                examineLoopsWithDSL(aNetworkType,
                                                    aResponseIPRT);
                            }
                            
                            // format recommended RTID
                            formatRecommendedRTIDIPDSLAM(NetworkType3Values.IPRT, aResponseIPRT);

                            // format secondary recommended RTIDs < Not Applicable >

                            // format terminal exhaust indicator < Not Applicalble >

                            // format recommended due date
                            formatRecommendedDueDate(aNetworkType,
                            		                 aResponseIPRT,
                                                     aResponseHelper.getFacilityLoopsWithRTID(),
                                                     aResponseHelper.getFacilityLoopsWithPendingSO(),
                                                     aRequestHelper.getUverseOrderDueDate());

                            // format proceed indicator
                            formatProceedIndicator(aNetworkType,
                                                   aResponseIPRT);
                        }
                        else
                        {
                        	aResponseHelper.setLSConditionedLoopFound(false);

                            aResponseIPRT.setOkToProceedIndicator(true);
                            aResponseIPRT.setRecommendedRTID(aRequestHelper.getRTID());
                        }
                        
                        // format RTID Source
                        formatRTIDSource(aNetworkType,
                        				 aResponseIPRT,
										 aResponseHelper.getErrorInLFACSIndicator());

                        // format messages
                        formatMessages(aNetworkType,
                                       aResponseIPRT,
                                       aRequestHelper.getClient());
                    }                   
                }
            }
        	
            ///////////////////////////////////////////////////////////////////////////////////////////////////
            // Network Type : Invalid
            // RM BIS will send the following information:
            //    OK to Proceed  Indicator is “FALSE”,
            //    Message is “Network Transport Type is NOT Valid”,
            //    Recommended RTID to be set to OVALS RTID and
            //    “OVALS” as the RTID Source,
            //    and all other Response Structure Information is Blank.
            ///////////////////////////////////////////////////////////////////////////////////////////////////
        	if (aRequestHelper.getInvalidNtiList() != null &&
            	aRequestHelper.getInvalidNtiList().length > 0)
            {
        		String[] aNetworkTypes = aRequestHelper.getInvalidNtiList();
                for (int i = 0; i < aNetworkTypes.length; i++)
                {
                    String aNetworkType = aNetworkTypes[i];
                    // log: message
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Building response for Network Type " + aNetworkType);
                    
                    // initialize response helper for INVALID NTI networkType
                    aResponseINVALIDNTI = new PublishValidateFacilityNotificationResponseINVALIDNTI(aUtility, aProperties);
                    // reset the indicators, use defaults per requirements.
                    aResponseHelper.resetIndicators();
                    aResponseINVALIDNTI.setOkToProceedIndicator(false);
                    aResponseINVALIDNTI.setRecommendedRTID(aRequestHelper.getRTID());
                    aResponseINVALIDNTI.setRTIDSource(RelatedCircuitIDSourceValues.RTID_OVALS);

                    //  Format messages
                    formatMessages(aNetworkType,
                                   aResponseINVALIDNTI,
                                   aRequestHelper.getClient());
                    
                }
            }
        }
        catch (Exception e)
        {
            // log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "Exception in PublishValidateFacilityNotification: buildResponseByNetworkType()");

            // log: exception message
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("Exception: [ Unexpected error encountered in building responses.");
            eLogMessage.append(e.getMessage());
            eLogMessage.append(" ]");

            // set: exception code / message        
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            								eLogMessage.toString(),
            								(String) aProperties.get("BIS_NAME").toString(),
            								aRequestHelper);
            // throw: exception
            throw new Exception(eLogMessage.toString());
        }
        finally
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
    }

    /**
     * Formats a Granite response detail.
     * @param PublishValidateFacilityNotificationResponseNtis aResponseByNetworkType
     * @return ResponseDetail2
     * @author Hongmei Parkin
     */
    private ResponseDetail2 formatGraniteResponseDetail(
		   PublishValidateFacilityNotificationResponseNtis aResponseByNetworkType)
   {
	   String aMethodName = "PublishValidateFacilityNotification: formatGraniteResponseDetail()";
       aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

      //No recommended RTID for Granite NTIs < Not Applicalble >	
   	  //no aAutoDSLDisconnectTelephoneNumbers < Not Applicalble >  	 	
   	  //no aPartialAnalysisIndicator < Not Applicalble >  	
   	  //no aWorkingTelephoneNumberNotInPremiseIndicator < Not Applicalble >
      //no secondary recommended RTIDs < Not Applicable >		
      //no format terminal exhaust indicator < Not Applicable >
       
       BooleanOpt aTerminalExhaustIndicator = new BooleanOpt();
       aTerminalExhaustIndicator.__default();
       BooleanOpt aWTNnotInPremiseIndicator = new BooleanOpt();
       aWTNnotInPremiseIndicator.__default();
       // format terminal exhaust indicator < Not Applicalble >
       // initialize recommended RTID
       StringOpt aRecommendedRTID = new StringOpt();
       aRecommendedRTID.__default();
     
       // build response detail
       ResponseDetail2 aResponseDetail = new ResponseDetail2(aRequestHelper.getNetworkType(aResponseByNetworkType.getANetworkType()),
                                                             (BillingAccount2SeqOpt) IDLUtil.toOpt(BillingAccount2SeqOpt.class, aResponseByNetworkType.getAccounts()),
                                                             aResponseByNetworkType.getOkToProceedIndicator(),
                                                             aResponseHelper.getPartialAnalysisIndicator(),
                                                             aResponseByNetworkType.getBTNnotFoundIndicator(),
                                                             aResponseByNetworkType.getWTNnotInPremiseIndicator(),
                                                             "Granite",
                                                             aRecommendedRTID,
                                                             (StringSeqOpt) IDLUtil.toOpt(StringSeqOpt.class, null),
                                                             aTerminalExhaustIndicator,
                                                             (StringSeqOpt) IDLUtil.toOpt(StringSeqOpt.class, null),
                                                             (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, aResponseByNetworkType.getDueDate()),
                                                             aResponseByNetworkType.getMessages(),
                                                             (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, aResponseHelper.getObjectProperties()));

       aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
	   return aResponseDetail;
   }

	/**
     * Builds response message.
     * @param BisContext aClientBisContext
     * @param PublishValidateFacilityNotificationRequestHelper aRequestHelper
     * @param PublishValidateFacilityNotificationResponseHelper aResponseHelper
     * @param boolean aErrorIndicator
     * @return String
     * @exception Exception generic exception
     * @author Rene Duka
     */
    private String buildMessagesByNetworkType(
        BisContext aClientBisContext,
        PublishValidateFacilityNotificationRequestHelper aRequestHelper,
        PublishValidateFacilityNotificationResponseHelper aResponseHelper,
        boolean aErrorIndicator)
        throws
            Exception
    {
        String aMethodName = "PublishValidateFacilityNotification: buildMessagesByNetworkType()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        ResponseDetail2[] aResponseDetails = null;
        ArrayList aResponseDetailsList = new ArrayList();
        ResponseDetail2 aResponseDetail = null;
        String aNetworkType = null;
        String[] aNetworkTypes = null;
        
        // build response details
        if (aRequestHelper.getGraniteNtiList() != null) 
        {   
        	if (aResponseNtis != null)
        	{
        		for (int i = 0; i < aResponseNtis.length; i++)
        		{                 
        			aResponseDetail = formatGraniteResponseDetail(aResponseNtis[i]);
        			if (aResponseDetail != null)
        			{
        				aResponseDetailsList.add(aResponseDetail);
        			}
        		}
        	}
        }
        
        if (aRequestHelper.getLfacsNtiList() != null)
        {       
            aNetworkTypes = aRequestHelper.getLfacsNtiList();
            PublishValidateFacilityNotificationResponseIF thisResponse = null;

            for (int i = 0; i < aNetworkTypes.length; i++)
            {
                aNetworkType = aNetworkTypes[i];
                if (aNetworkType.equals(NetworkType2Values.FTTN))                       // Network Type: FTTN
                {
                    thisResponse = aResponseFTTN;
                }
                else if (aNetworkType.equals(NetworkType2Values.FTTNBP))                // Network Type: FTTN-BP
                {
                    thisResponse = aResponseFTTNBP;
                }
                else if (aNetworkType.equals(NetworkType2Values.FTTP))                  // Network Type: FTTPIP
                {
                    thisResponse = aResponseFTTP;
                }
               
                else if (aNetworkType.equals(NetworkType3Values.IPCO))                  // Network Type: IP-CO
                {
                    thisResponse = aResponseIPCO;
                }
                else if (aNetworkType.equals(NetworkType3Values.IPRT))                  // Network Type: FTTC-EGPON
                {
                    thisResponse = aResponseIPRT;
                }
                else                                                                    // Network Type is invalid
                {
                    thisResponse = aResponseINVALIDNTI;
                }
                aResponseDetail = null;
                if (!aErrorIndicator)
                {
                    // build response detail
                    aResponseDetail = formatResponseDetail(aNetworkType, thisResponse);
                }
                else
                {
                    // build an empty response detail
                    aResponseDetail = formatResponseDetailError(aNetworkType, thisResponse);
                }
                // add to array list
                if (aResponseDetail != null)
                {
                    aResponseDetailsList.add(aResponseDetail);
                }
            }// for each nti
         }//if lfacs list
        if (aRequestHelper.getInvalidNtiList() != null)
        {       
            aNetworkTypes = aRequestHelper.getInvalidNtiList();
            PublishValidateFacilityNotificationResponseIF thisResponse = null;

            for (int i = 0; i < aNetworkTypes.length; i++)
            {
                aNetworkType = aNetworkTypes[i];
                thisResponse = aResponseINVALIDNTI;

                aResponseDetail = null;
                if (!aErrorIndicator)
                {
                    // build response detail
                    aResponseDetail = formatResponseDetail(aNetworkType, thisResponse);
                }
                else
                {
                    // build an empty response detail
                    aResponseDetail = formatResponseDetailError(aNetworkType, thisResponse);
                }
                // add to array list
                if (aResponseDetail != null)
                {
                    aResponseDetailsList.add(aResponseDetail);
                }
            }// for each nti
         }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        // build the response messages
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        if (aResponseDetailsList != null &&
        		aResponseDetailsList.size() > 0)
        {
        	aResponseDetails = (ResponseDetail2[]) aResponseDetailsList.toArray(new ResponseDetail2[aResponseDetailsList.size()]);
        	if (aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3)
        		|| aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING)) 
        	{
        		buildResponseDetail3(aResponseDetails);
            }
        }

        // build xml message
        String aXMLMessage = null;
        try
        {
        	LinkedHashMap aMessageTags = new LinkedHashMap();
            
        	//Message header is sequence sensitive. please do not alter the sequence.
        	if (aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)) 
            { 
            	aMessageTags.put("embus:MessageTag", "PublishValidateFacilityNotification2");
            }
        	else if (aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3)) 
            {
            	aMessageTags.put("embus:MessageTag", "PublishValidateFacilityNotification3");
            }
        	else if (aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING) ) 
            {
            	aMessageTags.put("embus:MessageTag", "PublishValidateFacilityForProvisioningNotification");
            }
                    	
            aMessageTags.put("embus:ApplicationID", "ApplicationID");
            aMessageTags.put("embus:MessageID", "MessageID");
            aMessageTags.put("embus:ConversationKey", "ConversationKey");
            aMessageTags.put("embus:LoggingKey", "LoggingKey");
            aMessageTags.put("embus:ResponseMessageExpiration", "0");
            
            if (aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)) 
            { 
            	PublishValidateFacilityNotification2Return aPVFN_Return = new PublishValidateFacilityNotification2Return(aClientBisContext,
                         (ResponseDetail2SeqOpt) IDLUtil.toOpt(ResponseDetail2SeqOpt.class, aResponseDetails),
                         (ExceptionDataOpt) IDLUtil.toOpt(ExceptionDataOpt.class, aResponseHelper.getExceptionData()));

				_publishValidateFacilityNotification2Response aPVFN_Response = new _publishValidateFacilityNotification2Response();
				aPVFN_Response.aPublishValidateFacilityNotification2Return(aPVFN_Return);
				_publishValidateFacilityNotification2ResponseBISMsg aPVFN_ResponseBISMsg = new _publishValidateFacilityNotification2ResponseBISMsg(aPVFN_Response);
				aXMLMessage = VAXBDocumentWriter.encode(aPVFN_ResponseBISMsg);
				aXMLMessage = SoapParserHelper.appendEMBUSSoapEnvelope(SoapParserHelper.removeTagsFromXML(aXMLMessage,
				           "<vaxb:VAXB", "</vaxb:VAXB>"),
				           aMessageTags);   	
            }
            else if (aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3)) 
            {
            	PublishValidateFacilityNotification3Return aPVFN_Return = null;
            	boolean sendExceptionData = Boolean.valueOf((String)aProperties.get("EXCEPTION_DATA_SEND")).booleanValue();
            	
            	if(sendExceptionData)
            	{
            		aPVFN_Return = new PublishValidateFacilityNotification3Return(aClientBisContext,
            		aResponseHelper.getResponseDetail3(),
            		aResponseHelper.getExceptionDataList());
            	}
            	else
            	{
            		aPVFN_Return = new PublishValidateFacilityNotification3Return(aClientBisContext,
                    aResponseHelper.getResponseDetail3(),
                    (ExceptionDataSeqOpt) IDLUtil.toOpt(ExceptionDataSeqOpt.class, null));
            		logExceptionData();
            	}

				_publishValidateFacilityNotification3Response aPVFN_Response = new _publishValidateFacilityNotification3Response();
				aPVFN_Response.aPublishValidateFacilityNotification3Return(aPVFN_Return);
				_publishValidateFacilityNotification3ResponseBISMsg aPVFN_ResponseBISMsg = new _publishValidateFacilityNotification3ResponseBISMsg(aPVFN_Response);
				aXMLMessage = VAXBDocumentWriter.encode(aPVFN_ResponseBISMsg);
				aXMLMessage = SoapParserHelper.appendEMBUSSoapEnvelope(SoapParserHelper.removeTagsFromXML(aXMLMessage,
				           "<vaxb:VAXB", "</vaxb:VAXB>"),
				           aMessageTags);   	
            
            }
            else if (aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING) ) 
            {
            	publishValidateFacilityForProvisioningNotificationReturn aPVFN_Return = new publishValidateFacilityForProvisioningNotificationReturn(aClientBisContext,
                        aResponseHelper.getResponseDetail3(),
                        aResponseHelper.getExceptionDataList());
            	
				_publishValidateFacilityForProvisioningNotificationResponse aPVFN_Response = new _publishValidateFacilityForProvisioningNotificationResponse();
				aPVFN_Response.apublishValidateFacilityForProvisioningNotificationReturn(aPVFN_Return);
				_publishValidateFacilityForProvisioningNotificationResponseBISMsg aPVFN_ResponseBISMsg = new _publishValidateFacilityForProvisioningNotificationResponseBISMsg(aPVFN_Response);
				aXMLMessage = VAXBDocumentWriter.encode(aPVFN_ResponseBISMsg);
				aXMLMessage = SoapParserHelper.appendEMBUSSoapEnvelope(SoapParserHelper.removeTagsFromXML(aXMLMessage,
				           "<vaxb:VAXB", "</vaxb:VAXB>"),
				           aMessageTags);   	  
            }
            aUtility.log(LogEventId.INFO_LEVEL_1, "XML Message: [" + aXMLMessage + "]");
        }
        catch (IOException ioe)
        {
            // log: IO exception message
            StringBuffer aIOLogMessage = new StringBuffer();
            aIOLogMessage.append("IO Exception: [ ").append(ioe.getMessage()).append(" ]");
            aIOLogMessage.append("Conversion to XML from PublishValidateFacilityNotification object failure.");
            aUtility.log(LogEventId.ERROR, aIOLogMessage.toString());
            // handle: exception
            aResponseHelper.handleException(ExceptionCode.ERR_RM_IO_EXCEPTION,
                            aIOLogMessage.toString(),
                            (String) aProperties.get("BIS_NAME").toString(),
                            aRequestHelper);

            // build response messages
            String aMessage = buildMessagesByNetworkType(aClientBisContext,
                                                         aRequestHelper,
                                                         aResponseHelper,
                                                         true);

            // publish message with the exception
            publishResponseMessage(aClient,
                                   aMessage);

            // throw: IO Exception
            aUtility.throwException(ExceptionCode.ERR_RM_IO_EXCEPTION,
                                    "XML conversion produced an IOException: " + ioe.getMessage(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
        return aXMLMessage;
    }

	/**
     * Logs the ExceptionData.
     * @param 
     * @return void
     * @author Julius Sembrano
     */
    private void logExceptionData() 
    {
    	String aMethodName = "logExceptionData";
    	aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    	int exceptionCount = 0;
		if(!OptHelper.isExceptionDataSeqOptEmpty(aResponseHelper.getExceptionDataList()))
		{
			ExceptionData[] aExceptionData = aResponseHelper.getExceptionDataList().theValue();
			for(int i = 0; i < aExceptionData.length; i++)
			{
				exceptionCount = i+1;
				aUtility.log(
						LogEventId.INFO_LEVEL_1, 
						"ExceptionData ["+ exceptionCount +"] " + 
						" <Code> "+ aExceptionData[i].aCode +
						" <Description> " + aExceptionData[i].aDescription +
						" <Origination> " + aExceptionData[i].aOrigination.theValue() +
						" <Severity> " + (SeverityOptBisHelper.toString(aExceptionData[i].aSeverity).equalsIgnoreCase("0")?"Recoverable":"UnRecoverable"));
			}
		}
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);		
	}

	/**
     * Formats a response detail.
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @return ResponseDetail2
     * @author Rene Duka
     */
    private ResponseDetail2 formatResponseDetail(
        String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType)
    {
        String aMethodName = "PublishValidateFacilityNotification: formatResponseDetail()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // format terminal exhaust indicator
        BooleanOpt aTerminalExhaustIndicator = new BooleanOpt();
        aTerminalExhaustIndicator.theValue(aResponseByNetworkType.getTerminalExhaustIndicator());

        // initialize recommended RTID
        StringOpt aRecommendedRTID = new StringOpt();
        aRecommendedRTID.__default();

        StringBuffer aRTID = new StringBuffer();
        if (aResponseByNetworkType.getRecommendedRTID() != null)
        {
            if (aResponseByNetworkType.getRecommendedRTID().equalsIgnoreCase("blank"))
            {
                aRTID.append("");
            }
            else
            {
                aRTID.append(aResponseByNetworkType.getRecommendedRTID());
            }
            aRecommendedRTID.theValue(aRTID.toString());
        }

        // build response detail
        ResponseDetail2 aResponseDetail = new ResponseDetail2(aRequestHelper.getNetworkType(aNetworkType),
                                                              (BillingAccount2SeqOpt) IDLUtil.toOpt(BillingAccount2SeqOpt.class, aResponseByNetworkType.getAccounts()),
                                                              aResponseByNetworkType.getOkToProceedIndicator(),
                                                              aResponseHelper.getPartialAnalysisIndicator(),
                                                              aResponseByNetworkType.getBTNnotFoundIndicator(),
                                                              aResponseByNetworkType.getWTNnotInPremiseIndicator(),
                                                              aResponseByNetworkType.getRTIDSource(),
                                                              aRecommendedRTID,
                                                              (StringSeqOpt) IDLUtil.toOpt(StringSeqOpt.class, aResponseByNetworkType.getRecommendedSecondaryRTID()),
                                                              aTerminalExhaustIndicator,
                                                              (StringSeqOpt) IDLUtil.toOpt(StringSeqOpt.class, aResponseByNetworkType.getAutoDSLDisconnectTelephoneNumbers()),
                                                              (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, aResponseByNetworkType.getDueDate()),
                                                              aResponseByNetworkType.getMessages(),
                                                              (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, aResponseHelper.getObjectProperties()));

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return aResponseDetail;
    }

    /**
     * Formats an empty response message.
     * @param String aNetworkType
     * @return ResponseDetail2
     * @author Rene Duka
     */
    private ResponseDetail2 formatResponseDetailError(String aNetworkType,
                                                      PublishValidateFacilityNotificationResponseIF aResponseByNetworkType)
    {
        String aMethodName = "PublishValidateFacilityNotification: formatResponseDetailError()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // format RTID source and recommended RTID, if it's an LFACS error, refer the client to OVALS for RTID
        String aRTIDSource = aResponseHelper.getErrorInLFACSIndicator() ? aResponseByNetworkType.getRTIDSource() : "";
        String aRecommendedRTID = aResponseHelper.getErrorInLFACSIndicator() ? aRequestHelper.getRTID() : null;
        
        // format an empty message
        Message[] aMessages = aResponseByNetworkType.getMessages() != null ? 
                              aResponseByNetworkType.getMessages() : 
                              new Message[] {new Message("", "", "")};

        boolean okToProceedIndicator = aResponseByNetworkType != null ? aResponseByNetworkType.getOkToProceedIndicator() : false;
        boolean partialAnalysisIndicator = aResponseHelper != null ? aResponseHelper.getPartialAnalysisIndicator() : false;
        boolean bTNnotFoundIndicator = aResponseByNetworkType != null ? aResponseByNetworkType.getBTNnotFoundIndicator() : false;
        boolean wTNnotInPremiseIndicator = aResponseHelper != null ? aResponseHelper.getWorkingTelephoneNumberNotInPremiseIndicator() : false;

        // format indicators for WTN not found in premise scenario
        if (aResponseHelper.getWorkingTelephoneNumberNotInPremiseIndicator())
        {
            okToProceedIndicator = true;
            partialAnalysisIndicator = false;
            bTNnotFoundIndicator = false;
            wTNnotInPremiseIndicator = true;
            aRTIDSource = RelatedCircuitIDSourceValues.RTID_OVALS;
            if (aRequestHelper.getRTID() != null)
            {
                aRecommendedRTID = aRequestHelper.getRTID();
            }
            String aCode = "SC1-0000";
            String aSeverity = "Information";
            String aText = "No conflicts.OK to proceed.";
            try
            {
                RtidMessagesRow aRtidMsgsRow = new RtidMessagesTable().retrieveRow(aCode,
                                                                                   aRequestHelper.getClient(),
                                                                                   aRequestHelper.getOrderActionType(),
                                                                                   aProperties,
                                                                                   aUtility);
                if (aRtidMsgsRow != null && aRtidMsgsRow.getCODE().length() > 0)
                {
                    aCode = aRtidMsgsRow.getCODE();
                    aSeverity = aRtidMsgsRow.getSEVERITY();
                    aText = aRtidMsgsRow.getTEXT();
                }
            }
            catch (Exception e)
            { /* No action, we'll just use the defaults. */}

            aMessages = new Message[] { new Message(aCode, aSeverity, aText) };
        }
        
        // build response detail
        ResponseDetail2 aResponseDetail = new ResponseDetail2(aRequestHelper.getNetworkType(aNetworkType),
                                                              (BillingAccount2SeqOpt) IDLUtil.toOpt(BillingAccount2SeqOpt.class, null),
                                                              okToProceedIndicator,
                                                              partialAnalysisIndicator,
                                                              bTNnotFoundIndicator,
                                                              wTNnotInPremiseIndicator,
                                                              aRTIDSource,
                                                              (StringOpt) IDLUtil.toOpt(StringOpt.class, aRecommendedRTID),
                                                              (StringSeqOpt) IDLUtil.toOpt(StringSeqOpt.class, null),
                                                              (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                              (StringSeqOpt) IDLUtil.toOpt(StringSeqOpt.class, null),
                                                              (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, null),
                                                              aMessages,
                                                              (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, null));

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return aResponseDetail;
    }
    
    /**
     * Builds a response detail.
     * @param ResponseDetail2[] aResponseDetail2
     * @return void
     * @author Hongmei Parkin
     */
    private void buildResponseDetail3(ResponseDetail2[] aResponseDetail2)
    {
    	ResponseDetail3[] aResponseDetail3 = new ResponseDetail3[aResponseDetail2.length];
    	BillingAccount3SeqOpt aBillingAccounts3 = (BillingAccount3SeqOpt) IDLUtil.toOpt(BillingAccount3SeqOpt.class, null);
    	BooleanOpt aWorkingTelephoneNumberNotInPremiseIndicator = (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null);
    	try 
    	{
	    	for (int i = 0; i < aResponseDetail2.length; i++ )
	    	{
	    		aBillingAccounts3 = (BillingAccount3SeqOpt) IDLUtil.toOpt(BillingAccount3SeqOpt.class, null);
    			if (!OptHelper.isBillingAccount2SeqOptEmpty(aResponseDetail2[i].aBillingAccounts))
				{
					aBillingAccounts3 = (BillingAccount3SeqOpt) IDLUtil.toOpt(BillingAccount3SeqOpt.class, getBillingAccounts3(aResponseDetail2[i].aBillingAccounts.theValue()));
				}
	    		
	    		aWorkingTelephoneNumberNotInPremiseIndicator.theValue(aResponseDetail2[i].aWorkingTelephoneNumberNotInPremiseIndicator);
	    		aResponseDetail3[i] = new ResponseDetail3(aResponseDetail2[i].aNti,
	    												  aBillingAccounts3,
	    												  aResponseDetail2[i].aProceedIndicator,
	    												  aResponseDetail2[i].aPartialAnalysisIndicator,
	    												  aResponseDetail2[i].aBillingTelephoneNumberNotFoundIndicator,
	    												  aWorkingTelephoneNumberNotInPremiseIndicator,
	    												  aResponseDetail2[i].aRelatedCircuitIDSource,
	    												  aResponseDetail2[i].aRecommendedRelatedCircuitID,
	    												  aResponseDetail2[i].aRecommendedSecondaryRelatedCircuitIDs,
	    												  aResponseDetail2[i].aTerminalExhaustIndicator,
	    												  aResponseDetail2[i].aAutoDSLDisconnectTelephoneNumbers,
	    												  aResponseDetail2[i].aRecommendedDueDate,
	    												  aResponseDetail2[i].aMessages,
	    												  aResponseDetail2[i].aProperties);
	    	}
	    	aResponseHelper.setResponseDetail3(aResponseDetail3);
    	} 
    	catch (Exception e) 
    	{
    		 aUtility.log(LogEventId.DEBUG_LEVEL_1,"Exception in buildResponseDetail3()" + e.getMessage());
    	}
    }
    
    /**
     * Formats an empty response message.
     * @param String aNetworkType
     * @return ResponseDetail2
     * @author Rene Duka
     */
    private ResponseDetail2 buildEmptyResponseMessage(String aNetworkType)
    {
        String aMethodName = "PublishValidateFacilityNotification: buildEmptyResponseMessage()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // format an empty message
        Message[] aMessages = new Message[] {new Message("", "", "")};

        // build response detail
        ResponseDetail2 aResponseDetail = new ResponseDetail2(aRequestHelper.getNetworkType(aNetworkType),
                                                              (BillingAccount2SeqOpt) IDLUtil.toOpt(BillingAccount2SeqOpt.class, null),
                                                              false,
                                                              false,
                                                              false,
                                                              false,
                                                              "",
                                                              (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                                                              (StringSeqOpt) IDLUtil.toOpt(StringSeqOpt.class, null),
                                                              (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
                                                              (StringSeqOpt) IDLUtil.toOpt(StringSeqOpt.class, null),
                                                              (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, null),
                                                              aMessages,
                                                              (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, null));

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return aResponseDetail;
    }

    /**
     * Examines loops to detemine the following:
     *     - if conflict is found
     *     - if u-verse is found
     *     - if DSL is found
     *     - loops with P commit
     *     - loops with S commit
     *     - loops with DSL
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @author Rene Duka
     */
    private void examineLoops(
        String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType)
    {
        String aMethodName = "PublishValidateFacilityNotification: examineLoops()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // **********************************************************************************
        // initialize local variables
        // **********************************************************************************
        boolean bConflict = false;
        boolean bUverseFound = false;
        boolean bUverseSOFound = false;
        boolean bDSLFound = false;
        boolean bFiberInPremise = false;
        ArrayList aLoopsWithPCommitList = new ArrayList();
        ArrayList aLoopsWithSCommitList = new ArrayList();
        ArrayList aLoopsWithDSLList = new ArrayList();
        String aWtn = null;

        // **********************************************************************************
        // retrieve all loops
        // **********************************************************************************
        FacilityLoop2[] aLoops = aResponseByNetworkType.getLoops();

        // **********************************************************************************
        // if loops are found, examine the services and set the indicators accordingly.
        // **********************************************************************************
        if (aLoops != null)
        {
            for (int i=0; i < aLoops.length; i++)
            {
            	try {
            	aWtn = aLoops[i].aWorkingTelephoneNumber.theValue();
            	} catch (Throwable e)
            	{
            		// null value is fine
            	}
                // retrieve commit status and broadband indicator
                if (!OptHelper.isStringOptEmpty(aLoops[i].aBroadbandPair))
                {
                    if (aLoops[i].aBroadbandPair.theValue().equalsIgnoreCase("BBP"))
                    {
                        if (!OptHelper.isStringOptEmpty(aLoops[i].aCommitStatus))
                        {
                            if (aLoops[i].aCommitStatus.theValue().equalsIgnoreCase("P"))
                            {
                                aLoopsWithPCommitList.add(aLoops[i]);
                            }
                            if (aLoops[i].aCommitStatus.theValue().equalsIgnoreCase("S"))
                            {
                                aLoopsWithSCommitList.add(aLoops[i]);
                            }
                        }
                    }
                }

                // retrieve services on the loop
                if (!OptHelper.isServiceItemSeqOptEmpty(aLoops[i].aServices))
                {
                    ServiceItem[] aServiceItems = aLoops[i].aServices.theValue();
                    for (int j=0; j < aServiceItems.length; j++)
                    {
                        if (!OptHelper.isStringOptEmpty(aServiceItems[j].aServiceType))
                        {
                            // retrieve the service type
                            String aServiceType = aServiceItems[j].aServiceType.theValue();
                            boolean bServiceConflictIndicator = aServiceItems[j].aConflictingServiceIndicator;
                            // set the value of conflicting service found indicator to TRUE if a conflicting service other than DSL is found
                            // R10 Code - added LS IPDSLAM and HYBRID IPDSLAM
                            if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
                            		|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
                            		|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
                            {
                                aLoopsWithDSLList.add(aLoops[i]);
                                bDSLFound = true;
                                ObjectProperty[] aServiceItemProperties = null;
                                ObjectPropertyManager aServiceItemOPM = new ObjectPropertyManager();
                                if (!OptHelper.isObjectPropertySeqOptEmpty(aServiceItems[j].aServiceItemProperties))
                                {
                                    // check if DSL is owned by AT&T or not
                                    aServiceItemProperties = aServiceItems[j].aServiceItemProperties.theValue();
                                    aServiceItemOPM = new ObjectPropertyManager(aServiceItems[j].aServiceItemProperties.theValue());
                                    // if non-ATT owned DSL, set bConflict to true
                                    // if DSL is null, set bConflict to false
                                    if (!checkDSLOwnedByATT(aServiceItemProperties))
                                    {
                                    	String aDSLServiceProvider = null;
        			       	            String aInternetServiceProvider = null;

        			       	            try
            		        			{
            		        				aDSLServiceProvider = aServiceItemOPM.getValue(ServiceItemPropertyValues.DSL_SERVICE_PROVIDER);
            		        				aInternetServiceProvider = aServiceItemOPM.getValue(ServiceItemPropertyValues.INTERNET_SERVICE_PROVIDER);

            		        			}
            		        			catch (Throwable e)
            		        			{
            		        				aDSLServiceProvider = null;
            			       	            aInternetServiceProvider = null;
            		        			}

            		        			//check if DSL or Internet Service Provider is not set, set bConflict to true (CR25554)
            		        			if ( aDSLServiceProvider == null || aInternetServiceProvider == null)
            		        			{
            		        				bConflict = false;
            		        			}
            		        			else
            		        			{
            		        				bConflict = true;
            		        			}
                                    }
                                    // else DSL is ATT-owned
                                    else
                                    {   // if client is negotiation and NTI is IPDSLAM, set conflict to true if UBAN is found
                                    	if(aNetworkType.equalsIgnoreCase(NetworkType3Values.IPCO) || 
                                        		aNetworkType.equalsIgnoreCase(NetworkType3Values.IPRT))
                                        {
    	                                    if(aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)
    	                                    		|| aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3))
    	                                    {
    	                                    	ObjectKey[] aObjectKeys = null;
    	                                    	String aUBAN = null;
    	                                    	try 
    	                                    	{
    	                                    		aObjectKeys = getUniversalBillingAccountNumber(aWtn).theValue().aKeys;
    	                                    		
    	                                    		for(int k = 0; k < aObjectKeys.length; k++)
    	                                    		{
    	                                    			if(aObjectKeys[k].aValue != null && !aObjectKeys[k].aValue.trim().equalsIgnoreCase(""))
    	                                    			{
    	                                    				aUBAN = aObjectKeys[k].aValue;
    	                                    				break;
    	                                    			}
    	                                    		}
	    	                                    	if(aUBAN != null)
	    	                                    	{
	                                    				aServiceItems[j].aConflictingServiceIndicator = true;
	    	                                    	}    	                                    	
	    	                                    	else
	    	                                    	{
	    	                                    		aServiceItems[j].aConflictingServiceIndicator = false;
	    	                                    	}
    	                                    	} 
    	                                    	catch (Throwable e)
    	                                    	{
    	                                    		//Null UBAN
    	                                    		aServiceItems[j].aConflictingServiceIndicator = false;
    	                                    	}
    	                                    }
    	                                    else if(aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING)) 
    	                                    {
    	                                    	aServiceItems[j].aConflictingServiceIndicator = false;
    	                                    }
    	                                    bServiceConflictIndicator = aServiceItems[j].aConflictingServiceIndicator;
                                        }                                 	
                                    }
                                }
                            }
                            else
                            {
                                if (bServiceConflictIndicator)
                                {
                                    bConflict = true;
                                }
                            }

                            // check if one of the service items is U-Verse
                            //     - if conflict indicator for U-verse is true, set the U-verse found indicator to true
                            //     - if conflict indicator for U-Verse is false, set the U-Verse service order found indicator to true
                            if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_UVERSE))
                            {
                                if (bServiceConflictIndicator)
                                {
                                    bUverseFound = true;
                                }
                                else
                                {
                                    bUverseSOFound = true;
                                }
                                //CR24671 - If OrderActionType sent by the client is CHANGE and NTI is FTTNBP and UVERSE is found,
                                //the conflicting services indicator will be set to FALSE for this conflicting service
                                if(aRequestHelper.getOrderActionType() != null
                                		&& aRequestHelper.getOrderActionType().equalsIgnoreCase("CHANGE")
                                		&& aNetworkType.equalsIgnoreCase(NetworkType2Values.FTTNBP))
                                {
                                	aServiceItems[j].aConflictingServiceIndicator = false;
                                	bConflict = false;
                                	bUverseFound = true;
                                }
                            }

                            // check if one of the service items is either GPON or BPON
                            if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_BPON)
                                || aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_GPON))
                            {
                                bFiberInPremise = true;
                            }
                            
                            // CR24678 - check if one of the service items is either UNE-P or LWC
                            if( (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_UNE_P)
                            	|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LWC))
                            	&& aNetworkType.equalsIgnoreCase(NetworkType2Values.FTTN)
                            	&& aResponseHelper.getOverrideUNEPLWCIndicator() == true
                            	&& aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING)
                            	&& aServiceItems[j].aConflictingServiceIndicator == true)
                            {
                            	aServiceItems[j].aConflictingServiceIndicator = false;                         
                            	bConflict = false;
                            }
                        }
                    }
                }
            }

            // **********************************************************************************
            // Set the following in the response helper:
            //     - bConflictFound
            //     - bUverseFound
            //     - bUverseSOFound
            //     - bDSLFound
            //     - bFiberInPremise
            //     - aLoopsWithPCommit
            //     - aLoopsWithSCommit
            //     - aLoopsWithDSL
            // **********************************************************************************
            aResponseHelper.setConflictFound(bConflict);
            aResponseHelper.setUverseFound(bUverseFound);
            aResponseHelper.setUverseSOFound(bUverseSOFound);
            aResponseHelper.setDSLFound(bDSLFound);
            aResponseHelper.setFiberInPremise(bFiberInPremise);

            FacilityLoop2[] aLoopsWithPCommit = null;
            if (aLoopsWithPCommitList.size() > 0) 
            {
                aLoopsWithPCommit = (FacilityLoop2[]) aLoopsWithPCommitList.toArray(new FacilityLoop2[aLoopsWithPCommitList.size()]);
                aResponseHelper.setFacilityLoopsWithPCommit(aLoopsWithPCommit);
            }

            FacilityLoop2[] aLoopsWithSCommit = null;
            if (aLoopsWithSCommitList.size() > 0)
            {
                aLoopsWithSCommit = (FacilityLoop2[]) aLoopsWithSCommitList.toArray(new FacilityLoop2[aLoopsWithSCommitList.size()]);
                aResponseHelper.setFacilityLoopsWithSCommit(aLoopsWithSCommit);
            }

            FacilityLoop2[] aLoopsWithDSL = null;
            if (aLoopsWithDSLList.size() > 0)
            {
                aLoopsWithDSL = (FacilityLoop2[]) aLoopsWithDSLList.toArray(new FacilityLoop2[aLoopsWithDSLList.size()]);
                aResponseHelper.setFacilityLoopsWithDSL(aLoopsWithDSL);
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Examines loops with DSL to detemine the following:
     *     - auto DSL disconnect TN
     *     - auto DSL disconnect indicator
     *     - DSL misuse
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @author Rene Duka
     */
    private void examineLoopsWithDSL(
        String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType)
    {
        String aMethodName = "PublishValidateFacilityNotification: examineLoopsWithDSL()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // **********************************************************************************
        // initialize local variables
        // **********************************************************************************
        boolean bDSLMisuse = false;
        int aDSLOwnedByATT = 0;
        int aDSLNotOwnedByATT = 0;
        String aWtn = null;

        // **********************************************************************************
        // Criteria
        //     - ALL AT&T DSL found on any loop will be recommended for Auto-Disconnect
        // **********************************************************************************

        // retrieve all loops with DSL
        FacilityLoop2[] aLoopsWithDSL = aResponseHelper.getFacilityLoopsWithDSL();

        // if loops are found, examine the DSL properties in each loop.
        if (aLoopsWithDSL != null)
        {
        	String aState = determineState(aRequestHelper.getNpaNxx());
        	
        	//added for CR25554: CPSOS call will only be made for states included in 13-state regions
        	if (((String) aProperties.get("13_STATE_LIST")).indexOf(":" + aState.toUpperCase() + ":") >= 0)
        	{
        		// call CPSOS
        		checkDSLServiceProvider (aLoopsWithDSL, aRequestHelper, aState);
        	}
        	else
        	{
        		aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aState + " is not included in the 13-state regions.");
        	}

			// sets auto DSL disconnect TNs
            setAutoDSLDisconnectTelephoneNumbers(aLoopsWithDSL,
                                                aResponseByNetworkType);
        }

        // **********************************************************************************
        // Examine all loops with DSL to determine the following:
        //     - number of ATT owned DSL
        //     - number of non-ATT owned DSL
        //     - DSL misuse
        // **********************************************************************************
        if (aLoopsWithDSL != null)
        {
        	for (int i=0; i < aLoopsWithDSL.length; i++)
            {
                ServiceItem[] aServiceItems = aLoopsWithDSL[i].aServices.theValue();
                             
                try {
                aWtn = aLoopsWithDSL[i].aWorkingTelephoneNumber.theValue();
                } catch (Throwable e)
                {
                	// null value is fine
                }
                
                for (int j=0; j < aServiceItems.length; j++)
                {
                    if (!OptHelper.isStringOptEmpty(aServiceItems[j].aServiceType))
                    {
                        // retrieve the service type
                        String aServiceType = aServiceItems[j].aServiceType.theValue();
                        // check if one of the service items is DSL
                        if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
                        		|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
                        		|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
                        {
                            ObjectProperty[] aServiceItemProperties = null;
                            ObjectPropertyManager aServiceItemOPM = new ObjectPropertyManager();
                            if (!OptHelper.isObjectPropertySeqOptEmpty(aServiceItems[j].aServiceItemProperties))
                            {
                            	
                                // check if DSL is owned by AT&T or not
                                aServiceItemProperties = aServiceItems[j].aServiceItemProperties.theValue();
                                aServiceItemOPM = new ObjectPropertyManager(aServiceItems[j].aServiceItemProperties.theValue());
                                if (checkDSLOwnedByATT(aServiceItemProperties))
                                {
                                    aDSLOwnedByATT++;
                                    if(aNetworkType.equalsIgnoreCase(NetworkType3Values.IPCO) || 
                                    		aNetworkType.equalsIgnoreCase(NetworkType3Values.IPRT))
                                    {
	                                    if(aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)
	                                    		|| aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3))
	                                    {
	                                    	ObjectKey[] aObjectKeys = null;
	                                    	String aUBAN = null;
	                                    	try 
	                                    	{
	                                    		aObjectKeys = getUniversalBillingAccountNumber(aWtn).theValue().aKeys;
	                                    		
	                                    		for(int k = 0; k < aObjectKeys.length; k++)
	                                    		{
	                                    			if(aObjectKeys[k].aValue != null && !aObjectKeys[k].aValue.trim().equalsIgnoreCase(""))
	                                    			{
	                                    				aUBAN = aObjectKeys[k].aValue;
	                                    				break;
	                                    			}
	                                    		}
    	                                    	if(aUBAN != null)
    	                                    	{
                                    				aServiceItems[j].aConflictingServiceIndicator = true;
    	                                    	}    	                                    	
    	                                    	else
    	                                    	{
    	                                    		aServiceItems[j].aConflictingServiceIndicator = false;
    	                                    	}
	                                    	} 
	                                    	catch (Throwable e)
	                                    	{
	                                    		//Null UBAN
	                                    		aServiceItems[j].aConflictingServiceIndicator = false;
	                                    	}                                    	
	                                    }
	                                    else if(aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING)) 
	                                    {
	                                    	aServiceItems[j].aConflictingServiceIndicator = false;
	                                    }
                                    }
                                }
                                else
                                {
                                    aDSLNotOwnedByATT++;
                                }
                                // check if DSL is misused
                                if (aServiceItemOPM.getValue(ServiceItemPropertyValues.DSL_MISUSE_INDICATOR) != null
                                	&& aServiceItemOPM.getValue(ServiceItemPropertyValues.DSL_MISUSE_INDICATOR).equalsIgnoreCase("true"))
                                {
                                    bDSLMisuse = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        // **********************************************************************************
        // Set the following in the response helper:
        //     - aDSLOwnedByATT
        //     - aDSLNotOwnedByATT
        //     - bDSLMisuse
        // **********************************************************************************
        aResponseHelper.setNumberOfATTDSL(aDSLOwnedByATT);
        aResponseHelper.setNumberOfNonATTDSL(aDSLNotOwnedByATT);
        aResponseHelper.setDSLMisuse(bDSLMisuse);

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Sets auto DSL disconnect telephone numbers.
     * @param FacilityLoop2[]                                 aLoopsWithDSL
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @author Rene Duka
     */
    private void setAutoDSLDisconnectTelephoneNumbers(
        FacilityLoop2[] aLoopsWithDSL,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType)
    {
    	String aMethodName = "PublishValidateFacilityNotification: setAutoDSLDisconnectTelephoneNumbers()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        ArrayList aDSLAutoDisconnectTelephoneNumbersList = new ArrayList();        
        for (int i = 0; i < aLoopsWithDSL.length; i++) 
		{
        	ServiceItem[] aServiceItems = aLoopsWithDSL[i].aServices.theValue();
        	for (int j=0; j < aServiceItems.length; j++)
        	{
        		if (!OptHelper.isStringOptEmpty(aServiceItems[j].aServiceType))
        		{
        			// retrieve the service type
        			String aServiceType = aServiceItems[j].aServiceType.theValue();
        			// check if one of the service items is DSL
        			// R1- Code - added LS IPDSLAM and HYBRID IPDSLAM
        			if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
        					|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
        					|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
        			{
        				ObjectProperty[] aServiceItemProperties = null;
        				ObjectPropertyManager aServiceItemOPM = new ObjectPropertyManager();
        				if (!OptHelper.isObjectPropertySeqOptEmpty(aServiceItems[j].aServiceItemProperties))
        				{
        					// check if DSL is owned by AT&T or not
        					aServiceItemProperties = aServiceItems[j].aServiceItemProperties.theValue();
        					aServiceItemOPM = new ObjectPropertyManager(aServiceItems[j].aServiceItemProperties.theValue());
        					if (checkDSLOwnedByATT(aServiceItemProperties))
        					{
        						// set DSL Auto-Disconnect indicator to TRUE
        						aServiceItemOPM.addWithOverride(new ObjectProperty(ServiceItemPropertyValues.DSL_AUTO_DISCONNECT_INDICATOR, "true"));
        						// add to DSL Auto-Disconnect telephone numbers list
        						if (!OptHelper.isStringOptEmpty(aLoopsWithDSL[i].aWorkingTelephoneNumber))
        						{
        							aDSLAutoDisconnectTelephoneNumbersList.add(aLoopsWithDSL[i].aWorkingTelephoneNumber.theValue());
        						}
        					}
        					aServiceItems[j].aServiceItemProperties.theValue(aServiceItemOPM.toArray());
        				}
        			}
        		}
        	}
        }

        // format DSL Auto-Disconnect telephone numbers
        String[] aDSLAutoDisconnectTelephoneNumbers = null;
        if (aDSLAutoDisconnectTelephoneNumbersList.size() > 0)
        {
            aDSLAutoDisconnectTelephoneNumbers = (String[]) aDSLAutoDisconnectTelephoneNumbersList.toArray(new String[aDSLAutoDisconnectTelephoneNumbersList.size()]);
            aResponseByNetworkType.setAutoDSLDisconnectTelephoneNumbers(aDSLAutoDisconnectTelephoneNumbers);
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Examines loops to detemine the following:
     *     - if DISH service is found
     *     - if Dial-up service is found
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @author Rene Duka
     */
    private void examineAccounts(
        String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType)
    {
        String aMethodName = "PublishValidateFacilityNotification: examineAccounts()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // **********************************************************************************
        // initialize local variables
        // **********************************************************************************
        boolean bDISHFound = false;
        boolean bDialUpFound = false;
        boolean bAccountFound = false;

        // **********************************************************************************
        // retrieve all accounts
        // **********************************************************************************
        BillingAccount2[] aAccounts = aResponseByNetworkType.getAccounts();

        // **********************************************************************************
        // if accounts are found, examine and set the indicators accordingly.
        // **********************************************************************************
        if (aAccounts != null)
        {
            for (int i=0; i < aAccounts.length; i++)
            {
                // check the value of the Billing Telephone Number
                if (!OptHelper.isStringOptEmpty(aAccounts[i].aBillingTelephoneNumber))
                {
                    bAccountFound = true;
                }

                // check the value of the DISH service indicator
                if (!OptHelper.isBooleanOptEmpty(aAccounts[i].aDISHServiceIndicator))
                {
                    if (aAccounts[i].aDISHServiceIndicator.theValue()) 
                    {
                    	bDISHFound = true;
                    }
                }

                // check the value of the Dial-up service indicator
                if (!OptHelper.isBooleanOptEmpty(aAccounts[i].aDialUpServiceIndicator))
                {
                	if (aAccounts[i].aDialUpServiceIndicator.theValue()) 
                    {
                		bDialUpFound = true;
                    }
                }
            }

            // **********************************************************************************
            // Set the following in the response helper:
            //     - bDISHFound
            //     - bDialUpFound
            //     - bAccountFound
            // **********************************************************************************
            aResponseHelper.setDISHFound(bDISHFound);
            aResponseHelper.setDialUpFound(bDialUpFound);
            aResponseHelper.setAccountFound(bAccountFound);
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Formats the billing telephone number not found indicator.
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @param ArrayList                                     aLoopsNoBTN
     * @author Rene Duka
     */
    private void formatBTNnotFoundIndicator(
        String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType,
        ArrayList aLoopsNoBTN)
    {
        String aMethodName = "PublishValidateFacilityNotification: formatBTNnotFoundIndicator()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // **********************************************************************************
        // Format the Billing Telephone Number not found indicator.
        //     - set to true if there is at least 1 loop without BTN
        //     - set to false if there is at least 1 account found in SM (partial)
        // **********************************************************************************
        if (aLoopsNoBTN.size() > 0)
        {
            aResponseByNetworkType.setBTNnotFoundIndicator(true);
        }

        if (aResponseHelper.getAccountFound())
        {
            aResponseByNetworkType.setBTNnotFoundIndicator(false);
        }
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Formats the recommended RTID for GPON.
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @param String                                        aRTIDFromClient
     * @author Rene Duka
     */
    private void formatRecommendedRTIDGPON(
        String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType)
    {
        String aMethodName = "PublishValidateFacilityNotification: formatRecommendedRTIDGPON()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // **********************************************************************************
        // Set to the value of the RTID found in the fiber loop.
        // **********************************************************************************
        if (aResponseHelper.getRecommendedRTID_GPON() != null)
        {
            aResponseByNetworkType.setRecommendedRTID(aResponseHelper.getRecommendedRTID_GPON());
            aResponseHelper.setRecommendedRTIDFound(true);
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Formats the recommended RTID for IPCO and IPRT.
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @author Rene Duka
     */
    private void formatRecommendedRTIDIPDSLAM(String aNetworkType, PublishValidateFacilityNotificationResponseIF aResponseByNetworkType) 
    {
        String aMethodName = "PublishValidateFacilityNotification: formatRecommendedRTIDIPDSLAM()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
    	// get all the loops assigned to the NTI requested
    	FacilityLoop2 [] aLoops = aResponseByNetworkType.getLoops();
    	int aLoopsWithRTID = 0;
    	int index = 0;
    	
    	for(int i = 0; i < aLoops.length; i ++)
    	{
    		// check if the loop has a related circuit ID
    		if(!OptHelper.isStringOptEmpty(aLoops[i].aRelatedCircuitID))
    		{
    			// increment the counter
    			aLoopsWithRTID++;
    			index  = i;
    		}
    	}
		if(!aResponseHelper.getConflictFound() && aLoopsWithRTID == 1)
		{
			aResponseByNetworkType.setRecommendedRTID(aLoops[index].aRelatedCircuitID.theValue());
			aResponseHelper.setRecommendedRTIDFound(true);
		}
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
	}

    /**
     * Formats the recommended RTID.
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @param String                                        aWorkingTelephoneNumber
     * @param String                                        aRTIDFromClient
     * @author Rene Duka
     */
    private void formatRecommendedRTID(String aNetworkType,
                                       PublishValidateFacilityNotificationResponseIF aResponseByNetworkType,
                                       String aWorkingTelephoneNumber)
    {
    	String aMethodName = "PublishValidateFacilityNotification: formatRecommendedRTID()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // **********************************************************************************
        // Determine RTID for all the P-Committed/BBP-Conditioned Loops that
        // have no conflict.
        // RTID value can be Blank or a WTN found on the loop.
        //
        // Retrieve all P-commit loops
        // **********************************************************************************
        FacilityLoop2[] aPCommitLoops = aResponseHelper.getFacilityLoopsWithPCommit();
        if (aPCommitLoops != null)
        {
            findRTID(aNetworkType, aResponseByNetworkType, aPCommitLoops, aWorkingTelephoneNumber);
        }

        // **********************************************************************************
        // Determine RTID for all the S-Committed/BBP-Conditioned Loops that
        // have no conflict.
        // RTID value can be Blank or a WTN found on the loop.
        //
        // Retrieve all S-commit loops
        // **********************************************************************************
        if (!aResponseHelper.getRecommendedRTIDFound())
        {
            FacilityLoop2[] aSCommitLoops = aResponseHelper.getFacilityLoopsWithSCommit();
            if (aSCommitLoops != null)
            {
                findRTID(aNetworkType, aResponseByNetworkType, aSCommitLoops, aWorkingTelephoneNumber);
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }
    
    /**
     * Formats RTID source.
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @param boolan										aErrorInLFACSIndicator
     * @author Rene Duka
     */
    private void formatRTIDSource(
        String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType,
		boolean aErrorInLFACSIndicator)
    {
        String aMethodName = "PublishValidateFacilityNotification: formatRTIDSource()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // **********************************************************************************
        // Format RTID source.
        // **********************************************************************************
        if (aErrorInLFACSIndicator)
		{
        	aResponseByNetworkType.setRTIDSource(RelatedCircuitIDSourceValues.RTID_OVALS);
		}
		else
		{
			aResponseByNetworkType.setRTIDSource(RelatedCircuitIDSourceValues.RTID_LFACS);
		}
        // **********************************************************************************

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Find RTID.
     * @param String 										aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @param FacilityLoop2[]                               aLoop
     * @param String                                        aWorkingTelephoneNumber
     * @return boolean
     * @author Rene Duka
     */
    private void findRTID(
    	String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType,
        FacilityLoop2[] aLoops,
        String aWorkingTelephoneNumber)
    {
        String aMethodName = "PublishValidateFacilityNotification: findRTID()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        
        // **********************************************************************************
        // CR24671
        //     - if the client sends a request to change the network transport type to FTTNBP
        //       at a premise where UVERSE is working, use the loop w/ tjhe existing UVERSE to
        //		 set the recommendedRTID
        // **********************************************************************************
        if(aRequestHelper.getOrderActionType() != null 
        		&& aRequestHelper.getOrderActionType().equalsIgnoreCase("CHANGE")
        		&& aNetworkType.equalsIgnoreCase(NetworkType2Values.FTTNBP))
        {
	        for(int i=0; i < aLoops.length; i++)
	        {
	        	FacilityLoop2 aLoop = aLoops[i];
	            // **********************************************************************************
	            // RTID cannot be recommended if it has conflicting service on it
	            //
	            // If conflict was found in one of the loops, check if the loop in process is the one
	            // with the conflict
	            // **********************************************************************************
	            boolean bLoopHasConflict = false;
	            if (aResponseHelper.getConflictFound())
	            {
	                bLoopHasConflict = checkLoopHasConflict(aLoop);
	            }
	            
	            if(!bLoopHasConflict)
	            {
	            	if(!OptHelper.isServiceItemSeqOptEmpty(aLoop.aServices))
	            	{
	            		ServiceItem[] aServiceItems = aLoop.aServices.theValue();
	            		
	            		for(int j = 0; j < aServiceItems.length; j ++)
	            		{
	            			if (!OptHelper.isStringOptEmpty(aServiceItems[j].aServiceType))
	            			{
	            				String aServiceType = aServiceItems[j].aServiceType.theValue();
	            				
	            				if(aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_UVERSE)
	            						&& !OptHelper.isStringOptEmpty(aLoop.aRelatedCircuitID))
	            				{
	            					aResponseByNetworkType.setRecommendedRTID(aLoop.aRelatedCircuitID.theValue());
	            					aResponseByNetworkType.setRTIDSource(RelatedCircuitIDSourceValues.RTID_LFACS);
	                                aResponseHelper.setRecommendedRTIDFound(true);
	            				}
	            			}
	            		}
	            	}
	            }
	        }
        }

        // **********************************************************************************
        // 1st criteria
        //     - value of aRelatedCircuitID of the loop where
        //       aWorkingTelephoneNumber is equal to aWorkingTelephoneNumber in input.
        // **********************************************************************************
        if (!aResponseHelper.getRecommendedRTIDFound())
	    {
	        for (int i=0; i < aLoops.length; i++)
	        {
	            FacilityLoop2 aLoop = aLoops[i];
	            // **********************************************************************************
	            // RTID cannot be recommended if it has conflicting service on it
	            //
	            // If conflict was found in one of the loops, check if the loop in process is the one
	            // with the conflict
	            // **********************************************************************************
	            boolean bLoopHasConflict = false;
	            if (aResponseHelper.getConflictFound())
	            {
	                bLoopHasConflict = checkLoopHasConflict(aLoop);
	            }
	
	            if (!bLoopHasConflict)
	            {
	                if (findRTID_Criteria1(aLoop,
	                                       aWorkingTelephoneNumber))
	                {
	                    aResponseByNetworkType.setRecommendedRTID(aLoop.aRelatedCircuitID.theValue());
	                    aResponseByNetworkType.setRTIDSource(RelatedCircuitIDSourceValues.RTID_LFACS);
	                    aResponseHelper.setRecommendedRTIDFound(true);
	                }
	            }
	
	            if (aResponseHelper.getRecommendedRTIDFound())
	            {
	                // check if the loop has DSL owned by AT&T or not
	                if (!OptHelper.isServiceItemSeqOptEmpty(aLoop.aServices))
	                {
	                    ServiceItem[] aServiceItems = aLoop.aServices.theValue();
	                    for (int j=0; j < aServiceItems.length; j++)
	                    {
	                        if (!OptHelper.isStringOptEmpty(aServiceItems[j].aServiceType))
	                        {
	                            String aServiceType = aServiceItems[j].aServiceType.theValue();
	                            if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
	                            	|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
	                                || aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
	                            {
	                                ObjectProperty[] aServiceItemProperties = null;
	                                ObjectPropertyManager aServiceItemOPM = new ObjectPropertyManager();
	                                if (!OptHelper.isObjectPropertySeqOptEmpty(aServiceItems[j].aServiceItemProperties))
	                                {
	                                    // check if DSL is owned by AT&T or not
	                                    aServiceItemProperties = aServiceItems[j].aServiceItemProperties.theValue();
	                                    aServiceItemOPM = new ObjectPropertyManager(aServiceItems[j].aServiceItemProperties.theValue());
	                                    if (checkDSLOwnedByATT(aServiceItemProperties))
	                                    {
	                                        aResponseHelper.setFacilityLoopWithRTIDandDSL(aLoop);
	                                    }
	                                }
	                                break;
	                            }
	                        }
	                    }
	                }
	                break;
	            }
	        }
	    }

        // **********************************************************************************
        // 2nd criteria
        //     - value of FacilityLoop2[].aRelatedCircuitID of the first loop where
        //       FacilityLoop2[].aServices[].aServiceType is equal to DSL and DSL is owned by AT&T.
        // **********************************************************************************
        if (!aResponseHelper.getRecommendedRTIDFound())
        {
            for (int i=0; i < aLoops.length; i++)
            {
                FacilityLoop2 aLoop = aLoops[i];
                // **********************************************************************************
                // RTID cannot be recommended if it has conflicting service on it
                //
                // If conflict was found in one of the loops, check if the loop in process is the one
                // with the conflict
                // **********************************************************************************
                boolean bLoopHasConflict = false;
                if (aResponseHelper.getConflictFound())
                {
                    bLoopHasConflict = checkLoopHasConflict(aLoop);
                }

                if (!bLoopHasConflict)
                {
                    if (findRTID_Criteria2(aLoop))
                    {
                        aResponseByNetworkType.setRecommendedRTID(aLoop.aRelatedCircuitID.theValue());
                        aResponseByNetworkType.setRTIDSource(RelatedCircuitIDSourceValues.RTID_LFACS);
                        aResponseHelper.setRecommendedRTIDFound(true);
                        aResponseHelper.setFacilityLoopWithRTIDandDSL(aLoop);
                    }
                }

                if (aResponseHelper.getRecommendedRTIDFound())
                {
                    break;
                }
            }
        }

        // **********************************************************************************
        // 3rd criteria
        //     - Value of FacilityLoop2[].aRelatedCircuitID of the first loop found.
        // **********************************************************************************
        if (!aResponseHelper.getRecommendedRTIDFound())
        {
            for (int i=0; i < aLoops.length; i++)
            {
                FacilityLoop2 aLoop = aLoops[i];
                // **********************************************************************************
                // RTID cannot be recommended if it has conflicting service on it
                //
                // If conflict was found in one of the loops, check if the loop in process is the one
                // with the conflict
                // **********************************************************************************
                boolean bLoopHasConflict = false;
                if (aResponseHelper.getConflictFound())
                {
                    bLoopHasConflict = checkLoopHasConflict(aLoop);
                }

                if (!bLoopHasConflict)
                {
                    if (findRTID_Criteria3(aLoop))
                    {
                        aResponseByNetworkType.setRecommendedRTID(aLoop.aRelatedCircuitID.theValue());
                        aResponseByNetworkType.setRTIDSource(RelatedCircuitIDSourceValues.RTID_LFACS);
                        aResponseHelper.setRecommendedRTIDFound(true);
                    }
                }

                if (aResponseHelper.getRecommendedRTIDFound())
                {
                    break;
                }
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Find secondary RTID.
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @param FacilityLoop2[]                               aLoop
     * @param String                                        aWorkingTelephoneNumber
     * @return boolean
     * @author Rene Duka
     */
    private void findSecondaryRTID(
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType,
        FacilityLoop2[] aLoops,
        String aWorkingTelephoneNumber)
    {
        String aMethodName = "PublishValidateFacilityNotification: findSecondaryRTID()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // Note: Capture primary RTID so we do not reuse it for the secondary RTID(s)
        String primaryRTID = aResponseByNetworkType.getRecommendedRTID() != null ? aResponseByNetworkType.getRecommendedRTID() : "";

        // **********************************************************************************
        // 1st criteria
        //     - value of aRelatedCircuitID of the loop where
        //       aWorkingTelephoneNumber is equal to aWorkingTelephoneNumber in input.
        // **********************************************************************************
        for (int i=0; i < aLoops.length; i++)
        {
            FacilityLoop2 aLoop = aLoops[i];
            // **********************************************************************************
            // RTID cannot be recommended if it has conflicting service on it
            //
            // If conflict was found in one of the loops, check if the loop in process is the one
            // with the conflict
            // **********************************************************************************
            boolean bLoopHasConflict = false;
            if (aResponseHelper.getConflictFound())
            {
                bLoopHasConflict = checkLoopHasConflict(aLoop);
            }

            if (!bLoopHasConflict)
            {
                if (findRTID_Criteria1(aLoop,
                                       aWorkingTelephoneNumber))
                {
                    if (!primaryRTID.equals(aLoop.aRelatedCircuitID.theValue().trim()))
                    {
                        String[] aRecommendedSecondaryRTIDs = new String[1];
                        aRecommendedSecondaryRTIDs[0] = aLoop.aRelatedCircuitID.theValue();
                        aResponseByNetworkType.setRecommendedSecondaryRTID(aRecommendedSecondaryRTIDs);
                        aResponseHelper.setRecommendedSecondaryRTIDFound(true);
                    }
                }
            }

            if (aResponseHelper.getRecommendedSecondaryRTIDFound())
            {
                break;
            }
        }

        // **********************************************************************************
        // 2nd criteria
        //     - value of FacilityLoop2[].aRelatedCircuitID of the first loop where
        //       FacilityLoop2[].aServices[].aServiceType is equal to DSL and DSL is owned by AT&T.
        // **********************************************************************************
        if (!aResponseHelper.getRecommendedSecondaryRTIDFound())
        {
            for (int i=0; i < aLoops.length; i++)
            {
                FacilityLoop2 aLoop = aLoops[i];
                // **********************************************************************************
                // RTID cannot be recommended if it has conflicting service on it
                //
                // If conflict was found in one of the loops, check if the loop in process is the one
                // with the conflict
                // **********************************************************************************
                boolean bLoopHasConflict = false;
                if (aResponseHelper.getConflictFound())
                {
                    bLoopHasConflict = checkLoopHasConflict(aLoop);
                }

                if (!bLoopHasConflict)
                {
                    if (findRTID_Criteria2(aLoop))
                    {
                        if (!primaryRTID.equals(aLoop.aRelatedCircuitID.theValue().trim()))
                        {
                            String[] aRecommendedSecondaryRTIDs = new String[1];
                            aRecommendedSecondaryRTIDs[0] = aLoop.aRelatedCircuitID.theValue();
                            aResponseByNetworkType.setRecommendedSecondaryRTID(aRecommendedSecondaryRTIDs);
                            aResponseHelper.setRecommendedSecondaryRTIDFound(true);
                        }
                    }
                }

                if (aResponseHelper.getRecommendedSecondaryRTIDFound())
                {
                    break;
                }
            }
        }

        // **********************************************************************************
        // 3rd criteria
        //     - Value of FacilityLoop2[].aRelatedCircuitID of the first loop found.
        // **********************************************************************************
        if (!aResponseHelper.getRecommendedSecondaryRTIDFound())
        {
            for (int i=0; i < aLoops.length; i++)
            {
                FacilityLoop2 aLoop = aLoops[i];
                // **********************************************************************************
                // RTID cannot be recommended if it has conflicting service on it
                //
                // If conflict was found in one of the loops, check if the loop in process is the one
                // with the conflict
                // **********************************************************************************
                boolean bLoopHasConflict = false;
                if (aResponseHelper.getConflictFound())
                {
                    bLoopHasConflict = checkLoopHasConflict(aLoop);
                }

                if (!bLoopHasConflict)
                {
                    if (findRTID_Criteria3(aLoop))
                    {
                        if (!primaryRTID.equals(aLoop.aRelatedCircuitID.theValue().trim()))
                        {
                            String[] aRecommendedSecondaryRTIDs = new String[1];
                            aRecommendedSecondaryRTIDs[0] = aLoop.aRelatedCircuitID.theValue();
                            aResponseByNetworkType.setRecommendedSecondaryRTID(aRecommendedSecondaryRTIDs);
                            aResponseHelper.setRecommendedSecondaryRTIDFound(true);
                        }
                    }
                }

                if (aResponseHelper.getRecommendedSecondaryRTIDFound())
                {
                    break;
                }
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Value of FacilityLoop2[].aRelatedCircuitID of the loop where
     *     FacilityLoop2[].aWorkingTelephoneNumber is equal to aWorkingTelephoneNumber in input.
     * @param FacilityLoop2 aLoop
     * @param String        aWorkingTelephoneNumber
     * @return boolean
     * @author Rene Duka
     */
    private boolean findRTID_Criteria1(
        FacilityLoop2 aLoop,
        String aWorkingTelephoneNumber)
    {
        String aMethodName = "PublishValidateFacilityNotification: findRTID_Criteria1()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // initialize return value to false
        boolean bRTIDFound = false;

        // find RTID
        if (!OptHelper.isStringOptEmpty(aLoop.aRelatedCircuitID))
        {
            String aRTID = aLoop.aRelatedCircuitID.theValue();
            if (aWorkingTelephoneNumber != null)
            {
                if (aRTID.equalsIgnoreCase(aWorkingTelephoneNumber))
                {
                    bRTIDFound = true;
                }
            }
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return bRTIDFound;
    }

    /**
     * Value of FacilityLoop2[].aRelatedCircuitID of the first LS conditioned loop where
     *     FacilityLoop2[].aServices[].aServiceType is equal to DSL and DSL is owned by AT&T.
     * @param FacilityLoop2 aLoop
     * @return boolean
     * @author Rene Duka
     */
    private boolean findRTID_Criteria2(FacilityLoop2 aLoop)
    {
        String aMethodName = "PublishValidateFacilityNotification: findRTID_Criteria2()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // initialize return value to false
        boolean bRTIDFound = false;

        // check if loop is conditioned for LS
        if (checkLoopLSConditioned(aLoop))
        {
            // check services
            if (!OptHelper.isServiceItemSeqOptEmpty(aLoop.aServices))
            {
                ServiceItem[] aServiceItems = aLoop.aServices.theValue();
                for (int i=0; i < aServiceItems.length; i++)
                {
                    if (!OptHelper.isStringOptEmpty(aServiceItems[i].aServiceType))
                    {
                        String aServiceType = aServiceItems[i].aServiceType.theValue();
                        if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
                        	|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
                            || aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
                        {
                            bRTIDFound = true;
                        }
                    }
                }
            }
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return bRTIDFound;
    }

    /**
     * Value of FacilityLoop2[].aRelatedCircuitID of the first LS conditioned loop found.
     * @param FacilityLoop2 aLoop
     * @return boolean
     * @author Rene Duka
     */
    private boolean findRTID_Criteria3(FacilityLoop2 aLoop)
    {
        String aMethodName = "PublishValidateFacilityNotification: findRTID_Criteria3()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // initialize return value to false
        boolean bRTIDFound = false;

        // check if loop is conditioned for LS
        if (checkLoopLSConditioned(aLoop))
        {
            bRTIDFound = true;
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return bRTIDFound;
    }

    /**
     * Formats the recommended secondary RTID.
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @param String                                        aWorkingTelephoneNumber
     * @param String                                        aRTIDFromClient
     * @author Rene Duka
     */
    private void formatRecommendedSecondaryRTID(
        String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType,
        String aWorkingTelephoneNumber,
        String aRTIDFromClient)
    {
        String aMethodName = "PublishValidateFacilityNotification: formatRecommendedSecondaryRTID()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // **********************************************************************************
        // Determine RTID for all the S-Committed/BBP-Conditioned Loops that have no conflict.
        // RTID value can be Blank or a WTN found on the loop.
        //
        // Retrieve all S-commit loops
        // **********************************************************************************
        FacilityLoop2[] aSCommitLoops = aResponseHelper.getFacilityLoopsWithSCommit();
        if (aSCommitLoops!= null)
        {
            findSecondaryRTID(aResponseByNetworkType,
                              aSCommitLoops,
                              aWorkingTelephoneNumber);
        }

        // **********************************************************************************
        // Determine RTID for all the P-Committed/BBP-Conditioned Loops that have no conflict.
        // RTID value can be Blank or a WTN found on the loop.
        //
        // Retrieve all P-commit loops
        // **********************************************************************************
        if (!aResponseHelper.getRecommendedSecondaryRTIDFound())
        {
            FacilityLoop2[] aPCommitLoops = aResponseHelper.getFacilityLoopsWithPCommit();
            if (aPCommitLoops!= null)
            {
                findSecondaryRTID(aResponseByNetworkType,
                                  aPCommitLoops,
                                  aWorkingTelephoneNumber);
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Determine T Commit indicator.
     * @param String aNetworkType
     * @throws DataNotFound
     * @throws ObjectNotFound
     * @throws NotImplemented
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws InvalidData
     * @author Rene Duka
     */
    private void determineTCommit(BisContext aContext, 
                                  String aNetworkType ) 

    throws InvalidData,
           SystemFailure,
           BusinessViolation,
           AccessDenied,
           NotImplemented,
           ObjectNotFound,
           DataNotFound
    {
        String aMethodName = "PublishValidateFacilityNotification: determineTCommit()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        if (aResponseHelper.getNumberOfBBPLoops() < 2)
        {
            // build INITIAL INQOSP request to LFACS
            NINQImpl aNINQRequest = aLFACSService.buildINQOSPRequest(aContext, aRequestHelper.getFacilityAddress());

            // send request to FACSRCAccess
            com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.ResponseMessageImpl aFACSRCOSPResponse =
                aLFACSService.sendINQOSPRequest(aContext,
                                                aRequestHelper.getNpaNxx(),
                                                aNINQRequest);
            // process response from FACSRCAccess
            if (aFACSRCOSPResponse != null)
            {
                // parse the response
                INQOSPResponseHelper aINQOSPResponseHelper = aLFACSService.parseINQOSPGResponse(aFACSRCOSPResponse,
                                                                                                aResponseHelper);
                boolean termExhaust = false;
                if (aINQOSPResponseHelper.getATCOMBBPCNT() == 0)
                   termExhaust = true;
                if (aResponseHelper.getNumberOfBBPLoops() < 1 && aINQOSPResponseHelper.getATCOMBBPCNT() <= 1)
                   termExhaust = true;

                 // R9 Code 
                 // Set TCommit based on the response from the INQOSP. 
                  aResponseHelper.setTCommitIndicator(termExhaust); 

                aUtility.log(LogEventId.INFO_LEVEL_2, "TCOMBBPCNT value: [" + aINQOSPResponseHelper.getATCOMBBPCNT() + "]");
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }
    
    /** 
     * Formats terminal exhaust indicator. 
     * @param String                                        aNetworkType 
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType 
     * @throws DataNotFound 
     * @throws ObjectNotFound 
     * @throws NotImplemented 
     * @throws AccessDenied 
     * @throws BusinessViolation 
     * @throws SystemFailure 
     * @throws InvalidData 
     * @author Vickie Ng 
     */ 
    private void formatTerminalExhaustIndicator(BisContext aContext, 
                                  String aNetworkType, 
                                  PublishValidateFacilityNotificationResponseIF aResponseByNetworkType) 
    throws InvalidData, 
           SystemFailure, 
           BusinessViolation, 
           AccessDenied, 
           NotImplemented, 
           ObjectNotFound, 
           DataNotFound 
    { 
        String aMethodName = "PublishValidateFacilityNotification: formatTerminalExhaustIndicator()"; 
 
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName); 
        
        if ( aResponseHelper.getBBPMissingIndicator() )
        {
        	if ((aNetworkType.equals(NetworkType2Values.FTTN) && aResponseHelper.getNumberOfBBPLoops() <  1 ) ||
        			(aNetworkType.equals(NetworkType2Values.FTTNBP) && aResponseHelper.getNumberOfBBPLoops() < 2 ))
        	{
        		aResponseByNetworkType.setTerminalExhaustIndicator(true); 
        		aResponseHelper.setBBPMissingMsgIndicator(true);
        	}
        }
        
        if (aResponseHelper.getLoadCoilExhaustedIndicator())
        {
        	int noLoadCoilLSLoops = aResponseHelper.getNumberOfBBPLoops() - aResponseHelper.getNumberOfLoadCoilLoops();
        	
        	if ((aNetworkType.equals(NetworkType2Values.FTTN) && noLoadCoilLSLoops <  1 ) ||
             	   (aNetworkType.equals(NetworkType2Values.FTTNBP) && noLoadCoilLSLoops < 2 ))
        	{
        		aResponseByNetworkType.setTerminalExhaustIndicator(true); 
        		aResponseHelper.setLoadCoilMsgIndicator(true);
        	}
        }
        
        if ( aResponseHelper.getTCommitIndicator() ) 
        	aResponseByNetworkType.setTerminalExhaustIndicator(true); 
 
        aUtility.log(LogEventId.INFO_LEVEL_2, "TerminalExhaustedIndicator value: [" + aResponseByNetworkType.getTerminalExhaustIndicator() + "]"); 
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName); 
    } 


    /**
     * Formats the recommended due date.
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @param FacilityLoop2[                                aLoopsWithRTID
     * @param FacilityLoop2[]                               aLoopsWithPendingSO
     * @author Rene Duka
     */
    private void formatRecommendedDueDate(
        String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType,
        FacilityLoop2[] aLoopsWithRTID,
        FacilityLoop2[] aLoopsWithPendingSO,
        EiaDate aDueDateFromClient)
    {
        String aMethodName = "PublishValidateFacilityNotification: formatRecommendedDueDate()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // **********************************************************************************
        // Set the value of the recommended due date.
        // **********************************************************************************
        boolean aRecommendedDueDateFound = false;
        EiaDate aRecommendedDueDate = null;
        
        // LS 10 Code - For IPDSLAM Network Types
        if(aNetworkType.equalsIgnoreCase(NetworkType3Values.IPCO) || 
        		aNetworkType.equalsIgnoreCase(NetworkType3Values.IPRT))
        {
        	// if client is negotiation
        	if(aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)
        			|| aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3))
        	{
        		if( aLoopsWithPendingSO != null)
        		{
        			FacilityLoop2[] aLoops = aResponseByNetworkType.getLoops();
        			
        			if(aLoops != null)
        			{
        				aRecommendedDueDate = findOutwardServiceOrderDueDate(aLoops);
        				aRecommendedDueDateFound = true;
        			}
        		}
        	}
        	// if client is provisioning, the recommended due date will be the input from the client,
        	// if no due date is sent by the client, RM BIS will not send a recommended due date
        	else if(aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING))
        	{
        		if (aDueDateFromClient != null)
	            {
	                aRecommendedDueDate = aDueDateFromClient;
	                aRecommendedDueDateFound = true;
	            }
        	}
        }
        // for non-IPDSLAM Network Types
        else
        {
	        // **********************************************************************************
	        // 1st criteria
	        //     - Due Date of the U-Verse pending service order
	        // **********************************************************************************
	        if (aResponseHelper.getUverseSOFound())
	        {
	            aRecommendedDueDate = aResponseHelper.getRecommendedDueDate_UVerseSO();
	            aRecommendedDueDateFound = true;
	        }
	        
	        // **********************************************************************************
	        // 2nd criteria
	        //     - Due Date sent by the client
	        // **********************************************************************************
	        if (!aRecommendedDueDateFound)
	        {
	            if (aDueDateFromClient != null)
	            {
	                aRecommendedDueDate = aDueDateFromClient;
	                aRecommendedDueDateFound = true;
	            }
	        }
	
	        // **********************************************************************************
	        // 3rd criteria
	        //     - Due Date from latest pending service orders of the loop with RTID or DSL
	        //     - Use the latest due date of all service orders on the loop to determine the earliest due date for each loop.
	        //     - The recommended due date will be:
	        //           - the latest due date on the loop with the Recommended RTID 
	        //           - the latest due date of any loop with any ATT DSL service
	        // **********************************************************************************
	        if (!aRecommendedDueDateFound)
	        {
	            if (aLoopsWithPendingSO != null)
	            {
	                String aRecommendedRTID = aResponseByNetworkType.getRecommendedRTID();
	                FacilityLoop2[] aLoops = aResponseByNetworkType.getLoops();
	                // 1st pass: find the latest Due Date on the loop with the Recommended RTID
	                if (aLoopsWithRTID != null
	                    && aRecommendedRTID != null
	                    && aLoops != null)
	                {
	                    for (int i=0; i < aLoops.length; i++) 
	                    {
	                        String aRTID = null;
	                        if (!OptHelper.isStringOptEmpty(aLoops[i].aRelatedCircuitID))
	                        {
	                            aRTID = aLoops[i].aRelatedCircuitID.theValue();
	                        }
	                        // check if loop has the recommended RTID
	                        if (aRTID != null 
	                            && aRTID.equalsIgnoreCase(aRecommendedRTID))
	                        {
	                            if (!OptHelper.isPendingServiceOrderSeqOptEmpty(aLoops[i].aPendingServiceOrders))
	                            {
	                                PendingServiceOrder[] aPendingServiceOrders = aLoops[i].aPendingServiceOrders.theValue();
	                                aRecommendedDueDate = findDueDate(aPendingServiceOrders);
	                                if (aRecommendedDueDate != null)
	                                {
	                                    aRecommendedDueDateFound = true;
	                                }
	                            }
	                        }
	                    }
	                }
	                // 2nd pass: find the latest due date of any loop with any ATT DSL service
	                if (!aRecommendedDueDateFound)
	                {
	                    FacilityLoop2[] aLoopsWithDSL = aResponseHelper.getFacilityLoopsWithDSL();
	                    if (aLoopsWithDSL != null)
	                    {
	                        for (int i=0; i < aLoopsWithDSL.length; i++)
	                        {
	                            ServiceItem[] aServiceItems = aLoopsWithDSL[i].aServices.theValue();
	                            for (int j=0; j < aServiceItems.length; j++)
	                            {
	                                if (!OptHelper.isStringOptEmpty(aServiceItems[j].aServiceType))
	                                {
	                                    // retrieve the service type
	                                    String aServiceType = aServiceItems[j].aServiceType.theValue();
	                                    // check if one of the service items is DSL
	                                    if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
	                                    	|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
	                                        || aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
	                                    {
	                                        ObjectProperty[] aServiceItemProperties = null;
	                                        ObjectPropertyManager aServiceItemOPM = new ObjectPropertyManager();
	                                        if (!OptHelper.isObjectPropertySeqOptEmpty(aServiceItems[j].aServiceItemProperties))
	                                        {
	                                            // check if DSL is owned by AT&T or not
	                                            aServiceItemProperties = aServiceItems[j].aServiceItemProperties.theValue();
	                                            aServiceItemOPM = new ObjectPropertyManager(aServiceItems[j].aServiceItemProperties.theValue());
	                                            if (checkDSLOwnedByATT(aServiceItemProperties))
	                                            {
	                                                if (!OptHelper.isPendingServiceOrderSeqOptEmpty(aLoopsWithDSL[i].aPendingServiceOrders))
	                                                {
	                                                    PendingServiceOrder[] aPendingServiceOrders = aLoopsWithDSL[i].aPendingServiceOrders.theValue();
	                                                    aRecommendedDueDate = findDueDate(aPendingServiceOrders);
	                                                    if (aRecommendedDueDate != null)
	                                                    {
	                                                        aRecommendedDueDateFound = true;
	                                                    }
	                                                }
	                                            }
	                                        }
	                                    }
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	            if (!aRecommendedDueDateFound)
	            {
	                // We haven't found a recommended due date, and we have
	                // pending SO(s), per BPR: RecommendedDueDate = "The Latest Due Date of any Loop."
	                ArrayList aPendingSOList = new ArrayList();
	                PendingServiceOrder[] aPendingServiceOrders = null;
	                FacilityLoop2[] aLoops = aResponseByNetworkType.getLoops();
	                try
	                {
	                    for (int i = 0; i < aLoops.length; i++)
	                    {
	                        if (!OptHelper.isPendingServiceOrderSeqOptEmpty(aLoops[i].aPendingServiceOrders))
	                        {
	                            for (int j = 0; j < aLoops[i].aPendingServiceOrders.theValue().length; j++)
	                            {
	                                aPendingSOList.add(aLoops[i].aPendingServiceOrders.theValue()[j]);
	                            }
	                        }
	                    }
	                }
	                catch (Exception e)
	                {}
	                if (aPendingSOList.size() > 0)
	                {
	                    aPendingServiceOrders = 
	                        (PendingServiceOrder[]) aPendingSOList.toArray(new PendingServiceOrder[aPendingSOList.size()]);
	                    if (aPendingServiceOrders != null)
	                    {
	                        aRecommendedDueDate = findDueDate(aPendingServiceOrders);
	                        if (aRecommendedDueDate != null)
	                        {
	                            aRecommendedDueDateFound = true;
	                        }
	                    }
	                }
	            }
	        }
        }
        
        FacilityLoop2[] aLoops = aResponseByNetworkType.getLoops();

        //after using the value of "NONE", we set it again to null. 
        //We don't want to show <circuitId>NONE</circuitId> in the response
        if(aLoops != null)
        {
	        for (int i = 0; i < aLoops.length; i++)
	        {
	        	if (!OptHelper.isPendingServiceOrderSeqOptEmpty(aLoops[i].aPendingServiceOrders))
	        	{
	        		PendingServiceOrder[] aPendingServiceOrders = aLoops[i].aPendingServiceOrders.theValue();
		        	
		        	try
		        	{
			        	if(aPendingServiceOrders[i].aCircuitId.theValue().equalsIgnoreCase("NONE"))
			
			        	{
			        		aPendingServiceOrders[i].aCircuitId = (StringOpt) IDLUtil.toOpt(StringOpt.class, null);
			        	}
		        	}
		        	catch(Throwable t)
		        	{
		        		//Null Circuit id. Don’t set it again
		        	}
	        	}	
	        }
        }

        // format recommended due date
        if (aRecommendedDueDateFound)
        {
            aResponseByNetworkType.setDueDate(aRecommendedDueDate);
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

	/**
     * Find the outward service order due date.
     * @param FacilityLoop2[] aLoops
     * @param EiaDate
     * @author Julius Sembrano
     */
    private EiaDate findOutwardServiceOrderDueDate(FacilityLoop2[] aLoops) 
    {
    	String aMethodName = "PublishValidateFacilityNotification: findOutwardServiceOrderDueDate()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    	
    	EiaDate aRecommendedDueDate = null;
    	EiaDate finalRecommendedDueDate = null;
		Date aFinalDueDate = null;
    	String outwardService = null;
    	
    	if(aLoops != null)
    	{
    		for(int i = 0; i < aLoops.length; i++)
    		{
    			try
    			{	    			
	    			if (!OptHelper.isPendingServiceOrderSeqOptEmpty(aLoops[i].aPendingServiceOrders))
	                {
	    				PendingServiceOrder[] aPendingServiceOrders = aLoops[i].aPendingServiceOrders.theValue();
	    				
	                    for (int j = 0; j < aPendingServiceOrders.length; j++)
	                    {
	                    	if (!OptHelper.isEiaDateOptNull(aPendingServiceOrders[j].aDueDate))
	                        {
	                            // initialize
	                            if (aRecommendedDueDate == null)
	                            {
	                            	finalRecommendedDueDate = aPendingServiceOrders[j].aDueDate.theValue();
	                                aRecommendedDueDate = aPendingServiceOrders[j].aDueDate.theValue();
	                                aFinalDueDate = new Date((int) aPendingServiceOrders[j].aDueDate.theValue().aYear,
	                                                         (int) aPendingServiceOrders[j].aDueDate.theValue().aMonth,
	                                                         (int) aPendingServiceOrders[j].aDueDate.theValue().aDay);
	                            }
	                            // convert into Date()
	                            Date aDueDate = new Date((int) aPendingServiceOrders[j].aDueDate.theValue().aYear,
	                                                     (int) aPendingServiceOrders[j].aDueDate.theValue().aMonth,
	                                                     (int) aPendingServiceOrders[j].aDueDate.theValue().aDay);
	                            // compare dates
	                            if (aDueDate.after(aFinalDueDate))
	                            {
	                                aFinalDueDate = aDueDate;
	                                aRecommendedDueDate = aPendingServiceOrders[j].aDueDate.theValue();
	                                
	                                try
	                                {
	                                	outwardService = aPendingServiceOrders[j].aCircuitId.theValue();
	                                }
	                                catch(Throwable t)
	                                {
	                                	// Null Circuit ID
	                                }
	                            }
	                        }
	                    }
	                } 
    			}
    			catch(Throwable t)
    			{
    				//Null pendingServiceorder
    			}
    		}
    	}
    	
    	if(aRecommendedDueDate != null 
    			&& outwardService!= null
    			&& outwardService.equalsIgnoreCase("NONE"))
    	{
    		finalRecommendedDueDate = aRecommendedDueDate;
    	}
    	aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    	return finalRecommendedDueDate;
	}

	/**
     * Find the latest date from the pending service orders.
     * @param PendingServiceOrder[] aPendingServiceOrders
     * @author Rene Duka
     */
    private EiaDate findDueDate(PendingServiceOrder[] aPendingServiceOrders)
    {
        String aMethodName = "PublishValidateFacilityNotification: findDueDate()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        EiaDate aRecommendedDueDate = null;
        Date aFinalDueDate = null;
        for (int i=0; i < aPendingServiceOrders.length; i++)
        {
            if (!OptHelper.isEiaDateOptNull(aPendingServiceOrders[i].aDueDate))
            {
                // initialize
                if (aRecommendedDueDate == null)
                {
                    aRecommendedDueDate = aPendingServiceOrders[i].aDueDate.theValue();
                    aFinalDueDate = new Date((int) aPendingServiceOrders[i].aDueDate.theValue().aYear,
                                             (int) aPendingServiceOrders[i].aDueDate.theValue().aMonth,
                                             (int) aPendingServiceOrders[i].aDueDate.theValue().aDay);
                }
                // convert into Date()
                Date aDueDate = new Date((int) aPendingServiceOrders[i].aDueDate.theValue().aYear,
                                         (int) aPendingServiceOrders[i].aDueDate.theValue().aMonth,
                                         (int) aPendingServiceOrders[i].aDueDate.theValue().aDay);
                // compare dates
                if (aDueDate.after(aFinalDueDate))
                {
                    aFinalDueDate = aDueDate;
                    aRecommendedDueDate = aPendingServiceOrders[i].aDueDate.theValue();
                }
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return aRecommendedDueDate;
    }
    
    /**
     * Formats proceed indicator.
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @author Rene Duka
     */
    private void formatProceedIndicator(
        String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType)
    {
        String aMethodName = "PublishValidateFacilityNotification: formatProceedIndicator()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // **********************************************************************************
        // Set to default value.
        //     - set to "FALSE"
        // **********************************************************************************
        aResponseByNetworkType.setOkToProceedIndicator(false);
               
        // **********************************************************************************
        // set to TRUE
        //     - if no conflict was found
        // **********************************************************************************
        if (!aResponseHelper.getConflictFound())
        {
            aResponseByNetworkType.setOkToProceedIndicator(true);
        }

        // **********************************************************************************
        // set to TRUE
        //     - if LFACS is not available
        // **********************************************************************************
        if (aResponseHelper.getErrorInLFACSIndicator())
        {
            aResponseByNetworkType.setOkToProceedIndicator(true);
        }

        // **********************************************************************************
        // set to TRUE
        //     - if we have a recommended RTID (including an RTID value of Blank)
        //       and there is no non-ATT DSL on any loop
        // **********************************************************************************
        if (!aResponseHelper.getConflictFound()
            && aResponseHelper.getRecommendedRTIDFound()
            && aResponseHelper.getNumberOfNonATTDSL() == 0)
        {
            aResponseByNetworkType.setOkToProceedIndicator(true);
        }
        
        // **********************************************************************************
        // set to IPDSLAM Proceed Indicator value
        //     - if network type is IP-CO/IP-RT
        // **********************************************************************************
        if ((aNetworkType.equalsIgnoreCase(NetworkType3Values.IPCO) 
            || aNetworkType.equalsIgnoreCase(NetworkType3Values.IPRT)))
            {
            	aResponseByNetworkType.setOkToProceedIndicator(aResponseHelper.getIPDSLAMProceedIndicator());
            }
	        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Formats messages.
     * @param String                                        aNetworkType
     * @param PublishValidateFacilityNotificationResponseIF aResponseByNetworkType
     * @param String                                        aClient
     * @author Rene Duka
     */
    private void formatMessages(
        String aNetworkType,
        PublishValidateFacilityNotificationResponseIF aResponseByNetworkType,
        String aClient)
    {
        String aMethodName = "PublishValidateFacilityNotification: formatMessages()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // Order Action Type is only valid for AMSS and mobilityCSI clients
        String aOrderActionType = null;
        if (aClient.equalsIgnoreCase("AMSS")
            || aClient.equalsIgnoreCase("mobilityCSI"))
        {
            aOrderActionType = aRequestHelper.getOrderActionType();
        }

        try
        {
            // initialize array list
            ArrayList aMessageList = new ArrayList();

            // Query the RTID_MESSAGES table using the Message[].Code and BisContext.aApplication to get
            // SEVERITY and TEXT.
            RtidMessagesTable aRtidMessagesTable = new RtidMessagesTable();
            RtidMessagesRow aRtidMessagesRow = null;

            // Scenario 1: No conflicting services at customer's location and U-verse ordering can proceed.
            //     - if the aProceedIndicator is set to TRUE, set the Message[].Code to “SC1-0000”.
            if (aResponseByNetworkType.getOkToProceedIndicator())
            {
                aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC1-0000",
                                                                   aClient,
                                                                   aOrderActionType,
                                                                   aProperties,
                                                                   aUtility);
                if (aRtidMessagesRow.getCODE().length() > 0)
                {
                    aMessageList.add(aRtidMessagesRow);
                }
            }

            // Scenario 2: The only conflicting service is a single AT&T provided ADSL & ISP service
            //             and U-verse ordering can proceed if the conflicting service is disconnected and that
            //             service is eligible for automated disconnect.
            //     - if there is a single DSL service found at the premise that is owned by ATT&T
            //       and no other conflicting services found, set the Message[].Code to “SC2-0000”.
            if (aResponseHelper.getNumberOfATTDSL() == 1
                && aResponseHelper.getNumberOfNonATTDSL() == 0
                && (!aResponseHelper.getConflictFound() || 
                		(((aNetworkType.equalsIgnoreCase(NetworkType3Values.IPCO) || aNetworkType.equalsIgnoreCase(NetworkType3Values.IPRT)) 
                				&& aResponseByNetworkType.getOkToProceedIndicator())))
                )
            {
                aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC2-0000",
                                                                   aClient,
                                                                   aOrderActionType,
                                                                   aProperties,
                                                                   aUtility);
                if (aRtidMessagesRow.getCODE().length() > 0)
                {
                    aMessageList.add(aRtidMessagesRow);
                }
            }

            // Scenario 3: There is more than one conflicting service and all conflicting services are AT&T provided ADSL & ISP service,
            //             the U-verse cannot proceed unless disconnects for all conflicting services will occur.
            //     - if there are multiple DSL services found at the premise that is owned by ATT&T
            //       and no other conflicting services found, set the Message[].Code to “SC3-0000”.
            if (aResponseHelper.getNumberOfATTDSL() > 1
                && aResponseHelper.getNumberOfNonATTDSL() == 0
                && (!aResponseHelper.getConflictFound() || 
                		(((aNetworkType.equalsIgnoreCase(NetworkType3Values.IPCO) || aNetworkType.equalsIgnoreCase(NetworkType3Values.IPRT)) 
                				&& aResponseByNetworkType.getOkToProceedIndicator())))
                )	
            {
                if (!aClient.equalsIgnoreCase("OMS"))
                {
                    boolean aDSLOnSameAccount = checkDSLOnSameAccount(aResponseByNetworkType.getAccounts());
                    if (aDSLOnSameAccount)
                    {
                        aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC3A-0000",
                                                                           aClient,
                                                                           aOrderActionType,
                                                                           aProperties,
                                                                           aUtility);
                    }
                    else
                    {
                        aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC3B-0000",
                                                                          aClient,
                                                                          aOrderActionType,
                                                                          aProperties,
                                                                          aUtility);
                    }
                    if (aRtidMessagesRow.getCODE().length() > 0)
                    {
                        aMessageList.add(aRtidMessagesRow);
                    }
                }
                else
                {
                    aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC3-0000",
                                                                      aClient,
                                                                      aOrderActionType,
                                                                      aProperties,
                                                                      aUtility);
                    if (aRtidMessagesRow.getCODE().length() > 0)
                    {
                        aMessageList.add(aRtidMessagesRow);
                    }
                }
            }

            // Scenario 4: One or more non-AT&T provided ADSL services, non AT&T provided ISP services or wholesale services exist
            //             and U-verse cannot proceed until the services are disconnected.
            //     - if there is a at least one conflicting service found at the premise, set the Message[].Code to “SC4-0000”.
        	if (!aResponseByNetworkType.getOkToProceedIndicator())
        	{
                aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC4-0000",
									                               aClient,
									                               aOrderActionType,
									                               aProperties,
									                               aUtility);
				if (aRtidMessagesRow.getCODE().length() > 0)
				{
					aMessageList.add(aRtidMessagesRow);
				}
        		
        	}
            
//            if ((aResponseHelper.getConflictFound()            		
//                 || aResponseHelper.getNumberOfNonATTDSL() > 0))
//            {
//                if (aResponseHelper.getFiberInPremise())
//                {
//                    if (!aClient.equalsIgnoreCase("AMSS"))
//                    {
//                        aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC4A-0000",
//                                                                          aClient,
//                                                                          aOrderActionType,
//                                                                          aProperties,
//                                                                          aUtility);
//                        if (aRtidMessagesRow.getCODE().length() > 0)
//                        {
//                            aMessageList.add(aRtidMessagesRow);
//                        }
//                    }
//                }
//                else
//                {
//                	if (!aResponseByNetworkType.getOkToProceedIndicator())
//                	{
//                        aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC4-0000",
//											                               aClient,
//											                               aOrderActionType,
//											                               aProperties,
//											                               aUtility);
//						if (aRtidMessagesRow.getCODE().length() > 0)
//						{
//							aMessageList.add(aRtidMessagesRow);
//						}
//                		
//                	}
//                }
//            }

            // Scenario 5: RM BIS unable to retrieve information from LFACS and SM BIS,
            //             U-verse order can only proceed using data retrieve from OVALS.
            //    - if there is a problem with the LFACS and SM BIS interface, set the Message[].Code to “SC5-0000”.
            if ((aResponseHelper.getErrorInLFACSIndicator() || aResponseHelper.getErrorInSMIndicator())
                && !aResponseByNetworkType
                                         .getClass()
                                         .getName()
                                         .equalsIgnoreCase(PublishValidateFacilityNotificationResponseINVALIDNTI.myClassName))
            {
                aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC5-0000",
                                                                   aClient,
                                                                   aOrderActionType,
                                                                   aProperties,
                                                                   aUtility);
                if (aRtidMessagesRow.getCODE().length() > 0)
                {
                    aMessageList.add(aRtidMessagesRow);
                }
            }

            // Scenario 6: DISH service found at the customer's location.
            //     - if there is a DISH service found at the premise, set the Message[].Code to “SC6-0000”.
            if (aResponseHelper.getDISHFound())
            {
                aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC6-0000",
                                                                  aClient,
                                                                  aOrderActionType,
                                                                  aProperties,
                                                                  aUtility);
                if (aRtidMessagesRow.getCODE().length() > 0)
                {
                    aMessageList.add(aRtidMessagesRow);
                }
            }

            // Scenario 7: Dial-up service found at the customer's location.
            //     - if there is a dial-up service found at the premise, set the Message[].Code to “SC7-0000”.
            if (aResponseHelper.getDialUpFound())
            {
                aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC7-0000",
                                                                  aClient,
                                                                  aOrderActionType,
                                                                  aProperties,
                                                                  aUtility);
                if (aRtidMessagesRow.getCODE().length() > 0)
                {
                    aMessageList.add(aRtidMessagesRow);
                }
            }

            // Scenario 8: DSL abuse detected at the customer's location.
            //     - o  If there is a DSL abuse detected at the premise, set the Message[].Code to “SC8-0000”.
            if (aResponseHelper.getDSLMisuse())
            {
                aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC8-0000",
                                                                  aClient,
                                                                  aOrderActionType,
                                                                  aProperties,
                                                                  aUtility);
                if (aRtidMessagesRow.getCODE().length() > 0)
                {
                    aMessageList.add(aRtidMessagesRow);
                }
            }

            // Scenario 9: U-verse exists.
            //     - o  If U-verse is detected at the premise, set the Message[].Code to “SC9-0000”.
            if (aResponseHelper.getUverseFound())
            {
                if (aRequestHelper.isGraniteNtis(aNetworkType) && 
                    aResponseByNetworkType.isGraniteUVerseFound(aNetworkType) == false)
                {
                    // Granite has determined no UVerse found for this NetworkType...
                }
                else if (aNetworkType.equalsIgnoreCase(NetworkType2Values.FTTNBP)
                	&& aRequestHelper.getOrderActionType().equalsIgnoreCase("CHANGE"))
                {
                	// CR24671: Existing U-Verse found on a loop when the Order Type is Change 
                	// and the network transport type is FTTN-BP will not be considered a conflict. 
                	// We do not send SC9 Error message
                }
                else
                {
                    aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC9-0000",
                                                                      aClient,
                                                                      aOrderActionType,
                                                                      aProperties,
                                                                      aUtility);
                    if (aRtidMessagesRow.getCODE().length() > 0)
                    {
                        aMessageList.add(aRtidMessagesRow);
                    }
                }
            }

            // Scenario 10: No LS capable loop found at the premise for FTTN and one LS capable loop found at the premise for FTTNBP
            //     - o  If no LS capable loop is found at the premise for FTTN and one LS capable loop found at the premise for FTTNBP,
            //          set the Message[].Code to “SC10-0000”.
            if ( !aResponseHelper.getErrorInLFACSIndicator())
            {
             	if ((aNetworkType.equals(NetworkType2Values.FTTN)&& aResponseHelper.getNumberOfBBPLoops()< 1)
               	   || (aNetworkType.equals(NetworkType2Values.FTTNBP)&& aResponseHelper.getNumberOfBBPLoops()< 2 && aResponseByNetworkType.getTerminalExhaustIndicator())
             	   || aResponseHelper.getSiteNotFoundInGranite(aNetworkType))
                {
             		aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC10-0000",
                                                                      aClient,
                                                                      aOrderActionType,
                                                                      aProperties,
                                                                      aUtility);
    	    	 	if (aRtidMessagesRow.getCODE().length() > 0)
    	    	 	{
    	    	 		aMessageList.add(aRtidMessagesRow);
    	    	 	}
                }
            }

            // Scenario 11: Invalid networkType
            //     - o If there is an invalid networkType in aNtis list, set the Message[].Code to “SC11-0000”.
            if (aResponseByNetworkType.getClass()
                                      .getName()
                                      .equalsIgnoreCase(PublishValidateFacilityNotificationResponseINVALIDNTI.myClassName))
            {
                // This is an INVALID NTI class object
                aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC11-0000",
                                                                  aClient,
                                                                  aOrderActionType,
                                                                  aProperties,
                                                                  aUtility);
                if (aRtidMessagesRow.getCODE().length() > 0)
                {
                    aMessageList.add(aRtidMessagesRow);
                }
            }
            
            // R9 Code
            // Scenario 12: No BBP Message
            //     - o If the loop is not BBP conditioned or COND field is missing on a P Committed or S Committed, set the Message[].Code to “SC12-0000”.
            if ( aResponseHelper.getBBPMissingMsgIndicator())
            {
            	aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC12-0000",
                                                                  aClient,
                                                                  aOrderActionType,
                                                                  aProperties,
                                                                  aUtility);
            	if (aRtidMessagesRow.getCODE().length() > 0)
            	{
            			aMessageList.add(aRtidMessagesRow);
                }
            }

            // Scenario 13: Load Coil Message
            //     - o Load coil present on a Lightspeed capable loop, set the Message[].Code to “SC13-0000”.
            if (aResponseHelper.getLoadCoilMsgIndicator())
            {
            	aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC13-0000",
                                                                  aClient,
                                                                  aOrderActionType,
                                                                  aProperties,
                                                                  aUtility);
            	if (aRtidMessagesRow.getCODE().length() > 0)
            	{
            		aMessageList.add(aRtidMessagesRow);
                }
            }
            
            // Scenario 14: T Commit Message
            //     - o Terminal Exhaust based on the TCOMBBPCNT, not enough T-Commit loops available for U-Verse, set the Message[].Code to “SC14-0000”.
            if (aResponseHelper.getTCommitIndicator() && aNetworkType.equals(NetworkType2Values.FTTNBP))
            {
                aRtidMessagesRow = aRtidMessagesTable.retrieveRow("SC14-0000",
                                                                  aClient,
                                                                  aOrderActionType,
                                                                  aProperties,
                                                                  aUtility);
                if (aRtidMessagesRow.getCODE().length() > 0)
                {
                    aMessageList.add(aRtidMessagesRow);
                }
            }

            // format messages
            if (aMessageList.size() > 0)
            {
                RtidMessagesRow[] aRtidMessagesRows = (RtidMessagesRow[]) aMessageList.toArray(new RtidMessagesRow[aMessageList.size()]);
                Message[] aMessages = new Message[aRtidMessagesRows.length];
                for (int i=0; i < aRtidMessagesRows.length; i++)
                {
                    aMessages[i] = new Message(aRtidMessagesRows[i].getCODE(),
                                               aRtidMessagesRows[i].getSEVERITY(),
                                               aRtidMessagesRows[i].getTEXT());
                }
                aResponseByNetworkType.setMessages(aMessages);
            }
        }
        catch (Exception e)
        {
            // log: message
            aUtility.log(LogEventId.INFO_LEVEL_1, "Exception in PublishValidateFacilityNotification: formatMessages()");
        }
        finally
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
    }

    /**
     * Examines services from loops.
     *
     * @author Rene Duka
     */
    private void examineServicesFromLoops()
    {
        String aMethodName = "PublishValidateFacilityNotification: examineServicesFromLoops()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        boolean aDSLAutoDisconnectSet = false;

        FacilityLoop2[] aLoops = null;
        if (aResponseHelper.getFacilityLoops()!= null)
        {
            aLoops = aResponseHelper.getFacilityLoops();
            // find the LS-conditioned loop with DSL owned by AT&T
            for (int i=0; i < aLoops.length; i++)
            {
                // check if there is an RTID on the loop (LS-conditioned)
                if (!OptHelper.isStringOptEmpty(aLoops[i].aRelatedCircuitID))
                {
                    // retrieve services on the loop
                    if (!OptHelper.isServiceItemSeqOptEmpty(aLoops[i].aServices))
                    {
                        ServiceItem[] aServiceItems = aLoops[i].aServices.theValue();
                        for (int j=0; j < aServiceItems.length; j++)
                        {
                            if (!OptHelper.isStringOptEmpty(aServiceItems[j].aServiceType))
                            {
                                // set the value of conflicting service found indicator to TRUE is a conflicting service is found
                                if (aServiceItems[j].aConflictingServiceIndicator)
                                {
                                    //aConflictingServiceFound = true;
                                }
                                // retrieve the service type
                                String aServiceType = aServiceItems[j].aServiceType.theValue();
                                // check if one of the service items is DSL
                                if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
                                	|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
                                    || aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
                                {
                                    ObjectProperty[] aServiceItemProperties = null;
                                    ObjectPropertyManager aServiceItemOPM = new ObjectPropertyManager();
                                    if (!OptHelper.isObjectPropertySeqOptEmpty(aServiceItems[j].aServiceItemProperties))
                                    {
                                        aServiceItemProperties = aServiceItems[j].aServiceItemProperties.theValue();
                                        aServiceItemOPM = new ObjectPropertyManager(aServiceItemProperties);
                                        boolean bATTOwnedByATT  = checkDSLOwnedByATT(aServiceItemProperties);
                                        if (bATTOwnedByATT)
                                        {
                                            if (!aDSLAutoDisconnectSet)
                                            {
                                                // set DSL Auto-Disconnect indicator to TRUE
                                                aServiceItemOPM.addWithOverride(new ObjectProperty(ServiceItemPropertyValues.DSL_AUTO_DISCONNECT_INDICATOR, "true"));
                                                aDSLAutoDisconnectSet = true;
                                            }
                                            aServiceItems[j].aServiceItemProperties.theValue(aServiceItemOPM.toArray());
                                        }
                                    }
                                }
                            } // no service type in the loop
                        } // examine each service
                    } // no services found in the loop
                }
            } // examine each loop

            // find the first loop with DSL owned by AT&T
            for (int i=0; i < aLoops.length; i++)
            {
                // retrieve services on the loop
                if (!OptHelper.isServiceItemSeqOptEmpty(aLoops[i].aServices))
                {
                    ServiceItem[] aServiceItems = aLoops[i].aServices.theValue();
                    for (int j=0; j < aServiceItems.length; j++)
                    {
                        if (!OptHelper.isStringOptEmpty(aServiceItems[j].aServiceType))
                        {
                            // set the value of conflicting service found indicator to TRUE is a conflicting service is found
                            if (aServiceItems[j].aConflictingServiceIndicator)
                            {
                                //aConflictingServiceFound = true;
                            }
                            // retrieve the service type
                            String aServiceType = aServiceItems[j].aServiceType.theValue();
                            // check if one of the service items is DSL
                            if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
                           		|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
                                || aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
                            {
                                ObjectProperty[] aServiceItemProperties = null;
                                ObjectPropertyManager aServiceItemOPM = new ObjectPropertyManager();
                                if (!OptHelper.isObjectPropertySeqOptEmpty(aServiceItems[j].aServiceItemProperties))
                                {
                                    aServiceItemProperties = aServiceItems[j].aServiceItemProperties.theValue();
                                    aServiceItemOPM = new ObjectPropertyManager(aServiceItemProperties);
                                    boolean bATTOwnedByATT  = checkDSLOwnedByATT(aServiceItemProperties);
                                    if (bATTOwnedByATT)
                                    {
                                        if (!aDSLAutoDisconnectSet)
                                        {
                                            // set DSL Auto-Disconnect indicator to TRUE
                                            aServiceItemOPM.addWithOverride(new ObjectProperty(ServiceItemPropertyValues.DSL_AUTO_DISCONNECT_INDICATOR, "true"));
                                            aDSLAutoDisconnectSet = true;
                                        }
                                        aServiceItems[j].aServiceItemProperties.theValue(aServiceItemOPM.toArray());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Examines loops from LFACS.
     * @param ArrayList aWTNListFromLFACS
     * @param ArrayList aLoopsNoBTN
     * @author Rene Duka
     */
    private void examineLoopsFromLFACS(ArrayList aWTNListFromLFACS)
    {
        String aMethodName = "PublishValidateFacilityNotification: examineLoopsFromLFACS()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        if (aResponseHelper.getFacilityLoops() != null)
        {
            FacilityLoop2[] aFacilityLoops = aResponseHelper.getFacilityLoops();
            // check if no WTN is returned from LFACS
            for (int i=0 ; i < aFacilityLoops.length ; i++)
            {
                if (!OptHelper.isStringOptEmpty(aFacilityLoops[i].aWorkingTelephoneNumber))
                {
                	// Loop from LFACS has WTN
                    //      - add FacilityLoop[i].WorkingTelephoneNumber to WTN from LFACS array list
                    if (!aWTNListFromLFACS.contains(aFacilityLoops[i].aWorkingTelephoneNumber.theValue()))
                	{
                    	aWTNListFromLFACS.add(aFacilityLoops[i].aWorkingTelephoneNumber.theValue());
                	}
                }
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Accesses SM Client.
     *
     * @param BisContext aContext
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
    public void accessSMClient(BisContext aContext)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound
    {
        String aMethodName = "PublishValidateFacilityNotification: accessSMClient()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        if (aSMClient == null)
        {
            try
            {
                //connect to SM Bean
                aSMClient = new SmClient();
//                (String) aProperties.get("SM_PROVIDER_URL"),
//                                         (String) aProperties.get("SM_BIS_NAME"),
//                                         (String) aProperties.get("BIS_NAME"),
//                                         (String) aProperties.get("INITIAL_CONTEXT_PROPERTIES_FILE"));
            }
            catch (NullPointerException npe)
            {
            	aUtility.throwException(ExceptionCode.ERR_RM_SMCLIENT_EXCEPTION,
                                        TranBase.formatInvalidData(ObjectKey.class,
                                                                   "aServiceHandle.aValue"),
                                        (String) aProperties.get("BIS_NAME"),
                                        Severity.UnRecoverable);
            }
        }

        if (aSMBean == null)
        {
            try
            {
                aSMBean = aSMClient.getSmBean(aContext, aUtility);
            }
            catch (Exception e)
            {
            	aUtility.throwException(ExceptionCode.ERR_RM_SMCLIENT_EXCEPTION,
                                        "Exception while trying to get SMBean reference " + e.getMessage(),
                                        (String) aProperties.get("BIS_NAME"),
                                        Severity.UnRecoverable);
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
    }

    /**
     * Calls SM:retrieveSubscriptionAccountsForService().
     *
     * @param BisContext aContext
     * @param String     aWorkingTelephoneNumber
     * @return SubscriptionAccountReturn
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
    public SubscriptionAccountReturn callRSAFS(
        BisContext aContext,
        String aWorkingTelephoneNumber)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound
    {
        String aMethodName = "PublishValidateFacilityNotification: callRSAFS()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // initialize return structure for SM:retrieveSubscriptionAccountsForService()
        SubscriptionAccountReturn aResult = null;

        // get SM bean
        if (aSMBean == null)
        {
            // access SM bean
            try
            {
                aSMBean = aSMClient.getSmBean(aContext, aUtility);
            }
            catch (Exception e)
            {
                aUtility.throwException(ExceptionCode.ERR_RM_SMCLIENT_EXCEPTION,
                                        "Exception while trying to get SMBean reference " + e.getMessage(),
                                        (String) aProperties.get("BIS_NAME"),
                                        Severity.UnRecoverable);
            }
        }

        // format parameters to call SM:retrieveSubscriptionAccountsForService()
        // Parameters: BisContext aContext
        //             ObjectKey  aServiceHandle (WTN)
        //             String     aAffiliate
        //             SubscriptionAccountInformationTypeOpt aSubscriptionAccountInformationType
        //             StringOpt  aSortingOrder
        //             EiaDateOpt aAsOfDate
        ObjectKey aServiceHandle = new ObjectKey(aWorkingTelephoneNumber, BisTypesObjectKeyKind.WORKINGTELEPHONENUMBER);
        SubscriptionAccountInformationType aInfoType = SubscriptionAccountInformationType.CustomerServiceInformation;

        // call SM:retrieveSubscriptionAccountsForService()
        try
        {
            aUtility.log(LogEventId.REMOTE_CALL,
                    (String) aProperties.get("SM_PROVIDER_URL" ),
                    (String) aProperties.get("SM_BIS_NAME"),
                    (String) aProperties.get("SM_BIS_NAME"),
                    "SM::retrieveSubscriptionAccountsForService");

            aResult = aSMBean.retrieveSubscriptionAccountsForService(
                            aContext,
                            aServiceHandle,
                            Affiliate.TELCO,
                            aInfoType,
                            (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                            (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, null));
            
            // handle ExceptionData returned by SM 
            if ((aResult.aExceptionData != null) && (aResult.aExceptionData.length > 0))
            {
            	aUtility.log(LogEventId.INFO_LEVEL_1, "SM::retrieveSubscriptionAccountsForService returned an exception(s).");
            	
            	for (int i=0; i < aResult.aExceptionData.length; i++)
            	{
            		// log all the ExceptionData returned by SM
            		aUtility.log(LogEventId.INFO_LEVEL_2, "ExceptionCode[" + i + "]: " + aResult.aExceptionData[i].aCode);
                	aUtility.log(LogEventId.INFO_LEVEL_2, "ExceptionMessage[" + i + "]: " + aResult.aExceptionData[i].aDescription);
            	}
            }
        }
/*        catch (RemoteException e)
        {
            aUtility.throwException(ExceptionCode.ERR_RM_SMCLIENT_EXCEPTION,
                                    "RemoteException when trying to call retrieveSubscriptionAccountsForService from SM: "
                                        + e.getMessage(),
                                    (String) aProperties.get("BIS_NAME"),
                                    Severity.UnRecoverable);
        }*/
        catch (NotImplemented e)
        {
            throwSmException(aContext, aServiceHandle, e.aExceptionData);
        }
        catch (SystemFailure e)
        {
            throwSmException(aContext, aServiceHandle, e.aExceptionData);
        }
        catch (InvalidData e)
        {
            throwSmException(aContext, aServiceHandle, e.aExceptionData);
        }
        catch (ObjectNotFound e)
        {
        	throwSmException(aContext, aServiceHandle, e.aExceptionData);
        }
        catch (DataNotFound e)
        {
        	aUtility.log(LogEventId.INFO_LEVEL_2, "ExceptionCode: " + e.aExceptionData.aCode);
            aUtility.log(LogEventId.INFO_LEVEL_2, "ExceptionMessage: " + e.aExceptionData.aDescription);
                        
            // handle exception
            aResponseHelper.handleException(e.aExceptionData.aCode,
            		e.aExceptionData.aDescription,
                            (String) aProperties.get("BIS_NAME").toString(),
                            aRequestHelper);
            
            aResponseHelper.setErrorInSMIndicator(true);
        }
        catch (AccessDenied e)
        {
            throwSmException(aContext, aServiceHandle, e.aExceptionData);
        }
        catch (BusinessViolation e)
        {
            throwSmException(aContext, aServiceHandle, e.aExceptionData);
            aResult = null; // hit BusinessRule 1 if we return, no CSI
        }
        finally
        {
            aUtility.log(LogEventId.REMOTE_RETURN,
                         (String) aProperties.get("SM_PROVIDER_URL"),
                         (String) aProperties.get("SM_BIS_NAME"),
                         (String) aProperties.get("SM_BIS_NAME"),
                         "SM::retrieveSubscriptionAccountsForService");
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
        return aResult;
    }

    /**
     * Extracts billing telephone number.
     *
     * @param BisContext aContext
     * @param SubscriptionAccountReturn aSubscriptionAccountReturn
     * @return String
     *
     * @author Rene Duka
     */
    private String extractBTN(
        BisContext aContext,
        SubscriptionAccountReturn aSubscriptionAccountReturn)
    {
        String aMethodName = "PublishValidateFacilityNotification: extractBTN()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        String aBTN = null;
        // extract the Billing Telephone Number
        for (int i = 0; i < aSubscriptionAccountReturn.aSubscriptionAccounts.length; i++)
        {
            if (aSubscriptionAccountReturn.aSubscriptionAccounts[i].aDefaultBillingAccount.aBillingAccountHandle.aValue != null)
            {
                aBTN = aSubscriptionAccountReturn.aSubscriptionAccounts[i].aDefaultBillingAccount.aBillingAccountHandle.aValue.substring(0, 10);
                aUtility.log(LogEventId.INFO_LEVEL_2, "BTN : " + aBTN);
                break;
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        return aBTN;
    }

    /**
     * Extracts account information.
     *
     * @param BisContext aContext
     * @param SubscriptionAccountReturn aSubscriptionAccountReturn
     * @param ArrayList aWTNListFromLFACS
     * @param ArrayList aWTNListAlreadyProcessed
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
    private BillingAccount2 extractAccount(
        BisContext aContext,
        SubscriptionAccountReturn aSubscriptionAccountReturn,
        ArrayList aWTNListFromLFACS,
        ArrayList aWTNListAlreadyProcessed)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound
    {
        String aMethodName = "PublishValidateFacilityNotification: extractAccount()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // format the region
        String aRegion = aRequestHelper.getRegion();

        // build empty BillingAccount
        BillingAccount2 aBillingAccount = new BillingAccount2((StringOpt) IDLUtil.toOpt(StringOpt.class, null),
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
                                                             (FacilityLoop2SeqOpt) IDLUtil.toOpt(FacilityLoop2SeqOpt.class, null));

        // initialize service item properties for DSL to null (CR25554)
        String aDSLServiceProvider = null;
        String aInternetServiceProvider = null;
        String aDSLAutoDisconnectIndicator = "false";
        String aDSLMisuseIndicator = "false";
        String aMemberID = null;
        String aPortal = null;
        String aSpeed = null;
        String aUBAN = null;
        boolean aDSLProductFound = false;
        boolean bDSLServiceProviderFound = false;
        boolean aDSLSpeedPropertyFound = false; 
        boolean aUBANPropertyFound = false;
        boolean aDishServiceIndicator = false;
        boolean aGiftBillingIndicator = false;
        boolean aHandicapIndicator = false;
        boolean aNonPublishedIndicator = false;
        
        String aProductSubscriptionLevelString = "Product Subscription -> ";

        // format SM BIS fields
        aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Accounts ...");
        for (int i = 0; i < aSubscriptionAccountReturn.aSubscriptionAccounts.length; i++)
        {
            // extract subscription account
            SubscriptionAccount aSubscriptionAccount = aSubscriptionAccountReturn.aSubscriptionAccounts[i];
            int aSubscriptionAccountCounter = i+1;
            aUtility.log(LogEventId.INFO_LEVEL_1, "Subscription Account: " + aSubscriptionAccountCounter + " of " + aSubscriptionAccountReturn.aSubscriptionAccounts.length);

            // ************************************************************************************
            // extract default billing account
            // ************************************************************************************

            // set billing telphone number
            // set customer code (last 3 digits in a 13-digit billing telephone number)
            String aBillingTelephoneNumber = aSubscriptionAccount.aDefaultBillingAccount.aBillingAccountHandle.aValue;
            if (aBillingTelephoneNumber.length() > 0)
            {
                if (aBillingTelephoneNumber.length() == 13)
                {
                    aBillingAccount.aBillingTelephoneNumber.theValue(aBillingTelephoneNumber);
                    aBillingAccount.aCustomerCode.theValue(aBillingTelephoneNumber.substring(10, 13));
                }
            }

            // set customer name
            String aCustomerName = aSubscriptionAccount.aDefaultBillingAccount.aBillingPartyHandle.aValue;
            if (aCustomerName.length() > 0) {
                aBillingAccount.aCustomerName.theValue(aCustomerName.trim());
            }

            // set account status
            String aAccountStatus = aSubscriptionAccount.aDefaultBillingAccount.aStatus;
            if (aAccountStatus.length() > 0) {
                aBillingAccount.aAccountStatus.theValue(aAccountStatus);
            }

            // set DSL Misuse indicator
            // set to TRUE if account status is "SUSP" and StatusReasonCode is either one of the following:
            //    - PLFR, PLHK, PLSP, PLVR, PLVT
            if (aAccountStatus.equalsIgnoreCase("SUSP"))
            {
                // ************************************************************************************
                // extract the properties in the default billing account
                // ************************************************************************************
                aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Default Billing Account -> Properties ...");
                if (aSubscriptionAccount.aDefaultBillingAccount.aProperties.length > 0)
                {
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Subscription Account -> Default Billing Account -> Properties found !!!");

                    ObjectPropertyManager aBA_ObjectPropertyManager = new ObjectPropertyManager(aSubscriptionAccount.aDefaultBillingAccount.aProperties);
                    // check for "StatusReasonCode"
                    if (aBA_ObjectPropertyManager.getValue("StatusReasonCode") != null)
                    {
                        String aStatusReasonCode = aBA_ObjectPropertyManager.getValue("StatusReasonCode");
                        if (((String) aProperties.get("STATUS_REASON_CODE")).indexOf(":" + aStatusReasonCode.toUpperCase()+ ":") >= 0)
                        
                        {
                            aDSLMisuseIndicator = "true";
                            aResponseHelper.setDSLMisuse(true);
                        }
                    }
                }
                else
                {
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Subscription Account -> Default Billing Account -> Properties is empty !!!");
                }
            }

            // ************************************************************************************
            // extract subscription account handle
            // ************************************************************************************
            ObjectKey aSubscriptionAccountHandle =  aSubscriptionAccount.aSubscriptionAccountHandle;
            aUtility.log(LogEventId.INFO_LEVEL_2, "SubscriptionAccountHandle[].aKind : " + aSubscriptionAccountHandle.aKind);
            aUtility.log(LogEventId.INFO_LEVEL_2, "SubscriptionAccountHandle[].aValue: " + aSubscriptionAccountHandle.aValue);

            // =====================================================================================
            // loop through all the product subscriptions in the subscription account
            // << aSubscriptionAccount.aProductSubscriptions >>
            // =====================================================================================
            aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Product Subscriptions ...");
            for (int j = 0; j < aSubscriptionAccount.aProductSubscriptions.length; j++)
            {
                // ************************************************************************************
                // extract product subscription
                // ************************************************************************************
                ProductSubscription aProductSubscription = aSubscriptionAccount.aProductSubscriptions[j];
                int aProductSubscriptionCounter = j+1;
                // log
                aUtility.log(LogEventId.INFO_LEVEL_1,
                             "Subscription Account : " + aSubscriptionAccountCounter
                                 + " : Product Subscription: " + aProductSubscriptionCounter + " of " + aSubscriptionAccount.aProductSubscriptions.length);
                
                // ************************************************************************************
                // loop through all the product identifiers in the product subscription
                //     - DISH Service Indicator
                //     - Non-Published Indicator (for SE)
                // ************************************************************************************
                setDISHServiceAndNonPublishedIndicators (aProductSubscription, aProductSubscriptionLevelString);
                
                // ************************************************************************************
                // extract the product subscription handle
                // ************************************************************************************
                aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Product Subscription -> Product Subscription Handle ...");
                if (!OptHelper.isObjectKeyOptEmpty(aProductSubscription.aProductSubscriptionHandle))
                {
                    ObjectKey aProductSubscriptionHandle =  aProductSubscription.aProductSubscriptionHandle.theValue();
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_2, "ProductSubscriptionHandle.aKind : " + aProductSubscriptionHandle.aKind);
                    aUtility.log(LogEventId.INFO_LEVEL_2, "ProductSubscriptionHandle.aValue: " + aProductSubscriptionHandle.aValue);
                    boolean aDialUpServiceIndicator = false;
                    if (aProductSubscriptionHandle.aKind.equalsIgnoreCase(BisTypesObjectKeyKind.PRODUCTNAME))
                    {
                        // set DSL found indicator
                    	if (((String) aProperties.get("PRODUCT_NAME_NON_SE")).indexOf(":" + aProductSubscriptionHandle.aValue.toUpperCase() + ":") >= 0)  	
                        {
                            aDSLProductFound = true;
                        }

                        // CR 16976: set DSL found indicator for SE
                    	if (((String) aProperties.get("PRODUCT_NAME_SE")).indexOf(":" + aProductSubscriptionHandle.aValue.toUpperCase() + ":") >= 0)
                        {	                    		
                            aDSLSpeedPropertyFound = true;
                            // format Speed
                            aSpeed = (String) aProperties.get("PRODUCT_SPEED_" + aProductSubscriptionHandle.aValue);
                           
                        }
                    	                                           	
                        // set dial-up service indicator
                        else if (aProductSubscriptionHandle.aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.PRODUCT_SBC_DIALUP))
                        {
                            aDialUpServiceIndicator = true;
                        }
                    }
                    aBillingAccount.aDialUpServiceIndicator.theValue(aDialUpServiceIndicator);
                }
                else
                {
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Subscription Account -> Product Subscription -> Product Subscription Handle is empty !!!");
                }

                // ************************************************************************************
                // extract the properties in the product subscription
                // ************************************************************************************
                aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Product Subscription -> Properties ...");
                if (aProductSubscription.aProperties.length > 0)
                {
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Subscription Account -> Product Subscription -> Properties found !!!");

                    ObjectPropertyManager aPS_ObjectPropertyManager = new ObjectPropertyManager(aProductSubscription.aProperties);
                    // check for "SpeedBasic"
                    if (aPS_ObjectPropertyManager.getValue("SpeedBasic") != null)
                    {
                        aSpeed = aPS_ObjectPropertyManager.getValue("SpeedBasic");
                        aDSLSpeedPropertyFound = true;
                    }
                    // check for "SpeedStatic"
                    else if (aPS_ObjectPropertyManager.getValue("SpeedStatic") != null)
                    {
                        aSpeed = aPS_ObjectPropertyManager.getValue("SpeedStatic");
                        aDSLSpeedPropertyFound = true;
                    }
                    // check for "SpeedBusiness"
                    else if (aPS_ObjectPropertyManager.getValue("SpeedBusiness") != null)
                    {
                        aSpeed = aPS_ObjectPropertyManager.getValue("SpeedBusiness");
                        aDSLSpeedPropertyFound = true;
                    }
                    // check for "SpeedEnhanced"
                    else if (aPS_ObjectPropertyManager.getValue("SpeedEnhanced") != null)
                    {
                        aSpeed = aPS_ObjectPropertyManager.getValue("SpeedEnhanced");
                        aDSLSpeedPropertyFound = true;
                    }
                    // check for "UniveralBillingAccountNumber"
                    if (aPS_ObjectPropertyManager.getValue("UniveralBillingAccountNumber") != null)
                    {
                        if (aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3)
                        		|| aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING))
                        {
	                        aUBAN = aPS_ObjectPropertyManager.getValue("UniveralBillingAccountNumber");
	                        aUBANPropertyFound = true;
	                       // aResponseHelper.setUniversalBillingAccountNumber(aUBAN);
		                    aUtility.log(LogEventId.INFO_LEVEL_1, "UBAN -> " + aUBAN);
                        }
                    }
                    
                    // check for "UniversalBillingAccountNumber"
                    if (aPS_ObjectPropertyManager.getValue("UniversalBillingAccountNumber") != null)
                    {
                        if (aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3)
                        		|| aRequestHelper.getTransactionType().equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING))
                        {
	                        aUBAN = aPS_ObjectPropertyManager.getValue("UniversalBillingAccountNumber");
	                        aUBANPropertyFound = true;
	                      //  aResponseHelper.setUniversalBillingAccountNumber(aUBAN);
		                    aUtility.log(LogEventId.INFO_LEVEL_1, "UBAN -> " + aUBAN);
                        }
                    }
                }
                else
                {
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Subscription Account -> Product Subscription -> Properties is empty !!!");
                }

                // ************************************************************************************
                // loop through all the property values in the product subscription
                // ************************************************************************************
                aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Product Subscription -> Property Values ...");
                for (int k = 0; k < aProductSubscription.aPropertyValues.length; k++)
                {
                    PropertyValue aPropertyValue =   aProductSubscription.aPropertyValues[k];
                    int aPropertyValueCounter = k+1;
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_1,
                                 "Subscription Account : " + aSubscriptionAccountCounter
                                     + " : Product Subscription : " + aProductSubscriptionCounter
                                     + " : Property Value : " + aPropertyValueCounter + " of " + aProductSubscription.aPropertyValues.length);
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_2, "PropertyValue : Identifier[0].aType : " + aPropertyValue.aIdentifiers[0].aType.value());
                    aUtility.log(LogEventId.INFO_LEVEL_2, "PropertyValue : Identifier[0].aValue: " + aPropertyValue.aIdentifiers[0].aValue);

                    // set non-published indicator (region-specific)
                    if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_MIDWEST))
                    {
                        if (aPropertyValue.aIdentifiers[0].aType.equals(IdentifierType.LH_FID)
                            && (!(aPropertyValue.aIdentifiers[0].aValue.length() <1)
                            	&& aPropertyValue.aIdentifiers[0].aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.FID_NP)))
                        {
                        	aResponseHelper.setNonPublishedIndicator(true);
                        }
                    }
                    if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHWEST))
                    {
                    	if (aPropertyValue.aIdentifiers[0].aType.equals(IdentifierType.LH_FID)
                            && (!(aPropertyValue.aIdentifiers[0].aValue.length() <1)
                               	&& (aPropertyValue.aIdentifiers[0].aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.FID_NP)
                               		|| aPropertyValue.aIdentifiers[0].aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.FID_NPU))))
                        {
                    		aResponseHelper.setNonPublishedIndicator(true); 
                        }
                    }
                    if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_WEST))
                    {
                        if (aPropertyValue.aIdentifiers[0].aType.equals(IdentifierType.LH_FID)
                            && (!(aPropertyValue.aIdentifiers[0].aValue.length() <1)
                            	&& ((String) aProperties.get("NONPUBLISHED_LH_FID_WEST")).indexOf(":" + aPropertyValue.aIdentifiers[0].aValue.toUpperCase() + ":") >= 0))
                        	
                        {
                        	aResponseHelper.setNonPublishedIndicator(true); 
                        }
                    }
                    if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_EAST))
                    {
                        if (aPropertyValue.aIdentifiers[0].aType.equals(IdentifierType.LH_FID)
                            && (!(aPropertyValue.aIdentifiers[0].aValue.length() <1)
                            	&& ((String) aProperties.get("NONPUBLISHED_LH_FID_EAST")).indexOf(":" + aPropertyValue.aIdentifiers[0].aValue.toUpperCase() + ":") >= 0)
                            && (!(aPropertyValue.aValue.length() < 1)
                            	&& (aPropertyValue.aValue.indexOf(PublishValidateFacilityNotificationConstants.FID_VALUE_NP_NSL) >= 0
                                     || aPropertyValue.aValue.indexOf(PublishValidateFacilityNotificationConstants.FID_VALUE_NP_OSL) >= 0)))
                        {
                        	aResponseHelper.setNonPublishedIndicator(true);
                        }
                    }
                    if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHEAST))
                    {
                        if (aPropertyValue.aIdentifiers[0].aType.equals(IdentifierType.LH_FID)
                            && (!(aPropertyValue.aIdentifiers[0].aValue.length() <1)
                            	&& aPropertyValue.aIdentifiers[0].aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.FID_NP)))
                        {
                        	aResponseHelper.setNonPublishedIndicator(true);
                        }
                    }
                }
            }

            // =====================================================================================
            // loop through all the services in the subscription account
            // << aSubscriptionAccount.aServices >>
            // =====================================================================================
            aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Services ...");
            for (int j = 0; j < aSubscriptionAccount.aServices.length; j++)
            {
                // extract service
                Service aService = aSubscriptionAccount.aServices[j];
                int aServicesCounter = j+1;
                // log
                aUtility.log(LogEventId.INFO_LEVEL_1,
                        "Subscription Account : " + aSubscriptionAccountCounter
                            + " : Service : " + aServicesCounter + " of " + aSubscriptionAccount.aServices.length);
                // ************************************************************************************
                // loop through all the product subscriptions in the service
                //     - DISH Service Indicator
                //     - Gift Billing Indicator
                // ************************************************************************************
                aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Service -> Product Subscriptions ...");
                for (int k = 0; k < aService.aProductSubscriptions.length; k++)
                {
                    // extract product subscription
                    ProductSubscription aProductSubscription = aService.aProductSubscriptions[k];
                    int aProductSubscriptionCounter = k+1;
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_1,
                                 "Subscription Account : " + aSubscriptionAccountCounter
                                     + " : Service : " + aServicesCounter
                                     + " : Product Subscription " + aProductSubscriptionCounter + " of " + aService.aProductSubscriptions.length);

                    // ************************************************************************************
                    // loop through all the product identifiers in the product subscription
                    //     - DISH Service Indicator
             	   	//	   - Non-Published Indicator (for SE)
                    // ************************************************************************************
                    setDISHServiceAndNonPublishedIndicators (aProductSubscription, aProductSubscriptionLevelString);

                    // ************************************************************************************
                    // loop through all the property values in the product subscription
                    //     - Gift Billing Indicator
                    //     - DSL Service Provider for non-SE regions
                    // ************************************************************************************
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Service -> Product Subscription -> Property Values ...");
                    for (int l = 0; l < aProductSubscription.aPropertyValues.length; l++)
                    {
                        // extract property value
                        PropertyValue aPropertyValue =   aProductSubscription.aPropertyValues[l];
                        int aPropertyValueCounter = l+1;
                        // log
                        aUtility.log(LogEventId.INFO_LEVEL_1,
                                "Subscription Account : " + aSubscriptionAccountCounter
                                + " : Service : " + aServicesCounter
                                + " : Product Subscription : " + aProductSubscriptionCounter
                                + " : Property Value " + aPropertyValueCounter + " of " + aProductSubscription.aPropertyValues.length);
                        // ************************************************************************************
                        // extract property value handle
                        //     - DSL Service Provider for non-SE regions
                        // ************************************************************************************
                        aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Service -> Property Value -> Property Value Handle ...");
                        if (!OptHelper.isObjectKeyOptEmpty(aPropertyValue.aPropertyValueHandle))
                        {
                            ObjectKey aPropertyValueHandle = aPropertyValue.aPropertyValueHandle.theValue();
                            // log
                            aUtility.log(LogEventId.INFO_LEVEL_2, "PropertyValueHandle.aKind : " + aPropertyValueHandle.aKind);
                            aUtility.log(LogEventId.INFO_LEVEL_2, "PropertyValueHandle.aValue: " + aPropertyValueHandle.aValue);
                            // set DSL service provider
                            if (aPropertyValueHandle.aKind.equalsIgnoreCase("FLOATED_FID"))
                            	
                            {
                                if (aPropertyValueHandle.aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_FID_CODE))
                                {
                                    bDSLServiceProviderFound = true;
                                    aUtility.log(LogEventId.INFO_LEVEL_1, "UNN1 FID is found!");
                                    aUtility.log(LogEventId.INFO_LEVEL_1, "UNN1 FID Code: " + aPropertyValue.aValue);
                                    
                                    if (!aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHEAST))
                                    {
                                    	aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_ASI;
                                    }
                                    
                                    // check for region-specific property values
                                    if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_MIDWEST))
                                    {
                                    	if (((String) aProperties.get("DSL_SERVICE_PROVIDER_UNN1_CODE_MIDWEST")).indexOf(":" + aPropertyValue.aValue + ":") == -1)
                                       
                                            {
                                                aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_INDICATOR;
                                                aUtility.log(LogEventId.DEBUG_LEVEL_2, "DSL Service Provider : Non-ASI");
                                            }
                                    }
                                    else if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHWEST))
                                    {   
                                    	if (((String) aProperties.get("DSL_SERVICE_PROVIDER_UNN1_CODE_SOUTHWEST")).indexOf(":" + aPropertyValue.aValue + ":") == -1)
                                        
                                        {
                                            aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_INDICATOR;
                                            aUtility.log(LogEventId.INFO_LEVEL_1, "DSL Service Provider : Non-ASI");
                                        }
                                    }
                                    else if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_WEST))
                                    {
                                        if (((String) aProperties.get("DSL_SERVICE_PROVIDER_UNN1_CODE_WEST")).indexOf(":" + aPropertyValue.aValue + ":") == -1)
                                        
                                        {
                                            aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_INDICATOR;
                                            aUtility.log(LogEventId.INFO_LEVEL_1, "DSL Service Provider : Non-ASI");
                                        }
                                    }   
                                    else if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_EAST))
                                    {
                                        if (((String) aProperties.get("DSL_SERVICE_PROVIDER_UNN1_CODE_EAST")).indexOf(":" + aPropertyValue.aValue + ":" ) == -1)
                                        {
                                            aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_INDICATOR;
                                            aUtility.log(LogEventId.INFO_LEVEL_1, "DSL Service Provider : Non-ASI");
                                        }
                                    }   
                                }
                                //CR 22040
                                if (aPropertyValueHandle.aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.FID_ISP_SERVICE_PROVIDER_MIDWEST))
                                {
                                    aUtility.log(LogEventId.INFO_LEVEL_1, " DNPC FID is found!");
                                    aUtility.log(LogEventId.INFO_LEVEL_1, "DNPC FID Code " + aPropertyValue.aValue);
                                    // check for region-specific property values
                                    if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHEAST))
                                    {
                                    	if (((String) aProperties.get("DNPC_CODE_SOUTHEAST")).equalsIgnoreCase(aPropertyValue.aValue))
                                        {
                                            aDSLProductFound = true;
                                        }
                                    }
                                }
                            }
                        }

                        // ************************************************************************************
                        // loop through all the identifiers in the property value
                        // ************************************************************************************
                        aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Service -> Product Subscription -> Property Value -> Identifier ...");
                        for (int m = 0; m < aPropertyValue.aIdentifiers.length; m++)
                        {
                            // extract identifier
                            Identifier aIdentifier = aPropertyValue.aIdentifiers[m];
                            int aIdentifierCounter = m+1;
                            // log
                            aUtility.log(LogEventId.INFO_LEVEL_1,
                                    "Subscription Account : " + aSubscriptionAccountCounter
                                    + " : Service : " + aServicesCounter
                                    + " : Product Subscription : " + aProductSubscriptionCounter
                                    + " : Property Value : " + aProductSubscriptionCounter
                                    + " : Product Identifier " + aIdentifierCounter + " of " + aPropertyValue.aIdentifiers.length);
                            // log
                            aUtility.log(LogEventId.INFO_LEVEL_2, "Type : " + aIdentifier.aType.value());
                            aUtility.log(LogEventId.INFO_LEVEL_2, "Value: " + aIdentifier.aValue);
                            // set gift billing indicator (region-specific)
                            if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_MIDWEST))
                            {
                                if (aIdentifier.aType.equals(IdentifierType.FLOATED_FID)
                                    && (((String) aProperties.get("GIFT_BILLING_FLOATED_FID_MIDWEST")).indexOf(":" + aIdentifier.aValue.toUpperCase() + ":") >= 0))
                                {
                                    aUtility.log(LogEventId.INFO_LEVEL_1, "Gift Billing Indicator found !!!");
                                    aGiftBillingIndicator = true;
                                }
                            }
                            if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHWEST))
                            {
                                if (aIdentifier.aType.equals(IdentifierType.LH_FID)
                                     && (((String) aProperties.get("GIFT_BILLING_LH_FID_SOUTHWEST")).indexOf(":" + aIdentifier.aValue.toUpperCase() + ":") >= 0))
                                {
                                    aUtility.log(LogEventId.INFO_LEVEL_1, "Gift Billing Indicator found !!!");
                                    aGiftBillingIndicator = true;
                                }
                            }
                            if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_WEST))
                            {
                                if (aIdentifier.aType.equals(IdentifierType.USOC)
                                    && (((String) aProperties.get("GIFT_BILLING_USOC_WEST")).indexOf(":" + aIdentifier.aValue.toUpperCase() + ":") >= 0)) 
                                {
                                    aUtility.log(LogEventId.INFO_LEVEL_1, "Gift Billing Indicator found !!!");
                                    aGiftBillingIndicator = true;
                                }
                            }
                            if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_EAST))
                            {
                                if (aIdentifier.aType.equals(IdentifierType.FLOATED_FID)
                                     && (((String) aProperties.get("GIFT_BILLING_FLOATED_FID_EAST")).indexOf(":" + aIdentifier.aValue.toUpperCase() + ":") >= 0)) 
                                {
                                    aUtility.log(LogEventId.INFO_LEVEL_1, "Gift Billing Indicator found !!!");
                                    aGiftBillingIndicator = true;
                                }
                            }
                        }
                    }
                }
                aBillingAccount.aDISHServiceIndicator.theValue(aResponseHelper.getDISHServiceIndicator());
                aBillingAccount.aGiftBillingIndicator.theValue(aGiftBillingIndicator);
                aBillingAccount.aNonPublishedIndicator.theValue(aResponseHelper.getNonPublishedIndicator());

                // ************************************************************************************
                // loop through all the property values in the service
                //     - DSL Service Provider for non-SE regions
                //     - Handicap Indicator
                // ************************************************************************************
                aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Service -> Property Values ...");
                for (int k = 0; k < aService.aPropertyValues.length; k++)
                {
                    // extract property value
                    PropertyValue aPropertyValue =   aService.aPropertyValues[k];
                    int aPropertyValueCounter = k+1;
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_1,
                                 "Subscription Account : " + aSubscriptionAccountCounter
                                     + " : Service : " + aServicesCounter
                                     + " : Property Value " + aPropertyValueCounter + " of " + aService.aPropertyValues.length);
                    // ************************************************************************************
                    // extract property value handle
                    //     - DSL Service Provider for non-SE regions
                    //     - Handicap Indicator for SE region
                    // ************************************************************************************
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Service -> Property Value -> Property Value Handle ...");
                    if (!OptHelper.isObjectKeyOptEmpty(aPropertyValue.aPropertyValueHandle))
                    {
                        ObjectKey aPropertyValueHandle = aPropertyValue.aPropertyValueHandle.theValue();
                        // log
                        aUtility.log(LogEventId.INFO_LEVEL_2, "PropertyValueHandle.aKind : " + aPropertyValueHandle.aKind);
                        aUtility.log(LogEventId.INFO_LEVEL_2, "PropertyValueHandle.aValue: " + aPropertyValueHandle.aValue);
                        // set DSL service provider
                        if (aPropertyValueHandle.aKind.equalsIgnoreCase("FLOATED_FID"))
                        {
                            if (aPropertyValueHandle.aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_FID_CODE))
                            {
                                bDSLServiceProviderFound = true;
                                aUtility.log(LogEventId.INFO_LEVEL_1, "UNN1 FID is found!");
                                aUtility.log(LogEventId.INFO_LEVEL_1, "UNN1 FID Code: " + aPropertyValue.aValue);
                                
                                if (!aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHEAST))
                                {
                                	aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_ASI;
                                }
                                // check for region-specific property values
                                if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_MIDWEST))
                                {
                                    if (((String) aProperties.get("DSL_SERVICE_PROVIDER_UNN1_CODE_MIDWEST")).indexOf(":" + aPropertyValue.aValue + ":") == -1)
                                        {
                                            aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_INDICATOR;
                                            aUtility.log(LogEventId.DEBUG_LEVEL_2, "DSL Service Provider : Non-ASI");
                                        }
                                }
                                else if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHWEST))
                                {
                                    if (((String) aProperties.get("DSL_SERVICE_PROVIDER_UNN1_CODE_SOUTHWEST")).indexOf(":" + aPropertyValue.aValue + ":") == -1)
                                    {
                                        aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_INDICATOR;
                                        aUtility.log(LogEventId.INFO_LEVEL_1, "DSL Service Provider : Non-ASI");
                                    }
                                }
                                else if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_WEST))
                                {
                                    if (((String) aProperties.get("DSL_SERVICE_PROVIDER_UNN1_CODE_WEST")).indexOf(":" + aPropertyValue.aValue + ":") == -1)
                                    {
                                        aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_INDICATOR;
                                        aUtility.log(LogEventId.INFO_LEVEL_1, "DSL Service Provider : Non-ASI");
                                    }
                                }
                                else if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_EAST))
                                {
                                    if (!(((String) aProperties.get("DSL_SERVICE_PROVIDER_UNN1_CODE_EAST")).equalsIgnoreCase(aPropertyValue.aValue)))
                                    {
                                        aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_INDICATOR;
                                        aUtility.log(LogEventId.INFO_LEVEL_1, "DSL Service Provider : Non-ASI");
                                    }
                                }
                            }
                            // CR 22040
                            if (aPropertyValueHandle.aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.FID_ISP_SERVICE_PROVIDER_MIDWEST))
                            {
                                aUtility.log(LogEventId.INFO_LEVEL_1, " DNPC FID is found!");
                                aUtility.log(LogEventId.INFO_LEVEL_1, "DNPC FID Code " + aPropertyValue.aValue);
                                // check for region-specific property values
                                if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHEAST))
                                {
                                	if (((String) aProperties.get("DNPC_CODE_SOUTHEAST")).equalsIgnoreCase(aPropertyValue.aValue))
                                    {
                                        aDSLProductFound = true;
                                    }
                                }
                            }
                            
                            // DR122586: Set handicap indicator for SE region
                            if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHEAST)
                            	&& aPropertyValueHandle.aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.HANDICAP_FID_SOUTHEAST))
                            {
                            	aUtility.log(LogEventId.INFO_LEVEL_1, "Handicap Indicator found !!!");
                                aHandicapIndicator = true;
                            }
                        }
                    }
                    else
                    {
                        // log
                        aUtility.log(LogEventId.INFO_LEVEL_1, "Subscription Account -> Service -> Property Value -> Property Value Handle is empty !!!");
                    }

                    // ************************************************************************************
                    // loop through all the identifiers in the property value
                    //     - Handicap Indicator
                    // ************************************************************************************
                    aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Service -> Property Value -> Identifiers ...");
                    for (int m = 0; m < aPropertyValue.aIdentifiers.length; m++)
                    {
                        // extract product identifier
                        Identifier aIdentifier = aPropertyValue.aIdentifiers[m];
                        int aIdentifierCounter = m+1;
                        // log
                        aUtility.log(LogEventId.DEBUG_LEVEL_2,
                                     "Subscription Account : " + aSubscriptionAccountCounter
                                         + " : Service : " + aServicesCounter
                                         + " : Property Value " + aPropertyValueCounter
                                         + " : Identifier " + aIdentifierCounter + " of " + aPropertyValue.aIdentifiers.length);
                        // log
                        aUtility.log(LogEventId.INFO_LEVEL_2, "Type : " + aIdentifier.aType.value());
                        aUtility.log(LogEventId.INFO_LEVEL_2, "Value: " + aIdentifier.aValue);
                        // set handicap indicator (region-specific)
                        if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_MIDWEST))
                        {
                            if (aPropertyValue.aIdentifiers[0].aType.equals(IdentifierType.FLOATED_FID)
                                && (aPropertyValue.aIdentifiers[0].aValue.equalsIgnoreCase("TNPC"))
                                && ((String) aProperties.get("HANDICAP_FLOATED_FID_TNPC")).indexOf(":" + aPropertyValue.aValue.toUpperCase() + ":") >= 0)
                            {
                                aUtility.log(LogEventId.INFO_LEVEL_1, "Handicap Indicator found !!!");
                                aHandicapIndicator = true;
                            }
                        }
                        if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHWEST))
                        {
                            if (aPropertyValue.aIdentifiers[0].aType.equals(IdentifierType.FLOATED_FID)
                                && (aPropertyValue.aIdentifiers[0].aValue.equalsIgnoreCase("TNPC"))
                                && ((String) aProperties.get("HANDICAP_FLOATED_FID_TNPC")).indexOf(":" + aPropertyValue.aValue.toUpperCase() + ":") >= 0)
                            {
                                aUtility.log(LogEventId.INFO_LEVEL_1, "Handicap Indicator found !!!");
                                aHandicapIndicator = true;
                            }
                        }
                        if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_WEST))
                        {
                            if (aPropertyValue.aIdentifiers[0].aType.equals(IdentifierType.LH_FID)
                                 && (aPropertyValue.aIdentifiers[0].aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.HANDICAP_FID_WEST))
                                 && (((String) aProperties.get("HANDICAP_LH_FID_TTY")).indexOf(":" + aPropertyValue.aValue.toUpperCase() + ":") >= 0))
                            {
                                aUtility.log(LogEventId.INFO_LEVEL_1, "Handicap Indicator found !!!");
                                aHandicapIndicator = true;
                            }
                        }
                        if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHEAST))
                        {
                            if (aPropertyValue.aIdentifiers[0].aType.equals(IdentifierType.FLOATED_FID)
                                && (aPropertyValue.aIdentifiers[0].aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.HANDICAP_FID_SOUTHEAST)))
                            {
                                aUtility.log(LogEventId.INFO_LEVEL_1, "Handicap Indicator found !!!");
                                aHandicapIndicator = true;
                            }
                        }
                    }
                    aBillingAccount.aHandicapIndicator.theValue(aHandicapIndicator);
                }
            }

            // =====================================================================================
            // loop through all the property values in the subscription account
            // << aSubscriptionAccount.aPropertyValues >>
            // =====================================================================================
            aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Property Values ...");
            for (int j = 0; j < aSubscriptionAccount.aPropertyValues.length; j++)
            {
                // extract property values
                PropertyValue aPropertyValue = aSubscriptionAccount.aPropertyValues[j];
                int aPropertyValueCounter = j+1;
                // log
                aUtility.log(LogEventId.INFO_LEVEL_1,
                        "Subscription Account : " + aSubscriptionAccountCounter
                            + " : Property Value : " + aPropertyValueCounter + " of " + aSubscriptionAccount.aPropertyValues.length);
                // extract identifier
                if (aPropertyValue.aIdentifiers.length > 0)
                {
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_2, "Identifier[0].aType : " + aPropertyValue.aIdentifiers[0].aType.value());
                    aUtility.log(LogEventId.INFO_LEVEL_2, "Identifier[0].aValue: " + aPropertyValue.aIdentifiers[0].aValue);

                    // set class of service (Residential or Business)
                    String aClassOfService = null;
                    if (aPropertyValue.aIdentifiers[0].aType.equals(IdentifierType.LH_FID)
                        && aPropertyValue.aIdentifiers[0].aValue.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.CLASS_OF_SERVICE_FID ))
                    {
                    	aClassOfService = (String) aProperties.get("CLASS_OF_SERVICE_" + aPropertyValue.aValue);
                    }
                    if (aClassOfService != null)
                    {
                        aBillingAccount.aClassOfService.theValue(aClassOfService);
                        aUtility.log(LogEventId.INFO_LEVEL_1, "Class of Service property found !!!");
                        break;
                    }
                }
            }

            // =====================================================================================
            // extract the properties in the subscription account
            // << aSubscriptionAccount.aProperties >>
            // =====================================================================================
            aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Properties ...");
            if (aSubscriptionAccount.aProperties.length > 0)
            {
                // log
                aUtility.log(LogEventId.INFO_LEVEL_1, "Subscription Account -> Properties found !!!");

                String aSA_Property = null;
                ObjectPropertyManager aSA_ObjectPropertyManager = new ObjectPropertyManager(aSubscriptionAccount.aProperties);

                // set member ID
                //     - check for "ParentMemberName"
                if (aSA_ObjectPropertyManager.getValue("ParentMemberName") != null)
                {
                    aMemberID = aSA_ObjectPropertyManager.getValue("ParentMemberName");
                }

                // set portal
                //     - check for "PortalProviderInformation"
                if (aSA_ObjectPropertyManager.getValue("PortalProvider") != null)
                {
                    aPortal = aSA_ObjectPropertyManager.getValue("PortalProvider");
                }

                // set wholesale indicator
                //     - check for "NonSBCAccountIndicator"
                boolean aWholesaleIndicator = false;
                if (aSA_ObjectPropertyManager.getValue("NonSBCAccountIndicator") != null)
                {
                    aWholesaleIndicator = true;
                }
                aBillingAccount.aWholesaleIndicator.theValue(aWholesaleIndicator);
            }
            else
            {
                // log
                aUtility.log(LogEventId.INFO_LEVEL_1, "Subscription Account -> Properties is empty !!!");
            }
        }

        // log
        aUtility.log(LogEventId.INFO_LEVEL_1, "Extract of rSAFABAN response COMPLETED !!!");

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Set Internet Service Provider
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Set to ATTIS
        // For 13- state region: 
        // if the com.sbc.eia.bis.ProductName in the ProductSubscriptionHandle has a value of either 
        //     - SBC_DSL_BASIC
        //     - SBC_DSL_BUS
        //     - SBC_DSL_STATIC
        //     - SBC_DSL_STATSNGIP
        //
        // For SE region: 
        // If the com.sbc.eia.bis.SbcServiceProvider in the propertyValueHandle has a floated FID of DNPC and value of 111115 
        // Else
        // set to Non-ATTIS
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (!aDSLProductFound)
        {
            // CR 22040 
            // For SE region if InternetServiceProvider is Non-ATTIS then DSLServiceProvider is set to Non-ASI
            if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHEAST))
            {
            	aInternetServiceProvider = PublishValidateFacilityNotificationConstants.INTERNET_SERVICE_PROVIDER_INDICATOR;
            	aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_INDICATOR;
            }
        }
        else
        {
        	aInternetServiceProvider = PublishValidateFacilityNotificationConstants.INTERNET_SERVICE_PROVIDER_ATTIS;
        	// For SE region, if InternetServiceProvider is ATTIS, DSLServiceProvider is set to ASI.        	
        	if (aRegion.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.REGION_SOUTHEAST))
        	{
        		aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_ASI;
        	}
        }
        
        // ************************************************************************************
        // compare WTNs from SM BIS with the WTNs from LFACS
        // ************************************************************************************
        // log
        aUtility.log(LogEventId.INFO_LEVEL_1, "Compare WTNs from rSAFABAN with WTNs from LFACS !!!");
        ArrayList aLoops = new ArrayList();
        Iterator aIterator = aWTNListFromLFACS.iterator();
        while (aIterator.hasNext())
        {
            String aWTN = (String) aIterator.next();
            boolean aWTNFound = false;
            // log
            aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Accounts ...");
            for (int i = 0; i < aSubscriptionAccountReturn.aSubscriptionAccounts.length; i++)
            {
                // extract subscription account
                SubscriptionAccount aSubscriptionAccount = aSubscriptionAccountReturn.aSubscriptionAccounts[i];
                int aSubscriptionAccountCounter = i+1;
                aUtility.log(LogEventId.INFO_LEVEL_1,
                             "Subscription Account: " + aSubscriptionAccountCounter + " of " + aSubscriptionAccountReturn.aSubscriptionAccounts.length);
                // log
                aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Billing Account ...");
                String aBillingTelephoneNumber = aSubscriptionAccount.aDefaultBillingAccount.aBillingAccountHandle.aValue;
                String aBTN = null;
                if (aSubscriptionAccount.aDefaultBillingAccount.aBillingAccountHandle.aValue.length() == 13)
                {
                    aBTN = aSubscriptionAccount.aDefaultBillingAccount.aBillingAccountHandle.aValue;
                }
                aUtility.log(LogEventId.INFO_LEVEL_2, "BTN from rSAFABAN : " + aBTN);
                // log
                aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : Subscription Account -> Services ...");
                for (int j = 0; j < aSubscriptionAccount.aServices.length; j++)
                {
                    // extract service
                    Service aService = aSubscriptionAccount.aServices[j];
                    int aServicesCounter = j+1;
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_1,
                                 "Subscription Account : " + aSubscriptionAccountCounter
                                     + " : Service : " + aServicesCounter + " of " + aSubscriptionAccount.aServices.length);
                    // extract WTN from rSAFABAN
                    String aAccountWTN = "";
                    if (aService.aServiceHandle != null)
                    {
                        aAccountWTN = aService.aServiceHandle.aValue;
                    }
                    // log
                    aUtility.log(LogEventId.INFO_LEVEL_2, "WTN from rSAFABAN : " + aAccountWTN);
                    aUtility.log(LogEventId.INFO_LEVEL_2, "WTN from LFACS    : " + aWTN);
                    // compare
                    if (aWTN.equalsIgnoreCase(aAccountWTN))
                    {
                        // update the responser driver
                        aResponseHelper.updateResponseDriver(aBTN, aWTN);
                        
                        if (aUBAN != null)
                        {
                        	aResponseHelper.updateUBANResponseDriver(aUBAN, aWTN);
                        }
                        // WTN found and add to WTN list
                        aWTNListAlreadyProcessed.add(aWTN);
                        aWTNFound = true;
                        
                        // remove WTN from aLoopsWithNoBTN if WTN is found in the rSAFABAN response
                        aResponseHelper.resetLoops();
                        for(int z = 0; z < aRequestHelper.getValidNtiList().length; z++)
                        {
                            aResponseHelper.removeLoopWithNoBTN(aResponseHelper.retrieveNT(aWTN), 
		                            aResponseHelper.getFacilityLoop(aWTN));
                        }
                        
                        // retrieve the Loop() information
                        FacilityLoop2 aFacilityLoop = null;;
                        aResponseHelper.resetLoops();
                        ArrayList aLoopsProcessed = new ArrayList();
                        
                        for(int z = 0; z < aRequestHelper.getValidNtiList().length; z++)
                        {
	                        aFacilityLoop = aResponseHelper.getFacilityLoop(aWTN);
	                        ArrayList aServiceItemsArray = new ArrayList();
	                        boolean aDSLFound = false;
	                        if (aFacilityLoop != null
	                        	&& !OptHelper.isServiceItemSeqOptEmpty(aFacilityLoop.aServices))
	                        {
	                            // if one of the services is DSL, set the values of the service item properties
	                            ServiceItem[] aServiceItems = aFacilityLoop.aServices.theValue();
	                            for (int k=0; k < aServiceItems.length; k++)
	                            {
	                                if (!OptHelper.isStringOptEmpty(aServiceItems[k].aServiceType))
	                                {
	                                    String aServiceType = aServiceItems[k].aServiceType.theValue();
	                                    boolean aConflictingServiceIndicator = aServiceItems[k].aConflictingServiceIndicator;
	                                    // log
	                                    aUtility.log(LogEventId.INFO_LEVEL_2, "Service Type : " + aServiceType);
	                                    aUtility.log(LogEventId.INFO_LEVEL_2, "Conflict     : " + aConflictingServiceIndicator);
	                                    // format service item properties
	                                    ObjectProperty[] aServiceItemProperties = null;
	                                    ObjectPropertyManager aServiceItemOPM = new ObjectPropertyManager();
	                                    if (!OptHelper.isObjectPropertySeqOptEmpty(aServiceItems[k].aServiceItemProperties))
	                                    {
	                                        aServiceItemProperties = aServiceItems[k].aServiceItemProperties.theValue();
	                                        aServiceItemOPM = new ObjectPropertyManager(aServiceItemProperties);
	                                    }
	                                    // if one of the conflicting services is DSL, format the following service item properties for DSL
	                                    //     - DSL Service Provider
	                                    //     - Internet Service Provider
	                                    //     - DSL Auto Disconnect Indicator
	                                    //     - DSL Misuse Indicator
	                                    //     - Member ID
	                                    //     - Portal
	                                    //     - Speed
	                                    if (aServiceItems[k].aServiceType.theValue().equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
	                                    	|| aServiceItems[k].aServiceType.theValue().equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
	                                    	|| aServiceItems[k].aServiceType.theValue().equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
	                                    {
	                                        // log
	                                        aUtility.log(LogEventId.INFO_LEVEL_1, "DSL Found : Adding Service Item Properties for DSL ... ");
	                                        // add service item properties for DSL
	                                        aDSLFound = true;
	                                        
	                                        //added for CR25554: Check if DSL/Internet service provider is set in SM for to determine if CPSOS call is needed
	                                        if (aDSLServiceProvider != null && aInternetServiceProvider != null)
	                                        {
	                                        	aServiceItemOPM.add(new ObjectProperty(ServiceItemPropertyValues.DSL_SERVICE_PROVIDER, aDSLServiceProvider));
	                                            aServiceItemOPM.add(new ObjectProperty(ServiceItemPropertyValues.INTERNET_SERVICE_PROVIDER, aInternetServiceProvider));
	                                            aServiceItemOPM.add(new ObjectProperty(ServiceItemPropertyValues.DSL_MISUSE_INDICATOR, aDSLMisuseIndicator));
	                                            aServiceItemOPM.add(new ObjectProperty(ServiceItemPropertyValues.DSL_AUTO_DISCONNECT_INDICATOR, "false"));
	                                         }
	                                        
	                                        if (aMemberID != null)
	                                        {
	                                            aServiceItemOPM.add(new ObjectProperty(ServiceItemPropertyValues.MEMBER_ID, aMemberID));
	                                        }
	                                        if (aPortal != null)
	                                        {
	                                            aServiceItemOPM.add(new ObjectProperty(ServiceItemPropertyValues.PORTAL, aPortal));
	                                        }
	                                        if (aSpeed != null)
	                                        {
	                                            aServiceItemOPM.add(new ObjectProperty(ServiceItemPropertyValues.SPEED, aSpeed));
	                                        }
	                                        aServiceItemProperties = aServiceItemOPM.toArray();
	
	                                        ServiceItem aServiceItem = new ServiceItem((StringOpt) IDLUtil.toOpt(StringOpt.class, aServiceType),
	                                                                                   aConflictingServiceIndicator,
	                                                                                   (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, aServiceItemProperties));
	
	                                        aServiceItemsArray.add(aServiceItem);
	
	                                    }
	                                    else
	                                    {
	                                        ServiceItem aServiceItem = new ServiceItem((StringOpt) IDLUtil.toOpt(StringOpt.class, aServiceType),
	                                                                                    aConflictingServiceIndicator,
	                                                                                    (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, aServiceItemProperties));
	                                        aServiceItemsArray.add(aServiceItem);
	                                    }
	
	                                }
	                            }
	                            // log
	                            aUtility.log(LogEventId.INFO_LEVEL_1, "All service items processed successfully !!!");
	                            if (aDSLFound)
	                            {
	                                // format working telephone number
	                                String aWorkingTelephoneNumber = null;
	                                if (!OptHelper.isStringOptEmpty(aFacilityLoop.aWorkingTelephoneNumber))
	                                {
	                                    aWorkingTelephoneNumber = aFacilityLoop.aWorkingTelephoneNumber.theValue();
	                                }
	                                // format circuit id
	                                String aCircuitID = null;
	                                if (!OptHelper.isStringOptEmpty(aFacilityLoop.aCircuitId)
	                                		&& !aFacilityLoop.aCircuitId.theValue().equalsIgnoreCase("NONE"))
	                                {
	                                    aCircuitID = aFacilityLoop.aCircuitId.theValue();
	                                }
	                                // format related Circuit ID
	                                String aRelatedCircuitID = null;
	                                if (!OptHelper.isStringOptEmpty(aFacilityLoop.aRelatedCircuitID))
	                                {
	                                    aRelatedCircuitID = aFacilityLoop.aRelatedCircuitID.theValue();
	                                }
	                                // format commit status
	                                String aCommitStatus = null;
	                                if (!OptHelper.isStringOptEmpty(aFacilityLoop.aCommitStatus))
	                                {
	                                    aCommitStatus = aFacilityLoop.aCommitStatus.theValue();
	                                }
	                                // format broadband pair
	                                String aBroadbandPair = null;
	                                if (!OptHelper.isStringOptEmpty(aFacilityLoop.aBroadbandPair))
	                                {
	                                    aBroadbandPair = aFacilityLoop.aBroadbandPair.theValue();
	                                }
	                                // format pending service orders
	                                PendingServiceOrder[] aPendingServiceOrders = null;
	                                if (!OptHelper.isPendingServiceOrderSeqOptEmpty(aFacilityLoop.aPendingServiceOrders))
	                                {
	                                    aPendingServiceOrders = aFacilityLoop.aPendingServiceOrders.theValue();
	                                }
	                                // format facility loop
	                                aFacilityLoop = new FacilityLoop2((StringOpt) IDLUtil.toOpt(StringOpt.class, aWorkingTelephoneNumber),
	                                                                  (StringOpt) IDLUtil.toOpt(StringOpt.class, aCircuitID),
	                                                                  (StringOpt) IDLUtil.toOpt(StringOpt.class, aRelatedCircuitID),
	                                                                  (StringOpt) IDLUtil.toOpt(StringOpt.class, aCommitStatus),
	                                                                  (StringOpt) IDLUtil.toOpt(StringOpt.class, aBroadbandPair),
	                                                                  (ServiceItemSeqOpt) IDLUtil.toOpt(ServiceItemSeqOpt.class, (ServiceItem[]) aServiceItemsArray.toArray(new ServiceItem[aServiceItemsArray.size()])),
	                                                                  (PendingServiceOrderSeqOpt) IDLUtil.toOpt(PendingServiceOrderSeqOpt.class, aPendingServiceOrders));
	                                // update FacilityLoop in the response helper
	                                if (aServiceItemsArray.size() > 0)
	                                {
	                                    aResponseHelper.updateFacilityLoop(aWTN, (ServiceItem[]) aServiceItemsArray.toArray(new ServiceItem[aServiceItemsArray.size()]));
	                                }
	                            }
	                        }
//                        }
		                    if(!aLoopsProcessed.contains(aFacilityLoop))
		                    {	
		                        aLoops.add(aFacilityLoop);
		                        break;
		                    }
                    	}
                    }
                }
                
                aResponseHelper.setWTNListAlreadyProcessed(aWTNListAlreadyProcessed); 
                
                // log
                aUtility.log(LogEventId.INFO_LEVEL_1, "All services processed successfully !!!");
                if (aWTNFound)
                {
                    break;
                }
            }
        }

        // format aFacilityLoops
        FacilityLoop2[] aFacilityLoops = null;
        if (aLoops.size() > 0)
        {
            aFacilityLoops = (FacilityLoop2[]) aLoops.toArray(new FacilityLoop2[aLoops.size()]);
            aBillingAccount.aFacilityLoops.theValue(aFacilityLoops);
        }

        aUtility.log(LogEventId.INFO_LEVEL_1, "<" + aMethodName);
        return aBillingAccount;
    }

    /**
     * Calls SM:retrieveSubscriptionAccountsForAffiliatesByAccountNumber()
     *
     * @param BisContext aContext
     * @param String     aBillingTelephoneNumber
     * @return SubscriptionAccountReturn
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
    public SubscriptionAccountReturn callRSAFABAN(
        BisContext aContext,
        String aBillingTelephoneNumber)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound
    {
        String aMethodName = "PublishValidateFacilityNotification: callRSAFABAN()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);

        // initialize return structure for SM:retrieveSubscriptionAccountsForAffiliatesByAccountNumber()
        SubscriptionAccountReturn aResult = null;

        // get SM bean
        if (aSMBean == null)
        {
            // access SM bean
            try
            {
                aSMBean = aSMClient.getSmBean(aContext, aUtility);
            }
            catch (Exception e)
            {
                aUtility.throwException(ExceptionCode.ERR_RM_SMCLIENT_EXCEPTION,
                                        "Exception while trying to get SMBean reference " + e.getMessage(),
                                        (String) aProperties.get("BIS_NAME"),
                                        Severity.UnRecoverable);
            }
        }

        // format parameters to call SM:retrieveSubscriptionAccountsForAffiliatesByAccountNumber()
        // Parameters: BisContext aContext
        //             ObjectKey  aServiceHandle (BTN)
        //             StringOpt  aZipCode
        //             StringSeq  aAffiliates
        //             SubscriptionAccountInformationTypeOpt aSubscriptionAccountInformationType
        //             LongOpt    aMaximumServicesToReturn
        //             StringOpt  aSortingOrder
        //             EiaDateOpt aAsOfDate
        ObjectKey aServiceHandle = new ObjectKey(aBillingTelephoneNumber, BisTypesObjectKeyKind.BILLINGTELEPHONENUMBER);
        SubscriptionAccountInformationType aInfoType = SubscriptionAccountInformationType.Both;
        String[] aAffiliates = new String[2];
        aAffiliates[0] = Affiliate.TELCO;
        aAffiliates[1] = Affiliate.INTERNET;

        // call SM:retrieveSubscriptionAccountsForAffiliatesByAccountNumber()
        try
        {
            aUtility.log(LogEventId.REMOTE_CALL,
                    (String) aProperties.get("SM_PROVIDER_URL" ),
                    (String) aProperties.get("SM_BIS_NAME"),
                    (String) aProperties.get("SM_BIS_NAME"),
                    "SM::retrieveSubscriptionAccountsForAffiliatesByAccountNumber");

            aResult = aSMBean.retrieveSubscriptionAccountsForAffiliatesByAccountNumber(
                           aContext,
                           aServiceHandle,
                           (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                           aAffiliates,
                           (SubscriptionAccountInformationTypeOpt) IDLUtil.toOpt(SubscriptionAccountInformationTypeOpt.class, aInfoType),
                           (LongOpt) IDLUtil.toOpt(LongOpt.class, null),
                           (StringOpt) IDLUtil.toOpt(StringOpt.class, null),
                           (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, null));

            // handle ExceptionData returned by SM 
            if ((aResult.aExceptionData != null) && (aResult.aExceptionData.length > 0))
            {
            	aUtility.log(LogEventId.INFO_LEVEL_1, "SM::retrieveSubscriptionAccountsForAffiliatesByAccountNumber returned an exception(s).");
            	
            	for (int i=0; i < aResult.aExceptionData.length; i++)
            	{
            		// log all the ExceptionData returned by SM
            		aUtility.log(LogEventId.INFO_LEVEL_2, "ExceptionCode[" + i + "]: " + aResult.aExceptionData[i].aCode);
                	aUtility.log(LogEventId.INFO_LEVEL_2, "ExceptionMessage[" + i + "]: " + aResult.aExceptionData[i].aDescription);
            	}
            }
        }
/*        catch (RemoteException e)
        {
            aUtility.throwException(ExceptionCode.ERR_RM_SMCLIENT_EXCEPTION,
                                    "RemoteException when trying to call retrieveSubscriptionAccountsForAffiliatesByAccountNumber from SM: "
                                        + e.getMessage(),
                                    (String) aProperties.get("BIS_NAME"),
                                    Severity.UnRecoverable);
        }*/
        catch (NotImplemented e)
        {
            throwSmException(aContext, aServiceHandle, e.aExceptionData);
        }
        catch (SystemFailure e)
        {
            throwSmException(aContext, aServiceHandle, e.aExceptionData);
        }
        catch (InvalidData e)
        {
            throwSmException(aContext, aServiceHandle, e.aExceptionData);
        }
        catch (ObjectNotFound e)
        {
        	throwSmException(aContext, aServiceHandle, e.aExceptionData);
        }
        catch (DataNotFound e)
        {
        	aUtility.log(LogEventId.INFO_LEVEL_2, "ExceptionCode: " + e.aExceptionData.aCode);
            aUtility.log(LogEventId.INFO_LEVEL_2, "ExceptionMessage: " + e.aExceptionData.aDescription);
 
            // handle exception
            aResponseHelper.handleException(e.aExceptionData.aCode,
            		e.aExceptionData.aDescription,
                            (String) aProperties.get("BIS_NAME").toString(),
                            aRequestHelper);
            
            aResponseHelper.setErrorInSMIndicator(true);
        }
        catch (AccessDenied e)
        {
            throwSmException(aContext, aServiceHandle, e.aExceptionData);
        }
        catch (BusinessViolation e)
        {
            throwSmException(aContext, aServiceHandle, e.aExceptionData);
            aResult = null; // hit BusinessRule 1 if we return, no CSI
        }
        finally
        {
            aUtility.log(LogEventId.REMOTE_RETURN,
                         (String) aProperties.get("SM_PROVIDER_URL"),
                         (String) aProperties.get("SM_BIS_NAME"),
                         (String) aProperties.get("SM_BIS_NAME"),
                         "SM::retrieveSubscriptionAccountsForAffiliatesByAccountNumber");
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
        return aResult;
    }

    /**
     * Logs message not sent to the client.
     *
     * @param String aMessage
     *
     * @author Rene Duka
     */
    private void logMessageNotSent(String aMessage)
    {

        StringBuffer aXMLNotSentLogMessage = new StringBuffer();
        aXMLNotSentLogMessage.append("MISSED_TARGET_XML: [ ").append(aMessage).append(" ]");
        aUtility.log(LogEventId.INFO_LEVEL_1, aXMLNotSentLogMessage.toString());
    }

    /**
     * Determines the client from BisContext.
     *
     * @param BisContext aClientBisContext
     * @return String
     *
     * @author Rene Duka
     */
    private String determineClient(BisContext aClientBisContext)
    {
        ObjectPropertyManager aContextOPM = new ObjectPropertyManager(aClientBisContext.aProperties);
        return aContextOPM.getValue(BisContextProperty.APPLICATION);
    }

    /**
     * Determines the network type(s) sent by the client.
     *
     * @param ObjectType[] aNtis
     * @return String[]
     *
     * @author Rene Duka
     */
    private void determineNetworkTypes(ObjectType[] aNtis)
    {
        String aMethodName = "PublishValidateFacilityNotification: determineNetworkTypes()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        ArrayList aLfacsNtisArray = new ArrayList();
        ArrayList aGraniteNtisArray = new ArrayList();
        ArrayList aInvalidNtisArray = new ArrayList();
        ArrayList aValidNtisArray = new ArrayList();
        
        for (int i=0; i < aNtis.length; i++)
        {
            // retrieve name
            String aName = aNtis[i].aName;
            if (aName.equalsIgnoreCase("networkTransport"))
            {
                if (!OptHelper.isAttributeTypeSeqOptEmpty(aNtis[i].aAttribute))
                {
                	aRequestHelper.setNtis(aNtis);
                    AttributeType[] aAttributeTypes = aNtis[i].aAttribute.theValue();
                    for (int j=0; j < aAttributeTypes.length; j++)
                    {                    	
                        if (aAttributeTypes[j].aName.equalsIgnoreCase("ntiNegotiationValue"))
                        {
                            // retrieve network type
                            String aNetworkType = aAttributeTypes[j].aValue;
                            
                            if (aRequestHelper.isLfacsNtis(aNetworkType)) 
                            {
                            	aLfacsNtisArray.add(aNetworkType);
                            	aValidNtisArray.add(aNetworkType);
                            }
                            else if (aRequestHelper.isGraniteNtis(aNetworkType)) 
                            {
                            	aGraniteNtisArray.add(aNetworkType);
                            	aValidNtisArray.add(aNetworkType);
                            }
                            else
                            {
                            	aInvalidNtisArray.add(aNetworkType);
                            }
                            // log
                            aUtility.log(LogEventId.DEBUG_LEVEL_2,
                                         "Network Type ==> " + aNetworkType);                        
                    	}
                    }                                                           
                }
            }
        }

        // format the network types
        if (aLfacsNtisArray.size() > 0)
        {
           aRequestHelper.setLfacsNtiList((String[]) aLfacsNtisArray.toArray(new String[aLfacsNtisArray.size()]));
        }
        if (aGraniteNtisArray.size() > 0)
        {
        	aRequestHelper.setGraniteNtiList((String[]) aGraniteNtisArray.toArray(new String[aGraniteNtisArray.size()]));
        }
        if (aValidNtisArray.size() > 0)
        {
        	aRequestHelper.setValidNtiList((String[]) aValidNtisArray.toArray(new String[aValidNtisArray.size()]));
        }        
        if (aInvalidNtisArray.size() > 0)
        {
        	aRequestHelper.setInvalidNtiList((String[]) aInvalidNtisArray.toArray(new String[aInvalidNtisArray.size()]));
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
       
    }
 
    /**
     * Determines the NpaNxx and CLLI8 sent by the client and returns the address.
     *
     * @param Location     aServiceLocation
     * @param Address      aFacilityAddress
     * @param StringBuffer aNpaNxx
     * @param StringBuffer aCLLI8
     * @return Address
     *
     * @author Rene Duka
     */
    private Address determineAddressInfo(
        Location aServiceLocation,
        StringBuffer aNpaNxx,
        StringBuffer aCLLI8)
    {
        // retrieve facility address and location properties
        ProviderLocationProperty aProviderLocationProperty = null;
        // initialize
        Address aFacilityAddress = null;
        aNpaNxx.append("");
        aCLLI8.append("");
        try
        {
            aProviderLocationProperty = aServiceLocation.aProviderLocationProperties[0];
            aFacilityAddress = aProviderLocationProperty.aServiceAddress.theValue();
            if (!OptHelper.isStringOptEmpty(aProviderLocationProperty.aPrimaryNpaNxx))
            {
                aNpaNxx.append(aProviderLocationProperty.aPrimaryNpaNxx.theValue());
            }
            if (!OptHelper.isStringOptEmpty(aProviderLocationProperty.aSbcServingOfficeWirecenter))
            {
                aCLLI8.append(aProviderLocationProperty.aSbcServingOfficeWirecenter.theValue());
            }
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
            aUtility.log(LogEventId.EXCEPTION, "aServiceLocation.aProviderLocationProperties[0].aServiceAddress.theValue()<CORBA_Exception>");
        }
        catch (NullPointerException e)
        {
            aUtility.log(LogEventId.EXCEPTION, "aServiceLocation.aProviderLocationProperties[0].aServiceAddress.theValue()<null>");
        }
        finally
        {
            return aFacilityAddress;
        }
    }


    /**
     * Throws exception from SM BIS.
     *
     * @param BisContext    aContext
     * @param ObjectKey     aServiceHandle
     * @param ExceptionData aExceptionData
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
    public void throwSmException(
        BisContext aContext,
        ObjectKey aServiceHandle,
        ExceptionData aExceptionData)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound 
    {

        String aOrigination = null;
        Severity aSeverity = null;
        String reqNum = aServiceHandle.aValue.trim();
        Properties aTagValues = new Properties();
        aTagValues.setProperty("TN", reqNum);

        try
        {
            aOrigination = aExceptionData.aOrigination.theValue();
        }
        catch (Exception e)
        {
        }

        try
        {
            aSeverity = aExceptionData.aSeverity.theValue();
        }
        catch (Exception e)
        {
        }

        ExceptionBuilderResult aResult = ExceptionBuilder.parseException(aContext,
                                                                         (String) aProperties.get("EXCEPTION_BUILDER_SM_RULE_FILE"),
                                                                         null,
                                                                         aExceptionData.aCode,
                                                                         aExceptionData.aDescription,
                                                                         false,
                                                                         ExceptionBuilderRule.NO_DEFAULT,
                                                                         null,
                                                                         null,
                                                                         aUtility,
                                                                         aOrigination,
                                                                         aSeverity,
                                                                         aTagValues);

       
        aResponseHelper.handleException(aResult.getException(), aRequestHelper);

        switch (aResult.getBusinessRule())
        {
            case 1 : // ok ignore exception
                aUtility.log(LogEventId.INFO_LEVEL_1,
                             "Ignore SM exception: "
                                 + aExceptionData.aCode
                                 + ": "
                                 + aExceptionData.aDescription);
                break;
            default : // throw the exception otherwise
                aResult.throwException(aContext, aUtility);
                break;
        }
    }     
    
    /**
     * Sets the DISH Service and Non-Published Indicators.
     *
     * @param ProductSubscription aProductSubscription
     * @param String aProductSubscriptionLevelString
     * @return void
     *
     * @author Lira Galsim
     */
    private void setDISHServiceAndNonPublishedIndicators (ProductSubscription aProductSubscription, String aProductSubscriptionLevelString)
    {
    	aUtility.log(LogEventId.INFO_LEVEL_1, "Extracting : " + aProductSubscriptionLevelString + " Product Identifiers ...");
    	String aRegion = aRequestHelper.getRegion();
    	for (int l = 0; l < aProductSubscription.aProductIdentifiers.length; l++)
        {
            // extract product identifier
            Identifier aProductIdentifier = aProductSubscription.aProductIdentifiers[l];
            int aProductIdentifierCounter = l+1;
            // log
            aUtility.log(LogEventId.INFO_LEVEL_1,
                         "Product Identifier " + aProductIdentifierCounter + " of " + aProductSubscription.aProductIdentifiers.length);
            
	    	aUtility.log(LogEventId.INFO_LEVEL_2, "Type : " + aProductIdentifier.aType.value());
	        aUtility.log(LogEventId.INFO_LEVEL_2, "Value: " + aProductIdentifier.aValue);
	        
	        // set dish service indicator        
            if (aProductIdentifier.aType.equals(IdentifierType.USOC)
                && aProductIdentifier.aValue != null)
            {
            	if (!(aProductIdentifier.aValue.trim().length() < 1)
            		&& ((String) aProperties.get("DISH_SERVICE_USOC")).indexOf(":" + aProductIdentifier.aValue.toUpperCase() + ":") >= 0)
                {
            		aUtility.log(LogEventId.INFO_LEVEL_1, "DISH Service found !!!");
            		aResponseHelper.setDISHServiceIndicator(true);
                }
               
            }            
	        
	        // set non-published indicator for SE using USOC value        
	        if (aRegion.equalsIgnoreCase("B"))
	        {
	            if (aProductIdentifier.aType.equals(IdentifierType.USOC)
	                && aProductIdentifier.aValue != null)
	            {
	            	if (!(aProductIdentifier.aValue.trim().length() < 1)
	            		&& (aProductIdentifier.aValue.substring(0, 3).equalsIgnoreCase(PublishValidateFacilityNotificationConstants.NONPUBLISHED_USOC_NPU)
	            		    || aProductIdentifier.aValue.substring(0, 3).equalsIgnoreCase(PublishValidateFacilityNotificationConstants.NONPUBLISHED_USOC_NP3)))
	                {
	            		aResponseHelper.setNonPublishedIndicator(true);
	                }
	            }            
	        }
        }
        if (aProductSubscription.aProductSubscriptions != null
        	&& aProductSubscription.aProductSubscriptions.length > 0)
    	{
        	aProductSubscriptionLevelString += "Product Subscription ->";
        	for (int l = 0; !(aResponseHelper.getDISHServiceIndicator()) && !(aResponseHelper.getNonPublishedIndicator()) && l < aProductSubscription.aProductSubscriptions.length; l++)
            {
                   setDISHServiceAndNonPublishedIndicators(aProductSubscription.aProductSubscriptions[l], aProductSubscriptionLevelString);
            }
    	}
    }
    
    /**
     * Call CPSOS and determine DSL/Internet Service Provider
     *
     * @param aLoopsWithDSL
     * @param pvfnReqHelper
     * @param state
     *
     * @author SL7683
     */
    private void checkDSLServiceProvider (FacilityLoop2[] aLoopsWithDSL,
    		PublishValidateFacilityNotificationRequestHelper pvfnReqHelper,
    		String aState)
    {
    	String aMethodName = "PublishValidateFacilityNotification: checkDSLServiceProvider()";
    	aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        try
        {
        		for (int i = 0; i < aLoopsWithDSL.length; i++)
    			{
    	        	String wtn = (!OptHelper.isStringOptEmpty( aLoopsWithDSL[i].aWorkingTelephoneNumber))? aLoopsWithDSL[i].aWorkingTelephoneNumber.theValue():null;
        			ArrayList aServiceItemsArray = new ArrayList();
    	            ServiceItem[] aServiceItems = aLoopsWithDSL[i].aServices.theValue();
        			boolean bServiceProviderInfoSetFromSM = true;

    	            for (int j=0; j < aServiceItems.length; j++)
    		        {	        			
    		        	if (!OptHelper.isStringOptEmpty(aServiceItems[j].aServiceType))
    		        	{
    		        		// retrieve the service type
    		        		String aServiceType = aServiceItems[j].aServiceType.theValue();
    		        		
    		        		// check if one of the service items is DSL
    		        		if (aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_DSL)
    	                       		|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_LS_IPDSLAM)
    	                       		|| aServiceType.equalsIgnoreCase(PublishValidateFacilityNotificationConstants.SERVICE_NAME_HYBRID_IPDSLAM))
    		        		{
    		        			ObjectProperty[] aServiceItemProperties = null;
    		        			ObjectPropertyManager aServiceItemOPM = new ObjectPropertyManager();
    		        			
    		        			if (!OptHelper.isObjectPropertySeqOptEmpty(aServiceItems[j].aServiceItemProperties))
    		        			{
    		        				aServiceItemOPM = new ObjectPropertyManager(aServiceItems[j].aServiceItemProperties.theValue());
    		        			}
    		        			
    		        			String aDSLServiceProvider = null;
			       	            String aInternetServiceProvider = null;

			       	            try
    		        			{
    		        				aDSLServiceProvider = aServiceItemOPM.getValue(ServiceItemPropertyValues.DSL_SERVICE_PROVIDER);
    		        				aInternetServiceProvider = aServiceItemOPM.getValue(ServiceItemPropertyValues.INTERNET_SERVICE_PROVIDER);

    		        			}
    		        			catch (Throwable e)
    		        			{
    		        				aDSLServiceProvider = null;
    			       	            aInternetServiceProvider = null;
    		        			}

    		        			//check if DSL or Internet Service Provider is not set from SM BIS
    		        			//call CPSOS to determine DSL and Internet Service Provider (CR25554)
    		        			if (wtn != null 
    		        				&& (aDSLServiceProvider == null || aInternetServiceProvider == null))
    		        			{
    		        				bServiceProviderInfoSetFromSM = false;
    		        				CPSOS cpsosClient = new CPSOS(aUtility, aProperties, wtn, aState, aRequestHelper, aResponseHelper);
    			    	            DslAccountLookupResponse cpsosResponse = cpsosClient.executeConnection();	
    			    	            
    			    	            String aDSLAutoDisconnectIndicator = "false";

    			       				if (cpsosResponse != null)
    		        		        {
    			       					if (cpsosClient.aCpsosSuccessInd)
    		        		            {
    		        		            	aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_ASI;
    		        		            	aInternetServiceProvider = PublishValidateFacilityNotificationConstants.INTERNET_SERVICE_PROVIDER_ATTIS;
    		        		            }
    		        		            else
    		        		            {
    		        		            	String errorId = cpsosResponse.getErrorId();
    		        		            	String errorDesc = cpsosResponse.getErrorText();

    		        		            	if(errorId.equals(CPSOSConstants.Service_Request_Authorization_Error_Code))
    		        		            	{
    		        		            		aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_ASI;
    		        		            		aInternetServiceProvider = PublishValidateFacilityNotificationConstants.INTERNET_SERVICE_PROVIDER_INDICATOR;
    		        		                }
    		        		            	else if(errorId.equals(CPSOSConstants.Backend_System_Error_Code))
    		        		            	{
    		        		            		aDSLServiceProvider = PublishValidateFacilityNotificationConstants.DSL_SERVICE_PROVIDER_INDICATOR;
    		        		            		aInternetServiceProvider = PublishValidateFacilityNotificationConstants.INTERNET_SERVICE_PROVIDER_INDICATOR;
    		        		                }
    		        		            	else
    		        		            	{
    		        		            		ExceptionBuilderResult aExceptionBuilderResult = ExceptionBuilder.parseException(pvfnReqHelper.getContext(),
    		        			    	           		(String) aProperties.get("EXCEPTION_BUILDER_CPSOS_RULE_FILE"),
    		        			    	           		null,
    		        			    	           		errorId,
    		        			    	                errorDesc,
    		        			    	                false,
    		        			    	                ExceptionBuilderRule.NO_DEFAULT,
    		        			    	                null,
    		        			    	                new Exception(),
    		        			    	                aUtility,
    		        			    	                cpsosClient.aHostName,
    		        			    	                null,
    		        			    	                null);

    		        			            	aResponseHelper.handleException(aExceptionBuilderResult.getException(),aRequestHelper);
    		        			            }
    		        			        }
    			        			}

    			       				if (aDSLServiceProvider != null && aInternetServiceProvider != null)
			        				{
    			       					aServiceItemOPM.add(new ObjectProperty(ServiceItemPropertyValues.DSL_SERVICE_PROVIDER, aDSLServiceProvider));
			        		            aServiceItemOPM.add(new ObjectProperty(ServiceItemPropertyValues.INTERNET_SERVICE_PROVIDER, aInternetServiceProvider));

			        		            aServiceItemProperties = aServiceItemOPM.toArray();


			        		            aUtility.log(LogEventId.INFO_LEVEL_2, "DSL Service Provider: " + aDSLServiceProvider);
			        		            aUtility.log(LogEventId.INFO_LEVEL_2, "Internet Service Provider: " + aInternetServiceProvider);

			        		            if (checkDSLOwnedByATT(aServiceItemProperties))
				        				{
				        					//set DSL Auto-Disconnect indicator to TRUE
			        						aDSLAutoDisconnectIndicator = "true";
			        					}
				        				else
				        				{
				        					// if DSL is not owned by ATT, set conflictFound indicator to TRUE.
				        					aResponseHelper.setConflictFound(true);
				        				}

			        		            aServiceItemOPM.add(new ObjectProperty(ServiceItemPropertyValues.DSL_AUTO_DISCONNECT_INDICATOR, aDSLAutoDisconnectIndicator));

			        					aServiceItemProperties = aServiceItemOPM.toArray();

			        				}
    			       				
    			       				ServiceItem aServiceItem = new ServiceItem((StringOpt) IDLUtil.toOpt(StringOpt.class, aServiceItems[j].aServiceType.theValue()),
											aServiceItems[j].aConflictingServiceIndicator,
                                           (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, aServiceItemProperties));

		        					aServiceItemsArray.add(aServiceItem);
    			       			}
    		        		}
    		        	}
    		        }
    	            
    	            if (!bServiceProviderInfoSetFromSM)
	    	        {
	
	    	            //format working telephone number
		                String aWorkingTelephoneNumber = null;
		                if (!OptHelper.isStringOptEmpty( aLoopsWithDSL[i].aWorkingTelephoneNumber))
		                {
		                    aWorkingTelephoneNumber =  aLoopsWithDSL[i].aWorkingTelephoneNumber.theValue();
		                }
		                // format circuit id
		                String aCircuitID = null;
		                if (!OptHelper.isStringOptEmpty( aLoopsWithDSL[i].aCircuitId)
		                		&& !aLoopsWithDSL[i].aCircuitId.theValue().equalsIgnoreCase("NONE"))
		                {
		                    aCircuitID =  aLoopsWithDSL[i].aCircuitId.theValue();
		                }
		                // format related Circuit ID
		                String aRelatedCircuitID = null;
		                if (!OptHelper.isStringOptEmpty( aLoopsWithDSL[i].aRelatedCircuitID))
		                {
		                    aRelatedCircuitID =  aLoopsWithDSL[i].aRelatedCircuitID.theValue();
		                }
		                // format commit status
		                String aCommitStatus = null;
		                if (!OptHelper.isStringOptEmpty( aLoopsWithDSL[i].aCommitStatus))
		                {
		                    aCommitStatus =  aLoopsWithDSL[i].aCommitStatus.theValue();
		                }
		                // format broadband pair
		                String aBroadbandPair = null;
		                if (!OptHelper.isStringOptEmpty( aLoopsWithDSL[i].aBroadbandPair))
		                {
		                    aBroadbandPair =  aLoopsWithDSL[i].aBroadbandPair.theValue();
		                }
		                // format pending service orders
		                PendingServiceOrder[] aPendingServiceOrders = null;
		                if (!OptHelper.isPendingServiceOrderSeqOptEmpty( aLoopsWithDSL[i].aPendingServiceOrders))
		                {
		                    aPendingServiceOrders =  aLoopsWithDSL[i].aPendingServiceOrders.theValue();
		                }
			        	// format facility loop
		                aLoopsWithDSL[i] = new FacilityLoop2((StringOpt) IDLUtil.toOpt(StringOpt.class, aWorkingTelephoneNumber),
		                                                  (StringOpt) IDLUtil.toOpt(StringOpt.class, aCircuitID),
		                                                  (StringOpt) IDLUtil.toOpt(StringOpt.class, aRelatedCircuitID),
		                                                  (StringOpt) IDLUtil.toOpt(StringOpt.class, aCommitStatus),
		                                                  (StringOpt) IDLUtil.toOpt(StringOpt.class, aBroadbandPair),
		                                                  (ServiceItemSeqOpt) IDLUtil.toOpt(ServiceItemSeqOpt.class, (ServiceItem[]) aServiceItemsArray.toArray(new ServiceItem[aServiceItemsArray.size()])),
		                                                  (PendingServiceOrderSeqOpt) IDLUtil.toOpt(PendingServiceOrderSeqOpt.class, aPendingServiceOrders));
		                // update FacilityLoop in the response helper
		                if (aServiceItemsArray.size() > 0)
		                {
		                    aResponseHelper.updateFacilityLoop(wtn, (ServiceItem[]) aServiceItemsArray.toArray(new ServiceItem[aServiceItemsArray.size()]));
		                }
    	            }
    	        }

        }
        catch (NullPointerException e)
        {
            // log: exception message
            StringBuffer nullLogMessage = new StringBuffer();
            nullLogMessage.append("> Exception: [ ").append(e.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, nullLogMessage.toString());
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            		nullLogMessage.toString(),
					(String) aProperties.get("BIS_NAME").toString(),
					aRequestHelper);
        }
        catch (Exception e)
        {
            // log: exception message
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("> Exception: [ ").append(e.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            aResponseHelper.handleException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
					eLogMessage.toString(),
					(String) aProperties.get("BIS_NAME").toString(),
					aRequestHelper);
        }
        finally
        {
        	aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        }
    }
    
    /**
     * Determines the region associated with the address.
     * 
     * @param String aPrimaryNpaNxx
     * @param String aCLLI8
     * @return String
     * 
     * @author Rene Duka
     */
    private String determineRegion(
        String aPrimaryNpaNxx,
        String aCLLI8) 
    {
        String aMethodName = "LFACS: determineRegion()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        String aRegion = null;
        try 
        {
            // query the SOAC_WIRE_CENTER table using the NpaNxx and CLLI8 (if available) 
            // to get the HOST_NAME and ENTITY.
            SoacWireCenterRow aSoacWireCenterRow = null;
            SoacWireCenterTable aSoacWireCenterTable = new SoacWireCenterTable();
            if (aCLLI8 != null) 
            {
                aSoacWireCenterRow = aSoacWireCenterTable.retrieveRow(aPrimaryNpaNxx,
                                                                      aCLLI8,
                                                                      aProperties,
                                                                      aUtility);
            }
            else 
            {
                aSoacWireCenterRow = aSoacWireCenterTable.retrieveRow(aPrimaryNpaNxx,
                                                                      aProperties,
                                                                      aUtility);
            }
    
            // query the TOPLISTENER_SOAC_LINK table using the HOST_NAME and ENTITY 
            // retrieved from the SOAC_WIRE_CENTER table to get the REGION.
            if (aSoacWireCenterRow != null) 
            {
                TopListenerSoacLinkTable aTopListenerSoacLinkTable = new TopListenerSoacLinkTable();
                TopListenerSoacLinkRow aTopListenerSoacLinkRow = aTopListenerSoacLinkTable.retrieveRow(aSoacWireCenterRow.getHOST_NAME(),
                                                                                                       aSoacWireCenterRow.getENTITY(),
                                                                                                       aProperties,
                                                                                                       aUtility);
    
                aRegion = aTopListenerSoacLinkRow.getREGION().trim();
            }
        }
        catch (SQLException sqle) 
        {
            // log: exception message            
            StringBuffer sqleLogMessage = new StringBuffer();
            sqleLogMessage.append("Exception: [ ").append(sqle.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, sqleLogMessage.toString());
        }
        catch (Exception e) 
        {
            // log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("> Exception: [ ").append(e.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
        }
        finally  
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);            
        }
        return aRegion;
    }        
    
    /**
     * Determines the state associated with the NPANXX.
     *
     * @param String aPrimaryNpaNxx
     * @return String
     *
     * @author Sheilla Lirio
     */
    private String determineState (String aPrimaryNpaNxx)
    {
    	String aMethodName = "PublishValidateFacilityNotification: determineState()";
    	String aCLLI8 = null;
    	String aState = null;
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        try
		{
			//query the SOAC_WIRE_CENTER table using the NpaNxx
            //to get the CLLI8
			SoacWireCenterRow aSoacWireCenterRow = null;
            SoacWireCenterTable aSoacWireCenterTable = new SoacWireCenterTable();

            //sample valid NPANXX - 262886, 614486, 740532, 740426
            if (aPrimaryNpaNxx != null)
            {
            	aSoacWireCenterRow = aSoacWireCenterTable.retrieveRow(aPrimaryNpaNxx, aProperties, aUtility);
            	aCLLI8 = aSoacWireCenterRow.getCLLI8().toString();
            	aUtility.log(LogEventId.INFO_LEVEL_1, "CLLI8 : " + aCLLI8);
            }

            //determine the State from CLLI8
    		//the 5th and the 6th characters of CLLI8 forms the State value
    		if (aCLLI8 != null)
    		{
    			aState = aCLLI8.substring(4, 6).toUpperCase();
    			aUtility.log(LogEventId.INFO_LEVEL_1, "State : " + aState);
    		}

		}
		catch (SQLException sqle)
        {
            // log: exception message
            StringBuffer sqleLogMessage = new StringBuffer();
            sqleLogMessage.append("Exception: [ ").append(sqle.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, sqleLogMessage.toString());
        }
        catch (Exception e)
        {
            // log: exception message
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("> Exception: [ ").append(e.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
        }
        finally
        {
        	aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        }
        return aState;
    }
    
    /**
     * Sets the BillingAccounts3.
     *
     * @param BillingAccount2[] aInput
     * @return BillingAccount3[]
     * @author Hongmei Parkin
     */
	public BillingAccount3[] getBillingAccounts3(BillingAccount2[] aInput) 
	{
		BillingAccount3[] aBillingAccount3 = new BillingAccount3[aInput.length];
		try 
		{
			FacilityLoop3SeqOpt facilityLoops3 = (FacilityLoop3SeqOpt) IDLUtil.toOpt(FacilityLoop3SeqOpt.class, null);
			for (int i = 0; i< aInput.length; i++)
			{
				facilityLoops3 = (FacilityLoop3SeqOpt) IDLUtil.toOpt(FacilityLoop3SeqOpt.class, null);
				if (!OptHelper.isFacilityLoop2SeqOptEmpty(aInput[i].aFacilityLoops))
				{
					facilityLoops3 = (FacilityLoop3SeqOpt) IDLUtil.toOpt(FacilityLoop3SeqOpt.class, getFacilityLoops3(aInput[i].aFacilityLoops.theValue()));
				}
				aBillingAccount3[i] = new BillingAccount3(aInput[i].aBillingTelephoneNumber,
														  aInput[i].aCustomerName,
														  aInput[i].aCustomerCode,
														  aInput[i].aAccountStatus,
														  aInput[i].aClassOfService,
														  aInput[i].aDISHServiceIndicator,
														  aInput[i].aDialUpServiceIndicator,
														  aInput[i].aNonPublishedIndicator,
														  aInput[i].aWholesaleIndicator,
														  aInput[i].aGiftBillingIndicator,
														  aInput[i].aHandicapIndicator,
														  facilityLoops3);				
			}
		} 
		catch (Exception e) 
		{
			   //aInput could be null, which is OK.
		}
		return aBillingAccount3;
	}
	
    /**
     * Sets the FacilityLoops3.
     *
     * @param FacilityLoop2[] aInput
     * @return FacilityLoop3[]
     * @author Hongmei Parkin
     */	
	public FacilityLoop3[] getFacilityLoops3(FacilityLoop2[] aInput) 
	{
		String aWtn = null;
		FacilityLoop3[] aFacilityLoop3 = new FacilityLoop3[aInput.length];
		
		try
		{
			CompositeObjectKeyOpt aUniversalBillingAccountNumber = (CompositeObjectKeyOpt) IDLUtil.toOpt(CompositeObjectKeyOpt.class, null);
			for (int i = 0; i< aInput.length; i++)
			{
				aUniversalBillingAccountNumber = (CompositeObjectKeyOpt) IDLUtil.toOpt(CompositeObjectKeyOpt.class, null);
				try 
				{
					aWtn = aInput[i].aWorkingTelephoneNumber.theValue();
					if (aWtn != null)
					{
						aUniversalBillingAccountNumber = (CompositeObjectKeyOpt) IDLUtil.toOpt(CompositeObjectKeyOpt.class, getUniversalBillingAccountNumber(aWtn).theValue());
					}
				} 
				catch (Throwable e)
				{
					// WTN could be null.
				}
				
				aFacilityLoop3[i] = new FacilityLoop3(aInput[i].aWorkingTelephoneNumber,
													  aUniversalBillingAccountNumber,
						   							  aInput[i].aCircuitId,
						   							  aInput[i].aRelatedCircuitID,
						   							  aInput[i].aCommitStatus,
						   							  aInput[i].aBroadbandPair,
						   							  aInput[i].aServices,
						   							  aInput[i].aPendingServiceOrders);						
			}
		}
		catch (Exception e) 
		{
			// aInput could be null, which is OK.
		}
		return aFacilityLoop3;
	}
	
    /**
     * Gets the Universal Billing Account Number.
     *
     * @param String aWTN
     * @return CompositeObjectKeyOpt
     * @author Hongmei Parkin
     */
	public CompositeObjectKeyOpt getUniversalBillingAccountNumber(
			String aWTN) 
	{
		CompositeObjectKeyOpt aUniversalBillingAccountNumber = (CompositeObjectKeyOpt) IDLUtil.toOpt(CompositeObjectKeyOpt.class, null);
		if (aWTN != null)
		{
			String aKind = "com.sbc.eia.bis.UniversalBillingAccountNumber";
		    com.sbc.eia.idl.types.CompositeObjectKey objKey = null;
		    
		    ObjectKey[] aObjectKeys = new ObjectKey[]{new ObjectKey(aResponseHelper.retrieveUban(aWTN), aKind)} ;
		    objKey = new CompositeObjectKey(aObjectKeys);
		    aUniversalBillingAccountNumber = (com.sbc.eia.idl.types.CompositeObjectKeyOpt)IDLUtil.toOpt(com.sbc.eia.idl.types.CompositeObjectKeyOpt.class,objKey);
		}
		
		return aUniversalBillingAccountNumber;
    }
}