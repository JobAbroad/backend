package jobabroad.exceptions;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2060462810999642020L;

	public UserNotFoundException() {
		
	}

	public UserNotFoundException(String message) {
		super(message);
	}
}
