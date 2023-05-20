// $Id: RmHome.java,v 1.1 2002/09/29 03:13:35 dm2328 Exp $

package com.sbc.eia.bis.facades.rm.ejb;

/**
 * This is a Home interface for the Session Bean
 */
public interface RmHome extends javax.ejb.EJBHome {

/**
 * create method for a session bean
 * @return com.sbc.eia.bis.facades.rm.ejb.Rm
 * @exception javax.ejb.CreateException The exception description.
 * @exception java.rmi.RemoteException The exception description.
 */
com.sbc.eia.bis.facades.rm.ejb.Rm create() throws javax.ejb.CreateException, java.rmi.RemoteException;
}
