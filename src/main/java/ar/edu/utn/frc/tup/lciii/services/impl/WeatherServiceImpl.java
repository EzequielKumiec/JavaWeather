package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.client.WeatherTemplate;
import ar.edu.utn.frc.tup.lciii.dtos.common.*;
import ar.edu.utn.frc.tup.lciii.models.*;
import ar.edu.utn.frc.tup.lciii.services.WeatherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WeatherTemplate weatherTemplate;

    @Override
    public List<Locations> getLocation() {
        return weatherTemplate.getLocationData();
    }

    @Override
    public WeatherResponseDTO getLocationWithDatetimeFilter(Long locataionId) {
        List<Locations> locations = weatherTemplate.getLocationData();
        List<Wind> winds = weatherTemplate.getAirWindData();
        List<Cloudiness> cloudinesses = weatherTemplate.getAirCloudinessData();
        List<AirQuality> airQualities = weatherTemplate.getAirQualityData();
        List<Temperature> temperatures = weatherTemplate.getTemperatureData();

        // Obtener los objetos correspondientes a la ubicación
        Locations location = locations.stream()
                .filter(loc -> loc.getId() == locataionId)
                .findFirst()
                .orElse(null);
        Wind wind = winds.stream()
                .filter(w -> w.getId() == locataionId)
                .findFirst()
                .orElse(null);
        Temperature temperature = temperatures.stream()
                .filter(t -> t.getId() == locataionId)
                .findFirst()
                .orElse(null);
        Cloudiness cloudiness = cloudinesses.stream()
                .filter(c -> c.getId() == locataionId)
                .findFirst()
                .orElse(null);
        AirQuality airQuality = airQualities.stream()
                .filter(aq -> aq.getId() == locataionId)
                .findFirst()
                .orElse(null);

        // Crear una instancia de WeatherResponseDTO y establecer los valores manualmente
        WeatherResponseDTO weatherResponseDTO = new WeatherResponseDTO();
        if (location != null) {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setId(location.getId());
            locationDTO.setName(location.getName());
            weatherResponseDTO.setLocation(locationDTO);
        }
        if (wind != null) {
            String windDirection = convertirStringDeWind(wind);
            weatherResponseDTO.setWind(windDirection);
        }
        if (temperature != null) {
            TemperatureDTO temperatureDTO = new TemperatureDTO();
            temperatureDTO.setValue(temperature.getTemperature());
            temperatureDTO.setUnit(temperature.getUnit());
            weatherResponseDTO.setTemperature(temperatureDTO);
        }
        if (cloudiness != null) {
            String cloudinessDescription = getCloudinessDescription(cloudiness.getCloudiness());
            CloudinessDTO cloudinessDTO = new CloudinessDTO();
            cloudinessDTO.setIndex(cloudiness.getCloudiness());
            cloudinessDTO.setDescription(cloudinessDescription);
            weatherResponseDTO.setCloudiness(cloudinessDTO);
        }
        if (airQuality != null) {
            String airQualityDescription = getAirQualityDescription(airQuality.getQuality());
            AirQualityDTO airQualityDTO = new AirQualityDTO();
            airQualityDTO.setIndex(airQuality.getQuality());
            airQualityDTO.setDescription(airQualityDescription);
            weatherResponseDTO.setAir_quality(airQualityDTO);
        }

        return weatherResponseDTO;
    }
    public static String getAirQualityDescription(int airQualityIndex) {
        if (airQualityIndex >= 0 && airQualityIndex <= 50) {
            return "Good";
        } else if (airQualityIndex >= 51 && airQualityIndex <= 100) {
            return "Moderate";
        } else if (airQualityIndex >= 101 && airQualityIndex <= 150) {
            return "Unhealthy for Sensitive Groups";
        } else if (airQualityIndex >= 151 && airQualityIndex <= 200) {
            return "Unhealthy";
        } else if (airQualityIndex >= 201 && airQualityIndex <= 300) {
            return "Very Unhealthy";
        } else if (airQualityIndex >= 301 && airQualityIndex <= 500) {
            return "Hazardous";
        } else {
            return "Unknown";
        }
    }

    public static String getCloudinessDescription(int cloudinessIndex) {
        if (cloudinessIndex == 0) {
            return "Clear sky";
        } else if (cloudinessIndex >= 1 && cloudinessIndex <= 3) {
            return "Few clouds";
        } else if (cloudinessIndex >= 4 && cloudinessIndex <= 6) {
            return "Sky half cloudy";
        } else if (cloudinessIndex >= 7 && cloudinessIndex <= 8) {
            return "Sky completely cloudy";
        } else {
            return "Unknown";
        }
    }

    public String convertirStringDeWind(Wind wind){
        return wind.getSpeed() + "Km/h from " + getFromWhere(wind);
    }

    public String getFromWhere(Wind wind){
        if (wind.getDirection()< 25){
            return "North";
        }else if (wind.getDirection()> 25 && wind.getDirection()< 50){
            return "Este";
        }else if (wind.getDirection()> 50 && wind.getDirection()< 75){
            return "Oeste";
        }else {
            return "Sur";
        }
    }

}
