package logic.exceptions;

/**
 * @author dominique toce
 */

public class DatabaseFailureException extends Exception {

	private static final long serialVersionUID = -8848305700376272314L;
	private static final String MESSAGE = "Something went wrong. Please, retry later.";
	
	public DatabaseFailureException() {
		super(MESSAGE);
	}

}
