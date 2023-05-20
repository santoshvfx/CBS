// $Id: SendTNAssignmentOrder.java,v 1.29 2009/05/27 21:42:13 sl7683 Exp $
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
//# 05/15/2006 Jon Costa			  Initial build.
//# 08/31/2006 Doris Sunga			  Prep LS4: replaced import files com.sbc.eia.bis.BusinessInterface.rm.SOAC.~
//# 								  with com.~.rm.database.CvoipOrderRow, com.~.rm.database.CvoipOrderTable, 
//#                                   com.~.rm.database.CvoipRulesRow and com.~.rm.database.CvoipRulesTable
//# 01/25/2007 Jon Costa			  PR 19319197: additional logging output per EMAS request.
//# 01/29/2007 Jon Costa			  PR 19334305: Log output of aTNSourcePoolUpdateNotifier as null/true/false
//# 03/30/2007 Jon Costa			  CR TBD: Change exception type for HIPCS update failure.
//# 04/09/2007 Jon Costa			  PR19714766, QC DR63137: When a TN pair has a M action code and the TN's
//#									  are equal, ignore that pair (no action in SWITCH or SOAC).
//# 04/23/2007 Jon Costa			  Fixed nullPointer for NO TN(s) when CAN or PCN
//# 05/10/2007 Jon Costa			  CR13804: Change for OTN==TN and actionCode == M
//# 05/18/2007 Jon Costa			  PR 19945106: Get TN(s) from CVOIP_ORDER table for HIPCS SWITCH cancel request. 
//# 06/23/2008 Doris Sunga			  CR 19539: Added calling of UpdateOrderCorrectionSuffix() for Order_Correction_Suffix table maintenance
//# 05/27/2009 Julius Sembrano        DR 132453: STNAO - Added validation for aTelephoneNumberOrder.aTelephoneNumber for order not Completion or Cancel

package com.sbc.eia.bis.facades.rm.transactions.SendTNAssignmentOrder; 

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC;
import com.sbc.eia.bis.BusinessInterface.rm.SWITCH.TNSourcePool;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.common.utilities.BisDateUtil;
import com.sbc.eia.bis.rm.database.CvoipOrderRow;
import com.sbc.eia.bis.rm.database.CvoipOrderTable;
import com.sbc.eia.bis.rm.database.CvoipRulesRow;
import com.sbc.eia.bis.rm.database.CvoipRulesTable;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.database.OrderCorrectionSuffixRow;
import com.sbc.eia.bis.rm.database.OrderCorrectionSuffixTable;
import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.bis.rm.utilities.ValidateHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.bishelpers.LocationBisHelper;
import com.sbc.eia.idl.rm.SendTNAssignmentOrderReturn;
import com.sbc.eia.idl.rm.bishelpers.SendTNAssignmentOrderReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.OrderActionTypeValues;
import com.sbc.eia.idl.rm_ls_types.SubActionTypeValues;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrder;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPair;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberPortingStatus;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberPortingStatusOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.BooleanOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;

