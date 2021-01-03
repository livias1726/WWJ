package logic.persistence.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.domain.CV;
import logic.exceptions.NoResultFoundException;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;

public class CvDAO {
	
	private CvDAO() {
		/**/
	}

	public static CV selectCVInfo(long accountID) throws SQLException, IOException, NoResultFoundException {
		CallableStatement stmt = null;
		ResultSet res = null;
		CV cv = new CV();

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_CV);
			res = RoutinesManager.bindParametersAndExec(stmt, (int)accountID);
			
			if(!res.first()) {
				throw new NoResultFoundException();
			}
			
			Blob blob = res.getBlob("document");			
			byte [] array = blob.getBytes(1, (int)blob.length());
		    File file = File.createTempFile("cv", ".binary", new File("../../presentation/resources/temp"));
		    try(FileOutputStream out = new FileOutputStream(file)){
		    	out.write(array);
			    cv.setCvDoc(file);
		    }
		    
            res.close(); 
            
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to retrieve the cv."); 
 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
        
        return cv;
	}

	public static void updateCVInfo(File cv, String id) throws IOException, SQLException {
		CallableStatement stmt = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.UPDATE_CV);
			
        	byte[] fileContent = new byte[(int)cv.length()];

    	    try (FileInputStream is = new FileInputStream(cv)){
    	    	while(is.read(fileContent) > 0);
    	    	RoutinesManager.bindParametersAndExec(stmt, id, is.toString());
    	    }

        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to update the cv."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}
}
