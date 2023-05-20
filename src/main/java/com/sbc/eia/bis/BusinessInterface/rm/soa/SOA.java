//$Id: SOA.java,v 1.16 2008/07/25 17:01:35 sc8468 Exp $
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
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 06/02/2006  Jyothi Jasti         Creation for LS3.
//# 07/17/2006  Jyothi Jasti         Changes for handling date in GMT. 
//# 07/27/2006  Jyothi Jasti         Added status while handling NotifySvActivityFailure.
//# 04/12/2007	Jon Costa			 Changed addSOAPMessageTags() call to capture return value.
//# 06/03/2007  Rene Duka            DR67732: SATNPS: fixed incorrect SOA orderNum mapping.
//# 06/13/2007  Rene Duka            DR67970: PTNPN: fixed concatenate the order type and serial number to be the SOAC Service Order number.
//# 10/30/2007  Mrinalini Peddi      DR75491: PTNPN: logging the xml when failed to send to target system   
//# 12/14/2007  Mrinalini Peddi      PR20939817: SOA to BIS error code mapping.
//# 5/13/2008   Ott Phannavong		 LS7 CR18595 modified buildMessage() to accept NotifySvDisconnect message

package com.sbc.eia.bis.BusinessInterface.rm.soa;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utility.soaphelpers.SoapParserHelper;
import com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo.OMSEmailHelper;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.oms.OMSService;
import com.sbc.eia.bis.embus.service.oms.access.OMSHelper;
import com.sbc.eia.bis.embus.service.soa.SOAService;
import com.sbc.eia.bis.embus.service.soa.SendRequestToSOA;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvActivateImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvActivityFailureImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvCancelImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvCreateAckImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvDisconnectImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvPTOImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvReleaseAckImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33DefnOrderNumberDefImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33DefnTnRangeDefImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33DefnTransactionInfoDefImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ServerOrderPathSubscriptionRequestsActivateSvImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResultImpl;
import com.sbc.eia.bis.facades.rm.transactions.PublishTNPortingNotification.PublishTNPortingNotification;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.database.OrderCorrectionSuffixRow;
import com.sbc.eia.bis.rm.database.OrderCorrectionSuffixTable;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm.PublishTNPortingNotificationReturn;
import com.sbc.eia.idl.rm.SendActivateTNPortingSubscriptionMsgReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._publishTNPortingNotificationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishTNPortingNotificationResponseBISMsg;
import com.sbc.eia.idl.rm_ls_types.NotifyActivityTypeValues;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberRequestStatusSeqOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.vicunalite.vaxb.VAXBDocumentWriter;

public class SOA
{

   protected static Properties soaToBisFailureCodeMappings = null;
   private Utility utility = null;
   private OMSService omsService = null;
   private SOAService soaService = null;
   private Hashtable properties = null;
   private String ruleFile = null;
   private PublishTNPortingNotification aPublishTNPortingNotification = null;
   private OMSEmailHelper aOMSEmailHelper = null;

   /**
    * constructor
    * @param aUtility
    * @param aProperties
    */
   public SOA(Utility aUtility, Hashtable aProperties)
   {
      properties = aProperties;
      utility = aUtility;
      ruleFile = (String) properties
            .get(SendRequestToSOA.SOA_EXCEPTION_RULE_FILE_TAG);
   }

