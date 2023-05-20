//$Id: LfacsFiberServicesRow.java,v 1.1 2009/02/05 03:01:24 lg4542 Exp $
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
//# Date          | Author              | Notes
//# --------------------------------------------------------------------------
//# 02/04/2009	    Lira Galsim			  Creation.

package com.sbc.eia.bis.rm.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Class      : LfacsFiberServicesRow
 * Description: Class file for retrieving rows 
 * from LFACS_FIBER_SERVICES table.
 */
public class LfacsFiberServicesRow extends DBRowBase
{
    private java.lang.String REGION = "";
    private java.lang.String CLS_SERVICE_CODE = "";
    private java.lang.String CLS_MODIFIER = "";
    private java.lang.String USOC = "";
    private java.lang.String SERVICE_CATEGORY = "";
    private java.lang.String SERVICE_NAME = "";
    private java.lang.String PROVISIONING_CONFLICT_IND = "";
    private java.lang.String NEGOTIATION_CONFLICT_IND = "";
    
    /**
     * Constructor
     */
    public LfacsFiberServicesRow()
    {
        super();
    }

    /**
     * Constructor:
     * 
     * @param aREGION
     * @param aCLS_SERVICE_CODE
     * @param aCLS_MODIFIER
     * @param aUSOC
     * @param aSERVICE_CATEGORY
     * @param aSERVICE_NAME
     * @param aPROVISIONING_CONFLICT_IND
     * @param aNEGOTIATION_CONFLICT_IND
     */
    public LfacsFiberServicesRow(String aREGION, String aCLS_SERVICE_CODE, String aCLS_MODIFIER, 
    		String aUSOC, String aSERVICE_CATEGORY, String aSERVICE_NAME, 
    		String aPROVISIONING_CONFLICT_IND, String aNEGOTIATION_CONFLICT_IND)
    {
        super();
        this.setREGION(aREGION);
        this.setCLS_SERVICE_CODE(aCLS_SERVICE_CODE);
        this.setCLS_MODIFIER(aCLS_MODIFIER);
        this.setUSOC(aUSOC);
        this.setSERVICE_CATEGORY(aSERVICE_CATEGORY);
        this.setSERVICE_NAME(aSERVICE_NAME);
        this.setPROVISIONING_CONFLICT_IND(aPROVISIONING_CONFLICT_IND);
        this.setNEGOTIATION_CONFLICT_IND(aNEGOTIATION_CONFLICT_IND);
    }

    /**
     * @param rs
     * @param aLogger
     */
    public void setRow(ResultSet rs, Logger aLogger) throws SQLException
    {
        if (rs.next())
        {
        	aLogger.log(LogEventId.INFO_LEVEL_2, "SQLResult: |" + rs.getString(1) + "|" + rs.getString(2) + "|"
                    + rs.getString(3) + "|" + rs.getString(4) + "|" + rs.getString(5) + "|" + rs.getString(6) + "|"
                    + rs.getString(7) + "|" + rs.getString(8) + "|" );
            this.setREGION(rs.getString(1));
            this.setCLS_SERVICE_CODE(rs.getString(2));
            this.setCLS_MODIFIER(rs.getString(3));
            this.setUSOC(rs.getString(4));
            this.setSERVICE_CATEGORY(rs.getString(5));
            this.setSERVICE_NAME(rs.getString(6));
            this.setPROVISIONING_CONFLICT_IND(rs.getString(7));
            this.setNEGOTIATION_CONFLICT_IND(rs.getString(8));
        }
    }

    /**
     * @return Returns the cLS_MODIFIER.
     */
    public java.lang.String getCLS_MODIFIER()
    {
        return CLS_MODIFIER;
    }

    /**
     * @param cls_modifier
     *            The cLS_MODIFIER to set.
     */
    public void setCLS_MODIFIER(java.lang.String cls_modifier)
    {
        CLS_MODIFIER = cls_modifier;
    }

    /**
     * @return Returns the cLS_SERVICE_CODE.
     */
    public java.lang.String getCLS_SERVICE_CODE()
    {
        return CLS_SERVICE_CODE;
    }

    /**
     * @param cls_service_code
     *            The cLS_SERVICE_CODE to set.
     */
    public void setCLS_SERVICE_CODE(java.lang.String cls_service_code)
    {
        CLS_SERVICE_CODE = cls_service_code;
    }

    /**
     * @return Returns the rEGION.
     */
    public java.lang.String getREGION()
    {
        return REGION;
    }

    /**
     * @param region
     *            The rEGION to set.
     */
    public void setREGION(java.lang.String region)
    {
        REGION = region;
    }

    /**
     * @return Returns the sERVICE_NAME.
     */
    public java.lang.String getSERVICE_NAME()
    {
        return SERVICE_NAME;
    }

    /**
     * @param service_name
     *            The sERVICE_NAME to set.
     */
    public void setSERVICE_NAME(java.lang.String service_name)
    {
        SERVICE_NAME = service_name;
    }

    /**
     * @return Returns the sERVICE_CATEGORY.
     */
    public java.lang.String getSERVICE_CATEGORY()
    {
        return SERVICE_CATEGORY;
    }

    /**
     * @param service_category
     *            The sERVICE_CATEGORY to set.
     */
    public void setSERVICE_CATEGORY(java.lang.String service_category)
    {
    	SERVICE_CATEGORY = service_category;
    }

    /**
     * @return Returns the uSOC.
     */
    public java.lang.String getUSOC()
    {
        return USOC;
    }

    /**
     * @param usoc
     *            The uSOC to set.
     */
    public void setUSOC(java.lang.String usoc)
    {
        USOC = usoc;
    }
    
    /**
     * @return Returns pROVISIONING_CONFLICT_IND.
     */
    public java.lang.String getPROVISIONING_CONFLICT_IND()
    {
    	return PROVISIONING_CONFLICT_IND;
    }
    
    /**
     * @param provisioning_conflict_indicator
     *            The pROVISIONING_CONFLICT_IND to set.
     */
    public void setPROVISIONING_CONFLICT_IND(java.lang.String provisioning_conflict_indicator)
    {
    	PROVISIONING_CONFLICT_IND = provisioning_conflict_indicator;
    }
    
    /**
     * @return Returns nEGOTIATION_CONFLICT_IND.
     */
    public java.lang.String getNEGOTIATION_CONFLICT_IND()
    {
    	return NEGOTIATION_CONFLICT_IND;
    }
    
    /**
     * @param negotiation_conflict_indicator
     *            The nEGOTIATION_CONFLICT_IND to set.
     */
    public void setNEGOTIATION_CONFLICT_IND(java.lang.String negotiation_conflict_indicator)
    {
    	NEGOTIATION_CONFLICT_IND = negotiation_conflict_indicator;
    }
}