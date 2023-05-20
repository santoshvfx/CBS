/*
 * Created on Jan 18, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.util;

public class FormatDate {
	
	
	//convert Data=e Format from mm-dd-yy to mmddyy
	public String DateFormat1(String str)
	{
	String str1 = str.substring(0,2) + str.substring(3,5) + str.substring(6,8);
    return str1;
	}
	
//	convert Data=e Format from mm-dd-yy to mm-dd
	public String DateFormat2(String str)
	{
	String str1 = str.substring(0,2) + "-" + str.substring(3,5);
    return str1;
	}
	
//	convert Data=e Format from mm-dd-yy to yymmdd
	public String DateFormat3(String str)
	{
	String str1 = str.substring(6,8) + str.substring(0,2) + str.substring(3,5);
    return str1;
	}
}
