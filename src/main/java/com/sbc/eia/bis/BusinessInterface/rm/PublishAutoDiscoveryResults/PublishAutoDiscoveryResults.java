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
//# 04/12/2005  Manjula Goniguntla    Creation.
//# 06/06/2005  Manjula Goniguntla    Added businesslogic for XNG and NetP.
//# 08/12/2005  Manjula Goniguntla    DR 142178: Exception Handling.
//# 09/06/2005  Manjula Goniguntla    DR 143125: Illegal Argument Exception.
//# 09/22/2005  Manjula Goniguntla    Changes made as a fix for the discrepancies between OMS and XNG.
//# 10/06/2005  Manjula Goniguntla    Fixed code for the DR 142810.
//# 11/08/2005  jp2854                Changes for IDL bundle 33 and removal of reference CustomerPremisEquipmentOpt. 
//# 01/16/2006  Michael Khalili       Changes for LS2 Support. 

package com.sbc.eia.bis.BusinessInterface.rm.PublishAutoDiscoveryResults;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.eia.bis.BusinessInterface.rm.NetProvision.Utilities.RmbisProductSubscriptionToNetPComponentList;
//import com.sbc.eia.bis.BusinessInterface.rm.XNGRC.Utilities.RmbisProductSubscriptionToXngRcComponentList;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.netprovision.NetProvisionService;
import com.sbc.eia.bis.embus.service.netprovision.helpers.ItemName;
import com.sbc.eia.bis.embus.service.netprovision.helpers.NullResourcesForServiceException;
import com.sbc.eia.bis.embus.service.netprovision.helpers.PublishAutoDiscoveryResultsDoHelper;
import com.sbc.eia.bis.embus.service.netprovision.helpers.PublishAutoDiscoveryResultsDoItem;
import com.sbc.eia.bis.embus.service.netprovision.helpers.RequestHelper;
import com.sbc.eia.bis.embus.service.netprovision.helpers.RequestItem;
import com.sbc.eia.bis.embus.service.netprovision.helpers.ResponseHelper;
import com.sbc.eia.bis.embus.service.netprovision.helpers.SendRequestToNetProvision;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestImpl;
import com.sbc.eia.bis.embus.service.xng.XNGService;
//import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.CPEType;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.CTMODELType;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.CTPATHIDType;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.CTTELEPHONENUMBERType;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.LSCircuitIDType;
//import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.RGType;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.TelephoneNumberType;
//import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.CPEImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.CTMODELImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.CTPATHIDImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.CTSITECITYImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.CTSITEPOSTCODE1Impl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.CTSITEPOSTCODE2Impl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.CTSITESTATEPROVImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.CTTELEPHONENUMBERImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.FacilityAddressImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.FacilityAddressTypeImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.LSCircuitIDImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.PublishAutoDiscoveryRequestImpl;
//import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.RGImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.TelephoneNumberImpl;
import com.sbc.eia.bis.embus.service.xng.access.XNGHelper;
import com.sbc.eia.bis.embus.service.xng.helpers.SendRequestToXNG;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.components.ProductSubscriptionManager;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerGranite;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.rm.PublishAutoDiscoveryResultsReturn;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.ResidentialGatewayOpt;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.sm_ls_types.ProductSubscriptionProperty;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
// for the new ProductSubcription build request method.
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.ComponentInfoImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.ComponentTagValueInfoImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.SeqDefaultComponentTagValueListImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.SeqDefaultComponentListImpl;

