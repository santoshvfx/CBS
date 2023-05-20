//$Id: PublishFacilityAssignmentOrderNotification3.java,v 1.4 2009/02/25 00:02:53 ra0331 Exp $
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
//# 02/22/2008  Doris Sunga			  LS7: Added myLogger in calling SOAC2()  
//############################################################################

package com.sbc.eia.bis.facades.rm.transactions.PublishFacilityAssignmentOrderNotification3;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC3;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.framework.logging.LogEventId;

public class PublishFacilityAssignmentOrderNotification3 extends TranBase {

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
	public PublishFacilityAssignmentOrderNotification3() 
	{
		super();
	}

	public PublishFacilityAssignmentOrderNotification3(Hashtable param) 
	{	
		super(param);
		aUtility = this;
		aProperties = getPROPERTIES();
	}
		
	public PublishFacilityAssignmentOrderNotification3(Utility utility, Hashtable myProperties, Logger logger) 
	{
		super(myProperties);
		myLogger = logger;
		aUtility = utility;
		aProperties = getPROPERTIES();
	}
		
	public void execute(Logger myLogger, SOACServiceOrderResponseParser aParsedFCIF, String correlationId, String aApplicationID)
		throws ParserSvcException, Exception
	{
		
	  String myMethodNameName = "PublishFacilityAssignmentOrderNotification3.execute()";

	  myLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);
	  if(myLogger instanceof Utility)
	  {
		   aUtility = (Utility)myLogger;
	  }
	  // execution continue from PublishFacilityAssignmentOrderNotificationBean: publishFacilityAssignmentOrderNotification - local EJB		
	  makeSOAC3(myLogger).publishFacilityAssignmentOrderNotification3(aUtility, aParsedFCIF, correlationId, aApplicationID);
	  
	  myLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
	}


	protected SOAC3 makeSOAC3(Logger myLogger)
	{
		return new SOAC3(aProperties, aUtility, myLogger);
	}
}
