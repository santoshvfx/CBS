//$Id: PublishFacilityAssignmentOrderNotification.java,v 1.1 2006/06/05 20:27:47 ds4987 Exp $
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
//# 06/09/2006	Doris Sunga	      Initial Author	
//# 12/04/2006  Doris Sunga	      DR #170893 - correlation id issue in PFAO log and xml output,  
//#                                       need to pass corr id when calling SOAC.publishFacilityAssignmentOrderNotification
//# 10/08/2007  Doris Sunga	      LS6 added myLogger parameter in execute() & new constructor
//# 10/31/2007  Doris Sunga	      LS6:MQC 77469: added myLogger to fix correlationID
//# 11/08/2008 	Ott Phannavong	  LS6: MQC 77469: Modified execute() and added if(myLogger instanceof Utility) block.
//# 02/20/2008  Doris Sunga       LS7: passed myLogger in calling makeSOAC()
//# --------------------------------------------------------------------

package com.sbc.eia.bis.facades.rm.transactions.PublishFacilityAssignmentOrderNotification;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.Logger;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;

public class PublishFacilityAssignmentOrderNotification extends TranBase {

protected Utility aUtility = null;
protected Hashtable aProperties = null;
protected Logger myLogger = null;
	
String aApplication = null;
String aCustomerName = null;
String aBusinessUnit = null;
String aLoggingInformation = null;	
		
/**
* Constructor 
*/
public PublishFacilityAssignmentOrderNotification() 
{
super();
}

public PublishFacilityAssignmentOrderNotification(Hashtable param) 
{	
super(param);
aUtility = this;
aProperties = getPROPERTIES();
}
	
public PublishFacilityAssignmentOrderNotification(Utility utility, Hashtable properties, Logger logger) 
{
super(properties);
myLogger = logger;
aUtility = utility;
aProperties = getPROPERTIES();
}
	
public void execute(Logger myLogger, SOACServiceOrderResponseParser parsedFCIF, String correlationId, String aApplicationID)
	throws ParserSvcException, Exception
{
	
  String myMethodNameName = "PublishFacilityAssignmentOrderNotification.execute()";

  myLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);
  if(myLogger instanceof Utility)
  {
	   aUtility = (Utility)myLogger;
  }
  // execution continue from PublishFacilityAssignmentOrderNotificationBean: publishFacilityAssignmentOrderNotification - local EJB		
  makeSOAC(myLogger).publishFacilityAssignmentOrderNotification(aUtility, parsedFCIF, correlationId, aApplicationID);

  myLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
}

/**
 * LS7
 * @return an instance of SOAC
 */
protected SOAC makeSOAC(Logger myLogger) 
{
   return new SOAC(aProperties, aUtility, myLogger);
}	
	
}