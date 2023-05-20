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
//# Date         | Author              | Notes
//# ----------------------------------------------------------------------------
//# 05/09/2005   Manjula Goniguntla    Creation.
//#	06/06/2005	 mk3198	               Modifications for soap via ldap.
//#	01/16/2006	 mk2394	               Modifications for LS2 Support.

package com.sbc.eia.bis.facades.testing.rm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.facades.rm.ejb.Rm;
import com.sbc.eia.bis.facades.testing.TestClient;
import com.sbc.eia.bis.facades.testing.objHelpers;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.bishelpers.AddressOptBisHelper;
import com.sbc.eia.idl.rm.PublishAutoDiscoveryResultsReturn;
import com.sbc.eia.idl.rm.RmFacadeSOABuilder;
import com.sbc.eia.idl.rm.RmFacadePackage._publishAutoDiscoveryResultsRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._publishAutoDiscoveryResultsRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishAutoDiscoveryResultsResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishAutoDiscoveryResultsResponseBISMsg;
import com.sbc.eia.idl.rm.bishelpers.PublishAutoDiscoveryResultsReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.bishelpers.OrderActionBisHelper;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.sm_ls_types.bishelpers.ProductSubscriptionSeqBisHelper;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.CompositeObjectKeyBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringOptBisHelper;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBWriter;



