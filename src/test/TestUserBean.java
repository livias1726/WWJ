package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.exceptions.BadAddressException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.bean.UserBean;

public class TestUserBean {

	@Test
	public void testInvalidFieldException() {
		UserBean bean = new UserBean();
		bean.setEmail("example@email.it");
		bean.setPassword("W34Kpass");
		
		
		assertThrows(InvalidFieldException.class, () -> bean.verifyEqualFields("example@email.it", "W34KpasS"));
	}
	
	@Test
	public void testBadAddressExceptionNotThrown() {
		UserBean bean = new UserBean();
		bean.setEmail("valid_email84@domain.org");
		
		boolean res;
		try {
			bean.verifySyntax();
			res = true;
		}catch(BadAddressException e) {
			res = false;
		}
		
		assertTrue(res);
	}
	
	@Test
	public void testBadAddressExceptionThrown() {
		UserBean bean = new UserBean();
		bean.setEmail("invalid_email84@domainnotprovided");
		
		try {
			bean.verifySyntax();
			fail("Exception not thrown");
		}catch(BadAddressException e) {
			assertEquals("The email address is not valid", e.getMessage());
		}
	}

}
