//$Id: ServiceBundleIdArray.java,v 1.1 2006/08/15 20:33:14 jo8461 Exp $

//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      	| Author              | Notes
//# --------------------------------------------------------------------
//# 6/02/2006	 Michael Khalili		Creation

package com.sbc.eia.bis.rm.components;

public class ServiceBundleIdArray {
	private String[] stringArray = null;
	private String delimiter = null;

	/**
	 * Used to create a new BitArray with the specified size.
	 * @param iSize the number of bits in the array.  The bits are
	 * initialized to false.
	 */
	public ServiceBundleIdArray(int iSize,String aDelimiter)
	{
		delimiter = aDelimiter;
		stringArray = new String[iSize];
		clear();
	}

	/**
	 * @returns the boolean value of the specified bit.
	 * @param iBit the bit to get the value for.  This index is zero based.
	 */
	public String get(int index)
	{
		return stringArray[index];
	}
	
	/**
	 * Sets the specified bit in the array to true.
	 * @param iBit the bit to set equal to true.  This index is zero based.
	 */
	public void set(int index,String aProductId,String aPropertyValue)
	{
	    StringBuffer strBuffer = new StringBuffer();
		
		if (index < size() )
		{   
			strBuffer.append(aProductId.toUpperCase());
			strBuffer.append("_");
			strBuffer.append(aPropertyValue.toUpperCase());
		  
		    stringArray[index] = strBuffer.toString();
		}

//		String wholeValue = null;
//		if (index < size() )
//		{   
//		  wholeValue = aProductId.toUpperCase();
//		  wholeValue += "_";
//		  wholeValue += aPropertyValue.toUpperCase(); 
//		  
//		  stringArray[index] = wholeValue;
//		}
		   
	}
	/**
	 * Sets the specified bit in the array to false.
	 * @param iBit the bit to set equal to false.  This index is zero based.
	 */
	public void clear(int index)
	{
		stringArray[index] = "NULL";
	}
	/**
	 * Sets the specified bit in the array to false.
	 * @param iBit the bit to set equal to false.  This index is zero based.
	 */
	public void clear()
	{

	  for (int i = 0; i < size(); i++)
		stringArray[i] = "NULL";
	}
 

	/**
	 * @returns the number of bits in the array.
	*/
	 public int size()
	 {
		  return stringArray.length;
	 }

	/**
	 * @returns a string representation of the BitArray.
	 */
	public String toString()
	{
		StringBuffer strRet = new StringBuffer();

		for (int i = 0; i < size(); i++)
		{
			strRet.append(get(i));
			if ( (i+1) < size() )
			   strRet.append(delimiter);
		}
		return strRet.toString();
	}


	public static void main(String[] args) {
	}
}
