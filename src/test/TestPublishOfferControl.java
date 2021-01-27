package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import logic.application.control.PublishOfferControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.bean.JobBean;
import logic.presentation.bean.OfferBean;

/**
 * @author dominique toce
 */

public class TestPublishOfferControl {

	@Test
	public void testAddJobInvalidField() {
		JobBean job = new JobBean();
		job.setName("Only name");
		
		assertThrows(InvalidFieldException.class, () -> job.verifyFields(job.getName(), job.getCategory()));
	}
		
	@Test
	public void testPublishOfferInvalidField() {
		OfferBean offer = new OfferBean();
		
    	offer.setStart("12:00");
    	offer.setFinish("11:00");
		
		assertThrows(InvalidFieldException.class, () -> offer.verifyFieldsValidity(offer.getStart(), offer.getFinish(), null));
	}
	
	@Test
	public void testAddJobSuccess() throws DatabaseFailureException {
		JobBean job = new JobBean();
		job.setName("NewJobName");
		job.setCategory("NewJobCategory");
		
		List<JobBean> baseList = PublishOfferControl.getInstance().retrieveJobs();
		
		PublishOfferControl.getInstance().saveNewJob(job);
		
		List<JobBean> newList = PublishOfferControl.getInstance().retrieveJobs();

		int exp = baseList.size() + 1;
		
		assertEquals(exp, newList.size());
	}

}
