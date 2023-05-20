// $Id: SelfTest.java,v 1.1 2006/06/27 23:30:20 sl2917 Exp $
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
//#      © 2006-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author      | Notes
//# ----------------------------------------------------------------------------
//# 06/27/2006  Sam Lok      Creation.
//############################################################################

package com.sbc.eia.bis.facades.testing.rm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.ejb.EJBObject;

import com.sbc.eia.bis.facades.rm.ejb.Rm;
import com.sbc.eia.bis.facades.testing.TestClient;
import com.sbc.eia.bis.facades.testing.objHelpers;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.rm.PingReturn;
import com.sbc.eia.idl.rm.RmFacadeSOABuilder;
import com.sbc.eia.idl.rm.RmFacadePackage._pingRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._pingRequestBISMsg;
import com.sbc.eia.idl.rm.bishelpers.PingReturnBisHelper;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBWriter;


/**
 * Ping test driver.
 * Creation date: (3/21/01 11:43:13 AM)
 * @author: Sam Lok - Local
 */
public class Ping extends TestRMCaseBase {
	private PingReturn result = null;
	private Hashtable props = null; 

//FOR RM PROXY
	private com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.BisContext aProxyContext = null;
	private String aProxyResourceHandle = null ;
	private com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.ObjectKey[] aProxyServiceTypeHandles = null ;
//	private com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.PingReturn resultProxy = null;
	private ObjectPropertyProxyManager objProxyHelper = null;

/**
 * RetrieveServiceProvidersForResource constructor comment.
 */
public Ping() {
	super();

	// set method name
	setMyMethodName( "ping" ) ;

}
/**
 * Insert the method's description here.
 * Creation date: (5/25/01 9:33:31 AM)
 */
protected void displayResult()
{
	if( result != null )
	{
		TestClient.log.printLog("OUTPUT DATA: " + 
			(new PingReturnBisHelper(result)).toString() );
	}
}
/**
 * Insert the method's description here.
 * Creation date: (5/25/01 4:51:31 PM)
 */
protected void displayProxyResult()
{
/*
	if( resultProxy != null )
	{
		String strOutput = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.bishelpers.RetrieveServiceProvidersForResourceReturnBisHelper( resultProxy ) .toString();
		TestClient.log.printLog("Output for Ping: " + strOutput);				
		System.out.println("Output for Ping: " + strOutput);
	}
*/
}

/**
 * Insert the method's description here.
 * Creation date: (5/25/01 9:33:31 AM)
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

	com.sbc.eia.bis.facades.rm.transactions.Ping.Ping 
	    ping = new com.sbc.eia.bis.facades.rm.transactions.Ping.Ping(props);
	
	try{
		result = ping.execute( (aContext!=null)?aContext:TestClient.myBisContext ) ;
	}
	catch ( MultipleExceptions me ) {  }

}

protected void execute(EJBObject remote)
throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, RemoteException, DataNotFound
{
	displayInput();
	try
	{
		result = ((Rm)remote).ping( (aContext!=null)?aContext:TestClient.myBisContext ) ;
	}
	catch ( MultipleExceptions me ) {  }
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
/*
	System.out.println("RetrieveServiceProvidersForResource:: execute() ::");
	com.sbc.gwsvcs.access.vicuna.EventResultPair response = null;
	RmProxyTest aRmProxyTest = new RmProxyTest();
	try{
		RmProxyHelper helper = new RmProxyHelper(props, aRmProxyTest);
	
		if(aProxyContext == null){						
			aProxyContext = new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.BisContext( objProxyHelper.getTypesObjectProperty(TestClient.myObjProp));									
		}
		
		com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForResource  request = 
			new com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForResource(aProxyContext,
			aProxyResourceHandle, 
			aProxyServiceTypeHandles );
			
		response = helper.retrieveServiceProvidersForResourceInput(null, null, 0, request);
		System.out.println( "Received event: " + response.getEventNbr() );
		
		resultProxy = (com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn)response.getTheObject();
		
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
*/	
	
}

/**
 * Insert the method's description here.
 * Creation date: (5/25/01 9:33:31 AM)
 */
protected void init(String paramList)
{
	// parse the argument from the test case
	StringTokenizer st = new StringTokenizer( paramList, TestClient.DEFAULT_DELIMITER );
	try
	{
		while ( st.hasMoreElements() )
		{
			String tag = TestClient.nextToken( st ) ;
			if ( tag.equals( "BisContext" ) ) aContext = objHelpers.getBisContext( st ) ;
		
		}
	}
	catch( Throwable t )
	{	// nothing to do
	}
	
}

protected void init(String paramList, String propertiesFile)
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
	System.out.println("Ping:: init()" );
	objProxyHelper = new ObjectPropertyProxyManager() ;	
	StringTokenizer st = new StringTokenizer( paramList, TestClient.DEFAULT_DELIMITER );
	
	try
	{
		while ( st.hasMoreElements() )
		{
			String tag = TestClient.nextToken( st ) ;
		
		 	if ( tag.equals( "BisContext" ) )
		 		aProxyContext = objProxyHelper.getProxyBisContext( st ) ;
		 		
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
	
	TestClient.log.printLog( "INPUT DATA: aContext<" + (new BisContextBisHelper((aContext!=null)?aContext:TestClient.myBisContext)).toString() + ">" );
		
}
/* (non-Javadoc)
 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String)
 */
protected void initSoapMessage(String paramList) {
    TestClient.log.printLog("Soap proxy is not implemented for this method");
	
}

/* (non-Javadoc)
 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String, java.util.Properties)
 */
protected void initSoapMessage(String paramList, Properties props) {
  	// parse the argument from the test case.
 		this.init(paramList);

		this.displayInput();

		sendMessage(props,
			RmFacadeSOABuilder._ping,
			createMessage());

	}

	/**
	* createMessage
	*/
	private String createMessage() {
		String xml = null;

		try {

			_pingRequest request =
				new _pingRequest(this.aContext);

			_pingRequestBISMsg requestMsg =
				new _pingRequestBISMsg(request);

			MMarshalObject msgs = requestMsg;
			//Encode them into XML
			xml = VAXBWriter.encode(msgs);

			//add SOAP TAGS
			xml =
				addSOAPTagstoXML(
					xml,
					RmFacadeSOABuilder._ping);

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
