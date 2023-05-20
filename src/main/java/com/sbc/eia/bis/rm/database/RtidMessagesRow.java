//$Id: RtidMessagesRow.java,v 1.5 2008/03/14 13:13:10 jc1421 Exp $
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
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 08/2007	   Jon Costa			  Creation.
//# 02/2008	   Jon Costa			  LS7: column ORDER_ACTION_TYPE added to table
//# 02/2008	   Jon Costa			  LS7: Changed table name to: RTID_MESSAGES2

/*****************************************************************************
//NOTE: DO NOT RTAG THIS FILE TO ANY RELEASE EALIER THAN LIGHTSPEED 7 (RM21.0)
******************************************************************************/
package com.sbc.eia.bis.rm.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author jc1421
 */
public class RtidMessagesRow extends DBRowBase
{
    private java.lang.String CODE = "";
    private java.lang.String APPLICATION = "";    
    private java.lang.String SEVERITY = "";
    private java.lang.String TEXT = ""; 
    private java.lang.String ORDER_ACTION_TYPE = "";
    /**
     * Constructor
     */
    public RtidMessagesRow()
    {
        super();
    }

    /**
     * Constructor:
     * 
     * @param aCODE
     * @param aAPPLICATION
     * @param aSEVERITY
     * @param aTEXT
     */
    public RtidMessagesRow(String aCODE, String aAPPLICATION, String aSEVERITY, String aTEXT, String aORDER_ACTION_TYPE)
    {
        super();
        this.setCODE(aCODE);
        this.setAPPLICATION(aAPPLICATION);        
        this.setSEVERITY(aSEVERITY);
        this.setTEXT(aTEXT);
        this.setORDER_ACTION_TYPE(aORDER_ACTION_TYPE);
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
                    + rs.getString(3) + "|" + rs.getString(4) + "|" + rs.getString(5) + "|");
            this.setCODE(rs.getString(1));
            this.setAPPLICATION(rs.getString(2));            
            this.setSEVERITY(rs.getString(3));
            this.setTEXT(rs.getString(4));
            this.setORDER_ACTION_TYPE(rs.getString(5));
        }
    }

    /**
     * @return Returns the aPPLICATION.
     */
    public java.lang.String getAPPLICATION()
    {
        return APPLICATION;
    }

    /**
     * @param application
     *            The aPPLICATION to set.
     */
    public void setAPPLICATION(java.lang.String application)
    {
        APPLICATION = application;
    }

    /**
     * @return Returns the cODE.
     */
    public java.lang.String getCODE()
    {
        return CODE;
    }

    /**
     * @param code
     *            The cODE to set.
     */
    public void setCODE(java.lang.String code)
    {
        CODE = code;
    }

    /**
     * @return Returns the sEVERITY.
     */
    public java.lang.String getSEVERITY()
    {
        return SEVERITY;
    }

    /**
     * @param severity
     *            The sEVERITY to set.
     */
    public void setSEVERITY(java.lang.String severity)
    {
        SEVERITY = severity;
    }

    /**
     * @return Returns the tEXT.
     */
    public java.lang.String getTEXT()
    {
        return TEXT;
    }

    /**
     * @param text
     *            The tEXT to set.
     */
    public void setTEXT(java.lang.String text)
    {
        TEXT = text;
    }
    /**
     * @return Returns the oRDER_ACTION_TYPE.
     */
    public java.lang.String getORDER_ACTION_TYPE()
    {
        return this.ORDER_ACTION_TYPE;
    }
    /**
     * @param order_action_type The oRDER_ACTION_TYPE to set.
     */
    public void setORDER_ACTION_TYPE(java.lang.String order_action_type)
    {
        this.ORDER_ACTION_TYPE = order_action_type;
    }
}