/**
 * @author jc1421
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SendTNAssignmentOrder extends TranBase
{
	private Utility aUtility = null;
	private Hashtable myProperties = null;
	private SOAC cacheSOAC = null;
	private TNSourcePool cacheTNSourcePool = null; // ---- HIPCS specific

	/**
	 * Constructor.
	 */
	public SendTNAssignmentOrder()
	{
		super();
	}

	/**
	 * Constructor.
	 * @param Hashtable
	 */
	public SendTNAssignmentOrder(Hashtable param)
	{
		super(param);
		aUtility = this;
		myProperties = getPROPERTIES();
	}

	/**
	 * Method execute.
	 * @param aContext
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aOrderNumber
	 * @param aOrderActionType
	 * @param aSubActionType
	 * @param aCompletionIndicator
	 * @param aServiceLocation
	 * @param aOriginalDueDate
	 * @param aSubsequentDueDate
	 * @param aApplicationDate
	 * @param aResendFlag
	 * @param aWireCenter
	 * @param aRateCenter
	 * @param aTelephoneNumberOrderPairs
	 * @param aProperties
	 * @param aLogger
	 * @return SendTNAssignmentOrderReturn
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 */
	public SendTNAssignmentOrderReturn execute(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aOrderNumber,
		String aOrderActionType,
		StringOpt aSubActionType,
		BooleanOpt aCompletionIndicator,
		Location aServiceLocation,
		EiaDate aOriginalDueDate,
		EiaDateOpt aSubsequentDueDate,
		EiaDate aApplicationDate,
		BooleanOpt aResendFlag,
		StringOpt aWireCenter,
		StringOpt aRateCenter,
		TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs,
		ObjectPropertySeqOpt aProperties,
		Logger aLogger)
		throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented
	{
		String myMethodNameName = "SendTNAssignmentOrder.execute()";
		myLogger = aLogger;
		log(LOG_DEBUG_LEVEL_1, ">" + myMethodNameName);
		callerContext = aContext;

		SendTNAssignmentOrderReturn sTNA_ackObj = null;

		try
		{
			String propertyValue = null;
			String aBisName = myProperties.get("BIS_NAME").toString();
			ObjectPropertyManager aContextOPM = new ObjectPropertyManager(aContext.aProperties);

			// Log BisContext
			try
			{
				log(LOG_INPUT_DATA, "aContext=<" + (new BisContextBisHelper(aContext)).toString() + ">");
			}
			catch (Exception e)
			{
				log(LOG_INPUT_DATA, "aContext=<null>");
			}

			// Validate BisContext
			ValidateHelper.validateBisContext(aUtility, aContextOPM, aBisName);

			//validate JMS_CORRELATION_ID
			if ((propertyValue = aContextOPM.getValue(BisContextProperty.JMS_CORRELATION_ID)) == null || propertyValue.trim().length() < 1)
				aUtility.throwException(
					ExceptionCode.ERR_RM_MISSING_JMS_CORRELATION_ID,
					formatInvalidData(BisContext.class, "aContext.aProperties[" + BisContextProperty.JMS_CORRELATION_ID + "]"),
					aBisName,
					Severity.UnRecoverable);

			//validate EMBUS_MESSAGE_TAG 
			if ((propertyValue = aContextOPM.getValue(BisContextProperty.EMBUS_MESSAGE_TAG)) == null || propertyValue.trim().length() < 1)
				aUtility.throwException(
					ExceptionCode.ERR_RM_MISSING_EMBUS_MESSAGE_TAG,
					formatInvalidData(BisContext.class, "aContext.aProperties[" + BisContextProperty.EMBUS_MESSAGE_TAG + "]"),
					aBisName,
					Severity.UnRecoverable);

			validateClient(
				aContextOPM,
				(String) null,
				ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
				ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION);

			logInput(
				aSOACServiceOrderNumber,
				aSOACServiceOrderCorrectionSuffix,
				aOrderNumber,
				aOrderActionType,
				aSubActionType,
				aCompletionIndicator,
				aServiceLocation,
				aOriginalDueDate,
				aSubsequentDueDate,
				aApplicationDate,
				aResendFlag,
				aWireCenter,
				aRateCenter,
				aTelephoneNumberOrderPairs,
				aProperties);

			validateInput(
				aSOACServiceOrderNumber,
				aSOACServiceOrderCorrectionSuffix,
				aOrderNumber,
				aOrderActionType,
				aSubActionType,
				aCompletionIndicator,
				aServiceLocation,
				aOriginalDueDate,
				aSubsequentDueDate,
				aApplicationDate,
				aResendFlag,
				aWireCenter,
				aRateCenter,
				aTelephoneNumberOrderPairs,
				aProperties);

			boolean portingInfoUpdated = true; // ---- HIPCS specific
			CvoipOrderRow[] aCvoipOrderRows = null;
			CvoipRulesRow[] aCvoipRulesRows = null;   
					
			if (cacheTNSourcePool == null)
				cacheTNSourcePool = new TNSourcePool(aUtility, myProperties);	

			// check if any TN is HIPCS TN 
			if (cacheTNSourcePool.checkHIPCSTNExist(aContext,aTelephoneNumberOrderPairs))
			{	
			   try
			   {
				   // search this Order in CVOIP_ORDER table 
				   aCvoipOrderRows =
					   new CvoipOrderTable().retrieveByOrder(aSOACServiceOrderNumber, myProperties, (com.sbc.bccs.utilities.Logger) this);
			   }
			   catch (SQLException sqle)
			   {
				   // throw System Failure for missing table, field, or permission 
				   aUtility.throwException(
						ExceptionCode.ERR_RM_INEXS_SQL_EXCEPTION,
						sqle.getMessage(),
						aBisName,
						Severity.UnRecoverable);							
			   }
			}
			if (!OptHelper.isStringOptEmpty(aSubActionType)
				&& aSubActionType.theValue().equalsIgnoreCase(SubActionTypeValues.SUB_ACTION_CANCEL))
			{
				if (aCvoipOrderRows != null && aCvoipOrderRows.length > 0)
				{
					// We have a cancel order with HIPCS TN(s) in the data base
					aTelephoneNumberOrderPairs = populateTNOP(aCvoipOrderRows);
				}
			}

			try
			{
				String subAction = null;
				try
				{
					subAction = aSubActionType.theValue(); // Optional 
				}
				catch (Exception e)
				{}
				// check if any TN is HIPCS TN 
				if (cacheTNSourcePool.checkHIPCSTNExist(aContext,aTelephoneNumberOrderPairs))
				{
				    aCvoipRulesRows =
					     new CvoipRulesTable().retrieveByOrder(
					    		 aOrderActionType,
					    		 subAction,
					    		 (aCvoipOrderRows == null ? "FALSE" : "TRUE"),
					    		 myProperties,
					    		 (com.sbc.bccs.utilities.Logger) this);
				}    
			}
			catch (SQLException sqle)
			{		
				// It's a System Failure when file does not exist
				aUtility.throwException(
						ExceptionCode.ERR_RM_INEXS_SQL_EXCEPTION,
						sqle.getMessage(),
						aBisName,
						Severity.UnRecoverable);			
			}			
			catch (Exception e)
			{
				// We must have a rules match, per HLD this is a BusinessViolation.
				String errDesc = "Unable to determine CVOIP rules data: " + e.getMessage();


				aUtility.throwException(
					ExceptionCode.ERR_RM_NO_MATCHING_RULE,
					errDesc,
					(String) myProperties.get("BIS_NAME").toString(),
					Severity.UnRecoverable);
			}

			// ----Begin---- HIPCS specific
			portingInfoUpdated =
				cacheTNSourcePool.updatePortingInfo(
					aContext,
					aSOACServiceOrderNumber,
					aOriginalDueDate,
					aTelephoneNumberOrderPairs,
					aCvoipOrderRows,
					aCvoipRulesRows);
			// ----End---- HIPCS specific

			ExceptionDataOpt aStatus = new ExceptionDataOpt();
			sTNA_ackObj =
				new SendTNAssignmentOrderReturn(
					aContext,
					(StringOpt) IDLUtil.toOpt((String) aSOACServiceOrderNumber),
					(StringOpt) IDLUtil.toOpt((String) aSOACServiceOrderCorrectionSuffix),
					aTelephoneNumberOrderPairs,
					aStatus,
					aProperties);

			if (portingInfoUpdated == true)
			{
				if (cacheSOAC == null)
					cacheSOAC = new SOAC(getPROPERTIES(), aUtility);

				cacheSOAC.SendTNAssignmentOrder(
					aContext,
					aSOACServiceOrderNumber,
					aSOACServiceOrderCorrectionSuffix,
					aOrderNumber,
					new ObjectPropertyManager(aProperties.theValue()).getValue("OmsOrderActionId").trim(),
					aOrderActionType,
					aSubActionType,
					aCompletionIndicator,
					aServiceLocation,
					aOriginalDueDate,
					aSubsequentDueDate,
					aApplicationDate,
					aResendFlag,
					aWireCenter,
					aRateCenter,
					aTelephoneNumberOrderPairs,
					aProperties,
					aCvoipOrderRows,
					aCvoipRulesRows);

				sTNA_ackObj.aStatus._default();

				// Order_Correction_Suffix table maintenance
				try
				{
				  OrderCorrectionSuffixTable aOrderCorrectionSuffixTable = new OrderCorrectionSuffixTable();	
				  aOrderCorrectionSuffixTable.UpdateOrderCorrectionSuffix(aContext,
						  						aSOACServiceOrderNumber,
						  						aSOACServiceOrderCorrectionSuffix,
						  						myProperties, 
						  						aUtility);
				} catch (Exception e) {
					// any exception error is okay to ignore				
				} 				
			}
			else
			{
				// ----Begin---- HIPCS specific
				// Porting update failure, acknowledgement is still returned but the ExceptionDataOpt aStatus
				// is populated with a Business Violation and the TN list, each TN with it's specific exception,
				// are returned for the client to review for a resend.
				String ErrText = "HIPCS processing failure, unable to continue. Refer to TN exception data for details.";
				log(LOG_INFO_LEVEL_1, ErrText);

				SeverityOpt aSeverity = new SeverityOpt();
				aSeverity.theValue(Severity.UnRecoverable);

				sTNA_ackObj.aStatus.theValue(
					new ExceptionData(
						ExceptionCode.ERR_RM_CVOIP_BUSINESS_VIOLATION,
						ErrText,
						(StringOpt) IDLUtil.toOpt((String) myProperties.get("BIS_NAME").toString()),
						aSeverity));
				// ----End---- HIPCS specific
			}
		}
		finally
		{
			log(LOG_DEBUG_LEVEL_1, "<" + myMethodNameName);
		}

		log(LOG_OUTPUT_DATA, "OUTPUT DATA: " + (new SendTNAssignmentOrderReturnBisHelper(sTNA_ackObj)).toString());
		return sTNA_ackObj;
	}
	
	/**
	 * Method populateTNOP.
	 * @param aCvoipOrderRows
	 * @return TelephoneNumberOrderPairSeqOpt
	 */
	private TelephoneNumberOrderPairSeqOpt populateTNOP(CvoipOrderRow[] aCvoipOrderRows)
	{
		TelephoneNumberOrderPairSeqOpt myTNPairs = new TelephoneNumberOrderPairSeqOpt();
		ArrayList aList = new ArrayList();
		TelephoneNumberOrder aTelephoneNumberOrder = null;
		TelephoneNumberOrderOpt aOldTelephoneNumberOrder = null;
		
		boolean tnsExists = false;

		String myMethodNameName = "SendTNAssignmentOrder.populateTNOP()";
		log(LOG_INFO_LEVEL_1, ">" + myMethodNameName);

		myTNPairs.__default();

		if (aCvoipOrderRows != null)
		{
			for (int i = 0; i < aCvoipOrderRows.length; i++)
			{
				aOldTelephoneNumberOrder = new TelephoneNumberOrderOpt();
				aOldTelephoneNumberOrder._default();

				try
				{
					if (aCvoipOrderRows[i].getTN() != null && !aCvoipOrderRows[i].getTN().equals(""))
					{
						aTelephoneNumberOrder = new TelephoneNumberOrder();
						aTelephoneNumberOrder.aTelephoneNumberType = new StringOpt();
						aTelephoneNumberOrder.aTelephoneNumberType.theValue("CVOIP");
						
						aTelephoneNumberOrder.aTelephoneNumber = new StringOpt();
						aTelephoneNumberOrder.aTelephoneNumber.theValue(aCvoipOrderRows[i].getTN());
						
						aTelephoneNumberOrder.aRequesterId = new StringOpt();
						aTelephoneNumberOrder.aRequesterId.theValue("OMS");
						
						aTelephoneNumberOrder.aActivityIndicator = new StringOpt();
						aTelephoneNumberOrder.aActivityIndicator.__default();
						
						aTelephoneNumberOrder.aActionInd = "";//null;
						
						aTelephoneNumberOrder.aTNSourcePool = new StringOpt();
						aTelephoneNumberOrder.aTNSourcePool.theValue("HIPCS");
						
						aTelephoneNumberOrder.aTNSourcePoolUpdateNotifier = new BooleanOpt();
						aTelephoneNumberOrder.aTNSourcePoolUpdateNotifier.__default();
						
						aTelephoneNumberOrder.aError = new ExceptionDataOpt();
						aTelephoneNumberOrder.aError._default();
						
						aTelephoneNumberOrder.aTelephoneNumberPortingStatus = new TelephoneNumberPortingStatusOpt();
						aTelephoneNumberOrder.aTelephoneNumberPortingStatus.__default();
						
						aList.add(new TelephoneNumberOrderPair(aOldTelephoneNumberOrder, aTelephoneNumberOrder));
						tnsExists = true;
					}
				}
				catch (Exception e)
				{}
			} // for loop

			if (tnsExists)
			{
				myTNPairs =
					(TelephoneNumberOrderPairSeqOpt) IDLUtil.toOpt(
						TelephoneNumberOrderPairSeqOpt.class,
						(TelephoneNumberOrderPair[]) aList.toArray(new TelephoneNumberOrderPair[aList.size()]));
				TNOPLogger(myTNPairs);
			}
		}
		
		log(LOG_INFO_LEVEL_1, "<" + myMethodNameName);
		return myTNPairs;
	}

	/**
	 * Method logInput.
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aOrderNumber
	 * @param aOrderActionType
	 * @param aSubActionType
	 * @param aCompletionIndicator
	 * @param aServiceLocation
	 * @param aOriginalDueDate
	 * @param aSubsequentDueDate
	 * @param aApplicationDate
	 * @param aResendFlag
	 * @param aWireCenter
	 * @param aRateCenter
	 * @param aTelephoneNumberOrderPairs
	 * @param aProperties
	 */
	protected void logInput(
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aOrderNumber,
		String aOrderActionType,
		StringOpt aSubActionType,
		BooleanOpt aCompletionIndicator,
		Location aServiceLocation,
		EiaDate aOriginalDueDate,
		EiaDateOpt aSubsequentDueDate,
		EiaDate aApplicationDate,
		BooleanOpt aResendFlag,
		StringOpt aWireCenter,
		StringOpt aRateCenter,
		TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs,
		ObjectPropertySeqOpt aProperties)
	{
		String myMethodNameName = "SendTNAssignmentOrder.logInput()";
		log(LOG_INFO_LEVEL_1, ">" + myMethodNameName);

		log(LOG_INPUT_DATA, "aSOACServiceOrderNumber<" + aSOACServiceOrderNumber + ">");
		log(LOG_INPUT_DATA, "aSOACServiceOrderCorrectionSuffix<" + aSOACServiceOrderCorrectionSuffix + ">");
		log(LOG_INPUT_DATA, "aOrderNumber<" + aOrderNumber + ">");
		log(LOG_INPUT_DATA, "aOrderActionType<" + aOrderActionType + ">");
		log(LOG_INPUT_DATA, "aSubActionType<" + printOpt(aSubActionType) + ">");

		try
		{
			log(LOG_INPUT_DATA, "aCompletionIndicator<" + (new BooleanOptBisHelper(aCompletionIndicator)).toString() + ">");
		}
		catch (Exception e)
		{
			log(LOG_INPUT_DATA, "aCompletionIndicator<null>");
		}

		try
		{
			log(LOG_INPUT_DATA, "aServiceLocation<" + (new LocationBisHelper(aServiceLocation)).toString() + ">");
		}
		catch (Exception e)
		{
			log(LOG_INPUT_DATA, "aServiceLocation<null>");
		}

		try
		{
			log(LOG_INPUT_DATA, "aOriginalDueDate<" + (new EiaDateBisHelper(aOriginalDueDate)).toString() + ">");
		}
		catch (Exception e)
		{
			log(LOG_INPUT_DATA, "aOriginalDueDate<null>");
		}

		try
		{
			log(LOG_INPUT_DATA, "aSubsequentDueDate<" + (new EiaDateOptBisHelper(aSubsequentDueDate)).toString() + ">");
		}
		catch (Exception e)
		{
			log(LOG_INPUT_DATA, "aSubsequentDueDate<null>");
		}

		try
		{
			log(LOG_INPUT_DATA, "aApplicationDate<" + (new EiaDateBisHelper(aApplicationDate)).toString() + ">");
		}
		catch (Exception e)
		{
			log(LOG_INPUT_DATA, "aApplicationDate<null>");
		}

		try
		{
			log(LOG_INPUT_DATA, "aResendFlag<" + (new BooleanOptBisHelper(aResendFlag)).toString() + ">");
		}
		catch (Exception e)
		{
			log(LOG_INPUT_DATA, "aResendFlag<null>");
		}

		log(LOG_INPUT_DATA, "aWireCenter<" + printOpt(aWireCenter) + ">");
		log(LOG_INPUT_DATA, "aRateCenter<" + printOpt(aRateCenter) + ">");
		
		TNOPLogger(aTelephoneNumberOrderPairs);

		try
		{
			log(LOG_INPUT_DATA, "aProperties<" + (new ObjectPropertySeqOptBisHelper(aProperties)).toString() + ">");
		}
		catch (Exception e)
		{
			log(LOG_INPUT_DATA, "aProperties<null>");
		}

		log(LOG_INFO_LEVEL_1, "<" + myMethodNameName);
	}

	/**
	 * Method TNOPLogger.
	 * @param aTelephoneNumberOrderPairs
	 */
	private void TNOPLogger(TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs)
	{
		try
		{
			log(LOG_INPUT_DATA, "aTelephoneNumberOrderPairs:");
			TelephoneNumberOrderPair[] aTNOPs = aTelephoneNumberOrderPairs.theValue();

			for (int i = 0; i < aTNOPs.length; i++)
			{
				try
				{
					log(
						LOG_INPUT_DATA,
						"["
							+ i
							+ "].aOldTelephoneNumberOrder<"
							+ printOpt(aTNOPs[i].aOldTelephoneNumberOrder.theValue().aTelephoneNumberType)
							+ "|"
							+ printOpt(aTNOPs[i].aOldTelephoneNumberOrder.theValue().aTelephoneNumber)
							+ "|"
							+ printOpt(aTNOPs[i].aOldTelephoneNumberOrder.theValue().aRequesterId)
							+ "|"
							+ printOpt(aTNOPs[i].aOldTelephoneNumberOrder.theValue().aActivityIndicator)
							+ "|"
							+ aTNOPs[i].aOldTelephoneNumberOrder.theValue().aActionInd
							+ "|"
							+ printOpt(aTNOPs[i].aOldTelephoneNumberOrder.theValue().aTNSourcePool)
							+ "|"
							+ new BooleanOptBisHelper(aTNOPs[i].aOldTelephoneNumberOrder.theValue().aTNSourcePoolUpdateNotifier).toString()
							+ "|"
							+ printOpt(
								aTNOPs[i]
									.aOldTelephoneNumberOrder
									.theValue()
									.aTelephoneNumberPortingStatus
									.theValue()
									.aRetainedPortingIndicator)
							+ "|"
							+ printOpt(
								aTNOPs[i]
									.aOldTelephoneNumberOrder
									.theValue()
									.aTelephoneNumberPortingStatus
									.theValue()
									.aNonRetainedPortingIndicator)
							+ "|"
							+ printOpt(aTNOPs[i].aOldTelephoneNumberOrder.theValue().aTelephoneNumberPortingStatus.theValue().aOldProvider)
							+ "|"
							+ printOpt(aTNOPs[i].aOldTelephoneNumberOrder.theValue().aTelephoneNumberPortingStatus.theValue().aNewProvider)
							+ "|"
							+ printOpt(aTNOPs[i].aOldTelephoneNumberOrder.theValue().aTelephoneNumberPortingStatus.theValue().aLocalRoutingNumber)
							+ "|>");
				}
				catch (Exception e)
				{
					log(LOG_INPUT_DATA, "[" + i + "].aOldTelephoneNumberOrder<null>");
				}
				String sourcePoolUpdateNotifier;
				try
				{
					if (aTNOPs[i].aTelephoneNumberOrder.aTNSourcePoolUpdateNotifier.theValue())
						sourcePoolUpdateNotifier = "true";
					else
						sourcePoolUpdateNotifier = "false";
				}
				catch (Exception e)
				{
					sourcePoolUpdateNotifier = "null";
				}

				log(
					LOG_INPUT_DATA,
					"["
						+ i
						+ "].aTelephoneNumberOrder<"
						+ printOpt(aTNOPs[i].aTelephoneNumberOrder.aTelephoneNumberType)
						+ "|"
						+ printOpt(aTNOPs[i].aTelephoneNumberOrder.aTelephoneNumber)
						+ "|"
						+ printOpt(aTNOPs[i].aTelephoneNumberOrder.aRequesterId)
						+ "|"
						+ printOpt(aTNOPs[i].aTelephoneNumberOrder.aActivityIndicator)
						+ "|"
						+ aTNOPs[i].aTelephoneNumberOrder.aActionInd
						+ "|"
						+ printOpt(aTNOPs[i].aTelephoneNumberOrder.aTNSourcePool)
						+ "|"
						+ sourcePoolUpdateNotifier
						+ "|"
						+ printOpt(aTNOPs[i].aTelephoneNumberOrder.aTelephoneNumberPortingStatus.theValue().aRetainedPortingIndicator)
						+ "|"
						+ printOpt(aTNOPs[i].aTelephoneNumberOrder.aTelephoneNumberPortingStatus.theValue().aNonRetainedPortingIndicator)
						+ "|"
						+ printOpt(aTNOPs[i].aTelephoneNumberOrder.aTelephoneNumberPortingStatus.theValue().aOldProvider)
						+ "|"
						+ printOpt(aTNOPs[i].aTelephoneNumberOrder.aTelephoneNumberPortingStatus.theValue().aNewProvider)
						+ "|"
						+ printOpt(aTNOPs[i].aTelephoneNumberOrder.aTelephoneNumberPortingStatus.theValue().aLocalRoutingNumber)
						+ "|>");
			}
		}
		catch (Exception e)
		{
			log(LOG_INPUT_DATA, "aTelephoneNumberOrderPairs<null>");
		}
	}


	/**
	 * Method validateInput.
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aOrderNumber
	 * @param aOrderActionType
	 * @param aSubActionType
	 * @param aCompletionIndicator
	 * @param aServiceLocation
	 * @param aOriginalDueDate
	 * @param aSubsequentDueDate
	 * @param aApplicationDate
	 * @param aResendFlag
	 * @param aWireCenter
	 * @param aRateCenter
	 * @param aTelephoneNumberOrderPairs
	 * @param aProperties
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	protected void validateInput(
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aOrderNumber,
		String aOrderActionType,
		StringOpt aSubActionType,
		BooleanOpt aCompletionIndicator,
		Location aServiceLocation,
		EiaDate aOriginalDueDate,
		EiaDateOpt aSubsequentDueDate,
		EiaDate aApplicationDate,
		BooleanOpt aResendFlag,
		StringOpt aWireCenter,
		StringOpt aRateCenter,
		TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs,
		ObjectPropertySeqOpt aProperties)
		throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
	{
		String myMethodNameName = "SendTNAssignmentOrder.validateInput()";
		log(LOG_INFO_LEVEL_1, ">" + myMethodNameName);

		String MISSING = "Missing required data: ";
		String INVALID = "Invalid required data: ";
		String aBisName = myProperties.get("BIS_NAME").toString();

		// ** validate required elements
		//
		ValidateHelper.validateString(
			aUtility,
			aSOACServiceOrderNumber,
			ExceptionCode.ERR_RM_MISSING_SERVICE_ORDER_NUMBER,
			MISSING + "aSOACServiceOrderNumber.",
			aBisName);

		if (aSOACServiceOrderNumber.trim().length() != 9 || !isAlphNum(aSOACServiceOrderNumber))
		{
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_SERVICE_ORDER_NUMBER,
				INVALID + "aSOACServiceOrderNumber.",
				aBisName,
				Severity.UnRecoverable);
		}

		ValidateHelper.validateString(
			aUtility,
			aSOACServiceOrderCorrectionSuffix,
			ExceptionCode.ERR_RM_MISSING_SOAC_SERVICE_ORDER_CORRECTION_SUFFIX,
			MISSING + "aSOACServiceOrderCorrectionSuffix.",
			aBisName);

		if (aSOACServiceOrderCorrectionSuffix.length() > 1 || !Character.isLetter(aSOACServiceOrderCorrectionSuffix.toUpperCase().charAt(0)))
		{
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_SOAC_SERVICE_ORDER_CORRECTION_SUFFIX,
				INVALID + "aSOACServiceOrderCorrectionSuffix, must be single alpha character (A-Z).",
				aBisName,
				Severity.UnRecoverable);
		}

		ValidateHelper.validateString(
			aUtility,
			aOrderNumber,
			ExceptionCode.ERR_RM_MISSING_OMS_ORDER_NUMBER,
			MISSING + "aOrderNumber.",
			aBisName);

		if (aOrderNumber.length() > 10)
		{
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_OMS_ORDER_NUMBER,
				INVALID + "aOrderNumber, must be less than or equal to 10 characters.",
				aBisName,
				Severity.UnRecoverable);
		}

		String OmsOrderActionId = "";
		try
		{
			// Determine OmsOrderActionId value...
			OmsOrderActionId = new ObjectPropertyManager(aProperties.theValue()).getValue("OmsOrderActionId");
			if (OmsOrderActionId == null || OmsOrderActionId.trim().length() < 1)
				throw new Exception();
		}
		catch (Exception e)
		{
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_ORDER_ACTION_ID,
				MISSING + "OmsOrderActionId.",
				aBisName,
				Severity.UnRecoverable);
		}

		if (OmsOrderActionId.trim().length() > 10)
		{
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_ORDER_ACTION_ID,
				INVALID + "OmsOrderActionId, must be less than or equal to 10 characters.",
				aBisName,
				Severity.UnRecoverable);
		}

		ValidateHelper.validateString(
			aUtility,
			aOrderActionType,
			ExceptionCode.ERR_RM_MISSING_ORDER_ACTION_TYPE,
			MISSING + "aOrderActionType.",
			aBisName);

		if (!aOrderActionType.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_PROVIDE)
			&& !aOrderActionType.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_CEASE)
			&& !aOrderActionType.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_CHANGE))
		{

			aUtility.throwException(
				ExceptionCode.ERR_RM_INVALID_ORDER_ACTION_TYPE,
				INVALID
					+ "aOrderActionType, valid values are: "
					+ OrderActionTypeValues.ORDER_ACTION_PROVIDE
					+ " or "
					+ OrderActionTypeValues.ORDER_ACTION_CEASE
					+ " or "
					+ OrderActionTypeValues.ORDER_ACTION_CHANGE,
				aBisName,
				Severity.UnRecoverable);
		}

		// Empty or null is a valid value since it is optional, but verify if populated.
		if (OptHelper.isStringOptEmpty(aSubActionType) == false)
		{
			if (!aSubActionType.theValue().equalsIgnoreCase(SubActionTypeValues.SUB_ACTION_AMEND)
				&& !aSubActionType.theValue().equalsIgnoreCase(SubActionTypeValues.SUB_ACTION_CANCEL))
			{
				aUtility.throwException(
					ExceptionCode.ERR_RM_INVALID_ORDER_SUB_ACTION_TYPE,
					INVALID
						+ "aSubActionType, valid values are: "
						+ SubActionTypeValues.SUB_ACTION_AMEND
						+ " or "
						+ SubActionTypeValues.SUB_ACTION_CANCEL
						+ " or 'null'",
					aBisName,
					Severity.UnRecoverable);
			}
		}

		ValidateHelper.validateLocation(aUtility, aServiceLocation, aBisName);

		try
		{
			// Check for House Number
			if (aServiceLocation.aProviderLocationProperties[0].aServiceAddress.theValue().aFieldedAddress().aHouseNumber.theValue() == null
				|| aServiceLocation
					.aProviderLocationProperties[0]
					.aServiceAddress
					.theValue()
					.aFieldedAddress()
					.aHouseNumber
					.theValue()
					.trim()
					.equalsIgnoreCase(
					""))
				throw new Exception();
		}
		catch (Exception e)
		{
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_SERVICE_LOCATION_HOUSE_NUMBER,
				MISSING + "Service Location House Number.",
				aBisName,
				Severity.UnRecoverable);
		}

		try
		{
			// Check for Street Name
			if (aServiceLocation.aProviderLocationProperties[0].aServiceAddress.theValue().aFieldedAddress().aStreetName.theValue() == null
				|| aServiceLocation
					.aProviderLocationProperties[0]
					.aServiceAddress
					.theValue()
					.aFieldedAddress()
					.aStreetName
					.theValue()
					.trim()
					.equalsIgnoreCase(
					""))
				throw new Exception();
		}
		catch (Exception e)
		{
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_SERVICE_LOCATION_STREET_NAME,
				MISSING + "Service Location Street Name.",
				aBisName,
				Severity.UnRecoverable);
		}

		try
		{
			// Check for State
			if (aServiceLocation.aProviderLocationProperties[0].aServiceAddress.theValue().aFieldedAddress().aState.theValue() == null
				|| aServiceLocation
					.aProviderLocationProperties[0]
					.aServiceAddress
					.theValue()
					.aFieldedAddress()
					.aState
					.theValue()
					.trim()
					.equalsIgnoreCase(
					""))
				throw new Exception();
		}
		catch (Exception e)
		{
			aUtility.throwException(ExceptionCode.ERR_RM_MISSING_STATE, MISSING + "Service Location State.", aBisName, Severity.UnRecoverable);
		}

		//Check for missing DueDate
		ValidateHelper.validateDueDate(aUtility, aOriginalDueDate, aBisName);

		// Convert to standard date format and verify valid date
		aOriginalDueDate = BisDateUtil.toEiaDate(aOriginalDueDate);
		verifyDate(aOriginalDueDate, "aOriginalDueDate", aBisName);

		try
		{
			EiaDate aDate = aSubsequentDueDate.theValue();
			aDate = BisDateUtil.toEiaDate(aDate);
			aSubsequentDueDate.theValue(aDate);
			verifyDate(aSubsequentDueDate.theValue(), "aSubsequentDueDate", aBisName);
		}
		catch (InvalidData id)
		{
			// Invalid...
			throw id;
		}
		catch (Exception e)
		{} // Ignore if null/missing, not a required field, exception only if populated and date is invalid.

		try
		{
			aApplicationDate = BisDateUtil.toEiaDate(aApplicationDate);
			verifyDate(aApplicationDate, "aApplicationDate", aBisName);
		}
		catch (InvalidData id)
		{
			// Invalid...
			throw id;
		}
		catch (Exception e)
		{
			// Missing...
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_APPLICATION_DATE,
				MISSING + "aApplicationDate",
				aBisName,
				Severity.UnRecoverable);
		}

		ValidateHelper.validateStringOpt(aUtility, aRateCenter, ExceptionCode.ERR_RM_MISSING_RATE_DISTRICT, MISSING + "aRateCenter", aBisName);

		// Verify that we have at least one telephone number unless it's a completion(PCN) or cancel(CAN).
		if ((OptHelper.isBooleanOptEmpty(aCompletionIndicator) == true || aCompletionIndicator.theValue() == false)
			&& (OptHelper.isStringOptEmpty(aSubActionType) == true
				|| aSubActionType.theValue().equalsIgnoreCase(SubActionTypeValues.SUB_ACTION_AMEND)))
		{
			TelephoneNumberOrderPair[] aTNOPs = null;
			try
			{
				aTNOPs = aTelephoneNumberOrderPairs.theValue();
				if (aTNOPs.length < 1)
				{
					throw new Exception();
				}
			}
			catch (Exception e)
			{
				aUtility.throwException(
					ExceptionCode.ERR_RM_MISSING_TELEPHONE_NUMBER,
					MISSING + "aTelephoneNumberOrderPairs, must have at least one telephone number.",
					aBisName,
					Severity.UnRecoverable);
			}
			try
			{
				for(int i = 0; i < aTNOPs.length; i++)
				{
					if (aTNOPs[i].aTelephoneNumberOrder != null)
					{
						if (OptHelper.isStringOptEmpty(aTNOPs[i].aTelephoneNumberOrder.aTelephoneNumber))
						{
							throw new Exception();
						}
						
						if (!OptHelper.isTelephoneNumberOrderOptEmpty(aTNOPs[i].aOldTelephoneNumberOrder))
						{
							if (OptHelper.isStringOptEmpty(aTNOPs[i].aOldTelephoneNumberOrder.theValue().aTelephoneNumber))
							{
								throw new Exception();
							}
						}
					}
				}
			}
			catch (Exception e)
			{
				aUtility.throwException(
						ExceptionCode.ERR_RM_MISSING_TELEPHONE_NUMBER,
						MISSING + "aTelephoneNumber, must have a value for telephone number.",
						aBisName,
						Severity.UnRecoverable);
			}
		}

		log(LOG_INFO_LEVEL_1, "<" + myMethodNameName);
	}

	/**
	 * Method printOpt.
	 * @param aStringOpt
	 * @return String
	 */
	private String printOpt(StringOpt aStringOpt)
	{
		try
		{
			return aStringOpt.theValue();
		}
		catch (Exception e)
		{}
		return "null";
	}

	/**
	 * Method isAlphNum.
	 * @param inString
	 * @return boolean
	 */
	private boolean isAlphNum(String inString)
	{
		for (int i = 0; i < inString.length(); i++)
		{
			if (!Character.isLetterOrDigit(inString.toUpperCase().charAt(i)))
				return false;
		}
		return true;
	}

	/**
	 * Method verifyDate.
	 * @param inDate
	 * @param dateParm
	 * @param aBisName
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	private void verifyDate(EiaDate inDate, String dateParm, String aBisName)
		throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
	{
		if (validDayMonth(inDate.aDay, inDate.aMonth))
		{
			if (inDate.aYear > 0 && inDate.aYear <= 2100)
			{
				return;
			}
		}

		aUtility.throwException(ExceptionCode.ERR_RM_INVALID_DATA, "Invalid date: " + dateParm, aBisName, Severity.UnRecoverable);
	}

	/**
	 * Method validDayMonth.
	 * @param aDay
	 * @param aMonth
	 * @return boolean
	 */
	private boolean validDayMonth(short aDay, short aMonth)
	{
		if (aMonth == 1 || aMonth == 3 || aMonth == 5 || aMonth == 7 || aMonth == 8 || aMonth == 10 || aMonth == 12)
		{
			if (aDay >= 1 && aDay <= 31)
			{
				return true;
			}
		}
		if (aMonth == 4 || aMonth == 6 || aMonth == 9 || aMonth == 11)
		{
			if (aDay >= 1 && aDay <= 30)
			{
				return true;
			}
		}
		if (aMonth == 2)
		{
			if (aDay >= 1 && aDay <= 29)
			{
				return true;
			}
		}
		return false;
	}
}