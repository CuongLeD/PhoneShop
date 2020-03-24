package model;

public class Customer {
	private int customerId;
	private String customerName;
	private String customerAddress;
	private String customerPhoneNumber;
	private String customerEmail;
	private java.util.Date dateCreate;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public java.util.Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(java.util.Date dateCreate) {
		this.dateCreate = dateCreate;
	}
}
