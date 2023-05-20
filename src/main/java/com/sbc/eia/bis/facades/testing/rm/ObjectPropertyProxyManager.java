package com.sbc.eia.bis.facades.testing.rm;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.eia.bis.facades.testing.TestClient;
import com.sbc.eia.bis.facades.testing.objHelpers;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_types.LoopInfoType;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt;
import com.sbc.logging.LogAssistant;
import com.sbc.logging.LogAssistantFactory;
import com.sbc.logging.message.MessageFactory;

/**
 * @author Sumana Roy
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 * 
#   History :
#   Date		| Author			| Version	|Notes
#   ----------------------------------------------------------------------------
#	6/23/2004	  Stevan Dunkin		  RM 9		 Added methods:
												 	getProxyPortConstraints
												 	getProxyMeasurementOpt
												 	getProxyMeasurement
												 	getProxyLocationOpt
												 	getProxyTrunkOpt
												 	getProxyTrunk
												 	getProxyLongOpt
												 	
	7/20/2004	Mark Liljequist		  RM 9			Added getBoolean.
	
	8/30/2004 	Stevan Dunkin		  RM 9		 Modified:
													getProxyLongOpt
													getProxyTrunkOpt
													getProxyTrunk
													getProxyLocationOpt
													getProxyMeasurementOpt
	04/13/2005  Jyothi Pentyala      RM 11      Modified createLocationProperty method 
    02/03/06    Changchuan Yin       RM 15.0    Commented the location constructor
 **/
public class ObjectPropertyProxyManager extends ObjectPropertyManager
{
	public final static String providerName = "SBC";
	/**
	 * Constructor for ObjectPropertyProxyManager.
	 */
	public ObjectPropertyProxyManager()
	{
		super();
	}

	/**
	 * Constructor for ObjectPropertyProxyManager.
	 * @param properties
	 */
	public ObjectPropertyProxyManager(ObjectProperty[] properties)
	{
		super(properties);
	}

	/**
	 * Constructor for ObjectPropertyProxyManager.
	 * @param properties
	 */
	public ObjectPropertyProxyManager(ObjectPropertySeqOpt properties)
	{
		super(properties);
	}

