// $Id: IDLObjectHelper.java,v 1.11 2007/07/30 18:58:40 rd2842 Exp $
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
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 05/10/2005  Rene Duka       Creation.
//# 05/11/2005  Rene Duka       Modified to use the overloaded method of StringOpt.
//# 05/12/2005  Rene Duka       Modified to conform to the new OUTPUT schema from Xng.
//# 05/16/2005  Rene Duka       Moved from BusinessInterface.createFacilityAssignment package.
//# 05/31/2005  Rene Duka       Made changes per WT notes.
//# 06/06/2005  Rene Duka       Changed formatting of aDSLAM.aServiceAreaInterfaceLocation and aDSLAM.aConnectivityStatus.
//# 06/23/2005  Rene Duka       Modified for LS Release 1.
//#
//# 07/01/2005	Chaitanya		Added methods to build response for RetrieveCustomerTransportInfo
//#								buildDSLAM(), buildFiberCable(),
//#								buildNetwork7450Switch(), buildOrderAction()
//# 07/14/2005	Chaitanya		Modified as per new Schema form XNG.
//# 11/08/2005  Chaitanya   	Made changes for IDLbundle 33.
//# LS2
//# 05/01/2006	Chaitanya		Modified methods buildDSLAM(), buildNetwork7450Switch(), buildOrderAction(),
//#	05/01/2006	Chaitanya		Added methods buildCopperSegment(), buildFiberSegment(), 
//#									buildONT(), buildOLT(), buildFttnResponse(), buildFttpResponse(),
//#									isFTTNListEmpty(), isFTTPListEmpty().
//# 01/19/2006  Rene Duka       Modified the following methods used by CFA for LS Release 2:
//#                                 - buildDSLAM, buildNetwork7450Switch, buildOrderAction
//# 02/01/2006  Kavitha Kodali	Modified to conform to the new RCTI output schema from XngRC.
//# 02/17/2006  kk8467			Changed for unit testing.
//# 03/01/2006  Kavitha Kodali	Added isVideoHeadOfficeRouterListEmpty(), isProductSubscriptionListEmpty(),
//#									buildVideoHeadOfficeRouter() for LS 2.1.5
//# 03/10/2006  Kavitha Kodali	Removed isVideoHeadOfficeRouterListEmpty(), isProductSubscriptionListEmpty(),
//#								buildVideoHeadOfficeRouter() methods.
//# 03/13/2006  Jyothi Pentyala Added buildEiaDateOpt(), buildUnfieldedAddress() methods.
//# 03/14/2006  Kavitha	Kodali	Updated for RCTI.
//# 04/03/2006  Kavitha	Kodali	Added try, catch blocks in isFTTNListEmpty() and isFTTPListEmpty().
//# 04/10/2006	Kavitha Kodali	Added log statements.
//# 05/18/2006	Kavitha Kodali	DR# 161479 - LS2 RCTI code walk thru changes.
//# 05/19/2006	Kavitha Kodali	Updated buildCopperSegment().
//# 06/09/2006	Kavitha Kodali	CR9309 for LFACS interface.
//# 06/16/2006  Mrinalini Peddi	LS3 RCTI code walk thru changes.
//# 11/03/2006  Mrinalini Peddi DR# 170882 - Added one more build buildOrderAction()
//# 12/11/2006  Rene Duka       PR 18942097 and 19042150 (R3)/DR 172866: Fixed business logic in LFACS FTTN scenario.
//# 04/02/2007	Prasad Ganji	LS5 Code changes.FACSAccess is changed to FACSRCAccess and FTTNBP is added for this release.
//# 04/06/2007	Prasad Ganji	Changed FacsResponse type from INQFASGImpl to INQFASGTypeImpl
//# 06/11/2007  Prasad Ganji	PR#20058925 - No RLoop test scenario
//# 06/29/2007  Mark Liljequist	Remove FACS methods for copper segments.
//# 07/25/2007  Rene Duka       PR 20330800/Defect 70686: Hanldle conditional elements in DSLAM.

package com.sbc.eia.bis.rm.utilities;

import java.util.List;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.common.utilities.BisDateUtil;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ADDRTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.SEGTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl.RSPTypeImpl.LOOPTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl.RSPTypeImpl.LOOPTypeImpl.RLOOPTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl.RSPTypeImpl.LOOPTypeImpl.SOTypeImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityResponse.impl.CreateFacilityResponseImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoResponse.FTTNT;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoResponse.FTTPT;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoResponse.impl.RetrieveCustomerTransportInfoResponseImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerGranite;
import com.sbc.eia.idl.lim.helpers.ProviderLocationPropertyHandler;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.LocationOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.rm_ls_types.CopperSegment;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentBP;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransport;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportBP;
import com.sbc.eia.idl.rm_ls_types.FiberSegment;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.FttnBP;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.Loop;
import com.sbc.eia.idl.rm_ls_types.Network7450Switch;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminal;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminal;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.srm_ls_types.ServiceRequest;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.StringOpt;

public class IDLObjectHelper {

