// $Id: CreateFacilityAssignment.java,v 1.40 2006/12/12 15:25:56 rd2842 Exp $
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
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 04/12/2005  Rene Duka    Creation.
//# 05/10/2005  Rene Duka    Added business interface logic.
//# 05/11/2005  Rene Duka    Modified to reflect changes to Xng RC wrapper.
//# 05/12/2005  Rene Duka    Modified to conform to the new OUTPUT schema from Xng.
//# 05/20/2005  Rene Duka    Modified for TestClient testing.
//# 05/31/2005  Rene Duka    Made changes per WT notes.
//# 06/06/2005  Rene Duka    Made changes per unit testing with XNG RC.
//# 06/23/2005  Rene Duka    Modified for LS Release 1.
//# 06/28/2005  Rene Duka    Modified to conform to the changes made to XnGRCServiceWrapper.
//# 07/21/2005  Rene Duka    Modified for LS Release 1.
//# 08/03/2005  Rene Duka    Modified for LS Release 1.
//# 08/12/2005  Rene Duka    DR 142178: Exception Handling.
//# 08/23/2005  Rene Duka    DR 142785: Make NpaNxx optional.
//# 09/01/2005  Rene Duka    DR 142785: Make NpaNxx optional.
//# 09/06/2005  Rene Duka    DR 142785: Additional fix, set it NpaNxx to blank if null.
//# 09/20/2005  Rene Duka    DR 143677: Set F1CableID and F2CableID to blank if null.
//# 11/11/2005  Rene Duka    Made changes for IDL Bundle 33.
//# 01/19/2006  Rene Duka    Made changes for LS Release 2.
//# 02/03/2006  Rene Duka    CR 8486 - Remove Service Bundle Id
//#                          CR 8535 - Remove Maintenance Inside Wiring (Granite IA sync-up)
//#                          CR 8248 - Remove Component Structure
//# 02/08/2006  Rene Duka    Set NpaNxx, StreetAddress, City and State to "" if not provided to avoid JaxB error.
//# 02/17/2006  Rene Duka    CR 8568: Handle validation of IBP/OBP on the FTTN-SAI DSLAM scenario.
//# 03/23/2006  Chaitanya    CR 9347 : Parse DSLAM Name and DSLAM Port from DSLAMId 
//# 05/10/2006  Mrinalini    Updated for LS3 changes
//# 06/13/2006  Mrinalini    Made changes for LS3
//# 06/16/2006  Mrinalini    LS3 CFA code walk thru changes.
//# 09/29/2006  Rene Duka    CR 10882 - Use common code to format OLT Port/ONT Index.
//# 10/02/2006  mp9129       Changes for LS4
//# 11/02/2006  mp9129       DR 170882: Set OrderActionId, OrderActionType value in CreateResponse()
//# 12/11/2006  Rene Duka    CR 12340 - Make NpaNxx, HouseNumber, and StreetName required.

package com.sbc.eia.bis.BusinessInterface.rm.CreateFacilityAssignment;

import java.util.Hashtable;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.xng.XNGService;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.CTPATHIDImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.CTPORTIDImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.CTSITECITYImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.CTSITEIDImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.CTSITEPOSTCODE1Impl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.CTSITEPOSTCODE2Impl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.CTSITESTATEPROVImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.CreateFacilityRequestImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.FTTNTImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.FTTPTImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.FacilityAddressImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.FttnRequestImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.FttpRequestImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.InBindingPostImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.LSCircuitIDImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.OLTTImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.ONTTImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.OutBindingPostImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.ParentSiteCLLIImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.SeqDefaultFTTNImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.SeqDefaultFTTPImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.ServiceAreaIPImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityResponse.impl.CreateFacilityResponseImpl;
import com.sbc.eia.bis.embus.service.xng.access.XNGHelper;
import com.sbc.eia.bis.embus.service.xng.helpers.SendRequestToXNG;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.DSLAMIdHelper;
import com.sbc.eia.bis.rm.utilities.IDLObjectHelper;
import com.sbc.eia.bis.rm.utilities.ONTAIdHelper;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerGranite;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.rm.CreateFacilityAssignmentReturn;
import com.sbc.eia.idl.rm_ls_types.CopperSegment;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransport;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.FiberSegment;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.Network7450Switch;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeChoice;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
 * @author rd2842
 *
 */
