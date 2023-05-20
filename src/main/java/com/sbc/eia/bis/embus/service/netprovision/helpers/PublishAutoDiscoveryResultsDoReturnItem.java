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
public class PublishAutoDiscoveryResultsDoReturnItem extends ServiceItem{
	
	/**
     * Constructor
     * @param name String
     */
    public PublishAutoDiscoveryResultsDoReturnItem(String name) {
        super(name);
    }
    /**
     * Construction
     * @param name String
     * @param list List
     */
    public PublishAutoDiscoveryResultsDoReturnItem(String name, List list) {
        super(name, list);
    }

    /**
     * Returns the Object ID
     * @return String
     */
    public String getObjectId() {
        return (String) this.theHashElements.get(ElementName.OBJECT_ID);
    }

    /**
     * Returns the Service Access Point
     * @return String
     */
    public String getServiceAccessPoint() {
        return (String) this.theHashElements.get(ElementName.SERVICE_ACCESS_POINT);
    }

}
