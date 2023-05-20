//$Id: QNIResponseHelper.java,v 1.9 2009/03/04 19:11:44 jc1421 Exp $
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

import java.util.ArrayList;
import java.util.Hashtable;

import com.att.it.granite.Attributes_GRP_t;
import com.att.it.granite.NTI_GRP_t;
import com.att.it.granite.QueryNetworkInventoryResponse;
import com.att.it.granite.Site_t;
import com.att.it.granite._Seq_defaultPath_t;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationRequestHelper;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationResponseHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

public class QNIResponseHelper extends GRANITE
{
    public QNIResponseHelper(Utility utility, Hashtable properties) throws InvalidData,
                                                                   AccessDenied,
                                                                   BusinessViolation,
                                                                   SystemFailure,
                                                                   NotImplemented,
                                                                   ObjectNotFound,
                                                                   DataNotFound
    {
        super(utility, properties);
    }

    /**
     * @param GraniteResp
     * @return
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
     */
    public QNIResponse buildResponse(BisContext aContext,
                                     QueryNetworkInventoryResponse GraniteResp,
                                     PublishValidateFacilityNotificationRequestHelper aRequestHelper,
                                     PublishValidateFacilityNotificationResponseHelper aResponseHelper) throws InvalidData,
                                                                                                       AccessDenied,
                                                                                                       BusinessViolation,
                                                                                                       SystemFailure,
                                                                                                       NotImplemented,
                                                                                                       ObjectNotFound,
                                                                                                       DataNotFound
    {
        String myMethodName = "QNIRequestHelper: buildRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        logResponseData(GraniteResp);

        QNISite aQNISite = new QNISite();
        aQNISite.setANTIConversionDate(GraniteResp != null ? GraniteResp.getSite().getNTIConversionDate() : null);
        aQNISite.setANtiGrp(this.getNtiGrp(GraniteResp != null ? GraniteResp.getSite().getNTI_GRP() : null));

        QNIResponse aQNIResponse = new QNIResponse();
        aQNIResponse.setASite(aQNISite);
        aQNIResponse.setAPath(this.getPathGrp(GraniteResp != null ? GraniteResp.getPath() : null));
        aQNIResponse.setAContext(aContext);
        aQNIResponse.setARequestHelper(aRequestHelper);
        aQNIResponse.setAResponseHelper(aResponseHelper);

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aQNIResponse;
    }
    
    private void logResponseData(QueryNetworkInventoryResponse GraniteResp)
    {
        String L2 = LogEventId.INFO_LEVEL_2;        
        aUtility.log(L2, "Logging Granite response - start");
        try
        {
            Site_t aSite = GraniteResp.getSite();
            if (aSite != null)
            {
                aUtility.log(L2, "     Site.NTIConversionDate:[" + aSite.getNTIConversionDate() + "]");
                if (aSite.getNTI_GRP() != null)
                {
                    for (int i = 0; i < aSite.getNTI_GRP().length; i++)
                    {
                        aUtility.log(L2, "        Site.NTI_GRP[" + i + "].NTI:[" + aSite.getNTI_GRP()[i].getNTI() + "]");
                        aUtility.log(L2, "Site.NTI_GRP[" + i + "].NTIModifier:["
                                         + aSite.getNTI_GRP()[i].getNTIModifier() + "]");
                    }
                }
            }
            _Seq_defaultPath_t aPath = GraniteResp.getPath();
            if (aPath != null)
            {
                for (int i = 0; i < aPath.getItem().length; i++)
                {
                    aUtility.log(L2, "                 Path[" + i + "].ID:[" + aPath.getItem()[i].getID() + "]");
                    aUtility.log(L2, "           Path[" + i + "].Category:[" + aPath.getItem()[i].getCategory() + "]");
                    aUtility.log(L2, "             Path[" + i + "].Status:[" + aPath.getItem()[i].getStatus() + "]");
                    aUtility.log(L2, "        Path[" + i + "].OrderNumber:[" + aPath.getItem()[i].getOrderNumber() + "]");
                    aUtility.log(L2, "          Path[" + i + "].OrderType:[" + aPath.getItem()[i].getOrderType() + "]");
                    Attributes_GRP_t attrGrp = aPath.getItem()[i].getAttributes_GRP();
                    if (attrGrp != null)
                    {
                        aUtility.log(L2, "    Path[" + i + "].AttrGrp.DueDate:[" + attrGrp.getDueDate() + "]");
                        aUtility.log(L2, "        Path[" + i + "].AttrGrp.NTI:[" + attrGrp.getNTI() + "]");
                        aUtility.log(L2, "Path[" + i + "].AttrGrp.NTIModifier:[" + attrGrp.getNTIModifier() + "]");
                    }
                }
            }
        }
        catch (Exception any) // Don't throw exception at logging step
        {}
        finally
        {
            aUtility.log(L2, "Logging Granite response - finish");
        }
    }

