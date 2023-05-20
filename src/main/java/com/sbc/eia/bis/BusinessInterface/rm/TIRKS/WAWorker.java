//$Id: WAWorker.java,v 1.8 2011/05/07 01:43:36 rs278j Exp $
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

import java.util.ArrayList;
import java.util.ListIterator;

import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.ActionOnScreenResponseHelper;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.CloseSessionResponseHelper;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.DataCenters;
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
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException;
import com.sbc.gwsvcs.service.circuitprovisioning.CircuitProvisioningAccess;
import com.sbc.gwsvcs.service.circuitprovisioning.CircuitProvisioningHelper;
import com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaOutput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQInput;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQTIRKSJXInput;


/**
 * Handles Work Authorization transactions with TIRKS.
 * Creation date: (6/25/01 11:34:33 AM)
 * @author: Creighton Malet

#   History :
#   Date       | Author        | Version   | Notes
#   ----------------------------------------------------------------------------
#	09/16/02	Mark Liljequist	6.0.0		RM64179
#											Added mcn field in process.
#	01/09/03	Mark Liljequist	6.0.5		Add Elogging.
#	12/12/2006	Prasad Ganji	1.0.0	 	Changed Exception Codes from RM to RMIM
#	07/10/2007	Prasad Ganji			 	Add CVS Id keyword on line 1
#	
*/
 
public class WAWorker implements TIRKSJXConstants
{
	private CircuitProvisioningHelper helper = null;
	private String imsRegions = null;
	private TIRKSJX TIRKSJXHelper = null;
	private com.sbc.bccs.utilities.Utility utility = null;
	private String dataCenters = null;
	private int state = 0;
	private WAInput input = null;
	private WorkAuthorizationInformation[] outputArray = null;
	private int receiveWaitTime = 0;
	private int maximumTimeOuts = 0;
	private int id = -1;
	private int numberOfTimeOuts = 0;
	private boolean hadATimeout = false;
	private boolean hadADowntime = false;
	private boolean reconnect = false;
	private int outputIndex = 0;
	private WaQInput request = new WaQInput();
	private WaQTIRKSJXInput requestTIRKSJX = new WaQTIRKSJXInput();
	private ArrayList exceptionArray = new ArrayList();
	private String CircuitProvisioningName = CircuitProvisioningAccess.name+"-"+CircuitProvisioningAccess.version;
	private boolean triedARegion = false;
	private String TIRKSJXName = null;
	private String TIRKSJXNameVersion = null;
	private boolean triedADataCenter = false;
	private DataCenters centers = null;
	private IMSRegions regions = null;
	private String bisName = null;
	private Company company = null;
	public final static int STATE_NEW_TRAN = 0;
	public final static int STATE_SEND = 1;
	public final static int STATE_RECEIVE = 2;
	public final static int STATE_IDLE = 3;
	private static final String processMethodName = "WAWorker::process()";
	String openSessionResponse = null;
	String sessionID = null;
	String waQueryResponse = null;
	String waRefreshResponse = null;
	ActionOnScreenResponseHelper waResponseHelper;
	ActionOnScreenResponseHelper waRefreshResponseHelper;
	ErrorOnScreenResponseHelper errorResponseHelper;
	
	/**
	 * Class constructor.
	 * Creation date: (6/25/01 2:11:00 PM)
	 */
	private WAWorker() {}
	
	/**
	 * Class constructor accepting setup parameters.
	 * Creation date: (6/25/01 2:11:00 PM)
	 * 
	 * @param aUtility
	 * @param anImsRegions
	 * @param aHelper
	 * @param aBisName
	 * @param aCompany
	 * @param anOutputArray
	 * @param aReceiveWaitTime
	 * @param aMaximumTimeOuts
	 * @param anId
	 */
	public WAWorker(
		com.sbc.bccs.utilities.Utility aUtility, 
		String anImsRegions, 
		CircuitProvisioningHelper aHelper,
		String aBisName, 
		Company aCompany, 
		WorkAuthorizationInformation[] anOutputArray, 
		int aReceiveWaitTime, 
		int aMaximumTimeOuts, 
		int anId)
	{
		utility = aUtility;
		imsRegions = anImsRegions;
		helper = aHelper;
		bisName = aBisName;
		company = aCompany;
		outputArray = anOutputArray;
		receiveWaitTime = aReceiveWaitTime;
		maximumTimeOuts = aMaximumTimeOuts;
		id = anId;
	}
	
