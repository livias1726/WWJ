package logic.application.control;

import java.sql.SQLException;

import logic.application.SessionFacade;
import logic.bean.ApplicationBean;
import logic.domain.Application;
import logic.exceptions.DatabaseFailureException;

public class ApplyToOfferControl {
	
	private static ApplyToOfferControl instance = null;

    private ApplyToOfferControl() {
    	/*Singleton*/
    }

    public static ApplyToOfferControl getInstance() {
        if(instance == null) {
        	instance = new ApplyToOfferControl();
        }

        return instance;
    }

	public void apply(ApplicationBean bean) throws DatabaseFailureException {
		Application appl = new Application(bean.getId());
		try {
			appl.addApplication(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			throw new DatabaseFailureException();
		}
	}
}