	public static DSLAMTransport buildDSLAM(
		Utility aUtility,
		CreateFacilityResponseImpl aResponse,
		String aBisName) {

		DSLAMTransport aDSLAM = BuildEmptyIDL.buildEmptyDSLAMObject();
        if (aResponse.getDSLAM() != null) {
            // from DSLAM element in XSD
            if (aResponse.getDSLAM().getDSLAMIdentifier() != null) {
                aDSLAM.aId = IDLUtil.toOpt(aResponse.getDSLAM().getDSLAMIdentifier());
            }
            if (aResponse.getDSLAM().getTerminal() != null) {
                aDSLAM.aEquipmentTargetId = IDLUtil.toOpt(aResponse.getDSLAM().getTerminal());
            }
            if (aResponse.getDSLAM().getPhysicalPort() != null) {
                aDSLAM.aPhysicalPort = IDLUtil.toOpt(aResponse.getDSLAM().getPhysicalPort().getCTPORTID().getPortID());
            }
            if (aResponse.getDSLAM().getLogicalPort() != null) {
                aDSLAM.aLogicalPort = IDLUtil.toOpt(aResponse.getDSLAM().getLogicalPort().getCTPORTID().getPortID());
            }
        }
		if (aResponse.getServiceAreaIP() != null) {
			// from ServiceAreaIP element in XSD
            StringOpt aLocationId = IDLUtil.toOpt(aResponse.getServiceAreaIP().getLocation().getCTSITEID().getSiteID());

			ProviderLocationProperty[] aProviderLocationProperties = new ProviderLocationProperty[1];
			ProviderLocationPropertyHandler aProviderLocationPropertyHandler = new ProviderLocationPropertyHandler();
			aProviderLocationProperties[0] = ProviderLocationPropertyHandler.getDefaultProviderLocationProperty();;
			Location aLocation = new Location(aLocationId, aProviderLocationProperties);
			aDSLAM.aLocation = (LocationOpt) IDLUtil.toOpt(LocationOpt.class, aLocation);
			aDSLAM.aModelNumber = IDLUtil.toOpt(aResponse.getServiceAreaIP().getCTMODEL().getModel());
			aDSLAM.aVLANId = IDLUtil.toOpt(aResponse.getServiceAreaIP().getVLAN());
			aDSLAM.aCrossConnectStatus = IDLUtil.toOpt(aResponse.getServiceAreaIP().getCrossConnectStatus());
			StringOpt aConnectivityStatus = IDLUtil.toOpt(aResponse.getServiceAreaIP().getConnectivityStatus());
			if (aConnectivityStatus != null) {
                boolean aValue = Boolean.valueOf(aConnectivityStatus.theValue()).booleanValue();
				BooleanOpt aBooleanOpt = new BooleanOpt();
				aBooleanOpt.theValue(aValue);
				aDSLAM.aConnectivityStatus = aBooleanOpt;
			}
		}
		return aDSLAM;
	}

	public static Network7450Switch buildNetwork7450Switch(
		Utility aUtility,
		CreateFacilityResponseImpl aResponse,
		String aBisName) {

		Network7450Switch aNetwork7450Switch = BuildEmptyIDL.buildEmptyNetwork7450SwitchObject();
		if (aResponse.getActelSwitch7450() != null) {
			// from ActelSwitch7450 element in XSD
			aNetwork7450Switch.aId = IDLUtil.toOpt(aResponse.getActelSwitch7450().getID7450());
			aNetwork7450Switch.aEquipmentTargetId = IDLUtil.toOpt(aResponse.getActelSwitch7450().getTerminalID7450());
			aNetwork7450Switch.aLogicalEgressPort = IDLUtil.toOpt(aResponse.getActelSwitch7450().getLogicalPort7450().getCTPORTID().getPortID());
			aNetwork7450Switch.aPhysicalEgressPort = IDLUtil.toOpt(aResponse.getActelSwitch7450().getPhysicalPort7450().getCTPORTID().getPortID());
			aNetwork7450Switch.aCLLI = IDLUtil.toOpt(aResponse.getActelSwitch7450().getCLLI7450().getCTEQUIPMENTCLLI().getEquipmentCLLI());
		}
		return aNetwork7450Switch;
	}

	public static OrderAction buildOrderAction(
		Utility aUtility,
		CreateFacilityResponseImpl aResponse,
		String aBisName) {

		OrderAction aOrderAction = BuildEmptyIDL.buildEmptyOrderActionObject();
		if (aResponse.getOrderNumber() != null) {
			aOrderAction.aOrder = IDLUtil.toOpt(aResponse.getOrderNumber());
		}
		return aOrderAction;
	}
	
