// $Id: ASON_DBconnection.java,v 1.2 2003/04/02 17:52:44 ts8181 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.ASON;

import java.sql.SQLException;
import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;


/**
 * Insert the type's description here.
 * Creation date: (10/18/01 12:04:26 PM)
 * @author: Sanjeev Verma
 */
public class ASON_DBconnection  {
/**
 * ASON_DBconnection constructor comment.
 */
public ASON_DBconnection() {
	super();
}
/**
 * ASON_DBconnection constructor comment.
 * @param dbConnection java.sql.Connection
 */
public DBConnection getDBconnection(Hashtable props,Logger aLogger) throws SQLException {
	
	String dataSourceNm = (String)props.get("jdbcDATA_SOURCE_NAME");
	String jdbcUsrId = (String)props.get("jdbcUSERID");
	String jdbcPassWord = (String)props.get("jdbcPASSWORD");
	String jdbcURL = (String)props.get("jdbcURL");
	String jdbcDriver = (String)props.get("jdbcDRIVER");
	String jdbcOption = (String)props.get("jdbcUSE_CONNECTION_POOL");
	
	DBConnection conn = new DBConnection(dataSourceNm,
										 jdbcUsrId,
										 jdbcPassWord,
										 jdbcURL,
										 jdbcDriver,
										 jdbcOption,
										 aLogger) ;
	return conn;

	}
}
