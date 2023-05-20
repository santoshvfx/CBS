//$Id: TIRKSBase.java,v 1.17 2011/05/07 01:41:39 rs278j Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//############################################################################ 

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.Host;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.ActionOnScreenResponseHelper;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.CBLSUnit;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.CloseSessionResponseHelper;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.CxrsOutputFields;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.DataCenters;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.EQPSCUnit;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.ErrorOnScreenResponseHelper;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.Field;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.OpenSessionResponseHelper;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.TIRKSJX;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.TIRKSJXConstants;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.TIRKSJXError;
import com.sbc.eia.bis.framework.logging.LogEventId;
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
import com.sbc.gwsvcs.service.circuitprovisioning.CircuitProvisioningAccess;
import com.sbc.gwsvcs.service.circuitprovisioning.CircuitProvisioningHelper;
import com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsOutput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQInput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsOutput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQInput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriOutput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQInput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQTIRKSJXInput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscOutput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQInput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocOutput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQInput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQTIRKSJXInput;
import com.sbc.gwsvcs.service.switchserver.SWITCHServerAccess;
import com.sbc.gwsvcs.service.switchserver.SWITCHServerHelper;
import com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException;
import com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.ExceptionResp_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Header_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuResp_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.TrnsptType_e;

/**
 * Implements the CircuitInformation interface which defines circuit functionality.
 * Creation date: (10/19/00 3:02:10 PM)
 * @author: Creighton Malet
 
#   History :
#   Date       | Author        |    Version	 Notes
#   ----------------------------------------------------------------------------
#	05/01/2002	Mark Liljequist		4.0		Major modes for CFA enhancements.
#	09/16/2002	Mark Liljequist		6.0		RM64179
#											Changes for ACNA in getINQNTUTrans.
#	01/08/2003	Mark Liljequist		6.0.5	Changes for E-logging.
#	12/12/2006	Prasad Ganji		1.0.0	Changed Exception Codes from RM to RMIM
#	07/10/2007	Prasad Ganji			 	Add CVS Id keyword on line 1
#
*/

