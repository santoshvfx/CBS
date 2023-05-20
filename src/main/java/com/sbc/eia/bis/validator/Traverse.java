//$Id: Traverse.java,v 1.2 2006/08/03 01:34:06 ml2917 Exp $

//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 2/17/2006	Mark Liljequist		Creation

package com.sbc.eia.bis.validator;

import java.util.Stack;

import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

public class Traverse {

	private Utility utility = null;

	public Traverse(Utility aUtility) {

		super();
		utility = aUtility;

	}

	public int traverse(
		BisContext context,
		ScenarioMap scenarioMap,
		OperationManager operationManager,
		Object object,
		String key)
		throws ValidatorException {

		utility.log(LogEventId.DEBUG_LEVEL_2, ">traverse()");

		ValidationVisitor visitor = new ValidationVisitor(operationManager);
		// Only one key per visit.
		visitor.setKey(key);

		scenarioMap.iterator();

		while (scenarioMap.hasNext()) {

			// Set the top level object and key.

			visitor.setObject(object);

			VisitMap visitMap = scenarioMap.next();
			visitMap.iterator();

			DataVisit dataVisit = null;

			// Need to reset all the iterations.

			if (visitMap.hasArray()) {
				while (visitMap.hasNext()) {
					visitMap.next().setIteration(0);
				}
			}

			Stack visitStack = new Stack();

			int armageddon = 0;
			int visitIndex = 0;

			try {

				// Traverse the map.
				// On exception leave the loop.

				for (;;) {

					//					utility.log(
					//						LogEventId.DEBUG_LEVEL_2,
					//						"For loop: Stack size: "
					//							+ visitStack.size()
					//							+ " VisitIndex: "
					//							+ visitIndex
					//							+ " Map size: "
					//							+ visitMap.size());

					if (visitIndex >= visitMap.size())
						break;

					dataVisit =
						(DataVisit) visitMap.get(visitIndex).accept(visitor);

					startLoop(visitIndex, visitStack, dataVisit);

					visitIndex++;

					visitIndex =
						finishLoop(
							visitor,
							visitMap,
							visitIndex,
							visitStack,
							dataVisit);

				}

			} catch (DataVisitorException e) {
				throw new ValidatorException(
					e.getMessage(),
					e.getCode(),
					e.getExceptionRule());
			}
		}

		utility.log(LogEventId.DEBUG_LEVEL_2, "<traverse()");

		return 0;

	}

	private void startLoop(
		int visitIndex,
		Stack visitStack,
		DataVisit dataVisit) {

		// Check for array.
		if (dataVisit.isArray() == false) {
			return;
		}

		// Check if this is the first time.
		if (dataVisit.getIteration() == 0) {
			// Save the array object.
			visitStack.push(new Integer(visitIndex));
			//			utility.log(
			//				LogEventId.DEBUG_LEVEL_2,
			//				"Start iteration index "
			//					+ visitIndex
			//					+ " Stack size: "
			//					+ visitStack.size());
		}

	}

	private int finishLoop(
		ValidationVisitor visitor,
		VisitMap visitMap,
		int visitIndex,
		Stack visitStack,
		DataVisit dataVisit) {

		// Check end of list.
		if (visitIndex < visitMap.size()) {
			// Not end of list.
			return visitIndex;
		}

		// End of list.
		// Loop untill the stack is clear or the iteration returns an index. 

		for (;;) {

			// Check end of stack.
			if (visitStack.isEmpty()) {
				// End of stack.
				break;
			}

			// More visits on stack.
			Integer integer = (Integer) visitStack.pop();
			int index = integer.intValue();
			// Get the last visit put on the stack.
			dataVisit = visitMap.get(index);

			//			utility.log(
			//				LogEventId.DEBUG_LEVEL_2,
			//				"End loop: Index from visit poped: "
			//					+ index
			//					+ " VisitIndex: "
			//					+ visitIndex
			//					+ " Stack size: "
			//					+ visitStack.size()
			//					+ " Map size: "
			//					+ visitMap.size()
			//					+ " Visit array iteration: "
			//					+ dataVisit.getIteration()
			//					+ " Visit array length: "
			//					+ dataVisit.getLength());

			// Check for more iterations.
			if (dataVisit.getIteration() < (dataVisit.getLength() - 1)) {
				// More iterations.
				// Get the iteration.
				int iterations = dataVisit.getIteration();
				// Increment iteration on the visit.
				iterations++;
				dataVisit.setIteration(iterations);
				// push visit back on the stack with new iteration count.
				visitStack.push(new Integer(index));
				// set the visitor.
				visitor.setObject(dataVisit.getObject());
				// Reset the visitIndex.
				return index;
			} else {
				// Reset the iteration on the visit to zero.
				dataVisit.setIteration(0);
			}
		}

		// Finished last iteration.

		return visitIndex;

	}

}
