package control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Shop;

public class ShopDAOImpl implements DBManipulateInterface<Shop>{

	@Override
	public boolean insertElement(Shop e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateElement(Shop e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteElement(Shop e) {
		// TODO Auto-generated method stub
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
			
		}
		return null;
	}

	@Override
	public List<Shop> getElements(int beginRow, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int amountRows() {
		int rows = 0;
		return rows;
	}
	

}
