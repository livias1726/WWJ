package logic.application.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.application.SessionFacade;
import logic.domain.Application;
import logic.domain.CV;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.bean.ApplicationBean;
import logic.presentation.bean.CVBean;
import logic.service.Entity;
import logic.service.ApplicationFactory;
import logic.service.Factory;
import logic.service.Types;

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
		Entity factory = Factory.getInstance().getObject(Types.APPLICATION);
		Application app = (Application)factory.createObject();

		try {
			List<Application> list = app.getApplications(SessionFacade.getSession().getID());
			ObservableList<ApplicationBean> dest = FXCollections.observableArrayList();
			for(ApplicationBean i: ((ApplicationFactory)factory).extractApplicationBeanList(list)) {
				dest.add(i);
			}
			return dest;
		} catch (SQLException se) {
			Logger.getLogger(SeekerAccountControl.class.getName()).log(Level.SEVERE, null, se);
			throw new DatabaseFailureException();
		}
	}

	public void removeApplications(List<Integer> selected) throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.APPLICATION);
		Application app = (Application)factory.createObject();
		
		try {
			app.removeApplicatinosFromDB(SessionFacade.getSession().getID(), selected);
		} catch (SQLException e) {
			Logger.getLogger(SeekerAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException(); 
		}
	}

	public CVBean retrieveCV(CVBean cvBean, Long accountID) throws DatabaseFailureException, NoResultFoundException {
		Entity factory = Factory.getInstance().getObject(Types.CV);
		CV cv = (CV)factory.createObject();
		try {
			cv.getCVFromDB(accountID);
			cvBean.setCv(cv.getCvDoc());
		} catch (SQLException | IOException e) {
			Logger.getLogger(SeekerAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException(); 
		} catch (NoResultFoundException e) {
			throw new NoResultFoundException("No CV document was found. Do you want to upload it?");
		}

		return cvBean;
	}

	public void updateCV(File curr) throws DatabaseFailureException {
		Entity factory = Factory.getInstance().getObject(Types.CV);
		CV cv = (CV)factory.createObject();
		try {
			cv.saveCV(curr, SessionFacade.getSession().getID());
		} catch (SQLException | IOException e) {
			Logger.getLogger(SeekerAccountControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException(); 
		}
	}
}
