package logic.application.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.application.SessionFacade;
import logic.bean.ApplicationBean;
import logic.bean.CVBean;
import logic.bean.JobBean;
import logic.bean.OfferBean;
import logic.domain.Application;
import logic.domain.CV;
import logic.domain.Offer;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;

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
    	
		List<ApplicationBean> dest = new ArrayList<>();
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

	public List<OfferBean> retrieveFavourites() throws DatabaseFailureException {
		Offer offer = new Offer();
    	List<Offer> list;
		try {
			list = offer.getFavourites(SessionFacade.getSession().getID().intValue());
		} catch (SQLException se) {
			throw new DatabaseFailureException();
		}
    	
		List<OfferBean> dest = new ArrayList<>();
    	for(Offer i: list) {
    		OfferBean offerBean = new OfferBean();  		
    		offerBean.setCompanyName(i.getCompanyName());
    		offerBean.setTaskDescription(i.getTaskDescription());

    		JobBean job = new JobBean();
    		job.setName(i.getPosition().getName());
    		job.setCategory(i.getPosition().getCategory());  		
    		offerBean.setPosition(job);
    		
    		dest.add(offerBean);
    	}
    	
    	return dest;
	}
}
