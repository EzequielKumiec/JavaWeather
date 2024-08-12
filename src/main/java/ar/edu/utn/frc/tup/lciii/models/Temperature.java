package ar.edu.utn.frc.tup.lciii.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Temperature {
    private Long id;
    private Long location_id;
    private int temperature;
    private String unit;
    private Date created_at;
}
