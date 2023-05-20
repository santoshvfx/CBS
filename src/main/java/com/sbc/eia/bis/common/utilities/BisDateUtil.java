package com.sbc.eia.bis.common.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;

/**
 * @author gg2712
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class BisDateUtil
{
	public static final String EMPTY_STRING = "";
	public static final String FORWARD_SLASH = "/";
	
	/*
	 * Main method to test class
	 */
	public static void main(String[] args) 
	{
		EiaDate aDate = new EiaDate();
		aDate.aDay = 26;
		aDate.aMonth = 8;
		aDate.aYear = 5;
		
		aDate = toEiaDate(aDate);
		System.out.println("In main, aDate is: " + aDate.aYear + "/" + aDate.aMonth + "/" + aDate.aDay);
	}
	
	/**
	 * Convert 2-digit year to 4-digit if needed. 
	 * A 2-digit year is adjusted to within 80 years before and 20 years after the
	 * current year. Example 25 is converted to 2005. 26 is converted to 1926.
	 * Any other lenght year will not be corrected and left as is.
	 * @param EiaDate aEiaDate
	 * @return EiaDate 
	 */
	public static EiaDate toEiaDate(EiaDate aEiaDate)
	{
		try
		{
			int aYear = aEiaDate.aYear;
			
			if (aYear < 100)
			{
				Calendar today = new GregorianCalendar();
				int currentYear = today.get(Calendar.YEAR);
				int maxYear = currentYear + 20;
				
				aYear = aYear + currentYear - (currentYear % 100);
				
				if (aYear > maxYear)
				{
					aYear = aYear - 100;
				}
				aEiaDate.aYear = (short) aYear;
			}
		} 
		catch (org.omg.CORBA.BAD_OPERATION e) 
		{
		}
		catch (java.lang.NullPointerException e) 
		{
		} 
		finally 
		{
			return aEiaDate;
		}
	}	

	/**
	 * convert date encapsulated in EiaDate to String date.
	 * @param EiaDate date
	 * @param String dateFormat
	 * @return String
	 */
	public static String dateToString(EiaDate date, String dateFormat) {
		if (date != null) {

			GregorianCalendar dateString =
				new GregorianCalendar(
					(int) date.aYear,
					(int) date.aMonth - 1,
					(int) date.aDay);

			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			return formatter.format(dateString.getTime());
		}

		return EMPTY_STRING;
	}
	
	/**
	 * convert date of type EiaDate to String date of format MM/DD.
	 * @param date
	 * @param format
	 * @param isMonthNumeric flag to check if required format has month in numeric form eg MM
	 * 		 to get month in MMM (eg JAN) format use dateToString(EiaDate,String) method
	 * @return String
	 */
	 public static String dateToString(EiaDate date, String format, boolean isMonthNumeric)
	 {
		StringBuffer sb = new StringBuffer();
	 	
		 if ((date != null) && (isMonthNumeric == true))
		 { 	
		 	if(format.equalsIgnoreCase("MM/DD"))
		 	{			 	
				if (date.aMonth <= 9)
				{
					sb.append("0" + date.aMonth + FORWARD_SLASH);
				}
				else
				{
					sb.append(date.aMonth + FORWARD_SLASH);
				}
				if (date.aDay <= 9)
				{
					sb.append("0" + date.aDay);
				}
				else
				{
					sb.append(date.aDay);
				}
		 	}
		 }
		 return sb.toString(); 
	 }
	 
	/**
	 * Converts an int to EiaDate
	 * @param int date
	 * @param String format
	 * @return EiaDate
	 */
	public static EiaDate toEiaDate(int intDate, String format) 
	{
		EiaDate ed = new EiaDate();
		String strDate = "";

		if (intDate > 0)
		{		
			strDate = String.valueOf(intDate);
			ed = toEiaDate(strDate, format);
		}

		return ed;
	}
	
	/**
	 * Converts a String to EiaDate
	 * @param String date
	 * @param String format
	 * @return EiaDate
	 */
	public static EiaDate toEiaDate(String strDate, String format) 
	{
		EiaDate ed = new EiaDate();

		if (strDate != null && strDate.trim().length() > 0 && 
					format!= null && format.trim().length() > 0) 
		{	
			if (strDate.trim().length() < format.trim().length())
			{
				strDate = padStringStart(strDate.trim(),format.trim().length(),'0'); 
			}
			
			Date date = null;
			try 
			{
				SimpleDateFormat myDateFormat = new SimpleDateFormat(format.trim());
				date = myDateFormat.parse(strDate.trim());
			} 
			catch (ParseException e) 
			{
				return ed;
			}
			
			Calendar dateCal = Calendar.getInstance();
			dateCal.setTime(date);

			ed.aDay = (short)dateCal.get(Calendar.DAY_OF_MONTH);
			ed.aMonth = (short)(dateCal.get(Calendar.MONTH) + 1);
			ed.aYear = (short)dateCal.get(Calendar.YEAR);
		}
		return ed;
	}

	/**
	 * Converts a Calendar to EiaDateOpt
	 * @param dateStamp Calendar
	 * @return EiaDateOpt
	 */
	public static EiaDateOpt toEiaDateOpt(Calendar dateStamp) {
		EiaDateOpt aEiaDateOpt = new EiaDateOpt();
		
		try {
			
			aEiaDateOpt.theValue( new EiaDate(
			(short) dateStamp.get(Calendar.YEAR),
			(short) ((short) dateStamp.get(Calendar.MONTH)+1),
			(short) dateStamp.get(Calendar.DAY_OF_MONTH)));
					
		} catch (org.omg.CORBA.BAD_OPERATION e) {
			aEiaDateOpt._default();
		} catch (java.lang.NullPointerException e) {
			aEiaDateOpt._default();
		} finally {
			return aEiaDateOpt;
		}
	}
	
	/**
	 * Converts an int to EiaDateOpt
	 * @param int date
	 * @param String format 
	 * @return EiaDateOpt
	 */
	public static EiaDateOpt toEiaDateOpt(int date, String format) 
	{
		EiaDateOpt edo = new EiaDateOpt();
		edo.theValue(toEiaDate(date, format));

		return edo;
	}

	/**
	 * Converts a String to EiaDateOpt
	 * @param String date
	 * @param String format 
	 * @return EiaDateOpt
	 */
	public static EiaDateOpt toEiaDateOpt(String date, String format) 
	{
		EiaDateOpt edo = new EiaDateOpt();
		edo.theValue(toEiaDate(date, format));

		return edo;
	}
			
	/**
	 * padStringStart() Pads string with char at beginning of string.
	 * Creation date: (11/11/03 3:41:22 PM)
	 * @param someString String
	 * @param length int
	 * @param pad char
	 * @return String
	 */

	public static String padStringStart(
		String someString,
		int length,
		char pad) {
		if (someString == null)
			return someString;

		StringBuffer temp = new StringBuffer(someString);
		while (temp.length() < length)
			temp.insert(0, pad);
		return temp.toString();
	}			
}
