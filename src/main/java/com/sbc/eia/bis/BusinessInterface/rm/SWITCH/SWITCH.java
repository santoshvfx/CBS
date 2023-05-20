// $Id: SWITCH.java,v 1.1 2006/08/15 20:31:27 jo8461 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.SWITCH;

import java.util.ArrayList;
import java.util.Properties;

import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.CompanyFactory;
import com.sbc.eia.bis.BusinessInterface.Host;
import com.sbc.eia.bis.BusinessInterface.InvalidCompanyException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.Selector;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.nam.database.TnMasterTable;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;
import com.sbc.gwsvcs.access.vicuna.EventResultPair;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException;
import com.sbc.gwsvcs.service.hostlookup.HOSTLOOKUPHelper;
import com.sbc.gwsvcs.service.switchserver.SWITCHServerAccess;
import com.sbc.gwsvcs.service.switchserver.SWITCHServerHelper;
import com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException;
import com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.ExceptionResp_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Header_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.InqCktReq_t;   
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktReq_t; 
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuResp_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.TrnsptType_e;

/**
 *  Implements the NetworkUnitInquiry Business Interface.
 *  Creation date: (05/22/2002)
 *  @author: Gerry Escolar

 
#   History :
#   Date      | Author        | Version      
#   ----------------------------------------------------------------------------
#	05/28/02	G. Escolar  	4.0.4 Beta 1   
#
#   Notes: Modified search for ASM Status and ACNA to search for ASM: instead of
#          simply ASM and OWNERSHIP= instead of simply OWNERSHIP. Also added 
#          boolean variables asmFound and acnaFound to retrieve first occurrences
#          of the text strings above.   	
#
#	06/04/02	G. Escolar  	4.0.5 Beta 3
#   06/10/02	G. Escolar		4.0.6 Beta 1	 Fixed asmStatus and acnaStatus values  
#
#   Notes: Added code to return space(s) when ASM Status and ACNA are not found.  	
#
#	07/16/02	G. Escolar  	4.1 Beta 1
#
#	Notes:
#      LS CFA changes for November 2002 release:
#
#         1) New PENDING_ASSIGNMENT and PENDING_DISCONNECT status for ASM
#         2) New algorithms for determining ASM and OA statuses
#         3) New Past Due Date field (PDD)
#            ** NOTE ** Due Date is sent out as CCYYMMDD
#
#
#	03/21/2004	Steva Dunkin		8.0.1    Added method for RM 131998 IDLC inquiry
#     
*/

public class SWITCH extends Host implements NetworkUnitInquiryInterface {

	public final static String HostClass_SWITCH = SWITCH.class.getName();
	private SWITCHServerHelper switchHelper = null;

	private TnMasterTable masterTable = null;
	private HOSTLOOKUPHelper hostHelper = null;

