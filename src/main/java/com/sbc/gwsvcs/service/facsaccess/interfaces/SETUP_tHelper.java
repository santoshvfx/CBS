package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SETUP_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private SETUP_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.SETUP_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.SETUP_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.SETUP_t();
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.WC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PRIORI = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.EMP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.EWO = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PAGE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA4 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR4 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR4 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA4 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR4 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR4 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA5 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR5 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR5 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA5 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR5 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR5 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA6 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR6 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR6 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA6 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR6 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR6 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA7 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR7 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR7 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA7 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR7 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR7 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA8 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR8 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR8 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA8 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR8 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR8 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA9 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR9 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR9 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA9 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR9 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR9 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA10 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR10 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR10 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA10 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR10 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR10 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA11 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR11 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR11 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA11 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR11 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR11 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA12 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR12 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR12 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA12 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR12 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR12 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA13 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR13 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR13 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA13 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR13 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR13 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA14 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR14 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR14 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA14 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR14 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR14 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRCA15 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRLPR15 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRHPR15 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOCA15 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOLPR15 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TOHPR15 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PRINT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DESTIN = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[79];
			i.read_octet_array (_bytes, 0, 79);
			int _j;
			for (_j = 0; _j < 79; _j++)
				if (_bytes[_j] == 0)
					break;
			value.INFO = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.SETUP_t value) { 
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.TID.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.WC.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.PRIORI.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.EMP.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.EWO.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.TR.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.DD.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.PAGE.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA2.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR2.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR2.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA2.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR2.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR2.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA3.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR3.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR3.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA3.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR3.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR3.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA4.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR4.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR4.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA4.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR4.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR4.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA5.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR5.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR5.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA5.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR5.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR5.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA6.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR6.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR6.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA6.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR6.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR6.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA7.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR7.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR7.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA7.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR7.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR7.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA8.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR8.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR8.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA8.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR8.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR8.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA9.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR9.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR9.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA9.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR9.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR9.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA10.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR10.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR10.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA10.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR10.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR10.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA11.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR11.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR11.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA11.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR11.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR11.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA12.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR12.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR12.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA12.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR12.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR12.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA13.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR13.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR13.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA13.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR13.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR13.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA14.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR14.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR14.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA14.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR14.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR14.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.FRCA15.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRLPR15.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FRHPR15.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TOCA15.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOLPR15.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TOHPR15.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.PRINT.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.DESTIN.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[79];
			byte[] _bytes_src = value.INFO.getBytes();
			int _j;
			for (_j = 0; _j < 79 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 79);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.SETUP_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.SETUP_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[101];
				members[0] = new StructMember();
				members[0].name = "TID";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (7, members[0].type);
				members[1] = new StructMember();
				members[1].name = "WC";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (9, members[1].type);
				members[2] = new StructMember();
				members[2].name = "PRIORI";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (3, members[2].type);
				members[3] = new StructMember();
				members[3].name = "EMP";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (8, members[3].type);
				members[4] = new StructMember();
				members[4].name = "EWO";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (13, members[4].type);
				members[5] = new StructMember();
				members[5].name = "TR";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (8, members[5].type);
				members[6] = new StructMember();
				members[6].name = "DD";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (9, members[6].type);
				members[7] = new StructMember();
				members[7].name = "PAGE";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (4, members[7].type);
				members[8] = new StructMember();
				members[8].name = "FRCA";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (11, members[8].type);
				members[9] = new StructMember();
				members[9].name = "FRLPR";
				members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[9].type = ORB.init().create_array_tc (5, members[9].type);
				members[10] = new StructMember();
				members[10].name = "FRHPR";
				members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[10].type = ORB.init().create_array_tc (5, members[10].type);
				members[11] = new StructMember();
				members[11].name = "TOCA";
				members[11].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[11].type = ORB.init().create_array_tc (11, members[11].type);
				members[12] = new StructMember();
				members[12].name = "TOLPR";
				members[12].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[12].type = ORB.init().create_array_tc (5, members[12].type);
				members[13] = new StructMember();
				members[13].name = "TOHPR";
				members[13].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[13].type = ORB.init().create_array_tc (5, members[13].type);
				members[14] = new StructMember();
				members[14].name = "FRCA2";
				members[14].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[14].type = ORB.init().create_array_tc (11, members[14].type);
				members[15] = new StructMember();
				members[15].name = "FRLPR2";
				members[15].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[15].type = ORB.init().create_array_tc (5, members[15].type);
				members[16] = new StructMember();
				members[16].name = "FRHPR2";
				members[16].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[16].type = ORB.init().create_array_tc (5, members[16].type);
				members[17] = new StructMember();
				members[17].name = "TOCA2";
				members[17].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[17].type = ORB.init().create_array_tc (11, members[17].type);
				members[18] = new StructMember();
				members[18].name = "TOLPR2";
				members[18].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[18].type = ORB.init().create_array_tc (5, members[18].type);
				members[19] = new StructMember();
				members[19].name = "TOHPR2";
				members[19].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[19].type = ORB.init().create_array_tc (5, members[19].type);
				members[20] = new StructMember();
				members[20].name = "FRCA3";
				members[20].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[20].type = ORB.init().create_array_tc (11, members[20].type);
				members[21] = new StructMember();
				members[21].name = "FRLPR3";
				members[21].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[21].type = ORB.init().create_array_tc (5, members[21].type);
				members[22] = new StructMember();
				members[22].name = "FRHPR3";
				members[22].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[22].type = ORB.init().create_array_tc (5, members[22].type);
				members[23] = new StructMember();
				members[23].name = "TOCA3";
				members[23].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[23].type = ORB.init().create_array_tc (11, members[23].type);
				members[24] = new StructMember();
				members[24].name = "TOLPR3";
				members[24].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[24].type = ORB.init().create_array_tc (5, members[24].type);
				members[25] = new StructMember();
				members[25].name = "TOHPR3";
				members[25].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[25].type = ORB.init().create_array_tc (5, members[25].type);
				members[26] = new StructMember();
				members[26].name = "FRCA4";
				members[26].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[26].type = ORB.init().create_array_tc (11, members[26].type);
				members[27] = new StructMember();
				members[27].name = "FRLPR4";
				members[27].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[27].type = ORB.init().create_array_tc (5, members[27].type);
				members[28] = new StructMember();
				members[28].name = "FRHPR4";
				members[28].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[28].type = ORB.init().create_array_tc (5, members[28].type);
				members[29] = new StructMember();
				members[29].name = "TOCA4";
				members[29].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[29].type = ORB.init().create_array_tc (11, members[29].type);
				members[30] = new StructMember();
				members[30].name = "TOLPR4";
				members[30].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[30].type = ORB.init().create_array_tc (5, members[30].type);
				members[31] = new StructMember();
				members[31].name = "TOHPR4";
				members[31].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[31].type = ORB.init().create_array_tc (5, members[31].type);
				members[32] = new StructMember();
				members[32].name = "FRCA5";
				members[32].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[32].type = ORB.init().create_array_tc (11, members[32].type);
				members[33] = new StructMember();
				members[33].name = "FRLPR5";
				members[33].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[33].type = ORB.init().create_array_tc (5, members[33].type);
				members[34] = new StructMember();
				members[34].name = "FRHPR5";
				members[34].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[34].type = ORB.init().create_array_tc (5, members[34].type);
				members[35] = new StructMember();
				members[35].name = "TOCA5";
				members[35].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[35].type = ORB.init().create_array_tc (11, members[35].type);
				members[36] = new StructMember();
				members[36].name = "TOLPR5";
				members[36].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[36].type = ORB.init().create_array_tc (5, members[36].type);
				members[37] = new StructMember();
				members[37].name = "TOHPR5";
				members[37].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[37].type = ORB.init().create_array_tc (5, members[37].type);
				members[38] = new StructMember();
				members[38].name = "FRCA6";
				members[38].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[38].type = ORB.init().create_array_tc (11, members[38].type);
				members[39] = new StructMember();
				members[39].name = "FRLPR6";
				members[39].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[39].type = ORB.init().create_array_tc (5, members[39].type);
				members[40] = new StructMember();
				members[40].name = "FRHPR6";
				members[40].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[40].type = ORB.init().create_array_tc (5, members[40].type);
				members[41] = new StructMember();
				members[41].name = "TOCA6";
				members[41].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[41].type = ORB.init().create_array_tc (11, members[41].type);
				members[42] = new StructMember();
				members[42].name = "TOLPR6";
				members[42].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[42].type = ORB.init().create_array_tc (5, members[42].type);
				members[43] = new StructMember();
				members[43].name = "TOHPR6";
				members[43].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[43].type = ORB.init().create_array_tc (5, members[43].type);
				members[44] = new StructMember();
				members[44].name = "FRCA7";
				members[44].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[44].type = ORB.init().create_array_tc (11, members[44].type);
				members[45] = new StructMember();
				members[45].name = "FRLPR7";
				members[45].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[45].type = ORB.init().create_array_tc (5, members[45].type);
				members[46] = new StructMember();
				members[46].name = "FRHPR7";
				members[46].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[46].type = ORB.init().create_array_tc (5, members[46].type);
				members[47] = new StructMember();
				members[47].name = "TOCA7";
				members[47].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[47].type = ORB.init().create_array_tc (11, members[47].type);
				members[48] = new StructMember();
				members[48].name = "TOLPR7";
				members[48].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[48].type = ORB.init().create_array_tc (5, members[48].type);
				members[49] = new StructMember();
				members[49].name = "TOHPR7";
				members[49].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[49].type = ORB.init().create_array_tc (5, members[49].type);
				members[50] = new StructMember();
				members[50].name = "FRCA8";
				members[50].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[50].type = ORB.init().create_array_tc (11, members[50].type);
				members[51] = new StructMember();
				members[51].name = "FRLPR8";
				members[51].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[51].type = ORB.init().create_array_tc (5, members[51].type);
				members[52] = new StructMember();
				members[52].name = "FRHPR8";
				members[52].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[52].type = ORB.init().create_array_tc (5, members[52].type);
				members[53] = new StructMember();
				members[53].name = "TOCA8";
				members[53].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[53].type = ORB.init().create_array_tc (11, members[53].type);
				members[54] = new StructMember();
				members[54].name = "TOLPR8";
				members[54].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[54].type = ORB.init().create_array_tc (5, members[54].type);
				members[55] = new StructMember();
				members[55].name = "TOHPR8";
				members[55].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[55].type = ORB.init().create_array_tc (5, members[55].type);
				members[56] = new StructMember();
				members[56].name = "FRCA9";
				members[56].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[56].type = ORB.init().create_array_tc (11, members[56].type);
				members[57] = new StructMember();
				members[57].name = "FRLPR9";
				members[57].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[57].type = ORB.init().create_array_tc (5, members[57].type);
				members[58] = new StructMember();
				members[58].name = "FRHPR9";
				members[58].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[58].type = ORB.init().create_array_tc (5, members[58].type);
				members[59] = new StructMember();
				members[59].name = "TOCA9";
				members[59].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[59].type = ORB.init().create_array_tc (11, members[59].type);
				members[60] = new StructMember();
				members[60].name = "TOLPR9";
				members[60].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[60].type = ORB.init().create_array_tc (5, members[60].type);
				members[61] = new StructMember();
				members[61].name = "TOHPR9";
				members[61].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[61].type = ORB.init().create_array_tc (5, members[61].type);
				members[62] = new StructMember();
				members[62].name = "FRCA10";
				members[62].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[62].type = ORB.init().create_array_tc (11, members[62].type);
				members[63] = new StructMember();
				members[63].name = "FRLPR10";
				members[63].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[63].type = ORB.init().create_array_tc (5, members[63].type);
				members[64] = new StructMember();
				members[64].name = "FRHPR10";
				members[64].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[64].type = ORB.init().create_array_tc (5, members[64].type);
				members[65] = new StructMember();
				members[65].name = "TOCA10";
				members[65].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[65].type = ORB.init().create_array_tc (11, members[65].type);
				members[66] = new StructMember();
				members[66].name = "TOLPR10";
				members[66].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[66].type = ORB.init().create_array_tc (5, members[66].type);
				members[67] = new StructMember();
				members[67].name = "TOHPR10";
				members[67].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[67].type = ORB.init().create_array_tc (5, members[67].type);
				members[68] = new StructMember();
				members[68].name = "FRCA11";
				members[68].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[68].type = ORB.init().create_array_tc (11, members[68].type);
				members[69] = new StructMember();
				members[69].name = "FRLPR11";
				members[69].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[69].type = ORB.init().create_array_tc (5, members[69].type);
				members[70] = new StructMember();
				members[70].name = "FRHPR11";
				members[70].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[70].type = ORB.init().create_array_tc (5, members[70].type);
				members[71] = new StructMember();
				members[71].name = "TOCA11";
				members[71].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[71].type = ORB.init().create_array_tc (11, members[71].type);
				members[72] = new StructMember();
				members[72].name = "TOLPR11";
				members[72].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[72].type = ORB.init().create_array_tc (5, members[72].type);
				members[73] = new StructMember();
				members[73].name = "TOHPR11";
				members[73].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[73].type = ORB.init().create_array_tc (5, members[73].type);
				members[74] = new StructMember();
				members[74].name = "FRCA12";
				members[74].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[74].type = ORB.init().create_array_tc (11, members[74].type);
				members[75] = new StructMember();
				members[75].name = "FRLPR12";
				members[75].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[75].type = ORB.init().create_array_tc (5, members[75].type);
				members[76] = new StructMember();
				members[76].name = "FRHPR12";
				members[76].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[76].type = ORB.init().create_array_tc (5, members[76].type);
				members[77] = new StructMember();
				members[77].name = "TOCA12";
				members[77].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[77].type = ORB.init().create_array_tc (11, members[77].type);
				members[78] = new StructMember();
				members[78].name = "TOLPR12";
				members[78].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[78].type = ORB.init().create_array_tc (5, members[78].type);
				members[79] = new StructMember();
				members[79].name = "TOHPR12";
				members[79].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[79].type = ORB.init().create_array_tc (5, members[79].type);
				members[80] = new StructMember();
				members[80].name = "FRCA13";
				members[80].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[80].type = ORB.init().create_array_tc (11, members[80].type);
				members[81] = new StructMember();
				members[81].name = "FRLPR13";
				members[81].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[81].type = ORB.init().create_array_tc (5, members[81].type);
				members[82] = new StructMember();
				members[82].name = "FRHPR13";
				members[82].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[82].type = ORB.init().create_array_tc (5, members[82].type);
				members[83] = new StructMember();
				members[83].name = "TOCA13";
				members[83].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[83].type = ORB.init().create_array_tc (11, members[83].type);
				members[84] = new StructMember();
				members[84].name = "TOLPR13";
				members[84].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[84].type = ORB.init().create_array_tc (5, members[84].type);
				members[85] = new StructMember();
				members[85].name = "TOHPR13";
				members[85].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[85].type = ORB.init().create_array_tc (5, members[85].type);
				members[86] = new StructMember();
				members[86].name = "FRCA14";
				members[86].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[86].type = ORB.init().create_array_tc (11, members[86].type);
				members[87] = new StructMember();
				members[87].name = "FRLPR14";
				members[87].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[87].type = ORB.init().create_array_tc (5, members[87].type);
				members[88] = new StructMember();
				members[88].name = "FRHPR14";
				members[88].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[88].type = ORB.init().create_array_tc (5, members[88].type);
				members[89] = new StructMember();
				members[89].name = "TOCA14";
				members[89].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[89].type = ORB.init().create_array_tc (11, members[89].type);
				members[90] = new StructMember();
				members[90].name = "TOLPR14";
				members[90].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[90].type = ORB.init().create_array_tc (5, members[90].type);
				members[91] = new StructMember();
				members[91].name = "TOHPR14";
				members[91].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[91].type = ORB.init().create_array_tc (5, members[91].type);
				members[92] = new StructMember();
				members[92].name = "FRCA15";
				members[92].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[92].type = ORB.init().create_array_tc (11, members[92].type);
				members[93] = new StructMember();
				members[93].name = "FRLPR15";
				members[93].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[93].type = ORB.init().create_array_tc (5, members[93].type);
				members[94] = new StructMember();
				members[94].name = "FRHPR15";
				members[94].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[94].type = ORB.init().create_array_tc (5, members[94].type);
				members[95] = new StructMember();
				members[95].name = "TOCA15";
				members[95].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[95].type = ORB.init().create_array_tc (11, members[95].type);
				members[96] = new StructMember();
				members[96].name = "TOLPR15";
				members[96].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[96].type = ORB.init().create_array_tc (5, members[96].type);
				members[97] = new StructMember();
				members[97].name = "TOHPR15";
				members[97].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[97].type = ORB.init().create_array_tc (5, members[97].type);
				members[98] = new StructMember();
				members[98].name = "PRINT";
				members[98].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[98].type = ORB.init().create_array_tc (2, members[98].type);
				members[99] = new StructMember();
				members[99].name = "DESTIN";
				members[99].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[99].type = ORB.init().create_array_tc (9, members[99].type);
				members[100] = new StructMember();
				members[100].name = "INFO";
				members[100].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[100].type = ORB.init().create_array_tc (79, members[100].type);
				myTc = ORB.init().create_struct_tc (id(), "SETUP_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com.sbc.gwsvcs.service.facsaccess.interfaces/SETUP_t:1.0"; } 
}
