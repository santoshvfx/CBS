//$Id: QNIPath.java,v 1.2 2009/02/10 21:33:42 jc1421 Exp $
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
public class QNIPath
{
    private String aID = null;
    private String aCategory = null;
    private String aStatus = null;
    private String aOrderNumber = null;
    private String aOrderType = null;
    private AttrGrp aAttrGrp = null;
    
    public QNIPath()
    {
        super();
    }

    public AttrGrp getAAttrGrp()
    {
        return aAttrGrp;
    }

    public void setAAttrGrp(AttrGrp attrGrp)
    {
        aAttrGrp = attrGrp;
    }

    public String getACategory()
    {
        return aCategory;
    }

    public void setACategory(String category)
    {
        aCategory = category;
    }

    public String getAID()
    {
        return aID;
    }

    public void setAID(String aid)
    {
        aID = aid;
    }

    public String getAOrderNumber()
    {
        return aOrderNumber;
    }

    public void setAOrderNumber(String orderNumber)
    {
        aOrderNumber = orderNumber;
    }

    public String getAOrderType()
    {
        return aOrderType;
    }

    public void setAOrderType(String orderType)
    {
        aOrderType = orderType;
    }

    public String getAStatus()
    {
        return aStatus;
    }

    public void setAStatus(String status)
    {
        aStatus = status;
    }
}
