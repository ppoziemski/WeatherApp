package weather.app.exceptions;

import java.time.LocalDate;

public class EmptyFieldWeatherCalenderException  extends RuntimeException {

	private static final String message1 = "Empty fields in object WeatherCalender where CALENDERDATE = ";
	private static final String message2 = " and WEATHER_TYPE_CD =  ";

	public EmptyFieldWeatherCalenderException(LocalDate calenderdate, String weatherTypeCd) {
		super(message1 + calenderdate + message2 + weatherTypeCd);
	}
	
}
