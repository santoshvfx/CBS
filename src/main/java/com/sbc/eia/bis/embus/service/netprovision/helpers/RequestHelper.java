// $Id: RequestHelper.java,v 1.4 2007/07/20 00:08:44 sl2917 Exp $

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
//# Date      | Author        		| Notes
//# ----------------------------------------------------------------------------
//# 9/27/2004   Mark Liljequist       Creation.
//# 10/21/2004  Stevan Dunkin         Added constructor to take DataServiceRequestHeader object
//# 10/22/2004  Stevan Dunkin         Added catching and logging of CORBA.BAD_OPERATION and NullPointerException
//# 10/22/2004  Stevan Dunkin and     Signature change for add requestItem().
//#             Jinmin Ni
//# 04/15/2005  Jinmin Ni			  Copy form netprovision.utities 
//# 06/09/2005  Manjula Goniguntla    Have set the Activation Params.
//#

package com.sbc.eia.bis.embus.service.netprovision.helpers;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestImpl;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestItemImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;


/**
 * @author ml2917
 *
 * The is a high level class to encapsulate the XML  generated objects.
 * 
 */

public class RequestHelper {

	protected RequestImpl theRequest;

	public RequestHelper() {

		theRequest = new RequestImpl();

		theRequest.setSchemaVersion(theRequest.getSchemaVersion());

	}

	public RequestHelper(String activityId, String designId, String orderId) {

		this();

		theRequest.setDesignId(designId);

		theRequest.setActivityId(activityId);

		theRequest.setOrderId(orderId);

	}

	/*
	 * Get the request object that has been built.
	 * 
	 */

	public RequestImpl getRequest() {

		return theRequest;
	}

	/*
	 * Add a request itme to the request.
	 * There can be many request items.
	 */

	public void addRequestItem(
		RequestItem aItem) {

		RequestItemImpl requestItem = new RequestItemImpl();

		requestItem.setAction(aItem.getAction());

		requestItem.setServiceRequest(aItem.getServiceRequest());

		requestItem.setServiceOperation(aItem.getServiceOperation());

		requestItem.setNotes(aItem.getNotes());

		requestItem.setResultPolicy(aItem.getPolicy());

		requestItem.setClassName(aItem.getClassName());

		requestItem.setName(aItem.getName());
		
		requestItem.setActivationParams(aItem.getActivationParamType());
		

		// Fill the item list on the requestItem.

		ServiceItemHelper serviceHelper = new ServiceItemHelper();
		serviceHelper.createRequestObjectItem(requestItem, aItem.getAItem());
		theRequest.getRequestItem().add(requestItem);

	}

}
