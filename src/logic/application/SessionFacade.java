package logic.application;

import logic.presentation.Screens;

public class SessionFacade {
	private static SessionFacade instance = null;

	private String currUser;
	private Screens curr;
	private Screens prev;

	private SessionFacade() {
		currUser = "";
		curr = Screens.MAIN;
	}
	
	public static SessionFacade getSession() {
		if (instance == null) 
			instance = new SessionFacade();
			
		return instance;
	}
	
	public String getCurrUser() {
		return currUser;
	}

	public Screens getScreen() {
		return curr;
	}
	
	public void setCurrUser(String currUser) {
		this.currUser = currUser;
	}

	public void setScreen(Screens next) {
		this.prev = this.curr;
		this.curr = next;
	}
	
	public Screens getPrevView() {
		return prev;
	}
}