/**
 * @author Owner
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PublishAutoDiscoveryResults {

	private Hashtable properties = null;
	private Utility utility = null;
	private String aXngRuleFile = null;
	private String aNetPRuleFile = null;
	private XNGService aService = null;
	private NetProvisionService service = null;
//	private RmbisProductSubscriptionToXngRcComponentList pSubUtilityForXng = null;
	private RmbisProductSubscriptionToNetPComponentList productSubUtilityForNetP = null;
	private ProductSubscriptionManager productSubscriptionMgr = null;

	private PublishAutoDiscoveryResults() {

	}

	public PublishAutoDiscoveryResults(
		Utility aUtility,
		Hashtable aProperties) {

		properties = aProperties;
		utility = aUtility;
		aXngRuleFile =
			(String) properties.get(
				SendRequestToXNG.XNG_EXCEPTION_RULE_FILE_TAG);
		aNetPRuleFile =
			(String) properties.get(
				SendRequestToNetProvision
					.NET_PROVISION_EXCEPTION_RULE_FILE_TAG);
							
	}

	public PublishAutoDiscoveryResults(
		Utility aUtility,
		Hashtable aProperties,
	    ProductSubscription[] aProductSubscriptions) {

		properties = aProperties;
		utility = aUtility;
		aXngRuleFile =
			(String) properties.get(
				SendRequestToXNG.XNG_EXCEPTION_RULE_FILE_TAG);
		aNetPRuleFile =
			(String) properties.get(
				SendRequestToNetProvision
					.NET_PROVISION_EXCEPTION_RULE_FILE_TAG);
							
//			setPSubUtilityForXng(new RmbisProductSubscriptionToXngRcComponentList(aProperties));	
			productSubUtilityForNetP = new RmbisProductSubscriptionToNetPComponentList(aProperties);		
			setProductSubscriptionManager(new ProductSubscriptionManager(utility,aProductSubscriptions));
	}

	public PublishAutoDiscoveryResultsReturn publishAutoDiscoveryResults(
		BisContext aContext,
		String aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		AddressOpt aServiceAddress,
		ProductSubscription[] aProductSubscriptions,
		StringOpt aTelephoneNumber,
		String aAssignedProductId,
		OrderAction aOrderAction,
		ObjectPropertySeqOpt aProperties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
			
			ArrayList aList = null;


		utility.log(
			LogEventId.DEBUG_LEVEL_1,
			">PublishAutoDiscoveryResults.publishAutoDiscoveryResults()");

			aList = productSubscriptionMgr.getComponents(false);

		// Only Build Request and send it to XNG and NetP RC if we have atlease one Component to send.		
		if (0 < aList.size())
		{
			   // build request item for XNG
			   PublishAutoDiscoveryRequestImpl request =
					buildRequest(
						aContext,
						aCustomerTransportId,
						aBillingAccountNumber,
						aServiceAddress,
						aProductSubscriptions,
						aTelephoneNumber,
						aAssignedProductId,
						aOrderAction,
						aProperties);

			   // process request

			   if (aService == null) 
			   {
				  try 
				  {
					 aService = new XNGService(properties, utility);
				  } 
				  catch (ServiceException e) 
				  {
					 ExceptionBuilder.parseException(
							aContext,
							aXngRuleFile,
							null,
							null,
							e.getMessage(),
							true,
							1,
							null,
							e,
							utility,
							// utility
							null, // origin use file
							null, // severity use file
							null);
				  }
			   } // if (aService == null) 
			   
			   	// send request to Xng
				new SendRequestToXNG(
					  utility,
					  aContext,
					  properties,
					  aService).sendRequest(request,aOrderAction);

			   // For NetP  
			   // construct request
               // Only send it to Netp if it matches with Product IDs list, which at this moment of time is
               // Only NAD.
               ArrayList netpList = productSubUtilityForNetP.ProductSubscription2ComponentListMap(productSubscriptionMgr.getComponents(false));			   
               if ( netpList.size() > 0)
               {			   
      
			       RequestHelper aRequestHelper =
					   new RequestHelper(null, null, aOrderAction.aOrder.theValue());

			       // build request item
			       PublishAutoDiscoveryResultsDoItem aRequestItem =
				      buildRequest(aContext, aCustomerTransportId, aProductSubscriptions);

			       // construct request helper
			       PublishAutoDiscoveryResultsDoHelper aPublishAutoDiscoveryResultsDoHelper =
				      new PublishAutoDiscoveryResultsDoHelper(RequestItem.NetP_WHndNode,aRequestItem);

			       // add request item to request
			       aRequestHelper.addRequestItem(aPublishAutoDiscoveryResultsDoHelper);

			       try 
			       {
				       // retrieve request
				       RequestImpl aRequest = aRequestHelper.getRequest();
	
				       // process request
				       if (service == null) 
				       {
						   try 
						   {
							   service = new NetProvisionService(properties, utility);
						   } 
						   catch (ServiceException e) 
						   {
							    ExceptionBuilder.parseException(
									 aContext,
									 aNetPRuleFile,
									 null,
									 null,
									 e.getMessage(),
									 true,
									 1,
									 null,
									 e,
									 utility,
									 // utility
									 null, // origin use file
									 null, // severity use file
									 null);
						   }
				        } // if (service == null) - NetP
	
				
				       // send request to NetP
				       Object aResponse = null;
	
				       aResponse =
					       SendRequestToNetProvision.sendRequest(
							  utility,
							  aContext,
							  aNetPRuleFile,
							  aRequest,
							  service);
	
					   // process response
					   ResponseHelper aResponseHelper =
						   new ResponseHelper(
							 aNetPRuleFile,
							 aResponse,
							 properties.get("BIS_NAME").toString());
	
					   try 
					   {
						   aResponseHelper.processResponse(aContext, utility);
					   } 
					   catch (NullResourcesForServiceException e) 
					   {
						   utility.log(LogEventId.DEBUG_LEVEL_2, e.getMessage());
						   utility.throwException(
							 ExceptionCode.ERR_RM_SERVICE_ITEM_NOT_FOUND,
							 "Null resource found",
							 properties.get("BIS_NAME").toString(),
							 Severity.Recoverable);
					   }
			       }
				   // ignore any NetP exception
				   catch (Exception e)
				   {
				   	utility.log(LogEventId.DEBUG_LEVEL_2, "NetP Exception reached: " + e.toString());
				   }

               } // if ( nepList.size() > 0)
		 } //if (0 < aList.size()) 


		utility.log(
				LogEventId.DEBUG_LEVEL_1,
				">PublishAutoDiscoveryResults.publishAutoDiscoveryResults()");

		return new PublishAutoDiscoveryResultsReturn(
				aContext,
				aCustomerTransportId,
				aBillingAccountNumber,
				aServiceAddress,
				aProductSubscriptions,
				aTelephoneNumber,
				aAssignedProductId,
				aOrderAction,
				aProperties);
	  
	}

	/**
	 * @param aContext
	 * @param aCustomerTransportId
	 * @param aProductSubscriptions
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	/******/ //buildRequest for NetP
	public PublishAutoDiscoveryResultsDoItem buildRequest(
		BisContext aContext,
		String aCustomerTransportId,
		ProductSubscription[] aProductSubscriptions)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		utility.log(
			LogEventId.DEBUG_LEVEL_1,
			">" + "PublishAutoDiscoveryResults::buildRequest()");

		// construct the request
		PublishAutoDiscoveryResultsDoItem req =
			new PublishAutoDiscoveryResultsDoItem(
				ItemName.PUBLISH_AUTO_DISCOVERY_RESULTS);

