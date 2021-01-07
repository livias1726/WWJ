package logic.application;

import java.util.ArrayList;
import java.util.List;

import logic.presentation.Scenes;

/**Singleton*/
public class SessionFacade {
	
	private static SessionFacade instance = null;

	private Users currUserType;
	private Long accountID;
	private Scenes curr;
	private List<Scenes> prev;
	private int counter = -1;

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
	
	public void setCurrUserType(Users currUser) {
		this.currUserType = currUser;
	}

	public void setScene(Scenes next) {
		if(counter == -1) {
			this.prev = new ArrayList<>();
		}else {
			this.prev.add(this.curr);
		}
		
		this.counter++;
		this.curr = next;
	}
	
	public Scenes getScene() {
		return curr;
	}

	public Scenes getPrevScene() {
		this.curr = prev.get(counter-1);
		prev.remove(counter-1);
		this.counter--;
		return curr;
	}
	
	public Long getID() {
		return accountID;
	}

	public void setID(Long accountID) {
		this.accountID = accountID;
	}
}
