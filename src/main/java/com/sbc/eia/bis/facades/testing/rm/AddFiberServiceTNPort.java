// $Id: AddFiberServiceTNPort.java,v 1.1 2009/06/05 21:39:29 eb231v Exp $
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
//# Date         | Author                     | Notes
//# ----------------------------------------------------------------------------
//# 06/05/2009    Lea Pada                      Creation.

package com.sbc.eia.bis.facades.testing.rm;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;

import javax.ejb.EJBObject;

import com.sbc.eia.bis.facades.rm.ejb.Rm;
import com.sbc.eia.bis.facades.testing.TestClient;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.rm.AddFiberServiceTNPortReturn;

public class AddFiberServiceTNPort extends TestRMCaseBase
{

	private AddFiberServiceTNPortReturn result = null;

	/**
	 * constructor: AddFiberServiceTNPort
	 */
	public AddFiberServiceTNPort(){
		super();
		setMyMethodName("AddFiberServiceTNPort");
	}
	
	
	protected void displayProxyResult() {}

	protected void displayResult() {}
	
	/**
	 * method: displayInput()
	 */
	protected void displayInput() {}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#execute()
	 */
	protected void execute()
	throws
		InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound {
		TestClient.log.printLog("EJB testing Not Implemented.");
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#execute(javax.ejb.EJBObject)
	 */
	protected void execute(javax.ejb.EJBObject remote)
	throws
		InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		RemoteException,
		ObjectNotFound,
		DataNotFound {
	result = ((Rm)remote).addFiberServiceTNPort(
			(aContext != null) ? aContext : TestClient.myBisContext, 
			null, null, null, null, null, null, null, null);
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#execute(java.util.Hashtable)
	 */
	protected void execute(Hashtable props)
	throws
		InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound {		
	TestClient.log.printLog("Vicuna proxy support not implemented");
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String)
	 */
	protected void init(String paramList) {}

	/**
	 * builds IDL - java objects from XML 
	 * @param xmlFileName
	 * @throws Exception
	 */
	public void buildIDLObjectsFromXML(String xmlFileName) throws Exception {}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String, java.lang.String)
	 */
	protected void init(java.lang.String paramList, java.lang.String propertiesFile) {}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String, java.util.Hashtable)
	 */
	protected void init(java.lang.String paramList, Hashtable props) {
		TestClient.log.printLog("Vicuna proxy support not implemented");
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initMessage(java.lang.String, java.util.Hashtable)
	 */
	protected void initMessage(java.lang.String paramList, Hashtable props) 
	{
		TestClient.log.printLog("MDB support not implemented");
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String, java.util.Properties)
	 */
	protected void initSoapMessage(String paramList, Properties aProps) 
	{
		TestClient.log.printLog("SOAFI proxy support not implemented");
	}

	/**
	 * method creates XML from Java objects
	 * @return
	 */
	private String createMessage() 
	{
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapHttpMessage(java.lang.String, java.util.Properties)
	 */
	protected void initSoapHttpMessage(String ParamList, Properties props) 
	{
		TestClient.log.printLog("SOAP/HTTP not implemented");
	}

}
