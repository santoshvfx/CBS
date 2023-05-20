//$Id: SOACServiceOrderRequestGenerator.java,v 1.16 2007/12/20 17:20:12 op1664 Exp $
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
//# 07/2006		Sriram Chevuturu      Creation
//# 05/10/2007  Jon Costa			  CR13804: Change for OTN==TN and actionCode == M
//# 06/05/2007  Jon Costa			  PR 20042595: Allow for null TN Pair(s) for cancel or completion.
//# 09/25/2007  Ott Phannavong		  LS6 - added application indicator
//# 11/09/2007  Doris Sunga			  DR 77978 - getFCIFRequestStringForVOIP() added empty string parameter as 
//#                                   application indicator in calling SvcOrdRequestDataObject() 
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm_ls_types.SubActionTypeValues;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrder;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPair;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberPortingStatus;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberPortingStatusOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
* @author sc8468
*
* To change this generated comment edit the template variable "typecomment":
* Window>Preferences>Java>Templates.
* To enable and disable the creation of type comments go to
* Window>Preferences>Java>Code Generation.
*/
public class SOACServiceOrderRequestGenerator extends
    ServiceOrderRequestGenerator
{

 private VoipReqSnEDataObject[] voipSnEData = null;

 public SOACServiceOrderRequestGenerator(Hashtable aProperties, Logger aLogger)
 {
    super(aProperties, aLogger);
 }

 /**
  * Method getFCIFRequestStringforFTTX.
  * @param aSOACServiceOrderNumber
  * @param aSOACServiceOrderCorrectionSuffix
  * @param aNetworkType
  * @param aOrderActionId
  * @param aOrderNumber
  * @param aOrderActionType
  * @param aCompletionIndicator
  * @param aSubActionType
  * @param aCircuitId
  * @param aServiceLocation
  * @param aOriginalDueDate
  * @param aSubsequentDueDate
  * @param aApplicationDate
  * @param aTDMTelphoneNumber
  * @param aRelatedServiceOrderNumber
  * @param aLineShareDisconnectFlag
  * @param aClassOfService
  * @param aResendFlag
  * @param aEntity
  * @param aEntityPlatform
  * @param aProperties
  * @param aOriginatingHost
  * @param aRegion
  * @param SpecialCondData
  * @param aApplicationIndicator  -application indicator (eg O or B)
  * @return String
  * @throws ParserSvcException
  */
 public String getFCIFRequestStringforFTTX(String aSOACServiceOrderNumber,
       String aSOACServiceOrderCorrectionSuffix, String aNetworkType,
       String aOrderActionId, String aOrderNumber, String aOrderActionType,
       BooleanOpt aCompletionIndicator, StringOpt aSubActionType,
       String aCircuitId, Location aServiceLocation,
       EiaDate aOriginalDueDate, EiaDateOpt aSubsequentDueDate,
       EiaDate aApplicationDate, StringOpt aTDMTelphoneNumber,
       StringOpt aRelatedServiceOrderNumber,
       BooleanOpt aLineShareDisconnectFlag, String aClassOfService,
       BooleanOpt aResendFlag, String aEntity, String aEntityPlatform,
       ObjectPropertySeqOpt aProperties, String aOriginatingHost,
       String aRegion, String SpecialCondData, String aApplicationIndicator)
       throws ParserSvcException
 {

    String myMethodName = "SOACServiceOrderRequestGenerator::getFCIFRequestStringforFTTX()";
    myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

    SvcOrdRequestDataObject aReqDataObject = new SvcOrdRequestDataObject(
          aSOACServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
          aNetworkType, aOrderActionId, aOrderNumber, aOrderActionType,
          aCompletionIndicator, aSubActionType, aCircuitId, aServiceLocation,
          aOriginalDueDate, aSubsequentDueDate, aApplicationDate,
          aTDMTelphoneNumber, aRelatedServiceOrderNumber,
          aLineShareDisconnectFlag, aClassOfService, aResendFlag, aEntity,
          aEntityPlatform, aProperties, aOriginatingHost, aRegion, null,
          null, SpecialCondData, aApplicationIndicator);
    /* 
     * The argument for VOIP data object Type and NpaNXX 
     * is null for FTTN / FTTP because it is gotten 
     * from aServiceLocation. 
     */

    myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

    return super.getFCIFRequestString(aReqDataObject);

 }

 /**
  * Method getFCIFRequestStringForVOIP.
  * @param aSOACServiceOrderNumber
  * @param aSOACServiceOrderCorrectionSuffix
  * @param aNetworkType
  * @param aOrderActionId
  * @param aOrderNumber
  * @param aOrderActionType
  * @param aCompletionIndicator
  * @param aSubActionType
  * @param aServiceLocation
  * @param aOriginalDueDate
  * @param aSubsequentDueDate
  * @param aApplicationDate
  * @param aEntity
  * @param aEntityPlatform
  * @param aProperties
  * @param aOriginatingHost
  * @param aRegion
  * @param aTelephoneNumberOrderPairs
  * @param aNpaNxx
  * @param SpecialCondData
  * @return String
  * @throws ParserSvcException
  */
 public String getFCIFRequestStringForVOIP(String aSOACServiceOrderNumber,
       String aSOACServiceOrderCorrectionSuffix, String aNetworkType,
       String aOrderActionId, String aOrderNumber, String aOrderActionType,
       BooleanOpt aCompletionIndicator, StringOpt aSubActionType,
       Location aServiceLocation, EiaDate aOriginalDueDate,
       EiaDateOpt aSubsequentDueDate, EiaDate aApplicationDate,
       String aEntity, String aEntityPlatform,
       ObjectPropertySeqOpt aProperties, String aOriginatingHost,
       String aRegion,
       TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs,
       String aNpaNxx, String SpecialCondData) throws ParserSvcException,
       DataNotFound
 {

    String myMethodName = "SOACServiceOrderRequestGenerator::getFCIFRequestStringforVOIP()";
    myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

    if((voipSnEData = extractVoipSnEData(aTelephoneNumberOrderPairs, aNpaNxx)) == null)
    {
       if((OptHelper.isBooleanOptEmpty(aCompletionIndicator) == true || aCompletionIndicator
             .theValue() == false)
             && (OptHelper.isStringOptEmpty(aSubActionType) == true || aSubActionType
                   .theValue().equalsIgnoreCase(
                         SubActionTypeValues.SUB_ACTION_AMEND)))
       {
          throw new DataNotFound();
       }
    }

    SvcOrdRequestDataObject aReqDataObject = new SvcOrdRequestDataObject(
          aSOACServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
          aNetworkType, aOrderActionId, aOrderNumber, aOrderActionType,
          aCompletionIndicator, aSubActionType, //all the commented variables are for FTTX type.
          null, //aCircuitId
          aServiceLocation, aOriginalDueDate, aSubsequentDueDate,
          aApplicationDate, null, //aTDMTelphoneNumber
          null, //aRelatedServiceOrderNumber
          null, //aLineShareDisconnectFlag,
          null, //aClassOfService
          null, //aResendFlag,
          aEntity, aEntityPlatform, aProperties, aOriginatingHost, aRegion,
          voipSnEData, aNpaNxx, SpecialCondData, 
          " ");

    myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

    return super.getFCIFRequestString(aReqDataObject);

 }

 public VoipReqSnEDataObject[] extractVoipSnEData(
       TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairSeq,
       String aNpaNxx)
 {
    String myMethodName = "SOACServiceOrderRequestGenerator::extractVoipSnEData()";
    myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

    boolean addThisTNOP = true;
    TelephoneNumberOrderPair[] aTelephoneNumberOrderPairs = null;
    VoipReqSnEDataObject[] aVoipReqSnEDataObjects = new VoipReqSnEDataObject[8];
    int numTels = 0;

    //first find number of telephone order pairs:	
    if(OptHelper
          .isTelephoneNumberOrderPairSeqOptEmpty(aTelephoneNumberOrderPairSeq) == false)
       aTelephoneNumberOrderPairs = aTelephoneNumberOrderPairSeq.theValue();

    if(aTelephoneNumberOrderPairs != null)
    {
       if(aTelephoneNumberOrderPairs.length > 0)
       {
          for(int i = 0; i < aTelephoneNumberOrderPairs.length; i++)
          {
             TelephoneNumberOrderPair dummyPair = aTelephoneNumberOrderPairs[i];

             TelephoneNumberOrder aTnOrder = dummyPair.aTelephoneNumberOrder;
             TelephoneNumberOrder aOldTnOrder = null;

             try
             {
                aOldTnOrder = dummyPair.aOldTelephoneNumberOrder.theValue();
             }
             catch(Exception e)
             {
             }

             addThisTNOP = true;
             if(aTelephoneNumberOrderPairs[i].aOldTelephoneNumberOrder != null)
             {
                // we should have a pair of TN(s)
                if(aTelephoneNumberOrderPairs[i].aTelephoneNumberOrder.aActionInd
                      .equalsIgnoreCase("M"))
                {
                   // The action is Modify
                   String oldTN = null;
                   String newTN = null;

                   try
                   {
                      oldTN = aTelephoneNumberOrderPairs[i].aOldTelephoneNumberOrder
                            .theValue().aTelephoneNumber.theValue();
                   }
                   catch(Exception e)
                   {
                      // Ignore nullPointerException at this point, a null value is valid
                   }

                   try
                   {
                      newTN = aTelephoneNumberOrderPairs[i].aTelephoneNumberOrder.aTelephoneNumber
                            .theValue();
                   }
                   catch(Exception e)
                   {
                      // Ignore nullPointerException at this point, a null value is valid
                   }

                   if(newTN != null && oldTN != null && newTN.equals(oldTN))
                   {
                      myLogger.log(LogEventId.INFO_LEVEL_1, "Equal OldTN ["
                            + oldTN + "] and NewTN [" + newTN
                            + "] are ignored.");
                      addThisTNOP = false;
                   }
                }

                try
                {
                   // If the activityInd is empty/null, we ignore it this TN
                   if(OptHelper
                         .isStringOptEmpty(aTelephoneNumberOrderPairs[i].aOldTelephoneNumberOrder
                               .theValue().aActivityIndicator))
                      aOldTnOrder = null;
                }
                catch(Exception e)
                {
                   aOldTnOrder = null;
                }
             }

             // If the activityInd is empty/null, we ignore it this TN pair
             if(OptHelper
                   .isStringOptEmpty(aTelephoneNumberOrderPairs[i].aTelephoneNumberOrder.aActivityIndicator))
                addThisTNOP = false;

             if(addThisTNOP)
             {
                VoipReqSnEDataObject aVoipObj = null;

                // Fetch The Telephone number and actionIndicator from TelephoneNumberOrder
                if(aTnOrder != null)
                {
                   aVoipObj = extractaTnOrderData(aTnOrder, aNpaNxx);
                   if(aVoipObj != null)
                   {
                      aVoipReqSnEDataObjects[numTels++] = aVoipObj;
                      aVoipObj = null;
                   }
                }

                if(aOldTnOrder != null)
                {
                   aVoipObj = extractaTnOrderData(aOldTnOrder, aNpaNxx);
                   if(aVoipObj != null)
                   {
                      aVoipReqSnEDataObjects[numTels++] = aVoipObj;
                      aVoipObj = null;
                   }
                }
             }
             dummyPair = null;
          } //end for 
       } //end inner if
    } //end outer if

    VoipReqSnEDataObject[] aNewVoipReqSnEDataObjects = null;

    if(numTels > 0)
    {
       aNewVoipReqSnEDataObjects = new VoipReqSnEDataObject[numTels];

       for(int x = 0; x < numTels; x++)
          aNewVoipReqSnEDataObjects[x] = aVoipReqSnEDataObjects[x];
    }

    myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
    return aNewVoipReqSnEDataObjects;
 }

 public VoipReqSnEDataObject extractaTnOrderData(
       TelephoneNumberOrder aTnOrder, String aNpaNxx)
 {
    String aTn = null, oldPrvd = null, newPrvd = null, aLrn = null, actionIndicator = null, aRetPortInd = null, aNonRetPortInd = null;

    VoipReqSnEDataObject aVoipReqSnEDataObject = null;
    ;

    // Fetch The Telephone number and actionIndicator from TelephoneNumberOrder
    if(aTnOrder != null)
    {
       if(!OptHelper.isStringOptEmpty(aTnOrder.aTelephoneNumber))
       {
          aTn = new String(aTnOrder.aTelephoneNumber.theValue());
          try
          {
             // SOAC's actionIndicator is the incoming aActivityIndicator
             actionIndicator = aTnOrder.aActivityIndicator.theValue();
          }
          catch(Exception e)
          {
          }
       }
       if(aTn != null && aTn.trim().length() > 0)
       {
          aVoipReqSnEDataObject = new VoipReqSnEDataObject();
          aVoipReqSnEDataObject.setTelephoneNumber(aTn.trim());

          if(actionIndicator != null && actionIndicator.trim().length() > 0)
             aVoipReqSnEDataObject.setActionIndicator(actionIndicator.trim());
       }
    }

    TelephoneNumberPortingStatusOpt aTNPortStatusOpt = aTnOrder.aTelephoneNumberPortingStatus;
    TelephoneNumberPortingStatus aTNPortStatus = null;

    try
    {
       aTNPortStatus = aTNPortStatusOpt.theValue();
    }
    catch(Exception e)
    {
       myLogger.log(LogEventId.INFO_LEVEL_1,
             "aTNPortStatusOpt has not value.");
    }

    if(aTNPortStatus != null)
    {

       if(!OptHelper.isStringOptEmpty(aTNPortStatus.aOldProvider))
          oldPrvd = aTNPortStatus.aOldProvider.theValue();

       if(!OptHelper.isStringOptEmpty(aTNPortStatus.aNewProvider))
          newPrvd = aTNPortStatus.aNewProvider.theValue();

       if(!OptHelper.isStringOptEmpty(aTNPortStatus.aLocalRoutingNumber))
          aLrn = aTNPortStatus.aLocalRoutingNumber.theValue();

       if(!OptHelper
             .isStringOptEmpty(aTNPortStatus.aRetainedPortingIndicator))
          aRetPortInd = aTNPortStatus.aRetainedPortingIndicator.theValue();

       if(!OptHelper
             .isStringOptEmpty(aTNPortStatus.aNonRetainedPortingIndicator))
          aNonRetPortInd = aTNPortStatus.aNonRetainedPortingIndicator
                .theValue();

       if(oldPrvd != null && oldPrvd.trim().length() > 0)
          aVoipReqSnEDataObject.setOldProvider(oldPrvd.trim());

       if(newPrvd != null && newPrvd.trim().length() > 0)
          aVoipReqSnEDataObject.setNewProvider(newPrvd.trim());

       if(aLrn != null && aLrn.trim().length() > 0)
          aVoipReqSnEDataObject.setLocalRountingNumber(aLrn.trim());

       if(aRetPortInd != null && aRetPortInd.trim().length() > 0)
       {
          aVoipReqSnEDataObject.setRetainedPortingIndicator(aRetPortInd
                .trim());
          //for LSO FID Data
          //if(aTn != null && aTn.trim().length() > 0)
          aVoipReqSnEDataObject.setLocalServingOfficeData(aNpaNxx.substring(
                0, 3)
                + " " + aNpaNxx.substring(3));

       }

       if(aNonRetPortInd != null && aNonRetPortInd.trim().length() > 0)
          aVoipReqSnEDataObject.setNonRetainedPortingIndicator(aNonRetPortInd
                .trim());

    }

    // make all the references null. just to make sure that any strings are good for gc.
    aTn = null;
    oldPrvd = null;
    newPrvd = null;
    aLrn = null;
    actionIndicator = null;

    return aVoipReqSnEDataObject;
 }
}