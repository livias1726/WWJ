package logic.bean;

import java.io.File;

import logic.application.control.SeekerAccountControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;

public class CVBean {

	private File cv;
	
	public File getCv() {
		return cv;
	}

	public void setCv(File cv) {
		this.cv = cv;
	}
	
	public CVBean getCVFile(Long accountID) throws DatabaseFailureException, NoResultFoundException {
		return SeekerAccountControl.getInstance().retrieveCV(accountID);
	}

	public void uploadNewCV(File curr) throws DatabaseFailureException {
		SeekerAccountControl.getInstance().updateCV(curr); 
	}
}
