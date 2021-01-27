package test;

import static org.junit.Assert.*;
import org.junit.Test;

import javafx.collections.ObservableList;
import logic.application.SessionFacade;
import logic.application.control.ApplyToOfferControl;
import logic.application.control.SeekerAccountControl;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.bean.ApplicationBean;

/**
 * @author alison petrilli
 */

public class TestApplyToOfferControl {

	@Test
	public void testApplyNotLogged() {
		ApplicationBean bean = new ApplicationBean();
		bean.setId(1);
		SessionFacade.getSession().setID(null);
		assertThrows(NullPointerException.class, () -> ApplyToOfferControl.getInstance().apply(bean));
	}

	@Test
	public void testApplyDuplicatedApplication() {
		ApplicationBean bean = new ApplicationBean();
		bean.setId(1);
		SessionFacade.getSession().setID((long) 2);
		assertThrows(DatabaseFailureException.class, () -> ApplyToOfferControl.getInstance().apply(bean));
	}
	
	@Test
	public void testApplySuccess() throws DatabaseFailureException {
		ApplicationBean bean = new ApplicationBean();
		bean.setId(2);
		SessionFacade.getSession().setID((long) 2);
		
		ApplyToOfferControl.getInstance().apply(bean);
		ObservableList<ApplicationBean> appl = SeekerAccountControl.getInstance().retrieveApplications();
		
		assertEquals(2, appl.size());
	}
}
