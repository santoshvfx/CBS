//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\ParseRequestI.java

/*
 * Created on May 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser;

import com.sbc.bccs.utilities.Logger;

/**
 * @author ns3580
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ParseRequestI 
{
      
   /**
    * Data accessors
    * Set the region using the static final choices provided in
    * this interface.
    * @param region
    * @roseuid 4284B7AC00E5
    */
   public abstract void setRegion(String region);
   
   /**
    * Returns the region data String
    * @return region
    * @roseuid 4284B7AC00E7
    */
   public abstract String getRegion();
   
   /**
    * Set the Service Order Number
    * @param son
    * @roseuid 4284B7AC00F3
    */
   public abstract void setSon(String son);
   
   /**
    * Returns the Service Order Number
    * @return son
    * @roseuid 4284B7AC00F5
    */
   public abstract String getSon();
   
   /**
    * Set the data format to one of the predefined formats
    * 1. "SOAC_FCIF",
    * 2. "POSTED_SERVICE_ORDER",
    * 3. "PENDING_SERVICE_ORDER",
    * 4. "PSR_OBJECTS"
    * 5. "SOAC_VALUEOBJECT"
    * @param dataFormat
    * @roseuid 4284B7AC0103
    */
   public abstract void setDataFormat(String dataFormat);
   
   /**
    * Returns the dataFormat String
    * @return dataFormat
    * @roseuid 4284B7AC0105
    */
   public abstract String getDataFormat();
   
   /**
    * Set the logger
    * @param logger
    * @roseuid 4284B7AC0122
    */
   public abstract void setLogger(Logger logger);
   
   /**
    * Returns the Logger object
    * @return logger
    * @roseuid 4284B7AC0133
    */
   public abstract Logger getLogger();
   
   /**
    * Set the operationType parameter using the preset values defined
    * in this interface.  The values are either
    * 1. "SOAC_VALUEOBJECT_TO_FCIF",
    * 2. "FCIF_TO_SOAC_VALUEOBJECT",
    * 3. "STRINGARRAY_TO_PSR"
    * 4. "PSR_TO_STRINGARRAY" etc
    * @param operationType
    * @roseuid 4284B7AC0141
    */
   public abstract void setOperationType(String operationType);
   
   /**
    * GReturns the operationType parameter.
    * The values returned are either
    * 1. "SOAC_VALUEOBJECT_TO_FCIF",
    * 2. "FCIF_TO_SOAC_VALUEOBJECT",
    * 3. "STRINGARRAY_TO_PSR"
    * 4. "PSR_TO_STRINGARRAY" etc
    * @return String operationType
    * @roseuid 4284B7AC0152
    */
   public abstract String getOperationType();
   
   /**
    * Set the service center code
    * @param serviceCenter
    * @param svcCenter
    * @roseuid 4284B7AC0153
    */
   public abstract void setSvcCenter(String svcCenter);
   
   /**
    * Returns the serviceCenter String
    * @return String serviceCenter
    * @roseuid 4284B7AC0162
    */
   public abstract String getSvcCenter();
   
   /**
    * This is a 13 character BTN or WTN
    * @param accountHandle
    * @roseuid 4284B7AC0163
    */
   public abstract void setAccountHandle(String accountHandle);
   
   /**
    * Returns the accountHandle String
    * @return String accountHandle
    * @roseuid 4284B7AC0171
    */
   public abstract String getAccountHandle();
   
   /**
    * Set the data that nedds to be parsed.
    * @param svcOrderData
    * @roseuid 4284B884022C
    */
   public abstract void setSvcOrderData(String[] svcOrderData);
   
   /**
    * Returns the serviceOrderData String[]
    * @return serviceOrderData
    * @roseuid 4284B884023B
    */
   public abstract String[] getSvcOrderData();
   
//   /**
//    * Set the ProductSubscriptionRequest array.
//    * @param psrArray
//    * @roseuid 4284B8840299
//    */
//   public abstract void setPsrArray(ProductSubscriptionRequest[] psrArray);
//   
//   /**
//    * Returns an array of ProductSubscriptionRequest
//    * @return psrArray
//    * @roseuid 4284B88402B8
//    */
//   public abstract ProductSubscriptionRequest[] getPsrArray();
   
   /**
    *  Sets the rawDataString that needs processing.
    * @param rawData
    */
   public abstract void setFcifDataString(String rawData);
   
     /**
      * Returns the rawDataString that needs processing.
      * @return java.lang.String
      * @roseuid 428B5ADB019A
      */
     public abstract String getFcifDataString();
     
   /**
    *  Sets the rawDataString that needs processing.
    * @param rawData
    */
   public abstract void setAssignmentString(String assignmentString);
   
     /**
      * Returns the rawDataString that needs processing.
      * @return java.lang.String
      * @roseuid 428B5ADB019A
      */
     public abstract String getAssignmentString();

     /**
      * 
      * @return ServiceOrderVO[]
      */
    public Object getServiceOrderVo();
    
    
    /**
     * 
     * @param orderVoArray
     */
    public void setServiceOrderVo(Object serviceOrderVo);
    
}
