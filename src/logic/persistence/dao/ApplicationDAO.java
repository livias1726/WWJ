package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.domain.Application;
import logic.domain.Job;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;
import logic.service.AbstractFactory;
import logic.service.Factory;
import logic.service.Types;

public class ApplicationDAO {
	
	private ApplicationDAO() {
		/**/
	}

	public static List<Application> selectSeekersApplications(long id) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<Application> list = new ArrayList<>();

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_APPLICATIONS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, (int)id);
			
            if (res.first()){ 
            	AbstractFactory factory = Factory.getInstance().getObject(Types.APPLICATION);
            	do {
            		Application item = (Application)factory.createObject();
                	item.setId(res.getInt("id"));
                	
                	Job position = new Job();
                	position.setName(res.getString("position"));
                	item.setPosition(position);
                	
                	item.setApplication(res.getDate("application").toLocalDate());
                	item.setExpiration(res.getDate("expiration").toLocalDate());
                	
                	list.add(item);
                }while(res.next());
            }

            res.close();          
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve seeker's applications."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
		
        return list;
	}

	public static void deleteSeekersApplications(Long id, List<Integer> selected) throws SQLException {
		CallableStatement stmt = null;
		
		try{
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.DELETE_APPLICATION);	
        	
        	for(int i=0; i<selected.size(); i++) {
        		RoutinesManager.bindParametersAndExec(stmt, id.intValue(), selected.get(i));
        	}
        	
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to delete applications."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}

	public static void insertSeekersApplication(int idApp, long idSeek) throws SQLException {
		CallableStatement stmt = null;
		
		try{
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.INSERT_APPLICATION);	
        	RoutinesManager.bindParametersAndExec(stmt, idApp, (int)idSeek);
			
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to insert an application."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}

}
