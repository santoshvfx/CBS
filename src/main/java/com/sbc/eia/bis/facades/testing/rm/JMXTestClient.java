// $Id: JMXTestClient.java,v 1.1 2008/07/11 20:55:07 hw7243 Exp $
package com.sbc.eia.bis.facades.testing.rm;

/*
 * Copyright Notice RESTRICTED - PROPRIETARY INFORMATION he information
 * herein is for use only by authorized employees of AT&T Services Inc. and
 * authorized Affiliates of AT&T Services, Inc., and is not for general
 * distribution within or outside the respective companies. Copying or
 * reporduction without prior written approval is prohibited.
 *
 * Copyright (c) 2008 AT&T Services, Inc. All rights reserved
 */
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import javax.management.AttributeList;
import javax.management.ObjectName;

import com.ibm.websphere.management.AdminClient;
import com.ibm.websphere.management.AdminClientFactory;
import com.ibm.websphere.management.Session;
import com.ibm.websphere.management.configservice.ConfigService;
import com.ibm.websphere.management.configservice.ConfigServiceHelper;
import com.ibm.websphere.management.configservice.ConfigServiceProxy;
import com.ibm.websphere.management.exception.ConnectorException;
import com.ibm.websphere.management.wlm.ClusterMemberData;

public class JMXTestClient
{
    private String method;
    private Properties data;
    private String host;
    private String port;
    private String mBeanIdentifier;
    private String homeJndi;
    private String soapRequestTimeOut;
    private String lookupType;
    private String lookupName;

