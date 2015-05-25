package org.wms.controller.inputorder;

import javax.swing.table.AbstractTableModel;

import org.wms.model.order.Order;
import org.wms.model.order.Orders;

public class OrdersTableModel extends AbstractTableModel {
	
	private Orders ordersModel;
	
	private String[] headers = {"Code", "Emission date", "Priority", "Order status", "Complete %", "Allocation %"};
	
	public OrdersTableModel(Orders ordersModel) {
		super();
		this.ordersModel = ordersModel;
	}

	@Override
	public int getRowCount() {
		return ordersModel.getOrderList().size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Order order = ordersModel.getOrderList().get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return order.getId();
		case 1:
			return order.getEmissionDate();
		case 2:
			return order.getPriority().name();
		case 3:
			return order.getOrderStatus().name();
		case 4:
			return order.getCompletePercentual();
		case 5:
			return order.getAllocationPercentual();
		default:
			break;
		}
		
		return "Unknow column: " + columnIndex;
	}

}