	/**
		 * ********************
		 * RetrieveCustomerTransportInfoResponseImpl
		 * ********************
		 */
	/**
	 * @param RetrieveCustomerTransportInfoResponseImpl aResponse
	 * @return DSLAMTransport
	 */
	public static DSLAMTransport buildDSLAM(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse) {
		
		String myMethodName = "IDLObjectHelper::buildDSLAM()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		String 	 aDSLAMEquipmentId 	= null;
		String 	 aDSLAMModel 		= null;
		String 	 aDSLAMTerminalId 	= null;
		String 	 aDSLAMPhysicalPort = null;
		String 	 aDSLAMLogicalPort 	= null;
		Location aLocation 			= null;
		String 	 aVLANId 			= null;		
		boolean aConnectivityStatus= false;
		String 	 aCrossConnectStatus= null;
		
		try {
			aDSLAMEquipmentId = aResponse.getFttnResponse().getFttnRSGRP().getDSLAMEquipmentId();
		} catch (Exception e) { }
		
		try {
			aDSLAMModel = aResponse.getFttnResponse().getFttnRSGRP().getDSLAMModel().getCTMODEL().getModel();
		} catch (Exception e) { }
		
		try {
			aDSLAMTerminalId = aResponse.getFttnResponse().getFttnRSGRP().getDSLAMTerminalId();
		} catch (Exception e) { }
		
		try {
			aDSLAMPhysicalPort = aResponse.getFttnResponse().getFttnRSGRP().getDSLAMPhysicalPort().getCTPORTID().getPortID();
		} catch (Exception e) { }
		
		try {
			aDSLAMLogicalPort = aResponse.getFttnResponse().getFttnRSGRP().getDSLAMLogicalPort().getCTPORTID().getPortID();
		} catch (Exception e) { }
		
		try {
			aVLANId = aResponse.getFttnResponse().getFttnRSGRP().getVLANId();
		} catch (Exception e) { }
		
		try {
			aConnectivityStatus = Boolean.valueOf(aResponse.getServiceAreaIP().getConnectivityStatus()).booleanValue();
		} catch (Exception e) { }
		
		try {
			aCrossConnectStatus = aResponse.getServiceAreaIP().getCrossConnectStatus();
		} catch (Exception e) { }
		
		try {
			StringOpt aLocationId = IDLUtil.toOpt(aResponse.getServiceAreaIP().getLocation().getCTSITEID().getSiteID());
			aLocation = new Location(aLocationId, new ProviderLocationProperty[]{});
		} catch (Exception e) { }
		
		DSLAMTransport aDSLAMTransport = NetworkTypeHelper.createDSLAMTransport(
						aDSLAMEquipmentId,
						aDSLAMModel,
						aDSLAMTerminalId,
						aDSLAMPhysicalPort,
						aDSLAMLogicalPort,
						aLocation,
						aVLANId,
						aConnectivityStatus,
						aCrossConnectStatus);
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aDSLAMTransport;
	}

	/**
		 * ********************
		 * RetrieveCustomerTransportInfoResponseImpl
		 * ********************
		 */
	/**
	 * @param RetrieveCustomerTransportInfoResponseImpl aResponse
	 * @return DSLAMTransportBP
	 */
	public static DSLAMTransportBP buildDSLAMTransportBP(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse) {
		
		String myMethodName = "IDLObjectHelper::buildDSLAMTransportBP()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		String 	 aSecondaryCircuitId = null;
		
		String 	 aDSLAMId 			= null;
		String	 aDSLAMEquipmentId 	= null;
		String 	 aDSLAMModel 		= null;
		String 	 aDSLAMTerminalId 	= null;
		String 	 aPrimaryPhysicalPort = null;
		String	 aSecondaryPhysicalPort = null;
		String 	 aDSLAMLogicalPort 	= null;
		Location aLocation 			= null;
		String 	 aVLANId 			= null;		
		boolean aConnectivityStatus= false;
		String 	 aCrossConnectStatus= null;
		
		try {
			aSecondaryCircuitId = aResponse.getFttnResponse().getFttnRSGRP().getSecondaryLSCircuitID().trim();
		} catch (Exception e) { }
	
	
		try {
			aDSLAMEquipmentId = aResponse.getFttnResponse().getFttnRSGRP().getDSLAMEquipmentId();
		} catch (Exception e) { }
		
		try {
			aDSLAMModel = aResponse.getFttnResponse().getFttnRSGRP().getDSLAMModel().getCTMODEL().getModel();
		} catch (Exception e) { }
		
		try {
			aDSLAMTerminalId = aResponse.getFttnResponse().getFttnRSGRP().getDSLAMTerminalId();
		} catch (Exception e) { }
		

		try {
			aPrimaryPhysicalPort = aResponse.getFttnResponse().getFttnRSGRP().getDSLAMPhysicalPort().getCTPORTID().getPortID();
		} catch (Exception e) { }

		try {
			aSecondaryPhysicalPort = aResponse.getFttnResponse().getFttnRSGRP().getDSLAMSecondaryPhysicalPort().getCTPORTID().getPortID();
		} catch (Exception e) { }


		try {
			aDSLAMLogicalPort = aResponse.getFttnResponse().getFttnRSGRP().getDSLAMLogicalPort().getCTPORTID().getPortID();
		} catch (Exception e) { }
		
		try {
			aVLANId = aResponse.getFttnResponse().getFttnRSGRP().getVLANId();
		} catch (Exception e) { }
		
		try {
			aConnectivityStatus = Boolean.valueOf(aResponse.getServiceAreaIP().getConnectivityStatus()).booleanValue();
		} catch (Exception e) { }
		
		try {
			aCrossConnectStatus = aResponse.getServiceAreaIP().getCrossConnectStatus();
		} catch (Exception e) { }
		
		try {
			StringOpt aLocationId = IDLUtil.toOpt(aResponse.getServiceAreaIP().getLocation().getCTSITEID().getSiteID());
			aLocation = new Location(aLocationId, new ProviderLocationProperty[]{});
		} catch (Exception e) { }
		
		DSLAMTransport aDSLAMTransport = NetworkTypeHelper.createDSLAMTransport(
											aDSLAMEquipmentId,
											aDSLAMModel,
											aDSLAMTerminalId,
											aPrimaryPhysicalPort,
											aDSLAMLogicalPort,
											aLocation,
											aVLANId,
											aConnectivityStatus,
											aCrossConnectStatus);		
		
		DSLAMTransportBP aDSLAMTransportBP = NetworkTypeHelper.createDSLAMTransportBP(
												aDSLAMTransport,
												aPrimaryPhysicalPort,
												aSecondaryCircuitId,
												aSecondaryPhysicalPort);
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aDSLAMTransportBP;
	}

