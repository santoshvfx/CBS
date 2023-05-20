//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\ParseRequest.java

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.assembler.strategy.AssemblerStrategy;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test.TestLogger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.ServiceOrderVO;
import com.sbc.eia.idl.srm_types.ProductSubscriptionRequest;

/**
 * com.sbc.eia.srm.parsersvc
 * ============================================================================
 * File name: ParseRequest
 * ============================================================================
 * Create Date: Dec 20, 2004
 * Create Time: 10:28:20 AM
 * ============================================================================
 * Author: Raj Sankuratri
 * UserID: @ns3580
 * ============================================================================
 * Class Purpose - ** Description and purpose of the Class in brief **
 */
public class ParseRequest implements ParseRequestI 
{
   private String svcOrderData[];
   
   /**
    * accountHandle:  The appropriate values for this would be a 13 character BTN or 
    * WTN
    */
   private String accountHandle = "1234567891234";
   private String son;
   private String svcCenter;
   private String operationType;
   private String fcifDataString;
   private String assignmentString;   
   
   /**
    * The values should be SBCMIDWEST, SBCWEST, SBCEAST, SBCSOUTHWEST
    */
   private String region;
   
   /**
    * Set the data format to one of the predefined formats
    * 1. "LIGHTSPEED_FCIF",
    * 2. "POSTED_SERVICE_ORDER",
    * 3. "PENDING_SERVICE_ORDER",
    * 4. "PSR_OBJECTS"
    */
   private String dataFormat;
   private Logger logger = new TestLogger ();
   private AssemblerStrategy assemblerStrategy;
   private ProductSubscriptionRequest psrArray[];
   private ServiceOrderVO[] serviceOrderVoArray;
   private Object serviceOrderVo;
   

   /**
    * @roseuid 4284B7AB02C9
    */
   public ParseRequest() 
   {

		// TODO Auto-generated constructor stub    
   }
   
   /**
    * Dec 27, 2004 8:19:29 AM
    * @return
    * @roseuid 4284B7AB02E8
    */
   public Logger getLogger() 
   {

		return logger;    
   }
   
   /**
    * Dec 27, 2004 8:19:29 AM
    * @param logger
    * @roseuid 4284B7AB02F8
    */
   public void setLogger(Logger logger) 
   {
		this.logger = logger;    
   }
   
   /**
    * Jan 12, 2005 1:34:47 PM
    * @return
    * @roseuid 4284B7AB0319
    */
   public AssemblerStrategy getAssemblerStrategy() 
   {
		return assemblerStrategy;    
   }
   
   /**
    * Jan 12, 2005 1:34:47 PM
    * @param assemblerStrategy
    * @roseuid 4284B7AB0335
    */
   public void setAssemblerStrategy(AssemblerStrategy assemblerStrategy) 
   {
		this.assemblerStrategy = assemblerStrategy;    
   }
   
   /**
    * @return
    * @roseuid 4284B7AB0346
    */
   public String getSvcCenter() 
   {
		return svcCenter;    
   }
   
   /**
    * @return
    * @roseuid 4284B7AB0355
    */
   public String getSon() 
   {
		return son;    
   }
   
   /**
    * @param svcCenter
    * @roseuid 4284B7AB0356
    */
   public void setSvcCenter(String svcCenter) 
   {
		this.svcCenter = svcCenter;    
   }
   
   /**
    * @param son
    * @roseuid 4284B7AB0364
    */
   public void setSon(String son) 
   {
		this.son = son;    
   }
   
   /**
    * @return
    * @roseuid 4284B7AB03A3
    */
   public String getRegion() 
   {
		return region;    
   }
   
   /**
    * @param region
    * @roseuid 4284B7AB03A4
    */
   public void setRegion(String region) 
   {
		this.region = region;    
   }
   
   /**
    * @return
    * @roseuid 4284B7AB03B3
    */
   public String getAccountHandle() 
   {
		return accountHandle;    
   }
   
   /**
    * @param accountHandle
    * @roseuid 4284B7AB03C2
    */
   public void setAccountHandle(String accountHandle) 
   {
		this.accountHandle = accountHandle;    
   }
   
   /**
    * (non-Javadoc)
    * @see com.sbc.eia.bis.srm.serviceorder.ParseRequestI#setDataFormat(java.lang.String)
    * @param dataFormat
    * @roseuid 4284B7AB03D2
    */
   public void setDataFormat(String dataFormat) 
   {
		this.dataFormat = dataFormat;    
   }
   
   /**
    * (non-Javadoc)
    * @see com.sbc.eia.bis.srm.serviceorder.ParseRequestI#getDataFormat()
    * @return java.lang.String
    * @roseuid 4284B7AB03D4
    */
   public String getDataFormat() 
   {
		return dataFormat;    
   }
   
   /**
    * @see 
    * com.sbc.eia.bis.srm.serviceorder.ParseRequestI#setOperationType(java.lang.String)
    * @param operationType
    * @roseuid 4284B7AB03E1
    */
   public void setOperationType(String operationType) 
   {
		this.operationType = operationType;    
   }
   
   /**
    * (non-Javadoc)
    * @see com.sbc.eia.bis.srm.serviceorder.ParseRequestI#getOperationType()
    * @return java.lang.String
    * @roseuid 4284B7AC0009
    */
   public String getOperationType() 
   {
		return operationType;    
   }

   
   /**
    * Dec 27, 2004 8:19:29 AM
    * @return
    * @roseuid 4284B8840009
    */
   public String[] getSvcOrderData() 
   {
		return svcOrderData;    
   }
 
   
   /**
    * @param svcOrderData
    * @roseuid 4284B88400A5
    */
   public void setSvcOrderData(String[] svcOrderData) 
   {
		this.svcOrderData = svcOrderData;    
   }
   
//   /**
//    * @return
//    * @roseuid 4284B88400A7
//    */
//   public ProductSubscriptionRequest[] getPsrArray() 
//   {
//		return psrArray;    
//   }
//   
//   /**
//    * @param psrArray
//    * @roseuid 4284B88400C4
//    */
//   public void setPsrArray(ProductSubscriptionRequest[] psrArray) 
//   {
//		this.psrArray = psrArray;    
//   }
   
   public void setFcifDataString(String fcifDataString) 
   {
    	this.fcifDataString = fcifDataString;
   }

   /**
    * @return java.lang.String
    * @roseuid 428B5ADB019A
    */
   public String getFcifDataString() 
   {
    return fcifDataString;
   }

   public void setAssignmentString(String assignmentString) 
   {
        this.assignmentString = assignmentString;
   }
   
   /**
    * @return java.lang.String
    * @roseuid 428B5ADB019A
    */
   public String getAssignmentString() 
   {
    return assignmentString;
   }

/**
 * @return
 */
public Object getServiceOrderVo()
{
	return serviceOrderVo;
}

/**
 * @param orderVOs
 */
public void setServiceOrderVo(Object serviceOrderVo)
{
	this.serviceOrderVo = serviceOrderVo;
}

}
