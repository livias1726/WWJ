package logic.application.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.application.SessionFacade;
import logic.domain.Application;
import logic.domain.CV;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.bean.ApplicationBean;
import logic.presentation.bean.CVBean;
import logic.presentation.bean.JobBean;

public class SeekerAccountControl {

	private static SeekerAccountControl instance = null;

    private SeekerAccountControl() {
    	/*Default constructor*/
    }

    public static SeekerAccountControl getInstance() {
        if(instance == null) {
        	instance = new SeekerAccountControl();
        }

        return instance;
    }

	public ObservableList<ApplicationBean> retrieveApplications() throws DatabaseFailureException {
		Application app = new Application();
    	List<Application> list;
		try {
			list = app.getApplications(SessionFacade.getSession().getID());
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    	
		ObservableList<ApplicationBean> dest = FXCollections.observableArrayList();
		for(Application i: list) {
			ApplicationBean bean = new ApplicationBean();
			bean.setId(i.getId());
			
			JobBean job = new JobBean();
			job.setName(i.getPosition().getName());
			bean.setPosition(job);
			
			bean.setApplication(i.getApplication());
			bean.setExpiration(i.getExpiration());
			
			dest.add(bean);
		}
		return dest;
	}

	public void removeApplications(List<Integer> selected) throws DatabaseFailureException {
		Application app = new Application();
		try {
			app.removeApplicatinosFromDB(selected);
		} catch (SQLException e) {
			throw new DatabaseFailureException(); 
		}
	}

	public CVBean retrieveCV(Long accountID) throws DatabaseFailureException, NoResultFoundException {
		CVBean bean = new CVBean();
		try {
			CV cv = new CV().getCVFromDB(accountID);
			bean.setCv(cv.getCvDoc());
		} catch (SQLException | IOException e) {
			throw new DatabaseFailureException(); 
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException("No CV document was found. Do you want to upload it?");
		}

		return bean;
	}

	public void updateCV(File curr) throws DatabaseFailureException {
		try {
			new CV().saveCV(curr, SessionFacade.getSession().getID().toString());
		} catch (SQLException | IOException e) {
			throw new DatabaseFailureException(); 
		}
	}
}
