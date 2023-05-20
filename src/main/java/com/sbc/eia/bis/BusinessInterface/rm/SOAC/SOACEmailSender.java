//$Id: SOACEmailSender.java,v 1.3 2007/11/12 23:15:48 ds4987 Exp $
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
//# 11/7/2007	   Ott Phannavong MQC 77874: LSTindicator in the email format was not initialized for the next client. Created setParsedFCIF().
//#								  MQC 77469: PFAO: mismatch correlation ID. Created setLogger() and setUtility()
//# 11/10/2007  Doris Sunga			 LS6: CR14149: BBNMS FOR MFI
//############################################################################
package com.sbc.eia.bis.BusinessInterface.rm.SOAC;

import java.util.Hashtable;
import java.util.Properties;

import javax.mail.MessagingException;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.Emailer;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.SystemFailure;

/**
 * @author op1664
 */
public abstract class SOACEmailSender
{
   protected BisContext context = null;
   protected static Utility myUtility = null;
   protected static Logger myLogger = null;
   protected SOACServiceOrderResponseParser parsedFCIF = null;
   protected Hashtable myProperties = null;
   public final static String[] SOACErrorCodes =
   {"FMIS", "FPLK", "FNAS", "FNIA", "FNIW", "FANR", "ENPO", "ELKT", "EHDR",
         "ESOS", "EDUP", "ESOI", "FMIU", "FPLU"};

   public final static String[] RMExceptionDesc =
   {"FACS Manual Assistance Solicited", "FACS Partial Lockout Solicited",
         "NO assignment section produced", "assignment not required",
         "Not a FACS Wire Center", "FACS assignments no longer required",
         "No Pending SO", "Lockout Violation", "Message Control Header Error",
         "SO sequence Error", "Duplicate SO Detected", "SO Image Error",
         "FACS Manual Assistance Unsolicited",
         "FACS Partial Lockout Unsolicited"};
   public final static String[] SOACStatusCodes =
   {"FANK", "FACH", "FANC", "FDND"};
   public final static String[] SOACStatusDesc =
   {"FACS assignment - no prior knowledge", "FACS assignments changed",
         "FACS assignment unchanged", "FACS assignment - do not distribute"};
   public static final String MAIL_SERVER_PROP = "mail.smtp.host";
   public static final String MAIL_TRANSPORT_PROTOCOL_PROP = "mail.transport.protocol";
   public static final String SECONDARY_MAIL_SERVER_PROP = "secondary.mail.smtp.host";
   public static final String MAIL_TRANSPORT_PROTOCOL = "smtp";
   protected String[] MAIL_TO;
   protected String MAIL_FROM;
   private String transactionName = "";
   /**
    *  sub class have overide this method
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
      return null;
   }    
   /**
    *
    * @param aContext
    * @param aUtility
    * @param aParsedFCIF
    * @param aProperties
    */
   protected SOACEmailSender(BisContext aContext, Utility aUtility,
         SOACServiceOrderResponseParser aParsedFCIF, Hashtable aProperties, Logger aLogger)
   {
      String myMethodNameName = "SOACEmailSender: SOACEmailSender()";
      aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

      context = aContext;
      myUtility = aUtility;
      parsedFCIF = aParsedFCIF;
      myProperties = aProperties;
      myLogger = aLogger;
      aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
   }
   
