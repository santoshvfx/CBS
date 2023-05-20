//$Id: SvcOrderRequestTester.java,v 1.6 2007/12/20 17:21:04 op1664 Exp $
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
//# 05/10/2007  Jon Costa			  CR13804: Change for OTN==TN and actionCode == M

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;


import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test.TestLogger;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrder;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPair;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberPortingStatus;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberPortingStatusOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;


/**
 * @author sc8468
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SvcOrderRequestTester {


	public static void main(String args[])
	{
		Hashtable myProperties = new Hashtable();
		myProperties.put("STDOUT","TRUE");
		myProperties.put("BIS_NAME","RM13.0");
		myProperties.put("BIS_VERSION","13.0.1");
		myProperties.put("LOG_POLICY_PATH","");	
		myProperties.put("bis.platform","dev");						
		
		TestLogger myLogger= new TestLogger();		



		//test for request string

		String aSOACServiceOrderNumber 		= "C910200L1";
		String aSOACServiceOrderCorrectionSuffix = new String("A");
		String aNetworkType				= new String("VOIP");
			
		String aOrderActionId				= "5678901234";	
		String aOrderNumber					= "1234567890";
//		String aOrderActionType				= "Provide";
		String aOrderActionType				= "Cease";
		
		BooleanOpt aCompletionIndicator		= new BooleanOpt();
			aCompletionIndicator.theValue(false);
		StringOpt aSubActionType			= new StringOpt();
//			aSubActionType.theValue("Cancel");
//			aSubActionType.theValue("Amend");
			
//		String aCircuitId					= "nn.aaaa.nnnnnn.nnn.ccc";
		String aCircuitId					= "XX.MCXX.101002..SW";
		
		
		StringOpt locationId = new StringOpt();
		locationId.theValue("LOC");
		ProviderLocationProperty[] aProviderLocationProperty = new ProviderLocationProperty[2];
		aProviderLocationProperty[0] = new ProviderLocationProperty();
		aProviderLocationProperty[0].aServiceAddress = new AddressOpt();
		
		Address aAddress =	new Address();

		FieldedAddress aFieldedAddress = new FieldedAddress();
		//aFieldedAddress.aAssignedHouseNumber= new StringOpt();
		//aFieldedAddress.aAssignedHouseNumber.theValue("7475");
		//aFieldedAddress.aStreetDirection = new StringOpt();
		//aFieldedAddress.aStreetDirection.theValue("LEFT");
		aFieldedAddress.aStreetName = new StringOpt();		
		aFieldedAddress.aStreetName.theValue("102 Scheele Dr");
		aFieldedAddress.aStreetThoroughfare = new StringOpt();		
		//aFieldedAddress.aStreetThoroughfare.theValue("ThoroughFare");
		aFieldedAddress.aStreetNameSuffix = new StringOpt();		
		aFieldedAddress.aStreetNameSuffix.theValue("St.");
		aFieldedAddress.aCity = new StringOpt();		
		//aFieldedAddress.aCity.theValue("Edison");
		aFieldedAddress.aState = new StringOpt();		
		//aFieldedAddress.aState.theValue("NJ");
		aFieldedAddress.aPostalCode = new StringOpt();		
		//aFieldedAddress.aPostalCode.theValue("07032");		
		aFieldedAddress.aLevelType = new StringOpt();
		//aFieldedAddress.aLevelType.theValue("aLevelType");
		aFieldedAddress.aLevelValue = new StringOpt();
		
		//aFieldedAddress.aLevelValue.theValue("aLevelValue");
		aFieldedAddress.aUnitType = new StringOpt();
		//aFieldedAddress.aUnitType.theValue("aUnitType");
		aFieldedAddress.aUnitValue = new StringOpt();		
		//aFieldedAddress.aUnitValue.theValue("aUnitValue");
		aFieldedAddress.aStructureType = new StringOpt();		
		//aFieldedAddress.aStructureType.theValue("aStructureType");
		aFieldedAddress.aStructureValue = new StringOpt();		
		//aFieldedAddress.aStructureValue.theValue("aStructureValue");
		
		
		aAddress.aFieldedAddress(aFieldedAddress);
		
		aProviderLocationProperty[0].aServiceAddress.theValue(aAddress);
		
		aProviderLocationProperty[0].aPrimaryNpaNxx = new StringOpt();		
		//aProviderLocationProperty[0].aPrimaryNpaNxx.theValue("479442");		
		
		String aNpaNxx = "479442";
		
		Location aServiceLocation			= new Location(locationId,aProviderLocationProperty);
		
	
		short x = 2005;
		short y =  10;
		short z =  30;
		
		EiaDate aOriginalDueDate			= new EiaDate(x,y,z);

		 x = 2005;
		 y =  8;
		 z =  20;		

		EiaDateOpt aSubsequentDueDate		= new EiaDateOpt();//null;//
		//for Amend Orders comment above line and uncomment below two lines
//		EiaDateOpt aSubsequentDueDate		= new EiaDateOpt();
			aSubsequentDueDate.theValue(new EiaDate(x,y,z));
		
//		 x = 5;
//		 y =  10;
//		 z =  11;		
//		EiaDateOpt aSubsequentDueDate		= null;
//			aSubsequentDueDate.theValue(new EiaDate());
//
//		EiaDateOpt aSubsequentDueDate		= new EiaDateOpt();
//			aSubsequentDueDate.theValue(new EiaDate());
		


		
		StringOpt aContactTelephoneNumber	= new StringOpt(	);
			aContactTelephoneNumber.theValue("4794421111");

		 x = 5;
		 y =  7;
		 z =  26;		

		EiaDate aApplicationDate			= new EiaDate(x,y,z);
				
		StringOpt aTDMTelphoneNumber		= new StringOpt();
			aTDMTelphoneNumber.theValue("4795212936");
		
		StringOpt aRelatedServiceOrderNumber= new StringOpt();
			aRelatedServiceOrderNumber.theValue("");//below line for related Service Order
//			aRelatedServiceOrderNumber.theValue("C12345678");			
		BooleanOpt aAdditionalLineFlag		= new BooleanOpt();
			aAdditionalLineFlag.theValue(false);
		
		BooleanOpt aLineShareDisconnectFlag	= new BooleanOpt();
			aLineShareDisconnectFlag.theValue(false);
		
		String aClassOfService				= "XR7FA";
		
		String aEntity = "C";
		String aRegionPlatTorm = "ST";

				BooleanOpt aResendFlag = new BooleanOpt();
				aResendFlag.theValue(false);
		
		ObjectPropertySeqOpt aProperties	= null;


		VoipReqSnEDataObject[] voipSnEData = new VoipReqSnEDataObject[4];

			SOACServiceOrderRequestGenerator  aSOACRequestGenerator = 
										new SOACServiceOrderRequestGenerator(myProperties,myLogger);

			String aFCIFRequest;
			try {
/*				aFCIFRequest =
					aSendF1F2OrderHelper.getFCIFRequestString(
						aSOACServiceOrderNumber,
						aSOACServiceOrderCorrectionSuffix,
						aNetworkType,
						aOrderActionId,
						aOrderNumber,
						aOrderActionType,
						aCompletionIndicator,
						aSubActionType,
						aCircuitId,
						aServiceLocation,
						aOriginalDueDate,
						aSubsequentDueDate,
						//aContactTelephoneNumber,
						aApplicationDate,
						aTDMTelphoneNumber,
						aRelatedServiceOrderNumber,
						//aAdditionalLineFlag,
						aLineShareDisconnectFlag,
						aClassOfService,
						aResendFlag,
						aEntity,
						aRegionPlatTorm,
						aProperties,
						(Logger)(aSendF1F2OrderHelper),
						"OMS","S");//here is the logger
*/						

