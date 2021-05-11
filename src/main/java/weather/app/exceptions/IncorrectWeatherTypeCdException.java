package weather.app.exceptions;
import java.util.List;
import java.util.stream.Collectors;

import weather.app.entity.WeatherType;

public class IncorrectWeatherTypeCdException extends RuntimeException {

	private static final String message1 = "Value WeatherTypeCd = ";
	private static final String message2 = " not in list types. WeatherTypeCd list in ";

	public IncorrectWeatherTypeCdException(String weatherTypeCD, List<WeatherType> weatherTypes) {
		super(message1 + weatherTypeCD + message2 + weatherTypes.stream().map(list -> list.getWeatherTypeCd().toString() ).collect(Collectors.toList())  );
	}

}
