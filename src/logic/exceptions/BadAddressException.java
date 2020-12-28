package logic.exceptions;

public class BadAddressException extends Exception {

	private static final long serialVersionUID = -4371445452216162102L;
	
	public BadAddressException(String message) {
		super(message);
	}
}
