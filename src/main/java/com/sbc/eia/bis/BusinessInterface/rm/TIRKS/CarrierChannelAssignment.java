// $Id: CarrierChannelAssignment.java,v 1.1 2002/09/29 03:12:23 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

/**
 * Contains carrier channel assignments information.
 * Creation date: (6/15/01 9:15:26 AM)
 * @author: Vickie Chui
 */
public class CarrierChannelAssignment {
	private String channel_pair = "";
	private String pend_act = "";
	private com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date_data = null;
	private String circuit_id = "";
	private String cur_act = "";
	public java.lang.String clo;
/**
 * ChannelAssignments constructor comment.
 */
public CarrierChannelAssignment() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (6/15/01 9:19:56 AM)
 * @return java.lang.String
 */
public java.lang.String getChannel_pair() {
	return channel_pair;
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/01 2:26:18 PM)
 * @return java.lang.String
 */
public java.lang.String getCircuit_id() {
	return circuit_id;
}
/**
 * Insert the method's description here.
 * Creation date: (7/9/01 10:58:22 AM)
 * @return java.lang.String
 */
public java.lang.String getClo() {
	return clo;
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/01 3:41:43 PM)
 * @return java.lang.String
 */
public java.lang.String getCur_act() {
	return cur_act;
}
/**
 * Insert the method's description here.
 * Creation date: (6/15/01 9:43:20 AM)
 * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t
 */
public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t getDue_date_data() {
	return due_date_data;
}
/**
 * Insert the method's description here.
 * Creation date: (6/15/01 9:21:42 AM)
 * @return java.lang.String
 */
public java.lang.String getPend_act() {
	return pend_act;
}
/**
 * Insert the method's description here.
 * Creation date: (6/15/01 9:19:56 AM)
 * @param newChannel_pair java.lang.String
 */
public void setChannel_pair(java.lang.String newChannel_pair) {
	channel_pair = newChannel_pair;
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/01 2:26:18 PM)
 * @param newCircuit_id java.lang.String
 */
public void setCircuit_id(java.lang.String newCircuit_id) {
	circuit_id = newCircuit_id;
}
/**
 * Insert the method's description here.
 * Creation date: (7/9/01 10:58:22 AM)
 * @param newClo java.lang.String
 */
public void setClo(java.lang.String newClo) {
	clo = newClo;
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/01 3:41:43 PM)
 * @param newCur_act java.lang.String
 */
public void setCur_act(java.lang.String newCur_act) {
	cur_act = newCur_act;
}
/**
 * Insert the method's description here.
 * Creation date: (6/15/01 9:43:20 AM)
 * @param newDue_date_data com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t
 */
public void setDue_date_data(com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t newDue_date_data) {
	due_date_data = newDue_date_data;
}
/**
 * Insert the method's description here.
 * Creation date: (6/15/01 9:21:42 AM)
 * @param newPend_acct java.lang.String
 */
public void setPend_act(java.lang.String newPend_act) {
	pend_act = newPend_act;
}
}
