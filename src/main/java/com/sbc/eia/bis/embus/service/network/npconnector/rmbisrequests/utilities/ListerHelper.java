package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.utilities;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.ListerRequestTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.OrderByItemSeqTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.OrderByItemTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.SearchItemSeqTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.SearchItemTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.UniqueByItemSeqTypeImpl;

/**
 * @author ML2917
 *
 * Wrapper class for the ListerRequestTypeImpl.
 *
 */

public class ListerHelper
{

	private UniqueByItemSeq uniqueByList =
		new UniqueByItemSeq();
	private AttributeItemSeqHelper columnList =
		new AttributeItemSeqHelper();
	private SearchItemSeq searchList = new SearchItemSeq();
	private OrderByItemSeq orderByList = new OrderByItemSeq();

	/**
	 * Constructor for NpListerRequestHelper.
	 */

	public ListerHelper()
	{
		super();

	}

	public void addColumn(String item)
	{

		columnList.add(item);

	}

	public void addSearch(String name, String operation, String value)
	{

		searchList.add(
			new SearchItemSeq.SearchItem(name, operation, value).getObject());

	}
	public void addOrderBy(String column, String order)
	{

		orderByList.add(new OrderByItemSeq.OrderByItem(column, order).getObject());

	}

	public void addUniqueBy()
	{

	}

	public ListerRequestTypeImpl getObject()
	{

		ListerRequestTypeImpl request = new ListerRequestTypeImpl();

		request.setOrderByList(orderByList.getObject());
		request.setColumnList(columnList.getObject());
		request.setSearchList(searchList.getObject());
		request.setUniqueByList(uniqueByList.getObject());

		return request;

	}
	
	 /****************************************************************
	 * 
	 * 
	 * Wrapper class for SearchItemSeqType.
	 * 
	 * 
	 */
	
	
	
	public static class SearchItemSeq
	{

		private SearchItemSeqTypeImpl searchItemSeqType = null;
		private boolean emptyList = true;

		public SearchItemSeq()
		{

			searchItemSeqType = new SearchItemSeqTypeImpl();

		}

		public void add(SearchItemTypeImpl item)
		{

			searchItemSeqType.getSearchItem().add(item);
			emptyList = false;

		}

		public SearchItemSeqTypeImpl getObject()
		{

			if (emptyList)
			{
				return null;
			}

			return searchItemSeqType;
		}

		public static class SearchItem
		{

			private SearchItemTypeImpl searchItemType = null;

			public SearchItem(String name, String operation, String value)
			{

				searchItemType = new SearchItemTypeImpl();

				searchItemType.setOperation(operation);
				searchItemType.setName(name);
				searchItemType.setValue(value);

			}

			public SearchItemTypeImpl getObject()
			{
				return searchItemType;
			}

		}
	}

	/****************************************************************
	 * 
	 * 
	 * Wrapper class for OrderByItemSeqType.
	 * 
	 * 
	 */


	public static class OrderByItemSeq
	{

		private OrderByItemSeqTypeImpl orderByItemSeqType = null;
		private boolean emptyList = true;

		public OrderByItemSeq()
		{

			orderByItemSeqType = new OrderByItemSeqTypeImpl();
			emptyList = true;

		}

		public void add(OrderByItemTypeImpl item)
		{

			orderByItemSeqType.getOrderByItem().add(item);

			emptyList = false;
		}

		public OrderByItemSeqTypeImpl getObject()
		{

			if (emptyList)
			{
				return null;
			}

			return orderByItemSeqType;
		}
		public static class OrderByItem
		{

			private OrderByItemTypeImpl orderByItemType = null;

			public OrderByItem(String column, String order)
			{

				orderByItemType = new OrderByItemTypeImpl();

				orderByItemType.setOrder(order);
				orderByItemType.setName(column);

			}

			public OrderByItemTypeImpl getObject()
			{
				return orderByItemType;
			}

		}
	}

	/****************************************************************
	 * 
	 * 
	 * Wrapper class for UniqueByItemSeqType.
	 * 
	 * 
	 */
		
	public static class UniqueByItemSeq
	{

		private UniqueByItemSeqTypeImpl uniqueByItemSeqType = null;
		private boolean emptyList = true;

		public UniqueByItemSeq()
		{

			uniqueByItemSeqType = new UniqueByItemSeqTypeImpl();
			emptyList = true;
		}

		public void add(String item)
		{

			uniqueByItemSeqType.getUniqueByItem().add(item);

			emptyList = false;
		}

		public UniqueByItemSeqTypeImpl getObject()
		{

			if (emptyList)
			{
				return null;
			}

			return uniqueByItemSeqType;

		}
	}

}
