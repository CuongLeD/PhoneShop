package control.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ImageLink;
import model.Phone;

public class PhoneDAOImpl implements DBManipulateInterface<Phone>{

	@Override
	public boolean insertElement(Phone e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "INSERT INTO Phone"
					+ "(phoneName, phoneQuantity, phonePrice, phoneDescription, shopId, categoryId, vendorId)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, e.getPhoneName());
			preparedStatement.setInt(2, e.getQuantity());
			preparedStatement.setLong(3, e.getPrice());
			preparedStatement.setString(4, e.getDecription());
			preparedStatement.setInt(5, e.getShopId());
			preparedStatement.setInt(6, e.getCategoryId());
			preparedStatement.setInt(7, e.getVendorId());			
			int affectRows = preparedStatement.executeUpdate();
			
			for(ImageLink image : e.getImageLinks())
			{
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteElement(Phone e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Phone getElementById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Phone> getElements(int beginRow, int amount) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "SELECT  phoneId, phoneName, phoneQuantity, phonePrice, phoneDescrible, Phones.shopId, Phones.categoryId, Vendors.vendorId, Vendors.vendorName, Shops.shopName, Categories.categoryName "
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
				phone.setDecription(resultSet.getString(5));
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
			System.out.println(phones.size());
			return phones;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public int amountRows() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
