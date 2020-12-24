package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import logic.domain.Offer;
import logic.exceptions.NoResultFoundException;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;

public class OfferDAO {

	private OfferDAO() {
		/**/
	}
	
	public static List<Offer> selectByPlace(String country) throws NoResultFoundException, SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<Offer> list = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_OFFERS_BY_COUNTRY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, country);
			
            if (!res.first()){           	
            	throw new NoResultFoundException();
            }
            
            res.first();
            
            do {
            	/*fetch results*/
            }while(res.next());
            
            res.close();          
        } catch (SQLException | ClassNotFoundException e) {
        	throw new SQLException("An error occured while trying to retrieve search by country results."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}

	public static List<Offer> selectOffers(String country, String job) throws NoResultFoundException, SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<Offer> list = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_OFFERS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, country, job);
			
            if (!res.first()){           	
            	throw new NoResultFoundException();
            }
            
            res.first();
            
            do {
            	/*fetch results*/
            }while(res.next());
            
            res.close();          
        } catch (SQLException | ClassNotFoundException e) {
        	throw new SQLException("An error occured while trying to retrieve offers search results."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}

	public static List<Offer> selectByJob(String job) throws NoResultFoundException, SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<Offer> list = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_OFFERS_BY_JOB, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, job);
			
            if (!res.first()){           	
            	throw new NoResultFoundException();
            }
            
            res.first();
            
            do {
            	/*fetch results*/
            }while(res.next());
            
            res.close();          
        } catch (SQLException | ClassNotFoundException e) {
        	throw new SQLException("An error occured while trying to retrieve search by job results."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}

}
