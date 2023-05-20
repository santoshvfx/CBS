// $Id: ModifyFacilityInfo.java,v 1.17 2006/08/24 22:08:47 sc8468 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2005.  All Rights Reserved.
//#
//# History :
//# Date      			| Author      		| Notes
//# ----------------------------------------------------------------------------
//#	06/24/2005	  		 Sriram Chevuturu		Creation.
//# 08/07/2005			 Sriram Chevuturu	Added Create Request Method.
//# 08/09/2005			 Sriram Chevuturu	Added Code so as to finish everything else.
//# 08/11/2005			 Sriram Chevuturu	Added Code for OrderAction.
//# 08/11/2005			 Sriram Chevuturu	Added Code for DR 142178: Exception Handling.
//# 08/23/2005			 Sriram Chevuturu	Some Minor Code Changes.
//# 04/02/2006           Sriram Chevuturu	Changed for Lightspeed Release 2

package com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo;

import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SoacDefect;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.xng.XNGService;
import com.sbc.eia.bis.embus.service.xng.ModifyFacilityInfoRequest.impl.*;
import com.sbc.eia.bis.embus.service.xng.ModifyFacilityInfoRequest.impl.ModifyFacilityInfoRequestImpl;
import com.sbc.eia.bis.embus.service.xng.ModifyFacilityInfoResponse.impl.ModifyFacilityInfoResponseImpl;
import com.sbc.eia.bis.embus.service.xng.access.XNGHelper;
import com.sbc.eia.bis.embus.service.xng.helpers.SendRequestToXNG;
import com.sbc.eia.bis.facades.rm.transactions.ModifyFacilityInfo.ModifyFacilityInfoReturn;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.DSLAMIdHelper;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.LocationOpt;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.bis.embus.service.xng.XNGService;

