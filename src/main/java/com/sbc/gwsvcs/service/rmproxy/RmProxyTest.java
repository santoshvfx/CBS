// $Id: RmProxyTest.java,v 1.6 2006/09/12 18:26:12 ds4987 Exp $

package com.sbc.gwsvcs.service.rmproxy;

import java.util.*;

import com.sbc.gwsvcs.service.rmproxy.exceptions.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
import com.sbc.bccs.idl.helpers.*;
import com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.lim_types.*;
import com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.types.*;
import com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.*;
import com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm_types.*;
import com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.*;
import com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.RmFacadePackage.*;


/**
 * Provides standalone testing.
 * Creation date: (2/26/01 12:32:12 PM)
 * @author: Creighton Malet
 */
public class RmProxyTest implements com.sbc.bccs.utilities.Logger {
	public final static String providerName = "SBC";	
	/**
 * Executes the program.
 * Creation date: (2/26/01 12:33:23 PM)
 * @param args java.lang.String[]
 */


	/**
 * Executes the program.
 * Creation date: (2/26/01 12:33:23 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args)
{
	Hashtable props = new Hashtable();
	props.put("RMPROXY_APPLDATA", "RMDEV");
	props.put("GWSVCS_CLNTUUID", "rmbis");
	props.put("VICUNA_XML_FILE", "c:\\vicunalite\\etc\\vicunalite.xml");
	props.put("VICUNA_SERVICE_CONFIG_DIR", "c:\\vicunalite\\etc");
	
  }

/**
 * Class constructor.
 */
public RmProxyTest() {
	super();
}
/**
 * Implementation of Logger.log().
 * Creation date: (2/26/01 12:42:18 PM)
 * @param eventId java.lang.String
 * @param message java.lang.String
 */
public void log(String eventId, String message) {
	System.out.println("LOG: " + eventId + " " + message);
}

public void log(String s1, String s2, String s3, String s4){}

public void log(String s1, String s2, String s3, String s4, String s5){}

}