/*				aFCIFRequest =		  
					aSOACRequestGenerator.getFCIFRequestStringforFTTX(
						aSOACServiceOrderNumber,
						aSOACServiceOrderCorrectionSuffix,
						aNetworkType,
						aOrderActionId,
						aOrderNumber,
						aOrderActionType,
						aCompletionIndicator,
						aSubActionType,
						aCircuitId,
						aServiceLocation,
						aOriginalDueDate,
						aSubsequentDueDate,
						aApplicationDate,
						aTDMTelphoneNumber,
						aRelatedServiceOrderNumber,
						aLineShareDisconnectFlag,
						aClassOfService,
						aResendFlag,
						aEntity,
						aRegionPlatTorm,
						aProperties,
						"OMS","S");//here is the logger


*/

	TelephoneNumberOrderPairSeqOpt	aTelephoneNumberOrderPairSeqOpt = prepareTelephoneNumberOrderPairSeqOpt();
				
					
				aFCIFRequest =		  
					aSOACRequestGenerator.getFCIFRequestStringForVOIP(
						aSOACServiceOrderNumber,
						aSOACServiceOrderCorrectionSuffix,
						aNetworkType,
						aOrderActionId,
						aOrderNumber,
						aOrderActionType,
						aCompletionIndicator,
						aSubActionType,
						aServiceLocation,
						aOriginalDueDate,
						aSubsequentDueDate,
						aApplicationDate,
						aEntity,
						aRegionPlatTorm,
						aProperties,
						"OMS"," ",
						aTelephoneNumberOrderPairSeqOpt,aNpaNxx,"EV");

					System.out.println("THe New Request String is : \n\n"+aFCIFRequest);			

			} catch (ParserSvcException e) {
				
				e.printStackTrace();	
			}
			catch (DataNotFound dnf)
			{
				System.out.println("no TN's to process, ignore request.");
			}
	}


	public static TelephoneNumberOrderPairSeqOpt prepareTelephoneNumberOrderPairSeqOpt()
	{
		
		TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairSeqOpt =	
												new TelephoneNumberOrderPairSeqOpt();
					
		TelephoneNumberOrderPair aTnOrdPair[] = new TelephoneNumberOrderPair[4];

		String aTN1 = "1234564444", aTN2 = "0987653333", aTN3 = "1625340000", aTN4 = "5069782222";
		String aAI1 = "I", aAI2 = "I", aAI3 = "I", aAI4 = "I";		
		String aOP1 = "ABCDEF", aOP2 = "GHIJKL", aOP3 = "MNOPQR", aOP4 = "STUVWX";
		String aNP1 = "fedcba", aNP2 = "lkjihg", aNP3 = "rqponm", aNP4 = "xwvuts";
		String aLRN1 = "AAAAAA", aLRN2 = "BBBBBB", aLRN3 = "CCCCCC", aLRN4 = "DDDDDD";	
		String aRP1 = "INVU", aRP2 = "INVU",   aRP3 ="INVU", aRP4 ="INVU";
		String aNRP1 = "RTN", aNRP2 = "POUT",   aNRP3 ="POUT", aNRP4 ="POUT";
//		String aOldTN1 = "4444123456", aOldTN2 = "3333098765", aOldTN3 = "00001625340000", aOldTN4 = "22225069782222";
//		String aOldAI1 = "O", aOldAI2 = "O", aOldAI3 = "O", aOldAI4 = "O";		
//		String aOldOP1 = null, aOldOP2 = null, aOldOP3 = null, aOldOP4 = null;
//		String aOldNP1 = null, aOldNP2 = null, aOldNP3 = null, aOldNP4 = null;
//		String aOldLRN1 = "DDDDDD", aOldLRN2 = "CCCCCC", aOldLRN3 = "BBBBBB", aOldLRN4 = "AAAAAA";						
		

		String aOldTN1 = "4444123456", aOldTN2 = "3333098765", aOldTN3 = "0000162534", aOldTN4 = null;
		String aOldAI1 = "O", aOldAI2 = "O", aOldAI3 = "O" , aOldAI4 = null;		
		String aOldOP1 = "xyzrst", aOldOP2 = "qeaiou", aOldOP3 = null, aOldOP4 = null;
		String aOldNP1 = "aeioua", aOldNP2 = "uoieau", aOldNP3 = null, aOldNP4 = null;
		String aOldLRN1 = "DDDDDD", aOldLRN2 = "CCCCCC", aOldLRN3 = null, aOldLRN4 = null;						
		String aORP1 = "INVU", aORP2 = "INVU",   aORP3 ="INVU", aORP4 ="INVU" ;
		String aONRP1 = "POUT", aONRP2 = "RTN",   aONRP3 ="POUT", aONRP4 ="POUT";

		
		aTnOrdPair[0] = prepareTelephoneNumberOrderPair(aTN1,aAI1,aOP1,aNP1,aLRN1,aRP1,aNRP1,aOldTN1,aOldAI1,aOldOP1,aOldNP1,aOldLRN1,aORP1,aONRP1);
		
		aTnOrdPair[1] = prepareTelephoneNumberOrderPair(aTN2,aAI2,aOP2,aNP2,aLRN2,aRP2,aNRP2,aOldTN2,aOldAI2,aOldOP2,aOldNP2,aOldLRN2,aORP2,aONRP2);
		aTnOrdPair[2] = prepareTelephoneNumberOrderPair(aTN3,aAI3,aOP3,aNP3,aLRN3,aRP3,aNRP3,aOldTN3,aOldAI3,aOldOP3,aOldNP3,aOldLRN3,aORP3,aONRP3);
		aTnOrdPair[3] = prepareTelephoneNumberOrderPair(aTN4,aAI4,aOP4,aNP4,aLRN4,null,null,aOldTN4,aOldAI4,aOldOP4,aOldNP4,aOldLRN4,null,null);						
		
		aTelephoneNumberOrderPairSeqOpt.theValue(aTnOrdPair);
		
		return aTelephoneNumberOrderPairSeqOpt;
			
	}

	public static  TelephoneNumberOrderPair prepareTelephoneNumberOrderPair(
	String aTN,
	String aAI,
	String aOP,
	String aNP,
	String aLRN,
	String aRP,
	String aNRP,
	String aOldTN,
	String aOldAI,
	String aOldOP,
	String aOldNP,
	String aOldLRN,
	String aORP,
	String aONRP)
	{

		TelephoneNumberOrder aTNOrd = prepareTelephoneNumberOrder(aTN,aAI,aOP,aNP,aLRN,aRP,aNRP);		
		TelephoneNumberOrder aOldTNOrd = prepareTelephoneNumberOrder(aOldTN,aOldAI,aOldOP,aOldNP,aOldLRN,aORP,aONRP);	
		TelephoneNumberOrderOpt aOldTNOrdOpt = new TelephoneNumberOrderOpt();	
		aOldTNOrdOpt.theValue(aOldTNOrd);

		TelephoneNumberOrderPair aTNNumOrdpair = new TelephoneNumberOrderPair(aOldTNOrdOpt,aTNOrd);

		return aTNNumOrdpair;	
	}
	

	public static TelephoneNumberOrderOpt prepareTelephoneNumberOrderOpt(String TnNum)
	{
		StringOpt aStOpt = new StringOpt();
		String aSt 		 = new String();
		StringOpt aTnOpt = new StringOpt();
		aTnOpt.theValue(TnNum);
		TelephoneNumberOrder aTN = 
							new TelephoneNumberOrder(null,aTnOpt,null,null,null,null,null,null,null);
		
		TelephoneNumberOrderOpt aTnNumOrdOpt  = new TelephoneNumberOrderOpt();
		aTnNumOrdOpt.theValue(aTN);
		
		return aTnNumOrdOpt;
		
	}
	public static TelephoneNumberOrder prepareTelephoneNumberOrder(
	String aTN,
	String aAI,
	String aOP,
	String aNP,
	String aLRN,
	String aRP,
	String aNRP	
	)
	{
		StringOpt aStOpt = new StringOpt();
		String aSt 		 = new String();
		StringOpt aTnOpt = new StringOpt();
		aTnOpt.theValue(aTN);
		
		TelephoneNumberPortingStatusOpt aTelephoneNumberPortingStatusOpt = 
														new TelephoneNumberPortingStatusOpt();

		TelephoneNumberPortingStatus aTelephoneNumberPortingStatus = 
														new TelephoneNumberPortingStatus();
		
		aTelephoneNumberPortingStatus.aOldProvider = IDLUtil.toOpt(aOP);
		aTelephoneNumberPortingStatus.aNewProvider = IDLUtil.toOpt(aNP);
		aTelephoneNumberPortingStatus.aLocalRoutingNumber = IDLUtil.toOpt(aLRN);
		aTelephoneNumberPortingStatus.aRetainedPortingIndicator = IDLUtil.toOpt(aRP);
		aTelephoneNumberPortingStatus.aNonRetainedPortingIndicator = IDLUtil.toOpt(aNRP);		

		aTelephoneNumberPortingStatusOpt.theValue(aTelephoneNumberPortingStatus);
		
		TelephoneNumberOrder aTNO = new TelephoneNumberOrder(
								null,aTnOpt,null,null,aAI,null,null,null,aTelephoneNumberPortingStatusOpt);
				
		return aTNO;
		
	}
	
}
