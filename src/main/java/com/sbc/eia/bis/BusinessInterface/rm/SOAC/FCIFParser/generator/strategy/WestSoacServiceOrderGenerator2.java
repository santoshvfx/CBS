//$Id: WestSoacServiceOrderGenerator2.java,v 1.4 2008/06/09 16:43:34 op1664 Exp $
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
//#      © 2008-2020 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 02/04/2008  Ott Phannavong		Creator 
//#									LS7: Overrides helper methods in SoacFcifGenerator to generate the output require for LS7
//# 06/06/2008  Ott Phannavong		LS8: CR18775 remove /SSM 
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.strategy;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;

/**
 * @author op1664
 *
 */
public class WestSoacServiceOrderGenerator2 extends
        WestSoacServiceOrderGenerator
{
    /* 
     * Overrides appendBillingAccountNumber() so that it will use
     * appendBillingAccountNumber2() to satisfy LS7 requirement
     */
    protected void appendBillingAccountNumber(
            SoacServiceOrderVO aSoacServcieVo, StringBuffer aRemarkSection)
    {
        appendBillingAccountNumber2(aSoacServcieVo, aRemarkSection);
    }
    /* 
     * Add addition fields to S and E section
     */
    protected void appendSandESection(SoacServiceOrderVO aSoacServcieVo,
            StringBuffer aSandESection)
    {
        appendSandESection2(aSoacServcieVo, aSandESection);
    }
    /* 
     * Overrides appendBCCR() so that it will use
     * appendBCCR2() to satisfy LS7 requirement
     */
    protected void appendBCCR(SoacServiceOrderVO aSoacServiceOrderVo,
            StringBuffer aSandESection)
    {
        appendBCCR2(aSoacServiceOrderVo, aSandESection);
    }
    /* 
     * Overrides appendNewConnectDisconnectForFTTNBP() so that it will use
     * appendNewConnectDisconnectForFTTNBP2() to satisfy LS7 requirement
     */
    protected void appendNewConnectDisconnectForFTTNBP(
            SoacServiceOrderVO aSoacServiceOrderVo, StringBuffer aSandESection)
    {
    	appendNewConnectDisconnectForFTTNBP2(aSoacServiceOrderVo, aSandESection);
    }
    /* 
     * Overrides appendADDITIONAL_LINE_FID(() so that it will use
     * appendSPECIAL_SAFEGUARD_MEASURE_FID() to satisfy LS7 requirement
     * LS7 requirement is to take out ADDITIONAL_LINE_FID and put in /SSM fid
     */
    protected void appendADDITIONAL_LINE_FID(
            SoacServiceOrderVO aSoacServiceOrderVo, StringBuffer aSandESection)
    {
        //LS8: CRC18775 remove /SSM 
    }
}