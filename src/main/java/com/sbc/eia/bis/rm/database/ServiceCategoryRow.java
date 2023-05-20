//$Id: ServiceCategoryRow.java,v 1.2 2009/04/02 20:47:57 lg4542 Exp $
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
 * Class      : ServiceCategoryRow
 * Description: Class file for retrieving rows from SERVICE_CATEGORY table
 */
public class ServiceCategoryRow extends DBRowBase
{
    private java.lang.String SERVICE_TYPE = "";
    private java.lang.String SERVICE_CATEGORY = "";

    /**
     * Constructor
     */
    public ServiceCategoryRow()
    {
        super();
    }

    /**
     * Constructor:
     * 
     * @param aSERVICE_TYPE
     * @param aSERVICE_DESCRIPTION
     * @param aSERVICE_CATEGORY
     */
    public ServiceCategoryRow(String aSERVICE_TYPE, String aSERVICE_CATEGORY)
    {
        super();
        this.setSERVICE_TYPE(aSERVICE_TYPE);
        this.setSERVICE_CATEGORY(aSERVICE_CATEGORY);
    }

    /**
     * @param rs
     * @param aLogger
     */
    public void setRow(ResultSet rs, Logger aLogger) throws SQLException
    {
        if (rs.next())
        {
            aLogger.log(LogEventId.INFO_LEVEL_2, "SQLResult: |" + rs.getString(1) + "|" + rs.getString(2) + "|");
            this.setSERVICE_TYPE(rs.getString(1));
            this.setSERVICE_CATEGORY(rs.getString(2));
        }
    }

    /**
     * @return Returns the sERVICE TYPE.
     */
    public java.lang.String getSERVICE_TYPE()
    {
        return SERVICE_TYPE;
    }

    /**
     * @param service_type
     *            The sERVICE_TYPE to set.
     */
    public void setSERVICE_TYPE(java.lang.String service_type)
    {
    	SERVICE_TYPE = service_type;
    }

    /**
     * @return Returns the sERVICE CATEGORY.
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
}