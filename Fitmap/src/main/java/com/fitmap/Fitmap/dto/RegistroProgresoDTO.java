package com.fitmap.Fitmap.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class RegistroProgresoDTO {
    @NotNull(message = "El usuarioId es obligatorio")
    private Long usuarioId;
    private String usuarioNombre;
    @NotNull(message = "El ejercicioId es obligatorio")
    private Long ejercicioId;
    private String ejercicioNombre;

    @Min(value = 1, message = "Las repeticiones deben ser al menos 1")
    private int repeticiones;

    @Min(value = 1, message = "El peso debe ser al menos 1")
    private double peso;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;
}
