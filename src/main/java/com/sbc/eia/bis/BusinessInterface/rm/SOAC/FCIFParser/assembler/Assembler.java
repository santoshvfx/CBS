//Source file: C:\\appl\\EIA\\srm\\Workspaces\\SRM-CPOS-Borg\\srm-ejb\\srm\\java\\src\\com\\sbc\\eia\\srm\\parsersvc\\assembler\\strategy\\Assembler.java

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.assembler;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.assembler.strategy.AssemblerStrategy;

/**
com.sbc.eia.srm.parsersvc.assembler.strategy
============================================================================
File name: Assembler
============================================================================
Create Date: Jan 4, 2005
Create Time: 10:16:53 AM
============================================================================
Author: Raj Sankuratri
UserID: @ns3580
============================================================================
Class Purpose - ** Description and purpose of the Class in brief **
 */
public abstract class Assembler implements AssemblerStrategy
{
	private Logger logger = null;
    private String orderContext = null;

	/**
	@roseuid 41DACF7303AF
	 */
	public Assembler()
	{
		super();
	}

	public void setLogger(Logger logger)
	{
		this.logger = logger;
	}
	public void setOrderContext(String orderContext)
	{
        this.orderContext = orderContext;
	}
	/**
	 * @return
	 */
	public Logger getLogger()
	{
		return logger;
	}
}
