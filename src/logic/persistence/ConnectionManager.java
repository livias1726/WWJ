package logic.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static String user = "root";
    private static String pass = "admin";
    private static String url = "jdbc:mysql://localhost:3306/wwj_db";
    private static String driver = "com.mysql.jdbc.Driver";
    
    private static Connection conn = null;
    
    private ConnectionManager() {
		/**/
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		if (conn == null) {
			 Class.forName(driver);
	         conn = DriverManager.getConnection(url, user, pass);
		}
		
		return conn;
	}
	
	public static void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			conn = null;
		}
	}
}
