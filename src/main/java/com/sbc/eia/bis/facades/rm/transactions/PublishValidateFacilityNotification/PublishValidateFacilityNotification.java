//$Id: PublishValidateFacilityNotification.java,v 1.14 2009/03/04 00:49:20 js7440 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date       | Author              | Notes
//# --------------------------------------------------------------------
//# 07/16/2007   Rene Duka             Creation.
//# 11/13/2007   Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 12/19/2007   Deepti Nayar          Defect 78781: Add vF correlation ID into the response.
//# 05/14/2008   Jon Costa			   Remove redundant XML logging, MDB already logs.
//# 02/03/2009	 Lira Galsim		   Modified for LS10.        

package com.sbc.eia.bis.facades.rm.transactions.PublishValidateFacilityNotification;

import java.util.Hashtable;

import javax.jms.TextMessage;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.facades.rm.transactions.ValidateFacility.ValidateFacilityConstants;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.BisContextHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.bishelpers.LocationBisHelper;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacility2RequestMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacility3RequestMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityForProvisioningRequestMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityRequestMsg;
import com.sbc.eia.idl.rm_ls_types.OrderAction2Opt;
import com.sbc.eia.idl.rm_ls_types.bishelpers.OrderAction2OptBisHelper;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ObjectType;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.EiaDateOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectTypeSeqBisHelper;
import com.sbc.eia.idl.types.bishelpers.ShortOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringOptBisHelper;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBReader;

/**
 * Class      : PublishValidateFacilityNotification
 * Description: Facade for pVFN transaction.
 *              - execute pVFN
 *              - log the XML input message
 *              - convert XML input message to java objects
 *              - log input
 *              - analyze the message   
 */
