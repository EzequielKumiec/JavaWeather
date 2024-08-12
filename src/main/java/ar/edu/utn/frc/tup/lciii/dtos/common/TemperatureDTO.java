package ar.edu.utn.frc.tup.lciii.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureDTO {
    private int value;
    private String unit;
}
