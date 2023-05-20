//$Id: RtidMsgCodeRow.java,v 1.2 2008/09/02 16:41:49 jo8461 Exp $
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
//# 08/2008    Jon Costa              Creation.

package com.sbc.eia.bis.rm.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author jc1421
 *
 */
public class RtidMsgCodeRow extends DBRowBase
{
    private RtidMsgCodeRow nextRtidMsgCodeRow = null;
    
    private java.lang.String CODE = "";
    private java.lang.Boolean PROCEED_IND = null;
    private int ATTDSL_NBR = 0;
    private int NON_ATTDSL_NBR = 0;   
    private java.lang.Boolean CONFLICT_IND = null;
    private java.lang.String APPLICATION = "";
    private java.lang.Boolean DSL_SME_ACCT_IND = null;
    private java.lang.Boolean FIBER_IND = null;
    private java.lang.Boolean LFACS_ERROR_IND = null;
    private java.lang.Boolean SM_ERROR_IND = null;
    private java.lang.Boolean DISH_IND = null;
    private java.lang.Boolean DIALUP_IND = null;
    private java.lang.Boolean DSL_MISUSE_IND = null;
    private java.lang.Boolean UVERSE_IND = null;
    private java.lang.Boolean BBPLOOP_NF_IND = null;
    private java.lang.Boolean INVALID_NTI_IND = null;
    private java.lang.Boolean LOAD_COIL_IND = null;
    private java.lang.Boolean TCOMMIT_IND = null;

    /**
     * Constructor
     *
     */
    public RtidMsgCodeRow()
    {
        super();
    }
    
    /** 
     * Constructor
     * @param code
     * @param attdsl_nbr
     * @param non_attdsl_nbr
     * @param proceed_ind
     * @param conflict_ind
     * @param application
     * @param dsl_sme_acct_ind
     * @param fiber_ind
     * @param lfacs_error_ind
     * @param sm_error_ind
     * @param dish_ind
     * @param dialup_ind
     * @param dsl_misuse_ind
     * @param uverse_ind
     * @param bbploop_nf_ind
     * @param invalid_nti_ind
     * @param load_coil_ind
     * @param tcommit_ind
     */
    public RtidMsgCodeRow(ResultSet rs, Logger aLogger) throws SQLException
    {
        super();
        this.setRow(rs, aLogger);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sbc.eia.bis.rm.database.DBRowBase#setRow(java.sql.ResultSet,
     *      com.sbc.bccs.utilities.Logger)
     */
    public void setRow(ResultSet rs, Logger aLogger) throws SQLException
    {
        if (rs.next())
        {
            aLogger.log(LogEventId.INFO_LEVEL_2, 
                        "SQLResult: |" + rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getInt(3) + "|" 
                                       + rs.getInt(4) + "|" + rs.getString(5) + "|" + rs.getString(6) + "|" 
                                       + rs.getString(7) + "|" + rs.getString(8) + "|" + rs.getString(9) + "|" 
                                       + rs.getString(10) + "|" + rs.getString(11) + "|" + rs.getString(12) + "|" 
                                       + rs.getString(13) + "|" + rs.getString(14) + "|" + rs.getString(15) + "|" 
                                       + rs.getString(16) + "|" + rs.getString(17) + "|" + rs.getString(18) + "|");
            this.setCODE(              rs.getString(1));
            this.setPROCEED_IND(       rs.getString(2));
            this.setATTDSL_NBR(        rs.getInt(3));
            this.setNON_ATTDSL_NBR(    rs.getInt(4));
            this.setCONFLICT_IND(      rs.getString(5));
            this.setAPPLICATION(       rs.getString(6));
            this.setDSL_SME_ACCT_IND(  rs.getString(7));
            this.setFIBER_IND(         rs.getString(8));
            this.setLFACS_ERROR_IND(   rs.getString(9));
            this.setSM_ERROR_IND(      rs.getString(10));
            this.setDISH_IND(          rs.getString(11));
            this.setDIALUP_IND(        rs.getString(12));
            this.setDSL_MISUSE_IND(    rs.getString(13));
            this.setUVERSE_IND(        rs.getString(14));
            this.setBBPLOOP_NF_IND(    rs.getString(15));
            this.setINVALID_NTI_IND(   rs.getString(16));
            this.setLOAD_COIL_IND(     rs.getString(17));
            this.setTCOMMIT_IND(       rs.getString(18));
            
            // Make a linked list of rows from the table...
            this.nextRtidMsgCodeRow = new RtidMsgCodeRow(rs, aLogger);

            // But terminate the last link with a null (CODE is a required field in the DB Table).
            if (this.nextRtidMsgCodeRow.CODE.equals(""))
                this.nextRtidMsgCodeRow = null; 
        }
    }
    
    public java.lang.String getAPPLICATION()
    {
        return APPLICATION;
    }

