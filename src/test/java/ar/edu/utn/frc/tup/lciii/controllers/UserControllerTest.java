package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.client.WeatherTemplate;
import ar.edu.utn.frc.tup.lciii.models.Locations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test") // Activar el perfil de pruebas
public class UserControllerTest {

    //test de itegracion
    @Autowired
    private WeatherTemplate weatherTemplate;
    @Test
    void getLocationDataIdTest(){
        List<Locations> result = weatherTemplate.getLocationData();

        assertEquals(2, Objects.requireNonNull(result.get(1).getId()));
    }
    @Test
    void getLocationDataTest(){
        List<Locations> result = weatherTemplate.getLocationData();
        Locations l = new Locations();
        l.setId(1L);
        l.setName("Location 1");
        l.setLatitude("40.7128");
        l.setLongitude("74.0060");
        assertEquals(l, Objects.requireNonNull(result.get(0)));
    }
}
