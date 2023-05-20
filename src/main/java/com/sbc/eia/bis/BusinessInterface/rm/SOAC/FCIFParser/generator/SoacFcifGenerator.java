//$Id: SoacFcifGenerator.java,v 1.17 2009/09/01 23:19:37 sl7683 Exp $
//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\generator\\strategy\\SoacFcifGenerator.java
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
//# 02/04/2008  Ott Phannavong		LS7: changed generateSandESection() and generateRemarkSection() to accommodate SFAO2 requirement
//# 03/26/2008  Doris Sunga			LS7 Defect 89216: add calling of appendBCCR() 
//# 03/31/2008  Jon Costa			DR89624: Commented out code that repeats BCCR fid for FTTN-BP.
//# 06/06/2008  Ott Phannavong		LS8: CRC18775 remove /SSM 
//# 01/26/2008  David Lee			LS10:  changed generateSandESection() to meet LS10 requirements
//# 08/14/2009  Sheilla Lirio 		LS11 DR137691: sFAO3/pFAON3 methods not able to include /DTID in the SOAC FCIF request.
//# 09/01/2009  Sheilla Lirio		PR25160550: updated generateRemarksSection() to return DSL Disconnect TN (if given in the client input) in the FCIF Order for all NTI. 
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator;

import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequestI;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.ParserConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.GeneratorException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SpaceSeparator;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.util.FormatDate;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.util.SoacValidator;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrderConstants;

