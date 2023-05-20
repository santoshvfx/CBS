//$Id: ParserSvcFactory.java,v 1.4 2008/02/28 23:15:01 op1664 Exp $
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
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 07/2006		SRM     Creation
//# 07/25/2008  Ott Phannavong 		  LS7: Added getParserSvc(Logger, String)

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
//import com.sbc.eia.bis.srm.serviceorder.ParserSvc;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParserSvc;
//import com.sbc.eia.bis.srm.serviceorder.ParserSvcImpl;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParserSvcImpl;
//import com.sbc.eia.bis.srm.serviceorder.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
/******************************************************************************
 * com.sbc.eia.srm.parsersvc
 * ============================================================================
 * File name: ParserSvcFactory
 * ============================================================================
 * Create Date: Dec 15, 2004
 * Create Time: 3:55:58 PM
 * ============================================================================
 * Author: Raj Sankuratri
 * UserID: @ns3580
 * ============================================================================
 * Class Purpose - ** Description and purpose of the Class in brief **
 * 
 * 
 *****************************************************************************/
public class ParserSvcFactory
{
	private ParserSvc parserSvc = null;
	private ParserSvcHelper helper = null;
	private Logger logger;
	
	private static ParserSvcFactory factory = new ParserSvcFactory();
    
    private ParserSvcException pse = null;
	

	/**
	 * 
	 */
	private ParserSvcFactory()
	{
		super();
	}
	
	/**
	 * @param logger
	 * @return
	 * @throws ParserSvcException
	 */
	public ParserSvc getParserSvc(Logger logger) throws ParserSvcException
	{
        helper = new ParserSvcHelper(logger);
 
		logger.log(LogEventId.DEBUG_LEVEL_1, "Creating ParserSvc instance");
		ParserSvcImpl impl = new ParserSvcImpl(helper);
				
		return impl;
	}
	/**
	 * @param logger
	 * @param aPropertyFileName
	 * @return an instance of ParserSvcImpl
	 * @throws ParserSvcException
	 */
	public ParserSvc getParserSvc(Logger logger, String aPropertyFileName) throws ParserSvcException
	{
        helper = new ParserSvcHelper2(logger, aPropertyFileName);
 
		logger.log(LogEventId.DEBUG_LEVEL_1, "Creating ParserSvc instance");
		ParserSvcImpl impl = new ParserSvcImpl(helper);
				
		return impl;
	}

	/**
	 * Dec 27, 2004	10:12:39 AM
	 * @return
	 * 
	 * 
	 */
	public static ParserSvcFactory getFactory()
	{		
		return factory;
	}

}
