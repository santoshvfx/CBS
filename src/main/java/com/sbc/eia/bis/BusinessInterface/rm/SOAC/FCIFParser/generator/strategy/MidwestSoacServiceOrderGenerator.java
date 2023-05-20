//$Id: MidwestSoacServiceOrderGenerator.java,v 1.8 2009/03/10 20:38:03 dl8121 Exp $
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
//#      © 2008-2020 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 05/25/2005  SRM Team			Creation
//# 02/04/2008  Ott Phannavong		LS7: Added calls to helper methods in SoacFcifGenerator
//# 01/26/2008  David Lee			LS10:  changed generateSvcOderTextSection and generateSandESection() to meet LS10 requirements.
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.strategy;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequestI;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.GeneratorException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.SoacFcifGenerator;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SpaceSeparator;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrderConstants;
/**
 * @author me8132
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class MidwestSoacServiceOrderGenerator extends SoacFcifGenerator
{
	int actionType = 0;
	StringBuffer sbControlSection = new StringBuffer(64);
	StringBuffer sbServiceOrderText = new StringBuffer(200);
	StringBuffer sbListingSection = new StringBuffer(500);
	StringBuffer sbFcifString = new StringBuffer(1500);
	StringBuffer sbSandESection = new StringBuffer(500);
   
/**
 * @roseuid 428B6654001E
 */
public MidwestSoacServiceOrderGenerator()
{
	 super();
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

protected StringBuffer generateControlSection()
{
	logger.log(LogEventId.DEBUG_LEVEL_1, "Generating Control Section." + 
		"Function Type = " +
		(String) soacServiceOrderVo.
			get(SoacServiceOrderConstants.FUNCTION_TYPE) + 
		", Correction Suffix = " +
		(String) soacServiceOrderVo.
			get(SoacServiceOrderConstants.CORRECTION_SUFFIX));

	sbControlSection.append(SoacServiceOrderConstants.SECTION_BEGIN);
	sbControlSection.append(SoacServiceOrderConstants.CONTROL_HEADER_SECTION_TAG);
	sbControlSection.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.FUNCTION_TYPE).toString());
	sbControlSection.append(SoacServiceOrderConstants.TRANSACTION_TYPE);
	sbControlSection.append(
		soacServiceOrderVo.get(    SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM).toString());
	sbControlSection.append(SpaceSeparator.S3);
	if (soacServiceOrderVo.get(SoacServiceOrderConstants.CORRECTION_SUFFIX) != null &&
	   !soacServiceOrderVo.get(SoacServiceOrderConstants.CORRECTION_SUFFIX).equals(""))
	{   
		sbControlSection.append(
			soacServiceOrderVo.get(SoacServiceOrderConstants.CORRECTION_SUFFIX).toString());
	}
	else
	{
		 sbControlSection.append(SpaceSeparator.S1);   
	}
	sbControlSection.append(SpaceSeparator.S3);
	sbControlSection.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.WIRE_CENTER_OR_NPANXX).toString());
	sbControlSection.append(SoacServiceOrderConstants.ORIGINATING_HOST_CONSTANT);

	//ORIGINATING_HOST_NAME must be five characters in length. So pad field with five space and then
	//append the first five characters to the StringBuffer
	String OriginatingHostName = ((String) soacServiceOrderVo.get(
		SoacServiceOrderConstants.ORIGINATING_HOST_NAME)) + SpaceSeparator.S5;
	sbControlSection.append(OriginatingHostName.substring(0,5));
    
	sbControlSection.append(SoacServiceOrderConstants.RECEIVING_SYSTEM_NAME_CONSTANT);
	sbControlSection.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.ENTITY).toString());
	sbControlSection.append(SpaceSeparator.S2);
	sbControlSection.append(SpaceSeparator.S3);
//	  sbControlSection.append(
//		  soacServiceOrderVo.get(SoacServiceOrderConstants.DUE_DATE).toString());
	sbControlSection.append(formatDate.DateFormat3((String)soacServiceOrderVo
			.get(SoacServiceOrderConstants.DUE_DATE)
			.toString()));
	sbControlSection.append(SpaceSeparator.S4);
	sbControlSection.append(SoacServiceOrderConstants.CONTROL_HEADER_SECTION_END_TAG);

	return sbControlSection;
}