/**
 * Created on May 12, 2005 To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * 
 * @author ns3580 To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class SoacFcifGenerator implements GeneratorI
{
	protected Logger logger = null;
	protected SoacServiceOrderVO soacServiceOrderVo = null;
	protected FormatDate formatDate = new FormatDate();
	protected SoacValidator soacVal = new SoacValidator();
	StringBuffer sbControlSection = new StringBuffer(64);
	StringBuffer sbSpecialConditionSection = new StringBuffer(12);
	StringBuffer sbEchoSection = new StringBuffer(44);
	protected StringBuffer sbRemarksSection = new StringBuffer(500);
	protected StringBuffer sbSandESection = new StringBuffer(500);
	int actionType = 0;

	/**
	 * @roseuid 428B6654001E
	 */
	public SoacFcifGenerator()
	{
		super();
	}

	/**
	 * @param request
	 * @throws com.sbc.eia.bis.srm.serviceorder.exceptions.GeneratorException
	 * @roseuid 4284F13101BE
	 */
	public void generate(ParseRequestI request) throws GeneratorException
	{

	}

	/**
	 * @param String
	 *            finctionType, String actionIndicator
	 * @roseuid 428A2B2601BD
	 */
	public int getActionType(String functionType, String actionIndicator)
	{
		if(functionType.toUpperCase().equals("PRE"))
		{
			if(actionIndicator.toUpperCase().equals("I"))
			{
				actionType = 1;
			}
			else
			{
				actionType = 2;
			}
		}
		else
		{
			if(functionType.toUpperCase().equals("CAN"))
			{
				actionType = 3;
			}
			else
			{
				if(functionType.toUpperCase().equals("COR"))
				{
					actionType = 4;
				}
				else
				{
					//PCN
					actionType = 5;
				}
			}
		}
		return actionType;
	}

	public StringBuffer createNewConnectOrDisconnectOrder(String region)
			throws GeneratorException
	{
		validateControlSectionInput();
		validateEchoSectionInput();
		validateSvcOrderTextSectionInput();
		validateListingSectionInput();
		//CR13440 validateRemarksSectionInput();
		validateSandESectionInput();

		StringBuffer sbNewConnectOrDisconnect = new StringBuffer(1500);

		sbNewConnectOrDisconnect.append((Object)generateControlSection());
		sbNewConnectOrDisconnect.append((Object)generateSpecialSection());
		sbNewConnectOrDisconnect.append((Object)generateEchoSection());
		sbNewConnectOrDisconnect.append((Object)generateSvcOrderText());
		sbNewConnectOrDisconnect.append((Object)generateListingSection());
		if((region.equalsIgnoreCase(ParserConstants.SW_REGION))
				|| (region.equalsIgnoreCase(ParserConstants.CVOIP)))
		{
			sbNewConnectOrDisconnect.append((Object)generateRemarksSection());
			sbNewConnectOrDisconnect.append((Object)generateSandESection());
		}
		else
		{
			sbNewConnectOrDisconnect.append((Object)generateSandESection());
			sbNewConnectOrDisconnect.append((Object)generateRemarksSection());
		}
		sbNewConnectOrDisconnect.append(SoacServiceOrderConstants.NEWLINE);
		return sbNewConnectOrDisconnect;
	}

	public StringBuffer createCancelOrder() throws GeneratorException
	{
		validateControlSectionInput();
		validateEchoSectionInput();

		StringBuffer sbCancel = new StringBuffer(120);
		sbCancel.append((Object)generateControlSection());
		sbCancel.append((Object)generateSpecialSection());
		sbCancel.append((Object)generateEchoSection());
		sbCancel.append(SoacServiceOrderConstants.NEWLINE);

		return sbCancel;
	}

	public StringBuffer createCorrectionOrder(String region)
			throws GeneratorException
	{
		validateControlSectionInput();
		validateEchoSectionInput();
		validateSvcOrderTextSectionInput();
		validateListingSectionInput();
		// CR 13440 validateRemarksSectionInput();
		validateSandESectionInput();

		StringBuffer sbCorrection = new StringBuffer(1500);
		sbCorrection.append((Object)generateControlSection());
		sbCorrection.append((Object)generateSpecialSection());
		sbCorrection.append((Object)generateEchoSection());
		sbCorrection.append((Object)generateSvcOrderText());
		sbCorrection.append((Object)generateListingSection());
		if((region.equalsIgnoreCase(ParserConstants.SW_REGION))
				|| (region.equalsIgnoreCase(ParserConstants.CVOIP)))
		{
			sbCorrection.append((Object)generateRemarksSection());
			sbCorrection.append((Object)generateSandESection());
		}
		else
		{
			sbCorrection.append((Object)generateSandESection());
			sbCorrection.append((Object)generateRemarksSection());
		}
		sbCorrection.append(SoacServiceOrderConstants.NEWLINE);
		return sbCorrection;
	}

	public StringBuffer createCompletionOrder() throws GeneratorException
	{
		validateControlSectionInput();
		validateEchoSectionInput();

		StringBuffer sbCompletion = new StringBuffer(120);
		sbCompletion.append((Object)generateControlSection());
		sbCompletion.append((Object)generateSpecialSection());
		sbCompletion.append((Object)generateEchoSection());
		sbCompletion.append(SoacServiceOrderConstants.NEWLINE);

		return sbCompletion;
	}

	protected StringBuffer generateControlSection()
	{
		logger.log(LogEventId.DEBUG_LEVEL_1, "Generating Control Section."
				+ "Function Type = "
				+ (String)soacServiceOrderVo
						.get(SoacServiceOrderConstants.FUNCTION_TYPE)
				+ ", Correction Suffix = "
				+ (String)soacServiceOrderVo
						.get(SoacServiceOrderConstants.CORRECTION_SUFFIX));

		sbControlSection.append(SoacServiceOrderConstants.SECTION_BEGIN);
		sbControlSection
				.append(SoacServiceOrderConstants.CONTROL_HEADER_SECTION_TAG);
		sbControlSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.FUNCTION_TYPE).toString());
		sbControlSection.append(SoacServiceOrderConstants.TRANSACTION_TYPE);
		sbControlSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM).toString());
		sbControlSection.append(SpaceSeparator.S3);
		sbControlSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.CORRECTION_SUFFIX).toString());

		sbControlSection.append(SpaceSeparator.S3);
		sbControlSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.WIRE_CENTER_OR_NPANXX).toString());
		sbControlSection
				.append(SoacServiceOrderConstants.ORIGINATING_HOST_CONSTANT);

		//ORIGINATING_HOST_NAME must be five characters in length. So pad field
		// with five space and then
		//append the first five characters to the StringBuffer
		String OriginatingHostName = ((String)soacServiceOrderVo
				.get(SoacServiceOrderConstants.ORIGINATING_HOST_NAME))
				+ SpaceSeparator.S5;
		sbControlSection.append(OriginatingHostName.substring(0, 5));
		sbControlSection
				.append(SoacServiceOrderConstants.RECEIVING_SYSTEM_NAME_CONSTANT);

		sbControlSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.ENTITY_PLATFORM).toString());
		sbControlSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.ENTITY).toString());

		sbControlSection.append(SpaceSeparator.S3);
		//	  sbControlSection.append(
		//		  soacServiceOrderVo.get(SoacServiceOrderConstants.DUE_DATE).toString());

		sbControlSection.append(formatDate
				.DateFormat3((String)soacServiceOrderVo.get(
						SoacServiceOrderConstants.DUE_DATE).toString()));
		sbControlSection.append(SpaceSeparator.S4);
		sbControlSection
				.append(SoacServiceOrderConstants.CONTROL_HEADER_SECTION_END_TAG);

		return sbControlSection;
	}

	protected StringBuffer generateSpecialSection()
	{
		logger.log(LogEventId.DEBUG_LEVEL_1, "Generating Special Section.");

		sbSpecialConditionSection
				.append(SoacServiceOrderConstants.SECTION_BEGIN);
		sbSpecialConditionSection
				.append(SoacServiceOrderConstants.SPECIAL_CONDITION_SECTION_TAG);
		if(soacServiceOrderVo
				.get(SoacServiceOrderConstants.SPECIAL_SECTION_IND) != null)
		{
			if(!soacServiceOrderVo.get(
					SoacServiceOrderConstants.SPECIAL_SECTION_IND).equals(""))
			{

				sbSpecialConditionSection.append(tokenizeString(
						soacServiceOrderVo.get(
								SoacServiceOrderConstants.SPECIAL_SECTION_IND)
								.toString(), 6));
				int len = 6 - soacServiceOrderVo.get(
						SoacServiceOrderConstants.SPECIAL_SECTION_IND)
						.toString().length();
				for(int i = 0; i < len; i++)
				{
					sbSpecialConditionSection.append(SpaceSeparator.S1);
				}

			}
		}
		else
		{
			sbSpecialConditionSection.append(SpaceSeparator.S6);
		}
		sbSpecialConditionSection.append(SoacServiceOrderConstants.SECTION_END);

		return sbSpecialConditionSection;
	}

	protected StringBuffer generateEchoSection()
	{
		logger.log(LogEventId.DEBUG_LEVEL_1, "Generating Echo Section.");

		sbEchoSection.append(SoacServiceOrderConstants.SECTION_BEGIN);
		sbEchoSection.append(SoacServiceOrderConstants.ECHO_SECTION_TAG);
		sbEchoSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.OMS_ORDER_NUM).toString());
		sbEchoSection.append(SpaceSeparator.S1);
		sbEchoSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.OMS_ORDER_ACTION_NUM).toString());
		sbEchoSection.append(SpaceSeparator.S1);
		sbEchoSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.NETWORK_TYPE).toString());
		sbEchoSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.REGION_INDICATOR).toString());
		sbEchoSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.RESEND_INDICATOR).toString());
		sbEchoSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.APPLICATION_INDICATOR).toString());
		sbEchoSection.append(SoacServiceOrderConstants.SECTION_END);

		return sbEchoSection;
	}

	protected abstract StringBuffer generateSvcOrderText();

	protected abstract StringBuffer generateListingSection();

	/**
	 * @return StringBuffer containing strings which goes into remark section
	 */
	protected StringBuffer generateRemarksSection()
	{
		String methodName = "SoacFcifGenerator: generateRemarksSection()";
		logger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

		sbRemarksSection.append(SoacServiceOrderConstants.NEWLINE);
		sbRemarksSection.append(SoacServiceOrderConstants.REMARKS_SECTION_TAG);
		sbRemarksSection.append(SoacServiceOrderConstants.NEWLINE);

		String regionIndicator = (String)soacServiceOrderVo
				.get(SoacServiceOrderConstants.REGION_INDICATOR);
		if((regionIndicator
				.equalsIgnoreCase(SoacServiceOrderConstants.E_REGION_INDICATOR))
				|| (regionIndicator
						.equalsIgnoreCase(SoacServiceOrderConstants.M_REGION_INDICATOR))
				|| (regionIndicator
						.equalsIgnoreCase(SoacServiceOrderConstants.B_REGION_INDICATOR)))
		{
			sbRemarksSection.append((SoacServiceOrderConstants.REMARKS_FID)
					.substring(0, 5));
			sbRemarksSection
					.append(SoacServiceOrderConstants.LIGHTSPEED_REMARK);
			sbRemarksSection.append(soacServiceOrderVo.get(
					SoacServiceOrderConstants.OMS_ORDER_NUM).toString());
			sbRemarksSection
					.append(SoacServiceOrderConstants.SEPARATOR_CONSTANT);
			sbRemarksSection.append(SoacServiceOrderConstants.NEWLINE);
			sbRemarksSection.append(SpaceSeparator.S5);
		}
		else if((regionIndicator
				.equalsIgnoreCase(SoacServiceOrderConstants.W_REGION_INDICATOR))
				|| (regionIndicator
						.equals(SoacServiceOrderConstants.S_REGION_INDICATOR))
				|| (regionIndicator.equals(SoacServiceOrderConstants.NO_REGION)))
		{
			sbRemarksSection.append(SoacServiceOrderConstants.REMARKS_FID);
			sbRemarksSection
					.append(SoacServiceOrderConstants.LIGHTSPEED_REMARK);
			sbRemarksSection.append(soacServiceOrderVo.get(
					SoacServiceOrderConstants.OMS_ORDER_NUM).toString());
			sbRemarksSection
					.append(SoacServiceOrderConstants.SEPARATOR_CONSTANT);
			sbRemarksSection.append(SoacServiceOrderConstants.NEWLINE);
			sbRemarksSection.append(SpaceSeparator.S6);
		}
		
		sbRemarksSection.append(soacServiceOrderVo.get(
				SoacServiceOrderConstants.OMS_ORDER_ACTION_NUM).toString());
		if(actionType == 1 || actionType == 4)
		{	
			String disConnectTnValue = null;
			if ((String)soacServiceOrderVo.get(SoacServiceOrderConstants.DSL_DISCONNECT_TN) != null)
			{
				disConnectTnValue = (String)soacServiceOrderVo
					.get(SoacServiceOrderConstants.DSL_DISCONNECT_TN);
			}
			
			if(disConnectTnValue != null)
			{
				sbRemarksSection.append(SoacServiceOrderConstants.NEWLINE);
				if(regionIndicator
						.equals(SoacServiceOrderConstants.W_REGION_INDICATOR)
						|| regionIndicator
								.equals(SoacServiceOrderConstants.S_REGION_INDICATOR)
						|| regionIndicator
								.equals(SoacServiceOrderConstants.NO_REGION))
					sbRemarksSection
							.append(SoacServiceOrderConstants.REMARKS_FID);
				else
					sbRemarksSection
							.append(SoacServiceOrderConstants.REMARKS_FID
									.substring(0, 5));
				sbRemarksSection
						.append(SoacServiceOrderConstants.DSL_DISCONNECT);
				sbRemarksSection.append(SoacServiceOrderConstants.NEWLINE);
				if(regionIndicator
						.equals(SoacServiceOrderConstants.W_REGION_INDICATOR)
						|| regionIndicator
								.equals(SoacServiceOrderConstants.S_REGION_INDICATOR)
						|| regionIndicator
								.equals(SoacServiceOrderConstants.NO_REGION))
				{
					sbRemarksSection.append(SpaceSeparator.S6);
				}
				else
				{
					sbRemarksSection.append(SpaceSeparator.S5);
				}

				sbRemarksSection
						.append(SoacServiceOrderConstants.DSL_DISCONNECT_TN);
				sbRemarksSection.append(disConnectTnValue.substring(0, 3));
				sbRemarksSection.append(" ");
				sbRemarksSection.append(disConnectTnValue.substring(3, 6));
				sbRemarksSection.append("-");
				sbRemarksSection.append(disConnectTnValue.substring(6));

			}
		}
		
		//LS7
		appendBillingAccountNumber(soacServiceOrderVo, sbRemarksSection);

		logger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
		return sbRemarksSection;
	}

	/**
	 * @return StringBuffer containing all string which go into S and E section
	 */
	protected StringBuffer generateSandESection()
	{
		String methodName = "SoacFcifGenerator:generateSandESection()";
		logger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

		String networkType = soacServiceOrderVo.get(
				SoacServiceOrderConstants.NETWORK_TYPE).toString();
		
		if (networkType.equalsIgnoreCase(SvcOrderConstants.IPCO)||networkType.equalsIgnoreCase(SvcOrderConstants.IPRT))
        {
        	sbSandESection.append(generateSandEsectionIPDSLAM());
        }
		else
		{
			sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
			sbSandESection.append(SoacServiceOrderConstants.SandE_SECTION_TAG);
			sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
			sbSandESection.append(soacServiceOrderVo.get(
					SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT).toString());

			sbSandESection.append(SpaceSeparator.S5);
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
			sbSandESection.append(SpaceSeparator.S6);
			sbSandESection
			.append(SoacServiceOrderConstants.CLASS_OF_SERVICE_FLOATED_FID);

			//check for variable length string
			sbSandESection.append(tokenizeString(soacServiceOrderVo.get(
					SoacServiceOrderConstants.CIRCUIT).toString(), 22));

			appendBCCR(soacServiceOrderVo, sbSandESection);

			String actionIndicator = getActionIndicator(soacServiceOrderVo);
			//LS7
			String oldNetworkType = getOldNetWorkType(soacServiceOrderVo);

			boolean isFromFTTNBPToFTTN = (oldNetworkType
					.equalsIgnoreCase(SoacServiceOrderConstants.FTTNBP_INDICATOR) && networkType
					.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR));

			sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
			sbSandESection.append(SpaceSeparator.S6);
			sbSandESection
			.append(SoacServiceOrderConstants.LOCAL_SERVING_OFFICE_FLOATED_FID);
			sbSandESection.append(soacServiceOrderVo.get(
					SoacServiceOrderConstants.FACS_WIRE_CENTER).toString());

			//The RTID FID would be used on inward LightSpeed FTTN orders and
			// LightSpeed RGPN, GPON orders
			if((actionIndicator.equalsIgnoreCase(SoacServiceOrderConstants.IN_ACTION_CODE))
					&& ((networkType
							.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR))
							|| (networkType
									.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR)) 
									|| (networkType
											.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR))))
			{

				if(soacServiceOrderVo.get(SoacServiceOrderConstants.RELATED_TDMTN) != null)
				{
					if(!soacServiceOrderVo.get(
							SoacServiceOrderConstants.RELATED_TDMTN).equals(""))
					{
						sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
						sbSandESection.append(SpaceSeparator.S6);
						sbSandESection
						.append(SoacServiceOrderConstants.RELATED_CIRCUIT_FID);
						sbSandESection
						.append(soacServiceOrderVo.get(
								SoacServiceOrderConstants.RELATED_TDMTN)
								.toString());
					}
				}
			}
			if((networkType
					.equalsIgnoreCase(SoacServiceOrderConstants.FTTP_NETWORK_INDICATOR))
					|| (networkType
							.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR))
							|| (networkType
									.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR)))
			{
				sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
				sbSandESection.append(SpaceSeparator.S6);
				sbSandESection
				.append(SoacServiceOrderConstants.BROADBAND_ROUTING_FID);

			}
			sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
			sbSandESection.append(SpaceSeparator.S6);
			sbSandESection
			.append(SoacServiceOrderConstants.FIBER_CUSTOMER_INDICATOR_FID);

			if(networkType
					.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR)
					|| networkType
					.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR))
			{
				sbSandESection.append(SoacServiceOrderConstants.FTTN_INDICATOR);
			}
			else if((networkType
					.equalsIgnoreCase(SoacServiceOrderConstants.FTTP_NETWORK_INDICATOR))
					|| (networkType
							.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR))
							|| (networkType
									.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR)))
			{
				sbSandESection.append(SoacServiceOrderConstants.FTTP_INDICATOR);
			}

			if(soacServiceOrderVo
					.get(SoacServiceOrderConstants.ADDITIONAL_LINE_FID) != null)
			{
				if(!soacServiceOrderVo.get(
						SoacServiceOrderConstants.ADDITIONAL_LINE_FID).equals(""))
				{
					sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
					sbSandESection.append(SpaceSeparator.S6);
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

	public void validateControlSectionInput() throws GeneratorException
	{
		if(soacServiceOrderVo.get(SoacServiceOrderConstants.FUNCTION_TYPE) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.CORRECTION_SUFFIX) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.WIRE_CENTER_OR_NPANXX) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.ORIGINATING_HOST_NAME) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.ENTITY_PLATFORM) == null
				|| soacServiceOrderVo.get(SoacServiceOrderConstants.ENTITY) == null
				|| soacServiceOrderVo.get(SoacServiceOrderConstants.DUE_DATE) == null)
		{
			logger
					.log(
							LogEventId.EXCEPTION,
							"SoacFcifGenerator::validateControlSectionInput - One or more "
									+ "fields in the Control Section data input is missing.");
			throw new GeneratorException(
					"SoacFcifGenerator::validateControlSectionInput - One or more "
							+ "fields in the Control Section data input is missing.");
		}
	}

	public void validateEchoSectionInput() throws GeneratorException
	{
		if(soacServiceOrderVo.get(SoacServiceOrderConstants.OMS_ORDER_NUM) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.OMS_ORDER_ACTION_NUM) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.NETWORK_TYPE) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.REGION_INDICATOR) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.RESEND_INDICATOR) == null)
		{
			logger
					.log(
							LogEventId.EXCEPTION,
							"SoacFcifGenerator::validateEchoSectionInput - Error validating Echo Section "
									+ "data input, OMS order number or action number is missing.");
			throw new GeneratorException(
					"SoacFcifGenerator::validateEchoSectionInput - Error validating Echo Section "
							+ "data input, OMS order number or action number is missing.");
		}
	}

	public void validateSvcOrderTextSectionInput() throws GeneratorException
	{
		if(!(soacServiceOrderVo.get(SoacServiceOrderConstants.NETWORK_TYPE) == null)
				&& (soacServiceOrderVo.get(
						SoacServiceOrderConstants.NETWORK_TYPE).toString()
						.equals("VOIP")))
		{
			if(soacServiceOrderVo.get(SoacServiceOrderConstants.TN_OR_NPANXX) == null
					|| soacServiceOrderVo
							.get(SoacServiceOrderConstants.APPLICATION_DATE) == null
					|| soacServiceOrderVo
							.get(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM) == null
					|| soacServiceOrderVo
							.get(SoacServiceOrderConstants.CORRECTION_SUFFIX) == null
					|| soacServiceOrderVo
							.get(SoacServiceOrderConstants.ORIGINAL_DUE_DATE) == null)
			{
				logger
						.log(
								LogEventId.EXCEPTION,
								"SoacFcifGenerator::validateSvcOrderTextSectionInput - One or more fields "
										+ "in the service order text section data input is missing.");
				throw new GeneratorException(
						"SoacFcifGenerator::validateSvcOrderTextSectionInput - One or more fields "
								+ "in the service order text section data input is missing.");
			}

		}
		else if(soacServiceOrderVo.get(SoacServiceOrderConstants.TN_OR_NPANXX) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.CLASS_OF_SERVICE) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.APPLICATION_DATE) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.CORRECTION_SUFFIX) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.ORIGINAL_DUE_DATE) == null)
		{
			logger
					.log(
							LogEventId.EXCEPTION,
							"SoacFcifGenerator::validateSvcOrderTextSectionInput - One or more fields "
									+ "in the service order text section data input is missing.");
			throw new GeneratorException(
					"SoacFcifGenerator::validateSvcOrderTextSectionInput - One or more fields "
							+ "in the service order text section data input is missing.");
		}
	}

	public void validateListingSectionInput() throws GeneratorException
	{
		if(soacServiceOrderVo.get(SoacServiceOrderConstants.BASIC_ADDRESS) == null)
		{
			logger.log(LogEventId.EXCEPTION,
					"SoacFcifGenerator::validateListingSectionInput - Basic address field "
							+ "in the listing section data input is missing.");
			throw new GeneratorException(
					"SoacFcifGenerator::validateListingSectionInput - Basic address field "
							+ "in the listing section data input is missing.");
		}
	}

	public void validateRemarksSectionInput() throws GeneratorException
	{
		if(soacServiceOrderVo
				.get(SoacServiceOrderConstants.REMARKS_DISCONNECT_TN) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.OMS_ORDER_NUM) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.OMS_ORDER_ACTION_NUM) == null)
		{
			logger.log(LogEventId.EXCEPTION,
					"SoacFcifGenerator::validateRemarksSectionInput - One or more fields "
							+ "in the remarks section data input is missing.");
			throw new GeneratorException(
					"SoacFcifGenerator::validateRemarksSectionInput - One or more fields "
							+ "in the remarks section data input is missing.");
		}
	}

	public void validateSandESectionInput() throws GeneratorException
	{
		if(!(soacServiceOrderVo.get(SoacServiceOrderConstants.NETWORK_TYPE) == null)
				&& (soacServiceOrderVo.get(
						SoacServiceOrderConstants.NETWORK_TYPE).toString()
						.equals("VOIP")))
		{
			if(soacServiceOrderVo
					.get(SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT) == null)
			{
				logger
						.log(
								LogEventId.EXCEPTION,
								"SoacFcifGenerator::validateSandESectionInput - One or more fields "
										+ "in the service and equipment section data input is missing.");
				throw new GeneratorException(
						"SoacFcifGenerator::validateSandESectionInput - One or more fields "
								+ "in the service and equipment section data input is missing.");
			}

		}
		else if(soacServiceOrderVo
				.get(SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT) == null
				|| soacServiceOrderVo.get(SoacServiceOrderConstants.CIRCUIT) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.FACS_WIRE_CENTER) == null
				|| soacServiceOrderVo
						.get(SoacServiceOrderConstants.NETWORK_TYPE) == null)
		{
			logger
					.log(
							LogEventId.EXCEPTION,
							"SoacFcifGenerator::validateSandESectionInput - One or more fields "
									+ "in the service and equipment section data input is missing.");
			throw new GeneratorException(
					"SoacFcifGenerator::validateSandESectionInput - One or more fields "
							+ "in the service and equipment section data input is missing.");
		}
	}

	public String tokenizeString(String parseData, int maxLength)
	{
		String buildString = "";
		String str = "";
		String nextStringToken = "";
		String space = " ";
		String region = "";
		StringTokenizer stringTokenizer = new StringTokenizer(parseData, " ");

		while(stringTokenizer.hasMoreTokens())
		{
			nextStringToken = stringTokenizer.nextToken();

			if((str.length() + nextStringToken.length()) > maxLength)
			{
				if(str.endsWith(" "))
				{
					//1-30-2007 Original design called for a new line character
					// to
					//be added so that a second line would be produced.
					//Then at some time in the past the code was changed to
					// exclude the
					//new line character. Now today we are adding the new line
					// logic back.
					buildString = buildString
							+ str.substring(0, str.length() - 1)
							+ SoacServiceOrderConstants.NEWLINE;
					//buildString = buildString + str.substring(0, str.length()
					// - 1);
				}
				else
				{
					buildString = buildString + str
							+ SoacServiceOrderConstants.NEWLINE;
					//buildString = buildString + str;
				}
				//If the reqion is Midwest or East the service order is
				// indented 5 spaces
				region = (String)soacServiceOrderVo
						.get(SoacServiceOrderConstants.REGION_INDICATOR
								.toString());
				if(region.equals("M") || region.equals("E"))
				{
					str = "     " + nextStringToken + space;
				}
				//for West, Southwest and Viop the service order is indented 6
				// spaces
				else
				{
					str = "      " + nextStringToken + space;
				}
			}
			else
			{
				//check to see if adding a space will put you over maxLength
				str = str + nextStringToken + space;
			}
		}
		//    buildString = buildString + str.substring(0,str.length()-1) +
		// SoacServiceOrderConstants.NEWLINE;
		buildString = buildString + str.substring(0, str.length() - 1);
		return buildString;
	}

	public void setSoacServiceOrderVo(ParseRequestI request)
	{
		soacServiceOrderVo = (SoacServiceOrderVO)request.getServiceOrderVo();
		String regionIndicator = (String)soacServiceOrderVo
				.get(SoacServiceOrderConstants.REGION_INDICATOR);
	}

	public void setLogger(ParseRequestI request)
	{
		this.logger = request.getLogger();
	}
	/**
	 * @param aSoacServcieVo
	 * @param aRemarkSection
	 */
	protected void appendBillingAccountNumber(
			SoacServiceOrderVO aSoacServcieVo, StringBuffer aRemarkSection)
	{
		/*
		 * Do nothing
		 */
	}
	/**
	 * Append billing account
	 * @param aSoacServcieVo
	 * @param aRemarkSection
	 */
	protected void appendBillingAccountNumber2(
			SoacServiceOrderVO aSoacServiceVo, StringBuffer aRemarkSection)
	{
		String methodName = "SoacFcifGenerator:appendBillingAccountNumber2()";
		logger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

		aRemarkSection.append(SoacServiceOrderConstants.NEWLINE);
		//default for REMARKS_FID is 6 spaces
		String regionIndicator = getRegionIndicator(aSoacServiceVo);
		if(regionIndicator.equals(SoacServiceOrderConstants.W_REGION_INDICATOR)
				|| regionIndicator
						.equals(SoacServiceOrderConstants.S_REGION_INDICATOR)
				|| regionIndicator.equals(SoacServiceOrderConstants.NO_REGION))
		{
			aRemarkSection.append(SoacServiceOrderConstants.REMARKS_FID);
		}
		else
		{
			//For SE, E, Midwest
			aRemarkSection.append(SoacServiceOrderConstants.REMARKS_FID
					.substring(0, 5));
		}
		aRemarkSection.append(SoacServiceOrderConstants.OMS_BAN_LABEL);
		aRemarkSection.append(aSoacServiceVo.get(
				SoacServiceOrderConstants.BILLING_ACCOUNT_NUMBER).toString());

		logger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
	}
	/**
	 * @param aSoacServiceOrderVo
	 * @param aSandESection
	 */
	protected void appendSandESection(SoacServiceOrderVO aSoacServiceOrderVo,
			StringBuffer aSandESection)
	{
		/*
		 * Do nothing
		 */
	}
	/**
	 * Add addition fields to S and E section
	 * 
	 * @param aSandESection
	 */
	protected void appendSandESection2(SoacServiceOrderVO aSoacServiceOrderVo,
			StringBuffer aSandESection)
	{
		String methodName = "SoacFcifGenerator.appendSandESection2()";
		logger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

		String oldNetworkType = getOldNetWorkType(aSoacServiceOrderVo);

		String networkType = aSoacServiceOrderVo.get(
				SoacServiceOrderConstants.NETWORK_TYPE).toString();
		boolean isFromFTTNBPToFTTN = (oldNetworkType
				.equalsIgnoreCase(SoacServiceOrderConstants.FTTNBP_INDICATOR) && networkType
				.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR));

		boolean isChangeOrChangeAmendOrder = isChangeOrChangeAmend(aSoacServiceOrderVo);

		String spaceSeparator = spaceIndex(aSoacServiceOrderVo);
		String spaceSeparatorForLineWithNOActionIndicator = spaceIndex(aSoacServiceOrderVo)
				+ " ";
		//1
		if(isChangeOrChangeAmendOrder)
		{
//			if(getActionIndicator(aSoacServiceOrderVo).equalsIgnoreCase(
//					SoacServiceOrderConstants.IN_ACTION_CODE))
//			{
//				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
//				aSandESection
//						.append(spaceSeparatorForLineWithNOActionIndicator); 
//				aSandESection
//						.append(SoacServiceOrderConstants.SPECIAL_SAFEGUARD_MEASURE_FID);
//			}
			//oldNetworkType=FTTN networkType=FTTNBP
			boolean isFromFTTNToFTTNBP = (oldNetworkType
					.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR) && networkType
					.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR));

			//oldNetworkType=FTTNBP networkType=FTTN
			String regionIndicator = (String)aSoacServiceOrderVo
					.get(SoacServiceOrderConstants.REGION_INDICATOR);

			if(isFromFTTNToFTTNBP || isFromFTTNBPToFTTN)
			{

				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
				aSandESection.append(SoacServiceOrderConstants.TO_ACTION_CODE);

				aSandESection.append(spaceSeparator);
				aSandESection.append(SoacServiceOrderConstants.FTTN_AS3NE);

				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
				aSandESection
						.append(spaceSeparatorForLineWithNOActionIndicator); 
				aSandESection
						.append(SoacServiceOrderConstants.CLASS_OF_SERVICE_FLOATED_FID);
				//check for variable length string
				//primary circuit ID
				aSandESection.append(tokenizeString(aSoacServiceOrderVo.get(
						SoacServiceOrderConstants.CIRCUIT).toString(), 22));

				String secondaryCircuitId = (String)aSoacServiceOrderVo
						.get(SoacServiceOrderConstants.SECONDARY_CIRCUIT_ID);
				if(isFromFTTNToFTTNBP && (secondaryCircuitId != null))
				{
					//Add BCCR here refer to business rule SF-4.6.R7.RMBIS.2
					aSandESection.append(SoacServiceOrderConstants.NEWLINE);
					aSandESection
							.append(spaceSeparatorForLineWithNOActionIndicator); 
					aSandESection
							.append(SoacServiceOrderConstants.BONDED_CIRCUIT_FLOATED_FID);

					aSandESection
							.append(tokenizeString(secondaryCircuitId, 22));

				}

				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
				aSandESection
						.append(spaceSeparatorForLineWithNOActionIndicator); 
				//six digits FACS wire center
				aSandESection
						.append(SoacServiceOrderConstants.LOCAL_SERVING_OFFICE_FLOATED_FID);
				aSandESection.append(aSoacServiceOrderVo.get(
						SoacServiceOrderConstants.FACS_WIRE_CENTER).toString());

				//add primary RTID
				String primaryRTID = (String)aSoacServiceOrderVo
						.get(SoacServiceOrderConstants.RELATED_TDMTN);
				if(primaryRTID != null)
				{
					if(!primaryRTID.equals(""))
					{
						aSandESection.append(SoacServiceOrderConstants.NEWLINE);
						aSandESection
								.append(spaceSeparatorForLineWithNOActionIndicator); 
						aSandESection
								.append(SoacServiceOrderConstants.RELATED_CIRCUIT_FID);
						aSandESection.append(primaryRTID);
					}
				}
				//add FCI here FTTN
				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
				aSandESection
						.append(spaceSeparatorForLineWithNOActionIndicator); 
				aSandESection
						.append(SoacServiceOrderConstants.FIBER_CUSTOMER_INDICATOR_FID);
				aSandESection.append(SoacServiceOrderConstants.FTTN_INDICATOR);

				//end of T section

				//I (if FTTN to BP)or O (if BP to FTTN) AS3NE.
				aSandESection.append(SoacServiceOrderConstants.NEWLINE);

				if(isFromFTTNToFTTNBP)
				{
					aSandESection
							.append(SoacServiceOrderConstants.IN_ACTION_CODE);
				}
				else
				{
					aSandESection
							.append(SoacServiceOrderConstants.OUT_ACTION_CODE);
				}
				aSandESection.append(spaceSeparator); 
				aSandESection.append(SoacServiceOrderConstants.FTTN_AS3NE);
				aSandESection.append(SoacServiceOrderConstants.NEWLINE);

				// /CLS nn.aaaa.nnnnnn.nnn.ccc. populated with secondary circuit
				// ID
				aSandESection
						.append(spaceSeparatorForLineWithNOActionIndicator); 
				aSandESection
						.append(SoacServiceOrderConstants.CLASS_OF_SERVICE_FLOATED_FID);
				aSandESection.append(tokenizeString(aSoacServiceOrderVo.get(
						SoacServiceOrderConstants.SECONDARY_CIRCUIT_ID)
						.toString(), 22));

				// /BCCR nn.aaaa.nnnnnn.nnn.ccc.
				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
				aSandESection
						.append(spaceSeparatorForLineWithNOActionIndicator); 
				aSandESection
						.append(SoacServiceOrderConstants.BONDED_CIRCUIT_FLOATED_FID);
				aSandESection.append(tokenizeString(aSoacServiceOrderVo.get(
						SoacServiceOrderConstants.CIRCUIT).toString(), 22));

				// /LSO nnn nnn.
				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
				aSandESection
						.append(spaceSeparatorForLineWithNOActionIndicator); 
				aSandESection
						.append(SoacServiceOrderConstants.LOCAL_SERVING_OFFICE_FLOATED_FID);
				aSandESection.append(aSoacServiceOrderVo.get(
						SoacServiceOrderConstants.FACS_WIRE_CENTER).toString());

				//RTID nnn nnn-nnnn. (if FTTN to BP) secondary RTID is a
				// StringOpt so secondaryRTID might be null

				if(isFromFTTNToFTTNBP)
				{
					String secondaryRTID = (String)aSoacServiceOrderVo
							.get(SoacServiceOrderConstants.SECONDARY_RELATED_TDMTN);
					if(secondaryRTID != null && !secondaryRTID.equals(""))
					{
						aSandESection.append(SoacServiceOrderConstants.NEWLINE);
						aSandESection
								.append(spaceSeparatorForLineWithNOActionIndicator); 
						aSandESection
								.append(SoacServiceOrderConstants.RELATED_CIRCUIT_FID);
						aSandESection.append(secondaryRTID);
					}
				}
				// /FCI FTTN.
				//add FCI here FTTN
				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
				aSandESection
						.append(spaceSeparatorForLineWithNOActionIndicator); 
				aSandESection
						.append(SoacServiceOrderConstants.FIBER_CUSTOMER_INDICATOR_FID);
				aSandESection.append(SoacServiceOrderConstants.FTTN_INDICATOR);
			}
		}
		logger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
	}
	/**
	 * @param aSoacServiceOrderVo
	 * @param aSandESection
	 */
	protected void appendBCCR(SoacServiceOrderVO aSoacServiceOrderVo,
			StringBuffer aSandESection)
	{
		//Do nothing
	}
	/**
	 * Appending SECONDARY_CIRCUIT_ID
	 * @param aSoacServiceOrderVo
	 * @param aSandESection
	 */
	protected void appendBCCR2(SoacServiceOrderVO aSoacServiceOrderVo,
			StringBuffer aSandESection)
	{
		String methodName = "SoacFcifGenerator: appendBCCR2()";
		logger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);
		
		String networkType = (String)aSoacServiceOrderVo
				.get(SoacServiceOrderConstants.NETWORK_TYPE);
		String spaceSeparatorForLineWithNoActionIndicator = spaceIndex(aSoacServiceOrderVo)
				+ " ";
		String oldNetworkType = getOldNetWorkType(aSoacServiceOrderVo);
		boolean isFromFTTNBPToFTTN = (oldNetworkType
				.equalsIgnoreCase(SoacServiceOrderConstants.FTTNBP_INDICATOR) && networkType
				.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR));
		String actionIndicator = getActionIndicator(aSoacServiceOrderVo);

		if((networkType.equalsIgnoreCase(SvcOrderConstants.FTTN_NETWORK) && isFromFTTNBPToFTTN)
				|| ((aSoacServiceOrderVo
						.get(SoacServiceOrderConstants.ORDER_ACTION_TYPE)
						.toString()
						.equalsIgnoreCase(
								SoacServiceOrderConstants.NEWCONNECT_ORDER_ACTION_TYPE))
								|| (aSoacServiceOrderVo
										.get(SoacServiceOrderConstants.ORDER_ACTION_TYPE)
										.toString()
										.equalsIgnoreCase(
												SoacServiceOrderConstants.CEASE_ORDER_ACTION_TYPE)))
												&& networkType.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR))
		{
			aSandESection.append(SoacServiceOrderConstants.NEWLINE);
			aSandESection.append(spaceSeparatorForLineWithNoActionIndicator);
			aSandESection
					.append(SoacServiceOrderConstants.BONDED_CIRCUIT_FLOATED_FID);
			aSandESection.append(tokenizeString(soacServiceOrderVo.get(
					SoacServiceOrderConstants.SECONDARY_CIRCUIT_ID).toString(),
					22));
		}
		logger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
	}
	/**
	 * @param aSoacServiceOrderVo
	 * @param aSandESection
	 */
	protected void appendNewConnectDisconnectForFTTNBP(
			SoacServiceOrderVO aSoacServiceOrderVo, StringBuffer aSandESection)
	{
		//Do nothing
	}
	/**
	 * Appending for for new connect and disconnect order when network type is bonded pair
	 * @param aSoacServiceOrderVo
	 * @param aSandESection
	 */
	protected void appendNewConnectDisconnectForFTTNBP2(
			SoacServiceOrderVO aSoacServiceOrderVo, StringBuffer aSandESection)
	{
		String methodName = "SoacFcifGenerator: newConnectAppendFTTNBP2()";
		logger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

		String networkType = aSoacServiceOrderVo.get(
				SoacServiceOrderConstants.NETWORK_TYPE).toString();
		//append the right number of spaces
		String spaceSeparatorForNoActionIndicator = spaceIndex(aSoacServiceOrderVo)
				+ " ";
		String spaceSeparator = spaceIndex(aSoacServiceOrderVo);
		String actionIndicator = getActionIndicator(aSoacServiceOrderVo);
		if((actionIndicator
				.equalsIgnoreCase(SoacServiceOrderConstants.IN_ACTION_CODE) 
				|| actionIndicator.equalsIgnoreCase(SoacServiceOrderConstants.OUT_ACTION_CODE))
				&& isBondedPair(aSoacServiceOrderVo))
		{
//4
//			if(actionIndicator
//					.equalsIgnoreCase(SoacServiceOrderConstants.IN_ACTION_CODE))
//			{
//				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
//				aSandESection.append(spaceSeparatorForNoActionIndicator); 
//				aSandESection
//						.append(SoacServiceOrderConstants.SPECIAL_SAFEGUARD_MEASURE_FID);
//			}
			aSandESection.append(SoacServiceOrderConstants.NEWLINE);
			aSandESection.append(actionIndicator);
			aSandESection.append(spaceSeparator);

			if(networkType
					.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR)
					|| networkType
							.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR))
			{
				aSandESection.append(SoacServiceOrderConstants.FTTN_AS3NE);
			}
			else if((networkType
					.equalsIgnoreCase(SoacServiceOrderConstants.FTTP_NETWORK_INDICATOR))
					|| (networkType
							.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR))
					|| (networkType
							.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR)))
			{
				aSandESection.append(SoacServiceOrderConstants.FTTP_AS3NF);
			}
			aSandESection.append(SoacServiceOrderConstants.NEWLINE);
			aSandESection.append(spaceSeparatorForNoActionIndicator);
			aSandESection
					.append(SoacServiceOrderConstants.CLASS_OF_SERVICE_FLOATED_FID);
			//check for variable length string
			aSandESection.append(tokenizeString(aSoacServiceOrderVo.get(
					SoacServiceOrderConstants.SECONDARY_CIRCUIT_ID).toString(),
					22));

			aSandESection.append(SoacServiceOrderConstants.NEWLINE);
			aSandESection.append(spaceSeparatorForNoActionIndicator);
			aSandESection
					.append(SoacServiceOrderConstants.BONDED_CIRCUIT_FLOATED_FID);
			aSandESection.append(tokenizeString(aSoacServiceOrderVo.get(
					SoacServiceOrderConstants.CIRCUIT).toString(), 22));

			aSandESection.append(SoacServiceOrderConstants.NEWLINE);
			aSandESection.append(spaceSeparatorForNoActionIndicator);
			aSandESection
					.append(SoacServiceOrderConstants.LOCAL_SERVING_OFFICE_FLOATED_FID);
			aSandESection.append(aSoacServiceOrderVo.get(
					SoacServiceOrderConstants.FACS_WIRE_CENTER).toString());

			if((networkType
					.equalsIgnoreCase(SoacServiceOrderConstants.FTTP_NETWORK_INDICATOR))
					|| (networkType
							.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR))
					|| (networkType
							.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR)))
			{
				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
				aSandESection.append(spaceSeparatorForNoActionIndicator);
				aSandESection
						.append(SoacServiceOrderConstants.BROADBAND_ROUTING_FID);

			}

			// The RTID FID would be used on inward LightSpeed FTTN orders and
			// LightSpeed RGPN, GPON orders
			String secondaryRTID = (String)aSoacServiceOrderVo
					.get(SoacServiceOrderConstants.SECONDARY_RELATED_TDMTN);
			if((actionIndicator
					.equalsIgnoreCase(SoacServiceOrderConstants.IN_ACTION_CODE))
					&& ((networkType
							.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR))
							|| (networkType
									.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR))
							|| (networkType
									.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR)) 
							|| networkType.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR)))
			{

				if((secondaryRTID != null))
				{
					if(!secondaryRTID.equals(""))
					{
						aSandESection.append(SoacServiceOrderConstants.NEWLINE);
						aSandESection.append(spaceSeparatorForNoActionIndicator);
						aSandESection
								.append(SoacServiceOrderConstants.RELATED_CIRCUIT_FID);
						aSandESection.append(secondaryRTID);
					}
				}
			}
			aSandESection.append(SoacServiceOrderConstants.NEWLINE);
			aSandESection.append(spaceSeparatorForNoActionIndicator);
			aSandESection
					.append(SoacServiceOrderConstants.FIBER_CUSTOMER_INDICATOR_FID);

			if(networkType
					.equalsIgnoreCase(SoacServiceOrderConstants.FTTN_INDICATOR)
					|| networkType
							.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR))
			{
				aSandESection.append(SoacServiceOrderConstants.FTTN_INDICATOR);
			}
			else if((networkType
					.equalsIgnoreCase(SoacServiceOrderConstants.FTTP_NETWORK_INDICATOR))
					|| (networkType
							.equalsIgnoreCase(SoacServiceOrderConstants.RGPN_NETWORK_INDICATOR))
					|| (networkType
							.equalsIgnoreCase(SoacServiceOrderConstants.GPON_NETWORK_INDICATOR)))
			{
				aSandESection.append(SoacServiceOrderConstants.FTTP_INDICATOR);
			}
			//5
