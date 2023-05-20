package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.assembler.strategy;

import com.sbc.bccs.utilities.Logger;

/******************************************************************************
 * com.sbc.eia.srm.parsersvc.assembler.strategy
 * ============================================================================
 * File name: AssemblerStrategy
 * ============================================================================
 * Create Date: Jan 4, 2005
 * Create Time: 11:26:49 AM
 * ============================================================================
 * Author: Raj Sankuratri
 * UserID: @ns3580
 * ============================================================================
 * Class Purpose - ** Description and purpose of the Class in brief **
 * 
 * 
 *****************************************************************************/
public interface AssemblerStrategy
{
	
	public static final int USOC = 0;
	public static final int FLOATED_FID = 2;
	public static final int LH_FID = 3;
	
	
    public void setLogger(Logger logger);
    
/**
 @param fidType String
 @param fidName String
 @param fidValue String
 @roseuid 41E40AF60269
 */
   public void addFidData(int fidType, String fidName, String fidValue);
   
   /**
   @roseuid 41E414650027
    */
   public Object assembleReturnObject(String btn);
   
   /**
   @param usocValue String
   @roseuid 41E414E40075
    */
   public void setUsocData(String usocValue);
   
   /**
   @param aActionIndicator String   
   @roseuid 41E423F20150
    */
   public void setActionIndicator(int aActionIndicator);
   
   /**
   @param fidValue String
   @roseuid 41E4205E03E0
    */
   public void setLHFData(String fidValue);
   
   public void setOrderContext(String orderContext);
}
