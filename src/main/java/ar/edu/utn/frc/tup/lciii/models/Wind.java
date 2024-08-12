package ar.edu.utn.frc.tup.lciii.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wind {
    private Long id;
    private Long location_id;
    private int speed;
    private int direction;
    private Date created_at;
}
