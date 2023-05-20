//$Id: PublishValidateFacilityNotificationResponseDriver.java,v 1.4 2009/06/22 04:55:10 lg4542 Exp $
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
//# 01/15/2008   Rene Duka             LS 7. Creation.
//# 02/07/2008   Rene Duka             LS 7.
//# 06/21/2009   Lira Galsim     	   Added facility loop to the response driver.

package com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2;
import com.sbc.eia.idl.rm_ls_types.ServiceItem;
import com.sbc.eia.idl.types.ObjectType;

/**
 * Class      : PublishValidateFacilityNotificationResponseController
 * Description: Controls the creation of response.  
 */
public class PublishValidateFacilityNotificationResponseDriver 
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;

    // network type
    private  String aNT;
    // working telephone number associated with the loop.
    private String aWTN;
    // billing telephone number associated with the working telephone number.    
    private String aBTN;
    // universal billing account number associated with the working telephone number.
    private String aUban;
    // loop associated with the ntwork type
    private FacilityLoop2 aFacilityLoop;

    /**
     * Constructor: PublishValidateFacilityNotificationResponseController
     * 
     * @author Rene Duka
     */
    public PublishValidateFacilityNotificationResponseDriver() 
    {
        // initialize
        aNT = "";
        aWTN = "";
        aBTN = "";
        aUban= "";
        aFacilityLoop = null;
    }
  
    /**
     * Constructor: PublishValidateFacilityNotificationResponseController
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Rene Duka
     */
    public PublishValidateFacilityNotificationResponseDriver (
        Utility utility, 
        Hashtable properties) 
    {
        this();
        aProperties = properties;
        aUtility = utility;
    }

    /**
     * Get network type.
     * 
     * @return aNT  
     *   
     * @author Rene Duka  
     */
    public String getNT() 
    {
        return aNT;
    }

    /**
     * Get working telephone number.
     * 
     * @return aWTN  
     *   
     * @author Rene Duka  
     */
    public String getWTN() 
    {
        return aWTN;
    }

    /**
     * Get billing telephone number.
     * 
     * @return aBTN  
     *   
     * @author Rene Duka  
     */
    public String getBTN() 
    {
        return aBTN;
    }

    /**
     * Get UBAN.
     * 
     * @return aUban  
     *   
     * @author Hongmei Parkin  
     */

	public String getUban() {
		return aUban;
	}

    /**
     * Get facility loop.
     * 
     * @return aFacilityLoop  
     *   
     * @author Lira Galsim  
     */
    public FacilityLoop2 getFacilityLoop() 
    {
        return aFacilityLoop;
    }

    /**
     * Set network type.
     * 
     * @param String aInput  
     *   
     * @author Rene Duka  
     */
    public void setNT(String aInput) 
    {
        aNT = aInput;
    }

    /**
     * Set working telephone number.
     * 
     * @param String aInput  
     *   
     * @author Rene Duka  
     */
    public void setWTN(String aInput) 
    {
        aWTN = aInput;
    }

    /**
     * Set billing telephone telephone number.
     * 
     * @param String aInput  
     *   
     * @author Rene Duka  
     */
    public void setBTN(String aInput) 
    {
        aBTN = aInput;
    }

    /**
     * Set UBAN.
     * 
     * @param String aInput  
     *   
     * @author Hongmei Parkin 
     */	
	public void setUban(String aInput) {
		aUban = aInput;
	}
	
    /**
     * Set facility loop.
     * 
     * @param FacilityLoop2 aInput  
     *   
     * @author Lira Galsim  
     */
    public void setFacilityLoop(FacilityLoop2 aInput) 
    {
    	aFacilityLoop = aInput;
    }
}
