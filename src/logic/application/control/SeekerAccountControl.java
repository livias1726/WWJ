package logic.application.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.application.SessionFacade;
import logic.bean.ApplicationBean;
import logic.bean.JobBean;
import logic.domain.Application;
import logic.exceptions.DatabaseFailureException;

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

	public List<ApplicationBean> retrieveApplications() throws DatabaseFailureException {
		Application app = new Application();
    	List<Application> list;
		try {
			list = app.getApplications(SessionFacade.getSession().getID());
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    	
		return modelToBean(list);
	}

	private List<ApplicationBean> modelToBean(List<Application> src) {
		List<ApplicationBean> dest = new ArrayList<>();
		for(Application i: src) {
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
}
