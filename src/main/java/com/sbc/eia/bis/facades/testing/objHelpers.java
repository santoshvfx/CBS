//$Id
package com.sbc.eia.bis.facades.testing;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.eia.idl.bim_types.Name;
import com.sbc.eia.idl.bim_types.NameOpt;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.ExtensionProperty;
import com.sbc.eia.idl.lim_types.ExtensionPropertySeqOpt;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.LocationOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;
import com.sbc.eia.idl.nam_types.NetworkAddress;
import com.sbc.eia.idl.nam_types.NetworkAddressFilter;
import com.sbc.eia.idl.nam_types.NetworkAddressOpt;
import com.sbc.eia.idl.nam_types.NetworkAddressProperty;
import com.sbc.eia.idl.rm_ls_types.CopperSegment;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentSeqOpt;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransport;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.FiberSegment;
import com.sbc.eia.idl.rm_ls_types.FiberSegmentSeqOpt;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.Network7450Switch;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeValues;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminal;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminalOpt;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminal;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminalOpt;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.OrderAction2;
import com.sbc.eia.idl.rm_ls_types.OrderAction2Opt;
import com.sbc.eia.idl.rm_ls_types.ResidentialGateway;
import com.sbc.eia.idl.rm_ls_types.ResidentialGatewayOpt;
import com.sbc.eia.idl.rm_ls_types.VOIP;
import com.sbc.eia.idl.rm_ls_types.VOIPOpt;
import com.sbc.eia.idl.rm_types.LoopInfoType;
import com.sbc.eia.idl.rm_types.NetworkChannelCodeCombination;
import com.sbc.eia.idl.rm_types.NetworkChannelCodeCombinationOpt;
import com.sbc.eia.idl.rm_types.PlannedPort;
import com.sbc.eia.idl.rm_types.PlannedPortOpt;
import com.sbc.eia.idl.rm_types.Port;
import com.sbc.eia.idl.rm_types.PortConstraints;
import com.sbc.eia.idl.rm_types.PortOpt;
import com.sbc.eia.idl.rm_types.ResourceSearchKey;
import com.sbc.eia.idl.rm_types.SubscriberSideCFAOpt;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.sm_ls_types.ProductSubscriptionSeqOpt;
import com.sbc.eia.idl.types.AttributeType;
import com.sbc.eia.idl.types.AttributeTypeSeqOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.Measurement;
import com.sbc.eia.idl.types.MeasurementOpt;
import com.sbc.eia.idl.types.MeasurementUnit;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectKeyOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ObjectType;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.eia.idl.types.Time;
import com.sbc.logging.LogAssistant;
import com.sbc.logging.LogAssistantFactory;

/**
 * Contains static methods to parse StringTokenizer to create certain objects for the test client.
 * Creation date: (3/23/01 7:58:54 AM)
 * @author: Sam Lok
#   History :
#   Date        | Author            | Version   |Notes
#   ----------------------------------------------------------------------------
#   6/14/2004     Stevan Dunkin       RM 9       Added methods:
													getBoolean
													getPortConstraints
													getMeasurementOpt
													getMeasurement
													getLocationOpt
													getTrunkOpt
													getTrunk
													getLongOpt
                                                    
                                                    
	8/25/2004    Mark Liljequit     RM 9.0          modified getTrunkOpt, getLocationOpt, getLongOpt, getTrunk,
													getMeasurementOpt   
    
	9/7/2004     Stevan Dunkin      RM 10.0      Added methods:
													getDataServiceResponseHeader()
													getCircuitDetail()
													getStringSeqOpt()
													getNetworkChannelCodeCombinationOpt()
													getPlannedPortOpt()
													getPlannedPort()
													getPort()
													getCircuitLayoutRecordDesignElementOpt()
													getCircuitLayoutRecordDesignElement()
													getDataServiceRequestHeader()
													getChannelAllocationPolicyOpt()
													getChannelAllocationPolicy()
													getPortOpt()
													getDataChannelOpt()
													getDataChannel()
													getDateOpt()    
													ConnectionSeqOpt()
													getConnections()
													getConnection()
                                                    
                                                    
	 11/01/2004  Stevan Dunkin       RM10.0         Fixed possible error in getMeasurement() method in which 
													second if structure would never result in true because of 
													incrementing the string token past the the item needed for the conditional check.
                                                    
													Modified logic in getLocation() method.
                                                    
	 11/02/2004  Stevan Dunkin       RM10.0         Fixed error in getDataChannel() method that resulted in an exception if no data.
     
	 11/02/2004  Stevan Dunkin       RM10.0         Fixed error in getMeasurement() method that resulted in an exception if no data.
                                               
	 11/10/2004  Mike McDonough      NAM 5.0.2      Added code to handle 'ServiceCity' input field                                                    
                                                    
	 1/6/2004    Mike McDonough      NAM 5.1        IDL bundle 23 added fields to two objects.
													FieldedAddress added five new fields aCountryCode, aCityCode, aServiceLocationName, 
													aAddressId, aAliasName, aAttention - NAM is not currently using these fields.  Set them
													all to default until the are needed.  
													ProviderLocationProperty added two new fields aEcktId, aCustomerPremiseIndicator - set to
													default values 
                                                    
	12/23/2004  Manjula Goniguntla                  Added methods for rm_winds
	01/21/2005     Jinmin Ni                        ATM: Added getLocationExtention() method
	02/02/2005     jp2854                           Added getEmptyCircuit() method
	02/09/2005     Jinmin Ni                        ATM: modify to accommodate the idl bundle 24 change in portgroup 
	02/18/2005     Vickie Ng                        Added WFA methods()
	03/10/2005  Manjula Goniguntla                  Deleted methods written for rm_winds.
	04/04/2005  Sriram Chevuturu                    Updated to comply with the change of ObjectHandle to ObjectKey.
	05/05/2005  jp2854                              Added getCompositeObjectKey method
	05/09/2005  Mark Kashevaroff                    Added getOrderAction() and getObjectPropertySeqOpt() 
	05/09/2005  Rene Duka                           Added DSLAM(), getNetwork7450Switch(), getVOIP(), getVOIPOpt(), getBooleanOpt() 
	05/09/2005  Manjula Goniguntla                  Added getAddressOpt(), getResidentialGatewayOpt(), getCustomerPremisEquipmentOpt().
	05/09/2005  Kavitha Kodali                      Added getNetworkAddressOpt(), getNetworkAddress(), getDSLAMTransportOpt(), getResidentialGateway().
	05/10/2005  Kavitha Kodali                      Added getActivationTime().
	05/10/2005  Kavitha Kodali                      Added getShort().
	05/16/2005  Rene Duka                           Modified getDSLAM(), getNetwork7450Switch(), getVOIP(), getVOIPOpt(), getBooleanOpt(), getLocation(), createLocationProperty()
	05/31/2005  Manjula Goniguntla                  Added methods getAddress() and getCustomerPremisEquipment(). 
	06/07/05    Jyothi Pentyala                     Added getServiceAreaInterface, getSplitter, getMultipleDwellingUnit, getSingleFamilyUnit, getFiberServingTerminal and corresponding getxxxOpt methods
	06/29/05    Kavitha Kodali                      Renamed getDSLAM() to getDSLAMTransport()
													Updated getDSLAMTransportOpt()
													Added aSerialNumber to getResidentialGateway()
													Renamed geSplitterOpt() to getSplitterOpt()
													Renamed geFiberServingTerminalOpt() to getFiberServingTerminalOpt()
													Added getPrimaryFlexiblePoint(), getOpticalLineTerminal(), getDSLAM()
													Added getPrimaryFlexiblePointOpt(), getOpticalLineTerminalOpt(), getDSLAMOpt()
	07/22/05    Jyothi Pentyala                     Added getFiberCable, getPigtailCable and corresponding getxxxOpt methods
	07/29/05    jp2854                              Added initializations for missing fields for unfiedled address in getLocation() method.
	10/06/2005  Manjula Goniguntla                  Modified getAddress() for the DR 142810.                                                                                
	11/01/2005  Sumana Roy                          Changed the data type of providerName to StringOpt  
													Changed the data type of the field aExtensions to ExtensionPropertySeqOpt in createLocationProperty()                                                                       
													Changed the method getLocation() to manipulate ExtensionPropertySeqOpt type aExtensions.
	11/07/2005  jp2854                              IDL bundle 33_0 changes.
	11/10/2005  jp2854                              Added getOpticalNetworkTerminal, getOpticalNetworkTerminalOpt methods.                                                  
	12/09/2005  Rene Duka                           Added framework for handling the NetworkType object.
	12/13/2005  Rene Duka                           Added getNetworkType().
	01/16/2006  Michael Khalili                     Added the getProductSubscription(StringTokenizer st) method.
	06/20/2006	Chaitanya							Added a field in OpticalNetworkTerminal object for IDL bundle change.
	08/18/2006  Mark Liljequist                     For IDL bundle 40. Remove objects releated to Modify Network Inventory.
	10/17/2006  Manjula Goniguntla                  Modified getTrunk() as this was resulting in NullPointerException for the DSL transactions. 
                                                           (NOTE: Found while working on the DR 168709) 
	07/19/2007  Sam Lok                             RM 389266:
                                                        Removed getDataServiceResponseHeader,
                                                                getCircuitDetail,
                                                                getCircuitLayoutRecordDesignElementOpt,
                                                                getCircuitLayoutRecordDesignElement,
                                                                getDataServiceRequestHeader,
                                                                getChannelAllocationPolicyOpt,
                                                                getChannelAllocationPolicy,
                                                                getDataChannelOpt,
                                                                getDataChannel,
                                                                getConnectionSeqOpt,
                                                                getConnections,
                                                                getConnection
    01/03/2009 Lira Galsim							LS10: Added getOrderAction2() and getOrderAction2Opt().                                                    
	*
    */

