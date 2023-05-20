//Source file: C:\\Appdev\\Wsad\\WorkSpace\\srm-branch\\srm-ejb\\srm\\java\\src\\com\\sbc\\eia\\srm\\parsersvc\\parser\\ParserStrategy.java

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.parser;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParserSvc;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.ParserConstants;

/**
com.sbc.eia.srm.parsersvc.parser
============================================================================
File name: ParserStrategy
============================================================================
Create Date: Dec 20, 2004
Create Time: 12:27:34 PM
============================================================================
Author: Raj Sankuratri
UserID: @ns3580
============================================================================
Class Purpose - ** Description and purpose of the Class in brief **
 */
public interface ParserStrategy extends ParserSvc 
{   
   /**
   @param logger
   @roseuid 41D032AB0335
    */
   public void setLogger(Logger logger);
   
   

	/**
 	* @param constantsName
 	*/
	public void setConstants(ParserConstants parserConstants);
   

}
