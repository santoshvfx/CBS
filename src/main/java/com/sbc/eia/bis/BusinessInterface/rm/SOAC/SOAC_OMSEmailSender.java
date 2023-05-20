//$ld$
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
//#      © 2007-2010 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 08/23/2007	   Ott Phannavong	Creation
//############################################################################
package com.sbc.eia.bis.BusinessInterface.rm.SOAC;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;

/**
 * @author op1664
 *
 */
public class SOAC_OMSEmailSender extends SOACEmailSender
{
   private static SOAC_OMSEmailSender singleton = null;
   /**
    * This method will create a new instance of SOAC_OMSEmailSender if one don't exist.
    */
   public static SOACEmailSender getInstance(BisContext aContext,
         Utility aUtility, SOACServiceOrderResponseParser aParsedFCIF,
         Hashtable aProperties, Logger aLogger)
   {
      String aMethodNameName = "SOAC_OMSEmailSender: getInstance()";
      aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodNameName);

      if(singleton == null)
      {
         return new SOAC_OMSEmailSender(aContext, aUtility, aParsedFCIF,
               aProperties, aLogger);
      }
      aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodNameName);
      return singleton;
   }
   /**
    * Private contructor, only use by SOAC_OMSEmailSender.java
    * @param aContext
    * @param aUtility
    * @param aParsedFCIF
    * @param aProperties
    */
   private SOAC_OMSEmailSender(BisContext aContext, Utility aUtility,
         SOACServiceOrderResponseParser aParsedFCIF, Hashtable aProperties, Logger aLogger)
   {
      super(aContext, aUtility, aParsedFCIF, aProperties, aLogger);
   }
   /**
    * This method will send email to the client.
    * @param aContext
    * @param aUtility
    * @param aParsedFCIF
    * @throws SystemFailure
    * @throws Exception
    */
   public void send() throws SystemFailure, Exception
   {

      String myMethodNameName = "SOACEmailSender: send()";
      myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

      try
      {
         String eSubject = "Lightspeed Assignment Problem";

         String ClientOrderNum = "NA";
         String DueDate = "NA";
         String ClientOrderActionNum = "NA";
         String LSCircuitID = "NA";
         String TelephoneNum = "NA";
         String StatusCode = "NA";
         String SOACServiceOrderNum = "NA";
         String SOACErrorMsg = "NA";
         StringBuffer AssgnSectionStrBuf = new StringBuffer("");

         try
         {
            ClientOrderNum = parsedFCIF.getOMSOrderNum().trim();
         }
         catch(NullPointerException e)
         {
         }
         try
         {
            DueDate = parsedFCIF.getDueDate().trim();
         }
         catch(NullPointerException e)
         {
         }
         try
         {
            ClientOrderActionNum = parsedFCIF.getOMSOrderActionNum().trim();
         }
         catch(NullPointerException e)
         {
         }
         try
         {
            LSCircuitID = parsedFCIF.getLSCircuitID().trim();
         }
         catch(NullPointerException e)
         {
         }
         try
         {
            TelephoneNum = parsedFCIF.getTelephoneNum().trim();
         }
         catch(NullPointerException e)
         {
         }
         try
         {
            StatusCode = parsedFCIF.getStatusCode().substring(0, 4).trim();
         }
         catch(NullPointerException e)
         {
         }
         try
         {
            SOACServiceOrderNum = parsedFCIF.getSOACServiceOrderNum().trim();
         }
         catch(NullPointerException e)
         {
         }
         try
         {
            SOACErrorMsg = parsedFCIF.getExceptionMessage().trim();
         }
         catch(NullPointerException e)
         {
         }
         try
         {
            String[] AsgnmntArray = parsedFCIF.getAssgnSectionString();
            for(int i = 0; i < AsgnmntArray.length; i++)
               AssgnSectionStrBuf.append(AsgnmntArray[i] + "\n");
         }
         catch(Exception e)
         {
            AssgnSectionStrBuf.append("NA");
         }
         String eBody = "A pending service order, or an existing service, may require your attention."
               + "\nLFACS reports that assignments may have changed on:"
               + "\nOrder Number:             "
               + ClientOrderNum
               + "\nDue date:                  "
               + DueDate
               + "\nOrder Action Number:       "
               + ClientOrderActionNum
               + "\nLS Circuit ID:             "
               + LSCircuitID
               + "\nTelephone Number:          "
               + TelephoneNum
               + "\nSOAC Status Type:          "
               + StatusCode
               + "\nSOAC Status Description:   "
               + (getExceptionDesc(StatusCode, parsedFCIF) != "" ? getExceptionDesc(
                     StatusCode, parsedFCIF)
                     : getStatusDesc(StatusCode, parsedFCIF))
               + "\nNetwork Type:              "
               + parsedFCIF.getNetworkType()
               + "\nPASS Type:                 NA"
               + "\nSOAC Service Order Number: "
               + SOACServiceOrderNum
               + "\nError Section:             "
               + SOACErrorMsg
               + "\nCurrent Assignment Section:\n"
               + AssgnSectionStrBuf.toString()
               + "\n\nPlease contact the appropriate assignment center and if necessary issue a change order, or a"
               + "\nsupplement on the pending order, to resolve this problem.  If there is no pending order, the change"
               + "\norder you issue should be today.";

         myUtility.log(LogEventId.INFO_LEVEL_1, "Sending email: [" + eBody
               + "]");

         SendEmailRequest(eSubject, eBody);
      }
      catch(Exception e)
      {
         myUtility.log(LogEventId.ERROR,
               "Found error in SOACEmailSender:sendEmail: [" + e.getMessage()
                     + "]");

         if(context == null)
            throw e;
         else
         {
            myUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                  "Exception message: " + e.getMessage(), (String)myProperties
                        .get("BIS_NAME").toString(), Severity.UnRecoverable);
         }
      }
      myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
   }
   /**
    * @return - the reciever email for OMS
    */
   public String getReceiverEmailPropertyName()
   {
      return "RECEPIENT_EMAIL_ADDRESS";
   }

}