package weather.app.exceptions;

import java.time.LocalDate;

public class LocalDateFromFutureException extends RuntimeException {

	private static final String message = "Date can not be from future. Date =";
	
	public LocalDateFromFutureException(LocalDate date) {
		super(message + date);
	}
	

}
