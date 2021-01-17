package logic.service;

import logic.domain.User;
import logic.presentation.bean.UserBean;

public class UserFactory implements AbstractFactory {

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
    	bean.setBirth(user.getBirth());
    	
    	return bean;
	}
}
