package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.utilities;

import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AttributeItemSeqTypeImpl;

/**
 * @author ML2917
 *
 * Wrapper class for AttributeItemSeqTypeImpl.
 */

public class AttributeItemSeqHelper
{

	private AttributeItemSeqTypeImpl attributeItemSeqType = null;

	/**
	 * @see java.lang.Object#Object()
	 */
	
	public AttributeItemSeqHelper()
	{
		attributeItemSeqType = new AttributeItemSeqTypeImpl();
	}

	/**
	 * Method add.
	 * @param item
	 */
	
	public void add(String item)
	{

		attributeItemSeqType.getAttributeItem().add(item);

	}

	/**
	 * Method getObject.
	 * @return AttributeItemSeqTypeImpl
	 */
	
	public AttributeItemSeqTypeImpl getObject()
	{
		return attributeItemSeqType;
	}

}
