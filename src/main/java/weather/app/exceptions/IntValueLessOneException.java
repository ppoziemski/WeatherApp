package weather.app.exceptions;

public class IntValueLessOneException extends RuntimeException {

	private static final String message = "Incorrect input values: ";

	public IntValueLessOneException(int value) {
		super(message + value);
	}
}
