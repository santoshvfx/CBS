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
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author      | Notes
//# --------------------------------------------------------------------
//# 05/10/2005  ck2932      Creation.
//# 05/10/2005  ck2932      Added business interface logic.
//# 06/23/2005  Chaitanya   Added logic for buildRequest() & buildResponse()
//# 07/12/2005  Chaitanya   Changed buildRequest() & buildResponse()
//#                         as per new XNG Schema
//# 08/03/2005  Chaitanya   Modified buildRequest() to handle All Exceptions
//# 08/04/2005  Chaitanya   Changes for Release
//# 08/12/2005  Chaitanya   Modified According to DR 142178 to display correct Exception Details
//# 08/24/2005  Chaitanya   Modified buildRequest() for DR143046
//# 09/14/2005  Chaitanya   Fixed DR144629 
//# 11/08/2005  Chaitanya   Made changes for IDLbundle 33. 
//# 05/01/2006  Chaitanya   Modified the method signature for generalization.
//#                             Modified business logic for LS2 
//# 02/01/2006  kk8467      Modified to conform to the new RCTI input schema from XngRC.
//# 02/17/2006  kk8467      Changed for unit testing.
//# 02/21/2006  kk8467      Changed for LS2_1_5_retrieveCustomerTransportInfo.
//# 03/01/2006  kk8467      Added initializeReturnObject() method.
//#                         Added mappings for VideoHeadOfficeRouters and ProductSubscription
//#                         Removed FacilityAddress() and changed for LS 2.1.5.
//# 03/10/2006  kk8467      Added isVideoHeadOfficeRouterListEmpty(), isProductSubscriptionListEmpty(),
//#                         buildVideoHeadOfficeRouter() methods
//# 03/13/2006  kk8467      Added initialization for ObjectProperties.
//# 04/07/2006  kk8467      Added ServiceLocation back.
//# 04/10/2006  kk8467      Updated to use common methods.
//# 04/17/2006  kk8467      Updated after integration testing.
//# 05/11/2006  Kavitha     Replaced RCTI code with LS2_1_5_retrieveCustomerTransportInfo method.
//# 06/13/2006  Mrinalini   Changes for LS3
//# 06/12/2006  pg7636      Added retrieve() and buildRequest() methods with TRE param for CR 8163
//# 06/15/2006  Kavitha     CR9309: LFACS interface
//# 06/16/2006  Mrinalini   LS3 code walk thru changes.
//# 06/19/2006  PG7636      CR 8163: Deleted the newly created methods - retrieve() and buildRequest() and 
//                          added TRE parameter to the existing retrieve() and buildRequest() methods as per Code Review.
//# 12/11/2006  Rene Duka   PR 18942097 and 19042150 (R3)/DR 172866: Fixed business logic in LFACS FTTN scenario.

package com.sbc.eia.bis.BusinessInterface.rm.RetrieveCustomerTransportInfo; 

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.rm.database.SoacWireCenterTable;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.xng.XNGService;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.CTPATHIDImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.CTSITECITYImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.CTSITEIDImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.CTSITEPOSTCODE1Impl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.CTSITEPOSTCODE2Impl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.CTSITESTATEPROVImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.FacilityAddressImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.LSCircuitIDImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.OrderActionImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.RetrieveCustomerTransportInfoRequestImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.WireCenterCLLIImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoResponse.impl.ComponentInfoImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoResponse.impl.RetrieveCustomerTransportInfoResponseImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoResponse.impl.VideoHeadOfficeRouterImpl;
import com.sbc.eia.bis.embus.service.xng.access.XNGHelper;
import com.sbc.eia.bis.embus.service.xng.helpers.SendRequestToXNG;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.BuildEmptyIDL;
import com.sbc.eia.bis.rm.utilities.IDLObjectHelper;
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
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerGranite;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.rmim.RetrieveCustomerTransportInfoReturn;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeValues;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.VideoHeadOfficeRouter;
import com.sbc.eia.idl.rm_ls_types.VideoHeadOfficeRouterSeqOpt;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.sm_ls_types.ProductSubscriptionProperty;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.gwsvcs.access.vicuna.EventResultPair;
import com.sbc.gwsvcs.service.facsaccess.FACSAccessAccess;
import com.sbc.gwsvcs.service.facsaccess.FACSAccessHelper;
import com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.Fasg_Inq_Req_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.INQ_FASG_Section_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.Result_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_e;

public class RetrieveCustomerTransportInfo {

    private Utility aUtility = null;
    private Hashtable aProperties = null;

    private String aRuleFile = null;
    private XNGService aService = null;
    private FACSAccessHelper helper = null;

    /**
     * Constructor for RetrieveCustomerTransportInfo.
     * @param Utility utility
     * @param Hashtable properties
     * @param String aRuleFile
     * @get - Returns the value to which the specified key is mapped in hashtable
     */
    public RetrieveCustomerTransportInfo(Utility utility, Hashtable properties) {
        aProperties = properties;
        aUtility = utility;
        aRuleFile = (String) properties.get(SendRequestToXNG.XNG_EXCEPTION_RULE_FILE_TAG);
    }

    /**
        * Method: retrieve()
        * @param BisContext             aContext
        * @param StringOpt              aCustomerTransportId
        * @param CompositeObjectKey aBillingAccountNumber
        * @param StringOpt              aSbcServingOfficeWirecenter
        * @param StringOpt          aPrimaryNpaNxx
        * @param AddressOpt             aServiceAddress
        * @param OrderAction            aOrderAction
        * @param ObjectPropertySeqOpt   aObjectProperties
        * @param String             aTRE
        *
        * @return RetrieveCustomerTransportInfoReturn
        *
        * @throws InvalidData           : An input parameter contained invalid data.
        * @throws AccessDenied          : Access to the specified domain object or information is not allowed.
        * @throws BusinessViolation : The attempted action violates a business rule.
        * @throws SystemFailure     : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
        * @throws NotImplemented        : The method has not been implemented.
        * @throws ObjectNotFound        : The desired domain object could not be found.
        * @throws DataNotFound          : No data found.
        */

