package logic.persistence.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.domain.Address;
import logic.domain.Company;
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
        	stmt = conn.prepareCall(RoutinesIdentifier.FETCH_COMPANY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			res = RoutinesManager.bindParametersAndExec(stmt, id.intValue());
			
            if (res.first()){           	
            	company = new Company();
            	company.setName(res.getString("name"));
            	company.setDescription(res.getString("description"));
            	
            	Address headquarter = new Address();
            	headquarter.setState(res.getString("head_state"));
            	headquarter.setCity(res.getString("head_city"));
            	headquarter.setPostalCode(res.getInt("head_pc"));
            	headquarter.setStreet(res.getString("head_street"));
            	headquarter.setNumber(res.getInt("head_number"));
            	
            	company.setHeadquarter(headquarter);
            	
            	do {
            		Address branch = new Address();
            		branch.setState(res.getString("state"));
            		branch.setCity(res.getString("city"));
            		branch.setPostalCode(res.getInt("postal_code"));
            		branch.setStreet(res.getString("street"));
            		branch.setNumber(res.getInt("number"));
            		company.getBranches().add(branch);
            	}while(res.next());
            }
           
            res.close();          
        } catch (SQLException | ClassNotFoundException e) {
        	throw new SQLException("An error occured while trying to retrieve company information."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return company;
	}

}
