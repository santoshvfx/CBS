//$Id: QNISite.java,v 1.2 2009/02/10 21:32:03 jc1421 Exp $
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
public class QNISite
{
    private String aNTIConversionDate = null;
    private NtiGrp aNtiGrp[] = null;
    
    public QNISite()
    {
        super();
    }
    
    public String getANTIConversionDate()
    {
        return aNTIConversionDate;
    }
    public void setANTIConversionDate(String conversionDate)
    {
        aNTIConversionDate = conversionDate;
    }
    public NtiGrp[] getANtiGrp()
    {
        return aNtiGrp;
    }
    public void setANtiGrp(NtiGrp[] ntiGrp)
    {
        aNtiGrp = ntiGrp;
    }
}
