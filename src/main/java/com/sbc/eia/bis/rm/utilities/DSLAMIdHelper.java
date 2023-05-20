//$Id: DSLAMIdHelper.java,v 1.1 2006/08/15 20:34:26 jo8461 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2005.  All Rights Reserved.
//#
//# History :
//# Date      | Author      | Notes
//# ----------------------------------------------------------------------------------------
//# 03/23/2006  Chaitanya    Creation.
//# 03/23/2006	Chaitanya	 CR 9347 : Parse DSLAM Name and DSLAM Port from DSLAMId
//# 05/10/2006	Chaitanya	 Add leading zeroes if none is present for Port. 

package com.sbc.eia.bis.rm.utilities;

import java.util.Hashtable;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.types.StringOpt;

/**
 * @author ck2932
 */
public class DSLAMIdHelper {
	
	private Utility aUtility = null;
	private Hashtable aProperties = null;
	 
	static final String PREPEND = "1-1";
	static final String DASH = "-";
	static final String ZERO = "0";

	private String aDSLAMId = null;
	private String aCODSLAMPort = null;
	
	private String aFloor = null;
	private String aShelf = null;
	private String aSlot = null;
	private String aPort = null;
	
	public DSLAMIdHelper(Utility utility, Hashtable properties) {
		aProperties = properties;
		aUtility = utility;	
	}
	
	/**
	 * @param aId
	 */
	
	public void splitDSLAM(StringOpt aId)	{
		
		String myMethodName = "DSLAMIdHelper:splitDSLAM()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		if (!OptHelper.isStringOptEmpty(aId))	{
			try {
				StringTokenizer st = new StringTokenizer(aId.theValue(), "-");
				if (st.hasMoreElements())
					aFloor = st.nextToken().trim();

				if (st.hasMoreElements())
					aShelf = st.nextToken().trim();

				if (st.hasMoreElements())
					aSlot = st.nextToken().trim();
					
				if (st.hasMoreElements())
					aPort = st.nextToken().trim();

			} catch (Exception e) {	
				aUtility.log(LogEventId.EXCEPTION, e.getMessage());
			}
		}
		
		if (aFloor != null)
		{
			if (!aFloor.equals(""))
				aDSLAMId = aFloor.trim();		
		}
		if (aShelf != null)
		{
			if (!aShelf.equals(""))
				aDSLAMId = aDSLAMId + DASH + aShelf.trim();
		}

		if (aSlot != null)
		{
			if (!aSlot.equals(""))
			{
				try	{				
					int aTrimmedSlot = Integer.parseInt(aSlot);  
					aCODSLAMPort = PREPEND + DASH + aTrimmedSlot;
				}
				catch(Exception e) 	{	
					e.printStackTrace(); 				
				}
			}			
		}
		if (aPort != null)
		{
			if ((!aPort.equals("")) && !(aPort.equals("0")))
			{	
				if (aPort.length() < 2 )				
					aPort = ZERO + aPort;
					
				aCODSLAMPort = aCODSLAMPort + DASH + aPort.trim();
			}
		}
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);	
	}
	
	/**
	 * @return aDSLAMId
	 */
	public String getDSLAMId()	{
		return aDSLAMId;
	}
	
	/**
	 * @return aCODSLAMPort
	 */
	public String getDSLAMPort()	{
		return aCODSLAMPort;
	}

}
