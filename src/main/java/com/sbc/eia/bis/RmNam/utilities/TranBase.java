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
//#       (C) SBC Services, Inc 2001-2003.  All Rights Reserved.
//#
//#	History :
//#	Date      | Author        | Notes
//#	----------------------------------------------------------------------------
//#	7/2001		Sam Lok			Creation.
//#	01/09/02	Sam Lok			3.0.0:  Updated to use new IDL bundles.
//#	01/14/02	Sam Lok			3.0.0:  Updated to use new non-versioned IDL bundles.
//#	09/12/02	Sam Lok			3.1.0:  Added a new TranBase::validateBisContext() to 
//#											validate specified component being present
//#											in BisContext.aProperties[].
//#	01/20/03	Sam Lok			3.2.0:  RM64452: added new setCallerContext to set the correct callContext.
//# 07/16/04	Stevan Dunkin	9.0.0	RM128789: IDPassword migration
//# 12/08/2004  Stevan Dunkin   RM 10   Added System propertie to use JDOM 1.0beta9 jars
//# 6/11/2007   Tulsi Kosuri		 	Eliminated deprecated warnings
 


package com.sbc.eia.bis.RmNam.utilities;
import java.text.*;

import javax.ejb.EJBException;
import java.rmi.*;
import com.sbc.vicunalite.api.MException;
import java.io.*;

import javax.naming.*;
import java.util.*;

import javax.sql.*;
import java.sql.*;

import com.sbc.eia.idl.types.*;
import com.sbc.logging.*;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.exception_types.*;

import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.eia.bis.framework.*;
import com.sbc.eia.bis.framework.logging.LogEventId ;
import com.sbc.bccs.idl.helpers.* ;
import com.sbc.bccs.utilities.* ;

import com.sbc.eia.bis.authorization.ClientAuthorization;
import com.sbc.eia.bis.authorization.AuthorizationException;

import com.sbc.eia.bis.RmNam.components.RoutingOverrideParser;


/**
 * TranBase can be extended by all the EJBs to take advantage of the common utility method provided here.
 * Creation date: (1/16/01 11:16:39 AM)
 * @author: Sam Lok - Local
 */
public class TranBase implements com.sbc.bccs.utilities.Utility {
	protected BisContext callerContext 			= null ;
	protected LogAssistant theLogAssistant 		= null;
	
	public String PROPERTIES_LOCATION;

	public InitialContext initialContext;
	public String portNumber		 				= null;
	public String hostName 						= null;
	public static final String DEFAULT_PROTOCOL 	= "IIOP";
	public static final String DEFAULT_HOST 		= "localhost";

	//Exception Information
	public static final int INVALID_DATA 		= ExceptionType.INVALID_DATA ;
	public static final int SYSTEM_FAILURE 		= ExceptionType.SYSTEM_FAILURE ;
	public static final int BUSINESS_VIOLATION 	= ExceptionType.BUSINESS_VIOLATION ;
	public static final int ACCESS_DENIED 		= ExceptionType.ACCESS_DENIED ;
	public static final int OBJECT_NOT_FOUND 	= ExceptionType.OBJECT_NOT_FOUND ;
	public static final int DATA_NOT_FOUND 		= ExceptionType.DATA_NOT_FOUND ;
	public static final int NOT_IMPLEMENTED 		= ExceptionType.NOT_IMPLEMENTED ;

 	// Log Event ID
 	public final static String LOG_ENTRY				= LogEventId.ENTRY ;
 	public final static String LOG_EXIT				= LogEventId.EXIT ;
 	public final static String LOG_EXCEPTION			= LogEventId.EXCEPTION ;
 	public final static String LOG_ERROR				= LogEventId.ERROR ;
 	public final static String LOG_INFO_LEVEL_1		= LogEventId.INFO_LEVEL_1 ;
 	public final static String LOG_INFO_LEVEL_2		= LogEventId.INFO_LEVEL_2 ;
 	public final static String LOG_DEBUG_LEVEL_1		= LogEventId.DEBUG_LEVEL_1 ;
 	public final static String LOG_DEBUG_LEVEL_2		= LogEventId.DEBUG_LEVEL_2 ;
 	public final static String LOG_INPUT_DATA		= LogEventId.INPUT_DATA ;
 	public final static String LOG_OUTPUT_DATA		= LogEventId.OUTPUT_DATA ;
 	public final static String LOG_FAILURE			= LogEventId.FAILURE ;
	public final static String LOG_PARTIAL_FAILURE	= LogEventId.PARTIAL_FAILURE ;
	public final static String LOG_REMOTE_CALL		= LogEventId.REMOTE_CALL ;
	public final static String LOG_REMOTE_RETURN		= LogEventId.REMOTE_RETURN ;
	public final static String LOG_BISPERF			= LogEventId.BISPERF ;
	public final static String LOG_AUDIT_TRAIL		= LogEventId.AUDIT_TRAIL ;
	public final static String LOG_STACK_TRACE		= LogEventId.STACK_TRACE ;

