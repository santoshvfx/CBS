//$Id: FttnResponseParser.java,v 1.5 2006/07/27 15:01:24 jc1421 Exp $
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

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

import java.util.Hashtable;

import org.omg.CORBA.portable.IDLEntity;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.NetworkTypeHelper;
import com.sbc.eia.idl.rm_ls_types.CopperSegment;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransport;
import com.sbc.eia.idl.rm_ls_types.Fttn;

/**
 * @author sc8468
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FttnResponseParser extends ResponseParser {

	//for processing FTTN case where the prefix can be O or I.
	protected String PREFIX_OF_SEGMENTS= null;			
	
	
	//for FTTN Structure generation
	protected DSLAMTransport aDSLAMTransport = null;
	protected CopperSegment[] aCopperSegments = null;	

	protected String   SAI_DSLAM_PIGTAIL_COPPER_CABLE	= new String();
	

	public FttnResponseParser(
		Hashtable aProperties,
		Logger aLogger,
		SOACServiceOrderResponseParser aSOACResponseParser) {
		myProperties = aProperties;
		myLogger = aLogger;
		this.aSOACResponseParser = aSOACResponseParser;
		aResponseSSOVO = aSOACResponseParser.aResponseSSOVO;
	}


	public void processResponse()
	{
			String myMethodName = "FttnResponseParser::processResponse()";
			myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		

			NUM_IF_SEGMENTS = numOf_TF_IF_OF_Segments(IF_SEGMENT_PREFIX);
			
			int numSegments = NUM_IF_SEGMENTS;
			
			if(NUM_IF_SEGMENTS == 0)
			{
				NUM_OF_SEGMENTS = numOf_TF_IF_OF_Segments(OF_SEGMENT_PREFIX);		
				numSegments = 	NUM_OF_SEGMENTS;
				PREFIX_OF_SEGMENTS = OF_SEGMENT_PREFIX.substring(0,1);
			}
			else 
				PREFIX_OF_SEGMENTS = IF_SEGMENT_PREFIX.substring(0,1);
				
			aCopperSegments = new CopperSegment[numSegments];
			
			
			String aDslam = getFTTN_DSLAM_ID();			
			aDSLAMTransport = NetworkTypeHelper.createDSLAMTransport(aDslam,null,null,
																	null,null,null,
																	null,false,null);
			String aTaperCode = getTaperCode();																
	
			SAI_DSLAM_CASE = isThis_SAI_DSLAM_case(numSegments);

			int numSaiCopperCables = 0; 
			int numCoCopperCables = 0; 
			
 
			CopperSegment[] tempCopper = new CopperSegment[2]; 
		
			
			if(SAI_DSLAM_CASE == true)
			{
	
				for(int x = SAI_DSLAM_SEGMENT_NUM; x<= numSegments; x++)
				{
				
					if(x == SAI_DSLAM_SEGMENT_NUM)
					{
	
						String terId 	= getFTTNTerminalIdentifier(x,PREFIX_OF_SEGMENTS);
						String inBiPost = getFTTNInBindingPost(x,PREFIX_OF_SEGMENTS);
						String OutBiPost= getFTTNOutBindingPost(x-1,PREFIX_OF_SEGMENTS);
						String OutPCol  = getFTTNOutPairColor(x,PREFIX_OF_SEGMENTS);
						String InCab	= getFTTNInCable(x,PREFIX_OF_SEGMENTS);
						String InPair	= getFTTNInPair(x,PREFIX_OF_SEGMENTS);
	
						tempCopper  = NetworkTypeHelper.createPigtailCopperSegment(
															terId,null,
															null,inBiPost,
															OutBiPost,
															OutPCol,
															InCab,
															InPair,
															aSOACResponseParser.CLLI8);	

						SAI_DSLAM_PIGTAIL_COPPER_CABLE = 	InCab;																							
						numSaiCopperCables = 2;															
					}
					else
					{ 
						if(x == SAI_DSLAM_SEGMENT_NUM + 1)
						{
							aCopperSegments[0] = tempCopper[0];
							aCopperSegments[1] = tempCopper[1];	
						}
						Integer aSegNum = new Integer(numSaiCopperCables + 1);						
						
						String terId 	= getFTTNTerminalIdentifier(x,PREFIX_OF_SEGMENTS);
						String inBiPost = getFTTNInBindingPost(x,PREFIX_OF_SEGMENTS);
						String OutBiPost= getFTTNOutBindingPost(x-1,PREFIX_OF_SEGMENTS);
						String OutPCol  = getFTTNOutPairColor(x,PREFIX_OF_SEGMENTS);
						String InCab	= getFTTNInCable(x,PREFIX_OF_SEGMENTS);
						String InPair	= getFTTNInPair(x,PREFIX_OF_SEGMENTS);
										
						aCopperSegments[numSaiCopperCables] =  NetworkTypeHelper.
												createCopperSegment(aSegNum.toString(),
															terId,null,
															null,inBiPost,
															OutBiPost,
															OutPCol,
															InCab,
															InPair,
															aSOACResponseParser.CLLI8);	
						numSaiCopperCables++;									
					}																						
				}
 				//more than 2 segments for sai. below only two segments. just pigtail 
				if(numSaiCopperCables > 2) 
				{ 
					tempCopper = null; 
					tempCopper = new CopperSegment[numSaiCopperCables]; 
    
					for ( int x = 0; x < numSaiCopperCables; x++) 
					{ 
						tempCopper[x] = aCopperSegments[x]; 
					} 
    
					aCopperSegments = tempCopper; 
				} 
				else if (numSaiCopperCables == 2) 
				{ 
					aCopperSegments = tempCopper; 
    
    			} 
								
			}//end of SAI DSLAM Case
			//NOT SAI DSLAM CASE	
			else 
			{		
				for(int x = 1; x <= numSegments; x++)
				{
					Integer aSegNum = new Integer(x);
	
					String terId 	= getFTTNTerminalIdentifier(x,PREFIX_OF_SEGMENTS);
					String inBiPost = getFTTNInBindingPost(x,PREFIX_OF_SEGMENTS);
					String OutBiPost= getFTTNOutBindingPost(x,PREFIX_OF_SEGMENTS);
					String OutPCol  = getFTTNOutPairColor(x,PREFIX_OF_SEGMENTS);
					String InCab	= getFTTNInCable(x,PREFIX_OF_SEGMENTS);
					String InPair	= getFTTNInPair(x,PREFIX_OF_SEGMENTS);
					
					aCopperSegments[x-1] = NetworkTypeHelper.
												createCopperSegment(aSegNum.toString(),
															terId,null,
															null,inBiPost,
															OutBiPost,
															OutPCol,
															InCab,
															InPair,
															aSOACResponseParser.CLLI8);	
					numCoCopperCables ++;															
				}
				tempCopper = null; 
				tempCopper = new CopperSegment[numCoCopperCables]; 
    
				for ( int x = 0; x < numCoCopperCables; x++) 
				{ 
					tempCopper[x] = aCopperSegments[x]; 
				}                           
    
				aCopperSegments = tempCopper; 
						
			}// END of CO DSLAM Case.

			aSOACResponseParser.defectCableList = aSOACResponseParser.parseDefectiveCablePairs();			
			FTTN_REPONSE_PROCESSED = true;
	
			myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);		
			
	}
	
	



	public String getFTTN_DSLAM_ID()
	{
			String myMethodName = "FttnResponseParser::getFTTN_DSLAM_ID()";
			myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
	
			String retVal = null;	
			if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true)
			{
				try
				{
					retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","IOE","TRE");
					if( retVal == null )
						retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","OOE","TRE");
					if( retVal == null )
						retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","IROE","TRE");
					if( retVal == null )
						retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","OROE","TRE");
					if( retVal == null )
						retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","IBOE","TRE");
					if( retVal == null )
						retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","OBOE","TRE");
					if( retVal == null )
						retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","IRBE","TRE");
					if( retVal == null )
						retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","ORBE","TRE");
										
	
					if( retVal != null )
						if(retVal.charAt(0) != 'L') //for CODSLAM the TRE FID value contains 'L' as the first Char.
							retVal = null;
						else	
							retVal  = retVal.trim();											
					myLogger.log(LogEventId.DEBUG_LEVEL_1, "FTTN_DSLAM_ID =<" + retVal + ">");
				}
				catch (Exception e)
				{}
			}
	
			myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
			return retVal;		
		
		
	}



	public String getTelephoneNum()
	{
		String myMethodName = "FttnResponseParser::getTelephoneNum()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
	
		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true)
		{
			try
			{
				retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","IOE","TN");
				if( retVal == null )
					retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","OOE","TN");
				if( retVal == null )
					retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","IROE","TN");
				if( retVal == null )
					retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","OROE","TN");
				if( retVal == null )
					retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","IBOE","TN");
				if( retVal == null )
					retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","OBOE","TN");
				if( retVal == null )
					retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","IRBE","TN");
				if( retVal == null )
					retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM","ORBE","TN");
										
			}
			catch (Exception e)
			{}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}
	

	public String getFTTNFacilitySegmentNumber() {
		String myMethodName =
			"FttnResponseParser::getFTTNFacilitySegmentNumber()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				//retVal = (String) aResponseSSOVO.get(SoacServiceOrderConstants);
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTNFacilitySegmentNumber =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTNTerminalIdentifier(
		int segmentNumber,
		String segFirstLetter) {
		String myMethodName = "FttnResponseParser::getFTTNTerminalIdentifier()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						segFirstLetter + "F" + segmentNumber,
						"TEA");
                if( retVal == null ) 
                	retVal = (String)aResponseSSOVO.getFloatedFidData(
                							"ASGM",segFirstLetter+"F"+segmentNumber,"ENC"); 
		
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTNTerminalIdentifier =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTNInBindingPost(
		int segmentNumber,
		String segFirstLetter) {
		String myMethodName = "FttnResponseParser::getFTTNInBindingPost()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						segFirstLetter + "F" + segmentNumber,
						"BP");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTNInBindingPost =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTNOutBindingPost(
		int segmentNumber,
		String segFirstLetter) {
		String myMethodName = "FttnResponseParser::getFTTNOutBindingPost()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						segFirstLetter + "F" + segmentNumber,
						"OBP");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTNOutBindingPost =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTNOutPairColor(
		int segmentNumber,
		String segFirstLetter) {
		String myMethodName = "FttnResponseParser::getFTTNOutPairColor()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						segFirstLetter + "F" + segmentNumber,
						"OPC");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTNOutPairColor =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTNInCable(int segmentNumber, String segFirstLetter) {
		String myMethodName = "FttnResponseParser::getFTTNInCable()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						segFirstLetter + "F" + segmentNumber,
						"CA");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTNInCable =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}

	public String getFTTNInPair(int segmentNumber, String segFirstLetter) {
		String myMethodName = "FttnResponseParser::getFTTNInPair()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String retVal = null;
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true) {
			try {
				retVal =
					(String) aResponseSSOVO.getFloatedFidData(
						"ASGM",
						segFirstLetter + "F" + segmentNumber,
						"PR");
				if (retVal != null)
					retVal = retVal.trim();
				myLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"FTTNInPair =<" + retVal + ">");
			} catch (Exception e) {
			}
		}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;

	}


	public boolean isThis_SAI_DSLAM_case(int numSegments)
	{
			String myMethodName = "FttnResponseParser::isThis_SAI_DSLAM_case()";
			myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
	
	
		boolean saiDslamCase = false;
		
		String temp = new String();
		
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true)
			{
				try
				{
					saiDslamCase = findAFID_DATA(numSegments,SAI_DSLAM_FID,SAI_DSLAM_FID_DATA1);
					if(saiDslamCase == false)
						saiDslamCase = findAFID_DATA(numSegments,SAI_DSLAM_FID,SAI_DSLAM_FID_DATA2);

					if( saiDslamCase )
						myLogger.log(LogEventId.INFO_LEVEL_1, "The Order is FTTN SAI Based DSLAM CASE");	
					else
						myLogger.log(LogEventId.INFO_LEVEL_1, "The Order is FTTN CO Based DSLAM CASE");	
	
				}
				catch (Exception e)
				{}
			}
	
	
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
	return saiDslamCase;	
	}
	
	public IDLEntity createNetworkStructure()
	{
			String myMethodName = "FttnResponseParser::getFTTN()";
			myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
			Fttn aFTTN = null;
	
			
			if (FTTN_REPONSE_PROCESSED == true)
			{
				aFTTN = NetworkTypeHelper.createFttn(aDSLAMTransport,aCopperSegments);
		
			}
	
			myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	
			return aFTTN ;
	}



	/**
	 * Returns the sAI_DSLAM_PIGTAIL_COPPER_CABLE.
	 * @return String
	 */
	public String getSAI_DSLAM_PIGTAIL_COPPER_CABLE() {
		return SAI_DSLAM_PIGTAIL_COPPER_CABLE;
	}

}
