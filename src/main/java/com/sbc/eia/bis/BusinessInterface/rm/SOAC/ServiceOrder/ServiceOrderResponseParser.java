//$Id: ServiceOrderResponseParser.java,v 1.9.18.1 2012/04/04 21:58:47 js7440 Exp $
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
//# 02/23/2007	Doris Sunga			  replacing System.out calls in line 99
//#									    with myLogger.log(LogEventId.INFO_LEVEL_2
//# 03/27/2007  Rene Duka             PR 19520247: Fixed circuitID format from "." to "/".

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

import java.util.Hashtable;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequest;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParserSvc;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory.ParserSvcFactory;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.accessor.DataAccessorException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.accessor.SoacFcifDataAccessor;
import com.sbc.eia.idl.rm_ls_types.OrderActionTypeValues;
import com.sbc.eia.idl.rm_ls_types.SubActionTypeValues;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
 * @author sc8468
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
//public class ServiceOrderResponseParser extends ServiceOrderTextProcessor  {
public class ServiceOrderResponseParser
{

	protected Logger myLogger = null;
	protected Hashtable myProperties = null;

	//protected ServiceOrderRequestHelper aSvcOrdHelper = null;

	protected ParseRequest aParseResponse;
	protected SoacServiceOrderVO aResponseSSOVO;
	protected SoacFcifDataAccessor aSoacFcifDataAccessor;

	protected boolean FCIF_RESPONSE_PROSESSED = false;

	private String aAsgnString = null;

	private String ErrMsg;
	//for parsing the exception code and description.
	private String errorCode;
	private String errorDesc;

	private boolean errorMessageParsed = false;

	//error code and description are delimted by this char.
	private String errorCodeDescDelim = ":";

	public ServiceOrderResponseParser(Hashtable aProperties, Logger aLogger)
	{
		myProperties = aProperties;
		myLogger = aLogger;
		//aSvcOrdHelper 	= 	new ServiceOrderCommonHelper(aProperties,aLogger);
	}

	/**
	 * Method processFCIFResponseString.
	 * @param responseFCIFString
	 * @param logger
	 * @throws ParserSvcException
	 */
	//Done with Request Now Response.
	public void processFCIFResponseString(String responseFCIFString)
			throws ParserSvcException, DataAccessorException
	{

		String myMethodName = "ServiceOrderResponseParser::processFCIFResponseString()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		//responseFCIFString=
		//	"*C2= CORSO C123456789  D   314991SOAC    SOPSTL    051229    0;%*SC=      ;%*EC=1234567890 0987654321;%*SN=FANK            ;%\r\n---ASGM\r\nG1   CLS 12.ABCD.123456..SW\r\nFA   1121 S MCKNIGHT RD/RT 15/RZ \r\n     13\r\nIOE  ZD00-1-08-14/EXK 314 111/TN 314 537-1129/LPS/TRE PMS.102A.1.1.2\r\n     /DF F23-25-10U05-1-15\r\nG2   WC 314 991\r\nIF1  /CA 73/PR 1479/DF F23-32-\r\n     02L02-4-04/PRQ Y/BP 579/OBP \r\n     328/TEA SAI 9030 CLAYTON RD;\r\n     EXJ/TPR 230503/RMTE D500310\r\n     /4550354/4849085\r\nIF2  /CA 9030C/PR 328/BP 3/PGS 73RMB/CUR E DVV8/TEA F\r\n     1101 S MCKNIGHT RD; PDW/BCF \r\n     BP 2 TEA R 3 THORNDELL/RMTE \r\n     4530541\r\nIF3  /CA 16805C/PR 970/BP 70/TEA WB 16650 FLR 2 ME CHESTERFIELD GROVE RD; CDW/RMTE 4536154";

		//Create Parse Request
		myLogger.log(LogEventId.INFO_LEVEL_2, "The Response String is "
				+ responseFCIFString);
		aParseResponse = new ParseRequest();

		//Populate Parse Request with required attributes

		//Set Logger
		aParseResponse.setLogger(myLogger);

		aParseResponse.setFcifDataString(responseFCIFString);
		aParseResponse.setRegion(SoacServiceOrderConstants.SBCSOUTHWEST);

		//set DataFormat
		aParseResponse.setDataFormat(SoacServiceOrderConstants.SOAC_FCIF);
		//aParseResponse.setDataFormat("SOAC_FCIF");

		//set OperationType
		aParseResponse
				.setOperationType(SoacServiceOrderConstants.SOAC_FCIF_TO_SOAC_VALUEOBJECT);
		ParserSvc aParserSvc;

		aParserSvc = ParserSvcFactory.getFactory().getParserSvc(myLogger);

		aParserSvc.processData(aParseResponse);

		aResponseSSOVO = (SoacServiceOrderVO)aParseResponse.getServiceOrderVo();

		//get an SOACFCIF data accessor.

		aSoacFcifDataAccessor = new SoacFcifDataAccessor(aResponseSSOVO);

		FCIF_RESPONSE_PROSESSED = true;

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		//return aSoacFcifDataAccessor;

	} //end processFCIFResponseString