	/**
	 * @param RetrieveCustomerTransportInfoResponseImpl aResponse
	 * @return Network7450Switch
	 */
	public static Network7450Switch buildNetwork7450Switch(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse) {
		
		String myMethodName = "IDLObjectHelper::buildNetwork7450Switch()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		String aId 					= null;
		String aCLLI 				= null;
		String aEquipmentTargetId 	= null;
		String aLogicalEgressPort 	= null;
		String aPhysicalEgressPort 	= null;
		
		try {
			aId = aResponse.getActelSwitch7450().getEquipmentId7450();
		} catch (Exception e) { }
		
		try {
			aCLLI = aResponse.getActelSwitch7450().getCLLI7450().getCTEQUIPMENTCLLI().getEquipmentCLLI();
		} catch (Exception e) { }
		
		try {
			aEquipmentTargetId = aResponse.getActelSwitch7450().getTerminalId7450();
		} catch (Exception e) { }
		
		try {
			aLogicalEgressPort = aResponse.getActelSwitch7450().getLogicalPort7450().getCTPORTID().getPortID();
		} catch (Exception e) { }
		
		try {
			aPhysicalEgressPort = aResponse.getActelSwitch7450().getPhysicalPort7450().getCTPORTID().getPortID();
		} catch (Exception e) { }
		
		Network7450Switch aNetwork7450Switch = BuildEmptyIDL.buildEmptyNetwork7450SwitchObject();
		 
		if (aResponse.getActelSwitch7450() != null)		{				
			aNetwork7450Switch.aId = IDLUtil.toOpt(aId);
			aNetwork7450Switch.aCLLI = IDLUtil.toOpt(aCLLI);
			aNetwork7450Switch.aEquipmentTargetId = IDLUtil.toOpt(aEquipmentTargetId);
			aNetwork7450Switch.aLogicalEgressPort = IDLUtil.toOpt(aLogicalEgressPort);
			aNetwork7450Switch.aPhysicalEgressPort = IDLUtil.toOpt(aPhysicalEgressPort);				
		}
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aNetwork7450Switch;
	}

	/**
	 * @param RetrieveCustomerTransportInfoResponseImpl aResponse
	 * @return OrderAction
	 */
	public static OrderAction buildOrderAction(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse) {
		
		String myMethodName = "IDLObjectHelper::buildOrderAction()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		OrderAction aOrderAction = BuildEmptyIDL.buildEmptyOrderActionObject();
		
		if (aResponse.getOrderNumber() != null) {	
			aOrderAction.aOrder = IDLUtil.toOpt(aResponse.getOrderNumber());			
		} 
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aOrderAction;
	}
		
	/**
	 * This is for FACS RC
	 * Method buildCopperSegment.
	 * @param aUtility
	 * @param aFacsResponse
	 * @return CopperSegment[]
	 */
	public static CopperSegment[] buildCopperSegment(
		Utility aUtility,
		com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl aFacsResponse) {
		
		String myMethodName = "IDLObjectHelper::buildCopperSegment()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);

		CopperSegment[] aCopperSegment = null;		
			
		LOOPTypeImpl aLOOP = (LOOPTypeImpl)aFacsResponse.getRSP().getLOOP().get(0);
		
		List aSegs = aLOOP.getSEG();
			
		int segLen =  aSegs.size();

		aCopperSegment = new CopperSegment[segLen];
		
		if (segLen != 0) {
			
			for (int i=0; i < segLen; i++) {

				SEGTypeImpl aSEG = null;
				String aOutBindingPost = null, aOutPairColor = null;
				
				aSEG = (SEGTypeImpl) aSegs.get(i);
				
		/*
		- LFACS will return either a numeric or non-numeric data in the OBP field.
		- OBP is already numeric. If non-numeric is returned in the OBP field, the data is OPC. 
		- RMIM BIS will set the OBP field to Null if LFACS returned a non-numeric OPC data then RMIM BIS populate the separate OPC field accordingly. 
		- If the OBP field value is numeric, RMIM BIS will not populate the separate OPC field. 
		*/				
				try {
					int numeric = Integer.parseInt(aSEG.getOBP());
					aOutBindingPost = Integer.toString(numeric);
				} catch (NumberFormatException e) {
					aOutPairColor  = aSEG.getOBP();
				}
				
				aCopperSegment[i] = new CopperSegment();	//No need of this.
				aCopperSegment[i] = NetworkTypeHelper.createCopperSegment(
										aSEG.getSEGNO(),		// Segment No - SEGNO
										aSEG.getTEA(),			// aTerminalId - Tea
										aSEG.getTP(),			// aTerminalType - TP
										aSEG.getTEA(),			// aTerminalLocation - TEA
										aSEG.getBP(),			// aInBindingPost - BP
										aOutBindingPost,		// aOutBindingPost - OBP
										aOutPairColor,			// aOutPairColor - OBP
										aSEG.getCA(),			// aInCableName - CA
										aSEG.getPR());			// aInCablePair - PR
			}
		}	

		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aCopperSegment;
	}

