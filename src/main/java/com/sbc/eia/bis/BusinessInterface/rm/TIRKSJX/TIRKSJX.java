//$Id: TIRKSJX.java,v 1.3.2.2 2012/09/27 09:38:21 js7440 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      � 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;

import java.io.ByteArrayOutputStream;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;

/**
 * Contains the logic for connecting to TIRKSJX API
 * 
 * @author js7440
 */
public class TIRKSJX implements TIRKSJXConstants
{
	public static final String TIRKSJX_Name = "TIRKSJX";
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	private String TIRKSJX_Version = null;
	private Properties TIRKSJXProperties = null;
	
	
	/**
	 * Constructor for class TIRKS
	 * 
	 * @param aUtility
	 * @param aProperties
	 */
	public TIRKSJX( 
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties) 
	{
		utility = aUtility;
		properties = aProperties;
		TIRKSJX_Version = (String ) aProperties.get("TIRKSJX_SERVICE_VERSION");
		String ruleFile = (String) properties.get("EXCEPTION_BUILDER_TIRKS_RULE_FILE");
	}
	
	/**
	 * open a TIRKSJX Session
	 * 
	 * @return Socket
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	private java.net.Socket openTIRKSJxSession() 
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::openTIRKSJxSession()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		java.net.SocketAddress socketAddress = null;
		java.net.Socket tirksjxSocket = null;
		String serverIPaddr = null;
		int serverPort = 0;
		int serverTimeout = 0;
		
		try
		{
			if(TIRKSJXProperties == null)
			{
				loadTIRKSJxProperties();
			}
			serverIPaddr = TIRKSJXProperties.getProperty("TIRKSJX.HOST").trim();
			serverPort = Integer.parseInt(TIRKSJXProperties.getProperty("TIRKSJX.PORT").trim());
			serverTimeout = Integer.parseInt(TIRKSJXProperties.getProperty("TIRKSJX.TIMEOUT").trim());
			
			socketAddress = new InetSocketAddress(serverIPaddr, serverPort);
			tirksjxSocket = new Socket();

			System.out.println("TIRKSJX openTIRKSJxSession: Attempting socket connection: " + socketAddress);
			tirksjxSocket.connect(socketAddress);
			tirksjxSocket.setSoTimeout(serverTimeout);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in creating a TIRKSJX socket " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return tirksjxSocket;		
	}	
	
	/**
	 * builds the TIRKSJX Open Session request
	 * 
	 * @param dataCenter
	 * @return JXAPIImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.impl.JXAPIImpl buildTIRKSJxOpenSessionRequest(
			String dataCenter)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::buildTIRKSJxOpenSessionRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.impl.JXAPIImpl aOpenSessionRequest = null;
		
		try
		{
			if(TIRKSJXProperties == null)
			{
				loadTIRKSJxProperties();
			}
			aOpenSessionRequest = new OpenSessionRequestHelper(utility, properties).buildOpenSessionRequest(TIRKSJXProperties, dataCenter);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in building TIRKSJX Open Session Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return aOpenSessionRequest;
	}
	
	/**
	 * build the TIRKSJC Close Session request
	 * 
	 * @param sessionID
	 * @return JXAPIImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.impl.JXAPIImpl buildTIRKSJxCloseSessionRequest(
			String sessionID)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::buildTIRKSJxCloseSessionRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.impl.JXAPIImpl aCloseSessionRequest = null;
		
		try
		{
			if(TIRKSJXProperties == null)
			{
				loadTIRKSJxProperties();
			}
			aCloseSessionRequest = new CloseSessionRequestHelper(utility, properties).buildCloseSessionRequest(TIRKSJXProperties, sessionID);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in building TIRKSJX Close Session Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return aCloseSessionRequest;
	}
	

	/**
	 * build the CBLS Query request
	 * 
	 * @param sessionID
	 * @param term_A
	 * @param term_Z
	 * @param cable
	 * @param fromUnit
	 * @param lastUnit
	 * @param isPageDownRequest
	 * @return JXAPIImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl buildCBLSQueryRequest(
			String sessionID, 
			String term_A, 
			String term_Z, 
			String cable, 
			String fromUnit, 
			String lastUnit,
			String actionIndicator)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::buildTIRKSJxCloseSessionRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl aCBLSQueryRequest = null;
		
		try
		{
			if(TIRKSJXProperties == null)
			{
				loadTIRKSJxProperties();
			}
			aCBLSQueryRequest = new CBLSQueryRequestHelper(
					utility,
					properties)
						.buildCBLSQueryRequest(
								TIRKSJXProperties, 
								sessionID, 
								term_A, 
								term_Z, 
								cable, 
								fromUnit, 
								lastUnit, 
								actionIndicator);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in building TIRKSJX CBLS Query Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return aCBLSQueryRequest;
	}
	
	/**
	 * build the CXRS Query request
	 * 
	 * @param sessionID
	 * @param term_A
	 * @param term_Z
	 * @param fac_des
	 * @param fac_type
	 * @param isPageDownRequest
	 * @return JXAPIImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl buildCXRSQueryRequest(
			String sessionID, 
			String term_A, 
			String term_Z, 
			String fac_des, 
			String fac_type,
			String actionIndicator)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::buildCXRSQueryRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl aCXRSQueryRequest = null;
		
		try
		{
			if(TIRKSJXProperties == null)
			{
				loadTIRKSJxProperties();
			}
			aCXRSQueryRequest = new CXRSQueryRequestHelper(
					utility, 
					properties)
						.buildCXRSQueryRequest(
								TIRKSJXProperties, 
								sessionID, 
								term_A, 
								term_Z, 
								fac_des, 
								fac_type, 
								actionIndicator);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in building TIRKSJX CXRS Query Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		return aCXRSQueryRequest;
	}
	
	/**
	 * build the DRI Query request
	 * 
	 * @param sessionID
	 * @param circuitFormat
	 * @param circuitID
	 * @param circuitLayoutOrder
	 * @param circuitAccessCode
	 * @param orderNumber
	 * @param supplementID
	 * @param isPageDownRequest
	 * @return JXAPIImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl buildDRIQueryRequest(
			String sessionID, 
			String circuitFormat, 
			String circuitID, 
			String circuitLayoutOrder, 
			String circuitAccessCode, 
			String orderNumber, 
			String supplementID,
			String actionIndicator)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::buildDRIQueryRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl aDRIQueryRequest = null;
		
		try
		{
			if(TIRKSJXProperties == null)
			{
				loadTIRKSJxProperties();
			}
			aDRIQueryRequest = new DRIQueryRequestHelper(
					utility, 
					properties)
						.buildDRIQueryRequest(
								TIRKSJXProperties, 
								sessionID, 
								circuitFormat, 
								circuitID, 
								circuitLayoutOrder, 
								circuitAccessCode, 
								orderNumber, 
								supplementID, 
								actionIndicator);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in building TIRKSJX DRI Query Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return aDRIQueryRequest;
	}
	
	/**
	 * build the EQPSC Query request
	 * 
	 * @param sessionID
	 * @param location
	 * @param equipCode
	 * @param level
	 * @param relayRack
	 * @param unit
	 * @param isPageDownRequest
	 * @return JXAPIImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl buildEQPSCQueryRequest(
			String sessionID, 
			String location, 
			String equipCode, 
			String level, 
			String relayRack,
			String unit,
			String actionIndicator)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::buildEQPSCQueryRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl aEPQSCQueryRequest = null;
		
		try
		{
			if(TIRKSJXProperties == null)
			{
				loadTIRKSJxProperties();
			}
			aEPQSCQueryRequest = new EQPSCQueryRequestHelper(
					utility, 
					properties)
						.buildEQPSCQueryRequest(
								TIRKSJXProperties, 
								sessionID, 
								location, 
								equipCode, 
								level, 
								relayRack, 
								unit, 
								actionIndicator);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in building TIRKSJX DRI Query Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);			
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return aEPQSCQueryRequest;
	}
	
	/**
	 * build the RDLOC Query request
	 * 
	 * @param sessionID
	 * @param location
	 * @param isPageDownRequest
	 * @return JXAPIImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl buildRDLOCQueryRequest(
			String sessionID, 
			String location,
			String actionIndicator)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::buildRDLOCQueryRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl aRDLOCQueryRequest = null;
		
		try
		{
			if(TIRKSJXProperties == null)
			{
				loadTIRKSJxProperties();
			}
			aRDLOCQueryRequest = new RDLOCQueryRequestHelper(
					utility, 
					properties)
					.buildRDLOCQueryRequest(
							TIRKSJXProperties, 
							sessionID, 
							location, 
							actionIndicator);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in building TIRKSJX RDLOC Query Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return aRDLOCQueryRequest;
	}
	
	/**
	 * build the WA Query request
	 * 
	 * @param sessionID
	 * @param circuitFormat
	 * @param circuitID
	 * @param circuitLayoutOrder
	 * @param circuitAccessCode
	 * @param isPageDownRequest
	 * @return JXAPIImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl buildWAQueryRequest(
			String sessionID, 
			String circuitFormat,
			String circuitID,
			String circuitLayoutOrder,
			String circuitAccessCode,
			String actionIndicator)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::buildWAQueryRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl aWAQueryRequest = null;
		
		try
		{
			if(TIRKSJXProperties == null)
			{
				loadTIRKSJxProperties();
			}
			aWAQueryRequest = new WAQueryRequestHelper(
					utility, 
					properties)
						.buildWAQueryRequest(
								TIRKSJXProperties, 
								sessionID, 
								circuitFormat, 
								circuitID, 
								circuitLayoutOrder, 
								circuitAccessCode, 
								actionIndicator);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in building TIRKSJX WA Query Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		return aWAQueryRequest;
	}
	
	/**
	 * send the Open Session request and return the String response
	 * 
	 * @param openSessionRequest
	 * @return String
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public String sendOpenSessionRequest(
			com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.impl.JXAPIImpl openSessionRequest)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::sendOpenSessionRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		StringWriter stringWriter = null;
		String msg = null;
		byte[] response = null;
		String openSessionResponsMessage = null;
		
		try 
		{
			JAXBContext context = JAXBContext.newInstance(NEW_SESSION_REQUEST_IMPLEMENTATION_CLASS);
			Marshaller marshaller = context.createMarshaller();
			stringWriter = new StringWriter();
			
			marshaller.marshal(openSessionRequest, stringWriter);
			msg = stringWriter.toString();
			
			utility.log( LogEventId.INFO_LEVEL_1, "TIRKS JX Open Session Request \n" + msg) ;
			
			response = sendAndReceive(msg.getBytes());
			
			if (response.length > 0)
			{
				openSessionResponsMessage = new String(response);
			}	
			
			utility.log( LogEventId.INFO_LEVEL_1, "TIRKS JX Open Session Response \n" + openSessionResponsMessage) ;
		} 
		catch (Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in sending TIRKSJX Open Session Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return openSessionResponsMessage;
	}
	
	/**
	 * send the Close Session request and return the response
	 * 
	 * @param closeSessionRequest
	 * @return String
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public String sendCloseSessionRequest(
			com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.impl.JXAPIImpl closeSessionRequest)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{	
		String myMethodName = "TIRKSJx::sendCloseSessionRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		StringWriter stringWriter = null;
		String msg = null;
		byte[] response = null;
		String closeSessionResponsMessage = null;
		
		try 
		{
			JAXBContext context = JAXBContext.newInstance(CLOSE_SESSION_REQUEST_IMPLEMENTATION_CLASS);
			Marshaller marshaller = context.createMarshaller();
			stringWriter = new StringWriter();
			
			marshaller.marshal(closeSessionRequest, stringWriter);
			msg = stringWriter.toString();	
			
			utility.log( LogEventId.INFO_LEVEL_1, "TIRKS JX Close Session Request \n" + msg) ;
			
			response = sendAndReceive(msg.getBytes());
			
			if (response.length > 0)
			{
				closeSessionResponsMessage = new String(response);
			}	
			
			utility.log( LogEventId.INFO_LEVEL_1, "TIRKS JX Close Session Response \n" + closeSessionResponsMessage) ;			
		} 
		catch (Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in sending TIRKSJX Close Session Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return closeSessionResponsMessage;
	}	
	
	/**
	 * send the CBLS, CXRS, DRI, EQPSC, RDLOC, and WA Action on Screen request
	 * 
	 * @param actionOnScreenRequest
	 * @return String
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public String sendActionOnScreenRequest(
			com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl actionOnScreenRequest) 
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{			
		String myMethodName = "TIRKSJx::sendActionOnSceenRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		StringWriter stringWriter = null;
		String msg = null;
		byte[] response = null;
		String actionOnScreenResponseMessage = null;
		
		try 
		{
			JAXBContext context = JAXBContext.newInstance(ACTION_ON_SCREEN_REQUEST_IMPLEMENTATION_CLASS);
			Marshaller marshaller = context.createMarshaller();
			stringWriter = new StringWriter();
			
			marshaller.marshal(actionOnScreenRequest, stringWriter);
			msg = stringWriter.toString();	
			
			utility.log( LogEventId.INFO_LEVEL_1, "TIRKS JX Action On Screen Request \n" + msg) ;
			
			response = sendAndReceive(msg.getBytes());
			
			if (response.length > 0)
			{
				actionOnScreenResponseMessage = new String(response);
			}
			utility.log( LogEventId.INFO_LEVEL_1, "TIRKS JX Action on Screen Response \n" + actionOnScreenResponseMessage) ;
		} 
		catch (Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in sending TIRKSJX Action on Screen Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return actionOnScreenResponseMessage;
	}
	
	/**
	 * parse the Open Session response
	 * 
	 * @param String response
	 * @return OpenSessionResponseHelper
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public OpenSessionResponseHelper parseOpenSessionResponse(
			String response)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound,
			ServiceException
	{
		String myMethodName = "TIRKSJx::parseOpenSessionResponse()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		OpenSessionResponseHelper openSessionResponseHelper = null;
		ErrorOnScreenResponseHelper errorResponseHelper = null;
		
		try 
		{
			if(checkResponseHasError(response))
			{
				errorResponseHelper = parseErrorOnScreenResponse(response);
				TIRKSJXError[] TIRKSJXError = errorResponseHelper.getTirksErrors();
				int TIRKSJXErrorSize = TIRKSJXError.length;
				String errorType = null;
				String errorMessage = null;
				
				for(int i=0; i<TIRKSJXErrorSize; i++)
				{
					errorType = TIRKSJXError[i].getErrorType();
					errorMessage = TIRKSJXError[i].getErrorMessage();
				}				
				throw new ServiceException(
						"Error in connecting to TiRKSJX API ",
						errorMessage + 
						" "+
						(String)properties.get("TIRKSJX_SERVICE_NAME") +
						" " + 
						(String)properties.get("TIRKSJX_SERVICE_VERSION"));
			}
			else
			{
				JAXBContext context = JAXBContext.newInstance(NEW_SESSION_RESPONSE_IMPLEMENTATION_CLASS);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				unmarshaller.unmarshal((new InputSource(new StringReader(response))));
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionResponse.impl.JXAPITypeImpl aOpenSessionResponse = 
					(com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionResponse.impl.JXAPITypeImpl)unmarshaller.unmarshal((new InputSource(new StringReader(response))));
	
				openSessionResponseHelper = new OpenSessionResponseHelper(utility, properties);
				openSessionResponseHelper.parseResponse(aOpenSessionResponse);
			}
		} 
		catch (JAXBException e) 
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in parsing TIRKSJX Open Session Response " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return openSessionResponseHelper;
	}
	
	/**
	 * parse the Close Session response
	 * 
	 * @param String response
	 * @return CloseSessionResponseHelper
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public CloseSessionResponseHelper parseCloseSessionResponse(
			String response)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::parseCloseSessionResponse()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		CloseSessionResponseHelper closeSessionResponseHelper = null;
		ErrorOnScreenResponseHelper errorResponseHelper = null;
		
		try 
		{
			if(checkResponseHasError(response))
			{
				errorResponseHelper = parseErrorOnScreenResponse(response);
				TIRKSJXError[] TIRKSJXError = errorResponseHelper.getTirksErrors();
				int TIRKSJXErrorSize = TIRKSJXError.length;
				String errorType = null;
				String errorMessage = null;
				
				for(int i=0; i<TIRKSJXErrorSize; i++)
				{
					errorType = TIRKSJXError[i].getErrorType();
					errorMessage = TIRKSJXError[i].getErrorMessage();
				}				
				throw new ServiceException(
						"Error in connecting to TiRKSJX API ",
						errorMessage + 
						" "+
						(String)properties.get("TIRKSJX_SERVICE_NAME") +
						" " + 
						(String)properties.get("TIRKSJX_SERVICE_VERSION"));
			}
			else
			{
				JAXBContext context = JAXBContext.newInstance(CLOSE_SESSION_RESPONSE_IMPLEMENTATION_CLASS);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				unmarshaller.unmarshal((new InputSource(new StringReader(response))));
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionResponse.impl.JXAPITypeImpl aCloseSessionResponse = 
					(com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionResponse.impl.JXAPITypeImpl)unmarshaller.unmarshal((new InputSource(new StringReader(response))));
	
				closeSessionResponseHelper = new CloseSessionResponseHelper(utility, properties);
				closeSessionResponseHelper.parseResponse(aCloseSessionResponse);
			}
			
		} 
		catch (Exception e) 
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in parsing TIRKSJX Close Session Response " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return closeSessionResponseHelper;
	}
	
	/**
	 * parse the Error On Screen response
	 * 
	 * @param String response
	 * @return ErrorOnScreenResponseHelper 
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public ErrorOnScreenResponseHelper parseErrorOnScreenResponse(
			String response) 
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::parseActionOnScreenResponse()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		ErrorOnScreenResponseHelper errorOnScreenResponseHelper = null;
		
		try 
		{
			JAXBContext context = JAXBContext.newInstance(ERROR_ON_SCREEN_RESPONSE_IMPLEMENTATION_CLASS);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.unmarshal((new InputSource(new StringReader(response))));
			
			com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxErrorOnScreenResponse.impl.JXAPITypeImpl aErrorResponse = 
				(com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxErrorOnScreenResponse.impl.JXAPITypeImpl)unmarshaller.unmarshal((new InputSource(new StringReader(response))));

			errorOnScreenResponseHelper = new ErrorOnScreenResponseHelper(utility, properties);
			errorOnScreenResponseHelper.parseResponse(aErrorResponse);
			
		} 
		catch (Exception e) 
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		return errorOnScreenResponseHelper;
	}
	
	/**
	 * parse the CBLS, CXRS, DRI, EQPSC, RDLOC, and WA Action On Screen response
	 * 
	 * @param response
	 * @return ActionOnScreenResponseHelper
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public ActionOnScreenResponseHelper parseActionOnScreenResponse(
			String response) 
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::parseActionOnScreenResponse()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		ActionOnScreenResponseHelper actionOnScreenResponseHelper = null;
		ErrorOnScreenResponseHelper errorResponseHelper = null;
		
		try 
		{
			if(checkResponseHasError(response))
			{
				errorResponseHelper = parseErrorOnScreenResponse(response);
				TIRKSJXError[] TIRKSJXError = errorResponseHelper.getTirksErrors();
				int TIRKSJXErrorSize = TIRKSJXError.length;
				String errorType = null;
				String errorMessage = null;
				
				for(int i=0; i<TIRKSJXErrorSize; i++)
				{
					errorType = TIRKSJXError[i].getErrorType();
					errorMessage = TIRKSJXError[i].getErrorMessage();
				}				
				throw new ServiceException(
						"Error in connecting to TiRKSJX API ",
						errorMessage + 
						" "+
						(String)properties.get("TIRKSJX_SERVICE_NAME") +
						" " + 
						(String)properties.get("TIRKSJX_SERVICE_VERSION"));
			}
			
			else
			{
				JAXBContext context = JAXBContext.newInstance(ACTION_ON_SCREEN_RESPONSE_IMPLEMENTATION_CLASS);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				unmarshaller.unmarshal((new InputSource(new StringReader(response))));
				
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenResponse.impl.JXAPITypeImpl aTIRKSJXResponse = 
					(com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenResponse.impl.JXAPITypeImpl)unmarshaller.unmarshal((new InputSource(new StringReader(response))));
	
				actionOnScreenResponseHelper = new ActionOnScreenResponseHelper(utility, properties);
				actionOnScreenResponseHelper.parseResponse(aTIRKSJXResponse);
			}
			
		} 
		catch (Exception e) 
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in parsing TIRKSJX Action On Screen Response " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		return actionOnScreenResponseHelper;
	}

	
	/**
	 * accept the bytes array TIRKSJX request and return the byte array response
	 * 
	 * @param tirksRequest
	 * @return byte[]
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public byte[] sendAndReceive(
			byte[] tirksRequest)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::sendAndReceive()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		ByteArrayOutputStream byteArrayOutputStream = null;
		byte[] serverResponseByteArray = null;
		Socket socket = null;
		OutputStream writer = null;
		InputStream reader = null;
		byte[] resultArray = null;
		
		try
		{
			utility.log(
					LogEventId.REMOTE_CALL,
					TIRKSJX_Name,
					TIRKSJX_Name + TIRKSJX_Version,
					TIRKSJX_Name + TIRKSJX_Version,
					myMethodName);
			
			byteArrayOutputStream = new ByteArrayOutputStream();
			serverResponseByteArray = new byte[1024];
			socket = openTIRKSJxSession();
			writer = socket.getOutputStream();
			reader = socket.getInputStream();
			int bytesRead;			
			writer.write(tirksRequest);
			writer.write(EOF_DELIMITER.getBytes());
			writer.flush();
			
			while((bytesRead = reader.read(serverResponseByteArray)) > 0 )
			{
				byteArrayOutputStream.write(serverResponseByteArray, 0, bytesRead );
			}
			
			utility.log(
					LogEventId.REMOTE_RETURN,
					TIRKSJX_Name,
					TIRKSJX_Name + TIRKSJX_Version,
					TIRKSJX_Name + TIRKSJX_Version,
					myMethodName);
			
			resultArray = byteArrayOutputStream.toByteArray();
			
			if(socket != null && socket.isConnected())
			{
				socket.close();
			}
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in sending TIRKSJX Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return resultArray;
	}
	
	/**
	 * determines the Data Center from TIRSKJX property file
	 * 
	 * @param String state
	 * @return String
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public String determineDataCenter(
			String state)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "TIRKSJx::determineDataCenter()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		String dataCenter = null;
		try
		{
			if(TIRKSJXProperties == null)
			{
				loadTIRKSJxProperties();
			}
			dataCenter = TIRKSJXProperties.getProperty(TIRKSJX_Name + "." + state);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in determining Data Center " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		return dataCenter;
	}
	
	/**
	 * checks if the TIRKSJX response has succeeding pages 
	 * 
	 * @param aFields
	 * @return boolean
	 */
	public boolean checkPageDownRequest(
			Field [] aFields)
	
