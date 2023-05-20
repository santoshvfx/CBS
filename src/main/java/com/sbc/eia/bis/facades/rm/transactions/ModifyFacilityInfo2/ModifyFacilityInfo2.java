//$Id: ModifyFacilityInfo2.java,v 1.2 2008/02/20 19:21:23 ds4987 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2007-2010 AT&T Intellectual Property All rights reserved.
//#
//# History :
//# Date                | Author            | Notes
//# ----------------------------------------------------------------------------
//# 01/25/2008            ds4987              Creation.
//# 01/20/2008			  ds4987			  Implemented POJO standards
package com.sbc.eia.bis.facades.rm.transactions.ModifyFacilityInfo2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.mail.MessagingException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC2;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC2;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOACEmailSender;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOACEmailSenderFactory;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SoacDefect;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrderConstants;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.ApplicationMapper;
import com.sbc.eia.bis.rm.utilities.BisContextHelper;
import com.sbc.eia.bis.rm.utilities.ValidateHelper;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.accessor.DataAccessorException;
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
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignment;
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignmentOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.eia.idl.types.bishelpers.ExceptionDataOptBisHelper;


/**
* ModifyFacilityInfo2 transaction class processes SOAC response for BBNMS request
* email is sent to BBNMS when response failed to sent.
* @author ds4987
*
*/
public class ModifyFacilityInfo2 extends TranBase 
{

private Utility aUtility = null;
private Hashtable aProperties = null;

//Status for both resend and unsolicited response
private final static String[] assignmentStatus = { "FANK", "FACH", "FANC", "FDND", "FUAU", "FUND" };
public String transactionName = "ModifyFacilityInfo2";

private boolean sendEmail;
private boolean sendEmailBBNMS = false;

StringOpt aServiceOrderNumber = IDLUtil.toOpt((String)null, false);
StringOpt aServiceOrderCorrectionSuffix = IDLUtil.toOpt((String)null,
    false);
StringSeqOpt aAssignmentLines = new StringSeqOpt();
String xmlResp = null;
Properties messageTags = null;

/**
* Constructor for ModifyFacilityInfo2.
*/
public ModifyFacilityInfo2() 
{
  super();
}

/**
* Constructor for ModifyFacilityInfo2.
* @param utility
* @param properties
*/
public ModifyFacilityInfo2(Utility utility, Hashtable properties) 
{
  super(properties);
  aUtility = utility;
  aProperties = getPROPERTIES();
}

/**
* set utility
* @param utility
*/
public void setUtility(Utility utility)
{
  aUtility = utility;
}

/**
* Main method: set, build and send response 
* @param SOACServiceOrderResponseParser
* @param correlationId
* @param aApplicationID
* @param myLogger
*/
public void modifyFacilityInfo2Request(SOACServiceOrderResponseParser parsedFCIF,
      String correlationId,
      String aApplicationID,
      com.sbc.bccs.utilities.Logger myLogger)
  throws ParserSvcException, DataAccessorException, IOException, MessagingException, Exception 
{      
  String myMethodNameName = "RM::ModifyFacilityInfo2:modifyFacilityInfo2Request";               
  aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodNameName);        
     
  sendEmail = false;            

  BisContext aContext = null;
  aContext = BisContextHelper.setBisContext(aApplicationID, null, null,
           correlationId, aProperties);  
  
	SOACEmailSender emailSender = SOACEmailSenderFactory.getInstanceValue(
	              aContext, aUtility, parsedFCIF, aProperties, myLogger);      
	try
	{	      
	     aUtility.log(LogEventId.DEBUG_LEVEL_2, "aContext=<"
	              + (new BisContextBisHelper(aContext)).toString() + ">");
	        
	     SOAC2 soac2 = new SOAC2(aProperties, aUtility, myLogger);
	     ServiceOrderAssignment soaObj = soac2.setServiceOrderAssignment(); 
	         
	     soac2.buildSoaObj(aContext, soaObj, parsedFCIF, SvcOrderConstants.MFI_TRANSACTION, aApplicationID);
	         
	     ServiceOrderAssignmentOpt aServiceOrderAssignment = new ServiceOrderAssignmentOpt();
	     aServiceOrderAssignment.theValue(soaObj);
	     ExceptionDataOpt aStatus = (ExceptionDataOpt) IDLUtil.toOpt(
	               ExceptionDataOpt.class, soac2.setStatusValue(parsedFCIF));
	     aUtility.log(LogEventId.DEBUG_LEVEL_2, "aStatus=<"
	               + (new ExceptionDataOptBisHelper(aStatus)).toString() + ">");

	     ObjectPropertySeqOpt aProperties = new ObjectPropertySeqOpt();
	     aProperties.__default();      

	     soac2.prepareAndForwardXml(aContext, soaObj.aServiceOrderNumber, 
	                 soaObj.aServiceOrderCorrectionSuffix,
	                 soaObj.aAssignmentLines, aStatus, aProperties,
	                 parsedFCIF, SvcOrderConstants.MFI2_TRANSACTION);
	         
	     aUtility.log(LogEventId.INFO_LEVEL_1, "Successful send of response.");
	 }     
	 catch(Exception e)              
	 {
	     // send email                   
	     aUtility.log(LogEventId.INFO_LEVEL_1,
						"Failed to send message. So sending email.");
	     emailSender.sendWhenReponseFailed(parsedFCIF);
	          
	 }
	 finally
	 {
	     aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodNameName);
	 }  
}
	

} //end of class ModifyFacilityInfo2