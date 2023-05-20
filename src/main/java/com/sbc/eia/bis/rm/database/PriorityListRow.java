//$Id: PriorityListRow.java,v 1.1 2009/02/05 03:01:24 lg4542 Exp $
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
 * Class      : PriorityListRow
 * Description: Class file for retrieving rows from PRIORITY_LIST table
 */
public class PriorityListRow extends DBRowBase
{
    private int PRIORITY_NUMBER = 0;
    private java.lang.String SERVICE_NAME = "";

    /**
     * Constructor
     */
    public PriorityListRow()
    {
        super();
    }

    /**
     * Constructor:
     * 
     * @param aPRIORITY_NUMBER
     * @param aSERVICE_NAME
     */
    public PriorityListRow(int aPRIORITY_NUMBER, String aSERVICE_NAME)
    {
        super();
        this.setPRIORITY_NUMBER(aPRIORITY_NUMBER);
        this.setSERVICE_NAME(aSERVICE_NAME);
    }

    /**
     * @param rs
     * @param aLogger
     */
    public void setRow(ResultSet rs, Logger aLogger) throws SQLException
    {
        if (rs.next())
        {
            aLogger.log(LogEventId.INFO_LEVEL_2, "SQLResult: |" + rs.getInt(1) + "|" + rs.getString(2) + "|");
            this.setPRIORITY_NUMBER(rs.getInt(1));
            this.setSERVICE_NAME(rs.getString(2));
        }
    }

    /**
     * @return Returns the pRIORITY_NUMBER.
     */
    public int getPRIORITY_NUMBER()
    {
        return PRIORITY_NUMBER;

    }

    /**
     * @param priority_number
     *            The pRIORITY_NUMBER to set.
     */
    public void setPRIORITY_NUMBER(int priority_number)
    {
    	PRIORITY_NUMBER = priority_number;
    }

    /**
     * @return Returns the sERVICE NAME.
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
}