	/**
	 * Method getDueDate.
	 * @return String
	 */
	public String getDueDate()
	{
		String myMethodName = "ServiceOrderResponseParser::getDueDate()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.DUE_DATE);
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "DueDate =<" + retVal
						+ ">");
			}
			catch(Exception e)
			{
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "DueDate =<" + retVal + ">");
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getCorrectionSuffix.
	 * @return String
	 */
	public String getCorrectionSuffix()
	{
		String myMethodName = "ServiceOrderResponseParser::getCorrectionSuffix()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.CORRECTION_SUFFIX);
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "CorrectionSuffix =<"
						+ retVal + ">");
			}
			catch(Exception e)
			{
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	public String getResendInd()
	{
		String myMethodName = "ServiceOrderResponseParser::getResendInd()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.RESEND_INDICATOR);
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "resendIndicator =<"
						+ retVal + ">");
			}
			catch(Exception e)
			{
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	public String getNetworkType()
	{
		String myMethodName = "ServiceOrderResponseParser::getNetworkType()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				
				retVal = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.NETWORK_TYPE);
				//LS7
				if(retVal.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR))
				{
					retVal = SvcOrderConstants.FTTNBP_NETWORK;
				}
				else if(retVal.equalsIgnoreCase(SvcOrderConstants.FTTP_NETWORK))
				{
					retVal = SvcOrderConstants.FTTPIP_NETWORK;
				}
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "NetworkType =<"
						+ retVal + ">");
				
			}
			catch(Exception e)
			{
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getStatusCode.
	 * @return String
	 */
	//from Status Section
	public String getStatusCode()
	{
		String myMethodName = "ServiceOrderResponseParser::getStatusCode()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.STATUS_CODE);
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "StatusCode =<" + retVal
						+ ">");
			}
			catch(Exception e)
			{
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getFunctionType.
	 * @return String
	 */
	//from Control Header
	public String getFunctionType()
	{
		String myMethodName = "ServiceOrderResponseParser::getFunctionType()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.FUNCTION_TYPE);
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "FunctionType =<"
						+ retVal + ">");
			}
			catch(Exception e)
			{
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getSOACServiceOrderNum.
	 * @return String
	 */
	public String getSOACServiceOrderNum()
	{
		String myMethodName = "ServiceOrderResponseParser::getSOACServiceOrderNum()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM);
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "SOACServiceOrderNum =<"
						+ retVal + ">");
			}
			catch(Exception e)
			{
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getWireCenter.
	 * @return String
	 */
	public String getWireCenter()
	{
		String myMethodName = "ServiceOrderResponseParser::getWireCenter()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.WIRE_CENTER_OR_NPANXX);
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "WireCenter =<" + retVal
						+ ">");
			}
			catch(Exception e)
			{
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getOMSOrderNum.
	 * @return String
	 */
	//from echo section
	public String getOMSOrderNum()
	{
		String myMethodName = "ServiceOrderResponseParser::getOMSOrderNum()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.OMS_ORDER_NUM);
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "OMSOrderNum =<"
						+ retVal + ">");
			}
			catch(Exception e)
			{
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getOMSOrderActionNum.
	 * @return String
	 */
	public String getOMSOrderActionNum()
	{
		String myMethodName = "ServiceOrderResponseParser::getOMSOrderActionNum()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.OMS_ORDER_ACTION_NUM);
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "OMSOrderActionNum =<"
						+ retVal + ">");
			}
			catch(Exception e)
			{
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getTelephoneNum.
	 * @return String
	 */
	//don't know from where are we getting this G1 or T1 LFD?? but works now...
	public String getTelephoneNum()
	{
		String myMethodName = "ServiceOrderResponseParser::getTelephoneNum()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = aSoacFcifDataAccessor.getTelephoneNumber();
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "TN =<" + retVal + ">");
			}
			catch(Exception e)
			{
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getAssgnSectionString.
	 * @return String
	 */
	//from assignement section
	public String[] getAssgnSectionString()
	{
		String myMethodName = "ServiceOrderResponseParser::getAssgnSectionString()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String temp = null;

		//Changed tempArray initialization size to 300
		String[] tempArray = new String[300];
		String[] asgnLines = null;

		String NEWLINE = "\n";
		String CARRIAGE_RETURN = "\r";
		String PLHDR_DELIM = ";}%";
		String ASGM_DELIM = "}%";

		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				temp = aParseResponse.getAssignmentString();
			}
			catch(Exception e)
			{
			}
		}

		if(temp != null)
			if(temp.endsWith(PLHDR_DELIM)
					|| temp.endsWith(PLHDR_DELIM + NEWLINE))
				temp = null;

		//now split the String into String[]
		if(temp != null)
		{
			aAsgnString = temp;
			StringTokenizer temp1 = new StringTokenizer(temp);
			int x = temp1.countTokens();
			int i = 0;
			try
			{
				while(i < x)
				{
					tempArray[i] = temp1.nextToken(NEWLINE);
					i++;
				}
			}
			catch(Exception e)
			{
			}

			if(temp.endsWith(ASGM_DELIM) || temp.endsWith(ASGM_DELIM + NEWLINE))
			{
				asgnLines = new String[i - 1];
				for(x = 0; x < i - 1; x++)
					asgnLines[x] = tempArray[x];
			}
			else
			{
				asgnLines = new String[i];
				for(x = 0; x < i; x++)
					asgnLines[x] = tempArray[x];
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return asgnLines;
	}

	/**
	 * Method getLSCircuitID.
	 * @return String
	 */
	//from assignment section
	public String getLSCircuitID()
	{
		String myMethodName = "ServiceOrderResponseParser::getLSCircuitID()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = aSoacFcifDataAccessor.getLsCircuitId().replace('.',
						'/');
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "LSCircuitID =<"
						+ retVal + ">");
			}
			catch(Exception e)
			{
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	public String getTaperCode()
	{
		String myMethodName = "ServiceOrderResponseParser::getTaperCode()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;

		return retVal;
	}
	/**
	 * Method getF1CODSlamPort.
	 * @return String
	 */
	public String getF1CODSlamPort()
	{
		String myMethodName = "ServiceOrderResponseParser::getF1CODSlamPort()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			if(aSoacFcifDataAccessor.getFacilityType().trim().equals(
					SvcOrderConstants.F1TYPE))
				try
				{
					retVal = aSoacFcifDataAccessor.getPortData();
					myLogger.log(LogEventId.DEBUG_LEVEL_1, "F1CODSlamPort =<"
							+ retVal + ">");
				}
				catch(Exception e)
				{
				}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getFacilityType.
	 * @return String
	 */
	public String getFacilityType()
	{
		String myMethodName = "ServiceOrderResponseParser::getFacilityType()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = aSoacFcifDataAccessor.getFacilityType();
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "FacilityType =<"
						+ retVal + ">");
			}
			catch(Exception e)
			{
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getF1Cable.
	 * @return String
	 */
	public String getF1Cable()
	{
		String myMethodName = "ServiceOrderResponseParser::getF1Cable()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			if(aSoacFcifDataAccessor.getFacilityType().trim().equals(
					SvcOrderConstants.F1TYPE))
			{
				try
				{
					retVal = aSoacFcifDataAccessor.getCableData();
					myLogger.log(LogEventId.DEBUG_LEVEL_1, "F1Cable =<"
							+ retVal + ">");
				}
				catch(Exception e)
				{
				}
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getF1Pair.
	 * @return String
	 */
	public String getF1Pair()
	{
		String myMethodName = "ServiceOrderResponseParser::getF1Pair()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			if(aSoacFcifDataAccessor.getFacilityType().trim().equals(
					SvcOrderConstants.F1TYPE))
			{
				try
				{
					retVal = aSoacFcifDataAccessor.getPairData();
					myLogger.log(LogEventId.DEBUG_LEVEL_1, "F1Pair =<" + retVal
							+ ">");
				}
				catch(Exception e)
				{
				}
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getF2Cable.
	 * @return String
	 */
	public String getF2Cable()
	{
		String myMethodName = "ServiceOrderResponseParser::getF2Cable()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			if(aSoacFcifDataAccessor.getFacilityType().trim().equals(
					SvcOrderConstants.F2TYPE))
			{
				try
				{
					retVal = aSoacFcifDataAccessor.getCableData();
					myLogger.log(LogEventId.DEBUG_LEVEL_1, "F2Cable =<"
							+ retVal + ">");
				}
				catch(Exception e)
				{
				}
			}
		}
		return retVal;
	}

	/**
	 * Method getF2Pair.
	 * @return String
	 */
	public String getF2Pair()
	{
		String myMethodName = "ServiceOrderResponseParser::getF2Pair()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			if(aSoacFcifDataAccessor.getFacilityType().trim().equals(
					SvcOrderConstants.F2TYPE))
			{
				try
				{
					retVal = aSoacFcifDataAccessor.getPairData();
					myLogger.log(LogEventId.DEBUG_LEVEL_1, "F2Pair =<" + retVal
							+ ">");
				}
				catch(Exception e)
				{
				}
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getF2BindingPost.
	 * @return String
	 */
	public String getF2BindingPost()
	{
		String myMethodName = "ServiceOrderResponseParser::getF2BindingPost()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			if(aSoacFcifDataAccessor.getFacilityType().trim().equals(
					SvcOrderConstants.F2TYPE))
				try
				{
					retVal = aSoacFcifDataAccessor.getBindingPostData();
					myLogger.log(LogEventId.DEBUG_LEVEL_1, "F2BindingPost =<"
							+ retVal + ">");
				}
				catch(Exception e)
				{
				}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method getF2SAITerminalID.
	 * @return String
	 */
	public String getF2SAITerminalID()
	{
		String myMethodName = "ServiceOrderResponseParser::getF2SAITerminalID()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			if(aSoacFcifDataAccessor.getFacilityType().trim().equals(
					SvcOrderConstants.F2TYPE))
				try
				{
					retVal = aSoacFcifDataAccessor.getTerminalIdData();
					myLogger.log(LogEventId.DEBUG_LEVEL_1, "F2SAITerminalID =<"
							+ retVal + ">");
				}
				catch(Exception e)
				{
				}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;
	}

	/**
	 * Method getF1FrameLocation.
	 * @return String
	 */
	//all the below are not needed
	public String getF1FrameLocation()
	{
		String myMethodName = "ServiceOrderResponseParser::getF1FrameLocation()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;

		if(FCIF_RESPONSE_PROSESSED == true)
		{
			// don't knwo what to return and i think these are optional fields. So it is o.k. to return null.

		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;
	}

	/**
	 * Method getF2DSLAMTerminalAddress.
	 * @return String
	 */
	public String getF2DSLAMTerminalAddress()
	{
		String myMethodName = "ServiceOrderResponseParser::getF2DSLAMTerminalAddress()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;

		if(FCIF_RESPONSE_PROSESSED == true)
		{
			// don't knwo what to return and i think these are optional fields. So it is o.k. to return null.

		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;
	}

	/**
	 * Method aSAILocation.
	 * @return String
	 */
	public String getF2SAILocation()
	{
		String myMethodName = "ServiceOrderResponseParser::aSAILocation()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;

		//looks like the terminal Id is what is present SAI Location.
		retVal = getF2SAITerminalID();

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;
	}

	/**
	 * Method aSAIDSLAMBindingPost.
	 * @return String
	 */
	public String aSAIDSLAMBindingPost()
	{
		String myMethodName = "ServiceOrderResponseParser::aSAIDSLAMBindingPost()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;

		if(FCIF_RESPONSE_PROSESSED == true)
		{
			if(aSoacFcifDataAccessor.getFacilityType().trim().equals(
					SvcOrderConstants.F2TYPE))
				try
				{
					retVal = aSoacFcifDataAccessor.getOutBindingPostData();
					myLogger.log(LogEventId.DEBUG_LEVEL_1,
							"F2SAIDLAMBindingPost =<" + retVal + ">");
				}
				catch(Exception e)
				{
				}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;
	}

	/**
	 * Method aDSLAMLocation.
	 * @return String
	 */
	public String aDSLAMLocation()
	{
		String myMethodName = "ServiceOrderResponseParser::aDSLAMLocation()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;

		if(FCIF_RESPONSE_PROSESSED == true)
		{
			// don't knwo what to return and i think these are optional fields. So it is o.k. to return null.

		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;
	}

	/**
	 * Method aCODSLAMPort.
	 * @return String
	 */
	public String aCODSLAMPort()
	{
		String myMethodName = "ServiceOrderResponseParser::aCODSLAMPort()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;

		if(FCIF_RESPONSE_PROSESSED == true)
		{
			// don't knwo what to return and i think these are optional fields. So it is o.k. to return null.

		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;
	}
	/**
	 * Method aDefectiveF2BindingPost.
	 * @return String
	 */
	public String aDefectiveF2BindingPost()
	{
		String myMethodName = "ServiceOrderResponseParser::aDefectiveF2BindingPost()";

		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			if(aSoacFcifDataAccessor.getFacilityType().trim().equals(
					SvcOrderConstants.F2TYPE))
				try
				{
					// don't knwo what to return and i think these are optional fields. So it is o.k. to return null.
					//retVal =	aSoacFcifDataAccessor.getBindingPostData();
				}
				catch(Exception e)
				{
				}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method aDefectiveCODSLAMPort.
	 * @return String
	 */
	public String aDefectiveCODSLAMPort()
	{
		String myMethodName = "ServiceOrderResponseParser::aDefectiveCODSLAMPort()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			if(aSoacFcifDataAccessor.getFacilityType().trim().equals(
					SvcOrderConstants.F1TYPE))
				try
				{
					// don't knwo what to return and i think these are optional fields. So it is o.k. to return null.
					//retVal=	aSoacFcifDataAccessor.getPortData();
				}
				catch(Exception e)
				{
				}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	/**
	 * Method aServiceLocation.
	 * @return String
	 */
	public String aServiceLocation()
	{
		String myMethodName = "ServiceOrderResponseParser::aServiceLocation()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;

		if(FCIF_RESPONSE_PROSESSED == true)
		{
			// don't knwo what to return and i think these are optional fields. So it is o.k. to return null.

		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;
	}

	// following for the OMS Email

	public String getOMSOrderActionID()
	{
		String myMethodName = "ServiceOrderResponseParser::getOMSOrderActionID()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return getOMSOrderActionNum();
	}

	public String getOrderActionType()
	{
		String myMethodName = "ServiceOrderResponseParser::getaOrderActionType()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String temp;
		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				temp = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.FUNCTION_TYPE);

				//now determine what kind of Order Action
				//PRE
				if(temp
						.equals(SvcOrderConstants.NEW_CONNECT_DISCONNECT_ACTION_TYPE)) // New Connect
				{
					temp = (String)aResponseSSOVO
							.get(SoacServiceOrderConstants.ACTION_INDICATOR);
					if(temp != null)
					{
						if(temp
								.equals(SvcOrderConstants.NEW_CONNECT_ACTION_INDICATOR))
							retVal = OrderActionTypeValues.ORDER_ACTION_PROVIDE;

						else if(temp
								.equals(SvcOrderConstants.DISCONNECT_ACTION_INDICATOR))
							retVal = OrderActionTypeValues.ORDER_ACTION_CEASE;
					}
					else
					{
						if(aAsgnString != null)
							if(aAsgnString.indexOf("*IF") != 0)
								retVal = OrderActionTypeValues.ORDER_ACTION_PROVIDE;
							else if(aAsgnString.indexOf("*OF") != 0)
								retVal = OrderActionTypeValues.ORDER_ACTION_CEASE;
						;
					}
				}
				else if(temp
						.equals(SvcOrderConstants.CANCELLATION_SUB_ACTION_TYPE)) // CAN
					retVal = SubActionTypeValues.SUB_ACTION_CANCEL;

				else if(temp.equals(SvcOrderConstants.DUE_DATE_SUB_ACTION_TYPE)) //AMEND
					retVal = SubActionTypeValues.SUB_ACTION_AMEND;

				else if(temp
						.equals(SvcOrderConstants.COMPLETION_SUB_ACTION_TYPE)) //PCN
					retVal = null;

				myLogger.log(LogEventId.DEBUG_LEVEL_1, "OrderActionType =<"
						+ retVal + ">");
			}
			catch(Exception e)
			{
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	public String getSOACStatusDecr()
	{
		String myMethodName = "ServiceOrderResponseParser::getSOACStatusDecr()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		String retVal = null;

		//there is no status description for now. so retuening null;

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	public String getExceptionMessage()
	{
		String myMethodName = "ServiceOrderResponseParser::getExceptionMessage()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		String retVal = null;

		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				//these two methods return the same string
				//retVal = (String) aResponseSSOVO.get(SoacServiceOrderConstants.ERR_MSG);
				ErrMsg = aSoacFcifDataAccessor.getErrorMessage();
			}
			catch(Exception e)
			{
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return ErrMsg;

	}

	/**
	 * Method aExceptionCode.
	 * @return String
	 */
	//Exception data
	// this is the only one that is required.. all other are not required...
	public String getExceptionCode()
	{
		String myMethodName = "ServiceOrderResponseParser::aExceptionCode()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		String retVal = null;

		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				//these two methods return the same string
				//retVal = (String) aResponseSSOVO.get(SoacServiceOrderConstants.ERR_MSG);
				retVal = aSoacFcifDataAccessor.getErrorMessage();

				if(retVal != null)
				{
					//see if the error message has already been parsed
					if(errorMessageParsed != true)
					{
						parseException(retVal);
					}
				}
				myLogger.log(LogEventId.DEBUG_LEVEL_1, "ExceptionCode =<"
						+ errorCode + ">");
				retVal = errorCode;

			}
			catch(Exception e)
			{
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;
	}

	/**
	 * Method aExceptionDescription.
	 * @return String
	 */
	public String getExceptionDescription()
	{
		String myMethodName = "ServiceOrderResponseParser::aExceptionDescription()";

		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		String retVal = null;

		if(FCIF_RESPONSE_PROSESSED == true)
		{

			try
			{
				retVal = aSoacFcifDataAccessor.getErrorMessage();

				if(retVal != null)
				{
					//see if the error message has already been parsed
					if(errorMessageParsed != true)
					{
						parseException(retVal);
					}
				}
				myLogger.log(LogEventId.DEBUG_LEVEL_1,
						"getExceptionDescription =<" + errorDesc + ">");
				retVal = errorDesc;
			}
			catch(Exception e)
			{
			}

		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;
	}

	private void parseException(String exceptionMessage)
	{
		String myMethodName = "ServiceOrderResponseParser::parseException()";

		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		//The exception message we get from service handler is like the following.
		//---ERR S001-009: THIS ORDER NUMBER ALREADY EXISTS IN FACS
		//where the errorCode is "S001-009" and ErrorDesc is "THIS ORDER NUMBER ALREADY EXISTS IN FACS".
		//this method is going to do that.

		String temp;

		try
		{
			//stripping the Literal "---ERR"
			temp = exceptionMessage
					.substring(SoacServiceOrderConstants.ERROR_SECTION_TAG
							.length());

			try
			{
				//now get the Exception Code which is delimited by ":", this has to be comfirmed.
				errorCode = temp.substring(0, temp.indexOf(errorCodeDescDelim))
						.trim();

				//now the desc.	+1 for deleting ":"
				errorDesc = temp
						.substring(temp.indexOf(errorCodeDescDelim) + 1).trim();
			}
			catch(Exception e)
			{
				myLogger
						.log(LogEventId.INFO_LEVEL_2,
								"Failure to parse error code from ---ERR text, return as comment.");
				errorCode = "COMMENT";
				errorDesc = temp.trim();
			}

			errorMessageParsed = true;
		}
		catch(Exception e)
		{
			myLogger.log(LogEventId.ERROR,
					"Failed to parse exceptionMessage: [" + exceptionMessage
							+ "]");
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

	}

	/**
	 * Method aOrigination.
	 * @return String
	 */
	public String getOrigination()
	{
		String myMethodName = "ServiceOrderResponseParser::aOrigination()";

		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;

		if(FCIF_RESPONSE_PROSESSED == true)
		{
			// don't knwo what to return and i think these are optional fields. So it is o.k. to return null.

		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;
	}

	/**
	 * Method aSeverity.
	 * @return String
	 */
	public String getSeverity()
	{
		String myMethodName = "ServiceOrderResponseParser::aSeverity()";

		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;

		if(FCIF_RESPONSE_PROSESSED == true)
		{
			// don't knwo what to return and i think these are optional fields. So it is o.k. to return null.

		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return retVal;

	}
	/**
	 * LS6
	 * Get the application indicator
	 * @return the application indicator
	 */
	public String getApplicationIndicator()
	{
		String myMethodName = "ServiceOrderResponseParser::getApplicationIndicator()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = (String)aResponseSSOVO
						.get(SoacServiceOrderConstants.APPLICATION_INDICATOR);
				myLogger.log(LogEventId.DEBUG_LEVEL_2,
						"ApplicationIndicator =<" + retVal + ">");
			}
			catch(Exception e)
			{
				myLogger.log("", e.getMessage());
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}
	/**
	 * LS6 
	 * Get the LST indicator
	 * @return true if LST exist, false otherwise
	 */
	public BooleanOpt getLSTIndicator()
	{
		String myMethodName = "ServiceOrderResponseParser: getLSTIndicator()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		BooleanOpt retVal = new BooleanOpt();
		retVal.theValue(false);
		if(FCIF_RESPONSE_PROSESSED == true)
		{
			if(aResponseSSOVO.getLhFidIndicator("ASGM", "LST"))
			{
				retVal.theValue(true);
			}

		}
		myLogger.log(LogEventId.DEBUG_LEVEL_2, "LSTIndicator =<"
				+ retVal.theValue() + ">");
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}
	/**
	 * LS7
	 * @return raw assignment section
	 */
	public String getRawAssignmentString()
	{
		return aParseResponse.getAssignmentString();
	}

}