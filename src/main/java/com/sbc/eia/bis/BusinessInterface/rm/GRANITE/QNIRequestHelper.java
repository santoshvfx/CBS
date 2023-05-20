//$Id: QNIRequestHelper.java,v 1.18 2009/03/11 18:45:48 jc1421 Exp $
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

import java.util.Hashtable;
import java.util.StringTokenizer;

import com.att.it.granite.GetSITE_t;
import com.att.it.granite.QNIPortTypeProxy;
import com.att.it.granite.QueryNetworkInventoryRequest;
import com.att.it.granite.QueryNetworkInventoryResponse;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationRequestHelper;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationResponseHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;

/**
 * @author jc1421
 * 
 */
public class QNIRequestHelper extends GRANITE
{
    public QNIRequestHelper(Utility utility, Hashtable properties) throws InvalidData,
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
     * @param aContext
     * @param aFacilityAddress
     * @param aNpaNxx
     * @param aCLLI8
     * @return QNIResponse
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
     */
    public QNIResponse callQueryNetworkInventory(BisContext aContext,
                                                 Address aFacilityAddress,
                                                 String aNpaNxx,
                                                 String aCLLI8,
                                                 PublishValidateFacilityNotificationRequestHelper aRequestHelper,
                                                 PublishValidateFacilityNotificationResponseHelper aResponseHelper)
        throws InvalidData,
               AccessDenied,
               BusinessViolation,
               SystemFailure,
               NotImplemented,
               ObjectNotFound,
               DataNotFound
    {
        String myMethodName = "QNIRequestHelper: callQueryNetworkInventory()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        QueryNetworkInventoryRequest QNIRequest = buildRequest(aContext, aFacilityAddress, aNpaNxx, aCLLI8);

        QNIPortTypeProxy aQNIPortTypeProxy = new QNIPortTypeProxy();
        
        aQNIPortTypeProxy.setEndpoint(this.GRANITEEndPointAddress);
        QueryNetworkInventoryResponse aGraniteResp = null;

        try
        {
            aUtility.log(LogEventId.REMOTE_CALL,
                         this.GRANITEEndPointAddress,
                         GRANITE.HostName + "-" + this.GRANITEVersion,
                         GRANITE.HostName + "-" + this.GRANITEVersion,
                         GRANITE.QNI_Method);
            aGraniteResp = aQNIPortTypeProxy.queryNetworkInventory(QNIRequest);
        }
        catch (java.rmi.RemoteException re)
        {
            re.printStackTrace();
            ExceptionBuilderResult aExceptionBuilderResult = 
                ExceptionBuilder.parseException(aContext,
                                                GRANITERuleFile,
                                                null,
                                                getXNGCode(re.getMessage()),
                                                re.getMessage(),
                                                false,
                                                ExceptionBuilderRule.NO_DEFAULT,
                                                null,
                                                re,
                                                aUtility,
                                                GRANITE.HostName,
                                                null,
                                                null);
            aResponseHelper.setErrorInLFACSIndicator(true); // Granite uses same SCx message based on this indicator.
            aResponseHelper.handleException(aExceptionBuilderResult.getException(), aRequestHelper);
        }
        catch (com.att.cio.WSException wse)
        {
            wse.printStackTrace();
            aResponseHelper.setErrorInLFACSIndicator(true); // Granite uses same SCx message based on this indicator.
            aResponseHelper.handleException(ExceptionCode.ERR_RM_GRANITE_EXCEPTION,

                                            "WSException: " + "[ Endpoint address<" + this.GRANITEEndPointAddress
                                                    + "> ] " + wse.getMessage(),
                                            GRANITE.HostName,
                                            aRequestHelper);
        }
        finally
        {
            aUtility.log(LogEventId.REMOTE_RETURN,
                         this.GRANITEEndPointAddress,
                         GRANITE.HostName + "-" + this.GRANITEVersion,
                         GRANITE.HostName + "-" + this.GRANITEVersion,
                         GRANITE.QNI_Method);
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return new QNIResponseHelper(this.aUtility, this.aProperties).buildResponse(aContext,
                                                                                    aGraniteResp, 
                                                                                    aRequestHelper, 
                                                                                    aResponseHelper);
    }

    /**
     * @param aContext
     * @param aFacilityAddress
     * @param aNpaNxx
     * @param aCLLI8
     * @return QueryNetworkInventoryRequest
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
     */
    private QueryNetworkInventoryRequest buildRequest(BisContext aContext,
                                                      Address aFacilityAddress,
                                                      String aNpaNxx,
                                                      String aCLLI8) throws InvalidData,
                                                                        AccessDenied,
                                                                        BusinessViolation,
                                                                        SystemFailure,
                                                                        NotImplemented,
                                                                        ObjectNotFound,
                                                                        DataNotFound
    {
        String myMethodName = "QNIRequestHelper: buildRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        // Instantiate request object from the generate class.
        QueryNetworkInventoryRequest QNIRequestObj = new QueryNetworkInventoryRequest();
        
        QNIRequestObj.setSchema_version(this.GRANITEVersion);
        
        GetSITE_t aGetSITE_t = new GetSITE_t();

        aGetSITE_t.setSiteType(this.GRANITESiteType);
        aGetSITE_t.setQueryType(this.GRANITEQueryType);

        if (aFacilityAddress != null && aFacilityAddress.aFieldedAddress() != null)
        {
            FieldedAddress FA = aFacilityAddress.aFieldedAddress();
            aGetSITE_t.setName(formatGRANITE_Address(FA));
            aGetSITE_t.setStreet(aGetSITE_t.getName());
            aGetSITE_t.setStructureType( !OptHelper.isStringOptEmpty(FA.aStructureType) ? FA.aStructureType.theValue() : null);
            aGetSITE_t.setStructureValue(!OptHelper.isStringOptEmpty(FA.aStructureValue) ? FA.aStructureValue.theValue() : null);
            aGetSITE_t.setElevationType( !OptHelper.isStringOptEmpty(FA.aLevelType) ? FA.aLevelType.theValue() : null);
            aGetSITE_t.setElevationValue(!OptHelper.isStringOptEmpty(FA.aLevelValue) ? FA.aLevelValue.theValue() : null);
            aGetSITE_t.setUnitType(      !OptHelper.isStringOptEmpty(FA.aUnitType) ? FA.aUnitType.theValue() : null);
            aGetSITE_t.setUnitValue(     !OptHelper.isStringOptEmpty(FA.aUnitValue)  ? FA.aUnitValue.theValue() : null);
            aGetSITE_t.setCity(          !OptHelper.isStringOptEmpty(FA.aCity) ? FA.aCity.theValue() : null);
            aGetSITE_t.setState(         !OptHelper.isStringOptEmpty(FA.aState) ? FA.aState.theValue() : null);
            aGetSITE_t.setPostal_Code_1( !OptHelper.isStringOptEmpty(FA.aPostalCode) ? FA.aPostalCode.theValue() : null);
            aGetSITE_t.setOVALSLUID(     !OptHelper.isStringOptEmpty(FA.aAddressId) ? FA.aAddressId.theValue() : null);
        }
        aGetSITE_t.setNPATTA(aNpaNxx);
        aGetSITE_t.setServingWireCenterCLLI(aCLLI8 != null ? aCLLI8.toUpperCase() : null);

        QNIRequestObj.setSite(aGetSITE_t);
        
        logRequestData(QNIRequestObj);

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return QNIRequestObj;
    }
    
    private void logRequestData(QueryNetworkInventoryRequest aQNIRequestObj)
    {
        String L2 = LogEventId.INFO_LEVEL_2;
        aUtility.log(L2, "      siteType:[" + aQNIRequestObj.getSite().getSiteType() + "]");
        aUtility.log(L2, "     queryType:[" + aQNIRequestObj.getSite().getQueryType() + "]");
        aUtility.log(L2, "          name:[" + aQNIRequestObj.getSite().getName() + "]");
        aUtility.log(L2, "        street:[" + aQNIRequestObj.getSite().getStreet() + "]");
        aUtility.log(L2, " structureType:[" + aQNIRequestObj.getSite().getStructureType() + "]");
        aUtility.log(L2, "structureValue:[" + aQNIRequestObj.getSite().getStructureValue() + "]");
        aUtility.log(L2, " elevationType:[" + aQNIRequestObj.getSite().getElevationType() + "]");
        aUtility.log(L2, "elevationValue:[" + aQNIRequestObj.getSite().getElevationValue() + "]");
        aUtility.log(L2, "      unitType:[" + aQNIRequestObj.getSite().getUnitType() + "]");
        aUtility.log(L2, "     unitValue:[" + aQNIRequestObj.getSite().getUnitValue() + "]");
        aUtility.log(L2, "          city:[" + aQNIRequestObj.getSite().getCity() + "]");
        aUtility.log(L2, "         state:[" + aQNIRequestObj.getSite().getState() + "]");
        aUtility.log(L2, " postal_Code_1:[" + aQNIRequestObj.getSite().getPostal_Code_1() + "]");
        aUtility.log(L2, "     OVALSLUID:[" + aQNIRequestObj.getSite().getOVALSLUID() + "]");
        aUtility.log(L2, "        nPATTA:[" + aQNIRequestObj.getSite().getNPATTA() + "]");
        aUtility.log(L2, " servingWCCLLI:[" + aQNIRequestObj.getSite().getServingWireCenterCLLI() + "]");
    }

    /**
     * @param aFA
     * @return String
     */
    private String formatGRANITE_Address(FieldedAddress aFA)
    {
        // Format per rqmnts:
        // houseNumberPrefix " " houseNumber " " houseNumberSuffix " "
        // streetDirection " " streetName " " streetThoroughfare " "
        // streetSuffix
        String GraniteAddress = (AddrHelper(aFA.aHouseNumberPrefix)  + 
                                 AddrHelper(aFA.aHouseNumber)        + 
                                 AddrHelper(aFA.aHouseNumberSuffix)  + 
                                 AddrHelper(aFA.aStreetDirection)    + 
                                 AddrHelper(aFA.aStreetName)         + 
                                 AddrHelper(aFA.aStreetThoroughfare) + 
                                 AddrHelper(aFA.aStreetNameSuffix)
                                ).trim().toUpperCase();
        return GraniteAddress;
    }

    /**
     * @param aSO
     * @return String
     */
    private String AddrHelper(StringOpt aSO)
    {
        String returnValue = "";
        if (!OptHelper.isStringOptEmpty(aSO))
        {
            returnValue = aSO.theValue() + " ";
        }
        return returnValue;
    }
    
    /**
     * @param aMessage
     * @return String
     */
    private String getXNGCode(String aMessage)
    {
        StringTokenizer aStringTokens = new StringTokenizer(aMessage, ":");
        String aCode = null; //  if code not found, let exception use a null code

        while (aStringTokens.hasMoreTokens())
        {
            String aToken = aStringTokens.nextToken().trim();
            if (aToken.endsWith("code"))
            {
                try
                {
                    // Line that ends with "code:" means the next line starts with the code value
                    aCode = aStringTokens.nextToken().trim().substring(0, aToken.indexOf(" ") + 1);
                }
                catch (Exception any) // if for any reason the above has exceptions, null is okay.
                {}
                break;
            }
        }
        return aCode;
    }
}