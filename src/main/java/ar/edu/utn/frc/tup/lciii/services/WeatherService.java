package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.WeatherResponseDTO;
import ar.edu.utn.frc.tup.lciii.models.Locations;
import ar.edu.utn.frc.tup.lciii.models.WeatherResponse;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public interface WeatherService {
    List<Locations> getLocation();
    WeatherResponseDTO getLocationWithDatetimeFilter(Long locataionId);
}
