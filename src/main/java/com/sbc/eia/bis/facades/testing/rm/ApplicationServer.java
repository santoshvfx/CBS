// $Id: ApplicationServer.java,v 1.1 2008/07/11 20:55:07 hw7243 Exp $
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
import javax.management.ObjectName;

import com.ibm.websphere.management.AdminClient;

public class ApplicationServer
{
    private String host;
    private String port;
    private String nodeName;
    private String memberName;
    private AdminClient adminClient;
    private ObjectName mBean;

    public ApplicationServer(String aHost, String aPort)
    {
        this.host = aHost;
        this.port = aPort;
        this.nodeName = "[unknown]";
        this.memberName = "[unknown]";
    }

    public ApplicationServer(String aHost, String aPort, String aNodeName,
            String aMemberName)
    {
        this.host = aHost;
        this.port = aPort;
        this.nodeName = aNodeName;
        this.memberName = aMemberName;
    }

    public String asString()
    {
        return "Application Server:" + "<name:" + memberName + ">"
                + "<host:" + host + ">" + "<port:" + port + ">" + "<node:"
                + nodeName + ">";

    }

    public AdminClient getAdminClient()
    {
        return adminClient;
    }

    public void setAdminClient(AdminClient adminClient)
    {
        this.adminClient = adminClient;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public ObjectName getMBean()
    {
        return mBean;
    }

    public void setMBean(ObjectName bean)
    {
        mBean = bean;
    }

    public String getMemberName()
    {
        return memberName;
    }

    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }

    public String getNodeName()
    {
        return nodeName;
    }

    public void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }
}
