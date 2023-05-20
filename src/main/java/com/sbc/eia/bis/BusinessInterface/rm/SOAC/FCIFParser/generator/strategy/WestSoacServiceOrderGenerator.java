package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.strategy;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequestI;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.GeneratorException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.SoacFcifGenerator;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SpaceSeparator;


/**
 * @author me8132
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class WestSoacServiceOrderGenerator extends SoacFcifGenerator
{
	int actionType = 0;
	StringBuffer sbServiceOrderText = new StringBuffer(200);
	StringBuffer sbListingSection = new StringBuffer(500);
	StringBuffer sbFcifString = new StringBuffer(1500);
    
/**
 * @roseuid 428B6654001E
 */
public WestSoacServiceOrderGenerator()
{
	super();
	// TODO Auto-generated constructor stub    
}

public void generate(ParseRequestI request) throws GeneratorException
{
	setLogger(request);
	setSoacServiceOrderVo(request);
	actionType = getActionType((String) soacServiceOrderVo.get("FUNCTION_TYPE"),
							   (String)soacServiceOrderVo.get("ACTION_INDICATOR"));

	switch (actionType)
	{
		case NEW_CONNECT_ORDER:
			sbFcifString = createNewConnectOrDisconnectOrder(request.getRegion());
			break;
		case DISCONNECT_ORDER:
			sbFcifString = createNewConnectOrDisconnectOrder(request.getRegion());   
			break;            
		case CANCEL_ORDER:
			sbFcifString = createCancelOrder();   
			break;            
		case CORRECTION_ORDER:
			sbFcifString = createCorrectionOrder(request.getRegion());   
			break;            
		case COMPLETION_ORDER:
			sbFcifString = createCompletionOrder();   
			break;            
	}
	//insure entier string is in upper case before returning to client
	request.setFcifDataString(sbFcifString.toString().toUpperCase());
}

protected StringBuffer generateSvcOrderText()
{
	logger.log(LogEventId.DEBUG_LEVEL_1, "Generating Service Order Text Section.");
	sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);  
	sbServiceOrderText.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.TN_OR_NPANXX).toString());
	sbServiceOrderText.append(SpaceSeparator.S3);
	sbServiceOrderText.append(formatDate.DateFormat1((String)soacServiceOrderVo
			.get(SoacServiceOrderConstants.APPLICATION_DATE).toString()));    
	sbServiceOrderText.append(formatDate.DateFormat1((String)soacServiceOrderVo
			.get(SoacServiceOrderConstants.ORIGINAL_DUE_DATE).toString()));
	sbServiceOrderText.append(SpaceSeparator.S4);
	sbServiceOrderText.append(SpaceSeparator.S6);
	sbServiceOrderText.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.CLASS_OF_SERVICE).toString());
	sbServiceOrderText.append(SpaceSeparator.S9);
	sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);    
	sbServiceOrderText.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM).toString());
	sbServiceOrderText.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.CORRECTION_SUFFIX).toString());
	sbServiceOrderText.append(SpaceSeparator.S17);
    
	if (soacServiceOrderVo.get(SoacServiceOrderConstants.RELATED_SERVICE_ORDER) != null)
	{
		if (!soacServiceOrderVo.get(SoacServiceOrderConstants.RELATED_SERVICE_ORDER).equals(""))
		{
			sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
			sbServiceOrderText.append(SoacServiceOrderConstants.RELATED_ORDER_FID);
			//check for variable length string
			sbServiceOrderText.append(
				tokenizeString(soacServiceOrderVo.get(
						SoacServiceOrderConstants.RELATED_SERVICE_ORDER).toString(),12));
		}
	}
	if (soacServiceOrderVo.get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE) != null)
	{
		if (!soacServiceOrderVo.get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE).equals(""))
		{
			sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
			sbServiceOrderText.append(SoacServiceOrderConstants.SD_FLOATED_FID.substring(1,4));
			sbServiceOrderText.append(SpaceSeparator.S3);
			sbServiceOrderText.append(formatDate.DateFormat1((String)soacServiceOrderVo
					.get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE).toString()));
            
		}
	}
	  if (soacServiceOrderVo.get(SoacServiceOrderConstants.EXCEPTION_ROUTING_INDICATOR) != null)
      {
      	sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
      	sbServiceOrderText.append("PRM   ");
      	sbServiceOrderText.append(soacServiceOrderVo.get(SoacServiceOrderConstants.EXCEPTION_ROUTING_INDICATOR));
      	
      }
	return sbServiceOrderText;
}

protected StringBuffer generateListingSection()
{
	logger.log(LogEventId.DEBUG_LEVEL_1, "Generating Listing Section.");
	sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
	sbListingSection.append(SoacServiceOrderConstants.LIST_SECTION_TAG);
	sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
	sbListingSection.append(SoacServiceOrderConstants.LISTING_ADDRESS_FID);
	//check for variable length string
//	  sbListingSection.append(
//		  tokenizeString(soacServiceOrderVo.get(
//			  SoacServiceOrderConstants.BASIC_ADDRESS).toString(),41));
	sbListingSection.append(
		tokenizeString(soacVal.enhanceBasicAddress((String)soacServiceOrderVo.get(
			SoacServiceOrderConstants.BASIC_ADDRESS).toString()),41));
	if (soacServiceOrderVo.get(SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS) != null)
	{
		if (!soacServiceOrderVo.get(SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS).equals(""))
		{
			sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
			sbListingSection.append(SpaceSeparator.S6);
			//check for variable length string
			sbListingSection.append(
				tokenizeString(soacServiceOrderVo.get(
					SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS).toString(),41));
		}
	}
    
	if (soacServiceOrderVo.get(SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM) != null)
	{
		if (!soacServiceOrderVo.get(SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM).equals(""))
		{
			sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
			sbListingSection.append(SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM_FID);
		   //check for variable length string
			sbListingSection.append(
				tokenizeString(soacServiceOrderVo.get(
					SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM).toString(),41));
		}
	}
	if (soacServiceOrderVo.get(SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO) != null)
	{
		if (!soacServiceOrderVo.get(SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO).equals(""))
		{
			sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
			sbListingSection.append(SoacServiceOrderConstants.LOCATION_FID);
			//check for variable length string
			sbListingSection.append(
				tokenizeString(soacServiceOrderVo.get(
					SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO).toString(), 41));
		}
	}
	return sbListingSection;
}

}
