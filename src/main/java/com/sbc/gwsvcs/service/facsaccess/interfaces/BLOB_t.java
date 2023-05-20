package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class BLOB_t implements org.omg.CORBA.portable.IDLEntity { 
	public byte[] FCIF;
	public String TRUNCATED;

	public BLOB_t () {
	}
	public BLOB_t (byte[] FCIF, String TRUNCATED) { 
		this.FCIF = FCIF;
		this.TRUNCATED = TRUNCATED;

	} 
}