   /**
    * the method performs the business logic.
    * @param aContext
    * @param aSOACServiceOrderNumber
    * @param aSOACServiceOrderCorrectionSuffix
    * @param aLocalServiceProviderId
    * @param aTelephoneNumbers
    * @param aLogger
    * @return the SendActivateTNPortingSubscriptionMsgReturn 
    * @throws InvalidData
    * @throws AccessDenied
    * @throws BusinessViolation
    * @throws SystemFailure
    * @throws NotImplemented
    * @throws ObjectNotFound
    * @throws DataNotFound
    */
   public SendActivateTNPortingSubscriptionMsgReturn sendActivateTNPortingSubscriptionMsg(
         BisContext aContext, String aSOACServiceOrderNumber,
         String aSOACServiceOrderCorrectionSuffix,
         String aLocalServiceProviderId, String[] aTelephoneNumbers,
         Logger aLogger) throws InvalidData, AccessDenied, BusinessViolation,
         SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
   {

      String methodName = "SOA:sendActivateTNPortingSubscriptionMsg()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      if(soaService == null)
      {
         soaService = new SOAService(properties, utility);
      }

      SOAResponseHelper responseHelper = new SOAResponseHelper(utility,
            properties);

      int size = aTelephoneNumbers.length;
      for(int i = 0; i < size; i++)
      {

         //build SOA request 
         OpIdl33ServerOrderPathSubscriptionRequestsActivateSvImpl request = buildRequest(
               aContext, aSOACServiceOrderNumber,
               aSOACServiceOrderCorrectionSuffix, aLocalServiceProviderId,
               aTelephoneNumbers[i]);

         // send request message to SOA		
         OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResultImpl response = sendRequest(
               aContext, request);
         //validate the SOA response	
         boolean isFailureResponse = responseHelper.processResponse(response);

         /*	CR 19539 -- Update the Order Correction Suffix, only if we got 
          * a response from SOA. Update DB only once per transaction.
         */
         if(i == 0)
         {
        	try 
        	{
        		OrderCorrectionSuffixTable aOrderCorrectionSuffixTable = new OrderCorrectionSuffixTable();

        		aOrderCorrectionSuffixTable.UpdateOrderCorrectionSuffix(
							aContext, aSOACServiceOrderNumber,
							aSOACServiceOrderCorrectionSuffix, properties,
							utility);
        		aOrderCorrectionSuffixTable = null;
			} 
        	catch (Exception e) 
        	{
					utility.log(LogEventId.INFO_LEVEL_2,
							"Non-fatal Exception: " + e + " Okay to proceed.");
			}
         }         
         
         // if failure response from SOA send message to client.
         if(isFailureResponse)
         {
            //build return
            PublishTNPortingNotificationReturn publishTNReturn = buildPublishTNPortingNotificationReturn(
                  response, aContext, aSOACServiceOrderNumber,
                  aSOACServiceOrderCorrectionSuffix, aLocalServiceProviderId,
                  aTelephoneNumbers[i]);

            //send message to the client
            EmailHelper emailHelper = new EmailHelper(utility, properties);
            try
            {
               String message = buildMessage(publishTNReturn, emailHelper);

               if(aPublishTNPortingNotification == null)
               {
                  aPublishTNPortingNotification = new PublishTNPortingNotification(
                        properties);
               }
               aPublishTNPortingNotification
                     .execute(aContext, message, aLogger);
            }
            catch(Exception e) // Catch IOException during IDL Object to XML conversion
            {
               // any errors send email
               utility.log(LogEventId.INFO_LEVEL_1,
                     "Failed to send message. So sending email.");
               sendEmail(emailHelper);
            }
         }
      }

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
      return buildAckResponse(aContext, aSOACServiceOrderNumber,
            aSOACServiceOrderCorrectionSuffix);
   }

   /**
    * publishTNPortingNotification method processes SOA response, create the SOAP message and send the SOAP message to OMS.
    * @param aContext
    * @param message
    * @param msgType
    */
   public void publishTNPortingNotification(BisContext aContext,
         String message, String msgType)
   {
      String methodName = "SOA:publishTNPortingNotification()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      EmailHelper emailHelper = new EmailHelper(utility, properties);

      try
      {
         //Synchronous SOA response for SendActivateTNPortingNotificationMsg is processed and the message is already created. Need to create message for Asynchornous response.  
         if(!msgType
               .equals(SOAConstants.PUBLISH_TN_PORTING_NOTIFICATION_RESPONSE_BIS_MSG))
         {
            message = buildMessage(aContext, message, msgType, emailHelper);
         }

         if(message != null)
         {
            sendMessage(aContext, message, emailHelper);
         }

      }
      catch(Exception e)
      {
         utility.log(LogEventId.INFO_LEVEL_1, "MISSED_TARGET_XML: " + message);

         utility.log(LogEventId.DEBUG_LEVEL_1,
               "Message is not sent to client. " + e.getMessage());
      }

      if(emailHelper.isSendEmail() == true)
         sendEmail(emailHelper);

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
   }

   /**
    * buildMessage creates SOAP message to be sent to OMS using SOA response message.
    * @param message
    * @param msgType
    * @param emailHelper
    * @return the message
    */
   public String buildMessage(BisContext context, String message,
         String msgType, EmailHelper emailHelper) throws Exception
   {
      String methodName = "SOA:buildMessage()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      SOAResponseHelper responseHelper = new SOAResponseHelper(utility,
            properties);
      String rmMessage = null;
      PublishTNPortingNotificationReturn publishTNReturn = null;
      boolean isValidResponse = true;
      Object response = null;