	/**
	 * Return the contained ObjectProperty objects as an array.
	 * Creation date: (3/8/01 4:41:24 PM)
	 * @return com.sbc.eia.idl.types.ObjectProperty[]
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty[] toArrayTypesObj()
	{
		return (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty[]) sequential.toArray(new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty[sequential.size()]);

	}

	/**
	 * Parse from a StringTokenizer and create a corresponding com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.BisContext object.
	 * Creation date: (5/14/01 10:08:26 AM)
	 * @return com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.BisContext
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.BisContext getProxyBisContext(StringTokenizer param)
	{
		String tag = null;
		ObjectPropertyManager myObjProp = new ObjectPropertyManager();

		tag = TestClient.nextToken(param);

		while (tag != null && !tag.equalsIgnoreCase("end"))
		{
			if (tag.equalsIgnoreCase(BisContextProperty.LOGGINGINFORMATION))
			{

				LogAssistant theLogAssistant = LogAssistantFactory.create(TestClient.nextToken(param), "--");
				theLogAssistant.log(MessageFactory.create(""));
				addTypesObjProperty(tag, theLogAssistant.getCorrID());
			}
			else
				addTypesObjProperty(tag, TestClient.nextToken(param));

			tag = TestClient.nextToken(param);
		}
		//System.out.println("Return bisContext::");
		return new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.BisContext(toArrayTypesObj());

	}

	/**
	 * Parse from a StringTokenizer and create a corresponding com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.BisContext object.
	 * Creation date: (5/14/01 10:08:26 AM)
	 * @return com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.BisContext
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty[] getTypesObjectProperty(ObjectPropertyManager aOjectPM)
	{
		String tmpTag = null;
		String tmpValue = null;
		ObjectProperty opArr[] = null;
		opArr = aOjectPM.toArray();

		List typesObjArr = new ArrayList();

		for (int i = 0; i < opArr.length; i++)
		{
			tmpTag = opArr[i].aTag;
			tmpValue = opArr[i].aValue;

			typesObjArr.add(new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty(tmpTag, tmpValue));
		}
		return (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty[]) typesObjArr.toArray(new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty[typesObjArr.size()]);

	}
	/**
	 * Parse the StringTokenizer to extract and return an Location object.
	 * Creation date: (3/23/01 3:08:33 PM)
	 * @return Location
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.Location getProxyLocation(java.util.StringTokenizer param)
	{
		objHelpers objHelper = new objHelpers();
		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.Location loProxy = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.Location();

		loProxy.aLocationId = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, null);

		// null Location?
		String tn = null;
		String stProxy = TestClient.nextToken(param);
		if (stProxy != null)
		{
			if (stProxy.equals("FieldedAddress"))
			{
				com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.FieldedAddress aFieldedAddress = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.FieldedAddress();

				aFieldedAddress.aRoute = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aBox = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aHouseNumberPrefix =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aHouseNumber = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aAssignedHouseNumber =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aHouseNumberSuffix =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aStreetDirection =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aStreetName = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aStreetThoroughfare =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aStreetNameSuffix =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aCity = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aState = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aPostalCode = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aPostalCodePlus4 =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aCounty = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aCountry = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aStructureType = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aStructureValue = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aLevelType = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aLevelValue = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aUnitType = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aUnitValue = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aOriginalStreetDirection =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aOriginalStreetNameSuffix =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aCassAddressLines =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringSeqOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringSeqOpt.class, objHelper.getStrings(param));
				aFieldedAddress.aAuxiliaryAddressLines =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringSeqOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringSeqOpt.class, objHelper.getStrings(param));
				aFieldedAddress.aCassAdditionalInfo =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aAdditionalInfo = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				aFieldedAddress.aBusinessName = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));

				//New fields added to fielded address IDL bundle 23 - not currently used.
                //Just setting default values as place holders
                StringOpt defaultStringOpt = new StringOpt();
                defaultStringOpt.__default();
                aFieldedAddress.aCountryCode = defaultStringOpt;
                aFieldedAddress.aCityCode = defaultStringOpt;
                aFieldedAddress.aServiceLocationName = defaultStringOpt;
                aFieldedAddress.aAddressId = defaultStringOpt;
                aFieldedAddress.aAliasName = defaultStringOpt;
                aFieldedAddress.aAttention = defaultStringOpt;
				
				//Address addr = new Address() ;
				com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.Address theAddress = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.Address();
				theAddress.aFieldedAddress(aFieldedAddress);

				stProxy = TestClient.nextToken(param);
				if (stProxy.equalsIgnoreCase("PROP"));
				if (TestClient.nextToken(param).equalsIgnoreCase("TelephoneNumber"))
				{
					tn = TestClient.nextToken(param);
				}
				loProxy.aProviderLocationProperties = createLocationProperty(theAddress, tn);
			}
			else if (stProxy.equals("UnfieldedAddress"))
			{
				com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.UnfieldedAddress unfieldedaddress = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.UnfieldedAddress();
				unfieldedaddress.aAddressLines =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringSeqOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringSeqOpt.class, objHelper.getStrings(param));
				unfieldedaddress.aCity = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aState = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aPostalCode = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aPostalCodePlus4 =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aCounty = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aCountry = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aStructureType = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aStructureValue =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aLevelType = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aLevelValue = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aUnitType = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aUnitValue = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
				unfieldedaddress.aAdditionalInfo =
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));

				com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.Address theAddress = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.Address();
				theAddress.aUnfieldedAddress(unfieldedaddress);

				loProxy.aProviderLocationProperties = createLocationProperty(theAddress, tn);
			}

			return loProxy;

		}
		else
		{
			return null;
		}
	}
	//
	//Create ProviderLocationProperty[]
	//
	private static com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.ProviderLocationProperty[] createLocationProperty(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.Address addr, String tn)
	{
		StringOpt defaultStringOpt = new StringOpt();
		defaultStringOpt.__default();
		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.ExtensionProperty aExtensions[] =
			new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.ExtensionProperty[] { new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.ExtensionProperty(" ", " ")};
		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.AddressOpt aPostalOpt = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.AddressOpt();
		aPostalOpt.__default();

		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.AddressOpt aSwitchSuperPopAddressOpt = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.AddressOpt();
		aSwitchSuperPopAddressOpt.__default();

		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.ProviderLocationProperty aLocationProperty[] = null;
		/* Commented out since this is valid and not requred for testing for RM 15.0
		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.ProviderLocationProperty aLocationProperty[] = 
		new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.ProviderLocationProperty[] {
				 new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.ProviderLocationProperty(
					providerName,
					aPostalOpt,
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.AddressOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.AddressOpt.class, addr),
					(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.AddressOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.AddressOpt.class, null),
					aSwitchSuperPopAddressOpt,
					defaultStringOpt,                
    				defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, tn),               
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    defaultStringOpt,                
				    (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.AddressOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.AddressOpt.class, null),	             
				    defaultStringOpt,	             
				    defaultStringOpt,	               
				    defaultStringOpt,	             
				    defaultStringOpt,	              
				    defaultStringOpt,	             
				    defaultStringOpt,                
				    defaultStringOpt,                
				    aExtensions )   };
				    */
		return aLocationProperty;
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding list of types_ObjectProperty objects.
	 * Creation date: (3/27/01 11:22:47 AM)
	 * @return types_ObjectProperty[]
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty[] getTypesObjectProperties(StringTokenizer param)
	{
		String tag = TestClient.nextToken(param);
		while (!tag.equalsIgnoreCase("end"))
		{
			String value = TestClient.nextToken(param);
			addTypesObjProperty(tag, value);
			tag = TestClient.nextToken(param);
		}
		return toArrayTypesObj();
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType object.
	 * Creation date: (3/23/01 8:20:07 AM)
	 * @return LoopInfoType
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType getProxyLoopInfoType(java.util.StringTokenizer param)
	{
		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType result = null;

		Integer type = new Integer(TestClient.nextToken(param));
		switch (type.intValue())
		{
			case com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType._ACTUAL :
				result = com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType.ACTUAL;
				break;

			case com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType._DESIGN :
				result = com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType.DESIGN;
				break;

			case com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType._MANUAL :
				result = com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType.MANUAL;
				break;

			case com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType._TENLOOP :
				result = com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType.TENLOOP;
				break;

			case LoopInfoType._PRE_QUAL :
			default :
				result = com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.LoopInfoType.PRE_QUAL;
				break;
		}
		return result;
	}
	/**
	 * Parse a StringTokenizer to extract and return a ResourceSearchKey object.
	 * Creation date: (3/23/01 8:01:26 AM)
	 * @return ResourceSearchKey
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.ResourceSearchKey getProxyResourceSearchKey(java.util.StringTokenizer param)
	{
		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.ResourceSearchKey result = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.ResourceSearchKey();

		// get WTN
		String tmpStr = TestClient.nextToken(param);
		if (tmpStr != null && tmpStr.equalsIgnoreCase("null"))
			tmpStr = null;

		if (tmpStr.equals("WTN"))
		{
			com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKey a = getProxyObjectKey(param);
			result.aServiceHandle = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKeyOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKeyOpt.class, a);
		}

		// get LSO
		tmpStr = TestClient.nextToken(param);
		if (tmpStr != null && tmpStr.equalsIgnoreCase("null"))
			tmpStr = null;
		if (tmpStr.equals("LSO"))
		{
			//com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt a = getProxyStringOpt(param);
			//result.aLocalServingOfficeHandle = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, a);
			result.aLocalServingOfficeHandle = getProxyStringOpt(param);
		}

		// get Location
	//	tmpStr = TestClient.nextToken(param); // fix testclient and remove this line here and in ObjectHelper
		tmpStr = TestClient.nextToken(param);
		if (tmpStr != null && tmpStr.equalsIgnoreCase("null"))
			tmpStr = null;
		if (tmpStr.equals("LOCATION"))
		{
			com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.Location aLoc = getProxyLocation(param);
			result.aServiceLocation = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.LocationOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.LocationOpt.class, aLoc);
		}

		return result;
	}

	/**
	 * Prase a StringTokenizer to retrieve the String and returns a StringOpt object.
	 * Creation date: (3/23/01 8:00:12 AM)
	 * @return StringOpt
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt getProxyStringOpt(java.util.StringTokenizer param)
	{

		String st = TestClient.nextToken(param);
		if (st != null && st.equalsIgnoreCase("null"))
			st = null;
		return (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, st);

	}
	/**
	 * Prase a StringTokenizer to retrieve the types_ObjectKey and returns a types_ObjectHandle object.
	 * Creation date: (3/23/01 8:00:12 AM)
	 * @return types_ObjectHandle
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKey getProxyObjectKey(java.util.StringTokenizer param)
	{
		String aValue = TestClient.nextToken(param);
		String aKind = TestClient.nextToken(param);

		if (aValue != null)
			return new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKey(aValue, aKind);
		else
			return new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKey(new String(), new String());
	}
	/**
	 * Prase a StringTokenizer to retrieve the ObjectKeys and returns a list of ObjectHandle object.
	 * Creation date: (3/23/01 8:00:12 AM)
	 * @return ObjectHandle[]
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKey[] getObjectKeys(java.util.StringTokenizer param)
	{
		List aList = new ArrayList();

		String aValue = TestClient.nextToken(param);
		while (aValue != null && !aValue.equalsIgnoreCase("end"))
		{
			String aKind = TestClient.nextToken(param);
			aList.add( new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKey(aValue, aKind) );
			aValue = TestClient.nextToken(param);
		}
		return (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKey[]) aList.toArray(new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKey[aList.size()]);
	}
	/**
	 * Iterate through the list until match tag.  Remove it from the list if
	 * requested to do so.
	 * Creation date: (3/8/01 4:59:25 PM)
	 * @return com.sbc.eia.idl.types.ObjectProperty
	 * @param tag java.lang.String
	 * @param remove boolean
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty getTypesObjProperty(String tag, boolean remove)
	{
		ListIterator li = sequential.listIterator();
		while (li.hasNext())
		{
			com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty op = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty) li.next();
			if (op.aTag.equals(tag))
			{
				if (remove)
					li.remove();
				return op;
			}
		}
		return null;
	}
	/**
	 * Add all the types_ObjectProperty objects in properties to the list.
	 * Creation date: (3/9/01 9:02:53 AM)
	 * @param properties com.sbc.eia.idl.types.ObjectProperty[]
	 */
	public void addAllTypesObjProperty(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty[] properties)
	{
		for (int i = 0; i < properties.length; i++)
			addTypesObjProperty(properties[i]);
	}

	/**
	 * Create a new types_ObjectProperty from the tag and value, and add it to the list.
	 * Creation date: (3/8/01 4:29:00 PM)
	 * @param tag java.lang.String
	 * @param value java.lang.String
	 */
	public void addTypesObjProperty(String tag, String value)
	{
		addTypesObjProperty(new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty(tag, value));
	}

	/**
	 * Add the types_ObjectProperty to the list.
	 * Creation date: (3/8/01 4:25:16 PM)
	 * @param property com.sbc.gwsvcs.service.rmproxy.interfaces.types_ObjectProperty
	 */
	public void addTypesObjProperty(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectProperty property)
	{
		getTypesObjProperty(property.aTag, true);
		sequential.add(property);
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding Date object.
	 * Creation date: (3/29/01 4:10:57 PM)
	 * @return EiaDate
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.EiaDate getDate(StringTokenizer param)
	{
		// we returns null for optional dates
		String aToken = TestClient.nextToken(param);
		if (aToken == null)
			return null;

		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.EiaDate adate = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.EiaDate();

		adate.aYear = Short.valueOf(aToken).shortValue();
		adate.aMonth = Short.valueOf(TestClient.nextToken(param)).shortValue();
		adate.aDay = Short.valueOf(TestClient.nextToken(param)).shortValue();

		return adate;
	}

	/**
	 * Prase a StringTokenizer to retrieve the ObjectKeys and returns a list of String object.
	 * Creation date: (3/23/01 8:00:12 AM)
	 * @return ObjectHandle[]
	 * @param param java.util.StringTokenizer
	 */
	public String[] getStrings(java.util.StringTokenizer param)
	{
		List aList = new ArrayList();

		String st = TestClient.nextToken(param);
		while (st != null && !st.equalsIgnoreCase("end"))
		{
			aList.add(st);
			st = TestClient.nextToken(param);
		}

		// Now we have to dump the aList into the String list
		return (String[]) aList.toArray(new String[aList.size()]);

	}

	/**
	 * Parse from a StringTokenizer and create a corresponding PortConstraints object.
	 * Creation date: 6/22/2004
	 * @return PortConstraints
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.PortConstraints getProxyPortConstraints(java.util.StringTokenizer param)
	{

		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.PortConstraints pConstraints = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.PortConstraints();

		pConstraints.aPortSpeed = getProxyMeasurementOpt(param);
		pConstraints.aServiceType = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		pConstraints.aResourceType = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		pConstraints.aServiceLocation = getProxyLocationOpt(param);
		pConstraints.aSwitchId = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		pConstraints.aFloor = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		pConstraints.aAisle = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		pConstraints.aBay = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		pConstraints.aShelfNumber = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		pConstraints.aCard = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		pConstraints.aPortNumber = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		//pConstraints.aExistingPortId = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));

		return pConstraints;

	}
	/**
	 * Parse from a StringTokenizer and create a corresponding MeasurementOpt object.
	 * Creation date: 6/22/2004
	 * @return MeasurementOpt
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.MeasurementOpt getProxyMeasurementOpt(java.util.StringTokenizer param)
	{
		return (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.MeasurementOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.MeasurementOpt.class, getProxyMeasurement(param));
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding Measurement object.
	 * Creation date: 6/22/2004
	 * @return Measurement
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.Measurement getProxyMeasurement(java.util.StringTokenizer param)
	{
		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.Measurement measure = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.Measurement();

		measure.aQuantity = new Double(TestClient.nextToken(param)).doubleValue();
		measure.aUnit = com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.MeasurementUnit.from_int(new Integer(TestClient.nextToken(param)).intValue());

		return measure;
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding LocationOpt object.
	 * Creation date: 6/22/2004
	 * @return LocationOpt
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.LocationOpt getProxyLocationOpt(java.util.StringTokenizer param)
	{
		return (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.LocationOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.LocationOpt.class, getProxyLocation(param));
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding TrunkOpt object.
	 * Creation date: 6/22/2004
	 * @return TrunkOpt
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.TrunkOpt getProxyTrunkOpt(java.util.StringTokenizer param)
	{
		return (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.TrunkOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.TrunkOpt.class, getProxyTrunk(param));
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding Trunk object.
	 * Creation date: 6/22/2004
	 * @return Trunk
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.Trunk getProxyTrunk(java.util.StringTokenizer param)
	{
		String s = TestClient.nextToken(param);
		
		if (s == null || s.equalsIgnoreCase("end"))
			return null;
	
		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.Trunk trk = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.Trunk();

		trk.aVirtualPathId = getProxyLongOpt(param);
		trk.aVirtualChannelId = getProxyLongOpt(param);
		trk.aATMCircuitId =
			(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
			
		return trk;
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding LongOpt object.
	 * Creation date: 6/22/2004
	 * @return LongOpt
	 * @param param java.util.StringTokenizer
	 */
	public com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.LongOpt getProxyLongOpt(java.util.StringTokenizer param)
	{
		String s;
		
		if ((s = TestClient.nextToken(param)) == null) {
			return (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.LongOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.LongOpt.class, (Integer) null);
		}
		else {
			return (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.LongOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.LongOpt.class, Integer.valueOf(s));
		}
	}
	
		/**
	 * Parse from a StringTokenizer and create a corresponding boolean object.
	 * Creation date: 7/15/2004
	 * @return getBoolean
	 * @param param java.util.StringTokenizer
	 */
	public boolean getBoolean (java.util.StringTokenizer param) {
		  
		return Boolean.valueOf( TestClient.nextToken( param ) ).booleanValue();
		
	}
	
	
	/**
	 * Parse from a StringTokenizer and create a corresponding OrderAction object.
	 * Creation date: 4/20/2005
	 * @return OrderAction
	 * @param param java.util.StringTokenizer
	 */
	public OrderAction getProxyOrderAction (java.util.StringTokenizer param) {

		com.sbc.eia.idl.types.StringOpt aOrder = (com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		com.sbc.eia.idl.types.StringOpt aOrderActionId = (com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		com.sbc.eia.idl.types.StringOpt aOrderActionType = (com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));
		com.sbc.eia.idl.types.StringOpt aSubType = (com.sbc.eia.idl.types.StringOpt) IDLUtil.toOpt(com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.StringOpt.class, TestClient.nextToken(param));

		return new OrderAction(aOrder, aOrderActionId, aOrderActionType, aSubType);
	}


	/**
	 * Parse from a StringTokenizer and create a corresponding ObjectPropertSeqOpt object.
	 * Creation date: 4/20/2005
	 * @return ObjectPropertySeqOpt
	 * @param param java.util.StringTokenizer
	 */
	public ObjectPropertySeqOpt getProxyObjectPropertySeqOpt (java.util.StringTokenizer param) {
		ArrayList propList = null;
		String tag = TestClient.nextToken(param);
		while (!tag.equalsIgnoreCase("end")) {
			String value = TestClient.nextToken(param);
			ObjectProperty prop = new ObjectProperty (tag, value);
			propList.add(value);
			tag = TestClient.nextToken(param);
		}
		ObjectProperty[] props = new ObjectProperty [propList.size()];
		for (int i = 0; i < propList.size(); i++) {
			props[i] = (ObjectProperty) propList.get(i);
		}

		ObjectPropertySeqOpt propSeq = new ObjectPropertySeqOpt();
		propSeq.theValue(props);
		return propSeq;
	}
	
}
