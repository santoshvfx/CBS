package com.sbc.eia.bis.facades.testing;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 * Insert the type's description here.
 * Creation date: (5/22/00 10:08:32 AM)
 * @author: Administrator
#   History :
#   Date		| Author			| Version	|Notes
#   ----------------------------------------------------------------------------
#	6/08/2004     Stevan Dunkin		  RM 9  	 Changed URL from separate Host and Port entry to 
#												 a single Corbaloc URL scheme. 
 */

public class InitialContextFactory {

	public final static String INIT_CONTEXT_FACTORY = "com.ibm.ws.naming.util.WsnInitCtxFactory";
	
	private InitialContext ctx;

	/**
	 * InitialContextFactory constructor comment.
	 */
	public InitialContextFactory() {
		try {
			ctx = getInitialContextInternal(null);
		}
		catch (NamingException e) {
			//dont do anything here
		}
	}

	/**
	 * InitialContextFactory constructor comment.
	 */
	public InitialContextFactory(String aProviderURL) {

		try {
			this.ctx = getInitialContextInternal(aProviderURL);
		}
		catch (NamingException e) {
			//dont do anything here
		}
	}

	/**
	 * InitialContextFactory constructor comment.
	 */
	public InitialContextFactory(
		String aProviderURL,
		String user,
		String password) {

		try {
			ctx = getInitialContextInternal(aProviderURL);
		}
		catch (NamingException e) {
			//dont do anything here
		}
	}
	
	/*
	 * Changed method logic; RM 9; Stevan Dunkin; 06/09/2004
	 * */
	public InitialContext getInitialContext()
		throws InitialContextFactoryException {

		if (this.ctx != null) {
			return this.ctx;
		}

		try {
			this.ctx = this.getInitialContextInternal(null);
			return this.ctx;
		}
		catch (NamingException e) {
			throw new InitialContextFactoryException(e.getMessage());
		}

	}
	
	/*
	 * Changed method logic; RM 9; Stevan Dunkin; 06/09/2004
	 * */
	private InitialContext getInitialContextInternal(String aProviderURL)
		throws NamingException {

		if (this.ctx != null) {
			return this.ctx;
		}

		Properties p = new Properties();

		if (aProviderURL != null) {
			System.out.println("Using provider URL of: " + aProviderURL);
			p.put(javax.naming.Context.PROVIDER_URL, aProviderURL);
		}

		p.put(
			Context.INITIAL_CONTEXT_FACTORY,
			INIT_CONTEXT_FACTORY);

		try {
			return new InitialContext(p);
		}
		catch (NamingException e) {
			throw new NamingException(
				"Error Retrieving InitialContext:" + e.getMessage());
		}
		catch (Exception e) {
			throw new NamingException(
				"Uncaught Exception while getting InitialContext:"
					+ e.getMessage());
		}
	}
}
