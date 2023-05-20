//$Id: SvcOrdRequestDataObject.java,v 1.9 2009/02/27 07:36:10 ra0331 Exp $
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
//# 05/16/2007	Doris Sunga			  CR 13440:  setAProperties(aProperties)
//# 09/25/2007  Ott Phannavong		  LS6 - added applicationIndicator as field and getApplicationIndicator()
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

import java.util.Hashtable;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.idl.lim_types.Location;
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
public class SvcOrdRequestDataObject
{
   private String soacServiceOrderNumber = null;
   private String soacServiceOrderCorrectionSuffix = null;
   private String networkType = null;
   private String orderActionId = null;
   private String orderNumber = null;
   private String orderActionType = null;
   private BooleanOpt completionIndicator = null;
   private StringOpt subActionType = null;
   private String circuitId = null;
   private Location serviceLocation = null;
   private EiaDate originalDueDate = null;
   private EiaDateOpt subsequentDueDate = null;
   private EiaDate applicationDate = null;
   private StringOpt TDMTelphoneNumber = null;
   private StringOpt relatedServiceOrderNumber = null;
   private BooleanOpt lineShareDisconnectFlag = null;
   private String classOfService = null;
   private BooleanOpt resendFlag = null;
   private String entity = null;
   private String entityPlatform = null;
   private String originatingHost = null;
   private String region = null;
   private ObjectPropertySeqOpt aProperties = null;
   private VoipReqSnEDataObject[] voipSnEData = null;
   protected Logger myLogger = null;
   protected Hashtable myProperties = null;
   //LS3 for CVOIP oredrs NPANXX is passed independently.
   //where as for FTTN/FTTP stuff it comes from aServiceLocation.
   protected String npaNxx = null;
   //Special Conditions Data for LS3 -- Communication Testing Purposes...
   String specialCondiData = null;
   //LS6
   private String applicationIndicator = null;
   //LS10???
   private StringOpt exceptionRoutingIndicator = null;
   
