package weather.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import weather.app.entity.WeatherCalender;
import weather.app.repo.WeatherCalenderRepository;

@Service
public class WeatherCalenderService {
	
	@Autowired
	private WeatherCalenderRepository weatherCalenderRepository;
	
	/**
	 * Get only one object WeatherCalender
	 * @param day
	 * @return object WeatherCalender
	 */
	public Optional<WeatherCalender> getWeatherDay(LocalDate day) {
		return this.weatherCalenderRepository.findById(day);
	}

	/**
	 * Get many objects WeatherCalender
	 * @param StartDt
	 * @param EndDt
	 * @return list object WeatherCalender
	 */
	public List<WeatherCalender> getWeatherBetweenDays(LocalDate startDt, LocalDate endDt) {
		return (List<WeatherCalender>) this.weatherCalenderRepository.findWetherBetweenDays(startDt, endDt);
	}
	
	/**
	 * Create or if exist then modify object WeatherCalender
	 * @param newWeatherCalenderDay
	 */
	public void saveWeather(WeatherCalender newWeatherCalenderDay) {
		weatherCalenderRepository.save(newWeatherCalenderDay);
	}
	
	public Page<WeatherCalender> getPaginWeatherBetweenDays(int paginNum, int paginSize, LocalDate startDt, LocalDate endDt){
		Pageable pageable = PageRequest.of(paginNum-1, paginSize, sortByIdDescending() );
		return this.weatherCalenderRepository.findPaginWeatherBetweenDays(startDt, endDt, pageable);
	}
	
	public List<WeatherCalender> getListWeatherBetweenDaysWithPagin(int paginNo, int pageSize, LocalDate startDate, LocalDate endDate){
		Page<WeatherCalender> page = this.getPaginWeatherBetweenDays(	paginNo, 
																		pageSize, 
																		startDate,
																		endDate);
		return page.getContent();
	}
	
	public Page<WeatherCalender> getPaginWeatherAllDays(int paginNum, int paginSize){
		Pageable pageable = PageRequest.of(paginNum-1, paginSize, sortByIdDescending());
		return this.weatherCalenderRepository.findAll(pageable);
	}
	
	public List<WeatherCalender> getListWeatherAllDaysWithPagin(int paginNo, int pageSize){
		Page<WeatherCalender> page = this.getPaginWeatherAllDays(	paginNo, 
																	pageSize);
		return page.getContent();
	}
	
	private Sort sortByIdDescending() {
        return new Sort(Sort.Direction.DESC, "calenderdate");
    }
}
