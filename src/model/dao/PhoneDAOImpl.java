package model.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ImageLink;
import model.Phone;

public class PhoneDAOImpl implements DBManipulateInterface<Phone>,
SearchInteface<Phone>
	
{

	@Override
	public boolean insertElement(Phone e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "INSERT INTO Phones"
					+ "(phoneName, phoneQuantity, phonePrice, phoneDescription, shopId, categoryId, vendorId)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, e.getPhoneName());
			preparedStatement.setInt(2, e.getQuantity());
			preparedStatement.setLong(3, e.getPrice());
			preparedStatement.setString(4, e.getDescription());
			preparedStatement.setInt(5, e.getShopId());
			preparedStatement.setInt(6, e.getCategoryId());
			preparedStatement.setInt(7, e.getVendorId());			
			int affectRows = preparedStatement.executeUpdate();
			
			String sqlSelectPhoneId = "SELECT phoneId FROM Phones "
					+ "WHERE phoneName=? AND phoneQuantity=? AND phonePrice=?";
			preparedStatement = connection.prepareStatement(sqlSelectPhoneId);
			preparedStatement.setString(1, e.getPhoneName());
			preparedStatement.setInt(2, e.getQuantity());
			preparedStatement.setLong(3, e.getPrice());
			ResultSet resultSet = preparedStatement.executeQuery();
			int phoneId = 0;
			while(resultSet.next())
				phoneId = resultSet.getInt(1);
			connection.close();
			
			for(ImageLink image : e.getImageLinks())
			{
				image.setPhoneId(phoneId);
				new ImageLinkDAOImpl().insertElement(image);
			}
			if(affectRows > 0)
				return true;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateElement(Phone e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "UPDATE Phones "
					+ "SET phoneName=?, phoneQuantity=?, phonePrice=?, phoneDescription=?, categoryId=?, vendorId=?, shopId=? "
					+ "WHERE phoneId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, e.getPhoneName());
			preparedStatement.setInt(2, e.getQuantity());
			preparedStatement.setLong(3, e.getPrice());
			preparedStatement.setString(4, e.getDescription());
			preparedStatement.setInt(5, e.getCategoryId());
			preparedStatement.setInt(6, e.getVendorId());
			preparedStatement.setInt(7, e.getShopId());
			preparedStatement.setInt(8, e.getPhoneId());
			int affectRows = preparedStatement.executeUpdate();
			connection.close();
			
			if(affectRows > 0)
				return true;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteElement(Phone e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "DELETE FROM Phones "
					+ "WHERE phoneId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, e.getPhoneId());
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
	public Phone getElementById(int id) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT  phoneId, phoneName, phoneQuantity, phonePrice, phoneDescription, Phones.shopId, Phones.categoryId, Vendors.vendorId, Vendors.vendorName, Shops.shopName, Categories.categoryName "
					+ "FROM Phones "
					+ "inner join Vendors on Vendors.vendorId = Phones.vendorId "
					+ "inner join Shops on Shops.shopId = Phones.shopId "
					+ "inner join Categories on Categories.categoryId = Phones.categoryId "
					+ "WHERE phoneId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Phone phone = null;
			while(resultSet.next())
			{
				phone = new Phone();
				phone.setPhoneId(resultSet.getInt(1));
				phone.setPhoneName(resultSet.getString(2));
				phone.setQuantity(resultSet.getInt(3));
				phone.setPrice(resultSet.getLong(4));
				phone.setDescription(resultSet.getString(5));
				phone.setShopId(resultSet.getInt(6));
				phone.setCategoryId(resultSet.getInt(7));
				phone.setVendorId(resultSet.getInt(8));
				phone.setVendor(resultSet.getString(9));
				phone.setShopName(resultSet.getString(10));
				phone.setPhoneCategory(resultSet.getString(11));
				
				List<ImageLink> imageLinks = ImageLinkDAOImpl.getByPhoneId(phone.getPhoneId());
				phone.setImageLinks(imageLinks);
				break;
			}
			connection.close();
			return phone;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Phone> getElements(int beginRow, int amount) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "SELECT  phoneId, phoneName, phoneQuantity, phonePrice, phoneDescription, Phones.shopId, Phones.categoryId, Vendors.vendorId, Vendors.vendorName, Shops.shopName, Categories.categoryName "
					+ "FROM Phones "
					+ "inner join Vendors on Vendors.vendorId = Phones.vendorId "
					+ "inner join Shops on Shops.shopId = Phones.shopId "
					+ "inner join Categories on Categories.categoryId = Phones.categoryId "
					+ "LIMIT ?, ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, beginRow);
			preparedStatement.setInt(2, amount);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Phone> phones = new ArrayList<Phone>();
			while(resultSet.next())
			{
				Phone phone = new Phone();
				phone.setPhoneId(resultSet.getInt(1));
				phone.setPhoneName(resultSet.getString(2));
				phone.setQuantity(resultSet.getInt(3));
				phone.setPrice(resultSet.getLong(4));
				phone.setDescription(resultSet.getString(5));
				phone.setShopId(resultSet.getInt(6));
				phone.setCategoryId(resultSet.getInt(7));
				phone.setVendorId(resultSet.getInt(8));
				phone.setVendor(resultSet.getString(9));
				phone.setShopName(resultSet.getString(10));
				phone.setPhoneCategory(resultSet.getString(11));
				
				List<ImageLink> imageLinks = ImageLinkDAOImpl.getByPhoneId(phone.getPhoneId());
				phone.setImageLinks(imageLinks);
				phones.add(phone);
			}
			connection.close();
			return phones;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public int amountRows() {
		int amount = 0;
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT COUNT(*) FROM Phones";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			amount = preparedStatement.getMaxRows();
			connection.close();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return amount;
	}

	@Override
	public Phone searchElementByName(String name) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT  phoneId, phoneName, phoneQuantity, phonePrice, phoneDescription, Phones.shopId, Phones.categoryId, Vendors.vendorId, Vendors.vendorName, Shops.shopName, Categories.categoryName "
					+ "FROM Phones "
					+ "inner join Vendors on Vendors.vendorId = Phones.vendorId "
					+ "inner join Shops on Shops.shopId = Phones.shopId "
					+ "inner join Categories on Categories.categoryId = Phones.categoryId "
					+ "WHERE phoneName = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			Phone phone = null;
			while(resultSet.next())
			{
				phone = new Phone();
				phone.setPhoneId(resultSet.getInt(1));
				phone.setPhoneName(resultSet.getString(2));
				phone.setQuantity(resultSet.getInt(3));
				phone.setPrice(resultSet.getLong(4));
				phone.setDescription(resultSet.getString(5));
				phone.setShopId(resultSet.getInt(6));
				phone.setCategoryId(resultSet.getInt(7));
				phone.setVendorId(resultSet.getInt(8));
				phone.setVendor(resultSet.getString(9));
				phone.setShopName(resultSet.getString(10));
				phone.setPhoneCategory(resultSet.getString(11));
				
				List<ImageLink> imageLinks = ImageLinkDAOImpl.getByPhoneId(phone.getPhoneId());
				phone.setImageLinks(imageLinks);
				break;
			}
			connection.close();
			return phone;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Phone searchById(int id) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT  phoneId, phoneName, phoneQuantity, phonePrice, phoneDescription, Phones.shopId, Phones.categoryId, Vendors.vendorId, Vendors.vendorName, Shops.shopName, Categories.categoryName "
					+ "FROM Phones "
					+ "inner join Vendors on Vendors.vendorId = Phones.vendorId "
					+ "inner join Shops on Shops.shopId = Phones.shopId "
					+ "inner join Categories on Categories.categoryId = Phones.categoryId "
					+ "WHERE phoneId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Phone phone = null;
			while(resultSet.next())
			{
				phone = new Phone();
				phone.setPhoneId(resultSet.getInt(1));
				phone.setPhoneName(resultSet.getString(2));
				phone.setQuantity(resultSet.getInt(3));
				phone.setPrice(resultSet.getLong(4));
				phone.setDescription(resultSet.getString(5));
				phone.setShopId(resultSet.getInt(6));
				phone.setCategoryId(resultSet.getInt(7));
				phone.setVendorId(resultSet.getInt(8));
				phone.setVendor(resultSet.getString(9));
				phone.setShopName(resultSet.getString(10));
				phone.setPhoneCategory(resultSet.getString(11));
				
				List<ImageLink> imageLinks = ImageLinkDAOImpl.getByPhoneId(phone.getPhoneId());
				phone.setImageLinks(imageLinks);
				break;
			}
			connection.close();
			return phone;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Phone> searchElementsByName(String name) {
		Connection connection = DBConnection.getConnection();
		try
		{	
			String sqlProc = "CALL sp_SearchPhoneByName(?)";
			CallableStatement callableStatement = connection.prepareCall(sqlProc);
			callableStatement.setString(1, name);
			
			ResultSet resultSet = callableStatement.executeQuery();
			List<Phone> phones = new ArrayList<Phone>();
			while(resultSet.next())
			{
				Phone phone = new Phone();
				phone.setPhoneId(resultSet.getInt(1));
				phone.setPhoneName(resultSet.getString(2));
				phone.setQuantity(resultSet.getInt(3));
				phone.setPrice(resultSet.getLong(4));
				phone.setDescription(resultSet.getString(5));
				phone.setShopId(resultSet.getInt(6));
				phone.setCategoryId(resultSet.getInt(7));
				phone.setVendorId(resultSet.getInt(8));
				phone.setVendor(resultSet.getString(9));
				phone.setShopName(resultSet.getString(10));
				phone.setPhoneCategory(resultSet.getString(11));
				
				List<ImageLink> imageLinks = ImageLinkDAOImpl.getByPhoneId(phone.getPhoneId());
				phone.setImageLinks(imageLinks);
				phones.add(phone);
			}
			connection.close();
			return phones;
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
}
