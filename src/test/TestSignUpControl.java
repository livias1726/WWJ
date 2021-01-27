package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.application.control.SignUpControl;
import logic.exceptions.DuplicatedUserException;
import logic.presentation.bean.AccountBean;
import logic.presentation.bean.UserBean;

public class TestSignUpControl {

	@Test
	public void testTrySignUpDuplicatedUser() {
		AccountBean acc = new AccountBean();
		UserBean us = new UserBean();
		
		us.setEmail("tom@gmail.com"); //Primary key for users: the only field that's duplicated
		us.setPassword("nonPassword");
		us.setFirstName("OtherName");
		us.setLastName("OtherLastName");
		
		acc.setUser(us);
		acc.setType("SEEKER");
		
		assertThrows(DuplicatedUserException.class, () -> SignUpControl.getInstance().trySignUp(acc));
	}

}
