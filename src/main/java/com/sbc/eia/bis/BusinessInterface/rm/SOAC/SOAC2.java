//$Id: SOAC2.java,v 1.3 2008/02/23 04:15:52 ds4987 Exp $
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
//#      © 2008-2010 AT&T Intellectual Property All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 01/29/2008	Ott Phannavong		  Creation
//# 01/29/2008  Doris Sunga			  LS7: MFI2 -Added MFI2
//# 02/22/2008  Doris Sunga			  LS7: update PFAO2
//############################################################################
package com.sbc.eia.bis.BusinessInterface.rm.SOAC;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utility.soaphelpers.SoapParserHelper;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOACEmailSender;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderRequestGenerator;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderRequestGenerator2;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrderConstants;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.exception_types.ExceptionCode;

import com.sbc.eia.idl.rm.RmFacadePackage._publishFacilityAssignmentOrderNotification2Response;
import com.sbc.eia.idl.rm.RmFacadePackage._publishFacilityAssignmentOrderNotification2ResponseBISMsg;
import com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotificationReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._publishFacilityAssignmentOrderNotificationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishFacilityAssignmentOrderNotificationResponseBISMsg;
import com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotification2Return;

import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfoResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfoResponseBISMsg;
import com.sbc.eia.idl.rm.ModifyFacilityInfoReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfo2Response;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfo2ResponseBISMsg;
import com.sbc.eia.idl.rm.ModifyFacilityInfo2Return;

import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeOpt;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeValues;
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignment;
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignmentOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.eia.idl.types.bishelpers.ExceptionDataOptBisHelper;
import com.sbc.vicunalite.vaxb.VAXBDocumentWriter;

public class SOAC2 extends SOAC
{
	protected StringOpt oldNetworkType = null;
	protected StringOpt secondaryCircuitID = null;
	protected StringOpt secondaryRelatedCircuitID = null;
	protected CompositeObjectKey billingAccountNumber = null;

