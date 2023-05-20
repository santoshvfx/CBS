// $Id: NetworkTypeHelper.java,v 1.6 2011/02/26 01:18:11 rs278j Exp $
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
//# Date		| Author		| Notes
//# --------------------------------------------------------------------
//# 03/01/2006  Kavitha Kodali	Added createVideoHeadOfficeRouter() for LS 2.1.5
//# 04/10/2006	Kavitha Kodali	Updated to use common methods.
//# 04/02/2007	Prasad Ganji	LS5 Code changes.FACSAccess is changed to FACSRCAccess and FTTNBP is added for this release.
//# 04/16/2007	Prasad Ganji	DR# 64028 - Fix code for FTTNBP network type
//# 06/08/2007	Prasad Ganji	PR#20058925 - Catch Exception
//# 06/11/2007  Prasad Ganji	PR#20058925 - No RLoop test scenario
//# 12/14/2004  Changchuan Yin  DR# 80101 - Handle aInFiberCable as empty string
//# 12/19/2004  Changchuan Yin  DR# 80101 - Remove enpty tag for cable in Granite repsonse when aInFiberCable as empty string

package com.sbc.eia.bis.rm.utilities;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.LocationOpt;
import com.sbc.eia.idl.rm_ls_types.CopperSegment;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentBP;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentBPSeqOpt;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentOpt;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentSeqOpt;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransport;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportBP;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportBPOpt;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.FiberSegment;
import com.sbc.eia.idl.rm_ls_types.FiberSegmentSeqOpt;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.FttnBP;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.Loop;
import com.sbc.eia.idl.rm_ls_types.LoopSeqOpt;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminal;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminalOpt;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminal;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminalOpt;
import com.sbc.eia.idl.srm_ls_types.ProductSubscriptionAction;
import com.sbc.eia.idl.srm_ls_types.ProductSubscriptionActionSeqOpt;
import com.sbc.eia.idl.srm_ls_types.ServiceRequest;
import com.sbc.eia.idl.srm_ls_types.ServiceRequestSeqOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;

