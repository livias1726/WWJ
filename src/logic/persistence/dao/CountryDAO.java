package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;

public class CountryDAO {
	
	private CountryDAO() {
		/**/
	}

	public static List<String> selectCountries() throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<String> list = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_COUNTRIES, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.executeStmt(stmt);
			
			if(res.first()) {
				list = new ArrayList<>();
				do {
					list.add(res.getString("name"));
				}while(res.next());
			}
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve list of countries."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}

}