    public void runJMXTestClient()
    {

        log("Running...");

        ApplicationServer[] appServers;
        if (lookupType.equalsIgnoreCase("cluster"))
        {
            try
            {
                appServers = getClusterMembers(lookupName, host, port,
                        soapRequestTimeOut);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return;
            }
        }
        else if (lookupType.equalsIgnoreCase("server"))
        {
            try
            {
                appServers = getServerByName(lookupName, host, port,
                        soapRequestTimeOut);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return;
            }
        }
        else
        {
            appServers = new ApplicationServer[]
            {
                new ApplicationServer(host, port)
            };
        }

        try
        {
            setAdminClients(appServers, soapRequestTimeOut);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        if (appServers.length < 1)
        {
            log("No application servers found.");
        }

        try
        {
            setMBeans(mBeanIdentifier, appServers);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        String signature[] =
        {
                "java.lang.String", "java.util.Properties"
        };

        Object params[] =
        {
                homeJndi, data
        };
        
        try
        {
            for (int j = 0; j < appServers.length; j++)
            {
                log("Invoking: " + method 
                        + " on " + appServers[j].asString());
                Object ret = appServers[j].getAdminClient().invoke(
                        appServers[j].getMBean(), method, params,
                        signature);
                log("Method " + method + "() has returned an object: "
                        + ret.getClass().getName());
                try
                {
                    log("Attempting to display the Properties:");
                    Enumeration en = ((Properties) ret).keys();
                    while (en.hasMoreElements())
                    {
                        String key = (String) en.nextElement();
                        log(key + ": "
                                + ((Properties) ret).getProperty(key));
                    }
                }
                catch (Exception e)
                {
                    throw new Exception("Unable to evaluate: "
                            + ret.getClass());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
    }

    private ApplicationServer[] getClusterMembers(String aLookupName,
            String aHost, String aPort, String aSoapRequestTimeOut)
            throws Exception
    {

        log("Getting cluster members for: <" + aLookupName + ">");
        AdminClient adminClient = getAdminClient(aHost, aPort,
                aSoapRequestTimeOut);

        ObjectName queryName = new ObjectName(
                "WebSphere:*,type=Cluster,name=" + aLookupName);
        Set s = adminClient.queryNames(queryName, null);
        ObjectName cluster = null;
        if (!s.isEmpty())
        {
            cluster = (ObjectName) s.iterator().next();
            log("Found Cluster: <" + cluster + ">");
        }
        else
        {
            throw new Exception("Cluster <" + aLookupName
                    + "> was not found");
        }
        ClusterMemberData[] members = (ClusterMemberData[]) adminClient
               .invoke(cluster, "getClusterMembers", null, null);

        ArrayList appServerArray = new ArrayList();
        for (int i = 0; i < members.length; i++)
        {
            appServerArray.add(new ApplicationServer("", "",
                    members[i].nodeName, members[i].memberName));
        }
        return setServerAttributes((ApplicationServer[]) appServerArray
                .toArray(new ApplicationServer[0]), adminClient);
       
    }

    private ApplicationServer[] getServerByName(String aLookupName,
            String aHost, String aPort, String aSoapRequestTimeOut)
            throws Exception
    {

        log("Getting server by name for: <" + aLookupName + ">");
        AdminClient adminClient = getAdminClient(aHost, aPort,
                aSoapRequestTimeOut);

        ObjectName queryName = new ObjectName(
                "WebSphere:*,type=Server,name=" + aLookupName);
        Set s = adminClient.queryNames(queryName, null);
        ObjectName applicationServer = null;
        if (!s.isEmpty())
        {
            applicationServer = (ObjectName) s.iterator().next();
            log("Found Application Server: <" + applicationServer + ">");
        }
        else
        {
            throw new Exception("Application Server <" + aLookupName
                    + "> was not found");
        }

        ArrayList appServerArray = new ArrayList();
        appServerArray.add(new ApplicationServer("", "", applicationServer
                .getKeyProperty("node"), applicationServer
                .getKeyProperty("name")));
        return setServerAttributes((ApplicationServer[]) appServerArray
                .toArray(new ApplicationServer[0]), adminClient);
    }

    private ApplicationServer[] setServerAttributes(
            ApplicationServer[] appServers, AdminClient anAdminClient)
            throws Exception
    {

        ObjectName applicationServerObject = null;
        ObjectName orbObject = null;
        ConfigService configService = new ConfigServiceProxy(anAdminClient);
        ObjectName patternObject = ConfigServiceHelper.createObjectName(
                null, "SOAPConnector");
        Session session = new Session();

        for (int i = 0; i < appServers.length; i++)
        {
            log("Find attributes for " + appServers[i].asString());
            applicationServerObject = configService.resolve(session,
                    "Node=" + appServers[i].getNodeName() + ":Server="
                            + appServers[i].getMemberName())[0];
            orbObject = configService.queryConfigObjects(session,
                    applicationServerObject, patternObject, null)[0];
            AttributeList address = (AttributeList) configService
                    .getAttribute(session, orbObject,
                            "SOAP_CONNECTOR_ADDRESS");
            appServers[i].setHost((String) ConfigServiceHelper
                    .getAttributeValue(address, "host"));
            appServers[i].setPort(String.valueOf(ConfigServiceHelper
                    .getAttributeValue(address, "port")));
        }
        return appServers;
    }

    private void setAdminClients(ApplicationServer[] appServers,
            String aSoapRequestTimeOut) throws Exception
    {
        for (int i = 0; i < appServers.length; i++)
        {
            log("Set admin client for " + appServers[i].asString());
            appServers[i].setAdminClient(getAdminClient(appServers[i]
                    .getHost(), appServers[i].getPort(),
                    aSoapRequestTimeOut));
        }
    }

    private void setMBeans(String anMBeanIdentifier,
            ApplicationServer[] appServers) throws Exception
    {

        Set s = null;

        ObjectName queryName = new ObjectName(mBeanIdentifier);
        for (int i = 0; i < appServers.length; i++)
        {
            log("Find MBean " + mBeanIdentifier + " for "
                    + appServers[i].asString());
            s = appServers[i].getAdminClient().queryNames(queryName, null);
            if (!s.isEmpty())
            {
                appServers[i].setMBean((ObjectName) s.iterator().next());
                log("Found MBean: <" + appServers[i].getMBean() + ">");
            }
            else
            {
                throw new Exception("MBean <" + anMBeanIdentifier
                        + "> was not found");
            }
        }
    }

    private AdminClient getAdminClient(String aHost, String aPort,
            String aSoapRequestTimeOut) throws ConnectorException
    {

        Properties connectProps = new Properties();
        connectProps.setProperty(AdminClient.CONNECTOR_TYPE,
                AdminClient.CONNECTOR_TYPE_SOAP);
        connectProps.setProperty(AdminClient.CONNECTOR_HOST, aHost);
        connectProps.setProperty(AdminClient.CONNECTOR_PORT, aPort);
        connectProps.setProperty(
                AdminClient.CONNECTOR_SOAP_REQUEST_TIMEOUT,
                aSoapRequestTimeOut);

        log("Creating Admin Client to:" + " "
                + connectProps.getProperty(AdminClient.CONNECTOR_HOST)
                + " "
                + connectProps.getProperty(AdminClient.CONNECTOR_PORT)
                + " (SOAP request time out: " + soapRequestTimeOut
                + " seconds)");
        return AdminClientFactory.createAdminClient(connectProps);
    }

    private void log(String message)
    {
        System.out.println("JMXTestClient: " + message);
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getMBeanIdentifier()
    {
        return mBeanIdentifier;
    }

    public void setMBeanIdentifier(String beanIdentifier)
    {
        mBeanIdentifier = beanIdentifier;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public Properties getData()
    {
        return data;
    }

    public void setData(Properties data)
    {
        this.data = data;
    }

    public String getHomeJndi()
    {
        return homeJndi;
    }

    public void setHomeJndi(String homeJndi)
    {
        this.homeJndi = homeJndi;
    }

    public String getSoapRequestTimeOut()
    {
        return soapRequestTimeOut;
    }

    public void setSoapRequestTimeOut(String soapRequestTimeOut)
    {
        this.soapRequestTimeOut = soapRequestTimeOut;
    }

    public String getLookupName()
    {
        return lookupName;
    }

    public void setLookupName(String lookupName)
    {
        this.lookupName = lookupName;
    }

    public String getLookupType()
    {
        return lookupType;
    }

    public void setLookupType(String lookupType)
    {
        this.lookupType = lookupType;
    }
}