	/**
	 * This is for FACS RC
	 * Method buildServiceOrder.
	 * @param aUtility
	 * @param aFacsResponse
	 * @return ServiceRequest
	 */
	public static ServiceRequest[] buildServiceOrder(
		Utility aUtility,
		com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl aFacsResponse) {
		
		String myMethodName = "IDLObjectHelper::buildServiceOrder()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);

		ServiceRequest[] aServiceOrders = null;		

		LOOPTypeImpl aLOOP = (LOOPTypeImpl)aFacsResponse.getRSP().getLOOP().get(0);
		
		List aSO = aLOOP.getSO();
			
		int soLen =  aSO.size();

		aServiceOrders = new ServiceRequest[soLen];
		
		if (soLen != 0) {
			
			for (int i=0; i < soLen; i++) {
				SOTypeImpl aServOrd = null;
				aServOrd = (SOTypeImpl) aSO.get(i);

				aServiceOrders[i] = NetworkTypeHelper.createServiceRequest(
													aServOrd.getCKID(),
													aServOrd.getORD(),
													aServOrd.getDD());
			}
		}
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aServiceOrders;
	}

	/**
	 * This is for FACS RC
	 * Method buildCopperSegmentBP.
	 * @param aUtility
	 * @param aResponse
	 * @param aFacsResponse
	 * @return CopperSegmentBP[]
	 */
	public static CopperSegmentBP[] buildCopperSegmentBP(
		Utility aUtility,
		LOOPTypeImpl aFACSLoop,
		RLOOPTypeImpl aFACSRLoop,
		String	loopType) {
		
		String myMethodName = "IDLObjectHelper::buildCopperSegmentBP()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);

		CopperSegmentBP[] aCopperSegmentBP = null;	

		List aSegs = null;
	
		if(loopType.equals("LOOP"))		//Get Segments from LOOP
			aSegs = (List) aFACSLoop.getSEG();
		else if(loopType.equals("RLOOP"))	//Get Segments from RLOOP
			aSegs = (List) aFACSRLoop.getSEG();
					
		int segLen =  aSegs.size();

		aCopperSegmentBP = new CopperSegmentBP[segLen];
		
		if (segLen != 0) {
			
			for (int i=0; i < segLen; i++) {

				SEGTypeImpl aSEG = null;
				String aOutBindingPost = null, aOutPairColor = null;
				
				aSEG = (SEGTypeImpl) aSegs.get(i);
				try {
					int numeric = Integer.parseInt(aSEG.getOBP());
					aOutBindingPost = Integer.toString(numeric);
				} catch (NumberFormatException e) {
					aOutPairColor  = aSEG.getOBP();
				}
				
				aCopperSegmentBP[i] = new CopperSegmentBP();
				aCopperSegmentBP[i] = NetworkTypeHelper.createCopperSegmentBP(
										aSEG.getSEGNO(),		// Segment No - SEGNO
										aSEG.getTEA(),			// aTerminalId - Tea
										aSEG.getTP(),			// aTerminalType - TP
										aSEG.getTEA(),			// aTerminalLocation - TEA
										aSEG.getBP(),			// aInBindingPost - BP
										aOutBindingPost,		// aOutBindingPost - OBP
										aOutPairColor,			// aOutPairColor - OBP
										aSEG.getCA(),			// aInCableName - CA
										aSEG.getPR(),			// aInCablePair - PR
										aSEG.getCOMM(),			//aCommitStatus,
										aSEG.getXSRT()	,		//aRestrictionCrossConnect,
										aSEG.getRMK0TE(),		//aRemarkTerminal,
										aSEG.getRMK0PR(),		//aRemarkCablePair,
										aSEG.getDLERMK());		//aDLERemark
			}
		}	

		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aCopperSegmentBP;
	}

	/**
	 * This is for FACS RC
	 * Method buildLoop.
	 * @param aUtility
	 * @param aFacsResponse
	 * @return Loop[]
	 */
	public static Loop[] buildLoop(
		Utility aUtility,
		com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl aFacsResponse) {
		
		String myMethodName = "IDLObjectHelper::buildLoop()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);

		Loop[] aLoop = null;
		LOOPTypeImpl aFACSLoop = null;
		RLOOPTypeImpl aFACSRLoop = null;
					
		CopperSegmentBP[] aCopperSegmentBP = null;
		
		int size = aFacsResponse.getRSP().getLOOP().size();
		
		if(size > 0) {

			aFACSLoop = (LOOPTypeImpl)aFacsResponse.getRSP().getLOOP().get(0);

			aFACSRLoop = (RLOOPTypeImpl)aFACSLoop.getRLOOP();
			
			if(aFACSRLoop != null)		//RLOOP Exists
				aLoop = new Loop[size+1];
			else
				aLoop = new Loop[size];			

			
			String loopType = "LOOP";

			aCopperSegmentBP = buildCopperSegmentBP(aUtility, aFACSLoop, aFACSRLoop, loopType);
			
			aLoop[0] = NetworkTypeHelper.createFACSLoopForBP(
											aFACSLoop.getCKID(),		//aCircuitIds
											aFACSLoop.getCKID2(),		//aCircuitIds
											aFACSLoop.getCKID3(),		//aCircuitIds
											aFACSLoop.getBCCR(),		//aBondedCrossReferenceCircuitId
											aFACSLoop.getWOL(),			//aWiredOutOfLimit
											aFACSLoop.getTSP(),			//aTelecomServicePriority
											aFACSLoop.getESL(),			//aEssentialService
											aCopperSegmentBP);
		
		//This is for RLoop
		
			if(aFACSRLoop != null)
			{
				aCopperSegmentBP = null;
			
				loopType = "RLOOP";				
				
				aCopperSegmentBP = buildCopperSegmentBP(aUtility, aFACSLoop, aFACSRLoop, loopType);
			
				aLoop[1] = NetworkTypeHelper.createFACSLoopForBP(
												aFACSRLoop.getCKID(),
												aFACSRLoop.getCKID2(),
												aFACSRLoop.getCKID3(),
												aFACSRLoop.getBCCR(),
												aFACSRLoop.getWOL(),
												aFACSRLoop.getTSP(),
												aFACSRLoop.getESL(),
												aCopperSegmentBP);
			}
			
		}
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aLoop;
	}
	
