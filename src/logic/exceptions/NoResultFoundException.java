package logic.exceptions;

/**
 * @author livia simoncini
 */

public class NoResultFoundException extends Exception {

	private static final long serialVersionUID = -5551754633962310941L;
	private static final String MESSAGE = "No results found.";
	
	public NoResultFoundException() {
		super(MESSAGE);
	}

	public NoResultFoundException(String string) {
		super(string);
	}

}
