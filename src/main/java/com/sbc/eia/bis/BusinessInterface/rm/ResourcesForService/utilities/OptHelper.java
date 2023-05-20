//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2001-2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author        		| Notes
//# ----------------------------------------------------------------------------
//# 9/27/2004   Jinmin Ni			Creation.
//# 10/01/204   Mark Liljequsit     Add for NetworkChannelCodeCombinationOpt.
//#			

package com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.utilities;
import com.sbc.eia.idl.rm_types.NetworkChannelCodeCombinationOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;

/**
 * @author ml2917
 *
 */

public class OptHelper
{

	/**
	 * Constructor for optHelper.
	 */

	private OptHelper()
	{
		super();
	}

	/**
	 * Method testOpt.
	 * @param stringOpt
	 * @param checkLength
	 * @return boolean
	 */

	public static boolean testOpt(StringOpt stringOpt, boolean checkLength)
	{

		if (!OptHelper.testOpt(stringOpt))
			return false;

		if (checkLength)
			if (stringOpt.theValue().trim().length() < 1)
				return false;

		return true;

	}

	/**
	 * Method testOpt.
	 * @param s
	 * @return boolean
	 */

	public static boolean testOpt(StringOpt stringOpt)
	{
		try
		{
			stringOpt.theValue();
			return true;

		} catch (org.omg.CORBA.BAD_OPERATION e)
		{
		} catch (NullPointerException e)
		{

		}

		return false;

	}

	/**
	 * Method testOpt.
	 * @param stringSeqOpt
	 * @return boolean
	 */
	public static boolean testOpt(StringSeqOpt stringSeqOpt)
	{
		try
		{
			stringSeqOpt.theValue();
			return true;

		} catch (org.omg.CORBA.BAD_OPERATION e)
		{
		} catch (NullPointerException e)
		{

		}

		return false;

	}

	/**
	 * Method testOpt.
	 * @param networkChannelCodeCombinationOpt
	 * @return boolean
	 */
	public static boolean testOpt(NetworkChannelCodeCombinationOpt networkChannelCodeCombinationOpt)
	{
		try
		{
			networkChannelCodeCombinationOpt.theValue();
			return true;

		} catch (org.omg.CORBA.BAD_OPERATION e)
		{
		} catch (NullPointerException e)
		{

		}

		return false;

	}

}
