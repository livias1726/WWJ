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
import logic.service.Entity;
import logic.service.Factory;
import logic.service.Types;

public class CompanyDAO {
	
	private CompanyDAO() {
		/**/
	}

	public static Company selectCompany(Company company, Long id) throws SQLException {	
		CallableStatement stmt = null;
		ResultSet res = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_COMPANY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, id.intValue());
			
            if (res.first()){         
            	company.setName(res.getString("name"));
            	company.setDescription(res.getString("description"));

            	Entity factoryAdd = Factory.getInstance().getObject(Types.ADDRESS);
        		Entity factoryCou = Factory.getInstance().getObject(Types.COUNTRY);
            	List<Address> branches = new ArrayList<>();
            	do {
            		Address branch = (Address)factoryAdd.createObject();
            		
            		Country cBranch = (Country)factoryCou.createObject();
            		cBranch.setName(res.getString("country"));
                	branch.setCountry(cBranch);
                	
            		branch.setState(res.getString("state"));
            		branch.setCity(res.getString("city"));
            		branch.setPostalCode(res.getString("postal_code"));
            		branch.setStreet(res.getString("street"));
            		branch.setNumber(res.getInt("number"));
            		branch.setId(res.getInt("id"));
            		
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
        	RoutinesManager.bindParametersAndExec(stmt, id.toString(), company.getName(), company.getDescription());
			
        	if(company.getBranches() != null) {
        		for(Address i: company.getBranches()) {
            		stmt = conn.prepareCall(RoutinesIdentifier.UPDATE_BRANCHES);
            		RoutinesManager.bindParametersAndExec(stmt, 
            			String.valueOf(i.getId()), id.toString(), i.getCountry().getName(), i.getState(), i.getCity(), 
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
}
