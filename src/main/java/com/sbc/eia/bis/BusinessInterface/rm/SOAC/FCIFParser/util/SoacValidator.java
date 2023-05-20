/*
 * Created on Jan 19, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.util;

public class SoacValidator {
	/*	
		public String enhanceBasicAddress(String address)
		{
			int i=0;
			StringBuffer sbuff = new StringBuffer(address);
			
			for(i=0; i<address.length(); i++)
			{
				if(!Character.isDigit(address.charAt(i)))
					break;
			}
			sbuff = sbuff.insert(i,"#");
			return sbuff.toString();
			
		}
	*/
	public String enhanceBasicAddress(String address) {
		if (address != null) {
			int i = address.indexOf(' ');

			if (i != -1) {
				StringBuffer sbuff = new StringBuffer(address);
				sbuff = sbuff.insert(i, "#");
				return sbuff.toString();
			}
		}
		return address;

	}

}
