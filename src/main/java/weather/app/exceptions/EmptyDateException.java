package weather.app.exceptions;

import java.time.LocalDate;

public class EmptyDateException extends RuntimeException {

	private static final String message = "Empty LocalDate in body";

	public EmptyDateException() {
		super(message);
	}
	

}
