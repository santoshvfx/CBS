package com.sbc.eia.idl.lim.helpers;

import com.sbc.eia.idl.lim_types.Address;


public class AddressHandlerEGATEWAY extends AddressHandler{

	
/**
 * AddressHandlerEGATEWAY constructor.
 */
 public AddressHandlerEGATEWAY() {
	super();
}
/**
 * AddressHandlerEGATEWAY constructor.
 * @param address Address
 * @exception AddressHandlerException
 */

public AddressHandlerEGATEWAY (Address address) 
throws AddressHandlerException
{
	super(address);
	
	
}


public String getAddressFormatType() {
	if ( !aAssignedHouseNumber.trim().equals("") && aHouseNumber.trim().equals("") ) 
		return "C";
	else if  (aAssignedHouseNumber.trim().equals("") && !aHouseNumber.trim().equals("") ) 
		return "";
	else
		return "?";
}


public String getHousNbr() {
	if ( !aHouseNumber.trim().equals("")  && aAssignedHouseNumber.trim().equals("")) 
		return aHouseNumber.trim();
	else if  (!aAssignedHouseNumber.trim().equals("") && aHouseNumber.trim().equals("") ) 
		return aAssignedHouseNumber.trim();
	else
		return "?";
	
}


				
			


	
	
}







