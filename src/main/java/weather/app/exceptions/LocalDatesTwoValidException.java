package weather.app.exceptions;

import java.time.LocalDate;

public class LocalDatesTwoValidException extends RuntimeException {

	private static final String message1 = "Incorrect values in LocalDate. START_DATE (";
	private static final String message2 = ") is later than END_DATE (";
	private static final String message3 = ")";

	public LocalDatesTwoValidException(LocalDate startDate, LocalDate endDate) {
		super(message1 + startDate + message2 + endDate + message3);
	}
	
}
