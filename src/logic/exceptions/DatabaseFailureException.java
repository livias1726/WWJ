package logic.exceptions;

public class DatabaseFailureException extends Exception {

	private static final long serialVersionUID = -8848305700376272314L;
	
	public DatabaseFailureException(String message) {
		super(message);
	}

}