/**
 * @author sc8468
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ModifyFacilityInfo {

	private Utility aUtility = null;
	private Hashtable aProperties = null;
	private String aRuleFile = null;
	private XNGService aService = null;

	/**
	 * Constructor for ModifyInventory
	 * @param Utility utility
	 * @param Hashtable properties
	 */
	public ModifyFacilityInfo(Utility utility, Hashtable properties) {
		aProperties = properties;
		aUtility = utility;
		aRuleFile = (String) properties.get(SendRequestToXNG.XNG_EXCEPTION_RULE_FILE_TAG);
	}

	public ModifyFacilityInfoReturn sendRequest(
		BisContext aContext,
		String aWireCenter,
		String aCircuitId,
		String aTaperCode,
		Fttn aFttn,
		Fttp aFttp,
		boolean isUpdateSegment,
		SoacDefect[] aDefectList,
		String OldTRE)
		throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound {

		String myMethodName = "ModifyFacilityInfo::sendRequest()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		ModifyFacilityInfoRequestImpl aRequest = null;
		// build request item

		aRequest = createRequest(aContext, aWireCenter, aCircuitId, aTaperCode, aFttn, aFttp, isUpdateSegment, aDefectList,OldTRE);

		// process request
		if (aService == null) {
			try {
				aService = new XNGService(aProperties, aUtility);
			} catch (ServiceException e) {
				aUtility.log(LogEventId.DEBUG_LEVEL_1, e.getMessage());
				 ExceptionBuilder.parseException(
							aContext,
							aRuleFile,
							null,
							null,
							e.getMessage(),
							true,
							1,
							null,
							e,
							aUtility,
							null,
							null,
							null);
			}

		}
			
		//Wait for Mark to change helper to return ModifyFacilityInfoResponseImpl
		ModifyFacilityInfoResponseImpl aResponse = (new SendRequestToXNG(aUtility, aContext, aProperties, aService)).sendRequest(aRequest, null);
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		//Only FTTN needs return
		if (aFttn != null)
		{
			return buildReturn(aResponse);
		}
		else 
			return null;
	}

	public ModifyFacilityInfoReturn buildReturn(ModifyFacilityInfoResponseImpl aResponse)
	{
		String myMethodName = "ModifyFacilityInfo::buildReturn()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);		
		ModifyFacilityInfoReturn aReturn = new ModifyFacilityInfoReturn();
		
		aReturn.setFttnDslamName(aResponse.getFttnResponse().getDSLAMId());
		aReturn.setFttnVlanId(aResponse.getFttnResponse().getVLANId());
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return aReturn;
	}
	
	public ModifyFacilityInfoRequestImpl createRequest(
		BisContext aContext,
		String aWireCenter,
		String aCircuitId,
		String aTaperCode,
		Fttn aFttn,
		Fttp aFttp,
		boolean isUpdateSegment,
		SoacDefect[] aDefectList,
		String oldTRE) {
		//throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound {

		String myMethodName = "ModifyFacilityInfo::createRequest()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		ModifyFacilityInfoRequestImpl aRequest = new ModifyFacilityInfoRequestImpl();

		// REQUIRED: Schema Version
		aRequest.setSchemaVersion(XNGHelper.XNG_RC_VERSION);


		// REQUIRED: LS_CircuitID - aCustomerTransportId
		// set LS_CircuitID in the REQUEST
		//
		CTPATHIDImpl aCTPATHIDImpl = new CTPATHIDImpl();
		aCTPATHIDImpl.setPathID(aCircuitId);
		LSCircuitIDImpl aLSCircuitIDImpl = new LSCircuitIDImpl();
		aLSCircuitIDImpl.setCTPATHID(aCTPATHIDImpl);

		aRequest.setLSCircuitID(aLSCircuitIDImpl);

		aRequest.setNPATTA(aWireCenter);

		if (aFttn != null) 
		{
			FttnRequestTypeImpl aFttnTypeImpl = createFttnRequest(aTaperCode, aFttn, isUpdateSegment);

			aRequest.setFttnRequest(aFttnTypeImpl);
	        if(oldTRE != null)
    	    {
				DSLAMIdHelper aOldDSLAM_Id_Helper = new DSLAMIdHelper(aUtility, aProperties);
				aOldDSLAM_Id_Helper.splitDSLAM(IDLUtil.toOpt(oldTRE));
       	 		
				aRequest.setOldSwitchTre(aOldDSLAM_Id_Helper.getDSLAMId());
				// set the new TRE if only the old TRE is there. SWITCH FTTN Case
		        if (!OptHelper.isDSLAMTransportOptNull(aFttn.aDSLAM)) 
		        {
					if (!OptHelper.isStringOptEmpty(aFttn.aDSLAM.theValue().aId)) 
	        	 	{
						DSLAMIdHelper aNewDSLAM_Id_Helper = new DSLAMIdHelper(aUtility, aProperties);
						aNewDSLAM_Id_Helper.splitDSLAM(aFttn.aDSLAM.theValue().aId);
	        	 		
						aRequest.setNewSwitchTre(aNewDSLAM_Id_Helper.getDSLAMId());
	        		}
		        }
    	    	
        	}     
			
		} 
		else if (aFttp != null) 
		{
			FttpRequestTypeImpl aFttpTypeImpl = createFttpRequest(aTaperCode, aFttp, isUpdateSegment);

			aRequest.setFttpRequest(aFttpTypeImpl);
		}

		SeqDefaultDefectListImpl aSeqDefaultDefectListImpl = new SeqDefaultDefectListImpl();

		if (aDefectList != null) {
			for (int i = 0; i < aDefectList.length; i++) {
				DefectInfoImpl aDefectInfoImpl = new DefectInfoImpl();

				aDefectInfoImpl.setDefectCode(aDefectList[i].getDefectCode());
				aDefectInfoImpl.setDefectTag("CABLE");
				aDefectInfoImpl.setDefectValue(aDefectList[i].getDefectCable());

				aSeqDefaultDefectListImpl.getItem().add(aDefectInfoImpl);

				DefectInfoImpl aDefectInfoImpl2 = new DefectInfoImpl();

				aDefectInfoImpl2.setDefectCode(aDefectList[i].getDefectCode());
				aDefectInfoImpl2.setDefectTag("PAIR");
				aDefectInfoImpl2.setDefectValue(aDefectList[i].getDefectPair());

				aSeqDefaultDefectListImpl.getItem().add(aDefectInfoImpl2);
			}

			aRequest.setDefectListGRP(aSeqDefaultDefectListImpl);
		}
				
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return aRequest;
	}

	public FttpRequestTypeImpl createFttpRequest(String aTaperCode, Fttp aFttp, boolean isUpdateSegment) {
		
		String myMethodName = "ModifyFacilityInfo::createFttpRequest()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);		
		//throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound {
		FttpRequestTypeImpl aFttpRequestTypeImpl = new FttpRequestTypeImpl();

		SeqDefaultFTTPImpl aSeqDefaultFTTPImpl = new SeqDefaultFTTPImpl();

		OLTTImpl aOLTTImpl = new OLTTImpl();
		if (!OptHelper.isOpticalLineTerminalOptNull(aFttp.aOpticalLineTerminal)) {
			// OLT_Id

			try {
				aOLTTImpl.setOLTId(aFttp.aOpticalLineTerminal.theValue().aId.theValue().trim());
			} catch (org.omg.CORBA.BAD_OPERATION e) {
				//Null value in Opt.
				//Optional field, don't need to set.
			}
			// OLT_Model
			try {
				aOLTTImpl.setOLTModel(aFttp.aOpticalLineTerminal.theValue().aModelNumber.theValue().trim());
			} catch (org.omg.CORBA.BAD_OPERATION e) {
				//Null value in Opt.
				//Optional field, don't need to set.
			}
			// OLT_Port
			try {
				aOLTTImpl.setOLTPort(aFttp.aOpticalLineTerminal.theValue().aPort.theValue().trim());
			} catch (org.omg.CORBA.BAD_OPERATION e) {
				//Null value in Opt.
				//Optional field, don't need to set.
			}
			// OLT_TargetId
			try {
				aOLTTImpl.setOLTTargetId(aFttp.aOpticalLineTerminal.theValue().aEquipmentTargetId.theValue().trim());
			} catch (org.omg.CORBA.BAD_OPERATION e) {
				//Null value in Opt.
				//Optional field, don't need to set.
			}
		}

		ONTTImpl aONTTImpl = new ONTTImpl();
		if (!OptHelper.isOpticalNetworkTerminalOptNull(aFttp.aOpticalNetworkTerminal)) {
			// ONT_Id
			try {
				aONTTImpl.setONTId(aFttp.aOpticalNetworkTerminal.theValue().aId.theValue().trim());
			} catch (org.omg.CORBA.BAD_OPERATION e) {
				//Null value in Opt.
				//Optional field, don't need to set.
			}
			// ONT_Model
			try {
				aONTTImpl.setONTModel(aFttp.aOpticalNetworkTerminal.theValue().aModelNumber.theValue().trim());
			} catch (org.omg.CORBA.BAD_OPERATION e) {
				//Null value in Opt.
				//Optional field, don't need to set.
			}
			// ONT_Port
			try {
				aONTTImpl.setONTPort(aFttp.aOpticalNetworkTerminal.theValue().aPort.theValue().trim());
			} catch (org.omg.CORBA.BAD_OPERATION e) {
				//Null value in Opt.
				//Optional field, don't need to set.
			}
			// ONT_AID
			try {
				aONTTImpl.setONTAID(aFttp.aOpticalNetworkTerminal.theValue().aAccessId.theValue().trim());
			} catch (org.omg.CORBA.BAD_OPERATION e) {
				//Null value in Opt.
				//Optional field, don't need to set.
			}
			// ONT_INDEX
			try {
				aONTTImpl.setONTIndex(aFttp.aOpticalNetworkTerminal.theValue().aIndex.theValue().trim());
			} catch (org.omg.CORBA.BAD_OPERATION e) {
				//Null value in Opt.
				//Optional field, don't need to set.
			}
			
		}

		if (isUpdateSegment) {

			for (int i = 0; i < aFttp.aSegments.theValue().length; i++) {
				FTTPTImpl aFTTPTImpl = new FTTPTImpl();
				// Fttp_CableId
				try {
					aFTTPTImpl.setFttpCableId(aFttp.aSegments.theValue()[i].aInFiberCable.theValue().trim());
				} catch (org.omg.CORBA.BAD_OPERATION e) {
					//Null value in Opt.
					//Optional field, don't need to set.
				}
				// Fttp_Strand
				try {
					String aValue = aFttp.aSegments.theValue()[i].aInFiberStrand.theValue().trim();
					try {
						aFTTPTImpl.setFttpStrand(Integer.parseInt(aValue));
					} catch (NumberFormatException e) {
					}
				} catch (org.omg.CORBA.BAD_OPERATION e) {
					//Null value in Opt.
					//Optional field, don't need to set.
				}
				// Fttp_EquipmentId
				try {
					aFTTPTImpl.setFttpEquipmentId(aFttp.aSegments.theValue()[i].aTerminalId.theValue().trim());
				} catch (org.omg.CORBA.BAD_OPERATION e) {
					//Null value in Opt.
					//Optional field, don't need to set.
				}
				// Fttp_Network_PortId
				try {
					aFTTPTImpl.setFttpNetworkPortId(aFttp.aSegments.theValue()[i].aNetworkPort.theValue().trim());
				} catch (org.omg.CORBA.BAD_OPERATION e) {
					//Null value in Opt.
					//Optional field, don't need to set.
				}
				// Fttp_Access_PortId
				try {
					aFTTPTImpl.setFttpAccessPortId(aFttp.aSegments.theValue()[i].aAccessPort.theValue().trim());
				} catch (org.omg.CORBA.BAD_OPERATION e) {
					//Null value in Opt.
					//Optional field, don't need to set.
				}

				aSeqDefaultFTTPImpl.getFTTPSegmentNumber().add(aFTTPTImpl);
			} //for FTTPT

		} // if isupdateSegment
		aSeqDefaultFTTPImpl.setOLTT(aOLTTImpl);
		aSeqDefaultFTTPImpl.setONTT(aONTTImpl);
		aSeqDefaultFTTPImpl.setTaperCode(aTaperCode);

		aFttpRequestTypeImpl.setFttpGRP(aSeqDefaultFTTPImpl);

		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return aFttpRequestTypeImpl;

	}

	public FttnRequestTypeImpl createFttnRequest(String aTaperCode, Fttn aFttn, boolean isUpdateSegment) {		
		//throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound {

		String myMethodName = "ModifyFacilityInfo::createFttnRequest()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		FttnRequestTypeImpl aFttnRequestTypeImpl = new FttnRequestTypeImpl();
		
        if (!OptHelper.isDSLAMTransportOptNull(aFttn.aDSLAM)) {
             if (!OptHelper.isStringOptEmpty(aFttn.aDSLAM.theValue().aId)) {
					
				DSLAMIdHelper aDSLAM_Id_Helper = new DSLAMIdHelper(aUtility, aProperties);
				aDSLAM_Id_Helper.splitDSLAM(aFttn.aDSLAM.theValue().aId);
					
				aFttnRequestTypeImpl.setDSLAMEquipmentId(aDSLAM_Id_Helper.getDSLAMId());
				aFttnRequestTypeImpl.setCODSLAMPort(aDSLAM_Id_Helper.getDSLAMPort());
                    
            }
        }


		try {
			aFttnRequestTypeImpl.setTaperCode(aTaperCode);
		} catch (org.omg.CORBA.BAD_OPERATION e) {
			//Null value in Opt.
			//Optional field, don't need to set.
		}
		catch(Exception e){}

		SeqDefaultFTTNImpl aSeqDefaultFTTNImpl = new SeqDefaultFTTNImpl();
			
		if (isUpdateSegment) {

			int numSegs = -1;
			try
			{
				numSegs = aFttn.aSegments.theValue().length;
					
			}catch(Exception e){}

			for (int i = 0; i < numSegs; i++) {
				FTTNTImpl aFTTNTImpl = new FTTNTImpl();

				try {
					aFTTNTImpl.setCableID(aFttn.aSegments.theValue()[i].aInCableName.theValue().trim());
				} catch (org.omg.CORBA.BAD_OPERATION e) {
					//Null value in Opt.
					//Optional field, don't need to set.
				}
				catch(Exception e){}				
				try {
					aFTTNTImpl.setCablePairNumber(Integer.parseInt(aFttn.aSegments.theValue()[i].aInCablePair.theValue().trim()));
				} catch (org.omg.CORBA.BAD_OPERATION e) {
					//Null value in Opt.
					//Optional field, don't need to set.
				}
				catch(Exception e){}
				try {
					aFTTNTImpl.setFttnEquipmentId(aFttn.aSegments.theValue()[i].aTerminalId.theValue().trim());
				} catch (org.omg.CORBA.BAD_OPERATION e) {
					//Null value in Opt.
					//Optional field, don't need to set.
				}
				catch(Exception e){}
				//try {
				//	aFTTNTImpl.setFttnEquipmentLocation(aFttn.aSegments.theValue()[i].aTerminalLocation.theValue().trim());
				//} catch (org.omg.CORBA.BAD_OPERATION e) {
				//Null value in Opt.
				//Optional field, don't need to set.
				//}
				//try {
				//	aFTTNTImpl.setFttnEquipmentType(aFttn.aSegments.theValue()[i].aTerminalType.theValue().trim());
				//} catch (org.omg.CORBA.BAD_OPERATION e) {
				//Null value in Opt.
				//Optional field, don't need to set.
				//}
				try {
					aFTTNTImpl.setOutPairColor(aFttn.aSegments.theValue()[i].aOutPairColor.theValue().trim());
				} catch (org.omg.CORBA.BAD_OPERATION e) {
					//Null value in Opt.
					//Optional field, don't need to set.
				}
				catch(Exception e){}				
				//Set in binding post
				//

				
				if(aFttn.aSegments.theValue()[i].aInBindingPost != null)
				{
					CTPORTIDTypeImpl aCTPORTIDTypeImpl = new CTPORTIDTypeImpl();				
					try {
						aCTPORTIDTypeImpl.setPortID(aFttn.aSegments.theValue()[i].aInBindingPost.theValue().trim());
						InBindingPostTypeImpl aInBindingPostTypeImpl = new InBindingPostTypeImpl();
						aInBindingPostTypeImpl.setCTPORTID(aCTPORTIDTypeImpl);
						aFTTNTImpl.setInBindingPost(aInBindingPostTypeImpl);
					
					} catch (org.omg.CORBA.BAD_OPERATION e) {
					//Null value in Opt.
					//Optional field, don't need to set.
					}
					catch(Exception e){}

				}
				
				//Set out binding post
				//
				if(aFttn.aSegments.theValue()[i].aOutBindingPost != null)
				{
					CTPORTIDTypeImpl aOutCTPORTIDTypeImpl = new CTPORTIDTypeImpl();
					try {
						aOutCTPORTIDTypeImpl.setPortID(aFttn.aSegments.theValue()[i].aOutBindingPost.theValue().trim());
						OutBindingPostTypeImpl aOutBindingPostTypeImpl = new OutBindingPostTypeImpl();
						aOutBindingPostTypeImpl.setCTPORTID(aOutCTPORTIDTypeImpl);
						aFTTNTImpl.setOutBindingPost(aOutBindingPostTypeImpl);
					
					} catch (org.omg.CORBA.BAD_OPERATION e) {
					//Null value in Opt.
					//Optional field, don't need to set.
					}
					catch(Exception e){}					

				}
				aSeqDefaultFTTNImpl.getFTTNSegmentNumber().add(aFTTNTImpl);

			} // for FTTNT

		} // isUpdateSegment

		try {
			aFttnRequestTypeImpl.setFttnGRP(aSeqDefaultFTTNImpl);
		} catch (org.omg.CORBA.BAD_OPERATION e) {
			//Null value in Opt.
			//Optional field, don't need to set.
		}
		catch(Exception e){}			
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return aFttnRequestTypeImpl;
	}
}