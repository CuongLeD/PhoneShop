package model;

import java.util.List;

public class Order {
	private int orderId;
	private String employeeName;
	private String customerName;
	private String shopName;
	private List<Phone> list;
	
	
	public List<Phone> getList() {
		return list;
	}
	public void setList(List<Phone> list) {
		this.list = list;
	}
	private java.util.Date dateCreate;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public java.util.Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(java.util.Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	
	

}
