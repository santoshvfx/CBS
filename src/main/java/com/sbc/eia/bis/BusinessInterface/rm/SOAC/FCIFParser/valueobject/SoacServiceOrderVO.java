//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\valueobject\\SoacServiceOrderVO.java

/*
 * Created on May 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject;


public class SoacServiceOrderVO extends ServiceOrderVO 
{
	
	private int maxFacility = 0;
	private String actionIndicator;
  
   /**
    * @roseuid 42938060006F
    */
   public SoacServiceOrderVO() 
   {
		super();
		// TODO Auto-generated constructor stub    
   }
   

	/**
	 * @return
	 */
	public int getMaxFacility()
	{
		return maxFacility;
	}

	/**
	 * @param i
	 */
	public void setMaxFacility(int i)
	{
		maxFacility = i;
	}

	/**
	 * @return
	 */
	public String getActionIndicator()
	{
		return actionIndicator;
	}

    public String getLhFidData(String sectionName, String lhFidName)
    {
        return super.getLhFidData(sectionName, lhFidName);
    }

	/**
	 * @param c
	 */
	public void setActionIndicator(String actionIndicator)
	{
		this.actionIndicator = actionIndicator;
	}

}
