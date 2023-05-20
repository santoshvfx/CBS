//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\valueobject\\SectionVO.java

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SectionVO
{
	private String sectionName;
	private char sectionSuffix;

	private List lhFidList = new ArrayList();
	private List defectFidList = new ArrayList();

	/**
	 * @roseuid 429380A0004F
	 */
	public SectionVO(String name)
	{
		this.sectionName = name;
	}

	public LhFidVO getLhFidVo(String fidName)
	{
		LhFidVO vo = null;

		Iterator iter = lhFidList.iterator();

		while (iter.hasNext())
		{
			vo = (LhFidVO) iter.next();
			if (vo.getFidName().equals(fidName))
			{
				return vo;
			}
		}

		return null;
	}

	public LhFidVO[] getAllLhFids()
	{
		LhFidVO[] voArray = new LhFidVO[lhFidList.size()];

		voArray = (LhFidVO[]) lhFidList.toArray(voArray);
		return voArray;
	}

	public void addLhFidVo(LhFidVO argVo)
	{
		if (argVo != null)
		{
			lhFidList.add(argVo);
		}
	}
	
	public void addDefectFidVo(DefectFidVO argVo)
		{
			if (argVo != null)
			{
				defectFidList.add(argVo);
			}
		}

	public DefectFidVO[] getAllDefectFids()
	{
		DefectFidVO[] defectFidArray = new DefectFidVO[defectFidList.size()];

		defectFidArray = (DefectFidVO[]) defectFidList.toArray(defectFidArray);
		return defectFidArray;
	}
	/**
	 * @return
	 */
	public String getSectionName()
	{
		// TODO Auto-generated method stub
		return sectionName;
	}
	/**
	 * @param string
	 */
	public void setSectionName(String string)
	{
		sectionName = string;
	}

	/**
	 * @return
	 */
	public char getSectionSuffix()
	{
		return sectionSuffix;
	}

	/**
	 * @param c
	 */
	public void setSectionSuffix(char sectionSuffix)
	{
		this.sectionSuffix = sectionSuffix;
	}

}
