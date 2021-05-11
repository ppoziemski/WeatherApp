package weather.app.web.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import weather.app.service.WeatherCalenderService;
import weather.app.service.WeatherTypeService;
import weather.app.entity.*;
import weather.app.exceptions.EmptyDateException;
import weather.app.exceptions.EmptyFieldWeatherCalenderException;
import weather.app.exceptions.IncorrectWeatherTypeCdException;
import weather.app.exceptions.IntValueLessOneException;
import weather.app.exceptions.LocalDateFromFutureException;
import weather.app.exceptions.LocalDatesTwoValidException;


@RequestMapping("/Administration")
@Controller
public class WeatherControler {
	
	private final WeatherCalenderService 	weatherCalenderService;
	private final WeatherTypeService 		weatherTypeService;
	
	@Autowired
	public WeatherControler(	WeatherCalenderService weatherCalenderService, 
			WeatherTypeService weatherTypeService) {
		this.weatherCalenderService = weatherCalenderService;
		this.weatherTypeService = weatherTypeService;
	}
	
	/**
	 * Method get type of weather from dictionary
	 */
	public List<WeatherType> getListWeatherType() {
		return weatherTypeService.getAllWeatherType();
	}
	
	/**
	 * Return list of weather type 
	 * @param model
	 * @return
	 */
	@ModelAttribute(name = "weatherType")
    public List<WeatherType> addAttributesWeatherTypes(ModelMap model) {
        return getListWeatherType();
    }
	
	/**
	 * Start Web Page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/")
	public String viewStart(ModelMap model) {

		model.addAttribute("weatherCalender", 	new WeatherCalender()); 
		model.addAttribute("totalPages", 		0);

		return getWeatherInfoPagin(model, 1, 5);
			
	}
	
	/**
	 * Create or modify object WeatherCalender
	 * @param model
	 * @param weatherCalender
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addNewWeatherCalenderDay(ModelMap model, @ModelAttribute WeatherCalender weatherCalender) { 
		
		validationEmptyFieldsInWeatherCalender(weatherCalender);
		validationValuesFromWeather(weatherCalender,getListWeatherType());
		
		weatherCalenderService.saveWeather(weatherCalender);

		return "redirect:/Administration/";
	}
	
	/**
	 * Get list of objects WeatherCalender using pagination. Method return objects between day start and end dates
	 * @param model
	 * @param paginNo
	 * @param startDate
	 * @param endDate
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value= "/checkPage", method = RequestMethod.GET)
	public String getWeatherInfoPagin(ModelMap model, 	@RequestParam(name="pagin_no") 						int paginNo,
														@RequestParam(name="start_date", required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
														@RequestParam(name="end_date",  required=true)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)	LocalDate endDate,
														@RequestParam(name="page_size") 					int pageSize) {
		
		validationInt(new ArrayList<Integer>() {{add(paginNo);add(pageSize);}});
		validationLocalDates(startDate, endDate);
			
		Page<WeatherCalender> page = weatherCalenderService.getPaginWeatherBetweenDays(paginNo, pageSize, startDate, endDate);
		
		modelLoadPage(model, page);
		
		model.addAttribute("weatherCalender", 	new WeatherCalender());
		model.addAttribute("currentPage", 	paginNo);
		model.addAttribute("start_date", 	startDate);
		model.addAttribute("end_date", 		endDate);
		
		return "MainView";
	}
	
	/**
	 * Get list of all objects WeatherCalender using pagination.
	 * @param model
	 * @param paginNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value= "/autoCheckPagin", method = RequestMethod.GET)
	public String getWeatherInfoPagin(ModelMap model, 	@RequestParam(name="pagin_no") 	int paginNo,
														@RequestParam(name="page_size") int pageSize) {
		
		this.validationInt(new ArrayList<Integer>() {{add(paginNo);add(pageSize);}});
		
		Page<WeatherCalender> page = weatherCalenderService.getPaginWeatherAllDays(paginNo, pageSize);
		
		modelLoadPage(model, page);

		model.addAttribute("weatherCalender", 	new WeatherCalender());
		model.addAttribute("currentPage", 	paginNo);
		
		return "MainView";
	}
	
	
	/**
	 * Method Load parameters from object Page to ModelMap
	 * @param model
	 * @param page
	 */
	private void modelLoadPage(ModelMap model, Page<WeatherCalender> page) {
		model.addAttribute("weatherFilter",	page.getContent());
		model.addAttribute("totalPages", 	page.getTotalPages());
		model.addAttribute("totalItems", 	page.getTotalElements());
	}
	
	/**
	 * Method valid object Integers from list. If parameter less than 1 the throw error
	 * @param list
	 */
	private void validationInt(List<Integer> list) {
		int i=0;
		for(i=0;i<list.size();i++) {
			if(!(list.get(i) > 0)){
				throw new IntValueLessOneException(list.get(i));
			}
		}
	}
	
	/**
	 * Method valid parameters in object WeatherCalender
	 * @param weatherCalender
	 */
	private void validationEmptyFieldsInWeatherCalender(WeatherCalender weatherCalender) {
		LocalDate dt = weatherCalender.getCalenderdate();
		String typCd = weatherCalender.getWeather().getWeatherTypeCd();
		
		if(dt == null || typCd == null) {
			throw new EmptyFieldWeatherCalenderException(dt, typCd);
		}
	}
	
	/**
	 * If start and end dates are null or
	 * start date equal end date 
	 * then throw error
	 * @param startDate
	 * @param endDate
	 */
	private void validationLocalDates(LocalDate startDate, LocalDate endDate) {
		
		if(startDate == null || endDate == null) {
			throw new EmptyDateException();
		}
		
		if(startDate.compareTo(endDate) > 0) {
			throw new LocalDatesTwoValidException(startDate, endDate);
		}
	}
	
	/**
	 * If date of weather is from future or
	 * type of weather not match with dictionary rows (table WEATHER_TYPE) 
	 * then throw error
	 * @param weatherCalender
	 * @param weatherTypesList
	 */
	private void validationValuesFromWeather(WeatherCalender weatherCalender, List<WeatherType> weatherTypesList) {
		LocalDate weatherDate = weatherCalender.getCalenderdate();
		String weatherTypeCd = weatherCalender.getWeather().getWeatherTypeCd();
		
		if(weatherDate.compareTo(LocalDate.now()) > 0) {
			throw new LocalDateFromFutureException(weatherDate);
		}
		
		if( ! weatherTypesList.stream().anyMatch(list -> list.getWeatherTypeCd().equals(weatherTypeCd)) )  {
			throw new IncorrectWeatherTypeCdException(weatherTypeCd, weatherTypesList);
		}
	}
		

}
