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
//# 11/02/2007  Doris Sunga			 LS6: CR14149: BBNMS FOR MFI 
//############################################################################
package com.sbc.eia.bis.BusinessInterface.rm.SOAC;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrderConstants;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.Severity;

import java.util.Hashtable;

import javax.mail.MessagingException;

/**
 * @author op1664
 */
public class SOAC_BBNMSEmailSender extends SOACEmailSender
{
   private static SOAC_BBNMSEmailSender singleton = null;

   /**
    * This method will create a new instance of SOAC_BBNMSEmailSender if one don't exist.
    * @param aContext
    * @param aUtility
    * @param aParsedFCIF
    * @param aProperties
    * @return
    */
   public static SOACEmailSender getInstance(BisContext aContext,
         Utility aUtility, SOACServiceOrderResponseParser aParsedFCIF,
         Hashtable aProperties, Logger aLogger)
   {
      if(singleton == null)
      {
         singleton = new SOAC_BBNMSEmailSender(aContext, aUtility, aParsedFCIF,
               aProperties, aLogger);
      }
      return singleton;
   }
   
   public static SOACEmailSender getInstance(BisContext aContext,
           Utility aUtility, SOACServiceOrderResponseParser aParsedFCIF,
           Hashtable aProperties)
     {
        if(singleton == null)
        {
           singleton = new SOAC_BBNMSEmailSender(aContext, aUtility, aParsedFCIF,
                 aProperties);
        }
        return singleton;
     }   
   /**
    * Private contructor, only use by SOAC_BBNMSEmailSender.java
    * @param aContext
    * @param aUtility
    * @param aParsedFCIF
    * @param aProperties
    */
   private SOAC_BBNMSEmailSender(BisContext aContext, Utility aUtility,
         SOACServiceOrderResponseParser aParsedFCIF, Hashtable aProperties, Logger aLogger)
   {
      super(aContext, aUtility, aParsedFCIF, aProperties, aLogger);
   }
   
   /**
    * Private contructor, only use by SOAC_BBNMSEmailSender.java
    * @param aContext
    * @param aUtility
    * @param aParsedFCIF
    * @param aProperties
    */
   private SOAC_BBNMSEmailSender(BisContext aContext, Utility aUtility,
           SOACServiceOrderResponseParser aParsedFCIF, Hashtable aProperties)
   {
        super(aContext, aUtility, aParsedFCIF, aProperties);
   }
   
   /**
    * This method is empty because BBNMS do not get an email in the normal case
    */
   public void send()
   {

   }
   /**
    * The email will be send to BBNMS when response failed 
    */
   public void sendWhenReponseFailed() throws SystemFailure, Exception
   {
      String myMethodNameName = "SOAC_BBNMSEmailSender: sendWhenResponseFailed()";
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
         //LS6
         BooleanOpt LSTIndicator = parsedFCIF.getLSTIndicator();

         String eBody = "A pending service order, or an existing service, may require your attention."
               + "\nLFACS reports that assignments may have changed on:"
               + "\nOrder Number:              "
               + ClientOrderNum
               + "\nDue date:                  "
               + DueDate
               + "\nOrder Action Number:   	   "
               + ClientOrderActionNum
               + "\nLS Circuit ID:             "
               + LSCircuitID
               + "\nTelephone Number:          "
               + TelephoneNum
               + "\nSOAC Status Type:          "
               + StatusCode
               + "\nLST Indicator:             "
               + LSTIndicator.theValue()
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

         this.SendEmailRequest(eSubject, eBody);

      }
      catch(MessagingException e)
      {
         myUtility.log(LogEventId.ERROR,
               "Found error in SOAC_BBNMSEmailSender: sendWhenResponseFailed: ["
                     + e.getMessage() + "]");

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
    * 
    * @return the receiver email for BBNMS
    */
   public String getReceiverEmailPropertyName()
   {
      return "BBNMS_REPLY_EMAIL_ADDRESS";
   }

   public void sendWhenReponseFailed(SOACServiceOrderResponseParser parsedFCIF) throws SystemFailure, Exception
   {
      String myMethodNameName = "SOAC_BBNMSEmailSender: sendWhenResponseFailed()";
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
         String OrderActionType = "NA";
         String ResendIndicator = "NA";
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

         BooleanOpt LSTIndicator = parsedFCIF.getLSTIndicator();
  
         if ((parsedFCIF.getOrderActionType() != null) && (!parsedFCIF.getOrderActionType().trim().equals("")))
              OrderActionType = parsedFCIF.getOrderActionType();
             
         ResendIndicator = parsedFCIF.getResendIndicator().equals("R") ? "Resend" : "NA";
       
         
         String eBody = "A pending service order, or an existing service, may require your attention."
               + "\nLFACS reports that assignments may have changed on:"
               + "\nOrder Number:              "
               + ClientOrderNum
               + "\nDue date:                  "
               + DueDate
               + "\nOrder Action Number:   	   "
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
               + "\nLST Indicator:             "                             
               + LSTIndicator.theValue()
         	   + "\nVLAN Affecting:            NA"
               + "\nResend Indicator:          "
               + ResendIndicator
               + "\nNetwork Type:              "
               + parsedFCIF.getNetworkType()
               + "\nPASS Type:                 "
               + OrderActionType
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

         this.SendEmailRequest(eSubject, eBody);
         
         myUtility.log(LogEventId.INFO_LEVEL_1, "Successful sending email.");         

      }
      catch(MessagingException e)
      {
         myUtility.log(LogEventId.ERROR,
               "Found error in SOAC_BBNMSEmailSender: sendWhenResponseFailed: ["
                     + e.getMessage() + "]");

         if(context == null)
            throw e;
         else
         {
            myUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                  "Exception message: " + e.getMessage(), (String)myProperties
                        .get("BIS_NAME").toString(), Severity.UnRecoverable);
         }
      }
      finally
      {
         myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
      }   

   }
}