      try
      {
         response = responseHelper.decode(message);
      }
      catch(Exception e)
      {
         utility.log(LogEventId.ERROR, ">"
               + SOAConstants.REASONS_FOR_SENDING_EMAIL[0] + e.getMessage());
         emailHelper.setSendEmail(true);
         emailHelper.setRootCause(SOAConstants.REASONS_FOR_SENDING_EMAIL[0]);
         emailHelper.setSoaMessage(message);
         throw e;
      }
      if(msgType.equals(NotifyActivityTypeValues.NOTIFY_ACTIVATE))
      {
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvActivateImpl tempResponse = (OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvActivateImpl) response;
         
         publishTNReturn = buildPublishTNPortingNotificationReturn(context,
               msgType, tempResponse);

         isValidResponse = responseHelper.validateMessage(tempResponse
               .getOrderNum(), emailHelper);
      }
      else if(msgType.equals(NotifyActivityTypeValues.NOTIFY_CREATE))
      {
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvCreateAckImpl tempResponse = (OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvCreateAckImpl) response;

         publishTNReturn = buildPublishTNPortingNotificationReturn(context,
               msgType, tempResponse, responseHelper);

         isValidResponse = responseHelper.validateMessage(tempResponse
               .getOrderNum(), emailHelper);
      }
      else if(msgType.equals(NotifyActivityTypeValues.NOTIFY_RELEASE))
      {
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvReleaseAckImpl tempResponse = (OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvReleaseAckImpl) response;

         publishTNReturn = buildPublishTNPortingNotificationReturn(context,
               msgType, tempResponse, responseHelper);

         isValidResponse = responseHelper.validateMessage(tempResponse
               .getOrderNum(), emailHelper);
      }
      else if(msgType.equals(NotifyActivityTypeValues.NOTIFY_PORT_TO_ORIGINAL))
      {
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvPTOImpl tempResponse = (OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvPTOImpl) response;

         publishTNReturn = buildPublishTNPortingNotificationReturn(context,
               msgType, tempResponse);

         isValidResponse = responseHelper.validateMessage(tempResponse
               .getOrderNum(), emailHelper);
      }
      else if(msgType.equals(NotifyActivityTypeValues.NOTIFY_CANCEL))
      {
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvCancelImpl tempResponse = (OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvCancelImpl) response;

         publishTNReturn = buildPublishTNPortingNotificationReturn(context,
               msgType, tempResponse);

         isValidResponse = responseHelper.validateMessage(tempResponse
               .getOrderNum(), emailHelper);
      }
      else if(msgType.equals(SOAConstants.NOTIFY_SV_ACTIVITY_FAILURE))
      {
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvActivityFailureImpl tempResponse = (OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvActivityFailureImpl) response;

         boolean isRequiredMessage = responseHelper.filterMessage(tempResponse);
         if(isRequiredMessage)
         {
            publishTNReturn = buildPublishTNPortingNotificationReturn(context,
                  msgType, tempResponse);

            isValidResponse = responseHelper.validateMessage(tempResponse
                  .getOrderNum(), emailHelper);
         }
         else
         {
            utility
                  .log(LogEventId.INFO_LEVEL_1,
                        "RMBIS received a message from SOA that is not required to send to the client");
            throw new Exception(
                  "RMBIS received a message from SOA that is not required to send to the client");
         }
      }
      /*
       * LS7 CR18595
       * The client can not accept NotifySvDisconnect message so,
       * we're sending them NotifySvActivate.
       */
      else if(msgType.equals(SOAConstants.NOTIFY_SV_DISCONNECT))
      {
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvDisconnectImpl tempResponse = (OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvDisconnectImpl) response;

         publishTNReturn = buildPublishTNPortingNotificationReturn(context,
               msgType, tempResponse);

         isValidResponse = responseHelper.validateMessage(tempResponse
               .getOrderNum(), emailHelper);
      }
      emailHelper.setPublishTNPortingNotificationReturn(publishTNReturn);
      try
      {
         rmMessage = buildMessage(publishTNReturn, emailHelper);
      }
      catch(Exception e)
      {
         utility.log(LogEventId.ERROR, ">" + "Failed to create BIS message"
               + e.getMessage());
         emailHelper.setSendEmail(true);
         emailHelper.setRootCause(SOAConstants.REASONS_FOR_SENDING_EMAIL[2]);
         emailHelper.setSoaMessage(message);
         throw e;
      }

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
      return rmMessage;
   }

   /**
    * method builds the SOA request object using client input data
    * @param aContext
    * @param aSOACServiceOrderNumber
    * @param aSOACServiceOrderCorrectionSuffix
    * @param aLocalServiceProviderId
    * @param aTelephoneNumber
    * @return the ActivateSv request
    */
   private OpIdl33ServerOrderPathSubscriptionRequestsActivateSvImpl buildRequest(
         BisContext aContext, String aSOACServiceOrderNumber,
         String aSOACServiceOrderCorrectionSuffix,
         String aLocalServiceProviderId, String aTelephoneNumber)
   {

      String methodName = "SOA:buildRequest()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      OpIdl33DefnTransactionInfoDefImpl tranInfo = new OpIdl33DefnTransactionInfoDefImpl();
      tranInfo.setInitSpid(aLocalServiceProviderId.trim());

      ObjectPropertyManager opmContext = new ObjectPropertyManager(
            aContext.aProperties);
      tranInfo.setInitSystem(opmContext
            .getValue(BisContextProperty.APPLICATION).trim());
      tranInfo.setInitUser(opmContext.getValue(BisContextProperty.CUSTOMERNAME)
            .trim());

      OpIdl33DefnOrderNumberDefImpl orderNumber = new OpIdl33DefnOrderNumberDefImpl();
      //orderNumber.setOrdTyp(SOAConstants.ORDER_TYPE);
      //orderNumber.setSerNum(aSOACServiceOrderNumber.trim());
      orderNumber.setOrdTyp(aSOACServiceOrderNumber.substring(0, 1).trim());
      orderNumber.setSerNum(aSOACServiceOrderNumber.substring(1,
            aSOACServiceOrderNumber.length()).trim());
      orderNumber.setSupNum(aSOACServiceOrderCorrectionSuffix.trim());

      OpIdl33DefnTnRangeDefImpl tnRange = new OpIdl33DefnTnRangeDefImpl();
      tnRange.setTnValue(aTelephoneNumber);
      tnRange.setEndStation("");

      OpIdl33ServerOrderPathSubscriptionRequestsActivateSvImpl request = new OpIdl33ServerOrderPathSubscriptionRequestsActivateSvImpl();
      request.setTransInfo(tranInfo);
      request.setOrderNum(orderNumber);
      request.setTnRng(tnRange);

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
      return request;
   }

   /**
    * the method sends the request to SOA and gets the response from SOA
    * @param bisContext
    * @param request
    * @return the SOA response
    * @throws InvalidData
    * @throws AccessDenied
    * @throws BusinessViolation
    * @throws SystemFailure
    * @throws NotImplemented
    * @throws ObjectNotFound
    * @throws DataNotFound
    */
   private OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResultImpl sendRequest(
         BisContext bisContext,
         OpIdl33ServerOrderPathSubscriptionRequestsActivateSvImpl request)
         throws InvalidData, AccessDenied, BusinessViolation, SystemFailure,
         NotImplemented, ObjectNotFound, DataNotFound
   {
      String methodName = "SOA:sendRequest()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      SendRequestToSOA soaHelper = new SendRequestToSOA(utility, bisContext,
            properties, soaService);
      OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResultImpl response = soaHelper
            .sendRequest(request);

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
      return response;
   }

   /**
    * method builds and returns the Acknowledge response.
    * @param aContext
    * @return the SendActivateTNPortingSubscriptionMsg return 
    */
   private SendActivateTNPortingSubscriptionMsgReturn buildAckResponse(
         BisContext aContext, String aSOACServiceOrderNumber,
         String aSOACServiceOrderCorrectionSuffix)
   {

      String methodName = "SOA:buildAckResponse()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      //Add acknowledgment tag/value to BisContext
      ObjectPropertyManager aContextMgr = new ObjectPropertyManager(
            aContext.aProperties);
      aContextMgr.add("Acknowledgement", "Acknowledgement");
      aContext.aProperties = aContextMgr.toArray();

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
      return new SendActivateTNPortingSubscriptionMsgReturn(aContext, IDLUtil
            .toOpt((String) aSOACServiceOrderNumber), IDLUtil
            .toOpt((String) aSOACServiceOrderCorrectionSuffix),
            (TelephoneNumberRequestStatusSeqOpt) IDLUtil.toOpt(
                  TelephoneNumberRequestStatusSeqOpt.class, null));
   }

   /**
    * buildMessage converts the Java object to XML using VAXB and returns the XML message. 
    * @param publishTNReturn
    * @param emailHelper
    * @return the message
    * @throws IOException
    */
   private String buildMessage(
         PublishTNPortingNotificationReturn publishTNReturn,
         EmailHelper emailHelper) throws IOException
   {
      String methodName = "SOA:buildMessage()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      String message = null;
      emailHelper.setPublishTNPortingNotificationReturn(publishTNReturn);

      _publishTNPortingNotificationResponse publishTNResponse = new _publishTNPortingNotificationResponse();
      publishTNResponse.aPublishTNPortingNotificationReturn(publishTNReturn);

      try
      {
         message = VAXBDocumentWriter
               .encode(new _publishTNPortingNotificationResponseBISMsg(
                     publishTNResponse));
      }
      catch(IOException e)
      {
         utility.log(LogEventId.ERROR, ">" + "Failed to convert IDL to XML "
               + e.getMessage());
         emailHelper.setSendEmail(true);
         emailHelper.setRootCause(SOAConstants.REASONS_FOR_SENDING_EMAIL[2]);
         throw e;
      }

      message = addSOAPMessageTags(message,
            SOAConstants.PUBLISH_TN_PORTING_NOTIFICATION);
      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
      return message;
   }

   /**
    * addSOAPMessageTags method appends the SOAP message tags.
    * @param message
    * @param messageTag
    * @return the message
    */
   private String addSOAPMessageTags(String message, String messageTag)
   {
      String methodName = "SOA:addSOAPMessageTags()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      //verify the messageTags
      Properties messageTags = new Properties();
      messageTags.setProperty("embus:MessageTag", messageTag);
      messageTags.setProperty("embus:ApplicationID", "ApplicationID");
      messageTags.setProperty("embus:MessageID", "MessageID");
      messageTags.setProperty("embus:CorrelationID", "CorrelationID");
      messageTags.setProperty("embus:LoggingKey", "LoggingKey");
      messageTags.setProperty("embus:ConversationKey", "ConversationKey");
      messageTags.setProperty("embus:ResponseMessageExpiration", "0");

      message = SoapParserHelper.appendEMBUSSoapEnvelope(SoapParserHelper
            .removeTagsFromXML(message, "<vaxb:VAXB", "</vaxb:VAXB>"),
            messageTags);
      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
      return message;
   }

   /**
    * sendMessage sends the message to OMS using OMS EMBus service wrapper
    * @param aContext
    * @param message
    * @param emailHelper
    * @throws ServiceException
    */
   public void sendMessage(BisContext aContext, String message,
         EmailHelper emailHelper) throws ServiceException
   {

      String methodName = "SOA.sendMessage()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      try
      {
         if(omsService == null)
         {
            omsService = new OMSService(properties, utility);
         }

         utility.log(LogEventId.REMOTE_CALL, OMSHelper.OMS_SERVICE_NAME,
               OMSHelper.OMS_SERVICE_NAME + OMSHelper.OMS_VERSION,
               OMSHelper.OMS_SERVICE_NAME + OMSHelper.OMS_VERSION,
               OMSHelper.OMS_REQUEST);

         omsService.publishMessage(message, null);

      }
      catch(ServiceException se)
      {
         utility.log(LogEventId.ERROR, ">" + "ServiceException "
               + se.getMessage());
         emailHelper.setSendEmail(true);
         emailHelper.setRootCause(SOAConstants.REASONS_FOR_SENDING_EMAIL[3]);
         throw se;
      }
      finally
      {
         utility.log(LogEventId.REMOTE_RETURN, OMSHelper.OMS_SERVICE_NAME,
               OMSHelper.OMS_SERVICE_NAME + OMSHelper.OMS_VERSION,
               OMSHelper.OMS_SERVICE_NAME + OMSHelper.OMS_VERSION,
               OMSHelper.OMS_REQUEST);
      }
      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
   }

   /**
    * sendEmail method builds Email subject and Email body and sends the Email.
    * @param emailHelper 
    */
   public void sendEmail(EmailHelper emailHelper)
   {
      String methodName = "SOA:sendEmail()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      if(aOMSEmailHelper == null)
         aOMSEmailHelper = new OMSEmailHelper(utility, properties);

      if(emailHelper.isSendEmail() == true
            && emailHelper.isEmailSent() == false)
      {
         emailHelper.buildEmailMessage();

         try
         {
            aOMSEmailHelper.prepareAndSendEmail(emailHelper.getEmailSubject(),
                  emailHelper.getEmailBody());
            emailHelper.setEmailSent(true);
         }
         catch(Exception e)
         {
            utility.log(LogEventId.ERROR, "Failed to send Email");
         }
      }

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
   }

   /**
    * buildPublishTNPortingNotificationReturn
    * @param aContext
    * @param aNotifyActivityType
    * @param response
    * @return the PublishTNPortingNotificationReturn
    */
   private PublishTNPortingNotificationReturn buildPublishTNPortingNotificationReturn(
         BisContext aContext,
         String aNotifyActivityType,
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvActivityFailureImpl response)
   {
      String methodName = "SOA:buildPublishTNPortingNotificationReturn()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      String aSoacServiceOrderNumber = null;
      String aSOACServiceOrderCorrectionSuffix = null;
      String aLocalServiceProviderId = null;
      String aTelephoneNumber = null;
      String aSubscriptionVersionState = null;
      String aCauseCode = null;
      ExceptionData aStatus = null;

      try
      {
         aSoacServiceOrderNumber = response.getOrderNum().getOrdTyp().trim()
               + response.getOrderNum().getSerNum().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aSOACServiceOrderCorrectionSuffix = response.getOrderNum().getSupNum()
               .trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aLocalServiceProviderId = response.getInitSpid().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aTelephoneNumber = response.getTnRng().getTnValue().trim();
      }
      catch(NullPointerException e)
      {
      }

      aCauseCode = String.valueOf(response.getErrCode());

      aStatus = new ExceptionData(ExceptionCode.ERR_RM_SOA_ACTIVITY_FAILURE,
            "SOA Activity failure", IDLUtil.toOpt(SOAConstants.SOA),
            (SeverityOpt) IDLUtil.toOpt((SeverityOpt.class),
                  Severity.Recoverable));

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);

      return buildPublishTNPortingNotificationReturn(aContext,
            aSoacServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
            aNotifyActivityType, aLocalServiceProviderId, null, null,
            aTelephoneNumber, null, aCauseCode, null, aStatus, null);
   }

   /**
    * buildPublishTNPortingNotificationReturn
    * @param aContext
    * @param aNotifyActivityType
    * @param response
    * @return the PublishTNPortingNotificationReturn
    */
   private PublishTNPortingNotificationReturn buildPublishTNPortingNotificationReturn(
         BisContext aContext,
         String aNotifyActivityType,
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvActivateImpl response)
   {
      String methodName = "SOA:buildPublishTNPortingNotificationReturn()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      String aSoacServiceOrderNumber = null;
      String aSOACServiceOrderCorrectionSuffix = null;
      String aLocalServiceProviderId = null;
      String aTelephoneNumber = null;
      String aSubscriptionVersionState = null;

      try
      {
         aSoacServiceOrderNumber = response.getOrderNum().getOrdTyp().trim()
               + response.getOrderNum().getSerNum().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aSOACServiceOrderCorrectionSuffix = response.getOrderNum().getSupNum()
               .trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aLocalServiceProviderId = response.getInitSpid().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aTelephoneNumber = response.getTnRng().getTnValue().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aSubscriptionVersionState = response.getSubVerState().trim();
      }
      catch(NullPointerException e)
      {
      }
      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);

      return buildPublishTNPortingNotificationReturn(aContext,
            aSoacServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
            aNotifyActivityType, aLocalServiceProviderId, null, null,
            aTelephoneNumber, aSubscriptionVersionState, null, null, null, null);
   }

   /**
    * buildPublishTNPortingNotificationReturn
    * @param aContext
    * @param aNotifyActivityType
    * @param helper
    * @return the PublishTNPortingNotificationReturn
    */
   private PublishTNPortingNotificationReturn buildPublishTNPortingNotificationReturn(
         BisContext aContext,
         String aNotifyActivityType,
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvCreateAckImpl response,
         SOAResponseHelper helper)
   {
      String methodName = "SOA:buildPublishTNPortingNotificationReturn()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      String aSoacServiceOrderNumber = null;
      String aSOACServiceOrderCorrectionSuffix = null;
      String aLocalServiceProviderId = null;
      String aTelephoneNumber = null;
      String aOldServiceProviderId = null;
      String aNewServiceProviderId = null;
      EiaDate aDueDate = null;

      try
      {
         aSoacServiceOrderNumber = response.getOrderNum().getOrdTyp().trim()
               + response.getOrderNum().getSerNum().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aSOACServiceOrderCorrectionSuffix = response.getOrderNum().getSupNum()
               .trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aLocalServiceProviderId = response.getInitSpid().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aTelephoneNumber = response.getTnRng().getTnValue().trim();
      }
      catch(NullPointerException e)
      {
      }

      aDueDate = helper.buildEiaDate(response.getDueDateTime());

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);

      return buildPublishTNPortingNotificationReturn(aContext,
            aSoacServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
            aNotifyActivityType, aLocalServiceProviderId,
            aOldServiceProviderId, aNewServiceProviderId, aTelephoneNumber,
            null, null, aDueDate, null, null);
   }

   /**
    * buildPublishTNPortingNotificationReturn
    * @param aContext
    * @param aNotifyActivityType
    * @param response
    * @param helper
    * @return the PublishTNPortingNotificationReturn
    */
   private PublishTNPortingNotificationReturn buildPublishTNPortingNotificationReturn(
         BisContext aContext,
         String aNotifyActivityType,
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvReleaseAckImpl response,
         SOAResponseHelper helper)
   {
      String methodName = "SOA:buildPublishTNPortingNotificationReturn()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      String aSoacServiceOrderNumber = null;
      String aSOACServiceOrderCorrectionSuffix = null;
      String aLocalServiceProviderId = null;
      String aTelephoneNumber = null;
      EiaDate aDueDate = null;

      try
      {
         aSoacServiceOrderNumber = response.getOrderNum().getOrdTyp().trim()
               + response.getOrderNum().getSerNum().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aSOACServiceOrderCorrectionSuffix = response.getOrderNum().getSupNum()
               .trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aLocalServiceProviderId = response.getInitSpid().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aTelephoneNumber = response.getTnRng().getTnValue().trim();
      }
      catch(NullPointerException e)
      {
      }

      aDueDate = helper.buildEiaDate(response.getDueDateTime());

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);

      return buildPublishTNPortingNotificationReturn(aContext,
            aSoacServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
            aNotifyActivityType.trim(), aLocalServiceProviderId, null, null,
            aTelephoneNumber, null, null, aDueDate, null, null);
   }

   /**
    * buildPublishTNPortingNotificationReturn
    * @param aContext
    * @param aNotifyActivityType
    * @param response
    * @return the PublishTNPortingNotificationReturn
    */
   private PublishTNPortingNotificationReturn buildPublishTNPortingNotificationReturn(
         BisContext aContext, String aNotifyActivityType,
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvPTOImpl response)
   {
      String methodName = "SOA:buildPublishTNPortingNotificationReturn()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      String aSoacServiceOrderNumber = null;
      String aSOACServiceOrderCorrectionSuffix = null;
      String aLocalServiceProviderId = null;
      String aTelephoneNumber = null;
      String aSubscriptionVersionState = null;

      try
      {
         aSoacServiceOrderNumber = response.getOrderNum().getOrdTyp().trim()
               + response.getOrderNum().getSerNum().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aSOACServiceOrderCorrectionSuffix = response.getOrderNum().getSupNum()
               .trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aLocalServiceProviderId = response.getInitSpid().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aTelephoneNumber = response.getTnRng().getTnValue().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aSubscriptionVersionState = response.getSubVerState().trim();
      }
      catch(NullPointerException e)
      {
      }

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);

      return buildPublishTNPortingNotificationReturn(aContext,
            aSoacServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
            aNotifyActivityType.trim(), aLocalServiceProviderId, null, null,
            aTelephoneNumber, aSubscriptionVersionState, null, null, null, null);
   }

   /**
    * buildPublishTNPortingNotificationReturn
    * @param aContext
    * @param aNotifyActivityType
    * @param response
    * @return the PublishTNPortingNotificationReturn
    */
   private PublishTNPortingNotificationReturn buildPublishTNPortingNotificationReturn(
         BisContext aContext,
         String aNotifyActivityType,
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvCancelImpl response)
   {
      String methodName = "SOA:buildPublishTNPortingNotificationReturn()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      String aSoacServiceOrderNumber = null;
      String aSOACServiceOrderCorrectionSuffix = null;
      String aLocalServiceProviderId = null;
      String aTelephoneNumber = null;

      try
      {
         aSoacServiceOrderNumber = response.getOrderNum().getOrdTyp().trim()
               + response.getOrderNum().getSerNum().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aSOACServiceOrderCorrectionSuffix = response.getOrderNum().getSupNum()
               .trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aLocalServiceProviderId = response.getInitSpid().trim();
      }
      catch(NullPointerException e)
      {
      }

      try
      {
         aTelephoneNumber = response.getTnRng().getTnValue().trim();
      }
      catch(NullPointerException e)
      {
      }

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);

      return buildPublishTNPortingNotificationReturn(aContext,
            aSoacServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
            aNotifyActivityType, aLocalServiceProviderId, null, null,
            aTelephoneNumber, null, null, null, null, null);
   }

   /**
    * method buildPublishTNPortingNotificationReturn
    * @param aContext
    * @param aSoacServiceOrderNumber
    * @param aSOACServiceOrderCorrectionSuffix
    * @param aNotifyActivityType
    * @param aLocalServiceProviderId
    * @param aOldServiceProviderId
    * @param aNewServiceProviderId
    * @param aTelephoneNumber
    * @param aSubscriptionVersionState
    * @param aCauseCode
    * @param aDueDate
    * @param aStatus
    * @param aObjectProperties
    * @return the PublishTNPortingNotificationReturn
    */
   private PublishTNPortingNotificationReturn buildPublishTNPortingNotificationReturn(
         BisContext aContext, String aSoacServiceOrderNumber,
         String aSOACServiceOrderCorrectionSuffix, String aNotifyActivityType,
         String aLocalServiceProviderId, String aOldServiceProviderId,
         String aNewServiceProviderId, String aTelephoneNumber,
         String aSubscriptionVersionState, String aCauseCode, EiaDate aDueDate,
         ExceptionData aStatus, ObjectProperty[] aObjectProperties)
   {
      String methodName = "SOA:buildPublishTNPortingNotificationReturn()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      PublishTNPortingNotificationReturn aReturn = new PublishTNPortingNotificationReturn();

      /**
       * CR 19539
       * Get the Latest Suffix from DB and update the return message with it.
       */
      if(aSoacServiceOrderNumber != null && aSoacServiceOrderNumber.trim().length() != 0)
      {
    	  try
    	  {
    		  // Try to retrieve the correction suffix. If the operation fails, 
    		  //then use the existing correction suffix and send it to OMS.
    		  
      		OrderCorrectionSuffixTable aOrderCorrectionSuffixTable = new OrderCorrectionSuffixTable();
				aSOACServiceOrderCorrectionSuffix = aOrderCorrectionSuffixTable
						.RetrieveOrderCorrectionSuffix(aContext,
								aSoacServiceOrderNumber,
								aSOACServiceOrderCorrectionSuffix, properties,
								utility);
				
			aOrderCorrectionSuffixTable = null;
    		  
    	  }
    	  catch(Exception e)
    	  {
  			utility.log(LogEventId.INFO_LEVEL_2, "Non-fatal Exception: " + e
					+ " Okay to proceed.");    		      		  
    	  }
      }
      
      aReturn.aContext = aContext;
      aReturn.aSoacServiceOrderNumber = IDLUtil
            .toOpt((String) aSoacServiceOrderNumber);
      aReturn.aSOACServiceOrderCorrectionSuffix = IDLUtil
            .toOpt((String) aSOACServiceOrderCorrectionSuffix);
      aReturn.aNotifyActivityType = IDLUtil.toOpt((String) aNotifyActivityType);
      aReturn.aLocalServiceProviderId = IDLUtil
            .toOpt((String) aLocalServiceProviderId);
      aReturn.aOldServiceProviderId = IDLUtil
            .toOpt((String) aOldServiceProviderId);
      aReturn.aNewServiceProviderId = IDLUtil
            .toOpt((String) aNewServiceProviderId);
      aReturn.aTelephoneNumber = IDLUtil.toOpt((String) aTelephoneNumber);
      aReturn.aSubscriptionVersionState = IDLUtil
            .toOpt((String) aSubscriptionVersionState);
      aReturn.aCauseCode = IDLUtil.toOpt((String) aCauseCode);
      aReturn.aDueDate = (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, aDueDate);
      aReturn.aStatus = (ExceptionDataOpt) IDLUtil.toOpt(
            ExceptionDataOpt.class, aStatus);
      aReturn.aProperties = (ObjectPropertySeqOpt) IDLUtil.toOpt(
            ObjectPropertySeqOpt.class, aObjectProperties);

      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
      return aReturn;
   }

   /**
    * buildPublishTNPortingNotificationReturn
    * @param response
    * @param aContext
    * @param aSoacServiceOrderNumber
    * @param aSOACServiceOrderCorrectionSuffix
    * @param aLocalServiceProviderId
    * @param aTelephoneNumber
    * @return the PublishTNPortingNotificationReturn
    */
   private PublishTNPortingNotificationReturn buildPublishTNPortingNotificationReturn(
         OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResultImpl response,
         BisContext aContext, String aSoacServiceOrderNumber,
         String aSOACServiceOrderCorrectionSuffix,
         String aLocalServiceProviderId, String aTelephoneNumber)
   {

      String methodName = "SOA:buildPublishTNPortingNotificationReturn()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      //get BIS error code and description to populate ExceptionData object.
      String bisError = (String) SOA.soaToBisFailureCodeMappings.get(response
            .getReturn());
      String bisErrorCode = "";
      java.util.StringTokenizer st = new java.util.StringTokenizer(bisError,
            "|");
      if(st.hasMoreTokens())
      {
         bisErrorCode = st.nextToken();
      }
      String bisErrorDescription = "SOA exception: " + response.getErrStr();

      /*if (st.hasMoreTokens()) {
       bisErrorDescription = st.nextToken();
       }*/

      ExceptionData exceptionData = new ExceptionData(bisErrorCode,
            bisErrorDescription, IDLUtil.toOpt(SOAConstants.SOA),
            (SeverityOpt) IDLUtil.toOpt((SeverityOpt.class),
                  Severity.Recoverable));
      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);

      return buildPublishTNPortingNotificationReturn(aContext,
            aSoacServiceOrderNumber, aSOACServiceOrderCorrectionSuffix, null,
            aLocalServiceProviderId, null, null, aTelephoneNumber, null, null,
            null, exceptionData, null);
   }

   /**
    * method loads the SOA Failure Status to BIS Error code mappings.
    */
   public void loadSOAToBISStatusMappings() throws InvalidData, AccessDenied,
         BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound,
         DataNotFound
   {
      String methodName = "SOA:loadSOAToBISStatusMappings()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      if(soaToBisFailureCodeMappings == null)
      {
         String fileName = (String) properties
               .get("SOA_FAILURE_STATUS_TO_BIS_ERROR_MAPPINGS");
         try
         {
            soaToBisFailureCodeMappings = PropertiesFileLoader.read(fileName,
                  utility);
         }
         catch(FileNotFoundException e)
         {
            utility
                  .log(LogEventId.DEBUG_LEVEL_2,
                        "SOA to BIS Status mappings file not found."
                              + e.getMessage());
            utility.throwException(
                  ExceptionCode.ERR_RM_PROPERTIES_FILE_NOT_FOUND,
                  "SOA to BIS Status mappings file not found.",
                  (String) properties.get("BIS_NAME"), Severity.Recoverable);
         }
         catch(IOException e)
         {
            utility
                  .log(LogEventId.DEBUG_LEVEL_2,
                        "Failed to load SOA to BIS Status mappings."
                              + e.getMessage());
            utility.throwException(ExceptionCode.ERR_RM_IO_EXCEPTION,
                  "Failed to load SOA to BIS Status mappings.",
                  (String) properties.get("BIS_NAME"), Severity.Recoverable);
         }
      }
      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);

   }
   /**
    * 
    * LS7 CR18595
    * The client can not accept NotifySvDisconnect message so,
    * we're sending them NotifySvActivate.
    * buildPublishTNPortingNotificationReturn
    * @param aContext
    * @param aNotifyActivityType
    * @param response
    * @return the PublishTNPortingNotificationReturn
    */
   private PublishTNPortingNotificationReturn buildPublishTNPortingNotificationReturn(
         BisContext aContext,
         String aNotifyActivityType,
         OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvDisconnectImpl response)
   {
      String methodName = "SOA:buildPublishTNPortingNotificationReturn()";
      utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      String soacServiceOrderNumber = null;
      String sOACServiceOrderCorrectionSuffix = null;
      String localServiceProviderId = null;
      String telephoneNumber = null;
      
      String aSubscriptionVersionState = SOAConstants.SUBSCRIPTION_VERSION_STATE_ACTIVE;
      aNotifyActivityType = NotifyActivityTypeValues.NOTIFY_ACTIVATE;

      try
      {
         soacServiceOrderNumber = response.getOrderNum().getOrdTyp().trim()
               + response.getOrderNum().getSerNum().trim();
      }
      catch(NullPointerException e)
      {
         //The program should continue when soacServiceOrderNumber is null
      }

      try
      {
         sOACServiceOrderCorrectionSuffix = response.getOrderNum().getSupNum()
               .trim();
      }
      catch(NullPointerException e)
      {
         //The program should continue when sOACServiceOrderCorrectionSuffix is null
      }

      try
      {
         localServiceProviderId = response.getInitSpid().trim();
      }
      catch(NullPointerException e)
      {
         //The program should continue when localServiceProviderId is null
      }

      try
      {
         telephoneNumber = response.getTnRng().getTnValue().trim();
      }
      catch(NullPointerException e)
      {
         //The program should continue when telephoneNumber is null
      }
      utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);

      return buildPublishTNPortingNotificationReturn(aContext,
            soacServiceOrderNumber, sOACServiceOrderCorrectionSuffix,
            aNotifyActivityType, localServiceProviderId, null, null,
            telephoneNumber, aSubscriptionVersionState, null, null, null, null);
   }
  	
}