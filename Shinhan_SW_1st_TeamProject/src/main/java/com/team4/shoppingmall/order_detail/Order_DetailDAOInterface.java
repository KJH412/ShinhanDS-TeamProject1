package com.team4.shoppingmall.order_detail;

import java.util.List;
import java.util.Map;


public interface Order_DetailDAOInterface {
	
	public List<Order_DetailDTO> selectByOrder_Id(int order_id);
	
	public List<Order_DetailDTO> selectByOrderDetail_Id(int orderdetail_id);

	public List<Order_DetailDTO> selectBySellerID(String member_id);
	
	public List<Order_DetailDTO> selectAll();

	public int orderDetailInsert(Order_DetailDTO order_detail);

	public int orderDetailUpdate(Order_DetailDTO order_detail);

	public int orderDetailStatusUpdate(Order_DetailDTO order_detail);
	
	public int orderDetailDelete(int orderdetail_id);

	public List<Map<String, Object>> searchCustomerOrderList(String searchOrderList);
}
