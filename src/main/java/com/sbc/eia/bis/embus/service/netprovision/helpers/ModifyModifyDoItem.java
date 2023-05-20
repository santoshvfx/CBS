package com.sbc.eia.bis.embus.service.netprovision.helpers;

import java.util.List;
/**
 * @author va6483
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ModifyModifyDoItem extends ServiceItem  {
	
    /**
     * Constructor
     */
    public ModifyModifyDoItem() {
        super();
    }

    /**
     * Constructor
     * @param name String
     */
    public ModifyModifyDoItem(String name) {
        super(name);
    }

    /**
     * Constructor
     * @param name String
     * @param list List
     */
    public ModifyModifyDoItem(String name, List list) {
        super(name, list);
    }	
    
    /**
     * Sets the Circuit Id
     * @param input
     */
    public void setCircuitId(String input) {
        try {
            this.theHashElements.put(ElementName.LS_CIRCUIT_ID_2, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the Service Bundle ID
     * @param input
     */
    public void setServiceBundleId(String input) {
        try {
            this.theHashElements.put(ElementName.SERVICE_BUNDLE_ID, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }
    
        /**
     * Sets the LS Order Number
     * @param input
     */
    public void setOrderActionNumber(String input) {
        try {
            this.theHashElements.put(ElementName.LS_ORDER_ACTION_NUMBER, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the LS Order Action ID
     * @param input
     */
    public void setOrderActionId(String input) {
        try {
            this.theHashElements.put(ElementName.LS_ORDER_ACTION_ID, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }
  
}
