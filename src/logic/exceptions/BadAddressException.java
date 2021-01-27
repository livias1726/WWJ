package logic.exceptions;

/**
 * @author alison petrilli
 */

public class BadAddressException extends Exception {
	
	private static final long serialVersionUID = 4662311012219224135L;

	public BadAddressException(String message) {
		super(message);
	}

}