public class objHelpers 
{
	public final static StringOpt providerName = IDLUtil.toOpt("SBC");
	private final static String ROUTING_DELIMITER = "#";
	private final static String DELIMITER = "|";
	/**
	 * objHelpers constructor comment.
	 */
	public objHelpers() 
	{
		super();
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding BisContext object.
	 * Creation date: (5/14/01 10:08:26 AM)
	 * @return BisContext
	 * @param param java.util.StringTokenizer
	 */
	public static BisContext getBisContext(StringTokenizer param) 
	{
		String tag = null;
		ObjectPropertyManager myObjProp = new ObjectPropertyManager();

		tag = TestClient.nextToken(param);
		while (tag != null && !tag.equalsIgnoreCase("end")) 
		{
			if (tag.equalsIgnoreCase(BisContextProperty.LOGGINGINFORMATION)) 
			{
				LogAssistant theLogAssistant =
					LogAssistantFactory.create(
						TestClient.nextToken(param),
						"--");

				theLogAssistant.genNewCorrID();
				myObjProp.add(tag, theLogAssistant.getCorrID());
			} 
			else if (tag.equalsIgnoreCase("Routing")) 
			{
				StringTokenizer st =
					new StringTokenizer(
						TestClient.nextToken(param),
						ROUTING_DELIMITER);
				StringBuffer sb = new StringBuffer();
				int i = 0, tokenCount = st.countTokens();
				while (st.hasMoreElements()) 
				{
					sb.append(st.nextToken());
					i++;
					if (i < tokenCount)
						sb.append(DELIMITER);
				}
				myObjProp.add(tag, sb.toString());
			} 
			else
				myObjProp.add(tag, TestClient.nextToken(param));

			tag = TestClient.nextToken(param);
		}
		return new BisContext(myObjProp.toArray());
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding Date object.
	 * Creation date: (3/29/01 4:10:57 PM)
	 * @return EiaDate
	 * @param param java.util.StringTokenizer
	 */
	public static EiaDate getDate(StringTokenizer param) 
	{
		// we returns null for optional dates
		String aToken = TestClient.nextToken(param);
		if (aToken == null)
			return null;

		EiaDate adate = new EiaDate();

		adate.aYear = Short.valueOf(aToken).shortValue();
		adate.aMonth = Short.valueOf(TestClient.nextToken(param)).shortValue();
		adate.aDay = Short.valueOf(TestClient.nextToken(param)).shortValue();

		return adate;
	}
    
	/**
	 * Parse the StringTokenizer to extract and return an Location object.
	 * Creation date: (3/23/01 3:08:33 PM)
	 * @return Location
	 * @param param java.util.StringTokenizer
	 */
	public static Location getLocation(java.util.StringTokenizer param) 
	{
		Location lo = new Location();
		lo.aLocationId = (StringOpt) IDLUtil.toOpt((String) null);
		// lo.aLocationId = (StringOpt) IDLUtil.toOpt((String) TestClient.nextToken(param));
  
		String tn = null;
		String sbcServingOfficeCode = null;
		String sbcServingOfficeWirecenter = null;
		String nearestSbcCoWirecenter = null;
		String primaryNpaNxx = null;        
		String st = TestClient.nextToken(param);
		ArrayList aList = new ArrayList();
        
		if (st != null) 
		{
			ExtensionProperty[] aExtensionProperties = null;
			ExtensionPropertySeqOpt aExtensions = (
				ExtensionPropertySeqOpt) IDLUtil.toOpt(
					ExtensionPropertySeqOpt.class,
					aExtensionProperties);
                    
			if (st.equals("FieldedAddress")) 
			{
				FieldedAddress fa = new FieldedAddress();
				
				fa.aAddressId = 
				    (StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aRoute =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aBox =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aHouseNumberPrefix =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aHouseNumber =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aAssignedHouseNumber =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aHouseNumberSuffix =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aStreetDirection =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aStreetName =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aStreetThoroughfare =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aStreetNameSuffix =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aCity =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aState =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aPostalCode =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aPostalCodePlus4 =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aCounty =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aCountry =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aStructureType =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aStructureValue =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aLevelType =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aLevelValue =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aUnitType =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aUnitValue =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aOriginalStreetDirection =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aOriginalStreetNameSuffix =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aCassAddressLines =
					(StringSeqOpt) IDLUtil.toOpt(
						StringSeqOpt.class,
						getStrings(param));
				fa.aAuxiliaryAddressLines =
					(StringSeqOpt) IDLUtil.toOpt(
						StringSeqOpt.class,
						getStrings(param));
				fa.aCassAdditionalInfo =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aAdditionalInfo =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				fa.aBusinessName =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));

				//New fields added to fielded address IDL bundle 23 - not currently used.
				//Just setting default values as place holders
				StringOpt defaultStringOpt = new StringOpt();
				defaultStringOpt.__default();
				fa.aCountryCode = defaultStringOpt;
				fa.aCityCode = defaultStringOpt;
				fa.aServiceLocationName = defaultStringOpt;
			    //fa.aAddressId = defaultStringOpt;
				fa.aAliasName = defaultStringOpt;
				fa.aAttention = defaultStringOpt;

				Address addr = new Address();
				addr.aFieldedAddress(fa);
				
				String lataname = null;
				String lpOffCode = null;

				st = TestClient.nextToken(param);
				if(st.equalsIgnoreCase("LATA"))
				{
					lataname = TestClient.nextToken(param);
					st = TestClient.nextToken(param);
				}
				if(st.equalsIgnoreCase("CLLI"))
				{
					lpOffCode = TestClient.nextToken(param);
					st = TestClient.nextToken(param);
				}
				
				st = TestClient.nextToken(param);
				if (st.equalsIgnoreCase("PROP")) 
				{
					st = TestClient.nextToken(param);
					if (st.equalsIgnoreCase("TelephoneNumber")) 
					{
						tn = TestClient.nextToken(param);
						//next token should be "ServingOfficeCode" or "end"
						st = TestClient.nextToken(param);
					}
					if (st.equalsIgnoreCase("ServingOfficeCode")) 
					{
						sbcServingOfficeCode = TestClient.nextToken(param);
						//next token should be "end"
						st = TestClient.nextToken(param);
					}
					if (st.equalsIgnoreCase("SbcServingOfficeWirecenter")) 
					{
						sbcServingOfficeWirecenter = TestClient.nextToken(param);
						st = TestClient.nextToken(param);
					}
					if (st.equalsIgnoreCase("PrimaryNpaNxx")) 
					{
						primaryNpaNxx = TestClient.nextToken(param);
						st = TestClient.nextToken(param);
					}
					if (st.equalsIgnoreCase("NearestSbcCoWirecenter")) 
					{
						nearestSbcCoWirecenter = TestClient.nextToken(param);
						st = TestClient.nextToken(param);
					}               
				}
				if (st.equalsIgnoreCase("ServiceArea")) 
				{
					//Changed the type of aExtensions to ExtensionPropertySeqOpt
					aExtensionProperties =
						new ExtensionProperty[] {
							 new ExtensionProperty(
								"ServiceArea",
								TestClient.nextToken(param))};
					aExtensions =
						(ExtensionPropertySeqOpt) IDLUtil.toOpt(
							ExtensionPropertySeqOpt.class,
							aExtensionProperties);

				}

				lo.aProviderLocationProperties =
					createLocationProperty(
						addr,
						tn,
						sbcServingOfficeCode,
						sbcServingOfficeWirecenter,
						primaryNpaNxx,
						nearestSbcCoWirecenter,
						aExtensions,
						lataname,
						lpOffCode);

			} 
			else if (st.equals("UnfieldedAddress")) 
			{
				UnfieldedAddress ufa = new UnfieldedAddress();

				ufa.aBusinessName =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aAddressLines =
					(StringSeqOpt) IDLUtil.toOpt(
						StringSeqOpt.class,
						getStrings(param));
				ufa.aCity =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aState =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aPostalCode =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aPostalCodePlus4 = (StringOpt) IDLUtil.toOpt("");
				ufa.aCounty =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aCountry =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aStructureType =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aStructureValue =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aLevelType =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aLevelValue =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aUnitType =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aUnitValue =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
				ufa.aAdditionalInfo =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
                
				//Initializations for missing fields. These fields are not currently used.
				ufa.aCountryCode = (StringOpt)IDLUtil.toOpt((String) null);
				ufa.aCityCode = (StringOpt)IDLUtil.toOpt((String) null);
				ufa.aServiceLocationName = (StringOpt)IDLUtil.toOpt((String) null);
				ufa.aAddressId = (StringOpt)IDLUtil.toOpt((String) null);
				ufa.aAliasName = (StringOpt)IDLUtil.toOpt((String) null);
				ufa.aAttention = (StringOpt)IDLUtil.toOpt((String) null);

				Address addr = new Address();
				addr.aUnfieldedAddress(ufa);
				
				String lataname = null;
				String lpOffCode = null;

				st = TestClient.nextToken(param);
				if(st.equalsIgnoreCase("LATA"))
				{
					lataname = TestClient.nextToken(param);
					st = TestClient.nextToken(param);
				}
				if(st.equalsIgnoreCase("CLLI"))
				{
					lpOffCode = TestClient.nextToken(param);
					st = TestClient.nextToken(param);
				}

				st = TestClient.nextToken(param);
				if (st.equalsIgnoreCase("PROP")) 
				{
					st = TestClient.nextToken(param);
					if (st.equalsIgnoreCase("TelephoneNumber")) 
					{
						tn = TestClient.nextToken(param);
						//next token should be "ServingOfficeCode" or "end"
						st = TestClient.nextToken(param);
					}
					if (st.equalsIgnoreCase("ServingOfficeCode")) 
					{
						sbcServingOfficeCode = TestClient.nextToken(param);
						//next token should be "end"
						st = TestClient.nextToken(param);
					}
					if (st.equalsIgnoreCase("SbcServingOfficeWirecenter")) 
					{
						sbcServingOfficeWirecenter = TestClient.nextToken(param);
						st = TestClient.nextToken(param);
					}
					if (st.equalsIgnoreCase("PrimaryNpaNxx"))
					{
						primaryNpaNxx = TestClient.nextToken(param);
						st = TestClient.nextToken(param);
					}
					if (st.equalsIgnoreCase("NearestSbcCoWirecenter")) 
					{
						nearestSbcCoWirecenter = TestClient.nextToken(param);
						st = TestClient.nextToken(param);
					}           
				}
				if (st.equalsIgnoreCase("ServiceArea")) 
				{
                    
					aExtensionProperties =
						new ExtensionProperty[] {
							 new ExtensionProperty(
								"ServiceArea",
								TestClient.nextToken(param))};
					aExtensions =
						(ExtensionPropertySeqOpt) IDLUtil.toOpt(
							ExtensionPropertySeqOpt.class,
							aExtensionProperties);
				}
				lo.aProviderLocationProperties =
					createLocationProperty(
						addr,
						tn,
						sbcServingOfficeCode,
						sbcServingOfficeWirecenter,
						primaryNpaNxx,
						nearestSbcCoWirecenter,
						aExtensions,
						lataname,
						lpOffCode);                   

			}
			return lo;
		} 
		else 
		{
			return null;
		}
	}
	//
	//Create ProviderLocationProperty[]
	//
	private static ProviderLocationProperty[] createLocationProperty(
		Address addr,
		String tn,
		String sbcServingOfficeCode,
		String sbcServingOfficeWirecenter,
		String primaryNpaNxx,
		String nearestSbcCoWirecenter,
		ExtensionPropertySeqOpt aExtensions,
		String lataName,
		String lpSrvOffCode) 
	{
		StringOpt defaultStringOpt = new StringOpt();
		defaultStringOpt.__default();

		if (aExtensions == null) 
		{
			ExtensionProperty[] aExtensionProperties = 
				new ExtensionProperty[0];               
			aExtensions =
				(ExtensionPropertySeqOpt) IDLUtil.toOpt(
					ExtensionPropertySeqOpt.class,
					aExtensionProperties);                     
		}

		AddressOpt aPostalOpt = new AddressOpt();
		aPostalOpt.__default();

		AddressOpt aSwitchSuperPopAddressOpt = new AddressOpt();
		aSwitchSuperPopAddressOpt.__default();

		//new ProviderLocationProperty[0];

		ProviderLocationProperty aLocationProperty[] = new ProviderLocationProperty[1];
		aLocationProperty[0] = new ProviderLocationProperty();
		aLocationProperty[0].aAddressMatchCode 					= defaultStringOpt;
		aLocationProperty[0].aAddressMatchCodeDescription 		= defaultStringOpt;
		aLocationProperty[0].aBuildingType						= defaultStringOpt;
		aLocationProperty[0].aCentralOfficeCode					= defaultStringOpt;
		aLocationProperty[0].aCentralOfficeType					= defaultStringOpt;
		aLocationProperty[0].aCityStatePostalCodeValid			= defaultStringOpt;
		aLocationProperty[0].aCommunityName						= defaultStringOpt;
		aLocationProperty[0].aCoSwitchSuperPop					= defaultStringOpt;
		aLocationProperty[0].aCountyId							= defaultStringOpt;
		aLocationProperty[0].aCustomerPremiseIndicator			= defaultStringOpt;
		aLocationProperty[0].aDomSwitchPop						= defaultStringOpt;
		aLocationProperty[0].aE911Address						= (AddressOpt) IDLUtil.toOpt(AddressOpt.class, null);
		aLocationProperty[0].aE911Exempt						= defaultStringOpt;
		aLocationProperty[0].aE911NonRecurringCharge			= defaultStringOpt;
		aLocationProperty[0].aE911Surcharge						= defaultStringOpt;
		aLocationProperty[0].aEcktId							= defaultStringOpt;
		aLocationProperty[0].aExceptionCode						= defaultStringOpt;
		aLocationProperty[0].aExceptionDescription				= defaultStringOpt;
		aLocationProperty[0].aExchangeCode						= defaultStringOpt;
		aLocationProperty[0].aExco								= defaultStringOpt;
		aLocationProperty[0].aExtensions 						= aExtensions;
		aLocationProperty[0].aGeoCode							= defaultStringOpt;
		aLocationProperty[0].aHorizontalCoordinate				= defaultStringOpt;
		aLocationProperty[0].aLataCode							= defaultStringOpt;
		aLocationProperty[0].aLataName							= (StringOpt) IDLUtil.toOpt(lataName);
		aLocationProperty[0].aLatitude							= defaultStringOpt;
		aLocationProperty[0].aLatitude							= defaultStringOpt;
		aLocationProperty[0].aLegalEntity						= defaultStringOpt;
		aLocationProperty[0].aLocalProviderAbbreviatedName		= defaultStringOpt;
		aLocationProperty[0].aLocalProviderExchangeCode			= defaultStringOpt;
		aLocationProperty[0].aLocalProviderName					= defaultStringOpt;
		aLocationProperty[0].aLocalProviderNumber				= defaultStringOpt;
		aLocationProperty[0].aLocalProviderServingOfficeCode	= (StringOpt) IDLUtil.toOpt(lpSrvOffCode);
		aLocationProperty[0].aLocationType						= defaultStringOpt;
		aLocationProperty[0].aLongitude							= defaultStringOpt;
		aLocationProperty[0].aMailingBarCodeSuffix				= defaultStringOpt;
		aLocationProperty[0].aMsaCode							= defaultStringOpt;
		aLocationProperty[0].aMsaName							= defaultStringOpt;
		aLocationProperty[0].aNearestDistanceColoToCo			= defaultStringOpt;
		aLocationProperty[0].aNearestDistanceSuperPopToCo		= defaultStringOpt;
		aLocationProperty[0].aNearestSbcColo					= defaultStringOpt;
		aLocationProperty[0].aNearestSbcCoSuperPop				= defaultStringOpt;
		aLocationProperty[0].aNearestSbcCoWirecenter			= (StringOpt) IDLUtil.toOpt(nearestSbcCoWirecenter);
		aLocationProperty[0].aOwnedWiring						= defaultStringOpt;
		aLocationProperty[0].aPostalAddress						= aPostalOpt;
		aLocationProperty[0].aPostalCarrierCode					= defaultStringOpt;
		aLocationProperty[0].aPrimaryDirectoryCode				= defaultStringOpt;
		aLocationProperty[0].aPrimaryNpaNxx 					 = (StringOpt) IDLUtil.toOpt(primaryNpaNxx);
		aLocationProperty[0].aProviderName 						 = providerName;
		aLocationProperty[0].aPublicSafetyAnsweringPointId		= defaultStringOpt;
		aLocationProperty[0].aQuickDialTone						= defaultStringOpt;
		aLocationProperty[0].aQuickDialToneNumber				= defaultStringOpt;
		aLocationProperty[0].aRateCenterCode					= defaultStringOpt;
		aLocationProperty[0].aRateZone							= defaultStringOpt;
		aLocationProperty[0].aRateZoneBandCode					= defaultStringOpt;
		aLocationProperty[0].aSAGAddress						= (AddressOpt) IDLUtil.toOpt(AddressOpt.class, null);;
		aLocationProperty[0].aSagNpa							= defaultStringOpt;
		aLocationProperty[0].aSagWireCenter						= defaultStringOpt;
		aLocationProperty[0].aSbcColoLsoCode					= defaultStringOpt;
		aLocationProperty[0].aSbcColoWirecenter					= defaultStringOpt;
		aLocationProperty[0].aSbcServingOfficeCode				= (StringOpt) IDLUtil.toOpt(sbcServingOfficeCode);
		aLocationProperty[0].aSbcServingOfficeWirecenter 		= (StringOpt) IDLUtil.toOpt(sbcServingOfficeWirecenter);
		aLocationProperty[0].aServiceAddress					= (AddressOpt) IDLUtil.toOpt(AddressOpt.class, addr);
		aLocationProperty[0].aServingAreaDescription			= defaultStringOpt;
		aLocationProperty[0].aServingNetworkType				= defaultStringOpt;
		aLocationProperty[0].aSmartmoves						= defaultStringOpt;
		aLocationProperty[0].aStreetAddressGuideArea			= defaultStringOpt;
		aLocationProperty[0].aSurcharge16Percent				= defaultStringOpt;
		aLocationProperty[0].aSurcharge4Percent					= defaultStringOpt;
		aLocationProperty[0].aSwitchDataSuperPop				= defaultStringOpt;
		aLocationProperty[0].aSwitchSuperPopAddress				= aSwitchSuperPopAddressOpt;
		aLocationProperty[0].aSwitchVoiceSuperPop				= defaultStringOpt;
		aLocationProperty[0].aTarCode							= defaultStringOpt;
		aLocationProperty[0].aTelephoneNumber					= (StringOpt) IDLUtil.toOpt(tn);
		aLocationProperty[0].aVerticalCoordinate				= defaultStringOpt;
		aLocationProperty[0].aVideoHubOffice					= defaultStringOpt;
		aLocationProperty[0].aWorkingServiceOnLocation			= defaultStringOpt;
			
		return aLocationProperty;
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding LoopInfoType object.
	 * Creation date: (3/23/01 8:20:07 AM)
	 * @return LoopInfoType
	 * @param param java.util.StringTokenizer
	 */
	public static LoopInfoType getLoopInfoType(
		java.util.StringTokenizer param) 
	{
		LoopInfoType result = null;

		Integer type = new Integer(TestClient.nextToken(param));
		switch (type.intValue()) 
		{
			case LoopInfoType._ACTUAL :
				result = LoopInfoType.ACTUAL;
				break;

			case LoopInfoType._DESIGN :
				result = LoopInfoType.DESIGN;
				break;

			case LoopInfoType._MANUAL :
				result = LoopInfoType.MANUAL;
				break;

			case LoopInfoType._TENLOOP :
				result = LoopInfoType.TENLOOP;
				break;

			case LoopInfoType._PRE_QUAL :
			default :
				result = LoopInfoType.PRE_QUAL;
				break;
		}
		return result;
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding NetworkAddressFilter object.
	 * Creation date: (3/26/01 11:47:46 AM)
	 * @return NetworkAddressFilter
	 * @param param java.util.StringTokenizer
	 */
	public static NetworkAddressFilter getNetworkAddressFilter(
		java.util.StringTokenizer param) 
	{
		NetworkAddressFilter naf = new NetworkAddressFilter();

		// the requested quantity
		naf.aQuantityRequested =
			Short.valueOf(TestClient.nextToken(param)).shortValue();

		// and a list of ObjectProperties
		naf.aProperties = getObjectProperties(param);

		return naf;
	}

	/**
	 * Prase a StringTokenizer to retrieve the ObjectKey and returns a ObjectKey object.
	 * Creation date: (4/1/05 8:00:12 AM)
	 * @return ObjectKey
	 * @param param java.util.StringTokenizer
	 */
	public static ObjectKey getObjectKey(
		java.util.StringTokenizer param) 
	{

		String aValue = TestClient.nextToken(param);
		String aKind = TestClient.nextToken(param);

		ObjectKey aOK = null;
		if (aValue != null)
			aOK = new ObjectKey(aValue, aKind);
		else 
			aOK = new ObjectKey("", "");
		return aOK;
	}
	/**
	 * Prase a StringTokenizer to retrieve the ObjectKeys and returns a list of ObjectKeys objects.
	 * Creation date: (4/1/05 8:00:12 AM)
	 * @return ObjectKey[]
	 * @param param java.util.StringTokenizer
	 */
	public static ObjectKey[] getObjectKeys(
		java.util.StringTokenizer param) 
	{
		List aList = new ArrayList();

		String aValue = TestClient.nextToken(param);
		while (aValue != null && !aValue.equalsIgnoreCase("end")) 
		{
			String aKind = TestClient.nextToken(param);
			aList.add( new ObjectKey(aValue, aKind));
			aValue = TestClient.nextToken(param);
		}
		return (ObjectKey[]) aList.toArray(new ObjectKey[aList.size()]);
	}
	
	/**
	 * @param param
	 * @return
	 */
	public static ObjectType[] getObjectTypes(
		java.util.StringTokenizer param) 
	{
		ObjectType[] aObjectTypes = null;
		
		AttributeType[] aAttributes = null;
					
		aObjectTypes = new ObjectType[1];
		aAttributes = new AttributeType[1];
		
		aAttributes[0] = new AttributeType("ntiNegotiationValue", TestClient.nextToken(param));								
	
		AttributeTypeSeqOpt aAttributesOpt =
			(AttributeTypeSeqOpt) IDLUtil.toOpt(
					AttributeTypeSeqOpt.class,
					aAttributes);
		
		aObjectTypes[0].aName = "networkTransport";
		aObjectTypes[0].aAttribute = aAttributesOpt;
		aObjectTypes[0].aObject = aObjectTypes;	
					
		return aObjectTypes;
	}
	
	/**
	 * Parse TN, ServiceArea, NetworkType values and returns a NetworkAddress array.
	 * Creation date: (01/13/2005)
	 * @return NetworkAddress[]
	 * @param param java.util.StringTokenizer
	 */
	public static NetworkAddress[] getNetworkAddresses(
		java.util.StringTokenizer param) 
	{
		List aList = new ArrayList();
		ObjectProperty[] aProperties = null;
		String status = "";
		String aValue = "";
		String aKind = "";
                
		String aString = new String();              

		aValue = TestClient.nextToken(param);
		while (aValue != null && !aValue.equalsIgnoreCase("end")) 
		{
			aProperties = new ObjectProperty[1];
			aProperties[0] =
				new ObjectProperty(
					NetworkAddressProperty.RESERVATION_STATUS,
					status);

			ObjectPropertySeqOpt propertyOpt =
				(ObjectPropertySeqOpt) IDLUtil.toOpt(
					ObjectPropertySeqOpt.class,
					aProperties);

			aList.add(new NetworkAddress(objHelpers.getObjectKey(param),
			// TN
			aString, // not used
			IDLUtil.toOpt(TestClient.nextToken(param)), // Service Area
			IDLUtil.toOpt(TestClient.nextToken(param)), // NetworkType
			propertyOpt)); // Property
			aValue = TestClient.nextToken(param);
		}
		return (NetworkAddress[]) aList.toArray(
			new NetworkAddress[aList.size()]);
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding list of ObjectProperty objects.
	 * Creation date: (3/27/01 11:22:47 AM)
	 * @return ObjectProperty[]
	 * @param param java.util.StringTokenizer
	 */
	public static ObjectProperty[] getObjectProperties(StringTokenizer param) 
	{
		ObjectPropertyManager aOPMgr = new ObjectPropertyManager();
		String tag = TestClient.nextToken(param);
		while (!tag.equalsIgnoreCase("end")) 
		{
			String value = TestClient.nextToken(param);
			aOPMgr.add(tag, value);
			tag = TestClient.nextToken(param);
		}
		return aOPMgr.toArray();
	}
	/**
	 * Parse a StringTokenizer to extract and return a ResourceSearchKey object.
	 * Creation date: (3/23/01 8:01:26 AM)
	 * @return ResourceSearchKey
	 * @param param java.util.StringTokenizer
	 */
	public static ResourceSearchKey getResourceSearchKey(
		java.util.StringTokenizer param) 
	{
		ResourceSearchKey result = new ResourceSearchKey();

		// get WTN
		String tmpStr = TestClient.nextToken(param);
		if (tmpStr.equals("WTN")) {
			ObjectKey a = getObjectKey(param);
			// result.aServiceHandle = (ObjectKeyOpt) IDLUtil.toOpt(ObjectHandleOpt.class, a);
			if (a.aValue == null || a.aValue.equals(""))
					result.aServiceHandle = (ObjectKeyOpt) IDLUtil.toOpt(ObjectKeyOpt.class, null);
			else
					result.aServiceHandle = (ObjectKeyOpt) IDLUtil.toOpt(ObjectKeyOpt.class, a);
		}

		// get LSO
		tmpStr = TestClient.nextToken(param);
		if (tmpStr.equals("LSO")) 
		{
			StringOpt a = getStringOpt(param);
			result.aLocalServingOfficeHandle = a;
		}

		// get Location
		tmpStr = TestClient.nextToken(param);
		if (tmpStr.equals("LOCATION")) 
		{
			Location a = getLocation(param);
			result.aServiceLocation =
				(LocationOpt) IDLUtil.toOpt(LocationOpt.class, a);
		}

		return result;
	}
	/**
	 * Prase a StringTokenizer to retrieve the String and returns a ShortOpt object.
	 * Creation date: (3/23/01 8:00:12 AM)
	 * @return ShortOpt
	 * @param param java.util.StringTokenizer
	 */
	public static ShortOpt getShortOpt(java.util.StringTokenizer param) 
	{
		String st ;
        
		if ((st = TestClient.nextToken(param)) == null) 
		{
			return (ShortOpt) IDLUtil.toOpt((Short) null);
		} 
		else 
		{
			return (ShortOpt) IDLUtil.toOpt(Short.valueOf(st));
		}
	}
	/**
	 * Prase a StringTokenizer to retrieve the ObjectKeys and returns a list of ObjectHandle object.
	 * Creation date: (3/23/01 8:00:12 AM)
	 * @return ObjectHandle[]
	 * @param param java.util.StringTokenizer
	 */
	public static short[] getShorts(java.util.StringTokenizer param) 
	{
		List aList = new ArrayList();

		String st = TestClient.nextToken(param);
		while (st != null && !st.equalsIgnoreCase("end")) 
		{
			aList.add(Short.valueOf(st));
			st = TestClient.nextToken(param);
		}

		// Now we have to dump the aList into the short list
		short[] shorts = new short[aList.size()];

		ListIterator aIt = aList.listIterator();
		int idx = 0;
		while (aIt.hasNext()) 
		{
			shorts[idx] = ((Short) aIt.next()).shortValue();
			idx++;
		}

		return shorts;
	}
	/**
	 * Prase a StringTokenizer to retrieve the String and returns a StringOpt object.
	 * Creation date: (3/23/01 8:00:12 AM)
	 * @return StringOpt
	 * @param param java.util.StringTokenizer
	 */
	public static StringOpt getStringOpt(java.util.StringTokenizer param) 
	{
		String st = TestClient.nextToken(param);
		if (st != null && st.equalsIgnoreCase("null"))
			st = null;

		return IDLUtil.toOpt((String) st);
	}
	/**
	 * Prase a StringTokenizer to retrieve the ObjectKeys and returns a list of String object.
	 * Creation date: (3/23/01 8:00:12 AM)
	 * @return ObjectHandle[]
	 * @param param java.util.StringTokenizer
	 */
	public static String[] getStrings(java.util.StringTokenizer param) 
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
	 * Parse from a StringTokenizer and create a corresponding boolean object.
	 * Creation date: 6/21/2004
	 * @return getBoolean
	 * @param param java.util.StringTokenizer
	 */
	public static boolean getBoolean(java.util.StringTokenizer param)
	{
		return Boolean.valueOf(TestClient.nextToken(param)).booleanValue();
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding PortConstraints object.
	 * Creation date: 6/14/2004
	 * @return PortConstraints
	 * @param param java.util.StringTokenizer
	 */
	public static PortConstraints getPortConstraints(
		java.util.StringTokenizer param) 
	{

		PortConstraints pConstraints = new PortConstraints();

		pConstraints.aPortSpeed = getMeasurementOpt(param);
		pConstraints.aServiceType =
			(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
		pConstraints.aResourceType =
			(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));

		pConstraints.aServiceLocation = getLocationOpt(param);

		pConstraints.aSwitchId =
			(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
		pConstraints.aFloor =
			(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
		pConstraints.aAisle =
			(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
		pConstraints.aBay =
			(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
		pConstraints.aShelfNumber =
			(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
		pConstraints.aCard =
			(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
		pConstraints.aPortNumber =
			(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
		pConstraints.aExistingCircuitId =
			(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));

		return pConstraints;

	}
	/**
	 * Parse from a StringTokenizer and create a corresponding MeasurementOpt object.
	 * Creation date: 6/14/2004
	 * @return MeasurementOpt
	 * @param param java.util.StringTokenizer
	 */
	public static MeasurementOpt getMeasurementOpt(
		java.util.StringTokenizer param) 
	{
		return (MeasurementOpt) IDLUtil.toOpt(
			MeasurementOpt.class,
			getMeasurement(param));
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding Measurement object.
	 * Creation date: 6/14/2004
	 * @return Measurement
	 * @param param java.util.StringTokenizer
	 */
	public static Measurement getMeasurement(java.util.StringTokenizer param) 
	{

		String value = TestClient.nextToken(param);

		if (value == null) 
		{
			return null;
		}

		Measurement measure = new Measurement();

		measure.aQuantity = new Double(value).doubleValue();

		value = TestClient.nextToken(param);
		if (value.equalsIgnoreCase("Mbps"))
			measure.aUnit = MeasurementUnit.MEGABITS_PER_SECOND;
		else if (value.equalsIgnoreCase("Kbps"))
			measure.aUnit = MeasurementUnit.KILOBITS_PER_SECOND;

		return measure;
	}
	/**
	 * Parse from a StringTokenizer and create a corresponding LocationOpt object.
	 * Creation date: 6/14/2004
	 * @return LocationOpt
	 * @param param java.util.StringTokenizer
	 */
	public static LocationOpt getLocationOpt(java.util.StringTokenizer param) 
	{
		return (LocationOpt) IDLUtil.toOpt(
			LocationOpt.class,
			getLocation(param));
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding LongOpt object.
	 * Creation date: 6/14/2004
	 * @return LongOpt
	 * @param param java.util.StringTokenizer
	 */
	public static LongOpt getLongOpt(java.util.StringTokenizer param) 
	{
		String s;

		if ((s = TestClient.nextToken(param)) == null) 
		{
			return (LongOpt) IDLUtil.toOpt((Integer) null);
		} 
		else 
		{
			return (LongOpt) IDLUtil.toOpt(Integer.valueOf(s));
		}
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding StringSeqOpt object.
	 * Creation date: 9/7/2004
	 * @param param java.util.StringTokenizer
	 * @return StringSeqOpt
	 */
	public static StringSeqOpt getStringSeqOpt(
		java.util.StringTokenizer param) 
	{
		return (StringSeqOpt) IDLUtil.toOpt(
			StringSeqOpt.class,
			getStrings(param));
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding NetworkChannelCodeCombinationOpt object.
	 * Creation date: 9/7/2004
	 * @param param java.util.StringTokenizer
	 * @return NetworkChannelCodeCombinationOpt
	 */
	public static NetworkChannelCodeCombinationOpt getNetworkChannelCodeCombinationOpt(
		java.util.StringTokenizer param) 
	{

		return (NetworkChannelCodeCombinationOpt) IDLUtil.toOpt(
			NetworkChannelCodeCombinationOpt.class,
			getNetworkChannelCodeCombination(param));
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding NetworkChannelCodeCombination object.
	 * Creation date: 9/7/2004
	 * @param param java.util.StringTokenizer
	 * @return NetworkChannelCodeCombination
	 */
	public static NetworkChannelCodeCombination getNetworkChannelCodeCombination(
		java.util.StringTokenizer param) 
	{
		NetworkChannelCodeCombination ncc = new NetworkChannelCodeCombination();

		String code = TestClient.nextToken(param);

		if (code == null)
			return null;
		ncc.aNetworkChannelCode = code;
		ncc.aNetworkChannelCodeDescription = getStringOpt(param);
		ncc.aNetworkChannelInterface = getStringOpt(param);
		ncc.aSecondaryNetworkChannelInterface = getStringOpt(param);

		return ncc;
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding PlannedPortOpt object.
	 * Creation date: 9/7/2004
	 * @param param java.util.StringTokenizer
	 * @return PlannedPortOpt
	 */
	public static PlannedPortOpt getPlannedPortOpt(
		java.util.StringTokenizer param) 
	{
		return (PlannedPortOpt) IDLUtil.toOpt(
			PlannedPortOpt.class,
			getPlannedPort(param));
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding PlannedPort object.
	 * Creation date: 9/7/2004
	 * @param param java.util.StringTokenizer
	 * @return PlannedPort
	 */
	public static PlannedPort getPlannedPort(java.util.StringTokenizer param) 
	{
		PlannedPort pp = new PlannedPort();

		pp.aPort = getPort(param);
		pp.aPlanDate = getDate(param);

		return pp;
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding Port object.
	 * Creation date: 9/7/2004
	 * @param param java.util.StringTokenizer
	 * @return Port
	 */
	public static Port getPort(java.util.StringTokenizer param) 
	{
		Port port = new Port();

		port.aPortId = getStringOpt(param);
		port.aPortSpeed = getMeasurementOpt(param);
		port.aServiceType = getStringOpt(param);
		port.aResourceType = getStringOpt(param);
		port.aLocation = getLocationOpt(param);
		port.aSwitchId = getStringOpt(param);
		port.aFloor = getStringOpt(param);
		port.aAisle = getStringOpt(param);
		port.aBay = getStringOpt(param);
		port.aShelfNumber = getStringOpt(param);
		port.aCard = getStringOpt(param);
		port.aPortNumber = getStringOpt(param);

		port.aRelayRack = getStringOpt(param);
		port.aUnit = getStringOpt(param);
		port.aNodeType = getStringOpt(param);
		port.aNodeName = getStringOpt(param);
		port.aAccessCustTerminalLocation = getStringOpt(param);

		port.aConnectingFacilityAssignment =
			(StringSeqOpt) IDLUtil.toOpt((String[]) null);
		port.aSubscriberSideCFA =
			(SubscriberSideCFAOpt) IDLUtil.toOpt(
				SubscriberSideCFAOpt.class,
				null);
		port.aStatus = getStringOpt(param);

		return port;
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding PortOpt object.
	 * Creation date: 9/7/2004
	 * @param param java.util.StringTokenizer
	 * @return PortOpt
	 */
	public static PortOpt getPortOpt(java.util.StringTokenizer param) 
	{

		return (PortOpt) IDLUtil.toOpt(PortOpt.class, getPort(param));

	}

	/**
	 * Parse from a StringTokenizer and create a corresponding EiaDateOpt object.
	 * Creation date: 9/7/2004
	 * @param param java.util.StringTokenizer
	 * @return EiaDateOpt
	 */
	public static EiaDateOpt getDateOpt(java.util.StringTokenizer param) 
	{
		return (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, getDate(param));
	}

	/**
	* Prase a StringTokenizer to retrieve the CompositeObjectKey and returns a CompositeObjectKey.
	* @return CompositeObjectKey
	* @param param java.util.StringTokenizer
	*/
	public static CompositeObjectKey getCompositeObjectKey(
		java.util.StringTokenizer param) 
	{
        
		ObjectKey[] aObjectKeys= getObjectKeys(param);
		return new CompositeObjectKey(aObjectKeys);
        
	} 

	/**
	 * Parse from a StringTokenizer and create a corresponding OrderAction object.
	 * Creation date: 4/202005
	 * @param param java.util.StringTokenizer
	 * @return OrderAction
	 */
	public static OrderAction getOrderAction(StringTokenizer param) 
	{
		OrderAction aOrderAction = new OrderAction();
        
		aOrderAction.aOrder = getStringOpt(param);
		aOrderAction.aOrderActionId = getStringOpt(param);
		aOrderAction.aOrderActionType = getStringOpt(param);
		aOrderAction.aSubType = getStringOpt(param);
		if (!TestClient.nextToken(param).equalsIgnoreCase("end")) 
		{
			System.out.println("Missing terminator");
		}
        
		return aOrderAction;
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding OrderAction2 object.
	 * @param param java.util.StringTokenizer
	 * @return OrderAction2
	 * 
	 * @author Lira Galsim
	 */
	public static OrderAction2 getOrderAction2(StringTokenizer param) 
	{
		OrderAction2 aOrderAction = new OrderAction2();
        
		aOrderAction.aOrderId = getStringOpt(param);
		aOrderAction.aOrderActionId = getStringOpt(param);
		aOrderAction.aOrderActionType = getStringOpt(param);
		aOrderAction.aSubType = getStringOpt(param);
		if (!TestClient.nextToken(param).equalsIgnoreCase("end")) 
		{
			System.out.println("Missing terminator");
		}
        
		return aOrderAction;
	}
	
	/**
	 * Parse from a StringTokenizer and create a corresponding OrderAction2Opt object.
	 * @param param java.util.StringTokenizer
	 * @return OrderAction2Opt
	 * 
	 * @author Lira Galsim
	 */
	public static OrderAction2Opt getOrderAction2Opt(java.util.StringTokenizer param)
	{
		return (OrderAction2Opt) IDLUtil.toOpt(OrderAction2Opt.class, getOrderAction2(param));
	}	

	/**
	 * Parse from a StringTokenizer and create a corresponding ObjectPropertySeqOpt object.
	 * Creation date: 4/202005
	 * @param param java.util.StringTokenizer
	 * @return ObjectPropertySeqOpt
	 */
	public static ObjectPropertySeqOpt getObjectPropertySeqOpt(StringTokenizer param) 
	{

		return (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, objHelpers.getObjectProperties(param));
        
		//ObjectProperty[] props = objHelpers.getObjectProperties(param);
		//ObjectPropertyManager mgr = new ObjectPropertyManager(props);
		//return mgr.toObjectPropertySeqOpt();
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding DSLAMTransport object.
	 * @return DSLAMTransport
	 * @param param java.util.StringTokenizer
	 */
	public static DSLAMTransport getDSLAMTransport(
		java.util.StringTokenizer param) 
	{
		String aToken = TestClient.nextToken(param);
		if (aToken != null && aToken.equalsIgnoreCase("end"))
			return null;

		DSLAMTransport aDSLAMTransport = new DSLAMTransport();

		aDSLAMTransport.aId = (StringOpt) IDLUtil.toOpt(aToken);
		aDSLAMTransport.aModelNumber = getStringOpt(param);
		aDSLAMTransport.aEquipmentTargetId = getStringOpt(param);
		aDSLAMTransport.aPhysicalPort = getStringOpt(param);
		aDSLAMTransport.aLogicalPort = getStringOpt(param);
		aDSLAMTransport.aLocation = getLocationOpt(param);
		aDSLAMTransport.aVLANId = getStringOpt(param);
		aDSLAMTransport.aConnectivityStatus = getBooleanOpt(param);
		aDSLAMTransport.aCrossConnectStatus = getStringOpt(param);
        
		return aDSLAMTransport;
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding Network7450Switch object.
	 * @return Network7450Switch
	 * @param param java.util.StringTokenizer
	 */
	public static Network7450Switch getNetwork7450Switch(
		java.util.StringTokenizer param) 
	{
		String aToken = TestClient.nextToken(param);
		if (aToken != null && aToken.equalsIgnoreCase("end"))
			return null;

		Network7450Switch aNetwork7450Switch = new Network7450Switch();

		aNetwork7450Switch.aId = (StringOpt) IDLUtil.toOpt(aToken);
		aNetwork7450Switch.aEquipmentTargetId = getStringOpt(param);
		aNetwork7450Switch.aCLLI = getStringOpt(param);
		aNetwork7450Switch.aLogicalEgressPort = getStringOpt(param);
		aNetwork7450Switch.aPhysicalEgressPort = getStringOpt(param);

		return aNetwork7450Switch;
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding VOIP object.
	 * @return VOIP
	 * @param param java.util.StringTokenizer
	 */
	public static VOIP getVOIP(
		java.util.StringTokenizer param) 
	{
		String aToken = TestClient.nextToken(param);
		if (aToken != null && aToken.equalsIgnoreCase("end"))
			return null;

		VOIP aVOIP = new VOIP();

		aVOIP.aPrimaryTelephoneNumber = (StringOpt) IDLUtil.toOpt(aToken);
		aVOIP.aSecondaryTelephoneNumber = getStringOpt(param);
		aVOIP.aTelephoneNumbers = getStringSeqOpt(param);

		return aVOIP;
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding VOIPOpt object.
	 * @return VOIPOpt
	 * @param param java.util.StringTokenizer
	 */
	public static VOIPOpt getVOIPOpt(java.util.StringTokenizer param) 
	{

		return (VOIPOpt) IDLUtil.toOpt(VOIPOpt.class, getVOIP(param));
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding BooleanOpt object.
	 * @return BooleanOpt
	 * @param param java.util.StringTokenizer
	 */
	public static BooleanOpt getBooleanOpt(java.util.StringTokenizer param) 
	{
		BooleanOpt aBooleanOpt = new BooleanOpt();
		aBooleanOpt.theValue(getBoolean(param));
        
		return aBooleanOpt;
	}
    
	/**
	 * Parse from a StringTokenizer and create a corresponding AddressOpt object.
	 * @return AddressOpt
	 * @param param java.util.StringTokenizer
	 */
	public static AddressOpt getAddressOpt(java.util.StringTokenizer param) 
	{
		return (AddressOpt) IDLUtil.toOpt(AddressOpt.class, getAddress(param));
	}
    
	/**
	 * Parse from a StringTokenizer and create a corresponding getResidentialGatewayOpt object.
	 * @return getResidentialGatewayOpt
	 * @param param java.util.StringTokenizer
	 */
	public static ResidentialGatewayOpt getResidentialGatewayOpt(java.util.StringTokenizer param) 
	{
		return (ResidentialGatewayOpt) IDLUtil.toOpt(ResidentialGatewayOpt.class, getResidentialGateway(param));
	}
    
	/**
	 * Parse a StringTokenizer and create a corresponding ResidentialGateway object.
	 * @return ResidentialGateway
	 * @param param java.util.StringTokenizer
	 */ 
	public static ResidentialGateway getResidentialGateway(java.util.StringTokenizer param) 
	{
    	String aToken = TestClient.nextToken(param);
		if (aToken == null || aToken.equalsIgnoreCase("end"))
			return null;
    
		ResidentialGateway aRG = new ResidentialGateway();
		aRG.aDeviceId = getStringOpt(param);
		aRG.aSerialNumber = getStringOpt(param);
		aRG.aAssetTag = getStringOpt(param);
		aRG.aFirmwareVersionNumber = getStringOpt(param);
		aRG.aModelNumber = getStringOpt(param);
		aRG.aIPAddress = (NetworkAddressOpt) IDLUtil.toOpt(NetworkAddressOpt.class, getNetworkAddress(param));
		aRG.aMACAddress = (NetworkAddressOpt) IDLUtil.toOpt(NetworkAddressOpt.class, getNetworkAddress(param));
		aRG.aVendor = getStringOpt(param);      

		return aRG;
	}
    
	/**
	 * Parse a StringTokenizer and create a corresponding DSLAMTransportOpt object.
	 * Creation date: 05/09/2005     
	 * @param param java.util.StringTokenizer
	 * @return DSLAMTransportOpt
	 */
	public static DSLAMTransportOpt getDSLAMTransportOpt(java.util.StringTokenizer param)
	{
		return (DSLAMTransportOpt) IDLUtil.toOpt(DSLAMTransportOpt.class, getDSLAMTransport(param));
	}

	/**
	 * Parse a StringTokenizer and create a corresponding NetworkAddress object.
	 * Creation date: 05/09/2005     
	 * @param param java.util.StringTokenizer
	 * @return NetworkAddress
	 */
	public static NetworkAddress getNetworkAddress(java.util.StringTokenizer param) 
	{
    	String aToken = TestClient.nextToken(param);
		if (aToken == null || aToken.equalsIgnoreCase("end"))
			return null;
        
		NetworkAddress aNA = new NetworkAddress();
        
		aNA.aNetworkAddress = objHelpers.getObjectKey(param);   // aNetworkAddress
		aNA.aNetworkAddressType = TestClient.nextToken(param);  // aNetworkAddressType
		aNA.aServiceArea = getStringOpt(param);                 // aService Area
		aNA.aNetworkType = getStringOpt(param);                 // aNetworkType
		aNA.aProperties = getObjectPropertySeqOpt(param);       // aProperties
    
		return aNA;
	}
    
	/**
	 * Parse a StringTokenizer and create a corresponding NetworkAddressOpt object.
	 * Creation date: 05/09/2005     
	 * @param param java.util.StringTokenizer
	 * @return NetworkAddressOpt
	 */
	public static NetworkAddressOpt getNetworkAddressOpt(java.util.StringTokenizer param) 
	{
		return (NetworkAddressOpt) IDLUtil.toOpt(NetworkAddressOpt.class, getNetworkAddress(param));
	}

	/**
	 * Parse a StringTokenizer and create a corresponding Time object.
	 * Creation date: 05/10/2005
	 * @param param java.util.StringTokenizer
	 * @return Time
	 */ 
	public static Time getTime(java.util.StringTokenizer param) 
	{
		String aToken = TestClient.nextToken(param);
		if (aToken == null || aToken.equalsIgnoreCase("end"))
			return null;
        
		Time aTime = new Time();
		aTime.aEiaDate = getDate(param);
		aTime.aHour = getShort(param);
		aTime.aMinute = getShort(param);
		aTime.aSecond = getShort(param);
		aTime.aMilliSeconds = getShort(param);
		aTime.UTCOffset = getShort(param);
        
		return aTime;
	}
    
	/**
	 * Parse a StringTokenizer.
	 * Creation date: 05/10/2005
	 * @param param java.util.StringTokenizer
	 * @return short
	 */ 
	public static short getShort(java.util.StringTokenizer param) 
	{ 
    	return (Short.valueOf(TestClient.nextToken(param))).shortValue();   
	}
    
	/**
	 * Parse from a StringTokenizer and create a corresponding Address object.
	 * @return Address
	 * @param param java.util.StringTokenizer
	 */
	public static Address getAddress(java.util.StringTokenizer param) 
	{     
        Address add = null;
		String tag = TestClient.nextToken(param);
              
		if (tag.equals("FieldedAddress")) 
		{
			add = new Address();
			FieldedAddress fa = new FieldedAddress();
							
			fa.aRoute =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aBox =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aHouseNumberPrefix =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aHouseNumber =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aAssignedHouseNumber =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aHouseNumberSuffix =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aStreetDirection =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aStreetName =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aStreetThoroughfare =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aStreetNameSuffix =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aCity =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aState =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aPostalCode =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aPostalCodePlus4 =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aCounty =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aCountry =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aStructureType =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aStructureValue =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aLevelType =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aLevelValue =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aUnitType =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aUnitValue =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aOriginalStreetDirection =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aOriginalStreetNameSuffix =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aCassAddressLines =
				(StringSeqOpt) IDLUtil.toOpt(
					StringSeqOpt.class,
					getStrings(param));
			fa.aAuxiliaryAddressLines =
				(StringSeqOpt) IDLUtil.toOpt(
					StringSeqOpt.class,
					getStrings(param));
			fa.aCassAdditionalInfo =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aAdditionalInfo =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			fa.aBusinessName =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));

			//New fields added to fielded address IDL bundle 23 - not currently used.
			//Just setting default values as place holders
			StringOpt defaultStringOpt = new StringOpt();
			defaultStringOpt.__default();
			fa.aCountryCode = defaultStringOpt;
			fa.aCityCode = defaultStringOpt;
			fa.aServiceLocationName = defaultStringOpt;
		  	fa.aAddressId = defaultStringOpt;
			fa.aAliasName = defaultStringOpt;
			fa.aAttention = defaultStringOpt;
            
			add.aFieldedAddress(fa); 
		}
       
		if (tag.equals("UnfieldedAddress")) 
		{
			add = new Address();
			UnfieldedAddress ufa = new UnfieldedAddress();
			ufa.aBusinessName =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aAddressLines =
				(StringSeqOpt) IDLUtil.toOpt(StringSeqOpt.class, getStrings(param));
			ufa.aCity =(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aState =(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aPostalCode = (StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aPostalCodePlus4 = (StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aCounty =(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aCountry =(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aStructureType = (StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aStructureValue = (StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aLevelType =(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aLevelValue =(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aUnitType =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aUnitValue =
				(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aAdditionalInfo =
					(StringOpt) IDLUtil.toOpt(TestClient.nextToken(param));
                
			ufa.aCountryCode = (StringOpt)IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aCityCode = (StringOpt)IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aServiceLocationName = (StringOpt)IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aAddressId = (StringOpt)IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aAliasName = (StringOpt)IDLUtil.toOpt(TestClient.nextToken(param));
			ufa.aAttention = (StringOpt)IDLUtil.toOpt(TestClient.nextToken(param));
            
			add.aUnfieldedAddress(ufa); 
		}
        
		return add;  
	} 

	/**
	 * Method getOpticalLineTerminalOpt.
	 * @param param
	 * @return OpticalLineTerminalOpt
	 */
	public static OpticalLineTerminalOpt getOpticalLineTerminalOpt(java.util.StringTokenizer param)
	{       
		return (OpticalLineTerminalOpt) IDLUtil.toOpt(OpticalLineTerminalOpt.class, getOpticalLineTerminal(param) );
	}
    
	/**
	 * Method getOpticalLineTerminal.
	 * @param param
	 * @return OpticalLineTerminal
	 */
	public static OpticalLineTerminal getOpticalLineTerminal(java.util.StringTokenizer param)
	{
		String aToken = TestClient.nextToken(param);
		if (aToken == null || aToken.equalsIgnoreCase("end"))
			return null;
        
		OpticalLineTerminal aOLT = new OpticalLineTerminal();
        
		aOLT.aAction = getStringOpt(param);
		aOLT.aId = getStringOpt(param);
		aOLT.aOLTLocation = getLightSpeedLocationOpt(param);
		aOLT.aStatus = getStringOpt(param);
		aOLT.aCategory = getStringOpt(param);
		aOLT.aSite = getStringOpt(param);
		aOLT.aVendor = getStringOpt(param);
		aOLT.aLineUp = getStringOpt(param);
		aOLT.aFrameBay = getStringOpt(param);
		aOLT.aShelf = getStringOpt(param);
		aOLT.aShelfHECI = getStringOpt(param);
		aOLT.aSlot = getStringOpt(param);
		aOLT.aCardHECI = getStringOpt(param);
		aOLT.aPort = getStringOpt(param);
		aOLT.aTemplateName = getStringOpt(param);
		aOLT.aChannelization = getStringOpt(param);
		aOLT.aBandwidth = getStringOpt(param);
		aOLT.aConnector = getStringOpt(param);
		aOLT.aModelNumber = getStringOpt(param);
		aOLT.aEquipmentTargetId = getStringOpt(param);
		aOLT.aCarrierLocationAddress = getLightSpeedLocationOpt(param);
		aOLT.aVLANId = getStringOpt(param);
		aOLT.aVirtualPathId = getStringOpt(param);
		aOLT.aVirtualChannelId = getStringOpt(param);
		aOLT.aLogicalPort = getStringOpt(param);
            
		return aOLT;
	}
    
	/**
	 * Method getLightSpeedLocationOpt. builds a Location object with LocationId that was extracted from test case string.  
	 * @param param
	 * @return LocationOpt
	 */
	public static LocationOpt getLightSpeedLocationOpt(java.util.StringTokenizer param) 
	{
		String aLocationId =  TestClient.nextToken(param);
		Location aLocation = getLocation(param);
        
		if(aLocationId != null && aLocation != null )
			aLocation.aLocationId.theValue(aLocationId);
            
		return (LocationOpt) IDLUtil.toOpt(LocationOpt.class,aLocation);
	}
    
    
	/**
	 * Method getOpticalNetworkTerminalOpt.
	 * @param param
	 * @return OpticalNetworkTerminalOpt
	 */
	public static OpticalNetworkTerminalOpt getOpticalNetworkTerminalOpt(java.util.StringTokenizer param)
	{       
		return (OpticalNetworkTerminalOpt) IDLUtil.toOpt(OpticalNetworkTerminalOpt.class, getOpticalNetworkTerminal(param) );
	}
    
	/**
	 * Method getOpticalNetworkTerminal.
	 * @param param
	 * @return OpticalNetworkTerminal
	 */
	public static OpticalNetworkTerminal getOpticalNetworkTerminal(java.util.StringTokenizer param)
	{
		String aToken = TestClient.nextToken(param);
		if (aToken == null || aToken.equalsIgnoreCase("end"))
			return null;
        
		OpticalNetworkTerminal aONT = new OpticalNetworkTerminal();
		aONT.aId = getStringOpt(param);
		aONT.aModelNumber = getStringOpt(param);
		aONT.aAccessId = getStringOpt(param);
		aONT.aPort = getStringOpt(param);
		aONT.aIndex = getStringOpt(param);
		aONT.aVLANId = getStringOpt(param);
		aONT.aSerialNumber = getStringOpt(param);
            
		return aONT;
	}
	/**
	 * @param st
	 * @return
	 */
	public static ProductSubscription[] getProductSubscription(StringTokenizer st) 
	{
		// TODO Auto-generated method stub
		ProductSubscription aObj = null;
		ArrayList aList = new ArrayList();
		Stack     aStack = new Stack();
		String sToken = null;
		ProductSubscription[] pSubs = null;
		Location lo = null;
		LocationOpt loOpt = null;
		// create a new ProductSub object and add it into the Stack
		//aStack.push(new ProductSubscription(null,null,null,null,null,null,null,null,null,null,null));
		try
		{
		//Testing part...
		//aStack.push(new ProductSubscription(null,null,null,null,null,null,null,(new ProductSubscription[0]),null,null,null));
		
		lo = new Location(((StringOpt)IDLUtil.toOpt((String) "Testing")),(new ProviderLocationProperty[0]));
		loOpt = (LocationOpt)IDLUtil.toOpt(LocationOpt.class,lo);

		aStack.push(new ProductSubscription(new ObjectKey("", ""),"",null,null,null,null,null,(new ProductSubscription[0]),loOpt,loOpt,null));

		sToken = TestClient.nextToken(st);
		while ( (null != sToken) && (!sToken.equalsIgnoreCase("end")) )
		{
			if ( sToken.equalsIgnoreCase("productSubscription"))
			{
				// create a new ProductSub object and add it into the Stack
				aStack.push(new ProductSubscription(new ObjectKey("", ""),"",null,null,null,null,null,(new ProductSubscription[0]),loOpt,loOpt,null));
			}
			else if ( (sToken.startsWith("aProductSubscriptionID")) )
			{
				aObj = (ProductSubscription) aStack.pop();
				aObj.aProductSubscriptionID = getObjectKey(st);
				aStack.push(aObj);
			}
			else if ( (sToken.startsWith("aProductID")) )
			{
				aObj = (ProductSubscription) aStack.pop();
				aObj.aProductID = TestClient.nextToken(st);
				aStack.push(aObj);
			}
			else if ( (sToken.startsWith("aProductName")) )
			{
				aObj = (ProductSubscription) aStack.pop();
				aObj.aProductName = getStringOpt(st);
				aStack.push(aObj);
		  	}
			else if ( (sToken.startsWith("aBillingAccountNumber")))
			{
				com.sbc.eia.idl.types.CompositeObjectKey objKey = null;
				com.sbc.eia.idl.types.CompositeObjectKeyOpt objKeyOpt = null;
				objKey = getCompositeObjectKey(st);
				objKeyOpt = (com.sbc.eia.idl.types.CompositeObjectKeyOpt)IDLUtil.toOpt(com.sbc.eia.idl.types.CompositeObjectKeyOpt.class,objKey);
				aObj = (ProductSubscription) aStack.pop();
				aObj.aBillingAccountID = (com.sbc.eia.idl.types.CompositeObjectKeyOpt)objKeyOpt;
				aStack.push(aObj);
			}
			else if ( (sToken.startsWith("aContractId")))
			{
				aObj = (ProductSubscription) aStack.pop();
				aObj.aContractID = getStringOpt(st);
				aStack.push(aObj);
			}
			else if ( (sToken.startsWith("aFirstInstallationDate")))
			{
				aObj = (ProductSubscription) aStack.pop();
				aObj.aFirstInstallationDate = getDateOpt(st);
				aStack.push(aObj);
				}
			else if ( (sToken.startsWith("aProductSubscriptionStatus")))
		  	{
				aObj = (ProductSubscription) aStack.pop();
				aObj.aProductSubscriptionStatus = getStringOpt(st);
				aStack.push(aObj);
		  	}
			else if ( (sToken.startsWith("aALocation")))
			{
				aObj = (ProductSubscription) aStack.pop();
				aObj.aALocation = getLocationOpt(st);
				aStack.push(aObj);
			}
			else if ( (sToken.startsWith("aZLocation")))
			{
				aObj = (ProductSubscription) aStack.pop();
				aObj.aZLocation = getLocationOpt(st);
				aStack.push(aObj);
			}
			else if ( (sToken.startsWith("Properties")))
			{
				aObj = (ProductSubscription) aStack.pop();
				aObj.aProperties = getObjectPropertySeqOpt(st);
				aList.add(aObj);
	//			aObj = (ProductSubscription) aStack.pop();
	//			aObj.aProperties = getObjectPropertySeqOpt(st);
	//			ProductSubscription aTempObj = null;
	//			aTempObj = (ProductSubscription) aStack.pop();
	//			aTempObj.aProductSubscriptions = new ProductSubscription[1];
	//			aTempObj.aProductSubscriptions[0] = aObj;
	//			aList.add(aTempObj);
			}

			sToken = TestClient.nextToken(st);

			} // while loop
		} 
		catch (Exception e)
		{
		   System.out.println(e.toString());
		}
		return (ProductSubscription[]) aList.toArray(new ProductSubscription[aList.size()]);
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding NetworkType object.
	 * @param param java.util.StringTokenizer
	 * @return NetworkType
	 */
	public static NetworkType getNetworkType(java.util.StringTokenizer param) 
	{
		String aToken = TestClient.nextToken(param);
		NetworkType aNetworkTypeChoice = new NetworkType();

		if (aToken.equals(NetworkTypeValues.FTTN)) 
		{
			Fttn aFttn = new Fttn();
			aFttn.aDSLAM = getDSLAMTransportOpt(param);
			aFttn.aSegments = getCopperSegmentSeqOpt(param);

			aNetworkTypeChoice.aFttn(aFttn); 
		}

		if (aToken.equals(NetworkTypeValues.FTTP)) 
		{
			Fttp aFttp = new Fttp();
			aFttp.aOpticalLineTerminal = getOpticalLineTerminalOpt(param);
			aFttp.aOpticalNetworkTerminal = getOpticalNetworkTerminalOpt(param);
			aFttp.aSegments = getFiberSegmentSeqOpt(param);

			aNetworkTypeChoice.aFttp(aFttp); 
		}
        
		return aNetworkTypeChoice;
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding CopperSegmentSeqOpt object.
	 * @param param java.util.StringTokenizer
	 * @return CopperSegmentSeqOpt
	 */
	public static CopperSegmentSeqOpt getCopperSegmentSeqOpt(java.util.StringTokenizer param) 
	{
		return (CopperSegmentSeqOpt) IDLUtil.toOpt(CopperSegmentSeqOpt.class, getCopperSegments(param));
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding FiberSegmentSeqOpt object.
	 * @param param java.util.StringTokenizer
	 * @return FiberSegmentSeqOpt
	 */
	public static FiberSegmentSeqOpt getFiberSegmentSeqOpt(java.util.StringTokenizer param) 
	{
		return (FiberSegmentSeqOpt) IDLUtil.toOpt(FiberSegmentSeqOpt.class, getFiberSegments(param));
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding CopperSegment[] object.
	 * @param param java.util.StringTokenizer
	 * @return CopperSegment[]
	 */
	public static CopperSegment[] getCopperSegments(StringTokenizer param)
	{
        String aToken = null;
		aToken = TestClient.nextToken(param);
		if (aToken.equalsIgnoreCase("CopperSegments"))
		{
			int aTotalSegments = Integer.parseInt(TestClient.nextToken(param));
			CopperSegment[] aCopperSegment = new CopperSegment[aTotalSegments];
			// initialize
			for (int i = 0 ; i < aTotalSegments; i++) 
			{
				aCopperSegment[i] = new CopperSegment();
			}
			// format
			aToken = TestClient.nextToken(param);
			while (aToken != null && !aToken.equalsIgnoreCase("end")) 
			{
				if (aToken.equalsIgnoreCase("Segment")) 
				{
					String aSegmentNumber = TestClient.nextToken(param);                
					int aNumber = Integer.parseInt(aSegmentNumber);                
					aCopperSegment[aNumber-1].aSegmentNumber = IDLUtil.toOpt((String) aSegmentNumber);
					aCopperSegment[aNumber-1].aTerminalId = getStringOpt(param);
					aCopperSegment[aNumber-1].aTerminalType = getStringOpt(param);
					aCopperSegment[aNumber-1].aTerminalLocation = getStringOpt(param);
					aCopperSegment[aNumber-1].aInBindingPost = getStringOpt(param);
					aCopperSegment[aNumber-1].aOutBindingPost = getStringOpt(param);
					aCopperSegment[aNumber-1].aOutPairColor = getStringOpt(param);
					aCopperSegment[aNumber-1].aInCableName = getStringOpt(param);
					aCopperSegment[aNumber-1].aInCablePair = getStringOpt(param);
				}
				aToken = TestClient.nextToken(param);
			}
			return aCopperSegment;
		}
		return null;
	}

	/**
	 * Parse from a StringTokenizer and create a corresponding FiberSegment[] object.
	 * @param param java.util.StringTokenizer
	 * @return FiberSegment[]
	 */
	public static FiberSegment[] getFiberSegments(StringTokenizer param) 
	{
		String aToken = null;
		aToken = TestClient.nextToken(param);
		if (aToken.equalsIgnoreCase("FiberSegments")) 
		{
			int aTotalSegments = Integer.parseInt(TestClient.nextToken(param));
			FiberSegment[] aFiberSegment = new FiberSegment[aTotalSegments];
			// initialize
			for (int i = 0 ; i < aTotalSegments; i++)
			{
				aFiberSegment[i] = new FiberSegment();
			}
			// format
			aToken = TestClient.nextToken(param);
			while (aToken != null && !aToken.equalsIgnoreCase("end"))
			{
				if (aToken.equalsIgnoreCase("Segment")) 
				{
					String aSegmentNumber = TestClient.nextToken(param);                
					int aNumber = Integer.parseInt(aSegmentNumber);                
					aFiberSegment[aNumber-1].aSegmentNumber = IDLUtil.toOpt((String) aSegmentNumber);
					aFiberSegment[aNumber-1].aTerminalId = getStringOpt(param);
					aFiberSegment[aNumber-1].aTerminalType = getStringOpt(param);
					aFiberSegment[aNumber-1].aNetworkPort = getStringOpt(param);
					aFiberSegment[aNumber-1].aAccessPort = getStringOpt(param);
					aFiberSegment[aNumber-1].aInFiberCable = getStringOpt(param);
					aFiberSegment[aNumber-1].aInFiberStrand = getStringOpt(param);
				}
				aToken = TestClient.nextToken(param);
			}
			return aFiberSegment;
		}
		return null;
	}
	
   
	/**
	 * Method getProductSubscriptionSeqOpt
	 * @param param
	 * @return ProductSubscriptionSeqOpt
	 */
	public static ProductSubscriptionSeqOpt getProductSubscriptionSeqOpt(java.util.StringTokenizer param)
	{       
		return (ProductSubscriptionSeqOpt) IDLUtil.toOpt(ProductSubscriptionSeqOpt.class, getProductSubscription(param) );
	}
	
	/**
	 * Method getName.
	 * @param param
	 * @return Name
	 */
	public static Name getName(java.util.StringTokenizer param)
	{       
		String aToken = TestClient.nextToken(param);
		if (aToken == null || aToken.equalsIgnoreCase("end"))
			return null;
        
		Name aName = new Name();
		aName.aNameHandle = TestClient.nextToken(param);
		aName.aProperties = getObjectProperties(param);		
            
		return aName;
	}
	
	/**
	 * Method getNameOpt.
	 * @param param
	 * @return NameOpt
	 */
	public static NameOpt getNameOpt(java.util.StringTokenizer param)
	{       
		return (NameOpt) IDLUtil.toOpt(NameOpt.class, getName(param) );
	}
	
}

