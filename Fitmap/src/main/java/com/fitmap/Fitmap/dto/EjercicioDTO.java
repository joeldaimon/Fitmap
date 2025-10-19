package com.fitmap.Fitmap.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EjercicioDTO {
    @NotNull(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "La categoría es obligatoria")
    private String categoria;
    @Min(value = 1, message = "La dificultad debe ser al menos 1")
    private int dificultad;
}
