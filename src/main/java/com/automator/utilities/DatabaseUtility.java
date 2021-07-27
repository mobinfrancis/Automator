package com.automator.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtility {

	public ResultSet getDataFromMySQLDB(String queryStatement) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL JDBC driver loaded successfully");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/opencart", "root", "football");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(queryStatement);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
