//$Id: ValidationRulesManager.java,v 1.1 2006/08/15 20:33:14 jo8461 Exp $

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
import java.util.StringTokenizer;
import gnu.regexp.*;


/**
 * @author mk2394
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ValidationRulesManager {

	private String delimiter = null;
	
	/**
	 * @param aDelimiter
	 */
	public ValidationRulesManager(String aDelimiter)
	{
		delimiter = aDelimiter;
	}

	/**
	 * @return
	 */
//	private String getDelimiter() {
//
//		return delimiter;
//	}

	/**
	 * @param rule
	 * @param value
	 * @return
	 * @throws REException
	 */
	public boolean ExecuteValidationRule(String rule,String value) throws REException
	{
		   boolean retValue = false;
		   String type = null,conditionalFlag = null, validValues = null;
		   StringTokenizer strTok = null;
              
		   strTok = new StringTokenizer( rule, delimiter );
		   type = strTok.nextToken();
		   conditionalFlag = strTok.nextToken();
		   validValues = strTok.nextToken();
              
		   switch (type.charAt(0))
		   {	
				 //String type
				 case 's':
				 case 'S':
					if (conditionalFlag.charAt(0) == 't' || conditionalFlag.charAt(0) == 'T' )
					   retValue = validateConditionalString(value,validValues);
					else
					   retValue = validateString(value);
				 break;
				 // Interger Type
				 case 'i':
				 case 'I':
					if (conditionalFlag.charAt(0) == 't' || conditionalFlag.charAt(0) == 'T' )
					   retValue = validateConditionalInteger(value,validValues);
					else
					   retValue = validateInteger(value);
				 break;
		   } // switch (type.charAt(0)) 
		   
		   return retValue;
	}

	/**
	 * @param value
	 * @return
	 * @throws REException
	 */
	private boolean validateString(String value) throws REException
		{	
		  boolean retValue = true;
		  RE regExpression = null;
      
      
		  regExpression = new RE( "[^A-Z&&[^_]]");
		  retValue = !regExpression.isMatch(value.toUpperCase());

      
		  return retValue;
		}

	/**
	 * @param value
	 * @param Conditional
	 * @return
	 * @throws REException
	 */
	private boolean validateConditionalString(String value,String Conditional) throws REException
		{	
		  boolean retValue = false;
		  RE regExpression = null;
      
		  // First check to see if it is an valid String before check its conditional part. 
		  if ( validateString(value.toUpperCase()) )
		  {
			  regExpression = new RE( Conditional);
			  retValue = regExpression.isMatch(value.toUpperCase());
		  }
      
		  return retValue;
		}

	/**
	 * @param value
	 * @return
	 * @throws REException
	 */
	private boolean validateInteger(String value) throws REException
		{	
			boolean retValue = true;
			REMatch[] allMatches = null;
			RE regExpression = null;

			regExpression = new RE( "[^0-9]");
			allMatches = regExpression.getAllMatches(value);
			if ( 0 < allMatches.length )
			   retValue = false;

      
			return retValue;
		}

	/**
	 * @param value
	 * @param condition
	 * @return
	 * @throws REException
	 */
	private boolean validateConditionalInteger(String value,String condition) throws REException
	{	
	  boolean retValue = false;
	  RE regExpression = null;
	  
	  // First check to see if it is an valid Integer before check its conditional part. 
	  if ( validateInteger(value))
	  {
		regExpression = new RE( condition);
		retValue = regExpression.isMatch(value);
	  }
      
	  return retValue;
	}

	
	public static void main(String[] args) {
	}
}