	{
		String myMethodName = "TIRKSJx::checkPageDownRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		boolean pageDownRequest = false;
		for(int i=0; i < aFields.length; i++)
		{
			if (aFields [i] != null && 
				aFields [i].getFieldName().equalsIgnoreCase(FIELD_SYSMSG) && 
				aFields [i].getFieldValue().indexOf(FIELD_OUTPUT_CONTINUED) > -1)
			{
				pageDownRequest = true;
			}
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName); 
		
		return pageDownRequest;
	}	
	
	/**
	 * check if TIRSKJX returns an error response
	 * 
	 * @param String aResponse
	 * @return boolean
	 */
	public boolean checkResponseHasError(
			String aResponse)
	
	{
		String myMethodName = "TIRKSJx::checkErrorResponse()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		boolean responseHasError = false;
		
		if(aResponse.indexOf(ERROR_RETURN) > -1)
		{
			responseHasError = true;
		}

		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName); 
		
		return responseHasError;
	}
	
	/**
	 * load the TIRKSJX properties
	 * 
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	private void loadTIRKSJxProperties()
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "TIRKSJx::loadTIRKSJxProperties()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
	
		// The file contains the TIRKSJx Properties 
		String tirksJxFile = (String) properties.get("TIRKSJX_PROPERTY_FILE");
		
		if (tirksJxFile == null || tirksJxFile.length() < 1)
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_STATE_IMS_REGION_MAP_FILE_PROPERTY,
				"TIRKSJX_PROPERTY_FILE property not found/not set",
				myMethodName,
				Severity.UnRecoverable);
		}
		
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"Loaded TIRKSJx Properties from: <"
				+ tirksJxFile
				+ ">");
		
		try 
		{
			TIRKSJXProperties = new Properties();
			
			PropertiesFileLoader.read(
				TIRKSJXProperties,
				tirksJxFile,
				utility);
		
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				("Loaded "
					+ TIRKSJXProperties.size()
					+ " TIRKSJx properties"));
		
			utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);			
		} 
		catch (FileNotFoundException e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_STATE_IMS_REGION_MAP_FILE,
				"FileNotFoundException: " + e.getMessage(),
				myMethodName,
				Severity.UnRecoverable);
		} 
		catch (IOException e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_STATE_IMS_REGION_MAP_FILE,
				"IOException: " + e.getMessage(),
				myMethodName,
				Severity.UnRecoverable,
				e);
		}
	}
}
