package com.sbc.eia.bis.facades.rm.ejb;
/**
 * Local Home interface for Enterprise Bean: Rm
 */
public interface RmLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: Rm
	 */
	public com.sbc.eia.bis.facades.rm.ejb.RmLocal create()
		throws javax.ejb.CreateException;
}
