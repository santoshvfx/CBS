//$Id:
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
//# 09/28/2007  Dolores Sunga             Creation.

package com.sbc.eia.bis.facades.rm.ejb.PublishFacilityAssignmentOrderNotification;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;

public interface PublishFacilityAssignmentOrderNotification extends
      javax.ejb.EJBLocalObject
{

   /**
    * @param parsedFCIF
    * @param correlationId
    * @param aApplicationID
    * @param isLST
    * @return
    * @throws Exception
    */

   public void publishFacilityAssignmentOrderNotification(
         com.sbc.bccs.utilities.Logger myLogger,
         SOACServiceOrderResponseParser parsedFCIF, String correlationId,
         String aApplicationID) throws Exception;
}