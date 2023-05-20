//$Id: GraniteFiberServicesRow.java,v 1.1 2009/02/05 03:01:24 lg4542 Exp $
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
 * Class      : GraniteFiberServicesRow
 * Description: Class file for retrieving rows 
 * from GRANITE_FIBER_SERVICES table.
 */
public class GraniteFiberServicesRow extends DBRowBase
{
    private java.lang.String NTI = "";
    private java.lang.String ORDER_MATCH = "";
    private java.lang.String ADSL = "";
    private java.lang.String VDSL = "";
    private java.lang.String GPON = "";
    private java.lang.String VOICE = "";
    private java.lang.String VLAN = "";
    private java.lang.String SERVICE_NAME = "";
    private java.lang.String CONFLICT_IND = "";
    
    /**
     * Constructor
     */
    public GraniteFiberServicesRow()
    {
        super();
    }

    /**
     * Constructor:
     * 
     * @param aNTI
     * @param aORDER_MATCH
     * @param aADSL
     * @param aVDSL
     * @param aGPON
     * @param aVOICE
     * @param aVLAN
     * @param aSERVICE_NAME
     * @paramM aCONFLICT_IND 
     */
    public GraniteFiberServicesRow(String aNTI, String aORDER_MATCH, String aADSL, String aVDSL, 
    		String aGPON, String aVOICE, String aVLAN, String aSERVICE_NAME, String aCONFLICT_IND)
    {
        super();
        this.setNTI(aNTI);
        this.setORDER_MATCH(aORDER_MATCH);
        this.setADSL(aADSL);
        this.setVDSL(aVDSL);
        this.setGPON(aGPON);
        this.setVOICE(aVOICE);
        this.setVLAN(aVLAN);
        this.setSERVICE_NAME(aSERVICE_NAME);
        this.setCONFLICT_IND(aCONFLICT_IND);
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
                    + rs.getString(7) + "|" + rs.getString(8) + "|" + rs.getString(9) + "|" );
            this.setNTI(rs.getString(1));
            this.setORDER_MATCH(rs.getString(2));
            this.setADSL(rs.getString(3));
            this.setVDSL(rs.getString(4));
            this.setGPON(rs.getString(5));
            this.setVOICE(rs.getString(6));
            this.setVLAN(rs.getString(7));
            this.setSERVICE_NAME(rs.getString(8));
            this.setCONFLICT_IND(rs.getString(9));
        }
    }

    /**
     * @return Returns the nTI.
     */
    public java.lang.String getNTI()
    {
        return NTI;
    }

    /**
     * @return Returns the oRDER_MATCH.
     */
    public java.lang.String getORDER_MATCH()
    {
        return ORDER_MATCH;
    }
    
    /**
     * @return Returns the aDSL.
     */
    public java.lang.String getADSL()
    {
        return ADSL;
    }    
    
    /**
     * @return Returns the vDSL.
     */
    public java.lang.String getVDSL()
    {
        return VDSL;
    }
    
    /**
     * @return Returns the gPON.
     */
    public java.lang.String getGPON()
    {
        return GPON;
    }
    
    /**
     * @return Returns the vOICE.
     */
    public java.lang.String getVOICE()
    {
        return VOICE;
    }
    
    /**
     * @return Returns the vLAN.
     */
    public java.lang.String getVLAN()
    {
        return VLAN;
    }
    
    /**
     * @return Returns the sERVICE_NAME.
     */
    public java.lang.String getSERVICE_NAME()
    {
        return SERVICE_NAME;
    }
    
    /**
     * @return Returns the cONFLICT_IND.
     */
    public java.lang.String getCONFLICT_IND()
    {
        return CONFLICT_IND;
    }    

    /**
     * @param nti
     *            The nTI to set.
     */
    public void setNTI(java.lang.String nti)
    {
        NTI = nti;
    }
    
    /**
     * @param order_match
     *            The oRDER_MATCH to set.
     */
    public void setORDER_MATCH(java.lang.String order_match)
    {
    	ORDER_MATCH = order_match;
    }
    
    /**
     * @param adsl
     *            The aDSL to set.
     */
    public void setADSL(java.lang.String adsl)
    {
    	ADSL = adsl;
    }
    
    /**
     * @param vdsl
     *            The vDSL to set.
     */
    public void setVDSL(java.lang.String vdsl)
    {
    	VDSL = vdsl;
    }
    
    /**
     * @param gpon
     *            The gPON to set.
     */
    public void setGPON(java.lang.String gpon)
    {
    	GPON = gpon;
    }
    
    /**
     * @param voice
     *            The vOICE to set.
     */
    public void setVOICE(java.lang.String voice)
    {
    	VOICE = voice;
    } 
    
    /**
     * @param vlan
     *            The VLAN to set.
     */
    public void setVLAN(java.lang.String vlan)
    {
    	VLAN = vlan;
    }  
    
    /**
     * @param service_name
     *            The SERVICE_NAME to set.
     */
    public void setSERVICE_NAME(java.lang.String service_name)
    {
    	SERVICE_NAME = service_name;
    }
    
    /**
     * @param conflict_ind
     *            The CONFLICT_IND to set.
     */
    public void setCONFLICT_IND(java.lang.String conflict_ind)
    {
    	CONFLICT_IND = conflict_ind;
    }    
}