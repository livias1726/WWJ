package logic.exceptions;

/**
 * @author alison petrilli
 */

public class IncompleteAccountException extends Exception {
	private static final long serialVersionUID = 2995041583105312258L;

	public IncompleteAccountException(String message) {
		super(message);
	}
}
