package Jdbc;

import java.sql.*;

public class DatabaseConnection {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_feedback";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
