package com.sbc.eia.bis.embus.service.netprovision.helpers;

import java.util.List;

/**
 * @author mg5629
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PublishAutoDiscoveryResultsDoItem extends ServiceItem {

    /**
     * Constructor
     */
    public PublishAutoDiscoveryResultsDoItem() {
        super();
    }

    /**
     * Constructor
     * @param name String
     */
    public PublishAutoDiscoveryResultsDoItem(String name) {
        super(name);
    }

    /**
     * Constructor
     * @param name String
     * @param list List
     */
    public PublishAutoDiscoveryResultsDoItem(String name, List list) {
        super(name, list);
    }

    /**
     * Sets the LS Circuit ID
     * @param input
     */
    public void setCustomerTransportId(String input) {
        try {
            this.theHashElements.put(ElementName.LS_CIRCUIT_ID_3, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }
    
    /**
     * Sets the RG
     * @param input
     */
/**** / Object parameters are removed from the request item to NetP.
 *    public void setRG(RGItem inputItem) {
        try {
            this.setEmbeddedItem(inputItem);
        }
        catch (NullPointerException e)  {
        }
    }
 /***/

   /**
     * Sets the Serial Number
     * @param input
     */
    public void setSerialNumber(String input) {
        try {
            this.theHashElements.put(ElementName.SERIAL_NUMBER, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }
    
      /**
     * Sets the Model Number
     * @param input
     */
    public void setModelNumber(String input) {
        try {
            this.theHashElements.put(ElementName.MODEL_NUMBER, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }   
    
   
    
}
