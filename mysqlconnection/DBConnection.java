package in.sts.excelutility.mysqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String url = "jdbc:mysql://localhost:3306/unique_data";
	private static final String userName = "root";
	private static final String Pass = "root";
	private static Connection connection;
	public static Connection connect() {
		try {
			connection = DriverManager.getConnection(url, userName, Pass);
			System.out.println("Database connection successful");
		}
		catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		}
		return connection;
	}

}
