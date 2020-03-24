package control.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import model.Account;

public class AccountDAOImpl  implements DBManipulateInterface<Account>{

	
	@Override
	public boolean insertElement(Account e) {
		Connection connection = DBConnection.getConnection();
		if(isAccountExist(e))
		{
			System.out.println("Account already exist!");
			return false;
		}
		else
		{
			try
			{
				String sqlQuery = "INSERT INTO Accounts(username, password, permission, employeeId, dateCreate, description) "
						+ "VALUES(?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
				preparedStatement.setString(1, e.getUsername());
				preparedStatement.setString(2, e.getPassword());
				preparedStatement.setString(3, e.getPermission());
				preparedStatement.setInt(4, e.getEmployeeId());
				preparedStatement.setTimestamp(5, new Timestamp(e.getDateCreate().getTime()));
				preparedStatement.setString(6, e.getDescription());
				int affectedRows = preparedStatement.executeUpdate();
				connection.close();
				if(affectedRows > 0)
					return true;
			}catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updateElement(Account e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteElement(Account e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getElementById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getElements(int beginRow, int endRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int amountRows() {
		int rows = 0;
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "SELECT COUNT(*) FROM Accounts";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				rows = resultSet.getInt(1);
				break;
			}
			connection.close();
			return rows;
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return rows;
	}

	public static Account getAccountByUsername(String username)
	{
		Connection connection = DBConnection.getConnection();
		try
		{
			String sql = "SELECT * FROM Accounts WHERE username=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			Account account = null;
			while(resultSet.next())
			{
				account = new Account();
				account.setUsername(resultSet.getString(1));
				account.setPassword(resultSet.getString(2));
				account.setPermission(resultSet.getString(3));
			}
			connection.close();			
			return account;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	public static boolean isAccountExist(Account account)
	{
		Connection connection = DBConnection.getConnection();
		try
		{
			String sqlQuery = "SELECT * FORM Accounts WHERE username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, account.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			connection.close();
			if(resultSet.getRow() == 0)
				return false;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return true;
	}
}
