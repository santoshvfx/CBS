package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.nam.database.TnMasterTable;
import com.sbc.eia.bis.rm.components.NamClientGetNetworkAddress;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;

/**
 * Does a lot of the Switch wire center logic.
 * Creation date: (10/19/00 3:02:10 PM)
 * @author:Mark Liljequist
 
#   History :
#   Date       | Author        |    Version	 Notes
#   ----------------------------------------------------------------------------
#	08/12/2002	Mark Liljequist		5.0		 Creation.
#
#	03/21/2004	Steva Dunkin		8.0.1    Added method for RM 131998 IDLC inquiry
#
#	09/08/2005  Mark Liljequist     11.0     Change methods calls on NamClientGetNetworkAddress.	
*/

/**
 * Insert the type's description here.
 * Creation date: (8/14/2002 10:09:51 AM)
 * @author: Mark Liljequist
 */
public class FacilityWireCenter {

	Utility theUtility;
	java.util.Hashtable theProperties;
	private NamClientGetNetworkAddress gna = null;

	/**
	 * FacilityWireCenter constructor comment.
	 */
	public FacilityWireCenter(
		Utility aUtility,
		java.util.Hashtable aProperties) {
		super();

		theUtility = aUtility;
		theProperties = aProperties;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/14/2002 10:54:33 AM)
	 * @return java.util.ArrayList
	 */
	public ArrayList getWireCenterBySwitchCLLI(String aCLLI)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {

		// The Switch Wire Center (SOAC_WC) is retrieved from the TN Master using the CCLI code. 

		String originator = (String) theProperties.get("BIS_NAME");

		// Set up Database connection.

		DBConnection dbConn = null;

		String jdbcDataSourceName = "";
		String jdbcUsrId = "";
		String jdbcPassWord = "";
		String jdbcDriver = "";
		String useDataSourcePool = "";
		String jdbcUrl = "";

		try {

			jdbcDataSourceName =
				(String) theProperties.get("jdbcDATA_SOURCE_NAME");
			jdbcUsrId = (String) theProperties.get("jdbcUSERID");
			jdbcPassWord = (String) theProperties.get("jdbcPASSWORD");
			jdbcDriver = (String) theProperties.get("jdbcDRIVER");
			useDataSourcePool =
				(String) theProperties.get("jdbcUSE_CONNECTION_POOL");
			jdbcUrl = (String) theProperties.get("jdbcURL");

		} catch (Exception ex) {

			theUtility.throwException(
				ExceptionCode.ERR_RM_JDBC_PROPS_FAILED,
				"JDBC properties failed: ",
				originator,
				Severity.UnRecoverable);

		}

		ArrayList wireCenters = null;

		TnMasterTable aMasterTable = new TnMasterTable();

		try {

			theUtility.log(
				LogEventId.REMOTE_CALL,
				(String) theProperties.get("jdbcURL"),
				(String) theProperties.get("jdbcURL"),
				(String) theProperties.get("jdbcUSERID"),
				"GWS::TnMasterTable.getWireCenterBySwitchCLLI()");

			dbConn =
				new DBConnection(
					jdbcDataSourceName,
					jdbcUsrId,
					jdbcPassWord,
					jdbcUrl,
					jdbcDriver,
					useDataSourcePool,
					theUtility);

			wireCenters = aMasterTable.getWireCenterBySwitchCLLI(aCLLI, dbConn);

		} catch (SQLException sqlEx) {

			theUtility.throwException(
				ExceptionCode.ERR_RM_WIRECENTER_NOT_FOUND,
				"Get Wire Center failed: "
					+ sqlEx.getErrorCode()
					+ sqlEx.getMessage(),
				originator,
				Severity.UnRecoverable);

		} catch (Exception ex) {

			theUtility.throwException(
				ExceptionCode.ERR_RM_DB_CONNECTION_FAILED,
				"DB Connection failed: ",
				originator,
				Severity.UnRecoverable);
		} finally {

			try {

				theUtility.log(
					LogEventId.REMOTE_RETURN,
					(String) theProperties.get("jdbcURL"),
					(String) theProperties.get("jdbcURL"),
					(String) theProperties.get("jdbcUSERID"),
					"GWS::TnMasterTable.getWireCenterBySwitchCLLI()");

				dbConn.disconnect();

			} catch (Exception ex) {

				theUtility.throwException(
					ExceptionCode.ERR_RM_DB_CONNECTION_FAILED,
					"DB Disconnect failed: ",
					originator,
					Severity.UnRecoverable);

			}

		}

		return wireCenters;

	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/14/2002 10:54:33 AM)
	 * @return java.util.ArrayList
	 */
	public ArrayList getWireCenterByTN(TN aTN)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {

		String originator = (String) theProperties.get("BIS_NAME");
		DBConnection dbConn = null;

		String jdbcDataSourceName = "";
		String jdbcUsrId = "";
		String jdbcPassWord = "";
		String jdbcDriver = "";
		String useDataSourcePool = "";
		String jdbcUrl = "";

		try {

			jdbcDataSourceName =
				(String) theProperties.get("jdbcDATA_SOURCE_NAME");
			jdbcUsrId = (String) theProperties.get("jdbcUSERID");
			jdbcPassWord = (String) theProperties.get("jdbcPASSWORD");
			jdbcDriver = (String) theProperties.get("jdbcDRIVER");
			useDataSourcePool =
				(String) theProperties.get("jdbcUSE_CONNECTION_POOL");
			jdbcUrl = (String) theProperties.get("jdbcURL");

		} catch (Exception ex) {

			theUtility.throwException(
				ExceptionCode.ERR_RM_JDBC_PROPS_FAILED,
				"JDBC properties failed: ",
				originator,
				Severity.UnRecoverable);

		}

		ArrayList wireCenters = null;

		TnMasterTable aMasterTable = new TnMasterTable();

		try {

			theUtility.log(
				LogEventId.REMOTE_CALL,
				(String) theProperties.get("jdbcURL"),
				(String) theProperties.get("jdbcURL"),
				(String) theProperties.get("jdbBySwitchCLLI()"),
				"GWS::TnMasterTable.getWireCenterByTN()");

			dbConn =
				new DBConnection(
					jdbcDataSourceName,
					jdbcUsrId,
					jdbcPassWord,
					jdbcUrl,
					jdbcDriver,
					useDataSourcePool,
					theUtility);
			int line = 0;
			try {
				line = Integer.parseInt(aTN.getLine());
			} catch (NumberFormatException e) {
			}

			wireCenters =
				aMasterTable.getWireCenter(
					aTN.getNpa(),
					aTN.getNxx(),
					line,
					dbConn);

		} catch (SQLException sqlEx) {

			theUtility.throwException(
				ExceptionCode.ERR_RM_WIRECENTER_NOT_FOUND,
				"Get Wire Center failed: "
					+ sqlEx.getErrorCode()
					+ sqlEx.getMessage(),
				originator,
				Severity.UnRecoverable);

		} catch (Exception ex) {

			theUtility.throwException(
				ExceptionCode.ERR_RM_DB_CONNECTION_FAILED,
				"DB Connection failed: ",
				originator,
				Severity.UnRecoverable);
		} finally {

			try {

				theUtility.log(
					LogEventId.REMOTE_RETURN,
					(String) theProperties.get("jdbcURL"),
					(String) theProperties.get("jdbcURL"),
					(String) theProperties.get("jdbcUSERID"),
					"GWS::TnMasterTable.getWireCenterByTN()");

				dbConn.disconnect();

			} catch (Exception ex) {

				theUtility.throwException(
					ExceptionCode.ERR_RM_DB_CONNECTION_FAILED,
					"DB Disconnect failed: ",
					originator,
					Severity.UnRecoverable);

			}

		}

		ArrayList dedup = new ArrayList();
		int wcs = 0;

		wirecenterloop : for (wcs = 0; wcs < wireCenters.size(); wcs++) {

			for (int j = 0; j < dedup.size(); j++) {
				if (dedup.get(j).equals((String) wireCenters.get(wcs)))
					continue wirecenterloop;
			}

			dedup.add((String) wireCenters.get(wcs));

		}

		return dedup;

	}
	public TN getWireCenterTN(TN aTN, BisContext aContext)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {

		String myMethodName = "FacilityWireCenter:getWireCenterTN()";

		theUtility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		if (gna == null)
			gna =
				new NamClientGetNetworkAddress(
					theUtility,
					theProperties);

		return gna.validatePortedTN(aTN, aContext);

	}
    