protected StringBuffer generateSvcOrderText()
{
	logger.log(LogEventId.DEBUG_LEVEL_1, "Generating Service Order Text Section.");
	sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
	sbServiceOrderText.append(SpaceSeparator.S3);
	sbServiceOrderText.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.TN_OR_NPANXX).toString());
	sbServiceOrderText.append(SpaceSeparator.S30);
	sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);    
	sbServiceOrderText.append(SpaceSeparator.S4);
	sbServiceOrderText.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM).toString());
	sbServiceOrderText.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.CORRECTION_SUFFIX).toString());
	sbServiceOrderText.append(SpaceSeparator.S15);
	sbServiceOrderText.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.CLASS_OF_SERVICE).toString());
	sbServiceOrderText.append(SpaceSeparator.S12);
	sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);    
	sbServiceOrderText.append(SpaceSeparator.S4);
	sbServiceOrderText.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.APPLICATION_DATE).toString());
	sbServiceOrderText.append(SpaceSeparator.S4);
	sbServiceOrderText.append(
		soacServiceOrderVo.get(SoacServiceOrderConstants.ORIGINAL_DUE_DATE).toString());
	sbServiceOrderText.append(SpaceSeparator.S6);
    
	if (soacServiceOrderVo.get(SoacServiceOrderConstants.RELATED_SERVICE_ORDER) != null)
	{
		if (!soacServiceOrderVo.get(SoacServiceOrderConstants.RELATED_SERVICE_ORDER).equals(""))
		{
			sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
			sbServiceOrderText.append(SoacServiceOrderConstants.RELATED_ORDER_FID.substring(0,5));
			//check for variable length string
			sbServiceOrderText.append(
				tokenizeString(soacServiceOrderVo.get(
						SoacServiceOrderConstants.RELATED_SERVICE_ORDER).toString(),11));
		}
	}
	if (soacServiceOrderVo.get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE) != null)
	{
		if (!soacServiceOrderVo.get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE).equals(""))
		{
			sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
			sbServiceOrderText.append(SoacServiceOrderConstants.SD_FLOATED_FID.substring(1,4));
			sbServiceOrderText.append(SpaceSeparator.S2);
			sbServiceOrderText.append(
				soacServiceOrderVo.get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE).toString());
            
		}
	}
	
	  if (soacServiceOrderVo.get(SoacServiceOrderConstants.EXCEPTION_ROUTING_INDICATOR) != null)
      {
      	sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
      	sbServiceOrderText.append("PRM  ");
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
	sbListingSection.append((SoacServiceOrderConstants.LISTING_ADDRESS_FID).substring(0,5));
	//check for variable length string
	sbListingSection.append(
		tokenizeString(soacServiceOrderVo.get(
			SoacServiceOrderConstants.BASIC_ADDRESS).toString(),30));
	if (soacServiceOrderVo.get(SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS) != null)
	{
		if (!soacServiceOrderVo.get(SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS).equals(""))
		{
			sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
			sbListingSection.append(SpaceSeparator.S5);
			//check for variable length string
			sbListingSection.append(
				tokenizeString(soacServiceOrderVo.get(
					SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS).toString(),30));
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
					SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM).toString(),30));
		}
	}
	if (soacServiceOrderVo.get(SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO) != null)
	{
		if (!soacServiceOrderVo.get(SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO).equals(""))
		{
			sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
			sbListingSection.append(SoacServiceOrderConstants.LOCATION_FID.substring(0,5));
			//check for variable length string
			sbListingSection.append(
				tokenizeString(soacServiceOrderVo.get(
					SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO).toString(), 30));
		}
	}
	return sbListingSection;
}

public void validateControlSectionInput() 
	throws GeneratorException
{
	if (soacServiceOrderVo.get(SoacServiceOrderConstants.FUNCTION_TYPE) == null
		|| soacServiceOrderVo.get(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM) == null
		|| soacServiceOrderVo.get(SoacServiceOrderConstants.CORRECTION_SUFFIX) == null
		|| soacServiceOrderVo.get(SoacServiceOrderConstants.WIRE_CENTER_OR_NPANXX) == null
		|| soacServiceOrderVo.get(SoacServiceOrderConstants.ORIGINATING_HOST_NAME) == null
		|| soacServiceOrderVo.get(SoacServiceOrderConstants.ENTITY) == null
		|| soacServiceOrderVo.get(SoacServiceOrderConstants.DUE_DATE) == null)
	{
		 logger.log(
			LogEventId.EXCEPTION,
			"SoacFcifGenerator::validateControlSectionInput - One or more " +
			"fields in the Control Section data input is missing.");
		 throw new GeneratorException(
			"SoacFcifGenerator::validateControlSectionInput - One or more " +
			"fields in the Control Section data input is missing.");       
	}
}

