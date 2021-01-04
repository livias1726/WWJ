package logic.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	
    private static String user;
    private static String pass;
    private static String url;
    
    private static Connection conn = null;
    
    private ConnectionManager() {
		/**/
	}
	
	public static Connection getConnection() throws SQLException{
		
		if (conn == null) {
			setUpConnection();
			conn = DriverManager.getConnection(url, user, pass);
		}
		
		return conn;
	}
	
	private static void setUpConnection() throws SQLException {
		Properties prop = new Properties();
		
		try (InputStream input = ConnectionManager.class.getResourceAsStream("../wwj.properties")) {
			prop.load(input);
			
			user = prop.getProperty("dbuser");
			pass = prop.getProperty("dbpasswd");
			url = prop.getProperty("dburl");
		} catch (IOException e) {
			throw new SQLException();
		}
	}

	public static void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			conn = null;
		}
	}
}
