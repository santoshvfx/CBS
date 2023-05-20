package com.att.lms.bis.service;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.eia.bis.facades.rm.ejb.RmBean;
import org.osjava.sj.loader.JndiLoader;

import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;

public class RmLegacyService extends RmBean {

  public RmLegacyService() throws Exception {
    String location = "";
    Properties props;

    try {
      props = new java.util.Properties();
      location = "rm.properties";

      if (location == null) {
        System.out.println("Throwing NamingException due to missing PROP_FILE_LOCATION" ) ;
        throw new NamingException("Property file name not found in environment");
      }
    }
    catch(NamingException ne){
      System.out.println("Throwing RemoteException due to Environment");
      throw new RemoteException("Error retrieving Property File Name::" + ne.getMessage());
    }

    try{
      //get properties file
      Properties p = PropertiesFileLoader.read( location, null );
      Encryption enc = new Encryption();
      p.put("jdbcPASSWORD", enc.decodePassword(p.getProperty("OSS_PUBLIC_KEY"), p.getProperty("jdbcPASSWORD")));
      this.PROPERTIES = (Hashtable)p ;
    }
    catch(IOException ie)
    {
      System.out.println("Throwing RemoteException due to IOException reading properties file." ) ;
      throw new RemoteException("Error Loading Properties::" + ie.getMessage());
    }
  }
}
