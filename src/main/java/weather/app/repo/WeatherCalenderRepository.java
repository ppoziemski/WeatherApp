package weather.app.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import weather.app.entity.WeatherCalender;

@Repository
public interface WeatherCalenderRepository extends JpaRepository<WeatherCalender, LocalDate> {

	@Query("SELECT w FROM WeatherCalender w WHERE w.calenderdate between :START_DT and :END_DT order by w.calenderdate DESC")
	public List<WeatherCalender> findWetherBetweenDays(@Param("START_DT") LocalDate StartDt,@Param("END_DT") LocalDate EndDt);
	
	@Query("SELECT w FROM WeatherCalender w WHERE w.calenderdate between :START_DT and :END_DT order by w.calenderdate DESC")
	public Page<WeatherCalender> findPaginWeatherBetweenDays(@Param("START_DT") LocalDate StartDt, @Param("END_DT") LocalDate EndDt, Pageable pageable);
}
