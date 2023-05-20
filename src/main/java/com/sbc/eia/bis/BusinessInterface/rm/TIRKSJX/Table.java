//$Id: Table.java,v 1.2 2011/04/07 03:08:51 rs278j Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX;

import java.util.ArrayList;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenResponse.impl.JXAPITypeImpl.ProcessingActionTypeImpl.GetFieldsTypeImpl.FieldTypeImpl.TableTypeImpl;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;

/**
 * Contains the logic for handling the Table field of the TIRKSJX response
 * 
 * @author js7440
 */
public class Table 
{

	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	private int columnCount = 0;
	private int rowCount = 0;
	private String [] rows = null;
	
	/**
	 * Constructor for class Table
	 * 
	 * @param Utility aUtility
	 * @param Hashtable aProperties
	 */
	public Table(
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties)
	{
		utility = aUtility;
		properties = aProperties;
	}
	
	/**
	 * parse the Table field
	 * 
	 * @param TableTypeImpl tableTypeImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public void parseTable(
			TableTypeImpl tableTypeImpl) 
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "Table::parseTable()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		try
		{
			if(tableTypeImpl.getColumnCount() != null)
			{
				setColumnCount(tableTypeImpl.getColumnCount().intValue());
			}
			if(tableTypeImpl.getRowCount() != null)
			{
				ArrayList aRowArray = new ArrayList();
				int rowCount = tableTypeImpl.getRowCount().intValue();
				for(int i=0; i<rowCount; i++)
				{
					String row = (String)tableTypeImpl.getRow().get(i);
					aRowArray.add(row);
				}
				if(aRowArray.size() > 0)
				{
					rows = (String []) aRowArray.toArray(new String[aRowArray.size()]);
				}
			}
			utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
		catch (Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in parseing Table " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
	}

	/**
	 * get the Column Count
	 * 
	 * @return int
	 */
	public int getColumnCount() 
	{
		return columnCount;
	}

	/**
	 * set the Column Count
	 * 
	 * @param int columnCount
	 */
	public void setColumnCount(int columnCount) 
	{
		this.columnCount = columnCount;
	}

	/**
	 * get the Row Count
	 * 
	 * @return int
	 */
	public int getRowCount() 
	{
		return rowCount;
	}

	/**
	 * set the Row Count
	 * 
	 * @param int rowCount
	 */
	public void setRowCount(int rowCount) 
	{
		this.rowCount = rowCount;
	}

	/**
	 * get the Table Rows
	 * 
	 * @return String[]
	 */
	public String[] getRows() 
	{
		return rows;
	}

	/**
	 * set the Table Rows
	 * 
	 * @param String[] rows
	 */
	public void setRows(String[] rows) 
	{
		this.rows = rows;
	}

}