 	// Some other junk
 	public final static String PRE					= LogEventId.PRE ;
 	public final static String POST					= LogEventId.POST ;
 
	public Hashtable PROPERTIES;

	public Logger myLogger = null;
	private javax.sql.DataSource dsHome;
/**
 * TranBase constructor: do nothing.
 */
public TranBase() {
	super();
    System.setProperty("VAXB_JDOM_VER", "1.0beta9");
}
/**
 * Insert the method's description here.
 * Creation date: (5/30/01 1:04:00 PM)
 * @param param java.util.Hashtable
 */
public TranBase(Hashtable param)
{
	setPROPERTIES( param ) ;
}
/**
 * Returns a formatted string suitable for constructing InvalideData exception for missing data.
 * Creation date: (9/14/01 3:59:06 PM)
 * @return java.lang.String
 * @param aTopLevelClassContainingTheOffendingField java.lang.Class
 * @param aFieldName java.lang.String
 */
public static String formatInvalidData( Class aTopLevelClassContainingTheOffendingField,
										String aFieldName )
{
	return "Missing Data: " +
		   aTopLevelClassContainingTheOffendingField.getName() + ":" +
		   aFieldName + ". "
		   ;
}
/**
 * Returns a formatted string suitable for constructing InvalideData exception.
 * Creation date: (9/14/01 3:59:06 PM)
 * @return java.lang.String
 * @param aTopLevelClassContainingTheOffendingField java.lang.Class
 * @param aFieldName java.lang.String
 * @param aValue java.lang.String
 * @param aReason java.lang.String
 */

public static String formatInvalidData( Class aTopLevelClassContainingTheOffendingField,
										String aFieldName,
										String aValue,
										String aReason )
{
	return "Invalid Data: " +
		   aTopLevelClassContainingTheOffendingField.getName() + ":" +
		   aFieldName + "<" + aValue + ">. Reason: " + aReason
		   ;
}
/**
 * getEnv() retrieve the value of the requested envName from NRMPROPS property list.
 * Creation date: (9/27/00 11:28:53 AM)
 * @return java.lang.String
 * @param param java.lang.String
 */
public String getEnv(String envName) 
{
	String envVal = (String)getPROPERTIES().get( envName ) ;
	return ( envVal == null ? envVal : envVal.trim() ) ;
}

/**
 * Insert the method's description here.
 * Creation date: (9/17/02 11:09:24 AM)
 */
public Logger getMyLogger() {
	// get a Logger?
	if ( myLogger == null )
		myLogger = new Logger( getPROPERTIES() ) ;

		return myLogger;

	}
/**
 * Test Method
 *
 * This just returns the name of the implemting class.
 */
public String getName() {
	return this.getClass().getName();
}
/**
 * getPROPERTIES() returns the properties list.
 * Creation date: (5/9/00 2:51:15 PM)
 * @return java.util.Hashtable
 */
public java.util.Hashtable getPROPERTIES() {
	return PROPERTIES ;
}
/**
 * Returns the content of PROPERTIES_LOCATION.
 * Creation date: (5/9/00 11:01:16 AM)
 * @return java.lang.String
 */
public java.lang.String getPROPERTIES_LOCATION() {
	return PROPERTIES_LOCATION;
}
/**
 * This init() is used to read the properties file.  If TranBase is extended by an EJB, the EJB's ejbCreate() should be overloaded to call init().
 * Creation date: (5/15/00 11:28:27 AM)
 * @param mySessionCtx javax.ejb.SessionContext
 * @exception RemoteException.
 */
protected void init(javax.ejb.SessionContext mySessionCtx) throws EJBException
{
	String location = "";
	Properties props;

	try{
		/***
		 * 
		 *    New logic to get properties file
		 * 
		 **/

		props = new java.util.Properties();
		 
		System.out.println("Processing Initial Context ....");
		 
		Context xContext = new InitialContext();
		Context myEnv = (Context)xContext.lookup("java:comp/env");
		location = (java.lang.String) myEnv.lookup("PROP_FILE_LOCATION");
		
		if(location == null)
		{
			System.out.println("Throwing NamingException due to missing PROP_FILE_LOCATION" ) ;
			throw new NamingException("Property file name not found in environment");
		}
	}
	catch(NamingException ne){
		System.out.println("Throwing RemoteException due to Environment");
		throw new EJBException("Error retrieving Property File Name::" + ne.getMessage());
	}

		
	try{
		//get properties file
		Properties p = PropertiesFileLoader.read( location, null );	
		
		//Decrypt password with given key
		Encryption enc = new Encryption();
		p.put("jdbcPASSWORD", enc.decodePassword(p.getProperty("OSS_PUBLIC_KEY"), p.getProperty("jdbcPASSWORD")));
		//p.put("predictorPASSWORD", enc.decodePassword(key.getProperty("key"), p.getProperty("predictorPASSWORD")));
			  	
		this.PROPERTIES = (Hashtable)p ;		
	}
	catch(IOException ie)
	{
		System.out.println("Throwing RemoteException due to IOException reading properties file." ) ;
		throw new EJBException("Error Loading Properties::" + ie.getMessage());
	}

//	this.DTD = getEnv("DTD_BASE") + getEnv("DTD_NRM") ;

}
/**
 * log() will use the BisLogger API to log a message.
 * Creation date: (1/8/01 3:53:39 PM)
 * @param eventId java.lang.String
 * @param message java.lang.String
 */
public void log(String eventId, String message)
{
	
	getMyLogger();
	
	// the beginning of a transaction	
	if ( eventId.equals( LOG_ENTRY ) )
		myLogger.start( callerContext ) ;

	// do the log
	myLogger.log( eventId, message ) ;

	// end the log context?
//	if ( eventId.equals( LOG_EXIT ) )
//		myLogger.end() ;
}
/**
logFailure() calls BisLoger to log failure
*/

public void log(String eventId, String code, String text, String origination)	
{
	// ExceptionThrower.throwException( callerContext, code, text, origination, severity, this ) ;

	getMyLogger();
	
		myLogger.log(eventId, code, text, origination);
	
}
/**
 * Log the BISPERF format.
 * Creation date: (1/8/01 3:53:39 PM)
 * @param eventSubType java.lang.String
 * @param originMethod java.lang.String
 * @param destApp java.lang.String
 * @param destMethod java.lang.String
 * @param eventId java.lang.String
 * @exception com.sbc.eia.bis.BusinessViolation The exception description.
 * @exception com.sbc.eia.bis.InvalidData The exception description.
 * @exception com.sbc.eia.bis.SystemFailure The exception description.
 * @exception com.sbc.eia.bis.AccessDenied The exception description.
 */
public void log(
	String eventId,
	String eventSubType,
	String originMethod,
	String destApp,
	String destMethod) {

	
	if (eventId.equals(LOG_BISPERF))
		log(
			eventId,
			BISPERFLogger.formatMsg(
				getEnv("BIS_NAME"),
				eventSubType,
				originMethod,
				destApp,
				destMethod));
	 else if (eventId.equals( LOG_REMOTE_CALL) ||eventId.equals( LOG_REMOTE_RETURN))
	 {
		 getMyLogger();
	 	myLogger.log(eventId, eventSubType, originMethod, destApp, destMethod);
	 }
}
/**
 * Insert the method's description here.
 * Creation date: (1/16/01 2:12:47 PM)
 * @param newNRMPROPS java.util.Hashtable
 */
public void setPROPERTIES(java.util.Hashtable newPROPERTIES) {
	PROPERTIES = newPROPERTIES;
}
/**
 * throwException() construct the error message and throw the appropriate exception.
 * Creation date: (5/15/00 11:28:27 AM)
 * @param type int
 * @param code java.lang.String
 * @param text java.lang.String
 * @param origination String
 * @param severity Severity
 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
 */
public void throwException(String code, String text, String origination, Severity severity)	
throws InvalidData, SystemFailure, BusinessViolation, AccessDenied, NotImplemented, ObjectNotFound, DataNotFound 
{
	
	ExceptionThrower.throwException( callerContext, code, text, origination, severity, this ) ;

}
/**
 * This throwException() is overloaded to accept a Vicuna MException.
 * Creation date: (5/15/00 11:28:27 AM)
 * @param type int
 * @param code java.lang.String
 * @param text java.lang.String
 * @param origination String
 * @param severity Severity
 * @param exception com.sbc.vicuna.api.MException
 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
 */
public void throwException(String code, String text, String origination, Severity severity, MException exception)
throws InvalidData, SystemFailure, BusinessViolation, AccessDenied, NotImplemented, ObjectNotFound, DataNotFound 
{
	
	// put the stack trace into a buffer and log it
	//
	ByteArrayOutputStream stackTraceBuf = new ByteArrayOutputStream() ;
	exception.printStackTraces( new PrintWriter( stackTraceBuf, true ) ) ;
		
	log( LOG_DEBUG_LEVEL_2, stackTraceBuf.toString() ) ;

	// now call the good old throwException()
	//
	ExceptionThrower.throwException( callerContext, code, text, origination, severity, this ) ;
	
		
}
/**
 * throwException() is overloaded to print the stack trace before calling the basic handleException().
 * Creation date: (5/15/00 11:28:27 AM)
 * @param code java.lang.String
 * @param text java.lang.String
 * @param origination String
 * @param severity Severity
 * @param exception java.lang.Exception
 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
 */
public void throwException(String code, String text, String origination, Severity severity, Exception exception)
throws InvalidData, SystemFailure, BusinessViolation, AccessDenied, NotImplemented, ObjectNotFound, DataNotFound 
{
	
	ExceptionThrower.throwException( callerContext, code, text, origination, severity, exception, this ) ;

	}
/**
 * Validates and return the aValue of the specified component being present in BisContext.aProperties[]..
 * Creation date: (4/26/01 8:43:01 AM)
 * @param aContextOPM	ObjectPropertyManager
 * @param bisName		java.lang.String
 * @param typeToCheck	java.lang.String
 * @param errorCode		java.lang.String
 * @param errorText		java.lang.String NOT USED
 * @exception InvalidData: An input parameter contained invalid data.
 * @exception AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception BusinessViolation: The attempted action violates a business rule.
 * @exception SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception NotImplemented: The method has not been implemented.
 * @exception ObjectNotFound: The desired domain object could not be found.
 * @exception DataNotFound: No data found.
 */
public String validateBisContext( ObjectPropertyManager aContextOPM,
								String typeToCheck,
								String errorCode,
								String errorText)
throws 	InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound 
{
	// BisContext aContext
	String propertyValue = null;
	if ( ( propertyValue = aContextOPM.getValue( typeToCheck ) ) == null ||
		 propertyValue.trim().length() < 1 )
		throwException(	errorCode,
						formatInvalidData( BisContext.class, "aContext.aProperties["+typeToCheck+"]" ),
						getEnv( "BIS_NAME" ), Severity.UnRecoverable ) ;
	return propertyValue.trim() ;
}
/**
 * Validates BisContext is not null and BisContext.aProperties is not null.
 * Creation date: (4/26/01 8:43:01 AM)
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param bisName java.lang.String
 * @param errorCode java.lang.String
 * @param errorText java.lang.String NOT USED
 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
 */
public void validateBisContext(BisContext aContext, String bisName, String errorCode, String errorText)
	throws 	InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound 
{
	// See if BisContext is null
	if (aContext == null)
		throwException( errorCode,
						formatInvalidData( BisContext.class,  "BisContext" ),
					    bisName, Severity.UnRecoverable ) ;

	// See if BisContext.aProperties is null
	if (aContext.aProperties == null)
		throwException( errorCode,
						formatInvalidData( BisContext.class,  "BisContext.aProperties" ),
						bisName, Severity.UnRecoverable ) ;

	// Check and set callerContext based on client's BisContext						
	try
	{
		setCallerContext( aContext ) ;
	}
	catch( NullPointerException ne )
	{
		throwException( errorCode,
						formatInvalidData( BisContext.class,  "BisContext.aProperties[LoggingInformation]" ),
						bisName, Severity.UnRecoverable ) ;
	}
}

/**
 * Assert the callContext attribute with newCallerContext.
 * Creation date: (1/15/03 8:43:01 AM)
 * @param newCallerContext BisContext
 */
public void setCallerContext(BisContext newCallerContext)
{
	BisContextManager theBisContextManager = new BisContextManager(newCallerContext);
	
	if ( theLogAssistant == null )
		theLogAssistant = LogAssistantFactory.create( getEnv( "BIS_NAME" ), getEnv("BIS_VERSION") ) ;
	
	if ( theBisContextManager.getLoggingInformationString() != null &&
		 theLogAssistant.setCorrID( theBisContextManager.getLoggingInformationString() )==1 )
	{
		callerContext = newCallerContext;
	}
	else
	{
		theLogAssistant.genNewCorrID();
		theBisContextManager.setLoggingInformationString( theLogAssistant.getCorrID() );
		callerContext = theBisContextManager.getBisContext();
	}
	
}

public static BisContext setBisContextAppl(BisContext aContext, String appl)
	{
		
		BisContext localContext = new BisContext(aContext.aProperties);
		
		BisContextManager aBisManager = new BisContextManager(localContext);
		
		aBisManager.setApplication(appl.trim());
		return aBisManager.getBisContext();
		
		
	}

/** 
 *  validateClient:  Calls ClientAuthorization class to
 * 					 authenticate the client
 * @param    	ObjectPropertyManager aContextOPM, 
 *				String errorCode,
 *				String systemFailureCode			
 * @throws		InvalidData: An input parameter contained invalid data.
 * 				AccessDenied: Access to the specified domain object or information is not allowed.
 * 				BusinessViolation: The attempted action violates a business rule.
 * 				SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * 				NotImplemented: The method has not been implemented.
 * 				ObjectNotFound: The desired domain object could not be found.
 * 				DataNotFound: No data found.	
 * @author      vc7563 Jun 22, 2004 3:46:02 PM
 */
public void validateClient( 
	ObjectPropertyManager aContextOPM, 
	String groupId,
	String errorCode,
	String systemFailureCode)
	throws 
	InvalidData, 
	AccessDenied, 
	BusinessViolation, 
	SystemFailure, 
	NotImplemented, 
	ObjectNotFound, 
	DataNotFound
	{
		try {	
			int index = this.getClass().getName().lastIndexOf('.');
				
			if ( ClientAuthorization.isAuthorized(				
				this.getClass().getName().substring(++index), 			// method Name
				aContextOPM.getValue( BisContextProperty.APPLICATION ),	// application
				aContextOPM.getValue( BisContextProperty.BUSINESSUNIT),	// business unit
				aContextOPM.getValue( BisContextProperty.SERVICECENTER),// service center
				groupId,												// group id 
				getPROPERTIES(),										// hash table
				this ) == false )										// logger
			{
				throwException( errorCode,
					"Unauthorized user <" + 
					aContextOPM.getValue( BisContextProperty.APPLICATION ) + "> <" +
					aContextOPM.getValue( BisContextProperty.BUSINESSUNIT) + "> <" +
					aContextOPM.getValue( BisContextProperty.SERVICECENTER) + "> <" +
					groupId + ">",
					getEnv("BIS_NAME"), Severity.UnRecoverable);		
			}
		}
		catch (AuthorizationException e )
		{
			throwException(
				systemFailureCode,
				e.getMessage(),
				getEnv("BIS_NAME"),
				Severity.UnRecoverable);
		} 
	}
	
	
	/**
 * Validates the BisContext for routing values.  Will throw 
 * AccessDenied exception if routing overrides are specified in 
 * non-supported environments or if client value specified in the
 * tuple does not match the application value in the BixContext.
 * @param bisContext com.sbc.eia.idl.bis_types.BisContext
 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
 */
public void validateRoutingOverride(BisContext aContext)
	throws
        InvalidData,
        AccessDenied,
        BusinessViolation,
        SystemFailure,
        NotImplemented,
        ObjectNotFound,
        DataNotFound
{
	
	java.util.Hashtable properties = this.getPROPERTIES();
	
	try
	{
		RoutingOverrideParser routingOverrideParser = new RoutingOverrideParser(properties, getMyLogger().getBisLogger());
		routingOverrideParser.validateRoutingOverride(aContext);
	}
	catch(OverrideParserSystemFailureException e)
	{
		
		throwException(
			ExceptionCode.ERR_NAM_OVERRIDE_PARSER_FAILURE,
			"Override Parser System Failure Exception: "
				+ e.getMessage(),
			(String) properties.get("BIS_NAME"),
			Severity.UnRecoverable);
	}
	catch(OverrideParserAccessDeniedException e)
	{
		
		throwException(
			ExceptionCode.ERR_NAM_OVERRIDE_PARSER_ACCESS_DENIED,
			"Routing Override Access Denied Exception: "+
			e.getMessage(),
			(String) properties.get("BIS_NAME"),
			Severity.UnRecoverable);
	}

    
  }

	
}