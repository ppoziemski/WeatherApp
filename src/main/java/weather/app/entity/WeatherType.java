package weather.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="WEATHER_TYPE")
public class WeatherType {

	@Id
	@GeneratedValue
	@Column(name="WEATHER_TYPE_CD")
	private String weatherTypeCd;
	
	@Column(name="TEMPERATURE_MIN")
	private int temperatureMin;
	@Column(name="TEMPERATURE_MAX")
	private int temperatureMax;
	
	@Column(name="IMAGE")
	@Length(min = 0, max = 50)
	private String image;
	
	public WeatherType() {
	}

	public WeatherType(String weatherTypeCd, int temperatureMin, int temperatureMax, String image) {
		this.weatherTypeCd = weatherTypeCd;
		this.temperatureMin = temperatureMin;
		this.temperatureMax = temperatureMax;
		this.image = image;
	}
	
	public String getWeatherTypeCd() {
		return weatherTypeCd;
	}
	public void setWeatherTypeCd(String weatherTypeCd) {
		this.weatherTypeCd = weatherTypeCd;
	}
	public int getTemperatureMin() {
		return temperatureMin;
	}
	public void setTemperatureMin(int temperatureMin) {
		this.temperatureMin = temperatureMin;
	}
	public int getTemperatureMax() {
		return temperatureMax;
	}
	public void setTemperatureMax(int temperatureMax) {
		this.temperatureMax = temperatureMax;
	}
	public String getImage() {
		return this.image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
