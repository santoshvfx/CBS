/*
 * Created on May 17, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.strategy;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequestI;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.GeneratorException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.SoacFcifGenerator;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SpaceSeparator;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.CVOIPServiceOrderVO;
;

/**
 * @author sd8583
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CVoIPSoacServiceOrderGenerator extends SoacFcifGenerator {

	int actionType = 0;
	StringBuffer sbServiceOrderText = new StringBuffer(200);
	StringBuffer sbListingSection = new StringBuffer(500);
	StringBuffer sbFcifString = new StringBuffer(1500);
	StringBuffer sbSandESection = new StringBuffer(500);

	public CVoIPSoacServiceOrderGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void generate(ParseRequestI request) throws GeneratorException {
		setLogger(request);
		setSoacServiceOrderVo(request);
		actionType =
			getActionType(
				(String) soacServiceOrderVo.get("FUNCTION_TYPE"),
				(String) soacServiceOrderVo.get("ACTION_INDICATOR"));

		switch (actionType) {
			case NEW_CONNECT_ORDER :
				sbFcifString =
					createNewConnectOrDisconnectOrder(request.getRegion());
				break;
			case DISCONNECT_ORDER :
				sbFcifString =
					createNewConnectOrDisconnectOrder(request.getRegion());
				break;
			case CANCEL_ORDER :
				sbFcifString = createCancelOrder();
				break;
			case CORRECTION_ORDER :
				sbFcifString = createCorrectionOrder(request.getRegion());
				break;
			case COMPLETION_ORDER :
				sbFcifString = createCompletionOrder();
				break;
		}
		//insure entier string is in upper case before returning to client
		request.setFcifDataString(sbFcifString.toString().toUpperCase());
	}

	protected StringBuffer generateSvcOrderText() {

		logger.log(
			LogEventId.DEBUG_LEVEL_1,
			"Generating Service Order Text Section.");
		sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
		sbServiceOrderText.append(
			soacServiceOrderVo
				.get(SoacServiceOrderConstants.TN_OR_NPANXX)
				.toString());
		sbServiceOrderText.append(SpaceSeparator.S5);
		soacServiceOrderVo.put(
			SoacServiceOrderConstants.CLASS_OF_SERVICE,
			"XR7FA");
		sbServiceOrderText.append(
			soacServiceOrderVo.get(SoacServiceOrderConstants.CLASS_OF_SERVICE));
		sbServiceOrderText.append(SpaceSeparator.S10);
		sbServiceOrderText.append(
			formatDate.DateFormat2(
				(String) soacServiceOrderVo
					.get(SoacServiceOrderConstants.APPLICATION_DATE)
					.toString()));
		sbServiceOrderText.append(SpaceSeparator.S13);
		sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
		sbServiceOrderText.append(
			soacServiceOrderVo
				.get(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM)
				.toString());
		sbServiceOrderText.append(
			soacServiceOrderVo
				.get(SoacServiceOrderConstants.CORRECTION_SUFFIX)
				.toString());
		sbServiceOrderText.append(SpaceSeparator.S17);
		sbServiceOrderText.append(
			soacServiceOrderVo
				.get(SoacServiceOrderConstants.ORIGINAL_DUE_DATE)
				.toString());
		sbServiceOrderText.append(SpaceSeparator.S15);

		if (soacServiceOrderVo.get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE)
			!= null) {
			if (!soacServiceOrderVo
				.get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE)
				.equals("")) {
				sbServiceOrderText.append(SoacServiceOrderConstants.NEWLINE);
				sbServiceOrderText.append(
					SoacServiceOrderConstants.SUBSEQ_DUE_DATE_FID);
				sbServiceOrderText.append(
					SoacServiceOrderConstants.SD_FLOATED_FID);
				sbServiceOrderText.append(
					soacServiceOrderVo
						.get(SoacServiceOrderConstants.SUBSEQ_DUE_DATE)
						.toString());

			}
		}

		return sbServiceOrderText;
	}

	protected StringBuffer generateListingSection() {
		logger.log(LogEventId.DEBUG_LEVEL_1, "Generating Listing Section.");
		sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
		sbListingSection.append(SoacServiceOrderConstants.LSTG_SECTION_TAG);
		sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
		sbListingSection.append(SoacServiceOrderConstants.LISTING_ADDRESS_FID);
		//check for variable length string
		sbListingSection.append(
			tokenizeString(
				soacServiceOrderVo
					.get(SoacServiceOrderConstants.BASIC_ADDRESS)
					.toString(),
				28));
		if (soacServiceOrderVo
			.get(SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS)
			!= null) {
			if (!soacServiceOrderVo
				.get(SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS)
				.equals("")) {
				sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
				sbListingSection.append(SpaceSeparator.S6);
				//check for variable length string
				sbListingSection.append(
					tokenizeString(
						soacServiceOrderVo
							.get(
								SoacServiceOrderConstants
									.EXTENDED_BASIC_ADDRESS)
							.toString(),
						28));
			}
		}
		if (soacServiceOrderVo
			.get(SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM)
			!= null) {
			if (!soacServiceOrderVo
				.get(SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM)
				.equals("")) {
				sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
				sbListingSection.append(
					SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM_FID);
				//check for variable length string
				sbListingSection.append(
					tokenizeString(
						soacServiceOrderVo
							.get(SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM)
							.toString(),
						41));
			}
		}
		if (soacServiceOrderVo
			.get(SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO)
			!= null) {
			if (!soacServiceOrderVo
				.get(SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO)
				.equals("")) {
				sbListingSection.append(SoacServiceOrderConstants.NEWLINE);
				sbListingSection.append(SoacServiceOrderConstants.LOCATION_FID);
				//check for variable length string
				sbListingSection.append(
					tokenizeString(
						soacServiceOrderVo
							.get(
								SoacServiceOrderConstants
									.SUPPLEMENTAL_ADDRESS_INFO)
							.toString(),
						28));
			}
		}
		return sbListingSection;
	}

	protected StringBuffer generateSandESection() {
		String FTTN_line_assinable_usoc = "AS3NE";
		String FTTP_line_assinable_usoc = "AS3NF";
		String CVOIP_line_assinable_usoc = "XRELV";
		logger.log(LogEventId.DEBUG_LEVEL_1, "Generating S and E Section.");
		sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
		sbSandESection.append(SoacServiceOrderConstants.SandE_SECTION_TAG);

		CVOIPServiceOrderVO[] VoipServiceOrders =
			soacServiceOrderVo.getVoipServiceOrders();
		//		CVOIPServiceOrderVO[] VoipServiceOrders = new CVOIPServiceOrderVO[1];
		//		VoipServiceOrders[0] = new CVOIPServiceOrderVO("1","2","3","4","5");

		for (int i = 0; i < VoipServiceOrders.length; i++) {
			sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
			sbSandESection.append(
				VoipServiceOrders[i].getActionIndicator().toString());
			sbSandESection.append(SpaceSeparator.S5);
			if (soacServiceOrderVo
				.get(SoacServiceOrderConstants.NETWORK_TYPE)
				.toString()
				.equalsIgnoreCase("FTTN")) {
				sbSandESection.append(FTTN_line_assinable_usoc);
			} else if (
				soacServiceOrderVo
					.get(SoacServiceOrderConstants.NETWORK_TYPE)
					.toString()
					.equalsIgnoreCase("FTTP")) {
				sbSandESection.append(FTTP_line_assinable_usoc);
			} else if (
				soacServiceOrderVo
					.get(SoacServiceOrderConstants.NETWORK_TYPE)
					.toString()
					.equalsIgnoreCase("VOIP")) {
				sbSandESection.append(CVOIP_line_assinable_usoc);
			}
			sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
			sbSandESection.append(SpaceSeparator.S6);
			sbSandESection.append(SoacServiceOrderConstants.TN_FLOATING_FID);
			//////			
			sbSandESection.append(VoipServiceOrders[i].getTelephoneNo());

			if (VoipServiceOrders[i].getOldProvider() != null) {
				if (!VoipServiceOrders[i].getOldProvider().equals("")) {
					sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
					sbSandESection.append(SpaceSeparator.S6);
					sbSandESection.append(
						SoacServiceOrderConstants.OLD_PROVIDER_FLOATING_FID);
					sbSandESection.append(
						VoipServiceOrders[i].getOldProvider());

				}
			}
			if (VoipServiceOrders[i].getNewProvider() != null) {
				if (!VoipServiceOrders[i].getNewProvider().equals("")) {
					sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
					sbSandESection.append(SpaceSeparator.S6);
					sbSandESection.append(
						SoacServiceOrderConstants.NEW_PROVIDER_FLOATING_FID);
					sbSandESection.append(
						VoipServiceOrders[i].getNewProvider());

				}
			}
			if (VoipServiceOrders[i].getLRN() != null) {
				if (!VoipServiceOrders[i].getLRN().equals("")) {
					sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
					sbSandESection.append(SpaceSeparator.S6);
					sbSandESection.append(
						SoacServiceOrderConstants.LRN_PROVIDER_FID);
					sbSandESection.append(VoipServiceOrders[i].getLRN());

				}
			}
			if (VoipServiceOrders[i].getRetPortInd() != null) {
				if (!VoipServiceOrders[i].getRetPortInd().equals("")) {

					sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
					sbSandESection.append(SpaceSeparator.S6);
					sbSandESection.append("/");
					sbSandESection.append(VoipServiceOrders[i].getRetPortInd());
				}
			}
			if (VoipServiceOrders[i].getNonRetPortInd() != null) {
				if (!VoipServiceOrders[i].getNonRetPortInd().equals("")) {

					sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
					sbSandESection.append(SpaceSeparator.S6);
					sbSandESection.append("/");
					sbSandESection.append(
						VoipServiceOrders[i].getNonRetPortInd());
				}
			}
			if (VoipServiceOrders[i].getLocalServingInd() != null) {
							if (!VoipServiceOrders[i].getLocalServingInd().equals("")) {

								sbSandESection.append(SoacServiceOrderConstants.NEWLINE);
								sbSandESection.append(SpaceSeparator.S6);
								sbSandESection.append(
						SoacServiceOrderConstants.LOCAL_SERVING_OFFICE_FLOATED_FID);
								sbSandESection.append(
									VoipServiceOrders[i].getLocalServingInd());
							}
						}	
		}
		return sbSandESection;
	}

}