	/**
	 * Class constructor accepting setup parameters.
	 * Creation date: (6/25/01 2:11:00 PM)
	 * 
	 * @param aUtility
	 * @param aProperties
	 * @param aDataCenters
	 * @param aHelper
	 * @param aBisName
	 * @param aCompany
	 * @param anOutputArray
	 * @param aReceiveWaitTime
	 * @param aMaximumTimeOuts
	 * @param anId
	 */
	public WAWorker(
		com.sbc.bccs.utilities.Utility aUtility, 
		java.util.Hashtable aProperties, 
		String aDataCenters, 
		TIRKSJX aHelper,
		String aBisName, 
		Company aCompany, 
		WorkAuthorizationInformation[] anOutputArray, 
		int aReceiveWaitTime, 
		int aMaximumTimeOuts, 
		int anId)
	{
		utility = aUtility;
		dataCenters = aDataCenters;
		TIRKSJXHelper = aHelper;
		bisName = aBisName;
		company = aCompany;
		outputArray = anOutputArray;
		receiveWaitTime = aReceiveWaitTime;
		maximumTimeOuts = aMaximumTimeOuts;
		id = anId;
		TIRKSJXName = (String ) aProperties.get("TIRKSJX_SERVICE_NAME");
		TIRKSJXNameVersion = TIRKSJXName + "-" +(String ) aProperties.get("TIRKSJX_SERVICE_VERSION");
	}
	
	/**
	 * Get the helper.
	 * Creation date: (6/27/01 12:04:22 PM)
	 * @return com.sbc.gwsvcs.service.circuitprovisioning.CircuitProvisioningHelper
	 */
	public com.sbc.gwsvcs.service.circuitprovisioning.CircuitProvisioningHelper getHelper() 
	{
		return helper;
	}
	
	/**
	 * Get the helper.
	 * Creation date: (6/27/01 12:04:22 PM)
	 * @return com.sbc.gwsvcs.service.circuitprovisioning.CircuitProvisioningHelper
	 */
	public TIRKSJX getTIRKSJXHelper() 
	{
		return TIRKSJXHelper;
	}
	
	/**
	 * Get the output index.
	 * Creation date: (6/25/01 6:11:00 PM)
	 * @return int
	 */
	public int getOutputIndex() 
	{
		return outputIndex;
	}
	
	/**
	 * Get the reconnect flag.
	 * Creation date: (6/25/01 6:01:11 PM)
	 * @return int
	 */
	public boolean getReconnect() 
	{
		return reconnect;
	}
	
	/**
	 * Get the state.
	 * Creation date: (6/25/01 6:01:11 PM)
	 * @return int
	 */
	public int getState() 
	{
		return state;
	}
	
	/**
	 * Set the output array element number
	 * Creation date: (6/25/01 1:12:56 PM)
	 * 
	 * @param anOutputIndex
	 */
	public void outputIndex(int anOutputIndex) 
	{
		outputIndex = anOutputIndex;
	}
	
