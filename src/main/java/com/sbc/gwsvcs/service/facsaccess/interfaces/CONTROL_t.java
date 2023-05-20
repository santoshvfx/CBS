package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class CONTROL_t implements org.omg.CORBA.portable.IDLEntity { 
	public String TID;
	public String WC;
	public String EWO;
	public String TR;
	public String RCTR;
	public String EMP;
	public String PRIORI;
	public String RCLST;
	public String RCBCT;
	public String RCBCF;
	public String RCLNMV;
	public String COSMOS;
	public String WORKSH;
	public String LSTWS;
	public String SORT;
	public String COPIES;
	public String DESTIN;
	public String LSTCOP;

	public CONTROL_t () {
	}
	public CONTROL_t (String TID, String WC, String EWO, String TR, String RCTR, String EMP, String PRIORI, String RCLST, String RCBCT, String RCBCF, String RCLNMV, String COSMOS, String WORKSH, String LSTWS, String SORT, String COPIES, String DESTIN, String LSTCOP) { 
		this.TID = TID;
		this.WC = WC;
		this.EWO = EWO;
		this.TR = TR;
		this.RCTR = RCTR;
		this.EMP = EMP;
		this.PRIORI = PRIORI;
		this.RCLST = RCLST;
		this.RCBCT = RCBCT;
		this.RCBCF = RCBCF;
		this.RCLNMV = RCLNMV;
		this.COSMOS = COSMOS;
		this.WORKSH = WORKSH;
		this.LSTWS = LSTWS;
		this.SORT = SORT;
		this.COPIES = COPIES;
		this.DESTIN = DESTIN;
		this.LSTCOP = LSTCOP;

	} 
}
