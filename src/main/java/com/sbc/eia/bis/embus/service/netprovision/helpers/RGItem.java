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
public class RGItem extends ServiceItem {

	/**
	 * Constructor
	 */
	public RGItem() {
		super();
	}

	/**
	 * Constructor
	 * @param name String
	 */
	public RGItem(String name) {
		super(name);
	}

	/**
	 * Constructor
	 * @param name String
	 * @param list List
	 */
	public RGItem(String name, List list) {
		super(name, list);
	}

	/**
	 * Sets the Serial Number
	 * @param input
	 */
	/*    public void setSerialNumber(String input) {
	        try {
	            this.theHashElements.put(ElementName.SERIAL_NUMBER, input.trim());
	        }
	        catch (NullPointerException e)  {
	        }
	    }
	*/
	/**
	 * Sets the Device Id
	 * @param input
	 */
	/*    public void setDeviceId(String input) {
	        try {
	            this.theHashElements.put(ElementName.DEVICE_ID, input.trim());
	        }
	        catch (NullPointerException e)  {
	        }
	    }
	*/
	/**
	 * Sets the Model Number
	 * @param input
	 */
	/*   public void setModelNumber(String input) {
	       try {
	           this.theHashElements.put(ElementName.MODEL_NUMBER, input.trim());
	       }
	       catch (NullPointerException e)  {
	       }
	   }
	*/
}
