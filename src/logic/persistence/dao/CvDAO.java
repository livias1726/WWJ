package logic.persistence.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import logic.domain.CV;
import logic.exceptions.NoResultFoundException;
import logic.persistence.ConnectionManager;
import logic.persistence.RoutinesIdentifier;
import logic.persistence.RoutinesManager;
import logic.service.AbstractFactory;
import logic.service.Factory;
import logic.service.Types;

public class CvDAO {
	
	private CvDAO() {
		/**/
	}

	public static CV selectCVInfo(long accountID) throws SQLException, IOException, NoResultFoundException {
		CallableStatement stmt = null;
		ResultSet res = null;
		
		AbstractFactory factory = Factory.getInstance().getObject(Types.CV);
		CV cv = (CV)factory.createObject();
		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.GET_CV);
			res = RoutinesManager.bindParametersAndExec(stmt, (int)accountID);
			
			if(!res.first()) {
				throw new NoResultFoundException();
			}
			
			Blob blob = res.getBlob("document");

			byte [] array = blob.getBytes(1, (int)blob.length());
		    File file = new File("temp_cv.pdf");
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

	public static void updateCVInfo(File cv, Long id) throws IOException, SQLException {
		CallableStatement stmt = null;

		try {
			Connection conn = ConnectionManager.getConnection();
        	stmt = conn.prepareCall(RoutinesIdentifier.UPDATE_CV);

        	byte[] fileContent = Files.readAllBytes(cv.toPath());
        	Blob blob = new SerialBlob(fileContent);
        	
        	RoutinesManager.bindParametersAndExec(stmt, id.intValue(), blob);
        	
        } catch (SQLException e) {
        	throw new SQLException("An error occured while trying to update the cv."); 
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}
}