/* (non-Javadoc)
 * @see com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.SoacFcifGenerator#generateSandESection()
 */
protected StringBuffer generateSandESection()
{
    String methodName = "MidwestSoacServiceOrderGenerator: generateSandESection()";
	logger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);
	
	String networkType = soacServiceOrderVo.get(SoacServiceOrderConstants.NETWORK_TYPE).toString();
	
	if (networkType.equalsIgnoreCase(SvcOrderConstants.IPCO)||networkType.equalsIgnoreCase(SvcOrderConstants.IPRT))
    {
    	sbSandESection.append(generateSandEsectionIPDSLAM());
    }
    else 
    {
    	sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
    	sbSandESection.append(SoacServiceOrderConstants.SandE_SECTION_TAG);
    	sbSandESection.append(SoacServiceOrderConstants.NEWLINE);		
    	sbSandESection.append(
    			soacServiceOrderVo.get(SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT).toString());
    	sbSandESection.append(SpaceSeparator.S4);
    	if (networkType.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR)
    			||networkType
    			.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR))
    	{
    		sbSandESection.append(SoacServiceOrderConstants.FTTN_AS3NE);
    	}
    	else if ((networkType.equalsIgnoreCase(SoacServiceOrderConstants.FTTP_NETWORK_INDICATOR))  
    			|| (networkType.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR)) 
    			||(networkType.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR)))
    	{
    		sbSandESection.append(SoacServiceOrderConstants.FTTP_AS3NF);
    	}

    	sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
    	sbSandESection.append(SpaceSeparator.S5);
    	sbSandESection.append(SoacServiceOrderConstants.CLASS_OF_SERVICE_FLOATED_FID); 
    	//check for variable length string
    	sbSandESection.append(
    			tokenizeString(soacServiceOrderVo.get(
    					SoacServiceOrderConstants.CIRCUIT).toString(), 22));

    	appendBCCR(soacServiceOrderVo, sbSandESection);

    	sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
    	sbSandESection.append(SpaceSeparator.S5);
    	sbSandESection.append(SoacServiceOrderConstants.LOCAL_SERVING_OFFICE_FLOATED_FID);
    	sbSandESection.append(
    			soacServiceOrderVo.get(SoacServiceOrderConstants.FACS_WIRE_CENTER).toString());

//  	The RTID FID would be used on inward LightSpeed FTTN orders and LightSpeed RGPN, GPON orders	
    	if ((soacServiceOrderVo.get(SoacServiceOrderConstants.ACTION_INDICATOR).toString().equalsIgnoreCase(SoacServiceOrderConstants.IN_ACTION_CODE)) 
    			&&(networkType.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR)) 
    			||(networkType.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR)) 
    			||(networkType.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR))
    			||(networkType.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR)))
    	{

    		if (soacServiceOrderVo.get(SoacServiceOrderConstants.RELATED_TDMTN) != null)
    		{
    			if (!soacServiceOrderVo.get(SoacServiceOrderConstants.RELATED_TDMTN).equals(""))
    			{
    				sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
    				sbSandESection.append(SpaceSeparator.S5);
    				sbSandESection.append(SoacServiceOrderConstants.RELATED_CIRCUIT_FID);
    				sbSandESection.append(
    						soacServiceOrderVo.get(SoacServiceOrderConstants.RELATED_TDMTN).toString());
    			}
    		}
    	}
    	if ((networkType.equalsIgnoreCase(SoacServiceOrderConstants.FTTP_NETWORK_INDICATOR))  
    			||(networkType.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR)) 
    			||(networkType.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR)))
    	{
    		sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
    		sbSandESection.append(SpaceSeparator.S5);
    		sbSandESection.append(SoacServiceOrderConstants.BROADBAND_ROUTING_FID);	

    	}
    	sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
    	sbSandESection.append(SpaceSeparator.S5);
    	sbSandESection.append(SoacServiceOrderConstants.FIBER_CUSTOMER_INDICATOR_FID);

    	if(networkType.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR)
    			|| networkType.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR))
    	{
    		sbSandESection.append(SoacServiceOrderConstants.FTTN_INDICATOR);
    	}
    	else if((networkType.equalsIgnoreCase(SoacServiceOrderConstants.FTTP_NETWORK_INDICATOR))  
    			||(networkType.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR)) 
    			||(networkType.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR)))
    	{
    		sbSandESection.append(SoacServiceOrderConstants.FTTP_INDICATOR);
    	}

    	if (soacServiceOrderVo.get(SoacServiceOrderConstants.ADDITIONAL_LINE_FID) != null)
    	{
    		if (!soacServiceOrderVo.get(SoacServiceOrderConstants.ADDITIONAL_LINE_FID).equals(""))
    		{	
    			sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
    			sbSandESection.append(SpaceSeparator.S5);
    			sbSandESection.append(SoacServiceOrderConstants.ADDITIONAL_LINE_FID);
    		}
    	}	
	appendADDITIONAL_LINE_FID(soacServiceOrderVo, sbSandESection);
    appendNewConnectDisconnectForFTTNBP(soacServiceOrderVo, sbSandESection);
    appendSandESection(soacServiceOrderVo, sbSandESection);
    }
	logger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
	return sbSandESection;
}

