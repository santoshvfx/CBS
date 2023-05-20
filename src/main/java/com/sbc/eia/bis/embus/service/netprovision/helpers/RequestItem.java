//$Id: RequestItem.java,v 1.6 2005/06/24 22:45:28 mg5629 Exp $

//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2001-2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author              | Notes
//# ----------------------------------------------------------------------------
//# 10/22/2004   Stevan Dunkin and    Creation
//#              Jinmin Ni & Mark
//#
//# 10/22/2004  Stevan Dunkin         Removed setter method for static final variable
//#                                   CLASS_NAME.
//# 10/25/2004  Jinmin Ni             Removed name from the constructor parameters list since
//#                                   name is a member of parameters - service item .
//# 10/28/2004  Jinmin Ni             Modified to set policy value in constructor
//# 01/11/2004  Jinmin Ni             Modifed to add class variable and constant for ATM,
//#                                   add getter and setter method for class variable and
//#                                   add constructor specific for ATM F/R.
//# 01/14/2005  Stevan Dunkin         added SL2PACCESSPOINT.
//# 01/18/2005  jp2854                added SZ5EQCIRCUIT.
//# 01/20/2005  jp2854                corrected the class name for SZ5EQCIRCUIT
//# 01/20/2005  Jinmin Ni             corrected the class name for SZ5LOCATION
//# 01/26/2005  Rene Duka             corrected the class name for SL2AccessConnectivity
//# 02/24/2005  Rene Duka             Added LAYER2CONNECTIONS
//# 03/01/2005  Stevan Dunkin         Added className INL_CLASS_NAME = "Internodal"
//# 03/01/2005  Stevan Dunkin         Added className CIRCUIT_USAGE_CLASS_NAME = "CircuitUsage"
//# 04/15/2005  Jinmin Ni             Copy form netprovision.utities
//# 04/19/2005  Rene Duka             Added className NetP_SLXdvcService = "SLXdvcService"
//# 06/07/2005  Manjula Goniguntla    Added classname, NetP_WHndNode for PublishAutoDiscoveryResults.
//# 06/09/2005  Manjula Goniguntla    Added getter and setter for ActivationParams for the trans. PublishAutoDiscoveryResults.
//# 06/10/2005  Rene Duka             Create a signature without ActivationParams for RequestItem.
//#


package com.sbc.eia.bis.embus.service.netprovision.helpers;

import com.sbc.eia.bis.embus.service.netprovision.interfaces.ActivationParamsType;

/**
 * @author sd6248
 *
 */

public class RequestItem
{
    public static final String POMS_CLASS_NAME    = "com.syndesis.NF.Internodal";
    public static final String NetP_SLXdvcService = "SLXdvcService";
    public static final String NetP_WHndNode      = "WHndNode";

    private String serviceRequest = "";
    private String serviceOperation = "";
    private String action = "";
    private String notes = "";
    private String policy;
    private String name = "";
    private String className="";

    private ActivationParamsType activationParamType;
    private ServiceItem aItem;

    public RequestItem(
        String serviceRequest,
        String serviceOperation,
        String action,
        String notes,
        String policy,
        String className,
        ServiceItem item,
        ActivationParamsType ACTIVATION_PARAM)
    {
        this.serviceRequest = serviceRequest;
        this.serviceOperation = serviceOperation;
        this.action = action;
        this.notes = notes;
        this.policy = policy;
        this.name = item.getItemName();
        this.className = className;
        aItem = item;

        ACTIVATION_PARAM.setNetworkDelivery("False");
        ACTIVATION_PARAM.setDescriptionPolicy("");
        ACTIVATION_PARAM.setPriority(1);


        activationParamType = ACTIVATION_PARAM;


    }

    public RequestItem(
        String serviceRequest,
        String serviceOperation,
        String action,
        String notes,
        String policy,
        String className,
        ServiceItem item)
    {
        this.serviceRequest = serviceRequest;
        this.serviceOperation = serviceOperation;
        this.action = action;
        this.notes = notes;
        this.policy = policy;
        this.name = item.getItemName();
        this.className = className;
        aItem = item;
    }

    /**
     * Sets the action.
     * @param action The action to set
     */
    public void setAction(String action)
    {
        this.action = action;
    }

    /**
     * Sets the aItem.
     * @param aItem The aItem to set
     */
    public void setAItem(ServiceItem aItem)
    {
        this.aItem = aItem;
    }

    /**
     * Sets the name.
     * @param name The name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the notes.
     * @param notes The notes to set
     */
    public void setNotes(String notes)
    {
        this.notes = notes;
    }


    /**
     * Sets the serviceOperation.
     * @param serviceOperation The serviceOperation to set
     */
    public void setServiceOperation(String serviceOperation)
    {
        this.serviceOperation = serviceOperation;
    }

    /**
     * Sets the serviceRequest.
     * @param serviceRequest The serviceRequest to set
     */
    public void setServiceRequest(String serviceRequest)
    {
        this.serviceRequest = serviceRequest;
    }

    /**
     * Returns the action.
     * @return String
     */
    public String getAction()
    {
        return action;
    }

    /**
     * Returns the aItem.
     * @return ServiceItem
     */
    public ServiceItem getAItem()
    {
        return aItem;
    }

    /**
     * Returns the name.
     * @return String
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the notes.
     * @return String
     */
    public String getNotes()
    {
        return notes;
    }

    /**
     * Returns the serviceOperation.
     * @return String
     */
    public String getServiceOperation()
    {
        return serviceOperation;
    }

    /**
     * Returns the serviceRequest.
     * @return String
     */
    public String getServiceRequest()
    {
        return serviceRequest;
    }

    /**
     * @return
     */
    public String getClassName()
    {
        return className;
    }

    /**
     * @return
     */
    public String getPolicy()
    {
        return policy;
    }

    /**
     * @param string
     */
    public void setClassName(String string)
    {
        className = string;
    }

    /**
     * @param string
     */
    public void setPolicy(String string)
    {
        policy = string;
    }

    /**
     * Returns the activationParamType.
     * @return ActivationParams
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.ActivationParamsType getActivationParamType() {
        return activationParamType;
    }

    /**
     * Sets the activationParamType
     * @param activationParamType
     */
    public void setActivationParamType(com.sbc.eia.bis.embus.service.netprovision.interfaces.ActivationParamsType actParamType) {
        this.activationParamType = actParamType;
    }

}
