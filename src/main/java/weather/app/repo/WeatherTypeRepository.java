package weather.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import weather.app.entity.WeatherType;

@Repository
public interface WeatherTypeRepository extends JpaRepository<WeatherType, String> {

}