public class TIRKSBase extends Host 
	implements TIRKSJXConstants
{
	private ArrayList circuitProvisioningHelpers = null;
	private Properties stateToIMSRegion = new Properties();
	private ArrayList tirksJxHelpers = null;
	private Properties TIRKSJXProperties = new Properties();
	private String TIRKSJXName = null;
	private String TIRKSJXNameVersion = null;
	private com.sbc.gwsvcs.service.switchserver.SWITCHServerHelper switchHelper = null;
	private com
	.sbc
	.gwsvcs
	.service
	.circuitprovisioning
	.CircuitProvisioningHelper circuitHelper =
	null;
	private String switchServerName =
		SWITCHServerAccess.name + "-" + SWITCHServerAccess.version;
	private String CircuitProvisioningName =
		CircuitProvisioningAccess.name
			+ "-"
			+ CircuitProvisioningAccess.version;

	/**
	 * Class constructor accepting runtime control parameters.
	 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @param aProperties java.util.Hashtable
	 */
	public TIRKSBase(
		Company aCompany,
		com.sbc.bccs.utilities.Utility aUtility,
		java.util.Hashtable aProperties) 
	{
		super(aCompany, aUtility, aProperties);
		TIRKSJXName = (String) aProperties.get("TIRKSJX_SERVICE_NAME");
		TIRKSJXNameVersion = TIRKSJXName + "-" +(String ) aProperties.get("TIRKSJX_SERVICE_VERSION");
	}
	/**
	 * Returns Cable Information from TIRKS.
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CableInformation
	 * @param aContext com.sbc.eia.idl.bis_types_.BisContext
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public ArrayList getCBLSTrans(BisContext aContext, CBLSInput input)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "getCBLSTrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String ruleFile =
			(String) getProperties().get("EXCEPTION_BUILDER_TIRKS_RULE_FILE");

		// Build the helper

		setupHelpers(1);

		CircuitProvisioningHelper circuitProvisioningHelper =
			(CircuitProvisioningHelper) circuitProvisioningHelpers.get(0);

		// Check if the ims regions are loaded.   
		// Load the State to IMS region mappings
		// Check if there is a valid sate to ims mapping

		String imsRegions = null;

		if (imsRegions == null) 
		{
			if (stateToIMSRegion.size() < 1)
				loadStateToIMSRegion();

			if ((imsRegions =
				(String) stateToIMSRegion.get(company.getState().getCode()))
				== null) {
				utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
					"Could not find TIRKS IMS region for state code: "
						+ company.getState().getCode(),
					myMethodName,
					Severity.UnRecoverable);
			}
			utility.log(
				LogEventId.INFO_LEVEL_2,
				company.getState().getCode()
					+ " IMS Regions<"
					+ imsRegions
					+ ">");
		}

		// Create input and ouput arrays and objects

		CBLSCableInformation cableInfo = null;
		ArrayList cableList = null;

		CblsOutput cableOutput = null;
		ArrayList cableRespSet = null;

		// Build the CBLS request

		CableId aci = input.getCableId();

		CblsQInput request =
			new CblsQInput(
				"",
				aci.getTermA(),
				aci.getTermZ(),
				aci.getCable(),
				input.getFromUnit(),
				input.getLastUnit());

		// Connect to the CircuitProvisioning Service	 
		// Loop through the regions, trying each in turn

		try 
		{
			boolean moreRegions = true;

			IMSRegions regions = new IMSRegions(imsRegions);

			utility.log(
				LogEventId.REMOTE_CALL,
				CircuitProvisioningAccess.name,
				CircuitProvisioningName,
				CircuitProvisioningName,
				"cblsQInput()");

			circuitProvisioningHelper.connect(null, null);

			while (moreRegions
				&& ((request.ims_region = regions.getNextRegion()) != null)) 
			{
				utility.log(
					LogEventId.INFO_LEVEL_2,
					"CircuitProvisioning Cable input request-->["
						+ "ims_region<"
						+ request.ims_region
						+ "> term A<"
						+ request.term_A
						+ "> term Z<"
						+ request.term_Z
						+ "> cable<"
						+ request.cable
						+ "> from unit<"
						+ request.from_unit
						+ "> last unit<"
						+ request.last_unit
						+ ">]");

				// Call the service to get the screens
				// Only want 1st screen

				cableRespSet =
					circuitProvisioningHelper.cblsQInput(
						circuitProvisioningHelper.USE_DEFAULT_TIMEOUT,
						request,
						1);

				EventResultPair aResp = null;

				// Don't need a loop - only want first screen

				if (cableRespSet.size() > 0) 
				{
					aResp =
						(
							com
								.sbc
								.gwsvcs
								.access
								.vicuna
								.EventResultPair) cableRespSet
								.get(
							0);
					utility.log(
						LogEventId.INFO_LEVEL_2,
						"Received event " + aResp.getEventNbr());

					// Analyse the result

					switch (aResp.getEventNbr()) 
					{
						case CircuitProvisioningAccess.TIRKS_CBLS_QO_NBR :

							cableOutput = (CblsOutput) aResp.getTheObject();
							utility.log(
								LogEventId.INFO_LEVEL_2,
								"Cable header output-->[cable<"
									+ cableOutput.cable
									+ "> term_A<"
									+ cableOutput.term_A
									+ "> term_Z<"
									+ cableOutput.term_A
									+ ">]");

							if (cableList == null)
								cableList = new ArrayList();

							for (int i = 0; i < 1; i++) 
							{

								// Create header data.

								cableInfo = new CBLSCableInformation();
								cableInfo.setCable(cableOutput.cable.trim());
								cableInfo.setTermA(cableOutput.term_A.trim());
								cableInfo.setTermZ(cableOutput.term_Z.trim());

								// Save region for next transaction

								cableInfo.setIMSRegion(request.ims_region);
								cableInfo.setCableIMSRegion(request.ims_region);

								utility.log(
									LogEventId.INFO_LEVEL_2,
									"Cable detail ouput-->[unit<"
										+ cableOutput.CblsGrp[i].unit
										+ "> cktid_clo<"
										+ cableOutput.CblsGrp[i].cktid_clo
										+ "> cktid_format<"
										+ cableOutput.CblsGrp[i].f
										+ "> activity_cur<"
										+ cableOutput.CblsGrp[i].activity_cur
										+ "> activity_pnd<"
										+ cableOutput.CblsGrp[i].activity_pnd
										+ "> due_date<"
										+ cableOutput.CblsGrp[i].due_date.MO
										+ "/"
										+ cableOutput.CblsGrp[i].due_date.DY
										+ "/"
										+ cableOutput.CblsGrp[i].due_date.YR
										+ ">]");
								// Create detail data.

								cableInfo.setCkitIdClo(
									cableOutput.CblsGrp[i].cktid_clo.trim());
								cableInfo.setCurAct(
									cableOutput.CblsGrp[i].activity_cur.trim());
								cableInfo.setDueDate(
									cableOutput.CblsGrp[i].due_date);
								cableInfo.setFormat(
									cableOutput.CblsGrp[i].f.trim());
								cableInfo.setPendAct(
									cableOutput.CblsGrp[i].activity_pnd.trim());
								cableInfo.setPair(
									cableOutput.CblsGrp[i].unit.trim());

								cableList.add(cableInfo);
							} // for loop

							// Found what is needed. 
							// Break from while loop and go back.

							moreRegions = false;
							break;

						default :
							utility.throwException(
								ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER,
								"CircuitProvisioningHelper failure: CircuitProvisioning::get: Unexpected return from CircuitProvisioningHelper:lnpActiveSubsInput(): "
									+ aResp.getEventNbr(),
								(String) getProperties().get("BIS_NAME"),
								Severity.UnRecoverable);
					} // switch loop

				} 
				else 
				{ // if loop for size of repsonse set.
					utility.log(
						LogEventId.INFO_LEVEL_2,
						"No data received form cable circuit.");
					utility.throwException(
						ExceptionCode.ERR_RM_TIRKS_DATA_NOT_FOUND,
						"TIRKS data not found for Cable Circuit: "
							+ input.getCableId().getCable(),
						"CableChannelAssignment",
						Severity.UnRecoverable);
				}
			} // while loop

		} 
		catch (CircuitProvisioningException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		catch (ServiceTimeoutException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		catch (ServiceException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		finally 
		{

			// log performance and disconnect

			try 
			{

				utility.log(
					LogEventId.REMOTE_RETURN,
					CircuitProvisioningAccess.name,
					CircuitProvisioningName,
					CircuitProvisioningName,
					"cblsQInput()");

				circuitProvisioningHelper.disconnect();

			} 
			catch (ServiceException e) 
			{
				utility.log(
					LogEventId.ERROR,
					"CircuitProvisioningHelper disconnect failure: ServiceException: "
						+ e.getExceptionCode()
						+ ": "
						+ e.getMessage());
			}
		}

		return cableList;

	}
	
	/**
	 * Returns Carrier Channel Assignment from TIRKS.
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CarrierChannelAssignment
	 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
	 * @param aCircuitId com.sbc.eia.BusinessInterface.rm.TIRKS.CircuitId
	 * @param imsRegion java.lang.String
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types_.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public ArrayList getCXRSTrans(
		BisContext aContext,
		CircuitId aCircuitId,
		String imsRegions)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "getCXRSTrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		ArrayList cxrsList = null;
		String ruleFile =
			(String) getProperties().get("EXCEPTION_BUILDER_TIRKS_RULE_FILE");

		// Build the helper
		setupHelpers(1);

		CircuitProvisioningHelper circuitProvisioningHelper =
			(CircuitProvisioningHelper) circuitProvisioningHelpers.get(0);

		if (imsRegions == null) 
		{
			// if imsRegion is not provided, better look it up
			// Load the State to IMS region mappings
			if (stateToIMSRegion.size() < 1)
				loadStateToIMSRegion();
			//Lookup IMS region (official routing)
			if ((imsRegions =
				(String) stateToIMSRegion.get(company.getState().getCode()))
				== null) 
			{
				utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
					"Could not find TIRKS IMS region for state code: "
						+ company.getState().getCode(),
					myMethodName,
					Severity.UnRecoverable);
			}
			utility.log(
				LogEventId.INFO_LEVEL_2,
				company.getState().getCode()
					+ " IMS Regions<"
					+ imsRegions
					+ ">");
		}
		String aFacDesg = new String();
		String aFacTyp = new String();
		String aLoca = new String();
		String aLocz = new String();
		StringTokenizer st =
			new StringTokenizer(aCircuitId.asOssString().substring(2), "/");
		try 
		{
			aFacDesg = st.nextToken();
			aFacTyp = st.nextToken();
			aLoca = st.nextToken();
			aLocz = st.nextToken();
		} 
		catch (NoSuchElementException nsee) 
		{
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"We got a NoSuchElementException: " + nsee.getMessage());
		}
		// Build the CXRS request
		CxrsQInput request =
			new CxrsQInput("", aLoca, aLocz, aFacDesg, aFacTyp);
		// Tirks error 302 -> 999

		ArrayList cxrsRespSet = null;
		CxrsOutput aCxrsOutput = null;

		// Connect to the CircuitProvisioning Service
		try 
		{

			utility.log(
				LogEventId.REMOTE_CALL,
				CircuitProvisioningAccess.name,
				CircuitProvisioningName,
				CircuitProvisioningName,
				"cxrsQInput()");

			circuitProvisioningHelper.connect(null, null);

			// Loop through the regions, trying each in turn
			boolean moreRegions = true;
			boolean triedARegion = false;
			IMSRegions regions = new IMSRegions(imsRegions);
			while (moreRegions
				&& ((request.ims_region = regions.getNextRegion()) != null)) 
			{
				triedARegion = true;

				try 
				{
					utility.log(
						LogEventId.INFO_LEVEL_2,
						"CircuitProvisioning Request["
							+ "ims_region<"
							+ request.ims_region
							+ "> aFacDesg<"
							+ aFacDesg
							+ "> aFacTyp<"
							+ aFacTyp
							+ "> aLoca<"
							+ aLoca
							+ "> aLocz<"
							+ aLocz
							+ ">]");

					cxrsRespSet =
						circuitProvisioningHelper.cxrsQInput(
							circuitProvisioningHelper.USE_DEFAULT_TIMEOUT,
							request,
							circuitProvisioningHelper.RECEIVE_ALL_MESSAGES);

					// Analyse the result
					ListIterator driRespEnum = cxrsRespSet.listIterator();
					com.sbc.gwsvcs.access.vicuna.EventResultPair aResp = null;
					while (driRespEnum.hasNext()) 
					{
						aResp =
							(com
								.sbc
								.gwsvcs
								.access
								.vicuna
								.EventResultPair) driRespEnum
								.next();
						utility.log(
							LogEventId.INFO_LEVEL_2,
							"Received event " + aResp.getEventNbr());

						if (aResp.getEventNbr()
							!= CircuitProvisioningAccess.TIRKS_CXRS_QO_NBR)
							utility.throwException(
								ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER,
								"CircuitProvisioningHelper failure: CircuitProvisioning::get: Unexpected return from CircuitProvisioningHelper:lnpActiveSubsInput(): "
									+ aResp.getEventNbr(),
								(String) getProperties().get("BIS_NAME"),
								Severity.UnRecoverable);

						aCxrsOutput = (CxrsOutput) aResp.getTheObject();
						for (int i = 0; i < aCxrsOutput.CxrsGrp.length; i++) 
						{
							if (cxrsList == null)
								cxrsList = new ArrayList();

							utility.log(
								LogEventId.INFO_LEVEL_2,
								"channel_pair<"
									+ aCxrsOutput.CxrsGrp[i].channel_pair
									+ "> cur_act<"
									+ aCxrsOutput.CxrsGrp[i].cur_act
									+ "> circuit_id<"
									+ aCxrsOutput.CxrsGrp[i].circuit_id
									+ "> pend_act<"
									+ aCxrsOutput.CxrsGrp[i].pend_act
									+ "> due_date<"
									+ aCxrsOutput.CxrsGrp[i].due_date_data.MO
									+ "/"
									+ aCxrsOutput.CxrsGrp[i].due_date_data.DY
									+ "/"
									+ aCxrsOutput.CxrsGrp[i].due_date_data.YR
									+ ">");
							if (aCxrsOutput
								.CxrsGrp[i]
								.channel_pair
								.trim()
								.length()
								> 1) 
							{
								CarrierChannelAssignment aChnlAsgn =
									new CarrierChannelAssignment();
								aChnlAsgn.setDue_date_data(
									aCxrsOutput.CxrsGrp[i].due_date_data);
								aChnlAsgn.setChannel_pair(
									aCxrsOutput.CxrsGrp[i].channel_pair.trim());
								aChnlAsgn.setCur_act(
									aCxrsOutput.CxrsGrp[i].cur_act.trim());
								aChnlAsgn.setPend_act(
									aCxrsOutput.CxrsGrp[i].pend_act.trim());
								aChnlAsgn.setCircuit_id(
									aCxrsOutput.CxrsGrp[i].circuit_id.trim());
								aChnlAsgn.setClo(aCxrsOutput.clo.trim());
								cxrsList.add(aChnlAsgn); // add to the list
							}
						}
					}
					moreRegions = false;
					break;
				} 
				catch (CircuitProvisioningException e) 
				{
					ExceptionBuilder.parseException(
						aContext,
						ruleFile,
						"",
						e.getMessage(),
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
				catch (ServiceTimeoutException e) 
				{
					ExceptionBuilder.parseException(
						aContext,
						ruleFile,
						"",
						e.getMessage(),
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
				catch (ServiceException e) 
				{
					ExceptionBuilder.parseException(
						aContext,
						ruleFile,
						"",
						e.getExceptionCode(),
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
			}
		} 
		catch (ServiceException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		finally 
		{
			try 
			{

				utility.log(
					LogEventId.REMOTE_RETURN,
					CircuitProvisioningAccess.name,
					CircuitProvisioningName,
					CircuitProvisioningName,
					"cxrsQInput()");

				circuitProvisioningHelper.disconnect();
			} 
			catch (ServiceException e) 
			{
				utility.log(
					LogEventId.ERROR,
					"CircuitProvisioningHelper disconnect failure: ServiceException: "
						+ e.getExceptionCode()
						+ ": "
						+ e.getMessage());
			}
		}

		return cxrsList;
	}
	
	/**
	 * Returns Design Releated Information from TIRKS.
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.DesignRelatedInformation
	 * @param aContext com.sbc.eia.idl.bis_types_.BisContext
	 * @param aCircuitId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId
	 * @param imsRegion java.lang.String
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public DesignRelatedInformation getDRTrans(
		BisContext aContext,
		CircuitId aCircuitId,
		String imsRegions)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "getDRTrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String ruleFile =
			(String) getProperties().get("EXCEPTION_BUILDER_TIRK_RULE_FILE");

		// Build the helper
		setupHelpers(1);

		CircuitProvisioningHelper circuitProvisioningHelper =
			(CircuitProvisioningHelper) circuitProvisioningHelpers.get(0);

		if (imsRegions == null) 
		{
			// if imsRegion is not provided, better look it up
			// Load the State to IMS region mappings
			if (stateToIMSRegion.size() < 1)
				loadStateToIMSRegion();
			//Lookup IMS region (official routing)
			if ((imsRegions =
				(String) stateToIMSRegion.get(company.getState().getCode()))
				== null) 
			{
				utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
					"Could not find TIRKS IMS region for state code: "
						+ company.getState().getCode(),
					myMethodName,
					Severity.UnRecoverable);
			}
			utility.log(
				LogEventId.INFO_LEVEL_2,
				company.getState().getCode()
					+ " IMS Regions<"
					+ imsRegions
					+ ">");
		}

		// Build the DRI request
		DriQInput request =
			new DriQInput("", "", aCircuitId.asOssString(), "", "");

		DesignRelatedInformation driInfo = null;
		ArrayList driRespSet = null;

		// Connect to the CircuitProvisioning Service
		try 
		{
			utility.log(
				LogEventId.REMOTE_CALL,
				CircuitProvisioningAccess.name,
				CircuitProvisioningName,
				CircuitProvisioningName,
				"driQInput()");

			//circuitProvisioningHelper.setMaximumMessagesToReceive(1); // Only want the first screen

			circuitProvisioningHelper.connect(null, null);

			// Loop through the regions, trying each in turn
			boolean moreRegions = true;
			boolean triedARegion = false;
			IMSRegions regions = new IMSRegions(imsRegions);
			while (moreRegions
				&& ((request.ims_region = regions.getNextRegion()) != null)) 
			{
				triedARegion = true;
				try 
				{
					utility.log(
						LogEventId.INFO_LEVEL_2,
						"CircuitProvisioning Request["
							+ "ims_region<"
							+ request.ims_region
							+ "> clo<"
							+ request.clo
							+ "> ckt<"
							+ request.ckt
							+ "> cac<"
							+ request.cac
							+ "> ord<"
							+ request.ord
							+ ">]");

					// only wants 1st screen
					driRespSet =
						circuitProvisioningHelper.driQInput(
							circuitProvisioningHelper.USE_DEFAULT_TIMEOUT,
							request,
							1);

					// Analyse the result
					com.sbc.gwsvcs.access.vicuna.EventResultPair aResp = null;
					if (driRespSet.size() > 0)
						// Don't need a loop - only want first screen
						
					{
						aResp =
							(
								com
									.sbc
									.gwsvcs
									.access
									.vicuna
									.EventResultPair) driRespSet
									.get(
								0);
						;
						utility.log(
							LogEventId.INFO_LEVEL_2,
							"Received event " + aResp.getEventNbr());

						switch (aResp.getEventNbr()) 
						{
							case CircuitProvisioningAccess.TIRKS_DRI_QO_NBR :
								{
									DriOutput aDriOutput =
										(DriOutput) aResp.getTheObject();
									utility.log(
										LogEventId.INFO_LEVEL_2,
										"nc<"
											+ aDriOutput.nc
											+ "> nci1<"
											+ aDriOutput.nci1
											+ "> nci2<"
											+ aDriOutput.nci2
											+ ">");
									driInfo = new DesignRelatedInformation();
									driInfo.setNc(aDriOutput.nc.trim());
									driInfo.setNci1(aDriOutput.nci1.trim());
									driInfo.setNci2(aDriOutput.nci2.trim());
									moreRegions = false;
									break;
								}

							default :
								utility.throwException(
									ExceptionCode
										.ERR_RM_CIRCUITPROVISIONING_HELPER,
									"CircuitProvisioningHelper failure: CircuitProvisioning::get: Unexpected return from CircuitProvisioningHelper:lnpActiveSubsInput(): "
										+ aResp.getEventNbr(),
									(String) getProperties().get("BIS_NAME"),
									Severity.UnRecoverable);

						}
					}
				} 
				catch (CircuitProvisioningException e) 
				{
					ExceptionBuilder.parseException(
						aContext,
						ruleFile,
						"",
						e.getMessage(),
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
				catch (ServiceTimeoutException e) 
				{
					ExceptionBuilder.parseException(
						aContext,
						ruleFile,
						"",
						e.getMessage(),
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
				catch (ServiceException e) 
				{
					ExceptionBuilder.parseException(
						aContext,
						ruleFile,
						"",
						e.getMessage(),
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
			}
		} 
		catch (ServiceException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		finally 
		{
			try 
			{

				utility.log(
					LogEventId.REMOTE_RETURN,
					CircuitProvisioningAccess.name,
					CircuitProvisioningName,
					CircuitProvisioningName,
					"driQInput()");

				circuitProvisioningHelper.disconnect();

			} 
			catch (ServiceException e) 
			{
				utility.log(
					LogEventId.ERROR,
					"CircuitProvisioningHelper disconnect failure: ServiceException: "
						+ e.getExceptionCode()
						+ ": "
						+ e.getMessage());
			}
		}

		return driInfo;
	}

	/**
	 * Look for equipemnt scan infromation.
	 * Try all the regions and all the Factypes.
	 * If nothing is found throw exception;
	 * 
	 * @param BisContext aContext
	 * @param EQPSCInput input
	 * @return ArrayList
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public ArrayList getEQPSCTrans(
		BisContext aContext, 
		EQPSCInput input)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "getEQPSCTrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String originator = (String) getProperties().get("BIS_NAME");

		String ruleFile =
			(String) getProperties().get("EXCEPTION_BUILDER_TIRKS_RULE_FILE");

		// Check if the ims regions are loaded.   
		// Load the State to IMS region mappings
		// Check if there is a valid sate to ims mapping

		String imsRegions = null;

		if (stateToIMSRegion.size() < 1)
			loadStateToIMSRegion();

		if ((imsRegions =
			(String) stateToIMSRegion.get(company.getState().getCode()))
			== null) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
				"Could not find TIRKS IMS region for state code: "
					+ company.getState().getCode(),
				originator,
				Severity.UnRecoverable);
		}

		utility.log(
			LogEventId.INFO_LEVEL_2,
			company.getState().getCode() + " IMS Regions<" + imsRegions + ">");

		// Create input and ouput arrays and objects.

		ExceptionBuilderResult ebr = null;
		EquipmentScanInformation eqpscInfo = null;
		ArrayList eqpscList = null;

		EqpscOutput eqpscOutput = null;
		ArrayList eqpscRespSet = null;

		// Build the APOT request
		// Set the region, location, equipemnt code, level and unit.

		EqpscQInput request =
			new EqpscQInput(
				"",
				input.getLocation(),
				"",
				input.getLevel(),
				input.formatRelayRack(),
				input.formatUnit());

		// Set to do nothing.
		boolean moreRegions = false;
		int codeCount = 1;

		IMSRegions regions = new IMSRegions(imsRegions);

		// Set up first code.
		// Get the equipment code by translating the factype.

		if (((request.ims_region = regions.getNextRegion()) != null)
			&& ((input.translateEquipmentCode(input.getFacType(), codeCount))
				!= null)) 
		{
			moreRegions = true;
		} 
		else 
		{
			utility.log(
				LogEventId.INFO_LEVEL_2,
				"No regions or fac types to get.");
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_DATA_NOT_FOUND,
				"TIRKS data not found for equipement scan Circuit: "
					+ input.getPointInterfaceId().getPointInterfaceId(),
				originator,
				Severity.UnRecoverable);
		}

		// Build the helper.

		try 
		{
			if (circuitHelper == null)
				circuitHelper =
					new CircuitProvisioningHelper(getProperties(), utility);
		} 
		catch (ServiceException e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER,
				"CircuitProvisioning failure: "
					+ e.getExceptionCode()
					+ ": "
					+ e.getMessage(),
				originator,
				Severity.UnRecoverable,
				e);
		}
		try 
		{

			utility.log(
				LogEventId.REMOTE_CALL,
				CircuitProvisioningAccess.name,
				CircuitProvisioningName,
				CircuitProvisioningName,
				"eqpscQInput()");

			try 
			{

				// Connect to the CircuitProvisioning Service.

				circuitHelper.connect(null, null);

			} 
			catch (ServiceException e) 
			{
				ExceptionBuilder.parseException(
					aContext,
					ruleFile,
					null,
					e.getMessage(),
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

			// Loop through the regions, trying each in turn
			while (moreRegions == true) 
			{

				try 
				{ // First try block.
					// Get the equipment code using the fac type translation.
					if ((request.equip_code =
						input.translateEquipmentCode(codeCount))
						!= null) 
					{
						codeCount++;
						// End of the fac types for an IMS region.
					} 
					else 
					{
						// Start next IMS region.
						if ((request.ims_region = regions.getNextRegion())
							!= null)
						{
							// Set the fac type translation to the first one. 
							codeCount = 1;
							// Get the equipment code using the fac type translation.
							request.equip_code =
								input.translateEquipmentCode(codeCount);
							// No more regions or fac types.
						} 
						else 
						{
							moreRegions = false;
							continue;
						}
					}

					utility.log(
						LogEventId.INFO_LEVEL_2,
						"CircuitProvisioning Equipment Scan input request-->["
							+ "ims_region<"
							+ request.ims_region
							+ "> location<"
							+ request.location
							+ "> equip_code<"
							+ request.equip_code
							+ "> level<"
							+ request.level
							+ "> relayrack<"
							+ request.relayrack
							+ "> unit<"
							+ request.unit
							+ ">]");

					// Call the service to get the screens
					// Only want 1st screen

					eqpscRespSet =
						circuitHelper.eqpscQInput(
							circuitHelper.USE_DEFAULT_TIMEOUT,
							request,
							1);

					EventResultPair aResp = null;

					// Don't need a loop - only want first screen

					if (eqpscRespSet.size() > 0) 
					{
						aResp =
							(
								com
									.sbc
									.gwsvcs
									.access
									.vicuna
									.EventResultPair) eqpscRespSet
									.get(
								0);
						utility.log(
							LogEventId.INFO_LEVEL_2,
							"Received event " + aResp.getEventNbr());

						// Analyse the result

						switch (aResp.getEventNbr()) 
						{
							case CircuitProvisioningAccess.TIRKS_EQPSC_QO_NBR :

								eqpscOutput =
									(EqpscOutput) aResp.getTheObject();
								utility.log(
									LogEventId.INFO_LEVEL_2,
									"Eqpsc header output-->[equip_code<"
										+ eqpscOutput.equip_code
										+ "> location<"
										+ eqpscOutput.location
										+ "> level<"
										+ eqpscOutput.level
										+ "> relay rack<"
										+ eqpscOutput.relayrack
										+ "> unit<"
										+ eqpscOutput.EqpscGrp[0].unit
										+ ">]");

								if (!input
									.getUnit()
									.equals(
										eqpscOutput.EqpscGrp[0].unit.trim())) 
								{
									utility.throwException(
										ExceptionCode
											.ERR_RM_TIRKS_DATA_NOT_FOUND,
										"TIRKS data not found for equipement scan Circuit: "
											+ input
												.getPointInterfaceId()
												.getPointInterfaceId(),
										originator,
										Severity.UnRecoverable);
								}

								if (eqpscList == null)
									eqpscList = new ArrayList();
								for (int i = 0;
									i < 1;
									i++) 
								{ // Create header data.

									eqpscInfo = new EquipmentScanInformation();
									eqpscInfo.setEquipmentCode(
										eqpscOutput.equip_code.trim());
									eqpscInfo.setLocation(
										eqpscOutput.location.trim());
									eqpscInfo.setLevel(
										eqpscOutput.level.trim());
									eqpscInfo.setLevel(
										eqpscOutput.relayrack.trim());
									// Save region for next transaction
									eqpscInfo.setIMSRegion(request.ims_region);

									utility.log(
										LogEventId.INFO_LEVEL_2,
										"eqpsc detail ouput-->[unit<"
											+ eqpscOutput.EqpscGrp[i].unit
											+ "> cktid_clo<"
											+ eqpscOutput.EqpscGrp[i].circuitid
											+ "> activity_cur<"
											+ eqpscOutput.EqpscGrp[i].curact
											+ "> activity_pnd<"
											+ eqpscOutput.EqpscGrp[i].pendact
											+ "> due_date<"
											+ eqpscOutput.EqpscGrp[i].duedate
											+ ">]");
									// Create detail data.
									eqpscInfo.setCkitIdClo(
										eqpscOutput
											.EqpscGrp[i]
											.circuitid
											.trim());
									eqpscInfo.setCurAct(
										eqpscOutput.EqpscGrp[i].curact.trim());
									eqpscInfo.setDueDate(
										eqpscOutput.EqpscGrp[i].duedate.trim());
									eqpscInfo.setPendAct(
										eqpscOutput.EqpscGrp[i].pendact.trim());
									eqpscInfo.setUnit(
										eqpscOutput.EqpscGrp[i].unit.trim());
									eqpscList.add(eqpscInfo);
								} // for loop to get all the data in the groups
								// Found what is needed.
								// Break from while loop.
								moreRegions = false;
								break;
							default :
								utility.throwException(
									ExceptionCode
										.ERR_RM_CIRCUITPROVISIONING_HELPER,
									"CircuitProvisioningHelper failure: CircuitProvisioning::get: "
										+ "Unexpected event return from CircuitProvisioningHelper: "
										+ aResp.getEventNbr(),
									originator,
									Severity.UnRecoverable);
						} // switch loop
					} 
					else // if there is data loop
						utility.log(
							LogEventId.INFO_LEVEL_2,
							"No data received from eqpsc circuit.");
				} 
				catch (CircuitProvisioningException e) 
				{
					// Second try block.
					// Parse the exception for not found.
					ebr =
						ExceptionBuilder.parseException(
							aContext,
							ruleFile,
							null,
							e.getMessage(),
							e.getMessage(),
							false,
							ExceptionBuilderRule.NO_DEFAULT,
							null,
							null,
							utility,
							null,
							null,
							null);
					// Check for object not found, timeout or business violation.
					// Only retry next region under these conditions.
					int code = ebr.getBusinessRule();
					if (code == 3 || code == 1 || code == 1) 
					{
						utility.log(
							LogEventId.INFO_LEVEL_2,
							"Exception caught: Object not found from EQPSC.");
						continue;
					} 
					else
						ebr.throwException(aContext, utility);
				} 
				catch (ServiceTimeoutException e) 
				{
					ebr =
						ExceptionBuilder.parseException(
							aContext,
							ruleFile,
							null,
							e.getMessage(),
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
				catch (ServiceException e) 
				{
					ExceptionBuilder.parseException(
						aContext,
						ruleFile,
						null,
						e.getMessage(),
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
			} // while loop
		} 
		finally 
		{ // log performance and disconnect
			try 
			{

				utility.log(
					LogEventId.REMOTE_RETURN,
					CircuitProvisioningAccess.name,
					CircuitProvisioningName,
					CircuitProvisioningName,
					"eqpscQInput()");

				circuitHelper.disconnect();

			} 
			catch (ServiceException e) 
			{
				utility.log(
					LogEventId.ERROR,
					"CircuitProvisioningHelper disconnect failure: ServiceException: "
						+ e.getExceptionCode()
						+ ": "
						+ e.getMessage());
			}
		}

		if (eqpscList == null) 
		{
			ebr.throwException(aContext, utility);

		}

		return eqpscList;
	}

	/**
	 * Returns Cable Information from SWITCH.
	 * 
	 * @param BisContext aContext
	 * @param CBLSInput input
	 * @return ArrayList
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public ArrayList getINQNTUTrans(
		BisContext aContext, 
		CBLSInput input)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "getINQNTUTrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		String originator = (String) getProperties().get("BIS_NAME");

		String ruleFile =
			(String) getProperties().get("EXCEPTION_BUILDER_SWITCH_RULE_FILE");

		String cilliFromSWITCH = null;
		String cilliFromINPUT =
			(input.getCableId().getTermA().trim().toUpperCase()).substring(
				0,
				8);
		utility.log(
			LogEventId.INFO_LEVEL_2,
			"CILLI from INPUT: " + cilliFromINPUT);
		INQNTUCableInformation cableInfo = new INQNTUCableInformation();
		// RM 107277
		String cableId = "ME: " + input.buildSwitchId().toUpperCase();

		// Get all the wire centers.

		ArrayList wireCenters = input.getWireCenters();

		try 
		{
			if (switchHelper == null)
				switchHelper = new SWITCHServerHelper(getProperties(), utility);
		} 
		catch (ServiceException e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_SWITCH_SERVER_HELPER,
				"SwtichServerHelper Failure: "
					+ e.getExceptionCode()
					+ " "
					+ e.getMessage(),
				originator,
				Severity.Recoverable);
		}

		// Build InqNtu request.
		// Build header.

		Header_t hdr =
			new Header_t("RMBIS", "RMBIS", "", "", TrnsptType_e.RPC_TRNSPT, "");

		// Build the switch input.

		String aCircuit = input.buildSwitchId();
		InqNtuReq_t inqNtuReq =
			new InqNtuReq_t(
				new Ex_t("ME", aCircuit),
				"",
				"Y",
				new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t(),
				"",
				"D");

		SwitchInqNtuResp_t inqNtuResult = null;

		try 
		{

			// Make the connection.
			utility.log(
				LogEventId.REMOTE_CALL,
				SWITCHServerAccess.name,
				switchServerName,
				switchServerName,
				"switchInqNtuReq()");

			try 
			{
				switchHelper.connect(null, null);
			} 
			catch (ServiceException e) 
			{

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
				"INQNTU number of wire centers = " + wireCenters.size());

			for (int j = 0; j < wireCenters.size(); j++) 
			{

				// Try to get cable info from switch.

				String aWireCenter = (String) wireCenters.get(j);
				SwitchInqNtuReq_t switchInqNtuRequest =
					new SwitchInqNtuReq_t(hdr, inqNtuReq, aWireCenter);

				utility.log(
					LogEventId.INFO_LEVEL_2,
					"INQNTU Cable input request-->["
						+ "cableID<"
						+ aCircuit
						+ "> wireCenter<"
						+ aWireCenter
						+ "> from unit<"
						+ input.getFromUnit()
						+ "> last unit<"
						+ input.getLastUnit()
						+ ">]");

				// Send InqNtu request.

				EventResultPair response = null;
				try 
				{

					response =
						switchHelper.switchInqNtuReq(0, switchInqNtuRequest);

				} 
				catch (SWITCHServerException e) 
				{

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

				} 
				catch (ServiceTimeoutException e) 
				{

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
				catch (ServiceException e) 
				{

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

				switch (event) 
				{

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

				//DR73468
				boolean dataNotFound = false;
				
				// Find something found break the loop.				
				if (inqNtuResult.InqNtuResp.length > 0) 
				{
					cilliFromSWITCH =
						cableInfo.parseCLLI(
							inqNtuResult.InqNtuResp[0].OTPT_LN_DESC_TX,
							cableId);

					utility.log(
						LogEventId.INFO_LEVEL_2,
						"CLLI FROM SWITCH : " + cilliFromSWITCH);

					if (cilliFromINPUT.equals(cilliFromSWITCH)) 
					{
						utility.log(
							LogEventId.DEBUG_LEVEL_2,
							"SWITCH CLLI matches with CLLI of input");
						break;
					} 
					else
					{
						dataNotFound = true;
						utility.log(
							LogEventId.DEBUG_LEVEL_2,
							"SWITCH CLLI does not match with CLLI of input");
					}
				} 
				else
					utility.log(
						LogEventId.DEBUG_LEVEL_2,
						"No data received from response.");

				// Find something found break the loop.Only retry next wire center code if object 
				//not found and if there are still wire centers to do.

				String messageCode = null;
				String messageText = null;

				if (inqNtuResult.Umsg.length != 0) 
				{
					messageCode = new String(inqNtuResult.Umsg[0].MSG_NBR);
					messageText = new String(inqNtuResult.Umsg[0].MSG_TX);
				}

				if (j < wireCenters.size() - 1) 
				{
					utility.log(
						LogEventId.INFO_LEVEL_2,
						" Try next wire center.");
					continue;
				} 
				else 
				{
					utility.log(
						LogEventId.INFO_LEVEL_2,
						"wire center list exhausted , no data found.");
						if (dataNotFound == true) messageCode = "W103-001";
					ExceptionBuilderResult ebr =
						ExceptionBuilder.parseException(
							aContext,
							ruleFile,
							null,
							messageCode,
							messageText,
							true,
							ExceptionBuilderRule.NO_DEFAULT,
							null,
							null,
							utility,
							null,
							null,
							null);
				}
			} // for loop

			// Start parsing the output.

			boolean asmFound = false;
			boolean pddFound = false;
			boolean oaFound = false;
			boolean ckidFound = false;
			boolean adsrFound = false;
			boolean acnaFound = false;
			// Set up the output.

			//INQNTUCableInformation cableInfo = null;
			ArrayList cableList = null;
			cableList = new ArrayList();

			//cableInfo = new INQNTUCableInformation();

			// Check each section for the wanted codes.
			// If the code is found mark it and don't try to find it again.
			for (int i = 0; i < inqNtuResult.InqNtuResp.length; i++) 
			{
				for (int line = 0;
					line < inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX.length;
					line++) 
				{
					utility.log(
						LogEventId.INFO_LEVEL_2,
						"line: "
							+ inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX[line]);
					if (inqNtuResult
						.InqNtuResp[i]
						.OTPT_LN_DESC_TX[line]
						.indexOf(cableId)
						== 0) 
					{
						if (!asmFound)
							if (cableInfo
								.parseASM(
									inqNtuResult
										.InqNtuResp[i]
										.OTPT_LN_DESC_TX[line
										+ 1])
								!= null)
								asmFound = true;
						if (!oaFound)
							if (cableInfo
								.parseOA(
									inqNtuResult
										.InqNtuResp[i]
										.OTPT_LN_DESC_TX[line
										+ 2])
								!= null)
								oaFound = true;
						if (!pddFound)
							if (cableInfo
								.parseDate(
									inqNtuResult
										.InqNtuResp[i]
										.OTPT_LN_DESC_TX[line
										+ 2])
								!= null)
								pddFound = true;
					}
					if (!acnaFound)
						if (cableInfo
							.parseACNA(
								inqNtuResult
									.InqNtuResp[i]
									.OTPT_LN_DESC_TX[line])
							!= null)
							acnaFound = true;

					if (!ckidFound)
						if (cableInfo
							.parseCKID(
								inqNtuResult
									.InqNtuResp[i]
									.OTPT_LN_DESC_TX[line])
							!= null)
							ckidFound = true;
					if (!adsrFound)
						if (cableInfo
							.parseADSR(
								inqNtuResult
									.InqNtuResp[i]
									.OTPT_LN_DESC_TX[line])
							!= null)
							adsrFound = true;
				} 
			} // End of Inner for loop

			// Log the data.

			utility.log(
				LogEventId.INFO_LEVEL_2,
				"Cable detail output-->[cktid_clo<"
					+ cableInfo.getCkitIdClo()
					+ "> ASM<"
					+ cableInfo.getASM()
					+ "> OA<"
					+ cableInfo.getOA()
					+ "> date<"
					+ cableInfo.getDate()
					+ "> ADSR<"
					+ cableInfo.getADSR()
					+ "> ACNA<"
					+ cableInfo.getCableACNA()
					+ ">]");

			// Found something, break the loop.

			cableInfo.setPair(input.buildSwitchChannelPair().trim());

			cableList.add(cableInfo);
			return cableList;

		} 
		finally 
		{

			// Log performance and disconnect.

			try 
			{
				utility.log(
					LogEventId.REMOTE_RETURN,
					SWITCHServerAccess.name,
					switchServerName,
					switchServerName,
					"switchInqNtuReq()");

				switchHelper.disconnect();

			} 
			catch (ServiceException e) 
			{
				utility.log(
					LogEventId.ERROR,
					"INQNTUHelper disconnect failure: ServiceException: "
						+ e.getExceptionCode()
						+ ": "
						+ e.getMessage());
			}
		}

	}
	
	/**
	 * Returns rdloc Information from TIRKS.
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.rdlocInformation
	 * @param aContext com.sbc.eia.idl.bis_types_.BisContext
	 * @param aCircuitId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId
	 * @param imsRegion java.lang.String
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public ArrayList getRDLOCTrans(
		BisContext aContext, 
		RDLOCInput input)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "getRDLOCTrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String ruleFile =
			(String) getProperties().get("EXCEPTION_BUILDER_TIRKS_RULE_FILE");

		// Build the helper
		setupHelpers(1);

		CircuitProvisioningHelper circuitProvisioningHelper =
			(CircuitProvisioningHelper) circuitProvisioningHelpers.get(0);

		String imsRegions = input.getIMSRegion();
		if (imsRegions == null) 
		{
			// Load the State to IMS region mappings
			if (stateToIMSRegion.size() < 1)
				loadStateToIMSRegion();

			if ((imsRegions =
				(String) stateToIMSRegion.get(company.getState().getCode()))
				== null) 
			{
				utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
					"Could not find TIRKS IMS region for state code: "
						+ company.getState().getCode(),
					myMethodName,
					Severity.UnRecoverable);
			}
			utility.log(
				LogEventId.INFO_LEVEL_2,
				company.getState().getCode()
					+ " IMS Regions<"
					+ imsRegions
					+ ">");
		}

		ReferenceLocationInformation rdlocInfo = null;
		ArrayList rdlocList = null;

		RdlocOutput rdlocOutput = null;
		ArrayList rdlocRespSet = null;

		// Build the RDLOC request

		RdlocQInput request = new RdlocQInput("", input.getLocation());

		// Connect to the CircuitProvisioning Service

		try 
		{

			// Only want the first screen
			utility.log(
				LogEventId.REMOTE_CALL,
				CircuitProvisioningAccess.name,
				CircuitProvisioningName,
				CircuitProvisioningName,
				"rdlocQInput()");

			circuitProvisioningHelper.connect(null, null);

			// Loop through the regions, trying each in turn

			boolean moreRegions = true;
			boolean triedARegion = false;
			IMSRegions regions = new IMSRegions(imsRegions);

			while (moreRegions
				&& ((request.ims_region = regions.getNextRegion()) != null)) 
			{
				triedARegion = true;
				utility.log(
					LogEventId.INFO_LEVEL_2,
					"CircuitProvisioning Location input request-->["
						+ "ims_region<"
						+ request.ims_region
						+ "> location<"
						+ request.location
						+ ">]");

				// only wants 1st screen
				rdlocRespSet =
					circuitProvisioningHelper.rdlocQInput(
						circuitProvisioningHelper.USE_DEFAULT_TIMEOUT,
						request,
						1);

				// Analyse the result
				// Don't need a loop - only want first screen
				EventResultPair aResp = null;
				if (rdlocRespSet.size() > 0) 
				{
					aResp = (EventResultPair) rdlocRespSet.get(0);

					utility.log(
						LogEventId.INFO_LEVEL_2,
						"Received event " + aResp.getEventNbr());

					switch (aResp.getEventNbr()) 
					{
						case CircuitProvisioningAccess.TIRKS_RDLOC_QO_NBR :
							{
								rdlocOutput =
									(RdlocOutput) aResp.getTheObject();
								utility.log(
									LogEventId.INFO_LEVEL_2,
									"Location ouput-->[description<"
										+ rdlocOutput.description
										+ "> company<"
										+ rdlocOutput.company
										+ "> loc<"
										+ rdlocOutput.locname
										+ "> owner<"
										+ rdlocOutput.owner
										+ ">]");

								if (rdlocList == null)
									rdlocList = new ArrayList();
								rdlocInfo = new ReferenceLocationInformation();
								rdlocInfo.setDescription(
									rdlocOutput.description.trim());
								rdlocInfo.setOwner(rdlocOutput.owner.trim());
								rdlocInfo.setLocName(
									rdlocOutput.locname.trim());

								//   Test data.
								//   rdlocInfo.setDescription("CAGE");
								//   rdlocInfo.setDescription("CCNA:ATA");
								//   rdlocInfo.setOwner("ATB");
								//   rdlocInfo.setLocName("ATC");

								rdlocList.add(rdlocInfo);
								moreRegions = false;
								break;
							}

						default :
							utility.throwException(
								ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER,
								"CircuitProvisioningHelper failure: CircuitProvisioning::get: Unexpected return from CircuitProvisioningHelper:lnpActiveSubsInput(): "
									+ aResp.getEventNbr(),
								(String) getProperties().get("BIS_NAME"),
								Severity.UnRecoverable);

					}
				}
			}
		} 
		catch (CircuitProvisioningException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		catch (ServiceTimeoutException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		catch (ServiceException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		finally 
		{
			try 
			{

				utility.log(
					LogEventId.REMOTE_RETURN,
					CircuitProvisioningAccess.name,
					CircuitProvisioningName,
					CircuitProvisioningName,
					"rdlocQInput()");

				circuitProvisioningHelper.disconnect();
			} 
			catch (ServiceException e) 
			{
				utility.log(
					LogEventId.ERROR,
					"CircuitProvisioningHelper disconnect failure: ServiceException: "
						+ e.getExceptionCode()
						+ ": "
						+ e.getMessage());
			}
		}

		return rdlocList;
	}
	
	/**
	 * Returns work authorization information from TIRKS.
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.WorkAuthorizationInformation[]
	 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
	 * @param inArray com.sbc.eia.bis.BusinessInterface.rm.TIRKS.WAInput[]
	 * @param aCircuitId com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.CircuitId
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public WorkAuthorizationInformation[] getWATrans(
		BisContext aContext,
		WAInput[] inArray)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "TIRKSBase::getWATrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		// No point processing an empty array
		if (inArray == null || inArray.length < 1) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_INVALID_WA_INPUT_ARRAY,
				myMethodName + ": passed null or empty WAInput[]",
				myMethodName,
				Severity.UnRecoverable);
		}

		// Load the State to IMS region mappings
		if (stateToIMSRegion.size() < 1)
			loadStateToIMSRegion();

		// Build the helpers
		setupHelpers(inArray.length);

		// Figure out the receive wait time (only needed if there is more than one input object processed at a time)
		int receiveWaitTime = 0;
		if (inArray.length > 1 && circuitProvisioningHelpers.size() > 1) 
		{
			try 
			{
				receiveWaitTime =
					Integer
						.valueOf(
							(String) getProperties().get(
								"TIRKS_RECEIVE_WAIT_TIME"))
						.intValue();
			} 
			catch (Exception e) 
			{
				utility.log(
					LogEventId.DEBUG_LEVEL_2,
					"TIRKS_RECEIVE_WAIT_TIME not found (or invalid) in properties. ("
						+ e.getMessage()
						+ "). Using 1");
				receiveWaitTime = 1;
			}
			if (receiveWaitTime < 1) 
			{
				utility.log(
					LogEventId.ERROR,
					"TIRKS_RECEIVE_WAIT_TIME invalid in properties: <"
						+ receiveWaitTime
						+ ">. Using 1");
				receiveWaitTime = 1;
			}
		}

		// Figure out the gross time out
		int circuitProvisioningTimeOut = 0;
		try 
		{
			circuitProvisioningTimeOut =
				Integer
					.valueOf(
						(String) getProperties().get(
							"CIRCUITPROVISIONING_TIMEOUT"))
					.intValue();
		} 
		catch (Exception e) 
		{
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"CIRCUITPROVISIONING_TIMEOUT not found (or invalid) in properties. ("
					+ e.getMessage()
					+ "). Using 30");
			circuitProvisioningTimeOut = 30;
		}
		if (circuitProvisioningTimeOut < 1) 
		{
			utility.log(
				LogEventId.ERROR,
				"CIRCUITPROVISIONING_TIMEOUT invalid in properties: <"
					+ circuitProvisioningTimeOut
					+ ">. Using 30");
			circuitProvisioningTimeOut = 30;
		}
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"circuitProvisioningTimeOut<" + circuitProvisioningTimeOut + ">");

		// Figure out how many helpers will be in use - all or just enough for one for each transaction
		int numberOfHelpersInUse =
			(inArray.length < circuitProvisioningHelpers.size()
				? inArray.length
				: circuitProvisioningHelpers.size());

		// Get the relevant IMS regions
		String imsRegions = null;
		if ((imsRegions =
			(String) stateToIMSRegion.get(company.getState().getCode()))
			== null) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
				"Could not find TIRKS IMS region for state code: "
					+ company.getState().getCode(),
				myMethodName,
				Severity.UnRecoverable);
		}
		utility.log(
			LogEventId.INFO_LEVEL_2,
			company.getState().getCode()
				+ " configured IMS Regions<"
				+ imsRegions
				+ ">");

		// Create the output array

		WorkAuthorizationInformation[] waInfo =
			new WorkAuthorizationInformation[inArray.length];
		for (int i = 0; i < waInfo.length; i++)
			waInfo[i] = null;

		ArrayList circuitProvisioningHelpersSubset = new ArrayList();
		try 
		{
			// Connect to the CircuitProvisioning Service. Use those helpers that manage to connect

			int invalidHelpers = 0;
			ServiceException saveServiceException = null;
			for (int i = 0; i < numberOfHelpersInUse; i++) 
			{
				try 
				{

					(
						(
							CircuitProvisioningHelper) circuitProvisioningHelpers
								.get(
							i)).connect(
						null,
						null);
					// Use this connected helper
					circuitProvisioningHelpersSubset.add(
						(
							CircuitProvisioningHelper) circuitProvisioningHelpers
								.get(
							i));

				} 
				catch (ServiceException e) 
				{
					saveServiceException = e;
					invalidHelpers++;
					utility.log(
						LogEventId.ERROR,
						"CircuitProvisioningHelper connect failure: ServiceException: "
							+ e.getExceptionCode()
							+ ": "
							+ e.getMessage());
				}
			}
			if ((numberOfHelpersInUse -= invalidHelpers) < 1) 
			{
				if (saveServiceException == null)
					utility.throwException(
						ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
						"CircuitProvisioningHelper connect failure on all helpers",
						"CircuitProvisioningHelper",
						Severity.UnRecoverable);
				else
					utility.throwException(
						ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
						"CircuitProvisioningHelper connect failure on all helpers",
						"CircuitProvisioningHelper",
						Severity.UnRecoverable,
						saveServiceException);
			}
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"numberOfHelpersInUse<" + numberOfHelpersInUse + ">");

			// Figure out the final time outs and maxium number of time outs
			int maximumTimeOuts = 0;
			if (numberOfHelpersInUse > 1) 
			{
				// Each transaction has a number of short time outs
				maximumTimeOuts = circuitProvisioningTimeOut / receiveWaitTime;
			} 
			else 
			{
				// The only transaction has one long time out
				receiveWaitTime = circuitProvisioningTimeOut;
				maximumTimeOuts = 1;
			}

			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"receiveWaitTime<" + receiveWaitTime + ">");
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"maximumTimeOuts<" + maximumTimeOuts + ">");

			// Loop through all the input requests, passing the work off to the workers
			WAWorker workers[] = new WAWorker[numberOfHelpersInUse];
			for (int i = 0; i < numberOfHelpersInUse; i++) 
			{
				workers[i] =
					new WAWorker(
						utility,
						imsRegions,
						(
							CircuitProvisioningHelper) circuitProvisioningHelpersSubset
								.get(
							i),
						(String) getProperties().get("BIS_NAME"),
						company,
						waInfo,
						receiveWaitTime,
						maximumTimeOuts,
						i);
				workers[i].setInput(inArray[i]);
				workers[i].outputIndex(i);
			}
			int numberComplete = 0;
			int nextToProcess = numberOfHelpersInUse;
			int usableWorkers = numberOfHelpersInUse;
			boolean setupWorker = true;

			// Multiplex through each worker until it returns true (complete) or throws an exception
			while (numberComplete < inArray.length) 
			{
				// The number of usable workers has dropped to zero because of reconnect failures
				if (usableWorkers < 1)
					utility.throwException(
						ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
						"CircuitProvisioningHelper connect failure on all helpers. All reconnects failed",
						"CircuitProvisioningHelper",
						Severity.UnRecoverable);

				for (int i = 0; i < workers.length; i++) 
				{
					utility.log(
						LogEventId.DEBUG_LEVEL_2,
						"numberComplete<"
							+ numberComplete
							+ "> nextToProcess<"
							+ nextToProcess
							+ ">");
					utility.log(
						LogEventId.DEBUG_LEVEL_2,
						"worker["
							+ i
							+ "] state<"
							+ workers[i].getState()
							+ "> outputIndex<"
							+ workers[i].getOutputIndex()
							+ ">");
					setupWorker = false;
					try 
					{
						if (workers[i].getState() != WAWorker.STATE_IDLE)
							// Only use non-idle workers
							setupWorker =
								workers[i].process(aContext, getProperties());
					} 
					catch (AccessDenied e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (BusinessViolation e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (InvalidData e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (NotImplemented e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (ObjectNotFound e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (DataNotFound e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (SystemFailure e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					}

					// Does the connection need to be dropped/reconnected
					if (workers[i].getState() != WAWorker.STATE_IDLE
						&& workers[i].getReconnect()
						&& ((!setupWorker)
						|| // not complete
					 (
							setupWorker && nextToProcess < inArray.length)))
						// complete - no more transactions
						{
						try 
						{
							workers[i].getHelper().disconnect();
							workers[i].getHelper().connect(null, null);
							workers[i].setReconnect(false);
						} 
						catch (ServiceException e) 
						{
							Exception ex = null;
							try {
								utility.throwException(
									ExceptionCode
										.ERR_RM_CIRCUITPROVISIONING_HELPER,
									"CircuitProvisioningHelper disconnect/reconnect failure on helper",
									"CircuitProvisioningHelper",
									Severity.UnRecoverable,
									e);
							} 
							catch (AccessDenied x) 
							{
								ex = x;
							} 
							catch (BusinessViolation x) 
							{
								ex = x;
							} 
							catch (InvalidData x) 
							{
								ex = x;
							} 
							catch (NotImplemented x) 
							{
								ex = x;
							} 
							catch (ObjectNotFound x) 
							{
								ex = x;
							} 
							catch (DataNotFound x) 
							{
								ex = x;
							} 
							catch (SystemFailure x) 
							{
								ex = x;
							}

							waInfo[workers[i].getOutputIndex()] =
								new WorkAuthorizationInformation();
							waInfo[workers[i].getOutputIndex()].setException(
								ex);
							workers[i].setIdleState();
							setupWorker = false;
							numberComplete++;
							usableWorkers--;
						}
					}

					if (setupWorker) // Worker needs a new transaction
						{
						// Complete - find another to process
						numberComplete++;
						if (nextToProcess < inArray.length) 
						{
							// There are more transactions
							workers[i].setInput(inArray[nextToProcess]);
							workers[i].outputIndex(nextToProcess++);
						} 
						else 
						{
							// There are no more transactions
							workers[i].setIdleState();
							setupWorker = false;
							try 
							{
								workers[i].getHelper().disconnect();
								// The finally clause will try again - this is to
								//	free connections as quickly as possible
							} 
							catch (ServiceException e) 
							{
								utility.log(
									LogEventId.ERROR,
									"CircuitProvisioningHelper disconnect failure: ServiceException: "
										+ e.getExceptionCode()
										+ ": "
										+ e.getMessage());
							}
						}
					}
				}
			}
		} 
		finally // Disconnect
		{
			for (int i = 0; i < numberOfHelpersInUse; i++) 
			{
				try 
				{
					(
						(CircuitProvisioningHelper) circuitProvisioningHelpersSubset
						.get(i))
						.disconnect();
				} 
				catch (ServiceException e) 
				{
					utility.log(
						LogEventId.ERROR,
						"CircuitProvisioningHelper disconnect failure: ServiceException: "
							+ e.getExceptionCode()
							+ ": "
							+ e.getMessage());
				}
			}
		}
		return waInfo;
	}
	
	/**
	 * Returns Cable Information from TIRKS.
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CableInformation
	 * @param aContext com.sbc.eia.idl.bis_types_.BisContext
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public ArrayList getTIRKSJXCBLSTrans(
		BisContext aContext, 
		CBLSInput input)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "getCBLSTrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String ruleFile = (String) getProperties().get("EXCEPTION_BUILDER_TIRKS_RULE_FILE");
		String openSessionResponse = null;
		String sessionID = null;
		String cblsQueryResponse = null;
		ActionOnScreenResponseHelper cblsResponseHelper;
		ErrorOnScreenResponseHelper errorResponseHelper;
		boolean hasPageDown;

		// Build the helper
		setupTIRKSJXHelpers(1);

		TIRKSJX TIRKSJXHelper =
			(TIRKSJX) tirksJxHelpers.get(0);

		// Check if the data centers are loaded.   
		// Load the State to data center mappings
		// Check if there is a valid sate to data center
		
		// Load the state to data center mappings
		if (TIRKSJXProperties.size() < 1)
		{
			loadTIRKSJxProperties();
		}

		String stateCode = company.getState().getCode();	
		String dataCenters = null;
		
		if ((dataCenters = (String) TIRKSJXProperties.get(TIRKSJXName + "." + stateCode))== null) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
				"Could not find TIRKS Data Center for state code: "
					+ company.getState().getCode(),
				myMethodName,
				Severity.UnRecoverable);
		}
		utility.log(
			LogEventId.INFO_LEVEL_2,
			company.getState().getCode()
				+ " configured Data Center<"
				+ dataCenters
				+ ">");

		// Create input and ouput arrays and objects

		CBLSCableInformation cableInfo = null;
		ArrayList cableList = null;

		// Build the CBLS request

		CableId aci = input.getCableId();

		CblsQTIRKSJXInput request =
			new CblsQTIRKSJXInput(
				"",	
				"",
				aci.getTermA(),
				aci.getTermZ(),
				aci.getCable(),
				input.getFromUnit(),
				input.getLastUnit());

		// Connect to the CircuitProvisioning Service	 
		// Loop through the regions, trying each in turn

		try 
		{
			boolean moreDataCenters = true;

			DataCenters centers = new DataCenters(dataCenters);

			utility.log(
				LogEventId.REMOTE_CALL,
				TIRKSJXName,
				TIRKSJXNameVersion,
				TIRKSJXNameVersion,
				"cblsQInput()");

			while (moreDataCenters
				&& ((request.data_center = centers.getNextDataCenter()) != null)) 
			{

				utility.log(
					LogEventId.INFO_LEVEL_2,
					"CircuitProvisioning Cable input request-->["
						+ "data_center<"
						+ request.data_center
						+ "> term A<"
						+ request.term_A
						+ "> term Z<"
						+ request.term_Z
						+ "> cable<"
						+ request.cable
						+ "> from unit<"
						+ request.from_unit
						+ "> last unit<"
						+ request.last_unit
						+ ">]");

				// Call the service to get the screens
				// Only want 1st screen
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.impl.JXAPIImpl openSessionRequest = TIRKSJXHelper.buildTIRKSJxOpenSessionRequest(request.data_center);
				openSessionResponse = TIRKSJXHelper.sendOpenSessionRequest(openSessionRequest);
				
				OpenSessionResponseHelper openSessionResponseHelper =  TIRKSJXHelper.parseOpenSessionResponse(openSessionResponse);
				sessionID = openSessionResponseHelper.getSessionID();
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl cblsQueryRequest 
				= TIRKSJXHelper.buildCBLSQueryRequest(	sessionID, 
														request.term_A, 
														request.term_Z, 
														request.cable, 
														request.from_unit,
														request.last_unit,
														ACTION_F1);
				cblsQueryResponse = TIRKSJXHelper.sendActionOnScreenRequest(cblsQueryRequest);
				
				if(TIRKSJXHelper.checkResponseHasError(cblsQueryResponse))
				{
					errorResponseHelper = TIRKSJXHelper.parseErrorOnScreenResponse(cblsQueryResponse);
					TIRKSJXError[] TIRKSJXError = errorResponseHelper.getTirksErrors();
					int TIRKSJXErrorSize = TIRKSJXError.length;
					String errorType = null;
					String errorMessage = null;
					
					for(int i=0; i<TIRKSJXErrorSize; i++)
					{
						errorType = TIRKSJXError[i].getErrorType();
						errorMessage = TIRKSJXError[i].getErrorMessage();
					}
					
					utility.log(
							LogEventId.INFO_LEVEL_2,
							"No data received form cable circuit.");
					
					utility.throwException(
						ExceptionCode.ERR_RM_TIRKS_DATA_NOT_FOUND,
						"TIRKS data not found for Cable Circuit: "
							+ input.getCableId().getCable(),
						"CableChannelAssignment",
						Severity.UnRecoverable);
				}
				else
				{
					cblsResponseHelper = TIRKSJXHelper.parseActionOnScreenResponse(cblsQueryResponse);
					Field [] cblsRespFields = cblsResponseHelper.getAFields();
				
					if (cblsRespFields != null && cblsRespFields.length > 0)  // Don't need a loop - only want first screen
					{						
						String cable = null;
						String term_A = null;
						String term_Z = null;
						ArrayList cbls_unit_list = new ArrayList(); 
						String systemMessage = null;
						String systemMessageCode = null;
						String systemMessageDescription = null;
						
						for (int i=0; i < cblsRespFields.length; i++)
						{
							if (cblsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CABLE))
							{
								cable = cblsRespFields[i].getFieldValue().trim();
							}
							if (cblsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_TERM_A))
							{
								term_A = cblsRespFields[i].getFieldValue().trim();
							}
							if (cblsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_TERM_Z))
							{
								term_Z = cblsRespFields[i].getFieldValue().trim();
							}
							if (cblsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_UNIT))
							{
								CBLSUnit cbls_unit = new CBLSUnit();
								cbls_unit.setUnit(cblsRespFields[i].getFieldValue().trim());
								cbls_unit.setInstance(cblsRespFields[i].getFieldInstance());
								
								cbls_unit_list.add(cbls_unit);
							}
							if (cblsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CKTID_CLO))
							{
								CBLSUnit unit = (CBLSUnit)cbls_unit_list.get(cblsRespFields[i].getFieldInstance());
								unit.setCktid_clo(cblsRespFields[i].getFieldValue().trim());
							}
							if (cblsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_F))
							{
								CBLSUnit unit = (CBLSUnit)cbls_unit_list.get(cblsRespFields[i].getFieldInstance());
								unit.setCktid_format(cblsRespFields[i].getFieldValue().trim());
							}
							if (cblsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_ACTIVITY_CUR))
							{
								CBLSUnit unit = (CBLSUnit)cbls_unit_list.get(cblsRespFields[i].getFieldInstance());
								unit.setActivity_cur(cblsRespFields[i].getFieldValue().trim());
							}
							if (cblsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_ACTIVITY_PND))
							{
								CBLSUnit unit = (CBLSUnit)cbls_unit_list.get(cblsRespFields[i].getFieldInstance());
								unit.setActivity_pnd(cblsRespFields[i].getFieldValue().trim());
							}
							if (cblsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_DUE_DATE))
							{
								String due_date = cblsRespFields[i].getFieldValue().trim();
								
								if(due_date.length() > 0)
								{
									String due_date_mo = due_date.substring(0, 2);
									String due_date_day = due_date.substring(2, 4);
									String due_date_yr = due_date.substring(4, 6);
									
									CBLSUnit unit = (CBLSUnit)cbls_unit_list.get(cblsRespFields[i].getFieldInstance());
									unit.setDue_date(due_date);
									unit.setDue_date_mo(due_date_mo);
									unit.setDue_date_day(due_date_day);
									unit.setDue_date_yr(due_date_yr);
								}
							}
							if (cblsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_SYSMSG))
							{
								systemMessage = cblsRespFields[i].getFieldValue().trim();
								systemMessageCode = systemMessage.substring(0, 7);
								systemMessageDescription = systemMessage.substring(8,systemMessage.length());
							}
						}
						
						if(systemMessageCode != null && 
								(!systemMessageCode.equalsIgnoreCase(CBLS_SUCCESS)
										|| !systemMessageCode.equalsIgnoreCase(CBLS_SUCCESS_PGDN)))
						{
							ExceptionBuilder.parseException(aContext,
									ruleFile,
									null,
									systemMessageCode,
									systemMessageDescription,
									false,
									ExceptionBuilderRule.NO_DEFAULT,
									null,
									null,
									utility,
									null,
									null,
									null);
						}
						else if(systemMessageCode != null && systemMessageCode.equalsIgnoreCase(CBLS_SUCCESS))
						{
							hasPageDown = false;
							
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"Received message " + systemMessage);
						}
						else if(systemMessageCode != null &&
								systemMessageCode.equalsIgnoreCase(CBLS_SUCCESS_PGDN))
						{
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"Received message " + systemMessage);
							
							ActionOnScreenResponseHelper cblsPageDownResponseHelper;
							String pageDownSystemMessage = null;
							String pageDownSystemCode = null;
							String pageDownSystemDescription = null;
							String cblsPageDownQueryResponse = null;
							hasPageDown = true;
							
							while(hasPageDown)
							{
								com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl cblsPageDownQueryRequest 
								= TIRKSJXHelper.buildCBLSQueryRequest(	sessionID, 
																		"", 
																		"", 
																		"", 
																		"",
																		"",
																		ACTION_F2);
								cblsPageDownQueryResponse = TIRKSJXHelper.sendActionOnScreenRequest(cblsPageDownQueryRequest);
								cblsPageDownResponseHelper = TIRKSJXHelper.parseActionOnScreenResponse(cblsPageDownQueryResponse);
								
								Field [] cblsPageDownRespFields = cblsPageDownResponseHelper.getAFields();
								
								if (cblsPageDownRespFields !=  null && cblsPageDownRespFields.length > 0)
								{
									for (int j=0; j < cblsPageDownRespFields.length; j++)
									{
										if (cblsPageDownRespFields[j].getFieldName().equalsIgnoreCase(FIELD_UNIT))
										{
											CBLSUnit cbls_unit = new CBLSUnit();
											cbls_unit.setUnit(cblsPageDownRespFields[j].getFieldValue().trim());
											cbls_unit.setInstance(cblsPageDownRespFields[j].getFieldInstance());
											
											cbls_unit_list.add(cbls_unit);
										}
										if (cblsPageDownRespFields[j].getFieldName().equalsIgnoreCase(FIELD_CKTID_CLO))
										{
											CBLSUnit unit = (CBLSUnit)cbls_unit_list.get(cblsPageDownRespFields[j].getFieldInstance());
											unit.setCktid_clo(cblsPageDownRespFields[j].getFieldValue().trim());
										}
										if (cblsPageDownRespFields[j].getFieldName().equalsIgnoreCase(FIELD_F))
										{
											CBLSUnit unit = (CBLSUnit)cbls_unit_list.get(cblsPageDownRespFields[j].getFieldInstance());
											unit.setCktid_format(cblsPageDownRespFields[j].getFieldValue().trim());
										}
										if (cblsPageDownRespFields[j].getFieldName().equalsIgnoreCase(FIELD_ACTIVITY_CUR))
										{
											CBLSUnit unit = (CBLSUnit)cbls_unit_list.get(cblsPageDownRespFields[j].getFieldInstance());
											unit.setActivity_cur(cblsPageDownRespFields[j].getFieldValue().trim());
										}
										if (cblsPageDownRespFields[j].getFieldName().equalsIgnoreCase(FIELD_ACTIVITY_PND))
										{
											CBLSUnit unit = (CBLSUnit)cbls_unit_list.get(cblsPageDownRespFields[j].getFieldInstance());
											unit.setActivity_pnd(cblsPageDownRespFields[j].getFieldValue().trim());
										}
										if (cblsPageDownRespFields[j].getFieldName().equalsIgnoreCase(FIELD_DUE_DATE))
										{
											String due_date = cblsPageDownRespFields[j].getFieldValue().trim();
											if(due_date.length() > 0)
											{	
												String due_date_mo = due_date.substring(0, 2);
												String due_date_day = due_date.substring(2, 4);
												String due_date_yr = due_date.substring(4, 6);
												
												CBLSUnit unit = (CBLSUnit)cbls_unit_list.get(cblsPageDownRespFields[j].getFieldInstance());
												unit.setDue_date(due_date);
												unit.setDue_date_mo(due_date_mo);
												unit.setDue_date_day(due_date_day);
												unit.setDue_date_yr(due_date_yr);
											}
										}
										if (cblsPageDownRespFields[j].getFieldName().equalsIgnoreCase(FIELD_SYSMSG))
										{
											pageDownSystemMessage = cblsPageDownRespFields[j].getFieldValue().trim();
											pageDownSystemCode = pageDownSystemMessage.substring(0, 7);
											pageDownSystemDescription = pageDownSystemMessage.substring(8, pageDownSystemMessage.length());
										}
									}									

									cblsPageDownRespFields = null;
									
									utility.log(
											LogEventId.INFO_LEVEL_2,
											"Received page down message " + pageDownSystemMessage);
									
									if(pageDownSystemCode.equalsIgnoreCase(CBLS_SUCCESS))
									{
										hasPageDown = false;
									}
								}
							}
						}
						
						utility.log(
								LogEventId.INFO_LEVEL_2,
								"Received message " + systemMessage);
						
						utility.log(
							LogEventId.INFO_LEVEL_2,
							"Cable header output-->[cable<"
								+ cable
								+ "> term_A<"
								+ term_A
								+ "> term_Z<"
								+ term_Z
								+ ">]");							
						
						if (cableList == null)
						{
							cableList = new ArrayList();
						}
						if(cbls_unit_list!= null && cbls_unit_list.size() > 0)
						{
							for(int k = 0; k < cbls_unit_list.size(); k ++)
							{
								CBLSUnit cbls_unit = (CBLSUnit) cbls_unit_list.get(k);
								utility.log(
										LogEventId.INFO_LEVEL_2,
										"Listing all Cable detail ouput-->[unit<"
											+ cbls_unit.getUnit()
											+ "> cktid_clo<"
											+ cbls_unit.getCktid_clo()
											+ "> cktid_format<"
											+ cbls_unit.getCktid_format()
											+ "> activity_cur<"
											+ cbls_unit.getActivity_cur()
											+ "> activity_pnd<"
											+ cbls_unit.getActivity_pnd()
											+ "> due_date<"
											+ cbls_unit.getDue_date_mo()
											+ "/"
											+ cbls_unit.getDue_date_day()
											+ "/"
											+ cbls_unit.getDue_date_yr()
											+ ">]");									
							}	
							
							CBLSUnit final_unit = (CBLSUnit)cbls_unit_list.get(0);
							String due_date_mo = final_unit.getDue_date_mo();
							String due_date_day = final_unit.getDue_date_day();
							String due_date_yr = final_unit.getDue_date_yr();
							
							String stringDate = due_date_mo 
												+ "/" 
												+ due_date_day 
												+ "/" 
												+ due_date_yr;	
							
							SimpleDateFormat originalDateFormat = new SimpleDateFormat("MM/dd/yy");								
							Date date = originalDateFormat.parse(stringDate);								
							SimpleDateFormat convertedDateFormat = new SimpleDateFormat("MM/dd/yyyy");								
							stringDate = convertedDateFormat.format(date);
							Dt_t finalDate = new Dt_t(stringDate.substring(stringDate.lastIndexOf("/") + 1), due_date_mo, due_date_day);
							
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"Cable detail ouput-->[unit<"
										+ final_unit.getUnit()
										+ "> cktid_clo<"
										+ final_unit.getCktid_clo()
										+ "> cktid_format<"
										+ final_unit.getCktid_format()
										+ "> activity_cur<"
										+ final_unit.getActivity_cur()
										+ "> activity_pnd<"
										+ final_unit.getActivity_pnd()
										+ "> due_date<"
										+ due_date_mo
										+ "/"
										+ due_date_day
										+ "/"
										+ stringDate.substring(stringDate.lastIndexOf("/") + 1)
										+ ">]");
							
							// Create header and detail data.
							cableInfo = new CBLSCableInformation();
							cableInfo.setCable(cable);
							cableInfo.setTermA(term_A);
							cableInfo.setTermZ(term_Z);
							// Save region for next transaction
							cableInfo.setDataCenter(request.ims_region);
							cableInfo.setCableDataCenter(request.data_center);
							cableInfo.setCkitIdClo(final_unit.getCktid_clo());
							cableInfo.setCurAct(final_unit.getActivity_cur());
							cableInfo.setDueDate(finalDate);
							cableInfo.setFormat(final_unit.getCktid_format());
							cableInfo.setPendAct(final_unit.getActivity_pnd());
							cableInfo.setPair(final_unit.getUnit());

							cableList.add(cableInfo);
						} // for loop

						// Found what is needed. 
						// Break from while loop and go back.
						moreDataCenters = false;
						break;
					}	
					else
					{
						//if loop for size of repsonse set.
						utility.log(
							LogEventId.INFO_LEVEL_2,
							"No data received form cable circuit.");
						
						utility.throwException(
							ExceptionCode.ERR_RM_TIRKS_DATA_NOT_FOUND,
							"TIRKS data not found for Cable Circuit: "
								+ input.getCableId().getCable(),
							"CableChannelAssignment",
							Severity.UnRecoverable);
					}
				}			
			} // while loop
		} 
		catch (ParseException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		catch (ServiceException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		finally 
		{
			utility.log(
				LogEventId.REMOTE_RETURN,
				TIRKSJXName,
				TIRKSJXNameVersion,
				TIRKSJXNameVersion,
				"cblsQInput()");
			
			if(sessionID != null)
			{
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.impl.JXAPIImpl closeSessionRequest = TIRKSJXHelper.buildTIRKSJxCloseSessionRequest(sessionID);
				String closeSessionResponse = TIRKSJXHelper.sendCloseSessionRequest(closeSessionRequest);
				// Parse the Close session response
				CloseSessionResponseHelper closeSessionResponseHelper = TIRKSJXHelper.parseCloseSessionResponse(closeSessionResponse);
				
				if(closeSessionResponseHelper != null)
				{
					if(closeSessionResponseHelper.getSessionAPIMessage() != null &&
							closeSessionResponseHelper.getSessionAPIMessage().equalsIgnoreCase(CLOSE_SESSION_SUCCESS))
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " " + CLOSE_SESSION_SUCCESS);
						
						sessionID = null;
					}
					else
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " not closed successfully. " 
									+ closeSessionResponseHelper.getSessionAPIMessage());
					}
				}
			}
		}
		return cableList;
	}

	/**
	 * Returns Carrier Channel Assignment from TIRKS.
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CarrierChannelAssignment
	 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
	 * @param aCircuitId com.sbc.eia.BusinessInterface.rm.TIRKS.CircuitId
	 * @param imsRegion java.lang.String
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types_.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public ArrayList getTIRKSJXCXRSTrans(
		BisContext aContext,
		CircuitId aCircuitId,
		String imsRegions)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "getCXRSTrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		ArrayList cxrsList = null;
		ArrayList outputFields = null;
		String ruleFile = (String) getProperties().get("EXCEPTION_BUILDER_TIRKS_RULE_FILE");
		String openSessionResponse = null;
		String sessionID = null;
		String cxrsQueryResponse = null;
		ActionOnScreenResponseHelper cxrsResponseHelper;
		ErrorOnScreenResponseHelper errorResponseHelper;
		boolean hasPageDown = false;

		// Build the helper
		setupTIRKSJXHelpers(1);

		TIRKSJX TIRKSJXHelper =
			(TIRKSJX) tirksJxHelpers.get(0);

		// Check if the data centers are loaded.   
		// Load the State to data center mappings
		// Check if there is a valid sate to data center mapping
		
		// Load the State to data center mappings
		if (TIRKSJXProperties.size() < 1)
		{
			loadTIRKSJxProperties();
		}

		String stateCode = company.getState().getCode();	
		String dataCenters = null;
		
		if ((dataCenters = (String) TIRKSJXProperties.get(TIRKSJXName + "." + stateCode))== null) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
				"Could not find TIRKS Data Center for state code: "
					+ company.getState().getCode(),
				myMethodName,
				Severity.UnRecoverable);
		}
		
		utility.log(
			LogEventId.INFO_LEVEL_2,
			company.getState().getCode()
				+ " configured Data Center<"
				+ dataCenters
				+ ">");
		
		String aFacDesg = new String();
		String aFacTyp = new String();
		String aTerma = new String();
		String aTermz = new String();
		
		StringTokenizer st =
			new StringTokenizer(aCircuitId.asOssString().substring(2), "/");
		
		try 
		{
			aFacDesg = st.nextToken();
			aFacTyp = st.nextToken();
			aTerma = st.nextToken();
			aTermz = st.nextToken();
		} 
		catch (NoSuchElementException nsee) 
		{
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"We got a NoSuchElementException: " + nsee.getMessage());
		}
		
		// Build the CXRS request
		CxrsQTIRKSJXInput request =
			new CxrsQTIRKSJXInput("", aTerma, aTermz, aFacDesg, aFacTyp);

		try 
		{
			utility.log(
				LogEventId.REMOTE_CALL,
				TIRKSJXName,
				TIRKSJXNameVersion,
				TIRKSJXNameVersion,
				"cxrsQInput()");

			// Loop through the regions, trying each in turn
			boolean moreDataCenters = true;
			boolean triedADataCenter = false;
			DataCenters centers = new DataCenters(dataCenters);
			
			while (moreDataCenters
				&& ((request.data_center = centers.getNextDataCenter()) != null)) 
			{
				triedADataCenter = true;

				utility.log(
					LogEventId.INFO_LEVEL_2,
					"CircuitProvisioning Request["
						+ "data_center<"
						+ request.data_center
						+ "> aFacDesg<"
						+ aFacDesg
						+ "> aFacTyp<"
						+ aFacTyp
						+ "> aTerma<"
						+ aTerma
						+ "> aTermz<"
						+ aTermz
						+ ">]");
				
				// Call the service to get the screens
				// Only want 1st screen
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.impl.JXAPIImpl openSessionRequest = TIRKSJXHelper.buildTIRKSJxOpenSessionRequest(request.data_center);
				openSessionResponse = TIRKSJXHelper.sendOpenSessionRequest(openSessionRequest);
				
				OpenSessionResponseHelper openSessionResponseHelper =  TIRKSJXHelper.parseOpenSessionResponse(openSessionResponse);
				sessionID = openSessionResponseHelper.getSessionID();
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl cxrsQueryRequest 
				= TIRKSJXHelper.buildCXRSQueryRequest(	sessionID, 
														aTerma, 
														aTermz, 
														aFacDesg, 
														aFacTyp,
														ACTION_F1);
				cxrsQueryResponse = TIRKSJXHelper.sendActionOnScreenRequest(cxrsQueryRequest);
				
				if(TIRKSJXHelper.checkResponseHasError(cxrsQueryResponse))
				{
					errorResponseHelper = TIRKSJXHelper.parseErrorOnScreenResponse(cxrsQueryResponse);
					TIRKSJXError[] TIRKSJXError = errorResponseHelper.getTirksErrors();
					int TIRKSJXErrorSize = TIRKSJXError.length;
					String errorType = null;
					String errorMessage = null;
					
					for(int i=0; i<TIRKSJXErrorSize; i++)
					{
						errorType = TIRKSJXError[i].getErrorType();
						errorMessage = TIRKSJXError[i].getErrorMessage();
					}
					
					utility.throwException(
							ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER,
							"CircuitProvisioningHelper failure: CircuitProvisioning::get: Unexpected return from CircuitProvisioningHelper:lnpActiveSubsInput(): "
								+ errorType 
								+ " "
								+ errorMessage,
							(String) getProperties().get("BIS_NAME"),
							Severity.UnRecoverable);

				}
				else
				{
					cxrsResponseHelper = TIRKSJXHelper.parseActionOnScreenResponse(cxrsQueryResponse);
					Field [] cxrsRespFields = cxrsResponseHelper.getAFields();
				
					if (cxrsRespFields != null && cxrsRespFields.length > 0)
					{	
						String clo = null;
						String systemMessage = null;
						String systemMessageCode = null;
						String systemMessageDescription = null;
						
						if (cxrsList == null)
						{
							cxrsList = new ArrayList();
						}
						if (outputFields == null)
						{
							outputFields = new ArrayList();
						}
						
						for (int i=0; i < cxrsRespFields.length; i++)
						{
							CxrsOutputFields aCxrsOutputField = null;
							
							if (cxrsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CLO))
							{
								clo = cxrsRespFields[i].getFieldValue().trim();
							}
							if (cxrsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_OUTPUT))
							{
								String output = cxrsRespFields[i].getFieldValue();
								
								if(output != null && !output.trim().equalsIgnoreCase(""))
								{
									aCxrsOutputField = new CxrsOutputFields();
									
									aCxrsOutputField.setInstance(cxrsRespFields[i].getFieldInstance());
									aCxrsOutputField.setChannel_pair((output.substring(0, 5).trim()));
									aCxrsOutputField.setCur_act((output.substring(12, 13).trim()));
									aCxrsOutputField.setCircuit_id((output.substring(22, 67).trim()));
									aCxrsOutputField.setPend_act((output.substring(68, 70).trim()));
									aCxrsOutputField.setDue_date_month((output.substring(71, 73).trim()));
									aCxrsOutputField.setDue_date_day((output.substring(74, 76).trim()));
									aCxrsOutputField.setDue_date_year((output.substring(77, 79).trim()));
										
									if((aCxrsOutputField.getDue_date_month() != null && !aCxrsOutputField.getDue_date_month().equalsIgnoreCase(""))
											&& (aCxrsOutputField.getDue_date_day() != null && !aCxrsOutputField.getDue_date_day().equalsIgnoreCase(""))
											&& aCxrsOutputField.getDue_date_year() != null && !aCxrsOutputField.getDue_date_year().equalsIgnoreCase(""))
									{
										String due_date_mo = aCxrsOutputField.getDue_date_month();
										String due_date_day = aCxrsOutputField.getDue_date_day();
										String due_date_yr = aCxrsOutputField.getDue_date_year();
										String stringDate = due_date_mo + "/" + due_date_day + "/" + due_date_yr;								
										SimpleDateFormat originalDateFormat = new SimpleDateFormat("MM/dd/yy");								
										Date date = originalDateFormat.parse(stringDate);								
										SimpleDateFormat convertedDateFormat = new SimpleDateFormat("MM/dd/yyyy");								
										stringDate = convertedDateFormat.format(date);
										Dt_t finalDate = new Dt_t(stringDate.substring(stringDate.lastIndexOf("/") + 1), due_date_mo, due_date_day);
										
										aCxrsOutputField.setDue_date_data(finalDate);
									}
									
									outputFields.add(aCxrsOutputField);
								}
							}
							if (cxrsRespFields[i].getFieldName().equalsIgnoreCase(FIELD_SYSMSG))
							{
								systemMessage = cxrsRespFields[i].getFieldValue().trim();
								systemMessageCode = systemMessage.substring(0, 7);
								systemMessageDescription = systemMessage.substring(8, systemMessage.length());
							}
						}
						if(systemMessageCode != null && 
								(!systemMessageCode.equalsIgnoreCase(CXRS_SUCCESS) || 
										systemMessageCode.equalsIgnoreCase(CXRS_SUCCESS_PGDN)))
						{
							throw new CircuitProvisioningException(systemMessageCode,systemMessageDescription);
						}
						if (systemMessageCode != null &&
								systemMessageCode.equalsIgnoreCase(CXRS_SUCCESS))
						{
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"Received message " + systemMessage);
							
							hasPageDown = false;
						}
						if (systemMessageCode != null &&
								systemMessageCode.equalsIgnoreCase(CXRS_SUCCESS_PGDN))
						{
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"Received message " + systemMessage);
							
							ActionOnScreenResponseHelper cxrsPageDownResponseHelper;
							String pageDownSystemMessage = null;
							String pageDownSystemCode = null;
							String pageDownSystemDescription = null;
							String cxrsPageDownQueryResponse = null;
							hasPageDown = true;
							
							while(hasPageDown)
							{
								com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl cxrsPageDownQueryRequest 
								= TIRKSJXHelper.buildCXRSQueryRequest(	sessionID, 
																		"", 
																		"", 
																		"", 
																		"",
																		ACTION_F2);
								cxrsPageDownQueryResponse = TIRKSJXHelper.sendActionOnScreenRequest(cxrsPageDownQueryRequest);
								cxrsPageDownResponseHelper = TIRKSJXHelper.parseActionOnScreenResponse(cxrsPageDownQueryResponse);
								
								Field [] cxrsPageDownRespFields = cxrsPageDownResponseHelper.getAFields();
								
								if (cxrsPageDownRespFields != null && cxrsPageDownRespFields.length > 0)
								{
									for (int i=0; i < cxrsPageDownRespFields.length; i++)
									{
										CxrsOutputFields aCxrsOutputField = null;
										
										if (cxrsPageDownRespFields[i].getFieldName().equalsIgnoreCase(FIELD_OUTPUT))
										{
											String output = cxrsPageDownRespFields[i].getFieldValue().trim();
											
											if(output != null && !output.equalsIgnoreCase(""))
											{
												aCxrsOutputField = new CxrsOutputFields();
												
												aCxrsOutputField.setInstance(cxrsPageDownRespFields[i].getFieldInstance());
												aCxrsOutputField.setChannel_pair((output.substring(0, 5).trim()));
												aCxrsOutputField.setCur_act((output.substring(12, 13).trim()));
												aCxrsOutputField.setCircuit_id((output.substring(22, 67).trim()));
												aCxrsOutputField.setPend_act((output.substring(68, 70).trim()));
												aCxrsOutputField.setDue_date_month((output.substring(71, 73).trim()));
												aCxrsOutputField.setDue_date_day((output.substring(74, 76).trim()));
												aCxrsOutputField.setDue_date_year((output.substring(77, 79).trim()));
													
												if((aCxrsOutputField.getDue_date_month() != null && !aCxrsOutputField.getDue_date_month().equalsIgnoreCase(""))
														&& (aCxrsOutputField.getDue_date_day() != null && !aCxrsOutputField.getDue_date_day().equalsIgnoreCase(""))
														&& aCxrsOutputField.getDue_date_year() != null && !aCxrsOutputField.getDue_date_year().equalsIgnoreCase(""))
												{
													String due_date_mo = aCxrsOutputField.getDue_date_month();
													String due_date_day = aCxrsOutputField.getDue_date_day();
													String due_date_yr = aCxrsOutputField.getDue_date_year();
													String stringDate = due_date_mo + "/" + due_date_day + "/" + due_date_yr;								
													SimpleDateFormat originalDateFormat = new SimpleDateFormat("MM/dd/yy");								
													Date date = originalDateFormat.parse(stringDate);								
													SimpleDateFormat convertedDateFormat = new SimpleDateFormat("MM/dd/yyyy");								
													stringDate = convertedDateFormat.format(date);
													Dt_t finalDate = new Dt_t(stringDate.substring(stringDate.lastIndexOf("/") + 1), due_date_mo, due_date_day);
													
													aCxrsOutputField.setDue_date_data(finalDate);
												}
												
												outputFields.add(aCxrsOutputField);
											}
											if (cxrsPageDownRespFields[i].getFieldName().equalsIgnoreCase(FIELD_SYSMSG))
											{
												pageDownSystemMessage = cxrsPageDownRespFields[i].getFieldValue().trim();
												pageDownSystemCode = pageDownSystemMessage.substring(0, 7);
												pageDownSystemDescription = pageDownSystemMessage.substring(8, pageDownSystemMessage.length());
											}
										}
									}

									cxrsPageDownRespFields = null;
									
									utility.log(
											LogEventId.INFO_LEVEL_2,
											"Received page down message " + pageDownSystemMessage);
									
									if(pageDownSystemCode.equalsIgnoreCase(CXRS_SUCCESS))
									{
										hasPageDown = false;
									}
								}
							}
						}								

						utility.log(
								LogEventId.INFO_LEVEL_2,
								"Received message " + systemMessage);
						
						for(int j = 0; j < outputFields.size(); j++)
						{
							CxrsOutputFields aCxrsOutputField = (CxrsOutputFields) outputFields.get(j);
							
							if (aCxrsOutputField.getChannel_pair() != null && !aCxrsOutputField.getChannel_pair().equalsIgnoreCase(""))
							{
								utility.log(
										LogEventId.INFO_LEVEL_2,
										"channel_pair<"
											+ aCxrsOutputField.getChannel_pair()
											+ "> cur_act<"
											+ aCxrsOutputField.getCur_act()
											+ "> circuit_id<"
											+ aCxrsOutputField.getCircuit_id()
											+ "> pend_act<"
											+ aCxrsOutputField.getPend_act()
											+ "> due_date<"
											+ aCxrsOutputField.getDue_date_month()
											+ "/"
											+ aCxrsOutputField.getDue_date_day()
											+ "/"
											+ aCxrsOutputField.getDue_date_year()
											+ ">");
								
								CarrierChannelAssignment aChnlAsgn = new CarrierChannelAssignment();
								aChnlAsgn.setDue_date_data(aCxrsOutputField.getDue_date_data());
								aChnlAsgn.setChannel_pair(aCxrsOutputField.getChannel_pair());
								aChnlAsgn.setCur_act(aCxrsOutputField.getCur_act());
								aChnlAsgn.setPend_act(aCxrsOutputField.getPend_act());
								aChnlAsgn.setCircuit_id(aCxrsOutputField.getCircuit_id());
								aChnlAsgn.setClo(clo);
								cxrsList.add(aChnlAsgn); // add to the list
							}
						}
					}
				}				

				moreDataCenters = false;
				break;
			} 
		}
		catch (ParseException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		catch (ServiceException e) {
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		finally 
		{
			utility.log(
				LogEventId.REMOTE_RETURN,
				TIRKSJXName,
				TIRKSJXNameVersion,
				TIRKSJXNameVersion,
				"cxrsQInput()");

			if(sessionID != null)
			{
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.impl.JXAPIImpl closeSessionRequest = TIRKSJXHelper.buildTIRKSJxCloseSessionRequest(sessionID);
				String closeSessionResponse = TIRKSJXHelper.sendCloseSessionRequest(closeSessionRequest);
				// Parse the Close session response
				CloseSessionResponseHelper closeSessionResponseHelper = TIRKSJXHelper.parseCloseSessionResponse(closeSessionResponse);
				
				if(closeSessionResponseHelper != null)
				{
					if(closeSessionResponseHelper.getSessionAPIMessage() != null &&
							closeSessionResponseHelper.getSessionAPIMessage().equalsIgnoreCase(CLOSE_SESSION_SUCCESS))
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " " + CLOSE_SESSION_SUCCESS);
						
						sessionID = null;
					}
					else
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " not closed successfully. " 
									+ closeSessionResponseHelper.getSessionAPIMessage());
					}
				}
			}
		}

		return cxrsList;
	}

	/**
	 * Returns Design Releated Information from TIRKS.
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.DesignRelatedInformation
	 * @param aContext com.sbc.eia.idl.bis_types_.BisContext
	 * @param aCircuitId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId
	 * @param imsRegion java.lang.String
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public DesignRelatedInformation getTIRKSJXDRTrans(
		BisContext aContext,
		CircuitId aCircuitId,
		String imsRegions)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "getDRTrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String ruleFile = (String) getProperties().get("EXCEPTION_BUILDER_TIRK_RULE_FILE");
		String openSessionResponse = null;
		String sessionID = null;
		String driQueryResponse = null;
		String driRefreshResponse = null;
		ActionOnScreenResponseHelper driResponseHelper;
		ActionOnScreenResponseHelper driRefreshResponseHelper;
		ErrorOnScreenResponseHelper errorResponseHelper;

		// Build the helper
		setupTIRKSJXHelpers(1);

		TIRKSJX TIRKSJXHelper =
			(TIRKSJX) tirksJxHelpers.get(0);

		// Check if the data centers are loaded.   
		// Load the State to data center mappings
		// Check if there is a valid sate to data center mapping
		
		// Load the State to Data center mappings
		if (TIRKSJXProperties.size() < 1)
		{
			loadTIRKSJxProperties();
		}

		String stateCode = company.getState().getCode();	
		String dataCenters = null;
		
		if ((dataCenters = (String) TIRKSJXProperties.get(TIRKSJXName + "." + stateCode))== null) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
				"Could not find TIRKS Data Center for state code: "
					+ company.getState().getCode(),
				myMethodName,
				Severity.UnRecoverable);
		}
		
		utility.log(
			LogEventId.INFO_LEVEL_2,
			company.getState().getCode()
				+ " configured Data Center<"
				+ dataCenters
				+ ">");

		// Build the DRI request
		DriQTIRKSJXInput request =
			new DriQTIRKSJXInput("", "", aCircuitId.asOssString(), "", "");

		DesignRelatedInformation driInfo = null;
		ArrayList driRespSet = null;

		try 
		{
			utility.log(
				LogEventId.REMOTE_CALL,
				TIRKSJXName,
				TIRKSJXNameVersion,
				TIRKSJXNameVersion,
				"driQInput()");

			// Loop through the data centers, trying each in turn
			boolean moreDataCenters = true;
			boolean triedADataCenter = false;
			DataCenters centers = new DataCenters(dataCenters);
			
			while (moreDataCenters
				&& ((request.data_center = centers.getNextDataCenter()) != null)) 
			{
				triedADataCenter = true;
				
				utility.log(
					LogEventId.INFO_LEVEL_2,
					"CircuitProvisioning Request["
						+ "data_center<"
						+ request.data_center
						+ "> clo<"
						+ request.clo
						+ "> ckt<"
						+ request.ckt
						+ "> cac<"
						+ request.cac
						+ "> ord<"
						+ request.ord
						+ ">]");

				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.impl.JXAPIImpl openSessionRequest = TIRKSJXHelper.buildTIRKSJxOpenSessionRequest(request.data_center);
				openSessionResponse = TIRKSJXHelper.sendOpenSessionRequest(openSessionRequest);
				
				OpenSessionResponseHelper openSessionResponseHelper =  TIRKSJXHelper.parseOpenSessionResponse(openSessionResponse);
				sessionID = openSessionResponseHelper.getSessionID();
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl driQueryRequest 
				= TIRKSJXHelper.buildDRIQueryRequest(	sessionID, 
														Character.toString(request.ckt.charAt(0)), 
														request.ckt.substring(2), 
														request.clo, 
														request.cac,
														request.ord,
														null,
														ACTION_F1);
				driQueryResponse = TIRKSJXHelper.sendActionOnScreenRequest(driQueryRequest);
				
				if(TIRKSJXHelper.checkResponseHasError(driQueryResponse))
				{
					errorResponseHelper = TIRKSJXHelper.parseErrorOnScreenResponse(driQueryResponse);
					TIRKSJXError[] TIRKSJXError = errorResponseHelper.getTirksErrors();
					int TIRKSJXErrorSize = TIRKSJXError.length;
					String errorType = null;
					String errorMessage = null;
					
					for(int i=0; i<TIRKSJXErrorSize; i++)
					{
						errorType = TIRKSJXError[i].getErrorType();
						errorMessage = TIRKSJXError[i].getErrorMessage();
					}

					utility.throwException(
							ExceptionCode
								.ERR_RM_CIRCUITPROVISIONING_HELPER,
							"CircuitProvisioningHelper failure: CircuitProvisioning::get: Unexpected return from CircuitProvisioningHelper:lnpActiveSubsInput(): "
								+ errorType
								+" "
								+ errorMessage,
							(String) getProperties().get("BIS_NAME"),
							Severity.UnRecoverable);
				}
				else
				{
					driResponseHelper = TIRKSJXHelper.parseActionOnScreenResponse(driQueryResponse);
					Field [] driRespFields = driResponseHelper.getAFields();
				
					if (driRespFields.length > 0)  // Don't need a loop - only want first screen
					{
						String nc = null;
						String nci1 = null;
						String nci2 = null;
						String systemMessage = null;
						String systemMessageCode = null;
						String systemMessageDescription = null;
						
						for (int i=0; i < driRespFields.length; i++)
						{
							if (driRespFields[i].getFieldName().equalsIgnoreCase(FIELD_NC))
							{
								nc = driRespFields[i].getFieldValue().trim();
							}
							if (driRespFields[i].getFieldName().equalsIgnoreCase(FIELD_NC1))
							{
								nci1 = driRespFields[i].getFieldValue().trim();
							}
							if (driRespFields[i].getFieldName().equalsIgnoreCase(FIELD_NC2))
							{
								nci2 = driRespFields[i].getFieldValue().trim();
							}
							if (driRespFields[i].getFieldName().equalsIgnoreCase(FIELD_SYSMSG))
							{
								systemMessage = driRespFields[i].getFieldValue().trim();
								systemMessageCode = systemMessage.substring(0, 7);
								systemMessageDescription = systemMessage.substring(8, systemMessage.length());
							}
						}
						
						if(systemMessageCode != null && !systemMessageCode.equalsIgnoreCase(DRI_SUCCESS))
						{
							ExceptionBuilder.parseException(aContext,
									ruleFile,
									null,
									systemMessageCode,
									systemMessageDescription,
									false,
									ExceptionBuilderRule.NO_DEFAULT,
									null,
									null,
									utility,
									null,
									null,
									null);
						}
						else
						{
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"Received message " + systemMessage);
							
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"nc<"
										+ nc
										+ "> nci1<"
										+ nci1
										+ "> nci2<"
										+ nci2
										+ ">");
								driInfo = new DesignRelatedInformation();
								driInfo.setNc(nc);
								driInfo.setNci1(nci1);
								driInfo.setNci2(nci2);
								moreDataCenters = false;
								break;
						}
					}
				}		
			}
		} 
		catch (ServiceException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		finally 
		{

			utility.log(
				LogEventId.REMOTE_RETURN,
				TIRKSJXName,
				TIRKSJXNameVersion,
				TIRKSJXNameVersion,
				"driQInput()");
			
			if(sessionID != null)
			{
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl driRefreshRequest 
				= TIRKSJXHelper.buildDRIQueryRequest(	sessionID, 
														"", 
														"", 
														"", 
														"",
														"",
														"",
														ACTION_F8);
				
				driRefreshResponse = TIRKSJXHelper.sendActionOnScreenRequest(driRefreshRequest);
				
				driRefreshResponseHelper = TIRKSJXHelper.parseActionOnScreenResponse(driRefreshResponse);
				
				Field [] driRespFields = driRefreshResponseHelper.getAFields();
				
				if (driRespFields.length > 0)  // Don't need a loop - only want first screen
				{
					String systemMessage = null;
					
					for (int i=0; i < driRespFields.length; i++)
					{
						if (driRespFields[i].getFieldName().equalsIgnoreCase(FIELD_SYSMSG))
						{
							systemMessage = driRespFields[i].getFieldValue().trim();
						}
					}
					
					utility.log(
							LogEventId.INFO_LEVEL_2,
							"DRI Refresh Response " +
							systemMessage );
				}
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.impl.JXAPIImpl closeSessionRequest = TIRKSJXHelper.buildTIRKSJxCloseSessionRequest(sessionID);
				String closeSessionResponse = TIRKSJXHelper.sendCloseSessionRequest(closeSessionRequest);
				// Parse the Close session response
				CloseSessionResponseHelper closeSessionResponseHelper = TIRKSJXHelper.parseCloseSessionResponse(closeSessionResponse);
				
				if(closeSessionResponseHelper != null)
				{
					if(closeSessionResponseHelper.getSessionAPIMessage() != null &&
							closeSessionResponseHelper.getSessionAPIMessage().equalsIgnoreCase(CLOSE_SESSION_SUCCESS))
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " " + CLOSE_SESSION_SUCCESS);
						
						sessionID = null;
					}
					else
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " not closed successfully. " 
									+ closeSessionResponseHelper.getSessionAPIMessage());
					}
				}
			}
		}

		return driInfo;
	}

	/**
	 * Look for equipemnt scan infromation.
	 * Try all the regions and all the Factypes.
	 * If nothing is found throw exception;
	 *  
	 * @param aContext
	 * @param input
	 * @return
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public ArrayList getTIRKSJXEQPSCTrans(
		BisContext aContext, 
		EQPSCInput input)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "getEQPSCTrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String originator = (String) getProperties().get("BIS_NAME");
		String ruleFile = (String) getProperties().get("EXCEPTION_BUILDER_TIRKS_RULE_FILE");
		String openSessionResponse = null;
		String sessionID = null;
		String eqpscQueryResponse = null;
		ActionOnScreenResponseHelper eqpscResponseHelper;
		ErrorOnScreenResponseHelper errorResponseHelper;
		TIRKSJX TIRKSJXHelper = null;
		boolean hasPageDown = false;
		
		// Check if the data centers are loaded.   
		// Load the State to data center mappings
		// Check if there is a valid sate to data center mapping
		
		// Load the State to data center mappings
		if (TIRKSJXProperties.size() < 1)
		{
			loadTIRKSJxProperties();
		}

		String stateCode = company.getState().getCode();	
		String dataCenters = null;
		
		if ((dataCenters = (String) TIRKSJXProperties.get(TIRKSJXName + "." + stateCode))== null) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
				"Could not find TIRKS Data Center for state code: "
					+ company.getState().getCode(),
				myMethodName,
				Severity.UnRecoverable);
		}
		utility.log(
			LogEventId.INFO_LEVEL_2,
			company.getState().getCode()
				+ " configured Data Center<"
				+ dataCenters
				+ ">");
		// Create input and ouput arrays and objects.

		ExceptionBuilderResult ebr = null;
		EquipmentScanInformation eqpscInfo = null;
		ArrayList eqpscList = null;

		// Build the APOT request
		// Set the region, location, equipemnt code, level and unit.

		EqpscQTIRKSJXInput request =
			new EqpscQTIRKSJXInput(
				"",
				"",
				input.getLocation(),
				"",
				input.getLevel(),
				input.formatRelayRack(),
				input.formatUnit());

		// Set to do nothing.
		boolean moreDataCenters = false;
		int codeCount = 1;

		DataCenters centers = new DataCenters(dataCenters);

		// Set up first code.
		// Get the equipment code by translating the factype.

		if (((request.data_center = centers.getNextDataCenter()) != null)
			&& ((input.translateEquipmentCode(input.getFacType(), codeCount))
				!= null)) 
		{
			moreDataCenters = true;
		} 
		else 
		{
			utility.log(
				LogEventId.INFO_LEVEL_2,
				"No regions or fac types to get.");
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_DATA_NOT_FOUND,
				"TIRKS data not found for equipement scan Circuit: "
					+ input.getPointInterfaceId().getPointInterfaceId(),
				originator,
				Severity.UnRecoverable);
		}

		// Build the helper.

		try 
		{
			// Build the helper
			setupTIRKSJXHelpers(1);

			TIRKSJXHelper =
				(TIRKSJX) tirksJxHelpers.get(0);
		} 
		catch (Exception e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER,
				"CircuitProvisioning failure: "
					+ ": "
					+ e.getMessage(),
				originator,
				Severity.UnRecoverable,
				e);
		}
		try 
		{

			utility.log(
				LogEventId.REMOTE_CALL,
				TIRKSJXName,
				TIRKSJXNameVersion,
				TIRKSJXNameVersion,
				"eqpscQInput()");			

			// Loop through the regions, trying each in turn
			while (moreDataCenters == true) 
			{
				// Get the equipment code using the fac type translation.
				if ((request.equip_code =
					input.translateEquipmentCode(codeCount))
					!= null) 
				{
					codeCount++;
					// End of the fac types for an IMS region.
				} 
				else 
				{
					// Start next data center region.
					if ((request.data_center = centers.getNextDataCenter()) != null) 
					{
						// Set the fac type translation to the first one. 
						codeCount = 1;
						// Get the equipment code using the fac type translation.
						request.equip_code =
							input.translateEquipmentCode(codeCount);
						// No more regions or fac types.
					} 
					else 
					{
						moreDataCenters = false;
						continue;
					}
				}

				utility.log(
					LogEventId.INFO_LEVEL_2,
					"TIRKSJX Equipment Scan input request-->["
						+ "data_center<"
						+ request.data_center
						+ "> location<"
						+ request.location
						+ "> equip_code<"
						+ request.equip_code
						+ "> level<"
						+ request.level
						+ "> relayrack<"
						+ request.relayrack
						+ "> unit<"
						+ request.unit
						+ ">]");

				// Call the service to get the screens
				// Only want 1st screen
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.impl.JXAPIImpl openSessionRequest = TIRKSJXHelper.buildTIRKSJxOpenSessionRequest(request.data_center);
				openSessionResponse = TIRKSJXHelper.sendOpenSessionRequest(openSessionRequest);
				
				OpenSessionResponseHelper openSessionResponseHelper =  TIRKSJXHelper.parseOpenSessionResponse(openSessionResponse);
				sessionID = openSessionResponseHelper.getSessionID();
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl eqpscQueryRequest 
				= TIRKSJXHelper.buildEQPSCQueryRequest(	sessionID, 
														request.location, 
														request.equip_code, 
														request.level, 
														request.relayrack,
														request.unit,
														ACTION_F1);
				eqpscQueryResponse = TIRKSJXHelper.sendActionOnScreenRequest(eqpscQueryRequest);
				
				if(TIRKSJXHelper.checkResponseHasError(eqpscQueryResponse))
				{
					errorResponseHelper = TIRKSJXHelper.parseErrorOnScreenResponse(eqpscQueryResponse);
					TIRKSJXError[] TIRKSJXError = errorResponseHelper.getTirksErrors();
					int TIRKSJXErrorSize = TIRKSJXError.length;
					String errorType = null;
					String errorMessage = null;
					
					for(int i=0; i<TIRKSJXErrorSize; i++)
					{
						errorType = TIRKSJXError[i].getErrorType();
						errorMessage = TIRKSJXError[i].getErrorMessage();
					}
					
					utility.throwException(
							ExceptionCode
								.ERR_RM_CIRCUITPROVISIONING_HELPER,
							"CircuitProvisioningHelper failure: CircuitProvisioning::get: "
								+ "Unexpected event return from CircuitProvisioningHelper: "
								+ errorType
								+ " "
								+ errorMessage,
							originator,
							Severity.UnRecoverable);
				}
				else
				{
					eqpscResponseHelper = TIRKSJXHelper.parseActionOnScreenResponse(eqpscQueryResponse);
					Field [] eqpscRespFields = eqpscResponseHelper.getAFields();
				
					if (eqpscRespFields != null && eqpscRespFields.length > 0)  // Don't need a loop - only want first screen
					{						
						String equip_code = null;
						String location = null;
						String level = null;
						String relayrack = null;
						String systemMessage = null;
						String systemMessageCode = null;
						String systemMessageDescription = null;
						ArrayList eqpscUnitList = new ArrayList();
						
						for (int i=0; i < eqpscRespFields.length; i++)
						{
							if (eqpscRespFields[i].getFieldName().equalsIgnoreCase(FIELD_EQUIP_CODE))
							{
								equip_code = eqpscRespFields[i].getFieldValue().trim();
							}
							if (eqpscRespFields[i].getFieldName().equalsIgnoreCase(FIELD_LOCATION))
							{
								location = eqpscRespFields[i].getFieldValue().trim();
							}
							if (eqpscRespFields[i].getFieldName().equalsIgnoreCase(FIELD_LEVEL))
							{
								level = eqpscRespFields[i].getFieldValue().trim();
							}
							if (eqpscRespFields[i].getFieldName().equalsIgnoreCase(FIELD_RELAY_RACK))
							{
								relayrack = eqpscRespFields[i].getFieldValue().trim();
							}
							if (eqpscRespFields[i].getFieldName().equalsIgnoreCase(FIELD_UNIT))
							{
								EQPSCUnit eqpscUnit = new EQPSCUnit();
								eqpscUnit.setUnit(eqpscRespFields[i].getFieldValue().trim());
								eqpscUnit.setInstance(eqpscRespFields[i].getFieldInstance());
								eqpscUnitList.add(eqpscUnit);
							}
							if (eqpscRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CIRCUIT_IDENTIFICATION))
							{
								EQPSCUnit eqpscUnit = (EQPSCUnit)eqpscUnitList.get(eqpscRespFields[i].getFieldInstance());
								eqpscUnit.setCircuitid(eqpscRespFields[i].getFieldValue().trim());
							}
							if (eqpscRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CURRENT_ACTIVITY))
							{
								EQPSCUnit eqpscUnit = (EQPSCUnit)eqpscUnitList.get(eqpscRespFields[i].getFieldInstance());
								eqpscUnit.setCuract(eqpscRespFields[i].getFieldValue().trim());
							}
							if (eqpscRespFields[i].getFieldName().equalsIgnoreCase(FIELD_PENDING_ACTIVITY))
							{
								EQPSCUnit eqpscUnit = (EQPSCUnit)eqpscUnitList.get(eqpscRespFields[i].getFieldInstance());
								eqpscUnit.setPendact(eqpscRespFields[i].getFieldValue().trim());
							}
							if (eqpscRespFields[i].getFieldName().equalsIgnoreCase(FIELD_DUE_DATE))
							{
								EQPSCUnit eqpscUnit = (EQPSCUnit)eqpscUnitList.get(eqpscRespFields[i].getFieldInstance());
								eqpscUnit.setDuedate(eqpscRespFields[i].getFieldValue().trim());
							}
							if (eqpscRespFields[i].getFieldName().equalsIgnoreCase(FIELD_SYSMSG))
							{
								systemMessage = eqpscRespFields[i].getFieldValue().trim();
								systemMessageCode = systemMessage.substring(0, 7);
								systemMessageDescription = systemMessage.substring(8, systemMessage.length());
							}
						}
						
						if(systemMessageCode != null && 
								(!systemMessageCode.equalsIgnoreCase(EQPSC_SUCCESS) || 
										!systemMessageCode.equalsIgnoreCase(EQPSC_SUCCESS_PGDN)))
						{
							ebr = ExceptionBuilder.parseException(
									aContext,
									ruleFile,
									null,
									systemMessageCode,
									systemMessageDescription,
									false,
									ExceptionBuilderRule.NO_DEFAULT,
									null,
									null,
									utility,
									null,
									null,
									null);
							
//							 Check for object not found, timeout or business violation.
							// Only retry next region under these conditions.
							int code = ebr.getBusinessRule();
							
							if (code == 3 || code == 1 || code == 1) 
							{
								utility.log(
									LogEventId.INFO_LEVEL_2,
									"Exception caught: Object not found from EQPSC.");
								continue;
							} 
							else
							{
								ebr.throwException(aContext, utility);
							}
						}
						if(systemMessageCode != null && systemMessageCode.equalsIgnoreCase(EQPSC_SUCCESS))
						{
							hasPageDown = false;
							
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"Received message " + systemMessage);
						}
							if(systemMessageCode != null && systemMessageCode.equalsIgnoreCase(EQPSC_SUCCESS_PGDN))
						{
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"Received message " + systemMessage);
							
							ActionOnScreenResponseHelper eqpscPageDownResponseHelper;
							String pageDownSystemMessage = null;
							String pageDownSystemCode = null;
							String pageDownSystemDescription = null;
							String eqpscPageDownQueryResponse = null;
							hasPageDown = true;
							
							while(hasPageDown)
							{
								com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl eqpscPageDownQueryRequest 
								= TIRKSJXHelper.buildEQPSCQueryRequest(	sessionID, 
																		"",
																		"", 
																		"", 
																		"",
																		"",
																		ACTION_F2);
								eqpscPageDownQueryResponse = TIRKSJXHelper.sendActionOnScreenRequest(eqpscPageDownQueryRequest);
								
								eqpscPageDownResponseHelper = TIRKSJXHelper.parseActionOnScreenResponse(eqpscPageDownQueryResponse);
								Field [] eqpscPageDownRespFields = eqpscPageDownResponseHelper.getAFields();
							
								if (eqpscPageDownRespFields != null && eqpscPageDownRespFields.length > 0)  // Don't need a loop - only want first screen
								{											
									for (int i=0; i < eqpscPageDownRespFields.length; i++)
									{
										if (eqpscPageDownRespFields[i].getFieldName().equalsIgnoreCase(FIELD_UNIT))
										{
											EQPSCUnit eqpscUnit = new EQPSCUnit();
											eqpscUnit.setUnit(eqpscPageDownRespFields[i].getFieldValue().trim());
											eqpscUnit.setInstance(eqpscPageDownRespFields[i].getFieldInstance());
											eqpscUnitList.add(eqpscUnit);
											
										}
										if (eqpscPageDownRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CIRCUIT_IDENTIFICATION))
										{
											EQPSCUnit eqpscUnit = (EQPSCUnit)eqpscUnitList.get(eqpscPageDownRespFields[i].getFieldInstance());
											eqpscUnit.setCircuitid(eqpscPageDownRespFields[i].getFieldValue().trim());
										}
										if (eqpscPageDownRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CURRENT_ACTIVITY))
										{
											EQPSCUnit eqpscUnit = (EQPSCUnit)eqpscUnitList.get(eqpscPageDownRespFields[i].getFieldInstance());
											eqpscUnit.setCuract(eqpscPageDownRespFields[i].getFieldValue().trim());
										}
										if (eqpscPageDownRespFields[i].getFieldName().equalsIgnoreCase(FIELD_PENDING_ACTIVITY))
										{
											EQPSCUnit eqpscUnit = (EQPSCUnit)eqpscUnitList.get(eqpscPageDownRespFields[i].getFieldInstance());
											eqpscUnit.setPendact(eqpscPageDownRespFields[i].getFieldValue().trim());
										}
										if (eqpscPageDownRespFields[i].getFieldName().equalsIgnoreCase(FIELD_DUE_DATE))
										{
											EQPSCUnit eqpscUnit = (EQPSCUnit)eqpscUnitList.get(eqpscPageDownRespFields[i].getFieldInstance());
											eqpscUnit.setDuedate(eqpscPageDownRespFields[i].getFieldValue().trim());
										}
										if (eqpscPageDownRespFields[i].getFieldName().equalsIgnoreCase(FIELD_SYSMSG))
										{
											pageDownSystemMessage = eqpscPageDownRespFields[i].getFieldValue().trim();
											pageDownSystemCode = pageDownSystemMessage.substring(0, 7);
											pageDownSystemDescription = pageDownSystemMessage.substring(8, pageDownSystemMessage.length());
										}
									}
									
									eqpscPageDownRespFields = null;
									
									utility.log(
											LogEventId.INFO_LEVEL_2,
											"Received message " + systemMessage);									
									
									if(pageDownSystemCode.equalsIgnoreCase(EQPSC_SUCCESS))
									{
										hasPageDown = false;
									}
								}
							}	
						}
						
						if(eqpscUnitList!= null && eqpscUnitList.size() > 0)
						{
							for(int j = 0; j < eqpscUnitList.size(); j++)
							{
								EQPSCUnit eqpscUnit = (EQPSCUnit)eqpscUnitList.get(j);
								utility.log(
										LogEventId.INFO_LEVEL_2,
										"eqpsc detail ouputs-->[unit<"
											+ eqpscUnit.getUnit()
											+ "> instance<"
											+ eqpscUnit.getInstance()
											+ "> cktid_clo<"
											+ eqpscUnit.getCircuitid()
											+ "> activity_cur<"
											+ eqpscUnit.getCuract()
											+ "> activity_pnd<"
											+ eqpscUnit.getPendact()
											+ "> due_date<"
											+ eqpscUnit.getDuedate()
											+ ">]");
							}
						}
						
						if(eqpscUnitList != null)
						{
							EQPSCUnit finalEqpscUnit = (EQPSCUnit)eqpscUnitList.get(0);
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"Eqpsc final header output-->[equip_code<"
										+ equip_code
										+ "> location<"
										+ location
										+ "> level<"
										+ level
										+ "> relay rack<"
										+ relayrack
										+ "> unit<"
										+ finalEqpscUnit.getUnit()
										+ ">]");
							
							if (!input.getUnit().equalsIgnoreCase(finalEqpscUnit.getUnit())) 
							{
								utility.throwException(
									ExceptionCode
										.ERR_RM_TIRKS_DATA_NOT_FOUND,
									"TIRKS data not found for equipement scan Circuit: "
										+ input
											.getPointInterfaceId()
											.getPointInterfaceId(),
									originator,
									Severity.UnRecoverable);
							}
							
							if (eqpscList == null)
							{
								eqpscList = new ArrayList();
							}
							
							for (int i = 0;	i < 1; i++) 
							{ // Create header data.
								eqpscInfo = new EquipmentScanInformation();
								eqpscInfo.setEquipmentCode(equip_code);
								eqpscInfo.setLocation(location);
								eqpscInfo.setLevel(level);
								eqpscInfo.setLevel(relayrack);
								// Save region for next transaction
								eqpscInfo.setIMSRegion(request.ims_region);
								eqpscInfo.setDataCenter(request.data_center);
	
								utility.log(
									LogEventId.INFO_LEVEL_2,
									"eqpsc detail ouput-->[unit<"
										+ finalEqpscUnit.getUnit()
										+ "> cktid_clo<"
										+ finalEqpscUnit.getCircuitid()
										+ "> activity_cur<"
										+ finalEqpscUnit.getCuract()
										+ "> activity_pnd<"
										+ finalEqpscUnit.getPendact()
										+ "> due_date<"
										+ finalEqpscUnit.getDuedate()
										+ ">]");
								// Create detail data.
								eqpscInfo.setCkitIdClo(finalEqpscUnit.getCircuitid());
								eqpscInfo.setCurAct(finalEqpscUnit.getCuract());
								eqpscInfo.setDueDate(finalEqpscUnit.getDuedate());
								eqpscInfo.setPendAct(finalEqpscUnit.getPendact());
								eqpscInfo.setUnit(finalEqpscUnit.getUnit());
								eqpscList.add(eqpscInfo);
							}								
						// Found what is needed.
						// Break from while loop.
							moreDataCenters = false;
							break;
						}
						else // if there is data loop
						{
							utility.log(
								LogEventId.INFO_LEVEL_2,
								"No data received from eqpsc circuit.");
						}
					}
				}
			} // while loop
		} 
		catch (ServiceException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		finally 
		{ // log performance and disconnect
			utility.log(
				LogEventId.REMOTE_RETURN,
				TIRKSJXName,
				TIRKSJXNameVersion,
				TIRKSJXNameVersion,
					"eqpscQInput()");
			
			if(sessionID != null)
			{
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.impl.JXAPIImpl closeSessionRequest = TIRKSJXHelper.buildTIRKSJxCloseSessionRequest(sessionID);
				String closeSessionResponse = TIRKSJXHelper.sendCloseSessionRequest(closeSessionRequest);
				// Parse the Close session response
				CloseSessionResponseHelper closeSessionResponseHelper = TIRKSJXHelper.parseCloseSessionResponse(closeSessionResponse);
				
				if(closeSessionResponseHelper != null)
				{
					if(closeSessionResponseHelper.getSessionAPIMessage() != null &&
							closeSessionResponseHelper.getSessionAPIMessage().equalsIgnoreCase(CLOSE_SESSION_SUCCESS))
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " " + CLOSE_SESSION_SUCCESS);
						
						sessionID = null;
					}
					else
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " not closed successfully. " 
									+ closeSessionResponseHelper.getSessionAPIMessage());
					}
				}
			}
		}

		if (eqpscList == null) 
		{
			ebr.throwException(aContext, utility);

		}
		
		return eqpscList;
	}
	
	/**
	 * Returns rdloc Information from TIRKS.
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.rdlocInformation
	 * @param aContext com.sbc.eia.idl.bis_types_.BisContext
	 * @param aCircuitId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId
	 * @param imsRegion java.lang.String
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public ArrayList getTIRKSJXRDLOCTrans(BisContext aContext, RDLOCInput input)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {

		String myMethodName = "getRDLOCTrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		String ruleFile = (String) getProperties().get("EXCEPTION_BUILDER_TIRKS_RULE_FILE");
		String openSessionResponse = null;
		String sessionID = null;
		String rdlocQueryResponse = null;
		ActionOnScreenResponseHelper rdlocResponseHelper;
		ErrorOnScreenResponseHelper errorResponseHelper;

		// Build the helper
		setupTIRKSJXHelpers(1);

		TIRKSJX TIRKSJXHelper =
			(TIRKSJX) tirksJxHelpers.get(0);
		
		// Load the State to data center mappings
		if (TIRKSJXProperties.size() < 1)
		{
			loadTIRKSJxProperties();
		}
		
		String stateCode = company.getState().getCode();	
		String dataCenters = null;
		
		if ((dataCenters = (String) TIRKSJXProperties.get(TIRKSJXName + "." + stateCode))== null) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
				"Could not find TIRKS Data Center for state code: "
					+ company.getState().getCode(),
				myMethodName,
				Severity.UnRecoverable);
		}
		utility.log(
			LogEventId.INFO_LEVEL_2,
			company.getState().getCode()
				+ " configured Data Center<"
				+ dataCenters
				+ ">");

		ReferenceLocationInformation rdlocInfo = null;
		ArrayList rdlocList = null;

		ArrayList rdlocRespSet = null;

		// Build the RDLOC request

		RdlocQTIRKSJXInput request = new RdlocQTIRKSJXInput("", input.getLocation());

		// Connect to the CircuitProvisioning Service

		try 
		{

			// Only want the first screen
			utility.log(
				LogEventId.REMOTE_CALL,
				TIRKSJXName,
				TIRKSJXNameVersion,
				TIRKSJXNameVersion,
				"rdlocQInput()");

			// Loop through the regions, trying each in turn

			boolean moreDataCenters = true;
			boolean triedADataCenter = false;
			DataCenters centers = new DataCenters(dataCenters);

			while (moreDataCenters
				&& ((request.data_center = centers.getNextDataCenter()) != null)) 
			{

				triedADataCenter = true;
				utility.log(
					LogEventId.INFO_LEVEL_2,
					"TIRKSJX Location input request-->["
						+ "data_center<"
						+ request.data_center
						+ "> location<"
						+ request.location
						+ ">]");

				// only wants 1st screen
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.impl.JXAPIImpl openSessionRequest = TIRKSJXHelper.buildTIRKSJxOpenSessionRequest(request.data_center);
				openSessionResponse = TIRKSJXHelper.sendOpenSessionRequest(openSessionRequest);
				
				OpenSessionResponseHelper openSessionResponseHelper =  TIRKSJXHelper.parseOpenSessionResponse(openSessionResponse);
				sessionID = openSessionResponseHelper.getSessionID();
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl rdlocQueryRequest 
				= TIRKSJXHelper.buildRDLOCQueryRequest(	sessionID,
														request.location,
														ACTION_F1);
				rdlocQueryResponse = TIRKSJXHelper.sendActionOnScreenRequest(rdlocQueryRequest);

				if(TIRKSJXHelper.checkResponseHasError(rdlocQueryResponse))
				{
					errorResponseHelper = TIRKSJXHelper.parseErrorOnScreenResponse(rdlocQueryResponse);
					TIRKSJXError[] TIRKSJXError = errorResponseHelper.getTirksErrors();
					int TIRKSJXErrorSize = TIRKSJXError.length;
					String errorType = null;
					String errorMessage = null;
					
					for(int i=0; i<TIRKSJXErrorSize; i++)
					{
						errorType = TIRKSJXError[i].getErrorType();
						errorMessage = TIRKSJXError[i].getErrorMessage();
					}

					utility.throwException(
							ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER,
							"CircuitProvisioningHelper failure: CircuitProvisioning::get: Unexpected return from CircuitProvisioningHelper:lnpActiveSubsInput(): "
								+ errorType
								+ " "
								+ errorMessage,
							(String) getProperties().get("BIS_NAME"),
							Severity.UnRecoverable);
				}
				else
				{
					rdlocResponseHelper = TIRKSJXHelper.parseActionOnScreenResponse(rdlocQueryResponse);
					Field [] rdlocRespFields = rdlocResponseHelper.getAFields();
				
					if (rdlocRespFields != null && rdlocRespFields.length > 0)  // Don't need a loop - only want first screen
					{
						String description = null;
						String company = null;
						String locname = null;
						String owner = null;
						String systemMessage = null;
						String systemMessageCode = null;
						String systemMessageDescription = null;
						
						for (int i=0; i < rdlocRespFields.length; i++)
						{
							if (rdlocRespFields[i].getFieldName().equalsIgnoreCase(FIELD_DESCRIPTION))
							{
								description = rdlocRespFields[i].getFieldValue().trim();
							}
							if (rdlocRespFields[i].getFieldName().equalsIgnoreCase(FIELD_COMPANY))
							{
								company = rdlocRespFields[i].getFieldValue().trim();
							}
							if (rdlocRespFields[i].getFieldName().equalsIgnoreCase(FIELD_LOC_NAME))
							{
								locname = rdlocRespFields[i].getFieldValue().trim();
							}
							if (rdlocRespFields[i].getFieldName().equalsIgnoreCase(FIELD_OWNER))
							{
								owner = rdlocRespFields[i].getFieldValue().trim();
							}
							if (rdlocRespFields[i].getFieldName().equalsIgnoreCase(FIELD_SYSMSG))
							{
								systemMessage = rdlocRespFields[i].getFieldValue().trim();
								systemMessageCode = systemMessage.substring(0, 7);
								systemMessageDescription = systemMessage.substring(8, systemMessage.length());
							}
						}
						if(systemMessageCode != null && !systemMessageCode.equalsIgnoreCase(RDLOC_SUCCESS))
						{
							utility.throwException(
									ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER,
									"CircuitProvisioningHelper failure: CircuitProvisioning::get: Unexpected return from CircuitProvisioningHelper:lnpActiveSubsInput(): "
										+ systemMessage,
									(String) getProperties().get("BIS_NAME"),
									Severity.UnRecoverable);
						}
						else
						{
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"Received message " + systemMessage);
							
							utility.log(
								LogEventId.INFO_LEVEL_2,
								"Location ouput-->[description<"
									+ description
									+ "> company<"
									+ company
									+ "> loc<"
									+ locname
									+ "> owner<"
									+ owner
									+ ">]");
							
							if (rdlocList == null)
								rdlocList = new ArrayList();
							rdlocInfo = new ReferenceLocationInformation();
							rdlocInfo.setDescription(description);
							rdlocInfo.setOwner(owner);
							rdlocInfo.setLocName(locname);

							rdlocList.add(rdlocInfo);
							moreDataCenters = false;
							break;
						}
					}
				}
			}
		} 
		catch (ServiceException e) 
		{
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				"",
				e.getMessage(),
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
		finally 
		{
			utility.log(
					LogEventId.REMOTE_RETURN,
					TIRKSJXName,
					TIRKSJXNameVersion,
					TIRKSJXNameVersion,
					"rdlocQInput()");
			
			if(sessionID != null)
			{
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.impl.JXAPIImpl closeSessionRequest = TIRKSJXHelper.buildTIRKSJxCloseSessionRequest(sessionID);
				String closeSessionResponse = TIRKSJXHelper.sendCloseSessionRequest(closeSessionRequest);
				// Parse the Close session response
				CloseSessionResponseHelper closeSessionResponseHelper = TIRKSJXHelper.parseCloseSessionResponse(closeSessionResponse);
				
				if(closeSessionResponseHelper != null)
				{
					if(closeSessionResponseHelper.getSessionAPIMessage() != null &&
							closeSessionResponseHelper.getSessionAPIMessage().equalsIgnoreCase(CLOSE_SESSION_SUCCESS))
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " " + CLOSE_SESSION_SUCCESS);
						
						sessionID = null;
					}
					else
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " not closed successfully. " 
									+ closeSessionResponseHelper.getSessionAPIMessage());
					}
				}
			}			
		}

		return rdlocList;
	}
	
	/**
	 * Returns work authorization information from TIRKS.
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.WorkAuthorizationInformation[]
	 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
	 * @param inArray com.sbc.eia.bis.BusinessInterface.rm.TIRKS.WAInput[]
	 * @param aCircuitId com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.CircuitId
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public WorkAuthorizationInformation[] getTIRKSJXWATrans(
		BisContext aContext,
		WAInput[] inArray)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {
		String myMethodName = "TIRKSBase::getWATrans()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		// No point processing an empty array
		if (inArray == null || inArray.length < 1) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_INVALID_WA_INPUT_ARRAY,
				myMethodName + ": passed null or empty WAInput[]",
				myMethodName,
				Severity.UnRecoverable);
		}
		
		// Load the State to IMS region mappings
		if (TIRKSJXProperties.size() < 1)
			loadTIRKSJxProperties();

		// Build the helpers
		setupTIRKSJXHelpers(inArray.length);

		// Figure out the receive wait time (only needed if there is more than one input object processed at a time)
		int receiveWaitTime = 0;
		if (inArray.length > 1 && tirksJxHelpers.size() > 1) {
			try {
				receiveWaitTime =
					Integer
						.valueOf(
							(String) getProperties().get(
								"TIRKS_RECEIVE_WAIT_TIME"))
						.intValue();
			} catch (Exception e) {
				utility.log(
					LogEventId.DEBUG_LEVEL_2,
					"TIRKS_RECEIVE_WAIT_TIME not found (or invalid) in properties. ("
						+ e.getMessage()
						+ "). Using 1");
				receiveWaitTime = 1;
			}
			if (receiveWaitTime < 1) {
				utility.log(
					LogEventId.ERROR,
					"TIRKS_RECEIVE_WAIT_TIME invalid in properties: <"
						+ receiveWaitTime
						+ ">. Using 1");
				receiveWaitTime = 1;
			}
		}

		// Figure out the gross time out
		int tirksXJTimeOut = 0;
		try {
			tirksXJTimeOut =
				Integer
					.valueOf(
						(String) getProperties().get(
							"TIRKSJX_SERVICE_TIMEOUT"))
					.intValue();
		} catch (Exception e) {
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"TIRKSJX_SERVICE_TIMEOUT not found (or invalid) in properties. ("
					+ e.getMessage()
					+ "). Using 30");
			tirksXJTimeOut = 30;
		}
		if (tirksXJTimeOut < 1) {
			utility.log(
				LogEventId.ERROR,
				"TIRKSJX_SERVICE_TIMEOUT invalid in properties: <"
					+ tirksXJTimeOut
					+ ">. Using 30");
			tirksXJTimeOut = 30;
		}
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"tirksXJTimeOut<" + tirksXJTimeOut + ">");

		// Figure out how many helpers will be in use - all or just enough for one for each transaction
		int numberOfHelpersInUse =
			(inArray.length < tirksJxHelpers.size()
				? inArray.length
				: tirksJxHelpers.size());

		// Get the relevant IMS regions

		String stateCode = company.getState().getCode();	
		String dataCenter = null;
		
		if ((dataCenter = (String) TIRKSJXProperties.get(TIRKSJXName + "." + stateCode))== null) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAPPING_NOT_FOUND,
				"Could not find TIRKS Data Center for state code: "
					+ company.getState().getCode(),
				myMethodName,
				Severity.UnRecoverable);
		}
		utility.log(
			LogEventId.INFO_LEVEL_2,
			company.getState().getCode()
				+ " configured Data Center<"
				+ dataCenter
				+ ">");

		// Create the output array

		WorkAuthorizationInformation[] waInfo =	new WorkAuthorizationInformation[inArray.length];
		
		for (int i = 0; i < waInfo.length; i++)
		
			waInfo[i] = null;

		ArrayList tirksJxHelpersSubset = new ArrayList();
		
		try 
		{
			// Connect to the CircuitProvisioning Service. Use those helpers that manage to connect
			int invalidHelpers = 0;
			Exception saveException = null;
			for (int i = 0; i < numberOfHelpersInUse; i++) 
			{
				try 
				{					
					// Use this connected helper
					tirksJxHelpersSubset.add((TIRKSJX) tirksJxHelpers.get(i));

				} 
				catch (Exception e) 
				{
					invalidHelpers++;
					utility.log(
						LogEventId.ERROR,
						"TIRKSJX connect failure: xception: "
							+ e.getMessage());
				}
			}
			if ((numberOfHelpersInUse -= invalidHelpers) < 1) 
			{
				if (saveException == null)
				{	utility.throwException(
						ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
						"TIRKSJX connect failure on all helpers",
						TIRKSJXName,
						Severity.UnRecoverable);
				}
				else
				{	utility.throwException(
						ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
						"TIRKSJX connect failure on all helpers",
						TIRKSJXName,
						Severity.UnRecoverable,
						saveException);
				}
			}
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"numberOfHelpersInUse<" + numberOfHelpersInUse + ">");

			// Figure out the final time outs and maxium number of time outs
			int maximumTimeOuts = 0;
			
			if (numberOfHelpersInUse > 1) 
			{
				// Each transaction has a number of short time outs
				maximumTimeOuts = tirksXJTimeOut / receiveWaitTime;
			} 
			else 
			{
				// The only transaction has one long time out
				receiveWaitTime = tirksXJTimeOut;
				maximumTimeOuts = 1;
			}

			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"receiveWaitTime<" + receiveWaitTime + ">");
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"maximumTimeOuts<" + maximumTimeOuts + ">");

			// Loop through all the input requests, passing the work off to the workers
			WAWorker workers[] = new WAWorker[numberOfHelpersInUse];
			for (int i = 0; i < numberOfHelpersInUse; i++) 
			{
				workers[i] =
					new WAWorker(
						utility,
						properties,
						dataCenter,
						(TIRKSJX) tirksJxHelpersSubset.get(i),
						(String) getProperties().get("BIS_NAME"),
						company,
						waInfo,
						receiveWaitTime,
						maximumTimeOuts,
						i);
				workers[i].setInput(inArray[i]);
				workers[i].outputIndex(i);
			}
			int numberComplete = 0;
			int nextToProcess = numberOfHelpersInUse;
			int usableWorkers = numberOfHelpersInUse;
			boolean setupWorker = true;

			// Multiplex through each worker until it returns true (complete) or throws an exception
			while (numberComplete < inArray.length) 
			{
				// The number of usable workers has dropped to zero because of reconnect failures
				if (usableWorkers < 1)
				{
					utility.throwException(
						ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
						"saveException connect failure on all helpers. All reconnects failed",
						"saveException",
						Severity.UnRecoverable);
				}

				for (int i = 0; i < workers.length; i++) 
				{
					utility.log(
						LogEventId.DEBUG_LEVEL_2,
						"numberComplete<"
							+ numberComplete
							+ "> nextToProcess<"
							+ nextToProcess
							+ ">");
					utility.log(
						LogEventId.DEBUG_LEVEL_2,
						"worker["
							+ i
							+ "] state<"
							+ workers[i].getState()
							+ "> outputIndex<"
							+ workers[i].getOutputIndex()
							+ ">");
					setupWorker = false;
					try 
					{
						if (workers[i].getState() != WAWorker.STATE_IDLE)
						{
							// Only use non-idle workers
							setupWorker =
								workers[i].processTIRKSJX(aContext, getProperties());
						}
							
					} 
					catch (AccessDenied e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (BusinessViolation e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (InvalidData e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (NotImplemented e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (ObjectNotFound e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (DataNotFound e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					} 
					catch (SystemFailure e) 
					{
						waInfo[workers[i].getOutputIndex()] =
							new WorkAuthorizationInformation();
						waInfo[workers[i].getOutputIndex()].setException(e);
						setupWorker = true;
					}

					// Does the connection need to be dropped/reconnected
					if (workers[i].getState() != WAWorker.STATE_IDLE
						&& workers[i].getReconnect()
						&& ((!setupWorker)
						|| (setupWorker && nextToProcess < inArray.length)))
						// complete - no more transactions
					{
						try 
						{
							workers[i].setReconnect(false);
						} 
						catch (Exception e) 
						{
							Exception ex = null;
							try {
								utility.throwException(
									ExceptionCode
										.ERR_RM_CIRCUITPROVISIONING_HELPER,
									"TIRKSJX disconnect/reconnect failure on helper",
									TIRKSJXName,
									Severity.UnRecoverable,
									e);
							} 
							catch (AccessDenied x) 
							{
								ex = x;
							} 
							catch (BusinessViolation x) 
							{
								ex = x;
							} 
							catch (InvalidData x) 
							{
								ex = x;
							} 
							catch (NotImplemented x) 
							{
								ex = x;
							} 
							catch (ObjectNotFound x) 
							{
								ex = x;
							} 
							catch (DataNotFound x) 
							{
								ex = x;
							} 
							catch (SystemFailure x) 
							{
								ex = x;
							}

							waInfo[workers[i].getOutputIndex()] =
								new WorkAuthorizationInformation();
							waInfo[workers[i].getOutputIndex()].setException(
								ex);
							workers[i].setIdleState();
							setupWorker = false;
							numberComplete++;
							usableWorkers--;
						}
					}

					if (setupWorker) // Worker needs a new transaction
					{
						// Complete - find another to process
						numberComplete++;
						if (nextToProcess < inArray.length) 
						{
							// There are more transactions
							workers[i].setInput(inArray[nextToProcess]);
							workers[i].outputIndex(nextToProcess++);
						} 
						else
						{
							// There are no more transactions
							workers[i].setIdleState();
							setupWorker = false;
							try 
							{
								//workers[i].getHelper().disconnect();
								// The finally clause will try again - this is to
								//	free connections as quickly as possible
							} 
							catch (Exception e) 
							{
								utility.log(
									LogEventId.ERROR,
									"TIRKSJX failure: ServiceException: "
										+ ": "
										+ e.getMessage());
							}
						}
					}
				}
			}
		} 
		finally // Disconnect
		{
			for (int i = 0; i < numberOfHelpersInUse; i++) 
			{
				try 
				{

				} 
				catch (Exception e) 
				{
					utility.log(
						LogEventId.ERROR,
						"TIRKSJX disconnect failure: Exception: "
							+ ": "
							+ e.getMessage());
				}
			}
		}
		return waInfo;
	}
	/**
	 * Figures out how many service helpers are needed and creates them accordingly.
	 * @param inputArrayLength int
	 */
	private void setupTIRKSJXHelpers(int inputArrayLength)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {
		String myMethodName = "TIRKSBase::setupTIRKSJXHelpers()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		// Figure out the maximum number of helpers
		int maximumHelpers = 0;
		
		try 
		{
			maximumHelpers =
				Integer
					.valueOf(
						(String) getProperties().get(
							"TIRKS_NUMBER_OF_HELPERS"))
					.intValue();
		} 
		catch (Exception e) 
		{
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"TIRKS_NUMBER_OF_HELPERS not found (or invalid) in properties. ("
					+ e.getMessage()
					+ "). Using 5");
			maximumHelpers = 5;
		}
		if (maximumHelpers < 1) 
		{
			utility.log(
				LogEventId.ERROR,
				"TIRKS_NUMBER_OF_HELPERS invalid in properties: <"
					+ maximumHelpers
					+ ">. Using 5");
			maximumHelpers = 5;
		}
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"maximumHelpers<" + maximumHelpers + ">");

		// First time
		if (tirksJxHelpers == null)
		{
			tirksJxHelpers = new ArrayList();
		}

		// How many new helpers are needed this time
		int newHelpersRequired = 0;
		
		if (tirksJxHelpers.size() < maximumHelpers
			&& tirksJxHelpers.size() < inputArrayLength)
		{
			newHelpersRequired =
				inputArrayLength < maximumHelpers
					? inputArrayLength - tirksJxHelpers.size()
					: maximumHelpers - tirksJxHelpers.size();
		}
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"newHelpersRequired<" + newHelpersRequired + ">");

		// Build the new helpers
		if (newHelpersRequired > 0) 
		{
			Exception savedE = null;
			
			try 
			{
				for (int i = 0; i < newHelpersRequired; i++)
					tirksJxHelpers.add(
						new TIRKSJX(
							utility,
							getProperties()));
			} 
			catch (Exception e) 
			{
				savedE = e;
				utility.log(
					LogEventId.ERROR,
					"TIRKSJX failure: "
						+ e.getMessage()
						+ ": "
						+ e.getMessage());
			}

			// Check there is at least one helper
			if (tirksJxHelpers.size() < 1) 
			{
				utility.throwException(
					ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER,
					"TIRKSJX failure: "
						+ savedE.getMessage()
						+ ": "
						+ savedE.getMessage(),
					TIRKSJXName,
					Severity.UnRecoverable,
					savedE);
			}
		}
	}
	
	/**
	 * Loads the TIRSKJX property file
	 * 
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	private void loadTIRKSJxProperties()
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "TIRKSJx::loadTIRKSJxProperties()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
	
		// The file contains the TIRKSJx Properties 
		String tirksJxFile = (String) getProperties().get("TIRKSJX_PROPERTY_FILE");
		
		if (tirksJxFile == null || tirksJxFile.length() < 1)
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_STATE_IMS_REGION_MAP_FILE_PROPERTY,
				"TIRKSJX_PROPERTY_FILE property not found/not set",
				myMethodName,
				Severity.UnRecoverable);
		}
		
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"Loaded TIRKSJx Properties from: <"
				+ tirksJxFile
				+ ">");
		
		try 
		{
			TIRKSJXProperties = new Properties();
			
			PropertiesFileLoader.read(
				TIRKSJXProperties,
				tirksJxFile,
				utility);
		
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				("Loaded "
					+ TIRKSJXProperties.size()
					+ " TIRKSJx properties"));
		
			utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);			
		} 
		catch (FileNotFoundException e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_STATE_IMS_REGION_MAP_FILE,
				"FileNotFoundException: " + e.getMessage(),
				myMethodName,
				Severity.UnRecoverable);
		} 
		catch (IOException e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_STATE_IMS_REGION_MAP_FILE,
				"IOException: " + e.getMessage(),
				myMethodName,
				Severity.UnRecoverable,
				e);
		}
	}
	
	/**
	 * Initializes the state to IMS region mapping from a properties file.
	 * 
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	private void loadStateToIMSRegion()
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {
		String myMethodName = "TIRKSBase::loadStateToIMSRegion()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		// The file contains data of the form: StateCode=IMSRegion:IMSRegion (eg: CA=IMSA5:IMSA8 or TX=IMSA6)
		String stateToIMSRegionFile =
			(String) getProperties().get("TIRKS_STATE_TO_IMS_REGION_MAP_FILE");
		if (stateToIMSRegionFile == null || stateToIMSRegionFile.length() < 1)
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_STATE_IMS_REGION_MAP_FILE_PROPERTY,
				"TIRKS_STATE_TO_IMS_REGION_MAP_FILE property not found/not set",
				myMethodName,
				Severity.UnRecoverable);

		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"Load state to ims region mappings from: <"
				+ stateToIMSRegionFile
				+ ">");
		try {
			PropertiesFileLoader.read(
				stateToIMSRegion,
				stateToIMSRegionFile,
				utility);
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				("Loaded "
					+ stateToIMSRegion.size()
					+ " state to ims region mappings"));
		} catch (FileNotFoundException e) {
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_STATE_IMS_REGION_MAP_FILE,
				"FileNotFoundException: " + e.getMessage(),
				myMethodName,
				Severity.UnRecoverable);
		} catch (IOException e) {
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_STATE_IMS_REGION_MAP_FILE,
				"IOException: " + e.getMessage(),
				myMethodName,
				Severity.UnRecoverable,
				e);
		}
	}
	
	/**
	 * Figures out how many service helpers are needed and creates them accordingly.
	 * @param inputArrayLength int
	 */
	private void setupHelpers(int inputArrayLength)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {
		String myMethodName = "TIRKSBase::setupHelpers()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		// Figure out the maximum number of helpers
		int maximumHelpers = 0;
		try {
			maximumHelpers =
				Integer
					.valueOf(
						(String) getProperties().get(
							"TIRKS_NUMBER_OF_HELPERS"))
					.intValue();
		} catch (Exception e) {
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"TIRKS_NUMBER_OF_HELPERS not found (or invalid) in properties. ("
					+ e.getMessage()
					+ "). Using 5");
			maximumHelpers = 5;
		}
		if (maximumHelpers < 1) {
			utility.log(
				LogEventId.ERROR,
				"TIRKS_NUMBER_OF_HELPERS invalid in properties: <"
					+ maximumHelpers
					+ ">. Using 5");
			maximumHelpers = 5;
		}
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"maximumHelpers<" + maximumHelpers + ">");

		// First time
		if (circuitProvisioningHelpers == null)
			circuitProvisioningHelpers = new ArrayList();

		// How many new helpers are needed this time
		int newHelpersRequired = 0;
		if (circuitProvisioningHelpers.size() < maximumHelpers
			&& circuitProvisioningHelpers.size() < inputArrayLength)
			newHelpersRequired =
				inputArrayLength < maximumHelpers
					? inputArrayLength - circuitProvisioningHelpers.size()
					: maximumHelpers - circuitProvisioningHelpers.size();
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"newHelpersRequired<" + newHelpersRequired + ">");

		// Build the new helpers
		if (newHelpersRequired > 0) {
			ServiceException savedE = null;
			try {
				for (int i = 0; i < newHelpersRequired; i++)
					circuitProvisioningHelpers.add(
						new CircuitProvisioningHelper(
							getProperties(),
							utility));
			} catch (ServiceException e) {
				savedE = e;
				utility.log(
					LogEventId.ERROR,
					"CircuitProvisioning failure: "
						+ e.getExceptionCode()
						+ ": "
						+ e.getMessage());
			}

			// Check there is at least one helper
			if (circuitProvisioningHelpers.size() < 1) {
				utility.throwException(
					ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER,
					"CircuitProvisioning failure: "
						+ savedE.getExceptionCode()
						+ ": "
						+ savedE.getMessage(),
					"CircuitProvisioningHelper",
					Severity.UnRecoverable,
					savedE);
			}
		}
	}
}
