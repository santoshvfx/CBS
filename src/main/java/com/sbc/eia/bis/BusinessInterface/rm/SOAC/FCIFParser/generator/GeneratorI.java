//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\generator\\GeneratorI.java

/*
 * Created on May 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequestI;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.GeneratorException;

/**
 * @author ns3580
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface GeneratorI 
{
 
public static final int NEW_CONNECT_ORDER = 1;
public static final int DISCONNECT_ORDER = 2;
public static final int CANCEL_ORDER = 3;
public static final int CORRECTION_ORDER = 4;
public static final int COMPLETION_ORDER = 5;

   /**
    * @param request
    * @return java.lang.Object[]
    * @throws com.sbc.eia.bis.srm.serviceorder.exceptions.GeneratorException
    * @roseuid 4284B8930095
    */
   public void generate(ParseRequestI request) throws GeneratorException;
}
