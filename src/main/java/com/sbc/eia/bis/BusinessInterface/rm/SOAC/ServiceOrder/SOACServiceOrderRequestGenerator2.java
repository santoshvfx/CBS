// $Id: SOACServiceOrderRequestGenerator2.java,v 1.1 2008/01/29 19:14:45 op1664
//Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//# RESTRICTED - PROPRIETARY INFORMATION
//# The information herein is for use only by authorized employees
//# of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//# Inc., and is not for general distribution within or outside the
//# respective companies.
//# Copying or reproduction without prior written approval is prohibited.
//#
//# © 2008-2010 AT&T Intellectual Property All rights reserved.
//#
//# History :
//# Date | Author | Notes
//# --------------------------------------------------------------------------
//# 01/29/2008 Ott Phannavong Creation
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParserSvc;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory.ParserSvcFactory;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderRequestGenerator;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrdRequestDataObject;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrderConstants;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm_ls_types.OrderActionTypeValues;
import com.sbc.eia.idl.rm_ls_types.SubActionTypeValues;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

public class SOACServiceOrderRequestGenerator2 extends
      SOACServiceOrderRequestGenerator
{
   protected StringOpt oldNetworkType = null;

   protected StringOpt secondaryCircuitID = null;

   protected StringOpt secondaryRelatedCircuitID = null;

   protected CompositeObjectKey billingAccountNumber = null;

   /**
    * @param aProperties
    * @param aLogger
    */
   public SOACServiceOrderRequestGenerator2(Hashtable aProperties,
         Logger aLogger, StringOpt aOldNetworkType,
         StringOpt aSecondaryCircuitID, StringOpt aSecondaryRelatedCircuitID,
         CompositeObjectKey aBillingAccountNumber)
   {
      super(aProperties, aLogger);

      String methodNameName = "SOACServiceOrderRequestGenerator2.SOACServiceOrderRequestGenerator2(...)";
      aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodNameName);

      oldNetworkType = aOldNetworkType;
      secondaryCircuitID = aSecondaryCircuitID;
      secondaryRelatedCircuitID = aSecondaryRelatedCircuitID;
      billingAccountNumber = aBillingAccountNumber;

      aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodNameName);
   }

   protected void addNetworkType(SoacServiceOrderVO aSSVO,
         SvcOrdRequestDataObject aReqDataObject)
   {
      String methodNameName = "SOACServiceOrderRequestGenerator2.addNetworkType(...)";
      myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodNameName);

      try
      {
         super.addNetworkType(aSSVO, aReqDataObject);
         if(aReqDataObject.getNetworkType().equalsIgnoreCase(
               SvcOrderConstants.FTTNBP_NETWORK))
         {
            aSSVO.put(SoacServiceOrderConstants.NETWORK_TYPE,
                  SvcOrderConstants.BOND_NETWORK_INDICATOR);
         }

      }
      catch(Exception e)
      {
         myLogger.log(LogEventId.DEBUG_LEVEL_1,
               ">>aNetworkType is Not Available.<<");
      }
      myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodNameName);
   }

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
         String aRegion, String aSpecialCondData, String aApplicationIndicator)
         throws ParserSvcException
   {
      String methodNameName = "SOACServiceOrderRequestGenerator2.getFCIFRequestStringforFTTX(...)";
      myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodNameName);

      SvcOrdRequestDataObject2 requestDataObject2 = new SvcOrdRequestDataObject2(
            aSOACServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
            aNetworkType, aOrderActionId, aOrderNumber, aOrderActionType,
            aCompletionIndicator, aSubActionType, aCircuitId, aServiceLocation,
            aOriginalDueDate, aSubsequentDueDate, aApplicationDate,
            aTDMTelphoneNumber, aRelatedServiceOrderNumber,
            aLineShareDisconnectFlag, aClassOfService, aResendFlag, aEntity,
            aEntityPlatform, aProperties, aOriginatingHost, aRegion, null,
            null, aSpecialCondData, aApplicationIndicator);
      requestDataObject2.setBillingAccountNumber(billingAccountNumber);
      requestDataObject2.setOldNetworkType(oldNetworkType);
      requestDataObject2
            .setSecondaryRelatedCircuitID(secondaryRelatedCircuitID);
      requestDataObject2.setSecondaryCircuitID(secondaryCircuitID);

      myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodNameName);
      return super.getFCIFRequestString(requestDataObject2);
   }

   protected void prepareSandESectionForFTTX(SoacServiceOrderVO aSSVO,
         SvcOrdRequestDataObject aReqDataObject)
   {
      String methodNameName = "SOACServiceOrderRequestGenerator2.prepareSandESectionForFTTX(...)";
      myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodNameName);

      super.prepareSandESectionForFTTX(aSSVO, aReqDataObject);
      try
      {
         aSSVO.put(SoacServiceOrderConstants.SECONDARY_CIRCUIT_ID,
               ((SvcOrdRequestDataObject2) aReqDataObject)
                     .getSecondaryCircuitID().theValue().replace('/', '.'));
      }
      catch(Exception e)
      {
         myLogger
               .log(
                     LogEventId.INFO_LEVEL_2,
                     ">>Secondary circuit ID is not available is required for order related to bonded pair network.<<");
      }
      try
      {

         aSSVO.put(SoacServiceOrderConstants.OLD_NETWORK_TYPE,
               ((SvcOrdRequestDataObject2) aReqDataObject).getOldNetworkType()
                     .theValue());
      }
      catch(Exception e)
      {
         myLogger
               .log(LogEventId.INFO_LEVEL_2,
                     ">>Old network type is not available is required for change/amend order.<<");
      }
      try
      {

         String rRID = ((SvcOrdRequestDataObject2) aReqDataObject)
               .getSecondaryRelatedCircuitID().theValue();
         String secondaryRTID = aSvcOrdReqHelper.formatTelephoneNumber(rRID);
         aSSVO.put(SoacServiceOrderConstants.SECONDARY_RELATED_TDMTN,
               secondaryRTID);
      }
      catch(Exception e)
      {
         myLogger
               .log(
                     LogEventId.INFO_LEVEL_2,
                     ">>Secondary RTID is not available is optional for order related to bonded pair network.<<");
      }
      myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodNameName);
   }

   protected void prepareRemarksAndMiscSections(SoacServiceOrderVO aSSVO,
         SvcOrdRequestDataObject aReqDataObject, String aActionIndicator)
   {
      String methodNameName = "SOACServiceOrderRequestGenerator2.prepareSandESectionForFTTX(...)";
      myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodNameName);

      super.prepareRemarksAndMiscSections(aSSVO, aReqDataObject,
            aActionIndicator);
      try
      {
         aSSVO.put(SoacServiceOrderConstants.BILLING_ACCOUNT_NUMBER,
               ((SvcOrdRequestDataObject2) aReqDataObject)
                     .getBillingAccountNumberString());
      }
      catch(Exception e)
      {
         myLogger.log(LogEventId.INFO_LEVEL_2,
               ">>Billing account number is not available.<<");
      }
      myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodNameName);
   }

   protected ParserSvc getParserSvc() throws ParserSvcException
   {
      return ParserSvcFactory.getFactory().getParserSvc(myLogger,
            "ParserSvc2.properties");
   }

   /**
    * @param reqDataObject
    */
   protected void setChangeActionIndicator(SvcOrdRequestDataObject reqDataObject)
   {
      dueDateActionIndicator = SvcOrderConstants.CHANGE_ACTION_INDICATOR;
   }
   /**
    * @param subActionType
    * @param completeIndicator
    * @return
    */
   protected boolean isDueDateChange(String subActionType, String orderActionType, boolean  completeIndicator)
   {      
      return (subActionType != null
      && subActionType.equalsIgnoreCase(SubActionTypeValues.SUB_ACTION_AMEND)
      && !orderActionType.equalsIgnoreCase(SvcOrderConstants.CHANGE_ORDER_ACTION_TYPE)
      && (completeIndicator == false));
   }
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.ServiceOrderRequestGenerator#addOrderActionTypeAndSubActionType(com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO, com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrdRequestDataObject)
	 */
	protected void addOrderActionTypeAndSubActionType(SoacServiceOrderVO aSSVO,
			SvcOrdRequestDataObject aReqDataObject) {
		String methodName = "SOACServiceOrderRequestGenerator2: addOrderActionTypeAndSubActionType()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

		//super.addOrderActionTypeAndSubActionType(aSSVO, aReqDataObject);
		aSSVO.put(SoacServiceOrderConstants.ORDER_ACTION_TYPE, aReqDataObject
				.getOrderActionType());
		try {

			aSSVO.put(SoacServiceOrderConstants.ORDER_SUBACTION_TYPE,
					aReqDataObject.getSubActionType().theValue());

		} catch (Exception e) {
			myLogger.log(LogEventId.DEBUG_LEVEL_2,
					">>Subaction type is not available is ok because it is a StringOpt.<<");
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);

	}
	   /**
	    * @param subActionType
	    * @param orderActionType
	    * @param completeIndicator
	    * @return
	    */
	   protected boolean prepareControlAndEchoForNewconnect(String subActionType, String orderActionType, boolean completeIndicator)
	   {
	      return (orderActionType != null
                && (orderActionType.equalsIgnoreCase(
                      OrderActionTypeValues.ORDER_ACTION_PROVIDE))
              && (subActionType == null) && (completeIndicator == false));
	   }
}