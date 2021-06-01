package DataAccess;

import java.sql.*;
import java.util.TimeZone;

public class DAO {

	private final String DB_NAME = "tsw";
	private final String TIMEZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=" + TimeZone.getDefault().getID();
	private final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME + TIMEZONE;
	private final String USER = "root";
	private final String PASSWORD = "password";
	private Connection jdbcConnection = null;

	public Connection getConnection() {
		return jdbcConnection;
	}

	void startConnection(){
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			this.jdbcConnection =  DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void closeConnection(){
		if (this.jdbcConnection != null) {
			try {
				this.jdbcConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
