package com.fitmap.Fitmap.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class UsuarioDTO {
    @NotNull(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "El email es obligatorio")
    private String email;
    private Set<RoleDTO> roles;
}
