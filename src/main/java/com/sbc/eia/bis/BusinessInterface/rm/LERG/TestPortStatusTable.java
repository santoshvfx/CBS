// $Id: TestPortStatusTable.java,v 1.5 2003/11/19 01:07:17 sr1284 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.LERG;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.nam.database.PortStatusRow;
import com.sbc.eia.bis.nam.database.PortStatusTable;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;

/**
 * Test RetrieveStateCodeByZip.
 * Creation date: (4/17/01 2:27:36 PM)
 * @author: Donald W. Lee
 */
public class TestPortStatusTable extends TranBase {

/**
 * Class constructor
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param myProperty java.util.Properties
 * @param aLogger com.sbc.eia.bis.framework.logging.BisLogger
 */
public TestPortStatusTable(BisContext aContext, Properties myProperty) {
	super();

	setPROPERTIES((Hashtable) myProperty);
	callerContext = aContext;
	/**
	 * Define BisLogger parameters.
	 */
	if( myLogger == null ) {
		myLogger = new Logger(myProperty.getProperty( "STDOUT" ).trim(),  
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
	System.out.println("Retrieving Port Status information... ");

	String propertiesFile = "c:/rm.properties";
	
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
		TestPortStatusTable aDbHelper = new TestPortStatusTable(aContext, myProperty);

		// Test cases
		TN aTN = new TN("925", "823", "1463", "");
		String anLRN = "9252441111";

		aDbHelper.retrieveByTN( myProperty, aTN );
//		aDbHelper.retrieveByTN( propertiesFile, aTN );
		aDbHelper.retrieveByLRN( myProperty, anLRN );
//		aDbHelper.retrieveByLRN( propertiesFile, anLRN );
	}
	catch( Exception e ) {
		System.out.println("Main() - Exception caught...") ;
		e.printStackTrace();
	};
	
}
/**
 * Execute PortStatusTable.find() by LRN.
 * Creation date: (4/16/01 11:08:36 AM)
 * @param aFileName java.lang.String
 * @param anLRN java.lang.String
 * @exception java.sql.SQLException The exception description.
 */
public void retrieveByLRN(String aFileName, String anLRN) 
throws java.sql.SQLException
{
	ArrayList retVal = new ArrayList();
	retVal = PortStatusTable.find( aFileName, null, anLRN, this ) ;
	PortStatusRow clli2PicRow = new PortStatusRow();
	for (int i=0; i < retVal.size(); i++){
		clli2PicRow = (PortStatusRow) retVal.get(i);
		System.out.println("PortStatusRow[" + i + "]=" + clli2PicRow.display());
	}
}
/**
 * Execute PortStatusTable.find() by LRN.
 * Creation date: (5/3/01 8:59:03 AM)
 * @param myProperty java.util.Properties
 * @param anLRN java.lang.String
 * @exception java.sql.SQLException The exception description.
 */
public void retrieveByLRN(Properties myProperty, String anLRN)
throws java.sql.SQLException
{
	ArrayList retVal = new ArrayList();
	PortStatusTable aPortStatusTable = new PortStatusTable();
	retVal = aPortStatusTable.find( myProperty, anLRN, this ) ;
	PortStatusRow clli2PicRow = new PortStatusRow();	
	for (int i=0; i < retVal.size(); i++){
		clli2PicRow = (PortStatusRow) retVal.get(i);
		System.out.println("PortStatusRow[" + i + "]=" + clli2PicRow.display());
	}
}
/**
 * Execute PortStatusTable.find() by TN.
 * Creation date: (5/3/01 8:59:03 AM)
 * @param myProperty java.util.Properties
 * @param aTN com.sbc.bccs.idl.helpers.TN
 * @exception java.sql.SQLException The exception description.
 */
public void retrieveByTN(Properties myProperty, TN aTN)
throws java.sql.SQLException
{
	ArrayList retVal = new ArrayList();
	PortStatusTable aPortStatusTable = new PortStatusTable();
	retVal = aPortStatusTable.find( myProperty, aTN, this ) ;
	PortStatusRow clli2PicRow = new PortStatusRow();	
	for (int i=0; i < retVal.size(); i++){
		clli2PicRow = (PortStatusRow) retVal.get(i);
		System.out.println("PortStatusRow[" + i + "]=" + clli2PicRow.display());
	}
}
/**
 * Execute PortStatusTable.find() by TN.
 * Creation date: (4/16/01 11:08:36 AM)
 * @param aFileName java.lang.String
 * @param aTN com.sbc.bccs.idl.helpers.TN
 * @exception java.sql.SQLException The exception description.
 */
public void retrieveByTN(String aFileName, TN aTN) 
throws java.sql.SQLException
{
	ArrayList retVal = new ArrayList();
	retVal = PortStatusTable.find( aFileName, aTN, null, this ) ;
	PortStatusRow clli2PicRow = new PortStatusRow();
	for (int i=0; i < retVal.size(); i++){
		clli2PicRow = (PortStatusRow) retVal.get(i);
		System.out.println("PortStatusRow[" + i + "]=" + clli2PicRow.display());
	}
}
}
