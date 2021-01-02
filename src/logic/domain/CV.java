package logic.domain;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import logic.exceptions.NoResultFoundException;
import logic.persistence.dao.CvDAO;

public class CV implements Serializable {

	private static final long serialVersionUID = 2228197502878008337L;
	
	private File cvDoc;
	
	public File getCvDoc() {
		return cvDoc;
	}

	public void setCvDoc(File file) {
		this.cvDoc = file;
	}

	public CV getCVFromDB(Integer id) throws SQLException, IOException, NoResultFoundException {
		return CvDAO.selectCVInfo(id);
	}

	public void saveCV(File cv, String id) throws IOException, SQLException {
		CvDAO.updateCVInfo(cv, id);
	}

}
