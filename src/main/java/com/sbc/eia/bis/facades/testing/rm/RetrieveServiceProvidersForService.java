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
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn;
import com.sbc.eia.idl.rm.RmFacadeSOABuilder;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveServiceProvidersForServiceRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveServiceProvidersForServiceRequestBISMsg;
import com.sbc.eia.idl.rm.bishelpers.RetrieveServiceProvidersForServiceReturnBisHelper;
//import com.sbc.eia.idl.types.ObjectHandle;
import com.sbc.eia.idl.types.ObjectKey;
//import com.sbc.eia.idl.types.bishelpers.ObjectHandleBisHelper;
//import com.sbc.eia.idl.types.bishelpers.ObjectHandleSeqBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectKeyBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectKeySeqBisHelper;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.service.rmproxy.RmProxyHelper;
import com.sbc.gwsvcs.service.rmproxy.RmProxyTest;
import com.sbc.gwsvcs.service.rmproxy.exceptions.RmProxyException;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBWriter;

import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;

/**
 * RetrieveServiceProvidersForResource test driver.
 * Creation date: (3/21/01 11:43:13 AM)
 * @author: Sam Lok - Local
 */
public class RetrieveServiceProvidersForService extends TestRMCaseBase {
	private ObjectKey aServiceHandle = null;
	private ObjectKey[] aServiceTypeHandles = null;
	private RetrieveServiceProvidersForServiceReturn result = null;
	private Hashtable props = null;	
	
//FOR RM PROXY
	private com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.BisContext aProxyContext = null;
	private com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKey aProxyServiceHandle = null ;
	private com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKey[] aProxyerviceTypeHandles = null ;	
	private com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn resultProxy = null;
	private ObjectPropertyProxyManager objProxyHelper = null;
	
/**
 * RetrieveServiceProvidersForResource constructor comment.
 */
public RetrieveServiceProvidersForService() {
	super();

	// set method name
	setMyMethodName( "RetrieveServiceProvidersForService" ) ;

}
/**
 * Insert the method's description here.
 * Creation date: (5/25/01 2:01:11 PM)
 */
protected void displayResult()
{
	if( result != null )
	{
		TestClient.log.printLog("OUTPUT DATA: " + 
			(new RetrieveServiceProvidersForServiceReturnBisHelper(result)).toString() );
	}
}
/**
 * Insert the method's description here.
 * Creation date: (5/25/01 4:51:31 PM)
 */
protected void displayProxyResult()
{
	if( resultProxy != null )
	{
		String strOutput = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.bishelpers.RetrieveServiceProvidersForServiceReturnBisHelper( resultProxy ) .toString();
		TestClient.log.printLog("Output for RetrieveServiceProvidersForService: " + strOutput);				
		System.out.println("Output for RetrieveServiceProvidersForService: " + strOutput);
	}
}
/**
 * Insert the method's description here.
 * Creation date: (5/25/01 2:01:11 PM)
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
	com.sbc.eia.bis.facades.rm.transactions.RetrieveServiceProvidersForService.RetrieveServiceProvidersForService 
	    retSPS = new com.sbc.eia.bis.facades.rm.transactions.RetrieveServiceProvidersForService.RetrieveServiceProvidersForService(props);
	
	result = retSPS.execute( (aContext!=null)?aContext:TestClient.myBisContext,
										  					   aServiceHandle,
															   aServiceTypeHandles,
															   new Logger() ) ;
}

protected void execute(javax.ejb.EJBObject remote)
throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, RemoteException, ObjectNotFound, DataNotFound
{
	displayInput();
	result = ((Rm)remote).retrieveServiceProvidersForService( (aContext!=null)?aContext:TestClient.myBisContext,
										  					   aServiceHandle,
															   aServiceTypeHandles ) ;
}
/*  PROXY CHANGE
 *
 * Insert the method's description here.
 * Creation date: (5/25/01 4:51:31 PM)
 *
 */
protected void execute(Hashtable props)
throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
{
	System.out.println("RetrieveServiceProvidersForService:: execute() ::");
	com.sbc.gwsvcs.access.vicuna.EventResultPair response = null;
	RmProxyTest aRmProxyTest = new RmProxyTest();
	try{
		RmProxyHelper helper = new RmProxyHelper(props, aRmProxyTest);
	
		if(aProxyContext == null){						
			aProxyContext = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.BisContext( objProxyHelper.getTypesObjectProperty(TestClient.myObjProp));									
		}
		
		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForService request = 
			new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForService(aProxyContext,
			aProxyServiceHandle, 
			aProxyerviceTypeHandles );

		response = helper.retrieveServiceProvidersForServiceInput(null, null, 0, request);
		System.out.println( "Received event: " + response.getEventNbr() );
		
		resultProxy = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn)response.getTheObject();
		
	}
	catch (RmProxyException e)
	{
		System.out.println("RmProxyException: " + e.getExceptionCode() + " " + e.getMessage());
	}
	catch (ServiceException e)
	{
		System.out.println("ServiceException: " + e.getExceptionCode() + " " + e.getMessage());
		e.printStackTraces();
	}	
	
	
}
/**
 * Insert the method's description here.
 * Creation date: (5/25/01 2:01:11 PM)
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
			if ( tag.equals( "BisContext" ) ) aContext = objHelpers.getBisContext( st ) ;
//			if ( tag.equals( "ServiceHandle" ) ) aServiceHandle = objHelpers.getObjectHandle( st ) ;
//			if ( tag.equals( "ServiceTypeHandles" ) ) aServiceTypeHandles = objHelpers.getObjectHandles( st ) ;
			if ( tag.equals( "ServiceHandle" ) ) aServiceHandle = objHelpers.getObjectKey( st ) ;
			if ( tag.equals( "ServiceTypeHandles" ) ) aServiceTypeHandles = objHelpers.getObjectKeys( st ) ;

		}
	}
	catch( Throwable t )
	{	// nothing to do
	}
	
}

protected void init(java.lang.String paramList, java.lang.String propertiesFile)
{
	
	File file;
	FileInputStream fis = null;
	
	try{
		
		file = new File(propertiesFile.trim());
		fis = new FileInputStream(file);
	
    } catch(Exception fe) { 
		System.out.println("Properties File Not Found: " + fe.getMessage() );
	}
	
	//get properties file
	
	Properties p = new Properties();
			
	try{		
		p.load(fis);
	}
	
	catch(IOException ie)
	{System.out.println("IOException reading properties file." ) ;	}
	
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
	System.out.println("RetrieveServiceProvidersForService:: init()" );
	objProxyHelper = new ObjectPropertyProxyManager() ;	
	StringTokenizer st = new StringTokenizer( paramList, TestClient.DEFAULT_DELIMITER );
	
	try
	{
		while ( st.hasMoreElements() )
		{
			String tag = TestClient.nextToken( st ) ;
		
		 	if ( tag.equals( "BisContext" ) ) 
		 		aProxyContext = objProxyHelper.getProxyBisContext( st ) ;
		 			
			if ( tag.equals( "ServiceHandle" ) )
				aProxyServiceHandle = objProxyHelper.getProxyObjectKey( st ) ;
				
			if ( tag.equals( "ServiceTypeHandles" ) )
				aProxyerviceTypeHandles = objProxyHelper.getObjectKeys( st ) ;
			
		}
	}
	catch( Throwable t )
	{	// nothing to do
	}
}
/**
 * Insert the method's description here.
 * Creation date: (5/25/01 4:51:31 PM)
 */
