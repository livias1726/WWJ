package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

import logic.domain.User;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;

public class UserDAO {
	
	private UserDAO() {
		/**/
	}

	public static User selectPersonalInfo(Integer id) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		User user = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_USER);
			res = RoutinesManager.bindParametersAndExec(stmt, id);
			
            if (res.first()){           	
            	user = new User(res.getString("email"), res.getString("pwd"), res.getString("first_name"), res.getString("last_name"));
            	user.setCity(res.getString("city"));
            	user.setBirth(res.getDate("birth").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
            
            res.close();          
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve personal information."); 
        }catch(NumberFormatException ne) {
        	ne.printStackTrace();
        	System.exit(0);
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
			RoutinesManager.bindParametersAndExec(stmt, user.getEmail(), user.getPwd(), user.getFirstName(), user.getLastName(), user.getCity(), user.getBirth().toString(), id.toString());
			     
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to update personal information."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}
   
}