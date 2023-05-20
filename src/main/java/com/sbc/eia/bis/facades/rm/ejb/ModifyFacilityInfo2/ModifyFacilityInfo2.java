// $Id: ModifyFacilityInfo2.java,v 1.1 2008/02/07 21:28:21 ds4987 Exp $
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
//# 01/29/2008  Dolores Sunga         Creation.

package com.sbc.eia.bis.facades.rm.ejb.ModifyFacilityInfo2;

import java.util.Hashtable;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;


public interface ModifyFacilityInfo2
	extends javax.ejb.EJBLocalObject {


  /**
   * @param utility
   * @param myProperties
   * @param parsedFCIF
   * @param correlationId
   * @param applicationID
   * @param myLogger
   * @throws Exception
   */
  public void modifyFacilityInfo2(Utility utility,
          Hashtable myProperties,
          SOACServiceOrderResponseParser parsedFCIF,
          String correlationId,
          String applicationID,
          com.sbc.bccs.utilities.Logger myLogger)
	    throws
	        Exception;

}