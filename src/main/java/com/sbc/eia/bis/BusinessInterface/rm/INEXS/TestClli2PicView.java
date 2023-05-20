// $Id: TestClli2PicView.java,v 1.3 2003/04/30 17:21:05 ts8181 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.INEXS;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;

/**
 * Test RetrieveStateCodeByZip.
 * Creation date: (4/17/01 2:27:36 PM)
 * @author: Donald W. Lee
 */
public class TestClli2PicView extends TranBase {

/**
 * Contruct a TestRetrievePicByCLLI object.
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param myProperty java.util.Properties
 * @param aLogger com.sbc.eia.bis.framework.logging.BisLogger
 */
public TestClli2PicView(BisContext aContext, Properties myProperty) {

	setPROPERTIES((Hashtable) myProperty);
	callerContext = aContext;
	/**
	 * Define BisLogger parameters.
	 */
	if( myLogger == null ) {
		myLogger = new Logger(myProperty.getProperty( "STD_OUT" ).trim(),  
							  myProperty.getProperty( "BIS_NAME" ).trim(),
							  myProperty.getProperty( "BIS_VERSION" ).trim(),
							  myProperty.getProperty( "LOG_POLICY_PATH" ).trim() ); 
	}

}
/**
 * Starts the TestRetrievePicByCLLI transaction.
 * Creation date: (4/17/01 2:29:24 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) 
{
	System.out.println("Retrieving a PicCd and PicNm by CLLI... ");

	String propertiesFile = "c:/deploy/clli2pic.properties";
	
	ObjectPropertyManager opm = new ObjectPropertyManager();
	opm.add(BisContextProperty.APPLICATION,"RM BIS");
	opm.add(BisContextProperty.SERVICECENTER,"CA");
	BisContext aContext = new BisContext(opm.toArray()) ;

	Properties myProperty = new Properties();
	
 	try {
		// Load jdbc properties for the TranBase class..
		File file;
		FileInputStream fis = null;
		
		//get properties file
		file = new File(propertiesFile);
		try{
			fis = new FileInputStream(file);
			myProperty.load(fis);
		}
		catch(Exception e){
			System.out.println("Load Property file failed.");
			e.printStackTrace();
		}
	
		fis.close();
		TestClli2PicView aDbHelper = new TestClli2PicView(aContext, myProperty);
		String	aCLLI  = new String("");

		// Test cases
		aCLLI = "JCSNMOCIDS0";
//		aCLLI = "STLSMO01DSC";

		aDbHelper.retrievePicByClli( myProperty, aCLLI );
//		aDbHelper.retrievePicByClli( propertiesFile, aCLLI );
	}
	catch( Exception e ) {
		System.out.println("Main() - Exception caught...") ;
		e.printStackTrace();
	};
	
}
/**
 * Execute RetrievePicByCLLI.
 * Creation date: (5/3/01 8:59:03 AM)
 * @param myProperty java.util.Properties
 * @param aCLLI java.lang.String
 * @exception java.sql.SQLException The exception description.
 */
public void retrievePicByClli(Properties myProperty, String aCLLI)
throws java.sql.SQLException
{
	ArrayList retVal = new ArrayList();
	Clli2PicView c2pview = new Clli2PicView(myProperty,this) ;
	retVal = c2pview.find( aCLLI, this ) ;
	Clli2PicRow clli2PicRow = new Clli2PicRow();	
	for (int i=0; i < retVal.size(); i++){
		clli2PicRow = (Clli2PicRow) retVal.get(i);
		System.out.println("Clli2PicRow[" + i + "]=" + clli2PicRow.display());
	}
}
}
