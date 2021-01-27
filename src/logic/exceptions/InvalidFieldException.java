package logic.exceptions;

/**
 * @author dominique toce
 */

public class InvalidFieldException extends Exception {
	
	private static final long serialVersionUID = -6565885992454931282L;
	
	public InvalidFieldException(String message) {
		super(message);
	}
}
