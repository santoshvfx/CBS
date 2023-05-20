//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\valueobject\\FloatedFidVO.java

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject;



public class FloatedFidVO
{
	private String fidName;
	private String fidData;

	/**
	 * @roseuid 429381C3004F
	 */
	public FloatedFidVO(String fidName, String fidData)
	{
		this.fidName = fidName;
		this.fidData = fidData;
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
	public void setFidData(String string)
	{
		fidData = string;
	}

	/**
	 * @param string
	 */
	public void setFidName(String string)
	{
		fidName = string;
	}

}
