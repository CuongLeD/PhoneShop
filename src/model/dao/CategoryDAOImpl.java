package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;

public class CategoryDAOImpl implements DBManipulateInterface<Category>{

	@Override
	public boolean insertElement(Category e) {
		Connection connection = DBConnection.getConnection();
		try{
			String sqlQuery = "INSERT INTO Categories(categoryName, categoryDescription)"
					+ "VALUES(?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, e.getCategoryName());
			preparedStatement.setString(2, e.getCategoryDescription());
			boolean result = preparedStatement.executeUpdate() > 0;
			connection.close();
			if(result)
				return true;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateElement(Category e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "UPDATE Categories SET categoryName=? categoryDescription=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, e.getCategoryName());
			preparedStatement.setString(2, e.getCategoryDescription());
			
			boolean result = preparedStatement.executeUpdate() > 0;
			connection.close();
			if(result)
				return true;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteElement(Category e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "DELETE FROM Categories WHERE categoryId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, e.getCategoryId());
			
			boolean result = preparedStatement.executeUpdate() > 0;
			connection.close();
			if(result)
				return true;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Category getElementById(int id) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT categoryId, categoryName, categoryDescription "
					+ "FROM Categories WHERE categoryId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			connection.close();
			while(resultSet.next())
			{
				Category Category = new Category();
				Category.setCategoryId(resultSet.getInt(1));
				Category.setCategoryName(resultSet.getString(2));
				Category.setCategoryDescription(resultSet.getString(3));
				
				return Category;
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> getElements(int beginRow, int amount) {
		Connection connection = DBConnection.getConnection();
		try {
			String sqlQuery = "SELECT categoryId, categoryName, categoryDescription "
					+ "FROM Categories LIMIT ?, ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, beginRow);
			preparedStatement.setInt(2, amount);
			System.out.println(beginRow + " " + amount);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Category> categories = new ArrayList<Category>();
			while(resultSet.next())
			{
				Category Category = new Category();
				Category.setCategoryId(resultSet.getInt(1));
				Category.setCategoryName(resultSet.getString(2));
				Category.setCategoryDescription(resultSet.getString(3));
				categories.add(Category);
			}
			connection.close();
			return categories;
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
			String sqlQuery = "SELECT COUNT(*) FROM Categories";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			int amount = -1;
			while(resultSet.next())
			{
				amount = resultSet.getInt(1);
				return amount;
			}
			connection.close();
			return amount;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return 0;
	}
	

}
