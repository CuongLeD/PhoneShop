package model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Vendor;

public class VendorDAOImpl implements DBManipulateInterface<Vendor>,
										SearchInteface<Vendor>
{

	@Override
	public boolean insertElement(Vendor e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "INSERT INTO Vendors(vendorName, vendorAddress, vendorPhoneName, vendorEmail) "
					+ "VALUES(?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, e.getVendorName());
			preparedStatement.setString(2, e.getVendorAddress());
			preparedStatement.setString(3, e.getVendorPhoneNumber());
			preparedStatement.setString(4,  e.getVendorEmail());
			
			boolean result = preparedStatement.executeUpdate() > 0;
			connection.close();
			return result;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateElement(Vendor e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "UPDATE Vendors "
					+ "SET vendorName=? vendorAddress=? vendorPhoneNumber=? vendorEmail=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, e.getVendorName());
			preparedStatement.setString(2, e.getVendorAddress());
			preparedStatement.setString(3, e.getVendorPhoneNumber());
			preparedStatement.setString(4, e.getVendorEmail());
			
			boolean result = preparedStatement.executeUpdate() > 0;
			connection.close();
			return result;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteElement(Vendor e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "DELETE FORM Vendors "
					+ "WHERE vendorId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, e.getVendorId());
			
			boolean result = preparedStatement.executeUpdate() > 0;
			connection.close();
			return result;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Vendor getElementById(int id) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT * FROM Vendors WHERE vendorId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Vendor vendor = new Vendor();
				vendor.setVendorId(resultSet.getInt(1));
				vendor.setVendorName(resultSet.getString(2));
				vendor.setVendorAddress(resultSet.getString(3));
				vendor.setVendorPhoneNumber(resultSet.getString(4));
				vendor.setVendorEmail(resultSet.getString(5));
				connection.close();
				return vendor;
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Vendor> getElements(int beginRow, int amount) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT * FROM Vendors LIMIT ?, ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, beginRow);
			preparedStatement.setInt(2, amount);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Vendor> vendors = new ArrayList<Vendor>();
			while(resultSet.next())
			{
				Vendor vendor = new Vendor();
				vendor.setVendorId(resultSet.getInt(1));
				vendor.setVendorName(resultSet.getString(2));
				vendor.setVendorAddress(resultSet.getString(3));
				vendor.setVendorPhoneNumber(resultSet.getString(4));
				vendor.setVendorEmail(resultSet.getString(5));
				vendors.add(vendor);
			}
			connection.close();
			return vendors;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public int amountRows() {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT COUNT(*) FROM Vendors";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			int amount = 0;
			while(resultSet.next())
				amount = resultSet.getInt(1);
			connection.close();
			return amount;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	
		return 0;
	}

	@Override
	public Vendor searchElementByName(String name) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT * FROM Vendors WHERE vendorName = CONVERT(?, BINARY)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, name);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Vendor vendor = new Vendor();
				vendor.setVendorId(resultSet.getInt(1));
				vendor.setVendorName(resultSet.getString(2));
				vendor.setVendorAddress(resultSet.getString(3));
				vendor.setVendorPhoneNumber(resultSet.getString(4));
				vendor.setVendorEmail(resultSet.getString(5));
				connection.close();
				return vendor;
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Vendor searchById(int id) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT * FROM Vendors WHERE vendorId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Vendor vendor = new Vendor();
				vendor.setVendorId(resultSet.getInt(1));
				vendor.setVendorName(resultSet.getString(2));
				vendor.setVendorAddress(resultSet.getString(3));
				vendor.setVendorPhoneNumber(resultSet.getString(4));
				vendor.setVendorEmail(resultSet.getString(5));
				connection.close();
				return vendor;
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Vendor> searchElementsByName(String name) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlProc = "CALL sp_SearchVendorByName(?)";
			CallableStatement callableStatement = connection.prepareCall(sqlProc);
			callableStatement.setString(1, name);
					
			ResultSet resultSet = callableStatement.executeQuery();
			List<Vendor> vendors = new ArrayList<Vendor>();
			while(resultSet.next())
			{
				Vendor vendor = new Vendor();
				vendor.setVendorId(resultSet.getInt(1));
				vendor.setVendorName(resultSet.getString(2));
				vendor.setVendorAddress(resultSet.getString(3));
				vendor.setVendorPhoneNumber(resultSet.getString(4));
				vendor.setVendorEmail(resultSet.getString(5));
				vendors.add(vendor);
			}
			connection.close();
			return vendors;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

}
