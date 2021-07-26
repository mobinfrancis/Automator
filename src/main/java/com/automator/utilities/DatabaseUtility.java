package com.automator.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtility {

	public void getDataFromMySQLDB() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL JDBC driver loaded successfully");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/opencart", "", "");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from products");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("product_id") + " -> " + resultSet.getString("product_name"));
			}
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

	}

}