	public SOAC2(Hashtable aProperties, Utility aUtility)
	{
		super(aProperties, aUtility);
	}
	public SOAC2(Hashtable aProperties, Utility aUtility, Logger aLogger)
	{
		super(aProperties, aUtility, aLogger);
	}
	/**
	 * @return Returns the billingAccountNumber.
	 */
	public CompositeObjectKey getBillingAccountNumber()
	{
		return billingAccountNumber;
	}
	/**
	 * @param billingAccountNumber The billingAccountNumber to set.
	 */
	public void setBillingAccountNumber(CompositeObjectKey billingAccountNumber)
	{
		this.billingAccountNumber = billingAccountNumber;
	}
	/**
	 * @return Returns the oldNetworkType.
	 */
	public StringOpt getOldNetworkType()
	{
		return oldNetworkType;
	}
	/**
	 * @param oldNetworkType The oldNetworkType to set.
	 */
	public void setOldNetworkType(StringOpt oldNetworkType)
	{
		this.oldNetworkType = oldNetworkType;
	}
	/**
	 * @return Returns the secondaryCircuitID.
	 */
	public StringOpt getSecondaryCircuitID()
	{
		return secondaryCircuitID;
	}
	/**
	 * @param secondaryCircuitID The secondaryCircuitID to set.
	 */
	public void setSecondaryCircuitID(StringOpt secondaryCircuitID)
	{
		this.secondaryCircuitID = secondaryCircuitID;
	}
	/**
	 * @return Returns the secondaryRelatedCircuitID.
	 */
	public StringOpt getSecondaryRelatedCircuitID()
	{
		return secondaryRelatedCircuitID;
	}
	/**
	 * @param secondaryRelatedCircuitID The secondaryRelatedCircuitID to set.
	 */
	public void setSecondaryRelatedCircuitID(StringOpt secondaryRelatedCircuitID)
	{
		this.secondaryRelatedCircuitID = secondaryRelatedCircuitID;
	}
	/**
	 * LS7
	 * @return an instance of SOACServiceOrderRequestGenerator2
	 */
	protected SOACServiceOrderRequestGenerator makeSOACServiceOrderRequestGenerator()
	{
		SOACServiceOrderRequestGenerator2 sOACServiceOrderRequestGenerator2 = new SOACServiceOrderRequestGenerator2(
				myProperties, (Logger)myUtility, oldNetworkType,
				secondaryCircuitID, secondaryRelatedCircuitID,
				billingAccountNumber); // later try taking out the cast to Logger see if it still work
		return (SOACServiceOrderRequestGenerator)sOACServiceOrderRequestGenerator2;
	}
	protected void sendXML(SOACServiceOrderResponseParser aParsedFCIF,
			String aApplicationID, SOACEmailSender aEmailSender,
			BisContext aContext) throws Exception
	{
		String myMethodNameName = "SOAC2: sendXML()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
		boolean emailReportSent = false;
		if(((aParsedFCIF.getFunctionType().equalsIgnoreCase(
				COMPLETION_SUB_ACTION_TYPE) || aParsedFCIF.getFunctionType()
				.equalsIgnoreCase(CANCELLATION_SUB_ACTION_TYPE)) && (isErrorStatusCode(aParsedFCIF
				.getStatusCode()) == true))
				|| aParsedFCIF.isCombinationResponse() == true)
		{
			aEmailSender.send();
			emailReportSent = true;
		}
		if(emailReportSent == false
				|| aParsedFCIF.isCombinationResponse() == true)
		{
			myLogger.log(LogEventId.DEBUG_LEVEL_2, "aContext=<"
					+ (new BisContextBisHelper(aContext)).toString() + ">");
			ServiceOrderAssignment soaObj = setServiceOrderAssignment();
			soaObj.aServiceOrderNumber.theValue(aParsedFCIF
					.getSOACServiceOrderNum());
			soaObj.aServiceOrderCorrectionSuffix.theValue(aParsedFCIF
					.getCorrectionSuffix());
			
		    try
		    {
		       if(aParsedFCIF.getAssgnSectionString() != null)
		          soaObj.aAssignmentLines.theValue(aParsedFCIF
		                .getAssgnSectionString());
		    }
		    catch(Exception e)
		    {
		    }
		       
			soaObj.aLSTIndicator.__default();
			ServiceOrderAssignmentOpt serviceOrderAssignment = new ServiceOrderAssignmentOpt();
			serviceOrderAssignment.theValue(soaObj);
			ExceptionDataOpt status = (ExceptionDataOpt)IDLUtil.toOpt(
					ExceptionDataOpt.class, setStatus(aParsedFCIF));
			myUtility.log(LogEventId.DEBUG_LEVEL_2, "aStatus=<"
					+ (new ExceptionDataOptBisHelper(status)).toString() + ">");
			ObjectPropertySeqOpt properties = new ObjectPropertySeqOpt();
			properties.__default();
			
			prepareAndForwardXml(aContext, 
			         soaObj.aServiceOrderNumber, 
	                 soaObj.aServiceOrderCorrectionSuffix,
	                 soaObj.aAssignmentLines,			        
			         status,properties, aParsedFCIF,
			         SvcOrderConstants.PFAO2_TRANSACTION);
               
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
	}
	
//	MFI2: LS7
	public void buildSoaObj(BisContext aContext, ServiceOrderAssignment soaObj,
	         SOACServiceOrderResponseParser parsedFCIF,
	         String aTransactionName,
	         String aApplicationID)
	    throws SystemFailure, Exception
	{
	  String myMethodNameName = "SOAC:buildSOAObj()";
	  myUtility.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);
	  
	  AdditionalErrorDesc = null;
	  try
	  {
	     soaObj.aServiceOrderNumber.theValue(parsedFCIF
	           .getSOACServiceOrderNum());
	     soaObj.aServiceOrderCorrectionSuffix.theValue(parsedFCIF
	           .getCorrectionSuffix());    
	         
	     try
	     {
	        if(parsedFCIF.getAssgnSectionString() != null)
	           soaObj.aAssignmentLines.theValue(parsedFCIF
	                 .getAssgnSectionString());
	        else
	        {    
	            myUtility.log(LogEventId.INFO_LEVEL_2, "Missing required data: Assignment Lines");
	            AdditionalErrorDesc = "Missing required data: Assignment Lines";
	        }    
	     }
	     catch(Exception e) 
	     {}
	     soaObj.aLSTIndicator.__default();  
	  }
	  catch(Exception e)
	  {
	     myUtility.log(LogEventId.ERROR,
	           "Found error in " + aTransactionName + ": ["
	                 + e.getMessage() + "]");

	     if(aContext == null)
	        throw e;
	     else
	     {
	        myUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
	              "Exception message: " + e.getMessage(), (String) myProperties
	                    .get("BIS_NAME").toString(), Severity.UnRecoverable);
	     }
	  }
	  myUtility.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);  
	}
	
	public void prepareAndForwardXml(BisContext aContext,
	    	StringOpt aServiceOrderNumber,
        StringOpt aServiceOrderCorrectionSuffix,	        
	        StringSeqOpt aAssignmentLines,
	        ExceptionDataOpt aStatus, ObjectPropertySeqOpt aProperties,
	        SOACServiceOrderResponseParser aParsedFCIF, 
	        String aTransactionName) 
	  throws Exception
	  {
	     String myMethodNameName = "SOAC2: prepareAndForwardXml()";
	     myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

	     ObjectPropertyManager aContextOPM = new ObjectPropertyManager(
	           aContext.aProperties);
	     String xmlResp = null;
	     Properties messageTags = null;

	     try
	     {
	        messageTags = new Properties();
	        messageTags.setProperty("embus:MessageTag", aTransactionName);
	        messageTags.setProperty("embus:ApplicationID", "ApplicationID");
	        messageTags.setProperty("embus:MessageID", "MessageID");
	        messageTags.setProperty("embus:ConversationKey", "ConversationKey");
	        messageTags.setProperty("embus:LoggingKey", "LoggingKey");
	        messageTags.setProperty("embus:ResponseMessageExpiration", "0");

	        if(aTransactionName.equalsIgnoreCase(SvcOrderConstants.MFI2_TRANSACTION))
		        xmlResp = VAXBDocumentWriter.encode(createRespBisMsgMFI2(aContext, 
		                aServiceOrderNumber, aServiceOrderCorrectionSuffix,
		                aAssignmentLines, aStatus, aProperties));
	        if(aTransactionName.equalsIgnoreCase(SvcOrderConstants.PFAO2_TRANSACTION))
		        xmlResp = VAXBDocumentWriter.encode(createRespBisMsgPFAO2(aContext, 
		                aServiceOrderNumber, aServiceOrderCorrectionSuffix,
		                aAssignmentLines, aStatus, aProperties));	            

	        xmlResp = SoapParserHelper.appendEMBUSSoapEnvelope(SoapParserHelper
	              .removeTagsFromXML(xmlResp, "<vaxb:VAXB", "</vaxb:VAXB>"),
	              messageTags);

	        myUtility.log(LogEventId.INFO_LEVEL_2, "XML Resp: [" + xmlResp + "]");
	     }
	     catch(IOException ioe)
	     {
	        myUtility
	              .log(
	                    LogEventId.AUDIT_TRAIL,
	                    "Conversion to XML from " + aTransactionName + " object failure: "
	                          + ioe.getMessage());
	        myUtility.throwException(ExceptionCode.ERR_RM_IO_EXCEPTION,
	              "XML conversion produced an IOException " + ioe.getMessage(),
	              (String)myProperties.get("BIS_NAME").toString(),
	              Severity.UnRecoverable);
	     }

	     forwardXMLToClient(aContext, xmlResp, messageTags);

	     myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
	  } 
	

	 private _modifyFacilityInfo2ResponseBISMsg createRespBisMsgMFI2(BisContext aContext,
		     StringOpt aServiceOrderNumber,
	         StringOpt aServiceOrderCorrectionSuffix,		         
	         StringSeqOpt aAssignmentLines,
	         ExceptionDataOpt aStatus, ObjectPropertySeqOpt aProperties)
	   {
	      String myMethodNameName = "SOAC2.createRespBisMsgMFI2()";
	      myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
	      
	      ModifyFacilityInfo2Return modifyFacilityInfo2Ret = new ModifyFacilityInfo2Return(
	              aContext, aServiceOrderNumber, aServiceOrderCorrectionSuffix,
	              aAssignmentLines, aStatus, aProperties);

	      _modifyFacilityInfo2Response modifyFacilityInfo2Resp = new _modifyFacilityInfo2Response();

	      modifyFacilityInfo2Resp
	    	.aModifyFacilityInfo2Return(modifyFacilityInfo2Ret);

	      _modifyFacilityInfo2ResponseBISMsg respBisMsg = new _modifyFacilityInfo2ResponseBISMsg(
	            modifyFacilityInfo2Resp);        
	      
	      myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
	      
	      return respBisMsg;
	   } 	
	 

	 private _publishFacilityAssignmentOrderNotification2ResponseBISMsg createRespBisMsgPFAO2(BisContext aContext,
		     StringOpt aServiceOrderNumber,
	         StringOpt aServiceOrderCorrectionSuffix,		         
	         StringSeqOpt aAssignmentLines,
	         ExceptionDataOpt aStatus, ObjectPropertySeqOpt aProperties)
	   {
	      String myMethodNameName = "SOAC2.createRespBisMsgPFAO2()";
	      myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
	      
	      PublishFacilityAssignmentOrderNotification2Return publishFacilityAssignment2Ret = new PublishFacilityAssignmentOrderNotification2Return(
	              aContext, aServiceOrderNumber, aServiceOrderCorrectionSuffix,
	              aAssignmentLines, aStatus, aProperties);

	        _publishFacilityAssignmentOrderNotification2Response publishFacilityAssignment2Resp = new _publishFacilityAssignmentOrderNotification2Response();

	        publishFacilityAssignment2Resp
	              .aPublishFacilityAssignmentOrderNotification2Return(publishFacilityAssignment2Ret);

	        _publishFacilityAssignmentOrderNotification2ResponseBISMsg respBisMsg = new _publishFacilityAssignmentOrderNotification2ResponseBISMsg(
	              publishFacilityAssignment2Resp);     
	      
	      myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
	      
	      return respBisMsg;
	   } 
}