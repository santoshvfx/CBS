package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class EWO_Inq_Req_Blob_tHolder implements org.omg.CORBA.portable.Streamable { 
	public EWO_Inq_Req_Blob_t value;

	public EWO_Inq_Req_Blob_tHolder () {
	}
	public EWO_Inq_Req_Blob_tHolder (EWO_Inq_Req_Blob_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.EWO_Inq_Req_Blob_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.EWO_Inq_Req_Blob_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.EWO_Inq_Req_Blob_tHelper.type();
	} 
}
