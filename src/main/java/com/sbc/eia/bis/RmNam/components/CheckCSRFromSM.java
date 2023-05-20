package com.sbc.eia.bis.RmNam.components;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;

import com.sbc.eia.idl.sm.SubscriptionAccountReturn;

/**
 * @author hw7243
 * Check customer service record code returned from SM
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CheckCSRFromSM {

	public CheckCSRFromSM() {
	super();	
	}
	
	public CheckCSRFromSM(Utility aUtility,String SMCSRFile) throws FileNotFoundException,IOException{
		
		utility = aUtility;
		
		loadCSR(utility, SMCSRFile);
	}
	private static final String YES="yes";
	private static final String NO="no";
	private static final String UNKNOWN="UNKNOWN";
	private Properties SMCSRProperties = new Properties();
	private Hashtable CSRStatus = new Hashtable();
	private String aStatus = "";
	private Utility utility = null;
	
	// 
	// Load CSR status (customer service Record code returned from SM) from properties file
	//
	private void loadCSR(Utility utility, String SMCSRFile) throws FileNotFoundException,IOException
	{					
		PropertiesFileLoader.read(SMCSRProperties, SMCSRFile, utility);				
	}
	
	public boolean isActiveAccount(SubscriptionAccountReturn result) {		
		boolean isActive = false;
		String value = "";
		
		for (int i=0; i<result.aSubscriptionAccounts.length; i++) {
			aStatus = result.aSubscriptionAccounts[i].aDefaultBillingAccount.aStatus.toLowerCase().trim();				
			//
			//If there is more than one status returned. Take the Live one if there is any.				
			//If there is no Live status check if it is new Status. if not then the 
			//status will be not live
			//
			if ((String)SMCSRProperties.get(aStatus) == null)
				isActive = newStatus(aStatus);
			else {
				value = (String)SMCSRProperties.get(aStatus);
				if (value.equalsIgnoreCase(YES) == true)  
				{	
					isActive = true;
					break;
				}				
				else isActive = false;
			}								
		}
	    
	    doLog(aStatus, isActive);	
	
		return isActive;
		
	}
	
	public String getSMStatus() {	
		return aStatus;
	}
	
	
	private void doLog (String aStatus, boolean isActive) {		
		utility.log(LogEventId.DEBUG_LEVEL_2, "SM CSR code = " + aStatus);
		utility.log(LogEventId.DEBUG_LEVEL_2, "SM CSR value from properties file isActive = " + isActive);		
	}
	
	private boolean newStatus (String aStatus) {		
		// If the CSR Status code is not defined in the properties file.
		// Treat it as UNKNOWN account and Log the value.
		//	
		String value = "";
		utility.log(LogEventId.INFO_LEVEL_1, "New Undefined SM_CSR Status: " + aStatus);
		
		value = (String)SMCSRProperties.get(UNKNOWN);
		
		if (value.equalsIgnoreCase(YES) == true)  					
			return true;
		else return false;
				
	}
}
