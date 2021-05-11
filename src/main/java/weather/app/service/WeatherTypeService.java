package weather.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weather.app.entity.WeatherType;
import weather.app.repo.WeatherTypeRepository;

@Service
public class WeatherTypeService {
	
	@Autowired
	private WeatherTypeRepository weatherTypeRepository;
	
	public List<WeatherType> getAllWeatherType() {
		return this.weatherTypeRepository.findAll();
	}

}
