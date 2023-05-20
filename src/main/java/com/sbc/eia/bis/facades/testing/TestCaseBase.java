package com.sbc.eia.bis.facades.testing;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.InitialContext;
import com.sbc.eia.idl.bis_types.BisContext;

/**
 * Insert the type's description here.
 * Creation date: (5/24/01 4:54:31 PM)
 * @author: Sam Lok
 */
public abstract class TestCaseBase {
	public java.lang.String myMethodName = null;
	protected BisContext aContext = null;	
	protected static java.lang.Object beanHome = null;
	protected static InitialContext initialContext = null;
/**
 * TestCaseBase constructor comment.
 */
public TestCaseBase() {
	super();
	setMyMethodName( this.getClass().getName() ) ;
}
/**
 * Insert the method's description here.
 * Creation date: (5/24/01 5:14:32 PM)
 */
protected abstract void processCase(String paramList) throws TestCaseException;

/**process JMX test cases
 * 
 * @param paramList
 * @throws TestCaseException
 */
protected abstract void processJmxCase(String paramList) throws TestCaseException;

/**
 * Insert the method's description here.
 * Creation date: (5/24/01 5:14:32 PM)
 */
protected abstract void processCase(String paramList, String propertiesFile) throws TestCaseException;

/* PROXY CHANGE
 * Insert the method's description here.
 * Creation date: (5/24/01 5:14:32 PM)
 */
protected abstract void processCase(String paramList, Hashtable props) throws TestCaseException;

/* MDB CHANGE
 * Insert the method's description here.
 * Creation date: (5/24/01 5:14:32 PM)
 */
protected abstract void processMessage(String paramList, Hashtable props) throws TestCaseException;

/**
 * SOAP PROXY
 * @param paramList
 * @throws TestCaseException
 */
protected abstract void processSoapMessage(String paramList,Properties props) throws TestCaseException;

/**
 * SOAP/HTTP PROXY
 * @param paramList
 * @throws TestCaseException
 */
protected abstract void processSoapHttpMessage(String paramList,Properties props) throws TestCaseException;


/**
 * Insert the method's description here.
 * Creation date: (5/25/01 9:13:46 AM)
 * @return BisContext
 */

public BisContext getAContext() {
	return aContext;
}
/**
 * Insert the method's description here.
 * Creation date: (5/24/01 5:12:26 PM)
 * @return java.lang.String
 */
public java.lang.String getMyMethodName(){
	return myMethodName;
}

/**
 * Insert the method's description here.
 * Creation date: (5/25/01 9:13:46 AM)
 * @param newAContext BisContext
 */
public void setAContext(BisContext newAContext) {
	aContext = newAContext;
}
/**
 * Insert the method's description here.
 * Creation date: (5/24/01 5:12:26 PM)
 * @param newMyMethodName java.lang.String
 */
public void setMyMethodName(java.lang.String newMyMethodName) {
	myMethodName = newMyMethodName;
}
}