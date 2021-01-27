package logic.persistence.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.FailedLoginException;
import javax.sql.rowset.serial.SerialBlob;

import logic.application.Users;
import logic.domain.Account;
import logic.domain.User;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;
import logic.service.Entity;
import logic.service.Factory;
import logic.service.Types;

public class AccountDAO {
	
	private AccountDAO() {
		/**/
	}
	
	public static Account selectAccount(Account account) throws SQLException, IOException {
		CallableStatement stmt = null;
		ResultSet res = null;
		
		try{
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_ACCOUNT, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        	
			res = RoutinesManager.bindParametersAndExec(stmt, (int)account.getID());
			
            if (res.first()){
            	Entity factory = Factory.getInstance().getObject(Types.USER);
        		User user = (User)factory.createObject();
                user.setFirstName(res.getString("first_name"));
                user.setLastName(res.getString("last_name"));

                account.setUser(user);
                account.setType(Users.stringToUsers(res.getString("role")));
                account.setPremium(res.getBoolean("premium"));

                Blob blob = res.getBlob("pic");
                if(blob != null) {
                	byte [] array = blob.getBytes(1, (int)blob.length());
        		    File file = new File("pic.jpg");
        		    try(FileOutputStream out = new FileOutputStream(file)){
        		    	out.write(array);
        			    account.setPic(file);
        		    }
                }
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

	public static Account executeLogin(Account account) throws FailedLoginException, SQLException {
    	CallableStatement stmt = null;
		ResultSet res = null;
        
        try {
        	Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.LOGIN, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, account.getUser().getEmail(), account.getUser().getPwd());
			
            if (!res.first()){           	
            	throw new FailedLoginException("Login failed");
            }
            
            res.first();
            account.getUser().setFirstName(res.getString("first_name"));
            account.getUser().setLastName(res.getString("last_name"));
            account.setType(Users.stringToUsers(res.getString("role")));
            account.setID(res.getInt("id"));
            account.setPremium(res.getBoolean("premium"));
            
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
	        	if(e.getSQLState() != null) {
	        		throw e;
	        	}
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

	public static void updatePic(File img, Long id) throws IOException, SQLException {
		CallableStatement stmt = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.UPDATE_PIC);

        	byte[] fileContent = Files.readAllBytes(img.toPath());
        	Blob blob = new SerialBlob(fileContent);
        	
        	RoutinesManager.bindParametersAndExec(stmt, id.intValue(), blob);
        	
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to update the profile pic."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}
}