	/**
	 * @param RetrieveCustomerTransportInfoResponseImpl aResponse
	 * @return FiberSegment
	 */
	public static FiberSegment[] buildFiberSegment(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse) {
		
		String myMethodName = "IDLObjectHelper::buildFiberSegment()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		FTTPT aFTTPT = null;
		FiberSegment[] aFiberSegment = null;
		if (! isFTTPListEmpty(aUtility, aResponse) )	
		{
			int size = aResponse.getFttpResponse().getFttpRSGRP().getFTTPSegmentNumber().size();
			aFiberSegment = new FiberSegment[size];
				
			for (int i = 0; i < size; i++) 
			{
				aFiberSegment[i] = new FiberSegment();
				aFTTPT = (FTTPT) aResponse.getFttpResponse().getFttpRSGRP().getFTTPSegmentNumber().get(i);
				
				aFiberSegment[i] = NetworkTypeHelper.createFiberSegment(
									Integer.toString(i+1),
									aFTTPT.getFttpEquipmentId(),
									aFTTPT.getFttpEquipmentType(),
									aFTTPT.getFttpNetworkPortId(),
									aFTTPT.getFttpAccessPortId(),
									aFTTPT.getFttpCableId(),
									Integer.toString(aFTTPT.getFttpStrand()));
			}
		}
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aFiberSegment;
	}
	
	/**
	 * @param RetrieveCustomerTransportInfoResponseImpl aResponse
	 * @return OpticalNetworkTerminal
	 */
	public static OpticalNetworkTerminal buildONT(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse) {
		
		String myMethodName = "IDLObjectHelper::buildONT()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		String aId 			= null;
		String aModelNumber = null;
		String aAccessId 	= null;
		String aPort 		= null;
		String aIndex 		= null;
		String aVLANId 		= null;
		
		try {
			aId = aResponse.getFttpResponse().getFttpRSGRP().getONTT().getONTId();
		} catch (Exception e) { }
		
		try {
			aModelNumber = aResponse.getFttpResponse().getFttpRSGRP().getONTT().getONTSerialNumber();
		} catch (Exception e) { }
		
		try {
			aAccessId = aResponse.getFttpResponse().getFttpRSGRP().getONTT().getONTAID();
		} catch (Exception e) { }
		
		try {
			aPort = aResponse.getFttpResponse().getFttpRSGRP().getONTT().getONTPort();
		} catch (Exception e) { }
		
		try {
			aVLANId = aResponse.getFttpResponse().getFttpRSGRP().getVLANId();
		} catch (Exception e) { }
		
		OpticalNetworkTerminal aONT = NetworkTypeHelper.createOpticalNetworkTerminal(
							aId,
							aModelNumber,
							aAccessId,
							aPort,
							null,
							aVLANId);
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aONT;
	}

	/**
	 * @param RetrieveCustomerTransportInfoResponseImpl aResponse
	 * @return OpticalLineTerminal
	 */
	public static OpticalLineTerminal buildOLT(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse) {
		
		String myMethodName = "IDLObjectHelper::buildOLT()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		String aId 				  = null;
		String aModelNumber 	  = null;
		String aEquipmentTargetId = null;
		Location aLocation 		  = null;
		String aVLANId 			  = null;
		String aVirtualPathId 	  = null;
		String aVirtualChannelId  = null;
		String aLogicalPort 	  = null;
		
		try {
			aId = aResponse.getFttpResponse().getFttpRSGRP().getOLTT().getOLTId();
		} catch (Exception e) { }
		
		try {
			aModelNumber = aResponse.getFttpResponse().getFttpRSGRP().getOLTT().getOLTModel();
		} catch (Exception e) { }
		
		try {
			aEquipmentTargetId = aResponse.getFttpResponse().getFttpRSGRP().getOLTT().getOLTTargetId();
		} catch (Exception e) { }
		
		try {
			aVLANId = aResponse.getFttpResponse().getFttpRSGRP().getVLANId();
		} catch (Exception e) { }
		
		try {
			aVirtualPathId = aResponse.getFttpResponse().getFttpRSGRP().getOLTT().getVirtualPathIdentifier();
		} catch (Exception e) { }
		
		try {
			aVirtualChannelId = aResponse.getFttpResponse().getFttpRSGRP().getOLTT().getVirtualChannelIdentifier();
		} catch (Exception e) { }
		
		try {
			aLogicalPort = aResponse.getFttpResponse().getFttpRSGRP().getOLTT().getOLTPort();
		} catch (Exception e) { }
		
		try {
			StringOpt aLocationId = IDLUtil.toOpt(aResponse.getFttpResponse().getFttpRSGRP().getOLTT().getCCLA());
			aLocation = new Location(aLocationId, new ProviderLocationProperty[]{});
		} catch (Exception e) { }
		
		OpticalLineTerminal aOLT = NetworkTypeHelper.createOpticalLineTerminal(
						null,
						aId,
						null, null, 
						null, null, 
						null, null, 
						null, null, 
						null, null, 
						null,
						null,
						null, null, 
						null, null, 
						aModelNumber,
						aEquipmentTargetId,
						aLocation,
						aVLANId,
						aVirtualPathId,
						aVirtualChannelId,
						aLogicalPort);
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aOLT;
	}
	
