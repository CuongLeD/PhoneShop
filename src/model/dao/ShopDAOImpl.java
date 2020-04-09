package model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Shop;
import model.service.SearchService;

public class ShopDAOImpl implements DBManipulateInterface<Shop>,
	SearchService<Shop>

{

	@Override
	public boolean insertElement(Shop e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "INSERT INTO Shops(shopName, shopAddress, shopPhoneNumber) "
					+ "VALUES(?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, e.getShopName());
			preparedStatement.setString(2, e.getShopAddress());
			preparedStatement.setString(3, e.getShopPhoneNumber());
			int affectedRows = preparedStatement.executeUpdate();
			
			connection.close();
			if(affectedRows > 0)
				return true;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateElement(Shop e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "UPDATE Shops "
					+ "SET shopName = ?, shopAddress = ?, shopPhoneNumber = ? "
					+ "WHERE shopId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, e.getShopName());
			preparedStatement.setString(2, e.getShopAddress());
			preparedStatement.setString(3, e.getShopPhoneNumber());
			preparedStatement.setInt(4, e.getShopId());
			int affectedRows = preparedStatement.executeUpdate();
			
			connection.close();
			if(affectedRows > 0)
				return true;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteElement(Shop e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "DELETE FROM Shops WHERE shopId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, e.getShopId());
			int affectedRows = preparedStatement.executeUpdate();
			
			connection.close();
			if(affectedRows > 0)
				return true;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Shop getElementById(int id) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "SELECT * FROM Shop WHERE shopId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Shop shop = new Shop();
			while(resultSet.next())
			{
				shop.setShopId(resultSet.getInt("shopId"));
				shop.setShopName(resultSet.getString("shopName"));
				shop.setShopAddress(resultSet.getString("shopAddress"));
				shop.setShopPhoneNumber(resultSet.getString("shopPhoneNumber"));
			}
			connection.close();
			return shop;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Shop> getElements(int beginRow, int amount) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT * FROM Shops LIMIT ?, ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, beginRow);
			preparedStatement.setInt(2, amount);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Shop> shops = new ArrayList<Shop>();
			while(resultSet.next())
			{
				Shop shop = new Shop();
				shop.setShopId(resultSet.getInt(1));
				shop.setShopName(resultSet.getString(2));
				shop.setShopAddress(resultSet.getString(3));
				shop.setShopPhoneNumber(resultSet.getString(4));
				shops.add(shop);
			}
			
			connection.close();
			return shops;
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
			String sqlQuery = "SELECT COUNT(*) FROM Shops";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			int rows = 0;
			while(resultSet.next())
				rows = resultSet.getInt(1);
			
			connection.close();
			return rows;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public Shop searchElementByName(String name) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "SELECT * FROM Shops WHERE shopName = CONVERT(?, BINARY)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			Shop shop = new Shop();
			while(resultSet.next())
			{
				shop.setShopId(resultSet.getInt("shopId"));
				shop.setShopName(resultSet.getString("shopName"));
				shop.setShopAddress(resultSet.getString("shopAddress"));
				shop.setShopPhoneNumber(resultSet.getString("shopPhoneNumber"));
				break;
			}
			connection.close();
			return shop;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Shop searchById(int id) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "SELECT * FROM Shops WHERE shopId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Shop shop = new Shop();
			while(resultSet.next())
			{
				shop.setShopId(resultSet.getInt("shopId"));
				shop.setShopName(resultSet.getString("shopName"));
				shop.setShopAddress(resultSet.getString("shopAddress"));
				shop.setShopPhoneNumber(resultSet.getString("shopPhoneNumber"));
			}
			connection.close();
			return shop;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Shop> searchElementsByName(String name) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlProc = "CALL sp_SearchShopByName(?)";
			CallableStatement callableStatement = connection.prepareCall(sqlProc);
			callableStatement.setString(1, name);
			
			ResultSet resultSet = callableStatement.executeQuery();
			List<Shop> shops = new ArrayList<Shop>();
			while(resultSet.next())
			{
				Shop shop = new Shop();
				shop.setShopId(resultSet.getInt(1));
				shop.setShopName(resultSet.getString(2));
				shop.setShopAddress(resultSet.getString(3));
				shop.setShopPhoneNumber(resultSet.getString(4));
				shops.add(shop);
			}
			
			connection.close();
			return shops;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	

}
