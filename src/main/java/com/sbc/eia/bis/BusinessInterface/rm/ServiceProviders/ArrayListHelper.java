// $Id: ArrayListHelper.java,v 1.1 2002/09/29 03:19:50 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders;

import java.util.*;
/**
 * Insert the type's description here.
 * Creation date: (5/7/01 4:33:02 PM)
 * @author: Sam Lok
 */
public final class ArrayListHelper {
/**
 * ArrayListHelper constructor comment.
 */
public ArrayListHelper() {
	super();
}
/**
 * Randomize the content of aArrayList.
 * Creation date: (5/15/00 2:25:16 PM)
 * @param aArrayList java.util.ArrayList
 */
public final static void randomize( ArrayList aArrayList ) 
{
	int randomSlot ;
	int vectorSize = aArrayList.size() ;
	Random randomizer = new Random() ;

	// run thru every element, and randomly move it somewhere else in the ArrayList
	//
	for ( int index = 0 ; index < vectorSize ; index++ )
	{
		Object curObj = null ;
		Object randomObj = null ;

		curObj = aArrayList.get( index ) ;			// save a copy of current object

		// pick a number from 0 to vectorSize-1
		randomSlot = (int)( randomizer.nextDouble() * ( vectorSize - 1 ) ) ;

		randomObj = aArrayList.get( randomSlot ) ;	// the object at the random location

		// then swap the current with the from object
		aArrayList.set( index, randomObj ) ;
		aArrayList.set( randomSlot, curObj ) ;
	}
}
}