    /**
     * @param aNtiGrp
     * @return
     */
    private NtiGrp[] getNtiGrp(NTI_GRP_t[] aNtiGrp)
    {
        String myMethodName = "QNIRequestHelper: getNtiGrp()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
      
        NtiGrp[] NtiGrpResp = null;

        if (aNtiGrp != null)
        {
            ArrayList aNtiGrpArr = new ArrayList();
            try
            {
                for (int i = 0; i < aNtiGrp.length; i++)
                {
                    aNtiGrpArr.add(new NtiGrp(aNtiGrp[i].getNTI(), aNtiGrp[i].getNTIModifier()));
                }
            }
            catch (Exception e)
            {
                aUtility.log(LogEventId.ERROR, "Unable to complete NtiGrp object: [" + e.getMessage() + "]");
            }

            if (aNtiGrpArr.size() > 0)
            {
                NtiGrpResp = (NtiGrp[]) aNtiGrpArr.toArray(new NtiGrp[aNtiGrpArr.size()]);
            }
        }
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return NtiGrpResp;
    }

    /**
     * @param aPath
     * @return
     */
    private QNIPath[] getPathGrp(_Seq_defaultPath_t aPath)
    {
        String myMethodName = "QNIRequestHelper: getPathArr()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
   
        QNIPath[] QNIPathResp = null;
        
        if (aPath != null && aPath.getItem() != null)
        {
            ArrayList aPathArr = new ArrayList();
            try
            {
                for (int i = 0; i < aPath.getItem().length; i++)
                {
                    QNIPath aQNIPathObj = new QNIPath();
                    aQNIPathObj.setAID(aPath.getItem()[i].getID());
                    aQNIPathObj.setACategory(aPath.getItem()[i].getCategory());
                    aQNIPathObj.setAStatus(aPath.getItem()[i].getStatus());
                    aQNIPathObj.setAOrderNumber(aPath.getItem()[i].getOrderNumber());
                    aQNIPathObj.setAOrderType(aPath.getItem()[i].getOrderType());

                    AttrGrp aAttrGrp = new AttrGrp();
                    aAttrGrp.setADueDate(aPath.getItem()[i].getAttributes_GRP().getDueDate());
                    aAttrGrp.setAPathNTI(aPath.getItem()[i].getAttributes_GRP().getNTI());
                    aAttrGrp.setAPathNTIModifier(aPath.getItem()[i].getAttributes_GRP().getNTIModifier());

                    aQNIPathObj.setAAttrGrp(aAttrGrp);
                    aPathArr.add(aQNIPathObj);
                }
            }
            catch (Exception e)
            {
                aUtility.log(LogEventId.ERROR, "Unable to complete Path object: [" + e.getMessage() + "]");
            }

            if (aPathArr.size() > 0)
            {
                QNIPathResp = (QNIPath[]) aPathArr.toArray(new QNIPath[aPathArr.size()]);
            }
        }
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return QNIPathResp;
    }
}