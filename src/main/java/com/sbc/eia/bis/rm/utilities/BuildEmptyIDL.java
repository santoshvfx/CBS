// $Id: BuildEmptyIDL.java,v 1.5 2008/01/30 05:42:01 rd2842 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#     The information herein is for use only by authorized employees
//#     of SBC Services Inc. and authorized Affiliates of SBC Services,
//#     Inc., and is not for general distribution within or outside the
//#     respective companies.
//#     Copying or reproduction without prior written approval is prohibited.
//#
//#     © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------------------
//# 05/10/2005  Rene Duka       Creation.
//# 05/12/2005  Rene Duka       Added buildOrderAction method.
//# 05/16/2005  Rene Duka       Moved from BusinessInterface.createFacilityAssignment package.
//# 06/02/2005   jp2854         Added ServiceAreaInterface, buildEmptySingleFamilyUnit, buildEmptyNetworkInventoryInfo, buildEmptySplitter, buildEmptyFiberServingTerminal, buildEmptyMultipleDwellingUnit methods
//# 06/06/2005  Rene Duka       Modified buildFiberCableObject method.
//# 06/07/2005  Chaitanya       Changed to accomodate toOpt(String)-typecasting null to String  
//# 06/23/2005  Rene Duka       Changed buildFiberCableObject to buildEmptyFiberCableObject.
//#                             Changed buildOrderActionObject to buildEmptyOrderActionObject.
//# 11/08/2005  Chaitanya   	Made changes for IDLbundle 33.
//# 12/16/2005  Jyothi Pentyala Added method buildEmptyOpticalLineTerminal method.
//# 03/01/2006	Kavitha Kodali	Added buildEmptyProductSubscriptionObject(), buildEmptyCompositeObjectKeyOpt(), buildEmptyEiaDateOpt(), buildEmptyLocationOpt(), 
//#									buildEmptyLocationObject(), buildEmptyObjectPropertySeqOpt(), buildEmptyProductSubscriptionArrayObject()  methods.
//# 03/06/2006	Kavitha Kodali	Added buildEmptyFacilityEquipment(), buildEmptyFacilityEquipmentOpt() methods.
//# 04/10/2006	Kavitha Kodali	Added buildEmptyDSLAMTransportOpt(), buildEmptyOpticalLineTerminalOpt(), 
//#								buildEmptyOpticalNetworkTerminalObject(), buildEmptyOpticalNetworkTerminalOpt(), 
//#								buildEmptyCopperSegmentObject(), buildEmptyCopperSegmentArrayObject(), buildEmptyCopperSegmentSeqOpt(), 
//#								buildEmptyFiberSegmentObject(), buildEmptyFiberSegmentArrayObject(), buildEmptyFiberSegmentSeqOptObject(), 
//#								buildEmptyFttnObject(), buildEmptyFttpObject(), buildEmptyVideoHeadOfficeRouterObject(), 
//#								buildEmptyVideoHeadOfficeRouterArrayObject(), buildEmptyVideoHeadOfficeRouterSeqOpt().
//# 04/17/2006	Kavitha Kodali	Updated buildEmptyObjectPropertySeqOpt() method.
//# 05/02/2006	Kavitha Kodali	Passed null in all Opt methods to create empty Opt objects.
//# 05/03/2006	Mark Liljequist	Passed null in all Opt methods to create empty Opt objects.
//# 04/02/2007	Prasad Ganji	LS5 Code changes.FACSAccess is changed to FACSRCAccess and FTTNBP is added for this release.

