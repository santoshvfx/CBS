//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\valueobject\\LhFidVO.java

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LhFidVO
{
	private String fidData = null;
	private String fidName = null;
	private int quantity;
	
	private int facilityNumber;

	private String actionIndicator;

	private List floatedFidList = new ArrayList();

	/**
	 * @roseuid 4293809F03DA
	 */
	public LhFidVO(String name)
	{
		this.fidName = name;
	}



	/**
	 * This method returns an array of FloatedVO's with a given name.
	 * 
	 * @param fidName
	 * @return FloatedFidVO[]
	 */
	public FloatedFidVO getFloatedFidVo(String fidName)
	{
		FloatedFidVO vo = null;

		Iterator iter = floatedFidList.iterator();

		while (iter.hasNext())
		{
			vo = (FloatedFidVO) iter.next();
			if (vo.getFidName().equals(fidName))
			{
				break;
			}
			else
			{
				vo = null;
			}
		}
		
		return vo;

	}

	/**
	 * This method returna all the FloatedFidVO's that make up the LhFidVO
	 * 
	 * @return FloatedFidVO[]
	 */
	public FloatedFidVO[] getAllFloatedFids()
	{
		FloatedFidVO[] voArray = new FloatedFidVO[floatedFidList.size()];

		voArray = (FloatedFidVO[]) floatedFidList.toArray(voArray);
		return voArray;
	}

	public void addFloatedFidVo(FloatedFidVO argVo)
	{
		if (argVo != null)
		{
			floatedFidList.add(argVo);
		}
	}

	/**
	 * @return
	 */
	public String getFidData()
	{
		return fidData;
	}

	/**
	 * @return
	 */
	public String getFidName()
	{
		return fidName;
	}

	/**
	 * @param string
	 */
	public void setFidData(String fidData)
	{
			this.fidData = fidData;
	}

	/**
	 * @param string
	 */
	public void setFidName(String string)
	{
		fidName = string;
	}

	/**
	 * @return
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * @param i
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * @return
	 */
	public String getActionIndicator()
	{
		return actionIndicator;
	}

	/**
	 * @return
	 */
	public int getFacilityNumber()
	{
		return facilityNumber;
	}


	/**
	 * @param i
	 */
	public void setFacilityNumber(int i)
	{
		facilityNumber = i;
	}

}
