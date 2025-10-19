package com.fitmap.Fitmap.mapper;

import com.fitmap.Fitmap.dto.RegistroProgresoDTO;
import com.fitmap.Fitmap.model.Ejercicio;
import com.fitmap.Fitmap.model.RegistroProgreso;
import com.fitmap.Fitmap.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface RegistroProgresoMapper {

    @Mapping(target = "usuarioId", source = "usuario", qualifiedByName = "usuarioToId")
    @Mapping(target = "ejercicioId", source = "ejercicio", qualifiedByName = "ejercicioToId")
    @Mapping(target = "usuarioNombre", source = "usuario", qualifiedByName = "usuarioNombre")
    @Mapping(target = "ejercicioNombre", source = "ejercicio", qualifiedByName = "ejercicioNombre")
    RegistroProgresoDTO toDTO(RegistroProgreso entity);

    RegistroProgreso toEntity(RegistroProgresoDTO dto);

    @Named("usuarioToId")
    default Long MapUsuario(Usuario usuario) {
        if (usuario == null) return null;
        return usuario.getId();
    }

    @Named("ejercicioToId")
    default Long MapEjercicio(Ejercicio ejercicio) {
        if (ejercicio == null) return null;
        return ejercicio.getId();
    }

    @Named("usuarioNombre")
    default String MapUsuarioNombre(Usuario usuario) {
        if(usuario == null) return null;
        return usuario.getNombre ();
    }

    @Named("ejercicioNombre")
    default String MapEjercicioNombre(Ejercicio ejercicio) {
        if(ejercicio == null) return null;
        return ejercicio.getNombre ();
    }
}
