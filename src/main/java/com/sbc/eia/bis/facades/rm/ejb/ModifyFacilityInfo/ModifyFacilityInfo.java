//$Id: ModifyFacilityInfo.java,v 1.3 2008/02/07 22:11:48 ds4987 Exp $
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

package com.sbc.eia.bis.facades.rm.ejb.ModifyFacilityInfo;

import java.util.Hashtable;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;


public interface ModifyFacilityInfo
	extends javax.ejb.EJBLocalObject {

//	/**
//	 * @param utility,
//	 * @param properties,
//	 * @param parsedFCIF,
//	 * @param correlationID
//	 * @param aApplicationID,
//	 * @param isLST
//	 * @return
//	 * @throws Exception
//	 */
//	public void modifyFacilityInfo(
//	        Utility utility, 
//	        Hashtable properties, 
//	        SOACServiceOrderResponseParser parsedFCIF,
//	        String correlationID,
//	        String aApplicationID,
//	        com.sbc.bccs.utilities.Logger myLogger)
//		throws
//			Exception;

  /**
   * @param utility
   * @param myProperties
   * @param parsedFCIF
   * @param correlationId
   * @param applicationID
   * @param myLogger
   * @throws Exception
   */
  public void modifyFacilityInfo(Utility utility, 
          Hashtable myProperties, 
          SOACServiceOrderResponseParser parsedFCIF, 
          String correlationId, 
          String applicationID, 
          com.sbc.bccs.utilities.Logger myLogger)
	    throws
	        Exception;

}