protected StringBuffer generateSandEsectionIPDSLAM()
{	
	StringBuffer TempSandAppend  = new StringBuffer(400);
	TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
    TempSandAppend.append(SoacServiceOrderConstants.SandE_SECTION_TAG);
    TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
    TempSandAppend.append(soacServiceOrderVo.get(
            SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT).toString());
    TempSandAppend.append(SpaceSeparator.S4);
    TempSandAppend.append(soacServiceOrderVo
			.get(SoacServiceOrderConstants.CLASS_OF_SERVICE));
    TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
    TempSandAppend.append(SpaceSeparator.S5);
    TempSandAppend
    .append(SoacServiceOrderConstants.CLASS_OF_SERVICE_FLOATED_FID);
    //check for variable length string
    TempSandAppend.append(tokenizeString(soacServiceOrderVo.get(
    SoacServiceOrderConstants.CIRCUIT).toString(), 22));
    TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
    TempSandAppend.append(SpaceSeparator.S5);
    TempSandAppend
    .append(SoacServiceOrderConstants.LOCAL_SERVING_OFFICE_FLOATED_FID);
    TempSandAppend.append(soacServiceOrderVo.get(
    SoacServiceOrderConstants.FACS_WIRE_CENTER).toString());
    if ((soacServiceOrderVo.get(SoacServiceOrderConstants.ACTION_INDICATOR)
            .toString()
            .equalsIgnoreCase(SoacServiceOrderConstants.IN_ACTION_CODE)))
    {                     
    	if (soacServiceOrderVo.get(SoacServiceOrderConstants.DRYLOOP_RELATED_CIRCUIT_FID)!= null)
    	{
    		TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
    		TempSandAppend.append(SpaceSeparator.S5);
    		TempSandAppend.append(SoacServiceOrderConstants.DRYLOOP_RELATED_CIRCUIT_FID);
    		TempSandAppend.append(soacServiceOrderVo.get(SoacServiceOrderConstants.DRYLOOP_RELATED_CIRCUIT_FID).toString());

    	}
    	else if(soacServiceOrderVo.get(SoacServiceOrderConstants.RELATED_TDMTN) != null)
    	{
    		if(!soacServiceOrderVo.get(
    				SoacServiceOrderConstants.RELATED_TDMTN).equals(""))
    		{
    			TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
    			TempSandAppend.append(SpaceSeparator.S5);
    			TempSandAppend
    			.append(SoacServiceOrderConstants.RELATED_CIRCUIT_FID);
    			TempSandAppend
    			.append(soacServiceOrderVo.get(
    					SoacServiceOrderConstants.RELATED_TDMTN)
    					.toString());
    		}
    	}
    }
    TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
    TempSandAppend.append(SpaceSeparator.S5);
    TempSandAppend.append(SoacServiceOrderConstants.GENERAL_FACILITIES_FID);
    TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
    TempSandAppend.append(SpaceSeparator.S5);
    TempSandAppend.append(SoacServiceOrderConstants.DSL_PROVISIONING_FID);
    TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
    TempSandAppend.append(SpaceSeparator.S5);
    TempSandAppend.append(SoacServiceOrderConstants.FIBER_CUSTOMER_INDICATOR);
    if ((soacServiceOrderVo.get(SoacServiceOrderConstants.ACTION_INDICATOR)
            .toString()
            .equalsIgnoreCase(SoacServiceOrderConstants.IN_ACTION_CODE))&&
            soacServiceOrderVo.get(SoacServiceOrderConstants.INTERCEPT_REDIRECT_INDICATOR)== "true")
    {
    	TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
    	TempSandAppend.append(SpaceSeparator.S5);
    	TempSandAppend.append(SoacServiceOrderConstants.INTERCEPT_REDIRECT_FID);
    }	
    return TempSandAppend;
}
}