    public RetrieveCustomerTransportInfoReturn retrieve(
        BisContext              aContext,
        StringOpt               aCustomerTransportId,
        CompositeObjectKey      aBillingAccountNumber,
        StringOpt               aSbcServingOfficeWirecenter,
        StringOpt               aPrimaryNpaNxx,
        AddressOpt              aServiceAddress,
        OrderAction             aOrderAction,
        ObjectPropertySeqOpt    aObjectProperties,
        String                  aTRE)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {

        String myMethodName = "RetrieveCustomerTransportInfo::retrieve()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);

        //build request item
        RetrieveCustomerTransportInfoRequestImpl aRequest = buildRequest(
                                                            aContext,
                                                            aCustomerTransportId,
                                                            aBillingAccountNumber,
                                                            aSbcServingOfficeWirecenter,
                                                            aPrimaryNpaNxx,
                                                            aServiceAddress,
                                                            aOrderAction,
                                                            aObjectProperties,
                                                            aTRE);
        
        // process request
        // Checking for Service, if not creating one
        if (aService == null) {
             try {
                aService = new XNGService(aProperties, aUtility);
             }
             catch (ServiceException e)  {
                 ExceptionBuilder.parseException(
                            aContext,
                            aRuleFile,
                            null,
                            null,
                            e.getMessage(),
                            true,
                            1,
                            null,
                            e,
                            aUtility,
                            null,
                            null,
                            null);
             }
         }

        // send request to Xng
        RetrieveCustomerTransportInfoResponseImpl aResponse =
                (new SendRequestToXNG(
                        aUtility,
                        aContext,
                        aProperties,
                        aService))
                        .sendRequest(
                                aRequest,
                                aOrderAction);

        StringOpt aOrigination = IDLUtil.toOpt((String) aProperties.get("BIS_NAME").toString());
        SeverityOpt severity = new SeverityOpt();
        severity.theValue(Severity.UnRecoverable);
        
        Result_t aFacsResponse = null;
        if ( aResponse.getFttnResponse() != null) {
            String npanxx = null;
            
            npanxx = retrieveNPANXX(aResponse);
            
            String aXngCustomerTransportId = null; 
            try {
                aXngCustomerTransportId = aResponse.getLSCircuitID().getCTPATHID().getPathID();
            } catch (Exception e) { }           
            
            String aFACSCustomerTransportId = null;
            aFACSCustomerTransportId=aXngCustomerTransportId.replace('/','.');
            
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "CustomerTransportId send to LFACS is  :  " + aFACSCustomerTransportId );
                        
            aFacsResponse = sendRequestToFACS(aContext, aFACSCustomerTransportId, npanxx);
        }
        
