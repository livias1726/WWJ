package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.domain.Business;
import logic.domain.BusinessInCountry;
import logic.domain.Country;
import logic.exceptions.NoResultFoundException;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;
import logic.service.Entity;
import logic.service.Factory;
import logic.service.Types;

public class BusinessDAO {
	
	private BusinessDAO() {
		/**/
	}

	public static List<Business> selectBusinesses() throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<Business> list = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_BUSINESSES, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.executeStmt(stmt);
			
			if(res.first()) {
				Entity factory = Factory.getInstance().getObject(Types.BUSINESS);
				list = new ArrayList<>();
				do {
					Business bus = (Business)factory.createObject();
					bus.setId(res.getInt("id"));
					bus.setName(res.getString("name"));
					bus.setCategory(res.getString("category"));
					list.add(bus);
				}while(res.next());
			}
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve list of businesses."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}

	public static List<BusinessInCountry> selectBusinessInCountry(String country, String bus) throws SQLException, NoResultFoundException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<BusinessInCountry> list = new ArrayList<>();

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_BUSINESS_IN_COUNTRY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, country, bus);
			
			processResearch(res, list);
			
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve list of businesses in country."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}

	public static List<BusinessInCountry> selectBusinessByCountry(String country) throws SQLException, NoResultFoundException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<BusinessInCountry> list = new ArrayList<>();

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_BUSINESS_BY_COUNTRY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, country);
			
			processResearch(res, list);
			
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve list of businesses by country."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}

	public static List<BusinessInCountry> selectBusinessByCategory(String business) throws SQLException, NoResultFoundException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<BusinessInCountry> list = new ArrayList<>();

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_BUSINESS_BY_CATEGORY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, business);
			
			processResearch(res, list);
			
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve list of businesses."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}
	
	public static List<BusinessInCountry> selectFavourites(Long id) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<BusinessInCountry> list = new ArrayList<>();
		
		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_FAVOURITE_BUSINESSES, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, id.intValue());
			
			processResearch(res, list);
			
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve favourite businesses."); 
		} catch (NoResultFoundException e) {
			/*No op*/
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return list;
	}
	
	private static void processResearch(ResultSet res, List<BusinessInCountry> list) throws NoResultFoundException, SQLException {
		if(!res.first()) {
			throw new NoResultFoundException();
		}

		res.first();
		Entity factoryBus = Factory.getInstance().getObject(Types.BUSINESSINCOUNTRY);
		Entity factoryCou = Factory.getInstance().getObject(Types.COUNTRY);

		do {

			BusinessInCountry business = (BusinessInCountry)factoryBus.createObject();
			business.setId(res.getInt("id"));
			business.setName(res.getString("name"));
			business.setCategory(res.getString("category"));
			business.setDescription(res.getString("description"));
		
			Country country = (Country)factoryCou.createObject();
			country.setName(res.getString("country"));
			country.setCurrency(res.getString("currency"));
			business.setCountry(country);
			
			business.setAverageEarnings(res.getFloat("average_earnings"));
			business.setAverageCost(res.getFloat("average_costs"));
			
			list.add(business);
		}while(res.next());
	}
	
	public static void insertIntoFavourite(int idFav, long idEnt) throws SQLException {
		CallableStatement stmt = null;
		
		try{
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.INSERT_FAVOURITE_BUSINESS);	
        	RoutinesManager.bindParametersAndExec(stmt, idFav, (int)idEnt);
			
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to insert a business to the favourite ones.");
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}

	public static void deleteFromFavourite(int idFav, long idEnt) throws SQLException {
		CallableStatement stmt = null;

		try{
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.DELETE_FAVOURITE_BUSINESS);	
        	RoutinesManager.bindParametersAndExec(stmt, idFav, (int)idEnt);
			
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to delete a business from the favourite ones."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}

	public static void selectBusinessStatistics(BusinessInCountry business) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_STATISTICS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        	res = RoutinesManager.bindParametersAndExec(stmt, String.valueOf(business.getId()), business.getCountry().getName());

			if(res.first()) {
				business.setAverageEarnings(res.getFloat("average_earnings"));
				business.setAverageCost(res.getFloat("average_costs"));
				
				List<Float> pop = new ArrayList<>();
				List<Integer> comp = new ArrayList<>();
				do {
					pop.add(res.getFloat("percentage"));
					comp.add(res.getInt("num_competitors"));
				}while(res.next());
				
				business.setPopularity(pop);
				business.setCompetitors(comp);
			}

			res.close();
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve the statistics."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}

	public static void selectBusinessFeasibility(BusinessInCountry business) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_FEASIBILITY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        	res = RoutinesManager.bindParametersAndExec(stmt, String.valueOf(business.getId()), business.getCountry().getName());

        	List<Float> taxes = new ArrayList<>();
			if(res.first()) {
				business.getCountry().setLivingExpense(res.getFloat("expense"));
				business.getCountry().setExampleCity(res.getString("example_city"));
				business.setStartExpense(res.getFloat("start"));
				taxes.add(res.getFloat("income"));
				taxes.add(res.getFloat("corporate"));
				taxes.add(res.getFloat("capital_gains"));
				taxes.add(res.getFloat("sales"));
				taxes.add(res.getFloat("property"));
				
				business.setTaxes(taxes);
			}

			res.close();
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve the statistics."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}
}