package com.sbc.eia.bis.rm.utilities;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.idl.lim.helpers.ProviderLocationPropertyHandler;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.LocationOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.rm_ls_types.BillingAccount2SeqOpt;
import com.sbc.eia.idl.rm_ls_types.BillingAccountSeqOpt;
import com.sbc.eia.idl.rm_ls_types.CopperSegment;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentBP;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentBPSeqOpt;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentOpt;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentSeqOpt;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransport;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportBP;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportBPOpt;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.FacilityEquipment;
import com.sbc.eia.idl.rm_ls_types.FacilityEquipment2;
import com.sbc.eia.idl.rm_ls_types.FacilityEquipmentOpt;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2SeqOpt;
import com.sbc.eia.idl.rm_ls_types.FacilityLoopSeqOpt;
import com.sbc.eia.idl.rm_ls_types.FiberSegment;
import com.sbc.eia.idl.rm_ls_types.FiberSegmentSeqOpt;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.FttnBP;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.Loop;
import com.sbc.eia.idl.rm_ls_types.LoopSeqOpt;
import com.sbc.eia.idl.rm_ls_types.Network7450Switch;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminal;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminalOpt;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminal;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminalOpt;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.VideoHeadOfficeRouter;
import com.sbc.eia.idl.rm_ls_types.VideoHeadOfficeRouterSeqOpt;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.sm_ls_types.ProductSubscriptionSeqOpt;
import com.sbc.eia.idl.srm_ls_types.ActionExceptionGroupSeqOpt;
import com.sbc.eia.idl.srm_ls_types.ProductSubscriptionAction;
import com.sbc.eia.idl.srm_ls_types.ProductSubscriptionActionSeqOpt;
import com.sbc.eia.idl.srm_ls_types.ServiceRequest;
import com.sbc.eia.idl.srm_ls_types.ServiceRequestSeqOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKeyOpt;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.eia.idl.types.TimeOfDayIntervalOpt;
import com.sbc.eia.idl.types.TimeOpt;


public class BuildEmptyIDL {

