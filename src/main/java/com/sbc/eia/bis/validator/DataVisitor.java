//$Id: DataVisitor.java,v 1.1 2006/06/23 20:40:30 ml2917 Exp $

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
//# Date      	| Author              | Notes
//# --------------------------------------------------------------------
//# 2/17/2006	 Mark Liljequist		Creation

package com.sbc.eia.bis.validator;

public abstract class DataVisitor {

	protected DataVisitor() {
	}

	public abstract Object visitField(FieldVisit visitor)
		throws DataVisitorException;

	public abstract Object visitKey(KeyVisit visitor)
		throws DataVisitorException;

	public abstract Object visitMethod(MethodVisit visitor)
		throws DataVisitorException;

	public abstract Object visitOperation(OperationVisit visitor)
		throws DataVisitorException;

	public abstract Object visitArray(ArrayVisit visitor)
		throws DataVisitorException;

}