   public SvcOrdRequestDataObject(String aSOACServiceOrderNumber,
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
         String aRegion, VoipReqSnEDataObject[] voipSnEData, String NpaNxx,
         String SpecialCondData)
   {
      soacServiceOrderNumber = aSOACServiceOrderNumber;
      soacServiceOrderCorrectionSuffix = aSOACServiceOrderCorrectionSuffix;
      networkType = aNetworkType;
      orderActionId = aOrderActionId;
      orderNumber = aOrderNumber;
      orderActionType = aOrderActionType;
      completionIndicator = aCompletionIndicator;
      subActionType = aSubActionType;
      circuitId = aCircuitId;
      serviceLocation = aServiceLocation;
      originalDueDate = aOriginalDueDate;
      subsequentDueDate = aSubsequentDueDate;
      applicationDate = aApplicationDate;
      TDMTelphoneNumber = aTDMTelphoneNumber;
      relatedServiceOrderNumber = aRelatedServiceOrderNumber;
      lineShareDisconnectFlag = aLineShareDisconnectFlag;
      classOfService = aClassOfService;
      resendFlag = aResendFlag;
      entity = aEntity;
      entityPlatform = aEntityPlatform;
      this.aProperties = aProperties;
      originatingHost = aOriginatingHost;
      region = aRegion;
      this.voipSnEData = voipSnEData;
      this.npaNxx = NpaNxx;
      //for VOIP setting NAPNXX
      setNpaNxxInServiceLocation();
      //for LS3
      specialCondiData = SpecialCondData;
      //cr 13440
      setAProperties(aProperties);
   }
   /**
    * LS6 create new to add application indicator
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
    * @param voipSnEData
    * @param NpaNxx
    * @param SpecialCondData
    * @param aApplicationIndicator
    */
   public SvcOrdRequestDataObject(String aSOACServiceOrderNumber,
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
         String aRegion, VoipReqSnEDataObject[] voipSnEData, String NpaNxx,
         String SpecialCondData, String aApplicationIndicator)
   {
      soacServiceOrderNumber = aSOACServiceOrderNumber;
      soacServiceOrderCorrectionSuffix = aSOACServiceOrderCorrectionSuffix;
      networkType = aNetworkType;
      orderActionId = aOrderActionId;
      orderNumber = aOrderNumber;
      orderActionType = aOrderActionType;
      completionIndicator = aCompletionIndicator;
      subActionType = aSubActionType;
      circuitId = aCircuitId;
      serviceLocation = aServiceLocation;
      originalDueDate = aOriginalDueDate;
      subsequentDueDate = aSubsequentDueDate;
      applicationDate = aApplicationDate;
      TDMTelphoneNumber = aTDMTelphoneNumber;
      relatedServiceOrderNumber = aRelatedServiceOrderNumber;
      lineShareDisconnectFlag = aLineShareDisconnectFlag;
      classOfService = aClassOfService;
      resendFlag = aResendFlag;
      entity = aEntity;
      entityPlatform = aEntityPlatform;
      this.aProperties = aProperties;
      originatingHost = aOriginatingHost;
      region = aRegion;
      this.voipSnEData = voipSnEData;
      npaNxx = NpaNxx;
      //for VOIP setting NAPNXX
      setNpaNxxInServiceLocation();
      //for LS3
      specialCondiData = SpecialCondData;
      //cr 13440
      setAProperties(aProperties);
      //LS6
      applicationIndicator = aApplicationIndicator;
   }
   
   
   /**
	* LS10 create new to add exceptionRoutingIndicator
    * LS10 create new to add relatedCircuitId and remove TDMTelphoneNumber
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
    * @param aRelatedCircuitId
    * @param aRelatedServiceOrderNumber
    * @param aLineShareDisconnectFlag
    * @param aClassOfService
    * @param aResendFlag
    * @param aEntity
    * @param aEntityPlatform
    * @param aProperties
    * @param aOriginatingHost
    * @param aRegion
    * @param voipSnEData
    * @param NpaNxx
    * @param SpecialCondData
    * @param aApplicationIndicator
    * @param exceptionRoutingIndicator
    */
   public SvcOrdRequestDataObject(String aSOACServiceOrderNumber,
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
         String aRegion, VoipReqSnEDataObject[] voipSnEData, String NpaNxx,
         String SpecialCondData, String aApplicationIndicator, 
         StringOpt aExceptionRoutingIndicator, BooleanOpt aInterceptRedirectIndicator,
         StringOpt aRelatedCircuitID, StringOpt aDryloopRelatedCircuitId)
   {
      soacServiceOrderNumber = aSOACServiceOrderNumber;
      soacServiceOrderCorrectionSuffix = aSOACServiceOrderCorrectionSuffix;
      networkType = aNetworkType;
      orderActionId = aOrderActionId;
      orderNumber = aOrderNumber;
      orderActionType = aOrderActionType;
      completionIndicator = aCompletionIndicator;
      subActionType = aSubActionType;
      circuitId = aCircuitId;
      serviceLocation = aServiceLocation;
      originalDueDate = aOriginalDueDate;
      subsequentDueDate = aSubsequentDueDate;
      applicationDate = aApplicationDate;
      //LS10
      TDMTelphoneNumber = null;
      exceptionRoutingIndicator = aExceptionRoutingIndicator;      
      relatedServiceOrderNumber = aRelatedServiceOrderNumber;
      lineShareDisconnectFlag = aLineShareDisconnectFlag;
      classOfService = aClassOfService;
      resendFlag = aResendFlag;
      entity = aEntity;
      entityPlatform = aEntityPlatform;
      this.aProperties = aProperties;
      originatingHost = aOriginatingHost;
      region = aRegion;
      this.voipSnEData = voipSnEData;
      npaNxx = NpaNxx;
      //for VOIP setting NAPNXX
      setNpaNxxInServiceLocation();
      //for LS3
      specialCondiData = SpecialCondData;
      //cr 13440
      setAProperties(aProperties);
      //LS6
      applicationIndicator = aApplicationIndicator;
   }
   
