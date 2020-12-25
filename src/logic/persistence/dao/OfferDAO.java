package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import logic.domain.Address;
import logic.domain.Job;
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
		List<Offer> list = new ArrayList<>();

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_OFFERS_BY_COUNTRY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, country);
			
			processResearch(res, list);     
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
		List<Offer> list = new ArrayList<>();

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_OFFERS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, country, job);
			
			processResearch(res, list);        
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
		List<Offer> list = new ArrayList<>();

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_OFFERS_BY_JOB, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, job);
			
            processResearch(res, list);
        } catch (SQLException | ClassNotFoundException e) {
        	throw new SQLException("An error occured while trying to retrieve search by job results."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}
	
	private static void processResearch(ResultSet res, List<Offer> list) throws SQLException, NoResultFoundException{
		if (!res.first()){           	
        	throw new NoResultFoundException();
        }
        
        res.first();
        
        do {
        	Offer offer = new Offer();
        	offer.setCompanyName(res.getString("company"));
        	
        	Job position = new Job();
        	position.setName(res.getString("position"));
        	offer.setPosition(position);
        	
        	offer.setTaskDescription(res.getString("description"));
        	
        	list.add(offer);
        }while(res.next());
        
        res.close();          
	}

	public static List<Offer> selectPublishedOffers(Long id) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<Offer> list = new ArrayList<>();

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_RECRUITERS_OFFERS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, id.intValue());
			
            if (res.first()){           	
            	do {
                	Offer item = new Offer();
                	item.setId(res.getInt("id"));
                	
                	Job position = new Job();
                	position.setName(res.getString("name"));
                	item.setPosition(position);
                	
                	item.setUpload(res.getDate("upload").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                	item.setExpiration(res.getDate("expiration").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                	item.setCandidates(res.getInt("id_account"));
                	
                	list.add(item);
                }while(res.next());
            }

            res.close();          
        } catch (SQLException | ClassNotFoundException e) {
        	throw new SQLException("An error occured while trying to retrieve recruiter's offers."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}

	public static Offer selectOffer(int id) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		Offer offer = new Offer();

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_OFFER, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, id);
			
            if (res.first()){           	
            	offer.setCompanyName(res.getString("company"));
            	
            	Job job = new Job();
            	job.setName(res.getString("position"));
            	offer.setPosition(job);
            	
            	offer.setTaskDescription(res.getString("description"));
            	
            	Address branch = new Address();
            	branch.setState(res.getString("state"));
            	branch.setCity(res.getString("city"));
            	branch.setPostalCode(res.getInt("postal_code"));
            	branch.setStreet(res.getString("street"));
            	branch.setNumber(res.getInt("number"));
            	offer.setBranch(branch);
            	
            	offer.setStart(res.getTime("start"));
            	offer.setFinish(res.getTime("finish"));
            	offer.setBaseSalary(res.getFloat("base_salary"));
            	offer.setExpiration(res.getDate("expiration").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            	
            	List<String> requirements = new ArrayList<>();
            	do {
            		requirements.add(res.getString("requirement"));
            	}while(res.next());
            }

            res.close();          
        } catch (SQLException | ClassNotFoundException e) {
        	throw new SQLException("An error occured while trying to retrieve offer details."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return offer;
	}

}
