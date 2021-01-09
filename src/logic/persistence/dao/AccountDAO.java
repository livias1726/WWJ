package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import logic.application.Users;
import logic.domain.Account;
import logic.domain.User;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;

public class AccountDAO {
	
	private AccountDAO() {
		/**/
	}
	
	public static Account selectAccount(long id) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		Account account = null;
		
		try{
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_ACCOUNT, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        	
			res = RoutinesManager.bindParametersAndExec(stmt, (int)id);
			
            if (res.first()){           	
            	long idA = res.getInt("id");
     
                User user = new User();
                user.setFirstName(res.getString("first_name"));
                user.setLastName(res.getString("last_name"));
                
                Users type = Users.stringToUsers(res.getString("role"));
                
                boolean premium = res.getBoolean("premium");
                
                account = new Account(user, type, idA);
                account.setPremium(premium);
            }

            res.close();
            
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve the account."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return account;
	}

	public static Account executeLogin(String email, String password) throws FailedLoginException, SQLException {
    	CallableStatement stmt = null;
		ResultSet res = null;
		Account account = null;
        
        try {
        	Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.LOGIN, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, email, password);
			
            if (!res.first()){           	
            	throw new FailedLoginException("Login failed");
            }
            
            res.first();
            
            long id = res.getInt("id");
            User user = new User(email, password, res.getString("first_name"), res.getString("last_name"));
            Users type = Users.stringToUsers(res.getString("role"));
            boolean premium = res.getBoolean("premium");
            
            account = new Account(user, type, id);
            account.setPremium(premium);
            
            res.close();
           
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to login."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return account;
    }
	
	 public static boolean createAccount(String email, String password, String firstName, String lastName, String role) throws SQLException{
	    	CallableStatement stmt = null;
	    	boolean ret = false;
	        
	        try {
	        	Connection conn = ConnectionManager.getConnection();
	        	stmt = conn.prepareCall(RoutinesIdentifier.SIGNUP);	        	
				RoutinesManager.bindParametersAndExec(stmt, email, password, firstName, lastName, role);
			    
				ret = true;
	            
	        } catch (SQLException e) {
	        	throw new SQLException("An error occured while trying to create the account."); 
			} finally {
				if(stmt != null) {
					stmt.close();
				}
			}

	        return ret;
	    }

	public static List<String> selectNotifications(long id) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<String> notif = new ArrayList<>();
		
		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_NOTIFICATIONS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        	
			res = RoutinesManager.bindParametersAndExec(stmt, (int)id);
			
            if (res.first()){           	
            	do {
            		notif.add(res.getString("content"));
            	}while(res.next());
            }
           
            res.close();          
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve the notifications."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return notif;
	}
}
