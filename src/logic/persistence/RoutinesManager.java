package logic.persistence;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoutinesManager {
	
	private RoutinesManager(){
		/**/
	}
	
	public static ResultSet bindParametersAndExec(CallableStatement stmt, String ... params) throws SQLException {
		for (int i = 0; i < params.length; i++) {
			stmt.setString(i+1, params[i]);
		}
				
		return executeStmt(stmt);
	}
	
	public static ResultSet bindParametersAndExec(CallableStatement stmt, int ... params) throws SQLException {
		for (int i = 0; i < params.length; i++) {
			stmt.setInt(i+1, params[i]);
		}
				
		return executeStmt(stmt);
	}

	public static ResultSet executeStmt(CallableStatement stmt) throws SQLException {
		if(stmt.execute()) {
			return stmt.getResultSet();
		}else {
			return null;
		}	
	}
}
