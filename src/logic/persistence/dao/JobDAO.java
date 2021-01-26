package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.domain.Job;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;
import logic.service.Entity;
import logic.service.Factory;
import logic.service.Types;

public class JobDAO {
	
	private JobDAO() {
		/**/
	}

	public static List<Job> selectJobs() throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<Job> list = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_JOBS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.executeStmt(stmt);
			
			if(res.first()) {
				Entity factory = Factory.getInstance().getObject(Types.JOB);
		    	
				list = new ArrayList<>();
				do {
					Job job = (Job)factory.createObject();
					job.setName(res.getString("name"));
					job.setCategory(res.getString("category"));
					job.setId(res.getInt("id"));
					list.add(job);
				}while(res.next());
			}
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve list of jobs."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}

	public static void insertJob(String name, String category) throws SQLException {
		CallableStatement stmt = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.INSERT_JOB);
			RoutinesManager.bindParametersAndExec(stmt, name, category);
			
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to insert a new job."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}
}
