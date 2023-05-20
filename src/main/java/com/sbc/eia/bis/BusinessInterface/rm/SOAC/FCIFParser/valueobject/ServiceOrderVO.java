/*
 * Created on May 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author ns3580
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class ServiceOrderVO extends HashMap implements Serializable
{
	protected String name;
	private SectionVO svo;
	private LhFidVO lhfvo;
	private FloatedFidVO ffvo;
	
	private CVOIPServiceOrderVO[] VoipServiceOrders;
	
	
	/**
	 * 
	 */
	public ServiceOrderVO()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	public String getFloatedFidData(
		String sectionName,
		String lhFidName,
		String ffidName)
	{

		String ffData = null;
		if (this.containsKey(sectionName))
		{
			svo = (SectionVO) get(sectionName);
			{
				lhfvo = svo.getLhFidVo(lhFidName);
				if (lhfvo != null)
				{
					ffvo = lhfvo.getFloatedFidVo(ffidName);
					if (ffvo != null)
					{
						ffData = (String) ffvo.getFidData();
					}
				}
			}

		}

		return ffData;
	}

	public String getLhFidData(String sectionName, String lhFidName)
	{
		String lhfData = null;
		if (this.containsKey(sectionName))
		{
			svo = (SectionVO)this.get(sectionName);
			lhfvo = lhfvo = svo.getLhFidVo(lhFidName);
			lhfData = lhfvo.getFidData();
		}
		return lhfData;
	}
	
	public boolean getLhFidIndicator(String sectionName, String lhFidName)
	{
		String lhfData = null;
		boolean flag = false;
		if (this.containsKey(sectionName))
		{
			svo = (SectionVO)this.get(sectionName);
			lhfvo = lhfvo = svo.getLhFidVo(lhFidName);
			if ((lhfvo != null) && !("").equals(lhfvo))
			{
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * @return
	 */
	public CVOIPServiceOrderVO[] getVoipServiceOrders() {
		return VoipServiceOrders;
	}

	/**
	 * @param orderVOs
	 */
	public void setVoipServiceOrders(CVOIPServiceOrderVO[] orderVOs) {
		VoipServiceOrders = orderVOs;
	}

}
