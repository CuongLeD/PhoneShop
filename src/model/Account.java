package model;

public class Account {
	private String username;
	private String password;
	private String permission;
	private int employeeId;
	private java.util.Date dateCreate;
	private String description;
		
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public java.util.Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(java.util.Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	
}