    public void setAPPLICATION(java.lang.String application)
    {
        APPLICATION = application;
    }

    public int getATTDSL_NBR()
    {
        return ATTDSL_NBR;
    }

    public void setATTDSL_NBR(int attdsl_nbr)
    {
        ATTDSL_NBR = attdsl_nbr;
    }

    public java.lang.Boolean getBBPLOOP_NF_IND()
    {
        return BBPLOOP_NF_IND;
    }

    public void setBBPLOOP_NF_IND(java.lang.String bbploop_nf_ind)
    {
        BBPLOOP_NF_IND = new Boolean(bbploop_nf_ind);
    }

    public java.lang.String getCODE()
    {
        return CODE;
    }

    public void setCODE(java.lang.String code)
    {
        CODE = code;
    }

    public java.lang.Boolean getCONFLICT_IND()
    {
        return CONFLICT_IND;
    }

    public void setCONFLICT_IND(java.lang.String conflict_ind)
    {
        CONFLICT_IND = new Boolean(conflict_ind);
    }

    public java.lang.Boolean getDIALUP_IND()
    {
        return DIALUP_IND;
    }

    public void setDIALUP_IND(java.lang.String dialup_ind)
    {
        DIALUP_IND = new Boolean(dialup_ind);
    }

    public java.lang.Boolean getDISH_IND()
    {
        return DISH_IND;
    }

    public void setDISH_IND(java.lang.String dish_ind)
    {
        DISH_IND = new Boolean(dish_ind);
    }

    public java.lang.Boolean getDSL_MISUSE_IND()
    {
        return DSL_MISUSE_IND;
    }

    public void setDSL_MISUSE_IND(java.lang.String dsl_misuse_ind)
    {
        DSL_MISUSE_IND = new Boolean(dsl_misuse_ind);
    }

    public java.lang.Boolean getDSL_SME_ACCT_IND()
    {
        return DSL_SME_ACCT_IND;
    }

    public void setDSL_SME_ACCT_IND(java.lang.String dsl_sme_acct_ind)
    {
        DSL_SME_ACCT_IND = new Boolean(dsl_sme_acct_ind);
    }

    public java.lang.Boolean getFIBER_IND()
    {
        return FIBER_IND;
    }

    public void setFIBER_IND(java.lang.String fiber_ind)
    {
        FIBER_IND = new Boolean(fiber_ind);
    }

    public java.lang.Boolean getINVALID_NTI_IND()
    {
        return INVALID_NTI_IND;
    }

    public void setINVALID_NTI_IND(java.lang.String invalid_nti_ind)
    {
        INVALID_NTI_IND = new Boolean(invalid_nti_ind);
    }

    public java.lang.Boolean getLFACS_ERROR_IND()
    {
        return LFACS_ERROR_IND;
    }

    public void setLFACS_ERROR_IND(java.lang.String lfacs_error_ind)
    {
        LFACS_ERROR_IND = new Boolean(lfacs_error_ind);
    }

    public java.lang.Boolean getLOAD_COIL_IND()
    {
        return LOAD_COIL_IND;
    }

    public void setLOAD_COIL_IND(java.lang.String load_coil_ind)
    {
        LOAD_COIL_IND = new Boolean(load_coil_ind);
    }

    public int getNON_ATTDSL_NBR()
    {
        return NON_ATTDSL_NBR;
    }

    public void setNON_ATTDSL_NBR(int non_attdsl_nbr)
    {
        NON_ATTDSL_NBR = non_attdsl_nbr;
    }

    public java.lang.Boolean getPROCEED_IND()
    {
        return PROCEED_IND;
    }

    public void setPROCEED_IND(java.lang.String proceed_ind)
    {
        PROCEED_IND = new Boolean(proceed_ind);
    }

    public java.lang.Boolean getSM_ERROR_IND()
    {
        return SM_ERROR_IND;
    }

    public void setSM_ERROR_IND(java.lang.String sm_error_ind)
    {
        SM_ERROR_IND = new Boolean(sm_error_ind);
    }

    public java.lang.Boolean getTCOMMIT_IND()
    {
        return TCOMMIT_IND;
    }

    public void setTCOMMIT_IND(java.lang.String tcommit_ind)
    {
        TCOMMIT_IND = new Boolean(tcommit_ind);
    }

    public java.lang.Boolean getUVERSE_IND()
    {
        return UVERSE_IND;
    }

    public void setUVERSE_IND(java.lang.String uverse_ind)
    {
        UVERSE_IND = new Boolean(uverse_ind);
    }

    public RtidMsgCodeRow getNextRtidMsgCodeRow()
    {
        return nextRtidMsgCodeRow;
    }

    public void setNextRtidMsgCodeRow(RtidMsgCodeRow nextRtidMsgCodeRow)
    {
        this.nextRtidMsgCodeRow = nextRtidMsgCodeRow;
    }
}
