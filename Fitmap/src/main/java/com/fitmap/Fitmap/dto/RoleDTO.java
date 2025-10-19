package com.fitmap.Fitmap.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
    @NotNull(message = "El nombre es obligatorio")
    private String nombre;
}