//			if(actionIndicator
//					.equalsIgnoreCase(SoacServiceOrderConstants.IN_ACTION_CODE))
//			{
//				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
//				aSandESection.append(spaceSeparatorForNoActionIndicator); 
//				aSandESection
//						.append(SoacServiceOrderConstants.SPECIAL_SAFEGUARD_MEASURE_FID);
//			}
		}
		logger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);

	}

	/**
	 * Appending /ADL (ADDITIONAL_LINE_FID) for SFAO
	 * @param aSoacServiceOrderVo
	 * @param aSandESection
	 */
	protected void appendADDITIONAL_LINE_FID(
			SoacServiceOrderVO aSoacServiceOrderVo, StringBuffer aSandESection)
	{
		String methodName = "SoacFcifGenerator: appendADDITIONAL_LINE_FID()";
		logger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);
		
		if(aSoacServiceOrderVo
				.get(SoacServiceOrderConstants.ADDITIONAL_LINE_FID) != null)
		{
			if(!aSoacServiceOrderVo.get(
					SoacServiceOrderConstants.ADDITIONAL_LINE_FID).equals(""))
			{
				String spaceSeparatorForActionIndicator = spaceIndex(aSoacServiceOrderVo)
						+ " ";
				aSandESection.append(SoacServiceOrderConstants.NEWLINE);
				aSandESection.append(spaceSeparatorForActionIndicator);
				aSandESection
						.append(SoacServiceOrderConstants.ADDITIONAL_LINE_FID);
			}
		}
		logger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
	}
	/**
	 * @param aSoacServiceOrderVo
	 * @return function type pre, cor, etc...
	 */
	private String getFunctionType(SoacServiceOrderVO aSoacServiceOrderVo)
	{
		return aSoacServiceOrderVo.get(SoacServiceOrderConstants.FUNCTION_TYPE)
				.toString();
	}
	/**
	 * @param aSoacServiceOrderVo
	 * @return action indicator, I, O or C
	 */
	private String getActionIndicator(SoacServiceOrderVO aSoacServiceOrderVo)
	{
		return (String)aSoacServiceOrderVo
				.get(SoacServiceOrderConstants.ACTION_INDICATOR);
	}
	/**
	 * @param aSoacServiceOrderVo
	 * @return region indicator
	 */
	private String getRegionIndicator(SoacServiceOrderVO aSoacServiceOrderVo)
	{
		return (String)aSoacServiceOrderVo
				.get(SoacServiceOrderConstants.REGION_INDICATOR);
	}
	/**
	 * @param aSoacServiceOrderVo
	 * @return five spaces for West and Southwest regions four spaces for other
	 *         regions
	 */
	private String spaceIndex(SoacServiceOrderVO aSoacServiceOrderVo)
	{
		String spaceSeparatorForActionIndicator;
		String regionIndicator = getRegionIndicator(soacServiceOrderVo);

		if(regionIndicator
				.equalsIgnoreCase(SoacServiceOrderConstants.W_REGION_INDICATOR)
				|| regionIndicator
						.equalsIgnoreCase(SoacServiceOrderConstants.S_REGION_INDICATOR))
		{
			spaceSeparatorForActionIndicator = SpaceSeparator.S5; 
		}
		else
		{
			spaceSeparatorForActionIndicator = SpaceSeparator.S4; 
		}
		return spaceSeparatorForActionIndicator;
	}
	/**
	 * @param aSoacServiceOrderVo
	 * @return true if network type or old network type is bonded pair
	 */
	private boolean isBondedPair(SoacServiceOrderVO aSoacServiceOrderVo)
	{
		String networkType = aSoacServiceOrderVo.get(
				SoacServiceOrderConstants.NETWORK_TYPE).toString();
		boolean bonded = false;

		if(networkType
				.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR)
				|| getOldNetWorkType(aSoacServiceOrderVo).equalsIgnoreCase(
						SvcOrderConstants.BOND_NETWORK_INDICATOR))
		{
			bonded = true;
		}
		return bonded;
	}
	/**
	 * @param soacServiceOrderVo
	 * @return old network type
	 */
	private String getOldNetWorkType(SoacServiceOrderVO soacServiceOrderVo)
	{
		String oldNetworkType = (String)soacServiceOrderVo
				.get(SoacServiceOrderConstants.OLD_NETWORK_TYPE);
		if(oldNetworkType == null)
		{
			oldNetworkType = "";
		}
		return oldNetworkType;
	}
	/**
	 * @param aSoacServiceOrderVo
	 * @return true if order action type is change or change-amend
	 */
	private boolean isChangeOrChangeAmend(SoacServiceOrderVO aSoacServiceOrderVo)
	{
		if(aSoacServiceOrderVo.get(SoacServiceOrderConstants.ORDER_ACTION_TYPE)
				.toString().equalsIgnoreCase(
						SvcOrderConstants.CHANGE_ORDER_ACTION_TYPE))
		{
			return true;
		}
		return false;
	}
	
	//ls10
	 protected StringBuffer generateSandEsectionIPDSLAM()
	    {	
		    StringBuffer TempSandAppend  = new StringBuffer(200);
	    	TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
	        TempSandAppend.append(SoacServiceOrderConstants.SandE_SECTION_TAG);
	        TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
	        TempSandAppend.append(soacServiceOrderVo.get(
	                SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT).toString());
	        TempSandAppend.append(SpaceSeparator.S5);
	        TempSandAppend.append(soacServiceOrderVo
					.get(SoacServiceOrderConstants.CLASS_OF_SERVICE));
	        TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
	        TempSandAppend.append(SpaceSeparator.S6);
	        TempSandAppend
	        .append(SoacServiceOrderConstants.CLASS_OF_SERVICE_FLOATED_FID);
	        //check for variable length string
	        TempSandAppend.append(tokenizeString(soacServiceOrderVo.get(
	        SoacServiceOrderConstants.CIRCUIT).toString(), 22));
	        TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
	        TempSandAppend.append(SpaceSeparator.S6);
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
	        		TempSandAppend.append(SpaceSeparator.S6);
	        		TempSandAppend.append(SoacServiceOrderConstants.DRYLOOP_RELATED_CIRCUIT_FID);
	        		TempSandAppend.append(soacServiceOrderVo.get(SoacServiceOrderConstants.DRYLOOP_RELATED_CIRCUIT_FID).toString());

	        	}
	        	else if(soacServiceOrderVo.get(SoacServiceOrderConstants.RELATED_TDMTN) != null)
	        	{
	        		if(!soacServiceOrderVo.get(
	        				SoacServiceOrderConstants.RELATED_TDMTN).equals(""))
	        		{
	        			TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
	        			TempSandAppend.append(SpaceSeparator.S6);
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
	        TempSandAppend.append(SpaceSeparator.S6);
	        TempSandAppend.append(SoacServiceOrderConstants.GENERAL_FACILITIES_FID);
	        TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
	        TempSandAppend.append(SpaceSeparator.S6);
	        TempSandAppend.append(SoacServiceOrderConstants.DSL_PROVISIONING_FID);
	        TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
	        TempSandAppend.append(SpaceSeparator.S6);
	        TempSandAppend.append(SoacServiceOrderConstants.FIBER_CUSTOMER_INDICATOR);
	        if ((soacServiceOrderVo.get(SoacServiceOrderConstants.ACTION_INDICATOR)
	                .toString()
	                .equalsIgnoreCase(SoacServiceOrderConstants.IN_ACTION_CODE))&&
	                soacServiceOrderVo.get(SoacServiceOrderConstants.INTERCEPT_REDIRECT_INDICATOR)== "true")
	        {
	        	TempSandAppend.append(SoacServiceOrderConstants.NEWLINE);
	        	TempSandAppend.append(SpaceSeparator.S6);
	        	TempSandAppend.append(SoacServiceOrderConstants.INTERCEPT_REDIRECT_FID);  	
	        }	
	        return TempSandAppend;
	    }
}