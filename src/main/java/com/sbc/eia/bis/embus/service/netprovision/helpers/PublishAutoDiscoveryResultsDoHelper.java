package com.sbc.eia.bis.embus.service.netprovision.helpers;

import com.sbc.eia.bis.embus.service.netprovision.interfaces.ActivationParamsType;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ActivationParamsTypeImpl;

/**
 * @author mg5629
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PublishAutoDiscoveryResultsDoHelper extends RequestItem {

    public static final String SERVICE_REQUEST = "Modify";
    public static final String SERVICE_OPERATION = "Modify";
    public static final String ACTION = "Do";
    public static final String NOTES = "";
    public static final String RESULT_POLICY = "";
    public static final ActivationParamsType ACTIVATION_PARAM
                          = new ActivationParamsTypeImpl() ;
    
    
    /**
     * constructor PublishAutoDiscoveryResultsDoHelper.
     * @param className
     * @param item
     */

    public PublishAutoDiscoveryResultsDoHelper (String className,ServiceItem item) 
    {
    
        super(SERVICE_REQUEST, SERVICE_OPERATION, ACTION, NOTES, RESULT_POLICY, className, item, ACTIVATION_PARAM);
   
    }


}
