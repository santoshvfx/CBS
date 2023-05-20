//$Id: ValidateFacility2.java,v 1.5 2009/01/21 19:15:41 hw7243 Exp $
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
//# Date      | Author              | Notes
//# 12/20/07    Hongmei Parkin        Created initial file
//# 01/18/08	Deepti Nayar		  Modified for LS7
//# --------------------------------------------------------------------

package com.sbc.eia.bis.facades.rm.transactions.ValidateFacility2;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.client.ClientService;
import com.sbc.eia.bis.facades.rm.transactions.ValidateFacility.ValidateFacilityConstants;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.NtiHelper;
import com.sbc.eia.bis.rm.utilities.ValidateHelper;
import com.sbc.eia.bis.validator.Validator;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
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
import com.sbc.eia.idl.rm.ValidateFacility2Return;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacility2Request;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacility2RequestMsg;
import com.sbc.eia.idl.rm.bishelpers.ValidateFacility2ReturnBisHelper;
import com.sbc.eia.idl.types.EiaDateOpt;
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
import com.sbc.vicunalite.vaxb.VAXBWriter;

public class ValidateFacility2 extends TranBase 
{

  protected Utility aUtility = null;
  protected Hashtable aProperties = null;
  protected Validator validator = null;
  
  /**
   * Constructor: ValidateFacility2
   * 
   * @author Deepti Nayar
   */
  
  public ValidateFacility2() 
  {
      super();
  }

  /**
   * Constructor: ValidateFacility2
   * 
   * @param Hashtable properties
   * 
   * @author Deepti Nayar
   */
 