        RetrieveCustomerTransportInfoReturn aReturnObject = buildResponse(
                                                                 aContext,
                                                                 aObjectProperties,
                                                                 aResponse,
                                                                 aFacsResponse);

        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);

        return aReturnObject;
    }

    /**
     * Method : buildRequest
     * @param BisContext                aContext
     * @param StringOpt             aCustomerTransportId
     * @param CompositeObjectKey        aBillingAccountNumber
     * @param StringOpt             aSbcServingOfficeWirecenter
     * @param StringOpt                 aPrimaryNpaNxx
     * @param AddressOpt                aServiceAddress
     * @param OrderAction               aOrderAction
     * @param ObjectPropertySeqOpt      aObjectProperties
     * @param String                    aTRE
     *
     * @return RetrieveCustomerTransportInfoRequestImpl
     *
     * @throws InvalidData              : An input parameter contained invalid data.
     * @throws AccessDenied         : Access to the specified domain object or information is not allowed.
     * @throws BusinessViolation        : The attempted action violates a business rule.
     * @throws SystemFailure            : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @throws NotImplemented           : The method has not been implemented.
     * @throws ObjectNotFound           : The desired domain object could not be found.
     * @throws DataNotFound         : No data found.
     */

    public RetrieveCustomerTransportInfoRequestImpl buildRequest(
        BisContext              aContext,
        StringOpt               aCustomerTransportId,
        CompositeObjectKey      aBillingAccountNumber,
        StringOpt               aSbcServingOfficeWirecenter,
        StringOpt               aPrimaryNpaNxx,
        AddressOpt              aServiceAddress,
        OrderAction             aOrderAction,
        ObjectPropertySeqOpt    aObjectProperties,
        String                  aTRE)
            throws
                InvalidData,
                AccessDenied,
                BusinessViolation,
                SystemFailure,
                NotImplemented,
                ObjectNotFound,
                DataNotFound {

        String myMethodName = "RetrieveCustomerTransportInfo::buildRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);

        RetrieveCustomerTransportInfoRequestImpl aRequest
                    = new RetrieveCustomerTransportInfoRequestImpl();

        // REQUIRED: Schema Version
        aRequest.setSchemaVersion(XNGHelper.XNG_RC_VERSION);

        // CustomerTransportId
        if (!OptHelper.isStringOptEmpty(aCustomerTransportId))  {
            CTPATHIDImpl aCTPATHIDImpl = new CTPATHIDImpl();
            LSCircuitIDImpl aLSCircuitIDImpl = new LSCircuitIDImpl();
        
            aCTPATHIDImpl.setPathID(aCustomerTransportId.theValue());
            aLSCircuitIDImpl.setCTPATHID(aCTPATHIDImpl);
            aRequest.setLSCircuitID(aLSCircuitIDImpl);
        }

        // BillingAccountNumber
        try {
            ObjectKey[] aObjectKeys = aBillingAccountNumber.aKeys;
            for (int i = 0; i < aObjectKeys.length; i++) {
                if (aObjectKeys[i].aKind.equals("com.sbc.eia.bis.BillingAccountNumber")) {
                    aRequest.setBAN(aObjectKeys[i].aValue);
                    break;
                }
            }
        } catch (org.omg.CORBA.BAD_OPERATION e) {   } 
        catch (NullPointerException e1) {   }
        
        // FacilityAddress
        if (!OptHelper.isAddressOptNull(aServiceAddress)) {
            FacilityAddressImpl aFacilityAddressImpl = getFacilityAddress(aServiceAddress.theValue());
            aRequest.setFacilityAddress(aFacilityAddressImpl);
        }
        
        // SbcServingOfficeWireCenter
        if (!OptHelper.isStringOptEmpty(aSbcServingOfficeWirecenter)) {
            WireCenterCLLIImpl aWireCenterImpl = new WireCenterCLLIImpl();
            CTSITEIDImpl aCTSITEIDImpl = new CTSITEIDImpl();
            
            aCTSITEIDImpl.setSiteID(aSbcServingOfficeWirecenter.theValue());
            aWireCenterImpl.setCTSITEID(aCTSITEIDImpl);
            aRequest.setWireCenterCLLI(aWireCenterImpl);
        }
        
        // NPANXX   
        if (!OptHelper.isStringOptEmpty(aPrimaryNpaNxx)) {
            aRequest.setNPANXX(aPrimaryNpaNxx.theValue());
        }

        // OrderAction          
        if (aOrderAction != null)   {           
            if (!OptHelper.isStringOptEmpty(aOrderAction.aOrder))   {
                OrderActionImpl aOrderActionImpl = new OrderActionImpl();
                aOrderActionImpl.setOrderNumber(aOrderAction.aOrder.theValue());
                aRequest.setOrderAction(aOrderActionImpl);
            }
        }
        
        //TRE
        if (aTRE != null && aTRE.length() > 0) {
            aRequest.setSwitchTRE(aTRE);
        }
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
        
        return aRequest;
    }
    
        
    /**
     * Method : getFacility
     * @param Address aFacilityAddress
     * @return FacilityAddressImpl
     */
    public FacilityAddressImpl getFacilityAddress(Address aFacilityAddress) {
        
        String myMethodName = "RetrieveCustomerTransportInfo::getFacilityAddress()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
        
        AddressHandlerGranite aGraniteAddress = null;
        FacilityAddressImpl aFacilityAddressImpl = new FacilityAddressImpl();
        
        try {
            aGraniteAddress = new AddressHandlerGranite(aFacilityAddress);
        }
        catch(AddressHandlerException e) {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "buildRequest:getFacilityAddress():AddressHandlerException");
        }
        
        // StreetAddress
        aFacilityAddressImpl.setStreetAddress(aGraniteAddress.getStreetAddress());
        
        // City
        CTSITECITYImpl aCTSITECITYImpl = new CTSITECITYImpl();
        aCTSITECITYImpl.setSiteCity(aGraniteAddress.getCity());
        aFacilityAddressImpl.setCTSITECITY(aCTSITECITYImpl);
        
        // State
        CTSITESTATEPROVImpl aCTSITESTATEPROVImpl = new CTSITESTATEPROVImpl();
        aCTSITESTATEPROVImpl.setSiteStateProv(aGraniteAddress.getState());
        aFacilityAddressImpl.setCTSITESTATEPROV(aCTSITESTATEPROVImpl);
        
        // PostalCode
        CTSITEPOSTCODE1Impl aCTSITEPOSTCODE1Impl = new CTSITEPOSTCODE1Impl();
        aCTSITEPOSTCODE1Impl.setSitePostCode1(aGraniteAddress.getPostalCode());
        aFacilityAddressImpl.setCTSITEPOSTCODE1(aCTSITEPOSTCODE1Impl);
        
        // PostalCodePlus4
        CTSITEPOSTCODE2Impl  aCTSITEPOSTCODE2Impl = new CTSITEPOSTCODE2Impl();
        aCTSITEPOSTCODE2Impl.setSitePostCode2(aGraniteAddress.getPostalCodePlus4());
        aFacilityAddressImpl.setCTSITEPOSTCODE2(aCTSITEPOSTCODE2Impl);
        
        // Structure
        aFacilityAddressImpl.setStructureType(aGraniteAddress.getStructType());
        aFacilityAddressImpl.setStructureValue(aGraniteAddress.getStructValue());
        
        // Elevation
        aFacilityAddressImpl.setElevationType(aGraniteAddress.getLevelType());
        aFacilityAddressImpl.setElevationValue(aGraniteAddress.getLevelValue());
        
        // Unit
        aFacilityAddressImpl.setUnitType(aGraniteAddress.getUnitType());
        aFacilityAddressImpl.setUnitValue(aGraniteAddress.getUnitValue());
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
        
        return aFacilityAddressImpl;
    }

    /**
     * Method : buildResponse
     * @param BisContext                                    aContext
     * @param ObjectPropertySeqOpt                          aObjectProperties
     * @param RetrieveCustomerTransportInfoResponseImpl     aResponse
     *
     * @return RetrieveCustomerTransportInfoReturn
     *
     * @throws InvalidData              : An input parameter contained invalid data.
     * @throws AccessDenied         : Access to the specified domain object or information is not allowed.
     * @throws BusinessViolation        : The attempted action violates a business rule.
     * @throws SystemFailure            : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @throws NotImplemented           : The method has not been implemented.
     * @throws ObjectNotFound           : The desired domain object could not be found.
     * @throws DataNotFound             : No data found.
     */

    public RetrieveCustomerTransportInfoReturn buildResponse(
        BisContext aContext,
        ObjectPropertySeqOpt aObjectProperties,
        RetrieveCustomerTransportInfoResponseImpl aResponse,
        Result_t aFacsResponse)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {

        String myMethodName = "RetrieveCustomerTransportInfo::buildResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);

        RetrieveCustomerTransportInfoReturn aReturn = initializeReturnObject();
        ComponentInfoImpl aComponentInfoImpl = null;
        boolean rgpon = false;
        
        // CustomerTransportId
        if ( aResponse.getFttnResponse() != null) {
            if (aFacsResponse.LOOP.length != 0) {
                aReturn.aCustomerTransportId = aFacsResponse.LOOP[0].CKID.replace('.','/');
            }
        }
        else {
            try {
                aReturn.aCustomerTransportId = aResponse.getLSCircuitID().getCTPATHID().getPathID();
            } catch (Exception e) { }
        }
        
        // Network7450Switch
        aReturn.aNetwork7450Switch = IDLObjectHelper.buildNetwork7450Switch(aUtility, aResponse);
        
        // SBCISPOPID
        if (!isStringEmpty(aResponse.getSBCCISPOPID()))
            aReturn.aSBCISPOPId = IDLUtil.toOpt(aResponse.getSBCCISPOPID());        

        // PathStatus
        try {
            aReturn.aPathStatus = IDLUtil.toOpt(aResponse.getServiceAreaIP().getPathStatus());
         } catch (Exception e) { }
        
        // ConnectorType
        try {
            aReturn.aConnectorType = IDLUtil.toOpt(aResponse.getServiceAreaIP().getConnectorType());
         } catch (Exception e) { }
                
        // VideoHeadOffice
        if (!isStringEmpty(aResponse.getVideoHeadOffice()))
            aReturn.aVideoHeadOffice = IDLUtil.toOpt(aResponse.getVideoHeadOffice());
        
        // OrderAction
        aReturn.aOrderAction = IDLObjectHelper.buildOrderAction(aUtility, aResponse);
        
        if ( aResponse.getFttnResponse() != null) {
            Fttn aFttn = IDLObjectHelper.buildFttnResponse(aUtility, aResponse);
            aReturn.aNetworkTypeChoice.aFttn(aFttn);
            
            try {
                aReturn.aTaperCode = IDLUtil.toOpt(aResponse.getFttnResponse().getFttnRSGRP().getTaperCode());
            } catch (Exception e) { }
        }
        
        if (aResponse.getFttpResponse() != null) {
            Fttp aFttp = IDLObjectHelper.buildFttpResponse(aUtility, aResponse);
            aReturn.aNetworkTypeChoice.aFttp(aFttp);
            
            try {
                aReturn.aTaperCode = IDLUtil.toOpt(aResponse.getFttpResponse().getFttpRSGRP().getTaperCode());
            } catch (Exception e) { }
        }
        //RGPON indicator   
/*      if (! isProductSubscriptionListEmpty(aResponse) ) {
            aComponentInfoImpl = (ComponentInfoImpl) aResponse.getComponentListGRP().getItem().get(0);
            try {
                rgpon = Boolean.valueOf(aComponentInfoImpl.getRGPONIndicator()).booleanValue();
            } catch (Exception e) {
                rgpon = false;
            }       
        }
*/
//      aReturn.aRGPONIndicator=rgpon;
        
        //Network Type
        if(rgpon)
            aReturn.aNetworkType = IDLUtil.toOpt(NetworkTypeValues.RGPON);
                                    
        else if(aResponse.getFttpResponse()!=null)
            aReturn.aNetworkType = IDLUtil.toOpt(NetworkTypeValues.FTTP);               
        else
            aReturn.aNetworkType = IDLUtil.toOpt(NetworkTypeValues.FTTN);                                   
                                
        if (aResponse.getVideoHeadOfficeRouterGRP() != null)
            aReturn.aVideoHeadOfficeRouters = (VideoHeadOfficeRouterSeqOpt) IDLUtil.toOpt(VideoHeadOfficeRouterSeqOpt.class, buildVideoHeadOfficeRouter(aResponse));
        
        if (aResponse.getComponentListGRP() != null)
            aReturn.aProductSubscriptions = buildProductSubscriptions(aResponse);
        
        aReturn.aContext = aContext;
        if (aObjectProperties != null)
            aReturn.aProperties = aObjectProperties;
                        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
        return aReturn;         
    }
    
    /**
     * @param stringToCheck
     * @return
     */
    public static boolean isStringEmpty(String stringToCheck) {
        if (stringToCheck != null && stringToCheck.trim().length() > 0)
            return false;

        return true;
    }
    
    /**
     * Method initializeReturnObject.
     * @return RetrieveCustomerTransportInfoReturn
     */
    public RetrieveCustomerTransportInfoReturn initializeReturnObject() {
    
        String myMethodName = "RetrieveCustomerTransportInfo::initializeReturnObject()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);

        RetrieveCustomerTransportInfoReturn aReturn = new RetrieveCustomerTransportInfoReturn();
        
        aReturn.aCustomerTransportId = "";      
        aReturn.aSBCISPOPId = (StringOpt) IDLUtil.toOpt((String)null);
        aReturn.aPathStatus = (StringOpt) IDLUtil.toOpt((String)null);
        aReturn.aConnectorType = (StringOpt) IDLUtil.toOpt((String)null);
        aReturn.aVideoHeadOffice = (StringOpt) IDLUtil.toOpt((String)null);
        aReturn.aTaperCode = (StringOpt) IDLUtil.toOpt((String)null);
        aReturn.aNetwork7450Switch =  BuildEmptyIDL.buildEmptyNetwork7450SwitchObject();
        aReturn.aOrderAction = BuildEmptyIDL.buildEmptyOrderActionObject();
        aReturn.aNetworkType = (StringOpt) IDLUtil.toOpt((String)null);
//      aReturn.aRGPONIndicator = false;
        aReturn.aProperties = BuildEmptyIDL.buildEmptyObjectPropertySeqOpt();
        aReturn.aNetworkTypeChoice = new NetworkType();
        
        //FTTN
        aReturn.aNetworkTypeChoice.aFttn(BuildEmptyIDL.buildEmptyFttnObject());
        
        //FTTP
        aReturn.aNetworkTypeChoice.aFttp(BuildEmptyIDL.buildEmptyFttpObject());
        
        //VideoHeadOfficeRouters    
        aReturn.aVideoHeadOfficeRouters = BuildEmptyIDL.buildEmptyVideoHeadOfficeRouterSeqOpt();
        
        //ProductSubscriptions
        aReturn.aProductSubscriptions =
            new ProductSubscription[] {
                 BuildEmptyIDL.buildEmptyProductSubscriptionObject()};
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
        return aReturn;
    }
    
    /**
     * Method buildProductSubscriptions.
     * @param aResponse
     * @return ProductSubscription[]
     */
    public ProductSubscription[] buildProductSubscriptions(RetrieveCustomerTransportInfoResponseImpl aResponse)
    {
        String myMethodName = "RetrieveCustomerTransportInfo::buildProductSubscriptions()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
        
        ComponentInfoImpl aComponentInfoImpl = null;
        ProductSubscription[] aProductSubscription = BuildEmptyIDL.buildEmptyProductSubscriptionArrayObject();
        
        if (! isProductSubscriptionListEmpty(aResponse) ) {
            
            int size = aResponse.getComponentListGRP().getItem().size();
            aProductSubscription = new ProductSubscription[size];
            
            for (int i = 0; i < size; i++) {
                aProductSubscription[i] = BuildEmptyIDL.buildEmptyProductSubscriptionObject();
                aComponentInfoImpl = (ComponentInfoImpl) aResponse.getComponentListGRP().getItem().get(i);
                
                ObjectPropertyManager aOPMgr = new ObjectPropertyManager();
                
                // Added condition to check for null because it is failing in the container code.
                if (aComponentInfoImpl.getComponentDeviceID() != null)
                    aOPMgr.add(ProductSubscriptionProperty.DEVICEID, aComponentInfoImpl.getComponentDeviceID());
                    
                if (aComponentInfoImpl.getComponentQualifier() != null)
                    aOPMgr.add(ProductSubscriptionProperty.QUALIFIER, aComponentInfoImpl.getComponentQualifier());
                    
                if (aComponentInfoImpl.getComponentSerialNo() != null)
                    aOPMgr.add(ProductSubscriptionProperty.SERIALNUMBER, aComponentInfoImpl.getComponentSerialNo());
                    
                if (aComponentInfoImpl.getCPEModel() != null)
                    aOPMgr.add(ProductSubscriptionProperty.MODELNUMBER, aComponentInfoImpl.getCPEModel());
                    
                if (aComponentInfoImpl.getCPEVendor() != null)
                    aOPMgr.add(ProductSubscriptionProperty.VENDOR, aComponentInfoImpl.getCPEVendor());
                    
                if (aComponentInfoImpl.getDecommissionDate() != null)
                    aOPMgr.add("DecommisionDate", aComponentInfoImpl.getDecommissionDate());
                    
                if (aComponentInfoImpl.getDueDate() != null)
                    aOPMgr.add("DueDate", aComponentInfoImpl.getDueDate());
                    
                if (aComponentInfoImpl.getInServiceDate() != null)
                    aOPMgr.add("InServiceDate", aComponentInfoImpl.getInServiceDate());
                    
                if (aComponentInfoImpl.getInstalledDate() != null)
                    aOPMgr.add("InstalledDate", aComponentInfoImpl.getInstalledDate());
                    
                if (aComponentInfoImpl.getOrderedDate() != null)
                    aOPMgr.add("OrderedDate", aComponentInfoImpl.getOrderedDate());
                    
                if (aComponentInfoImpl.getPurchaseDate() != null)
                    aOPMgr.add("PurchaseDate", aComponentInfoImpl.getPurchaseDate());
                    
                if (aComponentInfoImpl.getScheduleDate() != null)
                    aOPMgr.add("ScheduleDate", aComponentInfoImpl.getScheduleDate());
                
                if (aComponentInfoImpl.getComponentInstanceID() != null)
                    aOPMgr.add("InstanceId", aComponentInfoImpl.getComponentInstanceID());
                else
                    aOPMgr.add("InstanceId" , "");                      
                
                if (aComponentInfoImpl.getIPAddressType() != null)
                    aOPMgr.add("ipAddressType", aComponentInfoImpl.getIPAddressType());
                
                if (aComponentInfoImpl.getUVerseTechnology() != null)
                    aOPMgr.add("uverseTechnology", aComponentInfoImpl.getUVerseTechnology());
                
                if (aComponentInfoImpl.getHSIAType() != null)
                    aOPMgr.add("HsiaType", aComponentInfoImpl.getHSIAType());
                
                if (aComponentInfoImpl.getSTBType() != null)
                    aOPMgr.add("stbType", aComponentInfoImpl.getSTBType());
                
                if (aComponentInfoImpl.getBandwidth() != null)
                    aOPMgr.add("Bandwidth", aComponentInfoImpl.getBandwidth());
                
                if (aComponentInfoImpl.getSpeed() != null)
                    aOPMgr.add("Speed", aComponentInfoImpl.getSpeed());
                
                if (aComponentInfoImpl.getNoOfHDStreams() != null)
                    aOPMgr.add("HD_STREAM_QTY", aComponentInfoImpl.getNoOfHDStreams());
                    
                if (aComponentInfoImpl.getNoOfSDStreams() != null)
                    aOPMgr.add("SD_STREAM_QTY", aComponentInfoImpl.getNoOfSDStreams());
                                
                aProductSubscription[i].aProductID = aComponentInfoImpl.getComponentCode();
                aProductSubscription[i].aProductSubscriptionStatus = (StringOpt) IDLUtil.toOpt(StringOpt.class, aComponentInfoImpl.getComponentStatus());
                aProductSubscription[i].aProperties = (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, aOPMgr.toArray());
            }
        }
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
        return aProductSubscription;
    }
    
    /**
     * Method buildVideoHeadOfficeRouter.
     * @param aResponse
     * @return VideoHeadOfficeRouter[]
     */
    public VideoHeadOfficeRouter[] buildVideoHeadOfficeRouter(RetrieveCustomerTransportInfoResponseImpl aResponse) 
    {
        String myMethodName = "RetrieveCustomerTransportInfo::buildVideoHeadOfficeRouter()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
        
        VideoHeadOfficeRouterImpl aVideoHeadOfficeRouterImpl = null;        
        VideoHeadOfficeRouter[] aVideoHeadOfficeRouter = BuildEmptyIDL.buildEmptyVideoHeadOfficeRouterArrayObject();
        
        if (! isVideoHeadOfficeRouterListEmpty(aResponse) ) {
            
            int size = aResponse.getVideoHeadOfficeRouterGRP().getItem().size();
            aVideoHeadOfficeRouter = new VideoHeadOfficeRouter[size];
            
            for (int i = 0; i < size; i++) {
                aVideoHeadOfficeRouter[i] = new VideoHeadOfficeRouter();
                aVideoHeadOfficeRouterImpl = (VideoHeadOfficeRouterImpl) aResponse.getVideoHeadOfficeRouterGRP().getItem().get(i);
                
                aVideoHeadOfficeRouter[i] = createVideoHeadOfficeRouter(
                                                aVideoHeadOfficeRouterImpl.getVideoHeadOfficeRouterDeviceID(),
                                                aVideoHeadOfficeRouterImpl.getVideoHeadOfficeRouterDeviceCLLI().getCTEQUIPMENTCLLI().getEquipmentCLLI(),
                                                aVideoHeadOfficeRouterImpl.getVideoHeadOfficeRouterIPAddress());
            }
        }
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
        return aVideoHeadOfficeRouter;
    }
    
    /**
     * Method createVideoHeadOfficeRouter.
     * @param aDeviceId
     * @param aCLLI
     * @param aMaintenanceIPAddress
     * @return VideoHeadOfficeRouter
     */
    public VideoHeadOfficeRouter createVideoHeadOfficeRouter(
        String aDeviceId,
        String aCLLI,
        String aMaintenanceIPAddress) {
        
        String myMethodName = "RetrieveCustomerTransportInfo::createVideoHeadOfficeRouter()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
        
        VideoHeadOfficeRouter aVideoHeadOfficeRouter = new VideoHeadOfficeRouter();
        
        aVideoHeadOfficeRouter.aDeviceId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aDeviceId);
        aVideoHeadOfficeRouter.aCLLI = (StringOpt) IDLUtil.toOpt(StringOpt.class, aCLLI);
        aVideoHeadOfficeRouter.aMaintenanceIPAddress = (StringOpt) IDLUtil.toOpt(StringOpt.class, aMaintenanceIPAddress);
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);       
        return aVideoHeadOfficeRouter;
    }
    
    /**
     * Method isVideoHeadOfficeRouterListEmpty.
     * @param aResponse
     * @return boolean
     */
    public boolean isVideoHeadOfficeRouterListEmpty(RetrieveCustomerTransportInfoResponseImpl aResponse) {
        
        String myMethodName = "RetrieveCustomerTransportInfo::isVideoHeadOfficeRouterListEmpty()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
        
        boolean result = true;
        try {
            if( aResponse.getVideoHeadOfficeRouterGRP().getItem().get(0) != null)
                result = false;         
        } catch (Exception e) {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "RetrieveCustomerTransportInfo::isVideoHeadOfficeRouterListEmpty()" + "->" + e.getMessage());
        }
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
        return result;
    }
    
    /**
     * Method isProductSubscriptionListEmpty.
     * @param aResponse
     * @return boolean
     */
    public boolean isProductSubscriptionListEmpty(RetrieveCustomerTransportInfoResponseImpl aResponse) {
        
        String myMethodName = "RetrieveCustomerTransportInfo::isProductSubscriptionListEmpty()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
        
        boolean result = true;
        try {
                if (aResponse.getComponentListGRP().getItem().get(0) != null) 
                    result = false;
            } catch (Exception e) {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "RetrieveCustomerTransportInfo::isProductSubscriptionListEmpty()" + "->" + e.getMessage());
        }       
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);       
        return result;
    }   
    
    /**
     * Method retrieveNPANXX.
     * @param aResponse
     * @return String
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
     */
    public String retrieveNPANXX(
        RetrieveCustomerTransportInfoResponseImpl aResponse)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {
        
        String npanxx = null, aCLLI8 = null;
        ResultSet aResultSet = null;
        DBConnection aDBConnection = null;
        PreparedStatement aPreparedStatement = null;
        
        String myMethodName =  "retrieveCustomerTransportInfo::retrieveNPANXX()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
        
        String aOrigination = (String) aProperties.get("BIS_NAME").toString();
        SeverityOpt severity = new SeverityOpt();
        
    //  aCLLI8 = "dllstxfe";
        try {
            aCLLI8 = aResponse.getActelSwitch7450().getCLLI7450().getCTEQUIPMENTCLLI().getEquipmentCLLI().substring(0,8).toLowerCase();
        } catch (Exception e) { }
        
            
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "CLLI8 received from XngRC is :  " + aCLLI8);
        
        String SQLstatement = "select NPANXX from SOAC_WIRE_CENTER where CLLI8 = ?";
        
        try {
            aDBConnection = SoacWireCenterTable.getDBConnection(aProperties, aUtility);
            aPreparedStatement = aDBConnection.getConnection().prepareStatement(SQLstatement);
            aPreparedStatement.setString(1, aCLLI8);
            aResultSet = aPreparedStatement.executeQuery();
            
            if (aResultSet.next()) {
                aUtility.log(LogEventId.DEBUG_LEVEL_1, "Result: |" + aResultSet.getString(1) + "|");
                npanxx = aResultSet.getString(1);
                aUtility.log(LogEventId.INFO_LEVEL_1," Retrieved NPANXX  " + npanxx + "  for CLLI8   " + aCLLI8);
            }
            else {
                aUtility.throwException(ExceptionCode.ERR_RM_DATA_NOT_FOUND, "NPANXX not found in SOAC_WIRE_CENTER table", aOrigination, Severity.UnRecoverable);
            }
        } catch (SQLException e) {
            aUtility.log(LogEventId.ERROR, "Error message [" + e.getMessage() + "]");
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE, "SOAC_WIRE_CENTER table lookup failure", aOrigination, Severity.UnRecoverable);
            
        } finally {
            try {
                if (aResultSet != null) aResultSet.close();
                if (aPreparedStatement != null) aPreparedStatement.close();
            } catch (Exception e) {}
            
            try {
                aDBConnection.disconnect();
            } catch (Exception e) {}
            aDBConnection = null;
        }       
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return npanxx;
    }
    
    /**
     * Method sendRequestToFACS.
     * @param aContext
     * @param aCustomerTransportId
     * @param NPANXX
     * @return Result_t
     * @throws DataNotFound
     * @throws ObjectNotFound
     * @throws NotImplemented
     * @throws SystemFailure
     * @throws BusinessViolation
     * @throws AccessDenied
     * @throws InvalidData
     */
    public Result_t sendRequestToFACS(
        BisContext aContext,
        String aFACSCustomerTransportId,
        String npanxx)
        throws
            DataNotFound,
            ObjectNotFound,
            NotImplemented,
            SystemFailure,
            BusinessViolation,
            AccessDenied,
            InvalidData {
            
        String myMethodName =  "retrieveCustomerTransportInfo::sendRequestToFACS()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);

//      npanxx = "314469";
//      aXngCustomerTransportId = "3144691000";     
        
        String aLFACSRuleFile = null, aAPPLDATA = null;
        long aFACSAccessTimeout = 0;
        FACSAccessHelper aFACSAccessService = null;
        Result_t aFacsResponse = null;
        
        String aOrigination = (String) aProperties.get("BIS_NAME").toString();
        SeverityOpt severity = new SeverityOpt();
        
        try {
            aAPPLDATA = (String) aProperties.get("FACSACCESS_APPLDATA");
            aLFACSRuleFile = (String) aProperties.get("EXCEPTION_BUILDER_LFACS_RULE_FILE");
            aFACSAccessTimeout = Long.parseLong((String) aProperties.get("FACSACCESS_TIMEOUT"));
        } catch (NumberFormatException e) { }       
        
        EventResultPair response = null;

        Header_t hdr = new Header_t("", "", "","",TrnsptType_e.FILE_TRNSPT, "");

        C1_Section_t c1 = new C1_Section_t("INQ",
                                            "FAS",
                                            "S",
                                            "",
                                            "",
                                            "",
                                            npanxx,
                                            "",
                                            "",
                                            "",
                                            "",
                                            "",
                                            "",
                                            "",
                                            "",
                                            "");

        CTL_Section_t ctl = new CTL_Section_t("",
                                              "",
                                              "",
                                              "",
                                              "",
                                              "",
                                              "",
                                              "",
                                              "");

        INQ_FASG_Section_t fasg = new INQ_FASG_Section_t(
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         aFACSCustomerTransportId,
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         "",
                                                         ""
                                                         );
                                                             
        aUtility.log(
            LogEventId.REMOTE_CALL,
            FACSAccessAccess.name,
            FACSAccessAccess.name + FACSAccessAccess.version,
            FACSAccessAccess.name + FACSAccessAccess.version,
            "INQ_FASG_REQ");

        try {
            if (helper == null)
            helper = new FACSAccessHelper(aProperties, aUtility);
            
        } catch (com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException e ) {
            aUtility.throwException(ExceptionCode.ERR_RM_FACS_ACCESS_HELPER,
                                    "FACSAccessHelper failure: " + e.getExceptionCode() + "|" + e.getMessage(),
                                    aOrigination,
                                    Severity.UnRecoverable );       
        }
        
        try {
        Fasg_Inq_Req_t request = new Fasg_Inq_Req_t(hdr, c1, ctl, fasg);
        helper.connect(aAPPLDATA, null);
        response = helper.inqFasgReq(aFACSAccessTimeout,request);


            helper.disconnect();
            
            
        } catch (com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException e) {
            aUtility.throwException(ExceptionCode.ERR_RM_TIMEOUT, 
                                    "SerivceTimeoutException: " + e.getExceptionCode() + "|" + e.getMessage(), 
                                    "FACSAccess", 
                                    Severity.UnRecoverable);
        } catch (com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException e) {
            ExceptionBuilder.parseException(
                                aContext,
                                aLFACSRuleFile,
                                null,
                                null,
                                aFacsResponse.RESP[0].ERRMSG,
                                true,
                                1,
                                null,
                                e,
                                aUtility,
                                null,
                                null,
                                null);
        }finally {
            aUtility.log(
                LogEventId.REMOTE_RETURN,
                FACSAccessAccess.name,
                FACSAccessAccess.name + FACSAccessAccess.version,
                FACSAccessAccess.name + FACSAccessAccess.version,
                "INQ_FASG_REQ");
        }
        
        
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "Received Event: " + response.getEventNbr());
            aFacsResponse = (Result_t)response.getTheObject();  
    
            if (!aFacsResponse.C1.ST.equals("S")) {
                aUtility.log(LogEventId.DEBUG_LEVEL_1,"Error Msg  : " + aFacsResponse.RESP[0].ERRMSG + "  Error Type  :  " + aFacsResponse.RESP[0].ETYP );
                
                ExceptionBuilder.parseException(
                                aContext,
                                aLFACSRuleFile,
                                null,
                                null,
                                aFacsResponse.RESP[0].ERRMSG,
                                true,
                                1,
                                null,
                                null,
                                aUtility,
                                null,
                                null,
                                null);
            }
        for (int i=0; i < aFacsResponse.LOOP.length; i++) {
            if (aFacsResponse.LOOP[i].SEG.length != 0) {
                for (int j=0; j < (aFacsResponse.LOOP[i].SEG.length); j++) {
                    aUtility.log(LogEventId.INFO_LEVEL_1," LOOP  : " + aFacsResponse.LOOP[i].LPNO 
                        + " CKID :  " + aFacsResponse.LOOP[i].CKID 
                        + " Segment No :  " + aFacsResponse.LOOP[i].SEG[j].SEGNO
                        + " Tea :  " + aFacsResponse.LOOP[i].SEG[j].TEA
                        + " TP :  " + aFacsResponse.LOOP[i].SEG[j].TP
                        + " BP : " + aFacsResponse.LOOP[i].SEG[j].BP
                        + " OBP : " + aFacsResponse.LOOP[i].SEG[j].OBP
                        + " CA : " + aFacsResponse.LOOP[i].SEG[j].CA
                        + " PR : " + aFacsResponse.LOOP[i].SEG[j].PR);
                }
            }
        }
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aFacsResponse;
    }   
    
        /**
        * Method: retrieveNoLFACS()
        * @param BisContext             aContext
        * @param StringOpt              aCustomerTransportId
        * @param CompositeObjectKey     aBillingAccountNumber
        * @param StringOpt              aSbcServingOfficeWirecenter
        * @param StringOpt              aPrimaryNpaNxx
        * @param AddressOpt             aServiceAddress
        * @param OrderAction            aOrderAction
        * @param ObjectPropertySeqOpt   aObjectProperties
        * @param String                 aTRE
        *
        * @return RetrieveCustomerTransportInfoReturn
        *
        * @throws InvalidData           : An input parameter contained invalid data.
        * @throws AccessDenied          : Access to the specified domain object or information is not allowed.
        * @throws BusinessViolation     : The attempted action violates a business rule.
        * @throws SystemFailure         : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
        * @throws NotImplemented        : The method has not been implemented.
        * @throws ObjectNotFound        : The desired domain object could not be found.
        * @throws DataNotFound          : No data found.
        */

    public RetrieveCustomerTransportInfoReturn retrieveNoLFACS(
        BisContext              aContext,
        StringOpt               aCustomerTransportId,
        CompositeObjectKey      aBillingAccountNumber,
        StringOpt               aSbcServingOfficeWirecenter,
        StringOpt               aPrimaryNpaNxx,
        AddressOpt              aServiceAddress,
        OrderAction             aOrderAction,
        ObjectPropertySeqOpt    aObjectProperties,
        String                  aTRE)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {

        String myMethodName = "RetrieveCustomerTransportInfo::retrieve()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);

        //build request item
        RetrieveCustomerTransportInfoRequestImpl aRequest = buildRequest(aContext,
                                                                         aCustomerTransportId,
                                                                         aBillingAccountNumber,
                                                                         aSbcServingOfficeWirecenter,
                                                                         aPrimaryNpaNxx,
                                                                         aServiceAddress,
                                                                         aOrderAction,
                                                                         aObjectProperties,
                                                                         aTRE);
        
        // process request
        // Checking for Service, if not creating one
        if (aService == null) {
             try {
                aService = new XNGService(aProperties, aUtility);
             }
             catch (ServiceException e)  {
                ExceptionBuilder.parseException(aContext,
                                                aRuleFile,
                                                null,
                                                null,
                                                e.getMessage(),
                                                true,
                                                1,
                                                null,
                                                e,
                                                aUtility,
                                                null,
                                                null,
                                                null);
             }
         }

        // send request to Xng
        RetrieveCustomerTransportInfoResponseImpl aResponse =
                (new SendRequestToXNG(
                        aUtility,
                        aContext,
                        aProperties,
                        aService))
                        .sendRequest(
                                aRequest,
                                aOrderAction);

        StringOpt aOrigination = IDLUtil.toOpt((String) aProperties.get("BIS_NAME").toString());
        SeverityOpt severity = new SeverityOpt();
        severity.theValue(Severity.UnRecoverable);
/*        
        Result_t aFacsResponse = null;
        if ( aResponse.getFttnResponse() != null) {
            String npanxx = null;
            
            npanxx = retrieveNPANXX(aResponse);
            
            String aXngCustomerTransportId = null; 
            try {
                aXngCustomerTransportId = aResponse.getLSCircuitID().getCTPATHID().getPathID();
            } catch (Exception e) { }           
            
            String aFACSCustomerTransportId = null;
            aFACSCustomerTransportId=aXngCustomerTransportId.replace('/','.');
            
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "CustomerTransportId send to LFACS is  :  " + aFACSCustomerTransportId );
                        
            aFacsResponse = sendRequestToFACS(aContext, aFACSCustomerTransportId, npanxx);
        }
*/        
        RetrieveCustomerTransportInfoReturn aReturnObject = buildResponse(aContext,
                                                                          aObjectProperties,
                                                                          aResponse);

        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
        return aReturnObject;
    }

    /**
     * Method : buildResponse
     * @param BisContext                                    aContext
     * @param ObjectPropertySeqOpt                          aObjectProperties
     * @param RetrieveCustomerTransportInfoResponseImpl     aResponse
     *
     * @return RetrieveCustomerTransportInfoReturn
     *
     * @throws InvalidData              : An input parameter contained invalid data.
     * @throws AccessDenied             : Access to the specified domain object or information is not allowed.
     * @throws BusinessViolation        : The attempted action violates a business rule.
     * @throws SystemFailure            : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @throws NotImplemented           : The method has not been implemented.
     * @throws ObjectNotFound           : The desired domain object could not be found.
     * @throws DataNotFound             : No data found.
     */
    public RetrieveCustomerTransportInfoReturn buildResponse(
        BisContext aContext,
        ObjectPropertySeqOpt aObjectProperties,
        RetrieveCustomerTransportInfoResponseImpl aResponse)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {

        String myMethodName = "RetrieveCustomerTransportInfo::buildResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);

        RetrieveCustomerTransportInfoReturn aReturn = initializeReturnObject();
        ComponentInfoImpl aComponentInfoImpl = null;
        boolean rgpon = false;
        
        // CustomerTransportId
        if ( aResponse.getFttnResponse() != null) {
            try {
                aReturn.aCustomerTransportId = aResponse.getLSCircuitID().getCTPATHID().getPathID();
            } catch (Exception e) { }
        }
        
        // Network7450Switch
        aReturn.aNetwork7450Switch = IDLObjectHelper.buildNetwork7450Switch(aUtility, aResponse);
        
        // SBCISPOPID
        if (!isStringEmpty(aResponse.getSBCCISPOPID()))
            aReturn.aSBCISPOPId = IDLUtil.toOpt(aResponse.getSBCCISPOPID());        

        // PathStatus
        try {
            aReturn.aPathStatus = IDLUtil.toOpt(aResponse.getServiceAreaIP().getPathStatus());
         } catch (Exception e) { }
        
        // ConnectorType
        try {
            aReturn.aConnectorType = IDLUtil.toOpt(aResponse.getServiceAreaIP().getConnectorType());
         } catch (Exception e) { }
                
        // VideoHeadOffice
        if (!isStringEmpty(aResponse.getVideoHeadOffice()))
            aReturn.aVideoHeadOffice = IDLUtil.toOpt(aResponse.getVideoHeadOffice());
        
        // OrderAction
        aReturn.aOrderAction = IDLObjectHelper.buildOrderAction(aUtility, aResponse);
        
        if ( aResponse.getFttnResponse() != null) {
            Fttn aFttn = IDLObjectHelper.buildFttnResponse(aUtility, aResponse);
            aReturn.aNetworkTypeChoice.aFttn(aFttn);
            
            try {
                aReturn.aTaperCode = IDLUtil.toOpt(aResponse.getFttnResponse().getFttnRSGRP().getTaperCode());
            } catch (Exception e) { }
        }
        
        if (aResponse.getFttpResponse() != null) {
            Fttp aFttp = IDLObjectHelper.buildFttpResponse(aUtility, aResponse);
            aReturn.aNetworkTypeChoice.aFttp(aFttp);
            
            try {
                aReturn.aTaperCode = IDLUtil.toOpt(aResponse.getFttpResponse().getFttpRSGRP().getTaperCode());
            } catch (Exception e) { }
        }
        //RGPON indicator   
/*      if (! isProductSubscriptionListEmpty(aResponse) ) {
            aComponentInfoImpl = (ComponentInfoImpl) aResponse.getComponentListGRP().getItem().get(0);
            try {
                rgpon = Boolean.valueOf(aComponentInfoImpl.getRGPONIndicator()).booleanValue();
            } catch (Exception e) {
                rgpon = false;
            }       
        }
*/
//      aReturn.aRGPONIndicator=rgpon;
        
        //Network Type
        if(rgpon)
            aReturn.aNetworkType = IDLUtil.toOpt(NetworkTypeValues.RGPON);
                                    
        else if(aResponse.getFttpResponse()!=null)
            aReturn.aNetworkType = IDLUtil.toOpt(NetworkTypeValues.FTTP);               
        else
            aReturn.aNetworkType = IDLUtil.toOpt(NetworkTypeValues.FTTN);                                   
                                
        if (aResponse.getVideoHeadOfficeRouterGRP() != null)
            aReturn.aVideoHeadOfficeRouters = (VideoHeadOfficeRouterSeqOpt) IDLUtil.toOpt(VideoHeadOfficeRouterSeqOpt.class, buildVideoHeadOfficeRouter(aResponse));
        
        if (aResponse.getComponentListGRP() != null)
            aReturn.aProductSubscriptions = buildProductSubscriptions(aResponse);
        
        aReturn.aContext = aContext;
            
        //Set VPLS Domain   and SAPID                                                                     
        ObjectPropertyManager aOPMgr = new ObjectPropertyManager();
                        
        try{
            if(aObjectProperties != null && aObjectProperties.theValue().length > 0)
                aOPMgr.addAll(aObjectProperties.theValue());        
        }catch(Exception ex){
            // do nothing
            aUtility.log(LogEventId.DEBUG_LEVEL_1, ex.getMessage());    
        }
        
        if(aResponse.getVPLSDomain()!=null)
            aOPMgr.add("aVPLSDomain", aResponse.getVPLSDomain()); 
        
        if(aResponse.getVPLSSAPID()!=null)
            aOPMgr.add("aServiceAccessPoint", aResponse.getVPLSSAPID()); 
            
        aReturn.aProperties = (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class,aOPMgr.toArray());    
            
                        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
        return aReturn;         
    }
}