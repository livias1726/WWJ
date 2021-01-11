package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.domain.User;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;

public class UserDAO {
	
	private UserDAO() {
		/**/
	}

	public static User selectPersonalInfo(long id) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		User user = null;
		
		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_USER, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, (int)id);
			
            if (res.first()){           	
            	user = new User(res.getString("email"), res.getString("pwd"), res.getString("first_name"), res.getString("last_name"));
            	user.setCity(res.getString("city"));

            	if(res.getDate("birth") != null) {
            		user.setBirth(res.getDate("birth").toLocalDate());
            	}
            }

            res.close();          
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve personal information."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
		
        return user;
	}
	
	public static void updatePersonalInfo(User user, Long id) throws SQLException {
		CallableStatement stmt = null;
		
		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.UPDATE_USER);
        	
        	if(user.getBirth() != null) {
        		RoutinesManager.bindParametersAndExec(stmt, user.getEmail(), user.getPwd(), user.getFirstName(), user.getLastName(), user.getCity(), user.getBirth().toString(), id.toString());
        	}else {
        		RoutinesManager.bindParametersAndExec(stmt, user.getEmail(), user.getPwd(), user.getFirstName(), user.getLastName(), user.getCity(), null, id.toString());
        	}
			
			     
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to update personal information."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}
   
}