//		Properties tagValues = new Properties();
//		ObjectPropertyManager opmContext =
//			new ObjectPropertyManager(aContext.aProperties);

		// LS Circuit ID - aCustomerTransportId
		try {
			req.setCustomerTransportId(aCustomerTransportId);
		} catch (org.omg.CORBA.BAD_OPERATION e) {
		} catch (NullPointerException e) {
		}

		// Require ProductSubscription / ComponentList
		// Using the new utility class: RmbisProductSubscriptionToNetPComponentList
		//RmbisProductSubscriptionToNetPComponentList netPComponentListUtility = null;
		ArrayList aList = null;
		ProductSubscription aPSub = null;		
		com.sbc.eia.idl.types.ObjectProperty[] properties = null;
		com.sbc.eia.idl.types.ObjectProperty aProperty = null;
		int index=0,
			propertiesIndex=0;		
		
		aList = productSubscriptionMgr.getComponents(false);
		//netPComponentListUtility = new RmbisProductSubscriptionToNetPComponentList(this.properties);
		index = 0;

		while (index < aList.size() )
		{
			aPSub = (ProductSubscription)aList.get(index); 
			// NetP only cares about RG Component Type
			if ( productSubUtilityForNetP.isComponentValid(aPSub.aProductID))
			{
				properties = aPSub.aProperties.theValue();
				propertiesIndex = 0;
				while (propertiesIndex < properties.length)
				{
					aProperty = properties[propertiesIndex];
					if ( aProperty.aTag.equalsIgnoreCase(ProductSubscriptionProperty.SERIALNUMBER) )  
					{
						 if ( (aProperty.aValue != null) && (aProperty.aValue.trim().length() > 0) ) 
						 {                            							
							req.setSerialNumber(aProperty.aValue);
						 }
					}
					else if ( aProperty.aTag.equalsIgnoreCase(ProductSubscriptionProperty.MODELNUMBER) )  
					{
						if ( (aProperty.aValue != null) && (aProperty.aValue.trim().length() > 0) ) 
						{                            							
							req.setModelNumber(aProperty.aValue);
						}
					}
					propertiesIndex++;
				} // while (propertiesIndex < properties.length)
			
			} // if ( netPComponentListUtility.IsComponentValid(aPSub.aProductID))
			
			
			index++;
        
		} // while (index < aList.size() )
		utility.log(
			LogEventId.DEBUG_LEVEL_1,
			"<" + "PublishAutoDiscoveryResults::buildRequest()");

		return req;

	}

	// buildRequest for XNG 
	public PublishAutoDiscoveryRequestImpl buildRequest(
		BisContext aContext,
		String aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		AddressOpt aServiceAddress,
		ProductSubscription[] aProductSubscriptions,
		StringOpt aTelephoneNumber,
		String aAssignedProductId,
		OrderAction aOrderAction,
		ObjectPropertySeqOpt aProperties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		com
			.sbc
			.eia
			.bis
			.embus
			.service
			.xng
			.PublishAutoDiscoveryRequest
			.impl
			.PublishAutoDiscoveryRequestImpl request =
			new com
				.sbc
				.eia
				.bis
				.embus
				.service
				.xng
				.PublishAutoDiscoveryRequest
				.impl
				.PublishAutoDiscoveryRequestImpl();


		request.setSchemaVersion(XNGHelper.XNG_RC_VERSION);

		// Required : LS Circuit id 
		CTPATHIDType ctPATHIDType = new CTPATHIDImpl();
		ctPATHIDType.setPathID(aCustomerTransportId);
		LSCircuitIDType type = new LSCircuitIDImpl();
		type.setCTPATHID(ctPATHIDType);
		request.setLSCircuitID(type);

		//Required : BAN
		String ban = null;
		try {
			ObjectKey[] keys = aBillingAccountNumber.aKeys;
			for (int i = 0; i < keys.length; i++) {
				if (keys[i]
					.aKind
					.equals("com.sbc.eia.bis.BillingAccountNumber")) {
					ban = keys[i].aValue;
					break;
				}

			}

		} catch (org.omg.CORBA.BAD_OPERATION e) {
		} catch (NullPointerException e) {
		}

		request.setBAN(ban);

		//Optional : ServiceAddress
		if (aServiceAddress != null
			&& !OptHelper.isAddressOptNull(aServiceAddress)) {

			FacilityAddressTypeImpl aFacilityAddressImpl =
				new FacilityAddressImpl();
			Address aFacilityAddress = aServiceAddress.theValue();
			AddressHandlerGranite aGraniteAddress = null;

			try {
				aGraniteAddress = new AddressHandlerGranite(aFacilityAddress);
			} catch (AddressHandlerException e) {
			}

			// StreetAddress
			String aStreetAddress = aGraniteAddress.getStreetAddress();
			aFacilityAddressImpl.setStreetAddress(aStreetAddress);

			// City
			CTSITECITYImpl aCTSITECITYImpl = new CTSITECITYImpl();
			aCTSITECITYImpl.setSiteCity(aGraniteAddress.getCity());
			aFacilityAddressImpl.setCTSITECITY(aCTSITECITYImpl);

			// State 
			CTSITESTATEPROVImpl aCTSITESTATEPROVImpl =
				new CTSITESTATEPROVImpl();
			aCTSITESTATEPROVImpl.setSiteStateProv(aGraniteAddress.getState());
			aFacilityAddressImpl.setCTSITESTATEPROV(aCTSITESTATEPROVImpl);

			// PostalCode    
			CTSITEPOSTCODE1Impl aCTSITEPOSTCODE1Impl =
				new CTSITEPOSTCODE1Impl();
			aCTSITEPOSTCODE1Impl.setSitePostCode1(
				aGraniteAddress.getPostalCode());
			aFacilityAddressImpl.setCTSITEPOSTCODE1(aCTSITEPOSTCODE1Impl);

			// PostalCodePlus4   
			CTSITEPOSTCODE2Impl aCTSITEPOSTCODE2Impl =
				new CTSITEPOSTCODE2Impl();
			aCTSITEPOSTCODE2Impl.setSitePostCode2(
				aGraniteAddress.getPostalCodePlus4());
			aFacilityAddressImpl.setCTSITEPOSTCODE2(aCTSITEPOSTCODE2Impl);

			//StructureType
			aFacilityAddressImpl.setStructureType(
				aGraniteAddress.getStructType());

			//StructureValue
			aFacilityAddressImpl.setStructureValue(
				aGraniteAddress.getStructValue());

			//ElevationType  
			aFacilityAddressImpl.setElevationType(
				aGraniteAddress.getLevelType());

			//ElevationValue 
			aFacilityAddressImpl.setElevationValue(
				aGraniteAddress.getLevelValue());

			//UnitType   
			aFacilityAddressImpl.setUnitType(aGraniteAddress.getUnitType());

			//UnitValue  
			aFacilityAddressImpl.setUnitValue(aGraniteAddress.getUnitValue());

			request.setFacilityAddress(aFacilityAddressImpl);

		}

		// Require ProductSubscriptions / ComponentList
		ArrayList aList = null;
		ComponentInfoImpl componentInfo = null;
		ProductSubscription aPSub = null;		
		ObjectProperty[] properties = null;
		ObjectProperty aProperty = null;
		SeqDefaultComponentTagValueListImpl seqCTVInfo = null;
		ComponentTagValueInfoImpl CTVInfo = null;				
		SeqDefaultComponentListImpl seqComponentList = null;

		int index=0,
			propertiesIndex=0;		
		
		
		aList = productSubscriptionMgr.getComponents(false);

		// make sure that request's SeqDefaultComponentList is NOT NULL
		if ( null == request.getComponentListGRP())
		{  
			seqComponentList = new SeqDefaultComponentListImpl();
			request.setComponentListGRP(seqComponentList);			
		}

		index = 0;
		while (index < aList.size() )
		{
			componentInfo = new ComponentInfoImpl();
			seqCTVInfo = new SeqDefaultComponentTagValueListImpl();
			
			aPSub = (ProductSubscription)aList.get(index); 
			// setting the ComponentId
			componentInfo.setComponentCode(aPSub.aProductID);
			// setting the ComponentInstanceId
			componentInfo.setComponentInstanceId(aPSub.aProductSubscriptionID.aValue);
			try { 

				// setting the ComponentStatus
				if ((null != aPSub.aProductSubscriptionStatus) && 
				(!OptHelper.isStringOptEmpty(aPSub.aProductSubscriptionStatus)))	{  
					String productSubStatus = aPSub.aProductSubscriptionStatus.theValue();
					   componentInfo.setComponentStatus(productSubStatus);
				}
			} catch (NullPointerException np) {} 
            catch (org.omg.CORBA.BAD_OPERATION e) {} 

			properties = aPSub.aProperties.theValue();

			propertiesIndex = 0;
			//seqCTVInfo = null;
			while (propertiesIndex < properties.length)
			{
				aProperty = properties[propertiesIndex];
				if (aProperty.aTag.equalsIgnoreCase(ProductSubscriptionProperty.ACTIONID))
				{
				  componentInfo.setComponentActionCode(aProperty.aValue);
				}
				else if ((aProperty.aTag.equalsIgnoreCase(ProductSubscriptionProperty.QUALIFIER)) && 
					((ProductSubscriptionProperty.QUALIFIER != null) || 
						(ProductSubscriptionProperty.QUALIFIER.trim().length() > 0)))	{
					componentInfo.setComponentQualifier(aProperty.aValue);
				}
				else if (( aProperty.aTag.equalsIgnoreCase(ProductSubscriptionProperty.SERIALNUMBER))  && 
					((ProductSubscriptionProperty.SERIALNUMBER != null) || 
						(ProductSubscriptionProperty.SERIALNUMBER.trim().length() > 0)))	{
						componentInfo.setComponentSerialNo(aProperty.aValue);
				}
				else if ((aProperty.aTag.equalsIgnoreCase(ProductSubscriptionProperty.MODELNUMBER))  && 
					((ProductSubscriptionProperty.MODELNUMBER != null) || 
						(ProductSubscriptionProperty.MODELNUMBER.trim().length() > 0)))	{
						componentInfo.setCPEModel(aProperty.aValue);
				}
				else if ((aProperty.aTag.equalsIgnoreCase(ProductSubscriptionProperty.VENDOR)) && 
					((ProductSubscriptionProperty.VENDOR != null) || 
						(ProductSubscriptionProperty.VENDOR.trim().length() > 0)))	{						
						componentInfo.setCPEVendor(aProperty.aValue);
				}
				else if ((aProperty.aTag.equalsIgnoreCase(ProductSubscriptionProperty.DEVICEID)) && 
					((ProductSubscriptionProperty.VENDOR != null) || 
						(ProductSubscriptionProperty.VENDOR.trim().length() > 0)))	{ 
						  componentInfo.setComponentDeviceId(aProperty.aValue);
				}
				else if ((aProperty.aTag.equalsIgnoreCase(ProductSubscriptionProperty.MATERIAL_NUMBER)) && 
					((ProductSubscriptionProperty.MATERIAL_NUMBER!= null) || 
						(ProductSubscriptionProperty.MATERIAL_NUMBER.trim().length() > 0)))	{ 
						  componentInfo.setMaterialNumber(aProperty.aValue);
				} // Begining of the CR-9482
                else if ( aProperty.aTag.equalsIgnoreCase("Speed") )  
                {
			              componentInfo.setSpeed(aProperty.aValue);
                }
				else if ( aProperty.aTag.equalsIgnoreCase("uverseTechnology") )  
				{
						  componentInfo.setUVerseTechnology(aProperty.aValue);
				}
				else if ( aProperty.aTag.equalsIgnoreCase("HsiaType") )  
				{
						  componentInfo.setHSIAType(aProperty.aValue);
				}
				else if ( aProperty.aTag.equalsIgnoreCase("stbType") )  
				{
						  componentInfo.setSTBType(aProperty.aValue);
				} 
				else if ( aProperty.aTag.equalsIgnoreCase("IPAddressType") )  
				{
						  componentInfo.setIPAddressType(aProperty.aValue);
				} // End of the CR-9482
				else if ( aProperty.aTag.equalsIgnoreCase("InstalledDate"))  {
						  componentInfo.setInstalledDate(aProperty.aValue);
				} 
				else // For anything else add it into ComponentTagValueList  
				{
					if ( (null != aProperty.aTag) && (null != aProperty.aValue) )
					{
						CTVInfo = new ComponentTagValueInfoImpl();
						CTVInfo.setComponentTag(aProperty.aTag);
						CTVInfo.setComponentValue(aProperty.aValue);
						seqCTVInfo.getItem().add(CTVInfo);
					}
				
				}				
				propertiesIndex++;
			} // while (propertiesIndex < properties.length)

			componentInfo.setComponentTagValueListGRP(seqCTVInfo);
			  
			request.getComponentListGRP().getItem().add(componentInfo);

			index++;
        
		} // while (index < aList.size() )

		// Conditional : Telephone Number
		try {
			CTTELEPHONENUMBERType ctTelNumType = new CTTELEPHONENUMBERImpl();
			ctTelNumType.setTelephoneNumber(aTelephoneNumber.theValue());
			TelephoneNumberType telNumType = new TelephoneNumberImpl();
			telNumType.setCTTELEPHONENUMBER(ctTelNumType);
			request.setTelephoneNumber(telNumType);
		} catch (org.omg.CORBA.BAD_OPERATION e) {
		} catch (NullPointerException e) {
		}

		// Required : ProductCode
		 request.setProductCode(aAssignedProductId);

		// Required : OrderAction
		try {
			if ((null != aOrderAction.aOrder) && 
			(!OptHelper.isStringOptEmpty(aOrderAction.aOrder))) {
				request.setOrderNumber(aOrderAction.aOrder.theValue());
			}
		} catch (org.omg.CORBA.BAD_OPERATION e) {
		} catch (NullPointerException e) {
		}

		return request;

	}

