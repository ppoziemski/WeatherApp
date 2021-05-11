package weather.app.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class HandlerExceptions {

	@ResponseBody
	@ExceptionHandler(IntValueLessOneException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String intValueLessOneHandler(IntValueLessOneException ex) {
	        return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(LocalDatesTwoValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String locaDatesTwoValidHandler(LocalDatesTwoValidException ex) {
	        return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(EmptyFieldWeatherCalenderException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String emptyFieldWeatherCalenderHandler(EmptyFieldWeatherCalenderException ex) {
	        return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(EmptyDateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String emptyDateHandler(EmptyDateException ex) {
	        return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(LocalDateFromFutureException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String localDateFromFutureHandler(LocalDateFromFutureException ex) {
	        return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(IncorrectWeatherTypeCdException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String incorrectWeatherTypeCdHandler(IncorrectWeatherTypeCdException ex) {
	        return ex.getMessage();
	}
}
