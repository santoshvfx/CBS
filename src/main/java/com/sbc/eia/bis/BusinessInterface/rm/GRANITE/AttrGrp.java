//$Id: AttrGrp.java,v 1.2 2009/02/10 21:37:16 jc1421 Exp $
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
//# 01/2009    Jon Costa              Creation.

package com.sbc.eia.bis.BusinessInterface.rm.GRANITE;

/**
 * @author jc1421
 *
 */
public class AttrGrp
{
    private String aDueDate = null;
    private String aPathNTI = null;
    private String aPathNTIModifier = null;
    
    public AttrGrp()
    {
        super();
    }
    
    public String getADueDate()
    {
        return aDueDate;
    }
    public void setADueDate(String dueDate)
    {
        aDueDate = dueDate;
    }
    public String getAPathNTI()
    {
        return aPathNTI;
    }
    public void setAPathNTI(String pathNTI)
    {
        aPathNTI = pathNTI;
    }
    public String getAPathNTIModifier()
    {
        return aPathNTIModifier;
    }
    public void setAPathNTIModifier(String pathNTIModifier)
    {
        aPathNTIModifier = pathNTIModifier;
    }
}
