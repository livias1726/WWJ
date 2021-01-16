package logic.exceptions;

public class DuplicatedUserException extends Exception {

	private static final long serialVersionUID = -6317725911834964264L;
	private static final String MESSAGE = "User already signed. Change email or log in.";
	
	public DuplicatedUserException() {
		super(MESSAGE);
	}

}
