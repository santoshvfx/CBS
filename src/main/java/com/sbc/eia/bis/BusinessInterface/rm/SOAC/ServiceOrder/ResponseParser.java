//$Id: ResponseParser.java,v 1.4 2007/12/20 17:18:24 op1664 Exp $
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

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;


/**
 * @author sc8468
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public abstract class ResponseParser implements ResponseParserI{
	
	
	protected Logger myLogger = null;
	protected Hashtable myProperties = null;
	protected SoacServiceOrderVO aResponseSSOVO = null;
	protected SOACServiceOrderResponseParser aSOACResponseParser = null;

	
	
	
	protected int NUM_SEGMENTS = 0; 
	protected int NUM_TF_SEGMENTS = 0;	
	protected int NUM_IF_SEGMENTS = 0;
	protected int NUM_OF_SEGMENTS = 0;
	
	protected static final String TF_SEGMENT_PREFIX = "TF";			
	protected static final String IF_SEGMENT_PREFIX = "IF";			
	protected static final String OF_SEGMENT_PREFIX = "OF";		


	protected final String   SAI_DSLAM_FID	=	"PGS";
	protected final String   SAI_DSLAM_FID_DATA1	=	"73RMB";	
	protected final String   SAI_DSLAM_FID_DATA2	=	"73RMD";		
	protected boolean	SAI_DSLAM_CASE		=	false;
	protected String	SAI_DSLAM_SEGMENT	=	null;
	protected int		SAI_DSLAM_SEGMENT_NUM=	-1;	

	
	protected boolean FTTP_REPONSE_PROCESSED = false;
	protected boolean FTTN_REPONSE_PROCESSED = false;	
	



	protected int getTotalSegments()
	{
		
		int total = 0;
		
		total = numOf_TF_IF_OF_Segments(TF_SEGMENT_PREFIX) + numOf_TF_IF_OF_Segments(IF_SEGMENT_PREFIX);
		
		if (total == 0)
			total =	numOf_TF_IF_OF_Segments(OF_SEGMENT_PREFIX);
		
		return total;
	}
	


	public boolean findAFID_DATA(int numSegments,String aFid, String aFidData)
	{
		String myMethodName = "ResponseParser::findAFID_DATA()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);	
		
		String retVal = null;
		boolean ret = false;
		int index = 1;
		int typesOfSegments = 3;//TF, IF, OF
		int typeNum = 0;
		
		String[] segPrefix = {TF_SEGMENT_PREFIX,IF_SEGMENT_PREFIX,OF_SEGMENT_PREFIX};
		
		if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true)
		{
			while(typeNum < typesOfSegments && ret != true)
			{
				while(index <= numSegments && ret != true)
				{ 
					try
					{
						retVal = (String)aResponseSSOVO.getFloatedFidData("ASGM",segPrefix[typeNum]+index,"PGS");
						if(retVal != null)
							if(retVal.indexOf(SAI_DSLAM_FID_DATA1) != -1)
							{
								ret = true;
								SAI_DSLAM_SEGMENT = segPrefix[typeNum]+index;
								SAI_DSLAM_SEGMENT_NUM = index;
								myLogger.log(LogEventId.DEBUG_LEVEL_1, "from findAFID_DATA, SAI FID Data Found =<" + retVal + ">");							
							}
							else if(retVal.indexOf(SAI_DSLAM_FID_DATA2) != -1)
							{
								ret = true;								
								SAI_DSLAM_SEGMENT = segPrefix[typeNum]+index;							
								SAI_DSLAM_SEGMENT_NUM = index;							
								myLogger.log(LogEventId.DEBUG_LEVEL_1, "from findAFID_DATA, SAI FID Data Found =<" + retVal + ">");
							}	
					}
					catch (Exception e)
					{}
					index++;
				}
				typeNum++;
				index = 1;
			}
	
		}
			myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);		
		
	return ret;	
	}
	
	
	public int numOf_TF_IF_OF_Segments(String segmentPrefix)
	{
	
		String myMethodName = "ResponseParser::numOf_TF_IF_OF_Segments()";	
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		int XFs = 0;
		String [] temp = aSOACResponseParser.getAssgnSectionString();
		int index = 0;
	
		if (temp != null)
		{	
			while(index < temp.length)
			{
				if(temp[index].startsWith(segmentPrefix) || temp[index].startsWith("*"+segmentPrefix) )
					XFs++;
				index++;
			}
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	
		return XFs;
		
	}
	
	
	public String getTaperCode()
	{
		String myMethodName = "ResponseParser::getTaperCode()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
	
			String retVal = null;	
			if (aSOACResponseParser.FCIF_RESPONSE_PROSESSED == true)
			{
				try
				{
					retVal = (String) (String)aResponseSSOVO.getFloatedFidData("ASGM","IF1","TPR");
					if( retVal != null )
						retVal  = retVal.trim();							
					myLogger.log(LogEventId.DEBUG_LEVEL_1, "from IF1 Segment, TaperCode =<" + retVal + ">");
				}
				catch (Exception e)
				{}
				
				if(retVal == null)
					try
					{
						retVal = (String) (String)aResponseSSOVO.getFloatedFidData("ASGM","OF1","TPR");
						if( retVal != null )
							retVal  = retVal.trim();																			
						myLogger.log(LogEventId.DEBUG_LEVEL_1, "from OF1 Segment, TaperCode =<" + retVal + ">");
					}
					catch (Exception e)
					{}			
			}
	
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
			
	
			return retVal;		
		
		
	}
	
	public String getDataAfterNumberOfSpecifiedCharacters(String aString, int aChar, int numberOfInstancesAfter)
	{
		
		int index = 0;
		int previousIndex = 0;
		int numTimes = 0;
		
		String retVal= null;
		for(; numTimes <= numberOfInstancesAfter ; numTimes++)
		{
			previousIndex = index;
			index 	= aString.indexOf(aChar,previousIndex + 1);
			
			if( numTimes == numberOfInstancesAfter)
				if(index != -1)
					retVal= aString.substring(previousIndex+1,index);
				else
					retVal= aString.substring(previousIndex+1);
			
		}
		
		return retVal;
	}

	public String getDataTillNumberOfSpecialCharacters(String aString, int aChar, int numberOfInstancesAfter)
	{
		
		int index = 0;
		int previousIndex = 0;
		int numTimes = 0;
		
		int firstIndex = -1;
		
		String retVal= null;
		for(; numTimes <= numberOfInstancesAfter ; numTimes++)
		{
			previousIndex = index;
			index 	= aString.indexOf(aChar,previousIndex + 1);
			if(numTimes == 0 && index != -1)
				firstIndex = index;
			
			if( numTimes == numberOfInstancesAfter)
				if(index != -1)
					retVal= aString.substring(firstIndex+1,index);
				else
					retVal= aString.substring(firstIndex);
			
		}
		
		return retVal;
	}
	
	public int getNumberOfSpecialChars(String aString, int aChar)
	{
		int index = 0;
		int previousIndex = 0;
		int numTimes = 0;
		
		while(index != -1)
		{
			previousIndex = index;
			index 	= aString.indexOf(aChar,previousIndex + 1);
			numTimes++;
		}
	
		if(index == -1)
			numTimes--;
		
		return numTimes;
	}


}
