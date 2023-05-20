package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.loader;

import java.util.Hashtable;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.ParserConstants;

/******************************************************************************
 * com.sbc.eia.srm.parsersvc.constants
 * ============================================================================
 * File name: StateLoaderImpl
 * ============================================================================
 * Create Date: Dec 28, 2004
 * Create Time: 1:53:43 PM
 * ============================================================================
 * Author: Raj Sankuratri
 * UserID: @NS3580
 * ============================================================================
 * Class Purpose - ** Description and purpose of the Class in brief **
 * 
 * 
 *****************************************************************************/
public class StateLoader
{

        
   private Hashtable constants = new Hashtable();

	private static StateLoader instance = new StateLoader();

	/**
	 * 
	 */
	private StateLoader()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.srm.parsersvc.constants.StateLoader#loadState(java.lang.String)
	 */
	public ParserConstants loadState(String context)
	{
       // ParserConstants constants = null;
		if (constants.containsKey(context))
		{
            return ((ParserConstants)constants.get(context));
		}
        // if the string does not match anything else then
        // return null;
		return null;
	}

	/**
	 * Jan 12, 2005	2:49:30 PM
	 * 
	 * 
	 * 
	 */
	public static StateLoader getInstance()
	{
		return instance;
	}

}
