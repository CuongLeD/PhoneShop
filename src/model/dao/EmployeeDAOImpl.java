package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
			java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm;ss");
			statement.setString(6, sdf.format(e.getDateCreate()));
			statement.setInt(7, e.getShopId());
			int affectedRows = statement.executeUpdate();
			connection.close();
			if(affectedRows > 0){
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
					+ "SET employeeName=?, employeePosition=?, employeeAddress=?, employeePhoneNumber=?, employeeEmail=? "
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
				return true;
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
			String sql = "UPDATE Employees SET 	employeeStatus = ?"
					+ " WHERE employeeId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, e.isStatus() == true ? 1 : 0);
			preparedStatement.setInt(2, e.getEmployeeId());
			int affectedRows = preparedStatement.executeUpdate();
			connection.close();
			
			if(affectedRows > 0)
				return true;
			else
			{
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
			String sql = "SELECT * FROM Employee WHERE employeeId=? AND employeeStatus = 1";
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
				employee.setShopId(result.getInt("shopId"));
				employee.setShopName(new ShopDAOImpl().getElementById(employee.getEmployeeId()).getShopName());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				employee.setDateCreate(dateFormat.parse(result.getString(7)));
			}
			connection.close();
			return employee;
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<Employee> getElements(int beginRow, int amount) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT employeeId, employeeName, employeePosition, employeeAddress,\n"
					+ "employeePhoneNumber, employeeEmail, dateBegin, Shops.shopId, Shops.shopName "
					+ "FROM PhoneShop.Employees \n"  
					+ "INNER JOIN Shops ON Shops.shopId = Employees.shopId\n"  
					+ "WHERE employeeStatus = 1  LIMIT 0, 100 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<Employee> employees = new ArrayList<Employee>();
			while(resultSet.next())
			{
				Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getInt(1));
				employee.setEmployeeName(resultSet.getString(2));
				employee.setEmployeePosition(resultSet.getString(3));
				employee.setEmployeeAddress(resultSet.getString(4));
				employee.setEmployeePhoneNumber(resultSet.getString(5));
				employee.setEmployeeEmail(resultSet.getString(6));
				employee.setShopId(resultSet.getInt(8));
				employee.setShopName(resultSet.getString(9));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				employee.setDateCreate(dateFormat.parse(resultSet.getString(7)));
				employees.add(employee);
			}
			connection.close();
			
			return employees;
		}catch(SQLException e)
		{
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
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
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT employeeId, employeeName, employeePosition, employeeAddress,\n"
					+ "employeePhoneNumber, employeeEmail, dateBegin, Shops.shopId, Shops.shopName "
					+ "FROM PhoneShop.Employees \n"  
					+ "INNER JOIN Shops ON Shops.shopId = Employees.shopId\n"
					+ "WHERE employeeName = CONVERT(?, BINARY) AND employeeStatus = 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getInt(1));
				employee.setEmployeeName(resultSet.getString(2));
				employee.setEmployeePosition(resultSet.getString(3));
				employee.setEmployeeAddress(resultSet.getString(4));
				employee.setEmployeePhoneNumber(resultSet.getString(5));
				employee.setEmployeeEmail(resultSet.getString(6));
				employee.setShopId(resultSet.getInt(8));
				employee.setShopName(resultSet.getString(9));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				employee.setDateCreate(dateFormat.parse(resultSet.getString(7)));
				
				connection.close();
				return employee;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee searchById(int id) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT employeeId, employeeName, employeePosition, employeeAddress,\n"
					+ "employeePhoneNumber, employeeEmail, dateBegin, Shops.shopId, Shops.shopName "
					+ "FROM PhoneShop.Employees \n"  
					+ "INNER JOIN Shops ON Shops.shopId = Employees.shopId\n"
					+ "WHERE employeeId = ? AND employeeStatus = 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getInt(1));
				employee.setEmployeeName(resultSet.getString(2));
				employee.setEmployeePosition(resultSet.getString(3));
				employee.setEmployeeAddress(resultSet.getString(4));
				employee.setEmployeePhoneNumber(resultSet.getString(5));
				employee.setEmployeeEmail(resultSet.getString(6));
				employee.setShopId(resultSet.getInt(8));
				employee.setShopName(resultSet.getString(9));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				employee.setDateCreate(dateFormat.parse(resultSet.getString(7)));
				
				connection.close();
				return employee;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> searchElementsByName(String name) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "CALL sp_SearchEmployeeByName(?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<Employee> employees = new ArrayList<Employee>();
			while(resultSet.next())
			{
				Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getInt(1));
				employee.setEmployeeName(resultSet.getString(2));
				employee.setEmployeePosition(resultSet.getString(3));
				employee.setEmployeeAddress(resultSet.getString(4));
				employee.setEmployeePhoneNumber(resultSet.getString(5));
				employee.setEmployeeEmail(resultSet.getString(6));
				employee.setShopId(resultSet.getInt(8));
				employee.setShopName(resultSet.getString(9));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				employee.setDateCreate(dateFormat.parse(resultSet.getString(7)));
				employees.add(employee);
			}
			connection.close();
			
			return employees;
		}catch(SQLException e)
		{
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
