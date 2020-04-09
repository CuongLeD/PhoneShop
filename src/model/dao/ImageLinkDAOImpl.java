package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ImageLink;

public class ImageLinkDAOImpl implements DBManipulateInterface<ImageLink>{

	@Override
	public boolean insertElement(ImageLink e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "INSERT INTO ImagesLink(imageLink, isAvatar, phoneId)"
					+ "VALUES(?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, e.getImageLink());
			preparedStatement.setBoolean(2, e.isAvatar());
			preparedStatement.setInt(3, e.getPhoneId());
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
	public boolean updateElement(ImageLink e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "UPDATE TABLE ImagesLink "
					+ "SET imageLink = ?, isAvatar = ?, phoneId = ? "
					+ "WHERE imageLinkId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, e.getImageLink());
			preparedStatement.setInt(2, e.isAvatar() == true ? 1 : 0);
			preparedStatement.setInt(3, e.getPhoneId());
			preparedStatement.setInt(4, e.getImageLinkId());
			
			int affectedRows = preparedStatement.executeUpdate();
			connection.close();
			return (affectedRows > 0);
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteElement(ImageLink e) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "DELETE FROM ImageLinks "
					+ "WHERE imageLinkId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, e.getImageLinkId());
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
	public ImageLink getElementById(int id) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT * FROM ImagesLink WHERE ImagesLink = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			ImageLink imageLink = null;
			while(resultSet.next())
			{
				imageLink = new ImageLink();
				imageLink.setPhoneId(resultSet.getInt(1));
				imageLink.setImageLink(resultSet.getString(2));
				imageLink.setAvatar(resultSet.getInt(3) == 1);
				imageLink.setPhoneId(resultSet.getInt(4));
				break;
			}
			
			connection.close();
			return imageLink;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ImageLink> getElements(int beginRow, int amount) {
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT * FROM ImagesLink LIMIT ?, ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, beginRow);
			preparedStatement.setInt(2, amount);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<ImageLink> imageLinks = new  ArrayList<ImageLink>();
			while(resultSet.next())
			{
				ImageLink imageLink = new ImageLink();
				imageLink.setImageLinkId(resultSet.getInt(1));
				imageLink.setImageLink(resultSet.getString(2));
				imageLink.setAvatar(resultSet.getInt(3) == 1);
				imageLink.setPhoneId(resultSet.getInt(4));
				imageLinks.add(imageLink);
			}
			
			connection.close();
			return imageLinks;
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
			String sqlQuery = "SELECT COUNT(*) FROM ImagesLink";
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
	
	public static List<ImageLink> getByPhoneId(int phoneId)
	{
		List<ImageLink> imageLinks = null;
		Connection connection = DBConnection.getConnection();
		try
		{
			imageLinks = new ArrayList<ImageLink>();
			String sqlImageLink = "SELECT * FROM ImagesLink WHERE phoneId = ?"; 
			PreparedStatement preparedStatement = connection.prepareStatement(sqlImageLink);
			preparedStatement.setInt(1, phoneId);
			ResultSet resultSetImage = preparedStatement.executeQuery();
			while(resultSetImage.next())
			{
				ImageLink imageLink = new ImageLink();
				imageLink.setImageLinkId(resultSetImage.getInt(1));
				imageLink.setImageLink(resultSetImage.getString(2));
				imageLink.setAvatar(resultSetImage.getInt(3) == 1);
				imageLink.setPhoneId(resultSetImage.getInt(4));
				imageLinks.add(imageLink);
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return imageLinks;
	}

}
