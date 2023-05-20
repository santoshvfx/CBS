//$Id: LfacsServicesRow.java,v 1.5 2008/09/29 19:31:48 vc7563 Exp $
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
//# 08/29/2008	Souvik Paul			  CR 20389: Added 3 new columns to the table LFACS_SERVICES2
//# 08/09/10	Souvik Paul			  Added logging of exception columns
//# 09/29/2008	Vickie Ng			  LS9: removed ORDER_NUMBER_MATCH and EXCEPTION_PROCEED_INDICATOR columns

package com.sbc.eia.bis.rm.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author jc1421
 */
public class LfacsServicesRow extends DBRowBase
{
    private java.lang.String REGION = "";
    private java.lang.Boolean LS_CONDITIONED = null;
    private java.lang.String CLS_SERVICE_CODE = "";
    private java.lang.String CLS_MODIFIER = "";
    private java.lang.String USOC = "";
    private java.lang.String SERVICE_TYPE = "";
    private java.lang.String SERVICE_NAME = "";
    private java.lang.Boolean SERVICE_CONFLICT = null;
    private java.lang.String EXCEPTION_CLIENTS = "";
    private java.lang.String EXCEPTION_CONFLICT_INDICATOR = "";  
    
    /**
     * Constructor
     */
    public LfacsServicesRow()
    {
        super();
    }

    /**
     * Constructor:
     * 
     * @param aREGION
     * @param aLS_CONDITIONED
     * @param aCLS_SERVICE_CODE
     * @param aCLS_MODIFIER
     * @param aUSOC
     * @param aSERVICE_TYPE
     * @param aSERVICE_NAME
     * @param aSERVICE_CONFLICT
     * @param aEXCEPTION_CLIENTS
     * @param aEXCEPTION_CONFLICT_INDICATOR
     */
    public LfacsServicesRow(String aREGION, Boolean aLS_CONDITIONED,
            String aCLS_SERVICE_CODE, String aCLS_MODIFIER, String aUSOC, String aSERVICE_TYPE, 
            String aSERVICE_NAME, Boolean aSERVICE_CONFLICT, 
            String aEXCEPTION_CLIENTS, String aEXCEPTION_CONFLICT_INDICATOR)
    {
        super();
        this.setREGION(aREGION);
        this.setLS_CONDITIONED(aLS_CONDITIONED);
        this.setCLS_SERVICE_CODE(aCLS_SERVICE_CODE);
        this.setCLS_MODIFIER(aCLS_MODIFIER);
        this.setUSOC(aUSOC);
        this.setSERVICE_TYPE(aSERVICE_TYPE);
        this.setSERVICE_NAME(aSERVICE_NAME);
        this.setSERVICE_CONFLICT(aSERVICE_CONFLICT);
        this.setEXCEPTION_CLIENTS(aEXCEPTION_CLIENTS);
        this.setEXCEPTION_CONFLICT_INDICATOR(aEXCEPTION_CONFLICT_INDICATOR);
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
                    + rs.getString(7) + "|" + rs.getString(8) + "|" + rs.getString(9) + "|" 
                    + rs.getString(10) + "|" );
            this.setREGION(rs.getString(1));
            this.setLS_CONDITIONED(Boolean.valueOf(rs.getString(2)));
            this.setCLS_SERVICE_CODE(rs.getString(3));
            this.setCLS_MODIFIER(rs.getString(4));
            this.setUSOC(rs.getString(5));
            this.setSERVICE_TYPE(rs.getString(6));
            this.setSERVICE_NAME(rs.getString(7));
            this.setSERVICE_CONFLICT(Boolean.valueOf(rs.getString(8)));
            this.setEXCEPTION_CLIENTS(rs.getString(9));
            this.setEXCEPTION_CONFLICT_INDICATOR(rs.getString(10));
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
     * @return Returns the lS_CONDITIONED.
     */
    public java.lang.Boolean getLS_CONDITIONED()
    {
        return LS_CONDITIONED;
    }

    /**
     * @param ls_conditioned
     *            The lS_CONDITIONED to set.
     */
    public void setLS_CONDITIONED(java.lang.Boolean ls_conditioned)
    {
        LS_CONDITIONED = ls_conditioned;
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
     * @return Returns the sERVICE_CONFLICT.
     */
    public java.lang.Boolean getSERVICE_CONFLICT()
    {
        return SERVICE_CONFLICT;
    }

    /**
     * @param service_conflict
     *            The sERVICE_CONFLICT to set.
     */
    public void setSERVICE_CONFLICT(java.lang.Boolean service_conflict)
    {
        SERVICE_CONFLICT = service_conflict;
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
     * @return Returns the sERVICE_TYPE.
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
     * @return Returns EXCEPTION_CLIENTS.
     */
    public java.lang.String getEXCEPTION_CLIENTS()
    {
    	return EXCEPTION_CLIENTS;
    }
    
    /**
     * @param exception_clients
     *            The EXCEPTION_CLIENTS to set.
     */
    public void setEXCEPTION_CLIENTS(java.lang.String exception_clients)
    {
    	EXCEPTION_CLIENTS = exception_clients;
    }
    
    /**
     * @return Returns EXCEPTION_CONFLICT_INDICATOR.
     */
    public java.lang.String getEXCEPTION_CONFLICT_INDICATOR()
    {
    	return EXCEPTION_CONFLICT_INDICATOR;
    }
    
    /**
     * @param exception_conflict_indicator
     *            The EXCEPTION_CONFLICT_INDICATOR to set.
     */
    public void setEXCEPTION_CONFLICT_INDICATOR(java.lang.String exception_conflict_indicator)
    {
    	EXCEPTION_CONFLICT_INDICATOR = exception_conflict_indicator;
    }
}
