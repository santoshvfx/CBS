// $Id: SWITCHServerAccess.java,v 1.3 2006/06/05 18:43:45 rd2842 Exp $

package com.sbc.gwsvcs.service.switchserver;

import com.sbc.vicunalite.api.*;
import com.sbc.vicunalite.api.orb.*;
import java.io.*;
import com.sbc.gwsvcs.service.switchserver.exceptions.*;
import com.sbc.gwsvcs.service.switchserver.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * Wraps the service side for access to the SWITCHServer service.
 * Creation date: (4/16/00 11:36:38 AM)
 * @author: Creighton Malet
 */
public class SWITCHServerAccess extends ServiceAccess
{
	public final static String version = "11.0";
	public final static String name = "SWITCHSERVER";

	public final static int SWITCH_TN_INQ_REQ_NBR 	= SWITCHSERVER_Const.SwitchTnInqReq;
	public final static int SWITCH_TN_INQ_RESP_NBR 	= SWITCHSERVER_Const.SwitchTnInqResp;

	public final static int SWITCH_TN_UPD_REQ_NBR 	= SWITCHSERVER_Const.SwitchTnUpdReq;
	public final static int SWITCH_TN_UPD_RESP_NBR 	= SWITCHSERVER_Const.SwitchTnUpdResp;

	public final static int SWITCH_INQ_CKT_REQ_NBR 	= SWITCHSERVER_Const.SwitchInqCktReq;
	public final static int SWITCH_INQ_CKT_RESP_NBR = SWITCHSERVER_Const.SwitchInqCktResp;

	public final static int SWITCH_RTN_TN_REQ_NBR 	= SWITCHSERVER_Const.SwitchRtnTnReq;
	public final static int SWITCH_RTN_TN_RESP_NBR 	= SWITCHSERVER_Const.SwitchRtnTnResp;

	public final static int SWITCH_SEL_TNE_REQ_NBR 	= SWITCHSERVER_Const.SwitchSelTneReq;
	public final static int SWITCH_SEL_TNE_RESP_NBR = SWITCHSERVER_Const.SwitchSelTneResp;

	public final static int SWITCH_SEL_TN_REQ_NBR 	= SWITCHSERVER_Const.SwitchSelTnReq;
	public final static int SWITCH_SEL_TN_RESP_NBR 	= SWITCHSERVER_Const.SwitchSelTnResp;
	
	public final static int SWITCH_INQ_NTU_REQ_NBR 	= SWITCHSERVER_Const.SwitchInqNtuReq;
	public final static int SWITCH_INQ_NTU_RESP_NBR = SWITCHSERVER_Const.SwitchInqNtuResp;
				
    public final static int SWITCH_WSI_NTU_REQ_NBR  = SWITCHSERVER_Const.SwitchWsiNtuReq;
    public final static int SWITCH_WSI_NTU_RESP_NBR = SWITCHSERVER_Const.SwitchWsiNtuResp;

	public final static int EXCEPTION_NBR =  SWITCHSERVER_Const.ExceptionResp;
	
	public final static MEventType SWITCH_TN_INQ_REQ = 		new MEventType("SWITCH_TN_INQ_REQ");		// Event 5000
	public final static MEventType SWITCH_TN_INQ_RESP = 	new MEventType("SWITCH_TN_INQ_RESP");		// Event 5001

	public final static MEventType SWITCH_TN_UPD_REQ = 		new MEventType("SWITCH_TN_UPD_REQ");		// Event 5010
	public final static MEventType SWITCH_TN_UPD_RESP = 	new MEventType("SWITCH_TN_UPD_RESP");		// Event 5011

	public final static MEventType SWITCH_INQ_CKT_REQ = 	new MEventType("SWITCH_INQ_CKT_REQ");		// Event 5050
	public final static MEventType SWITCH_INQ_CKT_RESP = 	new MEventType("SWITCH_INQ_CKT_RESP");		// Event 5051

	public final static MEventType SWITCH_RTN_TN_REQ = 		new MEventType("SWITCH_RTN_TN_REQ");		// Event 5220
	public final static MEventType SWITCH_RTN_TN_RESP = 	new MEventType("SWITCH_RTN_TN_RESP");		// Event 5221

	public final static MEventType SWITCH_SEL_TNE_REQ = 	new MEventType("SWITCH_SEL_TNE_REQ");		// Event 5210
	public final static MEventType SWITCH_SEL_TNE_RESP = 	new MEventType("SWITCH_SEL_TNE_RESP");		// Event 5211
	
	public final static MEventType SWITCH_SEL_TN_REQ = 		new MEventType("SWITCH_SEL_TN_REQ");		// Event 5200
	public final static MEventType SWITCH_SEL_TN_RESP = 	new MEventType("SWITCH_SEL_TN_RESP");		// Event 5201
	
	public final static MEventType SWITCH_INQ_NTU_REQ = 	new MEventType("SWITCH_INQ_NTU_REQ");		// Event 5250
	public final static MEventType SWITCH_INQ_NTU_RESP = 	new MEventType("SWITCH_INQ_NTU_RESP");		// Event 5251
				
    public final static MEventType SWITCH_WSI_NTU_REQ =     new MEventType("SWITCH_WSI_NTU_REQ");       // Event 5170
    public final static MEventType SWITCH_WSI_NTU_RESP =    new MEventType("SWITCH_WSI_NTU_RESP");      // Event 5171
                
	public final static MEventType EXCEPTION = 				new MEventType("EXCEPTION");				// Event 9999
/**
 * Constructor accepting Vicuna configuration file, directory for configuration files and a Logger.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public SWITCHServerAccess(String vicunaXmlFile, String serviceXmlDir, com.sbc.bccs.utilities.Logger aLogger) throws ServiceException
{
	super(version, name, 30000, vicunaXmlFile, serviceXmlDir, aLogger);
}
}