/**
 * ActivateResourcesForDSLService test driver.
 * Creation date: (5/9/05)
 * @author mg5629
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PublishAutoDiscoveryResults extends TestRMCaseBase {
	private PublishAutoDiscoveryResultsReturn result = null;
	private String aCustomerTransportId;
    private CompositeObjectKey aBillingAccountNumber;
	private String aDestination;
	private AddressOpt aServiceAddress;
	private ProductSubscription[] aProductSubscriptions;
	private StringOpt aTelephoneNumber;
	private String aAssignedProductId;
	private OrderAction aOrderAction;
	private ObjectPropertySeqOpt aProperties;
   // private Logger aLogger = new Logger();
    private Hashtable props = null;
	private Logger aLogger = null;

    /**
    * PublishAutoDiscoveryResults constructor comment.
    */
    public PublishAutoDiscoveryResults() {
	    super();

	    // set method name
	    setMyMethodName( "PublishAutoDiscoveryResults" ) ;

    }
   
   /**
   * Insert the method's description here.
   * Creation date: (5/9/05)
   */
   protected void displayResult()
   {
	if (isXMLTestData) {
		_publishAutoDiscoveryResultsResponse res = new _publishAutoDiscoveryResultsResponse();
		String responseXML = null;

		if (result != null) {
			res.aPublishAutoDiscoveryResultsReturn(result);
			responseXML =
				convertMMarshalObjectToXML(
					new _publishAutoDiscoveryResultsResponseBISMsg(res));
		} else {
			if (responseError != null) {
				if (responseError instanceof SystemFailure)
					res.aSystemFailure((SystemFailure) responseError);
				if (responseError instanceof InvalidData)
					res.aInvalidData((InvalidData) responseError);
				if (responseError instanceof AccessDenied)
					res.aAccessDenied((AccessDenied) responseError);
				if (responseError instanceof BusinessViolation)
					res.aBusinessViolation((BusinessViolation) responseError);
				if (responseError instanceof NotImplemented)
					res.aNotImplemented((NotImplemented) responseError);
				if (responseError instanceof ObjectNotFound)
					res.aObjectNotFound((ObjectNotFound) responseError);
				if (responseError instanceof DataNotFound)
					res.aDataNotFound((DataNotFound) responseError);

				responseXML =
					convertMMarshalObjectToXML(
						new _publishAutoDiscoveryResultsResponseBISMsg(res));
			}
		}

		if (responseXML != null)
			TestClient.log.printLog("OUTPUT DATA: \n" + responseXML);
	} else if (result != null) {
		TestClient.log.printLog(
			"OUTPUT DATA: "
				+ (new PublishAutoDiscoveryResultsReturnBisHelper(result)).toString());

	}

   }


   /**
   * Insert the method's description here.
   * Creation date: (5/9/05)
   */

   protected void displayProxyResult()
   {
	
   }


   /**
   * Insert the method's description here.
   * Creation date: (5/9/05)
   * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData The exception description.
   * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied The exception description.
   * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation The exception description.
   * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure The exception description.
   * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented The exception description.
   * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound The exception description.
   * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
   */
   protected void execute()
       throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
   {
	   displayInput();
				
	   com.sbc.eia.bis.facades.rm.transactions.PublishAutoDiscoveryResults.PublishAutoDiscoveryResults 
	       actRFDSLS = new com.sbc.eia.bis.facades.rm.transactions.PublishAutoDiscoveryResults.PublishAutoDiscoveryResults(props);
	    
	   result = actRFDSLS.execute((aContext!=null)?aContext:TestClient.myBisContext, 
								   this.aCustomerTransportId,
							       this.aBillingAccountNumber,
								   this.aServiceAddress, 
								   this.aProductSubscriptions,
								   this.aTelephoneNumber,
								   this.aAssignedProductId,
								   this.aOrderAction,
								   this.aProperties,
								   aLogger) ;
   }

   protected void execute(javax.ejb.EJBObject remote)
       throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, RemoteException, ObjectNotFound, DataNotFound
   {
	   displayInput();
	   result = ((Rm)remote).publishAutoDiscoveryResults((aContext!=null)?aContext:TestClient.myBisContext, 
									                      this.aCustomerTransportId,
									                      this.aBillingAccountNumber,
									                      this.aServiceAddress, 
								                          this.aProductSubscriptions,
									                      this.aTelephoneNumber,
								                          this.aAssignedProductId,
									                      this.aOrderAction,
								                          this.aProperties) ;
   }


   /*  PROXY CHANGE
   *
   * Insert the method's description here.
   * Creation date: (5/9/05)
   *
   */

   protected void execute(Hashtable props)
       throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
   {
	
   }


   /**
   * Insert the method's description here.
   * Creation date: (5/9/05)
   */
   protected void init(java.lang.String paramList)
   {
	
	   // parse the argument from the test case
	   StringTokenizer st = new StringTokenizer( paramList, TestClient.DEFAULT_DELIMITER );
	   try
	   {
		
		   while ( st.hasMoreElements() )
		   {
			
			   String tag = TestClient.nextToken( st ) ;
			   
			   	if (tag.equals("XML")) {
					isXMLTestData = true;
					String xmlFileName = TestClient.nextToken(st);
					TestClient.log.printLog("XML Test Data File :" + xmlFileName);
					buildIDLObjectsFromXML(xmlFileName);
					return;
				}
			   
			   if ( tag.equals( "BisContext" ) ) aContext = objHelpers.getBisContext(st) ;
			   if ( tag.equals( "CustomerTransportId" ) ) aCustomerTransportId = TestClient.nextToken(st);
			   if ( tag.equals( "aBillingAccountNumber" ) ) aBillingAccountNumber = objHelpers.getCompositeObjectKey(st);
			   if ( tag.equals( "ServiceAddress" ) ) aServiceAddress = objHelpers.getAddressOpt(st);
				if ( tag.equals( "ProductSubscriptions" ))
				{
				  aProductSubscriptions = objHelpers.getProductSubscription(st);
				  System.out.println("Number of products = "+aProductSubscriptions.length);		 
				}
			   if ( tag.equals( "TelephoneNumber" ) ) aTelephoneNumber = objHelpers.getStringOpt(st) ;
			   if ( tag.equals( "AssignedProductId" ) ) aAssignedProductId = TestClient.nextToken(st);
			   if ( tag.equals( "OrderAction" ) ) aOrderAction = objHelpers.getOrderAction(st);
			   if ( tag.equals( "Properties" ) ) aProperties = objHelpers.getObjectPropertySeqOpt( st );
			
																	
		   }
	   }
	   catch( Throwable t )
	   {	// nothing to do
	   }
   }


   protected void init(java.lang.String paramList, java.lang.String propertiesFile)
   {
	   // parse the argument from the test case
	
	   System.out.println("propertiesFile = " + propertiesFile);
	
	   File file;
	   FileInputStream fis = null;
	
	   try
	   {
		    file = new File(propertiesFile.trim());
		    fis = new FileInputStream(file);
	
	   }catch(Exception fe) 
	   { 
	   	    System.out.println("Properties File Not Found: " + fe.getMessage() );
	   }
	
	   //get properties file
	
	   Properties p = new Properties();
			
	   try
	   {
		    p.load(fis);
		   
	   }catch(IOException ie)
	   {
		    System.out.println("IOException reading properties file." ) ;
	   }
	
	   // Set up properties for specific Bis
	
	   this.props = (Hashtable)p;		
	   init(paramList)	;
	
   }

  
   /*
   * PROXY CHANGE
   *
   */

   protected void init(java.lang.String paramList, Hashtable props )
   {
	
   }

   /**
   * Insert the method's description here.
   * Creation date: (5/9/05)
   */
   protected void initMessage(java.lang.String paramList, Hashtable props)
   {
				
   }

    /**
     * method: buildIDLObjectsFromXML(String)
     */
	public void buildIDLObjectsFromXML(String xmlFileName) throws Exception {
		//Read XML test data file
		requestXML = readFile(xmlFileName, true);

		//Convert XML to IDL object
		MMarshalObject aRequest = convertXMLToMMarshalObject(requestXML);

		//build request IDL objects from MMarshalObject
		_publishAutoDiscoveryResultsRequestBISMsg aRequestMsg =
		(_publishAutoDiscoveryResultsRequestBISMsg) aRequest;
		
		aContext = aRequestMsg.value.aContext;
		aCustomerTransportId = aRequestMsg.value.aCustomerTransportId;
		aBillingAccountNumber = aRequestMsg.value.aBillingAccountNumber;
		aServiceAddress = aRequestMsg.value.aServiceAddress;
		aProductSubscriptions = aRequestMsg.value.aProductSubscriptions;
		aTelephoneNumber = aRequestMsg.value.aTelephoneNumber;
		aAssignedProductId = aRequestMsg.value.aAssignedProductId;
		aOrderAction  = aRequestMsg.value.aOrderAction ;
		aProperties = aRequestMsg.value.aProperties;
	}


   protected void displayInput()
   {
		if (isXMLTestData) {
			TestClient.log.printLog("INPUT DATA: \n" + requestXML);
			return;
		}


	   TestClient.log.printLog("INPUT DATA:aContext<" + (new BisContextBisHelper((aContext!=null)?aContext:TestClient.myBisContext)).toString() + ">");
	   if(aCustomerTransportId != null) TestClient.log.printLog ("INPUT DATA:aCustomerTransportId<"+ aCustomerTransportId + ">");
	   if(aBillingAccountNumber != null) TestClient.log.printLog("INPUT DATA:aBillingAccountNumber<" + (new CompositeObjectKeyBisHelper(aBillingAccountNumber)).toString() + ">");
	   if(aDestination != null) TestClient.log.printLog ("INPUT DATA:aDestination<"+ aDestination + ">");
	   if(aServiceAddress != null) TestClient.log.printLog("INPUT DATA:aServiceAddress<" + (new AddressOptBisHelper(aServiceAddress)).toString() + ">");
       if(aProductSubscriptions != null) TestClient.log.printLog("INPUT DATA:aProductSubscriptions<" + (new ProductSubscriptionSeqBisHelper(aProductSubscriptions)).toString() + ">");

	   if(aTelephoneNumber != null) TestClient.log.printLog("INPUT DATA:aTelephoneNumber<" + (new StringOptBisHelper(aTelephoneNumber)).toString() + ">");
	   if(aAssignedProductId != null) TestClient.log.printLog ("INPUT DATA:aAssignedProductId<"+ aAssignedProductId + ">");
	   if(aOrderAction != null) TestClient.log.printLog("INPUT DATA:aOrderAction<" + (new OrderActionBisHelper(aOrderAction)).toString() + ">");
	   if(aProperties != null) TestClient.log.printLog("INPUT DATA:aProperties<" + (new ObjectPropertySeqOptBisHelper(aProperties)).toString() + ">");
		 
   }
 
   /* (non-Javadoc)
   * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String)
   */
   protected void initSoapMessage(String paramList)
   {
       TestClient.log.printLog("Soap proxy is not implemented for this method");
	
   }

  
   /********************SOAP PROXY************************/
   /* (non-Javadoc)
   * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String, java.util.Properties)
   */
   protected void initSoapMessage(String paramList, Properties props) {
          
       // parse the argument from the test case.
		this.init(paramList);

		this.displayInput();

		sendMessage(props,
			RmFacadeSOABuilder._publishAutoDiscoveryResults,
			createMessage());
   }

   /**
   * createMessage
   */
   private String createMessage() {
		String xml = null;
		try {

			_publishAutoDiscoveryResultsRequest request =
				new _publishAutoDiscoveryResultsRequest(
					this.aContext,
					this.aCustomerTransportId,
					this.aBillingAccountNumber,
					this.aServiceAddress, 
					this.aProductSubscriptions,
					this.aTelephoneNumber,
					this.aAssignedProductId,
					this.aOrderAction,
					this.aProperties) ; 

			_publishAutoDiscoveryResultsRequestBISMsg requestMsg =
				new _publishAutoDiscoveryResultsRequestBISMsg(request);

			MMarshalObject msgs = requestMsg;
			//Encode them into XML
			xml = VAXBWriter.encode(msgs);

			//add SOAP TAGS
			xml =
				addSOAPTagstoXML(
					xml,
					RmFacadeSOABuilder._publishAutoDiscoveryResults);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		return xml;
   }
   
 protected void initSoapHttpMessage( String ParamList, Properties props )
	{
		TestClient.log.printLog ("SOAP/HTTP not implemented" );
	}  
	
}