//Temporary Code - Begin

/****
*  The following two methods:  handleTheSpecialCaseForRG(...) and handleTheSpecialCaseForCPE(...) got
*  added as part of the Temporary solution for LS2 release. They got added since XNG RC does not support
*  the new R2 Schema changes based on the new ComponentList_GRP, therefore RM BIS needs to map its
*  ProductSubscription RG and CPE values to the RG and CPE based on the previous (R1) Schema.
*****/
/* 
 * @param aPSub
 * @param request
 * @throws InvalidData
 * @throws AccessDenied
 * @throws BusinessViolation
 * @throws SystemFailure
 * @throws NotImplemented
 * @throws ObjectNotFound
 * @throws DataNotFound
 */
//	public void handleTheSpecialCaseForRG(ProductSubscription aPSub,
//	    com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.PublishAutoDiscoveryRequestImpl request)
//		throws
//			InvalidData,
//			AccessDenied,
//			BusinessViolation,
//			SystemFailure,
//			NotImplemented,
//			ObjectNotFound,
//			DataNotFound {
//			
//			com.sbc.eia.idl.types.ObjectProperty[] properties = null;
//			com.sbc.eia.idl.types.ObjectProperty aProperty = null;
//			boolean bRGDeviceIdIsMissing,
//			        bRGModelNumberIsMissing; 
//			String  sDeviceIDValue = null,
//			        sModelNumberValue = null;
//			int propertiesIndex = 0;
//			
//			
//
//			bRGDeviceIdIsMissing = true;
//			bRGModelNumberIsMissing = true;
//			propertiesIndex = 0;
//			properties = aPSub.aProperties.theValue();
//			
//			while (propertiesIndex < properties.length)
//			{
//				aProperty = properties[propertiesIndex];
//				if ( aProperty.aTag.equalsIgnoreCase("ModelNumber") )  
//				{
//					if ((null != aProperty.aValue) && (aProperty.aValue.length() > 0) )
//					{   
//						// Set the bRGModelNumberIsMissing flag to false to prevent from throwing exception.
//						bRGModelNumberIsMissing = false;
//						// Save the ModelNumber value
//						sModelNumberValue = aProperty.aValue;
//					}
//					else // If there is no value for ModelNumber then exit from the loop and throw an exception. 
//					   break;					 
//				}
//				else if ( aProperty.aTag.equalsIgnoreCase("DeviceID") )  
//				{
//					// mapping the ProductSubscription's device ID to RGDeviceId.
//					if ((null != aProperty.aValue) && (aProperty.aValue.length() > 0) )
//	                {   
//	                   // Set the bRGDeviceIdIsMissing flag to false to prevent from throwing exception.
//	                   bRGDeviceIdIsMissing = false;
//					   // Save the DeviceId value
//					   sDeviceIDValue = aProperty.aValue;
//	                }
//                    else // If there is no value for DeviceID then exit from the loop and throw an exception. 
//                       break;					 
//				}
//				propertiesIndex++;
//			}//while (propertiesIndex < properties.length)
//			
//			if (bRGDeviceIdIsMissing )
//			{
//				utility.throwException(
//					ExceptionCode.ERR_RM_MISSING_NETWORK_COMPONENT,
//					"Missing required data: RG DeviceId",
//					(String)this.properties.get("BIS_NAME"),
//					Severity.UnRecoverable);
//			}
//			else if (bRGModelNumberIsMissing )
//			{
//				utility.throwException(
//					ExceptionCode.ERR_RM_MISSING_NETWORK_COMPONENT,
//					"Missing required data: RG ModelNumber",
//					(String)this.properties.get("BIS_NAME"),
//					Severity.UnRecoverable);
//			}
//            else 
//            {
//				// mapping the ProductSubscription's Model Number to RG.
//				RGType rgType = new RGImpl();
//				CTMODELType ctModelType = new CTMODELImpl();
//				ctModelType.setModel(sModelNumberValue);
//				rgType.setCTMODEL(ctModelType);
//				request.setRG(rgType);
//				// mapping the ProductSubscription's DeviceId to RG.
//				request.setRGDeviceID(sDeviceIDValue);
//				// As part of the temporary fix for LS2, I am giving it a dummy string to prevent from getting the
//				// following exception: java.lang.IllegalArgumentException: _object parameter must not be null
//				CPEType cpeType = new CPEImpl();
//				CTMODELType mp = new CTMODELImpl();
//				mp.setModel("dummy");
//				cpeType.setCTMODEL(mp);
//				request.setCPE(cpeType);
//            }
//
//	}//public void handleTheSpecialCaseForRG(...)
//
//	Temporary Code - End


	/**
	 * @return
	 */
//	public RmbisProductSubscriptionToNetPComponentList getPSubUtilityForNetP() {
//		return pSubUtilityForNetP;
//	}
//
//	/**
//	 * @return
//	 */
//	public RmbisProductSubscriptionToXngRcComponentList getPSubUtilityForXng() {
//		return pSubUtilityForXng;
//	}
//
//	/**
//	 * @param list
//	 */
//	public void setPSubUtilityForNetP(RmbisProductSubscriptionToNetPComponentList list) {
//		pSubUtilityForNetP = list;
//	}
//
//	/**
//	 * @param list
//	 */
//	public void setPSubUtilityForXng(RmbisProductSubscriptionToXngRcComponentList list) {
//		pSubUtilityForXng = list;
//	}
//
  /**
   * @param list
   */
  public void setProductSubscriptionManager(ProductSubscriptionManager mgr) {
	  productSubscriptionMgr = mgr;
  }


}