	/**
	 * Manages the actual send/receive mechanism.
	 * @return boolean
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
	 */
	public boolean process(
		BisContext aContext, 
		java.util.Hashtable aProperty)
		throws 	
			AccessDenied, 
			BusinessViolation, 
			InvalidData,
			NotImplemented, 
			ObjectNotFound, 
			SystemFailure, 
			DataNotFound
	{
		utility.log(LogEventId.DEBUG_LEVEL_1, processMethodName);
	
		// The worker is rotating through 3 states - new transaction, send and receive.
		// A single send is followed by multiple receives until success or too many attempts.
		// On a multi-IMS region the receive cycle is followed by another send on the next region
		// until the regions are exhausted. The transaction is completed by returning true or
		// throwing an exception. Control is given up by returning false (the process() method will
		// be called again).
	
		// New transaction state
		if (state == this.STATE_NEW_TRAN)
		{
			state = this.STATE_SEND;
			exceptionArray.clear();
			triedARegion = false;
			hadATimeout = false;
			hadADowntime = false;
			if (input.getImsRegion().trim().length() < 1) 
				regions = new IMSRegions(imsRegions);
			else 
				regions = new IMSRegions(input.getImsRegion().trim());
			utility.log(LogEventId.INFO_LEVEL_2, "Using IMS regions<" + regions.getListOfRegions() + ">");
					
			// Build the Work Authorization request
			request.clo = input.getClo();
			request.cac = input.getCac();
			request.ckt = (input.getCkt() == null ? "" : input.getCkt().asOssString());
	
			utility.log(
						LogEventId.REMOTE_CALL,
						CircuitProvisioningAccess.name,
						CircuitProvisioningName,
						CircuitProvisioningName,
						"waQInput()");
	
		}
	
		try 
		{
			// Send state. Loop through the regions, trying each in turn
			if (state == this.STATE_SEND)
			{
				if ((request.ims_region = regions.getNextRegion()) != null)
				{
					triedARegion = true;
					numberOfTimeOuts = 0;
					utility.log(LogEventId.INFO_LEVEL_2, "CircuitProvisioning Request[" +
						"ims_region<" + request.ims_region +
						"> clo<" + request.clo +
						"> cac<" + request.cac +
						"> ckt<" + request.ckt +
						">]");
					helper.waQInput(request);
					state = this.STATE_RECEIVE;
					return false;
				}
				// From here processing goes below the catch blocks
			}
			
			// Receive state
			else
			if (state == this.STATE_RECEIVE)
			{
				ArrayList waRespSet = helper.waQInput(receiveWaitTime, helper.RECEIVE_ALL_MESSAGES); // Staying connected - need to receive all messages
			
				// Analyse the result
				com.sbc.gwsvcs.access.vicuna.EventResultPair waResp = null;
				if (waRespSet.size() > 0)  // Don't need a loop - only want first screen
				{
					waResp = (com.sbc.gwsvcs.access.vicuna.EventResultPair)waRespSet.get(0);
					utility.log(LogEventId.INFO_LEVEL_2, "Received event " + waResp.getEventNbr());
			
					switch(waResp.getEventNbr())
					{
						case CircuitProvisioningAccess.TIRKS_WA_QO_NBR:
						{
							WaOutput aWaOutput = (WaOutput)waResp.getTheObject();
							utility.log(LogEventId.INFO_LEVEL_2,
								"acna<" + aWaOutput.acna + "> btn<" + aWaOutput.btn + "> ccna<" + aWaOutput.ccna + 
								"> ckr<" + aWaOutput.ckr + 
								"> ckt<" + aWaOutput.ckt + "> clo<" + aWaOutput.clo + "> loca<" +	aWaOutput.loca +
								"> locz<" + aWaOutput.locz + "> ord<" + aWaOutput.ord + "> mcn<" + aWaOutput.mcn +
								"> pon<" +	aWaOutput.pon + ">");
							
							outputArray[outputIndex] = new WorkAuthorizationInformation();
							outputArray[outputIndex].setAcna(aWaOutput.acna.trim());
							outputArray[outputIndex].setBtn(aWaOutput.btn.trim());
							outputArray[outputIndex].setCcna(aWaOutput.ccna.trim());
							outputArray[outputIndex].setCkr(aWaOutput.ckr.trim());
							outputArray[outputIndex].setCkt(aWaOutput.ckt.trim());
							outputArray[outputIndex].setClo(aWaOutput.clo.trim());
							outputArray[outputIndex].setLoca(aWaOutput.loca.trim());
							outputArray[outputIndex].setLocz(aWaOutput.locz.trim());
							outputArray[outputIndex].setOrd(aWaOutput.ord.trim());
							outputArray[outputIndex].setPon(aWaOutput.pon.trim());
							outputArray[outputIndex].setMcn(aWaOutput.mcn.trim());
							outputArray[outputIndex].setIMSRegion(request.ims_region);
							utility.log(
									LogEventId.REMOTE_RETURN,
									CircuitProvisioningAccess.name,
									CircuitProvisioningName,
									CircuitProvisioningName,
									"waQInput()");
									
							return true;
						}
					
						default:
							utility.log(
									LogEventId.REMOTE_RETURN,
									CircuitProvisioningAccess.name,
									CircuitProvisioningName,
									CircuitProvisioningName,
									"waQInput()");
							utility.throwException(
						  		ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER,
						  		"CircuitProvisioningHelper failure: " + processMethodName +
						  		": Unexpected return from CircuitProvisioningHelper:waQInput(): "
						  		+ waResp.getEventNbr(), bisName, Severity.UnRecoverable);
					}
				}
			}
			else
			// Invalid state
			{
				utility.log(
							LogEventId.REMOTE_RETURN,
							CircuitProvisioningAccess.name,
							CircuitProvisioningName,
							CircuitProvisioningName,
							"waQInput()");
							
				utility.throwException(
			  		ExceptionCode.ERR_RM_TIRKS_WORKER_INVALID_STATE,
			  		"Internal error - invalid state in " + processMethodName + ": " + state,
			  		bisName, Severity.UnRecoverable);
			}
		}
		catch (CircuitProvisioningException e)
		{
			// Look for a downstream time out
	
	
	 		ExceptionBuilderResult ebr;
			ebr = ExceptionBuilder.parseException(aContext,
											(String) aProperty.get("EXCEPTION_BUILDER_TIRKS_RULE_FILE"),
											null,
											e.getMessage(),
											e.getMessage(),
											false,
											ExceptionBuilderRule.NO_DEFAULT,
											null,null,utility,null,null,null);
			
			if (ebr.getBusinessRule() == 1) 
			{   // Had a timeout
				
				exceptionArray.add(ExceptionCode.ERR_RM_TIMEOUT + " TIRKS timed out. " + e.getExceptionCode() + " "
				+ e.getMessage());
				hadATimeout = true;
	
			}
			
			else if (ebr.getBusinessRule() == 2) 
			{   // Had a downtime
	
				exceptionArray.add(ExceptionCode.ERR_RM_SYSTEM_OFFLINE +
				" TIRKS down due to regularly scheduled maintenance. " + e.getExceptionCode() + " "
				+ e.getMessage());
				hadADowntime = true;
	
			}
			else
				exceptionArray.add(e.getExceptionCode() + " " + e.getMessage());
			state = this.STATE_SEND; // Try the next IMS region
			return false;
		}
		catch (ServiceTimeoutException e)
		{
			numberOfTimeOuts++;
			hadATimeout = true;
			utility.log(LogEventId.INFO_LEVEL_2, "numberOfTimeOuts<" + numberOfTimeOuts + ">");
			if (numberOfTimeOuts >= maximumTimeOuts)
			{
				// Have to disconnect/reconnect to flush any messages
				setReconnect(true); 
				try 
				{
					utility.throwException(
						ExceptionCode.ERR_RM_TIMEOUT,
						"TIRKS timed out. Timed out on receive (attempts: " + numberOfTimeOuts + ")",	// MATCH text below
						"CircuitProvisioningHelper", Severity.UnRecoverable);
				}
				catch (Exception ex)
				{
					exceptionArray.add(ExceptionCode.ERR_RM_TIMEOUT + " " +								// MATCH text above
						"TIRKS timed out. Timed out on receive (attempts: " + numberOfTimeOuts + ")");
					state = this.STATE_SEND; // Try another IMS region
					return false;
				}
			}
			state = this.STATE_RECEIVE; // Try another receive
			return false; 
		}
		catch (ServiceException e)
		{
			exceptionArray.add(e.getExceptionCode() + " " + e.getMessage());
			state = this.STATE_SEND;  // Try another IMS region
			setReconnect(true); // Force a disconnect/reconnect
			return false;
		}
	
		// By this point all the possible IMS regions have been tried
		
		// Make sure at least one region was tried
		if (! triedARegion)
		{
			String errorMessage = null;
			if (input.getImsRegion().trim().length() < 1)
				errorMessage = "Could not find usable TIRKS IMS region for state code: " + company.getState().getCode() +
					" IMSRegions: <" + imsRegions + ">";
			else
				errorMessage = "Could not find usable TIRKS IMS region within specified region: " +
					"input.ImsRegion: <" + input.getImsRegion() + ">";
			utility.throwException(ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAP_UNUSABLE,
				errorMessage, processMethodName, Severity.UnRecoverable);
		}
						
		// If all the errors are not found issue a 'Not Found' error
		ListIterator it = exceptionArray.listIterator();
		boolean allNotFound = true;
		String message;
		StringBuffer allMessages = new StringBuffer();
		while (it.hasNext())
		{
			message = (String)it.next();
			
			if (allMessages.length() < 1)
				allMessages.append(message);
			else
				allMessages.append("/" + message);
			message = message.toUpperCase();
	
	
	 		ExceptionBuilderResult ebr;
			ebr = ExceptionBuilder.parseException(aContext,
											(String) aProperty.get("EXCEPTION_BUILDER_TIRKS_RULE_FILE"),
											null,
											message,
											message,
											false,
											ExceptionBuilderRule.NO_DEFAULT,
											null,null,utility,null,null,null);
			
			// If a "circuit not found message" is not found for all messages
			// i.e. all regions
			// then assume it is a system failure of some kind....
			
			if (ebr.getBusinessRule() != 3) 
			{
				allNotFound = false;
			}
				
			// Keep looping to append all the error messages		
		}
		utility.log(
					LogEventId.REMOTE_RETURN,
					CircuitProvisioningAccess.name,
					CircuitProvisioningName,
					CircuitProvisioningName,
					"waQInput()");
		
		
		utility.log(LogEventId.DEBUG_LEVEL_2, "allNotFound<" + allNotFound + ">");
		if (allNotFound)
			return true;
							
		utility.throwException(
			(hadATimeout == true ? ExceptionCode.ERR_RM_TIMEOUT :
				(hadADowntime == true ? ExceptionCode.ERR_RM_SYSTEM_OFFLINE :
					ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER)),
			allMessages.toString(),
			"CircuitProvisioningHelper", Severity.UnRecoverable);
	
		return true; // Not reached
	}
	