   protected SOACEmailSender(BisContext aContext, Utility aUtility,
           SOACServiceOrderResponseParser aParsedFCIF, Hashtable aProperties)
     {
        String myMethodNameName = "SOACEmailSender: SOACEmailSender()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

        context = aContext;
        myUtility = aUtility;
        parsedFCIF = aParsedFCIF;
        myProperties = aProperties;
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
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

   /**
    * Send email when reponse failed.
    * @throws SystemFailure
    * @throws Exception
    */
   public void sendWhenReponseFailed(SOACServiceOrderResponseParser parsedFCIF ) throws SystemFailure, Exception
   {

   }
   
   /**
    * Method getExceptionDesc.
    * @param aStatusCode
    * @return String
    */
   public static String getExceptionDesc(String aStatusCode,
         SOACServiceOrderResponseParser parsedFCIF)
   {
      String myMethodName = "SOACEmailSender: getExceptionDesc()";
      myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

      String retVal = "SOAC System failure. ";

      if(parsedFCIF.isCombinationResponse() == true)
         retVal = "RM Invalid Data. "
               + parsedFCIF.getCombinationResponseExceptionMessage();
      else
      {
         for(int i = 0; i < SOACErrorCodes.length; i++)
         {
            if(aStatusCode.substring(0, 4).equalsIgnoreCase(SOACErrorCodes[i]))
               retVal = RMExceptionDesc[i];
         }
      }
      myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
      return retVal;
   }
   /**
    * Method getStatusDesc.
    * @param aStatusCode
    * @return String
    */
   public static String getStatusDesc(String aStatusCode,
         SOACServiceOrderResponseParser parsedFCIF)
   {
      String myMethodName = "SOACEmailSender: getStatusDesc()";
      myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

      String retVal = "";

      for(int i = 0; i < SOACStatusCodes.length; i++)
      {
         if(aStatusCode.substring(0, 4).equalsIgnoreCase(SOACStatusCodes[i]))
            retVal = SOACStatusDesc[i];
      }
      myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
      return retVal;
   }
   /**
    * Method SendEmailRequest.
    * @param aSubject
    * @param aBody
    * @throws MessagingException
    */
   public void SendEmailRequest(String aSubject, String aBody)
         throws MessagingException
   {
      String myMethodName = "SOACEmailSender: SendEmailRequest()";
      myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

      Properties emailProperties = new Properties();
      //set the email props.
      String primaryMailHost = (String)myProperties.get("PRIMARY_SMTP_SERVER");

      if(primaryMailHost != null)
      {
         primaryMailHost = primaryMailHost.trim();
      }
      else
      {
         primaryMailHost = "";
      }
      emailProperties.put("mail.smtp.host", primaryMailHost);

      String secondaryMailHost = (String)myProperties
            .get("SECONDARY_SMTP_SERVER");
      if(secondaryMailHost != null)
      {
         secondaryMailHost = secondaryMailHost.trim();

      }
      else
      {
         secondaryMailHost = "";
      }
      emailProperties.put("secondary.mail.smtp.host", secondaryMailHost);

      if((primaryMailHost.equals("")) && (secondaryMailHost.equals("")))
      {
         throw new MessagingException("Missing Both Primary SMTP Server "
               + "and  the Secondary SMTP Server Names. "
               + "Require atleast one SMTP Server Name");
      }
      emailProperties
            .put(MAIL_TRANSPORT_PROTOCOL_PROP, MAIL_TRANSPORT_PROTOCOL);

      //now Instantiate and send Email.
      Emailer aEmailer = new Emailer(myUtility, this.myProperties);
      aEmailer.setEmailConfig(emailProperties);
      aEmailer.setSecondarySMTPServerPropName(SECONDARY_MAIL_SERVER_PROP);

      String senderEmailPropertyName = getSenderEmailPropertyName();
      String senderEmail = (String)myProperties.get(senderEmailPropertyName);
      MAIL_FROM = senderEmail;

      String receiverEmailPropertyName = getReceiverEmailPropertyName();
      String receiverEmail = (String)myProperties
            .get(receiverEmailPropertyName);

      if(receiverEmail != null && receiverEmail.length() > 0)
      {
         MAIL_TO = receiverEmail.split(",");
         aEmailer.sendEmail(MAIL_TO, MAIL_FROM, aSubject, aBody);
      }
      else
      {
         myUtility.log(LogEventId.INFO_LEVEL_2, "There is no destination email addresses.");
      }
      myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

   }
   /**
    * @return - reciever email return depending on who the client is
    */
   public String getReceiverEmailPropertyName()
   {
      /*
       * This method is mean to be call directly.
       * Must overide the method in the subclass to use it.   
       */
      return null;
   }
   /**
    * @return - the sender email address
    */
   public String getSenderEmailPropertyName()
   {
      return "SENDER_EMAIL_ADDRESS";
   }
   
   /**
    * @return Returns the transactionName.
    */
   public String getTransactionName()
   {
      return transactionName;
   }
   /**
    * @param transactionName The transactionName to set.
    */
   public void setTransactionName(String aTransactionName)
   {
     transactionName = aTransactionName;
   }
   /**
    * @param parsedFCIF The parsedFCIF to set.
    */
   public void setParsedFCIF(SOACServiceOrderResponseParser aParsedFCIF)
   {
      parsedFCIF = aParsedFCIF;
   }
   /**
    * @param myLogger The myLogger to set.
    */
   public void setLogger(Logger aLogger)
   {
      SOACEmailSender.myLogger = aLogger;
   }
   /**
    * @param myUtility The myUtility to set.
    */
   public void setUtility(Utility aUtility)
   {
      SOACEmailSender.myUtility = aUtility;
   }
}