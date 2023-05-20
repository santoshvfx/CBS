//$Id: LfacsIpdslamServicesRow.java,v 1.2 2009/02/10 18:55:51 lg4542 Exp $
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
 * Class      : LfacsIpdslamServicesRow
 * Description: Class file for retrieving rows 
 * from LFACS_IPDSLAM_SERVICES_VIEW.
 */
public class LfacsIpdslamServicesRow extends DBRowBase
{
    private java.lang.String REGION = "";
    private java.lang.String CLS_SERVICE_CODE = "";
    private java.lang.String CLS_MODIFIER = "";
    private java.lang.String USOC = "";
    private java.lang.String NETWORK_INTERFACE = "";
    private java.lang.String SERVICE_CATEGORY = "";
    private java.lang.String SERVICE_NAME = "";
    private java.lang.String PROVISIONING_CONFLICT_IND = "";
    private java.lang.String NEGOTIATION_CONFLICT_IND = "";
    private java.lang.String OK_TO_PROCEED_IND = "";
    private int PRIORITY_NUMBER = 0;
    
    /**
     * Constructor
     */
    public LfacsIpdslamServicesRow()
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
     * @param aNETWORK_INTERFACE
     * @param aSERVICE_CATEGORY
     * @param aSERVICE_NAME
     * @param aPROVISIONING_CONFLICT_IND
     * @param aNEGOTIATION_CONFLICT_IND
     * @param aOK_TO_PROCEED_IND
     * @param aPRIORITY_NUMBER
     */
    public LfacsIpdslamServicesRow(String aREGION, String aCLS_SERVICE_CODE, String aCLS_MODIFIER, 
    		String aUSOC, String aNETWORK_INTERFACE, String aSERVICE_CATEGORY, String aSERVICE_NAME, 
    		String aPROVISIONING_CONFLICT_IND, String aNEGOTIATION_CONFLICT_IND, 
    		String aOK_TO_PROCEED_IND, int aPRIORITY_NUMBER)
    {
        super();
        this.setREGION(aREGION);
        this.setCLS_SERVICE_CODE(aCLS_SERVICE_CODE);
        this.setCLS_MODIFIER(aCLS_MODIFIER);
        this.setUSOC(aUSOC);
        this.setNETWORK_INTERFACE(aNETWORK_INTERFACE);
        this.setSERVICE_CATEGORY(aSERVICE_CATEGORY);
        this.setSERVICE_NAME(aSERVICE_NAME);
        this.setPROVISIONING_CONFLICT_IND(aPROVISIONING_CONFLICT_IND);
        this.setNEGOTIATION_CONFLICT_IND(aNEGOTIATION_CONFLICT_IND);
        this.setOK_TO_PROCEED_IND(aOK_TO_PROCEED_IND);
        this.setPRIORITY_NUMBER(aPRIORITY_NUMBER);
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
                    + rs.getString(7) + "|" + rs.getString(8) + "|" + rs.getString(9) + "|" + rs.getString(10) + "|"
                    + rs.getInt(11) + "|" );
            this.setREGION(rs.getString(1));
            this.setCLS_SERVICE_CODE(rs.getString(2));
            this.setCLS_MODIFIER(rs.getString(3));
            this.setUSOC(rs.getString(4));
            this.setNETWORK_INTERFACE(rs.getString(5));
            this.setSERVICE_CATEGORY(rs.getString(6));
            this.setSERVICE_NAME(rs.getString(7));
            this.setPROVISIONING_CONFLICT_IND(rs.getString(8));
            this.setNEGOTIATION_CONFLICT_IND(rs.getString(9));
            this.setOK_TO_PROCEED_IND(rs.getString(10));
            this.setPRIORITY_NUMBER(rs.getInt(11));
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
     * @return Returns the nETWORK_INTERFACE.
     */
    public java.lang.String getNETWORK_INTERFACE()
    {
        return NETWORK_INTERFACE;
    }

    /**
     * @param network_interface
     *            The nETWORK_INTERFACE to set.
     */
    public void setNETWORK_INTERFACE(java.lang.String network_interface)
    {
    	NETWORK_INTERFACE = network_interface;
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

    /**
     * @return Returns oK_TO_PROCEED_IND.
     */
    public java.lang.String getOK_TO_PROCEED_IND()
    {
    	return OK_TO_PROCEED_IND;
    }
    
    /**
     * @param ok_to_proceed_indicator
     *            The oK_TO_PROCEED_IND to set.
     */
    public void setOK_TO_PROCEED_IND(java.lang.String ok_to_proceed_indicator)
    {
    	OK_TO_PROCEED_IND = ok_to_proceed_indicator;
    }
    
    /**
     * @return Returns PRIORITY_NUMBER.
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
}