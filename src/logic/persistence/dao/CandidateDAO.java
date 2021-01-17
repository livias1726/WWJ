package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.domain.Candidate;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;
import logic.service.AbstractFactory;
import logic.service.Factory;
import logic.service.Types;

public class CandidateDAO {
	
	private CandidateDAO() {
		/**/
	}

	public static List<Candidate> selectCandidates(Long id) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<Candidate> list = new ArrayList<>();
		
		try{
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_CANDIDATES, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       	
			res = RoutinesManager.bindParametersAndExec(stmt, id.intValue());
			
            if (res.first()){
            	AbstractFactory factory = Factory.getInstance().getObject(Types.CANDIDATE);
        		
            	do {
            		Candidate cand = (Candidate)factory.createObject();
            		cand.setSeeker(Long.valueOf(res.getInt("candidate")));
            		cand.setOffer(res.getInt("offer"));
            		cand.setName(res.getString("first_name") + " " + res.getString("last_name"));
                	list.add(cand);
            	}while(res.next());           	
            }
            
            res.close();
           
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve the candidates list."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}

	public static void deleteCandidates(List<Long> candidates, List<Integer> offers) throws SQLException {
		CallableStatement stmt = null;
		
		try{
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.DELETE_APPLICATION, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);	
        	
        	for(int i=0; i<candidates.size(); i++) {
        		RoutinesManager.bindParametersAndExec(stmt, candidates.get(i).intValue(), offers.get(i));
        	}
			
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to delete candidates.");
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}

}
