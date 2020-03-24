package control.dao;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteElement(ImageLink e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageLink getElementById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImageLink> getElements(int beginRow, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int amountRows() {
		// TODO Auto-generated method stub
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
