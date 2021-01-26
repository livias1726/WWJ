package logic.application.control;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.application.SessionFacade;
import logic.domain.Application;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.ApplicationBean;
import logic.service.Entity;
import logic.service.Factory;
import logic.service.Types;

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
		Entity factory = Factory.getInstance().getObject(Types.APPLICATION);
		Application appl = (Application)factory.createObject();
		appl.setId(bean.getId());
		try {
			appl.addApplication(SessionFacade.getSession().getID());
		} catch (SQLException e) {
			Logger.getLogger(ApplyToOfferControl.class.getName()).log(Level.SEVERE, null, e);
			throw new DatabaseFailureException();
		}
	}
}
