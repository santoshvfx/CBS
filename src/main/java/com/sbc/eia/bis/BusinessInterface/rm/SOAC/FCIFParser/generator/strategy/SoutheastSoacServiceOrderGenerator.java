//$Id: SoutheastSoacServiceOrderGenerator.java,v 1.8 2009/03/10 20:38:30 dl8121 Exp $
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
 * @author sd8583
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SoutheastSoacServiceOrderGenerator extends SoacFcifGenerator
{

    int actionType = 0;
    StringBuffer sbServiceOrderText = new StringBuffer(200);
    StringBuffer sbListingSection = new StringBuffer(500);
    StringBuffer sbFcifString = new StringBuffer(1500);
    StringBuffer sbSandESection = new StringBuffer(500);

    /**
     * @roseuid 428B6654001E
     */
    public SoutheastSoacServiceOrderGenerator()
    {
        super();
        // TODO Auto-generated constructor stub    
    }

    public void generate(ParseRequestI request) throws GeneratorException
    {
        setLogger(request);
        setSoacServiceOrderVo(request);
        actionType = getActionType((String) soacServiceOrderVo
                .get("FUNCTION_TYPE"), (String) soacServiceOrderVo
                .get("ACTION_INDICATOR"));

        switch(actionType)
        {
        case NEW_CONNECT_ORDER:
            sbFcifString = createNewConnectOrDisconnectOrder(request
                    .getRegion());
            break;
        case DISCONNECT_ORDER:
            sbFcifString = createNewConnectOrDisconnectOrder(request
                    .getRegion());
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

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.srm.serviceorder.generator.SoacFcifGenerator#generateSvcOrderText()
     */
    protected StringBuffer generateSvcOrderText()
    {
        logger.log(LogEventId.DEBUG_LEVEL_1,
                "Generating Service Order Text Section.");

        sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
        sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
        sbServiceOrderText.append(SpaceSeparator.S1);
        sbServiceOrderText.append(soacServiceOrderVo.get(
                SoacServiceOrderConstants.TN_OR_NPANXX).toString());
        sbServiceOrderText.append(SpaceSeparator.S16);
        sbServiceOrderText.append(soacServiceOrderVo.get(
                SoacServiceOrderConstants.APPLICATION_DATE).toString());
        sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
        sbServiceOrderText.append(SpaceSeparator.S1);
        sbServiceOrderText.append(soacServiceOrderVo.get(
                SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM).toString());
        sbServiceOrderText.append(soacServiceOrderVo.get(
                SoacServiceOrderConstants.CORRECTION_SUFFIX).toString());
        sbServiceOrderText.append(soacServiceOrderVo.get(
                SoacServiceOrderConstants.CLASS_OF_SERVICE).toString());
        sbServiceOrderText.append(SpaceSeparator.S9);
        //		sbServiceOrderText.append(
        //			soacServiceOrderVo
        //				.get(SoacServiceOrderConstants.ORIGINAL_DUE_DATE)
        //				.toString());					

        sbServiceOrderText.append(soacServiceOrderVo.get(
                SoacServiceOrderConstants.ORIGINAL_DUE_DATE).toString());
        sbServiceOrderText.append(SpaceSeparator.S1);

        if(soacServiceOrderVo
                .get(SoacServiceOrderConstants.RELATED_SERVICE_ORDER) != null)
        {
            if(!soacServiceOrderVo.get(
                    SoacServiceOrderConstants.RELATED_SERVICE_ORDER).equals(""))
            {
                sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
                sbServiceOrderText
                        .append(SoacServiceOrderConstants.RELATED_ORDER_FID
                                .substring(0, 5));
                //check for variable length string
                sbServiceOrderText.append(tokenizeString(soacServiceOrderVo
                        .get(SoacServiceOrderConstants.RELATED_SERVICE_ORDER)
                        .toString(), 11));
            }
        }

        if(soacServiceOrderVo.get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE) != null)
        {
            if(!soacServiceOrderVo.get(
                    SoacServiceOrderConstants.SUBSEQ_DUE_DATE).equals(""))
            {
                sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
                sbServiceOrderText
                        .append(SoacServiceOrderConstants.SD_FLOATED_FID
                                .substring(1, 3));
                sbServiceOrderText.append(SpaceSeparator.S3);
                sbServiceOrderText.append(soacServiceOrderVo
                        .get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE));

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

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.srm.serviceorder.generator.SoacFcifGenerator#generateListingSection()
     */
    protected StringBuffer generateListingSection()
    {
        logger.log(LogEventId.DEBUG_LEVEL_1, "Generating Listing Section.");
        sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
        sbListingSection.append(SoacServiceOrderConstants.LIST_SECTION_TAG);
        sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
        sbListingSection.append((SoacServiceOrderConstants.LISTING_ADDRESS_FID)
                .substring(0, 5));
        //check for variable length string
        sbListingSection.append(tokenizeString(soacServiceOrderVo.get(
                SoacServiceOrderConstants.BASIC_ADDRESS).toString(), 30));
        if(soacServiceOrderVo
                .get(SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS) != null)
        {
            if(!soacServiceOrderVo.get(
                    SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS)
                    .equals(""))
            {
                sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
                sbListingSection.append(SpaceSeparator.S5);
                //check for variable length string
                sbListingSection.append(tokenizeString(soacServiceOrderVo.get(
                        SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS)
                        .toString(), 30));
            }
        }
        if(soacServiceOrderVo.get(SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM) != null)
        {
            if(!soacServiceOrderVo.get(
                    SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM).equals(""))
            {
                sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
                sbListingSection
                        .append(SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM_FID);
                //check for variable length string
                sbListingSection.append(tokenizeString(soacServiceOrderVo.get(
                        SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM)
                        .toString(), 30));
            }
        }
        if(soacServiceOrderVo
                .get(SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO) != null)
        {
            if(!soacServiceOrderVo.get(
                    SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO)
                    .equals(""))
            {
                sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
                sbListingSection.append(SoacServiceOrderConstants.LOCATION_FID
                        .substring(0, 5));
                //check for variable length string
                sbListingSection.append(tokenizeString(soacServiceOrderVo.get(
                        SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO)
                        .toString(), 30));
            }
        }
        return sbListingSection;
    }

    /* Genetate S and E section
     * @see com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.SoacFcifGenerator#generateSandESection()
     */
    protected StringBuffer generateSandESection()
    {
        String methodName = "SoutheastSoacServiceOrderGenerator: generateSandESection()";
        logger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

        
        sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
        sbSandESection.append(SoacServiceOrderConstants.SandE_SECTION_TAG);

        //NEW_CONNECT_DISCONNECT is I, O, or C
        sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
        sbSandESection.append(soacServiceOrderVo.get(
                SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT).toString());
        sbSandESection.append(SpaceSeparator.S4);

        String networkType = soacServiceOrderVo.get(
                SoacServiceOrderConstants.NETWORK_TYPE).toString();
        if (networkType.equalsIgnoreCase(SvcOrderConstants.IPCO)||networkType.equalsIgnoreCase(SvcOrderConstants.IPRT))
        {
        	sbSandESection.append(generateSandEsectionIPDSLAM());
        }
        else 
        {

        	if(networkType
        			.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR)
        			|| networkType
        			.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR))
        	{
        		sbSandESection.append(SoacServiceOrderConstants.FTTN_AS3NE);
        	}
        	else if((networkType
        			.equalsIgnoreCase(SoacServiceOrderConstants.FTTP_NETWORK_INDICATOR))
        			|| (networkType
        					.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR))
        					|| (networkType
        							.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR)))
        	{
        		sbSandESection.append(SoacServiceOrderConstants.FTTP_AS3NF);
        	}

        	sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
        	sbSandESection.append(SpaceSeparator.S5);
        	sbSandESection
        	.append(SoacServiceOrderConstants.CLASS_OF_SERVICE_FLOATED_FID);
        	//check for variable length string
        	sbSandESection.append(tokenizeString(soacServiceOrderVo.get(
        			SoacServiceOrderConstants.CIRCUIT).toString(), 22));
        	//LS7
        	appendBCCR(soacServiceOrderVo, sbSandESection);

        	sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
        	sbSandESection.append(SpaceSeparator.S5);
        	sbSandESection
        	.append(SoacServiceOrderConstants.LOCAL_SERVING_OFFICE_FLOATED_FID);
        	sbSandESection.append(soacServiceOrderVo.get(
        			SoacServiceOrderConstants.FACS_WIRE_CENTER).toString());

        	//The RTID FID would be used on inward LightSpeed FTTN orders and LightSpeed RGPN, GPON orders			
        	if((soacServiceOrderVo.get(SoacServiceOrderConstants.ACTION_INDICATOR)
        			.toString()
        			.equalsIgnoreCase(SoacServiceOrderConstants.IN_ACTION_CODE))
        			&& ((networkType
        					.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR))
        					|| (networkType
        							.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR)) 
        							|| (networkType
        									.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR))
        									||(networkType.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR))))
        	{

        		if(soacServiceOrderVo.get(SoacServiceOrderConstants.RELATED_TDMTN) != null)
        		{
        			if(!soacServiceOrderVo.get(
        					SoacServiceOrderConstants.RELATED_TDMTN).equals(""))
        			{
        				sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
        				sbSandESection.append(SpaceSeparator.S5);
        				sbSandESection
        				.append(SoacServiceOrderConstants.RELATED_CIRCUIT_FID);
        				sbSandESection
        				.append(soacServiceOrderVo.get(
        						SoacServiceOrderConstants.RELATED_TDMTN)
        						.toString());
        			}
        		}
        	}
        	if((networkType.equalsIgnoreCase("FTTP"))
        			|| (networkType.equalsIgnoreCase("RGPN"))
        			|| (networkType.equalsIgnoreCase("GPON")))
        	{
        		sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
        		sbSandESection.append(SpaceSeparator.S5);
        		sbSandESection
        		.append(SoacServiceOrderConstants.BROADBAND_ROUTING_FID);

        	}
        	sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
        	sbSandESection.append(SpaceSeparator.S5);
        	sbSandESection
        	.append(SoacServiceOrderConstants.FIBER_CUSTOMER_INDICATOR_FID);

        	if((networkType
        			.equalsIgnoreCase(SoacServiceOrderConstants.FTTP_NETWORK_INDICATOR))
        			|| (networkType
        					.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR))
        					|| (networkType
        							.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR)))
        	{
        		sbSandESection.append(SoacServiceOrderConstants.FTTP_INDICATOR);
        	}
        	else
        	{
        		sbSandESection.append(SoacServiceOrderConstants.FTTN_INDICATOR);
        	}

        	if(soacServiceOrderVo
        			.get(SoacServiceOrderConstants.ADDITIONAL_LINE_FID) != null)
        	{
        		if(!soacServiceOrderVo.get(
        				SoacServiceOrderConstants.ADDITIONAL_LINE_FID).equals(""))
        		{
        			sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
        			sbSandESection.append(SpaceSeparator.S5);
        			sbSandESection
        			.append(SoacServiceOrderConstants.ADDITIONAL_LINE_FID);
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