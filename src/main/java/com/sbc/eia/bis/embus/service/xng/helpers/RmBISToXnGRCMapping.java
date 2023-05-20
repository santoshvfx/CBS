//$Id: RmBISToXnGRCMapping.java,v 1.8 2005/09/19 19:28:22 jn1936 Exp $

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
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 5/02/2005 | Jinmin Ni	         | create
//# 5/10/2005 | Jinmin Ni            | Added logic to add destsination tag to jms 
//#                                    properites to XnG RC
//# 5/11/2005 | Jinmin Ni            | change private var properites to be hastable type
//# 5/19/2005 | Jinmin Ni            | change orderCode to "D" for orderPass of "Cease" per Leonard Crockett 
//# 6/01/2005 | Jinmin Ni            | Changed to use new copyright notice
//# 6/24/2005 | Jinmin Ni		     | Mapped to empty string if orderAction and member attributes are null
//# 9/19/2005 | Jinmin Ni            | Changed orderPass comparison against the idl defined constants in 
//#                                  | OrderActionTypeValue instead of hardcoded orderAction constants
//#
                               
package com.sbc.eia.bis.embus.service.xng.helpers;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.OrderActionTypeValues;

/**
 * @author jn1936
 */
public class RmBISToXnGRCMapping
{
	
	public static final String XNG_RC_DESTINATION_TAG = "XNG_RC_DESTINATION";
	
	public static final String defaultValue = "";
	//embus properties tag
	public static final String XNG_RC_ORDER_TYPE_TAG = "xngRcORderType";
	public static final String XNG_RC_ORDER_PASS_TAG = "xngRcOrderPass";
	public static final String XNG_RC_ACTION_CODE_TAG = "xngRcActionCode";
	public static final String EMBUS_MESSAGE_TAG="embusMessageTag";
	public static final String DESTINATION =  "DESTINATION";
	
	//embus properties value
//	public static final String N_ORDER_TYPE = "N";
//	public static final String C_ORDER_TYPE = "C";
//	public static final String D_ORDER_TYPE = "D";
	public static final String PRE_ORDER_PASS = "PRE";
	public static final String PCN_ORDER_PASS = "PCN";
	public static final String CPC_ORDER_PASS = "CPC";
	public static final String COR_ORDER_PASS = "COR";
	public static final String CAN_ORDER_PASS = "CAN";
	public static final String ADD_ACTION_CODE = "A";
	public static final String UPDATE_ACTION_CODE = "U";
	public static final String DELETE_ACTION_CODE = "D";
	public static final String RETRIEVE_ACTION_CODE = "R";
	public static final String ACKNOWLEDGEMENT = "Acknowledgement";

	private Properties aJMSProperties;
	private OrderAction aOrderAction;
	private Hashtable aRmBisProperties;
	
	public RmBISToXnGRCMapping(Hashtable aProp, OrderAction aOrderAction)
	{
		aJMSProperties = new Properties();
		aRmBisProperties = aProp;
		this.aOrderAction = aOrderAction;	
	}
	public Properties mapProperties()
	{
		orderActionToEmbusProperties();
		mapDestinationToEmbusProperties();
		return aJMSProperties;
	}
	public void orderActionToEmbusProperties()
	{
		//orderAction is required object, assume it is vertified before this call.

		//map orderType , 
		//TODO:  first char of the order
		String orderType = defaultValue;
		String orderPass = defaultValue;
		String actionCode = defaultValue;
		
		try
		{
			orderType = aOrderAction.aOrder.theValue().trim().substring(0,1).toUpperCase();
		}
		catch (org.omg.CORBA.BAD_OPERATION e)
		{
			//do nothing mapped to default
		}
		catch(NullPointerException e)
		{
			//do nothing mapped to default
		}
		catch(IndexOutOfBoundsException e)
		{
			//do nothing mapped to default
		}
		//TODO:
		//map orderPass  ??  find out the mapping
		try
		{
			orderPass = aOrderAction.aOrderActionType.theValue().trim();
		}
		catch (org.omg.CORBA.BAD_OPERATION e)
		{
			//do nothing mapped to default
		}
		catch(NullPointerException e)
		{
			//do nothing mapped to default
		}
		catch(IndexOutOfBoundsException e)
		{
			//do nothing mapped to default
		}
		
		if(orderPass.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_PROVIDE))
		{
			orderPass = PRE_ORDER_PASS;
			actionCode = ADD_ACTION_CODE;
		}
		else if(orderPass.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_CEASE))
		{
			orderPass = CAN_ORDER_PASS;
			actionCode = DELETE_ACTION_CODE;
		}
		else if(orderPass.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_CHANGE))
		{
			orderPass = COR_ORDER_PASS;
			actionCode = UPDATE_ACTION_CODE;
		}
		aJMSProperties.setProperty(XNG_RC_ORDER_TYPE_TAG,orderType);
		aJMSProperties.setProperty(XNG_RC_ORDER_PASS_TAG,orderPass);
		aJMSProperties.setProperty(XNG_RC_ACTION_CODE_TAG,actionCode);
		
	}
	
	public void mapDestinationToEmbusProperties()
	{
		String destination = (String)aRmBisProperties.get(XNG_RC_DESTINATION_TAG);
		aJMSProperties.setProperty(DESTINATION, destination== null ? defaultValue : destination);
	}
}