	public static Network7450Switch buildEmptyNetwork7450SwitchObject() {
		return new Network7450Switch(
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null));
	}

	public static DSLAMTransport buildEmptyDSLAMObject() {
		return new DSLAMTransport(
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			buildEmptyLocationOpt(),
			(StringOpt) IDLUtil.toOpt((String) null),
			(BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
			(StringOpt) IDLUtil.toOpt((String) null));
	}

	public static DSLAMTransportBP buildEmptyDSLAMBPObject() {
		return new DSLAMTransportBP(
			(DSLAMTransportOpt) IDLUtil.toOpt(DSLAMTransportOpt.class,buildEmptyDSLAMObject()),
			buildEmptyStringOpt(),
			buildEmptyStringOpt(),
			buildEmptyStringOpt()
			);
	}

	public static DSLAMTransportOpt buildEmptyDSLAMTransportOpt() {
		return (
			(DSLAMTransportOpt) IDLUtil.toOpt(DSLAMTransportOpt.class, null));
	}

	public static DSLAMTransportBPOpt buildEmptyDSLAMTransportBPOpt() {
		return (
			(DSLAMTransportBPOpt) IDLUtil.toOpt(DSLAMTransportBPOpt.class, null));
	}

	public static OrderAction buildEmptyOrderActionObject() {
		return new OrderAction(
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null));
	}

	public static OpticalLineTerminal buildEmptyOpticalLineTerminalObject() {
		return new OpticalLineTerminal(
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			buildEmptyLocationOpt(),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			buildEmptyLocationOpt(),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null));
	}

	public static OpticalLineTerminalOpt buildEmptyOpticalLineTerminalOpt() {
		return (OpticalLineTerminalOpt) IDLUtil.toOpt(
			OpticalLineTerminalOpt.class,
			null);
	}

	public static OpticalNetworkTerminal buildEmptyOpticalNetworkTerminalObject() {
		return new OpticalNetworkTerminal(
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null));
	}

	public static OpticalNetworkTerminalOpt buildEmptyOpticalNetworkTerminalOpt() {
		return (OpticalNetworkTerminalOpt) IDLUtil.toOpt(
			OpticalNetworkTerminalOpt.class,
			null);
	}

	public static CopperSegment buildEmptyCopperSegmentObject() {
		return new CopperSegment(
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null));
	}

	public static CopperSegmentBP buildEmptyCopperSegmentBPObject() {
		return new CopperSegmentBP(
			(CopperSegmentOpt) IDLUtil.toOpt(CopperSegmentOpt.class,buildEmptyCopperSegmentObject()),
			buildEmptyStringOpt(),
			buildEmptyStringOpt(),
			buildEmptyStringOpt(),
			buildEmptyStringOpt(),
			buildEmptyStringOpt());
	}

	public static CopperSegment[] buildEmptyCopperSegmentArrayObject() {
		return new CopperSegment[] { buildEmptyCopperSegmentObject()};
	}

	public static CopperSegmentSeqOpt buildEmptyCopperSegmentSeqOpt() {
		return (CopperSegmentSeqOpt) IDLUtil.toOpt(
			CopperSegmentSeqOpt.class,
			null);
	}

	public static FiberSegment buildEmptyFiberSegmentObject() {
		return new FiberSegment(
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null));
	}

	public static FiberSegment[] buildEmptyFiberSegmentArrayObject() {
		return new FiberSegment[] { buildEmptyFiberSegmentObject()};
	}

	public static FiberSegmentSeqOpt buildEmptyFiberSegmentSeqOptObject() {
		return (FiberSegmentSeqOpt) IDLUtil.toOpt(
			FiberSegmentSeqOpt.class,
			null);
	}

	public static Fttn buildEmptyFttnObject() {
		return new Fttn(
			buildEmptyDSLAMTransportOpt(),
			buildEmptyCopperSegmentSeqOpt());
	}

	public static FttnBP buildEmptyFttnBPObject() {
		return new FttnBP(
			buildEmptyDSLAMTransportBPOpt(),
			(BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null),
			buildEmptyStringOpt(),
			buildEmptyServiceOrders(),
			buildEmptyLoops());
	}

	public static ServiceRequest buildEmptyServiceRequestObject() {
		return new ServiceRequest(
			buildEmptyStringOpt(),
			buildEmptyStringOpt(),
			buildEmptyProductSubscriptionActionSeqOptObject(),
			buildEmptyObjectPropertySeqOpt());
	}

	public static ServiceRequestSeqOpt buildEmptyServiceOrders() {
		return ((ServiceRequestSeqOpt) IDLUtil.toOpt(ServiceRequestSeqOpt.class, null));
	}

	public static LoopSeqOpt buildEmptyLoops() {
		return ((LoopSeqOpt) IDLUtil.toOpt(LoopSeqOpt.class, null));
	}

	public static Loop buildEmptyLoopObject() {
		return new Loop(
					buildEmptyStringSeqOpt(),
					buildEmptyStringOpt(),
					buildEmptyStringOpt(),
					buildEmptyStringOpt(),
					buildEmptyStringOpt(),
					buildEmptyCopperSegmentBPSeqOpt());
	}

	public static ProductSubscriptionActionSeqOpt buildEmptyProductSubscriptionActionSeqOptObject() {
		return ((ProductSubscriptionActionSeqOpt) IDLUtil.toOpt(ProductSubscriptionActionSeqOpt.class, null));
	}

	public static ProductSubscriptionAction buildEmptyProductSubscriptionActionObject() {
		return new ProductSubscriptionAction(
					buildEmptyStringOpt(),
					buildEmptyStringOpt(),
					buildEmptyStringOpt(),
					buildEmptyStringOpt(),
					buildEmptyStringOpt(),
					buildEmptyStringOpt(),
					buildEmptyTimeOpt(),
					buildEmptyEiaDateOpt(),
					buildEmptyTimeOfDayIntervalOpt(),
					buildEmptyTimeOpt(),
					buildEmptyProductSubscriptionSeqOpt(),
					buildEmptyObjectPropertySeqOpt(),
					buildEmptyActionExceptionGroupSeqOpt()
		);
	}

	public static Fttp buildEmptyFttpObject() {
		return new Fttp(
			buildEmptyOpticalNetworkTerminalOpt(),
			buildEmptyOpticalLineTerminalOpt(),
			buildEmptyFiberSegmentSeqOptObject());
	}

	public static ProductSubscription buildEmptyProductSubscriptionObject() {
		return new ProductSubscription(
			new ObjectKey("", ""),
			"",
			(StringOpt) IDLUtil.toOpt((String) null),
			buildEmptyCompositeObjectKeyOpt(),
			(StringOpt) IDLUtil.toOpt((String) null),
			buildEmptyEiaDateOpt(),
			(StringOpt) IDLUtil.toOpt((String) null),
			new ProductSubscription[0],
			buildEmptyLocationOpt(),
			buildEmptyLocationOpt(),
			buildEmptyObjectPropertySeqOpt());
	}

	public static Location buildEmptyLocationObject() {
		ProviderLocationProperty aProviderLocationProperties[] =
			{
				ProviderLocationPropertyHandler
					.getDefaultProviderLocationProperty()};

		return new Location(
			(StringOpt) IDLUtil.toOpt((String) null),
			aProviderLocationProperties);
	}

	public static CompositeObjectKeyOpt buildEmptyCompositeObjectKeyOpt() {
		return (
			(CompositeObjectKeyOpt) IDLUtil.toOpt(
				CompositeObjectKeyOpt.class,
				null));
	}

	public static StringOpt buildEmptyStringOpt() {
		return ((StringOpt) IDLUtil.toOpt((String) null));
	}

	public static CopperSegmentBPSeqOpt buildEmptyCopperSegmentBPSeqOpt() {
		return ((CopperSegmentBPSeqOpt) IDLUtil.toOpt(CopperSegmentBPSeqOpt.class, null));
	}

	public static StringSeqOpt buildEmptyStringSeqOpt() {
		return ((StringSeqOpt) IDLUtil.toOpt(StringSeqOpt.class, null));
	}

	public static ProductSubscriptionSeqOpt buildEmptyProductSubscriptionSeqOpt() {
		return ((ProductSubscriptionSeqOpt) IDLUtil.toOpt(ProductSubscriptionSeqOpt.class, null));
	}

	public static ActionExceptionGroupSeqOpt buildEmptyActionExceptionGroupSeqOpt() {
		return ((ActionExceptionGroupSeqOpt) IDLUtil.toOpt(ActionExceptionGroupSeqOpt.class, null));
	}
	
	public static TimeOfDayIntervalOpt buildEmptyTimeOfDayIntervalOpt() {
		return ((TimeOfDayIntervalOpt) IDLUtil.toOpt(TimeOfDayIntervalOpt.class, null));
	}

	public static TimeOpt buildEmptyTimeOpt() {
		return ((TimeOpt) IDLUtil.toOpt(TimeOpt.class, null));
	}

	public static EiaDateOpt buildEmptyEiaDateOpt() {
		return ((EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, null));
	}

	public static LocationOpt buildEmptyLocationOpt() {
		return ((LocationOpt) IDLUtil.toOpt(LocationOpt.class, null));
	}

	public static ObjectPropertySeqOpt buildEmptyObjectPropertySeqOpt() {
		return (
			(ObjectPropertySeqOpt) IDLUtil.toOpt(
				ObjectPropertySeqOpt.class,
				null));
	}

	public static ProductSubscription[] buildEmptyProductSubscriptionArrayObject() {
		return new ProductSubscription[] {
			 new ProductSubscription(
				new ObjectKey("", ""),
				"",
				((StringOpt) IDLUtil.toOpt((String) null)),
				buildEmptyCompositeObjectKeyOpt(),
				((StringOpt) IDLUtil.toOpt((String) null)),
				buildEmptyEiaDateOpt(),
				((StringOpt) IDLUtil.toOpt((String) null)),
				new ProductSubscription[0],
				buildEmptyLocationOpt(),
				buildEmptyLocationOpt(),
				buildEmptyObjectPropertySeqOpt())};
	}

	public static FacilityEquipment buildEmptyFacilityEquipment() {
		return new FacilityEquipment(
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null));
	}
	
	public static FacilityEquipment2 buildEmptyFacilityEquipment2() {
		return new FacilityEquipment2(
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null));
	}

	public static FacilityEquipmentOpt buildEmptyFacilityEquipmentOpt() {
		return (
			(FacilityEquipmentOpt) IDLUtil.toOpt(
				FacilityEquipmentOpt.class,
				null));
	}

	public static VideoHeadOfficeRouter buildEmptyVideoHeadOfficeRouterObject() {
		return new VideoHeadOfficeRouter(
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null),
			(StringOpt) IDLUtil.toOpt((String) null));
	}

	public static VideoHeadOfficeRouter[] buildEmptyVideoHeadOfficeRouterArrayObject() {
		return new VideoHeadOfficeRouter[0];
	}

	public static VideoHeadOfficeRouterSeqOpt buildEmptyVideoHeadOfficeRouterSeqOpt() {
		return (VideoHeadOfficeRouterSeqOpt) IDLUtil.toOpt(
			VideoHeadOfficeRouterSeqOpt.class,
			null);
	}
    
    public static BooleanOpt buildEmptyBooleanOpt() {
        return ((BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, null));
    }
    
    public static BillingAccountSeqOpt buildEmptyBillingAccountSeqOpt() {
        return ((BillingAccountSeqOpt) IDLUtil.toOpt(BillingAccountSeqOpt.class, null));
    }

    public static BillingAccount2SeqOpt buildEmptyBillingAccount2SeqOpt() {
        return ((BillingAccount2SeqOpt) IDLUtil.toOpt(BillingAccount2SeqOpt.class, null));
    }

    public static FacilityLoopSeqOpt buildEmptyFacilityLoopSeqOpt() {
        return ((FacilityLoopSeqOpt) IDLUtil.toOpt(FacilityLoopSeqOpt.class, null));
    }

    public static FacilityLoop2SeqOpt buildEmptyFacilityLoop2SeqOpt() {
        return ((FacilityLoop2SeqOpt) IDLUtil.toOpt(FacilityLoop2SeqOpt.class, null));
    }
}
