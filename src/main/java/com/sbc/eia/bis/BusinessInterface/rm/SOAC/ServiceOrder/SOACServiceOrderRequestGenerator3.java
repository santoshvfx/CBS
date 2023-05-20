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
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm_ls_types.NetworkType3Values;
import com.sbc.eia.idl.rm_ls_types.OrderActionTypeValues;
import com.sbc.eia.idl.rm_ls_types.SubActionTypeValues;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

public class SOACServiceOrderRequestGenerator3 extends
      SOACServiceOrderRequestGenerator
{
	//LS9
	protected StringOpt oldNetworkType = null;
	protected StringOpt secondaryCircuitID = null;
	protected StringOpt secondaryRelatedCircuitID = null;
	protected CompositeObjectKey billingAccountNumber = null;

	//ra0331: Changes to LS10
	protected BooleanOpt interceptRedirectIndicator = null;
	protected StringOpt dryloopRelatedCircuitId = null;
	protected StringOpt dSLDisconnectTelephoneNumber = null;
	protected StringOpt exceptionRoutingIndicator = null;
	protected StringOpt relatedCircuitId = null;
	
	protected static final String IPDSLAM_RES_CLS_OF_SVC = "XR7DS";
	protected static final String IPDSLAM_BUS_CLS_OF_SVC = "XB7DS";
	protected static final String SOAC_RES_CLS_OF_SVC = "XR7FA";
	protected static final String SOAC_BUS_CLS_OF_SVC = "XB7FA";
	private String newClassOfService = null;
	
   /**
    * @param aProperties
    * @param aLogger
    */
   public SOACServiceOrderRequestGenerator3(Hashtable aProperties,
         Logger aLogger, StringOpt aOldNetworkType, StringOpt aRelatedCircuitId,
         StringOpt aSecondaryCircuitID, StringOpt aSecondaryRelatedCircuitID,
         CompositeObjectKey aBillingAccountNumber, BooleanOpt aInterceptRedirectIndicator,
         StringOpt aDryloopRelatedCircuitId, StringOpt aDSLDisconnectTelephoneNumber,
         StringOpt aExceptionRoutingIndicator)
   {
      super(aProperties, aLogger);

      String methodNameName = "SOACServiceOrderRequestGenerator3.SOACServiceOrderRequestGenerator3(...)";
      aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodNameName);

      oldNetworkType = aOldNetworkType;
      secondaryCircuitID = aSecondaryCircuitID;
      secondaryRelatedCircuitID = aSecondaryRelatedCircuitID;
      billingAccountNumber = aBillingAccountNumber;

      //ra0331: changes to LS10
      interceptRedirectIndicator = aInterceptRedirectIndicator;
      dryloopRelatedCircuitId = aDryloopRelatedCircuitId;
      dSLDisconnectTelephoneNumber = aDSLDisconnectTelephoneNumber;
      exceptionRoutingIndicator = aExceptionRoutingIndicator;
      relatedCircuitId = aRelatedCircuitId;
      
      aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodNameName);
   }

   protected void addNetworkType(SoacServiceOrderVO aSSVO,
         SvcOrdRequestDataObject aReqDataObject)
   {
      String methodNameName = "SOACServiceOrderRequestGenerator3.addNetworkType(...)";
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

         //ra0331: added changes to LS10
         if(aReqDataObject.getNetworkType().equalsIgnoreCase(
                 SvcOrderConstants.IPCO_NETWORK))
           {
              aSSVO.put(SoacServiceOrderConstants.NETWORK_TYPE,
                    SvcOrderConstants.IPCO);
           }
         
         //ra0331: added changes to LS10
         if(aReqDataObject.getNetworkType().equalsIgnoreCase(
                 SvcOrderConstants.IPRT_NETWORK))
           {
              aSSVO.put(SoacServiceOrderConstants.NETWORK_TYPE,
                    SvcOrderConstants.IPRT);
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
   		String methodNameName = "SOACServiceOrderRequestGenerator3.getFCIFRequestStringforFTTX(...)";
   		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodNameName);	   
   		newClassOfService = getNewClassOfService(aNetworkType, aClassOfService);
   		
      SvcOrdRequestDataObject3 requestDataObject3 = new SvcOrdRequestDataObject3(
    		  aSOACServiceOrderNumber, aSOACServiceOrderCorrectionSuffix, 
    		  aNetworkType, aOrderActionId, aOrderNumber, aOrderActionType, 
    		  aCompletionIndicator, aSubActionType, aCircuitId,
              aServiceLocation, aOriginalDueDate, aSubsequentDueDate, 
              aApplicationDate, aTDMTelphoneNumber, aRelatedServiceOrderNumber,
              aLineShareDisconnectFlag, newClassOfService, aResendFlag, 
              aEntity, aEntityPlatform, aProperties, aOriginatingHost,
              aRegion, null, null, aSpecialCondData, aApplicationIndicator);
      
      		requestDataObject3.setBillingAccountNumber(billingAccountNumber);
      		requestDataObject3.setOldNetworkType(oldNetworkType);
      		requestDataObject3.setSecondaryRelatedCircuitID(secondaryRelatedCircuitID);
      		requestDataObject3.setSecondaryCircuitID(secondaryCircuitID);

      		// ra0331: Added changes to LS10
      		//dr 123435 fix 3/9/09 dl8121
         	requestDataObject3.setInterceptRedirectIndicator(interceptRedirectIndicator);
            requestDataObject3.setDryloopRelatedCircuitId(dryloopRelatedCircuitId);
            requestDataObject3.setDSLDisconnectTelephoneNumber(dSLDisconnectTelephoneNumber);
            requestDataObject3.setExceptionRoutingIndicator(exceptionRoutingIndicator);
            requestDataObject3.setRelatedCircuitId(aTDMTelphoneNumber);
            requestDataObject3.setClassOfService(newClassOfService);
            
      myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodNameName);
      return super.getFCIFRequestString(requestDataObject3);
   }

   protected void prepareSandESectionForFTTX(SoacServiceOrderVO aSSVO,
         SvcOrdRequestDataObject aReqDataObject)
   {
      String methodNameName = "SOACServiceOrderRequestGenerator3.prepareSandESectionForFTTX(...)";
      myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodNameName);

      super.prepareSandESectionForFTTX(aSSVO, aReqDataObject);
      try
      {
         aSSVO.put(SoacServiceOrderConstants.SECONDARY_CIRCUIT_ID,
               ((SvcOrdRequestDataObject3) aReqDataObject)
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
               ((SvcOrdRequestDataObject3) aReqDataObject).getOldNetworkType()
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
    	  // dl8121: add changes in LS10 [AD: Validation p.5] //DR122583
    	  if (((SvcOrdRequestDataObject3)aReqDataObject).getInterceptRedirectIndicator().theValue()) {
    	  aSSVO.put(SoacServiceOrderConstants.DRYLOOP_RELATED_CIRCUIT_FID,
    			  ((SvcOrdRequestDataObject3) aReqDataObject).dryloopRelatedCircuitId.theValue().replace('/', '.'));
    	  aSSVO.put("INTERCEPT_REDIRECT_INDICATOR", "true");
    	  }
    	  else {
    		  String relCircuitId = ((SvcOrdRequestDataObject3)aReqDataObject).getRelatedCircuitId().theValue();
    		  String rTID = aSvcOrdReqHelper.formatTelephoneNumber(relCircuitId);
    		  aSSVO.put(SoacServiceOrderConstants.RELATED_TDMTN, rTID);
    		  aSSVO.put("INTERCEPT_REDIRECT_INDICATOR", "false");
    		  
    		  String rRID = ((SvcOrdRequestDataObject3) aReqDataObject)
	  			.getSecondaryRelatedCircuitID().theValue();
		      String secondaryRTID = aSvcOrdReqHelper.formatTelephoneNumber(rRID);
		      aSSVO.put(SoacServiceOrderConstants.RELATED_CIRCUIT_FID,
				  secondaryRTID);
    	  }
      }
      catch(Exception e)
      {
    	  if (((SvcOrdRequestDataObject3)aReqDataObject).getInterceptRedirectIndicator().theValue()) {
          myLogger
               .log(
                     LogEventId.INFO_LEVEL_2,
                     ">>Dryloop related circuit  id is not available is optional for order related to IPDSLAM network.<<");
    	  } else
    		  myLogger.log(
                  LogEventId.INFO_LEVEL_2,
                  ">>Secondary RTID is not available is optional for order related to bonded pair network.<<");
      }
//      try
//      {			//DR122583
//    		  String relCircuitId = ((SvcOrdRequestDataObject3)aReqDataObject).getRelatedCircuitId().theValue();
//    		  String rTID = aSvcOrdReqHelper.formatTelephoneNumber(relCircuitId);
//    		  aSSVO.put(SoacServiceOrderConstants.RELATED_TDMTN, rTID);
//
//    		  String rRID = ((SvcOrdRequestDataObject3) aReqDataObject)
//		  			.getSecondaryRelatedCircuitID().theValue();
//    		  String secondaryRTID = aSvcOrdReqHelper.formatTelephoneNumber(rRID);
//    		  aSSVO.put(SoacServiceOrderConstants.RELATED_CIRCUIT_FID,
//    				  secondaryRTID);
//      }
//      catch(Exception e)
//      {
//         myLogger
//               .log(
//                     LogEventId.INFO_LEVEL_2,
//                     ">>Secondary RTID is not available is optional for order related to bonded pair network.<<");
//      }
      
      myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + methodNameName);
   }

   protected void prepareRemarksAndMiscSections(SoacServiceOrderVO aSSVO,
         SvcOrdRequestDataObject aReqDataObject, String aActionIndicator)
   {
      String methodNameName = "SOACServiceOrderRequestGenerator3.prepareRemarksAndMiscSections(...)";
      myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodNameName);

      super.prepareRemarksAndMiscSections(aSSVO, aReqDataObject,
            aActionIndicator);
      
      try
      {
         aSSVO.put(SoacServiceOrderConstants.BILLING_ACCOUNT_NUMBER,
               ((SvcOrdRequestDataObject3) aReqDataObject)
                     .getBillingAccountNumberString());
      }
      catch(Exception e)
      {
         myLogger.log(LogEventId.INFO_LEVEL_2,
               ">>Billing account number is not available.<<");
      }
      
      try
      {
         aSSVO.put(SoacServiceOrderConstants.DSL_DISCONNECT_TN,
               ((SvcOrdRequestDataObject3) aReqDataObject)
                   .getDSLDisconnectTelephoneNumber().theValue());
      }
      catch(Exception e)
      {
         myLogger.log(LogEventId.INFO_LEVEL_2,
               ">>DSL disconnect number is not available.<<");
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
   protected boolean isDueDateChange(String subActionType, String orderActionType, boolean completeIndicator)
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
		String methodName = "SOACServiceOrderRequestGenerator3: addOrderActionTypeAndSubActionType()";
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
	   
	   /**
	   * Method getNewClassOfService.
	   * @param iBusResFlag
	   * @return String
	   */
	   protected String getNewClassOfService(String iNetworkType, String iClassOfService) {
           String myClassOfService = null;

           if (iNetworkType.equalsIgnoreCase(NetworkType3Values.IPCO) || iNetworkType.equalsIgnoreCase(NetworkType3Values.IPRT)) 
           {		   
        	   if(iClassOfService.trim().equalsIgnoreCase(SOAC_BUS_CLS_OF_SVC))
        		   myClassOfService = IPDSLAM_BUS_CLS_OF_SVC;
        	   else if (iClassOfService.trim().equalsIgnoreCase(SOAC_RES_CLS_OF_SVC))
        		   myClassOfService = IPDSLAM_RES_CLS_OF_SVC;
           } 
           else 
           {
//        	   if(iClassOfService.equalsIgnoreCase(ClassOfServiceValues.BUSINESS))
//        		   myClassOfService = SOAC_BUS_CLS_OF_SVC;
//        	   else if (iClassOfService.equalsIgnoreCase(ClassOfServiceValues.RESIDENTIAL))
//        		   myClassOfService = SOAC_RES_CLS_OF_SVC;
    		   myClassOfService = iClassOfService;
           }   
	     return myClassOfService;
	   }
}