// $Id: FiberFacilitiesRow.java,v 1.2 2008/03/14 13:13:10 jc1421 Exp $
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

package com.sbc.eia.bis.rm.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author jc1421
 */
public class FiberFacilitiesRow extends DBRowBase
{
    private java.lang.String FIBER_FACILITY = "";
    private java.lang.String TYPE = "";
    private java.lang.String MODEL = "";
    private java.lang.String LTS = "";

    /**
     * Constructor
     */
    public FiberFacilitiesRow()
    {
        super();
    }

    /**
     * Constructor:
     * 
     * @param aFIBER_FACILITY
     * @param aTYPE
     * @param aMODEL
     * @param aLTS
     */
    public FiberFacilitiesRow(String aFIBER_FACILITY, String aTYPE, String aMODEL, String aLTS)
    {
        super();
        this.setFIBER_FACILITY(aFIBER_FACILITY);
        this.setTYPE(aTYPE);
        this.setMODEL(aMODEL);
        this.setLTS(aLTS);
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
                    + rs.getString(3) + "|" + rs.getString(4) + "|");
            this.setFIBER_FACILITY(rs.getString(1));
            this.setTYPE(rs.getString(2));
            this.setMODEL(rs.getString(3));
            this.setLTS(rs.getString(4));
        }
    }

    /**
     * @return Returns the fIBER_FACILITY.
     */
    public java.lang.String getFIBER_FACILITY()
    {
        return FIBER_FACILITY;
    }

    /**
     * @param fiber_facility
     *            The fIBER_FACILITY to set.
     */
    public void setFIBER_FACILITY(java.lang.String fiber_facility)
    {
        FIBER_FACILITY = fiber_facility;
    }

    /**
     * @return Returns the lTS.
     */
    public java.lang.String getLTS()
    {
        return LTS;
    }

    /**
     * @param lts
     *            The lTS to set.
     */
    public void setLTS(java.lang.String lts)
    {
        LTS = lts;
    }

    /**
     * @return Returns the mODEL.
     */
    public java.lang.String getMODEL()
    {
        return MODEL;
    }

    /**
     * @param model
     *            The mODEL to set.
     */
    public void setMODEL(java.lang.String model)
    {
        MODEL = model;
    }

    /**
     * @return Returns the tYPE.
     */
    public java.lang.String getTYPE()
    {
        return TYPE;
    }

    /**
     * @param type
     *            The tYPE to set.
     */
    public void setTYPE(java.lang.String type)
    {
        TYPE = type;
    }
}