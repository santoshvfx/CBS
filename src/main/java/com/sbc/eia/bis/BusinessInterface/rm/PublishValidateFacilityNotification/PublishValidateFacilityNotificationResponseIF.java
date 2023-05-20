//$Id: PublishValidateFacilityNotificationResponseIF.java,v 1.6 2009/07/25 02:23:34 js7440 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date       | Author              | Notes
//# --------------------------------------------------------------------
//# 01/15/2008   Rene Duka             Creation.
//# 02/12/2008   Rene Duka             LS 7.
//# 07/22/2009 	 Julius Sembrano       PR25176019: Granite flow, there is no loop info return when WTN is returned from Granite. 
//#									   Modified logic for setting GraniteUverseNotFound and SiteNotFound

package com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification;

import java.util.Properties;

import com.sbc.eia.idl.rm_ls_types.BillingAccount2;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2;
import com.sbc.eia.idl.rm_ls_types.Message;
import com.sbc.eia.idl.types.EiaDate;

/**
 * Class      : PublishValidateFacilityNotificationResponseIF
 * Description: Interface for pVFN response helper.  
 */
public interface PublishValidateFacilityNotificationResponseIF
{
    public FacilityLoop2[] getLoops();
    public BillingAccount2[] getAccounts();
    public String getRecommendedRTID(); 
    public String[] getRecommendedSecondaryRTID();
    public boolean getTerminalExhaustIndicator();
    public String[] getAutoDSLDisconnectTelephoneNumbers();
    public String getRTIDSource(); 
    public EiaDate getDueDate(); 
    public boolean getOkToProceedIndicator();
    public boolean getBTNnotFoundIndicator();
    public boolean getWTNnotInPremiseIndicator();
    public boolean isGraniteUVerseFound(String aNti);
    public Message[] getMessages();
    
    public void setLoops(FacilityLoop2[] aInput);
    public void setAccounts(BillingAccount2[] aInput);
    public void setRecommendedRTID(String aInput); 
    public void setRecommendedSecondaryRTID(String[] aInput);
    public void setTerminalExhaustIndicator(boolean aInput);
    public void setAutoDSLDisconnectTelephoneNumbers(String[] aInput);
    public void setRTIDSource(String aInput); 
    public void setDueDate(EiaDate aInput); 
    public void setOkToProceedIndicator(boolean aInput);
    public void setBTNnotFoundIndicator(boolean aInput);
    public void setWTNnotInPremiseIndicator(boolean aInput);
    public void setMessages(Message[] aInput);
}
