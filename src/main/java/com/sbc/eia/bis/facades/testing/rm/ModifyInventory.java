//$Id: ModifyInventory.java,v 1.2 2007/09/18 17:14:34 rd2842 Exp $
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
//# Date         | Author              | Notes
//# ----------------------------------------------------------------------------
//# 09/18/2007    Rene Duka              Creation.

package com.sbc.eia.bis.facades.testing.rm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.eia.bis.facades.testing.TestClient;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

/**
 * @author rd2842
 */
public class ModifyInventory extends TestRMCaseBase {

    private Hashtable props = null;     
    private String aRequestMSG = null;      

    /**
     * Constructor for ModifyInventory.
     */
    public ModifyInventory() {
        super();
        setMyMethodName("ModifyInventory");
    }

    /**
     * method: displayInput()
     */
    protected void displayInput() {
        TestClient.log.printLog("Request MSG :" + aRequestMSG);
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#displayProxyResult()
     */
    protected void displayProxyResult() {
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#displayResult()
     */
    protected void displayResult() {
    }

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

        TestClient.log.printLog("Remote Session Bean testing Not Implemented.");
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
    protected void init(String paramList) {

        // parse the argument from the test case
        StringTokenizer st = new StringTokenizer(paramList, TestClient.DEFAULT_DELIMITER);
        try {
            while (st.hasMoreElements()) {
                String tag = TestClient.nextToken(st) ;
                System.out.println("Next Tag => " + tag);
                if (tag.equals("TXT")) {
                    String aTxtFileName = TestClient.nextToken(st) ;
                    TestClient.log.printLog("Test Data File :" + aTxtFileName);
                    aRequestMSG = readFile(aTxtFileName);
                }
                if (aRequestMSG == null)
                    throw new Exception("Request MSG is not available.");
            }
        } 
        catch (Throwable t) {
            //Notify user of error
            System.out.println("Verify test data, failure reading test data");
            t.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String, java.lang.String)
     */
    protected void init(String paramList, String propertiesFile) {

        // parse the argument from the test case
        System.out.println("propertiesFile = " + propertiesFile);
    
        File file;
        FileInputStream fis = null;
    
        try  {
            file = new File(propertiesFile.trim());
            fis = new FileInputStream(file);
    
        }
        catch(Exception fe) { 
            System.out.println("Properties File Not Found: " + fe.getMessage() );
        }
    
        //get properties file
        Properties p = new Properties();
        try {
            p.load(fis);
        }
        catch(IOException ie) {
            System.out.println("IOException reading properties file." ) ;
        }
    
        // Set up properties for specific Bis
        this.props = (Hashtable)p;      
        init(paramList) ;
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initMessage(java.lang.String, java.util.Hashtable)
     */
    protected void initMessage(String paramList, Hashtable props) {
        init(paramList);
        displayInput();

        // send message to the queue
        try {
            // The file contains data in the form: Transaction : Property : Value
            String aJMSPropertiesFile = (String) props.get("RM_MDB_TEST_CLIENT_CONFIG_FILE_NAME");

            RMJmsOpsHelper aHelper = new RMJmsOpsHelper();
            
            aHelper.loadProperties(aJMSPropertiesFile, myMethodName);
            
            TestClient.log.printLog("HostJNDIName          ==> " + aHelper.getHostJNDIName().trim());
            TestClient.log.printLog("ConnectionFactoryName ==> " + aHelper.getConnectionFactoryName().trim());
            TestClient.log.printLog("RequestQueue          ==> " + aHelper.getRequestQueue().trim());
            
            aHelper.sendMessage(aHelper.getHostJNDIName().trim(),
                                (String) props.get("USER_NAME"),
                                (new Encryption()).decodePassword((String) props.get("OSS_PUBLIC_KEY"), (String) props.get("PASSWORD")),
                                aHelper.getConnectionFactoryName().trim(),
                                aHelper.getRequestQueue().trim(),
                                "",         
                                45000,
                                aRequestMSG);

            TestClient.log.printLog("Message sent to Queue without error");

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String, java.util.Hashtable)
     */
    protected void init(java.lang.String paramList, Hashtable props) {
        TestClient.log.printLog("Vicuna proxy support not implemented");
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String, java.util.Properties)
     */
    protected void initSoapMessage(String paramList, Properties aProps) {
        TestClient.log.printLog("SOAFI proxy support not implemented");
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapHttpMessage(java.lang.String, java.util.Properties)
     */
    protected void initSoapHttpMessage(String ParamList, Properties props) {
        TestClient.log.printLog("SOAP/HTTP not implemented");
    }
}