   private void setNpaNxxInServiceLocation()
   {
      try
      {
         if(serviceLocation.aProviderLocationProperties[0].aPrimaryNpaNxx
               .theValue().trim().length() < 0)
         {
            serviceLocation.aProviderLocationProperties[0].aPrimaryNpaNxx = new StringOpt();
            serviceLocation.aProviderLocationProperties[0].aPrimaryNpaNxx
                  .theValue(this.npaNxx);
         }
      }
      catch(Exception e)
      {
         serviceLocation.aProviderLocationProperties[0].aPrimaryNpaNxx = new StringOpt();
         serviceLocation.aProviderLocationProperties[0].aPrimaryNpaNxx
               .theValue(this.npaNxx);
      }
   }
   /**
    * Returns the applicationDate.
    * @return EiaDate
    */
   public EiaDate getApplicationDate()
   {
      return applicationDate;
   }
   /**
    * Returns the aProperties.
    * @return ObjectPropertySeqOpt
    */
   public ObjectPropertySeqOpt getAProperties()
   {
      return aProperties;
   }
   /**
    * Returns the circuitId.
    * @return String
    */
   public String getCircuitId()
   {
      return circuitId;
   }
   /**
    * Returns the classOfService.
    * @return String
    */
   public String getClassOfService()
   {
      return classOfService;
   }
   /**
    * Returns the completionIndicator.
    * @return BooleanOpt
    */
   public BooleanOpt getCompletionIndicator()
   {
      return completionIndicator;
   }
   /**
    * Returns the entity.
    * @return String
    */
   public String getEntity()
   {
      return entity;
   }
   /**
    * Returns the entityPlatform.
    * @return String
    */
   public String getEntityPlatform()
   {
      return entityPlatform;
   }
   /**
    * Returns the lineShareDisconnectFlag.
    * @return BooleanOpt
    */
   public BooleanOpt getLineShareDisconnectFlag()
   {
      return lineShareDisconnectFlag;
   }
   /**
    * Returns the myLogger.
    * @return Logger
    */
   public Logger getMyLogger()
   {
      return myLogger;
   }
   /**
    * Returns the myProperties.
    * @return Hashtable
    */
   public Hashtable getMyProperties()
   {
      return myProperties;
   }
   /**
    * Returns the networkType.
    * @return String
    */
   public String getNetworkType()
   {
      return networkType;
   }
   /**
    * Returns the orderActionId.
    * @return String
    */
   public String getOrderActionId()
   {
      return orderActionId;
   }
   /**
    * Returns the orderActionType.
    * @return String
    */
   public String getOrderActionType()
   {
      return orderActionType;
   }
   /**
    * Returns the orderNumber.
    * @return String
    */
   public String getOrderNumber()
   {
      return orderNumber;
   }
   /**
    * Returns the originalDueDate.
    * @return EiaDate
    */
   public EiaDate getOriginalDueDate()
   {
      return originalDueDate;
   }
   /**
    * Returns the originatingHost.
    * @return String
    */
   public String getOriginatingHost()
   {
      return originatingHost;
   }
   /**
    * Returns the region.
    * @return String
    */
   public String getRegion()
   {
      return region;
   }
   /**
    * Returns the relatedServiceOrderNumber.
    * @return StringOpt
    */
   public StringOpt getRelatedServiceOrderNumber()
   {
      return relatedServiceOrderNumber;
   }
   /**
    * Returns the resendFlag.
    * @return BooleanOpt
    */
   public BooleanOpt getResendFlag()
   {
      return resendFlag;
   }
   /**
    * Returns the serviceLocation.
    * @return Location
    */
   public Location getServiceLocation()
   {
      return serviceLocation;
   }
   /**
    * Returns the soacServiceOrderCorrectionSuffix.
    * @return String
    */
   public String getSoacServiceOrderCorrectionSuffix()
   {
      return soacServiceOrderCorrectionSuffix;
   }
   /**
    * Returns the soacServiceOrderNumber.
    * @return String
    */
   public String getSoacServiceOrderNumber()
   {
      return soacServiceOrderNumber;
   }
   /**
    * Returns the subActionType.
    * @return StringOpt
    */
   public StringOpt getSubActionType()
   {
      return subActionType;
   }
   /**
    * Returns the subsequentDueDate.
    * @return EiaDateOpt
    */
   public EiaDateOpt getSubsequentDueDate()
   {
      return subsequentDueDate;
   }
   /**
    * Returns the tDMTelphoneNumber.
    * @return StringOpt
    */
   public StringOpt getTDMTelphoneNumber()
   {
      return TDMTelphoneNumber;
   }
   /**
    * Returns the voipSnEData.
    * @return VoipReqSnEDataObject[]
    */
   public VoipReqSnEDataObject[] getVoipSnEData()
   {
      return voipSnEData;
   }
   /**
    * Sets the applicationDate.
    * @param applicationDate The applicationDate to set
    */
   public void setApplicationDate(EiaDate applicationDate)
   {
      this.applicationDate = applicationDate;
   }
   /**
    * Sets the aProperties.
    * @param aProperties The aProperties to set
    */
   public void setAProperties(ObjectPropertySeqOpt aProperties)
   {
      this.aProperties = aProperties;
   }
   /**
    * Sets the circuitId.
    * @param circuitId The circuitId to set
    */
   public void setCircuitId(String circuitId)
   {
      this.circuitId = circuitId;
   }
   /**
    * Sets the classOfService.
    * @param classOfService The classOfService to set
    */
   public void setClassOfService(String classOfService)
   {
      this.classOfService = classOfService;
   }
   /**
    * Sets the completionIndicator.
    * @param completionIndicator The completionIndicator to set
    */
   public void setCompletionIndicator(BooleanOpt completionIndicator)
   {
      this.completionIndicator = completionIndicator;
   }
   /**
    * Sets the entity.
    * @param entity The entity to set
    */
   public void setEntity(String entity)
   {
      this.entity = entity;
   }
   /**
    * Sets the entityPlatform.
    * @param entityPlatform The entityPlatform to set
    */
   public void setEntityPlatform(String entityPlatform)
   {
      this.entityPlatform = entityPlatform;
   }
   /**
    * Sets the lineShareDisconnectFlag.
    * @param lineShareDisconnectFlag The lineShareDisconnectFlag to set
    */
   public void setLineShareDisconnectFlag(BooleanOpt lineShareDisconnectFlag)
   {
      this.lineShareDisconnectFlag = lineShareDisconnectFlag;
   }
   /**
    * Sets the myLogger.
    * @param myLogger The myLogger to set
    */
   public void setMyLogger(Logger myLogger)
   {
      this.myLogger = myLogger;
   }
   /**
    * Sets the myProperties.
    * @param myProperties The myProperties to set
    */
   public void setMyProperties(Hashtable myProperties)
   {
      this.myProperties = myProperties;
   }
   /**
    * Sets the networkType.
    * @param networkType The networkType to set
    */
   public void setNetworkType(String networkType)
   {
      this.networkType = networkType;
   }
   /**
    * Sets the orderActionId.
    * @param orderActionId The orderActionId to set
    */
   public void setOrderActionId(String orderActionId)
   {
      this.orderActionId = orderActionId;
   }
   /**
    * Sets the orderActionType.
    * @param orderActionType The orderActionType to set
    */
   public void setOrderActionType(String orderActionType)
   {
      this.orderActionType = orderActionType;
   }
   /**
    * Sets the orderNumber.
    * @param orderNumber The orderNumber to set
    */
   public void setOrderNumber(String orderNumber)
   {
      this.orderNumber = orderNumber;
   }
   /**
    * Sets the originalDueDate.
    * @param originalDueDate The originalDueDate to set
    */
   public void setOriginalDueDate(EiaDate originalDueDate)
   {
      this.originalDueDate = originalDueDate;
   }
   /**
    * Sets the originatingHost.
    * @param originatingHost The originatingHost to set
    */
   public void setOriginatingHost(String originatingHost)
   {
      this.originatingHost = originatingHost;
   }
   /**
    * Sets the region.
    * @param region The region to set
    */
   public void setRegion(String region)
   {
      this.region = region;
   }
   /**
    * Sets the relatedServiceOrderNumber.
    * @param relatedServiceOrderNumber The relatedServiceOrderNumber to set
    */
   public void setRelatedServiceOrderNumber(StringOpt relatedServiceOrderNumber)
   {
      this.relatedServiceOrderNumber = relatedServiceOrderNumber;
   }
   /**
    * Sets the resendFlag.
    * @param resendFlag The resendFlag to set
    */
   public void setResendFlag(BooleanOpt resendFlag)
   {
      this.resendFlag = resendFlag;
   }
   /**
    * Sets the serviceLocation.
    * @param serviceLocation The serviceLocation to set
    */
   public void setServiceLocation(Location serviceLocation)
   {
      this.serviceLocation = serviceLocation;
   }
   /**
    * Sets the soacServiceOrderCorrectionSuffix.
    * @param soacServiceOrderCorrectionSuffix The soacServiceOrderCorrectionSuffix to set
    */
   public void setSoacServiceOrderCorrectionSuffix(
         String soacServiceOrderCorrectionSuffix)
   {
      this.soacServiceOrderCorrectionSuffix = soacServiceOrderCorrectionSuffix;
   }
   /**
    * Sets the soacServiceOrderNumber.
    * @param soacServiceOrderNumber The soacServiceOrderNumber to set
    */
   public void setSoacServiceOrderNumber(String soacServiceOrderNumber)
   {
      this.soacServiceOrderNumber = soacServiceOrderNumber;
   }
   /**
    * Sets the subActionType.
    * @param subActionType The subActionType to set
    */
   public void setSubActionType(StringOpt subActionType)
   {
      this.subActionType = subActionType;
   }
   /**
    * Sets the subsequentDueDate.
    * @param subsequentDueDate The subsequentDueDate to set
    */
   public void setSubsequentDueDate(EiaDateOpt subsequentDueDate)
   {
      this.subsequentDueDate = subsequentDueDate;
   }
   /**
    * Sets the tDMTelphoneNumber.
    * @param tDMTelphoneNumber The tDMTelphoneNumber to set
    */
   public void setTDMTelphoneNumber(StringOpt tDMTelphoneNumber)
   {
      TDMTelphoneNumber = tDMTelphoneNumber;
   }
   /**
    * Sets the voipSnEData.
    * @param voipSnEData The voipSnEData to set
    */
   public void setVoipSnEData(VoipReqSnEDataObject[] voipSnEData)
   {
      this.voipSnEData = voipSnEData;
   }
   /**
    * Returns the npaNXX.
    * @return String
    */
   public String getNpaNxx()
   {
      return npaNxx;
   }
   /**
    * Sets the npaNXX.
    * @param npaNXX The npaNXX to set
    */
   public void setNpaNXX(String npaNxx)
   {
      this.npaNxx = npaNxx;
   }
   /**
    * Returns the aSpecialCondiData.
    * @return String
    */
   public String getSpecialCondiData()
   {
      return specialCondiData;
   }
   /**
    * Sets the aSpecialCondiData.
    * @param aSpecialCondiData The aSpecialCondiData to set
    */
   public void setSpecialCondiData(String aSpecialCondiData)
   {
      this.specialCondiData = aSpecialCondiData;
   }
   /**
    * Added for LS6
    * Getting application indicator
    * @return Returns the applicationIndicator.
    */
   public String getApplicationIndicator()
   {
      return applicationIndicator;
   }
	/**
	 * @return the exceptionRoutingIndicator
	 */
	public StringOpt getExceptionRoutingIndicator() {
		return exceptionRoutingIndicator;
	}
	/**
	 * @param exceptionRoutingIndicator the exceptionRoutingIndicator to set
	 */
	public void setExceptionRoutingIndicator(StringOpt exceptionRoutingIndicator) {
		this.exceptionRoutingIndicator = exceptionRoutingIndicator;
	}
}
