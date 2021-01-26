package logic.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import logic.domain.User;
import logic.presentation.bean.UserBean;

public class UserFactory implements Entity {

	@Override
    public User createObject() {
    	return new User();
    }

	public UserBean extractUserBean(User user) {
		UserBean bean = new UserBean();
		
    	bean.setEmail(user.getEmail());
    	bean.setPassword(user.getPwd());
    	bean.setFirstName(user.getFirstName());
    	bean.setLastName(user.getLastName());
    	bean.setCity(user.getCity());
    	if(user.getBirth() != null) {
    		bean.setBirth(user.getBirth().toString());
    	}
    	
    	return bean;
	}
	
	public User extractUser(UserBean bean) {
		User user = new User();
		
		user.setEmail(bean.getEmail());
		user.setPwd(bean.getPassword());
		user.setFirstName(bean.getFirstName());
		user.setLastName(bean.getLastName());
		user.setCity(bean.getCity());
		user.setBirth(LocalDate.parse(bean.getBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    	
    	return user;
	}
}
