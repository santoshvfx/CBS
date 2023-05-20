//$Id: FttpResponseParser.java,v 1.9 2006/10/26 17:26:45 ds4987 Exp $
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
//# 10/24/2006  Doris Sunga			  LS 4 adding GPON

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

import java.util.Hashtable;

import org.omg.CORBA.portable.IDLEntity;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.NetworkTypeHelper;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm_ls_types.FiberSegment;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminal;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminal;

/**
 * @author sc8468
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FttpResponseParser extends ResponseParser {

	//for FTTP  Structure generation
	protected OpticalLineTerminal aOpticalLineTerminal = null;
	protected OpticalNetworkTerminal aOpticalNetworkTerminal = null;
	protected FiberSegment[] aFiberSegments = null;

	/**
	 * Constructor for FttpResponseParser.
	 */
	public FttpResponseParser(
		Hashtable aProperties,
		Logger aLogger,
		SOACServiceOrderResponseParser aSOACResponseParser) {
		myProperties = aProperties;
		myLogger = aLogger;
		this.aSOACResponseParser = aSOACResponseParser;
		aResponseSSOVO = aSOACResponseParser.aResponseSSOVO;
	}

	public void processResponse() {
		String myMethodName = "FttpResponseParser::processFTTPType()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String aId = getFTTP_OLT_ID();
		String tId = getFTTP_OLT_TID();
		String aModel = getFTTP_OLT_Model();
		String aTaperCode = getTaperCode();

		String aOntAId = getFTTP_ONT_AID();
		String aOntId = getFTTP_ONT_ID();
		String aOntModel = getFTTP_ONT_Model();
		String aOntPort = getFTTP_ONT_Port();

		String aOLTPort = getFTTP_OLT_PORT(aOntAId);
		String aONTIndex = getFTTP_ONT_INDEX(aOntAId);

		//LS3 For RPGN Stuff -- Hongmei needs to find out..what are we supposed to do with these
		String aOltShelf = null, aOltSlot = null, aOntSlot  = null;
		//not used right now..as there is no place holder for this..
		aOntSlot = getFTTP_ONT_SLOT(aOntAId);

        int numFiberSegments = 0;

		aOpticalLineTerminal =
			NetworkTypeHelper.createOpticalLineTerminal(
				null,
				aId,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				aOltShelf,
				null,
				aOltSlot,
				null,
				aOLTPort,
				null,
				null,
				null,
				null,
				aModel,
				tId,
				null,
				null,
				null,
				null,
				null);

		aOpticalNetworkTerminal =
			NetworkTypeHelper.createOpticalNetworkTerminal(
				aOntId,
				aOntModel,
				aOntAId,
				aOntPort,
				aONTIndex,
				null);

		NUM_TF_SEGMENTS = numOf_TF_IF_OF_Segments(TF_SEGMENT_PREFIX);

		aFiberSegments = new FiberSegment[NUM_TF_SEGMENTS];

		for (int x = 1; x <= NUM_TF_SEGMENTS; x++) {
			Integer aSegNum = new Integer(x);
			String terID = getFTTPTerminlaID(x);
			String netPort = getFTTPNetworkPort(x);
			String accPort = getFTTPAccessPort(x);
			String inFibCab = getFTTPInFiberCable(x);
			String inStr = getFTTPInStrand(x);

			aFiberSegments[x - 1] =
				NetworkTypeHelper.createFiberSegment(
					aSegNum.toString(),
					terID,
					null,
					netPort,
					accPort,
					inFibCab,
					inStr,
					aSOACResponseParser.CLLI8);

			numFiberSegments++;
		}

 
    
		FiberSegment[] tempFiber = new FiberSegment[numFiberSegments]; 
    
    
		for ( int x = 0; x < numFiberSegments; x++) 
		{ 
			tempFiber[x] = aFiberSegments[x]; 
		}                 
    
		aFiberSegments = tempFiber; 
    
    
		aSOACResponseParser.defectCableList =
			aSOACResponseParser.parseDefectiveCablePairs();

		FTTP_REPONSE_PROCESSED = true;

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

	}

	//for FTTP stuff

	public String getFTTP_OLT_ID() {
		String myMethodName = "FttpResponseParser::getFTTP_OLT_ID()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"IF1",
						"TEA");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTP_OLT_ID =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTP_OLT_TID() {
		String myMethodName = "FttpResponseParser::getFTTP_OLD_TID()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"IF1",
						"TIDC");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTP_OLD_TID =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTP_OLT_Model() {
		String myMethodName = "FttpResponseParser::getFTTP_OLT_Model()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"IF1",
						"CMOD");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTP_OLT_Model =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTP_OLT_PORT(String ONTaId) {
		String myMethodName = "FttpResponseParser::getFTTP_OLT_PORT()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				//if ONTaID is ONT-A-B-C-D-E, then OLT Port is D.
				//for LS3 the OLT Port is A-B-C-D. not just D.
				retVal =
					getDataTillNumberOfSpecialCharacters(ONTaId, '-', 4);

				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTP_OLT_PORT =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTP_ONT_INDEX(String ONTaId) {
		String myMethodName = "FttpResponseParser::getFTTP_ONT_INDEX()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				//if ONTaID is ONT-A-B-C-D-E, then ONT Index is E.	
				retVal =
					getDataAfterNumberOfSpecifiedCharacters(ONTaId, '-', 5);

				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTP_ONT_INDEX =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTP_ONT_AID() {
		String myMethodName = "FttpResponseParser::getFTTP_ONT_AID()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"IF2",
						"AIDI");
				if (retVal != null) {
					retVal = retVal.trim();
					retVal = appendONTSlotAndPortToONTAID(retVal);
				}
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTP_ONT_AID =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String appendONTSlotAndPortToONTAID(String ONTaID) {
		String myMethodName =
			"FttpResponseParser::appendONTSlotAndPortToONTAID()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String appendData = "-1";

		String retVal = ONTaID;

		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			int numPos = getNumberOfSpecialChars(ONTaID, '-');			
			String aNetworkType = aSOACResponseParser.getNetworkType().trim();
				
			// ADDING LS 4 GPON
			if(numPos != 7)
				
				if(aNetworkType.equalsIgnoreCase(SvcOrderConstants.RGPN_NETWORK)
				 || aNetworkType.equalsIgnoreCase(SvcOrderConstants.GPON_NETWORK))
				{
					myLogger.log(LogEventId.DEBUG_LEVEL_1, "ONT AID not in Correct Format For " + aNetworkType +" Network Type.");	
					myLogger.log(LogEventId.DEBUG_LEVEL_1, "Setting the CombinationResponse Indicator to true");										
					aSOACResponseParser.setIsCombinationResponse(true);	
					aSOACResponseParser.setCombinationResponseExceptionCode(ExceptionCode.ERR_RM_INVALID_ONT_AID);
					aSOACResponseParser.setCombinationResponseExceptionMessage("Invalid ONT AID Format From SOAC for NetworkType " + aNetworkType +".");	

					myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
					return ONTaID;
				}


			if (numPos == 5)
					retVal = ONTaID + appendData + appendData;
			else if (numPos == 6)
				retVal = ONTaID + appendData;

		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}

	public String getFTTP_ONT_SLOT(String aOntAId) {
		String myMethodName = "FttpResponseParser::getFTTP_ONT_SLOT()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		// LS 4 GPON
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			if(aSOACResponseParser.getNetworkType().equalsIgnoreCase(SvcOrderConstants.RGPN_NETWORK)
			 || aSOACResponseParser.getNetworkType().equalsIgnoreCase(SvcOrderConstants.GPON_NETWORK))
				try {
					//if ONTaID is ONT-A-B-C-D-E-F-G, then ONT slot is F for RGPON/GPON.	
					if(aSOACResponseParser.isCombinationResponse() != false)
						retVal =
							getDataAfterNumberOfSpecifiedCharacters(aOntAId, '-', 6);

			} catch (Exception e) {
			}
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTP_ONT_SLOT =<" + retVal + ">");
			
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTP_ONT_ID() {
		String myMethodName = "FttpResponseParser::getFTTP_ONT_ID()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"IF2",
						"TEA");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTP_ONT_ID =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTP_ONT_Model() {
		String myMethodName = "FttpResponseParser::getFTTP_ONT_Model()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"IF2",
						"CMOD");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTP_ONT_Model =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTP_ONT_Port() {
		String myMethodName = "FttpResponseParser::getFTTP_ONT_Port()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"IF2",
						"CCP");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTP_ONT_Port =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	//FTTP Segment Info

	public String getFTTPSegmentNumber(int segmentNumber) {
		String myMethodName = "FttpResponseParser::getFTTPSegmentNumber()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"TF" + segmentNumber,
						"CCP");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTPSegmentNumber =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTPTerminlaID(int segmentNumber) {
		String myMethodName = "FttpResponseParser::getFTTPTerminlaID()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"TF" + segmentNumber,
						"TEA");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTPTerminlaID =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTPNetworkPort(int segmentNumber) {
		String myMethodName = "FttpResponseParser::getFTTPNetworkPort()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"TF" + segmentNumber,
						"NPN");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTPNetworkPor =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTPAccessPort(int segmentNumber) {
		String myMethodName = "FttpResponseParser::getFTTPAccessPort()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"TF" + segmentNumber,
						"APN");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTPAccessPort =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTPInFiberCable(int segmentNumber) {
		String myMethodName = "FttpResponseParser::getFTTPInFiberCable()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"TF" + segmentNumber,
						"CA");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTPInFiberCable =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTPInStrand(int segmentNumber) {
		String myMethodName = "FttpResponseParser::getFTTPInStrand()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						"TF" + segmentNumber,
						"PR");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTPInStrand =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public IDLEntity createNetworkStructure() {
		String myMethodName = "FttpResponseParser::getFTTP()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		Fttp aFTTP = null;

		if (FTTP_REPONSE_PROCESSED == true) {
			aFTTP =
				NetworkTypeHelper.createFttp(
					aOpticalNetworkTerminal,
					aOpticalLineTerminal,
					aFiberSegments);

		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return aFTTP;

	}

}