	/**
	 * @param RetrieveCustomerTransportInfoResponseImpl aResponse
	 * @return Fttp
	 */
	public static Fttp buildFttpResponse(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse) {
		
		String myMethodName = "IDLObjectHelper::buildFttpResponse()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		OpticalNetworkTerminal aOpticalNetworkTerminal = buildONT(aUtility, aResponse);
		OpticalLineTerminal aOpticalLineTerminal = buildOLT(aUtility, aResponse);
		FiberSegment[] aFiberSegment = buildFiberSegment(aUtility, aResponse);
		
		Fttp aFttp = NetworkTypeHelper.createFttp(
							aOpticalNetworkTerminal, 
							aOpticalLineTerminal, 
							aFiberSegment);
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aFttp;
	}
	
	/**
	 * This method will be used from LS5 since FACSAccess is now RACSRCAccess
	 * Method buildFttnResponse.
	 * @param aUtility
	 * @param aResponse
	 * @param aFacsResponse
	 * @return Fttn
	 */
	public static Fttn buildFttnResponse(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse,
		com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl aFacsResponse) {
		
		String myMethodName = "IDLObjectHelper::buildFttnResponse()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		DSLAMTransport aDSLAMTransport = buildDSLAM(aUtility, aResponse);
		CopperSegment[] aCopperSegment = buildCopperSegment(aUtility, aFacsResponse);

		Fttn aFttn = NetworkTypeHelper.createFttn(
									aDSLAMTransport, 
									aCopperSegment);
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aFttn;
	}

	/**
	 * This method will be used from LS5 since FACSAccess is now RACSRCAccess
	 * Method buildFttnBPResponse.
	 * @param aUtility
	 * @param aResponse
	 * @param aFacsResponse
	 * @return Fttn
	 */
	public static FttnBP buildFttnBPResponse(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse,
		com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl aFacsResponse) {
		
		String myMethodName = "IDLObjectHelper::buildFttnBPResponse()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		String aNidIndicator = null;
		String aRemarkAtLivingUnit = null;

		LOOPTypeImpl aLOOP = (LOOPTypeImpl)aFacsResponse.getRSP().getLOOP().get(0);
		
		List addrList = aLOOP.getADDR();
		
		if(addrList.size() > 0) {
			
			for (int i=0; i < addrList.size(); i++) {			
				ADDRTypeImpl addr = (ADDRTypeImpl)addrList.get(i);
		
				if(addr.getNID() != null)
					aNidIndicator = addr.getNID();		//valid values for this field is "Y" or "N"
				if(addr.getRMK0LU() != null)
					aRemarkAtLivingUnit = addr.getRMK0LU();
			}
		}
		
		DSLAMTransportBP aDSLAMTransportBP = buildDSLAMTransportBP(aUtility, aResponse);
		
		ServiceRequest[] aServiceOrders = buildServiceOrder(aUtility, aFacsResponse);
		
		Loop[] aLoops = buildLoop(aUtility, aFacsResponse);

		FttnBP aFttnBP = NetworkTypeHelper.createFttnBP(
									aDSLAMTransportBP, 
									aNidIndicator,
									aRemarkAtLivingUnit,
									aServiceOrders,
									aLoops);
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aFttnBP;
	}


	/**
	 * @param RetrieveCustomerTransportInfoResponseImpl aResponse
	 * @return boolean
	 */
	public static boolean isFTTNListEmpty(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse) {
		
		String myMethodName = "IDLObjectHelper::isFTTNListEmpty()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		boolean result = true;
		try {
			if( aResponse.getFttnResponse().getFttnRSGRP().getFTTNSegmentNumber().get(0) != null) 
				result = false;
		} catch (Exception e) {
			aUtility.log(LogEventId.DEBUG_LEVEL_1, "IDLObjectHelper::isFTTNListEmpty()" + "->" + e.getMessage());
		}
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return result;
	}