	// The list of immediate children (classes that derive from this class)
	private final static String[] hostList = null;

	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] { NetworkUnitInquiryInterfaceName };

	CompanyFactory aCompanyFactory = null;
	Properties property = null;

	private Exception exception = null;

	/**
	 * Switch constructor comment.
	 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
	 * @param aUtility com.sbc.eia.bis.BusinessInterface.Utility
	 * @param aProperties java.util.Hashtable
	 */
	public SWITCH(
		com.sbc.eia.bis.BusinessInterface.Company aCompany,
		Utility aUtility,
		java.util.Hashtable aProperties) {
		super(aCompany, aUtility, aProperties);
	}
	public String getACNA(String aStringDesc)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		String myMethodName = "SWITCH::getACNA()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String originator = (String) getProperties().get("BIS_NAME");

		String resultACNA = new String("Not Found");

		if (aStringDesc.length() > 0) {

			int j = aStringDesc.indexOf("OWNERSHIP=");

			int k = j + 10;

			int m = k + 3;

			resultACNA = aStringDesc.substring(k, m);

		} else {

			resultACNA = "";

		}

		return resultACNA;

	}
	public String getASMStatus(String aStringDesc)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		String myMethodName = "SWITCH::getASM()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String originator = (String) getProperties().get("BIS_NAME");

		String resultASM = new String("Not Found");

		if (aStringDesc.length() > 0) {

			int j = aStringDesc.indexOf("ASM:");

			int k = j + 5;

			int m = k + 3;

			String valueCKT = new String("CKT");
			String valueNO = new String("NO ");

			String checkASM = aStringDesc.substring(k, m);

			if (checkASM.equalsIgnoreCase(valueCKT)) {
				resultASM = "CKT";
			} else {
				if (checkASM.equalsIgnoreCase(valueNO)) {
					resultASM = "NO";
				} else {
					resultASM = "Invalid";
				}
			}

		}

		return resultASM;

	}
	public static Selector[] getCacheEntries(Utility aUtility)
		throws NullDataException, InvalidCompanyException, InvalidStateException {

		aUtility.log(LogEventId.DEBUG_LEVEL_1, "SWITCH::getCacheEntries()");

		return new Selector[] {
			new Selector(
				new Company(
					Company.Company_PacificBell,
					State.getAnAnyState(),
					null,
					null),
				NetworkUnitInquiryInterface.NetworkUnitInquiryInterfaceName,
				HostClass_SWITCH),
			new Selector(
				new Company(
					Company.Company_SouthWesternBell,
					State.getAnAnyState(),
					null,
					null),
				NetworkUnitInquiryInterface.NetworkUnitInquiryInterfaceName,
				HostClass_SWITCH),
			new Selector(
				new Company(
					Company.Company_SouthernNETelephone,
					State.getAnAnyState(),
					null,
					null),
				NetworkUnitInquiryInterface.NetworkUnitInquiryInterfaceName,
				HostClass_SWITCH),
			new Selector(
				new Company(
					Company.Company_Ameritech,
					State.getAnAnyState(),
					null,
					null),
				NetworkUnitInquiryInterface.NetworkUnitInquiryInterfaceName,
				HostClass_SWITCH)};
	}
	public static String[] getHostList(Utility aUtility) {
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "SWITCH::getHostList()");

		// Return the list of immediate children (classes that derive from this class)
		return hostList;
	}
	public static String[] getInterfaceList(Utility aUtility) {
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "SWITCH::getInterfaceList()");
		// Return the list of interfaces supported by this host
		return interfaceList;
	}
    
    /**
     * getSWITCHIDLCInfo
     * 
     * Method determines if there is IDLC information for a given TN and associated wire centers. 
     * 
     * RM 131998
     */
    public boolean getSWITCHIDLCInfo ( 
        BisContext aContext,
        String wTN,
        ArrayList aWireCenters ) 
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {
                
        String myMethodName = "SWITCH::getSWITCHIDLCInfo()";
        utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
        String originator = (String) getProperties().get("BIS_NAME");

        String ruleFile =
            (String) getProperties().get("EXCEPTION_BUILDER_SWITCH_RULE_FILE");

        String cableId = "TN: " + wTN.toUpperCase();

        String resultLineDesc = new String("Not Found");

        if (switchHelper == null) {
            try {
                switchHelper = new SWITCHServerHelper(getProperties(), utility);
            } catch (ServiceException e) {
                utility.throwException(
                    ExceptionCode.ERR_RM_SWITCH_SERVER_HELPER,
                    "SwtichServerHelper Failure: "
                        + e.getExceptionCode()
                        + " "
                        + e.getMessage(),
                    originator,
                    Severity.Recoverable);
            }
        }

        // Build InqCkt request

        Header_t hdr =
            new Header_t("BIS", "BIS", "", "", TrnsptType_e.RPC_TRNSPT, "");

        InqCktReq_t inqCktReq =
            new InqCktReq_t(
                new Ex_t("TN", wTN), "D");

        SwitchInqCktReq_t switchInqCktRequest = null;

        SwitchInqCktResp_t inqCktResult = null;

        String switchName =
            SWITCHServerAccess.name + "-" + SWITCHServerAccess.version;

        try {

            utility.log(
                LogEventId.REMOTE_CALL,
                SWITCHServerAccess.name,
                switchName,
                switchName,
                "switchInqCktReq()");

            // Send InqCkt request

            try {
                switchHelper.connect(null, null);
            } catch (ServiceException e) {

                ExceptionBuilder.parseException(
                    aContext,
                    ruleFile,
                    "",
                    e.getOriginalExceptionCode(),
                    e.getMessage(),
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    utility,
                    null,
                    null,
                    null);
            }

            // Check all the wire centers for the CLLI.

            utility.log(
                LogEventId.INFO_LEVEL_2,
                "INQCKT number of wire centers = " + aWireCenters.size());

            for (int j = 0; j < aWireCenters.size(); j++) {

                // Try to get cable info from switch.

                String aWireCenter = (String) aWireCenters.get(j);

                switchInqCktRequest =
                    new SwitchInqCktReq_t(hdr, inqCktReq, aWireCenter);

                utility.log(
                    LogEventId.INFO_LEVEL_2,
                    "INQCKT Cable input request-->["
                        + "cableID<"
                        + wTN
                        + "> wireCenter<"
                        + aWireCenter
                        + ">]");

                // Send InqCkt request.

                EventResultPair response = null;
                try {

                    response =
                        switchHelper.switchInqCktReq(0, switchInqCktRequest);

                } catch (SWITCHServerException e) {

                    ExceptionBuilderResult ebr;
                    ebr =
                        ExceptionBuilder.parseException(
                            aContext,
                            ruleFile,
                            null,
                            e.getOriginalExceptionCode(),
                            e.getMessage(),
                            true,
                            ExceptionBuilderRule.NO_DEFAULT,
                            null,
                            null,
                            utility,
                            null,
                            null,
                            null);

                } catch (ServiceTimeoutException e) {

                    ExceptionBuilder.parseException(
                        aContext,
                        ruleFile,
                        "",
                        e.getOriginalExceptionCode(),
                        e.getMessage(),
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        null,
                        null,
                        null);

                } catch (ServiceException e) {

                    ExceptionBuilder.parseException(
                        aContext,
                        ruleFile,
                        "",
                        e.getOriginalExceptionCode(),
                        e.getMessage(),
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        null,
                        null,
                        null);
                }

                utility.log(
                    LogEventId.INFO_LEVEL_2,
                    "Received event: " + response.getEventNbr());

                inqCktResult = null;

                // Check the repsonse event numbers.

                int event = response.getEventNbr();

                switch (event) {

                    case SWITCHServerAccess.SWITCH_INQ_CKT_RESP_NBR :
                        // Received event 5051. Success.
                        {
                            inqCktResult =
                                (SwitchInqCktResp_t) response.getTheObject();
                            break;
                        }
                    case SWITCHServerAccess.EXCEPTION_NBR :   
                        // Received event  9999.  Exception event.
                        {
                            ExceptionResp_t errorResponse =
                                (ExceptionResp_t) response.getTheObject();

                            ExceptionBuilder.parseException(
                                aContext,
                                ruleFile,
                                "",
                                Integer.toString(errorResponse.OutExcp.ERR_CD),
                                errorResponse.OutExcp.ERR_TX,
                                true,
                                ExceptionBuilderRule.NO_DEFAULT,
                                null,
                                null,
                                utility,
                                null,
                                null,
                                null);

                            break;
                        }

                    default : // Unknown event.
                        {
                            utility.throwException(
                                ExceptionCode.ERR_RM_SWITCH_SERVER_HELPER,
                                "SWITCHServerHelper failure: SWITCHServerAccess::get:"
                                    + " Unexpected event returned from SWITCHServerHelper:SwitchInqCktResp_t: "
                                    + response.getEventNbr(),
                                originator,
                                Severity.UnRecoverable);
                            break;
                        }

                } // End of case.

                // Find something found break the loop.

                if (inqCktResult.InqCktResp.length > 0)
                    break;

                utility.log(
                    LogEventId.INFO_LEVEL_2,
                    "No data received from response.");

                ExceptionBuilderResult ebr;
                ebr =
                    ExceptionBuilder.parseException(
                        aContext,
                        ruleFile,
                        null,
                        inqCktResult.Umsg[0].MSG_NBR,
                        new String(inqCktResult.Umsg[0].MSG_TX),
                        false,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        null,
                        null,
                        null);

                // Only retry next wire center code if object not found and
                // if there are still wire centers to do.

                if (ebr.getBusinessRule() == 3) {
                    if (j < aWireCenters.size() - 1) {

                        utility.log(
                            LogEventId.INFO_LEVEL_2,
                            "Exception caught: Object not found from switch, try next wire center.");

                        continue;
                    } else
                        ebr.throwException(aContext, utility);
                } else
                    ebr.throwException(aContext, utility);

            }

			boolean isIDLCFound = false;
			
            // Start parsing the response.
            
            if (inqCktResult.InqCktResp.length > 0) {

                // 'ALT CRV', 'ALT CHNL', SPCFUNC=INT

            outerLoop : for (int i = 0; i < inqCktResult.InqCktResp.length; i++) {

                    for (int x = 0;
                        x < inqCktResult.InqCktResp[i].OTPT_LN_DESC_TX.length;
                        x++) {

                        resultLineDesc =
                            inqCktResult.InqCktResp[i].OTPT_LN_DESC_TX[x];

                        utility.log(
                            LogEventId.INFO_LEVEL_2,
                            "IDLC Switch response ->[<"
                                + resultLineDesc
                                + ">]");
                           
                        if (inqCktResult.InqCktResp[i].OTPT_LN_DESC_TX[x].indexOf("ALT CRV ID:") > -1) {
                            
                            isIDLCFound = true;
                            break outerLoop;
                        }
                    

                    
                        if (inqCktResult.InqCktResp[i].OTPT_LN_DESC_TX[x].indexOf("ALT CHNL ID:") > -1) {
                            
                            isIDLCFound = true;
                            break outerLoop;
                        }
                    

                   
                        if ( inqCktResult.InqCktResp[i].OTPT_LN_DESC_TX[x].indexOf("SPCFUNC: INT") > -1 ) {
                            
                            isIDLCFound = true;
                            break outerLoop;
                        }
                     
                    } // End of Inner for loop

                } // End of Outer for loop 

            }

            return isIDLCFound;
            
        } finally {

            // Log performance and disconnect.

            try {
                utility.log(
                    LogEventId.REMOTE_RETURN,
                    SWITCHServerAccess.name,
                    switchName,
                    switchName,
                    "switchInqCktReq()");

                switchHelper.disconnect();

            } catch (ServiceException e) {
                utility.log(
                    LogEventId.ERROR,
                    "INQCKTHelper disconnect failure: ServiceException: "
                        + e.getExceptionCode()
                        + ": "
                        + e.getMessage());
            }
        }
    }
    
	/**
	 * Insert the method's description here.
	 * Creation date: (1/31/02 3:13:44 PM)
	 * @return java.lang.String
	 * @param aWireCenter java.lang.String
	 */
	public LineDescription getLineDescription(
		BisContext aContext,
		String aCircuit,
		ArrayList aWireCenters)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		String myMethodName = "SWITCH::getLineDescription()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String originator = (String) getProperties().get("BIS_NAME");

		String ruleFile =
			(String) getProperties().get("EXCEPTION_BUILDER_SWITCH_RULE_FILE");

		// RM 107277
		String cableId = "ME: " + aCircuit.toUpperCase();

		String resultLineDesc = new String("Not Found");

		LineDescription aLineDesc = new LineDescription();

		if (switchHelper == null) {
			try {
				switchHelper = new SWITCHServerHelper(getProperties(), utility);
			} catch (ServiceException e) {
				utility.throwException(
					ExceptionCode.ERR_RM_SWITCH_SERVER_HELPER,
					"SwtichServerHelper Failure: "
						+ e.getExceptionCode()
						+ " "
						+ e.getMessage(),
					originator,
					Severity.Recoverable);
			}
		}

		// Build InqNtu request

		Header_t hdr =
			new Header_t("BIS", "BIS", "", "", TrnsptType_e.RPC_TRNSPT, "");

		InqNtuReq_t inqNtuReq =
			new InqNtuReq_t(
				new Ex_t("ME", aCircuit),
				"",
				"Y",
				new Dt_t(),
				"",
				"D");

		SwitchInqNtuReq_t switchInqNtuRequest = null;

		SwitchInqNtuResp_t inqNtuResult = null;

		String switchName =
			SWITCHServerAccess.name + "-" + SWITCHServerAccess.version;

		try {

			utility.log(
				LogEventId.REMOTE_CALL,
				SWITCHServerAccess.name,
				switchName,
				switchName,
				"switchInqNtuReq()");

			// Send InqNtu request

			try {
				switchHelper.connect(null, null);
			} catch (ServiceException e) {

				ExceptionBuilder.parseException(
					aContext,
					ruleFile,
					"",
					e.getOriginalExceptionCode(),
					e.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					null,
					null,
					null);
			}

			// Check all the wire centers for the CLLI.

			utility.log(
				LogEventId.INFO_LEVEL_2,
				"INQNTU number of wire centers = " + aWireCenters.size());

			for (int j = 0; j < aWireCenters.size(); j++) {

				// Try to get cable info from switch.

				String aWireCenter = (String) aWireCenters.get(j);

				switchInqNtuRequest =
					new SwitchInqNtuReq_t(hdr, inqNtuReq, aWireCenter);

				utility.log(
					LogEventId.INFO_LEVEL_2,
					"INQNTU Cable input request-->["
						+ "cableID<"
						+ aCircuit
						+ "> wireCenter<"
						+ aWireCenter
						+ ">]");

				// Send InqNtu request.

				EventResultPair response = null;
				try {

					response =
						switchHelper.switchInqNtuReq(0, switchInqNtuRequest);

				} catch (SWITCHServerException e) {

					ExceptionBuilderResult ebr;
					ebr =
						ExceptionBuilder.parseException(
							aContext,
							ruleFile,
							null,
							e.getOriginalExceptionCode(),
							e.getMessage(),
							true,
							ExceptionBuilderRule.NO_DEFAULT,
							null,
							null,
							utility,
							null,
							null,
							null);

				} catch (ServiceTimeoutException e) {

					ExceptionBuilder.parseException(
						aContext,
						ruleFile,
						"",
						e.getOriginalExceptionCode(),
						e.getMessage(),
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						null,
						null,
						null);

				} catch (ServiceException e) {

					ExceptionBuilder.parseException(
						aContext,
						ruleFile,
						"",
						e.getOriginalExceptionCode(),
						e.getMessage(),
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						null,
						null,
						null);
				}

				utility.log(
					LogEventId.INFO_LEVEL_2,
					"Received event: " + response.getEventNbr());

				inqNtuResult = null;

				// Check the repsonse event numbers.

				int event = response.getEventNbr();

				switch (event) {

					case SWITCHServerAccess.SWITCH_INQ_NTU_RESP_NBR :
						// Received event 5251. Success.
						{
							inqNtuResult =
								(SwitchInqNtuResp_t) response.getTheObject();
							break;
						}
					case SWITCHServerAccess.EXCEPTION_NBR :
						// Received event  9999.  Exception event.
						{
							ExceptionResp_t errorResponse =
								(ExceptionResp_t) response.getTheObject();

							ExceptionBuilder.parseException(
								aContext,
								ruleFile,
								"",
								Integer.toString(errorResponse.OutExcp.ERR_CD),
								errorResponse.OutExcp.ERR_TX,
								true,
								ExceptionBuilderRule.NO_DEFAULT,
								null,
								null,
								utility,
								null,
								null,
								null);

							break;
						}

					default : // Unknown event.
						{
							utility.throwException(
								ExceptionCode.ERR_RM_SWITCH_SERVER_HELPER,
								"SWITCHServerHelper failure: SWITCHServerAccess::get:"
									+ " Unexpected event returned from SWITCHServerHelper:SwitchInqNtuResp_t: "
									+ response.getEventNbr(),
								originator,
								Severity.UnRecoverable);
							break;
						}

				} // End of case.

				// Find something found break the loop.

				if (inqNtuResult.InqNtuResp.length > 0)
					break;

				utility.log(
					LogEventId.INFO_LEVEL_2,
					"No data received from response.");

				ExceptionBuilderResult ebr;
				ebr =
					ExceptionBuilder.parseException(
						aContext,
						ruleFile,
						null,
						inqNtuResult.Umsg[0].MSG_NBR,
						new String(inqNtuResult.Umsg[0].MSG_TX),
						false,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						null,
						null,
						null);

				// Only retry next wire center code if object not found and
				// if there are still wire centers to do.

				if (ebr.getBusinessRule() == 3) {
					if (j < aWireCenters.size() - 1) {

						utility.log(
							LogEventId.INFO_LEVEL_2,
							"Exception caught: Object not found from switch, try next wire center.");

						continue;
					} else
						ebr.throwException(aContext, utility);
				} else
					ebr.throwException(aContext, utility);

			}

			// Start parsing the response.

			boolean asmFound = false;

			boolean oaFound = false;

			boolean pddFound = false;

			boolean acnaFound = false;

			boolean ckidFound = false;
			
			if (inqNtuResult.InqNtuResp.length > 0) {

				// Find ASMStatus, ACNA, OA and PDD

				for (int i = 0; i < inqNtuResult.InqNtuResp.length; i++) {

					for (int x = 0;
						x < inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX.length;
						x++) {

						resultLineDesc =
							inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX[x];

						utility.log(
							LogEventId.INFO_LEVEL_2,
							"LSCFA Switch response ->[<"
								+ resultLineDesc
								+ ">]");
						if (resultLineDesc
							.indexOf(cableId)
							== 0) {
							if (asmFound == false) {
								if (inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX[x + 1].indexOf("ASM:") > -1) {
									aLineDesc.setASMStatus(
										this.getASMStatus(inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX[x + 1]));
									asmFound = true;
								}
							}

							if (oaFound == false) {
								if (inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX[x + 2].indexOf("OA:") > -1) {
									aLineDesc.setOA(this.getOA(inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX[x + 2]));
									oaFound = true;
								}
							}

							if (pddFound == false) {
								if (inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX[x + 2].indexOf("PDD:") > -1) {
									aLineDesc.setPDD(
										this.getPDD(inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX[x + 2]));
									pddFound = true;
								}
							}
						}
						if (acnaFound == false) {
							if (resultLineDesc.indexOf("OWNERSHIP=") > -1) {
								aLineDesc.setACNA(this.getACNA(resultLineDesc));
								acnaFound = true;
							}
						}
						if (ckidFound == false) {
							if (resultLineDesc.indexOf("CKID:") > -1) {
								aLineDesc.setEccCkt(this.getEccCkt(resultLineDesc));
								ckidFound = true;
							}
						}
					} // End of Inner for loop

				} // End of Outer for loop

			}

			// Populate with correct values	

			if (asmFound == false) {
				aLineDesc.setASMStatus("Not Found");
			}

			if (oaFound == false) {
				aLineDesc.setOA("Not Found");
			}

			if (pddFound == false) {
				aLineDesc.setPDD("Not Found");
			}

			if (acnaFound == false) {
				aLineDesc.setACNA("Not Found");
			}

			if (ckidFound == false) {
				aLineDesc.setEccCkt("Not Found");
			}

			return aLineDesc;
		} finally {

			// Log performance and disconnect.

			try {
				utility.log(
					LogEventId.REMOTE_RETURN,
					SWITCHServerAccess.name,
					switchName,
					switchName,
					"switchInqNtuReq()");

				switchHelper.disconnect();

			} catch (ServiceException e) {
				utility.log(
					LogEventId.ERROR,
					"INQNTUHelper disconnect failure: ServiceException: "
						+ e.getExceptionCode()
						+ ": "
						+ e.getMessage());
			}
		}

	}
	public String getEccCkt(String aStringDesc)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		String myMethodName = "SWITCH::getEccCkt()";

		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String originator = (String) getProperties().get("BIS_NAME");

		String resultEccCkt = new String("Not Found");

		if (aStringDesc.length() > 0) {

			int j = aStringDesc.indexOf("CKID:");

			int k = j + 6;

			int m = k + 12;

			resultEccCkt = aStringDesc.substring(k, m);

		}

		return resultEccCkt;

	}
	
	public String getOA(String aStringDesc)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		String myMethodName = "SWITCH::getOA()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String originator = (String) getProperties().get("BIS_NAME");

		String resultOA = new String("Not Found");

		if (aStringDesc.length() > 0) {

			int j = aStringDesc.indexOf("OA:");

			int k = j + 4;

			int m = k + 2;

			String valueNA = new String("NA");
			String valuePI = new String("PI");
			String valuePO = new String("PO");

			String checkOA = aStringDesc.substring(k, m);

			if (checkOA.equalsIgnoreCase(valueNA)) {
				resultOA = "NA";
			} else {
				if (checkOA.equalsIgnoreCase(valuePI)) {
					resultOA = "PI";
				} else {
					if (checkOA.equalsIgnoreCase(valuePO)) {
						resultOA = "PO";
					} else {
						resultOA = "Invalid";
					}
				}
			}

		}

		return resultOA;

	}
	public String getPDD(String aStringDesc)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		String myMethodName = "SWITCH::getPDD()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String originator = (String) getProperties().get("BIS_NAME");

		String resultPDD = new String("Not Found");

		if (aStringDesc.length() > 0) {

			int j = aStringDesc.indexOf("PDD:");

			int k = j + 5;

			int m = k + 8;

			resultPDD = aStringDesc.substring(k, m);

		}

		return resultPDD;

	}
	public static Company[] getSupportedCompanies(Utility aUtility)
		throws NullDataException, InvalidCompanyException, InvalidStateException {
		aUtility.log(
			LogEventId.DEBUG_LEVEL_1,
			"SWITCH::getSupportedCompanies()");

		// Return the company/state combination supported by this host
		return new Company[] {
			new Company(
				Company.Company_PacificBell,
				State.getAnAnyState(),
				null,
				null),
			new Company(
				Company.Company_SouthWesternBell,
				State.getAnAnyState(),
				null,
				null),
			new Company(
				Company.Company_SouthernNETelephone,
				State.getAnAnyState(),
				null,
				null),
			new Company(
				Company.Company_Ameritech,
				State.getAnAnyState(),
				null,
				null)};
	}
}
