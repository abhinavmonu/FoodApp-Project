package com.tap.model;

public class OrderItem {
	
	private int orderId;
	private int menuId;
	private int quantity;
	private double totalAmount;
	
	public OrderItem() {
	}

	public OrderItem(int orderId, int menuId, int quantity, double totalAmount) {
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}



	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "OrderItem [orderId=" + orderId + ", menuId=" + menuId + ", quantity="
				+ quantity + ", totalAmount=" + totalAmount + "]";
	}	

}
