// $Id: ServiceItem.java,v 1.1 2005/04/18 16:17:02 jn1936 Exp $

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
//# 9/30/2004   Stevan Dunkin         Creation.
//# 10/25/2004  Stevan Dunkin         Changed getElemets() and getObjects() to return 
//#                                   an empty ArrayList if HashMaps are empty instead
//#                                   of throwing an exception. 
//# 11/08/2004  Vickie Ng			  Added isEmptyObject()
//# 04/15/2005  Jinmin Ni			  Copy form netprovision.utities 
//#

package com.sbc.eia.bis.embus.service.netprovision.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ElementItemImpl;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ObjectItemImpl;

/**
 * @author sd6248
 *
 */
public class ServiceItem {

	// Keep all the data in a hash table.

	protected HashMap theHashElements;     //Holds key/value pair element Items.
	private HashMap theHashItems;          //Holds embedded Item objects of the current Item.
	private String itemName;               //Holds the names of the embedded ServiceItem objects.
	private ArrayList embeddedItemNames;
    
	/**
	 * Constructor
	 */
	public ServiceItem() {
		theHashElements = new HashMap();
		theHashItems = new HashMap(); 
		embeddedItemNames = new ArrayList(); 
	}
    
	/**
	 * Constructor
	 * @param name
	 * @param list
	 */
	public ServiceItem(String name, List list) {
		this();
		this.itemName = name;
		this.setElements(list);
	}

	/**
	 * Constructor
	 * @param name
	 */
	public ServiceItem(String name) {

		this();
		this.itemName = name;

	}
    
	/**
	 * Set the name of the Service Item
	 * @param name
	 */
	public void setItemName(String name) {
		this.itemName = name;
	}

	/**
	 * Returns the name of the current ServiceItem.
	 * @return
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * Returns the names of the embedded ServiceItem objects.
	 * @return Vector containing sub Item names
	 */
	public ArrayList getEmbeddedItemNames() {
		return this.embeddedItemNames;
	}

	/**
	 * Get a List object as input. take the list of element from the object
	 * and put it in a hashtable.
	 * @param list
	 */
	public void setElements(List list) {
		Iterator elementIter = list.iterator();

		while (elementIter.hasNext()) {

			ElementItemImpl elementItem = (ElementItemImpl) elementIter.next();

			// Add to hash table.

			theHashElements.put(elementItem.getName(), elementItem.getValue());
		}
	}

	/**
	 * @return ArrayList of Element Items
	 * @throws NullDataException
	 */
	public ArrayList getElements() {

		if (theHashElements.isEmpty()) {
			return new ArrayList();
		}

		Iterator mapIter = theHashElements.entrySet().iterator();

		ArrayList list = new ArrayList();

		while (mapIter.hasNext()) {

			Map.Entry entry = (Map.Entry) mapIter.next();

			ElementItemImpl item = new ElementItemImpl();

			item.setName((String) entry.getKey());
			item.setValue((String) entry.getValue());

			list.add(item);

		}

		return list;

	}
	
	public ArrayList getObjects()  {

			if (theHashElements.isEmpty()) {
				return new ArrayList();
			}

			Iterator mapIter = theHashItems.entrySet().iterator();

			ArrayList list = new ArrayList();

			while (mapIter.hasNext()) {

				Map.Entry entry = (Map.Entry) mapIter.next();

				ObjectItemImpl item = new ObjectItemImpl();
				
				item.getObject().add(entry);

				list.add(item);

			}

			return list;

		}

	/**
	 * Set the embedded ServiceItem objects of this ServiceItem
	 * @param item ServiceItem object
	 */
	public void setEmbeddedItem(ServiceItem item) {

		this.theHashItems.put(item.getItemName(), item);
		this.embeddedItemNames.add(item.getItemName());
	}

	/**
	 * Get an embedded ServiceItem object from this ServiceItem
	 * @param name
	 * @return
	 */
	public ServiceItem getEmbeddedItem(String name) {
		return (ServiceItem) this.theHashItems.get(name);
	}

	/**
	 * Indicates if the SeviceItem contains embedded ServiceItems.
	 * @return boolean - true if there are embedded ServiceItems.
	 */
	public boolean containsEmbeddedItems() {
		return !this.theHashItems.isEmpty();
	}
	
	/**
	 * Method isEmptyObject.
	 * @return boolean
	 */
	public boolean isEmptyObject()
	{
		return (theHashElements.isEmpty() && theHashItems.isEmpty());	
	}
}
