/*
 * Created on Jun 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.accessor;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.FloatedFidVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.LhFidVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SectionVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;

/**
 * @author ns3580
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SoacFcifDataAccessor
{

	private SoacServiceOrderVO ssovo;
	private SectionVO svo;
	private LhFidVO lhFidVo, lhFidForCaPrData, lhFidForBpTeaData;
	//private FloatedFidVO ffVo;
	private String facilityType;

	private static final String F1TYPE = "F1";
	private static final String F2TYPE = "F2";

	/**
	 * 
	 */
	public SoacFcifDataAccessor(SoacServiceOrderVO ssovo) throws DataAccessorException
	{
		super();
		
		if (ssovo == null)
		{
			throw new DataAccessorException("The SoacServiceOrderVO cannot be null");
		}
		
		this.ssovo = ssovo;

		// Retrieve the assignment section/
		svo = (SectionVO) this.ssovo.get("ASGM");

		// check whether the response if an F1 or F2 type
		if (svo != null)
		{
			checkFacilityType();
		}
	}

	/**
	 * This method checks and sets the facility type and also
	 * identifies the LhFidVO's for getting the CA, PR, BP, TEA data.
	 * The reason behind identifying the LhFidVO's is that to verify the facility type
	 * (F1 or F2) we have to search for certain floated fids (PGS, CUR).
	 * The LhFidVo that contains these floated fids is used for certain
	 *  information (BP, TEA) and the following LhFid
	 * is used for certain pieces of information (CA, PR). 
	 * @return
	 */
	public void checkFacilityType()
	{
		// TODO Auto-generated method stub
		int facilityNumber = ssovo.getMaxFacility();
		if (facilityNumber > 0)
		{
			if (facilityNumber == 1) // definitely an F1 case
			{
				setFacilityType(F1TYPE);
				lhFidForCaPrData =
					svo.getLhFidVo(ssovo.getActionIndicator() + "F1");
				lhFidForBpTeaData = lhFidForCaPrData;
			}
			
			/**
			 * if the max facility number is greater than 1 we have to check
			 * and see if the Port data starts with "LD".  If it does then
			 * it is F1 type. 
			 */
			else if (facilityNumber > 1)
			{
				//check for a non-null string with atleast two characters
				String portData = getPortData();
				if ((portData != null) && (portData.length() > 2))
				{
					if (portData.startsWith("LD"))
						// If it starts with "LD" then it is "F1"
					{
						setFacilityType(F1TYPE);
						lhFidForCaPrData =
							svo.getLhFidVo(ssovo.getActionIndicator() + "F1");
							
						// For an F1 case the BP, TEA, CA and PR information
						// are obtained from the same left handed FID, which is
						// either the "IF1" or "OF1" left handed fid
						lhFidForBpTeaData = lhFidForCaPrData;
					}
				}
				/**
				 * check for PGS and CUR floated fids if the max facility
				 * number of greater than 1 and if the Port data does not
				 * contain "LD" check for the PGS and CUR floated Fids.
				 * If those fids are found then identify the parent left hand fid
				 * since the BP and TEA information is in that left handed fid. 
				 */
				if ((searchForFloatedFidData("PGS") != null)
					|| (searchForFloatedFidData("CUR") != null))
				{
					// Get the facility number for the LhFid with the PGS and CUR
					// floated fids to obtain the BP and TEA data
					// The CA and PR data will be obtained from the
					// next higher facility.
					setFacilityType(F2TYPE);
					if (lhFidVo != null)
					{
						lhFidForBpTeaData = lhFidVo;
						// lhFidVo is where PGS and/or CUR are present

						int num = lhFidForBpTeaData.getFacilityNumber() + 1;
						lhFidForCaPrData =
							svo.getLhFidVo(
								ssovo.getActionIndicator() + "F" + num);
						// Should we throw an exception if lhFidForBpTeaData == null?
					}

				}

			}
		}
	}

	/**
	 * This method earches for a floated fid with the passed in name
	 * within the LhFids that make up the section.
	 * @param floatedFidName
	 * @return
	 */
	private String searchForFloatedFidData(String floatedFidName)
	{
		String data = null;
		LhFidVO[] lhfVoArray;
		FloatedFidVO floatedFid = null;
		if (svo != null)
		{
			lhfVoArray = svo.getAllLhFids();
			for (int i = 0; i < lhfVoArray.length; i++)
			{
				floatedFid = lhfVoArray[i].getFloatedFidVo(floatedFidName);
				// The getFloatedFidVo(...) method returns a null
				// if it does not find the FloatedFid with the argument name
				if (floatedFid != null)
				{
					// Set the lhFidVo so that it can be used
					// by the calling method for additional data.
					lhFidVo = lhfVoArray[i];
					//Retrieve the requested data
					data = floatedFid.getFidData();
				}
			}
		}
		return data;
	}

	/**
	 * Returns the cable "CA" data
	 * @return String
	 */
	public String getCableData()
	{
		String retString = null;
		if ((lhFidForCaPrData != null)
			&& (lhFidForCaPrData.getFloatedFidVo("CA") != null))
		{
			retString = lhFidForCaPrData.getFloatedFidVo("CA").getFidData();
		}

		return retString;
	}

	/**
	 * This method returns the "PR" information
	 * @return String
	 */
	public String getPairData()
	{
		String retString = null;
		if ((lhFidForCaPrData != null)
			&& (lhFidForCaPrData.getFloatedFidVo("PR") != null))
		{
			retString = lhFidForCaPrData.getFloatedFidVo("PR").getFidData();
		}

		return retString;
	}

	/**
	 * This methid gets and returns the "TEA" information
	 * @return String
	 */
	public String getTerminalIdData()
	{
		String retString = null;
		if ((lhFidForBpTeaData != null)
			&& (lhFidForBpTeaData.getFloatedFidVo("TEA") != null))
		{
			retString = lhFidForBpTeaData.getFloatedFidVo("TEA").getFidData();
		}

		return retString;
	}

    /**
     * This method returns the "OBP" data
     * @return String
     */
    public String getOutBindingPostData()
    {
        String retString = null;
        if ((lhFidForBpTeaData != null)
            && (lhFidForBpTeaData.getFloatedFidVo("OBP") != null))
        {
            retString = lhFidForBpTeaData.getFloatedFidVo("OBP").getFidData();
        }

        return retString;

    }

	/**
	 * This method returns the "BP" data
	 * @return String
	 */
	public String getBindingPostData()
	{
		String retString = null;
		if ((lhFidForBpTeaData != null)
			&& (lhFidForBpTeaData.getFloatedFidVo("BP") != null))
		{
			retString = lhFidForBpTeaData.getFloatedFidVo("BP").getFidData();
		}

		return retString;

	}

    /**
     * This method returns the "BP" data
     * @return String
     */
    public String getDefectivePrCa()
    {
        String pairNumber = null;
        pairNumber = searchForFloatedFidData("DEF");

        return pairNumber;

    }

	/**
	 * Return the error messages
	 * @return
	 */
	public String getErrorMessage()
	{
		return (String) ssovo.get(SoacServiceOrderConstants.ERR_MSG);
	}

	/**
	 * Returns the port data
	 * @return
	 */
	public String getPortData()
	{
		// Find the LhFid with the TRE Floated FID and get the data
		String portData = null;
		portData = searchForFloatedFidData("TRE");

		return portData;
	}

	/**
	 * This method retrieves and returns the TN fid data.
	 * @return
	 */
	public String getTelephoneNumber()
	{
		String answer = null;
		// CR13440 , only inward is concerned
		//String action = null; 
		
		//action = ssovo.getActionIndicator();
		
		answer = ssovo.getFloatedFidData("ASGM", "IOE", "TN");
		if (answer != null) return answer;
		answer = ssovo.getFloatedFidData("ASGM", "IROE", "TN");
		if (answer != null) return answer;
		answer = ssovo.getFloatedFidData("ASGM", "IBOE", "TN");
		if (answer != null) return answer;
		answer = ssovo.getFloatedFidData("ASGM", "IRBE", "TN");
		return answer;
	}

	public String getLsCircuitId()
	{
		String answer = null;
		LhFidVO g1Fid = null;
		// This private method can retrieve either the TN or CLS floated fids
		// from the G1 left handed fid
		// The CLS fid does not follow the floated Fid rules since it
		// does not have a preceeding "/" hence this kludge.
		// We have to remove the "CLS " token from the string.
		// The CLS FloatedFid can have a preceeding "/" if the floated
		// Fid TN is present before it.

		// This retrieval method is for the case if the
		// floatedFID TN/CLS is present
		if (svo != null)
		{
			g1Fid = svo.getLhFidVo("G1");
		}
		if (g1Fid != null)
		{
			if (g1Fid.getFidData().startsWith("CLS"))
			{
				answer = g1Fid.getFidData().substring(4); // because CLS + space equals 4 characters
			}
			else // handle it like a normal floated fid
				{
				answer = ssovo.getFloatedFidData("ASGM", "G1", "CLS");
			}
		}

		return answer;
	}

	/**
	 * @return
	 */
	public String getFacilityType()
	{
		return facilityType;
	}

	/**
	 * @param string
	 */
	private void setFacilityType(String string)
	{
		facilityType = string;
	}

}
