package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.utilities;

import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AttributeValueItemSeqTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AttributeValueItemTypeImpl;

/**
 * @author ML2917
 *
 *
 * Wrapper class for the AttributeValueItemSeqTypeImpl.
 * This will contain a lsit of the attributes.
 * 
 */

public class AttributeValueItemSeqHelper
{

	private AttributeValueItemSeqTypeImpl attributeValueSeqType = null;
	private boolean emptyList = true;

	/**
	 * @see java.lang.Object#Object()
	 */
	
	public AttributeValueItemSeqHelper()
	{

		attributeValueSeqType = new AttributeValueItemSeqTypeImpl();

	}

	/**
	 * Method add.
	 * @param name
	 * @param value
	 */
	
	public void add(String name, String value)
	{

		attributeValueSeqType.getAttributeValueItem().add(
			new AttributeValueItem(name, value).getObject());

		emptyList = false;

	}

	/**
	 * Method create.
	 * @return AttributeValueItemSeqTypeImpl
	 */
	
	public AttributeValueItemSeqTypeImpl getObject()
	{

		if (emptyList)
		{
			return null;
		}

		return attributeValueSeqType;
	}

		/**
		 * @author ML2917
		 *
		 * Inner class for the attributes.
		 * This will always be a part of the top level class.
		 * 
		 */
	
	public class AttributeValueItem
	{

		private AttributeValueItemTypeImpl attributeValueItemType;

		/**
		 * Method AttributeValueItem.
		 * @param name
		 * @param value
		 */
		
		public AttributeValueItem(String name, String value)
		{

			attributeValueItemType = new AttributeValueItemTypeImpl();

			attributeValueItemType.setName(name);
			attributeValueItemType.setValue(value);

		}

		/**
		 * Method create.
		 * @return AttributeValueItemTypeImpl
		 */
		
		public AttributeValueItemTypeImpl getObject()
		{
			return attributeValueItemType;
		}

	}

}
