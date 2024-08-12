package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.WeatherResponseDTO;
import ar.edu.utn.frc.tup.lciii.entities.UserEntity;
import ar.edu.utn.frc.tup.lciii.models.Locations;
import ar.edu.utn.frc.tup.lciii.models.WeatherResponse;
import ar.edu.utn.frc.tup.lciii.services.UserService;
import ar.edu.utn.frc.tup.lciii.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private UserService userService;

    @GetMapping("/locations")
    public ResponseEntity<List<Locations>> getLocations(@RequestHeader("client_id") Long client_id, @RequestHeader("secret") String secret){
        Optional<UserEntity> user = userService.getUserById(client_id);

        if (user.isPresent() && user.get().getSecret().equals(secret)){
            List<Locations> locations = weatherService.getLocation();
            if (locations != null){
                return new ResponseEntity<>(locations, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/locations/datetime")
    public ResponseEntity<WeatherResponseDTO> getLocationsByDate(@RequestHeader("client_id") Long client_id, @RequestHeader("secret") String secret,@RequestHeader("idLocation") Long idLocation) {
        Optional<UserEntity> user = userService.getUserById(client_id);

        if (user.isPresent() && user.get().getSecret().equals(secret)){
            WeatherResponseDTO weatherResponse = weatherService.getLocationWithDatetimeFilter(idLocation);
            if (weatherResponse != null){
                return new ResponseEntity<>(weatherResponse, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
