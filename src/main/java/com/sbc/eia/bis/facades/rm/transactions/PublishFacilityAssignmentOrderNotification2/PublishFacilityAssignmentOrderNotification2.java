//$Id: PublishFacilityAssignmentOrderNotification2.java,v 1.4 2008/02/23 03:54:45 ds4987 Exp $
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

package com.sbc.eia.bis.facades.rm.transactions.PublishFacilityAssignmentOrderNotification2;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC2;
import com.sbc.eia.bis.facades.rm.transactions.PublishFacilityAssignmentOrderNotification.PublishFacilityAssignmentOrderNotification;

public class PublishFacilityAssignmentOrderNotification2 extends
		PublishFacilityAssignmentOrderNotification
{
	public PublishFacilityAssignmentOrderNotification2(Hashtable aProperties)
	{
		super(aProperties);
	}
  protected SOAC makeSOAC(Logger myLogger) 
  {
    SOAC2 soac2 = new SOAC2(aProperties, aUtility,myLogger);
    return (SOAC)soac2;
  }

}
