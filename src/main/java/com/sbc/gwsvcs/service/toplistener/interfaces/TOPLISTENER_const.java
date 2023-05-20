package com.sbc.gwsvcs.service.toplistener.interfaces;
import org.omg.CORBA.*;

public interface TOPLISTENER_const extends org.omg.CORBA.Object { 
	final public static int UUID_LNGTH = (int) (15);
	final public static int CONTEXT_LNGTH = (int) (31);
	final public static int HOST_NM_LNGTH = (int) (41);
	final public static int TRAN_ID_LNGTH = (int) (41);
	final public static int DESTINATION_LNGTH = (int) (121);
	final public static int ExceptionResp = (int) (9999);
	final public static int TX_1024_LNGTH = (int) (1025);
	final public static String TOPListenerName = (String) ("TOPListener");
	final public static String TOPListenerVersion = (String) ("2.0.0");
	final public static int TOPListenerSvcID = (int) (6642);
	final public static int TOPSendToHostReq = (int) (6000);
	final public static int TOPSendToHostResp = (int) (6001);
	final public static String TOPECHOTAG = (String) ("__OSSGW_TOPTAG__");
}
