//$Id: Field.java,v 1.2 2011/04/07 03:06:11 rs278j Exp $
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
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenResponse.impl.JXAPITypeImpl.ProcessingActionTypeImpl.GetFieldsTypeImpl.FieldTypeImpl;
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
 * Contains the logic for handling the Field structure from the TIRKSJX response
 * 
 * @author js7440
 */
public class Field 
{	
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	private String fieldName = null;
	private int fieldInstance = 0;
	private String fieldValue = null;
	private String fieldEditable = null;
	private int fieldFocus = 0;
	private String fieldHighlight = null;
	private String fieldIsValueSelected = null;
	private Table [] fieldTable = null;
	
	/**
	 * Constructor for class Field
	 * 
	 * @param Utility aUtility
	 * @param Hashtable aProperties
	 */
	public Field(
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties)
	{
		utility = aUtility;
		properties = aProperties;
	}

	/**
	 * parse the Field
	 * 
	 * @param FieldTypeImpl fieldTypeImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public void parseField(
			FieldTypeImpl fieldTypeImpl) 
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		
		String myMethodName = "Field::parseField";
//		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		try
		{
			if(fieldTypeImpl.getName() != null)
			{
				setFieldName(fieldTypeImpl.getName());
			}
			if(fieldTypeImpl.getInstance() != null)
			{
				setFieldInstance(fieldTypeImpl.getInstance().intValue());
			}
			if(fieldTypeImpl.getValue() != null)
			{
				setFieldValue(fieldTypeImpl.getValue());
			}
			if(fieldTypeImpl.getEditable() != null)
			{
				setFieldEditable(fieldTypeImpl.getEditable());
			}
			if(fieldTypeImpl.getFocus() != null)
			{
				setFieldFocus(fieldTypeImpl.getFocus().intValue());
			}
			if(fieldTypeImpl.getHighlight() != null)
			{
				setFieldHighlight(fieldTypeImpl.getHighlight());
			}
			if(fieldTypeImpl.getIsValueSelected() != null)
			{
				setFieldIsValueSelected(fieldTypeImpl.getIsValueSelected());
			}
			if(fieldTypeImpl.getTable() != null)
			{
				ArrayList aTableArray = new ArrayList();
				int tableCount = fieldTypeImpl.getTable().size();
				
				if(tableCount > 0)
				{
					for (int i=0; i < tableCount; i++ )
					{
						TableTypeImpl aTableTypeImpl = (TableTypeImpl) fieldTypeImpl.getTable().get(i);
						Table aTable = new Table(utility, properties);
						aTable.parseTable(aTableTypeImpl);	
						aTableArray.add(aTable);
					}
				}
				if(aTableArray.size() > 0)
				{
					fieldTable = (Table[]) aTableArray.toArray(new Table[aTableArray.size()]);
				}
			}
//			utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in parsing Fields " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
	}

	/**
	 * get the Field Editable value	
	 * 
	 * @return String
	 */
	public String getFieldEditable() 
	{
		return fieldEditable;
	}

	/**
	 * set the Field Editable value	
	 * 
	 * @param String fieldEditable
	 */
	public void setFieldEditable(String fieldEditable) 
	{
		this.fieldEditable = fieldEditable;
	}

	/**
	 * get the Field Focus
	 * 
	 * @return int
	 */
	public int getFieldFocus() 
	{
		return fieldFocus;
	}

	/**
	 * set the Field Focus
	 * 
	 * @param int fieldFocus
	 */
	public void setFieldFocus(int fieldFocus) 
	{
		this.fieldFocus = fieldFocus;
	}

	/**
	 * get the Field Highlight
	 * 
	 * @return String
	 */
	public String getFieldHighlight() 
	{
		return fieldHighlight;
	}

	/**
	 * set the Field Highlight
	 * 
	 * @param String fieldHighlight
	 */
	public void setFieldHighlight(String fieldHighlight) 
	{
		this.fieldHighlight = fieldHighlight;
	}

	/**
	 * get the Field Instance
	 * 
	 * @return int
	 */
	public int getFieldInstance() 
	{
		return fieldInstance;
	}

	/**
	 * set the Field Instance
	 * 
	 * @param int fieldInstance
	 */
	public void setFieldInstance(int fieldInstance) 
	{
		this.fieldInstance = fieldInstance;
	}

	/**
	 * get the FieldIsValueSelected
	 * 
	 * @return String
	 */
	public String getFieldIsValueSelected() 
	{
		return fieldIsValueSelected;
	}

	/**
	 * set the FieldIsValueSelected
	 * 
	 * @param String fieldIsValueSelected
	 */
	public void setFieldIsValueSelected(String fieldIsValueSelected) 
	{
		this.fieldIsValueSelected = fieldIsValueSelected;
	}

	/**
	 * get the Field Name
	 * 
	 * @return String
	 */
	public String getFieldName() 
	{
		return fieldName;
	}

	/**
	 * set the Field Name
	 * 
	 * @param String fieldName
	 */
	public void setFieldName(String fieldName) 
	{
		this.fieldName = fieldName;
	}

	/**
	 * get the Table field
	 * 
	 * @return Table[]
	 */
	public Table[] getFieldTable() 
	{
		return fieldTable;
	}

	/**
	 * set the Table field
	 * 
	 * @param Table[] fieldTable
	 */
	public void setFieldTable(Table[] fieldTable) 
	{
		this.fieldTable = fieldTable;
	}

	/**
	 * get the Filed Value
	 * 
	 * @return String
	 */
	public String getFieldValue() 
	{
		return fieldValue;
	}

	/**
	 * set the Field value
	 * 
	 * @param String fieldValue
	 */
	public void setFieldValue(String fieldValue) 
	{
		this.fieldValue = fieldValue;
	}
}
