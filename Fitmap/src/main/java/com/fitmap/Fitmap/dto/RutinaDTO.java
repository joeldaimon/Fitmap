package com.fitmap.Fitmap.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class RutinaDTO {
    @NotNull(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "El usuarioId es obligatorio")
    private Long usuarioId;
    private String usuarioNombre;
    private Set<EjercicioDTO> ejercicios;
}
