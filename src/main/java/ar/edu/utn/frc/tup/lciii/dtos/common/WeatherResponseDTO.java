package ar.edu.utn.frc.tup.lciii.dtos.common;

import ar.edu.utn.frc.tup.lciii.models.Locations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponseDTO {
    private LocationDTO location;
    private TemperatureDTO temperature;
    private String wind;
    private AirQualityDTO air_quality;
    private CloudinessDTO cloudiness;

}
