// $Id: CreateFacilityAssignment.java,v 1.29 2006/10/25 21:35:52 mp9129 Exp $
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
//# 03/30/2005  jp2854       Creation.
//# 04/08/2005  Jinmin Ni    Changed to accommodate the IDL interface change
//# 05/10/2005  Rene Duka    Added validation.
//# 05/20/2005  Rene Duka    Added conditional validations for FTTp and FTTn.
//# 05/31/2005  Rene Duka    Made changes per WT notes.
//# 06/01/2005  Chaitanya    Changed LOG_ENTRY, LOG_EXIT to LOG_DEBUG_LEVEL_1
//# 06/08/2005  Rene Duka    Logged input data before validation.
//# 06/23/2005  Rene Duka    Added validation for aDSLAM.aServiceAreaInterfaceLocation.aProviderLocationProperties[].aPrimaryNpaNxx.
//# 07/21/2005  Rene Duka    Modified for LS Release 1.
//# 08/03/2005  Rene Duka    Modified for LS Release 1.
//# 08/23/2005  Rene Duka    DR 142785: Make NpaNxx optional.
//# 11/11/2005  Rene Duka    Made changes for IDL Bundle 33.
//# 01/19/2006  Rene Duka    Made changes for LS Release 2.
//# 01/25/2006  Rene Duka    Made changes for LS Release 2.
//# 02/17/2006  Rene Duka    CR 8568: Handle validation of IBP/OBP on the FTTN-SAI DSLAM scenario.
//# 03/02/2006  Rene Duka    DR 156614: Fix CORBA Error when DSLAMTransport is null.
//# 03/28/2006  Rene Duka    CR 9517: OBP and IBP optional for FTTN - SAI DSLAM scenario.
//# 04/21/2006  Rene Duka    CR xxxx: OBP and IBP optional for FTTN (SAI and CO DSLAM) scenario.
//# 05/10/2006  Mrinalini	 Updated for LS3 changes	
//# 06/13/2006  Mrinalini    Made Changes for LS3
//# 10/02/2006  mp9129		 Changes for LS4.

package com.sbc.eia.bis.facades.rm.transactions.CreateFacilityAssignment;

import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.bis.rm.utilities.ValidateHelper;
import com.sbc.eia.bis.validator.Validator;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.lim_types.bishelpers.LocationBisHelper;
import com.sbc.eia.idl.rm.CreateFacilityAssignmentReturn;
import com.sbc.eia.idl.rm.bishelpers.CreateFacilityAssignmentReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.CopperSegment;
import com.sbc.eia.idl.rm_ls_types.FiberSegment;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeChoice;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.bishelpers.CopperSegmentSeqOptBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.DSLAMTransportOptBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.FiberSegmentSeqOptBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.FttnBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.FttpBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.NetworkTypeBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.OpticalLineTerminalOptBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.OpticalNetworkTerminalOptBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.OrderActionBisHelper;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.BooleanOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.CompositeObjectKeyBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringOptBisHelper;

/**
 * @author jp2854
 */
public class CreateFacilityAssignment extends TranBase {

    private Utility aUtility = null;
    private Hashtable aProperties = null;
    com.sbc.eia.bis.BusinessInterface.rm.CreateFacilityAssignment.CreateFacilityAssignment aCreateFacilityAssignment = null;
    
    private Validator validator = null;

    /**
     * Constructor for CreateFacilityAssignment.
     */
    public CreateFacilityAssignment() {
        super();
    }

    /**
     * Constructor for CreateFacilityAssignment.
     * @param param
     */
    public CreateFacilityAssignment(Hashtable param) {
        super(param);
        aUtility = this;
        aProperties = getPROPERTIES();
    }

    /**
     * Method: execute => createFacilityAssignment
     *
     * OMS will request facility assignment info (Rack/Shelf/Slot/Port) from Xng
     * via RM-BIS.
     *
     * @return CreateFacilityAssignmentReturn
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
     * @param ObjectPropertySeqOpt  aProperties
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     */
    public CreateFacilityAssignmentReturn execute(
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
        ObjectPropertySeqOpt aObjectProperties,
        Logger aLogger)
        throws
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented {

        String myMethodName = "CreateFacilityAssignment:execute()";
        myLogger = aLogger;
        callerContext = aContext;
        
        log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

        ObjectPropertyManager aContextOPM = new ObjectPropertyManager(aContext.aProperties);

        // log BisContext
        try {
            log(LOG_INPUT_DATA, "aContext=<" + (new BisContextBisHelper(aContext)).toString() + ">");
        }
        catch (Exception e) {
            log(LOG_INPUT_DATA, "aContext<null>");
        }

        CreateFacilityAssignmentReturn createFacilityAssignmentReturn = null;
        try {
            // validate BisContext
            ValidateHelper.validateBisContext(
                aUtility,
                aContextOPM,
                aProperties.get("BIS_NAME").toString());

             // is client authorized
            validateClient(aContextOPM,
                           null, // group_id
                           ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
                           ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION);
                           
		
		 // validate input         
			if (validator == null) {
				validator =
					new Validator(
						aUtility,
						aProperties,
						(String) aProperties.get(
							"CFA_VALIDATOR_VARIABLE_MAP_FILE"));
			}                           
			
			validator.validate(aContext,
			new CreateFacilityAssignmentValidation(
				aContext,
				aCustomerTransportId,
                aBillingAccountNumber,
                aServiceLocation,
               	aMaintenanceFlag,
                aDueDate,
                aOrderAction,
                aTaperCode,
                aNetworkType,
	            aNetworkTypeChoice,
                aObjectProperties),true);

 
            // create
            if (aCreateFacilityAssignment == null) {
                aCreateFacilityAssignment = new com.sbc.eia.bis.BusinessInterface.rm.CreateFacilityAssignment.CreateFacilityAssignment(aUtility, getPROPERTIES());
            }

            createFacilityAssignmentReturn = aCreateFacilityAssignment.create(aContext,
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

            // log output
            logOutput(createFacilityAssignmentReturn);

        }
        finally  {
            log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
        }
        return createFacilityAssignmentReturn;
    }

    /**
     * Method: logOutput
     * @param CreateFacilityAssignmentReturn aReturnObject
     */
    private void logOutput(CreateFacilityAssignmentReturn aReturnObject)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {

        String myMethodName = "CreateFacilityAssignment:logOutput()";
        log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

        // aReturnObject
        try {
            log(LOG_OUTPUT_DATA, "createFacilityAssignmentReturn=<" + (new CreateFacilityAssignmentReturnBisHelper(aReturnObject)).toString() + ">");
        }
        catch (Exception e) {
            log(LOG_OUTPUT_DATA, "aReturnObject<null>");
        }

        log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
    }
}