/**
 * @author hw7243
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class NetworkTypeHelper {

	/**
	 * Constructor for NetworkTypeHelper.
	 */
	/*public NetworkTypeHelper() {
		super();
	}*/

	public static Fttn createFttn(DSLAMTransport aDSLAM, CopperSegment[] aCopperSegment) {
		Fttn aFttn =  BuildEmptyIDL.buildEmptyFttnObject();
		aFttn.aDSLAM = (DSLAMTransportOpt) IDLUtil.toOpt(DSLAMTransportOpt.class, aDSLAM);
		aFttn.aSegments = (CopperSegmentSeqOpt) IDLUtil.toOpt(CopperSegmentSeqOpt.class, aCopperSegment);

		return aFttn;
	}

	public static FttnBP createFttnBP(DSLAMTransportBP aDSLAMTransportBP, String aNidIndicator, String aRemarkAtLivingUnit, ServiceRequest[] aServiceOrders, Loop[] aLoops) {
		FttnBP aFttnBP =  BuildEmptyIDL.buildEmptyFttnBPObject();

		aFttnBP.aDSLAMBondedPair = (DSLAMTransportBPOpt) IDLUtil.toOpt(DSLAMTransportBPOpt.class, aDSLAMTransportBP);
		aFttnBP.aLoops = (LoopSeqOpt) IDLUtil.toOpt(LoopSeqOpt.class, aLoops);
		try {
			aFttnBP.aNidIndicator = (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, aNidIndicator.trim());
		} catch (Exception any) {
			aFttnBP.aNidIndicator = (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, aNidIndicator);						
		}
		aFttnBP.aRemarkAtLivingUnit = setStringOptWithTrim(aRemarkAtLivingUnit);
		aFttnBP.aServiceOrders = (ServiceRequestSeqOpt) IDLUtil.toOpt(ServiceRequestSeqOpt.class, aServiceOrders);
		
		return aFttnBP;
	}

	public static DSLAMTransport createDSLAMTransport(
		String aId,
		String aModelNumber,
		String aEquipmentTargetId,
		String aPhysicalPort,
		String aLogicalPort,
		Location aLocation,
		String aVLANId,
		boolean aConnectivityStatus,
		String aCrossConnectStatus) {
			
		BooleanOpt aBooleanOpt = new BooleanOpt();
		aBooleanOpt.theValue(aConnectivityStatus);
		
		DSLAMTransport aDSLAMTransport = BuildEmptyIDL.buildEmptyDSLAMObject();

		try
		{
			aDSLAMTransport.aId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aId.trim());
		}
		catch(Exception e){
			aDSLAMTransport.aId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aId);					
		}	
		try
		{
			aDSLAMTransport.aModelNumber = (StringOpt) IDLUtil.toOpt(StringOpt.class, aModelNumber.trim());
		}
		catch(Exception e){
			aDSLAMTransport.aModelNumber = (StringOpt) IDLUtil.toOpt(StringOpt.class, aModelNumber);
		}	
		try
		{
			aDSLAMTransport.aEquipmentTargetId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aEquipmentTargetId.trim());
		}
		catch(Exception e){
			aDSLAMTransport.aEquipmentTargetId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aEquipmentTargetId);		
		}	
		try
		{
			aDSLAMTransport.aPhysicalPort = (StringOpt) IDLUtil.toOpt(StringOpt.class, aPhysicalPort.trim());
		}
		catch(Exception e){
			aDSLAMTransport.aPhysicalPort = (StringOpt) IDLUtil.toOpt(StringOpt.class, aPhysicalPort);		
		}	
		try
		{
			aDSLAMTransport.aLogicalPort = (StringOpt) IDLUtil.toOpt(StringOpt.class, aLogicalPort.trim());
		}
		catch(Exception e){
			aDSLAMTransport.aLogicalPort = (StringOpt) IDLUtil.toOpt(StringOpt.class, aLogicalPort);		
		}	
		aDSLAMTransport.aLocation = (LocationOpt) IDLUtil.toOpt(LocationOpt.class, aLocation);
		try
		{
			aDSLAMTransport.aVLANId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aVLANId.trim());
		}
		catch(Exception e){
			aDSLAMTransport.aVLANId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aVLANId);		
		}			
		aDSLAMTransport.aConnectivityStatus = aBooleanOpt;
		try
		{
			aDSLAMTransport.aCrossConnectStatus = (StringOpt) IDLUtil.toOpt(StringOpt.class, aCrossConnectStatus.trim());
		}
		catch(Exception e){
			aDSLAMTransport.aCrossConnectStatus = (StringOpt) IDLUtil.toOpt(StringOpt.class, aCrossConnectStatus);		
		}	

		return aDSLAMTransport;
	}

	public static DSLAMTransportBP createDSLAMTransportBP(
		DSLAMTransport aDSLAMTransportForBP,
		String 	 aPrimaryPhysicalPort,
		String   aSecondaryCircuitId,
		String 	 aSecondaryPhysicalPort) {
			
		DSLAMTransportBP aDSLAMTransportBP = BuildEmptyIDL.buildEmptyDSLAMBPObject();
		
		aDSLAMTransportBP.aDSLAMTransport = (DSLAMTransportOpt) IDLUtil.toOpt(DSLAMTransportOpt.class, aDSLAMTransportForBP);
		aDSLAMTransportBP.aPrimaryPhysicalPort = setStringOptWithTrim(aPrimaryPhysicalPort);
		aDSLAMTransportBP.aSecondaryCircuitId = setStringOptWithTrim(aSecondaryCircuitId);
		aDSLAMTransportBP.aSecondaryPhysicalPort = setStringOptWithTrim(aSecondaryPhysicalPort);

		return aDSLAMTransportBP;
	}

	// If it is SAI DSLAM case(PigTail case), this function will createfirst and second
	// segment fromthe input
	//
	public static CopperSegment[] createPigtailCopperSegment(
		String aTerminalId,
		String aTerminalType,
		String aTerminalLocation,
		String aInBindingPost,
		String aOutBindingPost,
		String aOutPairColor,
		String aInCableName,
		String aInCablePair,
		String aClli8) {
		CopperSegment[] aCopperSegmentList = new CopperSegment[2];

		CopperSegment aCopperSegment = BuildEmptyIDL.buildEmptyCopperSegmentObject();

		
			aCopperSegment.aSegmentNumber = (StringOpt) IDLUtil.toOpt(StringOpt.class, "1");

			try {
				aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId.trim());
			} catch (Exception e) {
				aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId);				
			}
			try {
				aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType.trim());
			} catch (Exception e) {
				aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType);				
			}
			try {
				aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation.trim());
			} catch (Exception e) {
				aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation);				
			}
			aCopperSegment.aInBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, null);
			try {
				aCopperSegment.aOutBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutBindingPost.trim());
			} catch (Exception e) {
				aCopperSegment.aOutBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutBindingPost);				
			}
			try {
				aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor.trim());
			} catch (Exception e) {
				aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor);				
			}


		if (aInCableName != null && aClli8 != null) {
				try {
					aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName.trim() + "_I" + "/" + aClli8.toUpperCase());
				} catch (Exception e) {
					aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName + "_I" + "/" + aClli8.toUpperCase());					
				}
		} else
				try {
					aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName.trim());
				} catch (Exception e) {
					aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName);					
				}

			try {
				aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair.trim());
			} catch (Exception e) {
				aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair);				
			}

		aCopperSegmentList[0] = aCopperSegment;
		aCopperSegment = new CopperSegment();  

			aCopperSegment.aSegmentNumber = (StringOpt) IDLUtil.toOpt(StringOpt.class, "2");
			try {
				aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId.trim());
			} catch (Exception e) {
				aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId);				
			}
			try {
				aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType.trim());
			} catch (Exception e) {
				aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType);				
			}
			try {
				aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation.trim());
			} catch (Exception e) {
				aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation);				
			}
			try {
				aCopperSegment.aInBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInBindingPost.trim());
			} catch (Exception e) {
				aCopperSegment.aInBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInBindingPost);
				
			}
			aCopperSegment.aOutBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, null);
			try {
				aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor.trim());
			} catch (Exception e) {
				aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor);				
			}

		if (aInCableName != null && aClli8 != null) {
				try {
					aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName.trim() + "_O" + "/" + aClli8.toUpperCase());
				} catch (Exception e) {
					aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName + "_O" + "/" + aClli8.toUpperCase());					
				}
		} else
				try {
					aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName.trim());
				} catch (Exception e) {
					aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName);					
				}

			try {
				aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair.trim());
			} catch (Exception e) {
				aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair);				
			}

		aCopperSegmentList[1] = aCopperSegment;

		return aCopperSegmentList;
	}
	
	/**
     * Creates Pigtail Copper Segments for IPDSLAM.
     * 
     * @param BisContext aContext
     * @param String aTerminalId
     * @param String aTerminalType
     * @param String aTerminalLocation
     * @param String aInBindingPost
     * @param String aOutBindingPost
     * @param String aOutPairColor
     * @param String aInCableName
     * @param String aInCablePair
     * @param String aClli8
     * @return CopperSegment[]
     * 
     * @author Richard Joseph Santiago
     */
	public static CopperSegment[] createPigtailCopperSegmentIPDSLAM(
		String aTerminalId,
		String aTerminalType,
		String aTerminalLocation,
		String aInBindingPost,
		String aOutBindingPost,
		String aOutPairColor,
		String aInCableName,
		String aInCablePair,
		String aClli8) 
	{
		CopperSegment[] aCopperSegmentList = new CopperSegment[2];
		CopperSegment aCopperSegment = BuildEmptyIDL.buildEmptyCopperSegmentObject();
		
		aCopperSegment.aSegmentNumber = (StringOpt) IDLUtil.toOpt(StringOpt.class, "1");

		try 
		{
			aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId.trim());
		} 
		catch (Exception e) 
		{
			aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId);				
		}
		try 
		{
			aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType.trim());
		} 
		catch (Exception e) 
		{
			aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType);				
		}
		try 
		{
			aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation.trim());
		} 
		catch (Exception e) 
		{
			aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation);				
		}
		
		aCopperSegment.aInBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, null);
		
		try 
		{
			aCopperSegment.aOutBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutBindingPost.trim());
		} 
		catch (Exception e) 
		{
			aCopperSegment.aOutBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutBindingPost);				
		}
		try 
		{
			aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor.trim());
		} 
		catch (Exception e) 
		{
			aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor);				
		}

		if (aInCableName != null && aClli8 != null) 
		{
			try 
			{
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName.trim() + "/" + aClli8.toUpperCase());
			} 
			catch (Exception e) 
			{
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName + "/" + aClli8.toUpperCase());					
			}
		} 
		else
		{
			try 
			{
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName.trim());
			}
			catch (Exception e) 
			{
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName);					
			}
		}
		try 
		{
			aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair.trim());
		} 
		catch (Exception e)
		{
			aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair);				
		}

		aCopperSegmentList[0] = aCopperSegment;
		aCopperSegment = new CopperSegment();  

		aCopperSegment.aSegmentNumber = (StringOpt) IDLUtil.toOpt(StringOpt.class, "2");
		
		try 
		{
			aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId.trim());
		}
		catch (Exception e) 
		{
			aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId);				
		}
		try 
		{
			aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType.trim());
		}
		catch (Exception e) 
		{
			aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType);				
		}
		try 
		{
			aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation.trim());
		}
		catch (Exception e) 
		{
			aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation);				
		}
		try 
		{
			aCopperSegment.aInBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInBindingPost.trim());
		}
		catch (Exception e) 
		{
			aCopperSegment.aInBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInBindingPost);
		}
		
		aCopperSegment.aOutBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, null);
		
		try 
		{
			aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor.trim());
		} 
		catch (Exception e) 
		{
			aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor);				
		}

		if (aInCableName != null && aClli8 != null) 
		{
			try 
			{
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName.trim() + "/" + aClli8.toUpperCase());
			}
			catch (Exception e) 
			{
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName + "/" + aClli8.toUpperCase());					
			}
		} 
		else
		{			
			try 
			{
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName.trim());
			}
			catch (Exception e) 
			{
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName);					
			}
		}
		try 
		{
			aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair.trim());
		}
		catch (Exception e) 
		{
			aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair);				
		}

		aCopperSegmentList[1] = aCopperSegment;

		return aCopperSegmentList;
	}
	
	public static CopperSegment createCopperSegment(
		String aSegmentNumber,
		String aTerminalId,
		String aTerminalType,
		String aTerminalLocation,
		String aInBindingPost,
		String aOutBindingPost,
		String aOutPairColor,
		String aInCableName,
		String aInCablePair,
		String aClli8) {
		CopperSegment aCopperSegment = BuildEmptyIDL.buildEmptyCopperSegmentObject();

		try {
			aCopperSegment.aSegmentNumber = (StringOpt) IDLUtil.toOpt(StringOpt.class, aSegmentNumber.trim());
		} catch (Exception e) {
			aCopperSegment.aSegmentNumber = (StringOpt) IDLUtil.toOpt(StringOpt.class, aSegmentNumber);			
		}
		try {
			aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId.trim());
		} catch (Exception e) {
			aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId);			
		}
		try {
			aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType.trim());
		} catch (Exception e) {
			aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType);			
		}
		try {
			aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation.trim());
		} catch (Exception e) {
			aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation);			
		}
		try {
			aCopperSegment.aInBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInBindingPost.trim());
		} catch (Exception e) {
			aCopperSegment.aInBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInBindingPost);			
		}
		try {
			aCopperSegment.aOutBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutBindingPost.trim());
		} catch (Exception e) {
			aCopperSegment.aOutBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutBindingPost);			
		}
		try {
			aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor.trim());
		} catch (Exception e) {
			aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor);			
		}

		if (aInCableName != null && aClli8 != null) {
			try {
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName.trim() + "/" + aClli8.toUpperCase());
			} catch (Exception e) {
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName + "/" + aClli8.toUpperCase());				
			}
		} else
			try {
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName.trim());
			} catch (Exception e) {
				aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName);				
			}

		try {
			aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair.trim());
		} catch (Exception e) {
			aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair);			
		}

		return aCopperSegment;
	}
	
	public static CopperSegment createCopperSegment(
		String aSegmentNumber,
		String aTerminalId,
		String aTerminalType,
		String aTerminalLocation,
		String aInBindingPost,
		String aOutBindingPost,
		String aOutPairColor,
		String aInCableName,
		String aInCablePair) {
		CopperSegment aCopperSegment = BuildEmptyIDL.buildEmptyCopperSegmentObject();

		try {
			aCopperSegment.aSegmentNumber = (StringOpt) IDLUtil.toOpt(StringOpt.class, aSegmentNumber.trim());
		} catch (Exception e) {
			aCopperSegment.aSegmentNumber = (StringOpt) IDLUtil.toOpt(StringOpt.class, aSegmentNumber);			
		}
		try {
			aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId.trim());
		} catch (Exception e) {
			aCopperSegment.aTerminalId = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalId);			
		}
		try {
			aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType.trim());
		} catch (Exception e) {
			aCopperSegment.aTerminalType = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalType);			
		}
		try {
			aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation.trim());
		} catch (Exception e) {
			aCopperSegment.aTerminalLocation = (StringOpt) IDLUtil.toOpt(StringOpt.class, aTerminalLocation);			
		}
		try {
			aCopperSegment.aInBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInBindingPost.trim());
		} catch (Exception e) {
			aCopperSegment.aInBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInBindingPost);			
		}
		try {
			aCopperSegment.aOutBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutBindingPost.trim());
		} catch (Exception e) {
			aCopperSegment.aOutBindingPost = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutBindingPost);			
		}
		try {
			aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor.trim());
		} catch (Exception e) {
			aCopperSegment.aOutPairColor = (StringOpt) IDLUtil.toOpt(StringOpt.class, aOutPairColor);			
		}
		try {
			aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName.trim());
		} catch (Exception e) {
			aCopperSegment.aInCableName = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCableName);			
		}
		try {
			aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair.trim());
		} catch (Exception e) {
			aCopperSegment.aInCablePair = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInCablePair);			
		}

		return aCopperSegment;
	}
	
	public static CopperSegmentBP createCopperSegmentBP(
		String aSegmentNumber,
		String aTerminalId,
		String aTerminalType,
		String aTerminalLocation,
		String aInBindingPost,
		String aOutBindingPost,
		String aOutPairColor,
		String aInCableName,
		String aInCablePair,
		String aCommitStatus,
		String aRestrictionCrossConnect,
		String aRemarkTerminal,
		String aRemarkCablePair,
		String aDLERemark) {
			
		CopperSegmentBP aCopperSegmentBP = BuildEmptyIDL.buildEmptyCopperSegmentBPObject();

		CopperSegment aCopperSegment = createCopperSegment(
											aSegmentNumber,
											aTerminalId,
											aTerminalType,
											aTerminalLocation,
											aInBindingPost,
											aOutBindingPost,
											aOutPairColor,
											aInCableName,
											aInCablePair);

		aCopperSegmentBP.aCopperSegment = (CopperSegmentOpt) IDLUtil.toOpt(CopperSegmentOpt.class, aCopperSegment);
		aCopperSegmentBP.aCommitStatus = setStringOptWithTrim(aCommitStatus);
		aCopperSegmentBP.aDLERemark = setStringOptWithTrim(aDLERemark);
		aCopperSegmentBP.aRemarkCablePair = setStringOptWithTrim(aRemarkCablePair);
		aCopperSegmentBP.aRemarkTerminal = setStringOptWithTrim(aRemarkTerminal);
		aCopperSegmentBP.aRestrictionCrossConnect = setStringOptWithTrim(aRestrictionCrossConnect);
		
		return aCopperSegmentBP;
	}


	public static ProductSubscriptionAction createProductSubscriptionAction(
		String aCircuitId, 
		String aORDNumber, 
		String aDueDate) {
		
		ProductSubscriptionAction aProductSubscriptionAction = BuildEmptyIDL.buildEmptyProductSubscriptionActionObject();
		try {
			aProductSubscriptionAction.aId = setStringOptWithTrim(aCircuitId.replace('.','/'));
		} catch (Exception any) { }
		aProductSubscriptionAction.aOrderNumber = setStringOptWithTrim(aORDNumber);
		//- format of date retrieved from LFACS is MM-DD-YY. RMIM needs to convert this to EiaDateOpt on the response back to the clients
		String aDate = aDueDate.trim();
	
		short yy = Short.parseShort(aDate.substring(6,8));
		short mm = Short.parseShort(aDate.substring(0,2));
		short ddd = Short.parseShort(aDate.substring(3,5));
		
		EiaDate aEiaDate = new EiaDate(yy, mm, ddd);
		
		aProductSubscriptionAction.aDueDate = (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, aEiaDate);		
		
		return aProductSubscriptionAction;
		}


	public static ServiceRequest createServiceRequest(
		String aCircuitId,
		String aORDNumber,
		String aDueDate) {
			
		ServiceRequest aServiceRequest = BuildEmptyIDL.buildEmptyServiceRequestObject();

		ProductSubscriptionAction aProductSubscriptionAction = createProductSubscriptionAction(aCircuitId, aORDNumber, aDueDate);

		aServiceRequest.aProductSubscriptionActions = (ProductSubscriptionActionSeqOpt)IDLUtil.toOpt(ProductSubscriptionActionSeqOpt.class, aProductSubscriptionAction);
		
		return aServiceRequest;
	}
	
	public static Loop createFACSLoopForBP(
		String aCircuitId1,
		String aCircuitId2,
		String aCircuitId3,
		String aBondedCrossReferenceCircuitId,
		String aWiredOutOfLimit,
		String aTelecomServicePriority,
		String aEssentialService,
		CopperSegmentBP[] aCopperSegmentBP) {
		
		Loop aLoop = BuildEmptyIDL.buildEmptyLoopObject();
	
		String[] aCircuitIds = {"", "", ""};
		try {
			aCircuitIds[0] = aCircuitId1.replace('.','/');
		} catch (Exception any) { }
		try {
			aCircuitIds[1] = aCircuitId2.replace('.','/');
		} catch (Exception any) { }
		try {
			aCircuitIds[2] = aCircuitId3.replace('.','/');
		} catch (Exception any) { }
		
		try {
			aLoop.aBondedCrossReferenceCircuitId = setStringOptWithTrim(aBondedCrossReferenceCircuitId.replace('.','/'));
		} catch (Exception any) { }
		aLoop.aCircuitIds = (StringSeqOpt) IDLUtil.toOpt(StringSeqOpt.class, aCircuitIds);
		aLoop.aCopperSegments = (CopperSegmentBPSeqOpt)IDLUtil.toOpt(CopperSegmentBPSeqOpt.class, aCopperSegmentBP);
		aLoop.aEssentialService = setStringOptWithTrim(aEssentialService);
		aLoop.aTelecomServicePriority = setStringOptWithTrim(aTelecomServicePriority);
		aLoop.aWiredOutOfLimit = setStringOptWithTrim(aWiredOutOfLimit);
	
		return aLoop;
	}
	
	
	public static OpticalLineTerminal createOpticalLineTerminal(
		String aAction,
		String aId,
		Location aOLTLocation,
		String aStatus,
		String aCategory,
		String aSite,
		String aVendor,
		String aLineUp,
		String aFrameBay,
		String aShelf,
		String aShelfHECI,
		String aSlot,
		String aCardHECI,
		String aPort,
		String aTemplateName,
		String aChannelization,
		String aBandwidth,
		String aConnector,
		String aModelNumber,
		String aEquipmentTargetId,
		Location aCarrierLocationAddress,
		String aVLANId,
		String aVirtualPathId,
		String aVirtualChannelId,
		String aLogicalPort) {
		OpticalLineTerminal aOpticalLineTerminal = BuildEmptyIDL.buildEmptyOpticalLineTerminalObject();

		aOpticalLineTerminal.aAction = setStringOptWithTrim( aAction);
		aOpticalLineTerminal.aId = setStringOptWithTrim( aId);
		aOpticalLineTerminal.aOLTLocation = (LocationOpt) IDLUtil.toOpt(LocationOpt.class, aOLTLocation);
		aOpticalLineTerminal.aStatus = setStringOptWithTrim( aStatus);
		aOpticalLineTerminal.aCategory = setStringOptWithTrim( aCategory);
		aOpticalLineTerminal.aSite = setStringOptWithTrim( aSite);
		aOpticalLineTerminal.aVendor = setStringOptWithTrim( aVendor);
		aOpticalLineTerminal.aLineUp = setStringOptWithTrim( aLineUp);
		aOpticalLineTerminal.aFrameBay = setStringOptWithTrim( aFrameBay);
		aOpticalLineTerminal.aShelf = setStringOptWithTrim( aShelf);
		aOpticalLineTerminal.aShelfHECI = setStringOptWithTrim( aShelfHECI);
		aOpticalLineTerminal.aSlot = setStringOptWithTrim( aSlot);
		aOpticalLineTerminal.aCardHECI = setStringOptWithTrim( aCardHECI);
		aOpticalLineTerminal.aPort = setStringOptWithTrim( aPort);
		aOpticalLineTerminal.aTemplateName = setStringOptWithTrim( aTemplateName);
		aOpticalLineTerminal.aChannelization = setStringOptWithTrim( aChannelization);
		aOpticalLineTerminal.aBandwidth = setStringOptWithTrim( aBandwidth);
		aOpticalLineTerminal.aConnector = setStringOptWithTrim( aConnector);
		aOpticalLineTerminal.aModelNumber = setStringOptWithTrim( aModelNumber);
		aOpticalLineTerminal.aEquipmentTargetId = setStringOptWithTrim( aEquipmentTargetId);
		aOpticalLineTerminal.aCarrierLocationAddress = (LocationOpt) IDLUtil.toOpt(LocationOpt.class, aCarrierLocationAddress);
		aOpticalLineTerminal.aVLANId = setStringOptWithTrim( aVLANId);
		aOpticalLineTerminal.aVirtualPathId = setStringOptWithTrim( aVirtualPathId);
		aOpticalLineTerminal.aVirtualChannelId = setStringOptWithTrim( aVirtualChannelId);
		aOpticalLineTerminal.aLogicalPort = setStringOptWithTrim( aLogicalPort);

		return aOpticalLineTerminal;
	}

	public static OpticalNetworkTerminal createOpticalNetworkTerminal(String aId, String aModelNumber, String aAccessId, String aPort, String aIndex, String aVLANId) {
		OpticalNetworkTerminal aOpticalNetworkTerminal = BuildEmptyIDL.buildEmptyOpticalNetworkTerminalObject();

		aOpticalNetworkTerminal.aId =  setStringOptWithTrim(aId);
		aOpticalNetworkTerminal.aModelNumber =  setStringOptWithTrim(aModelNumber);
		aOpticalNetworkTerminal.aAccessId =  setStringOptWithTrim(aAccessId);
		aOpticalNetworkTerminal.aPort = setStringOptWithTrim (aPort);
		aOpticalNetworkTerminal.aIndex = setStringOptWithTrim (aIndex);
		aOpticalNetworkTerminal.aVLANId = setStringOptWithTrim (aVLANId);

		return aOpticalNetworkTerminal;
	}

	public static Fttp createFttp(OpticalNetworkTerminal aOpticalNetworkTerminal, OpticalLineTerminal aOpticalLineTerminal, FiberSegment[] aFiberSegment) {
		Fttp aFttp = BuildEmptyIDL.buildEmptyFttpObject();

		aFttp.aOpticalLineTerminal = (OpticalLineTerminalOpt) IDLUtil.toOpt(OpticalLineTerminalOpt.class, aOpticalLineTerminal);
		aFttp.aOpticalNetworkTerminal = (OpticalNetworkTerminalOpt) IDLUtil.toOpt(OpticalNetworkTerminalOpt.class, aOpticalNetworkTerminal);
		aFttp.aSegments = (FiberSegmentSeqOpt) IDLUtil.toOpt(FiberSegmentSeqOpt.class, aFiberSegment);

		return aFttp;
	}

	public static FiberSegment createFiberSegment(
		String aSegmentNumber,
		String aTerminalId,
		String aTerminalType,
		String aNetworkPort,
		String aAccessPort,
		String aInFiberCable,
		String aInFiberStrand,
		String aClli8) {
		FiberSegment aFiberSegment = BuildEmptyIDL.buildEmptyFiberSegmentObject();

		aFiberSegment.aSegmentNumber =  setStringOptWithTrim( aSegmentNumber);
		aFiberSegment.aTerminalId =  setStringOptWithTrim(aTerminalId);
		aFiberSegment.aTerminalType =  setStringOptWithTrim( aTerminalType);
		aFiberSegment.aNetworkPort =  setStringOptWithTrim(aNetworkPort);
		aFiberSegment.aAccessPort =  setStringOptWithTrim(aAccessPort);
		//Changed for DR 80101, 12/13/2007
		if ((aInFiberCable != null && aInFiberCable.trim().length() != 0)  && aClli8 != null) 
		{
			try
			{
				aFiberSegment.aInFiberCable = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInFiberCable.trim() + "/" + aClli8.toUpperCase());
			}
			catch(Exception e)
			{
				aFiberSegment.aInFiberCable = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInFiberCable + "/" + aClli8.toUpperCase());				
			}
		} 
		else
		{  
			try
			{   //Changed for DR 80101, 12/19/2007
			    if (aInFiberCable.trim().length() != 0 )
				  aFiberSegment.aInFiberCable = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInFiberCable.trim());
			}
			catch(Exception e)
			{
				aFiberSegment.aInFiberCable = (StringOpt) IDLUtil.toOpt(StringOpt.class, aInFiberCable);				
			}
			
		}

		aFiberSegment.aInFiberStrand =  setStringOptWithTrim(aInFiberStrand);

		return aFiberSegment;
	}
	
	public static FiberSegment createFiberSegment(
		String aSegmentNumber,
		String aTerminalId,
		String aTerminalType,
		String aNetworkPort,
		String aAccessPort,
		String aInFiberCable,
		String aInFiberStrand) {
		FiberSegment aFiberSegment = BuildEmptyIDL.buildEmptyFiberSegmentObject();

		aFiberSegment.aSegmentNumber = setStringOptWithTrim(aSegmentNumber);
		aFiberSegment.aTerminalId =  setStringOptWithTrim(aTerminalId);
		aFiberSegment.aTerminalType =  setStringOptWithTrim(aTerminalType);
		aFiberSegment.aNetworkPort =  setStringOptWithTrim(aNetworkPort);
		aFiberSegment.aAccessPort =  setStringOptWithTrim(aAccessPort);
		
		aFiberSegment.aInFiberCable =  setStringOptWithTrim(aInFiberCable);

		aFiberSegment.aInFiberStrand =  setStringOptWithTrim(aInFiberStrand);

		return aFiberSegment;
	}
	
	public static StringOpt setStringOptWithTrim(String aStr)
	{
		StringOpt aStrOpt = null;
		try
		{
			aStrOpt = (StringOpt) IDLUtil.toOpt(aStr.trim());
		}
		catch(Exception e)
		{
			aStrOpt = (StringOpt) IDLUtil.toOpt((String)aStr);
		}
		
		return aStrOpt;
	}
	
}