	/**
	 * @param RetrieveCustomerTransportInfoResponseImpl aResponse
	 * @return boolean
	 */
	public static boolean isFTTPListEmpty(
		Utility aUtility,
		RetrieveCustomerTransportInfoResponseImpl aResponse) {
		
		String myMethodName = "IDLObjectHelper::isFTTPListEmpty()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		boolean result = true;
		try {
			if( aResponse.getFttpResponse().getFttpRSGRP().getFTTPSegmentNumber().get(0) != null)
				result = false;
		}
		catch (Exception e) {
			aUtility.log(LogEventId.DEBUG_LEVEL_1, "IDLObjectHelper::isFTTPListEmpty()" + "->" + e.getMessage());
		}
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return result;
	}
	/**
	 * buildUnfieldedAddress method builds Unfielded Address object using LIM helper.
	 * @param aUtility
	 * @param addressLine
	 * @param city
	 * @param state
	 * @param postalCode
	 * @param levelValue
	 * @param unitValue
	 * @return unfielded Address
	 */
	public static Address buildUnfieldedAddress(
		Utility aUtility,
		String addressLine,
		String city,
		String state,
		String postalCode,
		String levelValue,
		String unitValue) {

		String myMethodName = "IDLObjectHelper::buildUnfieldedAddress()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);
		
		AddressHandlerGranite aAddressHandlerGranite = null;
		try {
			aAddressHandlerGranite =
				new AddressHandlerGranite(
					addressLine,
					city,
					state,
					postalCode,
					"",
					levelValue,
					unitValue);

		} catch (AddressHandlerException e) {
			aUtility.log(
				LogEventId.DEBUG_LEVEL_1,
				"Error building unfielded Address object. " + e.getMessage());
		}
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);
		return aAddressHandlerGranite.getUFAddress();
	}

	/**
	 * buildEiaDateOpt method builds EiaDateOpt using date.
	 * @param date
	 * @return EiaDateOpt
	 */
	public static EiaDateOpt buildEiaDateOpt(String date) {
		
		EiaDateOpt aEiaDateOpt = null;

		if (date != null && date.trim().length() > 1)
			aEiaDateOpt = BisDateUtil.toEiaDateOpt(date, "MM/dd/yyyy");
		else
			aEiaDateOpt = (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, null);

		return aEiaDateOpt;
	}

    /**   
     * Method buildOrderAction. (For cFA)   
     * @param aUtility   
     * @param aResponse   
     * @param aBisName
     * @param aOrderAction
     * @return OrderAction   
     */   
    public static OrderAction buildOrderAction(   
        Utility aUitlity,   
        CreateFacilityResponseImpl aResponse,   
        String aBisName,   
        OrderAction aOrderAction) {   
       
        if (aResponse.getOrderNumber() != null) {   
            aOrderAction.aOrder = IDLUtil.toOpt(aResponse.getOrderNumber());   
        }   
        return aOrderAction;   
       
    } 

    /**   
     * Method buildFttnResponse. (For mI/mFI)   
     * @param aUtility   
     * @param aResponse   
     * @return Fttn   
     */   
    public static Fttn buildFttnResponse(   
        Utility aUtility,   
        RetrieveCustomerTransportInfoResponseImpl aResponse) {   
   
        String myMethodName = "IDLObjectHelper::buildFttnResponse()";   
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);   
   
        DSLAMTransport aDSLAMTransport = buildDSLAM(aUtility, aResponse);   
        CopperSegment[] aCopperSegment = buildCopperSegment(aUtility, aResponse);   
   
        Fttn aFttn = NetworkTypeHelper.createFttn(aDSLAMTransport,   
                                                  aCopperSegment);   
   
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);   
        return aFttn;   
    }   
   
    /**   
     * Method buildCopperSegment. (For mI/mFI)   
     * @param aUtility   
     * @param aResponse   
     * @return CopperSegment[]   
     */   
    public static CopperSegment[] buildCopperSegment(   
        Utility aUtility,   
        RetrieveCustomerTransportInfoResponseImpl aResponse) {   
   
        String myMethodName = "IDLObjectHelper::buildCopperSegment()";   
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " > " + myMethodName);   
   
        FTTNT aFTTNT = null;   
        CopperSegment[] aCopperSegment = null;   
        if (!isFTTNListEmpty(aUtility, aResponse)) {   
            int size = aResponse.getFttnResponse().getFttnRSGRP().getFTTNSegmentNumber().size();   
            aCopperSegment = new CopperSegment[size];   
   
            for (int i = 0; i < size; i++) {   
                aCopperSegment[i] = new CopperSegment();   
                aFTTNT = (FTTNT) aResponse.getFttnResponse().getFttnRSGRP().getFTTNSegmentNumber().get(i);   
/*   
                String aSegmentNumber,   
                String aTerminalId,   
                String aTerminalType,   
                String aTerminalLocation,   
                String aInBindingPost,   
                String aOutBindingPost,   
                String aOutPairColor,   
                String aInCableName,   
                String aInCablePair,   
*/   
                aCopperSegment[i] = NetworkTypeHelper.createCopperSegment(   
                                                                          Integer.toString(i+1),   
                                                                          aFTTNT.getFttnEquipmentId(),   
                                                                          aFTTNT.getFttnEquipmentType(),   
                                                                          aFTTNT.getFttnEquipmentLocation(),   
                                                                          aFTTNT.getInBindingPost().getCTPORTID().getPortID(),   
                                                                          aFTTNT.getOutBindingPost().getCTPORTID().getPortID(),   
                                                                          aFTTNT.getFttnOutPairColor(),   
                                                                          aFTTNT.getCableID(),   
                                                                          Integer.toString(aFTTNT.getCablePairNumber()));   
            }   
        }   
        aUtility.log(LogEventId.DEBUG_LEVEL_1, " < " + myMethodName);   
        return aCopperSegment;   
    } 
}