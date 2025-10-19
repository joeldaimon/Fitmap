package com.fitmap.Fitmap.mapper;

import com.fitmap.Fitmap.dto.RutinaDTO;
import com.fitmap.Fitmap.model.Rutina;
import com.fitmap.Fitmap.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring", uses = {EjercicioMapper.class})
public interface RutinaMapper {

    @Mapping(target = "usuarioId", source = "usuario", qualifiedByName = "usuarioToId")
    @Mapping(target = "usuarioNombre", source = "usuario", qualifiedByName = "usuarioNombre")
    @Mapping(target = "ejercicios", source = "ejercicios")
    RutinaDTO toDTO(Rutina rutina);

    @Mapping(target = "usuario", ignore = true)
    Rutina toEntity(RutinaDTO dto);

    @Named("usuarioToId")
    default Long MapUsuario(Usuario usuario) {
        if (usuario == null) return null;
        return usuario.getId();
    }

    @Named("usuarioNombre")
    default String MapUsuarioNombre(Usuario usuario) {
        if(usuario == null) return null;
        return usuario.getNombre ();
    }
}
