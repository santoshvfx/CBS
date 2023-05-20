//$Id: ServiceItemHelper.java,v 1.2 2006/08/11 23:07:56 ml2917 Exp $

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
//# 9/30/2004   Stevan Dunkin         Creation.
//# 10/25/2004  Stevan Dunkin         Removed try and catch blocks form getObjectItem()
//#                                   because of changes made to ServiceItem object.
//# 04/15/2005  Jinmin Ni			  Copy form netprovision.utities

package com.sbc.eia.bis.embus.service.netprovision.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sbc.eia.bis.embus.service.netprovision.interfaces.ObjectItem;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.RequestItem;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ObjectItemImpl;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ResponseItemImpl;

/**
 * @author sd6248
 *
 */

public class ServiceItemHelper {

	/**
	 * @see java.lang.Object#Object()
	 */

	public ServiceItemHelper() {

	}

	/**
	 * Method createRequestObjectItem.
	 * @param requestItem
	 * @param serviceItem
	 * @throws NullDataException
	 */

	public void createRequestObjectItem(RequestItem requestItem, ServiceItem serviceItem) {

		this.setElements(requestItem, serviceItem.getElements());

		if (serviceItem.containsEmbeddedItems()) {
			this.setObjects(requestItem, this.getObjectItem(serviceItem).getObject());
		}
	}

	/**
	 * Method setElements.
	 * @param item
	 * @param list
	 */

	private void setElements(ObjectItem item, ArrayList list) {
		Iterator listIter = list.iterator();
		while (listIter.hasNext()) {

			item.getElement().add(listIter.next());
		}

	}

	/**
	 * Method setObjects.
	 * @param item
	 * @param list
	 */

	private void setObjects(ObjectItem item, List list) {
		Iterator listIter = list.iterator();
		while (listIter.hasNext()) {
			item.getObject().add(listIter.next());
		}
	}

	/**
	 * Method getObjectItem.
	 * @param item
	 * @return ObjectItem
	 * @throws NullDataException
	 */

	private ObjectItem getObjectItem(ServiceItem item) {

		ObjectItemImpl objectImpl = new ObjectItemImpl();

		this.setElements(objectImpl, item.getElements());
		objectImpl.setName(item.getItemName());

		for (int i = 0; i < item.getEmbeddedItemNames().size(); i++) {
			objectImpl.getObject().add(
				getObjectItem(item.getEmbeddedItem((String) item.getEmbeddedItemNames().get(i))));
		}
		return objectImpl;
	}

	/**
	 * Method createServiceItem.
	 * @param responseItem
	 * @return ServiceItem
	 */

	public ServiceItem createServiceItem(ResponseItemImpl responseItem) throws NullResourcesForServiceException {

		return getServiceItem((ObjectItem) responseItem);
	}

	/**
	 * Method getServiceItem.
	 * @param item
	 * @return ServiceItem
	 * @throws NullResourcesForServiceException
	 */

	private ServiceItem getServiceItem(ObjectItem item) throws NullResourcesForServiceException {

		ServiceItem itemImpl = null;

		itemImpl = ServiceItemFactory.getInstance(item.getName().trim(), item.getElement());

		for (int i = 0; i < item.getObject().size(); i++) {
			itemImpl.setEmbeddedItem(getServiceItem((ObjectItem) item.getObject().get(i)));

		}

		return itemImpl;
	}

}
