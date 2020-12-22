package logic.application;

import logic.presentation.Scenes;

/**Singleton*/
public class SessionFacade {
	
	private static SessionFacade instance = null;

	private Users currUserType;
	private Long accountID;
	private Scenes curr;
	private Scenes prev;

	private SessionFacade() {
		currUserType = null;
		curr = Scenes.MAIN;
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

	public Scenes getScreen() {
		return curr;
	}
	
	public void setCurrUserType(Users currUser) {
		this.currUserType = currUser;
	}

	public void setScreen(Scenes next) {
		this.prev = this.curr;
		this.curr = next;
	}
	
	public Scenes getPrevScreen() {
		return prev;
	}
	
	public Long getID() {
		return accountID;
	}

	public void setID(Long accountID) {
		this.accountID = accountID;
	}
}
