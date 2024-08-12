package ar.edu.utn.frc.tup.lciii.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    private Locations location;
    private Temperature temperature;
    private String wind;
    private AirQuality air_quality;
    private Cloudiness cloudiness;
}
