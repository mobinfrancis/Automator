package com.automator.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DatabaseUtility {

	private Connection connection = null;
	private static final Logger log = Logger.getLogger(DatabaseUtility.class);

	public ResultSet getDataFromMySQLDB(String queryStatement) {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			log.info("MySQL JDBC driver is loaded successfully");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/opencart", System.getProperty(""),
					System.getProperty(""));
			statement = connection.createStatement();
			resultSet = statement.executeQuery(queryStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public void closeSQLDBConnection() {
		try {
			connection.close();
			log.info("MySQL DB connection is closed successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
