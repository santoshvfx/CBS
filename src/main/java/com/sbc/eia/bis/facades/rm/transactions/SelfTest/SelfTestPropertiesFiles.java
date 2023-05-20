//$Id: SelfTestPropertiesFiles.java,v 1.1 2011/04/08 17:18:53 rs278j Exp $
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
//# 11/28/2006  Jon Costa             Creation.
//#	02/17/2008	Julius Sembrano		  SelfTest enhancements

package com.sbc.eia.bis.facades.rm.transactions.SelfTest;

import java.util.Properties;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sun.java.util.collections.ArrayList;
import com.sun.java.util.collections.ListIterator;

/**
 * @author jc1421
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SelfTestPropertiesFiles
{
		
	/**
	 * Constructor for selfTestPropertiesFiles.
	 */
	public SelfTestPropertiesFiles(String fileName, BisContext aContext, BisLogger bisLogger, ArrayList aResultList)
	{
		super();
		String myMethodName = "selfTestPropertiesFiles::selfTestPropertiesFiles()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		//Use the util class FileLoader to read, parse, and put to the contents of FileResources.properties in an ArrayList
		ArrayList aPropertiesFilesList = FileLoader.loadFileContents(fileName, bisLogger); 
		
		ListIterator li = aPropertiesFilesList.listIterator();
		int fileCount = 1;
		while (li.hasNext())
			{
				String file = (String)li.next();
				bisLogger.log(LogEventId.OUTPUT_DATA, "File "  + fileCount +  " " + file);
				try
				{
					//get properties file
					fileCount++;
					Properties p = PropertiesFileLoader.read(file, bisLogger);
					bisLogger.log(
						LogEventId.INFO_LEVEL_1,
						file + " Loaded Successfully");
					SelfTestResult.addResultToList(
						"FILE",
						file + " Loaded Successfully.",
						true,
						bisLogger,
						aResultList);
				}
				catch (Exception e)
				{
					bisLogger.log(
							LogEventId.INFO_LEVEL_1,
							file + " Access Failed. " + e.getMessage());
					SelfTestResult.addResultToList(
						"FILE",
						file + " Access Failed. " + e.getMessage(),
						false,
						bisLogger,
						aResultList);
				}
			}

		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}
}
