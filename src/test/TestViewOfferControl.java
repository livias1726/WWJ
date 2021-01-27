package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.application.control.ViewOfferControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.bean.CountryBean;
import logic.presentation.bean.JobBean;

/**
 * @author dominique toce
 */

public class TestViewOfferControl {

	@Test
	public void testRetrieveOffersByJobNoResult() {
		JobBean noJob = new JobBean();
		noJob.setId(0);
		
		assertThrows(NoResultFoundException.class, () -> ViewOfferControl.getInstance().retrieveOffersByJob(noJob));
	}
	
	@Test
	public void testRetrieveOffersByCountryNoResult() throws DatabaseFailureException {
		CountryBean noCountry = new CountryBean();
		noCountry.setName("NoCountry");
		
		try {
			ViewOfferControl.getInstance().retrieveOffersByCountry(noCountry);
			fail("Exception not thrown");
		}catch(NoResultFoundException e) {
			assertEquals("No results found.", e.getMessage());
		}
		
	}

}
