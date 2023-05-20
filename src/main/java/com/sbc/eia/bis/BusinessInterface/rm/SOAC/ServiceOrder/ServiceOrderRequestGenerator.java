//$Id: ServiceOrderRequestGenerator.java,v 1.31 2009/06/14 23:26:22 hw7243 Exp $
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
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 07/2006		Sriram Chevuturu      Creation
//# 12/07/2006  Doris Sunga			  PR #172334 - FOR RGPON & GPON display TDM TN
//#									  on TN after echo section
//# 02/22/2007  Doris Sunga			  replacing System.out calls in lines 1231-1237
//#									  with 	myLogger.log(LogEventId.INFO_LEVEL_1...)
//# 03/27/2007  Rene Duka             PR 19520247: Fixed circuitID format from "." to "/".
//# 04/17/2007  Jon Costa			  CR13737: validation of completeIndicator==false when subActionType==Amend
//# 05/09/2007  Doris Sunga			  CR 13440: populate REMARKS_DISCONNECT_TN when aProperties.aDSLDisconnectTN
//#                                      is populated
//# 05/17/2007 Jon Costa			  PR (TBD): Add coma or semicolon to LOC data when there are multiple entries
//# 05/21/2007 Jon Costa			  DR66682: Recognize Change actionType with null subActionType .
//# 05/23/2007 Doris Sunga			  DR 67019 - pFAON - TN from RTID FID, converted to 10 digit numeric TDM Telco TN
//# 07/23/2007 Doris Sunga			  WR 70002226, LS5SE - setting 'B' for SOUTHEAST region 'SoacServiceOrderConstants.B_REGION'
//# 08/22/2007 Ott Phannavong		  LS6 - added application indicator in prepareEchoSection()
//# 11/13/2007 Doris Sunga            Defect #78225: change equals to equalsIgnoreCase()
//# 2/04/2008  Ott Phannavong         CR17421 add FTTP to echo section if network type is FTTPIP

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

import java.util.Hashtable;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequest;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParserSvc;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory.ParserSvcFactory;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.CVOIPServiceOrderVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerLFACS;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.rm_ls_types.OrderActionTypeValues;
import com.sbc.eia.idl.rm_ls_types.SubActionTypeValues;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;

