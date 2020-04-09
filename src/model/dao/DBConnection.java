package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection()
	{
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/PhoneShop?characterEncoding=UTF8";
			String username = "root";
			String password = "Cuong123456a@";
			connection = DriverManager.getConnection(url, username, password);
		}catch(ClassNotFoundException ex)
		{
			System.out.println("Can not load driver");
		}
		catch(SQLException ex)
		{
			System.out.println("Can not connect to database");
		}
		return connection;
	}

}