     //RM 131998
	/**
	 * Method getWireCenterByTNForIDLC.
	 * @param aTN TN
	 * @return ArrayList
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
    public ArrayList getWireCenterByTNForIDLC( TN aTN )
        throws
            AccessDenied,
            BusinessViolation,
            InvalidData,
            NotImplemented,
            ObjectNotFound,
            SystemFailure,
            DataNotFound {

        String originator = (String) theProperties.get("BIS_NAME");
        DBConnection dbConn = null;

        String jdbcDataSourceName = "";
        String jdbcUsrId = "";
        String jdbcPassWord = "";
        String jdbcDriver = "";
        String useDataSourcePool = "";
        String jdbcUrl = "";

        try {

            jdbcDataSourceName =
                (String) theProperties.get("jdbcDATA_SOURCE_NAME");
            jdbcUsrId = (String) theProperties.get("jdbcUSERID");
            jdbcPassWord = (String) theProperties.get("jdbcPASSWORD");
            jdbcDriver = (String) theProperties.get("jdbcDRIVER");
            useDataSourcePool =
                (String) theProperties.get("jdbcUSE_CONNECTION_POOL");
            jdbcUrl = (String) theProperties.get("jdbcURL");

        } catch (Exception ex) {

            theUtility.throwException(
                ExceptionCode.ERR_RM_JDBC_PROPS_FAILED,
                "JDBC properties failed: ",
                originator,
                Severity.UnRecoverable);

        }

        ArrayList wireCenters = null;

        TnMasterTable aMasterTable = new TnMasterTable();

        try {

            theUtility.log(
                LogEventId.REMOTE_CALL,
                (String) theProperties.get("jdbcURL"),
                (String) theProperties.get("jdbcURL"),
                (String) theProperties.get("jdbBySwitchCLLI()"),
                "GWS::TnMasterTable.getWireCenterByTN()");

            dbConn =
                new DBConnection(
                    jdbcDataSourceName,
                    jdbcUsrId,
                    jdbcPassWord,
                    jdbcUrl,
                    jdbcDriver,
                    useDataSourcePool,
                    theUtility);
            int line = 0;
            try {
                line = Integer.parseInt(aTN.getLine());
            } catch (NumberFormatException e) {
            }

            wireCenters =
                aMasterTable.getWireCenter(
                    aTN.getNpa(),
                    aTN.getNxx(),
                    line,
                    dbConn);

        } catch (SQLException sqlEx) {
        	
        	if ( sqlEx.getMessage().indexOf( "No wire center rows found" ) > -1 )
        	{
        		if( wireCenters == null ) {
        			wireCenters = new ArrayList();
                }
                if( wireCenters.size() > 0 ) {
                	wireCenters.clear();
                }
                wireCenters.add( new String( "NoDataFound" ) );
                return wireCenters;
        	}
        	else
        	{
        		theUtility.throwException(
				ExceptionCode.ERR_RM_WIRECENTER_NOT_FOUND,
				"Get Wire Center failed: "
					+ sqlEx.getErrorCode()
					+ sqlEx.getMessage(),
				originator,
				Severity.UnRecoverable);
        	}

        } catch (Exception ex) {

            theUtility.throwException(
                ExceptionCode.ERR_RM_DB_CONNECTION_FAILED,
                "DB Connection failed: ",
                originator,
                Severity.UnRecoverable);
        } finally {
        	
        	theUtility.log(
                    LogEventId.REMOTE_RETURN,
                    (String) theProperties.get("jdbcURL"),
                    (String) theProperties.get("jdbcURL"),
                    (String) theProperties.get("jdbcUSERID"),
                    "GWS::TnMasterTable.getWireCenterByTN()");

            try {

                dbConn.disconnect();

            } catch (Exception ex) {
            }

        }

        ArrayList dedup = new ArrayList();
        int wcs = 0;

        wirecenterloop : for (wcs = 0; wcs < wireCenters.size(); wcs++) {

            for (int j = 0; j < dedup.size(); j++) {
                if (dedup.get(j).equals((String) wireCenters.get(wcs)))
                    continue wirecenterloop;
            }

            dedup.add((String) wireCenters.get(wcs));

        }

        return dedup;

    }
}
