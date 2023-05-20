//$Id: NtiGrp.java,v 1.3 2009/02/19 20:13:45 jc1421 Exp $
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
public class NtiGrp
{
    private String aSiteNTI = null;
    private String aSiteNTIModifier = null;
    
    public NtiGrp()
    {
        super();
    }
    
    public NtiGrp(String aNTI, String aNTIModifier)
    {
        super();
        this.setASiteNTI(aNTI);
        this.setASiteNTIModifier(aNTIModifier);
    }

    public String getASiteNTI()
    {
        return aSiteNTI;
    }

    public void setASiteNTI(String siteNTI)
    {
        aSiteNTI = siteNTI;
    }

    public String getASiteNTIModifier()
    {
        return aSiteNTIModifier;
    }

    public void setASiteNTIModifier(String siteNTIModifier)
    {
        aSiteNTIModifier = siteNTIModifier;
    }
}
