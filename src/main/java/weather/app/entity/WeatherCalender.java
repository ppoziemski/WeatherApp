package weather.app.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="WEATHER_CALENDER")
public class WeatherCalender {

	@Id
	@Column(name="CALENDERDATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate calenderdate;
	
	@ManyToOne
    @JoinColumn(name="WEATHER_TYPE_CD", nullable=false)
	private WeatherType weather;

	public WeatherCalender() {
	}
	
	public WeatherCalender(LocalDate calenderdate, WeatherType weather) {
		super();
		this.calenderdate = calenderdate;
		this.weather = weather;
	}

	public LocalDate getCalenderdate() {
		return calenderdate;
	}

	public void setCalenderdate(LocalDate calenderdate) {
		this.calenderdate = calenderdate;
	}

	public WeatherType getWeather() {
		return weather;
	}

	public void setWeather(WeatherType weather) {
		this.weather = weather;
	}
}
