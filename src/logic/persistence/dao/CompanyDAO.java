package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.domain.Address;
import logic.domain.Company;
import logic.domain.Country;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;

public class CompanyDAO {
	
	private CompanyDAO() {
		/**/
	}

	public static Company selectCompanyInfo(Long id) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		Company company = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_COMPANY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, id.intValue());
			
            if (res.first()){           	
            	company = new Company(res.getString("name"));
            	company.setDescription(res.getString("description"));
            	
            	Address head = new Address();
            	
            	Country country = new Country();
            	country.setName(res.getString("head_country"));
            	head.setCountry(country);
            	
            	head.setState(res.getString("head_state"));
            	head.setCity(res.getString("head_city"));
            	head.setPostalCode(res.getInt("head_pc"));
            	head.setStreet(res.getString("head_street"));
            	head.setNumber(res.getInt("head_number"));
            	
            	company.setHeadquarter(head);

            	List<Address> branches = new ArrayList<>();
            	do {
            		Address branch = new Address();
            		
            		Country cBranch = new Country();
            		cBranch.setName(res.getString("country"));
                	branch.setCountry(cBranch);
                	
            		branch.setState(res.getString("state"));
            		branch.setCity(res.getString("city"));
            		branch.setPostalCode(res.getInt("postal_code"));
            		branch.setStreet(res.getString("street"));
            		branch.setNumber(res.getInt("number"));
                	
                	branches.add(branch);
            	}while(res.next());
            	
            	company.setBranches(branches);
            }
           
            res.close();          
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve company information."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return company;
	}

	public static void updateCompanyInfo(Company company, Long id) throws SQLException {
		CallableStatement stmt = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.UPDATE_COMPANY);
        	
        	if(company.getHeadquarter() != null) {
        		RoutinesManager.bindParametersAndExec(stmt, id.toString(), company.getName(), company.getDescription(), 
													  company.getHeadquarter().getCountry().getName(), company.getHeadquarter().getState(), 
													  company.getHeadquarter().getCity(), String.valueOf(company.getHeadquarter().getPostalCode()), 
													  company.getHeadquarter().getStreet(), String.valueOf(company.getHeadquarter().getNumber()));
        	}else {
        		RoutinesManager.bindParametersAndExec(stmt, id.toString(), company.getName(), company.getDescription(), null, null, null, null, null, null);
        	}
			
        	if(company.getBranches() != null) {
        		for(Address i: company.getBranches()) {
            		stmt = conn.prepareCall(RoutinesIdentifier.UPDATE_BRANCHES);
            		RoutinesManager.bindParametersAndExec(stmt, id.toString(), i.getCountry().getName(), i.getState(), i.getCity(), 
            											  String.valueOf(i.getPostalCode()), i.getStreet(), String.valueOf(i.getNumber()));
            	}
        	}
        	
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to update company information."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}

	public static List<Address> selectCompanyBranches(long id) throws SQLException {
		CallableStatement stmt = null;
		ResultSet res = null;
		List<Address> branches = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_COMPANY_BRANCHES, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, (int)id);
			
            if (res.first()){   
            	branches = new ArrayList<>();
            	do {
            		Address branch = new Address();
            		branch.setId(res.getInt("id"));
       
            		branch.setCity(res.getString("city"));
            		branch.setStreet(res.getString("street"));
            		branch.setNumber(res.getInt("number"));
                	
                	branches.add(branch);
            	}while(res.next());
            }
           
            res.close();          
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve company branches."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return branches;
	}

}
