//$ld$
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2007-2010 AT&T Intellectual Property All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 08/23/2007	   Ott Phannavong	Creation
//# 11/07/2007	   Ott Phannavong   MQC 77874: PFAO: LSTindicator in the email format was not initialized for the next client. In getInstance(), called setParsedFCIF()	 
//# 								MQC 77469: PFAO: mismatch correlation ID. In getInstance(), called setLogger() and setUtility()
//# 11/10/2007 Doris Sunga			LS6:CR14149 - MFI code
//############################################################################
package com.sbc.eia.bis.BusinessInterface.rm.SOAC;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrderConstants;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;

/**
 * @author op1664
 */
public class SOACEmailSenderFactory
{
   /**
    * Determine the email sender and create an instance of it.
    * @param aContext
    * @param aUtility
    * @param aParsedFCIF
    * @param aProperties
    * @return an instance of an email sender
    * @throws InvalidData
    * @throws AccessDenied
    * @throws BusinessViolation
    * @throws SystemFailure
    * @throws NotImplemented
    * @throws ObjectNotFound
    * @throws DataNotFound
    */
   public static SOACEmailSender getInstance(BisContext aContext,
         Utility aUtility, SOACServiceOrderResponseParser aParsedFCIF,
         Hashtable aProperties, String applicationID, Logger aLogger) throws InstantiationException, InvalidData,
         AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
         ObjectNotFound, DataNotFound
   {
      String aMethodNameName = "SOACEmailSenderFactory: getInstance()";
      aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodNameName);
      SOACEmailSender emailSender = null;
      if(applicationID == null)
      {
         aUtility.throwException(ExceptionCode.ERR_RM_INVALID_DATA,
               "Invalid application ID " + applicationID, (String)aProperties
                     .get("BIS_NAME").toString(), Severity.UnRecoverable);
      }
      if(applicationID.equals(SvcOrderConstants.BBNMS_APPLICATIONID))
      {
         emailSender = SOAC_BBNMSEmailSender.getInstance(aContext, aUtility,
               aParsedFCIF, aProperties, aLogger);
      }
      else if(applicationID.equals(SvcOrderConstants.OMS_APPLICATIONID))
      {
         emailSender = SOAC_OMSEmailSender.getInstance(aContext, aUtility,
               aParsedFCIF, aProperties, aLogger);
      } 
      else
      {
         emailSender = new NullEmailSender(aContext, aUtility, aParsedFCIF, aProperties, aLogger);
         
      }
      emailSender.setParsedFCIF(aParsedFCIF);
      emailSender.setLogger(aLogger);
      emailSender.setUtility(aUtility);
      aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodNameName);
      
      return emailSender;
   }
   
   /**
    * Determine the email sender and create an instance of it.
    * @param aContext
    * @param aUtility
    * @param aParsedFCIF
    * @param aProperties
    * @return an instance of an email sender
    * @throws InvalidData
    * @throws AccessDenied
    * @throws BusinessViolation
    * @throws SystemFailure
    * @throws NotImplemented
    * @throws ObjectNotFound
    * @throws DataNotFound
    */
   public static SOACEmailSender getInstanceValue(BisContext aContext,
         Utility aUtility, SOACServiceOrderResponseParser aParsedFCIF,
         Hashtable aProperties, com.sbc.bccs.utilities.Logger myLogger) 
         throws InstantiationException, InvalidData,
         AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
         ObjectNotFound, DataNotFound
   {
       String aMethodNameName = "SOACEmailSenderFactory: getInstance()";
       aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodNameName);
       
       SOACEmailSender emailSender = SOAC_BBNMSEmailSender.getInstance(aContext, aUtility,
               aParsedFCIF, aProperties, myLogger); 
       
       aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodNameName);

       return emailSender;
   }
   
   private static class NullEmailSender extends SOACEmailSender 
   {

      /**
       * @param aContext
       * @param aUtility
       * @param aParsedFCIF
       * @param aProperties
       * @param aLogger
       */
      protected NullEmailSender(BisContext aContext, Utility aUtility, SOACServiceOrderResponseParser aParsedFCIF, Hashtable aProperties, Logger aLogger)
      {
         super(aContext, aUtility, aParsedFCIF, aProperties, aLogger);
         aLogger.log(LogEventId.DEBUG_LEVEL_2, "No email send.");
      }
      /**
       * Send email to client
       * @throws SystemFailure
       * @throws Exception
       */
      public void send() throws SystemFailure, Exception
      {
         
      }
      /**
       * Send email when reponse failed.
       * @throws SystemFailure
       * @throws Exception
       */
      public void sendWhenReponseFailed() throws SystemFailure, Exception
      {

      }
   }
}
