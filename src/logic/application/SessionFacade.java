package logic.application;

import logic.presentation.Screens;

/**Singleton*/
public class SessionFacade {
	
	private static SessionFacade instance = null;

	private Users currUserType;
	private Long accountID;
	private Screens curr;
	private Screens prev;

	private SessionFacade() {
		currUserType = null;
		curr = Screens.MAIN;
	}
	
	public static SessionFacade getSession() {
		if (instance == null) {
			instance = new SessionFacade();
		}		
		return instance;
	}
	
	public Users getCurrUserType() {
		return currUserType;
	}

	public Screens getScreen() {
		return curr;
	}
	
	public void setCurrUserType(Users currUser) {
		this.currUserType = currUser;
	}

	public void setScreen(Screens next) {
		this.prev = this.curr;
		this.curr = next;
	}
	
	public Screens getPrevScreen() {
		return prev;
	}
	
	public Long getID() {
		return accountID;
	}

	public void setID(Long accountID) {
		this.accountID = accountID;
	}
}