/**
 * @author sc8468 To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
//public class ServiceOrderRequestFormatter extends ServiceOrderTextProcessor {
public class ServiceOrderRequestGenerator
{
	protected Logger myLogger = null;

	protected Hashtable myProperties = null;

	protected ServiceOrderRequestHelper aSvcOrdReqHelper = null;

	//can not have it in SvcOrderConstants..because it changes depending on
	// orderActionType
	protected String dueDateActionIndicator = null;

	public ServiceOrderRequestGenerator(Hashtable aProperties, Logger aLogger)
	{
		myProperties = aProperties;
		myLogger = aLogger;
		aSvcOrdReqHelper = new ServiceOrderRequestHelper(aProperties, aLogger);
	}

	public String getFCIFRequestString(SvcOrdRequestDataObject reqDataObject)
			throws ParserSvcException
	{
		String myMethodName = "ServiceOrderRequestProcessor::getFCIFRequestString()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		//Create SOAC Service Order Value Object
		SoacServiceOrderVO aSSOVO = new SoacServiceOrderVO();
		//a ParserService Object.
		ParserSvc aParserSvc;
		// Populate the SOAC Service Order VO with the values passed by OMS
		prepareRequestSpecificSOACSOVO(aSSOVO, reqDataObject);
		//Create Parse Request
		ParseRequest aParseRequest = new ParseRequest();
		//Populate Parse Request with required attributes
		aParseRequest.setLogger(myLogger);
		//Set Service Order
		aParseRequest.setServiceOrderVo(aSSOVO);
		//Set Region depending on the network
		//for FTTP / FTTN / RGPN region matters
		//for VOIP it does not for LS3
		if(reqDataObject.getNetworkType().equalsIgnoreCase(
				SvcOrderConstants.VOIP_NETWORK))
			aParseRequest.setRegion(SoacServiceOrderConstants.CVOIP);
		else
		{
			if(reqDataObject.getRegion().equalsIgnoreCase("S"))
				aParseRequest.setRegion(SoacServiceOrderConstants.SW_REGION);
			else if(reqDataObject.getRegion().equalsIgnoreCase("M"))
				aParseRequest.setRegion(SoacServiceOrderConstants.MW_REGION);
			else if(reqDataObject.getRegion().equalsIgnoreCase("E"))
				aParseRequest.setRegion(SoacServiceOrderConstants.E_REGION);
			else if(reqDataObject.getRegion().equalsIgnoreCase("W"))
				aParseRequest.setRegion(SoacServiceOrderConstants.W_REGION);
			else if(reqDataObject.getRegion().equalsIgnoreCase("B"))
				aParseRequest.setRegion(SoacServiceOrderConstants.B_REGION);
		}
		aParseRequest.setDataFormat("SOAC_FCIF");
		//set The Service Order Num
		aParseRequest.setSon((String)aSSOVO
				.get(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM));
		//SET Operation Type
		aParseRequest
				.setOperationType(SoacServiceOrderConstants.SOAC_VALUEOBJECT_TO_SOAC_FCIF);
		aParserSvc = getParserSvc();
		aParserSvc.processData(aParseRequest);
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		//FCIF Service Order Request String Retrieval
		return aParseRequest.getFcifDataString();
	}
	protected void prepareRequestSpecificSOACSOVO(SoacServiceOrderVO aSSOVO,
			SvcOrdRequestDataObject reqDataObject)
	{
		String myMethodName = "ServiceOrderRequestProcessor::prepareRequestSpecificSOACSOVO()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		boolean completeIndicator = false;
		String subActionType = null;
		// Due Date will be subesequent if present else original
		EiaDate aDueDate = aSvcOrdReqHelper.processDueDate(reqDataObject
				.getOriginalDueDate(), reqDataObject.getSubsequentDueDate());
		String orderActionType = reqDataObject.getOrderActionType();
		//now try to get the value of aSubActionType
		try
		{
			if(reqDataObject.getSubActionType() != null)
				subActionType = reqDataObject.getSubActionType().theValue();
			if(!(subActionType
					.equalsIgnoreCase(SubActionTypeValues.SUB_ACTION_CANCEL))
					&& !(subActionType
							.equalsIgnoreCase(SubActionTypeValues.SUB_ACTION_AMEND)))
				subActionType = null;
		}
		catch(Exception e)
		{
			subActionType = null;
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aSubActionType is  null.<<");
		}
		//now get the value of completionIndicator
		try
		{
			if(reqDataObject.getCompletionIndicator() != null)
				completeIndicator = reqDataObject.getCompletionIndicator()
						.theValue();
		}
		catch(Exception e)
		{
			completeIndicator = false;
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aCompletionIndicator  is  false.<<");
		}
		//Now Depending on the Action Type i.e. Type Of request, Prepare
		// Control
		// and eCHO sECTIONS.
		//For New Connect
		if(prepareControlAndEchoForNewconnect(subActionType, orderActionType,
				completeIndicator))
		{
			myLogger
					.log(LogEventId.DEBUG_LEVEL_1,
							">>Start NEW_CONNECT : Preparing Control Header and Echo Sections.");
			processNewConnectRequest(aSSOVO, reqDataObject,
					SvcOrderConstants.NEW_CONNECT_DISCONNECT_ACTION_TYPE,
					SvcOrderConstants.NEW_CONNECT_ACTION_INDICATOR, aDueDate);
			myLogger
					.log(LogEventId.DEBUG_LEVEL_1,
							"Done NEW_CONNECT  : Prepared Control Header and Echo Sections.<<");
			//		  }
		}
		//For Disconnect
		else if(orderActionType != null
				&& orderActionType
						.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_CEASE)
				&& (subActionType == null) && (completeIndicator == false))
		{
			myLogger
					.log(LogEventId.DEBUG_LEVEL_1,
							">>Start DISCONNECT : Preparing Control Header and Echo Sections.");
			processDisConnectRequest(aSSOVO, reqDataObject,
					SvcOrderConstants.NEW_CONNECT_DISCONNECT_ACTION_TYPE,
					SvcOrderConstants.DISCONNECT_ACTION_INDICATOR, aDueDate);
			myLogger
					.log(LogEventId.DEBUG_LEVEL_1,
							"Done DISCONNECT : Prepared Control Header and Echo Sections.<<");
		}
		//is this line needed.
		//if( aOrderActionType.equals(OMS_NEW_CONNECT_NAME) || aOrderActionType
		// .equals(OMS_DISCONNECT_NAME) ) {
		//For Cancellation
		else if(subActionType != null
				&& subActionType
						.equalsIgnoreCase(SubActionTypeValues.SUB_ACTION_CANCEL))
		{
			myLogger
					.log(LogEventId.DEBUG_LEVEL_1,
							">>Start CANCEL : Preparing Control Header and Echo Sections.");
			//Prepare Control Header
			//	Here the new connect or disconnect indicator is null
			processCancelRequest(aSSOVO, reqDataObject,
					SvcOrderConstants.CANCELLATION_SUB_ACTION_TYPE, "",
					aDueDate);
			myLogger
					.log(LogEventId.DEBUG_LEVEL_1,
							"Done CANCEL : Prepared Control Header and Echo Sections.<<");
		}
		//For Due date Change
		else if(isDueDateChange(subActionType, orderActionType,
				completeIndicator))
		{
			myLogger
					.log(LogEventId.DEBUG_LEVEL_1,
							">>Start AMEND_DUE_DATE : Preparing Control Header and Echo Sections.");
			//decide what is the Action Indicator..depending on what is the
			// OrderActionType
			//If it is "Provide" then the ActionInd is "I" and if It is
			// "Cease",
			// then ActionInd is "O"
			if(orderActionType != null)
			{
				if(orderActionType
						.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_PROVIDE))
				{
					dueDateActionIndicator = SvcOrderConstants.NEW_CONNECT_ACTION_INDICATOR;
				}
				else if(orderActionType
						.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_CEASE))
				{
					dueDateActionIndicator = SvcOrderConstants.DISCONNECT_ACTION_INDICATOR;
				}
				else
				{
					//Order action type is a require field, it should be one of
					// the
					// specify field
					//this was done before so i will leave it the way it is
					dueDateActionIndicator = SvcOrderConstants.NEW_CONNECT_ACTION_INDICATOR;
				}
			}
			else
			//defaulting to "I" -- may be this need to change in the future.
			{
				dueDateActionIndicator = SvcOrderConstants.NEW_CONNECT_ACTION_INDICATOR;
			}

			processAmendRequest(aSSOVO, reqDataObject,
					SvcOrderConstants.DUE_DATE_SUB_ACTION_TYPE,
					dueDateActionIndicator, aDueDate);
			myLogger
					.log(LogEventId.DEBUG_LEVEL_1,
							"Done AMEND_DUE_DATE : Prepared Control Header and Echo Sections.<<");
		}
		else if(orderActionType
				.equalsIgnoreCase(SvcOrderConstants.CHANGE_ORDER_ACTION_TYPE)
				&& (completeIndicator == false))
		{
			setChangeActionIndicator(reqDataObject);
			if(subActionType == null)
			{
				processAmendRequest(aSSOVO, reqDataObject,
						SvcOrderConstants.NEW_CONNECT_DISCONNECT_ACTION_TYPE,
						dueDateActionIndicator, aDueDate);
			}
			else
			{
				processAmendRequest(aSSOVO, reqDataObject,
						SvcOrderConstants.DUE_DATE_SUB_ACTION_TYPE,
						dueDateActionIndicator, aDueDate);
			}
		}
		//For Completion
		else
			//Opt field Hence try/catch
			try
			{
				myLogger
						.log(LogEventId.DEBUG_LEVEL_1,
								">>Start COMPLETION : Preparing Control Header and Echo Sections.");
				if(reqDataObject.getCompletionIndicator().theValue() == true)
				{
					//Prepare Control Header
					//	Here the new connect or disconnect indicator is null
					processCompletionRequest(aSSOVO, reqDataObject,
							SvcOrderConstants.COMPLETION_SUB_ACTION_TYPE, "",
							aDueDate);
				}
				myLogger
						.log(LogEventId.DEBUG_LEVEL_1,
								"Done COMPLETION : Prepared Control Header and Echo Sections.<<");
			}
			catch(org.omg.CORBA.BAD_OPERATION e)
			{
				myLogger.log(LogEventId.DEBUG_LEVEL_1,
						">>aCompletionIndicator is Not Available.<<");
			}
			catch(NullPointerException e)
			{
				myLogger.log(LogEventId.DEBUG_LEVEL_1,
						">>aCompletionIndicator is Not Available.<<");
			}
		//try to add the Special Conitions Section Data If any..For LS3 onwards
		// Communication Testing purposes.
		prepareSpecialConditionsSection(aSSOVO, reqDataObject);
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}
	protected void processNewConnectRequest(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject, String aOrderActionType,
			String aActionIndicator, EiaDate aDuedate)
	{
		String myMethodName = "ServiceOrderRequestProcessor::processNewConnectRequest()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		prepareControlHeaderSection(aSSVO, reqDataObject, aOrderActionType,
				aActionIndicator, aDuedate);
		prepareEchoSection(aSSVO, reqDataObject);
		prepareSvcOrderUnFieldedTextSection(aSSVO, reqDataObject,
				aOrderActionType, aActionIndicator);
		prepareListingSection(aSSVO, reqDataObject);
		prepareRemarksAndMiscSections(aSSVO, reqDataObject, aActionIndicator);
		if(reqDataObject.getNetworkType() != null
				&& reqDataObject.getNetworkType().equalsIgnoreCase(
						SvcOrderConstants.VOIP_NETWORK))
			prepareSandESectionForVOIP(aSSVO, reqDataObject.getVoipSnEData());
		else
			prepareSandESectionForFTTX(aSSVO, reqDataObject);
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	protected void processDisConnectRequest(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject, String aOrderActionType,
			String aActionIndicator, EiaDate aDuedate)
	{
		String myMethodName = "ServiceOrderRequestProcessor::processDisConnectRequest()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		prepareControlHeaderSection(aSSVO, reqDataObject, aOrderActionType,
				aActionIndicator, aDuedate);
		prepareEchoSection(aSSVO, reqDataObject);
		prepareSvcOrderUnFieldedTextSection(aSSVO, reqDataObject,
				aOrderActionType, aActionIndicator);
		prepareListingSection(aSSVO, reqDataObject);
		prepareRemarksAndMiscSections(aSSVO, reqDataObject, aActionIndicator);
		if(reqDataObject.getNetworkType() != null
				&& reqDataObject.getNetworkType().equalsIgnoreCase(
						SvcOrderConstants.VOIP_NETWORK))
			prepareSandESectionForVOIP(aSSVO, reqDataObject.getVoipSnEData());
		else
			prepareSandESectionForFTTX(aSSVO, reqDataObject);
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	protected void processCancelRequest(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject, String aOrderActionType,
			String aActionIndicator, EiaDate aDuedate)
	{
		String myMethodName = "ServiceOrderRequestProcessor::processCancelRequest()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		prepareControlHeaderSection(aSSVO, reqDataObject, aOrderActionType,
				aActionIndicator, aDuedate);
		prepareEchoSection(aSSVO, reqDataObject);
		prepareSvcOrderUnFieldedTextSection(aSSVO, reqDataObject,
				aOrderActionType, aActionIndicator);
		prepareListingSection(aSSVO, reqDataObject);
		prepareRemarksAndMiscSections(aSSVO, reqDataObject, aActionIndicator);
		if(reqDataObject.getNetworkType() != null
				&& reqDataObject.getNetworkType().equalsIgnoreCase(
						SvcOrderConstants.VOIP_NETWORK))
			prepareSandESectionForVOIP(aSSVO, reqDataObject.getVoipSnEData());
		else
			prepareSandESectionForFTTX(aSSVO, reqDataObject);
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	protected void processAmendRequest(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject, String aOrderActionType,
			String aActionIndicator, EiaDate aDuedate)
	{
		String myMethodName = "ServiceOrderRequestProcessor::processAmendRequest()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		prepareControlHeaderSection(aSSVO, reqDataObject, aOrderActionType,
				aActionIndicator, aDuedate);
		prepareEchoSection(aSSVO, reqDataObject);
		prepareSvcOrderUnFieldedTextSection(aSSVO, reqDataObject,
				aOrderActionType, aActionIndicator);
		prepareListingSection(aSSVO, reqDataObject);
		prepareRemarksAndMiscSections(aSSVO, reqDataObject, aActionIndicator);
		if(reqDataObject.getNetworkType() != null
				&& reqDataObject.getNetworkType().equalsIgnoreCase(
						SvcOrderConstants.VOIP_NETWORK))
			prepareSandESectionForVOIP(aSSVO, reqDataObject.getVoipSnEData());
		else
			prepareSandESectionForFTTX(aSSVO, reqDataObject);
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	protected void processCompletionRequest(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject, String aOrderActionType,
			String aActionIndicator, EiaDate aDuedate)
	{
		String myMethodName = "ServiceOrderRequestProcessor::processCompletionRequest()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		prepareControlHeaderSection(aSSVO, reqDataObject, aOrderActionType,
				aActionIndicator, aDuedate);
		prepareEchoSection(aSSVO, reqDataObject);
		prepareSvcOrderUnFieldedTextSection(aSSVO, reqDataObject,
				aOrderActionType, aActionIndicator);
		prepareListingSection(aSSVO, reqDataObject);
		prepareRemarksAndMiscSections(aSSVO, reqDataObject, aActionIndicator);
		if(reqDataObject.getNetworkType() != null
				&& reqDataObject.getNetworkType().equalsIgnoreCase(
						SvcOrderConstants.VOIP_NETWORK))
			prepareSandESectionForVOIP(aSSVO, reqDataObject.getVoipSnEData());
		else
			prepareSandESectionForFTTX(aSSVO, reqDataObject);
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	protected void prepareControlHeaderSection(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject, String aOrderActionType,
			String aActionIndicator, EiaDate aDueDate)
	{
		String myMethodName = "ServiceOrderRequestProcessor::prepareControlHeader()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		//from HLD, here are the fields in Control Header.
		//1. Function Type 2. SOAC Order Number 3. Correction Suffix 4. Wire
		// Center or NPA-NXX
		//5. Originatin Host Name 6. entityPlatform 7.RegionEntity
		//8.dueDate
		aSSVO.put(SoacServiceOrderConstants.FUNCTION_TYPE, aOrderActionType);
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.FUNCTION_TYPE=<"
							+ aOrderActionType + ">");
		}
		catch(Exception e)
		{
		}
		//if Action Indicator is present, then populate--only for New Connect
		// and
		// DisConnect
		//looks like it is present in Amend also..if it has related order then
		// the value is "I", if not "O"
		if(aActionIndicator.trim().equalsIgnoreCase(
				SvcOrderConstants.NEW_CONNECT_ACTION_INDICATOR)
				|| aActionIndicator.trim().equalsIgnoreCase(
						SvcOrderConstants.DISCONNECT_ACTION_INDICATOR)
				|| aActionIndicator.trim().equalsIgnoreCase(
						SvcOrderConstants.CHANGE_ACTION_INDICATOR))
		{
			aSSVO.put(SoacServiceOrderConstants.ACTION_INDICATOR,
					aActionIndicator);
			try
			{
				myLogger.log(TranBase.LOG_INPUT_DATA,
						"Setting SoacServiceOrderConstants.ACTION_INDICATOR=<"
								+ aActionIndicator + ">");
			}
			catch(Exception e)
			{
			}
		}
		aSSVO.put(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM,
				reqDataObject.getSoacServiceOrderNumber());
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM=<"
							+ reqDataObject.getSoacServiceOrderNumber() + ">");
		}
		catch(Exception e)
		{
		}
		//this is optional and Opt field. Hence the try / catch
		try
		{
			aSSVO.put(SoacServiceOrderConstants.CORRECTION_SUFFIX,
					reqDataObject.getSoacServiceOrderCorrectionSuffix());
		}
		catch(Exception e)
		{
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aSOACServiceOrderCorrectionSuffix is Not Available.<<");
		}
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.CORRECTION_SUFFIX=<"
							+ reqDataObject
									.getSoacServiceOrderCorrectionSuffix()
							+ ">");
		}
		catch(Exception e)
		{
		}
		aSSVO
				.put(
						SoacServiceOrderConstants.WIRE_CENTER_OR_NPANXX,
						reqDataObject.getServiceLocation().aProviderLocationProperties[0].aPrimaryNpaNxx
								.theValue());
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.WIRE_CENTER_OR_NPANXX=<"
									+ reqDataObject.getServiceLocation().aProviderLocationProperties[0].aPrimaryNpaNxx
											.theValue() + ">");
		}
		catch(Exception e)
		{
		}
		aSSVO.put(SoacServiceOrderConstants.ORIGINATING_HOST_NAME,
				reqDataObject.getOriginatingHost());
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.ORIGINATING_HOST_NAME=<"
							+ reqDataObject.getOriginatingHost() + ">");
		}
		catch(Exception e)
		{
		}
		//here goes southwest entity -- Jon should give me this.
		//got to figure out how to fill this -- HONGMEI -- yes JON..
		//		aSSVO.put(SoacServiceOrderConstants.ENTITY ,"C");
		aSSVO.put(SoacServiceOrderConstants.ENTITY, reqDataObject.getEntity());
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.ENTITY=<"
							+ reqDataObject.getEntity() + ">");
		}
		catch(Exception e)
		{
		}
		aSSVO.put(SoacServiceOrderConstants.DUE_DATE, aSvcOrdReqHelper
				.formatDate(aDueDate.aMonth)
				+ "-"
				+ aSvcOrdReqHelper.formatDate(aDueDate.aDay)
				+ "-"
				+ aSvcOrdReqHelper.formatYear(aDueDate.aYear));
		// test or prod...i.e. region_platform where i am going to getthis
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.DUE_DATE=<"
							+ aSSVO.get(SoacServiceOrderConstants.DUE_DATE)
							+ ">");
		}
		catch(Exception e)
		{
		}
		aSSVO.put(SoacServiceOrderConstants.ENTITY_PLATFORM, reqDataObject
				.getEntityPlatform());
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.ENTITY_PLATFORM=<"
							+ reqDataObject.getEntityPlatform() + ">");
		}
		catch(Exception e)
		{
		}
		try
		{
			aSSVO.put(SoacServiceOrderConstants.REGION_INDICATOR, reqDataObject
					.getRegion());
		}
		catch(Exception e)
		{
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aRegion is Not Available.<<");
		}
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.REGION_INDICATOR=<"
							+ reqDataObject.getRegion() + ">");
		}
		catch(Exception e)
		{
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	protected void prepareSpecialConditionsSection(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject)
	{
		String myMethodName = "ServiceOrderRequestProcessor::prepareSpecialConditionsSection()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		aSSVO.put(SoacServiceOrderConstants.SPECIAL_SECTION_IND, reqDataObject
				.getSpecialCondiData());
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	protected void prepareEchoSection(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject)
	{
		String myMethodName = "ServiceOrderRequestProcessor::prepareEchoSection()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		/*
		 * fields 1. OMS Order Number 2. OMS Order Action Number 3. Network
		 * Indicator 4. Resend Flag(optional) 5. Application Indicator
		 */
		aSSVO.put(SoacServiceOrderConstants.OMS_ORDER_NUM, reqDataObject
				.getOrderNumber());
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.OMS_ORDER_NUM=<"
							+ reqDataObject.getOrderNumber() + ">");
		}
		catch(Exception e)
		{
		}
		aSSVO.put(SoacServiceOrderConstants.OMS_ORDER_ACTION_NUM, reqDataObject
				.getOrderActionId());
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.OMS_ORDER_ACTION_NUM=<"
							+ reqDataObject.getOrderActionId() + ">");
		}
		catch(Exception e)
		{
		}
		//LS7
		addNetworkType(aSSVO, reqDataObject);
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.NETWORK_TYPE=<"
							+ aSSVO.get(SoacServiceOrderConstants.NETWORK_TYPE)
							+ ">");
		}
		catch(Exception e)
		{
		}
		try
		{
			if(reqDataObject.getResendFlag().theValue() == true)
				aSSVO.put(SoacServiceOrderConstants.RESEND_INDICATOR, "R");
			else
				aSSVO.put(SoacServiceOrderConstants.RESEND_INDICATOR, " ");
		}
		catch(org.omg.CORBA.BAD_OPERATION e)
		{
			aSSVO.put(SoacServiceOrderConstants.RESEND_INDICATOR, " ");
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aResendFlag is Not Available.<<");
		}
		catch(NullPointerException e)
		{
			aSSVO.put(SoacServiceOrderConstants.RESEND_INDICATOR, " ");
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aResendFlag is Not Available.<<");
		}
		//LS6
		aSSVO.put(SoacServiceOrderConstants.APPLICATION_INDICATOR,
				reqDataObject.getApplicationIndicator());
		myLogger
				.log(
						LogEventId.INFO_LEVEL_2,
						"Setting SoacServiceOrderConstants.APPLICATION_INDICATOR=<"
								+ aSSVO
										.get(SoacServiceOrderConstants.APPLICATION_INDICATOR)
								+ ">");
		//LS6 end
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	protected void prepareSvcOrderUnFieldedTextSection(
			SoacServiceOrderVO aSSVO, SvcOrdRequestDataObject reqDataObject,
			String aOrderActionType, String aActionIndicator)
	{
		String myMethodName = "ServiceOrderRequestProcessor::prepareSvcTextSection()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		//for LS3 CVOIP -- First Tel Number goes to TN_OR_NPANXX field.
		//but in the case of Completion and Cancellation there will not
		//be any telephone number. hence try catch.
		try
		{
			if(reqDataObject.getNetworkType().equalsIgnoreCase(
					SvcOrderConstants.VOIP_NETWORK))
			{
				aSSVO.put(SoacServiceOrderConstants.TN_OR_NPANXX,
						aSvcOrdReqHelper.formatTelephoneNumber(reqDataObject
								.getVoipSnEData()[0].getTelephoneNumber()));
			}
			else
			{
				if((OptHelper.isStringOptEmpty(reqDataObject
						.getTDMTelphoneNumber()) == false)
						&& (reqDataObject.getTDMTelphoneNumber().theValue()
								.length() == 10))
				{
					aSSVO
							.put(
									SoacServiceOrderConstants.TN_OR_NPANXX,
									(aSvcOrdReqHelper
											.formatTelephoneNumber(reqDataObject
													.getTDMTelphoneNumber()
													.theValue())));
				}
				else
				{
					aSSVO
							.put(
									SoacServiceOrderConstants.TN_OR_NPANXX,
									(aSvcOrdReqHelper
											.formatTelephoneNumber(reqDataObject
													.getServiceLocation().aProviderLocationProperties[0].aPrimaryNpaNxx
													.theValue()) + "-1111"));
				}
			}
		}
		catch(Exception e)
		{
			myLogger
					.log(TranBase.LOG_INPUT_DATA,
							"No NPA-NXX for VOIP Data. VOIP Completion or Cancellation Order.");
		}
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.TN_OR_NPANXX=<"
							+ aSSVO.get(SoacServiceOrderConstants.TN_OR_NPANXX)
							+ ">");
		}
		catch(Exception e)
		{
		}
		aSSVO.put(SoacServiceOrderConstants.APPLICATION_DATE, aSvcOrdReqHelper
				.formatDate(reqDataObject.getApplicationDate().aMonth)
				+ "-"
				+ aSvcOrdReqHelper.formatDate(reqDataObject
						.getApplicationDate().aDay)
				+ "-"
				+ aSvcOrdReqHelper.formatYear(reqDataObject
						.getApplicationDate().aYear));
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.APPLICATION_DATE=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.APPLICATION_DATE)
									+ ">");
		}
		catch(Exception e)
		{
		}
		aSSVO.put(SoacServiceOrderConstants.CLASS_OF_SERVICE, reqDataObject
				.getClassOfService());
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.CLASS_OF_SERVICE=<"
							+ reqDataObject.getClassOfService() + ">");
		}
		catch(Exception e)
		{
		}
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.APPLICATION_DATE=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.APPLICATION_DATE)
									+ ">");
		}
		catch(Exception e)
		{
		}
		aSSVO.put(SoacServiceOrderConstants.ORIGINAL_DUE_DATE, aSvcOrdReqHelper
				.formatDate(reqDataObject.getOriginalDueDate().aMonth)
				+ "-"
				+ aSvcOrdReqHelper.formatDate(reqDataObject
						.getOriginalDueDate().aDay)
				+ "-"
				+ aSvcOrdReqHelper.formatYear(reqDataObject
						.getOriginalDueDate().aYear));
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.ORIGINAL_DUE_DATE=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.ORIGINAL_DUE_DATE)
									+ ">");
		}
		catch(Exception e)
		{
		}
		//Opt field Hence try/catch
		//at last it is decide that...if OMS sends it ..we send it...
		//Opt field Hence try/catch
		//the following field is only for New connect and Due date Change AND
		// ALSO dIScONNECT
		try
		{
			if(aActionIndicator.trim().equalsIgnoreCase(
					SvcOrderConstants.NEW_CONNECT_ACTION_INDICATOR)
					|| aActionIndicator.trim().equalsIgnoreCase(
							SvcOrderConstants.DISCONNECT_ACTION_INDICATOR)
					|| aOrderActionType.trim().equalsIgnoreCase(
							SvcOrderConstants.DUE_DATE_SUB_ACTION_TYPE))
				aSSVO.put(SoacServiceOrderConstants.SUBSEQ_DUE_DATE,
						aSvcOrdReqHelper.formatDate(reqDataObject
								.getSubsequentDueDate().theValue().aMonth)
								+ "-"
								+ aSvcOrdReqHelper
										.formatDate(reqDataObject
												.getSubsequentDueDate()
												.theValue().aDay)
								+ "-"
								+ aSvcOrdReqHelper
										.formatYear(reqDataObject
												.getSubsequentDueDate()
												.theValue().aYear));
		}
		catch(Exception e)
		{
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aSubsequentDueDate is Not Available.<<");
		}
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.SUBSEQ_DUE_DATE=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE)
									+ ">");
		}
		catch(Exception e)
		{
		}
		try
		{
			aSSVO.put(SoacServiceOrderConstants.RELATED_SERVICE_ORDER,
					reqDataObject.getRelatedServiceOrderNumber().theValue());
		}
		catch(Exception e)
		{
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aRelatedServiceOrderNumber is Not Available.<<");
		}
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.RELATED_SERVICE_ORDER=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.RELATED_SERVICE_ORDER)
									+ ">");
		}
		catch(Exception e)
		{
		}
		try
		{
			if (reqDataObject.getExceptionRoutingIndicator().theValue().length() > 0) {
			aSSVO.put(SoacServiceOrderConstants.EXCEPTION_ROUTING_INDICATOR,
					reqDataObject.getExceptionRoutingIndicator().theValue());
			}
		}
		catch(Exception e)
		{
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	protected void prepareListingSection(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject)
	{
		String myMethodName = "ServiceOrderRequestProcessor::prepareListingSection()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		// make an address handle object to get address info...
		Address anAddress = reqDataObject.getServiceLocation().aProviderLocationProperties[0].aServiceAddress
				.theValue();
		AddressHandlerLFACS anAddHandlerLFACS;
		String temp1;
		String temp2;
		try
		{
			anAddHandlerLFACS = new AddressHandlerLFACS(anAddress);
			temp1 = aSvcOrdReqHelper.formatBasicAddress(anAddHandlerLFACS);
			temp2 = aSvcOrdReqHelper
					.formatExtendedBasicAddress(anAddHandlerLFACS);
			if(!temp2.trim().equals(""))
				aSSVO.put(SoacServiceOrderConstants.BASIC_ADDRESS, temp1 + ",");
			else
				aSSVO.put(SoacServiceOrderConstants.BASIC_ADDRESS, temp1);
			aSSVO.put(SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS, temp2);
		}
		catch(AddressHandlerException e)
		{
		}
		try
		{
			aSSVO
					.put(
							SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM,
							reqDataObject.getServiceLocation().aProviderLocationProperties[0].aServiceAddress
									.theValue().aFieldedAddress().aAssignedHouseNumber
									.theValue());
		}
		catch(Exception e)
		{
		}
		try
		{
			anAddHandlerLFACS = new AddressHandlerLFACS(anAddress);
			aSSVO.put(SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO,
					aSvcOrdReqHelper.formatSupplementalAddressInfo(
							anAddHandlerLFACS, aSSVO.get("REGION_INDICATOR")
									.toString()));
		}
		catch(AddressHandlerException e)
		{
		}
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.BASIC_ADDRESS=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.BASIC_ADDRESS)
									+ ">");
		}
		catch(Exception e)
		{
		}
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS)
									+ ">");
		}
		catch(Exception e)
		{
		}
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM)
									+ ">");
		}
		catch(Exception e)
		{
		}
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO)
									+ ">");
		}
		catch(Exception e)
		{
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	//everything else
	protected void prepareRemarksAndMiscSections(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject, String aActionIndicator)
	{
		String myMethodName = "ServiceOrderRequestProcessor::prepareMiscellaneousSection()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		//for New COnnect
		// if aLineShareDisconnectFlag is true then
		// Put in "DSL DISCONNECT". If not this line will not be here
		//Opt field Hence try/catch
		//at last it is decide that...if OMS sends it ..we send it...
		// CR 13440 populate REMARKS_DISCONNECT_TN only when aDSLDisconnect is
		// not
		// null or empty
		String aDSLDisconnectTN = null;
		try
		{
			aDSLDisconnectTN = new ObjectPropertyManager(reqDataObject
					.getAProperties().theValue()).getValue(
					"aDSLDisconnectTelephoneNumber").trim();
			if(aDSLDisconnectTN != null && aDSLDisconnectTN.trim().length() > 0)
				aSSVO.put(SoacServiceOrderConstants.REMARKS_DISCONNECT_TN,
						aDSLDisconnectTN);
			else
				aDSLDisconnectTN = null;
			aSSVO.put(SoacServiceOrderConstants.REMARKS_DISCONNECT_TN,
					aDSLDisconnectTN);
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aDSLDisconnectTelephoneNumber is Not Available.<<");
		}
		catch(Exception e)
		{
			aSSVO.put(SoacServiceOrderConstants.REMARKS_DISCONNECT_TN,
					aDSLDisconnectTN);
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aDSLDisconnectTelephoneNumber is Not Available.<<");
		}
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.REMARKS_DISCONNECT_TN=<"
							+ aDSLDisconnectTN + ">");
		}
		catch(Exception e)
		{
		}
		//for New COnnect
		// if aAdditionalLineFlag is true then
		// Put in "/ADL"FID. If not this line will not be here
		//Opt field Hence try/catch
		//at last it is decide that...if OMS sends it ..we send it...
		aSSVO.put(SoacServiceOrderConstants.ADDITIONAL_LINE_FLAG, "N");
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.ADDITIONAL_LINE_FLAG=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.ADDITIONAL_LINE_FLAG)
									+ ">");
		}
		catch(Exception e)
		{
		}
		//for New Connect and Disconnect only
		// For New it will be "I" & for disconnect it will be "O"
		if(aActionIndicator.trim().equalsIgnoreCase(
				SvcOrderConstants.NEW_CONNECT_ACTION_INDICATOR))
			aSSVO.put(SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT,
					SvcOrderConstants.NEW_CONNECT_ACTION_INDICATOR);
		else if(aActionIndicator.trim().equalsIgnoreCase(
				SvcOrderConstants.DISCONNECT_ACTION_INDICATOR))
			aSSVO.put(SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT,
					SvcOrderConstants.DISCONNECT_ACTION_INDICATOR);
		else if(aActionIndicator.trim().equalsIgnoreCase(
				SvcOrderConstants.CHANGE_ACTION_INDICATOR))
			aSSVO.put(SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT,
					SvcOrderConstants.CHANGE_ACTION_INDICATOR);
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT)
									+ ">");
		}
		catch(Exception e)
		{
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	protected void prepareSandESectionForFTTX(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject)
	{
		String myMethodName = "ServiceOrderRequestProcessor::prepareSandESection()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		aSSVO.put(SoacServiceOrderConstants.CIRCUIT, reqDataObject
				.getCircuitId().replace('/', '.'));
		try
		{
			myLogger.log(TranBase.LOG_INPUT_DATA,
					"Setting SoacServiceOrderConstants.CIRCUIT=<"
							+ reqDataObject.getCircuitId() + ">");
		}
		catch(Exception e)
		{
		}
		aSSVO
				.put(
						SoacServiceOrderConstants.FACS_WIRE_CENTER,
						aSvcOrdReqHelper
								.formatTelephoneNumber(reqDataObject
										.getServiceLocation().aProviderLocationProperties[0].aPrimaryNpaNxx
										.theValue()));
		try
		{
			aSSVO.put(SoacServiceOrderConstants.RELATED_TDMTN, aSvcOrdReqHelper
					.formatTelephoneNumber(reqDataObject.getTDMTelphoneNumber()
							.theValue()));
		}
		catch(Exception e)
		{
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aTDMTelphoneNumber is Not Available.<<");
		}
		try
		{
			myLogger
					.log(
							TranBase.LOG_INPUT_DATA,
							"Setting SoacServiceOrderConstants.RELATED_TDMTN=<"
									+ aSSVO
											.get(SoacServiceOrderConstants.RELATED_TDMTN)
									+ ">");
		}
		catch(Exception e)
		{
		}
		addOrderActionTypeAndSubActionType(aSSVO, reqDataObject);
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	protected void prepareSandESectionForVOIP(SoacServiceOrderVO aSSVO,
			VoipReqSnEDataObject[] voipSnEData)
	{
		String myMethodName = "ServiceOrderRequestProcessor::prepareSandESection()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		if(voipSnEData != null && voipSnEData.length > 0)
		{
			for(int x = 0; x < voipSnEData.length; x++)
			{
				myLogger.log(LogEventId.INFO_LEVEL_1,
						"Telephone Order Pair info for VOIP 	: " + (x + 1)
								+ " : Number Info");
				myLogger.log(LogEventId.INFO_LEVEL_1, "The TN 						: "
						+ voipSnEData[x].getTelephoneNumber());
				myLogger.log(LogEventId.INFO_LEVEL_1, "The Old Provider 			: "
						+ voipSnEData[x].getOldProvider());
				myLogger.log(LogEventId.INFO_LEVEL_1, "The New Provider 			: "
						+ voipSnEData[x].getNewProvider());
				myLogger.log(LogEventId.INFO_LEVEL_1,
						"The Local Routing Nunber 	: "
								+ voipSnEData[x].getLocalRountingNumber());
				myLogger.log(LogEventId.INFO_LEVEL_1,
						"The Retained Porting Ind 	: "
								+ voipSnEData[x].getLocalRountingNumber());
				myLogger.log(LogEventId.INFO_LEVEL_1,
						"The Non Retained Porting Ind: "
								+ voipSnEData[x].getLocalRountingNumber());
			}
			CVOIPServiceOrderVO[] dummy = new CVOIPServiceOrderVO[voipSnEData.length];
			for(int x = 0; x < voipSnEData.length; x++)
			{
				dummy[x] = new CVOIPServiceOrderVO(voipSnEData[x]
						.getActionIndicator(), aSvcOrdReqHelper
						.formatTelephoneNumber(voipSnEData[x]
								.getTelephoneNumber()), voipSnEData[x]
						.getOldProvider(), voipSnEData[x].getNewProvider(),
						voipSnEData[x].getLocalRountingNumber(), voipSnEData[x]
								.getRetainedPortingIndicator(), voipSnEData[x]
								.getNonRetainedPortingIndicator(),
						voipSnEData[x].getLocalServingOfficeData());
			}
			aSSVO.setVoipServiceOrders(dummy);
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	/**
	 * Adding networking type to echo section following SFAO rule
	 * 
	 * @param aSSVO
	 * @param reqDataObject
	 */
	protected void addNetworkType(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject reqDataObject)
	{
		String myMethodName = "ServiceOrderRequestGenerator.addNetworkType()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		try
		{
			if(reqDataObject.getNetworkType().equalsIgnoreCase(
					SvcOrderConstants.RGPON_NETWORK))
			{
				aSSVO.put(SoacServiceOrderConstants.NETWORK_TYPE,
						SvcOrderConstants.RGPN_NETWORK);
			}
			else if(reqDataObject.getNetworkType().equalsIgnoreCase(
					SvcOrderConstants.FTTPIP_NETWORK))
			{
				aSSVO.put(SoacServiceOrderConstants.NETWORK_TYPE,
						SvcOrderConstants.FTTP_NETWORK);
			}
			else
			{
				aSSVO.put(SoacServiceOrderConstants.NETWORK_TYPE, reqDataObject
						.getNetworkType());
			}
		}
		catch(Exception e)
		{
			myLogger.log(LogEventId.DEBUG_LEVEL_1,
					">>aNetworkType is Not Available.<<" + e.getStackTrace());
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}
	/**
	 * @return
	 * @throws ParserSvcException
	 */
	protected ParserSvc getParserSvc() throws ParserSvcException
	{
		return ParserSvcFactory.getFactory().getParserSvc(myLogger);
	}
	/**
	 * @param reqDataObject
	 */
	protected void setChangeActionIndicator(
			SvcOrdRequestDataObject reqDataObject)
	{
		//Do nothing for version before 2
	}
	/**
	 * @param aSSVO
	 * @param aReqDataObject
	 */
	protected void addOrderActionTypeAndSubActionType(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject aReqDataObject)
	{
		//Do nothing for version before 2
	}
	/**
	 * @param subActionType
	 * @param completeIndicator
	 * @return
	 */
	protected boolean isDueDateChange(String subActionType,
			String orderActionType, boolean completeIndicator)
	{
		return (subActionType != null
				&& subActionType
						.equalsIgnoreCase(SubActionTypeValues.SUB_ACTION_AMEND) && (completeIndicator == false));
	}
	/**
	 * @param subActionType
	 * @param orderActionType
	 * @param completeIndicator
	 * @return
	 */
	protected boolean prepareControlAndEchoForNewconnect(String subActionType,
			String orderActionType, boolean completeIndicator)
	{
		return (orderActionType != null
				&& (orderActionType
						.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_PROVIDE) || orderActionType
						.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_CHANGE))
				&& (subActionType == null) && (completeIndicator == false));
	}
}