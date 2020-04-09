package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Employee;

public class EmployeeDAOImpl implements DBManipulateInterface<Employee>,
SearchInteface<Employee>
{

	@Override
	public boolean insertElement(Employee e) {
		Connection connection = DBConnection.getConnection();
		try {
			String sql = "INSERT INTO Employees(employeeName, employeePosition, employeeAddress, employeePhoneNumber, employeeEmail, dateBegin, shopId) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, e.getEmployeeName());
			statement.setString(2, e.getEmployeePosition());
			statement.setString(3, e.getEmployeeAddress());
			statement.setString(4, e.getEmployeePhoneNumber());
			statement.setString(5, e.getEmployeeEmail());
			statement.setDate(6, java.sql.Date.valueOf(e.getDateCreate().toString()));
			statement.setInt(7, e.getShopId());
			boolean isExecute = statement.execute();
			connection.close();
			if(isExecute){
				System.out.println("Inserted success!");
				return true;
			}
			else
				System.out.println("Inserted fail!");
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateElement(Employee e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "UPDATE Employees "
					+ "SET employeeName=? employeePosition=? employeeAddress=? employeePhoneNumber=? employeeEmail=?"
					+ "WHERE employeeId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, e.getEmployeeName());
			preparedStatement.setString(2, e.getEmployeePosition());
			preparedStatement.setString(3, e.getEmployeeAddress());
			preparedStatement.setString(4, e.getEmployeePhoneNumber());
			preparedStatement.setString(5, e.getEmployeeEmail());
			preparedStatement.setInt(6, e.getEmployeeId());
			int affectedRow = preparedStatement.executeUpdate();
			connection.close();
			if(affectedRow > 0)
			{
				System.out.println(affectedRow + " rows affected");
				return true;
			}else
			{
				System.out.println("0 row affected");
			}
				
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteElement(Employee e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "DELETE FROM Employee WHERE employeeId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, e.getEmployeeId());
			int affectedRows = preparedStatement.executeUpdate();
			connection.close();
			
			if(affectedRows > 0)
				return true;
			else
			{
				System.out.println("The employee is not exist!");
				return false;
			}
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Employee getElementById(int id) {
		Connection connection = DBConnection.getConnection();
		try 
		{
			String sql = "SELECT * FROM Employee WHERE employeeId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			
			Employee employee = new Employee();
			while(result.next())
			{
				employee.setEmployeeId(result.getInt("employeeId"));
				employee.setEmployeeAddress(result.getString("employeeAddress"));
				employee.setEmployeeName(result.getString("employeeName"));
				employee.setEmployeePosition(result.getString("employeePosition"));
				employee.setEmployeeEmail(result.getString("employeeEmail"));
				employee.setEmployeePhoneNumber(result.getString("employeePhoneNumber"));
				employee.setDateCreate(result.getDate("dateCreate"));
				employee.setShopId(result.getInt("shopId"));
				employee.setShopName(new ShopDAOImpl().getElementById(employee.getEmployeeId()).getShopName());
			}
			connection.close();
			return employee;
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<Employee> getElements(int beginRow, int amount) {
		
		return null;
	}

	@Override
	public int amountRows() {
		int rows = 0;
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "SELECT COUNT(*) FROM Employee";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				rows = resultSet.getInt(1);
			}
			connection.close();
			return rows;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return rows;
	}

	@Override
	public Employee searchElementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee searchById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchElementsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