  public ValidateFacility2(Hashtable param) 
  {
      super(param);
      aUtility = this;
      aProperties = getPROPERTIES();
  }

  
  /**
   * Method: execute => validateFacility
   * @param aContext
   * @param aServiceLocation
   * @param aRelatedCircuitID
   * @param aWorkingTelephoneNumber
   * @param aMaxPairsToAnalyze
   * @param aSOACServiceOrderNumber
   * @param aSOACServiceOrderCorrectionSuffix
   * @param aUverseOrderDueDate
   * @param aNtis
   * @param aOrderActionType
   * @param aSubActionType
   * @param aProperties
   * @param aLogger
   * @return  ValidateFacility2Return
   * @throws BusinessViolation
   * @throws ObjectNotFound
   * @throws InvalidData
   * @throws AccessDenied
   * @throws SystemFailure
   * @throws DataNotFound
   * @throws NotImplemented
   */
  public ValidateFacility2Return execute(
  			BisContext aContext, 
			Location aServiceLocation, 
			StringOpt aRelatedCircuitID, 
			StringOpt aWorkingTelephoneNumber, 
			ShortOpt aMaxPairsToAnalyze, 
			StringOpt aSOACServiceOrderNumber, 
			StringOpt aSOACServiceOrderCorrectionSuffix, 
			EiaDateOpt aUverseOrderDueDate, 
			ObjectType[] aNtis, 
			StringOpt aOrderActionType, 
			StringOpt aSubActionType, 
			ObjectPropertySeqOpt aObjectProperties,
			Logger aLogger)
      throws 
          BusinessViolation,
          ObjectNotFound,
          InvalidData,
          AccessDenied,
          SystemFailure,
          DataNotFound,
          NotImplemented 
  {
	  String aTransactionType = ValidateFacilityConstants.VALIDATEFACILITY2;
	  String aMethodName = aTransactionType + ": execute()";
      myLogger = aLogger;
      callerContext = aContext;
      
      log(LOG_DEBUG_LEVEL_1, ">" + aMethodName);

      ObjectPropertyManager aContextOPM = new ObjectPropertyManager(aContext.aProperties);

      ValidateFacility2Return aValidateFacility2Return = null;
      
     // log input
  		logInput(aContext, 
  				aServiceLocation,	
				aRelatedCircuitID, 
				aWorkingTelephoneNumber, 
				aMaxPairsToAnalyze, 
				aSOACServiceOrderNumber, 
				aSOACServiceOrderCorrectionSuffix, 
				aUverseOrderDueDate, 
				aNtis, 
				aOrderActionType, 
				aSubActionType,
				aObjectProperties);
	
      try 
      {
          // validate BisContext
          ValidateHelper.validateBisContext(aUtility,
                                            aContextOPM,
                                            aProperties.get("BIS_NAME").toString());

          // is client authorized
          validateClient(aContextOPM,
                         null, // group_id
                         ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
                         ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION);
         

                       
          // validate input         
          if (validator == null) 
         {
              validator = new Validator(aUtility,
                                        aProperties,
                                        (String) aProperties.get("VF_VALIDATOR_VARIABLE_MAP_FILE")); 
          }      
          
          // validate input aNtis
          NtiHelper aNtiValidate = new NtiHelper();
          aNtiValidate.determineNetworkTypes(aNtis,aUtility,aProperties,aTransactionType); 
         
          
          validator.validate(aContext, new ValidateFacility2Validation(aContext,
                                                                      aServiceLocation,
                                                                      aRelatedCircuitID,
                                                                      aWorkingTelephoneNumber,
                                                                      aMaxPairsToAnalyze,
                                                                      aSOACServiceOrderNumber,
                                                                      aSOACServiceOrderCorrectionSuffix,
                                                                      aUverseOrderDueDate,
																	  aOrderActionType,
																	  aSubActionType,
                                                                      aObjectProperties), false);
         // build request XML
          String aRequestXML = buildRequestXML(aContext,
                                               aServiceLocation,
                                               aRelatedCircuitID,
                                               aWorkingTelephoneNumber,
                                               aMaxPairsToAnalyze,
                                               aSOACServiceOrderNumber,
                                               aSOACServiceOrderCorrectionSuffix,
                                               aUverseOrderDueDate,
											   aNtis,
											   aOrderActionType,
											   aSubActionType,
                                               aObjectProperties);

          
          // send request XML to pVFN
          sendRequest(aContext,
                      aRequestXML,
                      aTransactionType);

          // build acknowledgement response object
          aValidateFacility2Return = new ValidateFacility2Return(aContext);
        
          // log output
          logOutput(aValidateFacility2Return);
      }
      finally  
      {
          log(LOG_DEBUG_LEVEL_1, "<" + aMethodName);
      }
      return aValidateFacility2Return;
  }
  /**
   * Builds the request XML.
   *
   * @param BisContext aContext
   * @param Location   aServiceLocation
   * @param StringOpt  aRelatedCircuitID
   * @param StringOpt  aWorkingTelephoneNumber
   * @param ShortOpt   aMaxPairsToAnalyze
   * @param StringOpt  aSOACServiceOrderNumber
   * @param StringOpt  aSOACServiceOrderNumberSuffix
   * @param EiaDateOpt aUverseOrderDueDate
   * @param ObjectType[] aNtis
   * @param StringOpt aOrderActionType
   * @param StringOpt aSubActionType
   * @param ObjectPropertySeqOpt aObjectProperties
   * @return String
   * @exception InvalidData      : An input parameter contained invalid data.
   * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
   * @exception BusinessViolation: The attempted action violates a business rule.
   * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
   * @exception NotImplemented   : The method has not been implemented.
   * @exception ObjectNotFound   : The desired domain object could not be found.
   * @exception DataNotFound     : No data found.
   * 
   * @author Deepti Nayar
   */
  private String buildRequestXML(
      BisContext aContext,
      Location aServiceLocation,
      StringOpt aRelatedCircuitID,
      StringOpt aWorkingTelephoneNumber,
      ShortOpt aMaxPairsToAnalyze,
      StringOpt aSOACServiceOrderNumber,
      StringOpt aSOACServiceOrderCorrectionSuffix,
      EiaDateOpt aUverseOrderDueDate,
	  ObjectType[] aNtis,
	  StringOpt aOrderActionType,
	  StringOpt aSubActionType,
      ObjectPropertySeqOpt aObjectProperties)
      throws 
          BusinessViolation,
          ObjectNotFound,
          InvalidData,
          AccessDenied,
          SystemFailure,
          DataNotFound,
          NotImplemented 
  {
      String aMethodName = "ValidateFacility2: buildRequestXML()";
      log(LOG_DEBUG_LEVEL_1, ">" + aMethodName);

      // send request to MDB
      String aRequestXML = null;
      try 
      {
          // build the request message to be sent to pVFN transaction
          _validateFacility2Request aRequest = new _validateFacility2Request(aContext,
                                                                           aServiceLocation,
                                                                           aRelatedCircuitID,
                                                                           aWorkingTelephoneNumber,
                                                                           aMaxPairsToAnalyze,
                                                                           aSOACServiceOrderNumber,
                                                                           aSOACServiceOrderCorrectionSuffix,
                                                                           aUverseOrderDueDate,
																		   aNtis,
																		   aOrderActionType,
																		   aSubActionType,
                                                                           aObjectProperties);

          // format request XML file for vF
          MMarshalObject aMarshalObject = new _validateFacility2RequestMsg(aRequest);
          aRequestXML = ValidateFacilityConstants.BEGIN_VAXB_TAG
                          + VAXBWriter.encode(aMarshalObject).trim()
                          + ValidateFacilityConstants.END_VAXB_TAG;

          log(LogEventId.INFO_LEVEL_1, "XML Message: [" + aRequestXML + "]");            
      }
      catch (IOException ioe) 
      {
          aUtility.log(LogEventId.ERROR, "Conversion to XML from ValidateFacility2 object failure: " + ioe.getMessage());
          aUtility.throwException(ExceptionCode.ERR_RM_IO_EXCEPTION,
                                  "XML conversion produced an IOException " + ioe.getMessage(),
                                  (String) aProperties.get("BIS_NAME").toString(),
                                  Severity.UnRecoverable);
      }
      catch (Exception e) 
      {
          aUtility.log(LogEventId.ERROR, ">" + "Found error in buildRequestXML: [" + e.getMessage() + "]");
          aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                  "Exception message: " + e.getMessage(),
                                  (String) aProperties.get("BIS_NAME").toString(),
                                  Severity.UnRecoverable);
      }
      log(LOG_DEBUG_LEVEL_1, "<" + aMethodName);
      return aRequestXML;
  }
  /**
   * Sends the request XML.
   *
   * @param BisContext aContext
   * @param String     aRequestXML
   * @exception InvalidData      : An input parameter contained invalid data.
   * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
   * @exception BusinessViolation: The attempted action violates a business rule.
   * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
   * @exception NotImplemented   : The method has not been implemented.
   * @exception ObjectNotFound   : The desired domain object could not be found.
   * @exception DataNotFound     : No data found.
   * 
   * @author Deepti Nayar
   */
  public void sendRequest(
      BisContext aContext,
      String aRequestXML,
      String aTransactionType)
      throws 
          BusinessViolation,
          ObjectNotFound,
          InvalidData,
          AccessDenied,
          SystemFailure,
          DataNotFound,
          NotImplemented 
  {
      String aMethodName = aTransactionType + ": sendRequest()";
      log(LOG_DEBUG_LEVEL_1, ">" + aMethodName);

      ClientService aService = null; 
      try 
      {
          if (aService == null) 
          {
              aService = new ClientService(aProperties, aUtility);
          }
          
          aService.setClient("RM");
          // log remote call
          aService.logREMOTE_CALL();
          // add the JMS property idetifier for vF
          Properties aMessageTags = new Properties();
          aMessageTags.put(ValidateFacilityConstants.JMS_propertyName, 
        		  aTransactionType);
          // send message
          aService.publishMessage(aRequestXML, aMessageTags);
          // log remote return
          aService.logREMOTE_RETURN();            
      }
      catch (ServiceException se) 
      {
          aUtility.log(LogEventId.ERROR, ">" + "ServiceException " + se.getMessage());
          aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                  "Exception message: " + se.getMessage(),
                                  (String) aProperties.get("BIS_NAME").toString(),
                                  Severity.UnRecoverable);
      }
      catch (Exception e) 
      {
          aUtility.log(LogEventId.ERROR, ">" + "Found error in sendRequest: [" + e.getMessage() + "]");
          aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                  "Exception message: " + e.getMessage(),
                                  (String) aProperties.get("BIS_NAME").toString(),
                                  Severity.UnRecoverable);
      }
      finally 
      {
          log(LOG_DEBUG_LEVEL_1, "<" + aMethodName);
      }
  }
  /**
   * Logs input.
   * @param BisContext aBisContext
   * @param Location   aServiceLocation
   * @param StringOpt  aRelatedCircuitID
   * @param StringOpt  aWorkingTelephoneNumber
   * @param ShortOpt   aMaxPairsToAnalyze
   * @param StringOpt  aSOACServiceOrderNumber
   * @param StringOpt  aSOACServiceOrderCorrectionSuffix
   * @param EiaDateOpt aUverseOrderDueDate
   * @param ObjectType[] aNtis
   * @param StringOpt aOrderActionType
   * @param StringOpt aSubActionType
   * @param ObjectPropertySeqOpt aObjectProperties
   * 
   * @author Deepti Nayar
   */
	public void logInput( 
		      BisContext aBisContext,
		      Location aServiceLocation,
		      StringOpt aRelatedCircuitID,
		      StringOpt aWorkingTelephoneNumber,
		      ShortOpt aMaxPairsToAnalyze,
		      StringOpt aSOACServiceOrderNumber,
		      StringOpt aSOACServiceOrderCorrectionSuffix,
		      EiaDateOpt aUverseOrderDueDate,
			  ObjectType[] aNtis,
			  StringOpt aOrderActionType,
			  StringOpt aSubActionType,
		      ObjectPropertySeqOpt aObjectProperties)

  {
	    // aBisContext
      aUtility.log(LogEventId.INPUT_DATA, "aBisContext<" + (new BisContextBisHelper(aBisContext)).toString() + ">");
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

      // aSOACServiceOrderNumberSuffix
      try 
      {
          aUtility.log(LogEventId.INPUT_DATA, "aSOACServiceOrderNumberSuffix<" 
              + (new StringOptBisHelper(aSOACServiceOrderCorrectionSuffix)).toString() 
              + ">");
      }
      catch (Exception e) 
      {
          aUtility.log(LogEventId.INPUT_DATA, "aSOACServiceOrderNumberSuffix<null>");
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
   * Logs the output.
   *
   * @param ValidateFacility2Return aReturnObject
   * @exception InvalidData      : An input parameter contained invalid data.
   * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
   * @exception BusinessViolation: The attempted action violates a business rule.
   * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
   * @exception NotImplemented   : The method has not been implemented.
   * @exception ObjectNotFound   : The desired domain object could not be found.
   * @exception DataNotFound     : No data found.
   * 
   * @author Deepti Nayar
   */
  private void logOutput(ValidateFacility2Return aReturnObject)
      throws
          InvalidData,
          AccessDenied,
          BusinessViolation,
          SystemFailure,
          NotImplemented,
          ObjectNotFound,
          DataNotFound 
  {
      String aMethodName = "ValidateFacility2: logOutput()";
      log(LOG_DEBUG_LEVEL_1, ">" + aMethodName);

      // aReturnObject
      try 
      {
          log(LOG_OUTPUT_DATA, "ValidateFacility2Return=<" + (new ValidateFacility2ReturnBisHelper(aReturnObject)).toString() + ">");
      }
      catch (Exception e) 
      {
          log(LOG_OUTPUT_DATA, "aReturnObject<null>");
      }
      finally 
      {
          log(LOG_DEBUG_LEVEL_1, "<" + aMethodName);
      }
  }
}
 