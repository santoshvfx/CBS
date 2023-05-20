//Source file: C:\\Appdev\\Wsad\\WorkSpace\\srm-branch\\srm-ejb\\srm\\java\\src\\com\\sbc\\eia\\srm\\parsersvc\\assembler\\AssemblerHelper.java

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.assembler.strategy.helper;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.AssemblerException;

public class AssemblerHelper 
{
    private Logger logger;
   
/**
  @roseuid 41C30A090363
  */
public AssemblerHelper() throws AssemblerException
{
    super();
}

/**
 * @param logger
 */
public void setLogger(Logger logger)
{
    this.logger = logger;
}

}