	/**
	 * Manages the actual send/receive mechanism.
	 * 
	 * @param aContext
	 * @param aProperty
	 * @return boolean
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
	 */
	public boolean processTIRKSJX(
		BisContext aContext, 
		java.util.Hashtable aProperty)
		throws 	
			AccessDenied, 
			BusinessViolation, 
			InvalidData,
			NotImplemented, 
			ObjectNotFound, 
			SystemFailure, 
			DataNotFound
	{
		utility.log(LogEventId.DEBUG_LEVEL_1, processMethodName);
	
		// The worker is rotating through 3 states - new transaction, send and receive.
		// A single send is followed by multiple receives until success or too many attempts.
		// On a multi-IMS region the receive cycle is followed by another send on the next region
		// until the regions are exhausted. The transaction is completed by returning true or
		// throwing an exception. Control is given up by returning false (the process() method will
		// be called again).
	
		// New transaction state
		if (state == this.STATE_NEW_TRAN)
		{
			state = this.STATE_SEND;
			exceptionArray.clear();
			triedADataCenter = false;
			hadATimeout = false;
			hadADowntime = false;
			
			if (input.getDataCenter().trim().length() < 1) 
				centers = new DataCenters(dataCenters);
			else 
				centers = new DataCenters(input.getDataCenter().trim());
			utility.log(LogEventId.INFO_LEVEL_2, "Using Data Center <" + centers.getListOfDataCenters() + ">");
					
			// Build the Work Authorization request
			requestTIRKSJX.clo = input.getClo();
			requestTIRKSJX.cac = input.getCac();
			requestTIRKSJX.ckt = (input.getCkt() == null ? "" : input.getCkt().asOssString());
	
			utility.log(
						LogEventId.REMOTE_CALL,
						TIRKSJXName,
						TIRKSJXNameVersion,
						TIRKSJXNameVersion,
						"waQInput()");
	
		}
	
		try 
		{
			// Send state. Loop through the regions, trying each in turn
			if (state == this.STATE_SEND)
			{
				if ((requestTIRKSJX.data_center = centers.getNextDataCenter()) != null)
				{
					triedADataCenter = true;
					numberOfTimeOuts = 0;
					utility.log(LogEventId.INFO_LEVEL_2, "TIRKSJX Request[" +
						"datacenter<" + requestTIRKSJX.data_center +
						"> clo<" + requestTIRKSJX.clo +
						"> cac<" + requestTIRKSJX.cac +
						"> ckt<" + requestTIRKSJX.ckt +
						">]");
					
					com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.impl.JXAPIImpl openSessionRequest = TIRKSJXHelper.buildTIRKSJxOpenSessionRequest(requestTIRKSJX.data_center);
					openSessionResponse = TIRKSJXHelper.sendOpenSessionRequest(openSessionRequest);
					
					OpenSessionResponseHelper openSessionResponseHelper =  TIRKSJXHelper.parseOpenSessionResponse(openSessionResponse);
					sessionID = openSessionResponseHelper.getSessionID();
					
					com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl waQueryRequest 
					= TIRKSJXHelper.buildWAQueryRequest(	sessionID, 
													Character.toString(requestTIRKSJX.ckt.charAt(0)), 
													requestTIRKSJX.ckt.substring(2), 
													requestTIRKSJX.clo, 
													requestTIRKSJX.cac,
													ACTION_F1);
					waQueryResponse = TIRKSJXHelper.sendActionOnScreenRequest(waQueryRequest);
				
					state = this.STATE_RECEIVE;
					return false;
				}
				// From here processing goes below the catch blocks
			}
			
			// Receive state
			else
			if (state == this.STATE_RECEIVE)
			{
				if(TIRKSJXHelper.checkResponseHasError(waQueryResponse))
				{
					errorResponseHelper = TIRKSJXHelper.parseErrorOnScreenResponse(waQueryResponse);
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
					  		ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					  		"Received error from TIRKSJX " + errorType + ": " + errorMessage,
					  		bisName, Severity.UnRecoverable);
				}
				else
				{
					waResponseHelper = TIRKSJXHelper.parseActionOnScreenResponse(waQueryResponse);
					
					Field [] waRespFields = waResponseHelper.getAFields();
				
					if (waRespFields != null && waRespFields.length > 0)  // Don't need a loop - only want first screen
					{					
						String acna = null;
						String btn0 = null;
						String btn1 = null;
						String btn2 = null;
						String ccna = null;
						String ckr = null;
						String ckt_fmt = null;
						String ckt = null;
						String clo = null;
						String loca = null;
						String locz = null;
						String ord = null;
						String pon = null;
						String mcn = null;
						String systemMessage = null;
						String systemMessageCode = null;
						String systemMessageDescription = null;
						
						for (int i=0; i < waRespFields.length; i++)
						{
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_ACNA))
							{
								acna = waRespFields[i].getFieldValue().trim();
							}
							if ((waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_BTN))
									&& waRespFields[i].getFieldInstance() == Integer.parseInt(INSTANCE_0))
							{
								btn0 = waRespFields[i].getFieldValue().trim();
							}
							if ((waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_BTN))
									&& waRespFields[i].getFieldInstance() == Integer.parseInt(INSTANCE_1))
							{
								btn1 = waRespFields[i].getFieldValue().trim();
							}
							if ((waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_BTN))
									&& waRespFields[i].getFieldInstance() == Integer.parseInt(INSTANCE_2))
							{
								btn2 = waRespFields[i].getFieldValue().trim();
							}
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CCNA))
							{
								ccna = waRespFields[i].getFieldValue().trim();
							}
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CKR))
							{
								ckr = waRespFields[i].getFieldValue().trim();
							}
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CKT)
									&& waRespFields[i].getFieldInstance() == Integer.parseInt(INSTANCE_0))
							{
								ckt_fmt = waRespFields[i].getFieldValue().trim();
							}
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CKT)
									&& waRespFields[i].getFieldInstance() == Integer.parseInt(INSTANCE_1))
							{
								ckt = waRespFields[i].getFieldValue().trim();
							}
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_CLO))
							{
								clo = waRespFields[i].getFieldValue().trim();
							}
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_LOCA))
							{
								loca = waRespFields[i].getFieldValue().trim();
							}
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_LOCZ))
							{
								locz = waRespFields[i].getFieldValue().trim();
							}
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_ORD))
							{
								ord = waRespFields[i].getFieldValue().trim();
							}
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_PON))
							{
								pon = waRespFields[i].getFieldValue().trim();
							}
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_MCN))
							{
								mcn = waRespFields[i].getFieldValue().trim();
							}
							if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_SYSMSG))
							{
								systemMessage = waRespFields[i].getFieldValue().trim();
								systemMessageCode = systemMessage.substring(0, 7);
								systemMessageDescription = systemMessage.substring(8, systemMessage.length());
							}
						}
							
						if(systemMessageCode != null && !systemMessageCode.equalsIgnoreCase(WA_SUCCESS))
						{							
							throw new CircuitProvisioningException(systemMessageCode,systemMessageDescription);							
						}
						else
						{
							utility.log(
									LogEventId.INFO_LEVEL_2,
									"Received message " + systemMessage);
							
							String btnFinal = (btn0.concat(btn1)).concat(btn2);
							
							utility.log(LogEventId.INFO_LEVEL_2,
								"acna<" + acna + "> btn<" + btnFinal + "> ccna<" + ccna + 
								"> ckr<" + ckr + 
								"> ckt<" + ckt_fmt + " " + ckt + "> clo<" + clo + "> loca<" + loca +
								"> locz<" + locz + "> ord<" + ord + "> mcn<" + mcn +
								"> pon<" +	pon + ">");
							
							outputArray[outputIndex] = new WorkAuthorizationInformation();
							outputArray[outputIndex].setAcna(acna);
							outputArray[outputIndex].setBtn(btnFinal);
							outputArray[outputIndex].setCcna(ccna);
							outputArray[outputIndex].setCkr(ckr);
							outputArray[outputIndex].setCkt(ckt);
							outputArray[outputIndex].setClo(clo);
							outputArray[outputIndex].setLoca(loca);
							outputArray[outputIndex].setLocz(locz);
							outputArray[outputIndex].setOrd(ord);
							outputArray[outputIndex].setPon(pon);
							outputArray[outputIndex].setMcn(mcn);
							outputArray[outputIndex].setDataCenter(requestTIRKSJX.data_center);
							utility.log(
									LogEventId.REMOTE_RETURN,
									TIRKSJXName,
									TIRKSJXNameVersion,
									TIRKSJXNameVersion,
									"waQInput()");
									
							return true;
						}
					}
				}				
			}
			else
			// Invalid state
			{
				utility.log(
							LogEventId.REMOTE_RETURN,
							TIRKSJXName,
							TIRKSJXNameVersion,
							TIRKSJXNameVersion,
							"waQInput()");
							
				utility.throwException(
			  		ExceptionCode.ERR_RM_TIRKS_WORKER_INVALID_STATE,
			  		"Internal error - invalid state in " + processMethodName + ": " + state,
			  		bisName, Severity.UnRecoverable);
			}
		}
		catch(CircuitProvisioningException e)
		{
//			 Look for a downstream time out


	 		ExceptionBuilderResult ebr;
			ebr = ExceptionBuilder.parseException(aContext,
											(String) aProperty.get("EXCEPTION_BUILDER_TIRKS_RULE_FILE"),
											null,
											e.getMessage(),
											e.getMessage(),
											false,
											ExceptionBuilderRule.NO_DEFAULT,
											null,null,utility,null,null,null);
			
			if (ebr.getBusinessRule() == 1) {   // Had a timeout
				
				exceptionArray.add(ExceptionCode.ERR_RM_TIMEOUT + " TIRKS timed out. " + e.getExceptionCode() + " "
				+ e.getMessage());
				hadATimeout = true;

			}
			
			else if (ebr.getBusinessRule() == 2) {   // Had a downtime

				exceptionArray.add(ExceptionCode.ERR_RM_SYSTEM_OFFLINE +
				" TIRKS down due to regularly scheduled maintenance. " + e.getExceptionCode() + " "
				+ e.getMessage());
				hadADowntime = true;

			}
			else
				exceptionArray.add(e.getExceptionCode() + " " + e.getMessage());
			state = this.STATE_SEND; // Try the next IMS region
			return false;
		}
		catch (ServiceException e)
		{
			exceptionArray.add(e.getExceptionCode() + " " + e.getMessage());
			state = this.STATE_SEND;  // Try another IMS region
			setReconnect(true); // Force a disconnect/reconnect
			return false;
		}
		finally
		{
			if(sessionID != null)
			{
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl waRefreshRequest 
				= TIRKSJXHelper.buildWAQueryRequest(	sessionID, 
												"", 
												"", 
												"", 
												"",
												ACTION_F8);
				waRefreshResponse = TIRKSJXHelper.sendActionOnScreenRequest(waRefreshRequest);
				
				waRefreshResponseHelper = TIRKSJXHelper.parseActionOnScreenResponse(waRefreshResponse);
				
				Field [] waRespFields = waRefreshResponseHelper.getAFields();
				
				if (waRespFields != null && waRespFields.length > 0)  // Don't need a loop - only want first screen
				{
					String systemMessage = null;
					
					for (int i=0; i < waRespFields.length; i++)
					{
						if (waRespFields[i].getFieldName().equalsIgnoreCase(FIELD_SYSMSG))
						{
							systemMessage = waRespFields[i].getFieldValue().trim();
						}
					}
					utility.log(
							LogEventId.INFO_LEVEL_2,
							"WA Refresh Response " +
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
	
		// By this point all the possible IMS regions have been tried
		
		// Make sure at least one region was tried
		if (! triedADataCenter)
		{
			String errorMessage = null;
			if (input.getDataCenter().trim().length() < 1)
				errorMessage = "Could not find usable TIRKS Data Center for state code: " + company.getState().getCode() +
					" Data Center: <" + dataCenters + ">";
			else
				errorMessage = "Could not find usable TIRKS IMS region within specified region: " +
					"input.ImsRegion: <" + input.getDataCenter() + ">";
			utility.throwException(ExceptionCode.ERR_RM_TIRKS_IMS_REGION_MAP_UNUSABLE,
				errorMessage, processMethodName, Severity.UnRecoverable);
		}
						
		// If all the errors are not found issue a 'Not Found' error
		ListIterator it = exceptionArray.listIterator();
		boolean allNotFound = true;
		String message;
		StringBuffer allMessages = new StringBuffer();
		while (it.hasNext())
		{
			message = (String)it.next();
			
			if (allMessages.length() < 1)
				allMessages.append(message);
			else
				allMessages.append("/" + message);
			message = message.toUpperCase();
	
	
	 		ExceptionBuilderResult ebr;
			ebr = ExceptionBuilder.parseException(aContext,
											(String) aProperty.get("EXCEPTION_BUILDER_TIRKS_RULE_FILE"),
											null,
											message,
											message,
											false,
											ExceptionBuilderRule.NO_DEFAULT,
											null,null,utility,null,null,null);
			
			// If a "circuit not found message" is not found for all messages
			// i.e. all regions
			// then assume it is a system failure of some kind....
			
			if (ebr.getBusinessRule() != 3) {
				allNotFound = false;
			}
				
			// Keep looping to append all the error messages		
		}
		utility.log(
					LogEventId.REMOTE_RETURN,
					TIRKSJXName,
					TIRKSJXNameVersion,
					TIRKSJXNameVersion,
					"waQInput()");
		
		
		utility.log(LogEventId.DEBUG_LEVEL_2, "allNotFound<" + allNotFound + ">");
		if (allNotFound)
			return true;
							
		utility.throwException(
			(hadATimeout == true ? ExceptionCode.ERR_RM_TIMEOUT :
				(hadADowntime == true ? ExceptionCode.ERR_RM_SYSTEM_OFFLINE :
					ExceptionCode.ERR_RM_CIRCUITPROVISIONING_HELPER)),
			allMessages.toString(),
			"TIRKSJX", Severity.UnRecoverable);
	
		return true; // Not reached
	}
	
	/**
	 * Set the state to idle.
	 * Creation date: (6/25/01 6:01:11 PM)
	 */
	public void setIdleState() 
	{
		state = this.STATE_IDLE;
	}
	
	/**
	 * Set the input request data
	 * Creation date: (6/25/01 1:12:56 PM)
	 */
	public void setInput(WAInput anInput) 
	{
		input = anInput;
		state = this.STATE_NEW_TRAN;
	}
	/**
	 * Set the reconnect flag.
	 * Creation date: (6/25/01 6:01:11 PM)
	 * @return int
	 */
	public void setReconnect(boolean aReconnect) 
	{
		reconnect = aReconnect;
	}
}