public class CreateFacilityAssignment {

    private Utility aUtility = null;
    private Hashtable aProperties = null;
    private String aRuleFile = null;    
    private XNGService aService = null;
    
    /**
     * Constructor for ReserveActivateFacilityAssignment
     * @param Utility utility
     * @param Hashtable properties
     */
    public CreateFacilityAssignment (Utility utility, Hashtable properties) {
        aProperties = properties;
        aUtility = utility;
        aRuleFile = (String) properties.get(SendRequestToXNG.XNG_EXCEPTION_RULE_FILE_TAG);        
    }

    /**
     * Method: create
     * @param BisContext            aContext
     * @param String                aCustomerTransportId
     * @param CompositeObjectKey    aBillingAccountNumber
     * @param Location              aServiceLocation
     * @param StringOpt             aServiceBundleId
     * @param VOIPOpt               aVOIP
     * @param BooleanOpt            aMaintenanceFlag
     * @param EiaDate               aDueDate
     * @param OrderAction           aOrderAction
     * @param ProductSubscription[] aProductSubscriptions
     * @param StringOpt             aTaperCode
     * @param NetworkType           aNetworkTypeChoice
     * @param ObjectPropertySeqOpt  aProperties
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     */
    public CreateFacilityAssignmentReturn create(
        BisContext aContext,
        String aCustomerTransportId,
        CompositeObjectKey aBillingAccountNumber,
        Location aServiceLocation,
        BooleanOpt aMaintenanceFlag,
        EiaDate aDueDate,
        OrderAction aOrderAction,
        StringOpt aTaperCode, 
        String aNetworkType,
        NetworkType aNetworkTypeChoice,
        ObjectPropertySeqOpt aObjectProperties)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {

        String myMethodName = "CreateFacilityAssignment:create()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        // build request item
        CreateFacilityRequestImpl aRequest = createRequest(aContext,
                                                           aCustomerTransportId,
                                                           aBillingAccountNumber,
                                                           aServiceLocation,
                                                           aMaintenanceFlag,
                                                           aDueDate,
                                                           aOrderAction,
                                                           aTaperCode, 
                                                           aNetworkType,
                                                           aNetworkTypeChoice,
                                                           aObjectProperties);

        // process request
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
                                                aUtility,            // utility
                                                null,               // origin use file
                                                null,               // severity use file
                                                null);
            }
        }

        // send request to Xng
        CreateFacilityResponseImpl aResponseImpl = (new SendRequestToXNG(aUtility,
                                                                         aContext,
                                                                         aProperties,
                                                                         aService)).sendRequest(aRequest,
                                                                                                aOrderAction);

        // build return object
        CreateFacilityAssignmentReturn aReturnObject = createResponse(aContext,
                                                                      aObjectProperties,
                                                                      aResponseImpl,
                                                                      aOrderAction);                                                                      


        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aReturnObject;
    }

    /**
     * Method: createRequest
     * @return CreateFacilityRequestImpl
     * @param BisContext            aContext
     * @param String                aCustomerTransportId
     * @param CompositeObjectKey    aBillingAccountNumber
     * @param Location              aServiceLocation
     * @param BooleanOpt            aMaintenanceFlag
     * @param EiaDate               aDueDate
     * @param OrderAction           aOrderAction
     * @param StringOpt             aTaperCode
     * @param String                aNetworkType
     * @param NetworkType           aNetworkTypeChoice
     * @param ObjectPropertySeqOpt  aObjectProperties
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     */
    private CreateFacilityRequestImpl createRequest(
        BisContext aContext,
        String aCustomerTransportId,
        CompositeObjectKey aBillingAccountNumber,
        Location aServiceLocation,
        BooleanOpt aMaintenanceFlag,
        EiaDate aDueDate,
        OrderAction aOrderAction,
        StringOpt aTaperCode, 
        String aNetworkType,
        NetworkType aNetworkTypeChoice,
        ObjectPropertySeqOpt aObjectProperties)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {

        String myMethodName = "CreateFacilityAssignment:createRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        CreateFacilityRequestImpl aRequest = new CreateFacilityRequestImpl();

        // REQUIRED: Schema Version
        aRequest.setSchemaVersion(XNGHelper.XNG_RC_VERSION);

        // REQUIRED: LS_CircuitID - aCustomerTransportId
        CTPATHIDImpl aCTPATHIDImpl = new CTPATHIDImpl();
        aCTPATHIDImpl.setPathID(aCustomerTransportId.trim());
        LSCircuitIDImpl aLSCircuitIDImpl = new LSCircuitIDImpl();
        aLSCircuitIDImpl.setCTPATHID(aCTPATHIDImpl);
        // set LS_CircuitID in the REQUEST
        aRequest.setLSCircuitID(aLSCircuitIDImpl);

        // REQUIRED: BAN - aBillingAccountNumber
        ObjectKey[] aObjectKeys = aBillingAccountNumber.aKeys;
        for (int i = 0; i < aObjectKeys.length; i++) {
            if (aObjectKeys[i].aKind.equals("com.sbc.eia.bis.BillingAccountNumber")) {
                // set BAN in the REQUEST
                aRequest.setBAN(aObjectKeys[i].aValue);             
                break;
            }
        }

        // REQUIRED: OrderNumber - aOrderActionId.aOrder
        aRequest.setOrderNumber(aOrderAction.aOrder.theValue());

        // REQUIRED: FacilityAddress - aServiceLocation.aFieldedAddress (HouseNumber and StreetName)
        ProviderLocationProperty aProviderLocationProperty = null;
        Address aFacilityAddress = null;
        try {
            aProviderLocationProperty = aServiceLocation.aProviderLocationProperties[0];
            aFacilityAddress = aProviderLocationProperty.aServiceAddress.theValue();
        }
        catch (org.omg.CORBA.BAD_OPERATION e) {
            aUtility.log(LogEventId.EXCEPTION, "aServiceLocation.aProviderLocationProperties[0].aServiceAddress.theValue()<CORBA_Exception>");
        }
        catch (NullPointerException e) {
            aUtility.log(LogEventId.EXCEPTION, "aServiceLocation.aProviderLocationProperties[0].aServiceAddress.theValue()<null>");
        }

        // FacilityAddress
        FacilityAddressImpl aFacilityAddressImpl = new FacilityAddressImpl();
        if (aFacilityAddress != null) {
            AddressHandlerGranite aGraniteAddress = null;
            try {
                aGraniteAddress = new AddressHandlerGranite(aFacilityAddress);
            }
            catch (AddressHandlerException e) {
                aUtility.log(LogEventId.EXCEPTION, "aServiceLocation.aProviderLocationProperties[0].aServiceAddress.theValue().aFieldedAddress<Addess_Exception>");
            }
            
            //REQUIRED AddressID
            aFacilityAddressImpl.setAddressID(aGraniteAddress.getAddressId().trim());
            // StreetAddress
            aFacilityAddressImpl.setStreetAddress(aGraniteAddress.getStreetAddress().trim());
            // City
            CTSITECITYImpl aCTSITECITYImpl = new CTSITECITYImpl();
            aCTSITECITYImpl.setSiteCity(aGraniteAddress.getCity().trim());
            aFacilityAddressImpl.setCTSITECITY(aCTSITECITYImpl);
            // State
            CTSITESTATEPROVImpl aCTSITESTATEPROVImpl = new CTSITESTATEPROVImpl();
            aCTSITESTATEPROVImpl.setSiteStateProv(aGraniteAddress.getState().trim());
            aFacilityAddressImpl.setCTSITESTATEPROV(aCTSITESTATEPROVImpl);
            // PostalCode
            CTSITEPOSTCODE1Impl aCTSITEPOSTCODE1Impl = new CTSITEPOSTCODE1Impl();
            aCTSITEPOSTCODE1Impl.setSitePostCode1(aGraniteAddress.getPostalCode().trim());
            aFacilityAddressImpl.setCTSITEPOSTCODE1(aCTSITEPOSTCODE1Impl);
            // PostalCodePlus4
            CTSITEPOSTCODE2Impl aCTSITEPOSTCODE2Impl = new CTSITEPOSTCODE2Impl();
            aCTSITEPOSTCODE2Impl.setSitePostCode2(aGraniteAddress.getPostalCodePlus4().trim());
            aFacilityAddressImpl.setCTSITEPOSTCODE2(aCTSITEPOSTCODE2Impl);
            // StructureType
            aFacilityAddressImpl.setStructureType(aGraniteAddress.getStructType());
            // StructureValue
            aFacilityAddressImpl.setStructureValue(aGraniteAddress.getStructValue());
            // ElevationType
            aFacilityAddressImpl.setElevationType(aGraniteAddress.getLevelType());
            // ElevationValue
            aFacilityAddressImpl.setElevationValue(aGraniteAddress.getLevelValue());
            // UnitType
            aFacilityAddressImpl.setUnitType(aGraniteAddress.getUnitType());
            // UnitValue
            aFacilityAddressImpl.setUnitValue(aGraniteAddress.getUnitValue());
        }
        
        // set FacilityAddress in the REQUEST
        aRequest.setFacilityAddress(aFacilityAddressImpl);

        ServiceAreaIPImpl aServiceAreaIPImpl = new ServiceAreaIPImpl();

        // REQUIRED:  NPANXX
        aServiceAreaIPImpl.setNPANXX(aProviderLocationProperty.aPrimaryNpaNxx.theValue().trim());

        // REQUIRED: ParentSiteCLLI
        CTSITEIDImpl aCTSITEIDImpl_CLLI = new CTSITEIDImpl();
        aCTSITEIDImpl_CLLI.setSiteID(aProviderLocationProperty.aSbcServingOfficeWirecenter.theValue().trim());
        ParentSiteCLLIImpl aParentSiteCLLIImpl = new ParentSiteCLLIImpl();
        aParentSiteCLLIImpl.setCTSITEID(aCTSITEIDImpl_CLLI);
        aServiceAreaIPImpl.setServiceAreaWireCenterCLLI(aParentSiteCLLIImpl);
        
        /*
        // If extracting from aFttn.aDSLAM.aLocation
        if (isFttn) {
            // REQUIRED for FTTN: NPANXX - 
            if (!OptHelper.isStringOptEmpty(aProviderDSLAMLocationProperty.aPrimaryNpaNxx)) {
                aServiceAreaIPImpl.setNPANXX(aProviderDSLAMLocationProperty.aPrimaryNpaNxx.theValue().trim());
            }

            // REQUIRED for FTTN: ParentSiteCLLI
            if (!OptHelper.isStringOptEmpty(aProviderDSLAMLocationProperty.aSbcServingOfficeWirecenter)) {
                CTSITEIDImpl aCTSITEIDImpl_CLLI = new CTSITEIDImpl();
                aCTSITEIDImpl_CLLI.setSiteID(aProviderDSLAMLocationProperty.aSbcServingOfficeWirecenter.theValue().trim());
                ParentSiteCLLIImpl aParentSiteCLLIImpl = new ParentSiteCLLIImpl();
                aParentSiteCLLIImpl.setCTSITEID(aCTSITEIDImpl_CLLI);
                aServiceAreaIPImpl.setServiceAreaWireCenterCLLI(aParentSiteCLLIImpl);
            }
        }
        */

        // REQUIRED: DueDate - aDueDate
        String aDateString = "";
        short aMonth = aDueDate.aMonth;
        short aDay = aDueDate.aDay;
        short aYear = aDueDate.aYear;
        if (aMonth < 10) {
            aDateString +="0";
        }
        aDateString = aDateString + aMonth + "-";
        if (aDay < 10) {
            aDateString +="0";
        }
        aDateString = aDateString + aDay + "-";
        if (aYear < 10) {
            aDateString +="0";
        }
        aDateString = aDateString + aYear;
        aServiceAreaIPImpl.setDueDate(aDateString);

        // set ServiceAreaIP in the REQUEST
        aRequest.setServiceAreaIP(aServiceAreaIPImpl);

        if (aNetworkTypeChoice.discriminator() == NetworkTypeChoice.FTTN_Network) {
            // Get Fttn
            Fttn aFttn = null;
            try {
                aFttn = aNetworkTypeChoice.aFttn();
            }
            catch (Exception e) {
                aUtility.log(LogEventId.EXCEPTION, "aNetworkTypeChoice.aFttn<Exception>");
            }

            FttnRequestImpl aFttnRequestImpl = new FttnRequestImpl();
            // OPTIONAL: DSLAMEquipmentId
            // OPTIONAL: CODSLAMPort => Per DMS, Required only if it is in FTTN CO DSLAM scenario. RMBIS to derive from DSLAM ID."
            if (!OptHelper.isDSLAMTransportOptNull(aFttn.aDSLAM)) {
                if (!OptHelper.isStringOptEmpty(aFttn.aDSLAM.theValue().aId)) {
                    
                    DSLAMIdHelper aDSLAM_Id_Helper = new DSLAMIdHelper(aUtility, aProperties);
                    aDSLAM_Id_Helper.splitDSLAM(aFttn.aDSLAM.theValue().aId);
                    
                    aFttnRequestImpl.setDSLAMEquipmentId(aDSLAM_Id_Helper.getDSLAMId());
                    aFttnRequestImpl.setCODSLAMPort(aDSLAM_Id_Helper.getDSLAMPort());
                    
                    //aFttnRequestImpl.setDSLAMEquipmentId(aFttn.aDSLAM.theValue().aId.theValue());
                    //aFttnRequestImpl.setCODSLAMPort(aFttn.aDSLAM.theValue().aId.theValue());
                }
            }

            // OPTIONAL: TaperCode
            if (!OptHelper.isStringOptEmpty(aTaperCode)) {
                aFttnRequestImpl.setTaperCode(aTaperCode.theValue().trim());
            }

            // REQUIRED for FTTN: Fttn_GRP
            SeqDefaultFTTNImpl aSeqDefaultFTTNImpl = new SeqDefaultFTTNImpl();
            if (!OptHelper.isCopperSegmentSeqOptEmpty(aFttn.aSegments)) {
                CopperSegment[] aCopperSegment = aFttn.aSegments.theValue();
                for (int i = 0; i < aCopperSegment.length; i++) {
                    FTTNTImpl aFTTNTImpl = new FTTNTImpl();
                    // REQUIRED for FTTN: CableID
                    aFTTNTImpl.setCableID(aCopperSegment[i].aInCableName.theValue().trim());
                    // REQUIRED for FTTN: CablePairNumber
                    try {
                        aFTTNTImpl.setCablePairNumber(Integer.parseInt(aCopperSegment[i].aInCablePair.theValue().trim()));
                    }
                    catch (NumberFormatException e) {
                        aUtility.log(LogEventId.EXCEPTION, "aFttn.aSegments.theValue().aInCablePair.theValue()<NOT an Integer>");
                    }
                    // OPTIONAL: InBindingPost
                    if (!OptHelper.isStringOptEmpty(aCopperSegment[i].aInBindingPost)) {
                        CTPORTIDImpl aCTPORTIDImpl_IBP = new CTPORTIDImpl();
                        aCTPORTIDImpl_IBP.setPortID(aCopperSegment[i].aInBindingPost.theValue().trim());
                        InBindingPostImpl aInBindingPostImpl = new InBindingPostImpl();
                        aInBindingPostImpl.setCTPORTID(aCTPORTIDImpl_IBP);
                        aFTTNTImpl.setInBindingPost(aInBindingPostImpl);
                    }
                    // OPTIONAL: OutBindingPost
                    if (!OptHelper.isStringOptEmpty(aCopperSegment[i].aOutBindingPost)) {
                        CTPORTIDImpl aCTPORTIDImpl_OBP = new CTPORTIDImpl();
                        aCTPORTIDImpl_OBP.setPortID(aCopperSegment[i].aOutBindingPost.theValue().trim());
                        OutBindingPostImpl aOutBindingPostImpl = new OutBindingPostImpl();
                        aOutBindingPostImpl.setCTPORTID(aCTPORTIDImpl_OBP);
                        aFTTNTImpl.setOutBindingPost(aOutBindingPostImpl);
                    }
                    // OPTIONAL: OutPairColor
                    if (!OptHelper.isStringOptEmpty(aCopperSegment[i].aOutPairColor)) {
                        aFTTNTImpl.setOutPairColor(aCopperSegment[i].aOutPairColor.theValue().trim());
                    }
                    // REQUIRED: FttnEquipmentId
                    aFTTNTImpl.setFttnEquipmentId(aCopperSegment[i].aTerminalId.theValue().trim());
                    // Format
                    aSeqDefaultFTTNImpl.getFTTNSegmentNumber().add(aFTTNTImpl);
                }
            }
            aFttnRequestImpl.setFttnGRP(aSeqDefaultFTTNImpl);
            aRequest.setFttnRequest(aFttnRequestImpl);
        }

        if (aNetworkTypeChoice.discriminator() == NetworkTypeChoice.FTTP_Network) {
            // Get Fttp
            Fttp aFttp = null;
            try {
                aFttp = aNetworkTypeChoice.aFttp();
            }
            catch (Exception e) {
                aUtility.log(LogEventId.EXCEPTION, "aNetworkTypeChoice.aFttp<Exception>");
            }

            FttpRequestImpl aFttpRequestImpl = new FttpRequestImpl();
            SeqDefaultFTTPImpl aSeqDefaultFTTPImpl = new SeqDefaultFTTPImpl();                    

            // instantiate ONTAIdHelper
            ONTAIdHelper aONTAId_Helper = new ONTAIdHelper(aUtility, aProperties);
            aONTAId_Helper.parse(aFttp.aOpticalNetworkTerminal.theValue().aAccessId);
            
            // REQUIRED for FTTP: OLT_t
            OLTTImpl aOLTTImpl = new OLTTImpl();
            // REQUIRED for FTTP: OLT_Id
            aOLTTImpl.setOLTId(aFttp.aOpticalLineTerminal.theValue().aId.theValue().trim());
            // REQUIRED for FTTP: OLT_Model
            aOLTTImpl.setOLTModel(aFttp.aOpticalLineTerminal.theValue().aModelNumber.theValue().trim());
            // DERIVED: OLT_Port - Derived from ONT AID
            // ONT AID is of the format : Rack-Shelf-Slot-Port-Index. 
            // For example: If ONT AID is ONT-1-1-1-1-9, the OLT Port is 1-1-1-1.
            //              If ONT AID is ONTVDSL-2-1-1-1-5, the OLT Port is 2-1-1-1.
            aOLTTImpl.setOLTPort(aONTAId_Helper.getOLTPort());
            // REQUIRED for FTTP: OLT_TargetId
            aOLTTImpl.setOLTTargetId(aFttp.aOpticalLineTerminal.theValue().aEquipmentTargetId.theValue().trim());
            // Format
            aSeqDefaultFTTPImpl.setOLTT(aOLTTImpl);

            // REQUIRED for FTTP: ONT_t
            ONTTImpl aONTTImpl = new ONTTImpl();            
            // REQUIRED for FTTP: ONT_Id
            aONTTImpl.setONTId(aFttp.aOpticalNetworkTerminal.theValue().aId.theValue().trim());
            // REQUIRED for FTTP: ONT_Model
            aONTTImpl.setONTModel(aFttp.aOpticalNetworkTerminal.theValue().aModelNumber.theValue().trim());
            // REQUIRED for FTTP: ONT_Port
            aONTTImpl.setONTPort(aFttp.aOpticalNetworkTerminal.theValue().aPort.theValue().trim());
            // REQUIRED for FTTP: ONT_AID
            aONTTImpl.setONTAID(aFttp.aOpticalNetworkTerminal.theValue().aAccessId.theValue().trim());
            // DERIVED: ONT Index - Derived from ONT AID
            // ONT AID is of the format : Rack-Shelf-Slot-Port-Index
            // For example: If ONT AID is ONT-1-1-1-1-9, the ONT Index is 9.
            //              If ONT AID is ONTVDSL-2-1-1-1-5, the ONT Index is 5.
            aONTTImpl.setONTIndex(aONTAId_Helper.getONTIndex());
            // Format
            aSeqDefaultFTTPImpl.setONTT(aONTTImpl);

            // OPTIONAL: TaperCode
            if (!OptHelper.isStringOptEmpty(aTaperCode)) {
                aSeqDefaultFTTPImpl.setTaperCode(aTaperCode.theValue().trim());
            }

           // REQUIRED for FTTP: FTTP_t
            if (!OptHelper.isFiberSegmentSeqOptEmpty(aFttp.aSegments)) {
                FiberSegment[] aFiberSegment = aFttp.aSegments.theValue();
                for (int i = 0; i < aFiberSegment.length; i++) {
                    FTTPTImpl aFTTPTImpl = new FTTPTImpl();
                    // OPTIONAL for FTTP: Fttp_CableId
                    if(!OptHelper.isStringOptEmpty(aFiberSegment[i].aInFiberCable)){
                        aFTTPTImpl.setFttpCableId(aFiberSegment[i].aInFiberCable.theValue().trim());
                    }
                    // OPTIONAL for FTTP: Fttp_Strand
                    try {
                        if(!OptHelper.isStringOptEmpty(aFiberSegment[i].aInFiberStrand)){
                            aFTTPTImpl.setFttpStrand(Integer.parseInt(aFiberSegment[i].aInFiberStrand.theValue().trim()));
                        }
                    }
                    catch (NumberFormatException e) {
                        aUtility.log(LogEventId.EXCEPTION, "aFttp.aSegments.theValue().aInFiberStrand.theValue()<NOT an Integer>");
                    }
                    // OPTIONAL for FTTP: Fttp_EquipmentId
                    if(!OptHelper.isStringOptEmpty(aFiberSegment[i].aTerminalId)){
                        aFTTPTImpl.setFttpEquipmentId(aFiberSegment[i].aTerminalId.theValue().trim());
                    }
                    // OPTIONAL for FTTP: Fttp_Network_PortId
                    if(!OptHelper.isStringOptEmpty(aFiberSegment[i].aNetworkPort)){
                        aFTTPTImpl.setFttpNetworkPortId(aFiberSegment[i].aNetworkPort.theValue().trim());
                    }
                    // OPTIONAL for FTTP: Fttp_Access_PortId
                    if(!OptHelper.isStringOptEmpty(aFiberSegment[i].aAccessPort)){
                        aFTTPTImpl.setFttpAccessPortId(aFiberSegment[i].aAccessPort.theValue().trim());
                    }
                    // Format
                    aSeqDefaultFTTPImpl.getFTTPSegmentNumber().add(aFTTPTImpl);
                }
            }
            aFttpRequestImpl.setFttpGRP(aSeqDefaultFTTPImpl);
            aRequest.setFttpRequest(aFttpRequestImpl);
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aRequest;
    }

    /**
     * Method: createResponse
     * @return CreateFacilityAssignmentReturn
     * @param BisContext            aContext
     * @param ObjectPropertySeqOpt  aObjectProperties
     * @param ResponseMessageImpl   aResponseImpl
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     */
    private CreateFacilityAssignmentReturn createResponse(
        BisContext aContext,
        ObjectPropertySeqOpt aObjectProperties,
        CreateFacilityResponseImpl aResponseImpl,
        OrderAction aOrderAction)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {

        String myMethodName = "CreateFacilityAssignment:createResponse()";
        String aBisName = aProperties.get("BIS_NAME").toString();
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        // CustomerTransportId
        String aCustomerTransportId = aResponseImpl.getCustomerTransportID().getCTPATHID().getPathID();

        // DSLAMOpt
        DSLAMTransport aDSLAM = IDLObjectHelper.buildDSLAM(aUtility,
                                                           aResponseImpl,
                                                           aBisName);

        DSLAMTransportOpt aDSLAMOpt = (DSLAMTransportOpt) IDLUtil.toOpt(DSLAMTransportOpt.class, aDSLAM);

        // Network7450Switch
        Network7450Switch aNetwork7450Switch = IDLObjectHelper.buildNetwork7450Switch(aUtility,
                                                                                      aResponseImpl,
                                                                                      aBisName);
        // SBCISPOPID
        StringOpt aSBCISPOPId = (StringOpt) IDLUtil.toOpt(aResponseImpl.getSBCCISPOPID());

        // ServingWireCenterClli
        StringOpt aServingWireCenterClli = (StringOpt) IDLUtil.toOpt(aResponseImpl.getServingWireCenterClli());

        // TaperCode
        StringOpt aTaperCode = (StringOpt) IDLUtil.toOpt(aResponseImpl.getTaperCode());
        
        //VPLS Domain
         StringOpt aVPLSDomain= (StringOpt)IDLUtil.toOpt(aResponseImpl.getVPLS());

        // OrderAction
         aOrderAction = IDLObjectHelper.buildOrderAction(aUtility,
                                                                    aResponseImpl,
                                                                    aBisName,
                                                                    aOrderAction);

            
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        
        return new CreateFacilityAssignmentReturn(aContext,
                                                   aCustomerTransportId,
                                                   aDSLAMOpt,
                                                   aNetwork7450Switch,
                                                   aSBCISPOPId,
                                                   aTaperCode,
                                                   aVPLSDomain,
                                                   aOrderAction,
                                                   aServingWireCenterClli,
                                                   aObjectProperties);
    }
}