public class PublishValidateFacilityNotification extends TranBase 
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;
    private Logger aLogger = null;
    private com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotification aPublishValidateFacilityNotification = null;    
    
    private BisContext aClientBisContext = null;
    private Location aServiceLocation = null;
    private StringOpt aRelatedCircuitID = null;
    private StringOpt aWorkingTelephoneNumber = null;
    private ShortOpt aMaxPairsToAnalyze = null;
    private StringOpt aSOACServiceOrderNumber = null;
    private StringOpt aSOACServiceOrderNumberSuffix = null;
    private EiaDateOpt aUverseOrderDueDate = null;
    private ObjectType[] aNtis = null;
    private StringOpt aOrderActionType = null;
    private OrderAction2Opt aOrderAction2 = null;
    private StringOpt aSubActionType = null;
    private ObjectPropertySeqOpt aObjectProperties = null;
    private String aTransportType = null;
    
	/**
	* Constructor: PublishValidateFacilityNotification
    * @author Rene Duka
	*/
	public PublishValidateFacilityNotification()
    {
	    super();
	}
	
	/**
    * Constructor: PublishValidateFacilityNotification
    * 
    * @param Utility   utility
    * @param Hashtable properties
    * 
    * @author Rene Duka
    */
	public PublishValidateFacilityNotification(Hashtable param)
    {
	    super(param);
	    aUtility = this;
	    aProperties = getPROPERTIES();
	}
	
	/**
	 * Executes the pVFN transaction.
	 *
	 * @param BisContext aContext
	 * @param String     aMessage
	 * @param Logger     aLogger
     * @exception Exception generic exception
     * 
     * @author Rene Duka
	 */
	public void execute(
        BisContext aContext,
        javax.jms.Message aMessage,
        Logger aLogger)
        throws
            Exception 
    {
	    myLogger = aLogger;
			
	    String aMethodName = "PublishValidateFacilityNotification: execute()";
	    aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);  

	    aTransportType = aMessage.getStringProperty(ValidateFacilityConstants.JMS_propertyName);
	 	
	    // parse the input	
	    parseInput(aMessage);
      
	    // log input (java objects)
        logInput();	    

	    // create BisContext
		if (aContext == null)
        {
            BisContextManager aBisContextManager = new BisContextManager();
            aBisContextManager.setApplication((String) aProperties.get("BIS_CONTEXT_APPLICATION"));
            aBisContextManager.setCustomerName((String) aProperties.get("BIS_CONTEXT_APPLICATION"));
            aBisContextManager.setLoggingInformationString(myLogger.getBisLogger().get_correlation_id());
            aContext = aBisContextManager.getBisContext();
		}
        
		// add pVFN Correlation ID to BisContext from vF   
        ObjectPropertyManager aOPM = new ObjectPropertyManager(aClientBisContext.aProperties);
        aOPM.add(new ObjectProperty("LoggingInformation_PVFN", myLogger.getBisLogger().getCorrelationId()));
        aClientBisContext.aProperties = aOPM.toArray();
        
	    try
        {
	    	//Cache
	        if (aPublishValidateFacilityNotification == null)
            {
	            aPublishValidateFacilityNotification = 
                    new com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotification(aUtility, getPROPERTIES());
	        }
            // analyze message            
            aPublishValidateFacilityNotification.analyzeMessage(aContext,
                                                                aClientBisContext,
                    											aServiceLocation,
                    											aRelatedCircuitID,
                    											aWorkingTelephoneNumber,
                    											aMaxPairsToAnalyze,
                    											aSOACServiceOrderNumber,
                    											aSOACServiceOrderNumberSuffix,
                                                                aUverseOrderDueDate,
                                                                aNtis,
                                                                aOrderActionType,
                                                                aSubActionType,
                                                                aOrderAction2,
                                                                aTransportType,
                    											aObjectProperties);
	    }
	    catch (Exception e) 
        {
	        aUtility.log(LogEventId.ERROR, ">" + "Exception in PublishValidateFacilityNotification: " + " - " + e.getMessage());
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                    "Exception message: " + e.getMessage(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
	    }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
	}

	/**
     * Parses the xml message.
     *
     * @param String aMessage
     * @exception Exception generic exception
     * 
     * @author Rene Duka
	 */
	private void parseInput(javax.jms.Message aMessage)
        throws 
            Exception 
    {
        String aMethodName = "PublishValidateFacilityNotification: parseInput()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        // Reset the value of all the fields for each transaction. Incase the class variable is cached.
        resetFields();
        
        try 
        {
            // convert XML to IDL object
            MMarshalObject aRequest = convertXMLToMMarshalObject(((TextMessage) aMessage).getText());
                      
            if (aTransportType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)) {          	
            	getValidateFacility2Fields(aRequest);           	
            }
            else if (aTransportType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3)) {
            	getValidateFacility3Fields(aRequest);
            }
            else if (aTransportType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING) ) {
            	getValidateFacilityForProvisioningFields(aRequest);
            }
        }
        catch (Exception e)
        {
            // log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("> Exception: [ ");
            eLogMessage.append(e.getMessage());
            eLogMessage.append(" ] ");
            eLogMessage.append("> Failure to decode XML to MMarshalObject.");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            // throw: Exception
            throw new Exception(eLogMessage.toString());
        }
        finally
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
	}
	
	private void resetFields()
	{ 
		    
		    aClientBisContext = null;
		    aServiceLocation = null;
		    aRelatedCircuitID = null;
		    aWorkingTelephoneNumber = null;
		    aMaxPairsToAnalyze = null;
		    aSOACServiceOrderNumber = null;
		    aSOACServiceOrderNumberSuffix = null;
		    aUverseOrderDueDate = null;
		    aNtis = null;
		    aOrderActionType = null;
		    aOrderAction2 = null;
		    aSubActionType = null;
		    aObjectProperties = null;
	}
	
	/**
     * Parses the xml message for ValidateFacility2.
     *
     * @param MMarshalObject aRequest
     * 
     * @author Hongmei Parkin
	 */	
	private void getValidateFacility2Fields(MMarshalObject aRequest) 
	{
		// build request IDL objects from MMarshalObject
		_validateFacility2RequestMsg aRequestMsg = (_validateFacility2RequestMsg) aRequest;
        
        aClientBisContext = aRequestMsg.value.aContext;
        aServiceLocation = aRequestMsg.value.aServiceLocation;
        aRelatedCircuitID = aRequestMsg.value.aRelatedCircuitID;
        aWorkingTelephoneNumber = aRequestMsg.value.aWorkingTelephoneNumber;
        aMaxPairsToAnalyze = aRequestMsg.value.aMaxPairsToAnalyze;
        aSOACServiceOrderNumber = aRequestMsg.value.aSOACServiceOrderNumber;
        aSOACServiceOrderNumberSuffix = aRequestMsg.value.aSOACServiceOrderCorrectionSuffix;
        aUverseOrderDueDate = aRequestMsg.value.aUverseOrderDueDate;
        aNtis = aRequestMsg.value.aNtis;
        aOrderActionType = aRequestMsg.value.aOrderActionType;
        aSubActionType = aRequestMsg.value.aSubActionType;
        aObjectProperties = aRequestMsg.value.aProperties;
	}

	/**
     * Parses the xml message for ValidateFacility3.
     *
     * @param MMarshalObject aRequest
     * 
     * @author Hongmei Parkin
	 */	
	private void getValidateFacility3Fields(MMarshalObject aRequest) 
	{
		// build request IDL objects from MMarshalObject
		_validateFacility3RequestMsg aRequestMsg = (_validateFacility3RequestMsg) aRequest;
        
        aClientBisContext = aRequestMsg.value.aContext;
        aServiceLocation = aRequestMsg.value.aServiceLocation;
        aRelatedCircuitID = aRequestMsg.value.aRelatedCircuitID;
        aWorkingTelephoneNumber = aRequestMsg.value.aWorkingTelephoneNumber;
        aMaxPairsToAnalyze = aRequestMsg.value.aMaxPairsToAnalyze;
        aSOACServiceOrderNumber = aRequestMsg.value.aSOACServiceOrderNumber;
        aUverseOrderDueDate = aRequestMsg.value.aOrderDueDate;
        aNtis = aRequestMsg.value.aNtis;
        aOrderAction2 = aRequestMsg.value.aOrderAction;
        aObjectProperties = aRequestMsg.value.aProperties;
	}

	/**
     * Parses the xml message for ValidateFacilityForProvisioning.
     *
     * @param MMarshalObject aRequest
     * 
     * @author Hongmei Parkin
	 */	
	private void getValidateFacilityForProvisioningFields(MMarshalObject aRequest) 
	{
		// build request IDL objects from MMarshalObject
		_validateFacilityForProvisioningRequestMsg aRequestMsg = (_validateFacilityForProvisioningRequestMsg) aRequest;
        
        aClientBisContext = aRequestMsg.value.aContext;
        aServiceLocation = aRequestMsg.value.aServiceLocation;
        aRelatedCircuitID = aRequestMsg.value.aRelatedCircuitID;
        aWorkingTelephoneNumber = aRequestMsg.value.aWorkingTelephoneNumber;
        aMaxPairsToAnalyze = aRequestMsg.value.aMaxPairsToAnalyze;
        aSOACServiceOrderNumber = aRequestMsg.value.aSOACServiceOrderNumber;
        aUverseOrderDueDate = aRequestMsg.value.aOrderDueDate;
        aNtis = aRequestMsg.value.aNtis;
        aOrderAction2 = aRequestMsg.value.aOrderAction;
        aObjectProperties = aRequestMsg.value.aProperties;
	}

	/**
     * Logs input.
     * 
     * @author Rene Duka
	 */
	private void logInput() 
    {
	    // aBisContext
        aUtility.log(LogEventId.INPUT_DATA, "aBisContext<" + (new BisContextBisHelper(aClientBisContext)).toString() + ">");
        // aServiceLocation
        try 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aServiceLocation<" 
                + (new LocationBisHelper(aServiceLocation)).toString() 
                + ">");
        }
        catch (Exception e) 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aServiceLocation<null>");
        }
        // aRelatedCircuitID
        try 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aRelatedCircuitID<" 
                + (new StringOptBisHelper(aRelatedCircuitID)).toString() 
                + ">");
        }
        catch (Exception e) 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aRelatedCircuitID<null>");
        }
        // aWorkingTelephoneNumber
        try 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aWorkingTelephoneNumber<" 
                + (new StringOptBisHelper(aWorkingTelephoneNumber)).toString() 
                + ">");
        }
        catch (Exception e) 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aWorkingTelephoneNumber<null>");
        }
        // aMaxPairsToAnalyze
        try 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aMaxPairsToAnalyze<" 
                + (new ShortOptBisHelper(aMaxPairsToAnalyze)).toString() 
                + ">");
        }
        catch (Exception e) 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aMaxPairsToAnalyze<null>");
        }
        // aSOACServiceOrderNumber
        try 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aSOACServiceOrderNumber<" 
                + (new StringOptBisHelper(aSOACServiceOrderNumber)).toString() 
                + ">");
        }
        catch (Exception e) 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aSOACServiceOrderNumber<null>");
        }

        if (aTransportType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)) 
        {          	
       	 // aSOACServiceOrderNumberSuffix
           try 
           {
               aUtility.log(LogEventId.INPUT_DATA, "aSOACServiceOrderNumberSuffix<" 
                   + (new StringOptBisHelper(aSOACServiceOrderNumberSuffix)).toString() 
                   + ">");
           }
           catch (Exception e) 
           {
               aUtility.log(LogEventId.INPUT_DATA, "aSOACServiceOrderNumberSuffix<null>");
           }
        }
        
        // aUverseOrderDueDate
        try 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aUverseOrderDueDate<" 
                + (new EiaDateOptBisHelper(aUverseOrderDueDate)).toString() 
                + ">");
        }
        catch (Exception e) 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aUverseOrderDueDate<null>");
        }
        // aNtis
        try 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aNtis<" 
                + (new ObjectTypeSeqBisHelper(aNtis)).toString() 
                + ">");
        }
        catch (Exception e) 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aNtis<null>");
        }
        
        if (aTransportType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY2)) 
        {          	
           // aOrderActionType
           try 
           {
               aUtility.log(LogEventId.INPUT_DATA, "aOrderActionType<" 
                   + (new StringOptBisHelper(aOrderActionType)).toString() 
                   + ">");
           }
           catch (Exception e) 
           {
               aUtility.log(LogEventId.INPUT_DATA, "aOrderActionType<null>");
           }
           // aSubActionType
           try 
           {
               aUtility.log(LogEventId.INPUT_DATA, "aSubActionType<" 
                   + (new StringOptBisHelper(aSubActionType)).toString() 
                   + ">");
           }
           catch (Exception e) 
           {
               aUtility.log(LogEventId.INPUT_DATA, "aSubActionType<null>");
           }
        }
        else if (aTransportType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITY3) 
       			 || aTransportType.equalsIgnoreCase(ValidateFacilityConstants.VALIDATEFACILITYFORPROVISIONING) ) 
        {
       		// OrderAction2
        	try 
            {
                aUtility.log(LogEventId.INPUT_DATA, "aOrderAction2<" 
                    + (new OrderAction2OptBisHelper(aOrderAction2)).toString() 
                    + ">");
            }
            catch (Exception e) 
            {
                aUtility.log(LogEventId.INPUT_DATA, "aOrderAction2<null>");
            }
       }

        // aObjectProperties
        try 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aObjectProperties<" 
                + (new ObjectPropertySeqOptBisHelper(aObjectProperties)).toString() 
                + ">");
        }
        catch (Exception e) 
        {
            aUtility.log(LogEventId.INPUT_DATA, "aObjectProperties<null>");
        }
	}

    /**
     * Converts the xml message into java objects.
     *
     * @param String aXML
     * @return MMarshalObject
     * @exception Exception generic exception
     * 
     * @author Rene Duka
     */
    public MMarshalObject convertXMLToMMarshalObject(String aXML) 
        throws 
            Exception 
    {
        String aMethodName = "PublishValidateFacilityNotification: convertXMLToMMarshalObject()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        MMarshalObject aMMarshalObject = null;  
        try 
        {
            aMMarshalObject = VAXBReader.decode(aXML);
        }
        catch (Exception e) 
        {
            // log: exception message            
            StringBuffer eLogMessage = new StringBuffer();
            eLogMessage.append("> Exception: [ ");
            eLogMessage.append(e.getMessage());
            eLogMessage.append(" ] ");
            eLogMessage.append("> Failure to decode XML to MMarshalObject.");
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            // throw: Exception
            throw new Exception(eLogMessage.toString());
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
        return aMMarshalObject;
    }
}