protected void initMessage(java.lang.String paramList, Hashtable props)
	{
		TestClient.log.printLog("MDB is not implemented for this method");		
	}
protected void displayInput()
{
	TestClient.log.printLog("INPUT DATA:aContext<" + (new BisContextBisHelper((aContext!=null)?aContext:TestClient.myBisContext)).toString() + ">");
//	if(aServiceHandle != null)	TestClient.log.printLog("INPUT DATA:aServiceHandle<" + (new ObjectHandleBisHelper(aServiceHandle)).toString() + ">");
//	if(aServiceTypeHandles != null ) TestClient.log.printLog("INPUT DATA:aServiceTypeHandles<" + (new ObjectHandleSeqBisHelper(aServiceTypeHandles)).toString() + ">");
	if(aServiceHandle != null)	TestClient.log.printLog("INPUT DATA:aServiceHandle<" + (new ObjectKeyBisHelper(aServiceHandle)).toString() + ">");
	if(aServiceTypeHandles != null ) TestClient.log.printLog("INPUT DATA:aServiceTypeHandles<" + (new ObjectKeySeqBisHelper(aServiceTypeHandles)).toString() + ">");
	
}


/* (non-Javadoc)
 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String, java.util.Properties)
 */

protected void initSoapMessage(String paramList, Properties props) {
 	// parse the argument from the test case.
		this.init(paramList);

		this.displayInput();

		sendMessage(props,
			RmFacadeSOABuilder._retrieveServiceProvidersForService,
			createMessage());
	}

	/**
	* createMessage
	*/
	private String createMessage() {
		String xml = null;
		try {

			_retrieveServiceProvidersForServiceRequest request =
				new _retrieveServiceProvidersForServiceRequest(
					this.aContext,
					this.aServiceHandle,
					this.aServiceTypeHandles);

			_retrieveServiceProvidersForServiceRequestBISMsg requestMsg =
				new _retrieveServiceProvidersForServiceRequestBISMsg(request);

			MMarshalObject msgs = requestMsg;
			//Encode them into XML
			xml = VAXBWriter.encode(msgs);

			//add SOAP TAGS
			xml =
				addSOAPTagstoXML(
					xml,
					RmFacadeSOABuilder._retrieveServiceProvidersForService);

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
