package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.login.FailedLoginException;

import logic.application.Users;
import logic.domain.Account;
import logic.domain.User;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;

public class UserDAO {
	
	private UserDAO() {
		/**/
	}
    
    public static Account loginToSystem(String email, String password) throws FailedLoginException, SQLException {
    	CallableStatement stmt = null;
		ResultSet res = null;
		Account account = null;
        
        try (Connection conn = ConnectionManager.getConnection()){
        	
        	stmt = conn.prepareCall(RoutinesIdentifier.LOGIN);
			res = RoutinesManager.bindParametersAndExec(stmt, email, password);
			
            if (!res.first()){           	
            	throw new FailedLoginException("Login failed");
            }
            
            long id = res.getInt("id");
            User user = new User(res.getString("user"), password);
            Users type; 
            switch(res.getInt("type")) {
            	case 0:
            		type = Users.SEEKER;
            		break;
            	case 1:
            		type = Users.RECRUITER;
            		break;
            	case 2:
            		type = Users.ENTREPRENEUR;
            		break;
            	default:
            		type = Users.ADMIN;
            		break;
            }
            boolean premium = res.getBoolean("premium");
            
            account = new Account(user, type, id);
            account.setPremium(premium);
            
            res.close();
           
        } catch (SQLException | ClassNotFoundException e) {
        	throw new SQLException("Database error."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return account;
    }
}